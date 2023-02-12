package AlertingService;
import DispatchService.DispatchStrategy;

import java.util.List;

/*
The AlertConfigFactory returns the requested AlertConfig type object.
 */
public class AlertConfigFactory {
    public static AlertConfig getAlertConfig(AlertConfigType type, int count, String client, String eventType, List<DispatchStrategy> dispatchStrategyList, Integer... windowSizeInSecs){
        Integer windowSize = windowSizeInSecs.length > 0 ? windowSizeInSecs[0] : 0;
        if(type==AlertConfigType.SLIDING_WINDOW){
            return new SlidingWindowAlertConfig(count, windowSize, client, eventType, dispatchStrategyList);
        }else if(type==AlertConfigType.TUMBLING_WINDOW){
            return new TumblingWindowAlertConfig(count, windowSize, client, eventType, dispatchStrategyList);
        } else if(type==AlertConfigType.SIMPLE_COUNT){
            return new SimpleCountAlertConfig(count, client, eventType, dispatchStrategyList);
        }else return null;
    }
}
