import java.util.List;

public class SimpleCountAlertConfig implements AlertConfig{
    private int count;
    private String client;
    private String eventType;
    private List<DispatchStrategy> dispatchStrategyList;
    private int currCount;

    public SimpleCountAlertConfig(int count, String client, String eventType, List<DispatchStrategy> dispatchStrategyList) {
        this.count = count;
        this.client = client;
        this.eventType = eventType;
        this.dispatchStrategyList = dispatchStrategyList;
        this.currCount = 0;
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
        System.out.println();
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
}
