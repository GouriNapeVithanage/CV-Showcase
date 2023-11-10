import java.util.Scanner;

public class Main {
    private static MySet<String> mainDict = new MySet<String>();
    public static void main(String[] args) {
        Main main = new Main();
        Scanner input = new Scanner(System.in);
        String word, choice;

        Dictionary.initializeDict();

        mainDict = Dictionary.dict.copy();

        label:
        while (true) {
            System.out.println("""
                    \n
                    t - type and check a word.
                    a - add a word to the set of words.
                    e - exit the program
                    """);
            System.out.print("Type here: ");
            choice = input.nextLine();

            // spell checker
            switch (choice) {
                case "t":
                    // get the word from the user
                    System.out.print("Type your word: ");
                    word = input.nextLine();

                    // check for the spelling and output the suggestions
                    LinkedList.printList(main.spellChecker(word));
                    break;
                // to add n word to the set
                case "a":
                    // get the word from the user
                    System.out.print("\nType your word: ");
                    word = input.nextLine();

                    if (mainDict.contains(word)) {
                        System.out.println("This word is already there in the set.\n");
                    } else {
                        mainDict.add(word);
                        System.out.println(word + " has been added!");
                    }
                    break;
                // exit the program
                case "e":
                    System.out.println("Exiting...");
                    break label;
                default:
                    System.out.println("Invalid option, please try again.\n");
                    break;
            }
        }
    }

    public LinkedList spellChecker(String input) {
        LinkedList suggestions = new LinkedList();
        Main main = new Main();


        // convert the word given by the user to lowercase
        String word = input.toLowerCase();


        // when the user types the correct word with correct spellings
        if (mainDict.contains(word)) {
            LinkedList.insert(suggestions, word);
            return suggestions;
        }

        // when user gives a correct word but the letters are not in order
        for (int i = 0; i < word.length() - 1; i++) {
            String swapped = swapAdjacent(word, i);
            if (mainDict.contains(swapped)) {
                LinkedList.insert(suggestions, swapped);;
            }
        }

        // when user gives a correct word but there is a letter missing which needs to be added
        for (int i = 0; i < word.length() + 1; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                String inserted = insertLetter(word, i, c);
                if (mainDict.contains(inserted)) {
                    LinkedList.insert(suggestions, inserted);;
                }
            }
        }

        // when user gives a correct word but there is an extra letter added which needs to be deleted
        for (int i = 0; i < word.length(); i++) {
            String deleted = deleteLetter(word, i);
            if (mainDict.contains(deleted)) {
                LinkedList.insert(suggestions, deleted);;
            }
        }

        // replacing all the letters of the word a-z and checking whether there is a word or not
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                String replaced = replaceLetter(word, i, c);
                if (mainDict.contains(replaced)) {
                    LinkedList.insert(suggestions, replaced);;
                }
            }
        }

        if (suggestions.isEmpty()){
            LinkedList.insert(suggestions, "Your word is not in our dictionary. You can try adding the new word to our dictionary");
            return suggestions;
        }

        return suggestions;
    }

    // swap two letters of a given string at the given index and the character after
    public String swapAdjacent(String word, int index) {
        char[] letters = word.toCharArray();
        char temp = letters[index];
        letters[index] = letters[index+1];
        letters[index+1] = temp;
        return new String(letters);
    }

    // insert a letter to a given index of a string
    public String insertLetter(String word, int index, char letter) {
        StringBuilder sb = new StringBuilder(word);
        sb.insert(index, letter);
        return sb.toString();
    }

    // delete a letter of a string at a given index
    public String deleteLetter(String word, int index) {
        StringBuilder sb = new StringBuilder(word);
        sb.deleteCharAt(index);
        return sb.toString();
    }

    // replace a letter at a given index with a given letter of a string
    public String replaceLetter(String word, int index, char letter) {
        char[] letters = word.toCharArray();
        letters[index] = letter;
        return new String(letters);
    }
}