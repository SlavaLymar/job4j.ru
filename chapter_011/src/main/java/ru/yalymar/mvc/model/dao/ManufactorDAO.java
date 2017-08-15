package ru.yalymar.mvc.model.dao;

import org.hibernate.query.Query;
import ru.yalymar.mvc.model.models.Manufactor;

import java.util.List;

/**
 * @author slavalymar
 * @since 19.06.2017
 * @version 1
 */
public class ManufactorDAO extends DAO<Manufactor> {

    public int create(Manufactor m) {
        return super.tx(session -> (int) session.save(m));
    }

    public Manufactor read(int id) {
        return super.tx(session -> session.get(Manufactor.class, id));
    }

    public List<Manufactor> readAll() {
        return super.tx(session -> session.createQuery("from Manufactor").list());
    }

    public int update(int id, Manufactor newM) {
        int i = 0;
        Manufactor manuf = this.read(id);
        if (newM.getManuf() != null) {
            manuf.setManuf(newM.getManuf());
            i++;
        }
        if(i > 0){
            super.tx(session -> {
                session.update(manuf);
                return -1;
            });
        }
        return i;
    }

    public int delete(int id) {
        return super.tx(session -> {
            Query query = session.createQuery("delete Manufactor where id = :id");
            query.setParameter("id", id);
            return query.executeUpdate();
        });
    }
}
