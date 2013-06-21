/*
 * Copyright Northwestern University and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
 */
package testapp.dao;

import java.util.Collection;

import testapp.bean.Person;

public interface PersonDao {
	
	Person findById(Long id);
	
	Collection findByName(String name);
	
	Long save(Person person);
	
	void update(Person person);

	Collection getAll();

}
