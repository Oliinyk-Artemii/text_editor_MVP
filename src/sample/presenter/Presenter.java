package sample.presenter;

import javafx.scene.control.TextArea;
import sample.interfaces.IStorage;
import sample.interfaces.IText;
import sample.models.Storage;
import sample.models.Text;
import sample.models.Word;
import sample.view.IView;

import java.io.File;

public class Presenter implements IPresenter {
    private IStorage storage;
    private IText text;
    private TextArea field;
    private IView view;
    private File file;

    public Presenter(IView view){
        this.view = view;
        bind();
    }

    public void bind() {
        view.setPresenter(this);
        storage = new Storage();
    }

    @Override
    public void openFile(){
        file = view.getDataFile(IView.FileActions.OPEN_FILE);
        text = storage.readFromFile(file.getAbsolutePath());
        view.setText(text.getText());
    }

    public void saveFile(File file){
        text.setText(view.getText());
        file = view.getDataFile(IView.FileActions.SAVE_FILE);
        storage.writeToFile(text, file.getAbsolutePath());
    }

    public void copyText(String s){
        text.copyWord(new Word(s));
    }

    public void pasteText(){
        if(field.getSelectedText() != null && field.getSelectedText().length() > 0) {
            //TODO add replacing text
        }
        int ind = field.getCaretPosition();
        updateModel();
        text.pasteWord(field.getCaretPosition());
        updateView();
        field.positionCaret(ind + text.getBuffer().length());
    }

    @Override
    public void newFile() {
        field.clear();
        text = new Text(new String[]{""});
    }

    public void updateModel(){
        text.setText(field.getText().split("\n"));
    }

    public void updateView(){
        field.setText(text.getText());
    }


}
