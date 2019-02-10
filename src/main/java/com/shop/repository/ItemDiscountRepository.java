package com.shop.repository;

import com.shop.entity.ItemDiscount;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ItemDiscountRepository extends CrudRepository<ItemDiscount, Long> {

    Optional<ItemDiscount> findByItemIdAndPricingRuleId(Long itemId, Long pricingRuleId);
}