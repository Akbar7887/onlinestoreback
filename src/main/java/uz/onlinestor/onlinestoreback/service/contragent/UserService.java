package uz.onlinestor.onlinestoreback.service.contragent;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import uz.onlinestor.onlinestoreback.models.ACTIVE;
import uz.onlinestor.onlinestoreback.models.contragent.UserApp;
import uz.onlinestor.onlinestoreback.repository.contragent.UserRepository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@AllArgsConstructor
@Transactional
public class UserService {

    final UserRepository userRepository;

    public List<UserApp> getAll() {
        return userRepository.getAll(ACTIVE.ACTIVE);
    }

    public UserApp save(UserApp user) {
        return userRepository.save(user);
    }

    public UserApp delete(Long id) {
        UserApp userApp = userRepository.findById(id).orElse(null);
        assert userApp != null;
        userApp.setActive(ACTIVE.NOACTIVE);
        return userRepository.save(userApp);
    }

    public UserApp getUserByPhone(String phone){
        return userRepository.findByPhone(phone);
    }




    public void sendSms(String phone) throws JSONException, JsonProcessingException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "akbar358888@gmail.com");
        jsonObject.put("password", "WsdUTxO4dsWFKW8KJLURdNocD1ZIbK4UitiQF3Z3");

        String url = "https://notify.eskiz.uz/api/auth/login";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);
        String personResultAsJsonStr =
                restTemplate.postForObject(url, request, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(personResultAsJsonStr);

        if (root.path("message").asText().equals("token_generated")) {
            String token = root.get("data").path("token").asText();
            JSONObject jsonObjectsms = new JSONObject();
            HttpHeaders headersms = new HttpHeaders();
            headersms.setContentType(MediaType.APPLICATION_JSON);
            headersms.setBearerAuth(token);
            jsonObjectsms.put("mobile_phone", phone);
            jsonObjectsms.put("message", ThreadLocalRandom.current().nextInt(1000, 9999));
            jsonObjectsms.put("from", 4546);
            HttpEntity<String> requestsms = new HttpEntity<String>(jsonObjectsms.toString(), headersms);
            String personResultAsJsonStrsms =
                    restTemplate.postForObject("https://notify.eskiz.uz/api/message/sms/send", requestsms, String.class);
            JsonNode rootsms = objectMapper.readTree(personResultAsJsonStrsms);
            System.out.print(rootsms.get("id").asText());
        }
    }
}
