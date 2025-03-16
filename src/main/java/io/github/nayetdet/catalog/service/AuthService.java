package io.github.nayetdet.catalog.service;

import io.github.nayetdet.catalog.dto.auth.LoginDTO;
import io.github.nayetdet.catalog.dto.auth.RegisterDTO;
import io.github.nayetdet.catalog.dto.auth.TokenDTO;
import io.github.nayetdet.catalog.exception.InternalServerException;
import io.github.nayetdet.catalog.model.User;
import io.github.nayetdet.catalog.repository.PermissionRepository;
import io.github.nayetdet.catalog.repository.UserRepository;
import io.github.nayetdet.catalog.security.jwt.JwtTokenProvider;
import io.github.nayetdet.catalog.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;

    public TokenDTO login(LoginDTO loginDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(),
                loginDTO.getPassword()
        ));

        var user = userRepository.findByUsername(loginDTO.getUsername()).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }

        return jwtTokenProvider.createAccessToken(
                loginDTO.getUsername(),
                user.getRoles()
        );
    }

    public TokenDTO refreshToken(String username, String refreshToken) {
        if (!userRepository.existsByUsername(username)) {
            throw new UsernameNotFoundException("Username not found");
        }

        return jwtTokenProvider.refreshToken(refreshToken);
    }

    public TokenDTO register(RegisterDTO registerDTO) {
        var permission = permissionRepository
                // .findByDescription("COMMON_USER")
                .findByDescription("ADMIN") // Deixei assim para fins de estudo
                .orElseThrow(() -> new InternalServerException("Internal Server Error"));

        var user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setPermissions(List.of(permission));

        userValidator.validate(user);
        userRepository.save(user);

        return jwtTokenProvider.createAccessToken(
                user.getUsername(),
                user.getRoles()
        );
    }

}
