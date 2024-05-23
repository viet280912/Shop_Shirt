package com.example.firstproject.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
//    find order by order id
    @Query(value = "SELECT orders.* FROM orders WHERE orders.order_id = :order_id", nativeQuery = true)
    Order findOrderByID (@Param("order_id") int order_id);

//    find orders by user id
    @Query(value = "SELECT orders.* FROM orders JOIN users ON users.user_id = orders.order_id WHERE users.user_id = :user_id", nativeQuery = true)
    List<Order> findOrdersByUserID (@Param("user_id") int user_id);

//    find orders by address id
    @Query(value = "SELECT orders.* FROM orders JOIN users ON addresses.address_id = orders.order_id WHERE addresses.address_id = :address_id", nativeQuery = true)
    List<Order> findOrdersByAddressID (@Param("address_id") int address_id);

//    find orders by status
    @Query(value = "SELECT orders.* FROM orders WHERE addresses.address_id = UPPER(:status)", nativeQuery = true)
    List<Order> findOrdersByStatus (@Param("status") String status);

    @Query(value = "SELECT orders.* FROM orders WHERE user_id = :user_id AND address_id = :address_id AND status = UPPER(:status)", nativeQuery = true)
    List<Order> findOrdersByForm (@Param("user_id") int user_id, @Param("address_id") int address_id, @Param("status") String status);

//    find orders by range price
    @Query(value = "SELECT orders.* FROM orders WHERE total_price BETWEEN :x AND :y", nativeQuery = true)
    List<Order> findOrdersByRangePrice(@Param("x") float x, @Param("y") float y);

//    find orders by time
    @Query(value = "SELECT orders.* FROM orders WHERE order_date_time = :time", nativeQuery = true)
    List<Order> findOrdersByTime(@Param("time") Date time);

//    find orders in times
    @Query(value = "SELECT orders.* FROM orders WHERE order_date_time BETWEEN :timeX AND :timeY", nativeQuery = true)
    List<Order> findOrdersInTimes(@Param("timeX") Date timeX, @Param("timeY") Date timeY);

//    find orders < time
    @Query(value = "SELECT orders.* FROM orders WHERE order_date_time < :timeY", nativeQuery = true)
    List<Order> findOrdersToTime(@Param("timeY") Date timeY);

//    find orders > time
    @Query(value = "SELECT orders.* FROM orders WHERE order_date_time > :timeX", nativeQuery = true)
    List<Order> findOrdersTimeTo(@Param("timeX") Date timeX);
}
