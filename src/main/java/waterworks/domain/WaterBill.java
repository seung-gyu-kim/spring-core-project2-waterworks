package waterworks.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WaterBill {
    private String city;
    private String sector;
    private int unitPrice;
    private Integer billTotal;

    private int no;
    private int step;
    private int unitStart;
    private int unitEnd;
    private int basicCost;

    @Override
    public String toString() {
        return "WaterBill{" +
                "city='" + city + '\'' +
                ", sector='" + sector + '\'' +
                ", unitPrice=" + unitPrice +
                ", billTotal=" + billTotal +
                '}';
    }
}
