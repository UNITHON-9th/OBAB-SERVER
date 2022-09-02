package dev.unit.obab.core.web;


import dev.unit.obab.core.domain.ResponseEntity;
import dev.unit.obab.core.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/health-check")
@RestController
public class HealthCheckController {

    private final RedisUtil redisUtil;

    @GetMapping
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.successResponse("hi");
    }

    @GetMapping("/redis")
    public ResponseEntity<String> redisHealthCheck() {
        return ResponseEntity.successResponse(this.redisUtil.getData("temp"));
    }

}
