package sqa.hanu_minimart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;
import sqa.hanu_minimart.exception.AppException;
import sqa.hanu_minimart.model.*;
import sqa.hanu_minimart.payload.OrderPayload;
import sqa.hanu_minimart.repository.OrderRepository;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class OrderService {
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final OrderLineService orderLineService;
    @Autowired
    private ProductService productService;
    @Autowired
    private final AccountService accountService;
    @Autowired
    private final CartItemService cartItemSevice;
    @Autowired
    private final CartService cartService;

    public OrderService(OrderRepository orderRepository, OrderLineService orderLineService, AccountService accountService, CartItemService cartItemSevice, CartService cartService) {
        this.orderRepository = orderRepository;
        this.orderLineService = orderLineService;
        this.accountService = accountService;
        this.cartItemSevice = cartItemSevice;
        this.cartService = cartService;
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAllOrder();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).get();
    }

    public Order addNewOrder(OrderPayload orderPayload) {
        User user = accountService.findById(orderPayload.getUserId()).get();
        Cart cart = cartService.getCartById(orderPayload.getCartId());

        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date());               // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, 72);      // adds 72 hour
        cal.getTime();                         // returns new date object plus 72 hour

        Order order = new Order(user, cal.getTime(), OrderStatus.PENDING);
        Set<OrderLine> orderLines = new HashSet<>();
        Set<CartItem> cartItem = new HashSet<>(cart.getCartItem());
        double total = 0.0;
        for(CartItem item:cartItem) {
            List<Product> products = productService.findProductByNameSortedByExpAndImportDate(item.getProductName());
            int quantity = item.getQuantity();
            int totalProductInStorage = 0;

            for(Product product:products){
                totalProductInStorage += product.getQuantity();
            }

            total += quantity * products.get(0).getPrice();

            OrderLine orderLine = new OrderLine(order, item.getProductName(), quantity, products.get(0).getPrice());
            orderLine.setEnough(quantity <= totalProductInStorage);

            orderLines.add(orderLine);
        }
        cartItemSevice.deleteAll();
        order.setTotal(total);
        order.setOrderLine(orderLines);
        order = orderRepository.save(order);
        for(OrderLine orderLine:orderLines) {
            orderLineService.addNewOrderLine(orderLine);
        }
        return order;
    }

    public void deleteOrder(Long orderID) {
        boolean exists = orderRepository.existsById(orderID);
        if (!exists){
            throw new IllegalStateException("Order does not exist!");
        }
        orderRepository.deleteById(orderID);
    }

    @Transactional
    public void updateOrder(Order order, Long id) {
        if(!orderRepository.existsById(id)) {
            throw new IllegalStateException("Order does not exist");
        }
        Order currentOrder = orderRepository.findById(id).get();

        currentOrder.setDeliveryNotes(order.getDeliveryNotes());
        currentOrder.setDeliveryTime(order.getDeliveryTime());
        currentOrder.setOrderLine(order.getOrderLine());
        currentOrder.setTotal(order.getTotal());
        orderRepository.save(currentOrder);
    }

    @Transactional
    public void updateOrderStatus(Long id, String status){
        if(!orderRepository.existsById(id)) {
            throw new IllegalStateException("Order does not exist");
        }
        Order currentOrder = orderRepository.findById(id).get();

        if (status.equalsIgnoreCase("accepted")) {
            if (currentOrder.getStatus() != OrderStatus.ACCEPTED) {
                for (OrderLine orderLine : currentOrder.getOrderLine()) {
                    if (!orderLine.isEnough()) {
                        throw new AppException("Not enough product in storage");
                    }

                    List<Product> products = productService.findProductByNameSortedByExpAndImportDate(orderLine.getProductName());
                    int quantity = orderLine.getQuantity();

                    for (Product product : products) {
                        if (product.getQuantity() > quantity) {
                            product.setQuantity(product.getQuantity() - quantity);
                            productService.updateProductQuantity(product.getId(), product.getQuantity());
                            break;
                        } else if (product.getQuantity() == quantity) {
                            productService.deleteProduct(product.getId());
                            break;
                        } else {
                            quantity -= product.getQuantity();
                            productService.deleteProduct(product.getId());
                        }
                    }
                }

                currentOrder.setStatus(OrderStatus.ACCEPTED);
                orderRepository.save(currentOrder);
            }else{
                return;
            }
        }else if(status.equalsIgnoreCase("cancel")){
            currentOrder.setStatus(OrderStatus.CANCEL);
            orderRepository.save(currentOrder);
        }else{
            return;
        }
    }
}
