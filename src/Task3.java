
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class Task3 {

    public static ArrayList<Integer> list = new ArrayList<Integer>(100);
    public static Iterator iterator = list.iterator();
//     Задача 3
//   Дан список чисел. Превратите его в список квадратов этих чисел.
//
    public static void main(String[] args) {
        Random random = new Random();

        while (true) {
            list.add(random.nextInt((40) * 1) + 1);//from 1-40
            if (list.size() == 90) {
                break;
            }
        }
        System.out.println(list);

        System.out.println(numberSquaredAllElements(list));

    }

    public static ArrayList<Integer> numberSquaredAllElements(ArrayList<Integer> list) {
        ArrayList<Integer> list1 = new ArrayList<>(100);
        int temp = 0;
        for (int i = 0; i < list.size(); i++) {
            temp = list.get(i);
            list1.add(temp * 2);
        }
        return list1;
    }
}