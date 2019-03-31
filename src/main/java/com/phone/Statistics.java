package com.phone;


import java.util.LinkedHashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Statistics {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private long validCount;
	private long invalidCount;
	private long updateCount;
	private String error;
	private String fileName;
	
	@JsonIgnore
	@OneToMany(mappedBy = "statistics", cascade = CascadeType.ALL)
	private Set<PhoneEntry> phoneEntries = new LinkedHashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getValidCount() {
		return validCount;
	}

	public void setValidCount(long validCount) {
		this.validCount = validCount;
	}

	public long getInvalidCount() {
		return invalidCount;
	}

	public void setInvalidCount(long invalidCount) {
		this.invalidCount = invalidCount;
	}

	public long getUpdateCount() {
		return updateCount;
	}

	public void setUpdateCount(long updateCount) {
		this.updateCount = updateCount;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Set<PhoneEntry> getPhoneEntries() {
		return phoneEntries;
	}

	public void setPhoneEntries(Set<PhoneEntry> phoneEntries) {
		this.phoneEntries = phoneEntries;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((error == null) ? 0 : error.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (int) (invalidCount ^ (invalidCount >>> 32));
		result = prime * result + ((phoneEntries == null) ? 0 : phoneEntries.hashCode());
		result = prime * result + (int) (updateCount ^ (updateCount >>> 32));
		result = prime * result + (int) (validCount ^ (validCount >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Statistics other = (Statistics) obj;
		if (error == null) {
			if (other.error != null)
				return false;
		} else if (!error.equals(other.error))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (invalidCount != other.invalidCount)
			return false;
		if (phoneEntries == null) {
			if (other.phoneEntries != null)
				return false;
		} else if (!phoneEntries.equals(other.phoneEntries))
			return false;
		if (updateCount != other.updateCount)
			return false;
		if (validCount != other.validCount)
			return false;
		return true;
	}

	
	
	
	

}
