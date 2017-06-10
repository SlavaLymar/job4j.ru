package ru.yalymar.mapping.model.manager;

public class ManagersFactory {

    private final UserManager userManager = new UserManager();
    private final RoleManager roleManager = new RoleManager();

    public UserManager getUm() {
        return this.userManager;
    }

    public RoleManager getRoleManager() {
        return this.roleManager;
    }
}
