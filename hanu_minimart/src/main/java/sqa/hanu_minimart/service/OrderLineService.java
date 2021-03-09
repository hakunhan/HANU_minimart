package sqa.hanu_minimart.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sqa.hanu_minimart.model.Order;
import sqa.hanu_minimart.model.OrderLine;
import sqa.hanu_minimart.model.Product;
import sqa.hanu_minimart.repository.OrderLineRepository;
import sqa.hanu_minimart.repository.OrderRepository;
import sqa.hanu_minimart.repository.ProductRepository;

import java.util.List;

@Service
public class OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderLineService(OrderLineRepository orderLineRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderLineRepository = orderLineRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public List<OrderLine> getAllOrderLine() {
        return orderLineRepository.findAll();
    }

    /*  TODO: sửa lại tạo order line với việc nhận Order ID và tìm Order tương ứng trong db
     *           nếu không thấy trong db trả lại exception
     */
    public void createNewOrderLine(int orderID, int orderLineID, int quantity, String product) {
        List<Product> list = productRepository.findByNameContaining(product);
        Product currProduct = list.get(0);
        double price = currProduct.getPrice() * quantity;
        //TODO: sửa lại repository để tìm order bằng ID
        Order order = orderRepository.findById(orderID);
        OrderLine o = new OrderLine(orderLineID, order, quantity, product, price);
        orderLineRepository.save(o);
    }

    public void deleteOrderLine(int id) {
        boolean exists = productRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Order lien doesnt exist!");
        }
        orderLineRepository.deleteById(id);
    }

    public List<OrderLine> findByOrderID(int orderID) {
        return orderLineRepository.findByOrderID(orderID);
    }

    public double getTotalBill(int orderID) {
        List<OrderLine> list = findByOrderID(orderID);
        double total = 0;
        for (int i = 0; i < list.size(); i++) {
            total += list.get(i).getPrice();
        } return total;
    }
}
