public class ConsoleDispatchStrategy implements DispatchStrategy{
    String message;
    public ConsoleDispatchStrategy(String message){
        this.message=message;
    }
    @Override
    public void dispatchAlert() {
        System.out.println("[INFO] AlertingService: Dispatching to Console");
        System.out.println("[WARN] Alert: `"+message+"`");
    }
}
