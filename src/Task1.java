//     Задача 1
//     Дан список некоторых целых чисел, найдите значение 20 в нем и, если оно присутствует,
//     замените его на 200.
//     Обновите список только при первом вхождении числа 20.
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Task1 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(100);
        System.out.println(list.size());
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            list.add(random.nextInt() );
        }

        findTwenty(list);
    }
    public static List findTwenty(List<Integer> list) {
        System.out.println(list.toString());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(20)) {
                list.set(i, 200);
                break;
            }
        }
        System.out.println(list.toString());
        return list;
    }
}
