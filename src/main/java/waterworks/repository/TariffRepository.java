package waterworks.repository;

import waterworks.domain.WaterBill;

import java.net.URL;
import java.util.List;

public interface TariffRepository {
    public void load();

    // 사용량에 따른 요금 찾기(물 사용량)
    public List<WaterBill> findTop5ByUsage(int usage);
}
