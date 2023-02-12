package AlertingService;

import AlertingService.AlertConfig;
import DispatchService.DispatchStrategy;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TumblingWindowAlertConfig implements AlertConfig {
    private int count;
    private int windowSizeInSecs;
    private String client;
    private String eventType;
    private List<DispatchStrategy> dispatchStrategyList;
    private int currCount;

    private void startWindow(){
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(new TumblingWindowLogger(), 0, windowSizeInSecs, TimeUnit.SECONDS);
    }

    public TumblingWindowAlertConfig(int count, int windowSizeInSecs, String client, String eventType, List<DispatchStrategy> dispatchStrategyList) {
        this.count = count;
        this.windowSizeInSecs = windowSizeInSecs;
        this.client = client;
        this.eventType = eventType;
        this.dispatchStrategyList = dispatchStrategyList;
        this.startWindow();
    }

    @Override
    public String getClient() {
        return client;
    }

    @Override
    public String getEventType() {
        return eventType;
    }
    @Override
    public void processEvent() {
        currCount++;
        if(isThresholdBreached()){
            System.out.println("[INFO] MonitoringService: " +client+" "+eventType+" threshold breached");
            for(DispatchStrategy ds: dispatchStrategyList){
                ds.dispatchAlert();
            }
        }
    }

    private boolean isThresholdBreached() {
        return currCount>=count;
    }

    class TumblingWindowLogger implements Runnable {
        @Override
        public void run() {
            currCount=0;
            System.out.println("[INFO] MonitoringService: "+client+" "+eventType +" TUMBLING_WINDOW starts");
            try {
                Thread.sleep((windowSizeInSecs - 1) * 1000);
            } catch (InterruptedException e) {
                System.out.println("[ERROR] WindowMonitor: InterruptedException occurred");
            }
            currCount=0;
            System.out.println("[INFO] MonitoringService: "+client+" "+eventType +" TUMBLING_WINDOW ends");
        }
    }
}
