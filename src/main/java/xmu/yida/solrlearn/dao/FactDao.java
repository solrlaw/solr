package xmu.yida.solrlearn.dao;

import lombok.extern.java.Log;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Repository;
import xmu.yida.solrlearn.domain.fact.Fact;
import xmu.yida.solrlearn.domain.po.FactPO;
import xmu.yida.solrlearn.mapper.FactMapper;

import javax.annotation.Resource;
import java.util.List;

@Log
@Repository
public class FactDao {

    @Resource
    private SolrClient solrClient;

    @Resource
    private FactMapper factMapper;

    public SolrDocumentList getFact(String content) {
        try{
            SolrQuery solrQuery=new SolrQuery();
            System.out.println("查询关键词为："+content);
        solrQuery.setQuery("fact:"+content);
        solrQuery.setRows(50);
        solrQuery.setStart(0);
        QueryResponse response =solrClient.query(solrQuery);
            return response.getResults();
        }catch (Exception e){
            return null;
        }
    }


    public Fact addFact(Fact fact){
        FactPO factPO=new FactPO(fact);
        FactPO result=factMapper.addFactPO(factPO);
        SolrInputDocument input = new SolrInputDocument();
        input.addField("id",result.getId());
        input.addField("fact", result.getFact());
        try{
            solrClient.add(input);
            solrClient.commit();
        }catch (Exception e){
            log.warning("solr数据库插入数据失败！");
        }
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
            solrClient.deleteById(id.toString());
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

    public List<FactPO> getAllFacts(){
        return factMapper.getAllFacts();
    }
}
