package ru.yalymar.mapping.model.dao;

import org.hibernate.Session;
import ru.yalymar.mapping.model.Role;
import java.util.List;

public class RoleDAO extends DAO<Role> {

    public int create(Role role) {
        int i = super.create.daoCreate(role);
        int id = -1;
        if (i > 0) {
            id = role.getId();
        }
        return id;
    }

    @Override
    public Role daoRead(int id) {
        Session session = null;
        try {
            session = super.sessionFactory.openSession();
            return session.get(Role.class, id);
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    @Override
    public List<Role> daoReadAll() {
        Session session = null;
        try{
            session = super.sessionFactory.openSession();
            return session.createQuery("from Role").list();
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public int update(int id, Role newRole) {
        int i = 0;
        Role role = this.daoRead(id);
        if (newRole.getRole() != null) {
            role.setRole(newRole.getRole());
            i++;
        }
        super.update.daoUpdate(role);
        return i;
    }

    public int delete(int id) {
        String query = String.format("daoDelete Role where id = %d", id);
        int i = super.delete.daoDelete(query);
        return i;
    }
}
