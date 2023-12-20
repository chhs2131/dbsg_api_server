package kr.co.dbsg.api.api.health;

import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/health")
public class HealthController {
    @GetMapping("")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("ok " + LocalDateTime.now());
    }
}
