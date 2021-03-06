/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.tools.configuration;

import gov.nih.nci.cabig.ctms.CommonsSystemException;

import java.util.List;
import java.util.Map;

/**
 * Template base class for {@link Configuration} implementations.
 *
 * @author Rhett Sutphin
 */
public abstract class AbstractConfiguration implements Configuration {
    private Map<String, Object> map;
    private ConfigurationEventDispatchSupport dispatcher;

    protected AbstractConfiguration() {
        dispatcher = new ConfigurationEventDispatchSupport(this);
    }

    public <V> V get(ConfigurationProperty<V> property) {
        ConfigurationEntry entry = getEntry(property);
        if (entry == null) {
            return property.getDefault();
        } else {
            return entry.getValue() == null
                ? null
                : property.fromStorageFormat(entry.getValue());
        }
    }

    public <V> void set(ConfigurationProperty<V> property, V value) {
        ConfigurationEntry entry = getEntry(property);
        if (entry == null) {
            entry = createNewEntry(property);
        }
        entry.setValue(value == null ? null : property.toStorageFormat(value));
        store(entry);
        getEventDispatcher().dispatchUpdate(property);
    }

    public boolean isSet(ConfigurationProperty<?> property) {
        return getEntry(property) != null;
    }

    public <V> void reset(ConfigurationProperty<V> property) {
        ConfigurationEntry entry = getEntry(property);
        if (entry != null) remove(entry);
        getEventDispatcher().dispatchUpdate(property);
    }

    protected abstract <V> ConfigurationEntry getEntry(ConfigurationProperty<V> property);

    protected abstract void store(ConfigurationEntry entry);

    protected abstract void remove(ConfigurationEntry entry);

    /**
     * Allows subclasses to specify a subclass of {@link ConfigurationEntry} to
     * use.  The primary anticipated benefit of this is to allow for a
     * {@link DatabaseBackedConfiguration} stored in a table not called
     * "configuration".
     * <p>
     * The default is {@link DefaultConfigurationEntry},
     * which will usually be fine.
     */
    protected Class<? extends ConfigurationEntry> getConfigurationEntryClass() {
        return DefaultConfigurationEntry.class;
    }

    /**
     * Allows subclasses to specify how new entries should be created.  The default
     * is to create a new instance of the class returned by {@link #getConfigurationEntryClass()},
     * set the key from the provided <code>property</code> and return.
     */
    protected <V> ConfigurationEntry createNewEntry(ConfigurationProperty<V> property) {
        try {
            ConfigurationEntry entry = getConfigurationEntryClass().newInstance();
            entry.setKey(property.getKey());
            return entry;
        } catch (InstantiationException e) {
            throw new CommonsSystemException(
                "Could not instantiate a new configuration entry of class %s", e,
                getConfigurationEntryClass().getName());
        } catch (IllegalAccessException e) {
            throw new CommonsSystemException(
                "Could not instantiate a new configuration entry of class %s", e,
                getConfigurationEntryClass().getName());
        }
    }

    public java.util.Map<String, Object> getMap() {
        if (map == null) map = new DefaultConfigurationMap(this);
        return map;
    }

    protected ConfigurationEventDispatchSupport getEventDispatcher() {
        return dispatcher;
    }

    public synchronized void addConfigurationListener(ConfigurationListener listener) {
        getEventDispatcher().addListener(listener);
    }

    /**
     * Bean-style setter for listeners.  Intended for use in a dependency injection context.
     * Does not remove any existing listeners.
     */
    public synchronized void setConfigurationListeners(List<ConfigurationListener> listeners) {
        for (ConfigurationListener listener : listeners) {
            getEventDispatcher().addListener(listener);
        }
    }
}
