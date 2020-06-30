package xmu.yida.solrlearn.service;

import org.springframework.stereotype.Service;
import xmu.yida.solrlearn.domain.fact.Fact;

import java.util.List;

@Service
public interface SearchService {

    List<Fact> similarity(String content);
}
