/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.tools;

/**
 * @author Rhett Sutphin
*/
public class TestDataSourcePropertiesFactoryBean extends DataSourceSelfDiscoveringPropertiesFactoryBean {
    public static final String APPLICATION_NAME = "stromboli";

    public TestDataSourcePropertiesFactoryBean() {
        setApplicationDirectoryName(APPLICATION_NAME);
    }

    @Override
    protected void computeProperties() {
        properties.setProperty("computed", "42");
        properties.setProperty("applicationDirectoryName", getApplicationDirectoryName());
    }
}
