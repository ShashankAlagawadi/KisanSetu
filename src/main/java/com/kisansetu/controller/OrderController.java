package com.kisansetu.controller;

import com.kisansetu.dto.OrderRequest;
import com.kisansetu.model.Order;
import com.kisansetu.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*") // Enable CORS for all origins
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Create a new order
    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderRequest request) {
        try {
            Order order = orderService.createOrder(request);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // Get all orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    // Get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable String id) {
        Optional<Order> order = orderService.getOrderById(id);
        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get order by order number
    @GetMapping("/number/{orderNumber}")
    public ResponseEntity<?> getOrderByNumber(@PathVariable String orderNumber) {
        Optional<Order> order = orderService.getOrderByNumber(orderNumber);
        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get orders by buyer
    @GetMapping("/buyer/{buyerId}")
    public ResponseEntity<List<Order>> getOrdersByBuyer(@PathVariable String buyerId) {
        return ResponseEntity.ok(orderService.getOrdersByBuyer(buyerId));
    }

    // Get orders by farmer
    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<List<Order>> getOrdersByFarmer(@PathVariable String farmerId) {
        return ResponseEntity.ok(orderService.getOrdersByFarmer(farmerId));
    }

    // Order Status Workflow APIs

    // NEW WORKFLOW - PENDING/ACCEPTED/REJECTED flow

    // 1. Accept order (Farmer accepts pending order)
    @PostMapping("/{orderId}/accept")
    public ResponseEntity<?> acceptOrder(@PathVariable String orderId) {
        try {
            Order order = orderService.acceptOrder(orderId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Order accepted successfully");
            response.put("order", order);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // 2. Reject order (Farmer rejects pending order)
    @PostMapping("/{orderId}/reject")
    public ResponseEntity<?> rejectOrder(@PathVariable String orderId) {
        try {
            Order order = orderService.rejectOrder(orderId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Order rejected successfully");
            response.put("order", order);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // 3. Mark as shipped (Farmer ships accepted order)
    @PostMapping("/{orderId}/shipped")
    public ResponseEntity<?> markAsShipped(@PathVariable String orderId) {
        try {
            Order order = orderService.markAsShipped(orderId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Order marked as shipped");
            response.put("order", order);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // 4. Mark as delivered (Farmer marks shipped order as delivered)
    @PostMapping("/{orderId}/delivered")
    public ResponseEntity<?> markAsDelivered(@PathVariable String orderId) {
        try {
            Order order = orderService.markAsDelivered(orderId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Order marked as delivered");
            response.put("order", order);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // Generic status update endpoint (PUT with query param as requested)
    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrderStatus(
            @PathVariable String orderId,
            @RequestParam String status) {
        try {
            Order order;
            switch (status.toUpperCase()) {
                case "CONFIRMED":
                    order = orderService.acceptOrder(orderId);
                    break;
                case "REJECTED":
                    order = orderService.rejectOrder(orderId);
                    break;
                case "CANCELLED":
                    order = orderService.cancelOrder(orderId);
                    break;
                case "OUT_OF_STOCK":
                    order = orderService.markOutOfStock(orderId);
                    break;
                case "SHIPPED":
                    order = orderService.markAsShipped(orderId);
                    break;
                case "DELIVERED":
                    order = orderService.markAsDelivered(orderId);
                    break;
                default:
                    Map<String, String> error = new HashMap<>();
                    error.put("error", "Invalid status. Use: CONFIRMED, REJECTED, CANCELLED, OUT_OF_STOCK, SHIPPED, DELIVERED");
                    return ResponseEntity.badRequest().body(error);
            }
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Order status updated to " + status);
            response.put("order", order);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // LEGACY WORKFLOW - Keep for backward compatibility

    // Confirm order (Farmer confirms the order)
    @PostMapping("/{orderId}/confirm")
    public ResponseEntity<?> confirmOrder(@PathVariable String orderId) {
        try {
            Order order = orderService.confirmOrder(orderId);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // 2. Mark as paid (Buyer pays - goes to escrow)
    @PostMapping("/{orderId}/pay")
    public ResponseEntity<?> markAsPaid(@PathVariable String orderId) {
        try {
            Order order = orderService.markAsPaid(orderId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Payment successful. Amount held in escrow.");
            response.put("order", order);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // 3. Ship order (Farmer ships)
    @PostMapping("/{orderId}/ship")
    public ResponseEntity<?> shipOrder(@PathVariable String orderId) {
        try {
            Order order = orderService.shipOrder(orderId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Order marked as shipped");
            response.put("order", order);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // 4. Deliver order (Delivery confirmed)
    @PostMapping("/{orderId}/deliver")
    public ResponseEntity<?> deliverOrder(@PathVariable String orderId) {
        try {
            Order order = orderService.deliverOrder(orderId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Order marked as delivered");
            response.put("order", order);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // 5. Complete order (Buyer confirms receipt - payment released)
    @PostMapping("/{orderId}/complete")
    public ResponseEntity<?> completeOrder(@PathVariable String orderId) {
        try {
            Order order = orderService.completeOrder(orderId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Order completed. Payment released to farmer.");
            response.put("order", order);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // Cancel order
    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<?> cancelOrder(@PathVariable String orderId) {
        try {
            Order order = orderService.cancelOrder(orderId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Order cancelled successfully");
            response.put("order", order);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}
