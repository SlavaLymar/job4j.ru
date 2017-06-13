package ru.yalymar.mapping.model.dao;

import org.hibernate.Session;
import ru.yalymar.mapping.model.Manufactor;
import ru.yalymar.mapping.model.Model;
import ru.yalymar.mapping.model.unproxy.Unproxy;
import java.util.List;

public class ModelDAO extends DAO<Model> implements Unproxy<Manufactor> {

    public int create(Model model) {
//        int i = super.create.daoCreate(model);
//        int id = -1;
//        if (i > 0) {
//            id = model.getId();
//        }
//        return id;
        return 0;
    }

    @Override
    public Model daoRead(int id) {
        Session session = null;
        try {
            session = super.sessionFactory.openSession();
            Model model = session.get(Model.class, id);
            Manufactor manuf = this.initializeAndUnproxy(model.getManuf());
            model.setManuf(manuf);
            return model;
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public List<Model> daoReadAll() {
        Session session = null;
        try{
            session = super.sessionFactory.openSession();
            List<Model> models = session.createQuery("from Model").list();
            for(Model model : models){
                Manufactor manuf = this.initializeAndUnproxy(model.getManuf());
                model.setManuf(manuf);
            }
            return models;
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public int update(int id, Model newModel) {
        int i = 0;
        Model model = this.daoRead(id);
        if (newModel.getModel() != null) {
            model.setModel(newModel.getModel());
            i++;
        }
        if (newModel.getManuf() != null) {
            model.setManuf(newModel.getManuf());
            i++;
        }
        super.update.daoUpdate(model);
        return i;
    }

    public int delete(int id) {
        String query = String.format("delete Model where id = %d", id);
        int i = super.delete.daoDelete(query);
        return i;
    }
}
