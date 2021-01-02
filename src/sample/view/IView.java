package sample.view;

import sample.presenter.IPresenter;

import java.io.File;

public interface IView {

    void setPresenter(IPresenter presenter);

    File getDataFile(FileActions action);

    void setText(String text);

    String[] getText();

    public enum FileActions {
        SAVE_FILE,
        OPEN_FILE
    }
}
