package mini3;

/**
 * Transformation implementing a smoothing operation on cells of an array. The new value is the
 * average of the values in a neighborhood around a given cell, rounded to the nearest integer. The
 * size of the neighborhood is 2 * radius + 1, where the radius is a parameter provided to the
 * constructor. Values are not wrapped.
 */
public class SmoothingTransform implements ITransform {
	private int radius;

	public SmoothingTransform(int givenRadius) {
		radius = givenRadius;
	}

	@Override
	public int apply(int[][] elements) {
		int sum = 0;
		int count = elements.length * elements[0].length;
		for (int row = 0; row < elements.length; row++) {
			for (int col = 0; col < elements[0].length; col++) {
				sum += elements[row][col];
			}
		}
		return sum / count;
	}

	@Override
	public int getRadius() {
		return radius;
	}

	@Override
	public boolean isWrapped() {
		return false;
	}

}
