package xmu.yida.solrlearn.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import xmu.yida.solrlearn.domain.fact.Fact;
import xmu.yida.solrlearn.service.FactService;
import xmu.yida.solrlearn.util.ResponseUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
@DefaultProperties(defaultFallback = "searchGlobalFallback")
public class FactController {

    @Autowired
    private FactService factService;

    @GetMapping("/facts")
    @HystrixCommand
    Object getFact(@Param("keywords")String keywords){
        try{
            System.out.println("成功获取内容");
            return factService.getFact(keywords);
        } catch (Exception e){
            return null;
        }
    }

    @GetMapping("/facts/{id}")
    @HystrixCommand
    Object getFactById(@PathVariable Integer id){

        return factService.getFactById(id);
    }

    @PutMapping("/facts")
    @HystrixCommand
    Object updateFact(@RequestBody Fact fact, HttpServletRequest request){
        String token = request.getHeader("token");

        return factService.updateFact(fact);
    }

    @PostMapping("/facts")
    @HystrixCommand
    Object addFact(@RequestBody Fact fact){
        return factService.addFact(fact);
    }

    @DeleteMapping("/facts/{id}")
    @HystrixCommand
    Object deleteFactById(@PathVariable Integer id){
        return factService.deleteFactById(id);
    }

    @DeleteMapping("/facts")
    @HystrixCommand
    void deleteAll(){
        factService.deleteAll();
    }


    public Object searchGlobalFallback(){
        return ResponseUtil.customization(601, "查询服务正忙，请稍后再试！");
    }
}
