package charactercreator;

/*
 * This class provides a single method to calculate the bonus of a character's attribute (Strength, Dexterity, etc.)
 * 
 * Change Log
 * --12/10/2017--
 * Changed switch statement into much more condensed method.
 */

public class AttributeBonusCalculator {
    public static int getAttributeBonus(int attributeIn) {
        double attributeToCalculate = attributeIn;
        int modifier = (int)Math.floor((attributeToCalculate - 10)/2);
        return modifier;
    } // End public int getAttributeBonus.
} // End public class AttributeBonusCalculator.