package com.example.demo.repository;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.OrderStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    // Custom query methods can be added here if needed
	 @Query("SELECT o FROM Order o WHERE MONTH(o.createdAt) = :month AND YEAR(o.createdAt) = :year AND o.status = 'SUCCESS'")
	    List<Order> findSuccessfulOrdersByMonthAndYear(int month, int year);
	 
	 
	 
	 @Query("SELECT o FROM Order o WHERE DATE(o.createdAt) = :date AND o.status = 'SUCCESS'")
	    List<Order> findSuccessfulOrdersByDate(LocalDate date);


	 
	 @Query("SELECT o FROM Order o WHERE YEAR(o.createdAt) = :year AND o.status = 'SUCCESS'")
	    List<Order> findSuccessfulOrdersByYear(int year);
	 
	 
	 
	 
	 @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM Order o WHERE o.status = 'SUCCESS'")
	    BigDecimal calculateOverallBusiness();

	    @Query("SELECT o FROM Order o WHERE o.status = :status")
	    List<Order> findAllByStatus(OrderStatus status);
	 
}