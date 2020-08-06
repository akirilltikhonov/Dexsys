import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestTask {
    static List<Integer> list3 = new ArrayList<>();
    static List<Integer> list7 = new ArrayList<>();
    static List<Integer> list21 = new ArrayList<>();

//    0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24
//    24 23 22 21 20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1
    public static void main(String[] args) throws IOException {

        init(args);
        printAll();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String str = null;
            while (!(str = reader.readLine()).equals("exit")) {
                switch (str) {
                    case "print":
                        printAll();
                        break;
                    case "print X":
                        print(list3, "X");
                        break;
                    case "print S":
                        print(list7, "S");
                        break;
                    case "print M":
                        print(list21, "M");
                        break;
                    case "clear X":
                        list3.clear();
                        break;
                    case "clear S":
                        list7.clear();
                        break;
                    case "clear M":
                        list21.clear();
                        break;
                    case "merge":
                        merge();
                        break;
                    default :
                        System.out.println("Incorrect command. Call 'help' and try again");
                        break;
                }
            }
            System.out.println("Goodbye");
        }
    }

    public static void merge() {
        System.out.println(Stream.of(list3, list7, list21).flatMap(x -> x.stream()).collect(Collectors.toList()));
        list3.clear();
        list7.clear();
        list21.clear();
    }

    public static void init(String[] array) {
        list3.clear();
        list7.clear();
        list21.clear();

        for (String arg : array) {
            int num = Integer.parseInt(arg);
            boolean flag3;
            boolean flag7;
            if (flag3 = (num % 3 == 0)) {
                list3.add(num);
            }
            if (flag7 = (num % 7 == 0)) {
                list7.add(num);
            }
            if (flag3 && flag7) {
                list21.add(num);
            }
        }
        Collections.sort(list3);
        Collections.sort(list7);
        Collections.sort(list21);
    }
    static public void printAll() {
        print(list3, "X");
        print(list7, "S");
        print(list21, "M");
    }
    static public void print(List<Integer> list, String type) {
        if (list.size() == 0) {
            System.out.println("Список " + type + " пуст");
        } else {
            System.out.println(String.format("Список %s : %s", type, list));
        }

    }

}
