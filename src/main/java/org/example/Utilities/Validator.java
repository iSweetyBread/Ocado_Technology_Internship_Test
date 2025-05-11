package org.example.Utilities;

import java.io.File;

public class Validator {
    public static boolean ValidateFilePath(String path) throws Exception{
        File file = new File(path.trim());
        if(file.isFile()){
            if(!path.trim().endsWith(".json")){
                throw new Exception(path + " Is not a valid .json file");
            }
            return true;
        }
        return false;
    }
}
