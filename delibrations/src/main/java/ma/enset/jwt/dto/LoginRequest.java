package ma.enset.jwt.dto;

public record LoginRequest(
        String grantType,
        String email,
        String password,
        boolean withRefreshToken,
        String refreshToken
        ) {
}
