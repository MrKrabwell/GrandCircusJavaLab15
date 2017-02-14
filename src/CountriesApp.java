public class CountriesApp {

    public static void main(String[] args) {
        // Variable Declarations
        String fileName = "src/countries_list.txt";          // File name of the country list
        
        // Welcome message
        System.out.println("Welcome to the Countries Maintenance Application!\n");

        // Create new object of CountriesTextFile
        CountriesTextFile countryListFile = new CountriesTextFile();

        // Continue the program until user decides to quit
        programLoop:
        while (true) {

            // Prompt user on action
            System.out.println("1 - See the list of countries");
            System.out.println("2 - Add a country");
            System.out.println("3 - Delete a country");
            System.out.println("4 - Clear all entries");
            System.out.println("5 - Exit");
            System.out.print("\nEnter menu number (1-5): ");

            switch (InputValidator.getValidInteger(1,5)) {
                case 1:
                    // Print the entire list in the file
                    System.out.println();
                    if (countryListFile.readCountriesFromFile(fileName).isEmpty()) {
                        System.out.println("File is empty!!");
                    }
                    else {
                        System.out.println(countryListFile.readCountriesFromFile(fileName));
                    }
                    System.out.println();
                    break;
                case 2:
                    System.out.println();
                    // Get user input, notify user of result
                    System.out.print("Enter country: ");
                    if (countryListFile.addCountriesToFile(fileName, InputValidator.getString())) {
                        System.out.println("This country has been saved!");
                    }
                    else {
                        System.out.println("An error occurred!  This country has not been added.");
                    }
                    System.out.println();
                    break;
                case 3:
                    System.out.print("Enter country to remove: ");
                    if (countryListFile.removeCountryFromFile(fileName, InputValidator.getString())) {
                        System.out.println("This country has been removed");
                    }
                    else {
                        System.out.println("An error occurred!  This country is not removed");
                    }
                    System.out.println();
                    break;
                case 4:
                    if (countryListFile.clearAllEntries(fileName)) {
                        System.out.println("All entries have been cleared successfully");
                    }
                    else {
                        System.out.println("An error occurred!  File is not modified.");
                    }
                    System.out.println();
                    break;
                case 5:
                    break programLoop;
                default:
                    break;
            }
        }

        // Exit message
        System.out.println("\nGoodbye!");

    }
}
