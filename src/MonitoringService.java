import AlertingService.AlertConfig;

import java.util.ArrayList;
import java.util.List;

/*
MonitoringService maintains all the client configurations.
Whenever an event occurs it is processed by invoking the appropriate method for the given configuration.
 */
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
