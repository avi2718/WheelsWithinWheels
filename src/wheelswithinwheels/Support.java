/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels;

import java.io.*;
import java.text.*;
import java.util.Date;

/**
 *
 * @author maxwelllittle
 */
public class Support {
    
    private String errorMessage = "";
    private boolean wasError = false;
    
    public String getErrorMessage() {
        return errorMessage;
    }
    
    public boolean wasError() {
        return wasError;
    }
    
    private void clearError() {
        wasError = false;
        errorMessage = "";
    }
    
    private void setError(Exception e) {
        wasError = true;
        errorMessage = e.getMessage();
    }
    
    public String readTextFile(String file) {
        clearError();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
        }
        catch (Exception e) {
            setError(e);
            return "";
        }
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            
            return stringBuilder.toString();
        }
        catch (Exception e) {
            setError(e);
            return "";
        }
    }
    
    public void writeTextFile(String file, String text) {
        try (Writer writer  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"))) {
            writer.write(text);
        }
        catch (Exception e) {
            setError(e);
            return;
        }
    }
    
    public String[] splitStringIntoParts(String s) {
        return s.split("\\s+");
    }
    
    public String[] splitStringIntoLines(String s) {
        return s.split("\\r?\\n");
    }
    
    public String fit(String s, int size, boolean right) {
        String result = "";
        int sSize = s.length();
        if (sSize == size) {
            return s;
        }
        if (size < sSize) {
            return s.substring(0, size);
        }
        result = s;
        String addon = "";
        int num = size - sSize;
        for (int i = 0; i < num; i++) {
            addon += " ";
        }
        if (right) {
            return result + addon;
        }
        return addon + result;
    }
    
    public String fitI(int i, int size, boolean right) {
        String iAsString = "" + i;
        int newLength = iAsString.length();
        return fit(iAsString, newLength > size ? newLength : size, right);
    }
    
    public String fitD(Date d, int size, boolean right) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
        String dAsString = dateFormatter.format(d);
        return fit(dAsString, size, right);
    }
}
