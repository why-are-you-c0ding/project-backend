package wayc.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/")
    public String helloWorld(){
        return "hi";
    }
}

//backend-0.0.1-SNAPSHOT.jar