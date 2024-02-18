package waterworks;

import waterworks.domain.WaterBill;
import waterworks.repository.TariffRepository;
import waterworks.repository.impl.BasicTariffRepository;
import waterworks.service.ResultReportService;
import waterworks.service.WaterUsageFeeService;
import waterworks.service.impl.BasicResultReportService;
import waterworks.service.impl.BasicWaterUsageFeeService;
import waterworks.util.impl.CsvDataParser;

import java.util.List;
import java.util.Scanner;

public class BootStrap {
    public static void main(String[] args) {
        TariffRepository basicTariffRepository = new BasicTariffRepository(new CsvDataParser());
        basicTariffRepository.load();

        WaterUsageFeeService waterUsageFeeService = new BasicWaterUsageFeeService(basicTariffRepository);
        ResultReportService resultReportService = new BasicResultReportService();

        try(Scanner sc = new Scanner(System.in)) {
            while(true) {
                System.out.print("물 사용량을 입력하세요(숫자 외 입력시 종료): ");
                int usage = sc.nextInt();
                List<WaterBill> waterBills = waterUsageFeeService.getBillList(usage);
                resultReportService.report(waterBills);
            }
        } catch(Exception e) {
            System.out.println("종료합니다.");
        }
    }
}