//Задание 2
//        Создать программу по работе со словарем. Например,
//        англо-испанский или французско-немецкий, или любое
//        другое направление.
//        Практические задания
//        1
//        Программа должна:
//        ■ Обеспечивать начальный ввод данных для словаря.
//        ■ Позволять отобразить слово и его переводы.
//        ■ Позволять добавить, заменить, удалить переводы слова.
//        ■ Позволять добавить, заменить, удалить слово.
//        ■ Отображать топ-10 самых популярных слов (определяем популярность на основании счетчика обращений).
//        ■ Отображать топ-10 самых непопулярных слов (определяем непопулярность на основании счетчика обращений).

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Dictionary {
    public static MyComparator myComparator = new MyComparator();

    public static Comparator<Map.Entry<String, Integer>> cmp = Map.Entry.comparingByValue();

    public static Map<String, ArrayList<String>> dictionary = new HashMap<>();
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static Map<String, Integer> countPopularWord = new LinkedHashMap<>();

    public static String readWord;
    public static String readTranslate;
    public static int index;
    public static int count;
    public static int readChoose;

    public static void main(String[] args) throws IOException {
        init();
        int choose = showMenu();

        while (choose > 0 && choose < 6) {
            switch (choose) {
                case 1:
                    System.out.println("Enter the word for translate!");
                    try {
                        readWord = br.readLine();
                    } catch (NumberFormatException n) {
                        System.out.println("Entered the text wrong!");
                    }
                    displayWordAndTranslate(readWord);
                    addCount(readWord);
                    break;
                case 2:
                    System.out.println("Choose :\n 1 - add translate of the word \n" +
                            " 2 - delete translate of the word \n" +
                            " 3 - change translate of the word ");
                    try {
                        readChoose = Integer.parseInt(br.readLine());
                    } catch (NumberFormatException n) {
                        System.out.println("Entered the text wrong!");
                    }
                    switch (readChoose) {
                        case 1:
                            System.out.println("Enter the word to add translate!");
                            try {
                                readWord = br.readLine();
                                System.out.println("Enter translate of the word!");
                                System.out.println("Example: хороший , отличный, и т.д..");
                                readTranslate = br.readLine();
                            } catch (NumberFormatException n) {
                                System.out.println("Entered the text wrong!");
                            }
                            addTranslateOfTheWord(readWord, readTranslate);
                            showMap(dictionary);
                            addCount(readWord);
                            break;
                        case 2:

                            System.out.println("Enter translate of the word for delete!");
                            try {
                                readTranslate = br.readLine();
                            } catch (NumberFormatException n) {
                                n.printStackTrace();
                            }
                            deleteAllTranslateOfTheWord(readTranslate);

                            break;

                        case 3:
                            try {
                                System.out.println("Enter translate of the word for change!");
                                readTranslate = br.readLine();
                                System.out.println("Enter new translate of the word!");
                                readWord = br.readLine();
                            } catch (NumberFormatException n) {
                                n.printStackTrace();
                            }
                            changeTranslateOfWord(readTranslate, readWord);
                            addCount(readWord);
                            break;

                    }
                    break;
                case 3:
                    showMenuForChangeTheWord();
                    readChoose = Integer.parseInt(br.readLine());
                    switch (readChoose) {
                        case 1:
                            System.out.println("Enter the word for add!");
                            readWord = br.readLine();
                            System.out.println("Enter translate of the word!");
                            readTranslate = br.readLine();
                            ArrayList<String> list = new ArrayList<>();
                            list.add(readTranslate);
                            wordAdd(readWord, list);
                            showMap(dictionary);
                            addCount(readWord);
                            break;
                        case 2:
                            System.out.println("Enter the word for delete!");
                            readWord = br.readLine();
                            deleteWord(readWord);
                            showMap(dictionary);
                            break;
                        case 3:
                            System.out.println("Enter the word for change!");
                            readWord = br.readLine();
                            System.out.println("Enter new word!");
                            String newWord = br.readLine();
                            changeWord(readWord, newWord);
                            showMap(dictionary);
                            addCount(readWord);
                            break;
                    }
                    break;
                case 4:
                    showThreeMostPopularWord();
                    break;
                case 5:
                    showThreeUnpopularWords();
                    break;

            }
            choose = showMenu();


        }

    }

    private static void addCount(String readWord) {
        count = 1;
        if (countPopularWord.containsKey(readWord)) {
            count = countPopularWord.get(readWord).intValue();
            countPopularWord.replace(readWord, countPopularWord.get(readWord), count++);
        } else {
            countPopularWord.put(readWord, count);
        }
    }

    private static void showThreeUnpopularWords() {

        count = 0;
        countPopularWord.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue())
                .forEach(x -> {
                    if (count < 4) {
                        System.out.println(x.getKey() + " - " + x.getValue());
                        count++;
                    }
                });
    }

    private static void showThreeMostPopularWord() {
        count = 0;
        countPopularWord.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(x -> {
                    if (count < 4) {
                        System.out.println(x.getKey() + " - " + x.getValue());
                        count++;
                    }
                });


    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> st = map.entrySet().stream();
        st.sorted(Comparator.comparing(e -> e.getValue()))
                .forEach(e -> result.put(e.getKey(), e.getValue()));
        return result;

    }

    private static void changeWord(String readWord, String newWord) {
        for (Map.Entry<String, ArrayList<String>> map : dictionary.entrySet()) {
            if (readWord.equals(map.getKey())) {
                ArrayList<String> oldTranslate = new ArrayList<>();
                for (String s : map.getValue()) {
                    oldTranslate.add(s);
                }
                dictionary.remove(readTranslate);
                dictionary.put(newWord, oldTranslate);
                break;
            }

        }

    }

    private static void deleteWord(String readWord) {
        for (Map.Entry<String, ArrayList<String>> map : dictionary.entrySet()) {
            if (readWord.equals(map.getKey())) {
                dictionary.remove(readWord);
                break;
            }

        }
    }

    private static void showMap(Map<String, ArrayList<String>> dictionary) {
        for (Map.Entry<String, ArrayList<String>> map : dictionary.entrySet()) {
            System.out.println(map.getKey() + " - " + map.getValue());
        }
    }

    private static void wordAdd(String readWord, ArrayList<String> list) {
        dictionary.put(readWord, list);
    }

    private static void showMenuForChangeTheWord() {
        System.out.println("Choose :\n 1 - add the word and translate " +
                "\n 2 - delete word " +
                "\n 3 - change word ");
    }

    private static void changeTranslateOfWord(String readTranslate, String readWord) {
        for (Map.Entry<String, ArrayList<String>> map : dictionary.entrySet()) {
            if (map.getValue().contains(readTranslate)) {
                map.setValue(null);
                map.setValue(new ArrayList<>(Arrays.asList(readWord)));
            }
            index++;
        }

    }

    private static void deleteAllTranslateOfTheWord(String read) {
        if (read != null & read != "") {
            ArrayList<String> list = new ArrayList<>();
            dictionary.replace(read, dictionary.get(read), list);
        }
    }

    private static void addTranslateOfTheWord(String word, String translateForAdd) {
        if (word != null && word != "") {
            String[] newStr = translateForAdd.split(",");
            ArrayList<String> list = new ArrayList<>();
            index= (dictionary.get(word).size())-1;
            for (int i=0;i<index;i++){
                String one=dictionary.get(word).get(index);
                list.add(one);
            }
            list.addAll(Arrays.asList(newStr));
            dictionary.put(word, list);
        }
    }

    private static void displayWordAndTranslate(String word) {
        if (countPopularWord.containsKey(word)) {
            for (Map.Entry<String, Integer> map : countPopularWord.entrySet()) {
                if (word.equals(map.getKey())) {
                    count = map.getValue();
                }
            }
            countPopularWord.put(word, ++count);
        } else {
            count = 1;
            countPopularWord.put(word, count);
        }
        for (Map.Entry<String, ArrayList<String>> map : dictionary.entrySet()) {
            if (map.getKey().contains(word)) {
                System.out.println(map.getKey() + map.getValue().toString());
            }
        }
    }


    private static void init() {
        ArrayList<String> hello = new ArrayList<>();
        hello.addAll(Arrays.asList("привет", " здраствуйте"));
        dictionary.put("hello", hello);
        ArrayList<String> buy = new ArrayList<>();
        buy.addAll(Arrays.asList("пока", "досвидания"));
        dictionary.put("buy", buy);
        ArrayList<String> such = new ArrayList<>();
        such.addAll(Arrays.asList("какой", "какая", "какие"));
        dictionary.put("such", such);


    }

    private static int showMenu() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Menu: " +
                "\n 1 - display the word and its translations \n" +
                " 2 - add,delete,change translation of the word \n" +
                " 3 - add,delete,change  the word \n" +
                " 4 - display 3 the most popular words \n" +
                " 5 - display 3 not the most popular words . ");
        System.out.println(sb);
        return Integer.parseInt(br.readLine());
    }


}