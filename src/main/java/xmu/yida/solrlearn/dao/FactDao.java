package xmu.yida.solrlearn.dao;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.yida.solrlearn.domain.fact.Fact;
import xmu.yida.solrlearn.domain.po.FactPO;
import xmu.yida.solrlearn.mapper.FactMapper;

@Repository
public class FactDao {

    @Autowired
    private SolrClient solrClient;

    @Autowired
    private FactMapper factMapper;

    public SolrDocumentList getFact(String content) {
        try{
            SolrQuery solrQuery=new SolrQuery();
        solrQuery.setQuery("fact:"+content);
        solrQuery.setRows(50);
        solrQuery.setStart(0);
        solrQuery.set("fl","fact,id");
        QueryResponse response =solrClient.query(solrQuery);
        SolrDocumentList documentList=response.getResults();
        return documentList;
        }catch (Exception e){
            return null;
        }
    }


    public Fact addFact(Fact fact){
        FactPO factPO=new FactPO(fact);
        FactPO result=factMapper.addFactPO(factPO);
        return new Fact(result);
    }

    public Fact getFactById(Integer id){
        FactPO factPO=factMapper.getFactPOById(id);
        if(factPO!=null){
            return new Fact(factPO);
        }else{
            return null;
        }
    }

    public Fact updateFact(Fact fact){
        FactPO factPO=new FactPO(fact);
        factMapper.updateFactPO(factPO);
        return new Fact(factPO);
    }

    public boolean deleteFactById(Integer id){
        try{
            UpdateResponse updateResponse=solrClient.deleteById(id.toString());
            solrClient.commit();
            return factMapper.deleteFactPOById(id);
        }catch (Exception e){
            return false;
        }
    }
    /*
    只有solr数据库
     */
    public void deleteAll(){
        try{
            solrClient.deleteByQuery("*:*");
            solrClient.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
