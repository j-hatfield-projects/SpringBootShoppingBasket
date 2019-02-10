package com.shop.service;

import com.shop.entity.ItemDiscount;
import com.shop.entity.Order;
import com.shop.entity.OrderLine;
import com.shop.repository.ItemDiscountRepository;
import com.shop.repository.ItemRepository;
import com.shop.repository.OrderLineRepository;
import com.shop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.groupingBy;

@Service
public class CheckOutService {

    //TODO Constructor injection
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemDiscountRepository itemDiscountRepository;

    public Long createOrder(Long pricingRulesId){
        return orderRepository.save(Order.builder()
                //TODO JPA mapping to PricingRules
                .pricingRuleId(pricingRulesId)
                .build())
                .getId();
    }

    public void scanItem(Long orderId, Long itemId) {
        Order order = orderRepository.findById(orderId).get();
        OrderLine orderLine = OrderLine.builder()
                .orderId(orderId)
                .itemId(itemId)
                .build();
        orderLineRepository.save(orderLine);
        order.getOrderLines().add(orderLine);
        orderRepository.save(order);
    }

    public void voidItem(Long orderId, Long itemId) {
        Order order = orderRepository.findById(orderId).get();
        OrderLine orderLine = order.getOrderLines().stream()
                .filter(o -> o.getItemId().equals(itemId))
                .findFirst()
                .get();
        order.getOrderLines().remove(orderLine);
        orderRepository.save(order);
    }

    public String getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        Map<Long, List<OrderLine>> groupedOrderLines = order.getOrderLines().stream()
                .collect(groupingBy(OrderLine::getItemId));

        BigDecimal totalPrice = groupedOrderLines.entrySet().stream()
                .map(this :: calculateItemTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return "Order total: £" + totalPrice;
    }

    private BigDecimal calculateItemTotal(Map.Entry<Long, List<OrderLine>> entry) {
        BigDecimal itemPrice = itemRepository.findById(entry.getKey()).get().getUnitPrice();
        //TODO pass in the order instead of looking it up again... I have gone over the suggested 3 hours
        // also I would consider having a DiscountService
        Long pricingRuleId = orderRepository.findById(entry.getValue().get(0).getOrderId())
                .get().getPricingRuleId();
        Optional<ItemDiscount> itemDiscountOptional = itemDiscountRepository
                .findByItemIdAndPricingRuleId(entry.getKey(), pricingRuleId);
        if(!itemDiscountOptional.isPresent()){
            return itemPrice.multiply(new BigDecimal(entry.getValue().size()));
        } else {
            return calculateDiscountPrice(entry, itemPrice, itemDiscountOptional);
        }
    }

    private BigDecimal calculateDiscountPrice(Map.Entry<Long, List<OrderLine>> entry, BigDecimal itemPrice,
                                              Optional<ItemDiscount> itemDiscountOptional) {
        ItemDiscount itemDiscount = itemDiscountOptional.get();
        int itemQty = entry.getValue().size();
        int specialPriceQty = itemQty / itemDiscount.getQuantityThreshold();
        int regularPriceQty = itemQty % itemDiscount.getQuantityThreshold();
        BigDecimal specialPriceTotal = itemDiscount.getSpecialPrice().multiply(new BigDecimal(specialPriceQty));
        BigDecimal regularPriceTotal = itemPrice.multiply(new BigDecimal(regularPriceQty));
        return specialPriceTotal.add(regularPriceTotal);
    }
}