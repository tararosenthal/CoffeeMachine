package machine;

public enum Supplies {
    WATER, MILK, COFFEE_BEANS, CUPS;

    @Override
    public String toString() {
        return this.name().toLowerCase().replace('_', ' ');
    }
}
