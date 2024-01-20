package model;

import java.util.Scanner;

public class Utils {

    static Scanner sc = new Scanner(System.in);

    public static boolean demand(String msg) {
        boolean isDemand = false;

        System.out.println(msg);
        String choose = sc.nextLine();

        while (!choose.equals("Y") && !choose.equals("N")) {
            System.out.println("Invalid input. Please enter 'Y' or 'N'.");
            System.out.println(msg);
            choose = sc.nextLine();
        }

        switch (choose) {
            case "Y" -> {
                isDemand = true;
            }
            case "N" -> {
                isDemand = false;
            }
        }

        return isDemand;
    }

    public static String enterID(String st) {

        String id;
        do {
            System.out.print(st);
            id = checkInputString();

            if (checkDuplicate(id)) {
                System.out.println("The ID is already use. Please enter another ID.");
            }
        } while (checkDuplicate(id));
        return id;
    }

    public static String checkInputString() {
        while (true) {
            String result = sc.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    public static boolean checkDuplicate(String obj) {
        for (Fruit f : FruitManager.getFruitManage()) {
            if (f.getFruitId().equals(obj)) {
                return true;
            }
        }
        return false;
    }

    public static String getValue(String ms) {
        System.out.print(ms);
        return sc.nextLine().trim();
    }
    
    public static String getAndValidValue(String msg, String regex, String cause){
        String value;
        while (true){
            try {
                value = getValue(msg);
                if (value.isEmpty()) {
                    throw new Exception("Input can not empty");
                }
                if (!value.matches(regex)) {
                    throw new Exception(cause);
                }
                break;
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return value;
    }
    
     public static double getAndValidMoney(String msg){
        String money;
        while(true){
            try{
                money = getAndValidValue(msg, "[\\d.]+", "Invalid amount of money");
                
                if (Double.parseDouble(money) <= 0)
                    throw new Exception("Amount of money must a positive number");
                
                return Double.parseDouble(money);
            } catch (NumberFormatException e){
                System.out.println("Invalid amount of money");
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }
    
    

}
