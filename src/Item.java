//package charactercreator;

/*
 * This class is used to define Item objects.  An Item object is the basic type of any physical thing a character can have in their
 * inventory.  Other classes will extend the Item class to add specific features needed for weapons, armor, etc.
 */

public class Item extends Thing{

//    protected int cost;
    protected double weight;
    protected String costCurrency;      // The currency of the item, i.e. gold pieces, silver pieces, or copper pieces
    protected double singleWeight = 0;  // The weight of just one of this item.
    protected double totalWeight = 0;   // The weight of the total quantity of this item.
    protected int quantity = 1;         // The number of this item carried by the character.
    protected int singleCost = 0;       // The cost of just one of this item.
    protected int totalCost = 0;        // The cost of the total quantity of this item.

    //Constructor to be used when reading in data from items.json
    public Item(String idIn,String nameIn,int costIn, String costCurrencyIn, double singleWeightIn, String descriptionIn){
        super(idIn, nameIn, descriptionIn);
        this.singleCost = costIn;
        this.singleWeight = singleWeightIn;
        this.costCurrency = costCurrencyIn;

        this.totalWeight = this.singleWeight * this.quantity;
        this.totalCost = this.singleCost * this.quantity;
    }//end constructor

    // blank constructor
    public Item(){
        this.id = "defaultItem";
        this.name = "default Item";
        this.description = "A really generic item.";
        this.singleCost = 0;
        this.singleWeight = 0.0;
        this.costCurrency = "Gold";
        this.quantity = 1;
        this.totalWeight = 1;
        this.totalCost = 0;
    }// end blank constructor


    /**
     * printData serves as a temporary QC. It will not necessarily be used during operation.
     */
    @Override
    public void printData() {

        super.printData();
        try {
            System.out.println("Weight per item: " + this.singleWeight);
            System.out.println("Total cost is " + this.totalCost + " for " + this.quantity + " pieces, at " + this.singleCost + this.costCurrency + " per piece.");
        }catch (Exception e){
            System.out.println("\n\n\t **************  Item - OOPS ************** \n\n");
        }
    }

    // Setters and getters.  Note that no setters are available for totalWeight or totalCost.  This is intentional, as these numbers are automatically
    // manipulated within this class when needed.  At all other times, these numbers should only be altered by editing the quantity of the item currently
    // in the character's inventory.


    // cost currency, GP, SP, or CP
    public void setCostCurrency(String costCurrencyIn) {this.costCurrency = costCurrencyIn;}
    public String getCostCurrency() {return this.costCurrency;}

    // For ID.
    public void setID(String idIn) {this.id = idIn;}
    // For singleWeight.
    public void setSingleWeight(double weightIn) {this.singleWeight = weightIn;}
    public double getSingleWeight() {return this.singleWeight;}
    // For totalWeight.
    public double getTotalWeight() {return this.totalWeight;}
    // For description.
    public void setDescription(String descriptionIn) {this.description = descriptionIn;}
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
