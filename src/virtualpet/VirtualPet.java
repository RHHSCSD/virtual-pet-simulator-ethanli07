/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package virtualpet;
import java.util.*;
import java.io.*;
import java.lang.Exception;

/********************************************
* Program: Virtual Pet Simulator
* Date: March 18, 2024
* Author: Ethan Li
* Description: Simulates ownership of a pet
*********************************************/

public class VirtualPet {
    public static void main(String[] args) throws Exception {
        //Initialize Objects
        Scanner kb = new Scanner(System.in);
        
        //Splash Art
        drawStartScreen();
        
        //Variables
        boolean petCreated = false;
        boolean playPet = false;
        
        String username = "";
        String password = "";
        String petSelected = "";
        String petName = "";
        
        int maxPetStatsArray[] = new int[3];
        int currentPetStatsArray[] = new int[3];
        String petStatsNameArray[] = {"Health", "Hunger", "Energy"};
        
        String userGameMenuChoice = "";
        int userMoney = 3;
        int userMoneyStorage[] = {0};
        int petInteractionHistory[] = new int[3];
        
        boolean userBooleanData[] = {petCreated, playPet};
        String userStringData[] = {username, password, petSelected, petName};
        
        //Login 
        boolean entryAllowed = login(userBooleanData, userStringData, maxPetStatsArray, currentPetStatsArray, userMoneyStorage);
        petCreated = userBooleanData[0];
        playPet = userBooleanData[1];
        username = userStringData[0];
        password = userStringData[1];
        petSelected = userStringData[2];
        petName = userStringData[3];
        userMoney = userMoneyStorage[0];
        
        //Prevent user entry if they fail the login
        if (entryAllowed == false) {
          System.exit(0);
        }
        
        while (true) {
            //Update Arrays
            userBooleanData = new boolean[] {petCreated, playPet};
            userStringData = new String[] {username, password, petSelected, petName};
            
            //Menu Options
            if (petCreated == false) {
                petCreated = petNotCreated(petCreated, userBooleanData, userStringData, maxPetStatsArray, currentPetStatsArray, userMoney);
            }
            else { //pet has alreay been created
                playPet = petIsCreated(playPet, userBooleanData, userStringData, maxPetStatsArray, currentPetStatsArray, userMoney, petInteractionHistory);
            }

            //Let's the user select a pet and generates their name and stats
            if ((petCreated == true) && (petSelected.equals(""))) {
                petSelected = createPet();
                petName = namePet();
                maxPetStatsArray = petStats();
                currentPetStatsArray = maxPetStatsArray.clone();
                for (int i = 0; i < currentPetStatsArray.length; i++) {
                    currentPetStatsArray[i] -= 2;
                }
            }
            else if (playPet == true) { //if the pet is already created, let the user play with them
                System.out.println("1. Play a game      2. Interact with my pet");
                System.out.print("Do you want to play a game or interact with your pet? ");
                userGameMenuChoice = kb.nextLine(); 

                if (userGameMenuChoice.equals("1") || userGameMenuChoice.equals("play")) { //play a game with the pet
                    System.out.println("1. Number guessing game      2. Matching game");
                    System.out.print("Which game do you want to play input the number): ");
                    int gameSelection = kb.nextInt();
                    kb.nextLine();

                    if (gameSelection == 1) {
                        userMoney = numberGuessingGame(userMoney);
                    }

                    else if (gameSelection == 2) {
                        userMoney = letterUnscrambleGame(userMoney);  
                    }
                }

                else if (userGameMenuChoice.equals("2") || userGameMenuChoice.equals("interact")) {//interat with the pet
                    System.out.println("1. Play      2. Feed      3. Groom");
                    System.out.print("How do you wish to interact with your pet (input the number): ");
                    int userInteractionChoice = kb.nextInt() - 1;
                    kb.nextLine();

                    if (userMoney < 1) {//checks if they can afford it
                        System.out.println("Insufficient Money!");
                    }
                    else if (currentPetStatsArray[userInteractionChoice] == maxPetStatsArray[userInteractionChoice]) { //checks if the pet's stat is already maxed
                        System.out.println("You are already maxed out in this stat!");
                    }
                    else {
                        userMoney -= 1;
                        currentPetStatsArray[userInteractionChoice] += 1;
                        petInteractionHistory[userInteractionChoice] += 1;
                        System.out.println("Your pet's " + petStatsNameArray[userInteractionChoice] + " stat has been increased to " + currentPetStatsArray[userInteractionChoice] + "!");
                    }
                }
            } // playPet if loop
        }
    }

