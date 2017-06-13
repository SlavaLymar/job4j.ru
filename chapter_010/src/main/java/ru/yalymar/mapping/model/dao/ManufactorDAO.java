package ru.yalymar.mapping.model.dao;

import org.hibernate.Session;
import ru.yalymar.mapping.model.Manufactor;
import java.util.List;

public class ManufactorDAO extends DAO<Manufactor> {

    public int create(Manufactor m) {
//        int i = super.create.daoCreate(m);
//        int id = -1;
//        if (i > 0) {
//            id = m.getId();
//        }
//        return id;
    return 0;
    }

    @Override
    public Manufactor daoRead(int id) {
        Session session = null;
        try {
            session = super.sessionFactory.openSession();
            return session.get(Manufactor.class, id);
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public List<Manufactor> daoReadAll() {
        Session session = null;
        try{
            session = super.sessionFactory.openSession();
            return session.createQuery("from Manufactor").list();
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public int update(int id, Manufactor newM) {
        int i = 0;
        Manufactor manuf = this.daoRead(id);
        if (newM.getManuf() != null) {
            manuf.setManuf(newM.getManuf());
            i++;
        }
        super.update.daoUpdate(manuf);
        return i;
    }

    public int delete(int id) {
        String query = String.format("delete Manufactor where id = %d", id);
        int i = super.delete.daoDelete(query);
        return i;
    }
}
