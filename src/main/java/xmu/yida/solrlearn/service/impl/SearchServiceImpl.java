package xmu.yida.solrlearn.service.impl;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.yida.solrlearn.dao.FactDao;
import xmu.yida.solrlearn.domain.fact.Fact;
import xmu.yida.solrlearn.domain.po.FactPO;
import xmu.yida.solrlearn.service.SearchService;
import xmu.yida.solrlearn.service.feign.GraphServiceClient;

import javax.annotation.Resource;
import java.util.*;

@Service
public class SearchServiceImpl implements SearchService {

    @Resource
    private FactDao factDao;

    @Resource
    private GraphServiceClient graph;

    public static int N=5;
    public static final int INF=0x7FFFFFFF;
    public static int[][] weight;
    public static int[] A=new int[N];
    public static int[] B=new int[N];
    public static int[] match=new int[N];
    public static boolean []visited_l=new boolean[N];
    public static boolean []visited_r=new boolean[N];
    public static int []slack=new int[N];
    public static int []slack_right=new int[N];

    @Override
    public List<Fact> similarity(String content){
        //分词
        List<Word> words = WordSegmenter.seg(content);
        List<String> keys= new ArrayList<>();
        for(Word word:words)
        {

            String key=word.getText();
            try {
                if((Boolean)graph.findByObj(key)){
                    //将所有实体放入列表中
                    keys.add(key);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        List<Fact> facts= new ArrayList<>();
        List<FactPO> factPOS=factDao.getAllFacts();
        for(FactPO factPO: factPOS){
            facts.add(new Fact(factPO));
        }
        N=keys.size();
        weight=new int[N][N];
        A=new int[N];
        B=new int[N];
        match=new int[N];
        visited_l=new boolean[N];
        visited_r=new boolean[N];
        slack=new int[N];
        slack_right=new int[N];
        Map<Fact, Double> result= new HashMap<>();
        for(Fact fact:facts){
            List<Word> qwords=WordSegmenter.seg(fact.getFact());
            List<String> qkeys= new ArrayList<>();
            for(Word word:qwords)
            {
                String key=word.getText();
                try {
                    if((Boolean) graph.findByObj(key)) {
                        //将所有实体放入计算案例的列表中
                        qkeys.add(key);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    weight[i][j]=0;
                }
            }
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++) {
                    if(j==qkeys.size()) {
                        break;
                    }
                    try {
                        weight[i][j]= (Integer) graph.getDistance(keys.get(i),qkeys.get(j));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            int re=KM();
            double similarity=(double) re/(double)N;
            result.put(fact,similarity);
        }
        List<Map.Entry<Fact,Double>> re=new ArrayList<>(result.entrySet());
        re.sort((t1, t2) -> (int) (t2.getValue() - t1.getValue()));
        List<Fact> facts1=new ArrayList<>();
        for (Map.Entry<Fact, Double> factDoubleEntry : re) {
            facts1.add(factDoubleEntry.getKey());
        }
        if(facts1.size()>10) {
            return facts1.subList(0,9);
        }
        else {
            return facts1;
        }
    }


    public static int KM(){
        //初始化
        for(int i=0;i<N;i++) {
            match[i]=-1;
            B[i]=0;
            int temp=0;
            for(int j=0;j<N;j++) {
                if(temp<weight[i][j]) {
                    temp=weight[i][j];
                }
            }
            A[i]=temp;
        }
        for(int i=0;i<N;i++){
            while(true){
                for(int l=0;l<N;l++) {
                    visited_r[l]=false;
                    visited_l[l]=false;
                }
                if(findArgumentPath(i)) {
                    break;    //寻找增广路径
                }
                //修改标号
                int d=INF;
                for(int j=0;j<N;j++) {
                    if(visited_l[j]) {
                        for(int s=0;s<N;s++) {
                            if(!visited_r[s])
                            {
                                int temp=A[j]+B[s]-weight[j][s];
                                d= Math.min(temp, d);
                            }
                        }
                    }
                }
                for(int j=0;j<N;j++){
                    if(visited_l[j]) {
                        A[j]-=d;
                    }
                    if(visited_r[j]) {
                        B[j]+=d;
                    }
                }
            }
        }
        int result=0;
        for(int j=0;j<N;j++) {
            result+=weight[match[j]][j];
        }
        return result;

    }

    //在子图中寻找增广路径
    public static boolean findArgumentPath(int x){
        visited_l[x]=true;
        for(int y=0;y<N;y++) {
            if (!visited_r[y] && A[x] + B[y] == weight[x][y])
            {
                visited_r[y] = true;
                if (match[y] == -1 || findArgumentPath(match[y])) {
                    match[y] = x;
                    return true;
                }
            }
        }
        return false;
    }
}