    //Helper Methods
    public static void drawStartScreen() {
        //Title
        System.out.println("                 Cat Simulator     \n");
        
        //Splash Art
        System.out.println("          *        &&&&&&&&&        *");
        System.out.println("                  &&&&&&&&&&&");
        System.out.println("                 &&&&&&&&&&&&&             *");
        System.out.println("   *             &&&&&&&&&&&&&");
        System.out.println("                 &&&&&&&&&&&&&   *");
        System.out.println("         *        &&&&&&&&&&&");
        System.out.println("                   &&&&&&&&&           *");
        System.out.println("          |\\___/|     /\\___/\\");
        System.out.println("          )     (     )    ~(");
        System.out.println("         =\\     /=   =\\~    /=");
        System.out.println("           )===(       ) ~ (");
        System.out.println("          /     \\     /     \\");
        System.out.println("          |     |     ) ~   (");
        System.out.println("         /       \\   /     ~ \\");
        System.out.println("         \\       /   \\~     ~/");
        System.out.println("  _/\\_/\\_/\\__  _/_/\\_/\\__~__/_/\\_/\\_/\\_/\\_/\\_");
        System.out.println("  |  |  |  |( (  |  |  | ))  |  |  |  |  |  |");
        System.out.println("  |  |  |  | ) ) |  |  |//|  |  |  |  |  |  |");
        System.out.println("  |  |  |  |(_(  |  |  (( |  |  |  |  |  |  |");
        System.out.println("  |  |  |  |  |  |  |  |\\)|  |  |  |  |  |  |");
        System.out.println("  |  |  |  |  |  |  |  |  |  |  |  |  |  |  |");
    }
    
    public static boolean login(boolean userBooleanData[], String userStringData[], int maxPetStatsArray[], int currentPetStatsArray[], int userMoneyStorage[]) {
        Scanner kb = new Scanner(System.in);
        
        //Variables
        int incorrectPasswordCount = 0;   
        boolean entryAllowed = false;
        
        //Ask user for inputs
        System.out.print("Username: ");
        String userName = kb.nextLine();
        
        //Checks if a file exists and either creates an account or makes the user login accordingly
        File f = new File(userName + ".txt");
        
        if (f.exists() == false) {
            System.out.print("Password: ");
            String userPassword = kb.nextLine();
            entryAllowed = true;
            userStringData[0] = userName;
            userStringData[1] = userPassword;
        }
        else {
            try {
                Scanner input = new Scanner(f);
                
                userBooleanData[0] = input.nextBoolean();
                userBooleanData[1] = input.nextBoolean();
                input.nextLine();
                
                String correctUsername = input.nextLine();
                String correctPassword = input.nextLine();
                userStringData[2] = input.nextLine();
                userStringData[3] = input.nextLine();
                
                maxPetStatsArray[0] = input.nextInt();
                maxPetStatsArray[1] = input.nextInt();
                maxPetStatsArray[2] = input.nextInt();
                
                currentPetStatsArray[0] = input.nextInt();
                currentPetStatsArray[1] = input.nextInt();
                currentPetStatsArray[2] = input.nextInt();
                
                userMoneyStorage[0] = input.nextInt();
                
                //3 attempts for the user to get their login right
                while (incorrectPasswordCount < 3 && entryAllowed == false) {
                    System.out.print("Password: ");
                    String userPassword = kb.nextLine();
                    
                    if (userName.equals(correctUsername) && userPassword.equals(correctPassword)) {
                        entryAllowed = true;
                        userStringData[0] = userName;
                        userStringData[1] = userPassword;
                    }
                    else {
                        incorrectPasswordCount += 1;
                        System.out.println("Incorrect!");
                    }
                }
            }
            catch (Exception e) {
                System.out.println(e);
            }
            
            if (incorrectPasswordCount == 3) {
                System.out.println("Please wait a few minutes before trying to login again.");
            }
        }
        
        return entryAllowed;
    }
    
    public static boolean petNotCreated(boolean petCreated, boolean userBooleanData[], String userStringData[], int maxPetStatsArray[], int currentPetStatsArray[], int userMoney) {
        Scanner kb = new Scanner(System.in);
        
        //Provides menu options
        System.out.println("\n  1. Start     2. Instructions     3. Exit"   );
        System.out.print("\nWhere would you like to go? ");
        String userMenuOption = kb.nextLine();
        userMenuOption = userMenuOption.toLowerCase();

        //Accepts user inputs
        switch (userMenuOption) {
            case "1": 
            case "start":
                petCreated = true;
                break;
            case "2": 
            case "instructions":
                System.out.println("CS sucks");
                break;
            case "3":
            case "exit":
                saveUserData(userBooleanData, userStringData, maxPetStatsArray, currentPetStatsArray, userMoney);
                System.exit(0);
                break;
        }
        
        return petCreated;
    }
    
