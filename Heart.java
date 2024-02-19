import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * Heart
 * Contains properties for the Heart collectable sprites
 * @author Liam Allen
 * Course: CS1131
 * Lab: L01
 * 
 */

public class Heart implements Sprite {
    Image heart = null;
    ImageView heartView = null;
    boolean isVisible = false;

    public Heart() {
        try {
            heart = new Image(new FileInputStream("heart.png"));  
            heartView = new ImageView(heart); 
            heartView.setFitHeight(50);
            heartView.setFitWidth(50);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Heart(double x, double y, boolean isVisible) {
        try {
            heart = new Image(new FileInputStream("heart.png"));  
            heartView = new ImageView(heart); 
            heartView.setFitHeight(50);
            heartView.setFitWidth(50);
            heartView.setX(x);
            heartView.setY(y);
            this.isVisible = isVisible;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
     * Gets ImageView
     * @return ImageView
     */
    public ImageView getImageView() {   
        return heartView;
    }

    /*
     * Gets X coord
     * @return double
     */
    public double getX() {
        return heartView.getX();
    }

    /*
     * Gets Y coord
     * @return double
     */
    public double getY() {
        return heartView.getY();
    }

    /*
     * Gets visibility
     * @return boolean
     */
    public boolean isVisible() {
        return this.isVisible;
    }

    /*
     * Sets X coord
     * @param x
     */
    public void setX(double x) {
        heartView.setX(x);
    }

    /*
     * Sets Y coord
     * @param y
     */
    public void setY(double y) {
        heartView.setY(y);
    }

    /*
     * Sets width
     * @param width
     */
    public void setWidth(int width) {
        heartView.setFitWidth(width);
    }

    /*
     * Sets height
     * @param height
     */
    public void setHeight(int height) {
        heartView.setFitHeight(height);
    }

    /*
     * Sets visibility
     * @param isVisible
     */
    public void setVisibility(boolean isVisible) {
        this.isVisible = isVisible;
    }
}
