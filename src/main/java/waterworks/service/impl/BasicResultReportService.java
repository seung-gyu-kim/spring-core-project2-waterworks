package waterworks.service.impl;

import org.springframework.stereotype.Service;
import waterworks.domain.WaterBill;
import waterworks.service.ResultReportService;

import java.util.List;

@Service
public class BasicResultReportService implements ResultReportService {
    @Override
    public void report(List<WaterBill> data) {
        for(WaterBill r : data) {
            System.out.println(r);
        }
    }
}
