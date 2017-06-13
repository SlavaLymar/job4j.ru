package ru.yalymar.configuration.model.manager;

import ru.yalymar.configuration.model.Item;
import java.util.List;

/**
 * @author slavalymar
 * @since 13.06.2017
 * @version 1
 */
public class ItemManager extends Manager<Item>{

    public int create(Item item) {
        return super.tx(session -> (int) session.save(item));
    }

    public Item read(int id) {
        return super.tx(session -> session.get(Item.class, id));
    }

    public List<Item> readAll() {
        return super.tx(session -> session.createQuery("from Item").list());
    }

    public int update(int id, Item newItem) {
        int i = 0;
        Item item = this.read(id);
        if (item != null) {
            if (newItem.getDescription() != null) {
                item.setDescription(newItem.getDescription());
                i++;
            }
            super.tx(session -> {
                session.update(item);
                return 1;
            });
        }
        return i;
    }

    public void delete(int id) {
        Item item = this.read(id);
        super.tx(session -> {
            session.delete(item);
            return 1;
        });
    }


}
