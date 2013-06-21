/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.web.tabs;

/**
 * @author Rhett Sutphin
 */
public class SimpleTestTab<C> extends Tab<C> {
    public SimpleTestTab(int num) {
        super("Tab " + num, "Tab " + num, "v" + num);
    }
}
