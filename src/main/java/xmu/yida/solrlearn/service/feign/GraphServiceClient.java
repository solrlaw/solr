package xmu.yida.solrlearn.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import xmu.yida.solrlearn.domain.Node;
import xmu.yida.solrlearn.service.fallback.GraphServiceFallback;

@Component
@FeignClient(value = "GRAPH-SERVICE", fallback = GraphServiceFallback.class)
public interface GraphServiceClient {
    @GetMapping("/graph")
    public Object findNode(@RequestBody Node node);


    @GetMapping("/graph/{content}")
    public Object findByObj(@PathVariable String content);

    @GetMapping("/graph/{node1}/{node2}")
    public Object getDistance(@PathVariable String node1,@PathVariable String node2);
}
