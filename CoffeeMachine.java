package machine;

public class CoffeeMachine {
    private int water;
    private int milk;
    private int cups;
    private int beans;
    private int money;

    public CoffeeMachine(int water, int milk, int cups, int beans, int money) {
        this.water = water;
        this.milk = milk;
        this.cups = cups;
        this.beans = beans;
        this.money = money;
    }

    public int getWater() {
        return this.water;
    }

    public int getMilk() {
        return this.milk;
    }

    public int getCups() {
        return this.cups;
    }

    public int getBeans() {
        return this.beans;
    }

    public int getMoney() {
        return this.money;
    }

    public Supplies checkSupplies(Coffee coffee) {
        if (this.cups < 1) {
            return Supplies.CUPS;
        } else if (this.water < coffee.getWater()) {
            return Supplies.WATER;
        } else if (this.milk < coffee.getMilk()) {
            return Supplies.MILK;
        } else if (this.beans < coffee.getBeans()) {
            return Supplies.COFFEE_BEANS;
        }
        return null;
    }

    public void makeCoffee(Coffee coffee) {
        this.cups--;
        this.water -= coffee.getWater();
        this.milk -= coffee.getMilk();
        this.beans -= coffee.getBeans();
        this.money += coffee.getCost();
    }

    public void addSupplies(Supplies supplies, Integer amount) {
        switch (supplies) {
            case WATER:
                this.water += amount;
                break;
            case MILK:
                this.milk += amount;
                break;
            case COFFEE_BEANS:
                this.beans += amount;
                break;
            case CUPS:
                this.cups += amount;
                break;
        }
    }

    public int takeMoney() {
      int giveMoney = this.money;
      this.money = 0;
      return giveMoney;
    }
}

