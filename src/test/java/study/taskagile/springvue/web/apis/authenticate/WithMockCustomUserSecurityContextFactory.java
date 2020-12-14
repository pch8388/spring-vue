package study.taskagile.springvue.web.apis.authenticate;

import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import study.taskagile.springvue.domain.model.user.SimpleUser;
import study.taskagile.springvue.domain.model.user.User;

import java.lang.reflect.Field;

public class WithMockCustomUserSecurityContextFactory
    implements WithSecurityContextFactory<WithMockCustomUser> {
    @SneakyThrows
    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser customUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        SimpleUser principal =
            new SimpleUser(User.create("sc", "test@test.com", "123"));

        Class<?> clazz = principal.getClass();
        Field field = clazz.getDeclaredField("userId");
        field.setAccessible(true);
        field.set(principal, 1L);

        Authentication auth =
            new UsernamePasswordAuthenticationToken(principal, "password", principal.getAuthorities());
        context.setAuthentication(auth);
        return context;
    }
}
