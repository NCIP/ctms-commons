/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package gov.nih.nci.cabig.ctms.audit.dao.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Saurabh Agrawal
 */
public class AbstractQuery {

    private final String queryString;

    private StringBuffer queryBuffer;

    private final List<String> andConditions = new LinkedList<String>();
    private final List<String> orConditions = new LinkedList<String>();

    private final List<String> joins = new ArrayList<String>();

    private final Map<String, Object> queryParameterMap;

    private static String WHERE = "WHERE";

    private static String AND = "AND";

    private static String OR = "OR";

    public AbstractQuery(final String queryString) {
        this.queryString = queryString;
        queryParameterMap = new HashMap<String, Object>(0);
    }

    public String getQueryString() {
        String orderByString = "";
        if (queryString.lastIndexOf("order by") > 0) {
            orderByString = queryString.substring(queryString.lastIndexOf("order by"), queryString.length()).trim();
            queryBuffer = new StringBuffer(queryString.substring(0, queryString.lastIndexOf("order by")).trim());
        } else {
            queryBuffer = new StringBuffer(queryString.trim());
        }

        for (String join : joins) {
            queryBuffer.append(join);
        }

        for (String conditon : andConditions) {
            if (queryBuffer.toString().toUpperCase().indexOf(WHERE) < 0) {
                queryBuffer.append(" " + WHERE + " " + conditon);
            } else {
                queryBuffer.append(" " + AND + " " + conditon);
            }

        }

        if (!orConditions.isEmpty()) {
            boolean groupOR = andConditions.size() > 0 || queryBuffer.toString().toUpperCase().indexOf(WHERE) > 0;

            if (groupOR) {
                queryBuffer.append(" ");
                queryBuffer.append(AND);
                queryBuffer.append(" (");
            }

            int orIndx = 0;
            for (String conditon : orConditions) {
                if (orIndx == 0 && groupOR) {
                    queryBuffer.append(" " + conditon);
                } else {
                    queryBuffer.append(" ");
                    queryBuffer.append(OR);
                    queryBuffer.append(" ");
                    queryBuffer.append(conditon);
                }
                orIndx++;
            }

            if (groupOR) {
                queryBuffer.append(" )");
            }
        }


        if (!orderByString.equalsIgnoreCase("")) {
            // finally add order by
            queryBuffer.append(" ");
            queryBuffer.append(orderByString);
        }

        return queryBuffer.toString();
    }

    /**
     * Bind an argument to a named parameter.
     *
     * @param key   the key of the parameter
     * @param value the value of the parameter
     */
    protected void setParameter(final String key, final Object value) {
        queryParameterMap.put(key, value);
    }

    /**
     * add the 'Where' condition to the existing Query String.
     * <p>
     * For example if for the queryString is "Select * from Article a order by a.id"; andWhere("a.name=:name") will append queryString to
     * "Select * from Article a WHERE a.name=:name order by a.id"
     * </p>
     *
     * @param condition the condition
     */
    protected void andWhere(final String condition) {
        andConditions.add(condition);
    }


    public Map<String, Object> getParameterMap() {
        return queryParameterMap;
    }

    /**
     * Joins an object to the query
     * select * from Study s join s.identifiers as id where s.shortTitle='study'
     *
     * @param objectQuery
     */
    protected void join(String objectQuery) {
        addToJoinsList(" join " + objectQuery);

    }

    /**
     * Joins an object to the query
     * select * from Study s left join s.identifiers as id where s.shortTitle='study'
     *
     * @param objectQuery
     */
    public void leftJoin(String objectQuery) {
        addToJoinsList(" left join " + objectQuery);
    }

    public void leftJoinFetch(String objectQuery) {
        addToJoinsList(" left join fetch " + objectQuery);
    }

    private void addToJoinsList(String object){
        if(!joins.contains(object)) joins.add(object);
    }
}

