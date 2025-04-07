package org.michaelmathews.membershipfx;

import java.io.*;
import java.util.Hashtable;
import java.util.Scanner;

public class Auth {


    static Hashtable<String, String> userInfo = new Hashtable<>();


    public static boolean authorize(String username, String password)
    {
        readFromDB();
        if(userInfo.containsKey(username) && userInfo.get(username).equals(password))
        {
            return true;
        }
        return false;
    }

    public static void readFromDB()
    {
        try {
            File path = new File("src/main/java/org/michaelmathews/membershipfx/userInfo.txt");
            Scanner scnr = new Scanner(path);

            while (scnr.hasNextLine()) {
                String[] temp = scnr.nextLine().split(",");
                userInfo.put(temp[0], temp[1]);
            }
        }
        catch(FileNotFoundException e)
            {
                System.out.println(e.getMessage());
            }


    }

    public static void createUser(String username, String password)
    {
        try{
            FileWriter writer = new FileWriter("src/main/java/org/michaelmathews/membershipfx/userInfo.txt", true);
            writer.write("\n" + username + "," + password);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
