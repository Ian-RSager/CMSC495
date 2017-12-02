package charactercreator;

/*
 * This class is used to define ClassType objects, more commonly referred to simply as classes, or character classes.  These objects contain the necessary data and references
 * to generate a character class.
 */

public class ClassType {
    protected String id;                            // A unique string identifying the class, for internal use only.
    protected String name;                          // The name of the class, as the user will see it.
    protected String description;                   // A very brief plain text description of the class.
    protected int[] hitDice;                        // An array determining which hit die the character uses.  There are 4 possible values, signifying: d6, d8, d10, d12.
    protected String[] armorProficiencies;          // A list of the armor proficiencies the character gets.
    protected String[] weaponProficiencies;         // A list of the weapon proficiencies the character gets.
    protected String[] savingThrows;                // A list of the saving throws the character gains proficiency in.
    protected int numberOfSkillChoices;             // A number signifying the quantity of choices the player is able to make from the skillChoicesArray, to gain proficiency in those skills.
    protected String[] skillChoicesArray;           // A list of the skills the player may choose from, to gain proficiency in when creating a character of this class.
    protected String[] tools;                       // A list of the tools the character gains proficiency in.
    protected String equipment;                     // A description of the equipment the player may choose when creating a new character of this class.  For front-end use only.
    protected String[][] features;                  // A list of the features the class gains at each level.  Each feature is listed via its unique ID, per the ClassFeature class.
    protected String[] subClassChoices;             // A list of the possible subclasses the player may choose from, when creating a character with levels in this class.
    protected String[][] subClassFeaturesList;      // A list of all the class features each of the possible subclasses receives.  21 entries in total, the first to be used as an identifier, and indices 1-20 signifying which feature is received at levels 1 through 20 for this particular subclass.
    
    // Constructor to be used when reading in data from classTypes.json
    public ClassType(String id, String name, String description, int[] hitDice, String[] armorProficiencies, String[] weaponProficiencies, String[] savingThrows, int numberOfSkillChoices, 
            String[] skillChoicesArray, String[] tools, String equipment, String[][] features, String[] subClassChoices, String[][] subClassFeaturesList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hitDice = hitDice;
        this.armorProficiencies = armorProficiencies;
        this.weaponProficiencies = weaponProficiencies;
        this.savingThrows = savingThrows;
        this.numberOfSkillChoices = numberOfSkillChoices;
        this.skillChoicesArray = skillChoicesArray;
        this.tools = tools;
        this.equipment = equipment;
        this.features = features;
        this.subClassChoices = subClassChoices;
        this.subClassFeaturesList = subClassFeaturesList;
    } // End constructor.
    
    // Only getter methods will be used, as these values should not change during the operation of the program.
    public String getID() {return this.id;}
    public String getName() {return this.name;}
    public String getDescription() {return this.description;}
    public int[] getHitDice() {return this.hitDice;}
    public String[] getArmorProficiencies() {return this.armorProficiencies;}
    public String[] getWeaponProficiencies() {return this.weaponProficiencies;}
    public String[] getSavingThrows() {return this.savingThrows;}
    public int getNumberOfSkillChoices() {return this.numberOfSkillChoices;}
    public String[] getSkillChoicesArray() {return this.skillChoicesArray;}
    public String[] getTools() {return this.tools;}
    public String getEquipment() {return this.equipment;}
    public String[][] getFeatures() {return this.features;}
    public String[] getSubClassChoices() {return this.subClassChoices;}
    public String[][] getSubClassFeaturesList() {return this.subClassFeaturesList;}
} // End public class ClassType.