package examplewithgenerics;

public class MapperString implements Mapper<String, Integer> {

	@Override
	public Integer mapEvery(String arg) {
		return arg.length()+10;
	}

}
