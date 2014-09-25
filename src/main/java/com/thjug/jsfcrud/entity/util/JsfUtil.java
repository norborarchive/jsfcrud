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
package com.thjug.jsfcrud.entity.util;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

/**
 *
 * @author nuboat
 */
public class JsfUtil {

	public static SelectItem[] getSelectItems(final List<?> entities, final boolean selectOne) {
		final int size = selectOne ? entities.size() + 1 : entities.size();
		final SelectItem[] items = new SelectItem[size];
		int i = 0;
		if (selectOne) {
			items[0] = new SelectItem("", "---");
			i++;
		}
		for (Object x : entities) {
			items[i++] = new SelectItem(x, x.toString());
		}
		return items;
	}

	public static boolean isValidationFailed() {
		return FacesContext.getCurrentInstance().isValidationFailed();
	}

	public static void addErrorMessage(final Exception ex, final String defaultMsg) {
		final String msg = ex.getLocalizedMessage();
		if (msg != null && msg.length() > 0) {
			addErrorMessage(msg);
		} else {
			addErrorMessage(defaultMsg);
		}
	}

	public static void addErrorMessages(final List<String> messages) {
		for (final String message : messages) {
			addErrorMessage(message);
		}
	}

	public static void addErrorMessage(final String msg) {
		final FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}

	public static void addSuccessMessage(final String msg) {
		final FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
		FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
	}

	public static String getRequestParameter(final String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
	}

	public static Object getObjectFromRequestParameter(final String requestParameterName, final Converter converter, final UIComponent component) {
		final String theId = JsfUtil.getRequestParameter(requestParameterName);
		return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
	}

	public static enum PersistAction {
		CREATE,
		DELETE,
		UPDATE
	}
}
