package xmu.yida.solrlearn.service.fallback;

import org.springframework.stereotype.Component;
import xmu.yida.solrlearn.domain.Node;
import xmu.yida.solrlearn.service.feign.GraphServiceClient;

@Component
public class GraphServiceFallback implements GraphServiceClient {
    @Override
    public Object findNode(Node node) {
        String result= "知识图谱服务宕机";
        return result;
    }

    @Override
    public Object findByObj(String content) {
        String result= "知识图谱服务宕机";
        return result;
    }

    @Override
    public Object getDistance(String node1, String node2) {
        String result= "知识图谱服务宕机";
        return result;
    }
}
