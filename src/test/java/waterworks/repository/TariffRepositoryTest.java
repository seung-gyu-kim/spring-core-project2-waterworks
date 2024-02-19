package waterworks.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import waterworks.config.BeanConfig;
import waterworks.domain.WaterBill;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringJUnitConfig(BeanConfig.class)
class TariffRepositoryTest {
    @Autowired
    TariffRepository tariffRepository;

    @Test
    void loadAndfindTop5ByUsage() {
        tariffRepository.load();
        List<WaterBill> actualWaterBills = tariffRepository.findTop5ByUsage(1000);
        List<WaterBill> expectedWaterBills = List.of(
                new WaterBill(216, "고령군", "공업용", 2, 201, 999999, 370, null, null),
                new WaterBill(278, "통영시", "원정수판매용", 1, 1, 9999999, 413, null, null),
                new WaterBill(121, "금산군", "정수용", 1, 1, 999999, 433, null, null),
                new WaterBill(140, "나주시", "임시용(상)", 1, 1, 999999, 433, null, null),
                new WaterBill(153, "장흥군", "임시용(상)", 1, 1, 999999, 433, null, null)
        );
        assertThat(actualWaterBills).isEqualTo(expectedWaterBills);
    }
}