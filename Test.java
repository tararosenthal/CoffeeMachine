package machine;

public class Test {
    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 9, 120, 550);
        RunMachine.processInput(coffeeMachine);
    }
}
