/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package testapp.dao;

import java.util.Collection;

import testapp.bean.Statement;

public interface StatementDao {
	
	Statement findById(Long id);
	

	Long save(Statement statement);
	
	void update(Statement statement);

	Collection getAll();

}
