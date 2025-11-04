package cruz.zoo.com;

public class Tiger extends Animal {
    private static int numOfTigers = 0;
    private String roar;

    public Tiger(String sex, int age, int weight, String name,
                 String animalID, String birthDate, String color,
                 String origin, String arrivalDate) {
        super(sex, age, weight, name, animalID, birthDate, color, origin, arrivalDate);
        this.roar = "Mew!"; // default tiger sound
        numOfTigers++;
    }

    public static int getNumOfTigers() {
        return numOfTigers;
    }

    public String getRoar() {
        return roar;
    }
}
