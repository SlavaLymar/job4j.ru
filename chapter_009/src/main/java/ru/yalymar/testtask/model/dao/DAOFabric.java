package ru.yalymar.testtask.model.dao;

public class DAOFabric {

    private AddressManager addressManager;
    private RoleManager roleManager;
    private TypeOfMusicManager typeOfMusicManager;
    private UserManager userManager;

    public DAOFabric() {
        this.addressManager = new AddressManager(this);
        this.roleManager = new RoleManager(this);
        this.typeOfMusicManager = new TypeOfMusicManager(this);
        this.userManager = new UserManager(this);
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
