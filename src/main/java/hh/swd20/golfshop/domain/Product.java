package hh.swd20.golfshop.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	private String name;
	private String description;
	private Gender gender;
	private double price;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date publicationDate;
	
	@ManyToOne
	@JsonIgnoreProperties("brandProducts")
	@JoinColumn(name="brandId")
	private Brand brand;
	
	@ManyToOne
	@JsonIgnoreProperties("categoryProducts")
	@JoinColumn(name="categoryId")
	private Category category;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "userId")
	private User seller;
	
	public Product() {
		super();
		this.name = null;
		this.description = null;
		this.gender = null;
		this.price = 0.00;
		this.publicationDate = null;
		this.brand = null;
		this.category = null;
		this.seller = null;
	}
	
	public Product(String name, String description, Gender gender, double price, Date publicationDate,
			Brand brand, Category category, User seller) {
		super();
		this.name = name;
		this.description = description;
		this.gender = gender;
		this.price = price;
		this.publicationDate = publicationDate;
		this.brand = brand;
		this.category = category;
		this.seller = seller;
	}
	// tämä testausta varten
	public Product(String name, String description, Gender gender, double price,
			Brand brand, Category category, Date publicationDate) {
		super();
		this.name = name;
		this.description = description;
		this.gender = gender;
		this.price = price;
		this.brand = brand;
		this.category = category;
		this.publicationDate = publicationDate;
	}
	
	public Product(String name, String description, Gender gender, double price,
			Brand brand, Category category) {
		super();
		this.name = name;
		this.description = description;
		this.gender = gender;
		this.price = price;
		this.brand = brand;
		this.category = category;
	}
	
	public Product(String name, Gender gender, double price, Date publicationDate,
			Brand brand, Category category, User seller) {
		super();
		this.name = name;
		this.gender = gender;
		this.price = price;
		this.publicationDate = publicationDate;
		this.brand = brand;
		this.category = category;
		this.seller = seller;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public Long getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Gender getGender() {
		return gender;
	}

	public double getPrice() {
		return price;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public Brand getBrand() {
		return brand;
	}

	public Category getCategory() {
		return category;
	}

	public User getSeller() {
		return seller;
	}
		
	@Override
	public String toString() {
		return "Product: productId=" + productId + ", name=" + name + ", description=" + description + ", gender="
				+ gender + ", price=" + price + ", date=" + publicationDate + ", brand=" + brand + ", category="
				+ category + ", seller=" + seller;
	}
}
