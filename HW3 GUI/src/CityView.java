/**
 * This class provides the GUI to create a graphical
 * view of the city.
 * @author Griffen Marler
 * @version 1.00, 13 January 2019
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;


public class CityView extends JFrame {
    private JFrame Frame;
    private int teachercount;
    private JPanel setPanel;
    private JButton viewCityBreakdownButton;
    private JPanel CreateTeacher;
    private JPanel CreatePolice;
    private JPanel CreateKid;
    private JTextField enterNameOfKidTextField;
    private JTextField enterAgeOfKidTextField;
    private JTextField enterNameOfTeacherTextField;
    private JTextField enterAgeOfTeacherTextField;
    private JTextField enterGradeLevelTextField;
    private JTextField enterNameOfPoliceTextField;
    private JTextField enterAgeOfPoliceTextField;
    private JButton createPoliceButton;
    private JButton createTeacherButton;
    private JButton createKidButton;
    private JFormattedTextField formattedTextField1;
    private JComboBox PoliceRoleBox;
    private JComboBox TeacherSubjectBox;
    private JComboBox KidCandyBox;
    private JComboBox KidLocationBox;
    private JComboBox TeacherLocationBox;
    private JComboBox PoliceLocationBox;
    private JComboBox BuildingStatBox;
    private JPanel VisualPanel;
    private JLabel CityHallPic;
    private JLabel SchoolPic;
    private JButton button1;
    private JLabel TeacherLabel;
    private JLayeredPane PeoplePane;
    private ArrayList<Person> CityPeople1 = new ArrayList<Person>();
    private ArrayList<Person> School1 = new ArrayList<Person>();
    private ArrayList<Person> CityHall1 = new ArrayList<Person>();
    private ArrayList<JLabel> TeacherStorage = new ArrayList<>();
    private int TeacherStorageSize = -1;    // Initializes size of TeacherStorage JLabel Array
    private HashMap<JLabel, Person> PeopleMap = new HashMap<>();
    private CityHall A;
    private School B;
    private int TeacherSpacer = 5;
    private int KidSpacer = 15;
    private int PoliceSpacer = 20;
    private int LayerManager = 2;
    GridBagConstraints layoutConst = null;
    Point diffdrag;

    public CityView() {


        A = new CityHall("Whitworth City Hall", "500 N Main");
        B = new School("Whitworth Elementary", "300 W Hawthorne");

        enterNameOfKidTextField.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        enterNameOfKidTextField.addKeyListener(new KeyAdapter() {
            /**
             * Invoked when a key has been typed.
             * This event occurs when a key press is followed by a key release.
             *
             * @param e
             */
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });
        createTeacherButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    // Create new teacher using the user input from the GUI form
                    Teacher t = new Teacher(enterNameOfTeacherTextField.getText(), Integer.valueOf(enterAgeOfTeacherTextField.getText()),
                            Integer.valueOf(enterGradeLevelTextField.getText()), TeacherSubjectBox.getSelectedItem().toString());

                    // Get the image to use for the teacher
                    Toolkit toolkit1 = Toolkit.getDefaultToolkit();
                    URL imgUrl = new URL("https://secure.webtoolhub.com/static/resources/icons/set178/c33afd2f.png");
                    Image img = toolkit1.getImage(imgUrl);
                    img = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                    ImageIcon TeacherIcon = new ImageIcon(img);

                    // Store the Teacher in a Map, with the key as a New Jlabel.
                    // The reason I do this is so I can associate the JLabel that is output on the screen
                    // with the information from the object.
                    PeopleMap.put(new JLabel(TeacherIcon), t);

                    // Add the JLabel to the People pane. I am able to access the JLabel by using the getKeyFromValue
                    // function defined lower down in my program. The JLabel is added at depth LayerManager which starts at
                    // 2 and is incremented each time a new person is added.
                    PeoplePane.add((JLabel) getKeyFromValue(PeopleMap, t), new Integer(LayerManager));


                    PeoplePane.addMouseMotionListener(new MouseMotionAdapter() {
                        /**
                         * Invoked when a mouse button is pressed on a component and then
                         * dragged.  Mouse drag events will continue to be delivered to
                         * the component where the first originated until the mouse button is
                         * released (regardless of whether the mouse position is within the
                         * bounds of the component).
                         *
                         * @param e
                         */
                        @Override
                        public void mouseDragged(MouseEvent e) {
                            System.out.println("dragging");
                            JLabel label = null;
                            for (int i = 0; i < PeopleMap.size(); i++) {
                                if (((JLabel) getKeyFromValue(PeopleMap, t)).getBounds().contains(e.getPoint())) {
                                    // Set the label to the JLabel that is used to Map the current Teacher
                                    label = (JLabel) getKeyFromValue(PeopleMap, t);
                                }
                            }
                            if (label != null) {
                                if (diffdrag == null) {
                                    diffdrag = new Point(e.getX() - label.getBounds().x, e.getY() - label.getBounds().y);
                                }
                                label.setBounds(e.getX() - diffdrag.x, e.getY() - diffdrag.y, label.getBounds().width,
                                        label.getBounds().height);

                                System.out.printf("You are moving %s, moved label to <%d, %d> ", PeopleMap.get(label).toString(), e.getX() - diffdrag.x, e.getY() - diffdrag.y);
                            }
                            mouseReleased(e, PeopleMap.get(label));
                            super.mouseDragged(e);
                        }

                        /**
                         * This mouse released function checks if the user has moved
                         * one of the labels holding the person information to another
                         * part of the map. It is able to tell this through using coordinates that
                         * were found when going through my program. If the coordinates are in the range
                         * of a new location for a person, that person will be removed from the arraylist that
                         * they came from and added to the new arraylist of the updated location.
                         * @param e Mouse information
                         * @param person person object that is passed in
                         */
                        public void mouseReleased(MouseEvent e, Person person) {
                            // If the person is in the city
                            if(CityPeople1.contains(person)) {
                                // If the mouse is let go in the bounds of the city hall
                                if (e.getX() - diffdrag.x >= 393 && e.getX() - diffdrag.x <= 618 && e.getY() - diffdrag.y >= 455 && e.getY() - diffdrag.y <= 660)
                                {
                                  System.out.println("You have moved this person to city hall");
                                    CityPeople1.remove(person);
                                    CityHall1.add(person);
                                }
                                // If the mouse is let go in the bounds of the school
                                else if(e.getX() - diffdrag.x >= 48 && e.getX() - diffdrag.x <= 235 && e.getY() -diffdrag.y >= 28 && e.getY() - diffdrag.y <= 263)
                                {
                                    System.out.println("You have moved this person to School");
                                    CityPeople1.remove(person);
                                    School1.add(person);
                                }
                            }
                            // If the person is in the school
                            if(School1.contains(person)) {
                                // If the mouse is let go in the bounds of city hall
                                if (e.getX() - diffdrag.x >= 393 && e.getX() - diffdrag.x <= 618 && e.getY() - diffdrag.y >= 455 && e.getY() - diffdrag.y <= 660) {
                                    System.out.println("You have moved this person to city hall");
                                    School1.remove(person);
                                    CityHall1.add(person);
                                }
                                // If the person stays in the school, do nothing
                                else if(e.getX() - diffdrag.x >= 48 && e.getX() - diffdrag.x <= 235 && e.getY() -diffdrag.y >= 28 && e.getY() - diffdrag.y <= 263) {

                                }
                                // Else, the person has moved to the city
                                else {
                                  System.out.println("You have moved this person to the city!");
                                    School1.remove(person);
                                    CityPeople1.add(person);
                                }
                            }
                            // If the person is in the city hall
                            if(CityHall1.contains(person)) {
                                // If the person is in the bounds of the school
                                if(e.getX() - diffdrag.x >= 48 && e.getX() - diffdrag.x <= 235 && e.getY() -diffdrag.y >= 28 && e.getY() - diffdrag.y <= 263)
                                {
                                    System.out.println("You have moved this person to school");
                                    CityPeople1.remove(person);
                                    School1.add(person);
                                }
                                // If the person is still in city hall, do nothing
                                else if (e.getX() - diffdrag.x >= 393 && e.getX() - diffdrag.x <= 618 && e.getY() - diffdrag.y >= 455 && e.getY() - diffdrag.y <= 660) {

                                }
                                // Otherwise, the person moved to the city
                                else {
                                    System.out.println("You have moved this person to the city!");
                                    CityHall1.remove(person);
                                    CityPeople1.add(person);
                                }
                            }



                        }
                    });


                    // Add the created teacher to the user selected location
                    if (TeacherLocationBox.getSelectedItem().toString() == "City") {
                        CityPeople1.add(t);
                        ((JLabel) getKeyFromValue(PeopleMap, t)).setBounds(300 + TeacherSpacer, 300, 40, 40);
                    } else if (TeacherLocationBox.getSelectedItem().toString() == "City Hall") {
                        CityHall1.add(t);
                        ((JLabel) getKeyFromValue(PeopleMap, t)).setBounds(500 + TeacherSpacer, 600 + TeacherSpacer, 40, 40);
                    } else if (TeacherLocationBox.getSelectedItem().toString() == "School") {
                        School1.add(t);
                        ((JLabel) getKeyFromValue(PeopleMap, t)).setBounds(100 + TeacherSpacer, 100 + TeacherSpacer, 40, 40);
                    }
                    LayerManager++;
                    TeacherSpacer = TeacherSpacer + 10;
                    VisualPanel.setVisible(true);
                    VisualPanel.add(PeoplePane);
                    VisualPanel.revalidate();
                    JOptionPane.showMessageDialog(null, "You have created a new teacher!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERROR creating teacher!" + ex.getMessage());
                }


            }
        });
        TeacherSubjectBox.addItemListener(new ItemListener() {
            /**
             * Invoked when an item has been selected or deselected by the user.
             * The code written for this method performs the operations
             * that need to occur when an item is selected (or deselected).
             *
             * @param e
             */
            @Override
            public void itemStateChanged(ItemEvent e) {

            }
        });
        TeacherLocationBox.addItemListener(new ItemListener() {
            /**
             * Invoked when an item has been selected or deselected by the user.
             * The code written for this method performs the operations
             * that need to occur when an item is selected (or deselected).
             *
             * @param e
             */
            @Override
            public void itemStateChanged(ItemEvent e) {

            }
        });
        enterNameOfTeacherTextField.addKeyListener(new KeyAdapter() {
            /**
             * Invoked when a key has been typed.
             * This event occurs when a key press is followed by a key release.
             *
             * @param e
             */
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });
        enterAgeOfTeacherTextField.addKeyListener(new KeyAdapter() {
            /**
             * Invoked when a key has been typed.
             * This event occurs when a key press is followed by a key release.
             *
             * @param e
             */
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });
        enterGradeLevelTextField.addKeyListener(new KeyAdapter() {
            /**
             * Invoked when a key has been typed.
             * This event occurs when a key press is followed by a key release.
             *
             * @param e
             */
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });


        BuildingStatBox.addItemListener(new ItemListener() {
            /**
             * Invoked when an item has been selected or deselected by the user.
             * The code written for this method performs the operations
             * that need to occur when an item is selected (or deselected).
             *
             * @param e
             */
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Outputs a building summary of the selected building

                if (BuildingStatBox.getSelectedItem().toString() == "City Hall") {

                    JOptionPane.showMessageDialog(null, A.getName()+ "\n" + A.getAddress() + "\n" + " Statistics: \n Total Occupants: " + CityHall1.size() + "\n"
                            + "Number of Police Officers in City Hall: " + PoliceinCityHall() + "\n" + "Number of Common Citizens in City Hall: " + CommoninCityHall());
                } else if (BuildingStatBox.getSelectedItem().toString() == "School") {
                    JOptionPane.showMessageDialog(null, B.getName()+ "\n" + B.getAddress() + "\n " + " Statistics: \n Total Occupants: " + School1.size() + "\n"
                            + "Number of Kids in School: " + KidsinSchool() + "\n" + "Number of Teacher in School: " + TeachersinSchool() + "\n" + "Police Patrolling School: " + PoliceinSchool());
                }


            }
        });
        enterNameOfKidTextField.addKeyListener(new KeyAdapter() {
            /**
             * Invoked when a key has been typed.
             * This event occurs when a key press is followed by a key release.
             *
             * @param e
             */
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });
        enterAgeOfKidTextField.addKeyListener(new KeyAdapter() {
            /**
             * Invoked when a key has been typed.
             * This event occurs when a key press is followed by a key release.
             *
             * @param e
             */
            @Override
            public void keyTyped(KeyEvent e) {

            }
        });
        KidCandyBox.addItemListener(new ItemListener() {
            /**
             * Invoked when an item has been selected or deselected by the user.
             * The code written for this method performs the operations
             * that need to occur when an item is selected (or deselected).
             *
             * @param e
             */
            @Override
            public void itemStateChanged(ItemEvent e) {

            }
        });
        KidLocationBox.addItemListener(new ItemListener() {
            /**
             * Invoked when an item has been selected or deselected by the user.
             * The code written for this method performs the operations
             * that need to occur when an item is selected (or deselected).
             *
             * @param e
             */
            @Override
            public void itemStateChanged(ItemEvent e) {

            }
        });
        createKidButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {


                try {
                    // Create kid based off user input
                    Kid k = new Kid(enterNameOfKidTextField.getText(), Integer.valueOf(enterAgeOfKidTextField.getText()), KidCandyBox.getSelectedItem().toString());

                    // Get Kid image
                    Toolkit toolkit4 = Toolkit.getDefaultToolkit();
                    URL KidUrl = new URL("https://image.flaticon.com/icons/png/128/163/163807.png");
                    Image Kidimg = toolkit4.getImage(KidUrl);
                    Kidimg = Kidimg.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                    ImageIcon KidIcon = new ImageIcon(Kidimg);

                    // Store kid and new JLabel in a map
                    PeopleMap.put(new JLabel(KidIcon), k);

                    // Add JLabel to the layered pane
                    PeoplePane.add((JLabel) getKeyFromValue(PeopleMap, k), new Integer(LayerManager));

                    PeoplePane.addMouseMotionListener(new MouseMotionAdapter() {
                        /**
                         * Invoked when a mouse button is pressed on a component and then
                         * dragged.  Mouse drag events will continue to be delivered to
                         * the component where the first originated until the mouse button is
                         * released (regardless of whether the mouse position is within the
                         * bounds of the component).
                         *
                         * @param e
                         */
                        @Override
                        public void mouseDragged(MouseEvent e) {
                            System.out.println("dragging");
                            JLabel label = null;
                            for (int i = 0; i < PeopleMap.size(); i++) {
                                if (((JLabel) getKeyFromValue(PeopleMap, k)).getBounds().contains(e.getPoint())) {
                                    label = (JLabel) getKeyFromValue(PeopleMap, k);
                                }
                            }
                            if (label != null) {
                                if (diffdrag == null) {
                                    diffdrag = new Point(e.getX() - label.getBounds().x, e.getY() - label.getBounds().y);
                                }
                                label.setBounds(e.getX() - diffdrag.x, e.getY() - diffdrag.y, label.getBounds().width,
                                        label.getBounds().height);

                                System.out.printf("You are moving %s, moved label to <%d, %d> ", PeopleMap.get(label).toString(), e.getX() - diffdrag.x, e.getY() - diffdrag.y);
                            }
                            mouseReleased(e, PeopleMap.get(label));
                            super.mouseDragged(e);
                        }
                        public void mouseReleased(MouseEvent e, Person person) {
                            if(CityPeople1.contains(person)) {
                                if (e.getX() - diffdrag.x >= 393 && e.getX() - diffdrag.x <= 618 && e.getY() - diffdrag.y >= 455 && e.getY() - diffdrag.y <= 660)
                                {
                                    System.out.println("You have moved this person to city hall");
                                    CityPeople1.remove(person);
                                    CityHall1.add(person);
                                }
                                else if(e.getX() - diffdrag.x >= 48 && e.getX() - diffdrag.x <= 235 && e.getY() -diffdrag.y >= 28 && e.getY() - diffdrag.y <= 263)
                                {
                                    System.out.println("You have moved this person to School");
                                    CityPeople1.remove(person);
                                    School1.add(person);
                                }
                            }
                            if(School1.contains(person)) {
                                if (e.getX() - diffdrag.x >= 393 && e.getX() - diffdrag.x <= 618 && e.getY() - diffdrag.y >= 455 && e.getY() - diffdrag.y <= 660) {
                                    System.out.println("You have moved this person to city hall");
                                    School1.remove(person);
                                    CityHall1.add(person);
                                }
                                else if(e.getX() - diffdrag.x >= 48 && e.getX() - diffdrag.x <= 235 && e.getY() -diffdrag.y >= 28 && e.getY() - diffdrag.y <= 263) {

                                }
                                else {
                                    System.out.println("You have moved this person to the city!");
                                    School1.remove(person);
                                    CityPeople1.add(person);
                                }
                            }
                            if(CityHall1.contains(person)) {
                                if(e.getX() - diffdrag.x >= 48 && e.getX() - diffdrag.x <= 235 && e.getY() -diffdrag.y >= 28 && e.getY() - diffdrag.y <= 263)
                                {
                                    System.out.println("You have moved this person to school");
                                    CityPeople1.remove(person);
                                    School1.add(person);
                                }
                                else if (e.getX() - diffdrag.x >= 393 && e.getX() - diffdrag.x <= 618 && e.getY() - diffdrag.y >= 455 && e.getY() - diffdrag.y <= 660) {

                                }
                                else {
                                    System.out.println("You have moved this person to the city!");
                                    CityHall1.remove(person);
                                    CityPeople1.add(person);
                                }
                            }



                        }
                    });

                    // Put kid in the correct location
                    if (KidLocationBox.getSelectedItem().toString() == "City") {
                        CityPeople1.add(k);
                        ((JLabel) getKeyFromValue(PeopleMap, k)).setBounds(300 + KidSpacer, 300 + KidSpacer, 40, 40);
                    } else if (KidLocationBox.getSelectedItem().toString() == "City Hall") {
                        CityHall1.add(k);
                        ((JLabel) getKeyFromValue(PeopleMap, k)).setBounds(500 + KidSpacer, 600 + KidSpacer, 40, 40);
                    } else if (KidLocationBox.getSelectedItem().toString() == "School") {
                        School1.add(k);
                        ((JLabel) getKeyFromValue(PeopleMap, k)).setBounds(100 + KidSpacer, 100 + KidSpacer, 40, 40);
                    }
                    LayerManager++;
                    KidSpacer = KidSpacer + 7;
                    VisualPanel.setVisible(true);
                    VisualPanel.add(PeoplePane);
                    VisualPanel.revalidate();
                    Frame.pack();
                    JOptionPane.showMessageDialog(null, "You have created a new kid!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error creating kid!" + ex.getMessage());
                }
            }
        });

        enterNameOfPoliceTextField.addKeyListener(new KeyAdapter() {
            /**
             * Invoked when a key has been typed.
             * This event occurs when a key press is followed by a key release.
             *
             * @param e
             */
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });
        enterAgeOfPoliceTextField.addKeyListener(new KeyAdapter() {
            /**
             * Invoked when a key has been typed.
             * This event occurs when a key press is followed by a key release.
             *
             * @param e
             */
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });
        PoliceRoleBox.addItemListener(new ItemListener() {
            /**
             * Invoked when an item has been selected or deselected by the user.
             * The code written for this method performs the operations
             * that need to occur when an item is selected (or deselected).
             *
             * @param e
             */
            @Override
            public void itemStateChanged(ItemEvent e) {

            }
        });
        PoliceLocationBox.addItemListener(new ItemListener() {
            /**
             * Invoked when an item has been selected or deselected by the user.
             * The code written for this method performs the operations
             * that need to occur when an item is selected (or deselected).
             *
             * @param e
             */
            @Override
            public void itemStateChanged(ItemEvent e) {

            }
        });
        createPoliceButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Create new police officer given user input
                    Police p = new Police(enterNameOfPoliceTextField.getText(), Integer.valueOf(enterAgeOfPoliceTextField.getText()), Police.Role.valueOf(PoliceRoleBox.getSelectedItem().toString()));

                    // Get police image
                    Toolkit toolkit6 = Toolkit.getDefaultToolkit();
                    URL PoliceUrl = new URL("http://icons.iconarchive.com/icons/google/noto-emoji-people-profession/256/10417-man-police-officer-icon.png");
                    Image Policeimg = toolkit6.getImage(PoliceUrl);
                    Policeimg = Policeimg.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                    ImageIcon PoliceIcon = new ImageIcon(Policeimg);

                    // Put the Label and Police in the Map
                    PeopleMap.put(new JLabel(PoliceIcon), p);
                    // Add the JLabel to the layered pane
                    PeoplePane.add((JLabel) getKeyFromValue(PeopleMap, p), new Integer(LayerManager));
                    // Add a mouse Motion Listener to the pane
                    PeoplePane.addMouseMotionListener(new MouseMotionAdapter() {
                        /**
                         * Invoked when a mouse button is pressed on a component and then
                         * dragged.  Mouse drag events will continue to be delivered to
                         * the component where the first originated until the mouse button is
                         * released (regardless of whether the mouse position is within the
                         * bounds of the component).
                         *
                         * @param e
                         */
                        @Override
                        public void mouseDragged(MouseEvent e) {
                            System.out.println("dragging");
                            JLabel label = null;
                            for (int i = 0; i < PeopleMap.size(); i++) {
                                if (((JLabel) getKeyFromValue(PeopleMap, p)).getBounds().contains(e.getPoint())) {
                                    label = (JLabel) getKeyFromValue(PeopleMap, p);
                                }
                            }
                            if (label != null) {
                                if (diffdrag == null) {
                                    diffdrag = new Point(e.getX() - label.getBounds().x, e.getY() - label.getBounds().y);
                                }
                                label.setBounds(e.getX() - diffdrag.x, e.getY() - diffdrag.y, label.getBounds().width,
                                        label.getBounds().height);


                                System.out.printf("You are moving %s, and %d moved label to <%d, %d> \n ", PeopleMap.get(label).toString(), label.hashCode(),  e.getX() - diffdrag.x, e.getY() - diffdrag.y);
                                mouseReleased(e, PeopleMap.get(label));
                            }

                            super.mouseDragged(e);
                        }
                        public void mouseReleased(MouseEvent e, Person person) {
                            if(CityPeople1.contains(person)) {
                                if (e.getX() - diffdrag.x >= 393 && e.getX() - diffdrag.x <= 618 && e.getY() - diffdrag.y >= 455 && e.getY() - diffdrag.y <= 660)
                                {
                                    System.out.println("You have moved this person to city hall");
                                    CityPeople1.remove(person);
                                    CityHall1.add(person);
                                }
                                else if(e.getX() - diffdrag.x >= 48 && e.getX() - diffdrag.x <= 235 && e.getY() -diffdrag.y >= 28 && e.getY() - diffdrag.y <= 263)
                                {
                                    System.out.println("You have moved this person to School");
                                    CityPeople1.remove(person);
                                    School1.add(person);
                                }
                            }
                            if(School1.contains(person)) {
                                if (e.getX() - diffdrag.x >= 393 && e.getX() - diffdrag.x <= 618 && e.getY() - diffdrag.y >= 455 && e.getY() - diffdrag.y <= 660) {
                                    System.out.println("You have moved this person to city hall");
                                    School1.remove(person);
                                    CityHall1.add(person);
                                }
                               else if(e.getX() - diffdrag.x >= 48 && e.getX() - diffdrag.x <= 235 && e.getY() -diffdrag.y >= 28 && e.getY() - diffdrag.y <= 263) {

                                }
                                else {
                                    System.out.println("You have moved this person to the city!");
                                    School1.remove(person);
                                    CityPeople1.add(person);
                                }
                            }
                            if(CityHall1.contains(person)) {
                                if(e.getX() - diffdrag.x >= 48 && e.getX() - diffdrag.x <= 235 && e.getY() -diffdrag.y >= 28 && e.getY() - diffdrag.y <= 263)
                                {
                                    System.out.println("You have moved this person to school");
                                    CityPeople1.remove(person);
                                    School1.add(person);
                                }
                               else if (e.getX() - diffdrag.x >= 393 && e.getX() - diffdrag.x <= 618 && e.getY() - diffdrag.y >= 455 && e.getY() - diffdrag.y <= 660) {

                                }
                                else {
                                    System.out.println("You have moved this person to the city!");
                                    CityHall1.remove(person);
                                    CityPeople1.add(person);
                                }
                            }



                        }
                    });

                    // Put the created police in the correct location
                    if (PoliceLocationBox.getSelectedItem().toString() == "City") {
                        CityPeople1.add(p);
                        ((JLabel) getKeyFromValue(PeopleMap, p)).setBounds(300 + PoliceSpacer, 300 + PoliceSpacer, 40, 40);
                    } else if (PoliceLocationBox.getSelectedItem().toString() == "City Hall") {
                        CityHall1.add(p);
                        ((JLabel) getKeyFromValue(PeopleMap, p)).setBounds(500 + PoliceSpacer, 600 + PoliceSpacer, 40, 40);
                    } else if (PoliceLocationBox.getSelectedItem().toString() == "School") {
                        School1.add(p);
                        ((JLabel) getKeyFromValue(PeopleMap, p)).setBounds(100 + PoliceSpacer, 100 + PoliceSpacer, 40, 40);
                    }

                    LayerManager++;
                    PoliceSpacer = PoliceSpacer + 9;
                    VisualPanel.setVisible(true);
                    VisualPanel.add(PeoplePane);
                    VisualPanel.revalidate();
                    Frame.pack();
                    JOptionPane.showMessageDialog(null, "You have created a new police!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error creating police!" + ex.getMessage());
                }
            }
        });
        viewCityBreakdownButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Total People in City: " + PeopleMap.size() + "\n" + "Number of people wandering the city: " + CityPeople1.size() + "\n"
                        + "Number of people in City Hall: " + CityHall1.size() + "\n" + "Number of people in School: " + School1.size() + "\n");
            }
        });
    }


    /**
     * This function returns the Key given the value of a type person. This
     * allows me to add the specific JLabel to the layered pane while still connecting
     * the JLabel to the object information when it is implemented above. The following
     * stack overflow link helped me out with this function.
     * https://stackoverflow.com/questions/8112975/get-key-from-a-hashmap-using-the-value
     *
     * @param h
     * @param value
     * @return
     */
    public Object getKeyFromValue(Map h, Person value) {
        for (Object o : h.keySet()) {
            if (h.get(o).equals(value))
                return o;
        }
        return null;
    }


    /**
     * Find the current number of police in city hall
     *
     * @return amount of police in city hall
     */
    public int PoliceinCityHall() {
        int policecount = 0;
        for (int i = 0; i < CityHall1.size(); i++) {
            if (CityHall1.get(i) instanceof Police) {
                policecount++;
            }
        }
        return policecount;
    }

    /**
     * Find the number of regular citizens in city hall
     *
     * @return Kids and teachers in city hall
     */
    public int CommoninCityHall() {
        int commoncount = 0;
        for (int i = 0; i < CityHall1.size(); i++) {
            if (CityHall1.get(i) instanceof Kid || CityHall1.get(i) instanceof Teacher) {
                commoncount++;
            }
        }
        return commoncount;
    }

    /**
     * Find the number of kids in school
     *
     * @return amount of kids in school
     */
    public int KidsinSchool() {
        int kidcount = 0;
        for (int i = 0; i < School1.size(); i++) {
            if (School1.get(i) instanceof Kid) {
                kidcount++;
            }
        }
        return kidcount;
    }

    /**
     * Find the number of teachers in school
     *
     * @return amount of teachers in school
     */
    public int TeachersinSchool() {
        int teachcnt = 0;
        for (int i = 0; i < School1.size(); i++) {
            if (School1.get(i) instanceof Teacher) {
                teachcnt++;
            }
        }
        return teachcnt;
    }

    /**
     * Find the number of police in school
     *
     * @return amount of police patrolling school
     */
    public int PoliceinSchool() {
        int pcount = 0;
        for (int i = 0; i < School1.size(); i++) {
            if (School1.get(i) instanceof Police) {
                pcount++;
            }
        }
        return pcount;
    }

    // Used to initialize the GUI
    public void CityGraphicGUI() {
        Initialize();
        createUIComponents();
        Frame.setVisible(true);
    }

    public void Initialize() {

        Frame = new JFrame();
        Frame.setResizable(true);
        Frame.setSize(1000, 1000);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PeoplePane = new JLayeredPane();
        PeoplePane.setPreferredSize(new Dimension(800, 700));

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        URL IMG = getClass().getResource("/resources/School.png");
        ImageIcon SchoolIcon = new ImageIcon(IMG);
        JLabel SchoolLabel = new JLabel(SchoolIcon);
        SchoolLabel.setBounds(40, 40, 250, 250);
        PeoplePane.add(SchoolLabel, new Integer(1));

        Toolkit toolkit3 = Toolkit.getDefaultToolkit();
        URL MAP = getClass().getResource("/resources/MAP.jpg");
        ImageIcon MapIcon = new ImageIcon(MAP);
        Image image = MapIcon.getImage();
        Image newimg = image.getScaledInstance(800, 700, Image.SCALE_SMOOTH);
        MapIcon = new ImageIcon(newimg);
        JLabel MapLabel = new JLabel(MapIcon);
        MapLabel.setBounds(2, 2, 700, 700);
        PeoplePane.add(MapLabel, new Integer(0));

        Toolkit toolkit2 = Toolkit.getDefaultToolkit();
        URL IMG2 = getClass().getResource("/resources/75701-200.png");
        ImageIcon CityHallIcon = new ImageIcon(IMG2);
        JLabel CityHallPic = new JLabel(CityHallIcon);
        CityHallPic.setBounds(400, 450, 250, 250);
        PeoplePane.add(CityHallPic, new Integer(1));


        VisualPanel.add(PeoplePane);
        VisualPanel.setVisible(true);
        VisualPanel.revalidate();

        Frame.pack();


    }

    private void createUIComponents() {

        try {


            PeoplePane = new JLayeredPane();
            PeoplePane.setPreferredSize(new Dimension(800, 700));
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            URL IMG = getClass().getResource("/resources/School.png");
            ImageIcon SchoolIcon = new ImageIcon(IMG);
            JLabel SchoolLabel = new JLabel(SchoolIcon);
            SchoolLabel.setBounds(40, 40, 250, 250);
            PeoplePane.add(SchoolLabel, new Integer(1));


            Toolkit toolkit3 = Toolkit.getDefaultToolkit();
            URL MAP = getClass().getResource("/resources/MAP.jpg");
            ImageIcon MapIcon = new ImageIcon(MAP);
            Image image = MapIcon.getImage();
            Image newimg = image.getScaledInstance(800, 700, Image.SCALE_SMOOTH);
            MapIcon = new ImageIcon(newimg);
            JLabel MapLabel = new JLabel(MapIcon);
            MapLabel.setBounds(2, 2, 700, 700);
            PeoplePane.add(MapLabel, new Integer(0));

            Toolkit toolkit2 = Toolkit.getDefaultToolkit();
            URL IMG2 = getClass().getResource("/resources/75701-200.png");
            ImageIcon CityHallIcon = new ImageIcon(IMG2);
            JLabel CityHallPic = new JLabel(CityHallIcon);
            CityHallPic.setBounds(400, 450, 250, 250);
            PeoplePane.add(CityHallPic, new Integer(1));

            Toolkit toolkit1 = Toolkit.getDefaultToolkit();
            URL imgUrl = new URL("https://secure.webtoolhub.com/static/resources/icons/set178/c33afd2f.png");
            Image img = toolkit1.getImage(imgUrl);
            img = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            ImageIcon TeacherIcon = new ImageIcon(img);
            JLabel TeacherLabel = new JLabel(TeacherIcon);
            TeacherLabel.setBounds(20, 20, 20, 20);

            VisualPanel.setVisible(true);
            VisualPanel.add(PeoplePane);
            VisualPanel.revalidate();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CityView");
        CityView e = new CityView();
        frame.setContentPane(e.setPanel);
        e.Initialize();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();


        frame.setVisible(true);
    }


}

    /*

    UNUSED TEST FUNCTIONS and TEST SCENARIOS

    public class PersonLabel extends JLabel {
        private Person person;

        public void SetPerson(Person person) {
            this.person = person;
        }

        public Person getPerson() {
            return person;
        }
    }
*/




