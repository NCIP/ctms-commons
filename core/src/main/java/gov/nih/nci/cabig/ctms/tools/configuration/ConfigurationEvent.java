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
public class ConfigurationEvent {
    private final Configuration source;
    private final ConfigurationProperty<?> updatedProperty;

    public ConfigurationEvent(Configuration source, ConfigurationProperty<?> property) {
        this.source = source;
        this.updatedProperty = property;
    }

    public Configuration getSource() {
        return source;
    }

    public ConfigurationProperty<?> getUpdatedProperty() {
        return updatedProperty;
    }
}
