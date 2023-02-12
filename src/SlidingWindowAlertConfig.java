import java.util.ArrayList;
import java.util.List;
public class SlidingWindowAlertConfig implements AlertConfig{
    private int count;
    private int windowSizeInSecs;
    private String client;
    private String eventType;
    private List<DispatchStrategy> dispatchStrategyList;
    private List<Long> eventTimestamps;

    public SlidingWindowAlertConfig(int count, int windowSizeInSecs, String client, String eventType, List<DispatchStrategy> dispatchStrategyList) {
        this.count = count;
        this.windowSizeInSecs = windowSizeInSecs;
        this.client = client;
        this.eventType = eventType;
        this.dispatchStrategyList = dispatchStrategyList;
        this.eventTimestamps = new ArrayList<>();
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
        int currCount = 0;
        List<Long> toRemove = new ArrayList<>();

        for (long timestamp : eventTimestamps) {
            if ((currentTime - timestamp) < (windowSizeInSecs*1000)) {
                currCount++;
            } else {
                toRemove.add(timestamp);
            }
        }
        eventTimestamps.removeAll(toRemove);
        return currCount>=count;
    }
}
