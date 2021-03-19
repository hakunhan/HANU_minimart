package sqa.hanu_minimart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sqa.hanu_minimart.model.*;
import sqa.hanu_minimart.payload.OrderPayload;
import sqa.hanu_minimart.repository.OrderRepository;

import java.util.*;


@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderLineService orderLineService;
    @Autowired
    private ProductService productService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CartItemService cartItemSevice;
    @Autowired
    private CartService cartService;

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).get();
    }

    public Order addNewOrder(OrderPayload request) {

        User user = (User) accountService.getById(request.getUserId());

        Cart cart = cartService.getCartById(request.getCartId());

        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date());               // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, 72);      // adds 72 hour
        Date deliverDate = cal.getTime();                         // returns new date object plus 72 hour

        Order order = new Order(user, deliverDate, request.getDeliveryNotes(), request.getBillingAddress(), OrderStatus.PENDING);
        // save the order so we can get the order_id
        Order newOrder = orderRepository.save(order);

        Set<OrderLine> orderLines = new HashSet<>();
        Set<CartItem> cartItems = cart.getCartItem();
        Double orderTotalPrice = 0.0;
        Double orderLineTotalPrice = 0.0;

        //transfer cartItems of a cart into orderLines of an order, also calculate the total price
        for(CartItem item:cartItems) {
            String productName = item.getProduct().getName();
            int quantity = item.getQuantity();
            orderLineTotalPrice = quantity * item.getProduct().getPrice();
            orderTotalPrice += orderLineTotalPrice;

            OrderLine newOrderLine = new OrderLine(newOrder,quantity, item.getProduct(), orderLineTotalPrice);

            orderLines.add(newOrderLine);
            orderLineService.addNewOrderItem(newOrderLine);

            // only when the order status being set Accepted by employee
            // then the product quantity being reduced

//            //product by name : sorted with importdate DESC and expDate asc
//            List<Product> products = productService.getProductByName(productName);
//            for(Product product:products) {
//                if(product.getQuantity() > quantity) {
//                    productService.updateProductQuantity(product.getId(), product.getQuantity()-quantity);
//                    quantity = 0 ;
//                }else {
//                    quantity -= product.getQuantity();
//                    productService.deleteProduct(productName, product.getExpireDate(), product.getImportDate());
//                }
//                if(quantity == 0) {
//                    break;
//                }
//            }
        }
        // Reset the cart of a particular user
        cartItemSevice.deleteByCartId(cart.getId());
        newOrder.setTotal(orderTotalPrice);
        return orderRepository.save(newOrder);
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
