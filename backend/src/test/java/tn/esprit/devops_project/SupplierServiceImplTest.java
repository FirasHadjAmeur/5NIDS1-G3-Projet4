package tn.esprit.devops_project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.SupplierServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class SupplierServiceImplTest {

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Mock
    private SupplierRepository supplierRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllSuppliers() {
        // Créez une liste de fournisseurs fictifs
        Supplier supplier1 = new Supplier();
        supplier1.setIdSupplier(1L);
        Supplier supplier2 = new Supplier();
        supplier2.setIdSupplier(2L);
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(supplier1);
        suppliers.add(supplier2);

        // Définissez le comportement attendu du mock du répertoire
        when(supplierRepository.findAll()).thenReturn(suppliers);

        // Appelez la méthode du service
        List<Supplier> result = supplierService.retrieveAllSuppliers();

        // Vérifiez que la méthode a été appelée et que le résultat correspond aux données fictives
        Mockito.verify(supplierRepository, Mockito.times(1)).findAll();
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void testAddSupplier() {
        // Créez un fournisseur fictif
        Supplier supplier = new Supplier();
        supplier.setIdSupplier(1L);

        // Définissez le comportement attendu du mock du répertoire
        when(supplierRepository.save(supplier)).thenReturn(supplier);

        // Appelez la méthode du service
        Supplier result = supplierService.addSupplier(supplier);

        // Vérifiez que la méthode a été appelée et que le résultat correspond aux données fictives
        Mockito.verify(supplierRepository, Mockito.times(1)).save(supplier);
        Assertions.assertEquals(1L, result.getIdSupplier());
    }

    @Test
    public void testUpdateSupplier() {
        // Créez un fournisseur fictif à mettre à jour
        Supplier supplier = new Supplier();
        supplier.setIdSupplier(1L);

        // Définissez le comportement attendu du mock du répertoire
        when(supplierRepository.save(supplier)).thenReturn(supplier);

        // Appelez la méthode du service
        Supplier result = supplierService.updateSupplier(supplier);

        // Vérifiez que la méthode a été appelée et que le résultat correspond aux données fictives
        Mockito.verify(supplierRepository, Mockito.times(1)).save(supplier);
        Assertions.assertEquals(1L, result.getIdSupplier());
    }

    @Test
    public void testDeleteSupplier() {
        // ID fictif du fournisseur à supprimer
        Long supplierId = 1L;

        // Appelez la méthode du service
        supplierService.deleteSupplier(supplierId);

        // Vérifiez que la méthode a été appelée avec le bon ID
        Mockito.verify(supplierRepository, Mockito.times(1)).deleteById(supplierId);
    }

    @Test
    public void testRetrieveSupplier() {
        // Créez un fournisseur fictif à récupérer
        Supplier supplier = new Supplier();
        supplier.setIdSupplier(1L);

        // Définissez le comportement attendu du mock du répertoire
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));

        // Appelez la méthode du service
        Supplier result = supplierService.retrieveSupplier(1L);

        // Vérifiez que la méthode a été appelée et que le résultat correspond aux données fictives
        Mockito.verify(supplierRepository, Mockito.times(1)).findById(1L);
        Assertions.assertEquals(1L, result.getIdSupplier());
    }



}
