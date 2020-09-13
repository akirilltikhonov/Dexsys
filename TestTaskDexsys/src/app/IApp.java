package app;

import java.io.IOException;
import java.util.List;

public interface IApp {
    void info();
    void mainLoop() throws IOException;
    void init(String[] array);
    void anyMore();
    void printAll();
    void print(List<Integer> list, Type type);
    void merge();
    void help();
}
