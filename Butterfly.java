import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;

/*
 * Butterfly
 * Contains properties for the player's butterfly sprite
 * @author Liam Allen
 * Course: CS1131
 * Lab: L01
 * 
 */

public class Butterfly implements Sprite {
    Image butterfly = null;
    ImageView butterflyView = null;
    boolean isVisible = true;

    public Butterfly() {
        try {
            butterfly = new Image(new FileInputStream("butterfly.png"));  
            butterflyView = new ImageView(butterfly); 
            butterflyView.setFitHeight(50);
            butterflyView.setFitWidth(50);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
     * Gets ImageView
     * @return ImageView
     */
    public ImageView getImageView() {   
        return butterflyView;
    }

    /*
     * Gets X coord
     * @return double
     */
    public double getX() {
        return butterflyView.getX();
    }

    /*
     * Gets Y coord
     * @return double
     */
    public double getY() {
        return butterflyView.getY();
    }

    /*
     * Gets whether butterfly is visible
     * @return boolean
     */
    public boolean isVisible() {
        return isVisible;
    }

    /*
     * Sets X coord
     * @param x
     */
    public void setX(double x) {
        butterflyView.setX(x);
    }

    /*
     * Sets Y coord
     * @param y
     */
    public void setY(double y) {
        butterflyView.setY(y);
    }

    /*
     * Sets image width
     * @param width
     */
    public void setWidth(int width) {
        butterflyView.setFitWidth(width);
    }

    /*
     * Sets image height
     * @param height
     */
    public void setHeight(int height) {
        butterflyView.setFitHeight(height);
    }

    /*
     * Sets visibility
     * @param isVisible
     */
    public void setVisibility(boolean isVisible) {
        this.isVisible = isVisible;
    }
}
