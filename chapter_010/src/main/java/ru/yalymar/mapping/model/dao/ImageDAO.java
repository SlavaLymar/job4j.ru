package ru.yalymar.mapping.model.dao;

import org.hibernate.query.Query;
import ru.yalymar.mapping.model.Image;

import java.util.List;

public class ImageDAO extends DAO<Image> {

    public int create(Image image) {
        return super.tx(session -> (int) session.save(image));
    }

    public Image read(int id) {
        return super.tx(session -> session.get(Image.class, id));
    }

    public List<Image> readAll() {
        return super.tx(session -> session.createQuery("from Image").list());
    }

    public int delete(int id) {
        return super.tx(session -> {
            Query query = session.createQuery("delete Image where id = :id");
            query.setParameter("id", id);
            return query.executeUpdate();
        });
    }
}
