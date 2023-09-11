/**
 * Контроллер для аутентификации и регистрации пользователей.
 */
package com.example.onlineresellingproject.controller;

import com.example.onlineresellingproject.dto.user.Login;
import com.example.onlineresellingproject.dto.user.Register;
import com.example.onlineresellingproject.microservicemsg.localservice.StatisticMicroService;
import com.example.onlineresellingproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class  AuthController {

    private final AuthService authService;

    private final StatisticMicroService statisticMicroService;

    /**
     * Метод для аутентификации пользователя.
     *
     * @param login Объект, содержащий информацию о входе пользователя.
     * @return Ответ с кодом состояния HTTP. В случае успешной аутентификации возвращается 200 OK,
     * в противном случае - 401 Unauthorized.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        if (authService.login(login.getUsername(), login.getPassword())) {

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * Метод для регистрации нового пользователя.
     *
     * @param register Объект, содержащий информацию о регистрации нового пользователя.
     * @return Ответ с кодом состояния HTTP. В случае успешной регистрации возвращается 201 Created,
     * в противном случае - 400 Bad Request.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Register register) {
        if (authService.register(register)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
