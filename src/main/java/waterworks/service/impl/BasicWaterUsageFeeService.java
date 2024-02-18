package waterworks.service.impl;

import lombok.RequiredArgsConstructor;
import waterworks.domain.WaterBill;
import waterworks.repository.TariffRepository;
import waterworks.service.WaterUsageFeeService;

import java.util.List;

@RequiredArgsConstructor
public class BasicWaterUsageFeeService implements WaterUsageFeeService {
    private final TariffRepository tariffRepository;
    @Override
    public List<WaterBill> getBillList(int usage) {
        return tariffRepository.findTop5ByUsage(usage);
    }
}
