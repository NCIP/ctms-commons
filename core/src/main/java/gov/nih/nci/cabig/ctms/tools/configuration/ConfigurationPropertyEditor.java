/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.tools.configuration;

import org.apache.commons.lang.StringUtils;

import java.beans.PropertyEditorSupport;

/**
 * @author Rhett Sutphin
 */
public class ConfigurationPropertyEditor<V> extends PropertyEditorSupport {
    private ConfigurationProperty<V> property;

    public ConfigurationPropertyEditor(ConfigurationProperty<V> property) {
        this.property = property;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isBlank(text)) {
            setValue(null);
        } else {
            setValue(property.fromStorageFormat(text));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public String getAsText() {
        V v = (V) getValue();
        return v == null ? null : property.toStorageFormat(v);
    }
}
