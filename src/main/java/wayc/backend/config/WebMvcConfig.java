package wayc.backend.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import wayc.backend.config.argumentresolver.GetRecommendedItemArgumentResolver;
import wayc.backend.config.argumentresolver.GetStockArgumentResolver;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthenticationPrincipalArgumentResolver());
        resolvers.add(new GetStockArgumentResolver());
        resolvers.add(new GetRecommendedItemArgumentResolver());
    }
}
