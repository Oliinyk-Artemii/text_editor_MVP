package sample;

import javafx.application.Application;
import sample.view.View;

public class Main {
    public static void main(String [] args) {
        Application.launch(((Application) new View()).getClass());
    }
}
