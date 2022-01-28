package com.homelyfooduser.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homelyfooduser.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, String> {

	List<Orders> findOrdersByOrderDateBetween(LocalDateTime start,LocalDateTime end);
}
