package charactercreator;

/*
 * This class is used to define Race objects.  A Race refers to the well, race, of the character.
 */

public class Race {
    protected String id;                            // A unique string identifying the race, for internal use only.
    protected String name;                          // The name of the race, as the user will see it.
    protected String description;                   // A plain text description of the race.
    protected int[] abilityScore;                   // A list of the which ability scores receive a bonus.  In order these represent: Strength, Dexterity, Constitution, Intelligence, Wisdom, Charisma.
    protected int age;                              // The default age of the race.
    protected int heightInches;                     // The default height, in inches, of the race.
    protected boolean isMedium;                     // If true, the race is Medium sized, otherwise it is Small.
    protected int speed;                            // The race's movement speed, in feet per round.
    protected String[] traits;                      // A list of all the traits the race possesses, to be displayed to the user.
    
    // Constructor for use with JSON files.
    public Race(String id, String name, String description, int[] abilityScore, int age, int heightInches, boolean isMedium, int speed, String[] traits) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.abilityScore = abilityScore;
        this.age = age;
        this.heightInches = heightInches;
        this.isMedium = isMedium;
        this.speed = speed;
        this.traits = traits;
    } // End constructor for use with JSON files.
    
    // Getter methods.  No setter methods are provided, as Race objects should never change while the programming is running.
    public String getID() {return this.id;}
    public String getName() {return this.name;}
    public String getDescription() {return this.description;}
    public int[] getAbilityScore() {return this.abilityScore;}
    public int getAge() {return this.age;}
    public int getHeightInches() {return this.heightInches;}
    public boolean getIsMedium() {return this.isMedium;}
    public int getSpeed() {return this.speed;}
    public String[] getString() {return this.traits;}
} // End public class Race.