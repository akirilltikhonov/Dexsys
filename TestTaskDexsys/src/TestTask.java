import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestTask {

    enum Type {
        L3,
        L7,
        L21
    }

    static List<Integer> list3 = new ArrayList<>();
    static List<Integer> list7 = new ArrayList<>();
    static List<Integer> list21 = new ArrayList<>();
    static boolean anyMore = false;

    //    0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24
//    24 23 22 21 20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1
//    0 -1 -3 -7 -14 -18 -21
    public static void main(String[] args) throws IOException {
        System.out.println("The application receives on the input nothing or string array consisting of integer value.\n" +
                "For example, \" -3 -1 0 1 3 7\".\n" +
                "Three lists are displayed on the console which are divided without remainder by 3, 7 and 21.\n" +
                "Call 'help' to get all applications commands.\n");
        try {
            init(args);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect input program arguments. " +
                    "The application receives on the input nothing or string array consisting of integer value.");
            System.exit(0);
        }
        printAll();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String str = null;

            while (!(str = reader.readLine()).equals("exit")) {

                if (str.matches("init(\\s[-+]?\\d+)+\\s*")) {
                    String[] array = str.substring(5).split(" ");
                    init(array);

                }
                else if ("print".equals(str)) printAll();
                else if (("print " + Type.L3).equals(str)) print(list3, Type.L3);
                else if (("print " + Type.L7).equals(str)) print(list7, Type.L7);
                else if (("print " + Type.L21).equals(str)) print(list21, Type.L21);
                else if (("clear " + Type.L3).equals(str)) list3.clear();
                else if (("clear " + Type.L7).equals(str)) list7.clear();
                else if (("clear " + Type.L21).equals(str)) list21.clear();
                else if (("merge").equals(str)) merge();
                else if (("anyMore").equals(str)) anyMore();
                else if (("help").equals(str)) help();
                else System.out.println("Incorrect command. Call 'help' and try again");

            }
            System.out.println("\nExiting program. Goodbye");
        }
    }

    public static void init(String[] array) {
            list3.clear();
            list7.clear();
            list21.clear();
            anyMore = false;

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
                } else if (!anyMore && !(flag3 || flag7)) {
                    anyMore = true;
                }
            }
            Collections.sort(list3);
            Collections.sort(list7);
            Collections.sort(list21);
    }

    public static void anyMore() {
        System.out.println(anyMore);
    }

    static public void printAll() {
        print(list3, Type.L3);
        print(list7, Type.L7);
        print(list21, Type.L21);
    }

    static public void print(List<Integer> list, Type type) {
        if (list.size() == 0) {
            System.out.println("List " + type + " is empty");
        } else {
            System.out.println(String.format("List %s : %s", type, list));
        }
    }

    public static void merge() {
//        System.out.println(Stream.of(list3, list7, list21).flatMap(x -> x.stream()).sorted().collect(Collectors.toList()));
        System.out.println((Set) Stream.of(list3, list7, list21).flatMap(x -> x.stream()).collect(Collectors.toCollection(TreeSet::new)));
        list3.clear();
        list7.clear();
        list21.clear();
    }

    public static void help() {
        try {
            Files.readAllLines(Paths.get("help.txt")).stream().forEach(x -> System.out.println(x));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
