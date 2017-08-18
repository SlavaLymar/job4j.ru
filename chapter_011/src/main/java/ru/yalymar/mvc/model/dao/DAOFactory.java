package ru.yalymar.mvc.model.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
public class DAOFactory {

    public static final Logger logger = Logger.getLogger(DAOFactory.class);

    private UserDAO userDAO;
    private RoleDAO roleDAO;
    private CarDAO carDAO;
    private ManufactorDAO manufactorDAO;
    private ModelDAO modelDAO;
    private AdDAO adDAO;
    private BodyDAO bodyDAO;
    private ColorDAO colorDAO;
    private TransmissionsDAO transmissionsDAO;
    private ImageDAO imageDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Autowired
    public void setCarDAO(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @Autowired
    public void setManufactorDAO(ManufactorDAO manufactorDAO) {
        this.manufactorDAO = manufactorDAO;
    }

    @Autowired
    public void setModelDAO(ModelDAO modelDAO) {
        this.modelDAO = modelDAO;
    }

    @Autowired
    public void setAdDAO(AdDAO adDAO) {
        this.adDAO = adDAO;
    }

    @Autowired
    public void setBodyDAO(BodyDAO bodyDAO) {
        this.bodyDAO = bodyDAO;
    }

    @Autowired
    public void setColorDAO(ColorDAO colorDAO) {
        this.colorDAO = colorDAO;
    }

    @Autowired
    public void setTransmissionsDAO(TransmissionsDAO transmissionsDAO) {
        this.transmissionsDAO = transmissionsDAO;
    }

    @Autowired
    public void setImageDAO(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public RoleDAO getRoleDAO() {
        return roleDAO;
    }

    public CarDAO getCarDAO() {
        return carDAO;
    }

    public ManufactorDAO getManufactorDAO() {
        return manufactorDAO;
    }

    public ModelDAO getModelDAO() {
        return modelDAO;
    }

    public AdDAO getAdDAO() {
        return adDAO;
    }

    public BodyDAO getBodyDAO() {
        return bodyDAO;
    }

    public ColorDAO getColorDAO() {
        return colorDAO;
    }

    public TransmissionsDAO getTransmissionsDAO() {
        return transmissionsDAO;
    }

    public ImageDAO getImageDAO() {
        return imageDAO;
    }
}
