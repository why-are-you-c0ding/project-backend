//package wayc.backend.common;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.session.web.http.CookieSerializer;
//import org.springframework.session.web.http.DefaultCookieSerializer;
//
//@Configuration
//public class SpringSessionConfiguration {
//    @Bean
//    public CookieSerializer cookieSerializer() {
//        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
//        serializer.setCookieName("JSESSIONID");
//        serializer.setDomainName("localhost");
//        serializer.setCookiePath("/");
//        serializer.setCookieMaxAge(3600);
//        serializer.setSameSite("Lax");  // SameSite
//        serializer.setUseHttpOnlyCookie(true);
//        serializer.setUseSecureCookie(false);
//        return serializer;
//    }
//}
//
////https://www.springcloud.io/post/2022-04/spring-samesite/#gsc.tab=0