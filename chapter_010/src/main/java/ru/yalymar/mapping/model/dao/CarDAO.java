package ru.yalymar.mapping.model.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.yalymar.mapping.model.*;
import ru.yalymar.mapping.model.unproxy.Unproxy;
import java.util.List;

public class CarDAO extends DAO<Car> implements Unproxy {

    public int create(Car car) {
        int i = super.create.daoCreate(car);
        int id = -1;
        if (i > 0) {
            id = car.getId();
        }
        return id;
    }

    @Override
    public Car daoRead(int id) {
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
    public List<Car> daoReadAll() {
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

    public int update(int id, Car newCar) {
        int i = 0;
        Car car = this.daoRead(id);
        if (newCar.getModel() != null) {
            car.setModel(newCar.getModel());
            i++;
        }
        if (newCar.getTransmission() != null) {
            car.setTransmission(newCar.getTransmission());
            i++;
        }
        if (newCar.getBody() != null) {
            car.setBody(newCar.getBody());
            i++;
        }
        if (newCar.getColor() != null) {
            car.setColor(newCar.getColor());
            i++;
        }
        super.update.daoUpdate(car);
        return i;
    }

    public int delete(int id) {
        String query = String.format("daoDelete Car where id = %d", id);
        int i = super.delete.daoDelete(query);
        return i;
    }

}
