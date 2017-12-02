package charactercreator;

/*
 * This class is used to define ClassFeature objects.  A ClassFeature is a particular mechanic that a character has access to due to
 * one or more of the character classes that they have gained levels in.
 */

public class ClassFeature {
    protected String id;                            // A unique string identifying the feature, for internal use only.
    protected String name;                          // The name of the feature, as the user will see it.
    protected String description;                   // A plain text description of the feature.
    protected String[] classes;                     // An array of the classes, based on the class id tags, which have access to this feature.  Includes subclasses.
    protected String[] tags;                        // Plain text tags of the relevant classes or other descriptors which may be used to search for or describe this feature.
    protected String[][] levelingInfo;              // An array of the elements of the feature that change based upon the character's level in the relevant class.
    
    // Constructor to be used when reading in data from features.json.
    public ClassFeature(String id, String name, String description, String[] classes, String[] tags, String[][] levelingInfo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.classes = classes;
        this.tags = tags;
        this.levelingInfo = levelingInfo;
    } // End constructor.
    
    // Only getter methods will be used, as these values should not change during the operation of the program.
    public String getID() {return this.id;}
    public String getName() {return this.name;}
    public String getDescription() {return this.description;}
    public String[] getClasses() {return this.classes;}
    public String[] getTags() {return this.tags;}
    public String[][] getLevelingInfo() {return this.levelingInfo;}
    
    // Method to determine whether or not this feature has a particular entry in its class array.
    public boolean getHasClass(String classToSearchForIn) {
        for (String classString : this.classes) {
            if (classString.equalsIgnoreCase(classToSearchForIn)) {
                return true;
            } // End if.
        } // End for.
        return false;
    } // End getHasClass.
    
    // Method to determine whether or not this feature has a particular entry in its tag array.
    public boolean getHasTag(String tagToSearchForIn) {
        for (String tagString : this.tags) {
            if (tagString.equalsIgnoreCase(tagToSearchForIn)) {
                return true;
            } // End if.
        } // End for.
        return false;
    } // End getHasTag.
} // End public class ClassFeature.