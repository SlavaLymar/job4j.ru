package ru.yalymar.mvc.model.dao;

import org.hibernate.query.Query;
import ru.yalymar.mvc.model.models.Role;
import java.util.List;

/**
 * @author slavalymar
 * @since 19.06.2017
 * @version 1
 */
public class RoleDAO extends DAO<Role> {

    public int create(Role role) {
        return super.tx(session -> (int) session.save(role));
    }

    public Role read(int id) {
        return super.tx(session -> session.get(Role.class, id));
    }

    public List<Role> readAll() {
        return super.tx(session -> session.createQuery("from Role").list());
    }

    public int update(int id, Role newRole) {
        int i = 0;
        Role role = this.read(id);
        if (newRole.getRole() != null) {
            role.setRole(newRole.getRole());
            i++;
        }
        if(i > 0){
            super.tx(session -> {
                session.update(role);
                return -1;
            });
        }
        return i;
    }

    public int delete(int id) {
        return super.tx(session -> {
            Query query = session.createQuery("delete Role where id = :id");
            query.setParameter("id", id);
            return query.executeUpdate();
        });
    }
}
