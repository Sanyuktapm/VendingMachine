/**
 * The VendMachine class is for the Goodies assignments.
 * This class is a directory for other classes - it uses the Customer class and the BusinessOperations class to simulate a vending machine.
 * In this class, the user can purchase items as a shopper or manage their business as a manager.
 * 
 *
 * @Sanyukta Mudakannavar
 * @java 1.8.0 - 12/6/19
 */
import java.util.Scanner;
public class VendMachine
{
    public static void main(){
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to the Goodie Good vending machine!\n");

        int a = 1; //keeps track of different services of the vending machine
        int command = 0; //this is the number the consumer enters at the main menu screen
        while (a == 1){ //runs as long as a = 1, which isn't changed unless user asks to quit
            System.out.println("YOUR OPTIONS...\n1. SHOPPER - purchase an item\n2. BUSINESS - manage the business\n3. QUIT - quit the vending machine\n\nEnter [1, 2, or 3]");
            command = scan.nextInt();
            if (command == 1) {
                int b = 0; 
                Customer test = new Customer();

                test.display();
                System.out.println("\nWhich menu will you purchase from? [drinks or snacks]");
                scan.nextLine(); //prevents the nextLine() method from reading the newline as the next line
                String menu = scan.nextLine();

                System.out.println("What is the number of the item that you'd like to purchase?");
                int numofitem = scan.nextInt();

                System.out.println("How many units of the item would you like?");
                int quantity = scan.nextInt();
                System.out.println();

                test.order(menu, numofitem, quantity); 
                test.EditandSet(); //changes that order has caused must be set

                System.out.println("\n");
            }
            if (command == 2){
                BusinessOperations manage = new BusinessOperations();
                System.out.println("Select one of the following:\n1. show inventory\n2. change quantity of item\n3. change sale price of item\n4. change cost of item\n5. show the menus as seen by the customer");
                int tracker = scan.nextInt();
                if (tracker == 1) manage.inventory();
                if (tracker == 2) {
                    System.out.println("\nWhich menu would you like to edit?");
                    scan.nextLine(); //prevents the nextLine() method from reading the newline as the next line
                    String menu = scan.nextLine();

                    System.out.println("What is the number of the item you'd like to change the stock of?");
                    int numofitem = scan.nextInt();

                    System.out.println("What would you like to change the stock to?");
                    int stock = scan.nextInt();

                    manage.changeStock(menu, numofitem, stock);
                    manage.EditandSet(); //changes that requests have made to the arrays must be set

                    System.out.println("Done!");
                }
                if (tracker == 3) {
                    System.out.println("\nWhich menu would you like to edit?");
                    scan.nextLine(); //prevents the nextLine() method from reading the newline as the next line
                    String menu = scan.nextLine();

                    System.out.println("What is the number of the item you'd like to change the sale price of?");
                    int numofitem = scan.nextInt();

                    System.out.println("What would you like to change the sale price to?");
                    int saleprice = scan.nextInt();

                    manage.changeSalePrice(menu, numofitem, saleprice);
                    manage.EditandSet(); //changes that requests have made to the arrays must be set

                    System.out.println("Done!");
                }
                if (tracker == 4) {
                    System.out.println("\nWhich menu would you like to edit?");
                    scan.nextLine(); //prevents the nextLine() method from reading the newline as the next line
                    String menu = scan.nextLine();

                    System.out.println("What is the number of the item you'd like to change the cost of?");
                    int numofitem = scan.nextInt();

                    System.out.println("What would you like to change the cost to?");
                    int cost = scan.nextInt();

                    manage.changeCost(menu, numofitem, cost);
                    manage.EditandSet(); //changes that requests have made to the arrays must be set

                    System.out.println("Done!");
                }
                if (tracker == 5){
                    manage.menus();
                }
            }
            if (command == 3) a = 0; //ends program
            System.out.println();
        }
        System.out.println("It was a pleasure to serve you! Hope to see you again!\n"); //thank you message
    }
}
