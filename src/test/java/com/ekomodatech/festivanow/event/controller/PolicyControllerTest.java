package com.ekomodatech.festivanow.event.controller;

import com.ekomodatech.festivanow.event.entity.Policy;
import com.ekomodatech.festivanow.event.repository.PolicyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PolicyControllerTest {

    @Mock
    private PolicyRepository policyRepository;

    @InjectMocks
    private PolicyController policyController;

    @Test
    void testFindPolicy() {
        // Create a sample policy
        Policy policy = new Policy();
        long id = policy.getIdPolicy();
        policy.setName("Sample Policy");

        // Mock the behavior of the policyRepository
        when(policyRepository.findById(id)).thenReturn(Optional.of(policy));

        // Call the method under test
        Policy result = policyController.findPolicy(id);

        // Assert that the result is not null and has the expected policy
        assertNotNull(result);
        assertEquals(policy, result);
    }

    @Test
    void testListPolicies() {
        // Create sample policies
        Policy policy1 = new Policy();
        Policy policy2 = new Policy();
        List<Policy> policies = List.of(policy1, policy2);

        // Mock the behavior of the policyRepository
        when(policyRepository.findAll()).thenReturn(policies);

        // Call the method under test
        List<Policy> result = policyController.listPolicies();

        // Assert that the result is not null and contains the expected policies
        assertNotNull(result);
        assertEquals(policies, result);
    }

    @Test
    void testCreatePolicy() {
        // Create a sample policy
        Policy policy = new Policy();
        policy.setName("Sample Policy");

        // Mock the behavior of the policyRepository
        when(policyRepository.save(policy)).thenReturn(policy);

        // Call the method under test
        Policy result = policyController.createPolicy(policy);

        // Assert that the result is not null and has the expected policy
        assertNotNull(result);
        assertEquals(policy, result);
    }

    @Test
    void testDeletePolicy() {
        // Mock the behavior of the policyRepository
//        when(policyRepository.deleteById(1L)).thenReturn(1L);

        // Call the method under test
        ResponseEntity<Void> result = policyController.deletePolicy(1L);

        // Assert that the result is not null and has the expected status
        assertNotNull(result);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    void testUpdate() {
        // Create a sample policy
        Policy policy = new Policy();
        long id = policy.getIdPolicy();
        policy.setName("Sample Policy");

        // Mock the behavior of the policyRepository
        when(policyRepository.findById(id)).thenReturn(Optional.of(policy));

        // Create a sample model
        Model model = new ExtendedModelMap();

        // Call the method under test
        String result = policyController.update(id, model);

        // Assert that the result is not null and has the expected message
        assertNotNull(result);
        assertEquals("update", result);
    }

    // You can add similar test methods for other controller endpoints

}
