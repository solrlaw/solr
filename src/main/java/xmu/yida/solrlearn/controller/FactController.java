package xmu.yida.solrlearn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import xmu.yida.solrlearn.domain.fact.Fact;
import xmu.yida.solrlearn.service.FactService;

@RestController
public class FactController {

    @Autowired
    private FactService factService;

    @CrossOrigin
    @GetMapping("/facts")
    Object getFact(@Param("content")String content){
        try{
            System.out.println("成功获取内容");
            return factService.getFact(content);
        } catch (Exception e){
            return null;
        }
    }

    @CrossOrigin
    @GetMapping("/facts/{id}")
    Object getFactById(@PathVariable Integer id){
        return factService.getFactById(id);
    }

    @PutMapping("/facts")
    Object updateFact(@RequestBody Fact fact){
        return factService.updateFact(fact);
    }

    @PostMapping("/facts")
    Object addFact(@RequestBody Fact fact){
        return factService.addFact(fact);
    }

    @DeleteMapping("/facts/{id}")
    Object deleteFactById(@PathVariable Integer id){
        return factService.deleteFactById(id);
    }

    @DeleteMapping("/facts")
    void deleteAll(){
        factService.deleteAll();
    }
}
