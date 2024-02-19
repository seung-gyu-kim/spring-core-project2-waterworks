package waterworks.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import waterworks.domain.WaterBill;
import waterworks.service.impl.BasicResultReportService;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ResultReportServiceTest {
    @InjectMocks
    private BasicResultReportService resultReportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void report() {
        // given
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // when
        resultReportService.report(List.of(
                new WaterBill(216, "고령군", "공업용", 2, 201, 999999, 370, null, 370000),
                new WaterBill(278, "통영시", "원정수판매용", 1, 1, 9999999, 413, null, 413000),
                new WaterBill(121, "금산군", "정수용", 1, 1, 999999, 433, null, 433000),
                new WaterBill(140, "나주시", "임시용(상)", 1, 1, 999999, 433, null, 433000),
                new WaterBill(153, "장흥군", "임시용(상)", 1, 1, 999999, 433, null, 433000)
        ));

        // then
        assertThat(out.toString()).contains("WaterBill{city='고령군', sector='공업용', unitPrice=370, billTotal=370000}\n" +
                "WaterBill{city='통영시', sector='원정수판매용', unitPrice=413, billTotal=413000}\n" +
                "WaterBill{city='금산군', sector='정수용', unitPrice=433, billTotal=433000}\n" +
                "WaterBill{city='나주시', sector='임시용(상)', unitPrice=433, billTotal=433000}\n" +
                "WaterBill{city='장흥군', sector='임시용(상)', unitPrice=433, billTotal=433000}");
    }
}