import app.App;
import app.IApp;

import java.io.IOException;


//    Test input program arguments
//    0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24
//    24 23 22 21 20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1
//    0 -1 -3 -7 -14 -18 -21
//    -15.d -12.f -9. -6.0f -3.0d +0f 3d 6.00 +09.0
//    -15.1d -12.1f -9.01d -6.01f -3.1 +0.1f 3.1d 6.01 +09.1

public class TestTask {
    public static void main(String[] args) throws IOException {
        IApp app = new App();
        app.info();
        try {
            app.init(args);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect input program arguments. " +
                    "The application receives on the input nothing or string array consisting of integer or/and float/double numbers.\n" +
                    "Fix input data and try again");
            System.exit(0);
        }
        app.printAll();
        app.mainLoop();
    }
}
