package uz.onlinestor.onlinestoreback.resource.contragent;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.onlinestor.onlinestoreback.models.contragent.UserApp;
import uz.onlinestor.onlinestoreback.service.contragent.UserServiceIml;

import java.util.List;

@RestController
@RequestMapping("/online/doc/user/")
@RequiredArgsConstructor
public class UserResource {

    @Autowired
    final UserServiceIml userService;

    @GetMapping("get")
    private List<UserApp> getAll() {
        return userService.findAll();
    }

    @PostMapping("save")
    private UserApp save(@RequestBody UserApp user) {
        return userService.saveUser(user);
    }

    @PutMapping("delete")
    private void delete(@RequestParam("id") String id) {
        userService.deleteById(Long.parseLong(id));
    }

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

}
