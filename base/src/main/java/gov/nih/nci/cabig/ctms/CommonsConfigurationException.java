/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms;

/**
 * @author Rhett Sutphin
 */
public class CommonsConfigurationException extends CommonsSystemException {
    public CommonsConfigurationException(String message, Throwable cause, Object... substitutions) {
        super(message, cause, substitutions);
    }

    public CommonsConfigurationException(Throwable cause) {
        super(cause);
    }

    public CommonsConfigurationException(String message, Object... substitutions) {
        super(message, substitutions);
    }
}
