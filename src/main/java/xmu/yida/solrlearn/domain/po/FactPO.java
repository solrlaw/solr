package xmu.yida.solrlearn.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;
import xmu.yida.solrlearn.domain.fact.Fact;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@Alias("FactPO")
public class FactPO implements Serializable {
    private Integer id;
    private String fact;
    private String relevantIds;
    private Double punishOfMoney;
    private String criminals;
    private Boolean deathPenalty;
    private Integer imprisonment;
    private Boolean lifeImprisonment;

    public FactPO(Fact fact){
        this.id=fact.getId();
        this.fact=fact.getFact();
        this.relevantIds=fact.getMeta().getRelevant_articles();
        this.punishOfMoney=fact.getMeta().getPunish_of_money();
        this.criminals=fact.getMeta().getCriminals();
        this.deathPenalty=fact.getMeta().getTermOfImprisonment().getDeathPenalty();
        this.imprisonment=fact.getMeta().getTermOfImprisonment().getImprisonment();
        this.lifeImprisonment=fact.getMeta().getTermOfImprisonment().getLifeImprisonment();
    }
}
