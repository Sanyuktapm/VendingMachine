
/**
 * The Customer class is for the Goodies assignments. 
 * This class helps control the functions of a customer in the VendMachine class's simulation of a vending machine.
 * The customer can request to purchase items, which may be denied if the items are not available or accepted, to which the program will change the data about the vending machine.
 * This class utilizes the data.txt file to read and write information about the vending machine.
 *
 * @Sanyukta Prakash Mudakannavar
 * @java 1.8.0 - 12/6/19
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class Customer
{
    private String[] drinks = new String[9]; //names of items
    private double[] price1 = new double[9]; //sale price of items
    private double[] cost1 = new double[9]; //cost of items (for owner), not changed in this class
    private double[] sales1 = new double[9];
    private int[] stock1 = new int[9]; //amount of items

    private String[] snacks = new String[9]; //names of items
    private double[] price2 = new double[9]; //sale price of items
    private double[] cost2 = new double[9]; //cost of items (for owner), not changed in this class
    private double[] sales2 = new double[9];
    private int[] stock2 = new int[9]; //amount of items

    private BusinessOperations business;
    private String nameofmenu; //name of the menu being used
    private int numofitem; //number of the item of the good
    private int quantity; //quantity of the chosen item
    private boolean isAvailable = true; //keeps track of whether the chosen item is available

    public Customer()
    {        

        File file = new File("data.txt");

        try {
            Scanner scanner = new Scanner(file);

            int i = 0;
            while (scanner.hasNextLine()) { //loop runs as long as there are more lines of text
                String line = scanner.nextLine();

                while (i < 9){ //loop runs as long as the drink array is empty

                    if (line.equals("Drinks")) line = scanner.nextLine();

                    String[] splitted = line.split(","); //each line is split
                    drinks[i] = splitted[0]; //first section of the split line is the name of the drink  
                    stock1[i] = Integer.parseInt(splitted[1]); //second section of the split line is the quantity/stock
                    cost1[i] = Double.parseDouble(splitted[2]);  //third section of the split line is the cost (for the owner)
                    price1[i] = Double.parseDouble(splitted[3]); //fourth section of the split line is the sales price
                    if (splitted.length == 5) sales1[i] = Double.parseDouble(splitted[4]);
                    else sales1[i] = 0;
                    //^^info about sales is not be available the first time the raw data file is used
                    //^^if info about sales is not available, it will just be set to 0

                    i = i + 1;
                    if (scanner.hasNextLine()) line = scanner.nextLine();
                }
                i = 0; 

                while (i < 9){
                    if (line.equals("Snacks")) line = scanner.nextLine();

                    String[] splitted = line.split(","); //each line is split
                    snacks[i] = splitted[0]; //first section of the split line is the name of the snack  
                    stock2[i] = Integer.parseInt(splitted[1]); //second section of the split line is the quantity/stock
                    cost2[i] = Double.parseDouble(splitted[2]); //third section of the split line is the cost (for the owner)
                    price2[i] = Double.parseDouble(splitted[3]); //fourth section of the split line is the sales price
                    if (splitted.length == 5) sales2[i] = Double.parseDouble(splitted[4]);
                    else sales2[i] = 0;
                    //^^info about sales is not be available the first time the raw data file is used
                    //^^if info about sales is not available, it will just be set to 0

                    i = i + 1;
                    if (scanner.hasNextLine()) line = scanner.nextLine();

                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    } //reads Data file and copies it to the arrays

    public void order(String choice, int num, int quantityofgoods){ 
        //^^choice is the name of the menu, num is the number of the item, quantity is the desired amount of items

        nameofmenu = choice; //nameofmenu variable is set to the argument "choice"
        quantity = quantityofgoods;
        numofitem = num - 1; //numofitem variable is set to the argument "num"

        if (nameofmenu.equals("drinks")) { 
            if (quantity > stock1[numofitem]) {//if the stock if the item is empty, isAvailable is false
                System.out.println("Sorry, your selected item is not available.");
                isAvailable = false;
            }
            else {
                System.out.println("Your order has been confirmed~");
                double price = (double)quantity * price1[numofitem]; //price of request
                System.out.printf("%s\n%s\n%s%.2f", "Item: " + drinks[numofitem], "Number of Units: " + quantity, "Cost: " , price);
                System.out.println("\nThank you!");
                stock1[numofitem] = stock1[numofitem] - quantity; //stock is changed after purchase
                sales1[numofitem] = sales1[numofitem] + price; //changes sales
            }
        } 
        if (nameofmenu.equals("snacks")) {
            if (quantity > stock2[numofitem]){ //if the stock if the item is empty, isAvailable is false
                System.out.println("Sorry, your selected item is not available.");
                isAvailable = false;
            }

            else {
                System.out.println("Your order has been confirmed~");
                double price = (double)quantity * price2[numofitem];//price of request
                System.out.printf("%s\n%s\n%s%.2f", "Item: " + snacks[numofitem], "Number of Units: " + quantity, "Cost: " , price);
                System.out.println("\nThank you!");
                stock2[numofitem] = stock2[numofitem] - quantity; //stock is changed after purchase
                sales2[numofitem] = sales2[numofitem] + price; //changes sales
            }
        }
        //^^ if-statements set the menu arrays to the holder arrays
    }

    public boolean isAvailable(){
        return isAvailable;
    }

    public void EditandSet(){ 
        //sets the original arrays to the holder arrays 
        //rewrites the data file with the new arrays
        String myFile = "data.txt";
        try{
            FileWriter write = new FileWriter(myFile);
            PrintWriter print_line = new PrintWriter(write);

            print_line.printf("Drinks\n");
            for (int i = 0; i < drinks.length; i++){
                print_line.printf("%s%s%s%s%s\n", drinks[i] + ",", stock1[i] + ",", cost1[i] + ",", price1[i] + ",", sales1[i]);
            }

            print_line.printf("Snacks\n");
            for (int i = 0; i < snacks.length; i++){
                print_line.printf("%s%s%s%s%s\n", snacks[i] + ",", stock2[i] + ",", cost2[i] + ",", price2[i] + ",", sales2[i]);
            }

            print_line.close();
        }
        catch (IOException e) { System.out.println("It doesn't work");       
        }
    }

    public void display(){ //displays the menus, item numbers, item names, and price of items
        System.out.println();
        System.out.printf("%-26s %s", "ITEMS", "PRICE ($)");
        System.out.println("\n\nDrinks");
        for (int i = 0; i < drinks.length; i++){
            System.out.printf("%-5s %-25s %-25.2f", (i+1) + ".", drinks[i], price1[i]);
            System.out.println(); //printf format to show drinks
        }

        System.out.println("\n\nSnacks");
        for (int i = 0; i < drinks.length; i++){
            System.out.printf("%-5s %-25s %-25.2f", (i+1) + ".", snacks[i], price2[i]);
            System.out.println(); //printf format to show snacks
        }
    }
}
