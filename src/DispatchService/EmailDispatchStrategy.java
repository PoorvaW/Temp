package DispatchService;

/*
EmailDispatchStrategy implements the logic for dispatching an alert via Email.
 */
public class EmailDispatchStrategy implements DispatchStrategy{
    String subject;

    public EmailDispatchStrategy(String subject) {
        this.subject = subject;
    }

    @Override
    public void dispatchAlert() {
        System.out.println("[INFO] AlertingService: Dispatching an Email");
    }
}
