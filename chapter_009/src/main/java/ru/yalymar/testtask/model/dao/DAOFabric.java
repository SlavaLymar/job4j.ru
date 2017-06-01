package ru.yalymar.testtask.model.dao;

public class DAOFabric {

    private AddressManager addressManager;
    private RoleManager roleManager;
    private TypeOfMusicManager typeOfMusicManager;
    private UserManager userManager;

    public DAOFabric() {
        this.addressManager = new AddressManager();
        this.roleManager = new RoleManager();
        this.typeOfMusicManager = new TypeOfMusicManager();
        this.userManager = new UserManager();
    }

    public AddressManager getAddressManager() {
        return this.addressManager;
    }

    public RoleManager getRoleManager() {
        return this.roleManager;
    }

    public TypeOfMusicManager getTypeOfMusicManager() {
        return this.typeOfMusicManager;
    }

    public UserManager getUserManager() {
        return this.userManager;
    }
}
