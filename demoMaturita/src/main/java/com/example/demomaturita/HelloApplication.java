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
import java.util.*;

public class HelloApplication extends Application {


    public static String FROM = "Adrian Babis";
    public static String TO = "Pitomio Vokamura";
    public static Double MONEY;
    public static int ID = 0;
    public static List<Transaction> MAP = new LinkedList<>();
    public static ArrayList<String> USEDNAMES = new ArrayList<>();
    public static Double [] PERCENTAGE = {0.0,0.301,0.176,0.125,0.097,0.079,0.067,0.058,0.051,0.046};

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
        checkTransaction();
    }
/**** nefunguje :(
    public static void nameChooser(){
        for (Transaction transaction : MAP) {
            if (USEDNAMES.contains(transaction.getFROM())){
                continue;
            } else {
                USEDNAMES.add(transaction.getFROM());
            }
        }
        return;
    }
***/
    public static void checkTransaction(){
        ArrayList<Double> love = new ArrayList<>();
        ///for (int i = 0; i < USEDNAMES.size(); i++) {
            ///FROM = USEDNAMES.get(i);
            ///for (String usedname : USEDNAMES) {
                ///TO = usedname;
                if (!Objects.equals(TO, FROM)) {
                    for (Transaction transaction : MAP) {
                        if (FROM.equals(transaction.getFROM()) && TO.equals(transaction.getTO())) {
                            love.add(transaction.getMONEY());
                        }
                    }
                ///}
           /// }
            ///nameChooser();
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
            int in = 0;
            Character number = entry.getKey();
            double size = moneys.size();
            double d = entry.getValue()/size;
            System.out.format("%c --> %f\n", number, d);
            if (d-PERCENTAGE[in]>0.1 || PERCENTAGE[in]-d >0.1){
                System.out.println("this is sussy");
               ///HelloController.bigLabel.setText(TO+" and "+FROM+" are sussy");
           } else{
                System.out.println("they're ok");
           }
            in += 1;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}