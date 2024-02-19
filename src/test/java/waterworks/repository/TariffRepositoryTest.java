package waterworks.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import waterworks.config.BeanConfig;
import waterworks.domain.WaterBill;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(BeanConfig.class)
class TariffRepositoryTest {
    @Autowired
    TariffRepository tariffRepository;

    List<WaterBill> expectedWaterBills;

    @BeforeEach
    void setUp() {
        expectedWaterBills = List.of(
                new WaterBill(216, "고령군", "공업용", 2, 201, 999999, 370, null, null),
                new WaterBill(278, "통영시", "원정수판매용", 1, 1, 9999999, 413, null, null),
                new WaterBill(121, "금산군", "정수용", 1, 1, 999999, 433, null, null),
                new WaterBill(140, "나주시", "임시용(상)", 1, 1, 999999, 433, null, null),
                new WaterBill(153, "장흥군", "임시용(상)", 1, 1, 999999, 433, null, null)
        );
    }

    @Test
    void csvLoadAndfindTop5ByUsage() {
        tariffRepository.load(getClass().getClassLoader().getResource("Tariff_20220331.csv"));
        List<WaterBill> actualWaterBills = tariffRepository.findTop5ByUsage(1000);
        assertThat(actualWaterBills).isEqualTo(expectedWaterBills);
    }

    @Test
    void jsonLoadAndfindTop5ByUsage() {
        tariffRepository.load(getClass().getClassLoader().getResource("Tariff_20220331.json"));
        List<WaterBill> actualWaterBills = tariffRepository.findTop5ByUsage(1000);
        assertThat(actualWaterBills).isEqualTo(expectedWaterBills);
    }

    @Test
    void loadFail() {
        assertThatThrownBy(() -> tariffRepository.load(getClass().getClassLoader().getResource("Tariff_20220331.txt")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("지원하지 않는 파일 형식입니다.");
    }
}