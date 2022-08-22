import java.util.*;

public class Task2 {


//
//      Задача 2
//      Необходимо удалить пустые строки из списка строк.
//

    public static void main(String[] args) {


        ArrayList<String> list = new ArrayList<>(20);
        list.add("FwadHgjn");
        list.add("FdHgsaaffffjn");
        list.add("asHwadgjn");
        list.add("gfGHgfagsjn");
        list.add("lkGHgdsdsjn");
        list.trimToSize();


        list.stream().forEach(x -> System.out.println(x));


    }
}