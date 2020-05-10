package xmu.yida.solrlearn.domain.fact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class TermOfImprisonment implements Serializable {
    private Boolean deathPenalty;
    private Integer imprisonment;
    private Boolean lifeImprisonment;
}
