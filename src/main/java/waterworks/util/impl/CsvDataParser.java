package waterworks.util.impl;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import waterworks.domain.WaterSupplyTariff;
import waterworks.util.DataParser;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class CsvDataParser implements DataParser {
    @Override
    public List<WaterSupplyTariff> parse(URL path) {
        CsvMapper csvMapper = new CsvMapper();
        try(MappingIterator<WaterSupplyTariff> mappingIterator = csvMapper.readerFor(WaterSupplyTariff.class).with(getSchema()).readValues(path)) {
            return mappingIterator.readAll();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CsvSchema getSchema() {
        return CsvSchema.builder()
                .addColumn("no")
                .addColumn("name")
                .addColumn("type")
                .addColumn("step")
                .addColumn("unitStart")
                .addColumn("unitEnd")
                .addColumn("unitPrice")
                .addColumn("basicPrice")
                .build().withHeader();
    }
}
