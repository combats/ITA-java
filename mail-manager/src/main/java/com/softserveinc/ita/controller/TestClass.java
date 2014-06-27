package com.softserveinc.ita.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class TestClass {
    public static void main(String[] args) {
        String template ="";
        try(Scanner in = new Scanner(new FileReader("text.txt"))) {
            while(in.hasNext()){
                template += in.next();
                System.out.println(template);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(template);
    }
}
