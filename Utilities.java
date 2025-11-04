package cruz.zoo.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Utilities {

    // Keeps track of IDs for each species to ensure uniqueness
    private static final HashMap<String, Integer> speciesCounters = new HashMap<>();

    /**
     * genUniqueID(): Formulates a unique identifier for each animal
     * Format example: Hy01, Li03, Ti02, Be04
     */
    public static String calcAnimalID(String animalSpecies) {
        if (animalSpecies == null || animalSpecies.isEmpty()) {
            return "XX00";
        }

        animalSpecies = animalSpecies.toLowerCase();
        String prefix;

        switch (animalSpecies) {
            case "hyena" -> prefix = "Hy";
            case "lion" -> prefix = "Li";
            case "tiger" -> prefix = "Ti";
            case "bear" -> prefix = "Be";
            default -> prefix = "XX";
        }

        int nextNum = speciesCounters.getOrDefault(animalSpecies, 0) + 1;
        speciesCounters.put(animalSpecies, nextNum);

        return String.format("%s%02d", prefix, nextNum);
    }

    /**
     * genBirthDay(): Derives an animal's birthday from its age and season
     * Returns ISO 8601 format (yyyy-MM-dd)
     */
    public static String calcAnimalBirthDate(int age, String season) {
        int currentYear = LocalDate.now().getYear();
        int birthYear = currentYear - age;

        if (season == null || season.isEmpty()) {
            // If season not provided, default to mid-year
            return birthYear + "-07-01";
        }

        season = season.toLowerCase();
        String birthMonthDay;
        switch (season) {
            case "spring" -> birthMonthDay = "-03-21";
            case "summer" -> birthMonthDay = "-06-21";
            case "fall", "autumn" -> birthMonthDay = "-09-21";
            case "winter" -> birthMonthDay = "-12-21";
            default -> birthMonthDay = "-07-01"; // fallback if unknown
        }

        return birthYear + birthMonthDay;
    }

    /**
     * Returns today's date as arrival date (ISO 8601)
     */
    public static String arrivalDate() {
        LocalDate today = LocalDate.now();
        return today.toString(); // ISO 8601 format by default
    }

    /**
     * Reads animal name lists from InputStream (animalNames.txt)
     */
    public static AnimalNameListsWrapper createAnimalNameLists(InputStream inputStream) {
        ArrayList<String> hyenaNameList = new ArrayList<>();
        ArrayList<String> lionNameList = new ArrayList<>();
        ArrayList<String> tigerNameList = new ArrayList<>();
        ArrayList<String> bearNameList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            ArrayList<String> currentList = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.equalsIgnoreCase("Hyena Names:")) currentList = hyenaNameList;
                else if (line.equalsIgnoreCase("Lion Names:")) currentList = lionNameList;
                else if (line.equalsIgnoreCase("Tiger Names:")) currentList = tigerNameList;
                else if (line.equalsIgnoreCase("Bear Names:")) currentList = bearNameList;
                else if (!line.isEmpty() && currentList != null) {
                    String[] names = line.split(",\\s*");
                    for (String name : names) {
                        currentList.add(name.trim());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading animal names file: " + e.getMessage());
        }

        return new AnimalNameListsWrapper(hyenaNameList, lionNameList, tigerNameList, bearNameList);
    }
}
