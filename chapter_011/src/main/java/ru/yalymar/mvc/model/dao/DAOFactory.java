package ru.yalymar.mvc.model.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class DAOFactory {

    public static final Logger logger = Logger.getLogger(DAOFactory.class);

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private CarDAO carDAO;

    @Autowired
    private ManufactorDAO manufactorDAO;

    @Autowired
    private ModelDAO modelDAO;

    @Autowired
    private AdDAO adDAO;

    @Autowired
    private BodyDAO bodyDAO;

    @Autowired
    private ColorDAO colorDAO;

    @Autowired
    private TransmissionsDAO transmissionsDAO;

    @Autowired
    private ImageDAO imageDAO;


}
