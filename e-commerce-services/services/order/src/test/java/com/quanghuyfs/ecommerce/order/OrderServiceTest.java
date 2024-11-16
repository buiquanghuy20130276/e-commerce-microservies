package com.quanghuyfs.ecommerce.order;

import com.quanghuyfs.ecommerce.order.customer.CustomerClient;
import com.quanghuyfs.ecommerce.order.customer.ProductClient;
import com.quanghuyfs.ecommerce.order.exception.BusinessException;
import com.quanghuyfs.ecommerce.order.kafka.OrderProducer;
import com.quanghuyfs.ecommerce.order.mapper.OrderMapper;
import com.quanghuyfs.ecommerce.order.model.Order;
import com.quanghuyfs.ecommerce.order.model.PaymentMethod;
import com.quanghuyfs.ecommerce.order.payment.PaymentClient;
import com.quanghuyfs.ecommerce.order.repository.OrderRepository;
import com.quanghuyfs.ecommerce.order.request.OrderRequest;
import com.quanghuyfs.ecommerce.order.request.PurchaseRequest;
import com.quanghuyfs.ecommerce.order.response.CustomerResponse;
import com.quanghuyfs.ecommerce.order.response.OrderResponse;
import com.quanghuyfs.ecommerce.order.service.OrderItemService;
import com.quanghuyfs.ecommerce.order.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class OrderServiceTest {
    @InjectMocks
    private OrderService service;

    @Mock
    private OrderRepository repository;
    @Mock
    private OrderMapper mapper;
    @Mock
    private CustomerClient customerClient;
    @Mock
    private ProductClient productClient;
    @Mock
    private OrderItemService orderItemService;
    @Mock
    private OrderProducer orderProducer;
    @Mock
    private PaymentClient paymentClient;
    private OrderRequest orderRequest;
    private OrderResponse orderResponse;
    private CustomerResponse customerResponse;

    @BeforeEach
    void setUp() {
        customerResponse = new CustomerResponse(1L, "John Doe", "HUy", "john@example.com");

        orderRequest = new OrderRequest(
                1L,
                "ORDER123",
                new BigDecimal("100.00"),
                PaymentMethod.CREDIT_CARD,
                1L,
                List.of(new PurchaseRequest(1L, 2))
        );

        orderResponse = new OrderResponse(
                1L,
                "ORDER123",
                new BigDecimal("100.00"),
                PaymentMethod.CREDIT_CARD,
                1L
        );
    }

    @Test
    void createOrder_ShouldCreateOrderSuccessfully() {
        when(customerClient.findCustomerById(anyLong())).thenReturn(Optional.of(customerResponse));
        when(productClient.purchaseProducts(anyList())).thenReturn(List.of());
        when(mapper.toOrder(orderRequest)).thenReturn(new Order(1L, "huy", new BigDecimal("5000"), PaymentMethod.CREDIT_CARD, 1L, List.of(), LocalDateTime.now(), LocalDateTime.now()));
        when(repository.save(any())).thenReturn(new Order(1L, "huy", new BigDecimal("5000"), PaymentMethod.CREDIT_CARD, 1L, List.of(), LocalDateTime.now(), LocalDateTime.now()));
        when(mapper.fromOrder(any())).thenReturn(orderResponse);

        Long orderId = service.createOrder(orderRequest);
        System.out.println("orderId: " + orderId);
        Assertions.assertNotNull(orderId);
        Mockito.verify(customerClient, times(1)).findCustomerById(Mockito.anyLong());
        Mockito.verify(repository, times(1)).save(Mockito.any());
        Mockito.verify(paymentClient, times(1)).requestOrderPayment(Mockito.any());
        Mockito.verify(orderItemService, times(1)).saveOrderItem(Mockito.any());
        Mockito.verify(orderProducer, times(1)).sendOrderConfirmation(Mockito.any());
    }

    @Test
    void testCreateOrderCustomerNotFound() {
        when(customerClient.findCustomerById(anyLong())).thenReturn(Optional.empty());

        BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
            service.createOrder(orderRequest);
        });
        Assertions.assertEquals("Cannot create order :: No customer exist with the provided id", exception.getMsg());

    }

    @Test
    void testFindAllOrders() {
        List<Order> orders = List.of(new Order(), new Order());
        when(repository.findAll()).thenReturn(orders);
        List<OrderResponse> orderResponses = service.findAll();

        Assertions.assertEquals(2, orderResponses.size());
        Mockito.verify(repository, times(1)).findAll();
    }

    @Test
    void testFindOrderById_Success() {
        Long orderId = 1L;  
        Order mockOrder = new Order();
        mockOrder.setId(orderId);
        mockOrder.setReference("reference123");
        mockOrder.setTotalAmount(BigDecimal.TEN);
        mockOrder.setPaymentMethod(PaymentMethod.CREDIT_CARD);

        OrderResponse mockResponse = new OrderResponse(1L, "reference123", BigDecimal.TEN, PaymentMethod.CREDIT_CARD,1L);

        when(repository.findById(orderId)).thenReturn(Optional.of(mockOrder));
        when(mapper.fromOrder(mockOrder)).thenReturn(mockResponse);
        OrderResponse response = service.findById(orderId);

        Assertions.assertNotNull(response, "OrderResponse should not be null");
        Assertions.assertEquals(orderId, response.id(), "The order ID should match the requested ID");
        Assertions.assertEquals("reference123", response.reference(), "The order reference should match the expected value");
        verify(repository, times(1)).findById(orderId);
        verify(mapper, times(1)).fromOrder(mockOrder);
    }
    @Test
    void testFindOrderByIdNotFound() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            service.findById(1L);
        });
        Assertions.assertEquals("No order found with order ID 1", exception.getMessage());
    }
}
