/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.suite.authorization.domain;

/**
 * @author Rhett Sutphin
 */
public class TestStudy {
    private String ident;

    public TestStudy(String ident) {
        this.ident = ident;
    }

    public String getIdent() {
        return ident;
    }
}
