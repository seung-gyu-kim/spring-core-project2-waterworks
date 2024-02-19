package waterworks.util.impl;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import waterworks.config.BeanConfig;
import waterworks.domain.WaterBill;

import java.net.URL;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringJUnitConfig(BeanConfig.class)
class CsvDataParserTest {
    @Autowired
    CsvDataParser dataParser;

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