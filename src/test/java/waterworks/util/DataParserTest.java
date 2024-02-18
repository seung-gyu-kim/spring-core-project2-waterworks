package waterworks.util;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import waterworks.config.BeanConfig;
import waterworks.domain.WaterBill;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


@SpringJUnitConfig(BeanConfig.class)
class DataParserTest {
    @Autowired
    DataParser dataParser;

    //1, 동두천시 , 가정용 ,1,1,20,690,
    //2, 동두천시 , 가정용 ,2,21,30,1090,
    //3, 동두천시 , 가정용 ,3,31,999999,1530,
    //4, 동두천시 , 일반용 ,1,1,100,1410,
    //5, 동두천시 , 일반용 ,2,101,300,1480,
    //6, 동두천시 , 일반용 ,3,301,1000,1560,
    //7, 동두천시 , 일반용 ,4,1001,999999,1700,
    //8, 동두천시 , 일반용(미) ,1,1,1000,1090,
    //9, 동두천시 , 일반용(미) ,2,1001,9999999,1200,
    //10, 동두천시 , 전용공업용 ,1,1,999999,510,
    List<WaterBill> waterBills = new ArrayList<>();

    @BeforeEach
    void setUp() {

    }

    @Test
    void parse() {
        List<WaterBill> waterBillList = dataParser.parse(getClass().getClassLoader().getResource("Tariff_20220331.csv"));
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(waterBillList.size()).isEqualTo(303);
            softly.assertThat(waterBillList.get(0).getNo()).isEqualTo(1);
            softly.assertThat(waterBillList.get(302).getNo()).isEqualTo(350);
            softly.assertAll();
        });
    }

    @Test
    void parse_exception() {
        URL fail = getClass().getClassLoader().getResource("fail");
        assertThatThrownBy(() -> dataParser.parse(fail)).isInstanceOf(RuntimeException.class);
    }
}