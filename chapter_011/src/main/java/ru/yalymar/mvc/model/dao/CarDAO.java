package ru.yalymar.mvc.model.dao;

import org.hibernate.query.Query;
import ru.yalymar.mvc.model.models.*;

import java.util.List;

/**
 * @author slavalymar
 * @since 19.06.2017
 * @version 1
 */
public class CarDAO extends DAO<Car>{

    public int create(Car car) {
        return super.tx(session -> (int) session.save(car));
    }

    public Car read(int id) {
        Car car = super.tx(session -> {
            Car c = session.get(Car.class, id);
            return c;
        });
        return car;
    }

    public List<Car> readAll() {
        List<Car> cs = super.tx(session -> {
            List<Car> cars = session.createQuery("from Car").list();
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
                session.update(car);
                return -1;
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
