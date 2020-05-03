package bikerental;

public class UnknownAction implements Action {
    @Override
    public void performAction() {
        System.out.println("Unknown action, sorry");
    }
}
