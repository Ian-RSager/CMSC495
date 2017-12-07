
/*
 * This class is used to define Race objects.  A Race refers to the well, race, of the character.
 */

public class Race extends Thing{
    protected int[] abilityScore;                   // A list of the which ability scores receive a bonus.  In order these represent: Strength, Dexterity, Constitution, Intelligence, Wisdom, Charisma.
    protected int age;                              // The default age of the race.
    protected int heightInches;                     // The default height, in inches, of the race.
    protected boolean isMedium;                     // If true, the race is Medium sized, otherwise it is Small.
    protected int speed;                            // The race's movement speed, in feet per round.
    protected String[][] traits;                    // A list of all the traits the race possesses, to be displayed to the user.

    // Constructor to be used when reading in data from races.json
    public Race(String idIn, String nameIn, String descriptionIn, int[] abilityScoreIn, int ageIn, int heightInchesIn, boolean isMediumIn, int speedIn, String[][] traitsIn) {
        super(idIn, nameIn, descriptionIn);
        this.abilityScore = abilityScoreIn;
        this.age = ageIn;
        this.heightInches = heightInchesIn;
        this.isMedium = isMediumIn;
        this.speed = speedIn;
        this.traits = traitsIn;
    } // End constructor for use with JSON files.

    /**
     * printData serves as a temporary QC. It will not necessarily be used during operation.
     */
    @Override
    public void printData(){
        super.printData();
        try {
            System.out.println("Ability score : " + this.abilityScore);//ab score
            System.out.println("This race is considered an adult at " + this.age + " years old.");//age
            System.out.println("Average height in inches : " + this.heightInches);//hight
            if (isMedium) System.out.println("This race is a mediume sized race.");
            else System.out.println("This race is a small sized race.");
            System.out.println("This race can move " + this.speed + " spaces per move.");//speed
            for (String[] eachTrait : this.traits)
                for (String eaTrait : eachTrait)
                    System.out.println("\t:" + eaTrait);
        }catch (Exception e){
            System.out.println("\n\n\t **************  Race - OOPS ************** \n\n");
        }
    }


    // Getter methods.  No setter methods are provided, as Race objects should never change while the programming is running.
    public int[] getAbilityScore() {return this.abilityScore;}
    public int getAge() {return this.age;}
    public int getHeightInches() {return this.heightInches;}
    public boolean getIsMedium() {return this.isMedium;}
    public int getSpeed() {return this.speed;}
    public String[][] getString() {return this.traits;}
} // End public class Race.
