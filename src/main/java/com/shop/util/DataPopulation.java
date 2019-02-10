package com.shop.util;

import com.shop.entity.Item;
import com.shop.entity.ItemDiscount;
import com.shop.entity.PricingRule;
import com.shop.repository.ItemDiscountRepository;
import com.shop.repository.ItemRepository;
import com.shop.repository.PricingRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Component
public class DataPopulation {

    @Autowired//TODO constructor injection
    private PricingRuleRepository pricingRuleRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemDiscountRepository itemDiscountRepository;

    @PostConstruct
    public void populate() {
        System.out.println("Populating data");//TODO should be e.g. slf4j logger
        pricingRuleRepository.save(PricingRule.builder()
                .id(1L)
                .description("Example Pricing Rule").build());

        //copy and pasting test data to keep within a reasonable time limit
        itemRepository.save(Item.builder()
                .id(1L)
                .sku("A")
                .unitPrice(new BigDecimal("50"))
                .build());

        itemRepository.save(Item.builder()
                .id(2L)
                .sku("B")
                .unitPrice(new BigDecimal("30"))
                .build());

        itemRepository.save(Item.builder()
                .id(3L)
                .sku("C")
                .unitPrice(new BigDecimal("20"))
                .build());

        itemRepository.save(Item.builder()
                .id(4L)
                .sku("D")
                .unitPrice(new BigDecimal("15"))
                .build());

        itemDiscountRepository.save(ItemDiscount.builder()
                .id(1L)
                .itemId(1L)
                .pricingRuleId(1L)
                .quantityThreshold(3)
                .specialPrice(new BigDecimal("130"))
                .build());

        itemDiscountRepository.save(ItemDiscount.builder()
                .id(2L)
                .itemId(2L)
                .pricingRuleId(1L)
                .quantityThreshold(2)
                .specialPrice(new BigDecimal("45"))
                .build());
    }
}