package ru.yalymar.mvc.model.dao;

import org.springframework.stereotype.Service;
import ru.yalymar.mvc.model.models.Role;
import java.util.List;

@Service
public class RoleDAO extends DAO<Role> {

    public int create(Role role) {
        return super.action(hibernateTemplate -> (int) hibernateTemplate.save(role));
    }

    public Role read(int id) {
        return super.action(hibernateTemplate -> hibernateTemplate.get(Role.class, id));
    }

    public List<Role> readAll() {
        return super.action(hibernateTemplate -> hibernateTemplate.loadAll(Role.class));
    }

    public int update(int id, Role newRole) {
        int i = 0;
        Role role = this.read(id);
        if (newRole.getRole() != null) {
            role.setRole(newRole.getRole());
            i++;
        }
        if(i > 0){
            super.action(hibernateTemplate -> {
                hibernateTemplate.update(role);
                return -1;
            });
        }
        return i;
    }

    public int delete(int id) {
        Role role = this.read(id);
        return super.action(hibernateTemplate -> {
            hibernateTemplate.delete(role);
            return 1;
        });
    }
}
