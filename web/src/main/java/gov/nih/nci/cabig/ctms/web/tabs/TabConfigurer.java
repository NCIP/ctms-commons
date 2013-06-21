/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.web.tabs;

/**
 * Defines the interface for objects which provide IoC dependency resolution for tabs.
 *
 * @author Rhett Sutphin
 */
public interface TabConfigurer {
    void injectDependencies(Flow<?> flow);
}
