package xmu.yida.solrlearn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xmu.yida.solrlearn.service.SearchService;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/fact")
    Object getFact(@Param("content")String content){
        try{
            return searchService.getFact(content);
        } catch (Exception e){
            return null;
        }
    }
    @DeleteMapping("/facts/{id}")
    Object deleteFactById(@PathVariable String id){
        return searchService.deleteFactById(id);
    }

    @DeleteMapping("/facts")
    void deleteAll(){
        searchService.deleteAll();
    }
}
