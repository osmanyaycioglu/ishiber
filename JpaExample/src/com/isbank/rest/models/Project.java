package com.isbank.rest.models;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Project {
	@Id
	@GeneratedValue
	private long projectId;

	@Column(unique = true)
	private String name;
	
	@Column(name = "description")
	private String desc;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	@JoinTable(name = "cust_proj", joinColumns = @JoinColumn(name = "proj_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "cust_id", nullable = false))
	private Set<Customer> customers;

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customers == null) ? 0 : customers.hashCode());
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (projectId ^ (projectId >>> 32));
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
		Project other = (Project) obj;
		if (customers == null) {
			if (other.customers != null)
				return false;
		} else if (!customers.equals(other.customers))
			return false;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (projectId != other.projectId)
			return false;
		return true;
	}

	public static void main(String[] args) {
		Project pro1 = new Project();
		pro1.setProjectId(1);
		pro1.setName("proj1");
		pro1.setDesc("abc");

		Project pro2 = new Project();
		pro2.setProjectId(1);
		pro2.setName("proj1");
		pro2.setDesc("abc");

		String string = "osman";
		String string2 = new String("osman");
		
		if (string.equals(string2)) {
			System.out.println("str equals");
		} else {
			System.out.println("str not equals");
			
		}
		
		
		if (pro1.equals(pro2)) {
			System.out.println("equals");
		} else {
			System.out.println("not equals");
			
		}
		
	}

}
