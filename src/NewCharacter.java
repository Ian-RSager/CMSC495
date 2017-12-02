/*
 * File: NewCharacter.java
 * Date: November 11, 2017
 * Purpose: Creates a Gui with a Card Layout to Step by Step allow the user
 *          to create a new D&D 5th Edition character.
 *
 * First draft of Gui is complete, but all backend code needs to be written.
 * Many listeners still need to be implemented.
 */


//Imports
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


@SuppressWarnings("serial")    //Suppress serialVersionUID warning when compiling with -Xlint


//NewCharacter - Class to Display a Gui using a Card Layout
//               to guide a user through creating a new character
public class NewCharacter extends JFrame{

    //Gui Panels
    private JPanel         cardPanel;  //Card Panel
    private JPanelRace     card1;      //First Panel
    private JPanelClass    card2;      //Second Panel
    private JPanelABScores card3;      //Third Panel
    private JPanelAddInfo  card4;      //Fourth Panel
    
    //Background Color for all Screens
    private Color backgroundColor = new Color(252,229,83);

    //Constructor
    public NewCharacter(){
        
        //Set up the Window
        super("Create a Character");
        setSize (800, 800);
        setMinimumSize(new Dimension(800, 800));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create the individual cards
        card1 = new JPanelRace();
        card2 = new JPanelClass();
        card3 = new JPanelABScores();
        card4 = new JPanelAddInfo();

        //Create the Card Panel Layout
        cardPanel = new JPanel(new CardLayout());
        cardPanel.add(card1, "Card1");
        cardPanel.add(card2, "Card2");
        cardPanel.add(card3, "Card3");
        cardPanel.add(card4, "Card4");

        add(cardPanel,BorderLayout.CENTER);

        //Display
        pack();
        setVisible(true);
    }

    
    //Main Test Function
    //Will be taken out when linked with the rest of the program
    public static void main(String[] args){


        NewCharacter nc = new NewCharacter();

    }

    
    

    //JPanelRace - Private Inner Class that handles the 
    //             panel used to obtain input regarding the 
    //             character's race
    private class JPanelRace extends JPanel{

        ///////////////
        // Variables //
        ///////////////

        //Text Area for displaying the race description
        private JTextArea jtaInfo = new JTextArea ();

        //Labels
        private JLabel raceLblA   = new JLabel("Select a Race", JLabel.CENTER);
        private JLabel raceLblB   = new JLabel("Select a Race", JLabel.LEFT);
        private JLabel subraceLbl = new JLabel("Select a Subrace", JLabel.LEFT);
        private JLabel raceTxtLbl = new JLabel("Dwarf", JLabel.CENTER);
        private JLabel blankLbl = new JLabel("", JLabel.CENTER);

        //Combo boxes
        private String [] racesStr      = {"Dwarf","Elf","Human","Halfling",
                                           "Dragonborn","Gnome","Half-Elf",
                                           "Half-Orc","Tiefling"};
        private String [] subracesStr   = {"Hill Dwarf","Mountain Dwarf"};
        private JComboBox<String> raceCombo    = new JComboBox<String> (racesStr);
        private JComboBox<String> subRaceCombo = new JComboBox<String> (subracesStr);

        //Buttons
        private JButton jbNext = new JButton ("Save and Continue >");  

        private Font titleFont;
        
        //Scroll pane for jtextarea
        private JScrollPane jsp;

