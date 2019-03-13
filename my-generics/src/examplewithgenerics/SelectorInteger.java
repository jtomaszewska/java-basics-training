package examplewithgenerics;

public class SelectorInteger implements BooleanFunction<Integer> {

	@Override
	public Boolean isSatisfied(Integer arg) {
		return arg<10;
	}

}
