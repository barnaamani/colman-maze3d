package algorithms.mazeGenerators;

import java.util.List;

public interface IPickCellStrategy {
	Position Pick(List<Position> c);
}
