package ru.yalymar.mvc.model.dao;

import ru.yalymar.mvc.model.models.Body;

import java.util.List;

/**
 * @author slavalymar
 * @since 19.06.2017
 * @version 1
 */
public class BodyDAO extends DAO<Body>{

    public int create(Body body) {
        return super.action(hibernateTemplate -> (int) hibernateTemplate.save(body));
    }

    public Body read(int id) {
        return super.action(hibernateTemplate -> hibernateTemplate.get(Body.class, id));
    }

    public List<Body> readAll() {
        return super.action(hibernateTemplate -> hibernateTemplate.loadAll(Body.class));
    }

    public int update(int id, Body newBody) {
        int i = 0;
        Body body = this.read(id);
        if (newBody.getBody() != null) {
            body.setBody(newBody.getBody());
            i++;
        }
        if(i > 0){
            super.action(hibernateTemplate -> {
                hibernateTemplate.update(body);
                return -1;
            });
        }
        return i;
    }

    public int delete(int id) {
        Body body = this.read(id);
        return super.action(hibernateTemplate -> {
            hibernateTemplate.delete(body);
            return 1;
        });
    }
}
