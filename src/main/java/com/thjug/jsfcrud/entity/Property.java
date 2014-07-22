/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thjug.jsfcrud.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author PeerapatAsoktummarun
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Property.findAll", query = "SELECT p FROM Property p")})
public class Property implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "id")
	private String id;

	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "string")
	private String string;

	public Property() {
	}

	public Property(String id) {
		this.id = id;
	}

	public Property(String id, String string) {
		this.id = id;
		this.string = string;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Property)) {
			return false;
		}
		Property other = (Property) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.thjug.jsfcrud.entity.Property[ id=" + id + " ]";
	}

}
