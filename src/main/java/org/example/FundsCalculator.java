package org.example;

import org.example.DataObjects.Order;
import org.example.DataObjects.PaymentMethods;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class FundsCalculator {
    public static HashMap<String, Double> Calculate(ArrayList<Order> orders, ArrayList<PaymentMethods> payments){
        payments.sort(Comparator.comparingInt(PaymentMethods::getDiscount).reversed());
        HashMap<String, PaymentMethods> payMap = new HashMap<>();
        for(PaymentMethods pay : payments){
            payMap.put(pay.getId(), pay);
        }
        HashMap<String, Double> result = new HashMap<>();
        ArrayList<String> processedOrders = new ArrayList<>();

        //Processing payments with bank cards
        for(int i = 0; i < payments.size(); i++){
            if(payments.get(i).getId().equals("PUNKTY")){ continue; }
            PaymentMethods currentMethod = payments.get(i);

            double biggerDiscount = 0;
            Order orderFlag = new Order();
            for(int j = 0; j < orders.size(); j++){
                Order currentOrder = orders.get(j);
                if(processedOrders.contains(currentOrder.getId())){ continue; }
                if(!currentOrder.getPromotions().contains(currentMethod.getId())){ continue; }
                if(currentOrder.getValue() > currentMethod.getLimit()){ continue; }

                double discount = currentOrder.getValue() * currentMethod.getDiscount()*0.01;
                if(discount > biggerDiscount){
                    biggerDiscount = discount;
                    orderFlag = currentOrder;
                }
            }
            processedOrders.add(orderFlag.getId());
            double amountPaid = orderFlag.getValue() - biggerDiscount;
            result.merge(currentMethod.getId(), amountPaid, Double::sum);
            currentMethod.setLimit(currentMethod.getLimit() - amountPaid);
        }

        //Processing payments with "PUNKTY" in full
        ArrayList<Order> unProcessedOrders = new ArrayList<>();
        for(Order ord : orders){
            if(processedOrders.contains(ord.getId())){ continue; }
            unProcessedOrders.add(ord);
        }

        PaymentMethods currentMethod = payMap.get("PUNKTY");
        double biggerDiscount = 0;
        Order orderFlag = new Order();
        for(int i = 0; i < unProcessedOrders.size(); i++){
            Order currentOrder = unProcessedOrders.get(i);

            if(currentOrder.getValue() <= currentMethod.getLimit()){
                double discount = currentOrder.getValue() * currentMethod.getDiscount() * 0.01;

                if(discount > biggerDiscount){
                    biggerDiscount = discount;
                    orderFlag = currentOrder;
                }
            }
        }
        processedOrders.add(orderFlag.getId());
        double amountPaid = orderFlag.getValue() - biggerDiscount;
        result.merge(currentMethod.getId(), amountPaid, Double::sum);
        currentMethod.setLimit(currentMethod.getLimit() - amountPaid);

        //Processing payments with "PUNKTY" in parts
        unProcessedOrders = new ArrayList<>();
        for(Order ord : orders){
            if(processedOrders.contains(ord.getId())){ continue; }
            unProcessedOrders.add(ord);
        }

        currentMethod = payMap.get("PUNKTY");
        biggerDiscount = 0;
        orderFlag = new Order();
        for(int i = 0; i < unProcessedOrders.size(); i++){
            Order currentOrder = unProcessedOrders.get(i);

            if(currentOrder.getValue()*0.1 <= currentMethod.getLimit()){
                double discount = currentOrder.getValue()*0.1;

                if(discount > biggerDiscount){
                    biggerDiscount = discount;
                    orderFlag = currentOrder;
                }
            }
        }

        amountPaid = orderFlag.getValue() - biggerDiscount;
        double toPay = amountPaid - currentMethod.getLimit();
        result.merge(currentMethod.getId(), amountPaid-toPay, Double::sum);
        currentMethod.setLimit(currentMethod.getLimit() - amountPaid + toPay);

        for(int i = 0; i < payments.size(); i++){
            if(payments.get(i).getId().equals("PUNKTY")){ continue; }
            currentMethod = payments.get(i);

            if(currentMethod.getLimit() >= toPay){
                result.merge(currentMethod.getId(), toPay, Double::sum);
                currentMethod.setLimit(currentMethod.getLimit() - toPay);
                toPay = 0;
                break;
            }else{
                toPay = toPay - currentMethod.getLimit();
                result.merge(currentMethod.getId(), toPay, Double::sum);
            }
        }
        if(toPay == 0){
            processedOrders.add(orderFlag.getId());
        }

        //output
        unProcessedOrders = new ArrayList<>();
        for(Order ord : orders){
            if(processedOrders.contains(ord.getId())){ continue; }
            unProcessedOrders.add(ord);
        }
        if(unProcessedOrders.size() > 0){
            System.out.println("Not enough funds for following orders: ");
            double val = 0;
            for(Order i : unProcessedOrders){
                val += i.getValue();
                System.out.println(i.getId() + "\n");
            }
            System.out.println("With total cost of: " + val);
            System.out.println("\n");
        }
        return result;
    }
}
