/**
 * Attribution
 * CC BY
 * This license lets others distribute, remix, tweak,
 * and build upon your work, even commercially,
 * as long as they credit you for the original creation.
 * This is the most accommodating of licenses offered.
 * Recommended for maximum dissemination and use of licensed materials.
 *
 * http://creativecommons.org/licenses/by/3.0/
 * http://creativecommons.org/licenses/by/3.0/legalcode
 */
package com.thjug.jsfcrud.crud;

import com.thjug.jsfcrud.entity.Property;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nuboat
 */
@Stateless
public class PropertyFacade extends AbstractFacade<Property> {

	@PersistenceContext(unitName = "com.thjug_JsfCrud_war_1.0PU")
	private EntityManager em;

	public PropertyFacade() {
		super(Property.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
