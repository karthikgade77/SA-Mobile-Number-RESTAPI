package com.phone;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PhoneEntry {
	
	@Id
	@Column(nullable = false)
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(nullable = false)
	private String number;
	@Column(nullable = false)
	private String status;
	@Column(nullable = true)
	private String fix;
	
	@ManyToOne
	@JoinColumn
	private Statistics statistics;
	
	@Override
	public String toString() {
		return "PhoneEntry [id=" + id + ", number=" + number + ", status=" + status + ", fix=" + fix + "]";
	}
	public String getFix() {
		return fix;
	}
	public void setFix(String fix) {
		this.fix = fix;
	}
	public PhoneEntry()
	{
		
	}
	
	public PhoneEntry(Long id, String number, String status, String fix) {
		super();
		this.id = id;
		this.number = number;
		this.status = status;
		this.fix = fix;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fix == null) ? 0 : fix.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		PhoneEntry other = (PhoneEntry) obj;
		if (fix == null) {
			if (other.fix != null)
				return false;
		} else if (!fix.equals(other.fix))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
	

}
