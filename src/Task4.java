import java.util.ArrayList;
import java.util.Random;

public class Task4 {



//      Задача 4
//      Дан список чисел, необходимо удалить все вхождения числа 20 из него.


    public static void main(String[] args) {
        ArrayList<Integer> list=new ArrayList<>(100);
        Random random=new Random();
        while (true){
            if (list.size()<100){
                list.add(random.nextInt((30)*1)+9);
            }else {
                break;
            }
        }

        System.out.println(list);

        list.removeIf(x -> x.equals(20));

        System.out.println(list);
    }
}