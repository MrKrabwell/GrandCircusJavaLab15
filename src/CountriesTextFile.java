/**
 * Created by yosuk on 2/13/2017.
 */

import com.sun.org.apache.xpath.internal.SourceTree;
import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class CountriesTextFile {

    public CountriesTextFile() {
        // TODO: Make an actual constructor
        // Make constructor create the file if it doesn't exist, overload?
    }


    /**
     * This method will return a string of all the countries in the file, indicated by fileName
     * @param fileName The file name of the country list file to read
     * @return A String that holds the entire list of countries
     */
    public String readCountriesFromFile(String fileName) {
        // String builder to return the builded string
        StringBuilder allCountriesStrBldr = new StringBuilder();

        // Try to read from a file
        try {
            // Open new file stream with fileName
            FileInputStream countryFileIS = new FileInputStream(fileName);

            // Create Scanner object with countryFileIS
            Scanner fileScnr = new Scanner(countryFileIS);

            // Use the String Builder to make the string to return
            while (fileScnr.hasNextLine()) {
                allCountriesStrBldr.append(fileScnr.nextLine() + "\n");
            }

            // If it gets here then everything was read, close file
            countryFileIS.close();

            // Return the entire string
            return allCountriesStrBldr.toString();
        }
        // File doesn't exist, let user know and return null
        catch (FileNotFoundException e) {
            System.out.println("File is not found!!!");
            return null;
        }
        catch (IOException e) {
            System.out.println("IO Exception!!!");
            return null;
        }
    }


    /**
     * This method will add new countries to the country list, appended to the end
     * @param fileName File name of the country list
     * @param country Name of the country to be added
     * @return True if country was successfully added
     */
    public boolean addCountriesToFile(String fileName, String country) {
        try {
            // Create the Output Stream Object to write to file, be able to append
            FileOutputStream countryFileOS = new FileOutputStream(fileName, true);

            // Create the PrinterWriter object to print to file
            PrintWriter filePWriter = new PrintWriter(countryFileOS);

            // Write to file
            filePWriter.println(country);
            filePWriter.flush();

            // Close file
            filePWriter.close();

        }
        catch (FileNotFoundException e) {
            System.out.println("File not found!!!");
            // TODO: Ask user if they want to create file
            return false;
        }

        // If it got to here, everything executed
        return true;
    }

    /**
     * This method will remove a country from the file
     * @param fileName File name of the file to remove the string from
     * @param countryToRemove Case-insensitive country that needs to be removed from file
     * @return True if file is successfully removed, false if not.
     */
    public boolean removeCountryFromFile(String fileName, String countryToRemove) {

        // Put the entire list in a string
        String countryStr = readCountriesFromFile(fileName);

        // Check to see if string even exists
        if (!countryStr.toLowerCase().contains(countryToRemove.toLowerCase())) {
            System.out.println("That country doesn't exit in file!!!");
            return false;
        }

        // Create an ArrayList
        List<String> countryArray = new CopyOnWriteArrayList<String>();
        for (String element : countryStr.split("\n")) {
            countryArray.add(element);
        }

        // If the element matches countryToRemove, remove that element
        for (String element : countryArray) {
            if (element.equalsIgnoreCase(countryToRemove)) {
                countryArray.remove(element);
            }
        }

        // Remove all items in list
        clearAllEntries(fileName);

        // Rebuild the file
        for (String element : countryArray) {
            addCountriesToFile(fileName,element);
        }

        return true;
    }



    public void removeCountryfromFile(String fileName, int index) {
        // TODO: Write this code, this is an overloaded method of specifying the string
        return;
    }


    /**
     * This method will clear all the entries in the country file by creating a new file output stream
     * @param fileName Filename that needs to be cleared
     * @return true if executed successfully, false if not
     */
    public boolean clearAllEntries(String fileName) {

        try {
            // Create output stream for file
            FileOutputStream countryFileOS = new FileOutputStream(fileName);
            countryFileOS.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found!!!");
            return false;
        }
        catch (IOException e) {
            System.out.println("IOException!!!");
            return false;
        }

        return true;
    }


}
