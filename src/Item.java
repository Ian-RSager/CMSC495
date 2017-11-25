package charactercreator;

/*
 * This class is used to define Item objects.  An Item object is the basic type of any physical thing a character can have in their
 * inventory.  Other classes will extend the Item class to add specific features needed for weapons, armor, etc.
 */

public class Item {
    protected String id = "";           // A unique string identifying the item, for internal use only.
    protected String name = "";         // The name of the item, as the user will see it.
    protected double singleWeight = 0;  // The weight of just one of this item.
    protected double totalWeight = 0;   // The weight of the total quantity of this item.
    protected String description = "";  // A description of the item.
    protected int quantity = 1;         // The number of this item carried by the character.
    protected int singleCost = 0;       // The cost of just one of this item.
    protected int totalCost = 0;        // The cost of the total quantity of this item.
    
    // Constructor, all fields minus totals.  Best used for reading in items from a JSON or saved character file.
    public Item(String idIn, String nameIn, double singleWeightIn, String descriptionIn, int quantityIn, int singleCostIn) {
        this.id = idIn;
        this.name = nameIn;
        this.singleWeight = singleWeightIn;
        this.description = descriptionIn;
        this.quantity = quantityIn;
        this.singleCost = singleCostIn;
        
        this.totalWeight = this.singleWeight * this.quantity;
        this.totalCost = this.singleCost * this.quantity;
    } // End constructor, all fields.
    
    // Blank constructor.
    public Item() {
        this.id = "defaultItem";
        this.name = "Default Item";
        this.singleWeight = 0.0;
        this.totalWeight = 0.0;
        this.description = "A really generic item.";
        this.quantity = 1;
        this.singleCost = 0;
        this.totalCost = 0;
    } // End blank constructor.
    
    // Setters and getters.  Note that no setters are available for totalWeight or totalCost.  This is intentional, as these numbers are automatically
    // manipulated within this class when needed.  At all other times, these numbers should only be altered by editing the quantity of the item currently
    // in the character's inventory.
    // For ID.
    public void setID(String idIn) {this.id = idIn;}   
    public String getID() {return this.id;}
    // For name.
    public void setName(String nameIn) {this.name = nameIn;}
    public String getName() {return this.name;}
    // For singleWeight.
    public void setSingleWeight(double weightIn) {this.singleWeight = weightIn;}
    public double getSingleWeight() {return this.singleWeight;}
    // For totalWeight.
    public double getTotalWeight() {return this.totalWeight;}
    // For description.
    public void setDescription(String descriptionIn) {this.description = descriptionIn;}
    public String getDescription() {return this.description;}
    // Get quantity.
    public int getQuantity() {return this.quantity;}
    // Set quantity.  Note that this will automatically update totalWeight and totalCost.
    public void setQuantity(int quantityIn) {
        this.quantity = quantityIn;
        this.totalWeight = this.singleWeight * this.quantity;
        this.totalCost = this.singleCost * this.quantity;
    } // End set quantity.
    // For singleCost.
    public void setSingleCost(int singleCostIn) {this.singleCost = singleCostIn;}
    public int getSingleCost() {return this.singleCost;}
    // For totalCost.
    public double getTotalCost() {return this.totalCost;}
} // End public class Item.