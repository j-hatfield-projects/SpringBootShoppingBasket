package com.shop.repository;

import com.shop.entity.OrderLine;
import org.springframework.data.repository.CrudRepository;

public interface OrderLineRepository extends CrudRepository<OrderLine, Long> {}