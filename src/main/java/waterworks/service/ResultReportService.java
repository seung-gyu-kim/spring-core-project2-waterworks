package waterworks.service;

import waterworks.domain.WaterBill;

import java.util.List;

public interface ResultReportService {
    void report(List<WaterBill> data);
}
