
package com.william.graphing;


import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Bananas extends Canvas implements ActionListener  {
    private final List<Building> buildings;
    private static final List<Bullet> bullets = new ArrayList<>();
    private static CityScape cityScape;
    public boolean keepRepainting = true;
    public Bullet test;
    public int angle = 690;//Integer.parseInt(reader.readLine());
    public double radians;
    public double velocity;
    public long time;
    public static final int WIDTH = 1100;
    public static final int HEIGHT = 500;
    public static final int WIDTHMIN = 20;
    public static final int WIDTHMAX = 100;
    public static final int HEIGHTMIN = 80;
    public static final int HEIGHTMAX = 100;
    public Building hitBuilding;
    public int calculatedY;
    public static boolean destroyBullet;
    public Building cannonBuilding;
    public BufferedImage image;
    public VolatileImage vImg;
    public JTextField textField;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public boolean isGameDisplayed = false;

    private JPanel game = new JPanel();
    private JButton fireButton = new JButton();
    private JFrame f = new JFrame();
    private JTextField windBox = new JTextField();
    private JButton windButton = new JButton();
    private JTextField testText1 = new JTextField();
    private JLabel l = new JLabel();
    private JPanel menu = new JPanel();

    public static void main(String[] args) throws InterruptedException, IOException, LineUnavailableException, UnsupportedAudioFileException {
        Bananas canvas = new Bananas();

        canvas.f.setTitle("Game");
        // create a label to display text
        /*canvas.f.addWindowListener(new WindowAdapter() {
            public void windowOpened( WindowEvent e ){
                    canvas.f.requestFocus();
                    canvas.testText1.setText("Input");

            }
                //testText1.setFocusable(true);
        });
        canvas.testText1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                    canvas.testText1.setText("");
                }
            @Override
            public void focusLost(FocusEvent e) {
                canvas.testText1.setText("Input");
            }
        });

         */
        canvas.game.setLayout(new BorderLayout());
        // add buttons and textfield to panel
        canvas.windButton.addActionListener(canvas);
        canvas.windBox.addActionListener(canvas);
        canvas.fireButton.setBounds(525,0,75,25);
        canvas.fireButton.addActionListener(canvas);
        canvas.textField = canvas.testText1;
        //testButton.setBounds();
        canvas.testText1.setToolTipText("Please enter some text here");
        //p.add(l);
        canvas.game.add(canvas.fireButton);
        canvas.testText1.setBounds(450, 0, 75, 25);
        canvas.game.add(canvas.testText1);
        canvas.windBox.setBounds(450,0,75,25);
        canvas.windButton.setBounds(525,0,75,25);

        canvas.menu.add(canvas.windBox);
        canvas.menu.add(canvas.windButton);

        // add panel to frame
        canvas.f.add(canvas.menu);

        //f.remove(game);
        canvas.game.add(canvas);
        // set the size of frame
        canvas.f.setSize(1050,525);
        canvas.f.show();
        for (Building b : canvas.buildings){
            if (b instanceof CannonBuilding){
                canvas.cannonBuilding = b;
            }

        }
        if(canvas.cannonBuilding == null) {
            throw new RuntimeException("Huh");
        }
        /*
        JPanel frame = new JPanel();

        JTextField testText = new JTextField("                ");
        testText.setSize(100,30);
        testText.setSize(WIDTH, 50);
        //testText.setForeground(Color.BLACK);
        testText.addActionListener(canvas);
        frame.setLayout(new OverlayLayout(frame));
        JButton testButton =new JButton("Click Here");
        JButton startButton =new JButton("Click Here");

        canvas.setSize(WIDTH, HEIGHT);
        frame.add(testText);
        canvas.textField = testText;
        frame.add(testButton);
        frame.add(canvas);

        frame.setSize(frame.getWidth()-30, frame.getHeight());
        frame.show();

        //frame.setSize(frame.getWidth(), frame.getHeight() + 100);
        frame.setVisible(true);



        for (Building b : canvas.buildings){
            if (b instanceof CannonBuilding){
                canvas.cannonBuilding = b;
            }

        }
        if(canvas.cannonBuilding == null) {
            throw new RuntimeException("Huh");
        }
        */


    }


    public Bananas() throws IOException, UnsupportedAudioFileException {


        isGameDisplayed = false;
        //CityScape cityScape = new CityScape(new FullSpectrumRandomColorGenerator());
        CityScape cityScape = new CityScape(new NumberOfColorsRandomGenerator());
        //bullets.add(new Bullet(250,250,-25,25,System.currentTimeMillis()));
        //bullets.add(new Bullet(250,250,-45,15,System.currentTimeMillis()));

        textField = null;
        buildings = cityScape.buildBuildings();
        time = System.currentTimeMillis();
        velocity = 50d / 1000d;

        new Timer(125, this).start();
        game = new JPanel();
        fireButton = new JButton("Fire!");
        f = new JFrame("panel");
        windBox = new JTextField("1");
        windButton = new JButton("Set wind");
        testText1 = new JTextField("");
        l = new JLabel("panel label");
        menu = new JPanel();
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void drawGame(Graphics g){
        int x = 0;

        for (Building b : buildings) {
            g.setColor(b.getColor());
            try {
                x += b.draw(g, x, HEIGHT);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }


        if (destroyBullet) {
            return;
        }
        double timeDelta = System.currentTimeMillis() - time;


        for (Building b : buildings) {
/*
            if (b instanceof CannonBuilding) {
                cannonBuilding = b;

                System.out.println(cannonBuilding.getXCoordinate());
            }
*/

            for (Bullet bullet : bullets) {
                System.out.println(bullet.getBulletX() + "  " + bullet.getBulletY());
                //bullets.remove(0);


                bullet.draw(g);
                hitBuilding = isAnyBuildingHit(bullet.getBulletX(),bullet.getBulletY());

                if (hitBuilding instanceof GorillaBuilding) {
                    try {
                        image = ImageIO.read(new File("/home/william/Downloads/YouWin.png"));
                        //g.drawImage(image, 100, 100, Color.MAGENTA, null);
                        g.drawImage(image, 0, 0, 1100, 500, null);
                        keepRepainting = false;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }   //throw new RuntimeException(e);


                if (hitBuilding != null) {

                    File audioFile = new File("/home/william/Downloads/Explosion+1.wav");
                    AudioInputStream audioStream = null;
                    try {
                        audioStream = AudioSystem.getAudioInputStream(audioFile);
                    } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                        unsupportedAudioFileException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    AudioFormat format = audioStream.getFormat();

                    DataLine.Info info = new DataLine.Info(Clip.class, format);
                    Clip audioClip = null;
                    try {
                        audioClip = (Clip) AudioSystem.getLine(info);
                        audioClip.open(audioStream);
                    } catch (LineUnavailableException | IOException lineUnavailableException) {
                        lineUnavailableException.printStackTrace();
                    }
                    audioClip.start();
                }
                if (hitBuilding instanceof AlleyBuilding) {
                    buildings.set(buildings.indexOf(hitBuilding), new AlleyBuilding(new RubbleBuilding(hitBuilding), ((AlleyBuilding) hitBuilding).getAlleyWidth()));
                    bullets.remove(bullet);
                    System.out.println("The thing is YAH: " + bullets.contains(bullet));
                } else {
                    //System.out.println("Building hit " + hitBuilding.getXCoordinate() + "," + hitBuilding.getYCoordinate());
                    buildings.set(buildings.indexOf(hitBuilding), new RubbleBuilding(hitBuilding));
                    bullets.remove(bullet);
                    System.out.println("The thing is YAH: " + bullets.contains(bullet));
                }
            }

        }

        //
//            if (b instanceof CanonBuilding) {
//                double bIntercept = (b.getYCoordinate() - b.getHeight()) - b.getXCoordinate() * radians;
//                int initX = (int) (b.getXCoordinate() + timeDelta * velocity) + b.getWidth();
//                System.out.println(timeDelta + "::" + initX);
//                for (int i = initX; i < initX + 10; i += 2) {
//                    g.setColor(Color.BLUE);
//                    calculatedY = (int) ((radians * i) + bIntercept + .5 * .001 * timeDelta * timeDelta / 100);
//                    g.fillOval(i, calculatedY, 1, 1);
//
//                    //if(i >= WIDTH){
//                    //    System.out.println("Hello");
//                    //}
//                    //if(calculatedY >= HEIGHT){
//                    //          if ()
//                    //}
//                    Building hitBuilding = isAnyBuildingHit(i, calculatedY);
//                    if(i >= HEIGHT){
//                        destroyBullet = true;
//                    }
//                    if (hitBuilding != null) {
//                        System.out.println("Building hit " + hitBuilding.getXCoordinate() + "," + hitBuilding.getYCoordinate());
//                        buildings.set(buildings.indexOf(buildingHit), new RubbleBuilding(buildingHit));
//                        destroyBullet = true;
//
//                    }
//
//                }

//            }
    }
    public void paint(Graphics g) {
            drawGame(g);

        }





    public Building isAnyBuildingHit(double startX, double startY) {
        for (Building b : buildings) {
            if (b.buildingHit(startX, startY)) {
                return b;
            }
        }
        return null;
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        String actionCommand = e.getActionCommand();
        //now take input in a while
        //System.out.println(e);
        //actionCommand = "no";
        if (isGameDisplayed == false && actionCommand != null){

            isGameDisplayed = true;
            f.add(game);
            f.remove(menu);
            System.out.println("it work maybe");
            //System.out.println(source.toString());
            Bullet.WIND = Integer.parseInt(windBox.getText());
            System.out.println("it did it");

        }
        if(actionCommand != null && isGameDisplayed == true) {
            bullets.add(new Bullet(Integer.parseInt(textField.getText()), 75, this.cannonBuilding.getXCoordinate() + 100, cannonBuilding.getYCoordinate()-190, System.currentTimeMillis()));

            File audioFile = new File("/home/william/Downloads/Explosion+1.wav");
            System.out.print("Got here");
            AudioInputStream audioStream = null;
            try {
                audioStream = AudioSystem.getAudioInputStream(audioFile);
            } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                unsupportedAudioFileException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            AudioFormat format = audioStream.getFormat();

            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = null;
            try {
                audioClip = (Clip) AudioSystem.getLine(info);
                audioClip.open(audioStream);
            } catch (LineUnavailableException | IOException lineUnavailableException) {
                lineUnavailableException.printStackTrace();
            }
            audioClip.start();
        }
        f.show();
        if (keepRepainting) {
            repaint();
        }
    }
}
