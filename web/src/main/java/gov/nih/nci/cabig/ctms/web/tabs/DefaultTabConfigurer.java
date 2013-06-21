/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.web.tabs;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

/**
 * Implements {@link TabConfigurer} by delegating to the containing application context.
 *
 * @author Rhett Sutphin
 */
public class DefaultTabConfigurer implements TabConfigurer, ApplicationContextAware {
    private ApplicationContext applicationContext;

    public void injectDependencies(Flow<?> flow) {
        AutowireCapableBeanFactory autowireer = getBeanFactory();
        for (Tab<?> tab : flow.getTabs()) {
            autowireer.autowireBeanProperties(tab,
                AutowireCapableBeanFactory.AUTOWIRE_BY_NAME,
                false /* Not all settable properties are expected to be in the context */);
        }
    }

    protected AutowireCapableBeanFactory getBeanFactory() {
        return applicationContext.getAutowireCapableBeanFactory();
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
