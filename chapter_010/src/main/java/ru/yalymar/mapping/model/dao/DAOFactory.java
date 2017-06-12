package ru.yalymar.mapping.model.dao;

public class DAOFactory {

    private final UserDAO userDAO = new UserDAO();
    private final RoleDAO roleDAO = new RoleDAO();
    private final CarDAO carDAO = new CarDAO();
    private final ManufactorDAO manufactorDAO = new ManufactorDAO();
    private final ModelDAO modelDAO = new ModelDAO();
    private final AdDAO adDAO = new AdDAO();

    public UserDAO getUserDAO() {
        return this.userDAO;
    }

    public RoleDAO getRoleDAO() {
        return this.roleDAO;
    }

    public CarDAO getCarDAO() {
        return this.carDAO;
    }

    public ManufactorDAO getManufactorDAO() {
        return this.manufactorDAO;
    }

    public ModelDAO getModelDAO() {
        return this.modelDAO;
    }

    public AdDAO getAdDAO() {
        return this.adDAO;
    }
}
