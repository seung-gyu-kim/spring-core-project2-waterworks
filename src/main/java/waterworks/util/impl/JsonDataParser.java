package waterworks.util.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.stereotype.Component;
import waterworks.domain.WaterBill;
import waterworks.util.DataParser;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Component
public class JsonDataParser implements DataParser {
    @Override
    public List<WaterBill> parse(URL src) {
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        try {
            CollectionType valueType = mapper.getTypeFactory().constructCollectionType(List.class, WaterBill.class);
            List<WaterBill> waterBills = mapper.readValue(src, valueType);
            return waterBills;
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
