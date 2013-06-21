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
public class CommonsError extends Error {
    public CommonsError(String message) {
        super(message);
    }

    public CommonsError(String message, Throwable cause) {
        super(message, cause);
    }
}
