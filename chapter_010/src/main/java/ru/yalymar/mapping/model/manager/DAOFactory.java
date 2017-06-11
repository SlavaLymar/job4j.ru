package ru.yalymar.mapping.model.manager;

public class DAOFactory {

    private final UserDAO userManager = new UserDAO();
    private final RoleDAO roleManager = new RoleDAO();

    public UserDAO getUm() {
        return this.userManager;
    }

    public RoleDAO getRoleManager() {
        return this.roleManager;
    }
}
