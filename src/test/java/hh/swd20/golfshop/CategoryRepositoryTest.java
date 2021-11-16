package hh.swd20.golfshop;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.golfshop.domain.Category;
import hh.swd20.golfshop.domain.CategoryRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	// creating new category and saving it to database
	@Test
	public void createNewCategory() {
		Category category = new Category("TestCategory");
		categoryRepository.save(category);
		assertThat(category.getCategoryId()).isNotNull();
	}
		
	// finding category by id
	@Test
	public void findById() {
		Long id = (long) 2;
		Optional<Category> category = categoryRepository.findById(id);
		assertThat(category.get().getName()).isEqualTo("Balls");
	}
	
	// deleting category from database
	@Test
	public void deleteCategory() {
		Long id = (long) 6;
		categoryRepository.deleteById(id);
		assertThat(categoryRepository.findById(id)).isEmpty();
	}
	
	// updating category
	@Test
	public void updateCategory() {
		Long id = (long) 5;
		String name = "Shoes and spikes";
		Optional<Category> category = categoryRepository.findById(id);
		category.get().setName(name);
		categoryRepository.save(category.get());
		assertThat(categoryRepository.findById(id).get().getName()).isEqualTo("Shoes and spikes");
	}

}
