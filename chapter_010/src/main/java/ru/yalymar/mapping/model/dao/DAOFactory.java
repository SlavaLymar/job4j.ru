package ru.yalymar.mapping.model.dao;

import org.apache.log4j.Logger;

/**
 * @author slavalymar
 * @since 19.06.2017
 * @version 1
 */
public class DAOFactory {

    public static final Logger logger = Logger.getLogger(DAOFactory.class);

    private final UserDAO userDAO = new UserDAO();
    private final RoleDAO roleDAO = new RoleDAO();
    private final CarDAO carDAO = new CarDAO();
    private final ManufactorDAO manufactorDAO = new ManufactorDAO();
    private final ModelDAO modelDAO = new ModelDAO();
    private final AdDAO adDAO = new AdDAO();
    private final BodyDAO bodyDAO = new BodyDAO();
    private final ColorDAO colorDAO = new ColorDAO();
    private final TransmissionsDAO transmissionsDAO = new TransmissionsDAO();
    private final ImageDAO imageDAO = new ImageDAO();

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

    public BodyDAO getBodyDAO() {
        return this.bodyDAO;
    }

    public ColorDAO getColorDAO() {
        return this.colorDAO;
    }

    public TransmissionsDAO getTransmissionsDAO() {
        return this.transmissionsDAO;
    }

    public ImageDAO getImageDAO() {
        return this.imageDAO;
    }
}
