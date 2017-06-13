package ru.yalymar.mapping.model.dao;

import org.hibernate.query.Query;
import ru.yalymar.mapping.model.*;
import ru.yalymar.mapping.model.unproxy.Unproxy;
import java.util.List;
import java.util.Set;

public class AdDAO extends DAO<Ad> implements Unproxy {

    public int create(Ad ad) {
        return super.tx(session -> (int) session.save(ad));
    }

    public Ad read(int id) {
        Ad ad = super.tx(session -> session.get(Ad.class, id));
        Car car = (Car) this.initializeAndUnproxy(ad.getCar());
        User user = (User) this.initializeAndUnproxy(ad.getUser());
        Set<Image> images = (Set<Image>) this.initializeAndUnproxy(ad.getImages());
        ad.setCar(car);
        ad.setUser(user);
        ad.setImages(images);
        return ad;
    }

    public List<Ad> readAll() {
        List<Ad> ads = super.tx(session -> session.createQuery("from Ad").list());
        for (Ad ad : ads) {
            Car car = (Car) this.initializeAndUnproxy(ad.getCar());
            User user = (User) this.initializeAndUnproxy(ad.getUser());
            Set<Image> images = (Set<Image>) this.initializeAndUnproxy(ad.getImages());
            ad.setCar(car);
            ad.setUser(user);
            ad.setImages(images);
        }
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
        return super.tx(session -> {
            Query query = session.createQuery("delete Ad where id = :id");
            query.setParameter("id", id);
            return query.executeUpdate();
        });
    }

}
