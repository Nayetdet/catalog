package io.github.nayetdet.catalog.controller;

import io.github.nayetdet.catalog.controller.docs.AuthControllerDocs;
import io.github.nayetdet.catalog.dto.auth.LoginDTO;
import io.github.nayetdet.catalog.dto.auth.RegisterDTO;
import io.github.nayetdet.catalog.dto.auth.TokenDTO;
import io.github.nayetdet.catalog.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth", description = "Endpoints for managing user authentication")
public class AuthController extends AbstractController implements AuthControllerDocs {

    private final AuthService authService;

    @PostMapping("/login")
    @Override
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
        return ResponseEntity.ok(authService.login(loginDTO));
    }

    @PostMapping("/refresh/{username}")
    @Override
    public ResponseEntity<TokenDTO> refresh(@PathVariable String username, @RequestHeader("Authorization") String refreshToken) {
        return ResponseEntity.ok(authService.refreshToken(username, refreshToken));
    }

    @PostMapping("/register")
    @Override
    public ResponseEntity<TokenDTO> register(@RequestBody @Valid RegisterDTO registerDTO) {
        return ResponseEntity.ok(authService.register(registerDTO));
    }

}
