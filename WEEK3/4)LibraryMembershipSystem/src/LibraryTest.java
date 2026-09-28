class BrokenLibraryMember {

    static String name;
    static String memberId;
    static int booksIssued;

    BrokenLibraryMember(String name, String memberId, int booksIssued) {
        BrokenLibraryMember.name = name;
        BrokenLibraryMember.memberId = memberId;
        BrokenLibraryMember.booksIssued = booksIssued;
    }

    void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }
}


class LibraryMember {

    String name;
    String memberId;
    int booksIssued;

    static String libraryName = "City Library";
    static int memberCount = 0;

    LibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;

        memberCount++;
        this.memberId = "LM-" + (1000 + memberCount);
    }

    void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    static void printTotalMembers() {
        System.out.println("Total members: " + memberCount);
    }
}


public class LibraryTest {

    public static void main(String[] args) {

        System.out.println("Broken version:");

        BrokenLibraryMember m1 =
                new BrokenLibraryMember("Aditi", "LM-1001", 2);

        BrokenLibraryMember m2 =
                new BrokenLibraryMember("Rohan", "LM-1002", 3);

        m1.printMemberCard();
        m2.printMemberCard();

        System.out.println();

        System.out.println("Fixed version:");

        LibraryMember l1 =
                new LibraryMember("Aditi", 2);

        LibraryMember l2 =
                new LibraryMember("Rohan", 3);

        l1.printMemberCard();
        l2.printMemberCard();

        LibraryMember.printTotalMembers();
    }
}