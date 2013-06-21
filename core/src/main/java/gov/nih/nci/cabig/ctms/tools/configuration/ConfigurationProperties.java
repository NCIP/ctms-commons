/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.tools.configuration;

import java.util.Collection;

/**
 * Defines a collection of {@link DefaultConfigurationProperty}s for a certain
 * application.
 * 
 * @author Rhett Sutphin
 */
public interface ConfigurationProperties {
    int size();

    ConfigurationProperty<?> get(String key);

    Collection<ConfigurationProperty<?>> getAll();

    boolean containsKey(String key);

    String getNameFor(String key);

    String getDescriptionFor(String key);

    String getStoredDefaultFor(String key);
}
