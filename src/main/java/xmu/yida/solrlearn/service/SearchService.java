package xmu.yida.solrlearn.service;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.yida.solrlearn.dao.FactDao;

import java.io.IOException;

@Service
public class SearchService {

    @Autowired
    private FactDao factDao;

    public Object getFact(String content) {
        return factDao.getFact(content);
    }

    public Object deleteFactById(String id){
        return factDao.deleteFactById(id);
    }

    public void deleteAll(){
        factDao.deleteAll();
    }
}
