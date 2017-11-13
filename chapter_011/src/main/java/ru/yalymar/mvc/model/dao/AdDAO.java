package ru.yalymar.mvc.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.yalymar.mvc.model.dao.fileuploader.Upload;
import ru.yalymar.mvc.model.models.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class AdDAO extends DAO<Ad> implements Upload {

    private DAOFactory daoFactory;

    @Autowired
    public void setDaoFactory(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public int create(Ad ad) {
        return super.action(hibernateTemplate -> (int) hibernateTemplate.save(ad));
    }

    public Ad read(int id) {
        return super.action(hibernateTemplate -> hibernateTemplate.get(Ad.class, id));
    }

    public List<Ad> readAll() {
        return super.action(hibernateTemplate -> hibernateTemplate.loadAll(Ad.class));
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
        if (i > 0) {
            super.action(hibernateTemplate -> {
                hibernateTemplate.update(ad);
                return -1;
            });
        }
        return i;
    }

    public int delete(int id) {
        Ad ad = this.read(id);
        return super.action(hibernateTemplate -> {
            hibernateTemplate.delete(ad);
            return 1;
        });
    }

    @Override
    public Set<Image> getFiles(HttpServletRequest req, MultipartFile[] files)
            throws IOException, ServletException {
        Set<Image> result = new HashSet<>();
        String appPath = req.getServletContext().getRealPath("");
        String savePath = appPath + "uploadfiles";
        File fileDir = new File(savePath);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }

        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            if (fileName != null && !fileName.equals("")) {
                fileName = check(fileName);
                file.transferTo(new File(savePath + File.separator + fileName));
                result.add(new Image(savePath + File.separator + fileName));
            }
        }
        return result;
    }

    private String check(String fileName) {
        String finalFileName = fileName;
        if (this.daoFactory.getImageDAO()
                .readAll()
                .stream()
                .anyMatch(image -> image.getUrl().endsWith(finalFileName))
                ) {
            String[] names = fileName.split("\\.");
            fileName = check(String.format("%s%d.%s", names[0], (new Random().nextInt() * 10), names[1]));
        }
        return fileName;
    }


    public List<Ad> getAdByFilters(Map<String, String> filterData) {
        List<Ad> result = this.readAll();

        String manuf = filterData.get("manuf");
        if (manuf != null && !manuf.equals("")) {
            for (int i = 0; i < result.size(); i++) {
                if (!manuf.equals(result.get(i).getCar().getModel().getManuf().getManuf())) {
                    result.remove(i);
                    i--;
                }
            }
        }

        String model = filterData.get("model");
        if (model != null && !model.equals("")) {
            for (int i = 0; i < result.size(); i++) {
                if (!model.equals(result.get(i).getCar().getModel().getModel())) {
                    result.remove(i);
                    i--;
                }
            }
        }

        String transmission = filterData.get("transmission");
        if (transmission != null && !transmission.equals("")) {
            for (int i = 0; i < result.size(); i++) {
                if (!transmission.equals(result.get(i).getCar().getTransmission().getName())) {
                    result.remove(i);
                    i--;
                }
            }
        }

        String body = filterData.get("body");
        if (body != null && !body.equals("")) {
            for (int i = 0; i < result.size(); i++) {
                if (!body.equals(result.get(i).getCar().getBody().getBody())) {
                    result.remove(i);
                    i--;
                }
            }
        }

        String color = filterData.get("color");
        if (color != null && !color.equals("")) {
            for (int i = 0; i < result.size(); i++) {
                if (!color.equals(result.get(i).getCar().getColor().getColor())) {
                    result.remove(i);
                    i--;
                }
            }
        }

        String from = filterData.get("from");
        String to = filterData.get("to");
        if (!from.equals("") && !to.equals("")) {
            for (int i = 0; i < result.size(); i++) {
                int price = result.get(i).getPrice();
                if (price < Integer.parseInt(from) && price > Integer.parseInt(to)) {
                    result.remove(i);
                    i--;
                }
            }
        } else if (from.equals("") && !to.equals("")) {
            for (int i = 0; i < result.size(); i++) {
                int price = result.get(i).getPrice();
                if (price < 0 && price > Integer.parseInt(to)) {
                    result.remove(i);
                    i--;
                }
            }
        } else if (!from.equals("") && to.equals("")) {
            for (int i = 0; i < result.size(); i++) {
                int price = result.get(i).getPrice();
                if (price < Integer.parseInt(from)) {
                    result.remove(i);
                    i--;
                }
            }
        }
        return result;
    }

}
