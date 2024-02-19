package waterworks.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import waterworks.domain.WaterBill;
import waterworks.repository.TariffRepository;
import waterworks.util.DataParser;
import waterworks.util.impl.CsvDataParser;
import waterworks.util.impl.JsonDataParser;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BasicTariffRepository implements TariffRepository {
    private final CsvDataParser csvDataParser;
    private final JsonDataParser jsonDataParser;
    private List<WaterBill> tariffs;

    @Override
    public void load(URL src) {
        int lastIndex = src.toString().lastIndexOf('.');
        String ext = src.toString().substring(lastIndex + 1);
        if("csv".equals(ext)) {
            tariffs = csvDataParser.parse(src);
        } else if("json".equals(ext)) {
            tariffs = jsonDataParser.parse(src);
        } else {
            throw new IllegalArgumentException("지원하지 않는 파일 형식입니다.");
        }
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
