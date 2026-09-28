class LibraryMember {

    private String membershipId;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMember(String membershipId, String branchCode,
                         double finesOwed, String displayName) {

        membershipId = membershipId.trim();

        if (membershipId.isEmpty() || membershipId.length() < 4) {
            throw new IllegalArgumentException("Invalid membership ID");
        }

        this.membershipId = membershipId;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }

    static String classifyAccess(String fieldModifier,
                                 String accessorContext) {

        if (fieldModifier.equals("private")) {

            if (accessorContext.equals("SAME_CLASS")) {
                return "ALLOWED";
            }
            return "DENIED";
        }

        if (fieldModifier.equals("default")) {

            if (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            }
            return "DENIED";
        }

        if (fieldModifier.equals("protected")) {

            if (accessorContext.equals("DIFFERENT_PACKAGE")) {
                return "DENIED";
            }
            return "ALLOWED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {

        String[] modifiers = {
                "private", "default", "protected", "public"
        };

        String result = "";

        for (int i = 0; i < modifiers.length; i++) {

            int allowed = 0;
            int denied = 0;

            for (int j = 0; j < attempts.length; j++) {

                if (attempts[j][0].equals(modifiers[i])) {

                    String access =
                            classifyAccess(attempts[j][0], attempts[j][1]);

                    if (access.equals("ALLOWED")) {
                        allowed++;
                    }
                    else {
                        denied++;
                    }
                }
            }

            if (!result.isEmpty()) {
                result += " | ";
            }

            result += modifiers[i] + ": " +
                    allowed + " allowed / " +
                    denied + " denied";
        }

        return result;
    }
}


public class LibraryTest {

    public static void main(String[] args) {

        System.out.println(
                LibraryMember.classifyAccess(
                        "private", "SAME_CLASS"
                )
        );

        System.out.println(
                LibraryMember.classifyAccess(
                        "protected", "DIFFERENT_PACKAGE"
                )
        );

        String[][] attempts = {
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
                LibraryMember.summarizeByModifier(attempts)
        );

        try {
            LibraryMember member =
                    new LibraryMember(
                            "LB9",
                            "BR1",
                            0,
                            "Priya Nair"
                    );
        }
        catch (IllegalArgumentException e) {
            System.out.println("Construction rejected");
        }

        LibraryMember member2 =
                new LibraryMember(
                        "LB94",
                        "BR1",
                        0,
                        "Priya Nair"
                );

        System.out.println("Construction successful");
    }
}