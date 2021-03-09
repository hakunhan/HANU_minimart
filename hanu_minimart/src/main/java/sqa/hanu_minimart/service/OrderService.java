package sqa.hanu_minimart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sqa.hanu_minimart.model.*;
import sqa.hanu_minimart.repository.OrderRepository;

import java.util.List;


@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private ProductService productService;
    private OrderLineService orderLineService;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductService productService, OrderLineService orderLineService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.orderLineService = orderLineService;
    }

    public List<Order> getOrder(){
        return orderRepository.findAll();
    }

    public void placeOrder(Order order){
        orderRepository.save(order);
    }

    public List<Order> getPendingOrder(){ return orderRepository.getPendingOrder();}

    public void deleteOrder(int orderID) {
        boolean exists = orderRepository.existsById(orderID);
        if (!exists){
            throw new IllegalStateException("Order does not exist!");
        }
        orderRepository.deleteById(orderID);
    }

    private void updateOrderStatus(int orderID, OrderStatus status) {
        Order order = orderRepository.findById(orderID)
                .orElseThrow(() -> new IllegalStateException("Product does not exist!"));

        order.setStatus(status);

        orderRepository.save(order);
    }

    public void processOrder(int orderID) {
        List<OrderLine> orderLineList = orderLineService.findByOrderID(orderID);
        for (int i = 0; i < orderLineList.size(); i++) {
            OrderLine currOrderLine = orderLineList.get(i);
            List<Product> productList = productService.getProductByName(currOrderLine.getProduct());
            for (int j = 0; j < productList.size(); j++) {
                Product currProduct = productList.get(j);
                if (currProduct.getQuantity() >= currOrderLine.getQuantity()) {
                    ProductStatus status = currProduct.getProductStatus();
                    String stt = "";
                    if (status.equals(ProductStatus.HOT)) {
                        stt = "hot";
                    } else {
                        stt = "new";
                    }
                    productService.updateProductQuantity(currProduct.getId(), currProduct.getName(), currProduct.getPrice(), currProduct.getQuantity(), currProduct.getCategory(), stt, currProduct.getExpireDate().toString());
                    updateOrderStatus(orderID, OrderStatus.ACCEPTED);
                } else {
                    updateOrderStatus(orderID, OrderStatus.CANCEL);
                }
            }
        }
    }
}
