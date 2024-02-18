package waterworks.util;

import waterworks.domain.WaterBill;

import java.net.URL;
import java.util.List;

public interface DataParser {
    List<WaterBill> parse(URL path);
}