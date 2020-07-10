package xmu.yida.solrlearn.service.fallback;

import org.springframework.stereotype.Component;
import xmu.yida.solrlearn.service.feign.UserServiceClient;

@Component
public class UserServiceFallback implements UserServiceClient {
    @Override
    public boolean validate(String user, String requireRole) {
        return false;
    }
}
