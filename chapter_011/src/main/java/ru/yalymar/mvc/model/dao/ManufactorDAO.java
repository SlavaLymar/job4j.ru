package ru.yalymar.mvc.model.dao;

import ru.yalymar.mvc.model.models.Manufactor;

import java.util.List;

public class ManufactorDAO extends DAO<Manufactor> {

    public int create(Manufactor m) {
        return super.action(hibernateTemplate -> (int) hibernateTemplate.save(m));
    }

    public Manufactor read(int id) {
        return super.action(hibernateTemplate -> hibernateTemplate.get(Manufactor.class, id));
    }

    public List<Manufactor> readAll() {
        return super.action(hibernateTemplate -> hibernateTemplate.loadAll(Manufactor.class));
    }

    public int update(int id, Manufactor newM) {
        int i = 0;
        Manufactor manuf = this.read(id);
        if (newM.getManuf() != null) {
            manuf.setManuf(newM.getManuf());
            i++;
        }
        if(i > 0){
            super.action(hibernateTemplate -> {
                hibernateTemplate.update(manuf);
                return -1;
            });
        }
        return i;
    }

    public int delete(int id) {
        Manufactor manuf = this.read(id);
        return super.action(hibernateTemplate -> {
            hibernateTemplate.delete(manuf);
            return 1;
        });
    }
}
