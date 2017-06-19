package ru.yalymar.mapping.model.dao;

import org.hibernate.query.Query;
import ru.yalymar.mapping.model.models.Color;
import java.util.List;

/**
 * @author slavalymar
 * @since 19.06.2017
 * @version 1
 */
public class ColorDAO extends DAO<Color>{

    public int create(Color color) {
        return super.tx(session -> (int) session.save(color));
    }

    public Color read(int id) {
        return super.tx(session -> session.get(Color.class, id));
    }

    public List<Color> readAll() {
        return super.tx(session -> session.createQuery("from Color").list());
    }

    public int update(int id, Color newColor) {
        int i = 0;
        Color color = this.read(id);
        if (newColor.getColor() != null) {
            color.setColor(newColor.getColor());
            i++;
        }
        if(i > 0){
            super.tx(session -> {
                session.update(color);
                return -1;
            });
        }
        return i;
    }

    public int delete(int id) {
        return super.tx(session -> {
            Query query = session.createQuery("delete Color where id = :id");
            query.setParameter("id", id);
            return query.executeUpdate();
        });
    }
}
