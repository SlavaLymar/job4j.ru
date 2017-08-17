package ru.yalymar.mvc.model.dao;

import org.springframework.stereotype.Service;
import ru.yalymar.mvc.model.models.Color;

import java.util.List;

@Service
public class ColorDAO extends DAO<Color>{

    public int create(Color color) {
        return super.action(hibernateTemplate -> (int) hibernateTemplate.save(color));
    }

    public Color read(int id) {
        return super.action(hibernateTemplate -> hibernateTemplate.get(Color.class, id));
    }

    public List<Color> readAll() {
        return super.action(hibernateTemplate -> hibernateTemplate.loadAll(Color.class));
    }

    public int update(int id, Color newColor) {
        int i = 0;
        Color color = this.read(id);
        if (newColor.getColor() != null) {
            color.setColor(newColor.getColor());
            i++;
        }
        if(i > 0){
            super.action(hibernateTemplate -> {
                hibernateTemplate.update(color);
                return -1;
            });
        }
        return i;
    }

    public int delete(int id) {
        Color color = this.read(id);
        return super.action(hibernateTemplate -> {
            hibernateTemplate.delete(color);
            return 1;
        });
    }
}
