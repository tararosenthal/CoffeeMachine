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
            try {
                switch (MachineState.valueOf(scanner.nextLine().trim().toUpperCase())) {
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
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input");
            }
        }
    }

    public static void buyCoffee(CoffeeMachine coffeeMachine) {
        String temp = "";

        try {
            do {
                System.out.print(buyString);
                temp = scanner.nextLine().trim().toLowerCase();
            } while(!temp.equals("back") && !temp.equals("1") && !temp.equals("2") && !temp.equals("3"));
        } catch (Exception e) {
            System.out.println("Invalid input");
        }

        switch (temp) {
            case "back":
                return;
            case "1":
                currentCoffee = Coffee.ESPRESSO;
                break;
            case "2":
                currentCoffee = Coffee.LATTE;
                break;
            case "3":
                currentCoffee = Coffee.CAPPUCCINO;
                break;
        }

        if (coffeeMachine.checkSupplies(currentCoffee) == null) {
            System.out.print(enoughString);
            coffeeMachine.makeCoffee(currentCoffee);
        } else {
            System.out.printf("Sorry, not enough %s!\n", coffeeMachine.checkSupplies(currentCoffee));
        }
    }

    public static void fillMachine(CoffeeMachine coffeeMachine) {
        String temp;
        System.out.println();

        for (Supplies s : Supplies.values()) {
            try {
                do {
                    switch (s) {
                        case WATER:
                        case MILK:
                            System.out.printf("Write how many ml of %s you want to add:\n" + "> ", s);
                            break;
                        case COFFEE_BEANS:
                            System.out.printf("Write how many grams of %s you want to add:\n" + "> ", s);
                            break;
                        case CUPS:
                            System.out.printf("Write how many disposable %s of coffee you want to add:\n" + "> ", s);
                            break;
                    }
                    temp = scanner.nextLine();
                } while(!temp.chars().allMatch(Character::isDigit) || temp.length() > 9);
                coffeeMachine.addSupplies(s, Integer.parseInt(temp));
            } catch (Exception e) {
                System.out.print("Invalid input");
            }
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