//package charactercreator;

/*
 * This class is used to define Weapon objects.  A weapon is any item that can be used by a character to inflict
 * harm upon another player or NPC.  Thrown melee weapons will have both the isMelee and isRanged flags set to true, and will use the normalRange and
 * maximumRange values to indicate the appropriate ranges to use when thrown.
 */

public class Weapon extends Item {
    protected String damage = "1";                  // A value representing the damage the weapon can inflict, as a number of multi-sided dice.
    protected boolean isMelee = true;               // If the weapon is designed to be used in melee.
    protected boolean isRanged = false;             // If the weapon is designed to be used at range.
    protected boolean hasReach = false;             // If the weapon has reach, adding an additional 5 feet of range to its melee attacks if the wielder desires.
    protected int normalRange = 0;                  // The range, in feet, at which the weapon is able to be used without penalty.
    protected int maximumRange = 0;                 // The maximum range the weapon can be used at, between normalRange and this value, the wielder has Disadvantage on attacks.
    protected boolean isLoading = false;            // If the weapon can only fire one round of ammunition per attack action, due to needing to be reloaded.
    protected boolean hasFinesse = false;           // If the weapon allows the user to utilize their Dexterity instead of Strength for attack and damage rolls.
    protected boolean isHeavy = false;              // If Small creatures have Disadvantage on attack rolls with this weapon.
    protected boolean isLight = false;              // If this weapon provides bonuses when dual wielding.
    protected boolean isTwoHanded = false;          // If this weapon requires two hands to use.
    protected boolean isVersatile = false;          // If this weapon can be used in one or two hands.
    protected String versatileDamage = "";          // If the weapon is Versatile, this is damage done when it is wielded in both of the user's hands.
    protected boolean isSpecial = false;            // If the weapon has any special rules or properties.
    protected String isSpecialDescription = "";     // If the weapon is Special, a description of its special rules or properties.
    protected boolean isThrown;                     // If the weapon has the ability to be thrown, i.e. a dagger.

    // Constructor to be used when reading in data from weapons.json
    public Weapon(String idIn, String nameIn, int costIn, String costCurrencyIn, String damageIn, double weightIn,
                  boolean isMeleeIn, boolean isRangedIn, boolean hasReachIn, boolean isThrownIn,
                  int normalRangeIn, int maximumRangeIn, boolean isLoadingIn, boolean hasFinesseIn,
                  boolean isHeavyIn, boolean isLightIn, boolean isTwoHandedIn, boolean isVersatileIn,
                  boolean isSpecialIn, String versatileDamageIn, String isSpecialDescriptionIn, String descriptionIn) {

        super(idIn, nameIn, costIn, costCurrencyIn, weightIn, descriptionIn);
        this.damage = damageIn;
        this.isMelee = isMeleeIn;
        this.isRanged = isRangedIn;
        this.hasReach = hasReachIn;
        this.isThrown = isThrownIn;
        this.normalRange = normalRangeIn;
        this.maximumRange = maximumRangeIn;
        this.isLoading = isLoadingIn;
        this.hasFinesse = hasFinesseIn;
        this.isHeavy = isHeavyIn;
        this.isLight = isLightIn;
        this.isTwoHanded = isTwoHandedIn;
        this.isVersatile = isVersatileIn;
        this.versatileDamage = versatileDamageIn;
        this.isSpecial = isSpecialIn;
        this.isSpecialDescription = isSpecialDescriptionIn;
    } // End constructor for all fields.

