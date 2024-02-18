package waterworks.util.impl;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Component;
import waterworks.domain.WaterBill;
import waterworks.util.DataParser;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Component
public class CsvDataParser implements DataParser {
    @Override
    public List<WaterBill> parse(URL path) {
        CsvMapper csvMapper = new CsvMapper();
        try(MappingIterator<WaterBill> mappingIterator = csvMapper.readerFor(WaterBill.class).with(getSchema()).readValues(path)) {
            return mappingIterator.readAll();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CsvSchema getSchema() {
        return CsvSchema.builder()
                .addColumn("no")
                .addColumn("city")
                .addColumn("sector")
                .addColumn("step")
                .addColumn("unitStart")
                .addColumn("unitEnd")
                .addColumn("unitPrice")
                .addColumn("basicCost")
                .build().withHeader();
    }
}
