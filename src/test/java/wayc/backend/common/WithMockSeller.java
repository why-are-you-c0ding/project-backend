package wayc.backend.common;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockSellerSecurityContextFactory.class)
public @interface WithMockSeller {

    long principal() default 1L;
    String password() default "123";

}
