/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.domain;

/**
 * Utiltity methods for enums.
 *
 * @author Rhett Sutphin
 */
public class EnumHelper {
    public static <T extends Enum<T>> String sentenceCasedName(T instance) {
        StringBuilder name = new StringBuilder(instance.name().toLowerCase());
        name.replace(0, 1, name.substring(0, 1).toUpperCase());
        for (int i = 0 ; i < name.length() ; i++) {
            if (name.charAt(i) == '_') name.setCharAt(i, ' ');
        }
        return name.toString();
    }

    public static <T extends Enum<T>> String titleCasedName(T instance) {
        StringBuilder name = new StringBuilder(instance.name().toLowerCase());
        name.setCharAt(0, Character.toUpperCase(name.charAt(0)));
        for (int i = 0 ; i < name.length() ; i++) {
            if (name.charAt(i) == '_') {
                name.setCharAt(i, ' ');
                name.setCharAt(i + 1, Character.toUpperCase(name.charAt(i + 1)));
            }
        }
        return name.toString();
    }
}
