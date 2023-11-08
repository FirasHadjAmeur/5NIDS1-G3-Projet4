package tn.esprit.devops_project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.services.OperatorServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class OperatorServiceImplTest {

    @InjectMocks
    private OperatorServiceImpl operatorService;

    @Mock
    private OperatorRepository operatorRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllOperators() {
        // Create a list of fictitious operators
        Operator operator1 = new Operator();
        operator1.setIdOperateur(1L);
        Operator operator2 = new Operator();
        operator2.setIdOperateur(2L);
        List<Operator> operators = new ArrayList<>();
        operators.add(operator1);
        operators.add(operator2);

        // Define the expected behavior of the repository mock
        when(operatorRepository.findAll()).thenReturn(operators);

        // Call the service method
        List<Operator> result = operatorService.retrieveAllOperators();

        // Verify that the method was called and the result matches the fictitious data
        Mockito.verify(operatorRepository, Mockito.times(1)).findAll();
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void testAddOperator() {
        // Create a fictitious operator
        Operator operator = new Operator();
        operator.setIdOperateur(1L);

        // Define the expected behavior of the repository mock
        when(operatorRepository.save(operator)).thenReturn(operator);

        // Call the service method
        Operator result = operatorService.addOperator(operator);

        // Verify that the method was called and the result matches the fictitious data
        Mockito.verify(operatorRepository, Mockito.times(1)).save(operator);
        Assertions.assertEquals(1L, result.getIdOperateur());
    }

    @Test
    public void testUpdateOperator() {
        // Create a fictitious operator to update
        Operator operator = new Operator();
        operator.setIdOperateur(1L);

        // Define the expected behavior of the repository mock
        when(operatorRepository.save(operator)).thenReturn(operator);

        // Call the service method
        Operator result = operatorService.updateOperator(operator);

        // Verify that the method was called and the result matches the fictitious data
        Mockito.verify(operatorRepository, Mockito.times(1)).save(operator);
        Assertions.assertEquals(1L, result.getIdOperateur());
    }

    @Test
    public void testDeleteOperator() {
        // Fictitious ID of the operator to delete
        Long operatorId = 1L;

        // Call the service method
        operatorService.deleteOperator(operatorId);

        // Verify that the method was called with the correct ID
        Mockito.verify(operatorRepository, Mockito.times(1)).deleteById(operatorId);
    }

    @Test
    public void testRetrieveOperator() {
        // Create a fictitious operator to retrieve
        Operator operator = new Operator();
        operator.setIdOperateur(1L);

        // Define the expected behavior of the repository mock
        when(operatorRepository.findById(1L)).thenReturn(Optional.of(operator));

        // Call the service method
        Operator result = operatorService.retrieveOperator(1L);

        // Verify that the method was called and the result matches the fictitious data
        Mockito.verify(operatorRepository, Mockito.times(1)).findById(1L);
        Assertions.assertEquals(1L, result.getIdOperateur());
    }
}
