package com.example.phoenix.context.security;

import com.example.phoenix.domain.UserEntity;
import com.example.phoenix.model.UserMapper;
import com.example.phoenix.model.UserPrincipal;
import com.example.phoenix.repository.UserRepository;
import com.example.phoenix.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.bouncycastle.util.encoders.Base64;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtService jwtService;
  private final UserRepository userRepository;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }
    jwt = authHeader.substring(7);
    final Claims claims;
    try {
      claims = jwtService.getAllClaims(jwt);
    } catch (Exception e) {
      logger.warn(new String(Base64.decode(jwt.split(",")[1]), StandardCharsets.UTF_8));
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
      filterChain.doFilter(request, response);
      return;
    }
    if (StringUtils.isNotEmpty(claims.getSubject())
        && SecurityContextHolder.getContext().getAuthentication() == null) {
      final var userId = UUID.fromString(claims.get("userId", String.class));
      if (jwtService.isTokenValid(jwt, userId.toString())) {
        UserEntity user = userRepository.findByIdAndDeletedIsFalse(userId);

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        final Collection<? extends GrantedAuthority> authorities =
            Arrays.stream(user.getRole().split(",")).map(SimpleGrantedAuthority::new).toList();

        UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(
                new UserPrincipal(UserMapper.INSTANCE.toResponse(user), authHeader),
                null,
                authorities);
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        context.setAuthentication(authToken);
        SecurityContextHolder.setContext(context);
      }
    }
    filterChain.doFilter(request, response);
  }
}
