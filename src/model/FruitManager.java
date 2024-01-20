package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FruitManager {

    private static ArrayList<Fruit> fruitManage = new ArrayList<>();
    private static ArrayList<Order> fruitOrder = new ArrayList<>();
    private static Map<String, ArrayList<Order>> ordersHashMap = new HashMap<>();

    static Scanner sc = new Scanner(System.in);
    static File file = new File("src\\");
    static String path = file.getAbsolutePath();

    public FruitManager(String path, String fName) {
        loadData("\\customer.txt");
    }

    public static ArrayList<Fruit> getFruitManage() {
        return fruitManage;
    }

    public static void setFruitmanage(ArrayList<Fruit> fruitmanage) {
        FruitManager.fruitManage = fruitmanage;
    }

    public static ArrayList<Order> getFruitOrder() {
        return fruitOrder;
    }

    public static void setFruitOrder(ArrayList<Order> fruitOrder) {
        FruitManager.fruitOrder = fruitOrder;
    }

    public static void loadData(String fName) {
        String fruitInfo;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path + fName));
            while ((fruitInfo = br.readLine()) != null) {
                String[] b = fruitInfo.split(" \\| ");

                if (b.length == 5) {
                    String id = b[0].trim();
                    String name = b[1].trim();
                    Double prices = Double.valueOf(b[2].trim());
                    int quantity = Integer.parseInt(b[3].trim());
                    String origin = b[4].trim();

                    fruitManage.add(new Fruit(id, name, prices, quantity, origin));
                }
            }
            br.close();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    public static void saveData(String fName) {
        try (PrintWriter pr = new PrintWriter(path + fName)) {

            for (Fruit s : fruitManage) {

                String line = String.format("%-5s | %-15s | %-10.2f | %-8d | %-15s",
                        s.getFruitId(), s.getFruitName(), s.getFruitPrice(), s.getQuantity(), s.getOrigin());
                pr.println(line);
            }
            pr.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void printFruitList() {
        if (fruitManage.isEmpty()) {
            System.out.println("No fruit found.");
        } else {
            System.out.printf("%-5s | %-15s | %-10s | %-8s | %-15s%n", "ID", "Product", "Price", "Quantity", "Origin");
            for (Fruit st : fruitManage) {
                System.out.println(st.toString());
            }
        }
    }

    public static void addNewFruit() {
        System.out.println("Enter the infomation of fruit:");

        String id = Utils.enterID("ID: ");
//        System.out.print("Fruit name: ");
//        String name = sc.nextLine();
//
//        System.out.print("Price: ");
//        Double price = sc.nextDouble();
//
//        System.out.print("Quantity: ");
//        int quantity = sc.nextInt();
//
//        sc.nextLine();
//
//        System.out.print("Origin: ");
//        String origin = sc.nextLine();
        String name = Utils.getAndValidValue("Fruit name: ", "[\\pL]+", "Fruit name just contain letter").toUpperCase();
        Double price = Utils.getAndValidMoney("Price: ");
        int quantity = Integer.parseInt(Utils.getAndValidValue("Quantity: ", "[\\d]+", "Quantity just contain number"));
        String origin = Utils.getAndValidValue("Origin: ", "[\\pL]+", "Origin just contain letter").toUpperCase();

        fruitManage.add(new Fruit(id, name, price, quantity, origin));
        System.out.println("New fruit added successfully!");
    }

    public static void updatePruduct(String id, int quantity) {
        for (Fruit fruit : fruitManage) {
            if (fruit.getFruitId().equals(id)) {
                fruit.setQuantity(fruit.getQuantity() + quantity);
                return;
            }
        }
    }

    public static void itemAfterSelling(String id, int qOrder) {
        for (Fruit f : fruitManage) {
            if (f.getFruitId().equals(id)) {
                do {
                    if (f.getQuantity() >= qOrder) {
                        f.setQuantity(f.getQuantity() - qOrder);
                        System.out.println("Successfully");
                        return;
                    } else {
                        System.out.println("fail.");
                        System.out.print("Enter quantity again: ");
                        qOrder = sc.nextInt();
                    }
                } while (true);
            }
        }
    }

    public static void updateOrderProduct(String id, int quantity) {
        for (Order order : fruitOrder) {
            if (order.getFruitId().equals(id)) {
                order.setQuantityOders(order.getQuantityOders() + quantity);
                return;
            }
        }
    }

    public static void chooseProduct(String id, int quantity, String key) {
        for (Fruit f : fruitManage) {
            if (f.getFruitId().equals(id)) {
                System.out.println("You selected: " + f.getFruitName());
                System.out.println("Quantity: " + quantity);
                itemAfterSelling(id, quantity);
                Order order = new Order();
                order.setValuesFromFruit(f);
                order.setQuantityOders(quantity);

                ArrayList<Order> orderFruit = new ArrayList<>();

                ordersHashMap.putIfAbsent(key, orderFruit);
                ordersHashMap.get(key).add(order);

            }
        }
    }

    public static void displayListOrder(ArrayList<Order> lo) {
        double total = 0;
        System.out.printf("%-15s | %-10s | %-10s | %-10s%n", "Product", "Quantity", "Price", "Amount");
        for (Order order : lo) {

            System.out.printf("%-15s | %-10d | %-10.2f | %10.0f$%n",
                    order.getFruitName(), order.getQuantityOders(), order.getPrice(),
                    order.getPrice() * order.getQuantityOders());

            total += order.getPrice() * order.getQuantityOders();
        }
        System.out.println("Total: " + total);
    }

    private static void processDuplicateIds(ArrayList<Order> orders) {
        Map<String, ArrayList<Order>> idMap = new HashMap<>();

        for (Order order : orders) {
            String id = order.getFruitId();

            if (idMap.containsKey(id)) {
                ArrayList<Order> existingOrders = idMap.get(id);
                boolean found = false;

                for (Order existingOrder : existingOrders) {
                    if (existingOrder.getFruitName().equals(order.getFruitName())) {
                        existingOrder.setQuantityOders(existingOrder.getQuantityOders() + order.getQuantityOders());
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    existingOrders.add(order);
                }
            } else {
                ArrayList<Order> newOrders = new ArrayList<>();
                newOrders.add(order);
                idMap.put(id, newOrders);
            }
        }
        orders.clear();
        for (ArrayList<Order> value : idMap.values()) {
            orders.addAll(value);
        }
    }

    public static void viewOrders() {
        for (String name : ordersHashMap.keySet()) {
            System.out.println("Customer: " + name);
            ArrayList<Order> product = ordersHashMap.get(name);
            processDuplicateIds(product);
            displayListOrder(product);
        }
    }

    public static void viewCustomer(String key) {
        System.out.println("Customer: " + key);
        ArrayList<Order> product = ordersHashMap.get(key);
        processDuplicateIds(product);
        displayListOrder(product);
    }

}
