package xmu.yida.solrlearn.dao;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.yida.solrlearn.domain.Fact;

import java.io.IOException;
import java.util.List;

@Repository
public class FactDao {

    @Autowired
    private SolrClient solrClient;

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

    public Integer deleteFactById(String id){
        try{
            UpdateResponse updateResponse=solrClient.deleteById(id);
            solrClient.commit();
            return updateResponse.getStatus();
        }catch (Exception e){
            return null;
        }
    }

    public void deleteAll(){
        try{
            solrClient.deleteByQuery("*:*");
            solrClient.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
