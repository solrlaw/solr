package xmu.yida.solrlearn.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class FactPO implements Serializable {
    private Integer id;
    private String fact;
    private String relevantIds;
    private Double punishOfMoney;
    private String criminals;
    private Boolean deathPenalty;
    private Integer imprisonment;
    private Boolean lifeImprisonment;
}
