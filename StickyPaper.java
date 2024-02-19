import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * StickyPaper
 * Contains properties for the StickyPaper obstacle sprites
 * @author Liam Allen
 * Course: CS1131
 * Lab: L01
 * 
 */

public class StickyPaper implements Sprite {
    Image stickypaper = null;
    ImageView stickypaperView = null;
    boolean isVisible = false;
    public StickyPaper() {
        try {
            stickypaper = new Image(new FileInputStream("stickypaper.png"));  
            stickypaperView = new ImageView(stickypaper); 
            stickypaperView.setFitHeight(50);
            stickypaperView.setFitWidth(50);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public StickyPaper(double x, double y, boolean isVisible) {
        try {
            stickypaper = new Image(new FileInputStream("stickypaper.png"));  
            stickypaperView = new ImageView(stickypaper); 
            stickypaperView.setFitHeight(50);
            stickypaperView.setFitWidth(50);
            stickypaperView.setX(x);
            stickypaperView.setY(y);
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
        return stickypaperView;
    }

    /*
     * Gets X coord
     * @return double
     */
    public double getX() {
        return stickypaperView.getX();
    }

    /*
     * Gets Y coord
     * @return double
     */
    public double getY() {
        return stickypaperView.getY();
    }

    /*
     * Gets visibility
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
        stickypaperView.setX(x);
    }

    /*
     * Sets Y coord
     * @param y
     */
    public void setY(double y) {
        stickypaperView.setY(y);
    }

    /*
     * Sets width
     * @param width
     */
    public void setWidth(int width) {
        stickypaperView.setFitWidth(width);
    }

    /*
     * Sets height
     * @param height
     */
    public void setHeight(int height) {
        stickypaperView.setFitHeight(height);
    }

    /*
     * Sets visibility
     * @param isVisible
     */
    public void setVisibility(boolean isVisible) {
        this.isVisible = isVisible;
    }
}
