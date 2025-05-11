package org.example;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {
        if(args.length != 2){
            System.out.println("Usage: java -jar app.jar <orders.json> <paymentmethods.json>");
            return;
        }
        String pathOrders = args[0];
        String pathPayment = args[1];

        HashMap<String, Double> result = Service.ProcessData(pathOrders, pathPayment);
        for(String i : result.keySet()){
           System.out.println(i + ":\t" + result.get(i));
        }
    }
}