package com.kisansetu.service;

import com.kisansetu.dto.OrderRequest;
import com.kisansetu.model.Crop;
import com.kisansetu.model.Escrow;
import com.kisansetu.model.Order;
import com.kisansetu.model.User;
import com.kisansetu.repository.CropRepository;
import com.kisansetu.repository.EscrowRepository;
import com.kisansetu.repository.OrderRepository;
import com.kisansetu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CropRepository cropRepository;
    private final UserRepository userRepository;
    private final EscrowRepository escrowRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        CropRepository cropRepository,
                        UserRepository userRepository,
                        EscrowRepository escrowRepository) {
        this.orderRepository = orderRepository;
        this.cropRepository = cropRepository;
        this.userRepository = userRepository;
        this.escrowRepository = escrowRepository;
    }

    @Transactional
    public Order createOrder(OrderRequest request) {
        // Verify crop exists and is available
        Optional<Crop> cropOpt = cropRepository.findById(request.getCropId());
        if (cropOpt.isEmpty()) {
            throw new RuntimeException("Crop not found");
        }

        Crop crop = cropOpt.get();
        if (!crop.getIsAvailable() || crop.getQuantity() < request.getQuantity()) {
            throw new RuntimeException("Crop not available in requested quantity");
        }

        // Verify buyer exists
        Optional<User> buyerOpt = userRepository.findById(request.getBuyerId());
        if (buyerOpt.isEmpty() || !buyerOpt.get().getRole().equals("BUYER")) {
            throw new RuntimeException("Invalid buyer ID");
        }

        User buyer = buyerOpt.get();

        // Calculate total amount
        double totalAmount = request.getQuantity() * crop.getPricePerKg();

        // Create order
        Order order = new Order();
        order.setOrderNumber(generateOrderNumber());
        order.setCropId(request.getCropId());
        order.setCropName(crop.getName());
        order.setFarmerId(crop.getFarmerId());
        order.setFarmerName(crop.getFarmerName());
        order.setBuyerId(request.getBuyerId());
        order.setBuyerName(buyer.getFullName());
        order.setBuyerOrganization(request.getBuyerOrganization());
        order.setQuantity(request.getQuantity());
        order.setPricePerKg(crop.getPricePerKg());
        order.setTotalAmount(totalAmount);
        order.setStatus("PENDING");  // New workflow: starts as PENDING
        order.setPaymentStatus("PENDING");
        order.setDeliveryAddress(request.getDeliveryAddress());
        order.setDeliveryNotes(request.getDeliveryNotes());

        // Reduce crop quantity
        crop.setQuantity(crop.getQuantity() - request.getQuantity());
        if (crop.getQuantity() <= 0) {
            crop.setIsAvailable(false);
        }
        cropRepository.save(crop);

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }

    public Optional<Order> getOrderByNumber(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber);
    }

    public List<Order> getOrdersByBuyer(String buyerId) {
        return orderRepository.findByBuyerId(buyerId);
    }

    public List<Order> getOrdersByFarmer(String farmerId) {
        return orderRepository.findByFarmerId(farmerId);
    }

    @Transactional
    public Order confirmOrder(String orderId) {
        Order order = getOrderOrThrow(orderId);
        
        if (!order.getStatus().equals("PLACED")) {
            throw new RuntimeException("Order can only be confirmed from PLACED status");
        }

        order.setStatus("CONFIRMED");
        order.setConfirmedAt(LocalDateTime.now());

        return orderRepository.save(order);
    }

    // NEW WORKFLOW METHODS - PENDING/CONFIRMED/REJECTED flow
    
    @Transactional
    public Order acceptOrder(String orderId) {
        Order order = getOrderOrThrow(orderId);
        
        if (!order.getStatus().equals("PENDING")) {
            throw new RuntimeException("Order can only be accepted from PENDING status");
        }

        order.setStatus("CONFIRMED");
        order.setConfirmedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Transactional
    public Order rejectOrder(String orderId) {
        Order order = getOrderOrThrow(orderId);
        
        if (!order.getStatus().equals("PENDING")) {
            throw new RuntimeException("Order can only be rejected from PENDING status");
        }

        // Return quantity to crop
        Optional<Crop> cropOpt = cropRepository.findById(order.getCropId());
        if (cropOpt.isPresent()) {
            Crop crop = cropOpt.get();
            crop.setQuantity(crop.getQuantity() + order.getQuantity());
            crop.setIsAvailable(true);
            cropRepository.save(crop);
        }

        order.setStatus("REJECTED");
        order.setRejectedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Transactional
    public Order markAsShipped(String orderId) {
        Order order = getOrderOrThrow(orderId);
        
        if (!order.getStatus().equals("CONFIRMED")) {
            throw new RuntimeException("Order must be confirmed before shipping");
        }

        order.setStatus("SHIPPED");
        order.setShippedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Transactional
    public Order markAsDelivered(String orderId) {
        Order order = getOrderOrThrow(orderId);
        
        if (!order.getStatus().equals("SHIPPED")) {
            throw new RuntimeException("Order must be shipped before marking as delivered");
        }

        order.setStatus("DELIVERED");
        order.setDeliveredAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Transactional
    public Order markAsPaid(String orderId) {
        Order order = getOrderOrThrow(orderId);
        
        if (!order.getStatus().equals("CONFIRMED")) {
            throw new RuntimeException("Order must be confirmed before payment");
        }

        order.setPaymentStatus("HELD_IN_ESCROW");

        // Create escrow record
        Escrow escrow = new Escrow();
        escrow.setEscrowNumber(generateEscrowNumber());
        escrow.setOrderId(orderId);
        escrow.setOrderNumber(order.getOrderNumber());
        escrow.setBuyerId(order.getBuyerId());
        escrow.setFarmerId(order.getFarmerId());
        escrow.setAmount(order.getTotalAmount());
        escrow.setStatus("HELD");

        Escrow savedEscrow = escrowRepository.save(escrow);
        order.setEscrowId(savedEscrow.getId());

        return orderRepository.save(order);
    }

    @Transactional
    public Order shipOrder(String orderId) {
        Order order = getOrderOrThrow(orderId);
        
        if (!order.getPaymentStatus().equals("HELD_IN_ESCROW")) {
            throw new RuntimeException("Payment must be completed before shipping");
        }

        order.setStatus("SHIPPED");
        order.setShippedAt(LocalDateTime.now());

        return orderRepository.save(order);
    }

    @Transactional
    public Order deliverOrder(String orderId) {
        Order order = getOrderOrThrow(orderId);
        
        if (!order.getStatus().equals("SHIPPED")) {
            throw new RuntimeException("Order must be shipped before delivery");
        }

        order.setStatus("DELIVERED");
        order.setDeliveredAt(LocalDateTime.now());

        return orderRepository.save(order);
    }

    @Transactional
    public Order completeOrder(String orderId) {
        Order order = getOrderOrThrow(orderId);
        
        if (!order.getStatus().equals("DELIVERED")) {
            throw new RuntimeException("Order must be delivered before completion");
        }

        order.setStatus("COMPLETED");
        order.setCompletedAt(LocalDateTime.now());
        order.setPaymentStatus("RELEASED");

        // Release escrow
        if (order.getEscrowId() != null) {
            Optional<Escrow> escrowOpt = escrowRepository.findById(order.getEscrowId());
            if (escrowOpt.isPresent()) {
                Escrow escrow = escrowOpt.get();
                escrow.setStatus("RELEASED");
                escrow.setReleasedAt(LocalDateTime.now());
                escrowRepository.save(escrow);
            }
        }

        return orderRepository.save(order);
    }

    @Transactional
    public Order cancelOrder(String orderId) {
        Order order = getOrderOrThrow(orderId);
        
        if (order.getStatus().equals("COMPLETED") || order.getStatus().equals("CANCELLED")) {
            throw new RuntimeException("Cannot cancel completed or already cancelled order");
        }

        // Return quantity to crop if order was pending, placed, or confirmed
        if (order.getStatus().equals("PENDING") || order.getStatus().equals("PLACED") || order.getStatus().equals("CONFIRMED")) {
            Optional<Crop> cropOpt = cropRepository.findById(order.getCropId());
            if (cropOpt.isPresent()) {
                Crop crop = cropOpt.get();
                crop.setQuantity(crop.getQuantity() + order.getQuantity());
                crop.setIsAvailable(true);
                cropRepository.save(crop);
            }
        }

        // Refund escrow if payment was made
        if (order.getPaymentStatus().equals("HELD_IN_ESCROW") && order.getEscrowId() != null) {
            Optional<Escrow> escrowOpt = escrowRepository.findById(order.getEscrowId());
            if (escrowOpt.isPresent()) {
                Escrow escrow = escrowOpt.get();
                escrow.setStatus("REFUNDED");
                escrow.setRefundedAt(LocalDateTime.now());
                escrowRepository.save(escrow);
            }
            order.setPaymentStatus("REFUNDED");
        }

        order.setStatus("CANCELLED");
        return orderRepository.save(order);
    }

    @Transactional
    public Order markOutOfStock(String orderId) {
        Order order = getOrderOrThrow(orderId);
        
        // Only pending or confirmed orders can be marked out of stock
        if (!order.getStatus().equals("PENDING") && !order.getStatus().equals("CONFIRMED")) {
            throw new RuntimeException("Only PENDING or CONFIRMED orders can be marked as OUT_OF_STOCK");
        }

        // Return quantity to crop
        Optional<Crop> cropOpt = cropRepository.findById(order.getCropId());
        if (cropOpt.isPresent()) {
            Crop crop = cropOpt.get();
            crop.setQuantity(crop.getQuantity() + order.getQuantity());
            crop.setIsAvailable(true);
            cropRepository.save(crop);
        }

        // Refund escrow if payment was made
        if (order.getPaymentStatus().equals("HELD_IN_ESCROW") && order.getEscrowId() != null) {
            Optional<Escrow> escrowOpt = escrowRepository.findById(order.getEscrowId());
            if (escrowOpt.isPresent()) {
                Escrow escrow = escrowOpt.get();
                escrow.setStatus("REFUNDED");
                escrow.setRefundedAt(LocalDateTime.now());
                escrowRepository.save(escrow);
            }
            order.setPaymentStatus("REFUNDED");
        }

        order.setStatus("OUT_OF_STOCK");
        return orderRepository.save(order);
    }

    @Transactional
    public void markOrdersAsOutOfStockForCrop(String cropId) {
        // Find all PENDING or CONFIRMED orders for this crop
        List<Order> orders = orderRepository.findByCropIdAndStatusIn(cropId, 
            java.util.Arrays.asList("PENDING", "CONFIRMED"));
        
        for (Order order : orders) {
            // Return quantity to crop
            Optional<Crop> cropOpt = cropRepository.findById(order.getCropId());
            if (cropOpt.isPresent()) {
                Crop crop = cropOpt.get();
                crop.setQuantity(crop.getQuantity() + order.getQuantity());
                crop.setIsAvailable(true);
                cropRepository.save(crop);
            }

            // Refund escrow if payment was made
            if (order.getPaymentStatus().equals("HELD_IN_ESCROW") && order.getEscrowId() != null) {
                Optional<Escrow> escrowOpt = escrowRepository.findById(order.getEscrowId());
                if (escrowOpt.isPresent()) {
                    Escrow escrow = escrowOpt.get();
                    escrow.setStatus("REFUNDED");
                    escrow.setRefundedAt(LocalDateTime.now());
                    escrowRepository.save(escrow);
                }
                order.setPaymentStatus("REFUNDED");
            }

            order.setStatus("OUT_OF_STOCK");
            orderRepository.save(order);
        }
    }

    private Order getOrderOrThrow(String orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    private String generateOrderNumber() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String random = UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        return "ORD-" + timestamp + "-" + random;
    }

    private String generateEscrowNumber() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String random = UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        return "ESC-" + timestamp + "-" + random;
    }
}
