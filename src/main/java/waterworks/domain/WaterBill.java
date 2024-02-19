package waterworks.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class WaterBill {
    //순번": 1,
    //    "지자체명": "동두천시",
    //    "업종": "가정용",
    //    "단계": 1,
    //    "구간시작(세제곱미터)": 1,
    //    "구간끝(세제곱미터)": 20,
    //    "구간금액(원)": 690,
    //    "단계별 기본요금(원)": ""
    @JsonProperty("순번")
    private int no;

    @JsonProperty("지자체명")
    private String city;

    @JsonProperty("업종")
    private String sector;

    @JsonProperty("단계")
    private int step;

    @JsonProperty("구간시작(세제곱미터)")
    private int unitStart;

    @JsonProperty("구간끝(세제곱미터)")
    private int unitEnd;

    @JsonProperty("구간금액(원)")
    private int unitPrice;

    @JsonProperty("단계별 기본요금(원)")
    private Integer basicCost;

    @JsonIgnore
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
