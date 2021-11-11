package hh.swd20.golfshop.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Brand {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long brandId;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "brand")
	private List<Product> brandProducts;
	
	public Brand() {
		super();
		this.name = null;
	}

	public Brand(String name) {
		super();
		this.name = name;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setBrandProducts(List<Product> brandProducts) {
		this.brandProducts = brandProducts;
	}

	public Long getBrandId() {
		return brandId;
	}

	public String getName() {
		return name;
	}
	
	public List<Product> getBrandProducts() {
		return brandProducts;
	}

	@Override
	public String toString() {
		return "Brand: brandId=" + brandId + ", name=" + name;
	}
}
