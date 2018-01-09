/**
 * Program #3a
 * Convert a JPG image into its grayscale, red, green, and blue converted form
 * CS108-2
 * 3/4/2016
 * @author Steven Duong
 */

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Prog3a {
	private BufferedImage img = null;
	private File file = null;
	private String fileName;
	private String newName;

public Prog3a() {										//default constructor
	
}
public Prog3a(String fileName) {						//explicit value constructor
	this.fileName = fileName;
}
public void readImage() {
/**
 * Read in image file into img variable
 */	
	//check if file can be read
	try {
		file = new File(fileName);
		if(file.canRead())
			img = ImageIO.read(file);
		else												//if file cant be read, throw the exception
			throw new IOException("Cannot read file: " + file.getPath());
	}
	catch (IOException e) {
		System.out.println(e);
		System.exit(0);
	}
}

public void writeImage() {
/**
 * Write img to file
 */
	File outputFile = null;
	try {
		outputFile = new File(newName);								//create a new file using a new name
		ImageIO.write( img, "jpg", outputFile );					//load the new image into the new file
	}
	catch (IOException e) { 
		System.out.println(e);
	}
}

public void test(int x, int y) {
/**
 * Display RGB value (omit transparency)
 */
	int pixel = img.getRGB(x, y);
	
	int red = (pixel >> 16) & 0xff;
	int green = (pixel >> 8) & 0xff;
	int blue = pixel & 0xff;
	
	System.out.println("RGB: " + red + ", " + green + ", " + blue);
}

public void toGrayscale() {
/**
 * Convert each pixel to gray by taking the average of red, blue, and green
 */
	int height = img.getHeight();
	int width = img.getWidth();
	
    for(int y = 0; y < height; y++){
        for(int x = 0; x < width; x++){
            int pixel = img.getRGB(x, y);									//get the image pixels			
            
            int trans = (pixel >> 24) & 0xff;								
            int red = (pixel >> 16) & 0xff;
            int green = (pixel >> 8) & 0xff;
            int blue = pixel & 0xff;
            
            int avg = (red + green + blue) / 3;
            pixel = (trans << 24) | (avg << 16) | (avg << 8) | (avg);		//convert each pixel to gray
            img.setRGB(x, y, pixel);
        }
    }
    
    this.newName = appendColorToOutput("Gray");
}

public void toRed() {
/**
 * Convert each pixel in img to red, masking out green and blue
 */
	int height = img.getHeight();						//get the size of image
	int width = img.getWidth();
	
    for(int y = 0; y < height; y++){
        for(int x = 0; x < width; x++){
            int pic = img.getRGB(x,y);
            pic = pic & 0xffff0000;						//red
            img.setRGB(x, y, pic); 
        }
    }
    
    this.newName = appendColorToOutput("Red");
}

public void toGreen() {
/**
 * Convert each pixel in img to green, masking out red and blue
 */
	int height = img.getHeight();						//get the size of image
	int width = img.getWidth();
	
    for(int y = 0; y < height; y++){
        for(int x = 0; x < width; x++){
            int pic = img.getRGB(x,y);
            pic = pic & 0xff00ff00;						//green
            img.setRGB(x, y, pic);
        }
    }
    
    this.newName = appendColorToOutput("Green");
}

public void toBlue() {
/**
 * Convert each pixel in img to blue, masking out red and green
 */
	int height = img.getHeight();						//get the size of image
	int width = img.getWidth();
	
    for(int y = 0; y < height; y++) {
        for(int x = 0; x < width; x++) {
            int pic = img.getRGB(x,y);
            pic = pic & 0xff0000ff;						//blue
            img.setRGB(x, y, pic);
        }
    }
    
    this.newName = appendColorToOutput("Blue");
}

private String appendColorToOutput(String colorName) {
/**
 * Return output file name with color appended before ".jpg"
 */
	return this.fileName.replace(".jpg", colorName) + ".jpg";
}

}