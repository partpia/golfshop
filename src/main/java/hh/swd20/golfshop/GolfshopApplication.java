package hh.swd20.golfshop;

import java.time.LocalDate;
import java.time.ZoneId;
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
			// dates
			LocalDate lDate1 = LocalDate.of(2021, 7, 26);
			LocalDate lDate2 = LocalDate.of(2021, 9, 9);
			LocalDate lDate3 = LocalDate.of(2021, 10, 7);
			LocalDate lDate4 = LocalDate.of(2021, 11, 3);
			
			Date date1 = Date.from(lDate1.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
			Date date2 = Date.from(lDate2.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
			Date date3 = Date.from(lDate3.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
			Date date4 = Date.from(lDate4.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
			
			Product product1 = new Product("Stand Bag, Hippo", "Big opportunity! Blue-white, lightweight, lots of pockets, rain hood included.", Gender.UNISEX, 325.50, date1, brand10, category10, user4);
			Product product2 = new Product("D300", "Irons 9-6, P, S. Little scratched, grips as good as new. Great clubs for beginnner!", Gender.FEMALE, 199.90, date2, brand9, category4, user3);
			Product product3 = new Product("Cross W Hurricaine rain jacket", "Used couple of times, too small for me. Black-white, size 34.", Gender.FEMALE, 100, date2, brand10, category5, user3);
			Product product4 = new Product("Ping cap", "Pink Ping cap, a small makeup stain inside.", Gender.FEMALE, 12, date2, brand5, category5, user3);
			Product product5 = new Product("Pro V1 Yellow", "Half-empty packet, 6 balls.", Gender.UNISEX, 19.90, date3, brand8, category2, user4);
			Product product6 = new Product("Winter gloves - pair", "Black, used only once, size L.", Gender.MALE, 17.80, date4, brand1, category6, user2);
			
			productRepository.save(product1);
			productRepository.save(product2);
			productRepository.save(product3);
			productRepository.save(product4);
			productRepository.save(product5);
			productRepository.save(product6);
			
		};
	}

}
