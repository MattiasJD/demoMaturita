package com.example.demomaturita;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.spi.DecimalFormatSymbolsProvider;
import java.util.*;

public class HelloApplication extends Application {


    public static String FROM;
    public static String TO;
    public static Double MONEY;
    public static int ID = 0;
    public static List<Transaction> MAP = new LinkedList<>();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public static void openFileChooser() throws FileNotFoundException {
        Stage stage1 = new Stage();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage1);
        System.out.println("file loaded successfully");
        fileReader(file);
    }
    public static void fileReader(File file) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        Scanner scanner = new Scanner(fileInputStream);
        scanner.nextLine();
        while(scanner.hasNext()){
            String line = scanner.nextLine();
            String[] lineArr = line.split(";");
            FROM = lineArr[1];
            TO = lineArr[2];
            MONEY = Double.parseDouble(lineArr[3]);
            Transaction transaction = new Transaction(FROM, TO, MONEY);
            MAP.add(transaction);
            ID++;
        }
        checkTransaction("Adrian Babis","Milous Zeman");
    }
    public static void checkTransaction(String from, String to){
        ArrayList<Double> love = new ArrayList<>();
        for (int j = 0; j < MAP.size(); j++) {
                if (from.equals(MAP.get(j).getFROM()) && to.equals(MAP.get(j).getTO())){
                    love.add(MAP.get(j).getMONEY());
                }
        }
        benfordsLaw(love);
    }
    public static void benfordsLaw(ArrayList<Double> moneys){
        HashMap<Character, Integer> numMap = new HashMap<>();
        for (Double money : moneys) {
            String bb = String.valueOf(money);
            char firstDigit = bb.charAt(0);
            Integer digitCount = numMap.get(firstDigit);
            if (digitCount == null) {
                digitCount = 0;
            }
            digitCount++;
            numMap.put(firstDigit, digitCount);
        }
        for(Map.Entry<Character, Integer> entry : numMap.entrySet()) {
            Character number = entry.getKey();
            double size = moneys.size();
            double d = entry.getValue()/ (double)size;
            System.out.format("%c --> %f\n", number, d);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}