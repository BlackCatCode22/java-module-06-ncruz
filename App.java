package cruz.zoo.com;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        System.out.println("Welcome to the Zoo Program!");

        String nameFilePath = "animalNames.txt";
        String dataFilePath = "arrivingAnimals.txt";

        // Load animal names
        AnimalNameListsWrapper animalLists = null;
        try (InputStream nameFileStream = new FileInputStream(nameFilePath)) {
            animalLists = Utilities.createAnimalNameLists(nameFileStream);
        } catch (IOException e) {
            System.out.println("Error reading animal names file: " + e.getMessage());
            return;
        }

        ArrayList<String> hyenaNames = animalLists.getHyenaNameList();
        ArrayList<String> lionNames = animalLists.getLionNameList();
        ArrayList<String> tigerNames = animalLists.getTigerNameList();
        ArrayList<String> bearNames = animalLists.getBearNameList();

        ArrayList<Animal> zooAnimals = new ArrayList<>();
        HashMap<String, Animal> animalMap = new HashMap<>();
        HashMap<String, Integer> speciesCount = new HashMap<>();

        // Read arriving animals
        try (BufferedReader reader = new BufferedReader(new FileReader(dataFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length < 5) continue;

                String mainInfo = parts[0];
                String birthSeason = parts[1].split(" ")[2];
                String color = parts[2];
                int weight = Integer.parseInt(parts[3].replaceAll("[^\\d]", ""));
                String origin = parts[4] + (parts.length > 5 ? ", " + parts[5] : "");

                String[] mainParts = mainInfo.split(" ");
                int age = Integer.parseInt(mainParts[0].replaceAll("[^\\d]", ""));
                String sex = mainParts[3];
                String species = mainParts[4].toLowerCase();

                String arrivalDate = Utilities.arrivalDate();
                String animalID = Utilities.calcAnimalID(species);
                String birthDate = Utilities.calcAnimalBirthDate(age, birthSeason);

                String name = "";
                Animal animal = null;

                switch (species) {
                    case "hyena":
                        name = hyenaNames.remove(0);
                        animal = new Hyena(sex, age, weight, name, animalID, birthDate, color, origin, arrivalDate);
                        break;
                    case "lion":
                        name = lionNames.remove(0);
                        animal = new Lion(sex, age, weight, name, animalID, birthDate, color, origin, arrivalDate);
                        break;
                    case "tiger":
                        name = tigerNames.remove(0);
                        animal = new Tiger(sex, age, weight, name, animalID, birthDate, color, origin, arrivalDate);
                        break;
                    case "bear":
                        name = bearNames.remove(0);
                        animal = new Bear(sex, age, weight, name, animalID, birthDate, color, origin, arrivalDate);
                        break;
                    default:
                        System.out.println("Unknown species: " + species);
                        continue;
                }

                zooAnimals.add(animal);
                animalMap.put(animalID, animal);
                speciesCount.put(species, speciesCount.getOrDefault(species, 0) + 1);
            }

        } catch (IOException e) {
            System.out.println("Error reading arriving animals file: " + e.getMessage());
        }

        // Display species counts
        System.out.println("\nAnimal Species Count:");
        for (String species : speciesCount.keySet()) {
            System.out.println(capitalize(species) + "s: " + speciesCount.get(species));
        }
        System.out.println("Total Animals: " + Animal.getNumOfAnimals());

        // Generate zooPopulation.txt report
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("zooPopulation.txt"))) {
            writer.write("******* Zoo Population and Habitat Assignment Report ********\n\n");

            writeHabitatSection(writer, "Hyena", zooAnimals);
            writeHabitatSection(writer, "Lion", zooAnimals);
            writeHabitatSection(writer, "Tiger", zooAnimals);
            writeHabitatSection(writer, "Bear", zooAnimals);

            System.out.println("\nzooPopulation.txt has been generated successfully!");
        } catch (IOException e) {
            System.out.println("Error writing zooPopulation.txt: " + e.getMessage());
        }

        // Prompt user to search for animals by ID
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nEnter Animal ID to search (or type 'exit' to quit): ");
            String inputID = scanner.nextLine().trim();
            if (inputID.equalsIgnoreCase("exit")) break;

            Animal foundAnimal = animalMap.get(inputID);
            if (foundAnimal != null) {
                System.out.println("\nAnimal Found:");
                System.out.println(foundAnimal.fullInfo()); // You need a method in Animal class called fullInfo()
            } else {
                System.out.println("No animal found with ID: " + inputID);
            }
        }
        scanner.close();
    }

    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static void writeHabitatSection(BufferedWriter writer, String species, ArrayList<Animal> animals) throws IOException {
        writer.write(species + " Habitat:\n\n");
        for (Animal animal : animals) {
            if (animal.getClass().getSimpleName().equalsIgnoreCase(species)) {
                writer.write(animal.getAnimalID() + "; " +
                        animal.getAge() + " years old; " +
                        animal.getName() + "; birthDate: " + animal.getBirthDate() + "; " +
                        animal.getColor() + " color; " +
                        animal.getSex() + "; " +
                        animal.getWeight() + " pounds; " +
                        getSoundPhrase(animal) + "; " +
                        "from: " + animal.getOrigin() + "; " +
                        "arrived: " + animal.getArrivalDate() + "\n");
            }
        }
        writer.write("\n");
    }

    public static String getSoundPhrase(Animal animal) {
        if (animal instanceof Hyena) return "laugh: " + ((Hyena) animal).getLaugh();
        if (animal instanceof Lion) return "roar: " + ((Lion) animal).getRoar();
        if (animal instanceof Tiger) return "roar: " + ((Tiger) animal).getRoar();
        if (animal instanceof Bear) return "roar: " + ((Bear) animal).getRoar();
        return "";
    }
}
