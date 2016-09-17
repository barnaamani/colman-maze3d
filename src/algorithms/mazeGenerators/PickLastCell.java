package algorithms.mazeGenerators;

import java.util.List;

public class PickLastCell implements IPickCellStrategy{

	@Override
	public Position Pick(List<Position> c) {
		return c.get(c.size()-1);
	}
}
