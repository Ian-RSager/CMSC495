package charactercreator;

/*
 * This class defines Spell objects, magical incantations that allow the character to manipulate reality in fantastical ways.
 */

public class Spell {
    protected String id = "";                           // A unique string identifying the spell, for internal use only.
    protected String name = "";                         // The name of the spell, as the user will see it.
    protected String[] classes = {};                    // An array of the names of all classes capable of casting this spell.
    protected String level = "";                        // The level of the spell, from cantrip through 9.
    protected String components = "";                   // The components needed to cast the spell.
    protected String description = "";                  // A description of the functionality of the spell.
    protected String school = "";                       // A one-word description of which school of magic the spell falls under.
    protected String castingTime = "";                  // How long the spell takes to cast.
    protected String range = "";                        // The effective distance the spell maybe cast from.
    protected String duration = "";                     // How long the effects of the spell last.
    protected boolean canBeRitual = false;              // Whether or not the spell can be cast as a ritual.
    
    // Constructor for all fields.
    public Spell(String idIn, String nameIn, String[] classesIn, String levelIn, String componentsIn, String descriptionIn, String schoolIn,
            String castingTimeIn, String rangeIn, String durationIn, boolean canBeRitualIn) {
        this.id = idIn;
        this.name = nameIn;
        this.classes = classesIn;
        this.level = levelIn;
        this.components = componentsIn;
        this.description = descriptionIn;
        this.school = schoolIn;
        this.castingTime = castingTimeIn;
        this.range = rangeIn;
        this.duration = durationIn;
        this.canBeRitual = canBeRitualIn;
    } // End constructor for all fields.
    
    // Setters and getters.
    // For id.
    public void setID(String idIn) {this.id = idIn;}
    public String getID() {return this.id;}
    // For name.
    public void setName(String nameIn) {this.name = nameIn;}
    public String getName() {return this.name;}
    // For classes.
    public void setClasses(String[] classesIn) {this.classes = classesIn;}
    public String[] getClasses() {return this.classes;}
    // For level.
    public void setLevel(String levelIn) {this.level = levelIn;}
    public String getLevel() {return this.level;}
    // For components.
    public void setComponents(String componentsIn) {this.components = componentsIn;}
    public String getComponents() {return this.components;}
    // For description.
    public void setDescription(String descriptionIn) {this.description = descriptionIn;}
    public String getDescription() {return this.description;}
    // For school.
    public void setSchool(String schoolIn) {this.school = schoolIn;}
    public String getSchool() {return this.school;}
    // For casting time.
    public void setCastingTime(String castingTimeIn) {this.castingTime = castingTimeIn;}
    public String getCastingTime() {return this.castingTime;}
    // For range.
    public void setRange(String rangeIn) {this.range = rangeIn;}
    public String getRange() {return this.range;}
    // For duration.
    public void setDuration(String durationIn) {this.duration = durationIn;}
    public String getDuration() {return this.duration;}
    // For Ritual flag.
    public void setCanBeRitual(boolean canBeRitualIn) {this.canBeRitual = canBeRitualIn;}
    public boolean getCanBeRitual() {return this.canBeRitual;}
    
} // End public class Spell.