package step.learning;
import java.util.*;

// Exercise
// Implement a “dictionary” based on collections - the user enters a word, its translation or a message about the
// absence of the word is displayed.
// Place the list of words statically, do not update from the keyboard.

public class Dictionary  // This Dictionary class is a simple dictionary for translating words from English into Russian and back.
{
    private static final Map<String, String> _language_dictionary = new HashMap<>()  // Сontains key-value pairs for words and their translations from
    {{                                                                              // English into Russian.
        put("Fortress", "Крепость");
        put("Pizza", "Пицца");
        put("Witch", "Ведьма");
        put("Tequila", "Текила");
        put("Dictionary", "Словарь");
        put("Path", "Путь");
        put("Magic", "Магия");
        put("Bye", "Пока");
    }};
    public Iterator<Map.Entry<String, String>> _iterator_language_dictionary = _language_dictionary.entrySet().iterator();

    public String TranslateENGtoRUS()  // This method asks the user to enter an English word and tries to find its translation in the _language_dictionary.
    {                                 // If a translation is found, the method returns a string with the translation, otherwise
                                     // it returns the message "Sorry, I didn't find anything".
        String word = WriteDownWord("[Eng]: [Rus] -> ");

        while (_iterator_language_dictionary.hasNext())
        {
            Map.Entry<String, String> entry = _iterator_language_dictionary.next();

            if (entry.getKey().equalsIgnoreCase(word))
                return word + ": " + entry.getValue();
        }

        return "Sorry, I didn't find anything";
    }

    public String TranslateRUStoENG()  // Similar to TranslateENGtoRUS(), but requests a word in Russian and looks for
    {                                 // its translation from Russian to English.
        String word = WriteDownWord("[Rus]: [Eng] -> ");

        while (_iterator_language_dictionary.hasNext())
        {
            Map.Entry<String, String> entry = _iterator_language_dictionary.next();

            if (entry.getValue().equalsIgnoreCase(word))
                return word + ": " + entry.getKey();
        }

        return "Sorry, I didn't find anything";
    }

    private String WriteDownWord(String type)  // Prompts the user for a word and returns the entered word.
    {                                         // It is used in the TranslateENGtoRUS() and TranslateRUStoENG() methods to obtain a word from the user.
        Scanner scann = new Scanner(System.in);

        System.out.print(type);

        return scann.nextLine();
    }
}

// +  +  +  +  +  Client code.  +  +  +  +  +
//
//    public static void main( String[] args )
//    {
//        Dictionary dict = new Dictionary();
//
//        System.out.println(dict.TranslateENGtoRUS());
//        System.out.println(dict.TranslateRUStoENG());
//    }