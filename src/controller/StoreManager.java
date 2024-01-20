package controller;

import java.util.Scanner;
import model.FruitManager;
import model.Utils;
import view.Menu;

public class StoreManager extends Menu<String> {

    static Scanner sc = new Scanner(System.in);
    static String[] menu = {"Manage Fruit ", "View orders ", "Shopping (for buyer) ", "Exit "};
    static String fName = "\\fruitshop.txt";

    public StoreManager() {
        super("FRUIT SHOP SYSTEM", menu);
        FruitManager.loadData(fName);
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1 -> {
                manageFruit();
            }
            case 2 -> {
                FruitManager.viewOrders();
            }
            case 3 -> {
                shopping();
            }
            case 4 -> {
                System.out.println("Exiting...");
            }
            default -> {
                System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    public static void main(String[] args) {
        StoreManager st = new StoreManager();
        st.run();
    }

    public static void manageFruit() {
        System.out.println("MANAGE FRUIT: ");
        System.out.println("1.Add fruit ");
        System.out.println("2.Update fruit ");

        System.out.print("Input your choice: ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> {
                createFruit();
            }
            case 2 -> {
                String id = Utils.getValue("Input product ID want to update: ");
                System.out.print("Please input quantity you want to update: ");
                int quantity = sc.nextInt();
                FruitManager.updatePruduct(id, quantity);

                FruitManager.saveData(fName);
                FruitManager.printFruitList();

            }
        }
    }

    public static void createFruit() {
        do {
            FruitManager.addNewFruit();
        } while (Utils.demand("Do you want to continue (Y/N)?"));

        FruitManager.saveData(fName);
        FruitManager.printFruitList();
    }

    public static void shopping() {
        FruitManager.printFruitList();
        String id;
        int quantity;
        String key = Utils.getValue("Enter your name: ");

        do {
            id = Utils.getValue("Input product ID want to buy: ");
            System.out.print("Please input quantity: ");
            quantity = sc.nextInt();
            FruitManager.chooseProduct(id, quantity, key);

        } while (!Utils.demand("Do you want to order now (Y/N) ?"));

        FruitManager.saveData(fName);
        FruitManager.viewCustomer(key);

    }

}
