package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        greeting();
    }

    public static void greeting() {
        System.out.println("Welcome in our Bank");
        System.out.println("Are u already a member of our Bank ?");
        System.out.println("If yes, please enter (1) to login and (2) to join us!");

        Scanner userInputReader = new Scanner(System.in);
        while(true) {
            char userInput = userInputReader.next().charAt(0);
            switch (userInput) {
                case '1':
                    //login function
                    login();
                    break;
                case '2':
                    //register function
                    register();
                    break;
                default:
                    System.out.println("Wrong Input");
                    break;
            }
        }
    }

    public static void login() {
        System.out.println("PLEASE LOG IN");
    }

    public static void register() {
        System.out.println("REGISTER");
    }
}

