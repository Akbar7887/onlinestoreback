package uz.onlinestor.onlinestoreback.resource.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.onlinestor.onlinestoreback.models.orders.OrderUser;
import uz.onlinestor.onlinestoreback.service.orders.OrderUserService;

import java.util.List;

@RestController
@RequestMapping("/online/doc/order/")
@RequiredArgsConstructor
public class OrderUserResource  {

    @Autowired
    final OrderUserService orderUserService;

    @GetMapping("getbyuser")
    private List<OrderUser> getAll(@RequestParam("id") String id) {
        return orderUserService.getAllbyUserId(Long.parseLong(id));
    }
    @PostMapping("save")
    private OrderUser save(@RequestBody OrderUser orderUser) {
        return orderUserService.save(orderUser);
    }

    @PostMapping("addquantity")
    private OrderUser addremoveQuantity(@RequestBody OrderUser orderUser) {
        return orderUserService.addremoveQuantity(orderUser);
    }

    @DeleteMapping("delete")
    private void delete(@RequestParam("id") String id) {
        orderUserService.delete(Long.parseLong(id));
    }

}
