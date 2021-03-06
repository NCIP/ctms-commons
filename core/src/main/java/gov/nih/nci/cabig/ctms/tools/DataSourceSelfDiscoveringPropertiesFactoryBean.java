/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.tools;

import gov.nih.nci.cabig.ctms.CommonsConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * @author Rhett Sutphin
 */
public class DataSourceSelfDiscoveringPropertiesFactoryBean extends DatabaseConfigurationAccessor implements FactoryBean {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    public static final String HIBERNATE_DIALECT_PROPERTY_NAME = "hibernate.dialect";
    public static final String RDBMS_PROPERTY_NAME = "datasource.rdbms";
    public static final String DRIVER_PROPERTY_NAME = "datasource.driver";
    public static final String URL_PROPERTY_NAME = "datasource.url";
    public static final String USERNAME_PROPERTY_NAME = "datasource.username";
    public static final String PASSWORD_PROPERTY_NAME = "datasource.password";
    public static final String DEFAULT_POSTGRESQL_DIALECT
        = "edu.northwestern.bioinformatics.bering.dialect.hibernate.ImprovedPostgreSQLDialect";

    private String applicationDirectoryName;
    protected Properties properties;
    private Properties defaults;
    private List<String> searchedLocations;
    private boolean nullTolerant;

    public DataSourceSelfDiscoveringPropertiesFactoryBean() {
        defaults = new Properties();
        nullTolerant = false;
    }

    public synchronized Properties getProperties() {
        if (properties == null && searchedLocations == null) {
            initProperties();
        }
        return properties;
    }

    private void initProperties() {
        findProperties();
        if (properties != null) {
            internalComputeProperties();
            computeProperties();
        }
    }

    protected List<File> searchDirectories() {
        List<File> dirs = new LinkedList<File>();

        addPropertyBasedSearchDirectory(dirs, "catalina.base", "conf/%s");
        addPropertyBasedSearchDirectory(dirs, "catalina.home", "conf/%s");
        addPropertyBasedSearchDirectory(dirs, "user.home", ".%s");
        dirs.add(new File(String.format("/etc/%s", getApplicationDirectoryName())));

        return dirs;
    }

    private void addPropertyBasedSearchDirectory(
        List<File> searchDirs, String directoryProperty, String subPathFormat
    ) {
        String propertyValue = System.getProperty(directoryProperty);
        if (propertyValue == null) {
            log.debug("{} not set -- will not search", directoryProperty);
        } else {
            searchDirs.add(new File(propertyValue,
                String.format(subPathFormat, getApplicationDirectoryName())));
        }
    }

    private void findProperties() {
        if (getApplicationDirectoryName() == null) {
            throw new CommonsConfigurationException("No application directory name set; cannot search for datasource properties.");
        }

        String propfileName = getDatabaseConfigurationName() + ".properties";
        searchedLocations = new LinkedList<String>();
        for (File dir : searchDirectories()) {
            File possiblePath = new File(dir, propfileName);
            String abspath = possiblePath.getAbsolutePath();
            if (log.isDebugEnabled()) log.debug("Looking in " + abspath);
            if (possiblePath.exists()) {
                readProperties(possiblePath);
                return;
            } else {
                searchedLocations.add(abspath);
                if (log.isDebugEnabled()) log.debug("Not found in " + abspath);
            }
        }
        if (!isNullTolerant()) {
            throw new CommonsConfigurationException("Datasource configuration not found.  Looked in " + searchedLocations + '.');
        }
    }

    private void readProperties(File path) {
        try {
            log.info("Loading datasource properties from " + path.getAbsolutePath());
            properties = new Properties(getDefaults());
            properties.load(new FileInputStream(path));
        } catch (IOException e) {
            throw new CommonsConfigurationException(
                "Could not read datasource configuration from " + path.getAbsolutePath()
                    + ". (" + e.getMessage() + ')', e);
        }
    }

    /**
     * Template method allowing subclasses to set reasonable defaults for application-specific
     * properties.  They should set them on {@link #properties} directly.
     */
    protected void computeProperties() { }

    /**
     * Defaults the hibernate dialect for PostgreSQL only.
     */
    private void internalComputeProperties() {
        String dialect = selectHibernateDialect();
        if (dialect != null) properties.setProperty(HIBERNATE_DIALECT_PROPERTY_NAME, dialect);
    }

    private String selectHibernateDialect() {
        String explicit = properties.getProperty(HIBERNATE_DIALECT_PROPERTY_NAME);
        if (explicit != null) return explicit;

        String rdbms = properties.getProperty(RDBMS_PROPERTY_NAME);
        if (rdbms != null) {
            if (rdbms.toLowerCase().contains("postgres")) return DEFAULT_POSTGRESQL_DIALECT;
            return null;
        }

        String driver = properties.getProperty(DRIVER_PROPERTY_NAME);
        if (driver != null) {
            if (driver.toLowerCase().contains("postgres")) return DEFAULT_POSTGRESQL_DIALECT;
            return null;
        }

        return null;
    }

    ////// IMPLEMENTATION OF FACTORYBEAN

    public synchronized Object getObject() throws Exception {
        return getProperties();
    }

    public Class getObjectType() {
        return Properties.class;
    }

    public boolean isSingleton() {
        return true;
    }

    ////// CONFIGURATION

    // defaults are intended mainly for testing dynamically guessed properties
    public Properties getDefaults() {
        return defaults;
    }

    public void setDefaults(Properties defaults) {
        this.defaults = defaults;
    }

    public String getApplicationDirectoryName() {
        return applicationDirectoryName;
    }

    public void setApplicationDirectoryName(String applicationDirectoryName) {
        this.applicationDirectoryName = applicationDirectoryName;
    }

    public boolean isNullTolerant() {
        return nullTolerant;
    }

    public void setNullTolerant(boolean nullTolerant) {
        this.nullTolerant = nullTolerant;
    }

    public List<String> getSearchedLocations() {
        return searchedLocations;
    }
}
