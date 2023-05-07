package wayc.backend.common.exception;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wayc.backend.verification.exception.NotExistsMemberException;

/**
 * TODO 수정해야함.
 */
@RestController
public class ExceptionExampleController {

    @GetMapping("/exception/test")
    public void exceptionTest(){
        throw new NotExistsMemberException();
    }
}
