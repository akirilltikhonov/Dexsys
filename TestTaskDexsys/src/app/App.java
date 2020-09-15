package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App implements IApp {
    List<Integer> listDividedBy3 = new ArrayList<>();
    List<Integer> listDividedBy7 = new ArrayList<>();
    List<Integer> listDividedBy21 = new ArrayList<>();
    boolean anyMore = false;

    public void info() {
        System.out.println("The application receives on the input nothing or string array consisting of integer or/and float/double values.\n" +
                "For example: \"-3 -1 0 1 3 7\" or \"-15.d -12.f -9. -6.0f -3.0d +0f 3d 6.00 +09.0\".\n" +
                "Three lists are displayed on the console which are divided without remainder by 3, 7 and 21 (L3, L7 and L21, accordingly).\n" +
                "Call 'help' to get all application commands.\n");
    }

    public void mainLoop() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String str = null;
            while (!(str = reader.readLine()).equals("exit")) {
                // regex only for integer "init(\\s[-+]?\\d+)+\\s*"
                // regex for integer and decimal numbers "init(\\s[-+]?\\d+(.\\d+)?)+\\s*"
                // regex for Integer, Float and Double in all entry forms (almost) "init(\\s[-+]?\\d*((\\.\\d*))?(d|f)?)+\\s*"
                if (str.matches("init(\\s[-+]?\\d+((\\.\\d*))?(d|f)?)+\\s*")) {
                    String[] array = str.substring(5).split(" ");
                    init(array);
                } else if ("print".equals(str)) printAll();
                else if (("print " + Type.L3).equals(str)) print(listDividedBy3, Type.L3);
                else if (("print " + Type.L7).equals(str)) print(listDividedBy7, Type.L7);
                else if (("print " + Type.L21).equals(str)) print(listDividedBy21, Type.L21);
                else if (("clear " + Type.L3).equals(str)) listDividedBy3.clear();
                else if (("clear " + Type.L7).equals(str)) listDividedBy7.clear();
                else if (("clear " + Type.L21).equals(str)) listDividedBy21.clear();
                else if (("merge").equals(str)) merge();
                else if (("anyMore").equals(str)) anyMore();
                else if (("help").equals(str)) help();
                else System.out.println("Incorrect command. Call 'help' and try again\n");
            }
            System.out.println("\nExiting program. Goodbye");
        }
    }

    public void init(String[] array) {

        listDividedBy3.clear();
        listDividedBy7.clear();
        listDividedBy21.clear();
        anyMore = false;

        for (String arg : array) {
            double num = Double.parseDouble(arg);
            boolean flag3;
            boolean flag7;
            if (flag3 = (num % 3 == 0)) {
                if (!listDividedBy3.contains((int) num)) {
                    listDividedBy3.add((int) num);
                }
            }
            if (flag7 = (num % 7 == 0)) {
                if (!listDividedBy7.contains((int) num)) {
                    listDividedBy7.add((int) num);
                }
            }
            if (flag3 && flag7) {
                if (!listDividedBy21.contains((int) num)) {
                    listDividedBy21.add((int) num);
                }
            } else if (!anyMore && !(flag3 || flag7)) {
                anyMore = true;
            }
        }
        Collections.sort(listDividedBy3);
        Collections.sort(listDividedBy7);
        Collections.sort(listDividedBy21);
        System.out.println();
    }

    public void anyMore() {
        System.out.println(anyMore + "\n");
    }

    public void printAll() {
        print(listDividedBy3, Type.L3);
        print(listDividedBy7, Type.L7);
        print(listDividedBy21, Type.L21);
    }

    public void print(List<Integer> list, Type type) {
        if (list.size() == 0) {
            System.out.println("List " + type + " is empty");
        } else {
            System.out.println(String.format("List %s : %s", type, list));
        }
        System.out.println();
    }

    public void merge() {
//        System.out.println(Stream.of(listDividedBy3, listDividedBy7, listDividedBy21).flatMap(x -> x.stream()).sorted().collect(Collectors.toList()));
        System.out.println((Set) Stream.of(listDividedBy3, listDividedBy7, listDividedBy21).flatMap(x -> x.stream()).collect(Collectors.toCollection(TreeSet::new)));
        System.out.println();
        listDividedBy3.clear();
        listDividedBy7.clear();
        listDividedBy21.clear();
    }

    public void help() {
        try {
            Files.readAllLines(Paths.get("help.txt")).stream().forEach(x -> System.out.println(x));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
