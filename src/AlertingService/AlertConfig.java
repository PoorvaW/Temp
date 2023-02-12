package AlertingService;

public interface AlertConfig {
    public void processEvent();
    public String getClient();
    public String getEventType();
}
