package com.vojislav.budgetingapp.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Budget implements Comparable<Budget> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@ManyToMany
	@JoinTable(inverseJoinColumns = @JoinColumn(name="user_id"),joinColumns = @JoinColumn(name = "budget_id"))
	@JsonIgnore
	private Set<User> users=new HashSet<>();
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "budget")
	@JsonIgnore
	@OrderBy("id")
	private Set<Group> groups=new TreeSet<>();
	private LocalDate startDate;
	private LocalDate endDate;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "budget")
	private Set<Transaction> transcations=new HashSet<>();
	
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Set<Group> getGroups() {
		return groups;
	}
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
	public Set<Transaction> getTranscations() {
		return transcations;
	}
	public void setTranscations(Set<Transaction> transcations) {
		this.transcations = transcations;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	@Override
	public int compareTo(Budget o) {
		return this.id.compareTo(o.getId());
	}
	
	
	
}
