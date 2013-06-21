/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.tools.spring;

/**
 * Interface for objects which reverse spring's
 * {@link org.springframework.web.servlet.HandlerMapping}s.
 *
 * @author Rhett Sutphin
 */
public interface ControllerUrlResolver {
    ResolvedControllerReference resolve(String controllerBeanName);
}
