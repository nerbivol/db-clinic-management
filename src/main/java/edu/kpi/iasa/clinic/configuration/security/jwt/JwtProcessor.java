package edu.kpi.iasa.clinic.configuration.security.jwt;

import edu.kpi.iasa.clinic.configuration.security.UserPrincipal;
import edu.kpi.iasa.clinic.repository.model.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JwtProcessor {

    private JwtProperties properties;
    private String secret;
    private UserDetailsService userDetailsService;

    @Autowired
    public JwtProcessor(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
                        JwtProperties properties) {
        this.userDetailsService = userDetailsService;
        this.properties = properties;
        this.secret = getBase64EncodedSecretKey(properties.getSecret());
    }

    private String getBase64EncodedSecretKey(String secret) {
        return Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createJwt(String username, Collection<GrantedAuthority> authorities) {

        Claims claims = createClaims(username, authorities);

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + properties.getExpirationSeconds());

        return buildJwt(claims, now, expirationDate);
    }

    private Claims createClaims(String username, Collection<GrantedAuthority> authorities) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", getRoles(authorities));
        return claims;
    }

    private List<String> getRoles(Collection<GrantedAuthority> authorities) {
        List<String> roles = new ArrayList<>();

        authorities.forEach(role -> roles.add(role.getAuthority()));
        return roles;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String jwt) {
        Set<SimpleGrantedAuthority> grantedAuthoritySet = new HashSet<>();
        List<String> roles = (List<String>) getClaims(jwt).getBody().get("roles");
        Arrays.stream(roles.toArray())
                .forEach(s -> grantedAuthoritySet.add(new SimpleGrantedAuthority((String) s)));
        return grantedAuthoritySet;
    }

    private String buildJwt(Claims claims, Date issuedAt, Date expirationDate) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setIssuedAt(issuedAt)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, this.secret)
                .compact();
    }

    Authentication getAuthentication(String jwt) {
        UserDetails userDetails = new UserPrincipal(Account.builder().email(getEmail(jwt)).build());
        return new UsernamePasswordAuthenticationToken(userDetails, "", getAuthorities(jwt));
    }

    private String getEmail(String jwt) {
        return getClaims(jwt).getBody().getSubject();
    }

    private Jws<Claims> getClaims(String jwt) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt);
    }

    String getJwt(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return "no jwt";
    }

    boolean isValid(String jwt) {
        return jwt != null && jwt.contains(".") && hasNoWhitespaces(jwt) && isNotExpired(jwt);
    }

    private boolean hasNoWhitespaces(String token) {
        return !token.matches("[\\s]");
    }

    private boolean isNotExpired(String jwt) {
        return isExpirationNotBeforeNow(getClaims(jwt));
    }

    private boolean isExpirationNotBeforeNow(Jws<Claims> claims) {
        return !claims.getBody().getExpiration().before(new Date());
    }
}