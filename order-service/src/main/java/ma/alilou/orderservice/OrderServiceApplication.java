package ma.alilou.orderservice;

import ma.alilou.orderservice.entities.Order;
import ma.alilou.orderservice.entities.ProductItem;
import ma.alilou.orderservice.enums.OrderState;
import ma.alilou.orderservice.models.Product;
import ma.alilou.orderservice.repositories.OrderRepository;
import ma.alilou.orderservice.repositories.ProductItemRepository;
import ma.alilou.orderservice.restclients.InventoryRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(
			OrderRepository orderRepository,
			ProductItemRepository productItemRepository,
			InventoryRestClient inventoryRestClient
	){
		return args -> {
//			List<Product> allProducts = inventoryRestClient.getAllProducts();
			List<String> productsIds = List.of("P01","P02","P03");
			for (int i = 0; i < 5; i++) {
				Order order = Order.builder()
						.id(UUID.randomUUID().toString())
						.date(LocalDate.now())
						.orderState(OrderState.PENDING)
						.build();
				Order savedOrder = orderRepository.save(order);
				productsIds.forEach(product->{
					ProductItem productItem = ProductItem.builder()
							.productId(product)
							.quantity(new Random().nextInt(10))
							.price(Math.random()*6000)
							.order(savedOrder)
							.build();
					productItemRepository.save(productItem);
				});

			}
		};
	}
}
