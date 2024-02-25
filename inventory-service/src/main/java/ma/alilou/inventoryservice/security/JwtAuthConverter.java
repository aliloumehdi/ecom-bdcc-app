package ma.alilou.inventoryservice.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter=new JwtGrantedAuthoritiesConverter();
    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        Collection<GrantedAuthority> authorities= Stream.concat(
            jwtGrantedAuthoritiesConverter.convert(source).stream(),extractRolesFromToken(source).stream()
        ).collect(Collectors.toSet());

        return new JwtAuthenticationToken(source,authorities,source.getClaim("preferred_username"));
    }
    Collection<GrantedAuthority>extractRolesFromToken(Jwt token){
        Map<String,Object> realmAccess;
        Collection<String>roles;
        if(!Objects.nonNull(token.getClaim("realm_access")))
            return Set.of();
        realmAccess = token.getClaim("realm_access");
        roles = (Collection<String>) realmAccess.get("roles");
        return roles.stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toSet());
    }
}
