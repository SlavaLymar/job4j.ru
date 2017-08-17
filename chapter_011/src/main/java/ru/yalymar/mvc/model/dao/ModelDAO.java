package ru.yalymar.mvc.model.dao;

import org.springframework.stereotype.Service;
import ru.yalymar.mvc.model.models.Model;

import java.util.List;

@Service
public class ModelDAO extends DAO<Model>{

    public int create(Model model) {
        return super.action(hibernateTemplate -> (int) hibernateTemplate.save(model));
    }

    public Model read(int id) {
        return super.action(hibernateTemplate -> hibernateTemplate.get(Model.class, id));
    }

    public List<Model> readAll() {
        return super.action(hibernateTemplate -> hibernateTemplate.loadAll(Model.class));
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
            super.action(hibernateTemplate -> {
                hibernateTemplate.update(model);
                return -1;
            });
        }
        return i;
    }

    public int delete(int id) {
        Model model = this.read(id);
        return super.action(hibernateTemplate -> {
            hibernateTemplate.delete(model);
            return 1;
        });
    }
}
