package com.shop;

import com.shop.controller.CheckOutController;
import com.shop.service.CheckOutService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CheckOutControllerTest {

	@Mock
	CheckOutService checkOutService;

	@InjectMocks
    CheckOutController checkOutController;

	@Test
    //TODO other tests... usually I would be more strictly TDD however with limited time I have focussed
    // on design and an integration test that replicates an instance of checking out
	public void testCreateOrder_success() {
	    Long pricingRulesId = 1L;
	    Long orderId = 2L;
		when(checkOutService.createOrder(pricingRulesId)).thenReturn(orderId);

        Long result = checkOutController.createOrder(pricingRulesId);

        assertEquals(orderId, result);
		verify(checkOutService, times(1)).createOrder(pricingRulesId);
	}
}

