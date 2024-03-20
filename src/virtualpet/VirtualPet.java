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
        
        //Menu Options
        System.out.println("\n  1. Start     2. Instructions     3. Exit"   );
        
        //Initialize Scanner
        Scanner kb = new Scanner(System.in);
        
        System.out.print("\nWhere would you like to go? ");
        String userMenuOption = kb.nextLine();
        
        switch (userMenuOption) {
            case "1": 
            case "Start":
                System.out.println("Cat, Dog, Rabbit");
                System.out.print("What pet would you like to choose? ");
                String petSelected = kb.nextLine();
                
                System.out.println("You have selected the " + petSelected);
                break;
            case "2": 
            case "Instructions":
                break;
            case "3":
            case "Exit":
                System.exit(0);
                break;
        }
        
    }
}
