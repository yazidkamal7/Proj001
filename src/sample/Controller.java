package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import java.io.*;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import java.lang.String;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    File inFile;
    int p;
    ArrayList<Integer> LED = new ArrayList<Integer>();
    ArrayList<Integer> power = new ArrayList<Integer>();


    // file
    FileChooser fileChooser = new FileChooser();
    @FXML
    private TextArea area;
    @FXML
    private TextField TextFiled;
    @FXML
    private javafx.scene.control.Button Browess;
    @FXML
    public void Browse(ActionEvent actionEvent) {
        FileChooser file = new FileChooser();
        file.setTitle("Choose a file to Read");
        this.inFile = file.showOpenDialog(new Stage());
        this.TextFiled.setText(this.inFile.getPath());


    }
    @FXML
    public void printLCO(){

        ReadFile();
        setnumToPower();





        System.out.println(p);
//        for (int i=0;i<p;i++){
//            System.out.println(power.get(i));
//        }






        int tLCS[][];
        tLCS=printSch();
        System.out.println("Length of LCS is" + " " +
                lcs(  power.size(), LED.size()  ));
        //TextArea.setText("Length of LCS is"+ lcs( arr1, arr2, r, n ) + "\n" + " --------------------------------------" + "\n" +printSch(r,n,arr1,arr2) );

        print(tLCS , power.size(), LED.size() );

        //System.out.println(s);
    }

    public  void print (int[][] b, int i, int j) {

        if (i==0||j==0) {
            return ;
        }
        else if(b[i][j]==1) {
            System.out.println(power.get(i-1));


            print(b,i-1,j-1);
        }
        else {
            if (b[i][j]==2) {
                print(b, i-1, j);


            }
            else {
                print(b, i, j-1);
            }
        }
        return ;
    }

    int max(int a, int b)
    {
        return (a > b)? a : b;
    }
    @FXML
    public  int [] [] printSch () {
        int c[][] =new int [p+1][p+1];
        int b[][]=new int [p+1][p+1];
        for (int i = 0 ; i <= p; i++) {
            c[0][i]=0;
            b[0][i]=3;


        }
        for (int j =0 ; j < p; j++) {
            c[j][0]=0;
            b[j][0]=3;

        }
        for (int i = 1; i <= power.size() ; i++) {
            for (int j = 1; j <= LED.size() ; j++) {
                if (power.get(i-1) == LED.get(j-1)) {
                    c[i][j]=c[i-1][j-1]+1;
                    b[i][j]=1;
                }
                else if (c[i][j-1] > c[i-1][j]) {
                    c[i][j]=c[i][j-1];
                    b[i][j]=0;


                }
                else {
                    c[i][j]=c[i-1][j];
                    b[i][j]=2;
                }


            }
        }

        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b.length; j++) {
                System.out.print(String.valueOf(c[i][j])+"\t");

                area.appendText(String.valueOf(c[i][j])+"\t");

            }
            System.out.println();
            area.appendText("\n");

        }
        return c;

    }


    public int lcs( int r, int n )
    {
        if (r == 0 || n == 0)
            return 0;
        if (power.get(r-1) == LED.get(n-1))
            return lcs( r-1, n-1) +1;
        else
            return max(lcs( r, n-1), lcs( r-1, n));
    }
    public void ReadFile(){
        try {
            Scanner scanner = new Scanner(new File(String.valueOf(inFile)));
            if(scanner.hasNext()){
                p = Integer.parseInt(scanner.nextLine());
            }
            if(scanner.hasNext()){
               LED.add(scanner.nextInt());

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    void setnumToPower(){
        for (int i=1 ; i < p ; i++){
            power.add(i);
        }
    }
}