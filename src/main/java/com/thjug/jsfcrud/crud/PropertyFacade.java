/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thjug.jsfcrud.crud;

import com.thjug.jsfcrud.entity.Property;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author PeerapatAsoktummarun
 */
@Stateless
public class PropertyFacade extends AbstractFacade<Property> {
	@PersistenceContext(unitName = "com.thjug_JsfCrud_war_1.0PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public PropertyFacade() {
		super(Property.class);
	}

}
