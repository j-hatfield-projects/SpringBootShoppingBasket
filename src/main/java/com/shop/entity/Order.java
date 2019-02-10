package com.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "shopOrder")//'order' is reserved word
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long pricingRuleId;//TODO foreign key many to one

    @OneToMany(orphanRemoval = true)
    private List<OrderLine> orderLines;
}
