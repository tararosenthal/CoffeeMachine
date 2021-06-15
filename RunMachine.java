package machine;

import java.util.Scanner;

public class RunMachine {
    static Coffee currentCoffee;
    static boolean exit = false;
    public static final String chooseString = "\nWrite action (buy, fill, take, remaining, exit):\n" + "> ";
    public static final String buyString = "\nWhat do you want to buy? 1 - espresso, 2 - latte, " +
            "3 - cappuccino, back - to main menu:\n" + "> ";
    public static final String enoughString = "I have enough resources, making you a coffee! \n";
    private static final Scanner scanner = new Scanner(System.in);

    public static void processInput(CoffeeMachine coffeeMachine) {
        while (!exit) {
            System.out.print(chooseString);
                switch (ValidInput.validMachineState(scanner.nextLine())) {
                    case BUY:
                        buyCoffee(coffeeMachine);
                        break;
                    case FILL:
                        fillMachine(coffeeMachine);
                        break;
                    case TAKE:
                        takeMoney(coffeeMachine);
                        break;
                    case REMAINING:
                        printSupplies(coffeeMachine);
                        break;
                    case EXIT:
                        exit = true;
                        break;
                    case INVALID:
                        System.out.println("Invalid input. Try again.");
                        break;
                }
        }
    }

    public static void buyCoffee(CoffeeMachine coffeeMachine) {
       String input;
       currentCoffee = null;

        do {
            System.out.print(buyString);
            input = scanner.nextLine();
            if (ValidInput.isNotBack(input)) {
                currentCoffee = ValidInput.validCoffee(input);
                if (currentCoffee == null) {
                    System.out.println("Invalid input. Try again.");
                } else {
                    makeCoffee(coffeeMachine);
                }
            }
        } while (ValidInput.isNotBack(input) && currentCoffee == null);
    }

    public static void makeCoffee(CoffeeMachine coffeeMachine) {
        if (coffeeMachine.checkSupplies(currentCoffee) == null) {
            System.out.print(enoughString);
            coffeeMachine.makeCoffee(currentCoffee);
        } else {
            System.out.printf("Sorry, not enough %s!\n", coffeeMachine.checkSupplies(currentCoffee));
        }
    }

    public static void fillMachine(CoffeeMachine coffeeMachine) {
        int temp;

        for (Supplies s : Supplies.values()) {
            do {
                switch (s) {
                    case WATER:
                    case MILK:
                        System.out.printf("\nWrite how many ml of %s you want to add:\n" + "> ", s);
                        break;
                    case COFFEE_BEANS:
                        System.out.printf("\nWrite how many grams of %s you want to add:\n" + "> ", s);
                        break;
                    case CUPS:
                        System.out.printf("\nWrite how many disposable %s of coffee you want to add:\n" + "> ", s);
                        break;
                    }
                    temp = ValidInput.validNumber(scanner.nextLine());
                    if (temp < 0) {
                        System.out.println("Please enter a valid number.");
                    }
                } while(temp < 0);
                coffeeMachine.addSupplies(s, temp);
        }
    }

    public static void takeMoney(CoffeeMachine coffeeMachine) {
        System.out.printf("\nI gave you $%d\n", coffeeMachine.takeMoney());
    }

    public static void printSupplies(CoffeeMachine coffeeMachine) {
        System.out.printf("\nThe coffee machine has:\n" +
                        "%d ml of water \n" +
                        "%d ml of milk \n" +
                        "%d g of coffee beans \n" +
                        "%d disposable cups \n" +
                        "$%d of money \n",
                coffeeMachine.getWater(), coffeeMachine.getMilk(), coffeeMachine.getBeans(), coffeeMachine.getCups(), coffeeMachine.getMoney());
    }
}
