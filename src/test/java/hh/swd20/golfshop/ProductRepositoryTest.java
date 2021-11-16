package hh.swd20.golfshop;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.golfshop.domain.Brand;
import hh.swd20.golfshop.domain.Category;
import hh.swd20.golfshop.domain.Gender;
import hh.swd20.golfshop.domain.Product;
import hh.swd20.golfshop.domain.ProductRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProductRepositoryTest {
	
	@Autowired
	private ProductRepository productRepository;
	
	// creating new product and saving it to database
	@Test
	public void createNewProduct() {
		Product product = new Product("Iron 3", "Iron club no 3", Gender.MALE, 80, new Date(), new Brand("TestBrand"), new Category("TestCategory"));
		productRepository.save(product);
		assertThat(product.getProductId()).isNotNull();
	}
	
	// finding product by id
	@Test
	public void findById() {
		Long id = (long) 2;
		Optional<Product> product = productRepository.findById(id);
		assertThat(product.get().getName()).isEqualTo("D300");
	}
	
	// deleting product from database
	@Test
	public void deleteProduct() {
		Long id = (long) 4;
		productRepository.deleteById(id);
		assertThat(productRepository.findById(id)).isEmpty();
	}
	
	// updating product
	@Test
	public void updateProduct() {
		Long id = (long) 2;
		String name = "D300 irons";
		Optional<Product> product = productRepository.findById(id);
		product.get().setName(name);
		productRepository.save(product.get());
		assertThat(productRepository.findById(id).get().getName()).isEqualTo("D300 irons");
	}
	
}
