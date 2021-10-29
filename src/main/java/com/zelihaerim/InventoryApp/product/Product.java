package com.zelihaerim.InventoryApp.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.zelihaerim.InventoryApp.category.Category;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=128, nullable=false, unique=true)
	private String name;
	
	private float price;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;

	@OneToMany(mappedBy="product", cascade=CascadeType.ALL)
	private List<ProductDetails> details = new ArrayList<>();
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public void addDetail(String name, String value) {
		this.details.add(new ProductDetails(name,value,this));
	}

	public List<ProductDetails> getDetails() {
		return details;
	}

	public void setDetails(List<ProductDetails> details) {
		this.details = details;
	}
	public void setDetail(Integer id, String name, String value) {
		this.details.add(new ProductDetails(id,name,value,this));
	}
	
}
