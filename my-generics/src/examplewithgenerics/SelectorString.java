package examplewithgenerics;

public class SelectorString implements BooleanFunction<String> {

	@Override
	public Boolean isSatisfied(String arg) {
		return arg.length()>3;
	}

}
