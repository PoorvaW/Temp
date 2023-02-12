package AlertingService;

/*
The AlertConfig interface provides an abstraction for the different types of alert configurations that can be implemented at any point of time.
 */
public interface AlertConfig {
    public void processEvent();
    public String getClient();
    public String getEventType();
}
