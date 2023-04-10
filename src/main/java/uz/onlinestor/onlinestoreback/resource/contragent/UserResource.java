package uz.onlinestor.onlinestoreback.resource.contragent;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.*;
import uz.onlinestor.onlinestoreback.models.contragent.UserApp;
import uz.onlinestor.onlinestoreback.service.contragent.UserService;

import java.util.List;

@RestController
@RequestMapping("/online/doc/user/")
@RequiredArgsConstructor
public class UserResource {

    @Autowired
    final UserService userService;

    @GetMapping("get")
    private List<UserApp> getAll() {
        return userService.getAll();
    }

    @PostMapping("save")
    private UserApp save(@RequestBody UserApp user) {
        return userService.save(user);
    }

    @PutMapping("delete")
    private UserApp delete(@RequestParam("id") String id) {

        return userService.delete(Long.parseLong(id));
    }

    @PostMapping("sendsms")
    private void sendSms(@RequestParam("phone") String phone) throws JSONException, JsonProcessingException {
        userService.sendSms(phone);
    }


}
