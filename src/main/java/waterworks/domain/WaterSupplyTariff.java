package waterworks.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class WaterSupplyTariff {
    private int no;
    private String name;
    private String type;
    private int step;
    private int unitStart;
    private int unitEnd;
    private int unitPrice;
    private Integer basicPrice;
}
