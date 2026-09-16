package com.Store.MiniStore_app.Service;

import com.Store.MiniStore_app.Model.Product;
import com.Store.MiniStore_app.Repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

   public Product saveProduct(Product product){
        if(product.getPrice() == 0 || product.getPrice() < 0){
          throw new RuntimeException("Price cannot be negative");
        }
        if(product.getStock() == null || product.getStock() < 0){
            throw new RuntimeException("Stock cannot be negative");
        }
        if(product.getId() != null && productRepository.existsByCode(product.getCode())){
            throw new RuntimeException("Code already exists");
       }
        return productRepository.save(product);
   }

    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product updateProduct(Product product, Long id) {
        if (product.getPrice() == 0 || product.getPrice() < 0){
            throw new RuntimeException("Price cannot be negative");
        }

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()){
            throw new RuntimeException("Product not found");
        }

        Product productToUpdate = optionalProduct.get();
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setCode(product.getCode());
        productToUpdate.setStock(product.getStock());
        return productRepository.save(productToUpdate);
    }

    public void deleteProduct(Long id) {
        if(productRepository.findById(id).isPresent()){
            productRepository.deleteById(id);
            return;
        }
        throw new RuntimeException("Product not found");
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updatePrice(Long id, double price) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
        throw new RuntimeException("Product not found");
        }
        Product presentProduct = optionalProduct.get();
        presentProduct.setPrice(price);
        return productRepository.save(presentProduct);
    }

    public List<Product> searchByName(String name) {
        productRepository.findAll();
        return productRepository.findAllByName(name);

    }
}