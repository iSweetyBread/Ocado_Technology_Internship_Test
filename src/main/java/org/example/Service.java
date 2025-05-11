package org.example;

import org.example.Utilities.Serialization;
import org.example.Utilities.FileRead;
import org.example.DataObjects.Order;
import org.example.DataObjects.PaymentMethods;

import java.util.ArrayList;
import java.util.HashMap;

public class Service {
    public static HashMap<String, Double> ProcessData(String pathOrders, String pathPayment) throws Exception{
        ArrayList<String> dataOrders = FileRead.Read(pathOrders);
        ArrayList<String> dataPayment = FileRead.Read(pathPayment);

        ArrayList<Order> orders = Serialization.DeSerialize(dataOrders, Order.class);
        ArrayList<PaymentMethods> payments = Serialization.DeSerialize(dataPayment, PaymentMethods.class);

        return FundsCalculator.Calculate(orders, payments);
    }
}
