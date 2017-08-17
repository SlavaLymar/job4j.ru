package ru.yalymar.mvc.model.dao;

import org.springframework.stereotype.Service;
import ru.yalymar.mvc.model.models.Image;

import java.util.List;

@Service
public class ImageDAO extends DAO<Image> {

    public int create(Image image) {
        return super.action(hibernateTemplate -> (int) hibernateTemplate.save(image));
    }

    public Image read(int id) {
        return super.action(hibernateTemplate -> hibernateTemplate.get(Image.class, id));
    }

    public List<Image> readAll() {
        return super.action(hibernateTemplate -> hibernateTemplate.loadAll(Image.class));
    }

    public int delete(int id) {
        Image img = this.read(id);
        return super.action(hibernateTemplate -> {
            hibernateTemplate.delete(img);
            return 1;
        });
    }
}