        //Text Strings describing the races
        private String [] raceDescrStr   = {
            "Bold and hardy, dwarves are known as skilled warriors, miners, and "+
            "workers of stone and metal. \n\nRacial Traits: +2 Constitution, "+
            "Darkvision, Dwarven Resilience, Dwarven Combat Training, Stonecunning",
            
            "Elves are a magical people of otherworldly grace, living in the world "+
            "but not entirely part of it. \n\nRacial Traits: +2 Dexterity, Darkvision, "+
            "Keen Senses, Fey Ancestry, Trance",
            
            "Humans are the most adaptable and ambitious people among the common "+
            "races. Whatever drives them, humans are the innovators, the achievers, "+
            "and the pioneers of the worlds.  \n\nRacial Traits:  +1 to All Ability Scores, "+
            "Extra Language",
            
            "The diminutive halflings survive in a world full of larger creatures by "+
            "avoiding notice or, barring that, avoiding offense.  \n\nRacial Traits: "+
            "+2 Dexterity, Lucky, Brave, Halfling Nimbleness",
            
            "Dragonborn look very much like dragons "+
            "standing erect in humanoid form, though they lack wings or a tail.  \n\n"+
            "Racial Traits: +2 Strength, +1 Charisma, Draconic Ancestry, Breath "+
            "Weapon, Damage Resistance",
            
            "A gnome's energy and enthusiasm for living shines through every inch "+
            "of his or her tiny body.  \n\nRacial Traits: +2 Intelligence, Darkvision, "+
            "Gnome Cunning",
            
            "Half-elves combine what some say are the best qualities of their elf "+
            "and human parents.  \n\nRacial Traits:  +2 Charisma, +1 to Two Other "+
            "Ability Scores, Darkvision, Fey Ancestry, Skill Versatility", 
            
            "Half-orcs' grayish pigmentation, sloping foreheads, jutting jaws, "+ 
            "prominent teeth, and towering builds make their orcish heritage "+
            "plain for all to see.  \n\nRacial Traits:  +2 Strength, +1 Constitution, "+
            "Darkvision, Menacing, Relentless Endurance, Savage Attacks",
            
            "To be greeted with stares and whispers, to suffer violence and insult "+
            "on the street, to see mistrust and fear in every eye: this is the lot "+
            "of the tiefling.  \n\nRacial Traits:  +2 Charisma, +1 Intelligence, "+
            "Darkvision, Hellish Resistance, Infernal Legacy"};

        //////////////////////
        // End of Variables //
        //////////////////////


