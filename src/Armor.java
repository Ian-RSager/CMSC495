package charactercreator;

/*
 * This class is used to define Armor objects.  These are pieces of body armor or shields that a character will wear in order
 * to gain extra protection from attacks.
 */

public class Armor extends Item {
    protected String armorType = "Light";           // Whether the armor is classified as Light, Medium, Heavy, or a Shield.
    protected int armorClass = 0;                   // The bonus amount of armor this object adds to the character's base 10 armor class.
    protected int maxDexterityBonus = 99;           // The maximum amount of the character's Dexterity attribute bonus they can apply to their armor class if wearing this item.
    protected String donTime = "1 action";          // The amount of time it takes to put this armor on.
    protected String doffTime = "1 action";         // The amount of time it takes to take this armor off.
    protected int strengthThreshold = 0;            // The minimum amount of Strength needed to use this armor without penalty.
    protected boolean hasStealthPenalty = false;    // Whether or not the armor imposes Disadvantage on all Stealth checks by the character while equipped.
    
    // Constructor for all fields relevant specifically to the Armor class.  Good for loading objects from JSON files or creating new Weapon objects in the Character Creator.
    public Armor(String idIn, String nameIn, double singleWeightIn, String descriptionIn, int quantityIn, int singleCostIn,
            String armorTypeIn, int armorClassIn, int maxDexterityBonusIn, String donTimeIn, String doffTimeIn, int strengthThresholdIn, boolean hasStealthPenaltyIn) {
        this.id = idIn;
        this.name = nameIn;
        this.singleWeight = singleWeightIn;
        this.description = descriptionIn;
        this.quantity = quantityIn;
        this.singleCost = singleCostIn;
        
        this.totalWeight = this.singleWeight * this.quantity;
        this.totalCost = this.singleCost * this.quantity;
        
        this.armorType = armorTypeIn;
        this.armorClass = armorClassIn;
        this.maxDexterityBonus = maxDexterityBonusIn;
        this.donTime = donTimeIn;
        this.doffTime = doffTimeIn;
        this.strengthThreshold = strengthThresholdIn;
        this.hasStealthPenalty = hasStealthPenaltyIn;
    } // End constructor for all fields.
    
    // Getters and setters.
    // For armor type.
    public void setArmorType(String armorTypeIn) {this.armorType = armorTypeIn;}   
    public String getArmorType() {return this.armorType;}
    // For armor class.
    public void setArmorClass(int armorClassIn) {this.armorClass = armorClassIn;}
    public int getArmorClass() {return this.armorClass;}
    // For max Dexterity bonus.
    public void setMaxDexterityBonus(int maxDexterityBonusIn) {this.maxDexterityBonus = maxDexterityBonusIn;}
    public int getMaxDexterityBonus() {return this.maxDexterityBonus;}
    // For don time.
    public void setDonTime(String donTimeIn) {this.donTime = donTimeIn;}   
    public String getDonTime() {return this.donTime;}
    // For doff time.
    public void setDoffTime(String doffTimeIn) {this.doffTime = doffTimeIn;}   
    public String getDoffTime() {return this.doffTime;}
    // For Strength threshold.
    public void setStrengthThreshold(int strengthThresholdIn) {this.strengthThreshold = strengthThresholdIn;}
    public int getStrengthThreshold() {return this.strengthThreshold;}
    // For Stealth penalty.
    public void setHasStealthPenalty(boolean hasStealthPenaltyIn) {this.hasStealthPenalty = hasStealthPenaltyIn;}
    public boolean getHasStealthPenalty() {return this.hasStealthPenalty;}
    
} // End public class Armor.