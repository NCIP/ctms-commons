/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.tools.configuration;

/**
 * @author Rhett Sutphin
 */
public interface ConfigurationListener {
    void configurationUpdated(ConfigurationEvent update);
}
