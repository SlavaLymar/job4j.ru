package ru.yalymar.mapping.model.dao;

import org.hibernate.query.Query;
import ru.yalymar.mapping.model.*;
import ru.yalymar.mapping.model.unproxy.Unproxy;
import java.util.List;

public class CarDAO extends DAO<Car> implements Unproxy {

    public int create(Car car) {
        return super.tx(session -> (int) session.save(car));
    }

    public Car read(int id) {
        Car car = super.tx(session -> {
            Car c = session.get(Car.class, id);
            Model model = (Model) this.initializeAndUnproxy(c.getModel());
            Transmission transmission = (Transmission) this.initializeAndUnproxy(c.getTransmission());
            Body body = (Body) this.initializeAndUnproxy(c.getBody());
            Color color = (Color) this.initializeAndUnproxy(c.getColor());
            c.setModel(model);
            c.setTransmission(transmission);
            c.setBody(body);
            c.setColor(color);
            return c;
        });
        return car;
    }

    public List<Car> readAll() {
        List<Car> cs = super.tx(session -> {
            List<Car> cars = session.createQuery("from Car").list();
            for (Car car : cars) {
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
        });
        return cs;
    }

    public int update(int id, Car newCar) {
        int i = 0;
        Car car = this.read(id);
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
        if(i > 0) {
            super.tx(session -> {
                Query query = session.createQuery("update Car set transmission = :t, " +
                        "body = :b, color = :c where id = :id");
                query.setParameter("t", car.getTransmission());
                query.setParameter("b", car.getBody());
                query.setParameter("c", car.getColor());
                query.setParameter("id", id);
                return query.executeUpdate();
            });
        }
        return i;
    }

    public int delete(int id) {
        return super.tx(session -> {
            Query query = session.createQuery("delete Car where id = :id");
            query.setParameter("id", id);
            return query.executeUpdate();
        });
    }

}
