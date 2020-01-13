package xmu.yida.solrlearn.service;

import org.springframework.stereotype.Service;
import xmu.yida.solrlearn.domain.User;

import java.util.List;

@Service
public interface SolrService {
    List<User> addUser();
}
