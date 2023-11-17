package tn.esprit.devops_project.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.StockServiceImpl;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;

class StockServiceImplTest {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddStock() {
        Stock stock = new Stock();
        stock.setTitle("Test Stock");

        when(stockRepository.save(stock)).thenReturn(stock);

        Stock addedStock = stockService.addStock(stock);
        Assertions.assertEquals("Test Stock", addedStock.getTitle());
    }

    @Test
    public void testRetrieveStock() {
        Stock stock = new Stock();
        stock.setIdStock(1L);
        stock.setTitle("Test Stock");

        when(stockRepository.findById(1L)).thenReturn(java.util.Optional.of(stock));

        Stock retrievedStock = stockService.retrieveStock(1L);
        Assertions.assertEquals("Test Stock", retrievedStock.getTitle());
        Assertions.assertEquals(1L, retrievedStock.getIdStock());
    }

    @Test
    public void testRetrieveAllStocks() {
        Stock stock1 = new Stock();
        stock1.setIdStock(1L);
        Stock stock2 = new Stock();
        stock2.setIdStock(2L);

        List<Stock> stocks = new ArrayList<>();
        stocks.add(stock1);
        stocks.add(stock2);

        when(stockRepository.findAll()).thenReturn(stocks);

        List<Stock> allStocks = stockService.retrieveAllStock();
        Assertions.assertEquals(2, allStocks.size());
    }

    // Edge case
    @Test
    public void testRetrieveStockNotFound() {
        when(stockRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(NullPointerException.class, () -> {
            stockService.retrieveStock(1L);
        });
    }

    @Test
    public void testAddStockNullTitle() {
        Stock stock = new Stock();
        stock.setTitle(null);

        when(stockRepository.save(stock)).thenReturn(stock);

        Stock addedStock = stockService.addStock(stock);
        Assertions.assertNull(addedStock.getTitle());
    }

}