        //Constructor
        public JPanelRace(){

            setBackground(backgroundColor);
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            //Title
            titleFont = new Font("Serif", Font.BOLD, 32);
            raceLblA.setFont(titleFont);
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.gridheight = 1;
            gbc.weighty = 1.0;
            gbc.weightx = 1.0;
            add(raceLblA , gbc);

            
            /////////////////
            // Right Panel //
            /////////////////
            JPanel right = new JPanel(new BorderLayout());
            right.setBackground(backgroundColor);
            
            //Race Description Label
            titleFont = new Font("Serif", Font.BOLD, 26);
            raceTxtLbl.setFont(titleFont);
            right.add(raceTxtLbl,BorderLayout.NORTH);

            //Set up JTextArea for Description
            jtaInfo.setEditable(false);
            jtaInfo.setLineWrap(true);
            jtaInfo.setWrapStyleWord(true);
            jtaInfo.setFont (new java.awt.Font ("Monospaced", 0, 12));
            jsp = new JScrollPane (jtaInfo);
            jsp.setPreferredSize(new Dimension(300, 300));
            jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            jtaInfo.setText(raceDescrStr[0]);
            right.add(jsp,BorderLayout.CENTER);

            
            ////////////////
            // Left Panel //
            ////////////////
            JPanel left = new JPanel(new GridLayout(0, 1));
            left.setBackground(backgroundColor);
            
            //Race Selection Label
            raceLblB.setFont(titleFont);
            left.add(raceLblB);
            
            //Race Combo
            left.add(raceCombo);
            left.add(blankLbl);

            //Select subrace Label
            //titleFont = new Font("Serif", Font.PLAIN, 18);
            subraceLbl.setFont(titleFont);
            left.add(subraceLbl, gbc);

            //Subrace combo
            left.add(subRaceCombo, gbc);
            
            //Add left Panel to the Gui
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridwidth = GridBagConstraints.RELATIVE;
            add(left, gbc);
            
            //Add right Panel to the Gui			
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            add(right, gbc);

            //Save & Continue button
            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.LAST_LINE_END;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            add(jbNext, gbc);

            setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

            ////////////////////
            //Action Listeners//
            ////////////////////
            
            //Next Button
            jbNext.addActionListener   (e -> switchStateNext());
            
            //Race Combo Box
            //Should update the Gui Elements whenever a new race is
            //selected
            raceCombo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    subRaceCombo.removeAllItems();
                    Object selected = comboBox.getSelectedItem();
                    String rStr = selected.toString();
                    
                    raceTxtLbl.setText(rStr);
                    switch(rStr){
                        case "Dwarf":
                            jtaInfo.setText(raceDescrStr[0]);
                            subRaceCombo.addItem("Hill Dwarf");
                            subRaceCombo.addItem("Mountain Dwarf");
                            break;
                        case "Elf":
                            jtaInfo.setText(raceDescrStr[1]);
                            subRaceCombo.addItem("High Elf");
                            subRaceCombo.addItem("Wood Elf");
                            subRaceCombo.addItem("Dark Elf");
                            break;
                        case "Human":
                            jtaInfo.setText(raceDescrStr[2]);
                            break;
                        case "Halfling":
                            jtaInfo.setText(raceDescrStr[3]);
                            subRaceCombo.addItem("Lightfoot");
                            subRaceCombo.addItem("Stout");
                            break;
                        case "Dragonborn":
                            jtaInfo.setText(raceDescrStr[4]);
                            break;
                        case "Gnome":
                            jtaInfo.setText(raceDescrStr[5]);
                            subRaceCombo.addItem("Forest Gnome");
                            subRaceCombo.addItem("Rock Gnome");
                            break;
                        case "Half-Elf":
                            jtaInfo.setText(raceDescrStr[6]);
                            break;
                        case "Half-Orc":
                            jtaInfo.setText(raceDescrStr[7]);
                            break;
                        case "Tiefling":
                            jtaInfo.setText(raceDescrStr[8]);						
                            break;
                        default:
                            jtaInfo.setText("Error, Invalid");	
                            break;
                    }

                                           

                }
            });
        }


        //Method to handle what to do when the Save & Continue
        //Button is clicked
        private void switchStateNext(){
            
            //Obtain and Validate, and store character data
            // (TBD)
            
            //Move to next card
            CardLayout c1 = (CardLayout)(cardPanel.getLayout());
            c1.next(cardPanel);
        }

    }
    
    
    
    
    //JPanelClass - Private Inner Class that handles the 
    //              panel used to obtain input regarding the 
    //              character's class
    private class JPanelClass extends JPanel{

        ///////////////
        // Variables //
        ///////////////


        //Text Area for displaying information about the selected class
        private JTextArea jtaInfo = new JTextArea ();

        //Labels
        private JLabel classLblA   = new JLabel("Select a Class", JLabel.CENTER);
        private JLabel classLblB   = new JLabel("Select a Class", JLabel.LEFT);
        private JLabel startLvlLbl = new JLabel("Starting Level", JLabel.LEFT);
        private JLabel classTxtLbl = new JLabel("Barbarian", JLabel.CENTER);
        private JLabel blankLbl = new JLabel("", JLabel.CENTER);

        //Combo boxes
        private String [] classesStr      = {"Barbarian","Bard","Cleric","Druid",
                                           "Fighter","Monk","Paladin","Ranger",
                                           "Rogue","Sorcerer","Wizard","Warlock"};
        private JComboBox<String> classCombo    = new JComboBox<String> (classesStr);
        
        //Level Spinner (1 to 100, default 1, incr by 1)
        SpinnerNumberModel snm = new SpinnerNumberModel(new Integer(1),new Integer(1),new Integer(100),new Integer(1));
        JSpinner spnLevl = new JSpinner(snm);
    
        //Buttons
        private JButton jbNext = new JButton ("Save and Continue >");    
        private JButton jbPrev = new JButton ("< Prev");    

        private Font titleFont;
        
        //Scroll pane for jtextarea
        private JScrollPane jsp;

        //Descriptions of the classes
        private String [] classDescrStr   = {
            "Barbarians are savage warriors who deal out powerful blows from their mighty" +
            " weapons. They charge from foe to foe and seldom feel the pain of an enemy's" +
            " strike. For barbarians' foes, the moments of greatest terror come when "+
            "barbarians call upon primal forces to lend power to their raging spirits."+
            "These rages, although temporary, give a barbarian incredible powers, a "+
            "combination of skill, willpower, and a legacy of ancient tribal rituals.",
            
            "Bards are artists first and foremost, and they practice magic just as they"+
            " practice song, drama, or poetry. They have a clear sense of how people perceive "+
            "reality, so they master charm magic and some illusions. Sagas of great heroes are" +
            " part of a bard's repertoire, and most bards follow the example of many fables "+
            "and become skilled in a variety of fields. A bard's artistic ability, knowledge "+
            "of lore, and arcane might are widely respected, particularly among the world's rulers.",

            "Clerics are battle leaders who are invested with divine power. They blast foes with "+
            "magical prayers, bolster and heal companions, and lead the way to victory with a mace "+
            "in one hand and a holy symbol in the other. Clerics run the gamut from humble servants "+
            "of the common folk to ruthless enforcers of evil gods.",
            
            "Secretive and enigmatic, druids call the wilderness their home. They are capable of "+
            "running with a wolf pack, speaking with the most ancient trees, and watching thunderstorms "+
            "from atop the clouds themselves. They regard challenges as tests, both of their fitness and "+
            "of their connection with the wild places of the world. And though many druids project an "+
            "outward calm, they have the cunning of the beast and the fury of the storm.",
            
            "Fighters are determined combat adepts trained to protect the other members of their adventuring "+
            "groups. Fighters define the front line by bashing and slicing foes into submission while reflecting "+
            "enemy attacks through the use of heavy armor. Fighters draw weapons for gold, for glory, for duty, "+
            "and for the mere joy of unrestrained martial exercise.",

            "From high in forbidding mountains to the alleys of a city's meanest district, the ascetic warriors "+
            "known as monks practice their art. By focusing on their minds and bodies, honing both to near perfection, "+
            "they master a psionic fighting art that allows them to deliver a punch with the force of a giant's club "+
            "and to absorb attacks as easily as a heavily armored knight. Monks tap into the psionic potential that "+
            "rests within themselves, turning that energy further inward to fortify their bodies and sharpen their minds.",

            "Paladins are indomitable warriors who’ve pledged their prowess to something greater than themselves. "+
            "Paladins smite enemies with divine authority, bolster the courage of nearby companions, and radiate as "+
            "if a beacon of inextinguishable hope. Paladins are transfigured on the field of battle, exemplars of "+
            "divine ethos in action.",
            
            "Rangers are watchful warriors who roam past the horizon to safeguard a region, a principle, or a way of life. "+
            "Masters of bow and blade, rangers excel at hit-and-run assaults and can quickly and silently eliminate foes. "+
            "Rangers lay superb ambushes and excel at avoiding danger.",
            
            "Rogues are cunning and elusive adversaries. Rogues slip into and out of shadows on a whim, pass anywhere across "+
            "the field of battle without fear of reprisal, and appear suddenly only to drive home a lethal blade.",
            
            "The sorcerer is the arcane antithesis of the wizard. Wielding raw, barely contained magical power, sorcerers "+
            "channel bursts and blasts of arcane energy through their bodies. They gain their power not through rigorous "+
            "study of esoteric tomes, but by harnessing magic in their blood, waiting to be tapped and shaped. If wizards "+
            "wield magic as fighters wield swords, a sorcerer's magic is the arcing greataxe of a raging barbarian.",
            
            "Wizards are scions of arcane magic. Wizards tap the true power that permeates the cosmos, research esoteric "+
            "rituals that can alter time and space, and hurl balls of fire that incinerate massed foes. Wizards wield "+
            "spells the way warriors brandish swords.",
            
            "Warlocks channel arcane might wrested from primeval entities. They commune "+
            "with infernal intelligences and fey spirits, scour enemies with potent blasts "+
            "of eldritch power, and bedevil foes with hexing curses. Armed with esoteric "+
            "secrets and dangerous lore, warlocks are clever and resourceful foes."
        };

        //////////////////////
        // End of Variables //
        //////////////////////


        //Constructor
        public JPanelClass(){

            JPanel helper = new JPanel();
            helper.setLayout(new FlowLayout(FlowLayout.LEFT));
            helper.setBackground(backgroundColor);
            setLayout(new GridBagLayout());
            setBackground(backgroundColor);
            GridBagConstraints gbc = new GridBagConstraints();

            //Title
            titleFont = new Font("Serif", Font.BOLD, 32);
            classLblA.setFont(titleFont);
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.gridheight = 1;
            gbc.weighty = 1.0;
            gbc.weightx = 1.0;
            add(classLblA , gbc);
            

            
            /////////////////
            // Right Panel //
            /////////////////
            JPanel right = new JPanel(new BorderLayout());
            right.setBackground(backgroundColor);
            
            //Class Description Label
            titleFont = new Font("Serif", Font.BOLD, 26);
            classTxtLbl.setFont(titleFont);
            right.add(classTxtLbl,BorderLayout.NORTH);

            //Set up JTextArea for Description
            jtaInfo.setEditable(false);
            jtaInfo.setLineWrap(true);
            jtaInfo.setWrapStyleWord(true);
            jtaInfo.setFont (new java.awt.Font ("Monospaced", 0, 12));
            jsp = new JScrollPane (jtaInfo);
            jsp.setPreferredSize(new Dimension(300, 300));
            jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            jtaInfo.setText(classDescrStr[0]);
            right.add(jsp,BorderLayout.CENTER);

            
            ////////////////
            // Left Panel //
            ////////////////
            JPanel left = new JPanel(new GridLayout(0, 1));
            left.setBackground(backgroundColor);
            
            //Class Selection Label
            classLblB.setFont(titleFont);
            left.add(classLblB);
            
            //Starting Level Value
            left.add(classCombo);
            
            left.add(blankLbl);
            left.add(blankLbl);

            //Select Starting Level
            startLvlLbl.setFont(titleFont);
            
            left.add(startLvlLbl);

            //Starting Level
            helper.add(spnLevl);
            left.add(helper);
            
            //Add Left Panel to Gridbag Layout
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridwidth = GridBagConstraints.RELATIVE;
            add(left, gbc);
            
            //Add Right Panel to Gridbag Layout			
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            add(right, gbc);
            
            //Prev button
            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.LAST_LINE_START;
            gbc.gridwidth = GridBagConstraints.RELATIVE;
            add(jbPrev, gbc);

            //Save & Continue button
            gbc.gridx = 1;
            gbc.gridy = 6;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.LAST_LINE_END;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            add(jbNext, gbc);

            setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

            //Action Listeners - Next/Prev Buttons
            jbNext.addActionListener   (e -> switchStateNext());
            jbPrev.addActionListener   (e -> switchStatePrev());
            
            //Action Listener for Class Selection
            //Will update Gui Elements when a new class is selected
            classCombo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    
                    Object selected = comboBox.getSelectedItem();
                    String rStr = selected.toString();
                                        
                    classTxtLbl.setText(rStr);
                    switch(rStr){
                        case "Barbarian":
                            jtaInfo.setText(classDescrStr[0]);
                            break;
                        case "Bard":
                            jtaInfo.setText(classDescrStr[1]);
                            break;
                        case "Cleric":
                            jtaInfo.setText(classDescrStr[2]);
                            break;
                        case "Druid":
                            jtaInfo.setText(classDescrStr[3]);
                            break;
                        case "Fighter":
                            jtaInfo.setText(classDescrStr[4]);
                            break;
                        case "Monk":
                            jtaInfo.setText(classDescrStr[5]);
                            break;
                        case "Paladin":
                            jtaInfo.setText(classDescrStr[6]);
                            break;
                        case "Ranger":
                            jtaInfo.setText(classDescrStr[7]);
                            break;
                        case "Rogue":
                            jtaInfo.setText(classDescrStr[8]);						
                            break;
                        case "Sorcerer":
                            jtaInfo.setText(classDescrStr[9]);						
                            break;
                        case "Wizard":
                            jtaInfo.setText(classDescrStr[10]);						
                            break;
                        case "Warlock":
                            jtaInfo.setText(classDescrStr[11]);						
                            break;
                        default:
                            jtaInfo.setText("Error, Invalid");	
                            break;
                    }
                }
            });
        }

        //Moves to Previous Card
        private void switchStatePrev(){
            //Go to previous card
            CardLayout c1 = (CardLayout)(cardPanel.getLayout());
            c1.previous(cardPanel);
        }

        //Moves to Next Card
        private void switchStateNext(){
            
            //Obtain and Validate, and store character data
            // (TBD)
            
            //Go to next card
            CardLayout c1 = (CardLayout)(cardPanel.getLayout());
            c1.next(cardPanel);
        }

    }




    //JPanelABScores - Private Inner Class that handles the 
    //                 panel used to obtain input regarding the 
    //                 character's Ability Scores
    private class JPanelABScores extends JPanel{

        ///////////////
        // Variables //
        ///////////////


        //Labels
        private JLabel abilityLblA   = new JLabel("Determine Ability Scores", JLabel.CENTER);
        private JLabel abilityLblB   = new JLabel("Ability Scores", JLabel.CENTER);
        private JLabel strLbl = new JLabel("Strength (STR)", JLabel.LEFT);
        private JLabel dexLbl = new JLabel("Dexterity (DEX)", JLabel.LEFT);
        private JLabel constLbl = new JLabel("Constitution (CON)", JLabel.LEFT);
        private JLabel intLbl = new JLabel("Intelligence (INT)", JLabel.LEFT);
        private JLabel wisdomLbl = new JLabel("Wisdom (WIS)", JLabel.LEFT);
        private JLabel charismaLbl = new JLabel("Charisma (CHA)", JLabel.LEFT);
        private JLabel ptsassignLbl = new JLabel("Total Points Assigned: 60", JLabel.LEFT);
        
        private JLabel rollStr = new JLabel("  0",JLabel.CENTER);
        private JLabel rollDex = new JLabel("  0",JLabel.CENTER);
        private JLabel rollCon = new JLabel("  0",JLabel.CENTER);
        private JLabel rollInt = new JLabel("  0",JLabel.CENTER);
        private JLabel rollWis = new JLabel("  0",JLabel.CENTER);
        private JLabel rollCha = new JLabel("  0",JLabel.CENTER);
        
        //Spinners (-100 to 100, default 10, incr by 1)
        SpinnerNumberModel snm1 = new SpinnerNumberModel(new Integer(10),new Integer(-100),new Integer(100),new Integer(1));
        SpinnerNumberModel snm2 = new SpinnerNumberModel(new Integer(10),new Integer(-100),new Integer(100),new Integer(1));
        SpinnerNumberModel snm3 = new SpinnerNumberModel(new Integer(10),new Integer(-100),new Integer(100),new Integer(1));
        SpinnerNumberModel snm4 = new SpinnerNumberModel(new Integer(10),new Integer(-100),new Integer(100),new Integer(1));
        SpinnerNumberModel snm5 = new SpinnerNumberModel(new Integer(10),new Integer(-100),new Integer(100),new Integer(1));
        SpinnerNumberModel snm6 = new SpinnerNumberModel(new Integer(10),new Integer(-100),new Integer(100),new Integer(1));		
        JSpinner spn1 = new JSpinner(snm1);
        JSpinner spn2 = new JSpinner(snm2);
        JSpinner spn3 = new JSpinner(snm3);
        JSpinner spn4 = new JSpinner(snm4);
        JSpinner spn5 = new JSpinner(snm5);
        JSpinner spn6 = new JSpinner(snm6);
        
        
        //Buttons
        private JButton jbNext = new JButton ("Save and Continue >");    
        private JButton jbPrev = new JButton ("< Prev");    

        private Font titleFont;
        


        //////////////////////
        // End of Variables //
        //////////////////////


        public JPanelABScores(){

            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            setBackground(backgroundColor);

            //Title
            titleFont = new Font("Serif", Font.BOLD, 32);
            abilityLblA.setFont(titleFont);
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(0,0,0,1); 
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.gridheight = 1;
            gbc.weighty = 1.0;
            gbc.weightx = 1.0;
            add(abilityLblA , gbc);
            
            //Titles for left/right panels
            titleFont = new Font("Serif", Font.BOLD, 22);
            JLabel ptBuy = new JLabel("Point Buy",JLabel.CENTER);
            abilityLblB.setFont(titleFont);
            ptBuy.setFont(titleFont);
            JButton bRoll = new JButton("Roll");
            bRoll.setFont(titleFont);
            
            
            
            ///////////////////
            // Ability Panel //
            ///////////////////
            JPanel abilityPanel = new JPanel(new GridLayout(0, 3));
            JPanel f1 = new JPanel(new FlowLayout());
            JPanel f2 = new JPanel(new FlowLayout());
            JPanel f3 = new JPanel(new FlowLayout());
            JPanel f4 = new JPanel(new FlowLayout());
            JPanel f5 = new JPanel(new FlowLayout());
            JPanel f6 = new JPanel(new FlowLayout());
            abilityPanel.setBackground(backgroundColor);
            f1.setBackground(backgroundColor);
            f2.setBackground(backgroundColor);
            f3.setBackground(backgroundColor);
            f4.setBackground(backgroundColor);
            f5.setBackground(backgroundColor);
            f6.setBackground(backgroundColor);
            
            abilityPanel.add(abilityLblB);
            abilityPanel.add(ptBuy);
            abilityPanel.add(bRoll);
            
            //Strength
            titleFont = new Font("Serif", Font.PLAIN, 22);
            strLbl.setFont(titleFont);
            abilityPanel.add(strLbl);
            f1.setFont(titleFont);
            f1.add(spn1);
            abilityPanel.add(f1);
            abilityPanel.add(rollStr);

            //Dexterity
            dexLbl.setFont(titleFont);
            abilityPanel.add(dexLbl);
            f2.add(spn2);
            abilityPanel.add(f2);
            abilityPanel.add(rollDex);
            
            //Const
            constLbl.setFont(titleFont);
            abilityPanel.add(constLbl);
            f3.add(spn3);
            abilityPanel.add(f3);
            abilityPanel.add(rollCon);

            //Intelligence
            intLbl.setFont(titleFont);
            abilityPanel.add(intLbl);
            f4.add(spn4);
            abilityPanel.add(f4);
            abilityPanel.add(rollInt);

            //Wisdom
            wisdomLbl.setFont(titleFont);
            abilityPanel.add(wisdomLbl);
            f5.add(spn5);
            abilityPanel.add(f5);
            abilityPanel.add(rollWis);

            //Charisma
            charismaLbl.setFont(titleFont);
            abilityPanel.add(charismaLbl);
            f6.add(spn6);
            abilityPanel.add(f6);	
            abilityPanel.add(rollCha);		
            
            //Add Panel to the Main Panel
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            add(abilityPanel, gbc);
            
            //Radio Button Group to Choose between Rolled Abilities
            //or Point assigned abilities
            JRadioButton rPt   = new JRadioButton("Use Point Buy");
            JRadioButton rRoll = new JRadioButton("Use Roll");
            ButtonGroup rgroup = new ButtonGroup();
            rPt.setBackground(backgroundColor);
            rPt.setFont(titleFont);
            rRoll.setBackground(backgroundColor);
            rRoll.setFont(titleFont);
            rgroup.add(rPt);
            rgroup.add(rRoll);
            
            //Total Points Assigned
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.LAST_LINE_START;
            gbc.gridwidth = GridBagConstraints.RELATIVE;
            ptsassignLbl.setFont(titleFont);
            add(ptsassignLbl, gbc);
            
            //Radio Button Selection
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridwidth = GridBagConstraints.RELATIVE;
            add(rPt, gbc);
            gbc.gridx = 1;
            gbc.gridy = 4;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            add(rRoll, gbc);
            
            //Prev button
            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.LAST_LINE_START;
            gbc.gridwidth = GridBagConstraints.RELATIVE;
            add(jbPrev, gbc);

            //Save & Continue button
            gbc.gridx = 1;
            gbc.gridy = 5;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.LAST_LINE_END;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            add(jbNext, gbc);


            setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

            //Action Listeners
            jbNext.addActionListener   (e -> switchStateNext());
            jbPrev.addActionListener   (e -> switchStatePrev());
            
        }

        //Method to go to Previous Card
        private void switchStatePrev(){
            //Go to previous card
            CardLayout c1 = (CardLayout)(cardPanel.getLayout());
            c1.previous(cardPanel);
        }

        //Method to go to Next Card
        private void switchStateNext(){
            
            //Obtain and Validate, and store character data
            // (TBD)
            
            //Go to next card
            CardLayout c1 = (CardLayout)(cardPanel.getLayout());
            c1.next(cardPanel);
        }

    }	





    //JPanelAddInfo -  Private Inner Class that handles the 
    //                 panel used to obtain input regarding the 
    //                 character's Additional Information
    private class JPanelAddInfo extends JPanel{

        ///////////////
        // Variables //
        ///////////////


        //Labels
        private JLabel addInfoLbl   = new JLabel("Additional Information", JLabel.CENTER);
    
        private JLabel nameLbl   = new JLabel("Name:", JLabel.LEFT);
        private JLabel weightLbl = new JLabel("Weight:", JLabel.LEFT);
        private JLabel heightLbl = new JLabel("Height:", JLabel.LEFT);
        private JLabel sexLbl = new JLabel("Sex:", JLabel.LEFT);
        private JLabel alignmentLbl = new JLabel("Alignment:", JLabel.LEFT);
        private JLabel backgroundLbl = new JLabel("Background:", JLabel.LEFT);

        //Text Fields		
        private JTextField nameTxt   = new JTextField("", 10);
        private JTextField weightTxt = new JTextField("", 10);
        private JTextField heightTxt = new JTextField("", 10);		

        //Combo boxes
        private String [] sexStr      = {"Male","Female"};
        private JComboBox<String> sexCombo    = new JComboBox<String> (sexStr);
        private String [] alignmentStr      = {"Lawful Good", "Neutral Good", "Chaotic Good",
                                               "Lawful Neutral", "Neutral", "Chaotic Neutral",
                                               "Lawful Evil","Neutral Evil","Chaotic Evil"};
        private JComboBox<String> alignCombo    = new JComboBox<String> (alignmentStr);
        private String [] backgroundStr      = {"Acolyte","Criminal","Folk Hero","Noble",
                                                "Sage","Soldier","Charlatan","Entertainer",
                                                "Guild Artisan","Hermit","Outlander","Urchin",
                                                "Caravan Specialist","Guild Merchant","Hermit",
                                                "Outlander","Knight","Sailor","Pirate"};
        private JComboBox<String> backCombo    = new JComboBox<String> (backgroundStr);
    

        //Buttons
        private JButton jbUpload = new JButton("Upload an image");
        private JButton jbNext = new JButton ("Generate Character Sheet");    
        private JButton jbPrev = new JButton ("< Prev");    

        private Font titleFont;


        //////////////////////
        // End of Variables //
        //////////////////////


        //Constructor
        public JPanelAddInfo(){

            JPanel helper = new JPanel();
            helper.setLayout(new FlowLayout(FlowLayout.LEFT));
            helper.setBackground(backgroundColor);
            setLayout(new GridBagLayout());
            setBackground(backgroundColor);
            GridBagConstraints gbc = new GridBagConstraints();

            //Title
            titleFont = new Font("Serif", Font.BOLD, 32);
            addInfoLbl.setFont(titleFont);
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.gridheight = 1;
            gbc.weighty = 1.0;
            gbc.weightx = 1.0;
            add(addInfoLbl , gbc);
            

            
            ///////////////////////////////////////////
            // Right Panel - Image and Upload Button //
            ///////////////////////////////////////////
            JPanel right = new JPanel(new BorderLayout());
            right.setBackground(backgroundColor);
            BufferedImage photo;
            JLabel photoLabel;
            
            try {
                photo = ImageIO.read(new File("cabbage.jpg"));
                Image resizedPhoto = photo.getScaledInstance(100, 100, Image.SCALE_SMOOTH);	
                photoLabel = new JLabel(new ImageIcon(resizedPhoto));
                photoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
                photoLabel.setSize(50, 50);
                //Show Image
                right.add(photoLabel,BorderLayout.CENTER);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            //Upload Image Button
            titleFont = new Font("Serif", Font.BOLD, 26);
            jbUpload.setFont(titleFont);
            right.add(jbUpload,BorderLayout.SOUTH);

            
            ////////////////////////////////////
            // Left Panel - User Entered Data //
            ////////////////////////////////////
            JPanel left = new JPanel(new GridLayout(0, 2));
            left.setBackground(backgroundColor);
            
            //Class Selection Label
            nameLbl.setFont(titleFont);
            nameTxt.setFont(titleFont);
            left.add(nameLbl);
            left.add(nameTxt);
            
            weightLbl.setFont(titleFont);
            weightTxt.setFont(titleFont);
            left.add(weightLbl);
            left.add(weightTxt);
            
            heightLbl.setFont(titleFont);
            heightTxt.setFont(titleFont);
            left.add(heightLbl);
            left.add(heightTxt);
            
            sexLbl.setFont(titleFont);
            sexCombo.setFont(titleFont);
            left.add(sexLbl);
            left.add(sexCombo);
            
            alignmentLbl.setFont(titleFont);
            alignCombo.setFont(titleFont);
            left.add(alignmentLbl);
            left.add(alignCombo);
            
            backgroundLbl.setFont(titleFont);
            backCombo.setFont(titleFont);
            left.add(backgroundLbl);
            left.add(backCombo);
            
            //Add left panel to Grid Bag Layout
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridwidth = GridBagConstraints.RELATIVE;
            add(left, gbc);
            
            //Add right panel to Grid Bag Layout
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            add(right, gbc);
            
            //Prev button
            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.LAST_LINE_START;
            gbc.gridwidth = GridBagConstraints.RELATIVE;
            add(jbPrev, gbc);

            //Save & Continue button
            gbc.gridx = 1;
            gbc.gridy = 6;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.LAST_LINE_END;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            add(jbNext, gbc);

            setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

            //Action Listeners for Buttons
            jbNext.addActionListener   (e -> generateCharacter());
            jbPrev.addActionListener   (e -> switchStatePrev());
        }

        //Method to go to Previous Card
        private void switchStatePrev(){
            //Go to previous card
            CardLayout c1 = (CardLayout)(cardPanel.getLayout());
            c1.previous(cardPanel);
        }

        //Method to go to Next Card
        private void generateCharacter(){
            
            //Obtain and Validate, and store character data
            // (TBD)
            
            //Go to Main Gui

        }

    }
    

}



 