import AlertingService.AlertConfig;
import AlertingService.AlertConfigFactory;
import AlertingService.AlertConfigType;
import DispatchService.ConsoleDispatchStrategy;
import DispatchService.EmailDispatchStrategy;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            MonitoringService monitoringService = MonitoringService.getInstance();
            AlertConfig xPaymentConfig = AlertConfigFactory.getAlertConfig(AlertConfigType.TUMBLING_WINDOW, 4, "X", "PAYMENT_EXCEPTION", Arrays.asList(
                    new ConsoleDispatchStrategy("issue in payment"), new EmailDispatchStrategy("payment exception threshold breached")),10);
            monitoringService.addClientConfig(xPaymentConfig);
            monitoringService.processEvent("X", "PAYMENT_EXCEPTION");
            monitoringService.processEvent("X", "PAYMENT_EXCEPTION");
            monitoringService.processEvent("X", "PAYMENT_EXCEPTION");
            monitoringService.processEvent("X", "PAYMENT_EXCEPTION");
            Thread.sleep(10000);
            monitoringService.processEvent("X", "PAYMENT_EXCEPTION");



            AlertConfig xUserServiceConfig = AlertConfigFactory.getAlertConfig(AlertConfigType.SLIDING_WINDOW, 2, "X", "USER_SERVICE_EXCEPTION", Arrays.asList(
                    new ConsoleDispatchStrategy("issue in user service")), 2);

            monitoringService.addClientConfig(xUserServiceConfig);
            monitoringService.processEvent("X", "USER_SERVICE_EXCEPTION");
            monitoringService.processEvent("X", "USER_SERVICE_EXCEPTION");
            monitoringService.processEvent("X", "USER_SERVICE_EXCEPTION");
            Thread.sleep(2000);
            monitoringService.processEvent("X", "USER_SERVICE_EXCEPTION");

            AlertConfig xDataServiceConfig = AlertConfigFactory.getAlertConfig(AlertConfigType.SIMPLE_COUNT,3,"X", "DATA_SERVICE_EXCEPTION", Arrays.asList(
                    new ConsoleDispatchStrategy("issue in data service")));
            monitoringService.addClientConfig(xDataServiceConfig);
            monitoringService.processEvent("X","DATA_SERVICE_EXCEPTION");
            monitoringService.processEvent("X","DATA_SERVICE_EXCEPTION");
            monitoringService.processEvent("X","DATA_SERVICE_EXCEPTION");
            monitoringService.processEvent("X","DATA_SERVICE_EXCEPTION");


        } catch (Exception e){
            System.out.println(e);
        }
    }
}