package wayc.backend.common.healthcheck;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthCheckController {

    private final Environment environment;

    @GetMapping("/health-check")
    public ResponseEntity healthCheck() {
        String[] activeProfiles = environment.getActiveProfiles();
        return new ResponseEntity<>(activeProfiles[0], HttpStatus.OK);
    }
}
