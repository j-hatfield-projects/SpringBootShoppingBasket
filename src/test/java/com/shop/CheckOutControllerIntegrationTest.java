package com.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CheckOutControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @Test
    //TODO with more time I would have tests for each scenario of each API. This test is to show how the API
    // is used to meet the spec
    public void testCheckoutProcess() {
        //create order to begin checkout
        template.postForEntity("http://localhost:" + port + "/checkout/order/1",
                null, Long.class);

        //scan some items
        template.postForEntity("http://localhost:" + port + "/checkout/order/1/item/1",
                null, Void.class);
        template.postForEntity("http://localhost:" + port + "/checkout/order/1/item/1",
                null, Void.class);
        template.postForEntity("http://localhost:" + port + "/checkout/order/1/item/1",
                null, Void.class);

        template.postForEntity("http://localhost:" + port + "/checkout/order/1/item/2",
                null, Void.class);
        template.postForEntity("http://localhost:" + port + "/checkout/order/1/item/2",
                null, Void.class);

        template.postForEntity("http://localhost:" + port + "/checkout/order/1/item/3",
                null, Void.class);

        //subtotal
        ResponseEntity<String> response = template.getForEntity("http://localhost:" + port + "/checkout/order/1",
               String.class);
        assertEquals(response.getBody(), "Order total: £195.00");

        //void an item
        template.delete("http://localhost:" + port + "/checkout/order/1/item/2");

        //final total
        response = template.getForEntity("http://localhost:" + port + "/checkout/order/1",
                String.class);
        assertEquals(response.getBody(), "Order total: £180.00");
    }
}