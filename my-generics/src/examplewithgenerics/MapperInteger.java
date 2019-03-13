package examplewithgenerics;

public class MapperInteger implements Mapper<Integer, Integer> {

	@Override
	public Integer mapEvery(Integer arg) {
		return arg+10;
	}

}
