package sqa.hanu_minimart.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sqa.hanu_minimart.model.OrderLine;
import sqa.hanu_minimart.repository.OrderLineRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class    OrderLineService {
    @Autowired
    private final OrderLineRepository orderLineRepository;


    public OrderLineService(OrderLineRepository orderLineRepository) {
        this.orderLineRepository = orderLineRepository;
    }
    public OrderLine getById(Long id) {
        return orderLineRepository.findById(id).get();
    }


    public List<OrderLine> getAllOrderLine() {
        return orderLineRepository.findAll();
    }

    public OrderLine addNewOrderLine(OrderLine orderItem) {
        return orderLineRepository.save(orderItem);
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
}
