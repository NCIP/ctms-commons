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
public class CommonsSystemException extends RuntimeException {
    public CommonsSystemException(String message, Object... messageFormats) {
        super(String.format(message, messageFormats));
    }

    public CommonsSystemException(String message, Throwable cause, Object... messsageFormats) {
        super(String.format(message, messsageFormats), cause);
    }

    public CommonsSystemException(Throwable cause) {
        super(cause);
    }
}
