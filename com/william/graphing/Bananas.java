
package com.william.graphing;


import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Bananas extends Canvas implements ActionListener {
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


    public static void main(String[] args) throws InterruptedException, IOException, LineUnavailableException, UnsupportedAudioFileException {
        JFrame f = new JFrame("panel");
        f.setTitle("Game");
        // create a label to display text
        JLabel l = new JLabel("panel label");
        JTextField testText1 = new JTextField("hello");
        Bananas canvas = new Bananas();

        // create a panel to add buttons
        JPanel p = new JPanel();
        p.setLayout(new OverlayLayout(p));
        // add buttons and textfield to panel

        //p.add(l);
        testText1.setBounds(50, 50, 280, 50);
        f.add(testText1);

        // add panel to frame
        f.add(p);
        f.add(canvas);
        // set the size of frame
        f.setSize(1050,525);
        f.show();
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

        testButton.addActionListener(canvas);
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



        //CityScape cityScape = new CityScape(new FullSpectrumRandomColorGenerator());
        CityScape cityScape = new CityScape(new NumberOfColorsRandomGenerator());
        //bullets.add(new Bullet(250,250,-25,25,System.currentTimeMillis()));
        //bullets.add(new Bullet(250,250,-45,15,System.currentTimeMillis()));

        textField = null;
        buildings = cityScape.buildBuildings();
        time = System.currentTimeMillis();
        velocity = 50d / 1000d;

        new Timer(125, this).start();
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public void drawStartMenu(Graphics g){

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
                hitBuilding = isAnyBuildingHit((int) bullet.getBulletX(), (int) bullet.getBulletY());
                System.out.println(bullet.getBulletX() + "  " + bullet.getBulletY());
                //bullets.remove(0);


                bullet.draw(g);
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





    public Building isAnyBuildingHit(int startX, int startY) {
        for (Building b : buildings) {
            if (b.buildingHit(startX, startY)) {
                return b;
            }
        }
        return null;
    }


    private Color getRandomColor() {
        return new Color(getRandomNumber(0, 225), getRandomNumber(0, 225), getRandomNumber(0, 0));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String actionCommand = e.getActionCommand();
        //now take input in a while
        System.out.println(e);
        if(actionCommand != null) {
            bullets.add(new Bullet(Integer.parseInt(textField.getText()), 225, this.cannonBuilding.getXCoordinate() + 100, cannonBuilding.getYCoordinate()-190, System.currentTimeMillis()));
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
        if (keepRepainting == true) {
            repaint();
        }
    }
}
