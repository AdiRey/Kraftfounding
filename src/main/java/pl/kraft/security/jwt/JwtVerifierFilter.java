package pl.kraft.security.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtVerifierFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String key = "TODO - SECURE KEY bezpiecznybezpiecznybezpiecznybezpiecznybezpieczny";
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        try {
            String token = authorizationHeader.replace("Bearer ", "");
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(key.getBytes())).parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            String subject = body.getSubject();
            List<Map<String, String>> authorities = (List<Map<String, String>>)body.get("authorities");
            Set<SimpleGrantedAuthority> authoritySet = authorities.stream().map(
                    simple -> new SimpleGrantedAuthority(simple.get("authority"))
            ).collect(Collectors.toSet());
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    subject,
                    null,
                    authoritySet
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            httpServletResponse.addHeader("Authorization", "Bearer " + token);
        } catch (ExpiredJwtException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