    public static boolean petIsCreated(boolean playPet, boolean userBooleanData[], String userStringData[], int maxPetStatsArray[], int currentPetStatsArray[], int userMoney, int petInteractionHistory[]) {
        Scanner kb = new Scanner(System.in);
        
        //Provides menu options
        System.out.println("\n  1. Play/Interact     2. Instructions     3. Exit"   );
        System.out.print("\nWhere would you like to go? ");
        String userMenuOption = kb.nextLine();
        userMenuOption = userMenuOption.toLowerCase();

        //Accepts user inputs
        switch (userMenuOption) {
            case "1": 
            case "play":
            case "interact":
                playPet = true;
                break;
            case "2": 
            case "instructions":
                playPet = false;
                System.out.println("CS sucks");
                break;
            case "3":
            case "exit":
                summaryOfTheDay(petInteractionHistory);
                saveUserData(userBooleanData, userStringData, maxPetStatsArray, currentPetStatsArray, userMoney);
                System.exit(0);
                break;
         }
        
        return playPet;
    }
    
    public static String createPet() {
        Scanner kb = new Scanner(System.in);
        
        //Pet Selection
        System.out.println("Cat, Dog, Rabbit");
        System.out.print("What pet would you like to choose? ");
        String petSelected = kb.nextLine();

        petSelected = petSelected.toLowerCase();
        while ((petSelected.equals("cat") == false) && (petSelected.equals("dog") == false) && (petSelected.equals("rabbit") == false)) {
            System.out.println("Invalid Choice of Pet!");
        }
        
        //Ouput and returns the user's choice
        System.out.println("You have selected the " + petSelected);
        return petSelected;
    }
    
    public static String namePet() {
        Scanner kb = new Scanner(System.in);
        Random r = new Random();
        
        //Variables
        String petName = "";
        final int MIN_NAME_LENGTH = 4;
        
        //Asks the user what they want to do
        System.out.print("Would you like to name the pet yourself? ");
        String customName = kb.nextLine();
        customName = customName.toLowerCase();

        //If the user wants to name the pet themselves
        if (customName.equals("yes") == true) {
            System.out.print("What would you like to name your pet: ");
            petName = kb.nextLine();
        }
        else { //Generates a custom name if they don't
            String vowelList = "aeiou";
            String consonantList = "bcdfghjklmnpqrstvwxyz";

            int maxLetterCount = r.nextInt(5) + MIN_NAME_LENGTH;

            for (int i = 0; i < maxLetterCount; i = i + 2) {
                petName += consonantList.charAt(r.nextInt(21));
                if (r.nextDouble() < 0.1) {
                    petName += petName.charAt(petName.length()-1);
                    i += 1;
                }

                petName += vowelList.charAt(r.nextInt(5));
                if (r.nextDouble() < 0.1) {
                    petName += petName.charAt(petName.length()-1);
                    i += 1;
                }
            }
        }
        
        //Output and returns the pet's name
        System.out.println("Your pet, named " + petName + ", has been born!");
        return petName;
    }
    
    public static int[] petStats() {
        Random r = new Random();
        
        //Variables
        final int MAX_PET_STATS = 14;
        int maxPetStatsArray[] = {2, 2, 2}; 
        
        //Generates the pet's max stats
        for (int i = 0; i < MAX_PET_STATS; i++) {
            maxPetStatsArray[r.nextInt(3)] += 1;
        } 
        
        //Outputs and returns the pet's max stats
        System.out.println("Max Pet Health: " + maxPetStatsArray[0] + "\nMax Pet Hunger: " + maxPetStatsArray[1] + "\nMax Pet Energy: " + maxPetStatsArray[2]);
        return maxPetStatsArray; 
    }
    
    public static int numberGuessingGame(int userMoney) {
        Scanner kb = new Scanner(System.in);
        Random r = new Random();
        
        //Variables
        int numberToBeGuessed = (r.nextInt(100) + 1);
        boolean numberGuessed = false;
        int numberOfIncorrectGuesses = 0;
        int moneyEarned = 100;
        
        //Checks if the user's guess is correct, gives them feedback if it isn't
        while ((numberGuessed == false) && (numberOfIncorrectGuesses < 8)) {
            System.out.print("Guess a number: ");
            int userGuess = kb.nextInt();

            if (userGuess < numberToBeGuessed) {
                System.out.println("Too Low");
                moneyEarned /= 2;
                numberOfIncorrectGuesses += 1;
            }
            else if (userGuess > numberToBeGuessed) {
                System.out.println("Too High");
                moneyEarned /= 2;
                numberOfIncorrectGuesses += 1;
            }
            else {
                System.out.println("Correct! Congragulations you have earned $" + moneyEarned);
                userMoney += moneyEarned;
                System.out.println("You currently have $" + userMoney);
                numberGuessed = true;
            }
        }
        
        //Tells the user if they've made too many incorrect guesses
        if (numberOfIncorrectGuesses == 8) {
            System.out.println("Too many incorrect guesses. You lose!");
        }
        
        return userMoney;
    }
    
