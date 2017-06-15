package ru.yalymar.mapping.model.dao;

import org.hibernate.query.Query;
import ru.yalymar.mapping.model.Body;

import java.util.List;

public class BodyDAO extends DAO<Body>{

    public int create(Body body) {
        return super.tx(session -> (int) session.save(body));
    }

    public Body read(int id) {
        return super.tx(session -> session.get(Body.class, id));
    }

    public List<Body> readAll() {
        return super.tx(session -> session.createQuery("from Body").list());
    }

    public int update(int id, Body newBody) {
        int i = 0;
        Body body = this.read(id);
        if (newBody.getBody() != null) {
            body.setBody(newBody.getBody());
            i++;
        }
        if(i > 0){
            super.tx(session -> {
                Query query = session.createQuery("update Body set body = :r " +
                        "where id = :id");
                query.setParameter("r", body.getBody());
                query.setParameter("id", id);
                return query.executeUpdate();
            });
        }
        return i;
    }

    public int delete(int id) {
        return super.tx(session -> {
            Query query = session.createQuery("delete Body where id = :id");
            query.setParameter("id", id);
            return query.executeUpdate();
        });
    }
}