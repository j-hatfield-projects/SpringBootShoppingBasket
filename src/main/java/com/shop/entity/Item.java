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
public class Item {

    @Id
    private Long id;
    private String sku;
    private BigDecimal unitPrice;
}
