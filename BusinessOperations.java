
/**
 * BusinessOperations is a class for the Goodies assignment. 
 * This class is designed to show the necessary options and provide the necessary commands to the manager of the vending machine.
 * These commands include showing inventory of the goods, changing the sale price of an item, changing the stock of an item, and changing the cost of an item.
 * This class utilizes the data.txt file to read and write information about the vending machine.
 *
 * @Sanyukta Mudakannavar
 * @java 1.8.0 - 12/6/19
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class BusinessOperations
{
    private String[] drinks = new String[9]; //names of items
    private double[] price1 = new double[9]; //sale price of items
    private double[] cost1 = new double[9]; //cost of items (for owner)
    private double[] sales1 = new double[9];
    private int[] stock1 = new int[9]; //amount of items

    private String[] snacks = new String[9]; //names of items
    private double[] price2 = new double[9]; //sale price of items
    private double[] cost2 = new double[9]; //cost of items (for owner)
    private double[] sales2 = new double[9];
    private int[] stock2 = new int[9]; //amount of items

    private String menu;
    private int numofitem;
    private double cost;
    private double stock;
    private Customer version;
    public BusinessOperations(){

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
        
        version = new Customer();
    } //reads Data file and copies it to the arrays

    public void inventory(){
        System.out.println();
        System.out.printf("%-20s %-15s %-15s %-20s %-20s", "ITEMS", "STOCK", "COST($)", "SALE PRICE($)", "SALES($)");
        System.out.println("\n\nDrinks\n");
        for (int i = 0; i < drinks.length; i++){
            System.out.printf("%-20s %-15d %-15.2f %-20.2f %-15.2f", (i+1) + ". " + drinks[i], stock1[i], cost1[i], price1[i], sales1[i]);
            System.out.println(); //printf format^^; prints the drinks menu
        }

        System.out.println("\n\nSnacks\n");
        for (int i = 0; i < drinks.length; i++){
            System.out.printf("%-20s %-15d %-15.2f %-20.2f %-15.2f", (i+1) + ". " + snacks[i], stock2[i], cost2[i], price2[i], sales2[i]);
            System.out.println(); //printf format^^; prints the snacks menu
        }
    }

    public void menus(){
        version.display(); //calls customer to show menus from customer's perspective
    }
    
    public void changeSalePrice(String nameofmenu, int numberofitem, double newprice){
        if (nameofmenu.equals("drinks")) {
            price1[numberofitem - 1] = newprice; //it's numberofitem - 1 because the index is one less than the number that the user requested
        }
        if (nameofmenu.equals("snacks")) {
            price2[numberofitem - 1] = newprice;
        }
        System.out.println("i did this" + price1[numberofitem - 1] + newprice);
        EditandSet(); //confirm the changes
    }

    public void changeStock(String nameofmenu, int numberofitem, int newquantity){
        if (nameofmenu.equals("drinks")) {
            stock1[numberofitem - 1] = newquantity; //it's numberofitem - 1 because the index is one less than the number that the user requested
        }
        if (nameofmenu.equals("snacks")) {
            stock2[numberofitem - 1] = newquantity;
        }
        EditandSet(); //confirm the changes
    }

    public void changeCost(String nameofmenu, int numberofitem, double newcost){
        if (nameofmenu.equals("drinks")) {
            cost1[numberofitem - 1] = newcost; //it's numberofitem - 1 because the index is one less than the number that the user requested
        }
        if (nameofmenu.equals("snacks")) {
            cost2[numberofitem - 1] = newcost;
        }
        EditandSet(); //confirm the changes
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

}