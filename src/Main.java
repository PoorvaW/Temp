import AlertingService.AlertConfig;
import AlertingService.AlertConfigFactory;
import AlertingService.AlertConfigType;
import DispatchService.ConsoleDispatchStrategy;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            MonitoringService monitoringService = MonitoringService.getInstance();
            AlertConfig simpleCountAlertConfig = AlertConfigFactory.getAlertConfig(AlertConfigType.SIMPLE_COUNT, 3, "abc", "abc", Arrays.asList(
                    new ConsoleDispatchStrategy("issue in user service")));
            monitoringService.addClientConfig(simpleCountAlertConfig);
            monitoringService.processEvent("abc", "abc");
            monitoringService.processEvent("abc", "abc");
            monitoringService.processEvent("abc", "abc");
            monitoringService.processEvent("abc", "abc");

            AlertConfig slidingWindowAlertConfig = AlertConfigFactory.getAlertConfig(AlertConfigType.SLIDING_WINDOW, 2, "pqr", "pqr", Arrays.asList(
                    new ConsoleDispatchStrategy("issue in user service")), 1);

            monitoringService.addClientConfig(slidingWindowAlertConfig);
            monitoringService.processEvent("pqr", "pqr");
            monitoringService.processEvent("pqr", "pqr");
            monitoringService.processEvent("pqr", "pqr");
            Thread.sleep(2000);
            monitoringService.processEvent("pqr", "pqr");

            AlertConfig tumblingWindowAlertConfig = AlertConfigFactory.getAlertConfig(AlertConfigType.TUMBLING_WINDOW,2,"xyz", "xyz", Arrays.asList(new ConsoleDispatchStrategy("incorrect")),5);
            monitoringService.addClientConfig(tumblingWindowAlertConfig);
            monitoringService.processEvent("xyz","xyz");
            monitoringService.processEvent("xyz","xyz");
            monitoringService.processEvent("xyz","xyz");
            Thread.sleep(5000);
            monitoringService.processEvent("xyz","xyz");


        } catch (Exception e){
            System.out.println(e);
        }
    }
}