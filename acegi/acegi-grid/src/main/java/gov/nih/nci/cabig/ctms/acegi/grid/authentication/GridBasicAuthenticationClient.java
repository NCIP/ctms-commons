/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.acegi.grid.authentication;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 * 
 */
public interface GridBasicAuthenticationClient {

    /**
     * 
     * @param username
     * @param password
     * @return String representation of grid proxy
     * @throws AuthenticationErrorException if an error is encountered during authentication
     */
    String authenticate(String username, String password) throws AuthenticationErrorException;

}
