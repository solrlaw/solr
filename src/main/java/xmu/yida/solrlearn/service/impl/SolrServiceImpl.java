package xmu.yida.solrlearn.service.impl;

import org.springframework.stereotype.Service;
import xmu.yida.solrlearn.domain.User;
import xmu.yida.solrlearn.service.SolrService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SolrServiceImpl implements SolrService {
    @Override
    public List<User> addUser() {
        List<User> list=new ArrayList<>();
        for(int i=0;i<5;i++){
            User user=new User();
            user.setId(UUID.randomUUID().toString().replace("-",""));
            user.setName("jack"+i);
            if(i%2==0){
                user.setSex("男");
            }else{
                user.setSex("女");
            }
            user.setAddress("曾厝垵666"+i);
            user.setHost(456+i);
            list.add(user);
        }
        return list;
    }
}
