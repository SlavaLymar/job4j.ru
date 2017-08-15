package ru.yalymar.mvc.model.dao;

import org.hibernate.query.Query;
import ru.yalymar.mvc.model.dao.unproxy.Unproxy;
import ru.yalymar.mvc.model.models.Manufactor;
import ru.yalymar.mvc.model.models.Model;

import java.util.List;

/**
 * @author slavalymar
 * @since 19.06.2017
 * @version 1
 */
public class ModelDAO extends DAO<Model> implements Unproxy<Manufactor> {

    public int create(Model model) {
        return super.tx(session -> (int) session.save(model));
    }

    public Model read(int id) {
        Model model = super.tx(session -> {
            Model m = session.get(Model.class, id);
            Manufactor manuf = this.initializeAndUnproxy(m.getManuf());
            m.setManuf(manuf);
            return m;
        });
        return model;
    }

    public List<Model> readAll() {
        List<Model> ms = super.tx(session -> {
            List<Model> models = session.createQuery("from Model").list();
            for (Model model : models) {
                Manufactor manuf = this.initializeAndUnproxy(model.getManuf());
                model.setManuf(manuf);
            }
            return models;
        });
        return ms;
    }

    public int update(int id, Model newModel) {
        int i = 0;
        Model model = this.read(id);
        if (newModel.getModel() != null) {
            model.setModel(newModel.getModel());
            i++;
        }
        if (newModel.getManuf() != null) {
            model.setManuf(newModel.getManuf());
            i++;
        }
        if(i > 0){
            super.tx(session -> {
                session.update(model);
                return -1;
            });
        }
        return i;
    }

    public int delete(int id) {
        return super.tx(session -> {
            Query query = session.createQuery("delete Model where id = :id");
            query.setParameter("id", id);
            return query.executeUpdate();
        });
    }
}
