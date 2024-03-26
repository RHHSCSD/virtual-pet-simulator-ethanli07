/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package virtualpet;
import java.util.*;

/********************************************
* Program: Virtual Pet Simulator
* Date: March 18, 2024
* Author: Ethan Li
* Description: Simulates ownership of a pet
*********************************************/

public class VirtualPet {
    public static void main(String[] args) {
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
        
        //Initialize Objects
        Scanner kb = new Scanner(System.in);
        Random r = new Random();
        
        //Variables
        final String GAME_NAME = "snoopy";
        final String GAME_PASSWORD = "toto";
        final int MIN_NAME_LENGTH = 4;
        
        boolean entryAllowed = false;
        boolean startGame = false;
        boolean petIsChosen = false;
        
        //Login 
        System.out.print("Username: ");
        String userName = kb.nextLine();
        
        System.out.print("Password: ");
        String userPassword = kb.nextLine();
        
        if (userName.equals(GAME_NAME) && userPassword.equals(GAME_PASSWORD)) {
            entryAllowed = true;
        }
        
        //Menu Options
        if (entryAllowed == true) {
            System.out.println("\n  1. Start     2. Instructions     3. Exit"   );
            System.out.print("\nWhere would you like to go? ");
            String userMenuOption = kb.nextLine();
            userMenuOption = userMenuOption.toLowerCase();

            //Menu
            switch (userMenuOption) {
                case "1": 
                case "start":
                    startGame = true;
                    break;
                case "2": 
                case "instructions":
                    break;
                case "3":
                case "exit":
                    System.exit(0);
                    break;
            }
        }
        
        if (startGame == true) {
            //Pet Selection
            System.out.println("Cat, Dog, Rabbit");
            System.out.print("What pet would you like to choose? ");
            String petSelected = kb.nextLine();
            
            petSelected = petSelected.toLowerCase();
            if (petSelected.equals("cat") || petSelected.equals("dog") || petSelected.equals("rabbit")) {
                
                System.out.println("You have selected the " + petSelected);
                petIsChosen = true;
            }
            else {
                System.out.println("Invalid Choice of Pet!");
            }
            
            if (petIsChosen == true) {
                System.out.print("Would you like to name the pet yourself? ");
                String customName = kb.nextLine();
                customName = customName.toLowerCase();
                
                String petName = "";
                
                if (customName.equals("yes") == true) {
                    System.out.print("What would you like to name your pet: ");
                    petName = kb.nextLine();
                }
                else {
                    String vowelList = "aeiou";
                    String consonantList = "bcdfghjklmnpqrstvwxyz";
                    
                    int maxLetterCount = r.nextInt(5) + MIN_NAME_LENGTH;
                 
                    for (int i = 0; i < maxLetterCount; i = i + 2) {
                        petName += consonantList.charAt(r.nextInt(21));
                        if (r.nextDouble() < 0.2) {
                            petName += petName.charAt(petName.length()-1);
                            i += 1;
                        }
                        
                        petName += vowelList.charAt(r.nextInt(5));
                        if (r.nextDouble() < 0.2) {
                            petName += petName.charAt(petName.length()-1);
                            i += 1;
                        }
                    }
                }
                
                System.out.println("Your pet, named " + petName + ", has been born!");
            }
            else {
            }
           
        }
    }
}
