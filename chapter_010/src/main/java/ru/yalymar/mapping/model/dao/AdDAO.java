package ru.yalymar.mapping.model.dao;

import org.hibernate.Session;
import ru.yalymar.mapping.model.*;
import ru.yalymar.mapping.model.unproxy.Unproxy;
import java.util.List;
import java.util.Set;

public class AdDAO extends DAO<Ad> implements Unproxy {

    public int create(Ad ad) {
//        int i = super.create.daoCreate(ad);
//        int id = -1;
//        if (i > 0) {
//            id = ad.getId();
//        }
//        return id;
        return 0;
    }

    @Override
    public Ad daoRead(int id) {
        Session session = null;
        try {
            session = super.sessionFactory.openSession();
            Ad ad = session.get(Ad.class, id);
            Car car = (Car) this.initializeAndUnproxy(ad.getCar());
            User user = (User) this.initializeAndUnproxy(ad.getUser());
            Set<Image> images = (Set<Image>) this.initializeAndUnproxy(ad.getImages());
            ad.setCar(car);
            ad.setUser(user);
            ad.setImages(images);
            return ad;
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public List<Ad> daoReadAll() {
        Session session = null;
        try{
            session = super.sessionFactory.openSession();
            List<Ad> ads = session.createQuery("from Ad").list();
            for(Ad ad : ads){
                Car car = (Car) this.initializeAndUnproxy(ad.getCar());
                User user = (User) this.initializeAndUnproxy(ad.getUser());
                Set<Image> images = (Set<Image>) this.initializeAndUnproxy(ad.getImages());
                ad.setCar(car);
                ad.setUser(user);
                ad.setImages(images);
            }
            return ads;
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public int update(int id, Ad newAd) {
        int i = 0;
        Ad ad = this.daoRead(id);
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
        super.update.daoUpdate(ad);
        return i;
    }

    public int delete(int id) {
        Ad ad = this.daoRead(id);
        Session session = null;
        try {
            session = super.sessionFactory.openSession();
            session.beginTransaction();
            session.delete(ad);
            session.getTransaction().commit();
            return 1;
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

}
