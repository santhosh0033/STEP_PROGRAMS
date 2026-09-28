class FoodOrder {

    private String studentName;
    private String dishName;
    private boolean delivered;

    public FoodOrder(String studentName, String dishName) {

        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid student name");
        }

        if (dishName == null || dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid dish name");
        }

        this.studentName = studentName;
        this.dishName = dishName;
        this.delivered = false;
    }

    void markDelivered() {

        if (!delivered) {
            delivered = true;
            System.out.println("Order marked as delivered.");
        }
        else {
            System.out.println("Order was already delivered.");
        }
    }

    static void processBatch(String[][] rawOrders) {

        int valid = 0;
        int rejected = 0;

        for (int i = 0; i < rawOrders.length; i++) {

            try {
                FoodOrder order =
                        new FoodOrder(rawOrders[i][0], rawOrders[i][1]);

                valid++;
            }
            catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid +
                " | Rejected: " + rejected);
    }
}


public class FoodOrderTest {

    public static void main(String[] args) {

        String[][] rawOrders = {
                {"Ravi", "Paneer Butter Masala"},
                {"", "Chole Bhature"},
                {"Meera", " "},
                {"Divya", "Veg Biryani"}
        };

        FoodOrder.processBatch(rawOrders);

        FoodOrder order =
                new FoodOrder("Ravi", "Paneer Butter Masala");

        order.markDelivered();
        order.markDelivered();
    }
}
