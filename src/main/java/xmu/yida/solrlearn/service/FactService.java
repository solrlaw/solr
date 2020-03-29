package xmu.yida.solrlearn.service;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.yida.solrlearn.dao.FactDao;
import xmu.yida.solrlearn.domain.fact.Fact;

import java.io.IOException;

@Service
public class FactService {

    @Autowired
    private FactDao factDao;

    public Object getFact(String content) {
        return factDao.getFact(content);
    }

    public Fact getFactById(Integer id){
        return factDao.getFactById(id);
    }

    public Fact updateFact(Fact fact){
        return factDao.updateFact(fact);
    }

    public boolean deleteFactById(Integer id){
        return factDao.deleteFactById(id);
    }

    public void deleteAll(){
        factDao.deleteAll();
    }

    public Fact addFact(Fact fact){
        return factDao.addFact(fact);
    }
}
