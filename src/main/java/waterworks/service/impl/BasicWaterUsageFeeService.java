package waterworks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import waterworks.domain.WaterBill;
import waterworks.repository.TariffRepository;
import waterworks.service.WaterUsageFeeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasicWaterUsageFeeService implements WaterUsageFeeService {
    private final TariffRepository tariffRepository;
    @Override
    public List<WaterBill> getBillList(int usage) {
        List<WaterBill> result = tariffRepository.findTop5ByUsage(usage);
        result.forEach(wb -> wb.setBillTotal(wb.getUnitPrice() * usage));
        return result;
    }
}
