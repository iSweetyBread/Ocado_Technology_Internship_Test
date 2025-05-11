package org.example.Utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class FileRead {
    public static ArrayList<String> Read(String path) throws Exception{
        return InternalRead(path);
    }
    public static ArrayList<String> Read() throws Exception{
        System.out.println("Please, enter the filepath: ");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        return InternalRead(path);
    }

    private static ArrayList<String> InternalRead(String path) throws Exception{
        if(!Validator.ValidateFilePath(path)){
            throw new Exception(path + "\tIs invalid path");
        }
        ArrayList<String> result = new ArrayList<>();

        File file = new File(path);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            String data = scanner.next();
            result.add(data);
        }
        scanner.close();
        result.removeFirst();
        result.removeLast();

        return result;
    }
}
