package com.shop.controller;

import com.shop.service.CheckOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/checkout")
public class CheckOutController {

    CheckOutService checkOutService;

    @Autowired
    public CheckOutController(CheckOutService checkOutService) {
        this.checkOutService = checkOutService;
    }

    @RequestMapping(value = "/order/{pricingRulesId}", method = POST)
    @ResponseBody
    public Long createOrder(@PathVariable("pricingRulesId") Long pricingRulesId) {
        return checkOutService.createOrder(pricingRulesId);
    }

    @RequestMapping(value = "/order/{orderId}/item/{itemId}", method = POST)
    @ResponseBody
    public void scanItem(@PathVariable("orderId") Long orderId, @PathVariable("itemId") Long itemId) {
        checkOutService.scanItem(orderId, itemId);
    }

    @RequestMapping(value = "/order/{orderId}/item/{itemId}", method = DELETE)
    @ResponseBody
    public void voidItem(@PathVariable("orderId") Long orderId, @PathVariable("itemId") Long itemId) {
        checkOutService.voidItem(orderId, itemId);
    }

    @RequestMapping(value = "/order/{orderId}", method = GET)
    @ResponseBody
    public String getOrder(@PathVariable("orderId") Long orderId) {
        return checkOutService.getOrder(orderId);
    }
}