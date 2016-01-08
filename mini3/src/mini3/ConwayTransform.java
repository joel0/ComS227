package mini3;

/**
 * Transformation implementing Conway's Game of Life. All cells have value 0 or 1. The new value is
 * based on the center cell along with the sum S of its eight neighbors, according to the rules:
 * <ul>
 * <li>if S < 2 the result is 0
 * <li>if the center cell is 1 and S is 2 or 3, the result is 1
 * <li>if S > 3 the result is 0
 * <li>if the center cell is 0 and S is exactly 3, the result is 1
 * </ul>
 * See http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
 *
 * <p>
 * The radius is 1 and the wrapping behavior is true.
 */
public class ConwayTransform implements ITransform {

	@Override
	public int apply(int[][] elements) {
		if (elements.length != 2 * getRadius() + 1 || elements[0].length != 2 * getRadius() + 1) {
			throw new IllegalArgumentException();	
		}
		int sum = 0;
		int centerCellValue = elements[(elements.length - 1) / 2][(elements[0].length - 1) / 2];
		for (int row = 0; row < elements.length; row++) {
			for (int col = 0; col < elements[0].length; col++) {
				if (!(row == (elements.length - 1) / 2 && col == (elements[0].length - 1) / 2)) {
					sum += elements[row][col];
				}
			}
		}
		// for (int row = 0; row < elements.length; row++) {
		// for (int col = 0; col < elements[row].length; col++) {
		// System.out.print(elements[row][col]);
		// }
		// System.out.println();
		// }
		// System.out.println("sum: " + sum);
		if (sum < 2 || sum > 3) {
			return 0;
		} else {
			if (centerCellValue == 1) {
				return 1;
			} else if (centerCellValue == 0 && sum == 3) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	@Override
	public int getRadius() {
		return 1;
	}

	@Override
	public boolean isWrapped() {
		return true;
	}

}
