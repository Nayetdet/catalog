package io.github.nayetdet.catalog.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Schema(name = "Token")
public class TokenDTO {

    private String username;
    private Boolean isAuthenticated;
    private Date createdAt;
    private Date expiresAt;
    private String accessToken;
    private String refreshToken;

}
