package com.example.learning;

import inputs.KeyboardInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class GamePanel extends JPanel {
    private BufferedImage img;
    private BufferedImage cloudone;
    private BufferedImage cloudtwo;
    private BufferedImage greenery;
    private BufferedImage chicken, flyingchicken;
    int highscorenumber = 0;
    boolean checker = true;
    int holesize = 250;
    char oneone = '-';
    char onetwo = '-';
    char onethree = '-';
    char twoone = '-';
    char twotwo = '-';
    char twothree = '-';
    char threeone = '-';
    char threetwo = '-';
    char threethree = '-';
    int scoreone = 0;
    int scoretwo = 0;
    int scorethree = 0;
    int enternameheight = -100;
    double pipespeedvalue = 0.15;
    double gravitynumbervalue = -0.3;
    double birdyvalue = 150.0;
    double pipeonex = 1500;
    double pipetwox = 2100;
    double pipethreex = 2700;
    double gravitynumber = gravitynumbervalue;
    double pipespeed = pipespeedvalue;
    boolean gamescreen = true;
    private boolean keyProcessed = false;
    int score = 0;
    java.awt.Font pixelfont;
    Random random = new Random();
    int pipeonehole = random.nextInt(380) + 100;
    int pipetwohole = random.nextInt(380) + 100;
    int pipethreehole = random.nextInt(380) + 100;
    double cloudoney = 50;
    double cloudonex = 1200;
    double cloudtwoy = 300;
    double cloudtwox = 1500;
    double cloudthreey = 100;
    double cloudthreex = 1800;
    double cloudfoury = 600;
    double cloudfourx = 2100;

    public GamePanel() throws IOException {
        importImg();
        loadPixelFont();
        setFocusable(true);
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        setBackground(new Color(66, 135, 245));
    }

    private void loadPixelFont() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("Minecraft.ttf")) {
            if (is == null) {
                throw new IOException("Font file not found");
            }
            pixelfont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, is).deriveFont(24f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pixelfont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    private void importImg() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("cloudone.png")) {
            if (is == null) {
                throw new IOException("cloudone.png not found");
            }
            cloudone = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("cloudtwo.png")) {
            if (is == null) {
                throw new IOException("cloudtwo.png not found");
            }
            cloudtwo = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("greenery.png")) {
            if (is == null) {
                throw new IOException("greenery.png not found");
            }
            greenery = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("chicken.png")) {
            if (is == null) {
                throw new IOException("chicken.png not found");
            }
            chicken = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("flyingframe.png")) {
            if (is == null) {
                throw new IOException("flyingframe.png not found");
            }
            flyingchicken = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        flyingchicken = resizeImage(flyingchicken, 90, 50);
        chicken = resizeImage(chicken, 90, 50);
        greenery = resizeImage(greenery, 1300, 650);
        cloudtwo = resizeImage(cloudtwo, 120, 90);
        cloudone = resizeImage(cloudone, 150, 100);
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, originalImage.getType());
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g2d.dispose();
        return resizedImage;
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void Gravity() {
        birdyvalue += gravitynumber;
        gravitynumber += 0.0008;

        if (pipeonex < -5) {
            pipeonex = 1700;
            pipeonehole = random.nextInt(380) + 100;
            checker = true;
        }
        if (pipetwox < -5) {
            pipetwox = 1700;
            pipetwohole = random.nextInt(380) + 100;
            checker = true;
        }
        if (pipethreex < -5) {
            pipethreex = 1700;
            pipethreehole = random.nextInt(380) + 100;
            checker = true;
        }

        if (cloudonex < -120) {
            cloudonex = 1500;
            cloudoney = random.nextInt(380) + 100;
        }
        if (cloudtwox < -120) {
            cloudtwox = 1500;
            cloudtwoy = random.nextInt(380) + 100;
        }
        if (cloudthreex < -120) {
            cloudthreex = 1500;
            cloudthreey = random.nextInt(380) + 100;
        }
        if (cloudfourx < -120) {
            cloudfourx = 1500;
            cloudfoury = random.nextInt(380) + 100;
        }

        pipeonex -= pipespeed;
        pipetwox -= pipespeed;
        pipethreex -= pipespeed;
        cloudonex -= pipespeed/2;
        cloudtwox -= pipespeed/2;
        cloudthreex -= pipespeed/2;
        cloudfourx -= pipespeed/2;
    }

    public void Jump() {
        gravitynumber = -0.4;
    }

    public void LossCheck() {
        if (pipeonex < 90 && pipeonex > 0) {
            if ((birdyvalue < pipeonehole) || (birdyvalue + 50 > pipeonehole + holesize)) {
                gamescreen = false;
                UpdateScoreboard();
            } else if (checker) {
                score++;
                checker = false;
            }
        }

        if (pipetwox < 90 && pipetwox > 0) {
            if ((birdyvalue < pipetwohole) || (birdyvalue + 50 > pipetwohole + holesize)) {
                gamescreen = false;
                UpdateScoreboard();
            } else if (checker) {
                score++;
                checker = false;
            }
        }

        if (pipethreex < 90 && pipethreex > 0) {
            if ((birdyvalue < pipethreehole) || (birdyvalue + 50 > pipethreehole + holesize)) {
                gamescreen = false;
                UpdateScoreboard();
            } else if (checker) {
                score++;
                checker = false;
            }
        }

        if (birdyvalue > 900) {
            gamescreen = false;
            checker = true;
            UpdateScoreboard();
        }
    }

    public void UpdateScoreboard() {
        if (score > scoreone) {
            enternameheight = 300;
            scorethree = scoretwo;
            scoretwo = scoreone;
            scoreone = score;
            threeone = twoone;
            threetwo = twotwo;
            threethree = twothree;
            twoone = oneone;
            twotwo = onetwo;
            twothree = onethree;
            highscorenumber = 1;
            oneone = '-';
            onetwo = '-';
            onethree = '-';
        } else if (score > scoretwo) {
            enternameheight = 400;
            scorethree = scoretwo;
            scoretwo = score;
            threeone = twoone;
            threetwo = twotwo;
            threethree = twothree;
            highscorenumber = 4;
            twoone = '-';
            twotwo = '-';
            twothree = '-';
        } else if (score > scorethree) {
            enternameheight = 500;
            scorethree = score;
            highscorenumber = 7;
            threeone = '-';
            threetwo = '-';
            threethree = '-';
        }
    }

    public int updatename(char letter) {
        if (!keyProcessed) {
            switch (highscorenumber) {
                case 1 -> { oneone = letter; highscorenumber++; }
                case 2 -> { onetwo = letter; highscorenumber++; }
                case 3 -> { onethree = letter; enternameheight = -100; highscorenumber = 0; }
                case 4 -> { twoone = letter; highscorenumber++; }
                case 5 -> { twotwo = letter; highscorenumber++; }
                case 6 -> { twothree = letter; enternameheight = -100; highscorenumber = 0; }
                case 7 -> { threeone = letter; highscorenumber++; }
                case 8 -> { threetwo = letter; highscorenumber++; }
                case 9 -> { threethree = letter; enternameheight = -100; highscorenumber = 0; }
            }
            setKeyProcessed(true);
        }
        return highscorenumber;
    }

    public boolean isKeyProcessed() {
        return keyProcessed;
    }

    public void setKeyProcessed(boolean keyProcessed) {
        this.keyProcessed = keyProcessed;
    }

    public void Restart() {
        checker = true;
        gamescreen = true;
        birdyvalue = 150.0;
        pipeonex = 1500;
        pipetwox = 2100;
        pipethreex = 2700;
        gravitynumber = gravitynumbervalue;
        pipespeed = pipespeedvalue;
        score = 0;
        Random random = new Random();
        pipeonehole = random.nextInt(380) + 100;
        pipetwohole = random.nextInt(380) + 100;
        pipethreehole = random.nextInt(380) + 100;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gamescreen) {
            g.drawImage(cloudone, (int) cloudonex, (int) cloudoney, null);
            g.drawImage(cloudtwo, (int) cloudtwox, (int) cloudtwoy, null);
            g.drawImage(cloudone, (int) cloudthreex, (int) cloudthreey, null);
            g.drawImage(cloudtwo, (int) cloudfourx, (int) cloudfoury, null);
            g.drawImage(greenery, 0, 450, null);
            g.setColor(new Color(20, 204, 33));
            g.fillRect((int) pipeonex, 0, 40, pipeonehole);
            g.fillRect((int) pipeonex, pipeonehole + holesize, 40, 900);
            g.fillRect((int) pipetwox, 0, 40, pipetwohole);
            g.fillRect((int) pipetwox, pipetwohole + holesize, 40, 900);
            g.fillRect((int) pipethreex, 0, 40, pipethreehole);
            g.fillRect((int) pipethreex, pipethreehole + holesize, 40, 900);
            g.setColor(Color.GREEN);
            if (gravitynumber < 0) {
                g.drawImage(flyingchicken, 30, (int) birdyvalue, null);
            } else {
                g.drawImage(chicken, 30, (int) birdyvalue, null);
            }

            g.setFont(pixelfont);
            g.drawString("Score: " + score, 1100, 50);

            LossCheck();
            Gravity();
        } else {
            g.setColor(Color.BLACK);
            g.setFont(pixelfont.deriveFont(Font.BOLD, 24f));
            g.drawString("Your Score: " + score, 600, 50);
            g.drawString("Enter Name: ", 470, enternameheight);
            g.setColor(Color.WHITE);
            g.drawString("" + oneone + onetwo + onethree + "  " + scoreone, 650, 300);
            g.drawString("" + twoone + twotwo + twothree + "  " + scoretwo, 650, 400);
            g.drawString("" + threeone + threetwo + threethree + "  " + scorethree, 650, 500);
            g.drawString("Press W to Jump and R to reset", 500, 700);
        }
    }
}
