package charactercreator;

import java.io.Serializable;

/*
 * This class is used to define Armor objects.  These are pieces of body armor or shields that a character will wear in order
 * to gain extra protection from attacks.
 */

public class Armor extends Item implements Serializable {

    protected String armorType = "Light";           // Whether the armor is classified as Light, Medium, Heavy, or a Shield.
    protected int armorClass = 0;                   // The bonus amount of armor this object adds to the character's base 10 armor class.
    protected int maxDexterityBonus = 99;           // The maximum amount of the character's Dexterity attribute bonus they can apply to their armor class if wearing this item.
    protected String donTime = "1 action";          // The amount of time it takes to put this armor on.
    protected String doffTime = "1 action";         // The amount of time it takes to take this armor off.
    protected int strengthThreshold = 0;            // The minimum amount of Strength needed to use this armor without penalty.
    protected boolean hasStealthPenalty = false;    // Whether or not the armor imposes Disadvantage on all Stealth checks by the character while equipped.

    //Constructor to be used when reading in data from armor.json
    public Armor(String idIn, String nameIn, int costIn, String costCurrencyIn,
                 double weightIn, String armorTypeIn, int armorClassIn,
                 int maxDexterityBonusIn, String donTimeIn, String doffTimeIn,
                 int strengthThresholdIn, boolean hasStealthPenaltyIn, String descriptionIn){

        super(idIn, nameIn, costIn, costCurrencyIn, weightIn, descriptionIn);
        this.armorType = armorTypeIn;
        this.armorClass = armorClassIn;
        this.maxDexterityBonus = maxDexterityBonusIn;
        this.donTime = donTimeIn;
        this.doffTime = doffTimeIn;
        this.strengthThreshold = strengthThresholdIn;
        this.hasStealthPenalty =hasStealthPenaltyIn;

    }// end constructor

    // Getters and Setters
    public void setArmorType(String armorTypeIn){this.armorType = armorTypeIn;}
    public String getArmorType(){return this.armorType;}

    public void setArmorClass(int armorClassIn){this.armorClass = armorClassIn;}
    public int getArmorClass(){return this.armorClass;}

    public void setMaxDexterityBonus(int maxDexterityBonusIn) {this.maxDexterityBonus = maxDexterityBonusIn;}
    public int getMaxDexterityBonus(){return this.maxDexterityBonus;}

    public void setDonTime(String donTimeIn){this.armorType = donTimeIn;}
    public String getDonTime(){return this.donTime;}

    public void setDoffTime(String doffTimeIn){this.armorType = doffTimeIn;}
    public String getDoffTime(){return this.doffTime;}

    public void setStrengthThreshold(int strengthThresholdIn){this.strengthThreshold = strengthThresholdIn;}
    public int getStrengthThreshold(){return this.strengthThreshold;}

    public void setHasStealthPenalty(boolean hasStealthPenaltyIn){this.hasStealthPenalty = hasStealthPenaltyIn;}
    public boolean getHasStealthPenalty(){return this.hasStealthPenalty;}


} // End public class Armor.
