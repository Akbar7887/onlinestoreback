//package uz.onlinestor.onlinestoreback.resource.contragent;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.configurationprocessor.json.JSONException;
//import org.springframework.web.bind.annotation.*;
//import uz.onlinestor.onlinestoreback.models.contragent.Role;
//import uz.onlinestor.onlinestoreback.models.contragent.UserApp;
//import uz.onlinestor.onlinestoreback.service.contragent.UserService;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import static org.springframework.http.HttpHeaders.AUTHORIZATION;
//import static org.springframework.http.HttpStatus.FORBIDDEN;
//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//
//@RestController
//@RequestMapping("/online/doc/user/")
//@RequiredArgsConstructor
//public class UserResource {
//
//    @Autowired
//    final UserService userService;
//
//    @GetMapping("get")
//    private List<UserApp> getAll() {
//        return userService.getAll();
//    }
//
//    @PostMapping("save")
//    private UserApp save(@RequestBody UserApp user) {
//        return userService.save(user);
//    }
//
//    @PutMapping("delete")
//    private UserApp delete(@RequestParam("id") String id) {
//
//        return userService.delete(Long.parseLong(id));
//    }
//
//    @PostMapping("sendsms")
//    private void sendSms(@RequestParam("phone") String phone) throws JSONException, JsonProcessingException {
//        userService.sendSms(phone);
//    }
//
//    @GetMapping("/token/refresh")
//    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String authorizationHeader = request.getHeader(AUTHORIZATION);
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            try {
//                String refresh_token = authorizationHeader.substring("Bearer ".length());
//                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
//                JWTVerifier verifier = JWT.require(algorithm).build();
//                DecodedJWT decodedJWT = verifier.verify(refresh_token);
//                String username = decodedJWT.getSubject();
//                UserApp appUser = userService.getUserByPhone(username);
//
//                String access_token = JWT.create()
//                        .withSubject(appUser.getUsername())
//                        .withExpiresAt(new java.sql.Date(System.currentTimeMillis() + 10 * 60 * 1000))
//                        .withIssuer(request.getRequestURI().toString())
//                        .withClaim("roles", appUser.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
//                        .sign(algorithm);
//
//                Map<String, String> tokens = new HashMap<>();
//                tokens.put("access_token", access_token);
//                tokens.put("refresh_token", refresh_token);
//                response.setContentType(APPLICATION_JSON_VALUE);
//                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
//
//            } catch (Exception exception) {
//
//                response.setHeader("error", exception.getMessage());
//                response.setStatus(FORBIDDEN.value());
////                  response.sendError(FORBIDDEN.value());
//                Map<String, String> error = new HashMap<>();
//                error.put("error_message", exception.getMessage());
//                response.setContentType(APPLICATION_JSON_VALUE);
//                new ObjectMapper().writeValue(response.getOutputStream(), error);
//            }
//
//        } else {
//            throw new RuntimeException("Refresh token is missing");
//        }
//    }
//
//}