/*public int TotalTeachers() {
        int totalteach = 0;
        for(int i = 0; i < CityHall1.size(); i++) {
            if (CityHall1.get(i) instanceof Teacher) {
                totalteach++;
            }
        }
        for(int j = 0; j < CityPeople1.size(); j++) {
            if(CityPeople1.get(j) instanceof Teacher) {
                totalteach++;
            }
            totalteach = totalteach + TeachersinSchool();
        }

        return totalteach;
    }

    public int TotalKids() {
        int totalkid = 0;
        for(int i = 0; i < CityHall1.size(); i++) {
            if (CityHall1.get(i) instanceof Kid) {
                totalkid++;
            }
        }
        for(int j = 0; j < CityPeople1.size(); j++) {
            if(CityPeople1.get(j) instanceof Kid) {
                totalkid++;
            }
        for(int k = 0; k < School1.size(); k++) {
               if(School1.get(k) instanceof Kid) {
                   totalkid++;
               }
        }
        }

        return totalkid;
    }

    public int TotalPolice() {
        int totalpolice = 0;
        for(int i = 0; i < CityPeople1.size(); i++) {
            if(CityPeople1.get(i) instanceof Police) {
                totalpolice++;
            }
            totalpolice = totalpolice + PoliceinCityHall() + PoliceinSchool();
        }
        return totalpolice;
    }*/
















    // public void JLabel() {

     //   JFrame Frame = new JFrame();
       // try {
        //    URL url = new URL("https://i.etsystatic.com/5209325/r/il/5198dc/689043960/il_fullxfull.689043960_fvm5.jpg");
         //   Jlabel1.setIcon(new ImageIcon(url));
         //   Frame.setLayout(new GridBagLayout());
         //   Frame.setBounds(800, 800, 800, 800);
         //   Frame.add(Jlabel1);
         //   Frame.add(radioButton1);
         //   Frame.add(Jpanel2);
         //   Frame.setVisible(true);
       // } catch (MalformedURLException e) {
        //    e.printStackTrace();
      //  }


  //  }

