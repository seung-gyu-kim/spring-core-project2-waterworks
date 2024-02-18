package waterworks.util.impl;

import org.springframework.stereotype.Component;
import waterworks.domain.WaterBill;
import waterworks.util.DataParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvDataParser implements DataParser {
    @Override
    public List<WaterBill> parse(URL path) {
        List<WaterBill> waterBillList = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new InputStreamReader(path.openStream()))) {
            br.readLine();

            String line;
            while((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                waterBillList.add(WaterBill.builder()
                        .no(Integer.parseInt(tokens[0]))
                        .city(tokens[1].strip())
                        .sector(tokens[2].strip())
                        .step(Integer.parseInt(tokens[3]))
                        .unitStart(Integer.parseInt(tokens[4]))
                        .unitEnd(Integer.parseInt(tokens[5]))
                        .unitPrice(Integer.parseInt(tokens[6]))
                        .build());
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return waterBillList;
    }
/*@Override
    public List<WaterBill> parse(URL path) {
        CsvMapper csvMapper = new CsvMapper();
        try(MappingIterator<WaterBill> mappingIterator = csvMapper.readerFor(WaterBill.class).with(getSchema()).readValues(path)) {
            List<WaterBill> waterBills = mappingIterator.readAll();
            waterBills.forEach(wb -> {
                wb.setCity(wb.getCity().strip());
                wb.setSector(wb.getSector().strip());
            });
            return waterBills;
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
    }*/
}
