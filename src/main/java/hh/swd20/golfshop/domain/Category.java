package hh.swd20.golfshop.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;
	
	@NotEmpty(message = "Category name must not be empty")
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<Product> categoryProducts;
	
	public Category() {
		super();
		this.name = null;
	}
	
	public Category(String name) {
		super();
		this.name = name;
	}
	//setters
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setCategoryProducts(List<Product> categoryProducts) {
		this.categoryProducts = categoryProducts;
	}
	// getters
	public Long getCategoryId() {
		return categoryId;
	}

	public String getName() {
		return name;
	}
	
	public List<Product> getCategoryProducts() {
		return categoryProducts;
	}

	@Override
	public String toString() {
		return "Category: categoryId=" + categoryId + ", name=" + name;
	}
}
