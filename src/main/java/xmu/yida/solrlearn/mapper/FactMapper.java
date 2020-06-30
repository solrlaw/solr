package xmu.yida.solrlearn.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import xmu.yida.solrlearn.domain.po.FactPO;

import java.util.List;

@Component
@Mapper
public interface FactMapper {

    FactPO getFactPOById(Integer id);

    boolean deleteFactPOById(Integer id);

    FactPO addFactPO(FactPO factPO);

    FactPO updateFactPO(FactPO factPO);

    List<FactPO> getAllFacts();
}
