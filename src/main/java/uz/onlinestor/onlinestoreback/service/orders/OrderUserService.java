package uz.onlinestor.onlinestoreback.service.orders;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.onlinestor.onlinestoreback.models.orders.OrderUser;
import uz.onlinestor.onlinestoreback.repository.orders.OrderUserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class OrderUserService {

    @Autowired
    final OrderUserRepository orderRepository;

    public List<OrderUser> getAllbyUserId(Long id) {
        return orderRepository.getAllByUserId(id);
    }

    public OrderUser save(OrderUser orderUser) {

        List<OrderUser> orderUserOnes = new ArrayList<>();
        orderUserOnes = orderRepository.getByUserAndProductID(orderUser.getProduct().getId(), orderUser.getUserApp().getId());

        OrderUser orderUser1 = orderUserOnes.stream().findFirst().orElse(null);
        if (orderUserOnes.size() == 0) {
            return orderRepository.save(orderUser);
        } else {
            orderUser1.setQuantity(orderUser1.getQuantity() + 1);
            return orderRepository.save(orderUser1);
        }

    }

    public OrderUser addremoveQuantity(OrderUser orderUser) {
        return orderRepository.save(orderUser);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
