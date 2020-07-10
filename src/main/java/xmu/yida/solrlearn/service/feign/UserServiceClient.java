package xmu.yida.solrlearn.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xmu.yida.solrlearn.service.fallback.UserServiceFallback;

@Component
@FeignClient(value = "USER-SERVICE", fallback = UserServiceFallback.class)
public interface UserServiceClient {

    @GetMapping("/validate")
    public boolean validate(@RequestParam String user, @RequestParam String requireRole);
}
