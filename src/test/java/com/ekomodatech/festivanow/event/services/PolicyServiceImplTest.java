package com.ekomodatech.festivanow.event.services;

import com.ekomodatech.festivanow.event.entity.Policy;
import com.ekomodatech.festivanow.event.repository.PolicyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PolicyServiceImplTest {

    @Mock
    private PolicyRepository policyRepository;

    @InjectMocks
    private PolicyServiceImpl policyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSavePolicy() {
        // Create a sample policy
        Policy policy = new Policy();
//        long id = policy.getIdPolicy();
        policy.setName("Sample Policy");

        // Mock the behavior of the policyRepository
        Mockito.when(policyRepository.save(policy)).thenReturn(policy);

        // Call the method under test
        Policy savedPolicy = policyService.save(policy);

        // Assert that the result is not null and has the expected policy
        assertNotNull(savedPolicy);
        assertEquals(policy, savedPolicy);
    }

    @Test
    public void testFindByIdPolicy() {
        // Create a sample policy
        Policy policy = new Policy();
        long id = policy.getIdPolicy();
        policy.setName("Sample Policy");

        // Mock the behavior of the policyRepository
        Mockito.when(policyRepository.findById(id)).thenReturn(Optional.of(policy));

        // Call the method under test
        Policy foundPolicy = policyService.findByIdPolicy(id);

        // Assert that the result is not null and has the expected policy
        assertNotNull(foundPolicy);
        assertEquals(policy, foundPolicy);
    }

    @Test
    public void testFindAllPolicies() {
        // Create sample policies
        Policy policy1 = new Policy();
        Policy policy2 = new Policy();
        List<Policy> policies = List.of(policy1, policy2);

        // Mock the behavior of the policyRepository
        Mockito.when(policyRepository.findAll()).thenReturn(policies);

        // Call the method under test
        List<Policy> foundPolicies = policyService.findAllPolicies();

        // Assert that the result is not null and contains the expected policies
        assertNotNull(foundPolicies);
        assertEquals(policies, foundPolicies);
    }

    @Test
    public void testDeletePolicy() {
        // Mock the behavior of the policyRepository
        Mockito.doNothing().when(policyRepository).deleteById(1L);

        // Call the method under test
        policyService.deletePolicy(1L);

        // Assert that the delete operation was invoked
        Mockito.verify(policyRepository, Mockito.times(1)).deleteById(1L);
    }

    // You can add similar test methods for other service methods
}
