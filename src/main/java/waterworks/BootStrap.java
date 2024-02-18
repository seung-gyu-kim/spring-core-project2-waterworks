package waterworks;

import waterworks.domain.WaterSupplyTariff;
import waterworks.util.impl.CsvDataParser;

import java.util.List;

public class BootStrap {
    public static void main(String[] args) {
        CsvDataParser csvDataParser = new CsvDataParser();
        List<WaterSupplyTariff> tariffs = csvDataParser.parse(BootStrap.class.getClassLoader().getResource("Tariff_20220331.csv"));
        for(WaterSupplyTariff tariff : tariffs) {
            System.out.println(tariff);
        }
    }
}