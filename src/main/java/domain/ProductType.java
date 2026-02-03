package domain;

public enum ProductType {
    PERSONAL_LOAN(1),
    REAL_ESTATE(2),
    VEHICLE(3);

    private final int id;

    ProductType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}