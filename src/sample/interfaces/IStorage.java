package sample.interfaces;

public interface IStorage {

    IText readFromFile(String fileName);

    void writeToFile(IText text, String fileName);

}