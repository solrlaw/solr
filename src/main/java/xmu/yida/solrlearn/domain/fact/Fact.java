package xmu.yida.solrlearn.domain.fact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import xmu.yida.solrlearn.domain.po.FactPO;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Fact implements Serializable {
    private Integer id;
    private String fact;
    private Meta meta;

    public Fact(FactPO factPO) {
        this.setId(factPO.getId());
        this.setFact(factPO.getFact());
        Meta meta = new Meta();
        meta.setAccusation(factPO.getRelevantIds());
        meta.setRelevant_articles(factPO.getRelevantIds());
        meta.setCriminals(factPO.getCriminals());
        TermOfImprisonment termOfImprisonment = new TermOfImprisonment();
        termOfImprisonment.setDeathPenalty(factPO.getDeathPenalty());
        termOfImprisonment.setImprisonment(factPO.getImprisonment());
        termOfImprisonment.setLifeImprisonment(factPO.getLifeImprisonment());
        meta.setTermOfImprisonment(termOfImprisonment);
        this.setMeta(meta);
    }
}
