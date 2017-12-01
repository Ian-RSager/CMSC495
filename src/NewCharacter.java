/*
 * File: NewCharacter.java
 * Date: December 1, 2017
 * Purpose: Creates a Gui with a Card Layout to Step by Step allow the user
 *          to create a new D&D 5th Edition character.
 *
 * First draft of Gui is complete, but all backend code needs to be written.
 * Background image and resizing added.
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

import java.awt.event.ComponentListener;

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
    private BufferedImage photo;
    private Image resizedPhoto;
    private ImageIcon iiphoto = new ImageIcon();
    private JLabel background;
	
    //Constructor
    public NewCharacter(){
        
        //Set up the Window
        super("Create a Character");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize (700, 685);
        setMinimumSize(new Dimension(700, 685));
        setLocationRelativeTo(null);
		
		try {
			photo = ImageIO.read(new File("background2.jpg"));
		    resizedPhoto = photo.getScaledInstance(700, 685, Image.SCALE_FAST);	
			iiphoto.setImage(resizedPhoto);
			background=new JLabel(iiphoto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			background=new JLabel();
		}
        add(background);
        background.setLayout(new BorderLayout());
		
        //Create the individual cards
        card1 = new JPanelRace();
        card2 = new JPanelClass();
        card3 = new JPanelABScores();
        card4 = new JPanelAddInfo();

        //Create the Card Panel Layout
        cardPanel = new JPanel(new CardLayout());
		cardPanel.setOpaque(false);
        cardPanel.add(card1, "Card1");
        cardPanel.add(card2, "Card2");
        cardPanel.add(card3, "Card3");
        cardPanel.add(card4, "Card4");

        background.add(cardPanel,BorderLayout.CENTER);
		
		//Add a component listener to handle resizing the background image
		//if the window is resized
        background.addComponentListener(new ComponentAdapter () {
            public void componentResized(ComponentEvent e) {
				Dimension newSize = e.getComponent().getBounds().getSize();
		        resizedPhoto = photo.getScaledInstance((int)newSize.getWidth(), (int)newSize.getHeight(), Image.SCALE_FAST);	
	  		    iiphoto.setImage(resizedPhoto);
                background=new JLabel(iiphoto);
            }
        });

        //Display
        pack();
        setVisible(false);
    }
	
	//newCharacterGui - method to create and display the new character creator gui
    public void newCharacterGui(){
	    this.setVisible(true);
    }
    
    //Main Test Function
    //Will be taken out when linked with the rest of the program
    public static void main(String[] args){
        NewCharacter nc = new NewCharacter();
		nc.newCharacterGui();
    }

    
    

    //JPanelRace - Private Inner Class that handles the 
    //             panel used to obtain input regarding the 
    //             character's race
    private class JPanelRace extends JPanel{

        ///////////////
        // Variables //
        ///////////////

        //Text Area for displaying the race description
        private JTextArea raceDescriptionTextArea = new JTextArea ();

        //Labels
        private JLabel raceScreenLabel = new JLabel("Select a Race", JLabel.CENTER);
        private JLabel selectARaceLabel = new JLabel("Select a Race", JLabel.LEFT);
        private JLabel subraceLabel = new JLabel("Select a Subrace", JLabel.LEFT);
        private JLabel raceLabel = new JLabel("Dwarf", JLabel.CENTER);
        private JLabel blankLbl = new JLabel("", JLabel.CENTER);

        //Combo boxes
        private String [] racesStr      = {"Dwarf","Elf","Human","Halfling",
                                           "Dragonborn","Gnome","Half-Elf",
                                           "Half-Orc","Tiefling"};
        private String [] subracesStr   = {"Hill Dwarf","Mountain Dwarf"};
        private JComboBox<String> raceComboBox  = new JComboBox<String> (racesStr);
        private JComboBox<String> subRaceComboBox = new JComboBox<String> (subracesStr);

        //Buttons
        private JButton saveAndContinueButton = new JButton ("Save and Continue >");  

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
            setOpaque(false);
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            //Title
            titleFont = new Font("Default", Font.BOLD, 32);
            raceScreenLabel.setFont(titleFont);
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.gridheight = 1;
            gbc.weighty = 1.0;
            gbc.weightx = 1.0;
            add(raceScreenLabel, gbc);

            
            /////////////////
            // Right Panel //
            /////////////////
            JPanel right = new JPanel(new BorderLayout());
            right.setOpaque(false);
            
            //Race Description Label
            titleFont = new Font("Default", Font.BOLD, 26);
            raceLabel.setFont(titleFont);
            right.add(raceLabel,BorderLayout.NORTH);

            //Set up JTextArea for Description
            raceDescriptionTextArea.setEditable(false);
            raceDescriptionTextArea.setLineWrap(true);
            raceDescriptionTextArea.setWrapStyleWord(true);
            raceDescriptionTextArea.setFont (new java.awt.Font ("Monospaced", 0, 12));
            jsp = new JScrollPane (raceDescriptionTextArea);
            jsp.setPreferredSize(new Dimension(300, 300));
            jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            raceDescriptionTextArea.setText(raceDescrStr[0]);
            right.add(jsp,BorderLayout.CENTER);

            
            ////////////////
            // Left Panel //
            ////////////////
            JPanel left = new JPanel(new GridLayout(0, 1));
            left.setOpaque(false);
            
            //Race Selection Label
            selectARaceLabel.setFont(titleFont);
            left.add(selectARaceLabel);
            
            //Race Combo
            left.add(raceComboBox);
            left.add(blankLbl);

            //Select subrace Label
            //titleFont = new Font("Default", Font.PLAIN, 18);
            subraceLabel.setFont(titleFont);
            left.add(subraceLabel, gbc);

            //Subrace combo
            left.add(subRaceComboBox, gbc);
            
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
            add(saveAndContinueButton, gbc);

            setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

            ////////////////////
            //Action Listeners//
            ////////////////////
            
            //Next Button
            saveAndContinueButton.addActionListener   (e -> switchStateNext());
            
            //Race Combo Box
            //Should update the Gui Elements whenever a new race is
            //selected
            raceComboBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    subRaceComboBox.removeAllItems();
                    Object selected = comboBox.getSelectedItem();
                    String rStr = selected.toString();
                    
                    raceLabel.setText(rStr);
                    switch(rStr){
                        case "Dwarf":
                            raceDescriptionTextArea.setText(raceDescrStr[0]);
                            subRaceComboBox.addItem("Hill Dwarf");
                            subRaceComboBox.addItem("Mountain Dwarf");
                            break;
                        case "Elf":
                            raceDescriptionTextArea.setText(raceDescrStr[1]);
                            subRaceComboBox.addItem("High Elf");
                            subRaceComboBox.addItem("Wood Elf");
                            subRaceComboBox.addItem("Dark Elf");
                            break;
                        case "Human":
                            raceDescriptionTextArea.setText(raceDescrStr[2]);
                            break;
                        case "Halfling":
                            raceDescriptionTextArea.setText(raceDescrStr[3]);
                            subRaceComboBox.addItem("Lightfoot");
                            subRaceComboBox.addItem("Stout");
                            break;
                        case "Dragonborn":
                            raceDescriptionTextArea.setText(raceDescrStr[4]);
                            break;
                        case "Gnome":
                            raceDescriptionTextArea.setText(raceDescrStr[5]);
                            subRaceComboBox.addItem("Forest Gnome");
                            subRaceComboBox.addItem("Rock Gnome");
                            break;
                        case "Half-Elf":
                            raceDescriptionTextArea.setText(raceDescrStr[6]);
                            break;
                        case "Half-Orc":
                            raceDescriptionTextArea.setText(raceDescrStr[7]);
                            break;
                        case "Tiefling":
                            raceDescriptionTextArea.setText(raceDescrStr[8]);						
                            break;
                        default:
                            raceDescriptionTextArea.setText("Error, Invalid");	
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
        private JTextArea classDescriptionTextArea = new JTextArea ();

        //Labels
        private JLabel classScreenLabel = new JLabel("Select a Class", JLabel.CENTER);
        private JLabel selectAClassLabel = new JLabel("Select a Class", JLabel.LEFT);
        private JLabel startingLevelLabel = new JLabel("Starting Level", JLabel.LEFT);
        private JLabel classLabel = new JLabel("Barbarian", JLabel.CENTER);
        private JLabel blankLbl = new JLabel("", JLabel.CENTER);

        //Combo boxes
        private String [] classesStr      = {"Barbarian","Bard","Cleric","Druid",
                                           "Fighter","Monk","Paladin","Ranger",
                                           "Rogue","Sorcerer","Wizard","Warlock"};
        private JComboBox<String> classComboBox = new JComboBox<String> (classesStr);
        
        //Level Spinner (1 to 100, default 1, incr by 1)
        SpinnerNumberModel snm = new SpinnerNumberModel(new Integer(1),new Integer(1),new Integer(100),new Integer(1));
        JSpinner spnLevl = new JSpinner(snm);
    
        //Buttons
        private JButton saveAndContinueButton = new JButton ("Save and Continue >");    
        private JButton previousButton = new JButton ("< Prev");    

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

            "Paladins are indomitable warriors who've pledged their prowess to something greater than themselves. "+
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
            helper.setOpaque(false);
            setLayout(new GridBagLayout());
			setOpaque(false);
            GridBagConstraints gbc = new GridBagConstraints();

            //Title
            titleFont = new Font("Default", Font.BOLD, 32);
            classScreenLabel.setFont(titleFont);
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.gridheight = 1;
            gbc.weighty = 1.0;
            gbc.weightx = 1.0;
            add(classScreenLabel , gbc);
            

            
            /////////////////
            // Right Panel //
            /////////////////
            JPanel right = new JPanel(new BorderLayout());
            right.setOpaque(false);
            
            //Class Description Label
            titleFont = new Font("Default", Font.BOLD, 26);
            classLabel.setFont(titleFont);
            right.add(classLabel,BorderLayout.NORTH);

            //Set up JTextArea for Description
            classDescriptionTextArea.setEditable(false);
            classDescriptionTextArea.setLineWrap(true);
            classDescriptionTextArea.setWrapStyleWord(true);
            classDescriptionTextArea.setFont (new java.awt.Font ("Monospaced", 0, 12));
            jsp = new JScrollPane (classDescriptionTextArea);
            jsp.setPreferredSize(new Dimension(300, 300));
            jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            classDescriptionTextArea.setText(classDescrStr[0]);
            right.add(jsp,BorderLayout.CENTER);

            
            ////////////////
            // Left Panel //
            ////////////////
            JPanel left = new JPanel(new GridLayout(0, 1));
            left.setOpaque(false);
            
            //Class Selection Label
            selectAClassLabel.setFont(titleFont);
            left.add(selectAClassLabel);
            
            //Starting Level Value
            left.add(classComboBox);
            
            left.add(blankLbl);
            left.add(blankLbl);

            //Select Starting Level
            startingLevelLabel.setFont(titleFont);
            
            left.add(startingLevelLabel);

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
            add(previousButton, gbc);

            //Save & Continue button
            gbc.gridx = 1;
            gbc.gridy = 6;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.LAST_LINE_END;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            add(saveAndContinueButton, gbc);

            setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

            //Action Listeners - Next/Prev Buttons
            saveAndContinueButton.addActionListener   (e -> switchStateNext());
            previousButton.addActionListener   (e -> switchStatePrev());
            
            //Action Listener for Class Selection
            //Will update Gui Elements when a new class is selected
            classComboBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    
                    Object selected = comboBox.getSelectedItem();
                    String cStr = selected.toString();
                                        
                    classLabel.setText(cStr);
                    switch(cStr){
                        case "Barbarian":
                            classDescriptionTextArea.setText(classDescrStr[0]);
                            break;
                        case "Bard":
                            classDescriptionTextArea.setText(classDescrStr[1]);
                            break;
                        case "Cleric":
                            classDescriptionTextArea.setText(classDescrStr[2]);
                            break;
                        case "Druid":
                            classDescriptionTextArea.setText(classDescrStr[3]);
                            break;
                        case "Fighter":
                            classDescriptionTextArea.setText(classDescrStr[4]);
                            break;
                        case "Monk":
                            classDescriptionTextArea.setText(classDescrStr[5]);
                            break;
                        case "Paladin":
                            classDescriptionTextArea.setText(classDescrStr[6]);
                            break;
                        case "Ranger":
                            classDescriptionTextArea.setText(classDescrStr[7]);
                            break;
                        case "Rogue":
                            classDescriptionTextArea.setText(classDescrStr[8]);						
                            break;
                        case "Sorcerer":
                            classDescriptionTextArea.setText(classDescrStr[9]);						
                            break;
                        case "Wizard":
                            classDescriptionTextArea.setText(classDescrStr[10]);						
                            break;
                        case "Warlock":
                            classDescriptionTextArea.setText(classDescrStr[11]);						
                            break;
                        default:
                            classDescriptionTextArea.setText("Error, Invalid");	
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
        private JLabel abilityScoreScreenLabel = new JLabel("Determine Ability Scores", JLabel.CENTER);
        private JLabel abilityLblB   = new JLabel("Ability Scores", JLabel.CENTER);
        private JLabel strengthLabel = new JLabel("Strength (STR)", JLabel.LEFT);
        private JLabel dexterityLabel = new JLabel("Dexterity (DEX)", JLabel.LEFT);
        private JLabel constitutionLabel = new JLabel("Constitution (CON)", JLabel.LEFT);
        private JLabel intelligenceLabel = new JLabel("Intelligence (INT)", JLabel.LEFT);
        private JLabel wisdomLabel = new JLabel("Wisdom (WIS)", JLabel.LEFT);
        private JLabel charismaLabel = new JLabel("Charisma (CHA)", JLabel.LEFT);
        private JLabel totalPointsField = new JLabel("Total Points Assigned: 60", JLabel.LEFT);
        
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
        JSpinner strengthJSpinner = new JSpinner(snm1);
        JSpinner dexterityJSpinner = new JSpinner(snm2);
        JSpinner constitutionJSpinner = new JSpinner(snm3);
        JSpinner wisdomJSpinner = new JSpinner(snm4);
        JSpinner intelligenceJSpinner = new JSpinner(snm5);
        JSpinner charismaJSpinner = new JSpinner(snm6);
        
        
        //Buttons
        private JButton saveAndContinueButton = new JButton ("Save and Continue >");    
        private JButton previousButton = new JButton ("< Prev");    

        private Font titleFont;
        


        //////////////////////
        // End of Variables //
        //////////////////////


        public JPanelABScores(){

            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            setOpaque(false);

            //Title
            titleFont = new Font("Default", Font.BOLD, 32);
            abilityScoreScreenLabel.setFont(titleFont);
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(0,0,0,1); 
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.gridheight = 1;
            gbc.weighty = 1.0;
            gbc.weightx = 1.0;
            add(abilityScoreScreenLabel , gbc);
            
            //Titles for left/right panels
            titleFont = new Font("Default", Font.BOLD, 22);
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
            abilityPanel.setOpaque(false);
            f1.setOpaque(false);
            f2.setOpaque(false);
            f3.setOpaque(false);
            f4.setOpaque(false);
            f5.setOpaque(false);
            f6.setOpaque(false);
            
            abilityPanel.add(abilityLblB);
            abilityPanel.add(ptBuy);
            abilityPanel.add(bRoll);
            
            //Strength
            titleFont = new Font("Default", Font.PLAIN, 22);
            strengthLabel.setFont(titleFont);
            abilityPanel.add(strengthLabel);
            f1.setFont(titleFont);
            f1.add(strengthJSpinner);
            abilityPanel.add(f1);
            abilityPanel.add(rollStr);

            //Dexterity
            dexterityLabel.setFont(titleFont);
            abilityPanel.add(dexterityLabel);
            f2.add(dexterityJSpinner);
            abilityPanel.add(f2);
            abilityPanel.add(rollDex);
            
            //Constitution
            constitutionLabel.setFont(titleFont);
            abilityPanel.add(constitutionLabel);
            f3.add(constitutionJSpinner);
            abilityPanel.add(f3);
            abilityPanel.add(rollCon);

            //Intelligence
            intelligenceLabel.setFont(titleFont);
            abilityPanel.add(intelligenceLabel);
            f4.add(intelligenceJSpinner);
            abilityPanel.add(f4);
            abilityPanel.add(rollInt);

            //Wisdom
            wisdomLabel.setFont(titleFont);
            abilityPanel.add(wisdomLabel);
            f5.add(wisdomJSpinner);
            abilityPanel.add(f5);
            abilityPanel.add(rollWis);

            //Charisma
            charismaLabel.setFont(titleFont);
            abilityPanel.add(charismaLabel);
            f6.add(charismaJSpinner);
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
            rPt.setOpaque(false);
            rPt.setFont(titleFont);
            rRoll.setOpaque(false);
            rRoll.setFont(titleFont);
            rgroup.add(rPt);
            rgroup.add(rRoll);
            
            //Total Points Assigned
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.LAST_LINE_START;
            gbc.gridwidth = GridBagConstraints.RELATIVE;
            totalPointsField.setFont(titleFont);
            add(totalPointsField, gbc);
            
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
            add(previousButton, gbc);

            //Save & Continue button
            gbc.gridx = 1;
            gbc.gridy = 5;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.LAST_LINE_END;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            add(saveAndContinueButton, gbc);


            setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

            //Action Listeners
            saveAndContinueButton.addActionListener   (e -> switchStateNext());
            previousButton.addActionListener   (e -> switchStatePrev());
            
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
        private JLabel additionalInfoScreenLabel = new JLabel("Additional Information", JLabel.CENTER);
    
        private JLabel nameLabel   = new JLabel("Name:", JLabel.LEFT);
        private JLabel weightLabel = new JLabel("Weight:", JLabel.LEFT);
        private JLabel heightLabel = new JLabel("Height:", JLabel.LEFT);
        private JLabel sexLabel = new JLabel("Sex:", JLabel.LEFT);
        private JLabel alignmentLabel = new JLabel("Alignment:", JLabel.LEFT);
        private JLabel backgroundLabel = new JLabel("Background:", JLabel.LEFT);

        //Text Fields		
        private JTextField nameTextField   = new JTextField("", 10);
        private JTextField weightTextField = new JTextField("", 10);
        private JTextField heightTextField = new JTextField("", 10);		

        //Combo boxes
        private String [] sexStr      = {"Male","Female"};
        private JComboBox<String> sexComboBox = new JComboBox<String> (sexStr);
        private String [] alignmentStr      = {"Lawful Good", "Neutral Good", "Chaotic Good",
                                               "Lawful Neutral", "Neutral", "Chaotic Neutral",
                                               "Lawful Evil","Neutral Evil","Chaotic Evil"};
        private JComboBox<String> alignmentComboBox  = new JComboBox<String> (alignmentStr);
        private String [] backgroundStr      = {"Acolyte","Criminal","Folk Hero","Noble",
                                                "Sage","Soldier","Charlatan","Entertainer",
                                                "Guild Artisan","Hermit","Outlander","Urchin",
                                                "Caravan Specialist","Guild Merchant","Hermit",
                                                "Outlander","Knight","Sailor","Pirate"};
        private JComboBox<String> backgroundComboBox = new JComboBox<String> (backgroundStr);
    

        //Buttons
        private JButton uploadImageButton = new JButton("Upload an image");
        private JButton generateCharacterSheetButton = new JButton ("Generate Character Sheet");    
        private JButton previousButton = new JButton ("< Prev");    

        private Font titleFont;


        //////////////////////
        // End of Variables //
        //////////////////////


        //Constructor
        public JPanelAddInfo(){

            JPanel helper = new JPanel();
            helper.setLayout(new FlowLayout(FlowLayout.LEFT));
            helper.setOpaque(false);
            setLayout(new GridBagLayout());
            setOpaque(false);
            GridBagConstraints gbc = new GridBagConstraints();

            //Title
            titleFont = new Font("Default", Font.BOLD, 32);
            additionalInfoScreenLabel.setFont(titleFont);
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.gridheight = 1;
            gbc.weighty = 1.0;
            gbc.weightx = 1.0;
            add(additionalInfoScreenLabel , gbc);
            

            
            ///////////////////////////////////////////
            // Right Panel - Image and Upload Button //
            ///////////////////////////////////////////
            JPanel right = new JPanel(new BorderLayout());
            right.setOpaque(false);
            BufferedImage characterImage;
            JLabel photoLabel;
            
            try {
                characterImage = ImageIO.read(new File("cabbage.jpg"));
                Image lresizedPhoto = characterImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);	
                photoLabel = new JLabel(new ImageIcon(lresizedPhoto));
                photoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
                photoLabel.setSize(50, 50);
                //Show Image
                right.add(photoLabel,BorderLayout.CENTER);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            //Upload Image Button
            titleFont = new Font("Default", Font.PLAIN, 18);
            uploadImageButton.setFont(titleFont);
            right.add(uploadImageButton,BorderLayout.SOUTH);

            
            ////////////////////////////////////
            // Left Panel - User Entered Data //
            ////////////////////////////////////
            JPanel left = new JPanel(new GridLayout(0, 2));
            left.setOpaque(false);
            
            //Class Selection Label
            nameLabel.setFont(titleFont);
            nameTextField.setFont(titleFont);
            left.add(nameLabel);
            left.add(nameTextField);
            
            weightLabel.setFont(titleFont);
            weightTextField.setFont(titleFont);
            left.add(weightLabel);
            left.add(weightTextField);
            
            heightLabel.setFont(titleFont);
            heightTextField.setFont(titleFont);
            left.add(heightLabel);
            left.add(heightTextField);
            
            sexLabel.setFont(titleFont);
            sexComboBox.setFont(titleFont);
            left.add(sexLabel);
            left.add(sexComboBox);
            
            alignmentLabel.setFont(titleFont);
            alignmentComboBox.setFont(titleFont);
            left.add(alignmentLabel);
            left.add(alignmentComboBox);
            
            backgroundLabel.setFont(titleFont);
            backgroundComboBox.setFont(titleFont);
            left.add(backgroundLabel);
            left.add(backgroundComboBox);
            
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
            add(previousButton, gbc);

            //Save & Continue button
            gbc.gridx = 1;
            gbc.gridy = 6;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.LAST_LINE_END;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            add(generateCharacterSheetButton, gbc);

            setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

            //Action Listeners for Buttons
            generateCharacterSheetButton.addActionListener   (e -> generateCharacter());
            previousButton.addActionListener   (e -> switchStatePrev());
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



 
