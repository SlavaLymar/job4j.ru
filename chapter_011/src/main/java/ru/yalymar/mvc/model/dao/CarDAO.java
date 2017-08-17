package ru.yalymar.mvc.model.dao;

import ru.yalymar.mvc.model.models.*;

import java.util.List;

public class CarDAO extends DAO<Car>{

    public int create(Car car) {
        return super.action(hibernateTemplate -> (int) hibernateTemplate.save(car));
    }

    public Car read(int id) {
        Car car = super.action(hibernateTemplate -> {
            Car c = hibernateTemplate.get(Car.class, id);
            return c;
        });
        return car;
    }

    public List<Car> readAll() {
        return super.action(hibernateTemplate -> hibernateTemplate.loadAll(Car.class));
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
            super.action(hibernateTemplate -> {
                hibernateTemplate.update(car);
                return -1;
            });
        }
        return i;
    }

    public int delete(int id) {
        Car car = this.read(id);
        return super.action(hibernateTemplate -> {
            hibernateTemplate.delete(car);
            return 1;
        });
    }

}
