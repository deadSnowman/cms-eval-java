package com.test.dao;

import com.test.dao.Entity;

/**
 * Make the provided Entity abstract class usable for use in the EntityService
 * @author SethThomas
 *
 */
public class EntityImpl extends Entity {

	/**
	 * EntityImpl has all the functions of Entity, but can now be instantiated
	 */
	public EntityImpl() {
		super();
	}
	
}
