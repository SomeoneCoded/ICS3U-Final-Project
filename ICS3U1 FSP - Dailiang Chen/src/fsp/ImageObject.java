package fsp;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageObject {
	
	BufferedImage shape;
	Color color;
	int size;
	
	public ImageObject(BufferedImage shape, Color color, int size) {
		this.shape = shape;
		this.color = color;
		this.size = size;
	}

	public BufferedImage getShape() {
		return shape;
	}

	public void setShape(BufferedImage shape) {
		this.shape = shape;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "ImageObject [shape=" + shape + ", color=" + color + ", size=" + size + "]";
	}
	
	
}