    /**
     * printData serves as a temporary QC. It will not necessarily be used during operation.
     */
    @Override
    public void printData(){
        super.printData();
        try {
            System.out.println("Damage : " + this.damage);
            System.out.println("Armor Class: " + this.isMelee);
            System.out.println("isRanged: " + this.isRanged);
            System.out.println("hasReach: " + this.hasReach);
            System.out.println("has Reach: " + this.isThrown);
            System.out.println("Normal Range : " + this.normalRange);
            System.out.println("Maximum Range : " + this.maximumRange);
            System.out.println("is loading : " + this.isLoading);
            System.out.println("has finesse: " + this.hasFinesse);
            System.out.println("is heavy : " + this.isHeavy);
            System.out.println("is light : " + this.isLight);
            System.out.println("is 2-handed : " + this.isTwoHanded);
            System.out.println("is versatile : " + this.isVersatile);
            System.out.println("Versatility description : " + this.versatileDamage);
            System.out.println("is special : " + this.isSpecial);
            System.out.println("special description : " + this.isSpecialDescription);
            System.out.println("Total cost is " + this.totalCost + " for " + this.quantity + " pieces, at " + this.singleCost + this.costCurrency + " per piece.");
        }catch (Exception e){
            System.out.println("\n\n\t **************  Weapon - OOPS ************** \n\n");
        }

    }

    // Getters and setters.
    // For damage.
    public void setDamage(String damageIn) {this.damage = damageIn;}
    public String getDamage() {return this.damage;}
    // For Melee flag.
    public void setIsMelee(boolean isMeleeIn) {this.isMelee = isMeleeIn;}
    public boolean getIsMelee() {return this.isMelee;}
    // For Ranged flag.
    public void setIsRanged(boolean isRangedIn) {this.isRanged = isRangedIn;}
    public boolean getIsRanged() {return this.isRanged;}
    // For Reach flag.
    public void setHasReach(boolean hasReachIn) {this.hasReach = hasReachIn;}
    public boolean getHasReach() {return this.hasReach;}
    // For Thrown flag
    public void setIsThrown(boolean isThrownIn){this.isThrown = isThrownIn;}
    public boolean getIsThrown() {return this.isThrown;}
    // For normal range.
    public void setNormalRange(int normalRangeIn) {this.normalRange = normalRangeIn;}
    public int getNormalRange() {return this.normalRange;}
    // For maximum range.
    public void setMaximumRange(int maximumRangeIn) {this.maximumRange = maximumRangeIn;}
    public int getMaximumRange() {return this.maximumRange;}
    // For Loading flag.
    public void setIsLoading(boolean isLoadingIn) {this.isLoading = isLoadingIn;}
    public boolean getIsLoading() {return this.isLoading;}
    // For Finesse flag.
    public void setHasFinesse(boolean hasFinesseIn) {this.hasFinesse = hasFinesseIn;}
    public boolean getHasFinesse() {return this.hasFinesse;}
    // For Heavy flag.
    public void setIsHeavy(boolean isHeavyIn) {this.isHeavy = isHeavyIn;}
    public boolean getIsHeavy() {return this.isHeavy;}
    // For Light flag.
    public void setIsLight(boolean isLightIn) {this.isLight = isLightIn;}
    public boolean getIsLight() {return this.isLight;}
    // For Two-Handed flag.
    public void setIsTwoHanded(boolean isTwoHandedIn) {this.isTwoHanded = isTwoHandedIn;}
    public boolean getIsTwoHanded() {return this.isTwoHanded;}
    // For Versatile flag.
    public void setIsVersatile(boolean isVersatileIn) {this.isVersatile = isVersatileIn;}
    public boolean getIsVersatile() {return this.isVersatile;}
    // For Versatile damage.
    public void setVersatileDamage(String versatileDamageIn) {this.versatileDamage = versatileDamageIn;}
    public String getVersatileDamage() {return this.versatileDamage;}
    // For Special flag.
    public void setIsSpecial(boolean isSpecialIn) {this.isSpecial = isSpecialIn;}
    public boolean getIsSpecial() {return this.isSpecial;}
    // For Special description.
    public void setIsSpecialDescription(String isSpecialDescriptionIn) {this.isSpecialDescription = isSpecialDescriptionIn;}
    public String getIsSpecialDescription() {return this.isSpecialDescription;}

} // End public class Weapon.
