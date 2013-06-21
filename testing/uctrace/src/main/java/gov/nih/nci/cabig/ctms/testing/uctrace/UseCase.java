/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.testing.uctrace;

/**
 * @author Rhett Sutphin
 */
public interface UseCase {
    int getMajor();

    int getMinor();

    String getTitle();
}
