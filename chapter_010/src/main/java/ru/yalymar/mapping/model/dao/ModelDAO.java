package ru.yalymar.mapping.model.dao;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.yalymar.mapping.model.Manufactor;
import ru.yalymar.mapping.model.Model;
import ru.yalymar.mapping.model.User;
import ru.yalymar.mapping.model.unproxy.Unproxy;
import java.util.List;

//TODO
public class ModelDAO extends DAO<Model> implements Unproxy<Manufactor> {

    public int create(Model model) {
        int i = super.create.daoCreate(model);
        int id = -1;
        if (i > 0) {
            id = model.getId();
        }
        return id;
    }

    @Override
    public Model daoRead(int id) {
        Session session = null;
        try {
            session = super.sessionFactory.openSession();
            Model model = session.get(Model.class, id);
//            Model model = this.initializeAndUnproxy(user.getRole());
//            user.setRole(role);
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
//            if(newUser.getLogin() != null) {
//                user.setLogin(newUser.getLogin());
//                i++;
//            }
//            if(newUser.getPassword() != null) {
//                user.setPassword(newUser.getPassword());
//                i++;
//            }
//            if(newUser.getName() != null){
//                user.setName(newUser.getName());
//                i++;
//            }
//            if(newUser.getRole() != null){
//                user.setRole(newUser.getRole());
//                i++;
//            }
        super.update.daoUpdate(model);
        return i;
    }

    public int delete(int id) {
            String query = String.format("daoDelete User where id = %d", id);
        int i = super.delete.daoDelete(query);
            return i;
    }
}
