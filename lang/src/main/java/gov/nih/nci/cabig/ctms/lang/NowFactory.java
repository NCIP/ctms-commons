/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.lang;

import java.util.Date;
import java.sql.Timestamp;

/**
 * Provides an injectable interface for determining "now."  This makes it easier to write
 * consistent tests for code that needs to set some value to the current date or timestamp.
 *
 * @author Rhett Sutphin
 */
public class NowFactory {
    public Date getNow() {
        return new Date();
    }

    public Timestamp getNowTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
