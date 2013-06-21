/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.suite.authorization;

import gov.nih.nci.cabig.ctms.CommonsSystemException;

/**
 * Indicates an improperly set up authorization/provisioning instance.
 * 
 * @author Rhett Sutphin
 */
public class SuiteAuthorizationValidationException extends CommonsSystemException {
    public SuiteAuthorizationValidationException(String message, Object... messageFormats) {
        super(message, messageFormats);
    }

    public SuiteAuthorizationValidationException(String message, Throwable cause, Object... messsageFormats) {
        super(message, cause, messsageFormats);
    }

    public SuiteAuthorizationValidationException(Throwable cause) {
        super(cause);
    }
}
