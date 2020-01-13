package xmu.yida.solrlearn.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {

    @Field("id")
    private String id;

    @Field("item_name")
    private String name;

    @Field("item_sex")
    private String sex;

    @Field("item_address")
    private String address;

    @Field("item_host")
    private Integer host;
}
