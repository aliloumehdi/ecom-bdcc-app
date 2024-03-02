package ma.alilou.orderservice.interceptors;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        JwtAuthenticationToken defaultOidcUser=getAuthenticationToken();
        requestTemplate.header("Authorization","Bearer "+defaultOidcUser.getToken().getTokenValue());
    }
    JwtAuthenticationToken getAuthenticationToken(){
        SecurityContext securityContext= SecurityContextHolder.getContext();
        return (JwtAuthenticationToken) securityContext.getAuthentication();
    }
}
