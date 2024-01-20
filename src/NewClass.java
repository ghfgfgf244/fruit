////package model;
//
//import java.util.ArrayList;
//import java.util.Hashtable;
//import java.util.Scanner;
//import view.Validate;
//
//
//public class Order {
//    static Scanner sc = new Scanner(System.in);
//    static Validate valid = new Validate();
//    static ArrayList<Fruit> boughtItems = new ArrayList<>();
//    static FruitManager list = new FruitManager();
//    static Hashtable<String, ArrayList<Fruit>> customerOrderList = new Hashtable<>();
//    
//    public static void orderFruit(){
//        String continueOption;
//        do{
//            list.displayListFruit();
//            System.out.println("Please select product ID from 1 to"+list.getFruitList().size());
//            int choice = sc.nextInt();
//            String id = list.getFruitList().get(choice-1).getFruitID();
//            String fName = list.getFruitList().get(choice-1).getFruitName();
//            double price=list.getFruitList().get(choice-1).getPrice();
//            System.out.println("You selected: "+fName);
//            System.out.print("Please input quantity: ");
//            int quantity = sc.nextInt();
////            double amount = quantity*list.getFruitList().get(choice-1).getPrice();
//            Fruit f=new Fruit(id, fName, price, quantity);
//            boughtItems.add(f);
//            System.out.print("Do you want to order now (Y/N)? ");
//            continueOption = sc.nextLine();
//            if(continueOption.equalsIgnoreCase("Y")){
//                System.out.printf("%-16s %s", "Product","| Quantity | Price | Amount");
//                for (Fruit item:boughtItems) {
//                    System.out.printf("%-16s %s %-9s %s %.3f %-3s %s %.3f %-3s\n", item.getFruitName(), "  ", item.getQuantity(), "  ",item.getPrice(),"$","  ",item.getPrice()*item.getQuantity(),"$");
//                }
//                double total=0;
//                for(Fruit item:boughtItems){
//                    double amount = item.getPrice()*item.getQuantity();
//                    total+=amount;
//                }
//                System.out.println("Tota:"+total+"$");
//                String customerName=valid.getValue("Input your name:");
//                ArrayList<Fruit> customerOrder = customerOrderList.getOrDefault(customerName, new ArrayList<>());
//                customerOrder.addAll(boughtItems);
//                customerOrderList.put(customerName, customerOrder);
//                boughtItems.clear();
//            }
//        }
//        while(continueOption.equalsIgnoreCase("N"));
//    }
//    
//    public static void viewOrder(){
//        for(String customerName:customerOrderList.keySet()){
//            ArrayList<Fruit> order = customerOrderList.get(customerName);
//            System.out.println("Customer: "+customerName);
//            System.out.printf("%-16s %s", "Product","| Quantity | Price | Amount");
//            for (Fruit item:order) {
//                System.out.printf("%-16s %s %-9s %s %.3f %-3s %s %.3f %-3s\n", item.getFruitName(), "  ", item.getQuantity(), "  ",item.getPrice(),"$","  ",item.getPrice()*item.getQuantity(),"$");
//            }
//            double total=0;
//            for(Fruit item:boughtItems){
//                double amount = item.getPrice()*item.getQuantity();
//                total+=amount;
//            }
//            System.out.println("Tota:"+total+"$");
//        }
//    }
//    
//}