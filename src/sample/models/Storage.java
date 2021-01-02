package sample.models;

import sample.interfaces.IStorage;
import sample.interfaces.IText;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage implements IStorage {

    @Override
    public IText readFromFile(String fileName) {//read text from file
        Text text = new Text(new String[]{""});
        Scanner scanner = new Scanner("");
        try(FileReader fileReader = new FileReader(fileName)) {
            scanner = new Scanner(fileReader);
            //open streams for reading text from file

            StringBuilder stringBuilder = new StringBuilder();
            //read while we have text
            while(scanner.hasNext()) {
                //read one line from file
                stringBuilder.append(scanner.nextLine());
                stringBuilder.append("\n");
            }
            //remove last new line
            stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
            //result to instance our Text
            text = new Text(stringBuilder.toString().split("\n"));

        } catch (FileNotFoundException e) {
            //if file was not found
            e.printStackTrace();
        } catch (IOException e) {
            //if we get any stream exception
            e.printStackTrace();
        } finally {
            //close stream
            scanner.close();
        }
        return text;
    }

    @Override
    public void writeToFile(IText text, String fileName) {
        //save text in file
        try(FileWriter fileWriter = new FileWriter(fileName)) {
            //open streams for writing text in file
            fileWriter.write(text.toString());
        } catch (IOException e) {
            //if we get any stream exception
            e.printStackTrace();
        }
    }
}