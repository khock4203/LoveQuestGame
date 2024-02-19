import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.TextArea;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;

/*
 * LoveQuest
 * Main class, contains all graphics and implementation of other classes
 * @author Liam Allen, Kevin Hock, Issak De Ycaza, Cameron Cischke
 * Course: CS1131
 * Lab: L01
 * 
 */

public class LoveQuest extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    private ImageView bgImageView = null;
    private Pane gamePane = null;
    private static Text scoreText;
    private double mouseX = 0;
    private double mouseY = 0;
    private double speedMultiplier = 0;
    private int score = 0;
    private int heartValue = 0;
    private long secondsLeft = 0;
    private boolean hardcore = false;
    private boolean peaceful = true;
    private boolean penaltyActive = false;

    private enum difficulty{UNSELECTED, EASY, MEDIUM, HARD};
    private difficulty difficultySelection = difficulty.UNSELECTED;

    private AnimationTimer animationTimer;

    private static final int timerLength = 60;
    private static long startTime;
    private Text timerText;

    private Scene scene = new Scene(new Pane(), 800, 600);

    public void start(Stage stage) {
        menuScreen();
   
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Lovequest");
        stage.show();
    }

    /*
     * Creates and sets the pane for gameplay, also runs everything happening during the game
     */
    public void gameTime() {
        score = 0;
        gamePane = new Pane();
        addBackground(gamePane);
        penaltyActive = false;

        Heart heart1 = new Heart(Math.random() * 750, Math.random() * 550, false);
        Heart heart2 = new Heart(Math.random() * 750, Math.random() * 550, false);
        Heart heart3 = new Heart(Math.random() * 750, Math.random() * 550, false);
        Heart heart4 = new Heart(Math.random() * 750, Math.random() * 550, false);
        Heart heart5 = new Heart(Math.random() * 750, Math.random() * 550, false);
        Heart heart6 = new Heart(Math.random() * 750, Math.random() * 550, false);
        Heart heart7 = new Heart(Math.random() * 750, Math.random() * 550, false);
        Heart heart8 = new Heart(Math.random() * 750, Math.random() * 550, false);
        Heart heart9 = new Heart(Math.random() * 750, Math.random() * 550, false);

        StickyPaper sp1 = new StickyPaper(Math.random() * 750, Math.random() * 550, false);
        StickyPaper sp2 = new StickyPaper(Math.random() * 750, Math.random() * 550, false);
        StickyPaper sp3 = new StickyPaper(Math.random() * 750, Math.random() * 550, false);
        StickyPaper sp4 = new StickyPaper(Math.random() * 750, Math.random() * 550, false);
        StickyPaper sp5 = new StickyPaper(Math.random() * 750, Math.random() * 550, false);
        StickyPaper sp6 = new StickyPaper(Math.random() * 750, Math.random() * 550, false);
        StickyPaper sp7 = new StickyPaper(Math.random() * 750, Math.random() * 550, false);
        StickyPaper sp8 = new StickyPaper(Math.random() * 750, Math.random() * 550, false);
        StickyPaper sp9 = new StickyPaper(Math.random() * 750, Math.random() * 550, false);

        switch(difficultySelection) {
            case HARD:
                heart7.setVisibility(true);
                heart8.setVisibility(true);
                heart9.setVisibility(true);
                gamePane.getChildren().addAll(heart7.getImageView(), heart8.getImageView(), heart9.getImageView());
                if(!peaceful) {
                    sp7.setVisibility(true);
                    sp8.setVisibility(true);
                    sp9.setVisibility(true);
                    gamePane.getChildren().addAll(sp7.getImageView(), sp8.getImageView(), sp9.getImageView());
                }

                startAnimation(heart7);
                animationTimer.start();
                startAnimation(heart8);
                animationTimer.start();
                startAnimation(heart9);
                animationTimer.start();
                startAnimation(sp7);
                animationTimer.start();
                startAnimation(sp8);
                animationTimer.start();
                startAnimation(sp9);
                animationTimer.start();
            case MEDIUM:
                heart4.setVisibility(true);
                heart5.setVisibility(true);
                heart6.setVisibility(true);
                gamePane.getChildren().addAll(heart4.getImageView(), heart5.getImageView(), heart6.getImageView());
                if(!peaceful) {
                    sp4.setVisibility(true);
                    sp5.setVisibility(true);
                    sp6.setVisibility(true);
                    gamePane.getChildren().addAll(sp4.getImageView(), sp5.getImageView(), sp6.getImageView());
                }

                startAnimation(heart4);
                animationTimer.start();
                startAnimation(heart5);
                animationTimer.start();
                startAnimation(heart6);
                animationTimer.start();
                startAnimation(sp4);
                animationTimer.start();
                startAnimation(sp5);
                animationTimer.start();
                startAnimation(sp6);
                animationTimer.start();
            case EASY:
                heart1.setVisibility(true);
                heart2.setVisibility(true);
                heart3.setVisibility(true);
                gamePane.getChildren().addAll(heart1.getImageView(), heart2.getImageView(), heart3.getImageView());

                if(!peaceful) {
                    sp1.setVisibility(true);
                    sp2.setVisibility(true);
                    sp3.setVisibility(true);
                    gamePane.getChildren().addAll(sp1.getImageView(), sp2.getImageView(), sp3.getImageView());
                }

                startAnimation(heart1);
                animationTimer.start();
                startAnimation(heart2);
                animationTimer.start();
                startAnimation(heart3);
                animationTimer.start();
                startAnimation(sp1);
                animationTimer.start();
                startAnimation(sp2);
                animationTimer.start();
                startAnimation(sp3);
                animationTimer.start();
            default:
                break;
        }

        Butterfly player = new Butterfly();
        gamePane.getChildren().add(player.getImageView());

        //timer stuff
        timerText = new Text(670, 40, "TEST");
        timerText.setFont(new Font(34));
        timerText.setStroke(Color.rgb(8, 19, 61));
        timerText.setFill(Color.WHITE);
        timerText.setTextAlignment(TextAlignment.CENTER);
        gamePane.getChildren().add(timerText);

        //score stuff
        scoreText = new Text(657.5, 120, "SCORE\n0");
        scoreText.setFont(new Font(34));
        scoreText.setStroke(Color.rgb(20, 19, 61));
        scoreText.setFill(Color.WHITE);
        scoreText.setTextAlignment(TextAlignment.CENTER);
        gamePane.getChildren().add(scoreText);

        scene.setOnMouseMoved(event -> {
            if(!penaltyActive) {
                mouseX = event.getSceneX();
                mouseY = event.getSceneY();

                player.setX(mouseX - 25);
                player.setY(mouseY - 25);

                heartCollision(player, heart1);
                heartCollision(player, heart2);
                heartCollision(player, heart3);
                heartCollision(player, heart4);
                heartCollision(player, heart5);
                heartCollision(player, heart6);
                heartCollision(player, heart7);
                heartCollision(player, heart8);
                heartCollision(player, heart9);

                if(!peaceful) {
                    stickyPaperCollision(player, sp1);
                    stickyPaperCollision(player, sp2);
                    stickyPaperCollision(player, sp3);
                    stickyPaperCollision(player, sp4);
                    stickyPaperCollision(player, sp5);
                    stickyPaperCollision(player, sp6);
                    stickyPaperCollision(player, sp7);
                    stickyPaperCollision(player, sp8);
                    stickyPaperCollision(player, sp9);
                }

                if(!heart1.isVisible() && !heart2.isVisible() && !heart3.isVisible()) {
                    if(difficultySelection == difficulty.EASY) {
                        gameOver();
                    } else if(!heart4.isVisible() && !heart5.isVisible() && !heart6.isVisible()) {
                        if(difficultySelection == difficulty.MEDIUM) {
                            gameOver();
                        } else if(!heart7.isVisible() && !heart8.isVisible() && !heart9.isVisible()) {
                            gameOver();
                        }
                    }
                }
            } 
        });

        timer();
        scene.setCursor(Cursor.NONE);
        scene.setRoot(gamePane);
    }

    /*
     * Creates and sets the pane for the main menu
     */
    public void menuScreen() {
        Pane menuPane = new Pane();
        difficultySelection = difficulty.UNSELECTED;
        addBackground(menuPane);
        try{
            Image logo = new Image(new FileInputStream("lovequest.png"));
            ImageView logoIV = new ImageView(logo);
            logoIV.setFitWidth(400);
            logoIV.setFitHeight(300);
            logoIV.setX(200);
            logoIV.setY(50);
            menuPane.getChildren().add(logoIV);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        Button playButton = new Button("START");
        playButton.setOnAction(event -> {
            difficultySelect();
        });
        playButton.setLayoutX(300);
        playButton.setLayoutY(350);
        
        Button creditsButton = new Button("Credits");
        creditsButton.setOnAction(event -> {
            creditsScreen();
        });
        creditsButton.setLayoutX(440);
        creditsButton.setLayoutY(350);

        Text description = new Text("Try to collect all the hearts as fast as possible!");
        description.setX(225);
        description.setY(580);
        description.setFont(new Font(18));

        menuPane.getChildren().addAll(playButton, creditsButton, description);
        scene.setRoot(menuPane);
    }

    /*
     * Contains and creates the pane for the credits screen
     */
    public void creditsScreen() {
        Pane creditsPane = new Pane();
        addBackground(creditsPane);
        Text text = new Text();
        Button buttonBack = new Button("Back");
        buttonBack.setOnAction(event -> {
            menuScreen();
        });
        buttonBack.setLayoutY(500);
        buttonBack.setLayoutX(380);
        text = new Text("Created By"
                + "\n" + "\n" + "Cameron Cischke"
                + "\n" + "\n" + "Liam Allen"
                + "\n" + "\n" + "Isaak De Ycaza"
                + "\n" + "\n" +"Kevin Hock" );
        text.setX(325);
        text.setY(150);
        text.setFont(new Font(20));
        text.textAlignmentProperty().set(TextAlignment.CENTER);
        creditsPane.getChildren().addAll(buttonBack, text);
        scene.setRoot(creditsPane);
    }

    /*
     * Creates and contains the pane for the difficulty select page
     */
    public void difficultySelect() {
        Pane difficultySelectPane = new Pane();
        addBackground(difficultySelectPane);
        hardcore = false;
        peaceful = false;

        Button easyButton = new Button("EASY");
        Button mediumButton = new Button("MEDIUM");
        Button hardButton = new Button("HARD");
        Button hardcoreButton = new Button("HARDCORE MODE OFF");
        Button peacefulButton = new Button("PEACEFUL MODE OFF");
        Button backButton = new Button("Back");

        easyButton.setLayoutX(213);
        easyButton.setLayoutY(400);
        easyButton.setMinWidth(75);
        mediumButton.setLayoutX(363);
        mediumButton.setLayoutY(400);
        mediumButton.setMinWidth(75);
        hardButton.setLayoutX(513);
        hardButton.setLayoutY(400);
        hardButton.setMinWidth(75);
        hardcoreButton.setLayoutX(220-15);
        hardcoreButton.setLayoutY(450);
        peacefulButton.setLayoutX(435-15);
        peacefulButton.setLayoutY(450);
        backButton.setLayoutX(363);
        backButton.setLayoutY(500);
        backButton.setMinWidth(75);

        easyButton.setOnAction(event -> {
            difficultySelection = difficulty.EASY;
            speedMultiplier = 1;
            heartValue = 10;
            gameTime();
        });

        mediumButton.setOnAction(event -> {
            difficultySelection = difficulty.MEDIUM;
            speedMultiplier = 2;
            heartValue = 8;
            gameTime();
        });

        hardButton.setOnAction(event -> {
            difficultySelection = difficulty.HARD;
            speedMultiplier = 5;
            heartValue = 6;
            gameTime();
        });

        hardcoreButton.setOnAction(event -> {
            toggleHardcore();
            if(hardcore) {
                hardcoreButton.setText("HARDCORE MODE ON");
            } else {
                hardcoreButton.setText("HARDCORE MODE OFF");
            }
        });

        peacefulButton.setOnAction(event -> {
            togglePeaceful();
            if(peaceful) {
                peacefulButton.setText("PEACEFUL MODE ON");
            } else {
                peacefulButton.setText("PEACEFUL MODE OFF");
            }
        });

        backButton.setOnAction(event -> {
            menuScreen();
        });

        Heart h1 = new Heart(225, 330, false);
        Heart h2 = new Heart(375-8, 330-5, false);
        Heart h3 = new Heart(375+8, 330+5, false);
        Heart h4 = new Heart(525-10, 330-10, false);
        Heart h5 = new Heart(525, 330, false);
        Heart h6 = new Heart(525+10, 330+10, false);

        difficultySelectPane.getChildren().addAll(easyButton, mediumButton, hardButton, hardcoreButton, peacefulButton, backButton, 
                h1.getImageView(), h2.getImageView(), h3.getImageView(), h4.getImageView(), h5.getImageView(), h6.getImageView());
        scene.setRoot(difficultySelectPane);
    }

    /*
     * Creates and contains everything for the pane of the game over/high score page
     */
    public void gameOver() {
        penaltyActive = true;
        Pane gameOverPane = new Pane();
        addScore((int) secondsLeft);
        if(peaceful) {
            score /= 2;
        } else if(hardcore) {
            score += 30;
        }
        addBackground(gameOverPane);
        scene.setCursor(Cursor.DEFAULT);

        Scoreboard board = new Scoreboard();
        board.getFileBoard();
        Text header = new Text();
        Text display = new Text();
        Text yourScore = new Text();

        yourScore.setText("Score: " + score);
        yourScore.setLayoutX(348);
        yourScore.setLayoutY(500);
        yourScore.setFont(new Font(30));
        
        TextArea input = new TextArea();
        input.setLayoutX(358);
        input.setLayoutY(400);
        input.setMaxWidth(100);
        input.setMaxHeight(1);

        Text error = new Text();
        error.setText("Invalid input, please enter three characters without spaces");
        error.setVisible(false);
        error.setFill(Color.RED);
        error.setX(250);
        error.setY(590);
        
        Button enterButton = new Button("Enter");
        enterButton.setLayoutX(385);
        enterButton.setLayoutY(445);

        Button playAgainButton = new Button("Play Again");
        playAgainButton.setLayoutX(373);
        playAgainButton.setLayoutY(400);
        playAgainButton.setVisible(false);

        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setLayoutX(371);
        mainMenuButton.setLayoutY(440);
        mainMenuButton.setVisible(false);

        enterButton.setOnAction(event -> {
            if(input.getText().length() != 3 || input.getText().contains(" ")) {
                error.setVisible(true);
            } else {
                board.updateBoard(input.getText().toUpperCase(), score);
                display.setText(board.getBoard());
                input.setVisible(false);
                enterButton.setVisible(false);
                error.setVisible(false);
                playAgainButton.setVisible(true);
                mainMenuButton.setVisible(true);
            }
        });

        playAgainButton.setOnAction(event -> {
            gameTime();
        });

        mainMenuButton.setOnAction(event -> {
            menuScreen();
        });

        header.setText("High Scores");
        header.setX(280);
        header.setY(100);
        header.setFont(new Font(50));

        display.setText(board.getBoard());
        display.setX(350);
        display.setY(200);
        display.setTextAlignment(TextAlignment.CENTER);
        display.setFont(new Font(30));

        gameOverPane.getChildren().addAll(header, display, enterButton, input, error, mainMenuButton, playAgainButton, yourScore);
        scene.setRoot(gameOverPane);
    }
    
    /*
     * Sets the background image, used when creating each pane
     * @param pane
     */
    public void addBackground(Pane pane) {
        try {
            Image backgroundImage = null;
            if(difficultySelection == difficulty.HARD) {
                backgroundImage = new Image(new FileInputStream("background3.png"));  
            } else if(difficultySelection == difficulty.MEDIUM) {
                backgroundImage = new Image(new FileInputStream("background2.png"));
            } else {
                backgroundImage = new Image(new FileInputStream("background1.png"));
            }
            
            bgImageView = new ImageView(backgroundImage); 
            bgImageView.setFitWidth(800);
            bgImageView.setFitHeight(600);
            pane.getChildren().add(bgImageView);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }  

    /*
     * Freezes player for 2 seconds when they touch stickypaper, or ends the game in hardcore
     * @param player
     * @param sp
     */
    public void stickyPaperCollision(Butterfly player, StickyPaper sp) {
        if (player.getX() + 42 >= sp.getX() && player.getX() + 8 <= sp.getX() + 50 && player.getY() + 42 >= sp.getY() && player.getY() + 8 <= sp.getY() + 50 && sp.isVisible()) {
            if(!hardcore) {
                penaltyActive = true;
                long startTime = System.currentTimeMillis();
                int mouseLockX = (int) Math.round(MouseInfo.getPointerInfo().getLocation().getX());
                int mouseLockY = (int) Math.round(MouseInfo.getPointerInfo().getLocation().getY());
        
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(event -> {
                    try{
                        Robot zeus = new Robot();

                        if(System.currentTimeMillis() - startTime >= 2000) {
                            sp.setX(3620);
                            zeus.mouseMove(mouseLockX, mouseLockY);
                            penaltyActive = false;
                            sp.setVisibility(false);
                        }
                    } catch(AWTException e) {
                        e.printStackTrace();
                    }
                });
                pause.play();
            } else {
                gameOver();
            }
        }
    }

    /*
     * Removes hearts and adds score when player touches hearts
     * @param player
     * @param heart
     */
    public void heartCollision(Butterfly player, Heart heart) {
        if(player.getX() + 42 >= heart.getX() && player.getX() + 8 <= heart.getX() + 50 && player.getY() + 42 >= heart.getY() && player.getY() + 8 <= heart.getY() + 50) {
            addScore(heartValue);
            heart.setX(3620);
            heart.setVisibility(false);
        }
    }

    /*
     * Setup/execute the timer displayed during gameplay
     */
    public void timer() {
        startTime = System.currentTimeMillis();
        Timer timer = new Timer(true);

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
                secondsLeft = timerLength - elapsedTime;

                if (secondsLeft > 0) {
                    timerText.setText("TIME\n" + secondsLeft);
                } else {
                    if(scene.getRoot() == gamePane) {
                        gameOver();
                    }
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }

    /*
     * Turn hardcore on/off
     */
    public void toggleHardcore() {
        hardcore = !hardcore;
    }

    /*
     * Turn peaceful on/off
     */
    public void togglePeaceful() {
        peaceful = !peaceful;
    }

    /*
     * Adds a given amount to the score
     * @param amount
     */
    public void addScore(int amount) {
        score += amount;
        scoreText.setText("SCORE\n"+amount);
    }

    /*
     * gets random number for the sprite speed
     * @return double
     */
    public double getRandomSpeed() {
        return new Random().nextDouble(); // *0.5
    }

    /*
     * Movement for hearts and stickypaper during gameplay
     * @param sprite
     */
    public void startAnimation(Sprite sprite) {
        animationTimer = new AnimationTimer() {
            double xSpeed = getRandomSpeed()*(Math.random() > 0.5 ? 1 : -1);
            double ySpeed = getRandomSpeed()*(Math.random() > 0.5 ? 1 : -1);
            @Override
            public void handle(long now) {
                // Occasionally change speed and direction
                if (Math.random() < 0.01) {
                    xSpeed = speedMultiplier * getRandomSpeed()*(Math.random() > 0.5 ? 1 : -1);
                    ySpeed = speedMultiplier * getRandomSpeed()*(Math.random() > 0.5 ? 1 : -1);
                }

                if(sprite.isVisible()) {
                    // Wrap around when reaching the end of the window
                    if (sprite.getX() > 810 ) {
                        sprite.setX(-100);
                    }
                    if (sprite.getY() > 610) {
                        sprite.setY(-100 );
                    }
                    if (sprite.getX() < -100 ) {
                        sprite.setX(810);
                    }
                    if (sprite.getY() < -100 ) {
                        sprite.setY(610);
                    }

                    // Update the object's position
                    sprite.setX(sprite.getX() + xSpeed);
                    sprite.setY(sprite.getY() + ySpeed);
                }
            }
        };
    }
}