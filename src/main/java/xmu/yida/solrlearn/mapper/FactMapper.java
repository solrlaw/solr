package xmu.yida.solrlearn.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import xmu.yida.solrlearn.domain.po.FactPO;

@Component
@Mapper
public interface FactMapper {

    public FactPO getFactPOById(Integer id);

    public boolean deleteFactPOById(Integer id);

    public FactPO addFactPO(FactPO factPO);

    public FactPO updateFactPO(FactPO factPO);
}
