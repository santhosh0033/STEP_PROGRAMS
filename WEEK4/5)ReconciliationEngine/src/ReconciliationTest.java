class DeliveryAccount {

    static double minimumSurgePercent;

    static {
        minimumSurgePercent = 1.0;
    }

    String studentId;
    double orderValue;

    public DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0);
    }

    double calculateSurgeFee(int delayMinutes) {

        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double fee = 0;

        int firstTier = Math.min(delayMinutes, 5);
        fee += firstTier * orderValue * 0.005;

        if (delayMinutes > 5) {
            int secondTier = Math.min(delayMinutes - 5, 10);
            fee += secondTier * orderValue * 0.01;
        }

        if (delayMinutes > 15) {
            int thirdTier = delayMinutes - 15;
            fee += thirdTier * orderValue * 0.02;
        }

        double minimumFee =
                orderValue * minimumSurgePercent / 100;

        if (fee < minimumFee) {
            fee = minimumFee;
        }

        return fee;
    }

    void processAccount(DeliveryAccount account,
                        double amount,
                        int delayMinutes) {

        if (account instanceof PremiumAccount) {
            PremiumAccount premium = (PremiumAccount) account;

            double fee = premium.calculateSurgeFee(delayMinutes);

            System.out.println(
                    premium.studentId + " Premium Surge Fee: Rs " + fee
            );
        }
        else {
            double fee = account.calculateSurgeFee(delayMinutes);

            System.out.println(
                    account.studentId + " Regular Surge Fee: Rs " + fee
            );
        }
    }

    static void processBatch(DeliveryAccount[] accounts,
                             double[] amounts,
                             int[] delayMinutesArray) {

        int length = Math.min(
                accounts.length,
                Math.min(amounts.length, delayMinutesArray.length)
        );

        int processed = 0;
        int nullSkipped = 0;
        int premiumCount = 0;
        int regularCount = 0;
        double grandTotal = 0;

        for (int i = 0; i < length; i++) {

            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            try {

                double fee =
                        accounts[i].calculateSurgeFee(delayMinutesArray[i]);

                if (accounts[i] instanceof PremiumAccount) {
                    premiumCount++;
                }
                else {
                    regularCount++;
                }

                grandTotal += fee;
                processed++;

            }
            catch (Exception e) {
                System.out.println(
                        "Error processing " + accounts[i].studentId
                );
            }
        }

        System.out.println();
        System.out.println(
                processed + " processed | " +
                        nullSkipped + " null skipped | " +
                        premiumCount + " premium | " +
                        regularCount + " regular | " +
                        "grand total surge fees = Rs " + grandTotal
        );
    }
}


class PremiumAccount extends DeliveryAccount {

    PremiumAccount(String studentId, double orderValue) {
        super(studentId, orderValue);
    }

    PremiumAccount(String studentId) {
        super(studentId);
    }

    @Override
    final double calculateSurgeFee(int delayMinutes) {

        double fee = super.calculateSurgeFee(delayMinutes);

        return fee * 0.5;
    }
}


public class ReconciliationTest {

    public static void main(String[] args) {

        DeliveryAccount[] accounts = {
                new PremiumAccount("STU001", 500),
                null,
                new DeliveryAccount("STU002", 300)
        };

        double[] amounts = {
                500, 400, 300
        };

        int[] delayMinutesArray = {
                10, 5, 0
        };

        DeliveryAccount.processBatch(
                accounts,
                amounts,
                delayMinutesArray
        );
    }
}