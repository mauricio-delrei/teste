package com.kiosia.b2wchallenge.repository;

import com.kiosia.b2wchallenge.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends Repository<Order, Long> {
  Order save(Order order);

  Optional<Order> findById(Long id);

  List<Order> findAll();

  //select avg(total) from orders where date between '2019-10-10' and '2019-10-12'
  @Query("SELECT AVG(total) FROM Order WHERE date BETWEEN :initial AND :final")
  Double findAtpByDatePeriod(@Param("initial") Date initialDate, @Param("final") Date finalDate);
}
