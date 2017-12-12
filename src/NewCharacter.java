/*
 * File: NewCharacter.java
 * Date: December 11, 2017
 * Purpose: Creates a Gui with a Card Layout to Step by Step allow the user
 *          to create a new D&D 5th Edition character.
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
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;  
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.*;

import java.awt.event.ComponentListener;
import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;
import java.awt.image.AffineTransformOp;
import java.util.Random;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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

    //Character being created
    private Character myCharacter;
    private int hitdice = 0;   //Character's hit dice
    
    private  MasterLists ml;
    private ArrayList<String> selectedFeatList = null;
    
    //Constructor
    public NewCharacter(){
        
        //Set up the Window
        super("Create a Character");
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

        //Create a new character to populate
        ml = new MasterLists();
        myCharacter = new Character();

        //Dont allow user to close it
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        //Display
        pack();
        setLocationByPlatform(true);
        setVisible(false);
    }
    
    //newCharacterGui - method to create and display the new character creator gui
    public void newCharacterGui(){
        this.setVisible(true);
    }
    
    //Main Test Function
    //Will be taken out when linked with the rest of the program
    public static void main(String[] args){
        //JFrame frame1 = new JFrame("DialogEx");
        //frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame1.setSize (700, 685);
        //frame1.setMinimumSize(new Dimension(700, 685));
        //frame1.setLocationRelativeTo(null);
        //frame1.setVisible(true);
      
        NewCharacter nc = new NewCharacter();
        nc.newCharacterGui();
        
    }



    //Getter method for character object
    public Character getCharacter(){
        return myCharacter;
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
        
        //For Images
        private ImageIcon iicon = new ImageIcon();

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
            JPanel right = new JPanel(new BorderLayout(0,5));
            right.setOpaque(false);
            
            //Race Description Label
            titleFont = new Font("Default", Font.BOLD, 26);
            raceLabel.setFont(titleFont);
            right.add(raceLabel,BorderLayout.NORTH);
            
            //Race Image
            JLabel racePicture = new JLabel("",JLabel.CENTER);
            updateRaceImg("Dwarf.jpg",iicon);
            racePicture.setIcon(iicon);
            racePicture.setSize(180, 225);
            right.add(racePicture,BorderLayout.CENTER);

            //Set up JTextArea for Description
            raceDescriptionTextArea.setEditable(false);
            raceDescriptionTextArea.setLineWrap(true);
            raceDescriptionTextArea.setWrapStyleWord(true);
            raceDescriptionTextArea.setFont (new java.awt.Font ("Monospaced", 0, 12));
            jsp = new JScrollPane (raceDescriptionTextArea);
            jsp.setPreferredSize(new Dimension(300, 150));
            jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            raceDescriptionTextArea.setText(raceDescrStr[0]);
            right.add(jsp,BorderLayout.SOUTH);

            
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
                            updateRaceImg("Dwarf.jpg",iicon);
                            raceDescriptionTextArea.setText(raceDescrStr[0]);
                            subRaceComboBox.addItem("Hill Dwarf");
                            subRaceComboBox.addItem("Mountain Dwarf");
                            break;
                        case "Elf":
                            updateRaceImg("Elf.jpg",iicon);
                            raceDescriptionTextArea.setText(raceDescrStr[1]);
                            subRaceComboBox.addItem("High Elf");
                            subRaceComboBox.addItem("Wood Elf");
                            subRaceComboBox.addItem("Dark Elf");
                            break;
                        case "Human":
                            updateRaceImg("Human.jpg",iicon);
                            raceDescriptionTextArea.setText(raceDescrStr[2]);
                            break;
                        case "Halfling":
                            updateRaceImg("Halfling.jpg",iicon);
                            raceDescriptionTextArea.setText(raceDescrStr[3]);
                            subRaceComboBox.addItem("Lightfoot");
                            subRaceComboBox.addItem("Stout");
                            break;
                        case "Dragonborn":
                            updateRaceImg("Dragonborn.jpg",iicon);
                            raceDescriptionTextArea.setText(raceDescrStr[4]);
                            break;
                        case "Gnome":
                            updateRaceImg("Gnome.jpg",iicon);
                            raceDescriptionTextArea.setText(raceDescrStr[5]);
                            subRaceComboBox.addItem("Forest Gnome");
                            subRaceComboBox.addItem("Rock Gnome");
                            break;
                        case "Half-Elf":
                            updateRaceImg("Half-elf.jpg",iicon);
                            raceDescriptionTextArea.setText(raceDescrStr[6]);
                            break;
                        case "Half-Orc":
                            updateRaceImg("Half-orc.jpg",iicon);
                            raceDescriptionTextArea.setText(raceDescrStr[7]);
                            break;
                        case "Tiefling":
                            updateRaceImg("Tiefling.png",iicon);
                            raceDescriptionTextArea.setText(raceDescrStr[8]);						
                            break;
                        default:
                            raceDescriptionTextArea.setText("Error, Invalid");	
                            break;
                    }
                    raceDescriptionTextArea.setCaretPosition(0);

                }
            });
        }


        //Method to handle what to do when the Save & Continue
        //Button is clicked
        private void switchStateNext(){
            
            //Obtain and Validate, and store character data

            //Nothing to validate, combo boxes force valid data

            //Set race/subrace.  Race is the subrace if it exists. Otherwise its the race.
            //That's how we are handling it in Character.java
            String race = raceLabel.getText();
            String subrace = (String)subRaceComboBox.getSelectedItem();
            if(subrace.length() > 0)
                myCharacter.setRace(subrace);
            else
                myCharacter.setRace(race);
            
            //Move to next card
            CardLayout c1 = (CardLayout)(cardPanel.getLayout());
            c1.next(cardPanel);
        }

        //Updates the image associated with a race in the Gui
        private void updateRaceImg(String imageName, ImageIcon imgIcon){
            try {
                //Read in original image
                BufferedImage origImage;
                origImage = ImageIO.read(new File("races/" + imageName));
                double w = origImage.getWidth();
                double h = origImage.getHeight();
                double scalew = 180.0/w;
                double scaleh = 225.0/h;
                
                //Create a new buffered image with the correct width/height
                //Set the scaling transformation
                BufferedImage imgafter = new BufferedImage(180, 225, BufferedImage.TYPE_INT_ARGB);
                AffineTransform scaleInstance = AffineTransform.getScaleInstance(scalew, scaleh);
                AffineTransformOp scaleOp = new AffineTransformOp(scaleInstance, AffineTransformOp.TYPE_BILINEAR);

                //redraw the image
                Graphics2D g2 = (Graphics2D) imgafter.getGraphics();
                g2.drawImage(origImage, scaleOp, 0, 0);
                g2.dispose();
                        
                //Set the new icon
                imgIcon.setImage(imgafter);
                revalidate();
                repaint();
            } catch (IOException e) {
                e.printStackTrace();
            }   
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
                                           "Rogue","Sorcerer","Warlock","Wizard"};
        private JComboBox<String> classComboBox = new JComboBox<String> (classesStr);
        
        //Level Spinner (1 to 100, default 1, incr by 1)
        //SpinnerNumberModel snm = new SpinnerNumberModel(new Integer(1),new Integer(1),new Integer(100),new Integer(1));
        
		//Week 7 -- Group decision to fix Level at 1 to simplify project because of features
		SpinnerNumberModel snm = new SpinnerNumberModel(new Integer(1),new Integer(1),new Integer(1),new Integer(1));
		JSpinner spnLevl = new JSpinner(snm);
    
        //Buttons
        private JButton addFeatures = new JButton("Add Features");
        private JButton saveAndContinueButton = new JButton ("Save and Continue >");    
        private JButton previousButton = new JButton ("< Prev");    

        private Font titleFont;
        private JDialog featModalDialog;
        private JList<String> featJlist;
        
        //For Images
        private ImageIcon iicon = new ImageIcon();
        
        //Scroll pane for jtextarea
        private JScrollPane jsp;

        //Descriptions of the classes
        private int classIndex = 0;
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
            
            "Warlocks channel arcane might wrested from primeval entities. They commune "+
            "with infernal intelligences and fey spirits, scour enemies with potent blasts "+
            "of eldritch power, and bedevil foes with hexing curses. Armed with esoteric "+
            "secrets and dangerous lore, warlocks are clever and resourceful foes.",
            
            "Wizards are scions of arcane magic. Wizards tap the true power that permeates the cosmos, research esoteric "+
            "rituals that can alter time and space, and hurl balls of fire that incinerate massed foes. Wizards wield "+
            "spells the way warriors brandish swords."
        };

        //////////////////////
        // End of Variables //
        //////////////////////

        private String selectedClass = "Barbarian";
        //Constructor
        public JPanelClass(){

            JPanel helper = new JPanel();
            helper.setLayout(new FlowLayout(FlowLayout.LEFT));
            helper.setOpaque(false);
            setLayout(new GridBagLayout());
            setOpaque(false);
            GridBagConstraints gbc = new GridBagConstraints();
            
            
            //Init preselected class variable
            hitdice = 12;

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
            JPanel right = new JPanel(new BorderLayout(0,5));
            right.setOpaque(false);
            
            //Class Description Label
            titleFont = new Font("Default", Font.BOLD, 26);
            classLabel.setFont(titleFont);
            right.add(classLabel,BorderLayout.NORTH);
            
            //Class Image
            JLabel classPicture = new JLabel("",JLabel.CENTER);
            updateClassImg("Barbarian.png",iicon);
            classPicture.setIcon(iicon);
            classPicture.setSize(180, 225);
            right.add(classPicture,BorderLayout.CENTER);

            //Set up JTextArea for Description
            classDescriptionTextArea.setEditable(false);
            classDescriptionTextArea.setLineWrap(true);
            classDescriptionTextArea.setWrapStyleWord(true);
            classDescriptionTextArea.setFont (new java.awt.Font ("Monospaced", 0, 12));
            jsp = new JScrollPane (classDescriptionTextArea);
            jsp.setPreferredSize(new Dimension(300, 150));
            jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            classDescriptionTextArea.setText(classDescrStr[0]);
            right.add(jsp,BorderLayout.SOUTH);

            
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
            (((JSpinner.DefaultEditor) spnLevl.getEditor()).getTextField()).setColumns(2);
            (((JSpinner.DefaultEditor) spnLevl.getEditor()).getTextField()).setEditable(false);
            helper.add(spnLevl);
            left.add(helper);
            
            left.add(addFeatures);
            
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
            addFeatures.addActionListener (e -> featureDialog());
            saveAndContinueButton.addActionListener   (e -> switchStateNext());
            previousButton.addActionListener   (e -> switchStatePrev());
            
            //Action Listener for Class Selection
            //Will update Gui Elements when a new class is selected
            classComboBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    JComboBox comboBox = (JComboBox) event.getSource();
                    
                    Object selected = comboBox.getSelectedItem();
                    String cStr = selected.toString();
                    
                    //Change of class, erase all features
                    selectedFeatList = null;
                                        
                    classLabel.setText(cStr);
                    switch(cStr){
                        case "Barbarian":
                            selectedClass = "Barbarian";
                            classIndex = 0;
                            hitdice = 12;
                            updateClassImg("Barbarian.png",iicon);
                            classDescriptionTextArea.setText(classDescrStr[0]);
                            break;
                        case "Bard":
                            selectedClass = "Bard";
                            classIndex = 1;
                            hitdice = 8;
                            updateClassImg("Bard.jpg",iicon);
                            classDescriptionTextArea.setText(classDescrStr[1]);
                            break;
                        case "Cleric":
                            selectedClass = "Cleric";
                            classIndex = 2;
                            hitdice = 8;
                            updateClassImg("Cleric.jpg",iicon);
                            classDescriptionTextArea.setText(classDescrStr[2]);
                            break;
                        case "Druid":
                            selectedClass = "Druid";
                            classIndex = 3;
                            hitdice = 8;
                            updateClassImg("Druid.jpg",iicon);
                            classDescriptionTextArea.setText(classDescrStr[3]);
                            break;
                        case "Fighter":
                            selectedClass = "Fighter";
                            classIndex = 4;
                            hitdice = 10;
                            updateClassImg("Fighter.jpg",iicon);
                            classDescriptionTextArea.setText(classDescrStr[4]);
                            break;
                        case "Monk":
                            selectedClass = "Monk";
                            classIndex = 5;
                            hitdice = 8;
                            updateClassImg("Monk.jpg",iicon);
                            classDescriptionTextArea.setText(classDescrStr[5]);
                            break;
                        case "Paladin":
                            selectedClass = "Paladin";
                            classIndex = 6;
                            hitdice = 10;
                            updateClassImg("Paladin.jpg",iicon);
                            classDescriptionTextArea.setText(classDescrStr[6]);
                            break;
                        case "Ranger":
                            selectedClass = "Ranger";
                            classIndex = 7;
                            hitdice = 10;
                            updateClassImg("Ranger.jpg",iicon);
                            classDescriptionTextArea.setText(classDescrStr[7]);
                            break;
                        case "Rogue":
                            selectedClass = "Rogue";
                            classIndex = 8;
                            hitdice = 8;
                            updateClassImg("Rogue.jpg",iicon);
                            classDescriptionTextArea.setText(classDescrStr[8]);						
                            break;
                        case "Sorcerer":
                            selectedClass = "Sorcerer";
                            classIndex = 9;
                            hitdice = 6;
                            updateClassImg("Sorcerer.png",iicon);
                            classDescriptionTextArea.setText(classDescrStr[9]);						
                            break;
                        case "Warlock":
                            selectedClass = "Warlock";
                            classIndex = 10;
                            hitdice = 8;
                            updateClassImg("Warlock.png",iicon);
                            classDescriptionTextArea.setText(classDescrStr[10]);						
                            break;
                        case "Wizard":
                            selectedClass = "Wizard";
                            classIndex = 11;
                            hitdice = 6;
                            updateClassImg("Wizard.jpg",iicon);
                            classDescriptionTextArea.setText(classDescrStr[11]);						
                            break;

                        default:
                            classDescriptionTextArea.setText("Error, Invalid");	
                            break;
                    }
                    classDescriptionTextArea.setCaretPosition(0);
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
            
            //Initial Experience Point Values for levels 1 to 20
            int exprPtArray[] = {0,300,900,2700,6500,14000,23000,34000,48000,64000,
                                 85000,100000,120000,140000,165000,195000,225000,
                                 265000,305000,355000};
            int expPts = 0;
            
            if(((int)spnLevl.getValue()) > 20)
                expPts = exprPtArray[19];
            else
                expPts = exprPtArray[((int)spnLevl.getValue())-1];
            
            //Obtain and Validate, and store character data

            //Nothing to validate, combo boxes force valid data

            //Set class/starting level (level 1 only)
            myCharacter.setClassLevel(classIndex,1);
            myCharacter.setExperiencePoints(expPts);
            
            //Go to next card
            CardLayout c1 = (CardLayout)(cardPanel.getLayout());
            c1.next(cardPanel);
        }
        
        //Updates the image associated with a class in the Gui
        private void updateClassImg(String imageName, ImageIcon imgIcon){
            try {
                //Read in original image
                BufferedImage origImage;
                origImage = ImageIO.read(new File("classes/" + imageName));
                double w = origImage.getWidth();
                double h = origImage.getHeight();
                double scalew = 180.0/w;
                double scaleh = 225.0/h;
                
                //Create a new buffered image with the correct width/height
                //Set the scaling transformation
                BufferedImage imgafter = new BufferedImage(180, 225, BufferedImage.TYPE_INT_ARGB);
                AffineTransform scaleInstance = AffineTransform.getScaleInstance(scalew, scaleh);
                AffineTransformOp scaleOp = new AffineTransformOp(scaleInstance, AffineTransformOp.TYPE_BILINEAR);

                //redraw the image
                Graphics2D g2 = (Graphics2D) imgafter.getGraphics();
                g2.drawImage(origImage, scaleOp, 0, 0);
                g2.dispose();
                        
                //Set the new icon
                imgIcon.setImage(imgafter);
                revalidate();
                repaint();
            } catch (IOException e) {
                e.printStackTrace();
            }   
        }
        
        
        private void featureDialog(){
            selectedFeatList = null;
            JButton saveFeat = new JButton("Save Selected Features");
            JPanel featPanel = new JPanel(new BorderLayout());
            JLabel info = new JLabel("Select all character features (Use Ctrl/Shift)",JLabel.CENTER);
            featModalDialog = new JDialog(null, "Feature Selection", ModalityType.APPLICATION_MODAL);
            featModalDialog.add(Box.createRigidArea(new Dimension(400, 400)));
            featModalDialog.setSize (400, 400);
            featModalDialog.setMinimumSize(new Dimension(400, 400));
            
            final DefaultListModel<String> modelList;    
            modelList = new DefaultListModel<>();  
            
            //Add Features to the list Here (Based on class)...
            ClassType selection = ml.getClassTypeByID(selectedClass);
            int numFeatures = selection.features[0].length;
            for(int n = 0; n < numFeatures; n++){
                String feat = selection.features[0][n];
                modelList.addElement(feat); 
            }
            
            
            featJlist = new JList<>(modelList);  
            featJlist.setBounds(300,300, 75,75);  
            JScrollPane listScroller = new JScrollPane(featJlist);
            listScroller.setPreferredSize(new Dimension(300, 300));
            saveFeat.addActionListener(e -> closeFeatureDialog());
            
            featPanel.add(info,BorderLayout.NORTH);
            featPanel.add(listScroller,BorderLayout.CENTER);
            featPanel.add(saveFeat,BorderLayout.SOUTH);
            
            //List of features to select using shift/ctrl and clicking mouse
            featModalDialog.add(featPanel);

            featModalDialog.pack();
            featModalDialog.setLocationByPlatform(true);
            featModalDialog.setLocationRelativeTo(this);            
            featModalDialog.setVisible(true);

            return;
        }

        
        private void closeFeatureDialog(){
            selectedFeatList = (ArrayList<String>) featJlist.getSelectedValuesList();

            featModalDialog.setVisible(false);
            featModalDialog.dispose();
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
        private JLabel totalPointsField = new JLabel("Total Points Remaining: ", JLabel.LEFT);
        private int remainingPts = 27;
        private JLabel ptsRemainField = new JLabel(Integer.toString(remainingPts), JLabel.LEFT);
        
        private JLabel hitPointsText = new JLabel("Hit Points: ", JLabel.LEFT);
        private int hitPts = 0;
        private JLabel hitPointsField = new JLabel(Integer.toString(remainingPts), JLabel.LEFT);
        
        private JTextField rollStr = new JTextField("0",2);
        private JTextField rollDex = new JTextField("0",2);
        private JTextField rollCon = new JTextField("0",2);
        private JTextField rollInt = new JTextField("0",2);
        private JTextField rollWis = new JTextField("0",2);
        private JTextField rollCha = new JTextField("0",2);
        
        //Spinners (8 to 15, default 8, incr by 1)
        private SpinnerNumberModel snm1 = new SpinnerNumberModel(new Integer(8),new Integer(8),new Integer(15),new Integer(1));
        private SpinnerNumberModel snm2 = new SpinnerNumberModel(new Integer(8),new Integer(8),new Integer(15),new Integer(1));
        private SpinnerNumberModel snm3 = new SpinnerNumberModel(new Integer(8),new Integer(8),new Integer(15),new Integer(1));
        private SpinnerNumberModel snm4 = new SpinnerNumberModel(new Integer(8),new Integer(8),new Integer(15),new Integer(1));
        private SpinnerNumberModel snm5 = new SpinnerNumberModel(new Integer(8),new Integer(8),new Integer(15),new Integer(1));
        private SpinnerNumberModel snm6 = new SpinnerNumberModel(new Integer(8),new Integer(8),new Integer(15),new Integer(1));		
        private JSpinner strengthJSpinner = new JSpinner(snm1);
        private JSpinner dexterityJSpinner = new JSpinner(snm2);
        private JSpinner constitutionJSpinner = new JSpinner(snm3);
        private JSpinner wisdomJSpinner = new JSpinner(snm4);
        private JSpinner intelligenceJSpinner = new JSpinner(snm5);
        private JSpinner charismaJSpinner = new JSpinner(snm6);
        
        //Radio Buttons
        private JRadioButton rPt;    //Point Buy Method
        private JRadioButton rRoll;  //Roll Method
        
        //Buttons
        private JButton saveAndContinueButton = new JButton ("Save and Continue >");    
        private JButton previousButton = new JButton ("< Prev");    
        private int rolled = 0;

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
            JPanel f7 = new JPanel(new FlowLayout());
            f7.setOpaque(false);
            f7.add(rollStr);
            abilityPanel.add(f7);
            (((JSpinner.DefaultEditor) strengthJSpinner.getEditor()).getTextField()).setColumns(2);
            (((JSpinner.DefaultEditor) strengthJSpinner.getEditor()).getTextField()).setEditable(false);

            //Dexterity
            dexterityLabel.setFont(titleFont);
            abilityPanel.add(dexterityLabel);
            f2.add(dexterityJSpinner);
            abilityPanel.add(f2);
            JPanel f8 = new JPanel(new FlowLayout());
            f8.setOpaque(false);
            f8.add(rollDex);
            abilityPanel.add(f8);
            (((JSpinner.DefaultEditor) dexterityJSpinner.getEditor()).getTextField()).setColumns(2);
            (((JSpinner.DefaultEditor) dexterityJSpinner.getEditor()).getTextField()).setEditable(false);
            
            //Constitution
            constitutionLabel.setFont(titleFont);
            abilityPanel.add(constitutionLabel);
            f3.add(constitutionJSpinner);
            abilityPanel.add(f3);
            JPanel f9 = new JPanel(new FlowLayout());
            f9.setOpaque(false);
            f9.add(rollCon);
            abilityPanel.add(f9);
            (((JSpinner.DefaultEditor) constitutionJSpinner.getEditor()).getTextField()).setColumns(2);
            (((JSpinner.DefaultEditor) constitutionJSpinner.getEditor()).getTextField()).setEditable(false);
            
            //Intelligence
            intelligenceLabel.setFont(titleFont);
            abilityPanel.add(intelligenceLabel);
            f4.add(intelligenceJSpinner);
            abilityPanel.add(f4);
            JPanel f10 = new JPanel(new FlowLayout());
            f10.setOpaque(false);
            f10.add(rollInt);
            abilityPanel.add(f10);
            (((JSpinner.DefaultEditor) intelligenceJSpinner.getEditor()).getTextField()).setColumns(2);
            (((JSpinner.DefaultEditor) intelligenceJSpinner.getEditor()).getTextField()).setEditable(false);
            
            //Wisdom
            wisdomLabel.setFont(titleFont);
            abilityPanel.add(wisdomLabel);
            f5.add(wisdomJSpinner);
            abilityPanel.add(f5);
            JPanel f11 = new JPanel(new FlowLayout());
            f11.setOpaque(false);
            f11.add(rollWis);
            abilityPanel.add(f11);
            (((JSpinner.DefaultEditor) wisdomJSpinner.getEditor()).getTextField()).setColumns(2);
            (((JSpinner.DefaultEditor) wisdomJSpinner.getEditor()).getTextField()).setEditable(false);
            
            //Charisma
            charismaLabel.setFont(titleFont);
            abilityPanel.add(charismaLabel);
            f6.add(charismaJSpinner);
            abilityPanel.add(f6);
            JPanel f12 = new JPanel(new FlowLayout());
            f12.setOpaque(false);
            f12.add(rollCha);
            abilityPanel.add(f12);
            (((JSpinner.DefaultEditor) charismaJSpinner.getEditor()).getTextField()).setColumns(2);
            (((JSpinner.DefaultEditor) charismaJSpinner.getEditor()).getTextField()).setEditable(false);
            
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
            rPt   = new JRadioButton("Use Point Buy",true);
            rRoll = new JRadioButton("Use Roll");
            ButtonGroup rgroup = new ButtonGroup();
            rPt.setOpaque(false);
            rPt.setFont(titleFont);
            rRoll.setOpaque(false);
            rRoll.setFont(titleFont);
            rgroup.add(rPt);
            rgroup.add(rRoll);
            
            rPt.addActionListener(e -> calculateHitPoints());
            rRoll.addActionListener(e -> calculateHitPoints());
            
            //Total Points Assigned
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.insets = new Insets(0,0,5,0);  //bottom padding
            gbc.anchor = GridBagConstraints.LAST_LINE_START;
            gbc.gridwidth = GridBagConstraints.RELATIVE;
            JPanel ptsPanel = new JPanel(new FlowLayout());
            ptsPanel.setOpaque(false);
            totalPointsField.setFont(titleFont);
            ptsRemainField.setFont(titleFont);
            ptsPanel.add(totalPointsField);
            ptsPanel.add(ptsRemainField);
            //Hit Points
            hitPointsText.setFont(titleFont);
            hitPointsField.setFont(titleFont);
            hitPointsText.setBorder(new EmptyBorder(0, 50, 0, 0));

            calculateHitPoints();
            ptsPanel.add(hitPointsText);
            ptsPanel.add(hitPointsField);
            add(ptsPanel, gbc);
            
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
            
            //JSpinner Listeners for Ability Scores
            strengthJSpinner.addChangeListener(listener);
            dexterityJSpinner.addChangeListener(listener);
            constitutionJSpinner.addChangeListener(listener);
            wisdomJSpinner.addChangeListener(listener);
            intelligenceJSpinner.addChangeListener(listener);
            charismaJSpinner.addChangeListener(listener);

            //Action Listeners
            bRoll.addActionListener   (e -> rollDiceForABScores());
            saveAndContinueButton.addActionListener   (e -> switchStateNext());
            previousButton.addActionListener   (e -> switchStatePrev());
            
        }
        
        //Listens for an Ability Score Spinner to be clicked
        //Updates remaining points on every click
        //Remaining points are allowed to go negative, but must be zero
        //when continuing to the next screen
        ChangeListener listener = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int pts1 = getABCost((int)strengthJSpinner.getValue());
                int pts2 = getABCost((int)dexterityJSpinner.getValue());
                int pts3 = getABCost((int)constitutionJSpinner.getValue());
                int pts4 = getABCost((int)wisdomJSpinner.getValue());
                int pts5 = getABCost((int)intelligenceJSpinner.getValue());
                int pts6 = getABCost((int)charismaJSpinner.getValue());

                remainingPts = 27-(pts1+pts2+pts3+pts4+pts5+pts6);
                ptsRemainField.setText(Integer.toString(remainingPts));
                
                calculateHitPoints();
            }
        };
        
        private void calculateHitPoints(){
            int constitution = getConst();
            if(constitution >= 0){
                hitPts = hitdice + abilityScoreToModifier(constitution);
                hitPointsField.setText(Integer.toString(hitPts));
            }
            else{
                hitPts = 0;
                hitPointsField.setText(" ");
            }
        }
        
        private int getConst(){
            
            //return the const.
            if(rRoll.isSelected())
                return Integer.parseInt(rollCon.getText());
            else if(rPt.isSelected())
                return (int)constitutionJSpinner.getValue();
            else
                return -1;
        }
        
        //Returns ability score cost
        //Values taken from D&D Players Handbook
        private int getABCost(int score){
            switch(score){
                case 8:
                    return 0;
                case 9:
                    return 1;
                case 10:
                    return 2;
                case 11:
                    return 3;
                case 12:
                    return 4;
                case 13:
                    return 5;
                case 14:
                    return 7;
                case 15:
                    return 9;
                default:
                    return 0;
            }
        }
        
        //Returns the modifier associated with an ability score
        private int abilityScoreToModifier(int abscore){
            return (abscore-10)/2;
        }

        
        //Dice Roll Method For Ability Scores
        private void rollDiceForABScores(){

            //Assign Scores
            rolled = 1;
            rollStr.setText(Integer.toString(rollDice4x()));
            rollDex.setText(Integer.toString(rollDice4x()));
            rollCon.setText(Integer.toString(rollDice4x()));
            rollInt.setText(Integer.toString(rollDice4x()));
            rollWis.setText(Integer.toString(rollDice4x()));
            rollCha.setText(Integer.toString(rollDice4x()));
            
            //Update hit points
            calculateHitPoints();
        }
        
        //Returns the value from rolling four
        //6-sided dice and removing the smallest roll
        private int rollDice4x(){
            int smallest = 0;
            int total = 0;
            Random rand = new Random();

            int dice1 = rand.nextInt(7);
            smallest = dice1;
            int dice2 = rand.nextInt(7);
            if(dice2 < smallest)
                smallest = dice2;
            int dice3 = rand.nextInt(7);
            if(dice3 < smallest)
                smallest = dice3;
            int dice4 = rand.nextInt(7);
            if(dice4 < smallest)
                smallest = dice4;
            total = dice1+dice2+dice3+dice4-smallest;
            return total;
        }

        //Method to go to Previous Card
        private void switchStatePrev(){
            //Go to previous card
            CardLayout c1 = (CardLayout)(cardPanel.getLayout());
            c1.previous(cardPanel);
        }

        //Method to go to Next Card
        private void switchStateNext(){
            
            //Obtain data, Validate, and store character data
            int str, dex, con, intel, wis, cha;
           
            if(rRoll.isSelected()){
                //Make sure they rolled 
                if(rolled == 0){
                    JOptionPane.showMessageDialog(null,"All abilities 0.  Please Click the Roll Button.",
                    "Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                str = Integer.parseInt(rollStr.getText());
                dex = Integer.parseInt(rollDex.getText());
                con = Integer.parseInt(rollCon.getText());
                intel = Integer.parseInt(rollInt.getText());
                wis = Integer.parseInt(rollWis.getText());
                cha = Integer.parseInt(rollCha.getText());
            }
            else if(rPt.isSelected()){
                
                //Remaining Points to spend need to be 0
                if(remainingPts != 0){
                    JOptionPane.showMessageDialog(null, 
                    "Remaining Purchase Points should be 0.","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                str = (int)strengthJSpinner.getValue();
                dex = (int)dexterityJSpinner.getValue();
                con = (int)constitutionJSpinner.getValue();
                intel = (int)wisdomJSpinner.getValue();
                wis = (int)intelligenceJSpinner.getValue();
                cha = (int)charismaJSpinner.getValue();
            }
            else{
                //Need to select Point Buy or Roll Ability Score Method
                JOptionPane.showMessageDialog(null, 
                    "Please Click the Point Buy or Roll Option.","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            myCharacter.setAbilityScoreStrength(str);
            myCharacter.setAbilityScoreDexterity(dex);
            myCharacter.setAbilityScoreConstitution(con);
            myCharacter.setAbilityScoreIntelligence(intel);
            myCharacter.setAbilityScoreWisdom(wis);
            myCharacter.setAbilityScoreCharisma(cha);
            
            //Hit Points
            if(hitPts > 0){
                myCharacter.setHitPointsCurrent(hitPts);
                myCharacter.setHitPointsMaximum(hitPts);
            }
            else{
                JOptionPane.showMessageDialog(null, 
                    "Hit Points Incorrect.","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            
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
        private JLabel weightLabel = new JLabel("Weight (lbs):", JLabel.LEFT);
        private JLabel heightLabel = new JLabel("Height (Ft):", JLabel.LEFT);
        private JLabel sexLabel = new JLabel("Sex:", JLabel.LEFT);
        private JLabel alignmentLabel = new JLabel("Alignment:", JLabel.LEFT);
        private JLabel backgroundLabel = new JLabel("Background:", JLabel.LEFT);
        private JLabel factionLabel = new JLabel("Faction:", JLabel.LEFT);

        //Text Fields		
        private JTextField nameTextField   = new JTextField("", 10);
        private JTextField weightTextField = new JTextField("", 10);
        private JTextField heightTextField = new JTextField("", 10);
        private JTextField factionTextField = new JTextField("", 10);

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
                                                "Caravan Specialist","Guild Merchant",
                                                "Outlander","Knight","Sailor","Pirate"};
        private JComboBox<String> backgroundComboBox = new JComboBox<String> (backgroundStr);
    

        //Buttons
        private JButton generateCharacterSheetButton = new JButton ("Generate Character Sheet");    
        private JButton previousButton = new JButton ("< Prev");    

        private Font titleFont;
        
        private String[] charImages = {"Augrek_Brighthelm.png", "Beldora.png", 
            "Darathra_Shendrel.png", "Darz_Helgar.png",
            "Duvessa_Shane.png", "Ghelryn_Foehammer.png", "Lifferlas.png", "Markham_Southwell.png",
            "Miros_Xelbrin.png", "Narth_Tezrin.png", "Naxene_Drathkala.png", "Oren_Yogilvy.png",
            "Othovir.png", "Shalvus_Martholio.png", "Sir_Baric_Nylef.png", "Sirac_of_Suzail.png",
            "Urgala_Meltimer.png", "Zi_Liang.png", "cabbage.jpg"};
        private int charImgIndex = 0;
        private ImageIcon iicon;

                
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
            JLabel photoLabel = new JLabel();
            
            JLabel imgselect = new JLabel("Character Image",JLabel.CENTER);
            titleFont = new Font("Default", Font.BOLD, 24);
            imgselect.setFont(titleFont);            
            right.add(imgselect,BorderLayout.NORTH);
            titleFont = new Font("Default", Font.PLAIN, 18);   
            iicon = new ImageIcon(); 
            updateCharacterImg(charImgIndex);
            photoLabel.setIcon(iicon);
            photoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
            photoLabel.setSize(50, 50);
            //Show Image
            right.add(photoLabel,BorderLayout.CENTER);
                    
            //Create and initialize the character selection buttons.
            JPanel iFlow = new JPanel(new FlowLayout(FlowLayout.CENTER));
            iFlow.setOpaque(false);
            JButton imgPrevButton = new JButton();
            try{
                Image prevImg = ImageIO.read(getClass().getResource("Back24.gif"));
                imgPrevButton.setIcon(new ImageIcon(prevImg));
            }
            catch(Exception ee){
                System.out.println(ee);
            }
            imgPrevButton.addActionListener (e -> getPrevCharacterImg());
            iFlow.add(imgPrevButton);
 
            JButton imgNextButton = new JButton();
            try{
                Image nextImg = ImageIO.read(getClass().getResource("Forward24.gif"));
                imgNextButton.setIcon(new ImageIcon(nextImg));
            }
            catch(Exception ee){
                System.out.println(ee);
            }
            imgNextButton.addActionListener (e -> getNextCharacterImg());
            iFlow.add(imgNextButton);
            right.add(iFlow,BorderLayout.SOUTH);
    
            
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
            
            factionLabel.setFont(titleFont);
            factionTextField.setFont(titleFont);
            left.add(factionLabel);
            left.add(factionTextField);
            
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
        
        //Cycles through the character photo selection
        private void getPrevCharacterImg(){
            
            //Adjust image name index
            charImgIndex--;
            if(charImgIndex < 0)
                charImgIndex = charImages.length-1;
                        
            updateCharacterImg(charImgIndex);		
        }
    
        private void getNextCharacterImg(){

            //Adjust image name index			
            charImgIndex++;
            if(charImgIndex >= charImages.length)
                charImgIndex = 0;
            
            updateCharacterImg(charImgIndex);	
        }
        
        private void updateCharacterImg(int charImgIndex){
            try {
                //Read in original image
                BufferedImage characterImage;
                characterImage = ImageIO.read(new File("Portraits/" + charImages[charImgIndex]));
                double w = characterImage.getWidth();
                double h = characterImage.getHeight();
                double scalew = 200.0/w;
                double scaleh = 200.0/h;
                
                //Create a new buffered image with the correct width/height
                //Set the scaling transformation
                BufferedImage imgafter = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
                AffineTransform scaleInstance = AffineTransform.getScaleInstance(scalew, scaleh);
                AffineTransformOp scaleOp = new AffineTransformOp(scaleInstance, AffineTransformOp.TYPE_BILINEAR);

                //redraw the image
                Graphics2D g2 = (Graphics2D) imgafter.getGraphics();
                g2.drawImage(characterImage, scaleOp, 0, 0);
                g2.dispose();
                        
                //Set the new icon
                iicon.setImage(imgafter);
                revalidate();
                repaint();
            } catch (IOException e) {
                e.printStackTrace();
            }   
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
            
            //Make sure something is in name,weight,height text boxes
            if(nameTextField.getText().length() == 0){
                JOptionPane.showMessageDialog(null,"Name is empty.",
                    "Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(heightTextField.getText().length() == 0){
                JOptionPane.showMessageDialog(null,"Height is empty.",
                    "Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(weightTextField.getText().length() == 0){
                JOptionPane.showMessageDialog(null,"Weight is empty.",
                    "Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(factionTextField.getText().length() == 0){
                JOptionPane.showMessageDialog(null,"Faction is empty.",
                    "Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            //Make sure name is <= 12 characters
            if(nameTextField.getText().length() > 12){
                JOptionPane.showMessageDialog(null,"Name is limited to 12 characters.",
                    "Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            //Make sure faction is <= 12 characters
            if(factionTextField.getText().length() > 12){
                JOptionPane.showMessageDialog(null,"Faction is limited to 12 characters.",
                    "Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            

            //Make sure weight/height contain only numbers
            int weight, height;
            try{
               weight = Integer.parseInt(weightTextField.getText());
            }
            catch(NumberFormatException ee){
                JOptionPane.showMessageDialog(null,"Invalid Weight Input, Should be a number!");
                return;
            }
            try{
               height = Integer.parseInt(heightTextField.getText());
            }
            catch(NumberFormatException ee){
                JOptionPane.showMessageDialog(null,"Invalid Height Input, Should be a number!");
                return;
            }
            
            //Set character values
            myCharacter.setName(nameTextField.getText());
            myCharacter.setFaction(factionTextField.getText());
            myCharacter.setWeight(weight);
            myCharacter.setHeight(height);
            myCharacter.setSex((String)sexComboBox.getSelectedItem());
            myCharacter.setAlignment((String)alignmentComboBox.getSelectedItem());
            myCharacter.setBackground((String)backgroundComboBox.getSelectedItem());
            myCharacter.setCharacterImg("Portraits/" + charImages[charImgIndex]);
            
            //Add Features that were selected from selectedFeatList, if any were
            //Do it here so that you dont need to remove if they hit the back button
            if(selectedFeatList != null){
                int numFeats = selectedFeatList.size();
                for(int n = 0; n < numFeats; n++){
                    myCharacter.addClassFeature(selectedFeatList.get(n));
                }
            }
            
            //Alex's new initialize method goes here
            //Covers language, items, etc.
            //myCharacter.initializeCharacter();
            
            //Go to Main Gui
            setVisible(false);
            dispose();
        }

    }
    

}



 
