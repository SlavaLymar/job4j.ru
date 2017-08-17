package ru.yalymar.mvc.model.dao;

import ru.yalymar.mvc.model.models.Transmission;

import java.util.List;

public class TransmissionsDAO extends DAO<Transmission>{

    public int create(Transmission transmission) {
        return super.action(hibernateTemplate -> (int) hibernateTemplate.save(transmission));
    }

    public Transmission read(int id) {
        return super.action(hibernateTemplate -> hibernateTemplate.get(Transmission.class, id));
    }

    public List<Transmission> readAll() {
        return super.action(hibernateTemplate -> hibernateTemplate.loadAll(Transmission.class));
    }

    public int update(int id, Transmission newTransmisson) {
        int i = 0;
        Transmission transmission = this.read(id);
        if (newTransmisson.getName() != null) {
            transmission.setName(newTransmisson.getName());
            i++;
        }
        if(i > 0){
            super.action(hibernateTemplate -> {
                hibernateTemplate.update(transmission);
                return -1;
            });
        }
        return i;
    }

    public int delete(int id) {
        Transmission transmission = this.read(id);
        return super.action(hibernateTemplate -> {
            hibernateTemplate.delete(transmission);
            return 1;
        });
    }
}
