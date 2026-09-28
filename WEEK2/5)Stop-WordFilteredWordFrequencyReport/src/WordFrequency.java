import java.util.*;

public class WordFrequency {

    static void printFilteredWordFrequency(String feedback) {

        // Stop words
        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};

        // Convert to lowercase
        feedback = feedback.toLowerCase();

        // Remove punctuation
        feedback = feedback.replace(".", "");
        feedback = feedback.replace(",", "");

        // Split into words
        String[] words = feedback.split("\\s+");

        // HashMap to store word frequency
        HashMap<String, Integer> frequency = new HashMap<>();

        for (int i = 0; i < words.length; i++) {

            // Check whether word is a stop word
            boolean isStopWord = false;

            for (int j = 0; j < stopWords.length; j++) {

                if (words[i].equals(stopWords[j])) {
                    isStopWord = true;
                    break;
                }
            }

            // Skip stop words
            if (isStopWord) {
                continue;
            }

            // Count frequency
            if (frequency.containsKey(words[i])) {
                frequency.put(words[i], frequency.get(words[i]) + 1);
            }
            else {
                frequency.put(words[i], 1);
            }
        }

        // Convert HashMap entries to a List
        List<Map.Entry<String, Integer>> list =
                new ArrayList<>(frequency.entrySet());

        // Sort by count in descending order
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

            public int compare(
                    Map.Entry<String, Integer> a,
                    Map.Entry<String, Integer> b) {

                return b.getValue() - a.getValue();
            }
        });

        // Print result
        for (Map.Entry<String, Integer> entry : list) {

            System.out.println(
                    entry.getKey() + ": " + entry.getValue()
            );
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter feedback: ");
        String feedback = sc.nextLine();

        printFilteredWordFrequency(feedback);

        sc.close();
    }
}