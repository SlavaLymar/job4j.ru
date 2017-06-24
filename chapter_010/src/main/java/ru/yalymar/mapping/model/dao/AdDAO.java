package ru.yalymar.mapping.model.dao;

import org.hibernate.Criteria;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.yalymar.mapping.model.dao.fileuploader.Upload;
import ru.yalymar.mapping.model.dao.unproxy.Unproxy;
import ru.yalymar.mapping.model.models.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author slavalymar
 * @since 19.06.2017
 * @version 1
 */
public class AdDAO extends DAO<Ad> implements Unproxy, Upload {

    public int create(Ad ad) {
        return super.tx(session -> (int) session.save(ad));
    }

    public Ad read(int id) {
        Ad ad = super.tx(session -> {
            Ad a = session.get(Ad.class, id);

            Car car = (Car) this.initializeAndUnproxy(a.getCar());
            Model model = (Model) this.initializeAndUnproxy(car.getModel());
            Manufactor manufactor = (Manufactor) this.initializeAndUnproxy(model.getManuf());
            model.setManuf(manufactor);
            Transmission transmission = (Transmission) this.initializeAndUnproxy(car.getTransmission());
            Body body = (Body) this.initializeAndUnproxy(car.getBody());
            Color color = (Color) this.initializeAndUnproxy(car.getColor());
            car.setModel(model);
            car.setTransmission(transmission);
            car.setBody(body);
            car.setColor(color);

            User user = (User) this.initializeAndUnproxy(a.getUser());
            Set<Image> images = (Set<Image>) this.initializeAndUnproxy(a.getImages());
            a.setCar(car);
            a.setUser(user);
            a.setImages(images);
            return a;
        });
        return ad;
    }

    public List<Ad> readAll() {
        List<Ad> ads = super.tx(session -> {
            List<Ad> as = session.createQuery("from Ad").list();
            for (Ad ad : as) {
                Car car = (Car) this.initializeAndUnproxy(ad.getCar());
                Model model = (Model) this.initializeAndUnproxy(car.getModel());
                Manufactor manufactor = (Manufactor) this.initializeAndUnproxy(model.getManuf());
                model.setManuf(manufactor);
                Transmission transmission = (Transmission) this.initializeAndUnproxy(car.getTransmission());
                Body body = (Body) this.initializeAndUnproxy(car.getBody());
                Color color = (Color) this.initializeAndUnproxy(car.getColor());
                car.setModel(model);
                car.setTransmission(transmission);
                car.setBody(body);
                car.setColor(color);

                User user = (User) this.initializeAndUnproxy(ad.getUser());
                Set<Image> images = (Set<Image>) this.initializeAndUnproxy(ad.getImages());
                ad.setCar(car);
                ad.setUser(user);
                ad.setImages(images);
            }
            return as;
        });
        return ads;
    }

    public int update(int id, Ad newAd) {
        int i = 0;
        Ad ad = this.read(id);
        if (newAd.getTittle() != null) {
            ad.setTittle(newAd.getTittle());
            i++;
        }
        if (newAd.getCar() != null) {
            ad.setCar(newAd.getCar());
            i++;
        }
        if (newAd.isDone()) {
            ad.setDone(newAd.isDone());
            i++;
        }
        if(i > 0){
            super.tx(session -> {
                session.update(ad);
                return -1;
            });
        }
        return i;
    }

    public int delete(int id) {
        Ad ad = this.read(id);
        return super.tx(session -> {
            session.delete(ad);
            return 1;
        });
    }

    @Override
    public Set<Image> getFiles(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        Set<Image> result = new HashSet<>();
        String appPath = req.getServletContext().getRealPath("");
        String savePath =  appPath + "uploadfiles";
        File fileDir = new File(savePath);
        if(!fileDir.exists()){
            fileDir.mkdir();
        }

        Collection<Part> items = req.getParts();
        for(Part part : items){
            String fileName = this.extractFileName(part);
            if(fileName != null && !fileName.equals("")){
                part.write(savePath + File.separator + fileName);
                result.add(new Image(savePath + File.separator + fileName));
            }
        }
        return result;
    }

    private String extractFileName(Part part){
        String result = null;
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for(String s : items){
            if(s.trim().startsWith("filename")){
                result = s.substring(s.indexOf("=") + 2, s.length()-1);
                break;
            }
        }
        return result;
    }

    public List<Ad> getAdByFilters(Map<String, String> filterData) {
        List<Ad> result = this.readAll();

        String manuf = filterData.get("manuf");
        if(manuf != null && !manuf.equals("")){
            for(int i = 0; i<result.size(); i++){
                if(!manuf.equals(result.get(i).getCar().getModel().getManuf().getManuf())){
                    result.remove(i);
                    i--;
                }
            }
        }

        String model = filterData.get("model");
        if(model != null && !model.equals("")){
            for(int i = 0; i<result.size(); i++){
                if(!model.equals(result.get(i).getCar().getModel().getModel())){
                    result.remove(i);
                    i--;
                }
            }
        }

        String transmission = filterData.get("transmission");
        if(transmission != null && !transmission.equals("")){
            for(int i = 0; i<result.size(); i++){
                if(!transmission.equals(result.get(i).getCar().getTransmission().getName())){
                    result.remove(i);
                    i--;
                }
            }
        }

        String body = filterData.get("body");
        if(body != null && !body.equals("")){
            for(int i = 0; i<result.size(); i++){
                if(!body.equals(result.get(i).getCar().getBody().getBody())){
                    result.remove(i);
                    i--;
                }
            }
        }

        String color = filterData.get("color");
        if(color != null && !color.equals("")){
            for(int i = 0; i<result.size(); i++){
                if(!color.equals(result.get(i).getCar().getColor().getColor())){
                    result.remove(i);
                    i--;
                }
            }
        }

        String from = filterData.get("from");
        String to = filterData.get("to");
        if(!from.equals("") && !to.equals("")){
            for(int i = 0; i<result.size(); i++){
                int price = result.get(i).getPrice();
                if(price < Integer.parseInt(from) && price > Integer.parseInt(to)){
                    result.remove(i);
                    i--;
                }
            }
        }
        else if(from.equals("") && !to.equals("")){
            for(int i = 0; i<result.size(); i++){
                int price = result.get(i).getPrice();
                if(price < 0 && price > Integer.parseInt(to)){
                    result.remove(i);
                    i--;
                }
            }
        }
        else if(!from.equals("") && to.equals("")){
            for(int i = 0; i<result.size(); i++){
                int price = result.get(i).getPrice();
                if(price < Integer.parseInt(from)){
                    result.remove(i);
                    i--;
                }
            }
        }
        return result;
    }

}
