package waterworks.service;

import waterworks.domain.WaterBill;

import java.util.List;

public interface WaterUsageFeeService {
    public List<WaterBill> getBillList(int usage);
}
