package charactercreator;

/*
 * This class defines Skill objects, which allow the character to interact with the game world in a wide variety of ways.  They represent the
 * crystallization of some type of knowledge and/or training the character has practiced (or is naturally gifted at.)
 */

public class Skill {
    protected String id = "";                           // A unique string identifying the skill, for internal use only.
    protected String name = "";                         // The name of the skill, as the user will see it.
    protected String description = "";                  // A description of the skill and how it can be used.
    protected boolean isProficient = false;             // Whether or not the character is proficient with this skill, and will receive their Proficiency Bonus when using it.
    protected String relevantAbilityScore = "Strength"; // Which ability score will be used when calculating ability bonus values.
    protected int miscBonus = 0;                        // Any additional bonuses the character has to this skill.
    
    // Constructor for all fields.
    public Skill(String idIn, String nameIn, boolean isProficientIn, String relevantAbilityScoreIn, int miscBonusIn) {
        this.id = idIn;
        this.name = nameIn;
        this.isProficient = isProficientIn;
        this.relevantAbilityScore = relevantAbilityScoreIn;
        this.miscBonus = miscBonusIn;
    } // End constructor for all fields.
    
    // Setters and getters.
    // For id.
    public void setID(String idIn) {this.id = idIn;}   
    public String getID() {return this.id;}
    // For name.
    public void setName(String nameIn) {this.name = nameIn;}
    public String getName() {return this.name;}
    // For description.
    public void setDescription(String descriptionIn) {this.description = descriptionIn;}
    public String getDescription() {return this.description;}
    // For isProficient.
    public void setIsProficient(boolean isProficientIn) {this.isProficient = isProficientIn;}
    public boolean getIsProficient() {return this.isProficient;}
    // For relevant ability score.
    public void setRelevantAbilityScore(String relevantAbilityScoreIn) {this.relevantAbilityScore = relevantAbilityScoreIn;}
    public String getRelevantAbilityScore() {return this.relevantAbilityScore;}
    // For miscellaneous bonus.
    public void setMiscBonus(int miscBonusIn) {this.miscBonus = miscBonusIn;}
    public int getMiscBonus() {return this.miscBonus;}
    
} // End public class Skill.