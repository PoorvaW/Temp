package AlertingService;

import DispatchService.DispatchStrategy;

import java.util.LinkedList;
import java.util.List;

/*
SlidingWindowAlertConfig implements the logic for sliding window type alert.
 */
public class SlidingWindowAlertConfig implements AlertConfig {
    private int count;
    private int windowSizeInSecs;
    private String client;
    private String eventType;
    private List<DispatchStrategy> dispatchStrategyList;
    private LinkedList<Long> eventTimestamps;

    public SlidingWindowAlertConfig(int count, int windowSizeInSecs, String client, String eventType, List<DispatchStrategy> dispatchStrategyList) {
        this.count = count;
        this.windowSizeInSecs = windowSizeInSecs;
        this.client = client;
        this.eventType = eventType;
        this.dispatchStrategyList = dispatchStrategyList;
        this.eventTimestamps = new LinkedList<>();
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
        long currentTime = System.currentTimeMillis();
        eventTimestamps.add(currentTime);
        if(isThresholdBreached(currentTime)){
            System.out.println("[INFO] MonitoringService: " +client+" "+eventType+" threshold breached");
            for(DispatchStrategy ds: dispatchStrategyList){
                ds.dispatchAlert();
            }
        }
    }

    private boolean isThresholdBreached(long currentTime) {
        long lowerBound = currentTime - windowSizeInSecs * 1000;
        while (!eventTimestamps.isEmpty() && eventTimestamps.peekFirst() < lowerBound) {
            eventTimestamps.removeFirst();
        }
        return eventTimestamps.size()>=count;
    }
}
