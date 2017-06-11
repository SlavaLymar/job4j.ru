package ru.yalymar.mapping.model.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.yalymar.mapping.model.*;
import ru.yalymar.mapping.model.unproxy.Unproxy;
import java.util.List;

public class CarDAO extends DAO<Car> implements Unproxy {

    @Override
    public int create(Car car) {
        Session session = null;
        try {
            session = super.sessionFactory.openSession();
            session.beginTransaction();
            int i = (Integer) session.save(car);
            int id = -1;
            if(i > 0){
                id = car.getId();
            }
            session.getTransaction().commit();
            return id;
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public Car read(int id) {
        Session session = null;
        try {
            session = super.sessionFactory.openSession();
            Car car = session.get(Car.class, id);
            Model model = (Model) this.initializeAndUnproxy(car.getModel());
            Transmission transmission = (Transmission) this.initializeAndUnproxy(car.getTransmission());
            Body body = (Body) this.initializeAndUnproxy(car.getBody());
            Color color = (Color) this.initializeAndUnproxy(car.getColor());
            car.setModel(model);
            car.setTransmission(transmission);
            car.setBody(body);
            car.setColor(color);
            return car;
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public List<Car> readAll() {
        Session session = null;
        try{
            session = super.sessionFactory.openSession();
            List<Car> cars = session.createQuery("from Car").list();
            for(Car car : cars){
                Model model = (Model) this.initializeAndUnproxy(car.getModel());
                Transmission transmission = (Transmission) this.initializeAndUnproxy(car.getTransmission());
                Body body = (Body) this.initializeAndUnproxy(car.getBody());
                Color color = (Color) this.initializeAndUnproxy(car.getColor());
                car.setModel(model);
                car.setTransmission(transmission);
                car.setBody(body);
                car.setColor(color);
            }
            return cars;
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public int update(int id, Car newCar) {
        Session session = null;
        int i = 0;
        try{
            session = super.sessionFactory.openSession();
            session.beginTransaction();
            Car car = session.get(Car.class, id);
            if(newCar.getModel() != null) {
                car.setModel(newCar.getModel());
                i++;
            }
            if(newCar.getTransmission() != null) {
                car.setTransmission(newCar.getTransmission());
                i++;
            }
            if(newCar.getBody() != null){
                car.setBody(newCar.getBody());
                i++;
            }
            if(newCar.getColor() != null){
                car.setColor(newCar.getColor());
                i++;
            }
            session.update(newCar);
            session.getTransaction().commit();
            return i;
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public int delete(int id) {
        Session session = null;
        int i;
        try {
            session = super.sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery(
                    "delete Car where id = :i");
            query.setParameter("i", id);
            i = query.executeUpdate();
            session.getTransaction().commit();
            return i;
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

}
