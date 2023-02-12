import java.util.ArrayList;
import java.util.List;

public class MonitoringService {
    private static MonitoringService instance;
    private List<AlertConfig> clientConfigs;

    private MonitoringService()
    {
        this.clientConfigs = new ArrayList<>();
    }
    synchronized public static MonitoringService getInstance()
    {
        if (instance == null)
        {
            // if instance is null, initialize
            instance = new MonitoringService();
        }
        return instance;
    }

    public void addClientConfig(AlertConfig config){
        clientConfigs.add(config);
    }

    public void processEvent(String client, String eventType){
        for(AlertConfig clientConfig: clientConfigs){
            if(clientConfig.getClient().equals(client) && clientConfig.getEventType().equals(eventType)){
                clientConfig.processEvent();
            }
        }

    }
}
