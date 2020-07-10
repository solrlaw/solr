package xmu.yida.solrlearn.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import xmu.yida.solrlearn.domain.fact.Fact;
import xmu.yida.solrlearn.service.FactService;
import xmu.yida.solrlearn.service.feign.UserServiceClient;
import xmu.yida.solrlearn.util.ResponseUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@DefaultProperties(defaultFallback = "searchGlobalFallback")
public class FactController {

    @Resource
    private FactService factService;

    @Resource
    private UserServiceClient userServiceClient;

    @GetMapping("/facts")
    @HystrixCommand
    Object getFact(@Param("keywords")String keywords){
        try{
            System.out.println("成功获取内容");
            Object data = factService.getFact(keywords);
            return ResponseUtil.customization(200, "成功获取solr内容", data);
        } catch (Exception e){
            return ResponseUtil.fail();
        }
    }

    @GetMapping("/facts/{id}")
    @HystrixCommand
    Object getFactById(@PathVariable Integer id){

        Object data = factService.getFactById(id);
        return ResponseUtil.ok(data);
    }

    @PutMapping("/facts")
    @HystrixCommand
    Object updateFact(@RequestBody Fact fact, HttpServletRequest request){
        String user = request.getHeader("user");
        boolean auth = userServiceClient.validate(user, "管理员");
        if(auth){
            return ResponseUtil.ok(factService.updateFact(fact));
        }else{
            return ResponseUtil.customization(-1, "当前用户操作无权限");
        }
    }

    @PostMapping("/facts")
    @HystrixCommand
    Object addFact(@RequestBody Fact fact, HttpServletRequest request){
        String user = request.getHeader("user");
        boolean auth = userServiceClient.validate(user, "管理员");
        if(auth){
            return ResponseUtil.ok(factService.addFact(fact));
        }else{
            return ResponseUtil.customization(-1, "当前用户操作无权限");
        }
    }

    @DeleteMapping("/facts/{id}")
    @HystrixCommand
    Object deleteFactById(@PathVariable Integer id){
        return factService.deleteFactById(id);
    }

    @DeleteMapping("/admin/facts")
    @HystrixCommand
    void deleteAll(){
        factService.deleteAll();
    }


    public Object searchGlobalFallback(){
        return ResponseUtil.customization(601, "查询服务正忙，请稍后再试！");
    }
}
