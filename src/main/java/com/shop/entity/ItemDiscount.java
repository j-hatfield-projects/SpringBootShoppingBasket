package com.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDiscount {

    @Id
    private Long id;
    //TODO below should be the id (composite using JPA embeddable class)
    // not enforcing it in order to keep within a reasonable time limit
    private Long itemId;
    private Long pricingRuleId;

    private int quantityThreshold;
    private BigDecimal specialPrice;
}
