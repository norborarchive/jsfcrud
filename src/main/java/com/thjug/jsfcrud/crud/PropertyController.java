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
import com.thjug.jsfcrud.entity.util.JsfUtil;
import com.thjug.jsfcrud.entity.util.JsfUtil.PersistAction;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author nuboat
 */
@SessionScoped
@Named("propertyController")
public class PropertyController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Property selected;
	private List<Property> items = null;

	@EJB
	private PropertyFacade ejbFacade;

	public PropertyController() {
	}

	public Property getSelected() {
		return selected;
	}

	public void setSelected(Property selected) {
		this.selected = selected;
	}

	protected void setEmbeddableKeys() {
	}

	protected void initializeEmbeddableKey() {
	}

	private PropertyFacade getFacade() {
		return ejbFacade;
	}

	public Property prepareCreate() {
		selected = new Property();
		initializeEmbeddableKey();
		return selected;
	}

	public void create() {
		persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PropertyCreated"));
		if (!JsfUtil.isValidationFailed()) {
			items = null;    // Invalidate list of items to trigger re-query.
		}
	}

	public void update() {
		persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PropertyUpdated"));
	}

	public void destroy() {
		persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PropertyDeleted"));
		if (!JsfUtil.isValidationFailed()) {
			selected = null; // Remove selection
			items = null;    // Invalidate list of items to trigger re-query.
		}
	}

	public List<Property> getItems() {
		if (items == null) {
			items = getFacade().findAll();
		}
		return items;
	}

	private void persist(final PersistAction persistAction, final String successMessage) {
		if (selected != null) {
			setEmbeddableKeys();
			try {
				if (persistAction != PersistAction.DELETE) {
					getFacade().edit(selected);
				} else {
					getFacade().remove(selected);
				}
				JsfUtil.addSuccessMessage(successMessage);
			} catch (EJBException ex) {
				final String msg;
				final Throwable cause = ex.getCause();
				if (cause != null) {
					msg = cause.getLocalizedMessage();
				} else {
					msg = "";
				}
				if (msg.length() > 0) {
					JsfUtil.addErrorMessage(msg);
				} else {
					JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
				}
			} catch (final Exception ex) {
				Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
				JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
			}
		}
	}

	public Property getProperty(final String id) {
		return getFacade().find(id);
	}

	public List<Property> getItemsAvailableSelectMany() {
		return getFacade().findAll();
	}

	public List<Property> getItemsAvailableSelectOne() {
		return getFacade().findAll();
	}

	@FacesConverter(forClass = Property.class)
	public static class PropertyControllerConverter implements Converter {

		@Override
		public Object getAsObject(final FacesContext facesContext, final  UIComponent component, final String value) {
			if (value == null || value.length() == 0) {
				return null;
			}
			final PropertyController controller = (PropertyController) facesContext.getApplication().getELResolver().
					getValue(facesContext.getELContext(), null, "propertyController");
			return controller.getProperty(getKey(value));
		}

		String getKey(final String value) {
			java.lang.String key;
			key = value;
			return key;
		}

		String getStringKey(final String value) {
			return value;
		}

		@Override
		public String getAsString(final FacesContext facesContext, final UIComponent component, final Object object) {
			if (object == null) {
				return null;
			}
			if (object instanceof Property) {
				final Property o = (Property) object;
				return getStringKey(o.getId());
			} else {
				Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Property.class.getName()});
				return null;
			}
		}

	}

}
