package cruz.zoo.com;

public class Hyena extends Animal {
    private static int numOfHyenas = 0;
    private String laugh;

    public Hyena(String sex, int age, int weight, String name,
                 String animalID, String birthDate, String color,
                 String origin, String arrivalDate) {
        super(sex, age, weight, name, animalID, birthDate, color, origin, arrivalDate);
        this.laugh = "Haha"; // default laugh
        numOfHyenas++;
    }

    public static int getNumOfHyenas() {
        return numOfHyenas;
    }

    public String getLaugh() {
        return laugh;
    }
}
