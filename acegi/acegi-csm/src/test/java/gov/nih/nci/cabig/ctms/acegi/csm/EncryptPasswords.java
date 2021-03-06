/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.acegi.csm;

import gov.nih.nci.security.util.StringEncrypter;

public class EncryptPasswords {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        StringEncrypter enc = new StringEncrypter();
        System.out.println(enc.encrypt("user_1"));
        System.out.println(enc.encrypt("participant_cd1"));
    }

}
