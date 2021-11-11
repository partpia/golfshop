package hh.swd20.golfshop;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.golfshop.domain.Brand;
import hh.swd20.golfshop.domain.BrandRepository;
import hh.swd20.golfshop.domain.Category;
import hh.swd20.golfshop.domain.CategoryRepository;
import hh.swd20.golfshop.domain.Gender;
import hh.swd20.golfshop.domain.Product;
import hh.swd20.golfshop.domain.ProductRepository;
import hh.swd20.golfshop.domain.User;
import hh.swd20.golfshop.domain.UserRepository;

@SpringBootApplication
public class GolfshopApplication {
	private static final Logger log = LoggerFactory.getLogger(GolfshopApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GolfshopApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner productDemo(ProductRepository productRepository, CategoryRepository categoryRepository,
			BrandRepository brandRepository, UserRepository userRepository) {
		return (args) -> {
			log.info("Save a couple categories");
			Category category1 = new Category("Bags");
			Category category2 = new Category("Balls");
			Category category3 = new Category("Carts");
			Category category4 = new Category("Clubs");
			Category category5 = new Category("Clothing");
			Category category6 = new Category("Gloves");
			Category category7 = new Category("Shoes");
			Category category8 = new Category("Other aids");
			Category category9 = new Category("Training aids");
			Category category10 = new Category("Other");
			
			categoryRepository.save(category1);
			categoryRepository.save(category2);
			categoryRepository.save(category3);
			categoryRepository.save(category4);
			categoryRepository.save(category5);
			categoryRepository.save(category6);
			categoryRepository.save(category7);
			categoryRepository.save(category8);
			categoryRepository.save(category9);
			categoryRepository.save(category10);
			
			log.info("Save a couple brands");
			Brand brand1 = new Brand("Callaway");
			Brand brand2 = new Brand("Cleveland");
			Brand brand3 = new Brand("Cobra");
			Brand brand4 = new Brand("Mizuno");
			Brand brand5 = new Brand("Ping");
			Brand brand6 = new Brand("Srixon");
			Brand brand7 = new Brand("TaylorMade");
			Brand brand8 = new Brand("Titleist");
			Brand brand9 = new Brand("Wilson");
			Brand brand10 = new Brand("Other");
			
			brandRepository.save(brand1);
			brandRepository.save(brand2);
			brandRepository.save(brand3);
			brandRepository.save(brand4);
			brandRepository.save(brand5);
			brandRepository.save(brand6);
			brandRepository.save(brand7);
			brandRepository.save(brand8);
			brandRepository.save(brand9);
			brandRepository.save(brand10);
			
			log.info("Save users");
			User user1 = new User("user", "$2a$10$8LaV9cSMGuHcoSxUs0CHrOAg0OCX2l.ccP7RO/sF5C6xjADnciE06", "User", "UserLast", "user@user.fi", "1234567", "USER");
			User user2 = new User("admin", "$2a$10$dBWnLMvsvVUzxt8pBpnOteDNcDM0PzErA.hEEsjOpnSX156rhrj5u", "Aino", "Admin", "admin@admin.com", "7654321", "ADMIN");
			User user3 = new User("kerttuli", "$2a$10$QXo6wkng3.hJ9kLgErZLzO9PfsjBYhIdQ09ylqmMA.G65jZebfntG", "Kerttu", "Leppä", "leppa@kerttu.fi", "050123457", "USER");
			User user4 = new User("r.reipas", "$2a$10$qhK8d9Qa0KFCAAMVfCmSc.E9qiI28c.LPBm3crN2lc1D.5z9ro95S", "Risto", "Reipas", "rreipas@mail.com", "0507654321", "USER");
			
			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(user3);
			userRepository.save(user4);
			
			log.info("Save a couple products");
			Product product1 = new Product("D300", "Yhden kesän pelatut", Gender.FEMALE, 199.90, new Date(), brand9, category4, user3);
			Product product2 = new Product("Pro V1 Yellow", "Vajaa pakkaus, 10kpl, käyttämättömät", Gender.UNISEX, 39.90, new Date(), brand8, category2, user4);
			Product product3 = new Product("Miesten talvihanskat", "Mustat, kerran käytetyt", Gender.MALE, 17.80, new Date(), brand1, category6, user2);
			
			productRepository.save(product1);
			productRepository.save(product2);
			productRepository.save(product3);
		};
	}

}
