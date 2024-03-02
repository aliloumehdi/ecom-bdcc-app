package ma.alilou.orderservice.restclients;

import ma.alilou.orderservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "http://localhost:8087",name = "inv-service")
public interface InventoryRestClient {
    @GetMapping("/api/products")
    List<Product>getAllProducts();
    @GetMapping("/api/products/{id}")
    Product findProductById(@PathVariable String id);
}
