import javafx.scene.image.ImageView;

/*
 * Sprite
 * Contains properties for all sprites
 * @author Liam Allen
 * Course: CS1131
 * Lab: L01
 * 
 */

public interface Sprite {
    //get methods
    ImageView getImageView();
    double getX();
    double getY();
    boolean isVisible();

    //set methods
    void setX(double x);
    void setY(double y);
    void setWidth(int width);
    void setHeight(int height);
    void setVisibility(boolean isVisible);
}
