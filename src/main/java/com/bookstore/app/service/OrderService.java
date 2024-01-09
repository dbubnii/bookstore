package com.bookstore.app.service;

import com.bookstore.app.model.Order;
import com.bookstore.app.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
	private final Logger log = LoggerFactory.getLogger(OrderService.class.getSimpleName());

	private final OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public List<Order> getAllOrders() {
		log.info("Retrieving all orders");
		return orderRepository.findAll();
	}

	public Order getOrderById(Long id) {
		log.info("Retrieving order with id {}", id);

		return orderRepository.findById(id).orElse(null);
	}

	public Order createOrder(Order order) {
		log.info("Creating new order {}", order);

		return orderRepository.save(order);
	}

	public Order updateOrder(Long id, Order updatedOrder) {
		log.info("Updating order with id {} to {}", id, updatedOrder);

		Order existingOrder = orderRepository.findById(id).orElse(null);

		if (existingOrder != null) {
			existingOrder.setUser(updatedOrder.getUser());
			existingOrder.setOrderDate(updatedOrder.getOrderDate());
			existingOrder.setOrderId(updatedOrder.getOrderId());
			orderRepository.save(existingOrder);
		}

		return null;
	}

	public void deleteOrder(Long id) {
		log.info("Deleting order with id {}", id);

		orderRepository.deleteById(id);
	}
}
