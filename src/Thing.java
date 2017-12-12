package charactercreator;

/**
 * This class creates Thing objects and serves as a structural class.
 * This class is not directly used within the program aside from acting as a superclass for all other support classes.
 */

public class Thing implements java.io.Serializable {
    protected String id;                            // A unique string identifying the feature, for internal use only.
    protected String name;                          // The name of the feature, as the user will see it.
    protected String description;                   // A plain text description of the feature.

    // Constructor for structural class
    public Thing(String idIn, String nameIn,  String descriptionIn ) {
        this.id = idIn;
        this.name = nameIn;
        this.description = descriptionIn;
    } // End constructor.

    // no argument, blank constructor
    public Thing(){
        this.id = "default id";
        this.name = "default name";
        this.description = "default description";

    }

    //Getters
    public String getID() {return this.id;}
    public String getName() {return this.name;}
    public String getDescription() {return this.description;}

    public void setId(String idIn) {this.id = idIn;}
    public void setName(String nameIn) {this.name = nameIn;}
    public void setdescription(String descriptionIn) {this.description = descriptionIn;}


}
