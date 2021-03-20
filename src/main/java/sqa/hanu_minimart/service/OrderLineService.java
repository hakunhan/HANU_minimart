package sqa.hanu_minimart.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sqa.hanu_minimart.model.OrderLine;
import sqa.hanu_minimart.repository.OrderLineRepository;
import sqa.hanu_minimart.repository.OrderRepository;
import sqa.hanu_minimart.repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderLineService {
    @Autowired
    private final OrderLineRepository orderLineRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public OrderLineService(OrderLineRepository orderLineRepository) {
        this.orderLineRepository = orderLineRepository;
    }
    public OrderLine getById(Long id) {
        return orderLineRepository.findById(id).get();
    }


    public List<OrderLine> getAllOrderLine() {
        return orderLineRepository.findAll();
    }

    /*  TODO: sửa lại tạo order line với việc nhận Order ID và tìm Order tương ứng trong db
     *           nếu không thấy trong db trả lại exception
     */
    public OrderLine addNewOrderLine(OrderLine orderItem) {
        return orderLineRepository.save(orderItem);
    }

    public void update(Long orderId, Long orderItemId) {
        orderLineRepository.update(orderId, orderItemId);
    }

    public void deleteAll() {
        orderLineRepository.deleteAll();
    }

    public void deleteById(Long id) {
        orderLineRepository.deleteById(id);
    }

    @Transactional
    public void update(OrderLine orderLine, Long id) {
        if(!orderLineRepository.existsById(id)) {
            throw new IllegalStateException("OrderItem does not exist.");
        }
        OrderLine currentOrderLine = orderLineRepository.findById(id).get();
        currentOrderLine.setQuantity(orderLine.getQuantity());
        orderLineRepository.save(currentOrderLine);
    }

    public List<OrderLine> findByOrderID(Long orderID) {
        return orderLineRepository.findByOrder_Id(orderID);
    }

    public double getTotalBill(Long orderID) {
        List<OrderLine> list = findByOrderID(orderID);
        double total = 0;
        for (int i = 0; i < list.size(); i++) {
            total += list.get(i).getPrice();
        } return total;
    }
}
