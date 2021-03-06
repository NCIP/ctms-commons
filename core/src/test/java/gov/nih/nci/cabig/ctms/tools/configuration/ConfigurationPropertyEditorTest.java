/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.tools.configuration;

import java.util.List;
import java.util.Arrays;

import gov.nih.nci.cabig.ctms.testing.CommonsCoreTestCase;

/**
 * @author Rhett Sutphin
 */
public class ConfigurationPropertyEditorTest extends CommonsCoreTestCase {
    private ConfigurationPropertyEditor<List<String>> editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        editor = new ConfigurationPropertyEditor<List<String>>(ExampleConfiguration.ADDRESSES);
    }

    @SuppressWarnings("unchecked")
    public void testSetText() throws Exception {
        editor.setAsText("a, b, c, d");

        assertTrue(editor.getValue() instanceof List);
        List<String> actual = ((List<String>) editor.getValue());
        assertEquals("Wrong number of values", 4, actual.size());
        assertEquals("Wrong value 0", "a", actual.get(0));
        assertEquals("Wrong value 1", "b", actual.get(1));
        assertEquals("Wrong value 2", "c", actual.get(2));
        assertEquals("Wrong value 3", "d", actual.get(3));
    }

    public void testSetTextBlankIsNull() throws Exception {
        editor.setAsText(" \t  \r");
        assertNull(editor.getValue());
    }
    
    public void testGetText() throws Exception {
        editor.setValue(Arrays.asList("g", "h", "i"));
        assertEquals("g, h, i", editor.getAsText());
    }
    
    public void testGetTextWhenNull() throws Exception {
        editor.setValue(null);
        assertNull(editor.getAsText());
    }
}
