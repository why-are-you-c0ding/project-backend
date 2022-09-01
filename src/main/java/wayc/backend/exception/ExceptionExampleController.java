package wayc.backend.exception;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionExampleController {

    @GetMapping("/exception/test")
    public void exceptionTest(){
        throw new RuntimeException();
    }
}
