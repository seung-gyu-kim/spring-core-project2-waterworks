package waterworks.domain;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class WaterBill {
    private int no;
    private String city;
    private String sector;
    private int step;
    private int unitStart;
    private int unitEnd;
    private int unitPrice;
    private Integer basicCost;
    private Integer billTotal;

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
