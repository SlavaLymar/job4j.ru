package ru.yalymar.mapping.model.dao;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.query.Query;
import ru.yalymar.mapping.model.*;
import ru.yalymar.mapping.model.dao.fileuploader.Upload;
import ru.yalymar.mapping.model.unproxy.Unproxy;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class AdDAO extends DAO<Ad> implements Unproxy, Upload {

    public int create(Ad ad) {
        return super.tx(session -> (int) session.save(ad));
    }

    public Ad read(int id) {
        Ad ad = super.tx(session -> {
            Ad a = session.get(Ad.class, id);
            Car car = (Car) this.initializeAndUnproxy(a.getCar());
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
                Query query = session.createQuery("update Ad set tittle = :t, " +
                        "car = :c, done = :d where id = :id");
                query.setParameter("t", ad.getTittle());
                query.setParameter("c", ad.getCar());
                query.setParameter("d", ad.isDone());
                query.setParameter("id", id);
                return query.executeUpdate();
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
    public Set<Image> getFiles(HttpServletRequest req, HttpServletResponse resp,
                               ServletContext context) throws IOException {
        Set<Image> result = new HashSet<>();

        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (!isMultipart) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return result;
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024*1024);
        File tempDir = (File)context.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(tempDir);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(1024 * 1024);
        try {
            List items = upload.parseRequest(req);
            Iterator iter = items.iterator();

            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (!item.isFormField()) {
                    File uploadFile;
                    Random random = new Random();

                    String path;
                    do{
                        path = context.
                                getRealPath("mapping/upload/"+ random.nextInt() + item.getName());
                        uploadFile = new File(path);
                    }
                    while(uploadFile.exists());

                    uploadFile.createNewFile();
                    item.write(uploadFile);

                    result.add(new Image(path));
                }
            }
        }
        catch (FileUploadException e){
            DAOFactory.logger.error(e.getMessage(), e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return result;
        }
        catch (Exception e) {
            DAOFactory.logger.error(e.getMessage(), e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return result;
        }
        return result;
    }

}
