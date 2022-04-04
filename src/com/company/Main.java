package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        while (true) {
            char userInput = userInputReader.next().charAt(0);
            switch (userInput) {
                case '1' ->
                        //login function
                        login();
                case '2' ->
                        //register function
                        register();
                default -> System.out.println("Wrong Input");
            }
        }
    }

    public static void login() {
        Scanner userCredentials = new Scanner(System.in);

            try {
                //Read input from a user (username and password)
                System.out.println("Username: ");
                String username = userCredentials.nextLine();

                System.out.println("Password: ");
                String password = userCredentials.nextLine();

                // Create reader to read user registered data
                BufferedReader readUserInput = new BufferedReader(
                         new FileReader("Register.txt"));
                // Read user input, first username then password in next line
                // (simple logic for only one user because every new registration
                // with BufferedWriter will be overridden
                String readUsername = readUserInput.readLine();
                String readUserPassword = readUserInput.readLine();
                outer: while (true) {
                    //check every line if is not a null using while loop
                while ((readUsername != null && readUserPassword != null )) {
                    // check if user input equals the data in a file, check if on 1st line username equals
                    // the given one from user and check the second line for a password
                    if (readUsername.equals(username)  && readUserPassword.equals(password)) {
                       //TODO  call a  function to open banking menu after successful login.
                        System.out.println("you have successful logged in.");
                         break outer;
                    } else {
                        System.out.println("Wrong username or password.");
                    }

                }}

                readUserInput.close();
            } catch (Exception error) {
                System.out.println("Wrong username or password.");
                error.printStackTrace();
            }


    }

    public static void register() {
        System.out.println("REGISTER");

        Scanner newUsernameReader = new Scanner(System.in);
        Scanner newPasswordReader = new Scanner(System.in);

        System.out.println("Your username: ");
        String newUsername = newUsernameReader.nextLine();
        System.out.println("YOur password: ");
        String newPassword = newPasswordReader.nextLine();

        // write user input to the file
        try {
            BufferedWriter writeUserInput = new BufferedWriter(
                    new FileWriter("Register.txt"));
            writeUserInput.write(newUsername + '\n');
            writeUserInput.write(newPassword + '\n');
            writeUserInput.close();
        } catch (Exception error) {
            System.out.println("An error occurred.");
            error.printStackTrace();
        }
    }


    // TODO create a function for a banking menu an interaction with it
}


