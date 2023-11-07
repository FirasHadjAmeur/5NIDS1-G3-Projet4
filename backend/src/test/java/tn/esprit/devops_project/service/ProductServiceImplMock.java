package tn.esprit.devops_project.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class ProductServiceImplMock {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    void setUp() {
        // Initialize Mockito annotations
    }

    @Test
    public void testAddProduct() {
        // Create a mock product and stock
        Product product = new Product();
        product.setTitle("Test Product");
        product.setPrice(10.0f);
        product.setQuantity(100);

        when(stockRepository.findById(1L)).thenReturn(Optional.of(new Stock())); // Mock the stock lookup

        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product); // Mock the save operation

        // Call the addProduct method and assert the result
        Product addedProduct = productService.addProduct(product, 1L);
        Assertions.assertEquals("Test Product", addedProduct.getTitle());
    }

    @Test
    public void testRetrieveProduct() {
        // Create a mock product
        Product product = new Product();
        product.setIdProduct(1L);
        product.setTitle("Test Product");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product)); // Mock the product lookup

        // Call the retrieveProduct method and assert the result
        Product retrievedProduct = productService.retrieveProduct(1L);
        Assertions.assertEquals("Test Product", retrievedProduct.getTitle());
        Assertions.assertEquals(1L, retrievedProduct.getIdProduct());
    }

    @Test
    public void testRetreiveAllProduct() {
        // Create a list of mock products
        Product product1 = new Product();
        product1.setIdProduct(1L);
        Product product2 = new Product();
        product2.setIdProduct(2L);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        when(productRepository.findAll()).thenReturn(products); // Mock the product list retrieval

        // Call the retreiveAllProduct method and assert the result
        List<Product> allProducts = productService.retreiveAllProduct();
        Assertions.assertEquals(2, allProducts.size());
    }

    @Test
    public void testRetrieveProductByCategory() {
        // Create a list of mock products with a specific category
        Product product1 = new Product();
        product1.setIdProduct(1L);
        product1.setCategory(ProductCategory.ELECTRONICS);

        Product product2 = new Product();
        product2.setIdProduct(2L);
        product2.setCategory(ProductCategory.ELECTRONICS);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        when(productRepository.findByCategory(ProductCategory.ELECTRONICS)).thenReturn(products); // Mock the product
                                                                                                  // list retrieval

        // Call the retrieveProductByCategory method and assert the result
        List<Product> categoryProducts = productService.retrieveProductByCategory(ProductCategory.ELECTRONICS);
        Assertions.assertEquals(2, categoryProducts.size());
    }

    @Test
    public void testDeleteProduct() {
        // Create a mock product
        Product product = new Product();
        product.setIdProduct(1L);

        // Call the deleteProduct method
        productService.deleteProduct(1L);

        // Verify that the method was called
        Mockito.verify(productRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void testRetreiveProductStock() {
        // Create a list of mock products with a specific stock
        Product product1 = new Product();
        product1.setIdProduct(1L);
        product1.setTitle("Product 1");

        Product product2 = new Product();
        product2.setIdProduct(2L);
        product2.setTitle("Product 2");

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        when(productRepository.findByStockIdStock(1L)).thenReturn(products); // Mock the product list retrieval

        // Call the retreiveProductStock method and assert the result
        List<Product> stockProducts = productService.retreiveProductStock(1L);
        Assertions.assertEquals(2, stockProducts.size());
    }
}
