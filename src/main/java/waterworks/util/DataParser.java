package waterworks.util;

import waterworks.domain.WaterSupplyTariff;

import java.net.URL;
import java.util.List;

public interface DataParser {
    List<WaterSupplyTariff> parse(URL path);
}