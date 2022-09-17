package wayc.backend.shop.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/items")
public class ItemController {

    @GetMapping()
    public void test(Authentication authentication, HttpServletRequest request){
        System.out.println("request = " + request);
        System.out.println("authentication = " + authentication.getName());
    }
}
