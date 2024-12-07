import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FindMyCar {
    static final double REGISTRATION_FEE = 0.07;
    static final double INSURANCE_FEE = 0.12;
    static final double HANDLING_FEE = 0.02;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Define cars
        List<Car> cars = Arrays.asList(
                new Car("AltoK10", Arrays.asList("STD", "LXI", "VXI", "VXI+"), Arrays.asList(3.99, 4.25, 4.75, 5.00)),
                new Car("WagonR", Arrays.asList("LXI", "VXI", "ZXI", "ZXI+"), Arrays.asList(5.54, 6.25, 6.75, 7.00)),
                new Car("Celerio", Arrays.asList("LXI", "VXI", "ZXI", "ZXI+"), Arrays.asList(4.99, 6.00, 6.75, 7.04)),
                new Car("Fronx", Arrays.asList("Sigma", "Delta", "Zeta", "Alpha", "Alpha Dual Tone"), Arrays.asList(7.51, 10.00, 11.00, 12.00, 13.04)),
                new Car("Jimny", Arrays.asList("Zeta", "Alpha"), Arrays.asList(12.74, 14.95))
        );

        //define accessory
        List<Accessory> accessories = Arrays.asList(
               new Accessory("Alloy Wheels", 15000, 40000),
                new Accessory("Body Side Moulding", 1500, 3000),
                new Accessory("Door Visors", 1000, 2500),
                new Accessory("Mud Flaps", 500, 1200),
                new Accessory("Spoilers", 3000, 8000),
                new Accessory("Chrome Garnish", 1000, 5000),
                new Accessory("Roof Rails", 2500, 6000),
                new Accessory("Side Skirts", 4000, 10000),
                new Accessory("Seat Covers", 2000, 10000),
                new Accessory("Floor Mats", 1000, 3000),
                new Accessory("Steering Wheel Covers", 500, 1500),
                new Accessory("Sunshades", 500, 2000),
                new Accessory("Dashboard Kits", 2000, 5000),
                new Accessory("Ambient Lighting", 2000, 6000),
                new Accessory("Car Perfumes", 200, 1000),
                new Accessory("Touchscreen Infotainment Systems", 10000, 50000),
                new Accessory("Speakers and Amplifiers", 5000, 20000),
                new Accessory("Reverse Parking Sensors", 2000, 5000),
                new Accessory("Rear View Cameras", 3000, 7000),
                new Accessory("GPS Navigation Systems", 5000, 15000),
                new Accessory("Car Chargers", 300, 1000),
                new Accessory("Car Alarms", 2000, 5000),
                new Accessory("Central Locking Systems", 3000, 8000),
                new Accessory("Fire Extinguishers", 500, 2000),
                new Accessory("First Aid Kits", 300, 1000),
                new Accessory("Child Safety Seats", 5000, 20000),
                new Accessory("Car Covers", 1000, 5000),
                new Accessory("Cleaning Kits", 500, 2000),
                new Accessory("Polish and Wax", 300, 1500),
                new Accessory("Pressure Washers", 5000, 15000)
        );


        // Prompt user for budget
        System.out.print("Enter your budget in Lakhs: ");
        double budget = scanner.nextDouble();

        // Display available cars within budget
        System.out.println("\nAvailable cars within your budget:");
        for (Car car : cars) {
            for (int i = 0; i < car.prices.size(); i++) {
                double carPrice = car.prices.get(i) * 100000; // Convert Lakhs to Rupees
                double onRoadPrice = carPrice + carPrice * REGISTRATION_FEE + carPrice * INSURANCE_FEE + carPrice * HANDLING_FEE;
                if (onRoadPrice <= budget * 100000) {
                    System.out.println(car.name + " - " + car.variants.get(i) + " : " + car.prices.get(i) + " Lakhs");
                }
            }
        }

        // Prompt user to select a car
        System.out.print("\nSelect a car: ");
        String selectedCarName = scanner.next();
        Car selectedCar = null;
        for (Car car : cars) {
            if (car.name.equalsIgnoreCase(selectedCarName)) {
                selectedCar = car;
                break;
            }
        }

        if (selectedCar == null) {
            System.out.println("Invalid car selection.");
            return;
        }

        // Display variants and prompt user to select one
        System.out.println("\nAvailable variants for " + selectedCar.name + ":");
        for (int i = 0; i < selectedCar.variants.size(); i++) {
            double carPrice = selectedCar.prices.get(i) * 100000; // Convert Lakhs to Rupees
            double onRoadPrice = carPrice + carPrice * REGISTRATION_FEE + carPrice * INSURANCE_FEE + carPrice * HANDLING_FEE;
            if (onRoadPrice <= budget * 100000) {
                System.out.println((i + 1) + ". " + selectedCar.variants.get(i) + " : " + selectedCar.prices.get(i) + " Lakhs");
            }
        }
        System.out.print("\nSelect a variant: ");
        int variantIndex = scanner.nextInt() - 1;

        if (variantIndex < 0 || variantIndex >= selectedCar.variants.size()) {
            System.out.println("\nInvalid variant selection.");
            return;
        }

        //calculating base price and onroadprice for selected car
        double select_carPrice = (selectedCar.prices.get(variantIndex))*100000;
        double select_onroadprice=  select_carPrice + select_carPrice * REGISTRATION_FEE + select_carPrice * INSURANCE_FEE + select_carPrice * HANDLING_FEE;

        // Calculate remaining budget
        double remainingBudget = (budget * 100000) - select_onroadprice ;
        double totalAccessoryCost = 0;

        //array to keep track of accessories added by user
        ArrayList<Integer> accessories_added = new ArrayList<>();


        // Display accessories and prompt user to select
        while (remainingBudget > 0) {
            System.out.println("\nAvailable accessories:");
            for (int i = 0; i < accessories.size(); i++) {
                Accessory accessory = accessories.get(i);
                System.out.println((i + 1) + ". " + accessory.name + " : ₹" + accessory.minPrice + " - ₹" + accessory.maxPrice);
            }

            System.out.println("\nIndex of Accessories added to your bag");
            accessories_added.forEach(val -> System.out.print(val+1+" "));

            System.out.println("\n\nYou have ₹" + remainingBudget + " remaining for accessories.\n");
            System.out.print("Select an accessory (or enter 0 to finish): ");
            int accessoryIndex = scanner.nextInt() - 1;

            if (accessoryIndex == -1) {
                break; // User chose to finish
            }

            if (accessoryIndex < 0 || accessoryIndex >= accessories.size()) {
                System.out.println("Invalid accessory selection.\n");
                continue;
            }

            Accessory selectedAccessory = accessories.get(accessoryIndex);
            System.out.print("\nEnter the price for " + selectedAccessory.name + " (₹" + selectedAccessory.minPrice + " - ₹" + selectedAccessory.maxPrice + "): ");
            int accessoryPrice = scanner.nextInt();

            if (accessoryPrice < selectedAccessory.minPrice || accessoryPrice > selectedAccessory.maxPrice) {
                System.out.println("Invalid price. Please enter a price within the specified range.\n");
                continue;
            }

            if (accessoryPrice > remainingBudget) {
                System.out.println("Warning! : You don't have enough budget for this accessory.\n");
                continue;
            }

            remainingBudget -= accessoryPrice;
            totalAccessoryCost+=accessoryPrice;
            accessories_added.add(accessoryIndex);
            System.out.println("\n"+selectedAccessory.name+" added for your car. Remaining budget: ₹" + remainingBudget);
        }

        //final OnRoad price after adding Accessory Cost
        select_onroadprice+=totalAccessoryCost;

        System.out.println("\nBILL GENERATED");
        System.out.println("Base Price for "+ selectedCar.name + " - " + selectedCar.variants.get(variantIndex)+" is : ₹" + select_carPrice);
        System.out.println("Total Accessory Cost : ₹" + totalAccessoryCost);
        System.out.printf("Registration_FEE : ₹%.1f%n", select_carPrice*REGISTRATION_FEE);
        System.out.println("Insurance_FEE : ₹" + select_carPrice*INSURANCE_FEE);
        System.out.println("Handling_FEE : ₹" +select_carPrice* HANDLING_FEE);
        System.out.println("\nFinal OnRoad price for "+selectedCar.name + " - " + selectedCar.variants.get(variantIndex)+": ₹" + select_onroadprice);

        System.out.println("\nBudget left after buying your Dream car: ₹" + remainingBudget);
    }
}
