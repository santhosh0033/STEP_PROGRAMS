import java.util.Arrays;

class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    static int totalReceipts;

    static {
        totalReceipts = 0;
    }

    public LoanReceipt(String memberId, String[] bookIds) {

        for (String id : bookIds) {
            if (!isValidBookId(id)) {
                throw new IllegalArgumentException("Invalid Book ID");
            }
        }

        this.memberId = memberId;
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
        totalReceipts++;
    }

    private static boolean isValidBookId(String id) {

        if (id == null || id.length() != 6) {
            return false;
        }

        if (!id.startsWith("BK-")) {
            return false;
        }

        for (int i = 3; i < 6; i++) {
            if (!Character.isDigit(id.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public String[] getBookIds() {
        return Arrays.copyOf(bookIds, bookIds.length);
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {

        if (!isValidBookId(newId)) {
            throw new IllegalArgumentException("Invalid Book ID");
        }

        String[] newBookIds =
                Arrays.copyOf(bookIds, bookIds.length);

        newBookIds[index] = newId;

        return new LoanReceipt(memberId, newBookIds);
    }
}


final class ReferenceOnlyLoanReceipt extends LoanReceipt {

    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId,
                                    String[] bookIds,
                                    String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }
}


public class LoanReceiptTest {

    static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceipt receipt : receipts) {

            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            }
            else {
                regular++;
            }
        }

        return processed + " processed | " +
                nullSkipped + " null skipped | " +
                referenceOnly + " reference-only | " +
                regular + " regular";
    }


    public static void main(String[] args) {

        try {
            LoanReceipt r1 =
                    new LoanReceipt(
                            "LIB-8841",
                            new String[]{"BK-100", "bad"}
                    );
        }
        catch (IllegalArgumentException e) {
            System.out.println("Construction rejected");
        }

        LoanReceipt r2 =
                new LoanReceipt(
                        "LIB-8841",
                        new String[]{"BK-100", "BK-101"}
                );

        String[] ids = r2.getBookIds();

        ids[0] = "HACKED";

        System.out.println(r2.getBookIds()[0]);

        LoanReceipt[] receipts = {
                new ReferenceOnlyLoanReceipt(
                        "LIB-001",
                        new String[]{"BK-200"},
                        "Reading Room 3"
                ),
                null,
                new LoanReceipt(
                        "LIB-002",
                        new String[]{"BK-201"}
                )
        };

        System.out.println(
                processNightlyCirculation(receipts)
        );
    }
}