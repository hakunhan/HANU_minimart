package sqa.hanu_minimart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sqa.hanu_minimart.model.*;
import sqa.hanu_minimart.payload.OrderPayload;
import sqa.hanu_minimart.repository.OrderRepository;

import java.util.*;


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
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).get();
    }

    public Order addNewOrder(Cart cart, Long userId) {

        User user = accountService.findById(userId).get();

        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date());               // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, 72);      // adds 72 hour
        cal.getTime();                         // returns new date object plus 72 hour

        Order order = new Order(user, cal.getTime(), OrderStatus.PENDING);
        Set<OrderLine> orderLines = new HashSet<>();
        Set<CartItem> cartItem = cart.getCartItem();
        Double total = 0.0;
        for(CartItem item:cartItem) {
            List<Product> products = productService.findProductByIdSortedByExpAndImportDate(item.getProductName());
            int quantity = item.getQuantity();
            total += quantity * products.get(0).getPrice();

            OrderLine orderLine = orderLineService.addNewOrderItem(new OrderLine(null, item.getProductName(), quantity));
            orderLines.add(orderLine);

            for(Product product:products) {
                if(product.getQuantity() > quantity) {
                    productService.updateProductQuantity(product.getId(), product.getQuantity()-quantity);
                    quantity = 0 ;
                }else {
                    quantity -= product.getQuantity();
                    productService.deleteProduct(item.getProductName(), product.getExpireDate(), product.getImportDate());
                }
                if(quantity == 0) {
                    break;
                }
            }
        }
        cartItemSevice.deleteAll();
        cartService.update(userId);
        order.setTotal(total);
        order.setOrderLine(orderLines);
        order = orderRepository.save(order);
        for(OrderLine orderLine:orderLines) {
            orderLineService.update(order.getId(), orderLine.getId());
        }
        return order;
    }

    public List<Order> getPendingOrder(){ return orderRepository.getPendingOrder();}

    public void deleteOrder(Long orderID) {
        boolean exists = orderRepository.existsById(orderID);
        if (!exists){
            throw new IllegalStateException("Order does not exist!");
        }
        orderRepository.deleteById(orderID);
    }

    private void updateOrderStatus(Long orderID, OrderStatus status) {
        Order order = orderRepository.findById(orderID)
                .orElseThrow(() -> new IllegalStateException("Product does not exist!"));

        order.setStatus(status);

        orderRepository.save(order);
    }

    @Transactional
    public void updateOrder(Order order, Long id) {
        if(!orderRepository.existsById(id)) {
            throw new IllegalStateException("Order does not exist");
        }
        Order currentOrder = orderRepository.findById(id).get();
        currentOrder.setDeliveryNotes(order.getDeliveryNotes());
        currentOrder.setDeliveryTime(order.getDeliveryTime());
        currentOrder.setCreatedTime(order.getCreatedTime());
        currentOrder.setStatus(order.getStatus());
        currentOrder.setOrderLine(order.getOrderLine());
        currentOrder.setTotal(order.getTotal());
        orderRepository.save(currentOrder);
    }


    public void processOrder(int orderID) {
//        List<OrderLine> orderLineList = orderLineService.findByOrderID(orderID);
//        for (int i = 0; i < orderLineList.size(); i++) {
//            OrderLine currOrderLine = orderLineList.get(i);
//            List<Product> productList = productService.getProductByName(currOrderLine.getProduct());
//            for (int j = 0; j < productList.size(); j++) {
//                Product currProduct = productList.get(j);
//                if (currProduct.getQuantity() >= currOrderLine.getQuantity()) {
//                    ProductStatus status = currProduct.getProductStatus();
//                    String stt = "";
//                    if (status.equals(ProductStatus.HOT)) {
//                        stt = "hot";
//                    } else {
//                        stt = "new";
//                    }
//                    productService.updateProduct(currProduct.getId(), currProduct.getName(), currProduct.getPrice(),
//                                                currProduct.getQuantity(), currProduct.getCategory(), currProduct.getDescription(),
//                                                currProduct.getPicture_URL(), currProduct.getSale(), stt, currProduct.getExpireDate().toString());
//                    updateOrderStatus(orderID, OrderStatus.ACCEPTED);
//                } else {
//                    updateOrderStatus(orderID, OrderStatus.CANCEL);
//                }
//            }
//        }

        // Logic to reduce the product quantity will be put here
        // Remember to change the order status to Accept
    }
}
