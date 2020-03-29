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
public class Meta implements Serializable {
    private String relevant_articles;
    private String accusation;
    private Double punish_of_money;
    private String criminals;
    private  TermOfImprisonment termOfImprisonment;
}