//}

 /*   public void Initialize() {
        JFrame frame = new JFrame();
        try {

            frame.setContentPane(new Jlabel1(new ImageIcon(ImageIO.read(url))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.pack();
        frame.setVisible(true);

    }

}

*/
/*





        frame.setBounds(800, 800, 800, 800);




        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new GridBagLayout());

        try {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            URL imgUrl = new URL("https://i.etsystatic.com/5209325/r/il/5198dc/689043960/il_fullxfull.689043960_fvm5.jpg");
            Image img = toolkit.getImage(imgUrl);
            img = img.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(img);
            JButton button1 = new JButton(icon);
            frame.add(button1);
            frame.setVisible(true);
        }
        catch(Exception ex) {
            System.out.println("Didnt work");
            ;
        }
    }
}

/*
                    PeoplePane = new JLayeredPane();
                    PeoplePane.setPreferredSize(new Dimension(800,700));
                    Toolkit toolkit = Toolkit.getDefaultToolkit();
                    URL IMG = getClass().getResource("/resources/School.png");
                    ImageIcon SchoolIcon = new ImageIcon(IMG);
                    JLabel SchoolLabel = new JLabel(SchoolIcon);
                    SchoolLabel.setBounds(40, 40, 250, 250);
                    PeoplePane.add(SchoolLabel, new Integer(1));

                    Toolkit toolkit3 = Toolkit.getDefaultToolkit();
                    URL MAP = getClass().getResource("/resources/MAP.jpg");
                    ImageIcon MapIcon = new ImageIcon(MAP);
                    Image image = MapIcon.getImage();
                    Image newimg = image.getScaledInstance(800,700,Image.SCALE_SMOOTH);
                    MapIcon = new ImageIcon(newimg);
                    JLabel MapLabel = new JLabel(MapIcon);
                    MapLabel.setBounds(2, 2, 700, 700);
                    PeoplePane.add(MapLabel, new Integer(0));

                    Toolkit toolkit2 = Toolkit.getDefaultToolkit();
                    URL IMG2 = getClass().getResource("/resources/75701-200.png");
                    ImageIcon CityHallIcon = new ImageIcon(IMG2);
                    JLabel CityHallPic = new JLabel(CityHallIcon);
                    CityHallPic.setBounds(400, 450, 250, 250);
                    PeoplePane.add(CityHallPic, new Integer(1));
*/

