package sample.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.presenter.IPresenter;
import sample.presenter.Presenter;

import java.io.File;
import java.util.Optional;

public class View extends Application implements IView {
    private Stage primaryStage;
    private TextArea textField;
    private IPresenter presenter;
    private MenuBar menuBar;
    private Menu fileMenu;
    private Menu editMenu;
    private MenuItem newItem;
    private MenuItem openFileItem;
    private MenuItem saveFileItem;
    private MenuItem exitItem;
    private MenuItem copyItem;
    private MenuItem pasteItem;
    private BorderPane root;
    private Scene scene;
    private FileChooser fileChooser;
    @Override
    public void start(Stage stage){
        presenter = new Presenter(this);
        primaryStage = stage;
        primaryStage.setTitle("Text Editor v2.0");

        textField = new TextArea();

        // Create MenuBar
        menuBar = new MenuBar();

        // Create menus
        fileMenu = new Menu("File");
        editMenu = new Menu("Edit");

        // Create MenuItems
        newItem = new MenuItem("New");
        openFileItem = new MenuItem("Open...");
        saveFileItem = new MenuItem("Save...");
        exitItem = new MenuItem("Exit");
        copyItem = new MenuItem("Copy");
        pasteItem = new MenuItem("Insert");

        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);


        newItem.setOnAction(actionEvent -> {
            presenter.newFile();
        });
        openFileItem.setOnAction(actionEvent -> {
            if (presenter != null) {
                presenter.openFile();
            }
        });
        // Add menuItems to the Menus
        fileMenu.getItems().addAll(newItem, openFileItem, saveFileItem, exitItem);
        editMenu.getItems().addAll(copyItem, pasteItem);
        saveFileItem.setOnAction(actionEvent -> {
            if (presenter != null) {
                presenter.saveFile();
            }
        });
        exitItem.setOnAction(actionEvent -> presenter.exit());
        copyItem.setOnAction(actionEvent -> presenter.copyText(textField.getSelectedText()));
        pasteItem.setOnAction(actionEvent -> presenter.pasteText());

        // Add Menus to the MenuBar
        menuBar.getMenus().addAll(fileMenu, editMenu);


        root = new BorderPane();
        root.setTop(menuBar);

        root.setCenter(textField);
        scene = new Scene(root, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setPresenter(IPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public File getDataFile(FileActions action) {
        switch (action) {
            case OPEN_FILE -> {
                return fileChooser.showOpenDialog(primaryStage);
            }
            case SAVE_FILE -> {
                return fileChooser.showSaveDialog(primaryStage);
            }
            default -> {
                return new File("");
            }
        }
    }
    @Override
    public boolean showDialog(FileActions action) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        String title, text = "Are you sure you want to quit without saving?";

        switch (action) {
            case OPEN_FILE -> title = "Opening";
            case SAVE_FILE -> title = "Saving";
            case NEW_FILE -> title = "Creating";
            default -> title = "Exiting";
        }
        alert.setTitle(title);
        alert.setHeaderText(text);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        } else {
            alert.close();
            return false;
        }
    }

    @Override
    public void setText(String text) {
        textField.setText(text);
    }

    @Override
    public String[] getText() {
        return textField.getText().split("\n");
    }
}
