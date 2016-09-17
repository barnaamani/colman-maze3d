package algorithms.mazeGenerators;

import java.util.List;
import java.util.Random;

public class PickRandomCell implements IPickCellStrategy {

	@Override
	public Position Pick(List<Position> c) {
		Random rand = new Random();
		return c.get(rand.nextInt(c.size()));
	}

}
