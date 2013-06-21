/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.tools;

import org.apache.commons.lang.StringUtils;

/**
 * @author Rhett Sutphin
 */
public class DatabaseConfigurationAccessor {
    private String databaseConfigurationName;
    public static final String DEFAULT_DB_CONFIGURATION_NAME = "datasource";

    public String getDatabaseConfigurationName() {
        return StringUtils.isBlank(databaseConfigurationName)
            ? DEFAULT_DB_CONFIGURATION_NAME
            : databaseConfigurationName;
    }

    public void setDatabaseConfigurationName(String databaseConfigurationName) {
        this.databaseConfigurationName = databaseConfigurationName;
    }
}
