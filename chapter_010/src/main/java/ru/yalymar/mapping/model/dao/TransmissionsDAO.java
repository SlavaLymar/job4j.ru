package ru.yalymar.mapping.model.dao;

import org.hibernate.query.Query;
import ru.yalymar.mapping.model.models.Transmission;
import java.util.List;

/**
 * @author slavalymar
 * @since 19.06.2017
 * @version 1
 */
public class TransmissionsDAO extends DAO<Transmission>{

    public int create(Transmission transmission) {
        return super.tx(session -> (int) session.save(transmission));
    }

    public Transmission read(int id) {
        return super.tx(session -> session.get(Transmission.class, id));
    }

    public List<Transmission> readAll() {
        return super.tx(session -> session.createQuery("from Transmission").list());
    }

    public int update(int id, Transmission newTransmisson) {
        int i = 0;
        Transmission transmission = this.read(id);
        if (newTransmisson.getName() != null) {
            transmission.setName(newTransmisson.getName());
            i++;
        }
        if(i > 0){
            super.tx(session -> {
                session.update(transmission);
                return -1;
            });
        }
        return i;
    }

    public int delete(int id) {
        return super.tx(session -> {
            Query query = session.createQuery("delete Transmission where id = :id");
            query.setParameter("id", id);
            return query.executeUpdate();
        });
    }
}
