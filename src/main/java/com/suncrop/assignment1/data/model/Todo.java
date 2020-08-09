/**
 * The data model used to fetch a data from the datasource
 */
package com.suncrop.assignment1.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import javax.persistence.Id;

@Entity
@Table(name = "TodoList")
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "completed")
	private boolean completed;
	
	public Todo() {}
	
	public Todo(String itemName, boolean completed) {
	    this.itemName = itemName;
	    this.completed = completed;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the completed
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * @param completed the completed to set
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}	
}