    public static int letterUnscrambleGame(int userMoney) {
        Scanner kb = new Scanner(System.in);
        Random r = new Random();
        
        //Variables
        String stringLetterList = "AABBCCDDEE";
        String stringListHidden = "**********";
        String stringToBeGuessed = "";
        int moneyEarned = 100;
        
        //Creates a scrambled string
        while (stringLetterList.equals("") == false) {
            int letterIndex = r.nextInt(stringLetterList.length());
            stringToBeGuessed += stringLetterList.charAt(letterIndex);
            stringLetterList = (stringLetterList.substring(0, letterIndex) + stringLetterList.substring(letterIndex + 1));
        }
        
        //Checks if the user's guess matches the hidden, scrambled string
        while (stringListHidden.equals(stringToBeGuessed) == false) {
            System.out.print("Guess your first index: ");
            int firstIndexGuessed = kb.nextInt();


            System.out.print("Guess your second index: ");
            int secondIndexGuessed = kb.nextInt();

            if (stringToBeGuessed.charAt(firstIndexGuessed) == stringToBeGuessed.charAt(secondIndexGuessed)) {
                stringListHidden = (stringListHidden.substring(0, Math.min(firstIndexGuessed, secondIndexGuessed)) + stringToBeGuessed.charAt(firstIndexGuessed) + stringListHidden.substring(Math.min(firstIndexGuessed, secondIndexGuessed) + 1, Math.max(firstIndexGuessed, secondIndexGuessed)) + stringToBeGuessed.charAt(firstIndexGuessed) + stringListHidden.substring(Math.max(firstIndexGuessed, secondIndexGuessed) + 1));
            }
            else {
                System.out.println("Letter at index " + firstIndexGuessed + " is " + stringToBeGuessed.charAt(firstIndexGuessed));
                System.out.println("Letter at index " + secondIndexGuessed + " is " + stringToBeGuessed.charAt(secondIndexGuessed));
                moneyEarned /= 2;
            } 
            
            System.out.println(stringListHidden);
        }
        
        //Congragulates the user if they won
        System.out.println("You solved it! Congragulations you have earned " + moneyEarned);
        userMoney += moneyEarned;
        System.out.println("You currently have $" + userMoney); 
        
        return userMoney;
    }
    
    public static void saveUserData(boolean userBooleanData[], String userStringData[], int maxPetStatsArray[], int currentPetStatsArray[], int userMoney) {
        File userFile = new File(userStringData[0] + ".txt");
        
        try {
            PrintWriter output = new PrintWriter(userFile);
        
            //Iterates through arrays, saving data to a File
            for (int i = 0; i < 2; i++) {
                output.println(userBooleanData[i]);
            }
            for (int i = 0; i < 4; i++) {
                output.println(userStringData[i]);
            }
            for (int i = 0; i < 3; i++) {
                output.println(maxPetStatsArray[i]);
            }
            for (int i = 0; i < 3; i++) {
                output.println(currentPetStatsArray[i]);
            }
            output.println(userMoney);
            output.close();
        }
        catch (Exception e) {
            System.out.println("An error has occured.");
        }
    }
    
    public static void summaryOfTheDay(int petInteractionHistory[]) {
        System.out.println("\nToday you have: ");
        
        //Ouputs activity history and their respectitive awards if the user has earned it
        System.out.println("Groomed your pet " + petInteractionHistory[0] + " times.");
        if (petInteractionHistory[0] > 5) {
            System.out.println("Congragulations you have earned the 'Long Life Ahead' Award!!");
        }
        System.out.println("Fed your pet " + petInteractionHistory[1] + " times.");
        if (petInteractionHistory[0] > 5) {
            System.out.println("Congragulations you have earned the 'Avid Eater' Award!!");
        }
        System.out.println("Played with your pet " + petInteractionHistory[2] + " times.");
        if (petInteractionHistory[0] > 5) {
            System.out.println("Congragulations you have earned the 'Full of Energy' Award!!");
        }
    }
}
