package waterworks.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import waterworks.domain.WaterBill;
import waterworks.repository.TariffRepository;
import waterworks.service.impl.BasicWaterUsageFeeService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WaterUsageFeeServiceTest {
    @InjectMocks
    private BasicWaterUsageFeeService waterUsageFeeService;
    @Mock
    private TariffRepository tariffRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getBillList() {
        // given
        Mockito.when(tariffRepository.findTop5ByUsage(Mockito.anyInt())).thenReturn(List.of(
                new WaterBill(216, "고령군", "공업용", 2, 201, 999999, 370, null, null),
                new WaterBill(278, "통영시", "원정수판매용", 1, 1, 9999999, 413, null, null),
                new WaterBill(121, "금산군", "정수용", 1, 1, 999999, 433, null, null),
                new WaterBill(140, "나주시", "임시용(상)", 1, 1, 999999, 433, null, null),
                new WaterBill(153, "장흥군", "임시용(상)", 1, 1, 999999, 433, null, null)
        ));

        // when
        List<WaterBill> actualWaterBills = waterUsageFeeService.getBillList(1000);

        // then
        List<WaterBill> expectedWaterBills = List.of(
                new WaterBill(216, "고령군", "공업용", 2, 201, 999999, 370, null, 370000),
                new WaterBill(278, "통영시", "원정수판매용", 1, 1, 9999999, 413, null, 413000),
                new WaterBill(121, "금산군", "정수용", 1, 1, 999999, 433, null, 433000),
                new WaterBill(140, "나주시", "임시용(상)", 1, 1, 999999, 433, null, 433000),
                new WaterBill(153, "장흥군", "임시용(상)", 1, 1, 999999, 433, null, 433000)
        );
        assertThat(actualWaterBills).isEqualTo(expectedWaterBills);
    }
}