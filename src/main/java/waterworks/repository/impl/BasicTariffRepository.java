package waterworks.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import waterworks.domain.WaterBill;
import waterworks.repository.TariffRepository;
import waterworks.util.DataParser;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BasicTariffRepository implements TariffRepository {
    private final DataParser dataParser;
    private List<WaterBill> tariffs;

    @Override
    public void load() {
        tariffs = dataParser.parse(getClass().getClassLoader().getResource("Tariff_20220331.csv"));
    }

    @Override
    public List<WaterBill> findTop5ByUsage(int usage) {
        return tariffs.stream()
                .filter(t -> t.getUnitStart() <= usage && usage <= t.getUnitEnd())
                .sorted(Comparator.comparing(WaterBill::getUnitPrice))
                .limit(5)
                .collect(Collectors.toList());
    }
}
