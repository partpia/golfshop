package hh.swd20.golfshop;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.golfshop.domain.Brand;
import hh.swd20.golfshop.domain.BrandRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BrandRepositoryTest {
	
	@Autowired
	private BrandRepository brandRepository;
	
	// creating new brand and saving it to database
	@Test
	public void createNewBrand() {
		Brand brand = new Brand("Testbrand");
		brandRepository.save(brand);
		assertThat(brand.getBrandId()).isNotNull();
	}
	
	// finding brand by id
	@Test
	public void findById() {
		Long id = (long) 9;
		Optional<Brand> brand = brandRepository.findById(id);
		assertThat(brand.get().getName()).isEqualTo("Wilson");
	}
	
	// deleting brand from database
	@Test
	public void deleteBrand() {
		Long id = (long) 5;
		brandRepository.deleteById(id);
		assertThat(brandRepository.findById(id)).isEmpty();
	}
	
	// updating brand
	@Test
	public void updateBrand() {
		Long id = (long) 5;
		String name = "PING";
		Optional<Brand> brand = brandRepository.findById(id);
		brand.get().setName(name);
		brandRepository.save(brand.get());
		assertThat(brandRepository.findById(id).get().getName()).isEqualTo("PING");
	}
	
}
