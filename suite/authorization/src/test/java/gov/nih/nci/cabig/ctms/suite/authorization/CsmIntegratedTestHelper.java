/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.suite.authorization;

import gov.nih.nci.cabig.ctms.CommonsError;
import gov.nih.nci.cabig.ctms.suite.authorization.csmext.FasterAuthorizationDao;
import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.dao.AuthorizationDAO;
import gov.nih.nci.security.dao.AuthorizationDAOImpl;
import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.provisioning.AuthorizationManagerImpl;
import gov.nih.nci.security.system.ApplicationSessionFactory;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static gov.nih.nci.cabig.ctms.suite.authorization.CsmHelper.SUITE_APPLICATION_NAME;

/**
 * @author Rhett Sutphin
 */
public class CsmIntegratedTestHelper {
    private static AuthorizationDAO originalDao;
    private static FasterAuthorizationDao fasterDao;
    private static AuthorizationManager manager;
    private static SessionFactory sf;

    public synchronized static AuthorizationManager getAuthorizationManager() {
        if (manager == null) {
            try {
                // CSM requires that the SF be loaded before the AuthorizationManager is created.  Really.
                AuthorizationDAO dao = getAuthorizationDao();
                AuthorizationManagerImpl newMgr = new AuthorizationManagerImpl(SUITE_APPLICATION_NAME);
                newMgr.setAuthorizationDAO(dao);
                manager = newMgr;
            } catch (CSConfigurationException e) {
                throw new RuntimeException(e);
            }
        }
        return manager;
    }

    public synchronized static AuthorizationDAO getAuthorizationDao() {
        if (originalDao == null) {
            try {
                originalDao = new AuthorizationDAOImpl(getSessionFactory(), SUITE_APPLICATION_NAME);
            } catch (CSConfigurationException e) {
                throw new CommonsError("Creating the CSM authorization DAO failed", e);
            }
        }
        return originalDao;
    }

    public synchronized static FasterAuthorizationDao getFasterAuthorizationDao() {
        if (fasterDao == null) {
            try {
                fasterDao = new FasterAuthorizationDao(getSessionFactory(), SUITE_APPLICATION_NAME);
            } catch (CSConfigurationException e) {
                throw new CommonsError("Creating the CSM authorization DAO failed", e);
            }
        }
        return fasterDao;
    }

    public static SessionFactory getSessionFactory() {
        if (sf == null) {
            try {
                sf = ApplicationSessionFactory.getSessionFactory(
                    SUITE_APPLICATION_NAME, csmConnectionProperties());
            } catch (CSConfigurationException e) {
                throw new CommonsError("Creating the CSM SessionFactory failed", e);
            }
        }
        return sf;
    }

    public static IDatabaseTester createDatabaseTester() {
        Map<String, String> props = csmConnectionProperties();
        String driver = props.get("hibernate.connection.driver_class");
        String url = props.get("hibernate.connection.url");
        String username = props.get("hibernate.connection.username");
        String password = props.get("hibernate.connection.password");

        return new JdbcDatabaseTester(driver, url, username, password);
    }

    // The over-strong return typing here is required by CSM
    @SuppressWarnings({"unchecked"})
    private static HashMap<String, String> csmConnectionProperties() {
        Properties connectionProperties = new Properties();
        try {
            connectionProperties.load(
                CsmIntegratedTestHelper.class.getResourceAsStream("/csm-connection.properties"));
        } catch (IOException e) {
            throw new CommonsError("Could not load/parse connection properties file for integrated tests.", e);
        } catch (NullPointerException e) {
            throw new CommonsError("Could not find resource for connection properties.", e);
        }
        return new HashMap<String, String>((Map) connectionProperties);
    }

    // static class
    private CsmIntegratedTestHelper() { }
}
