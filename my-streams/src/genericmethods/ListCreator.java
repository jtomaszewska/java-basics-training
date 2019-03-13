package genericmethods;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator<T> {

	private List<T> list;

	public ListCreator(List<T> list1) {
		this.list = list1;
	}

	public static <T2> ListCreator<T2> collectFrom(List<T2> list) {
		return new ListCreator<T2>(list);
	}

	public ListCreator<T> when(Predicate<T> function) {
		ArrayList<T> result = new ArrayList<>();
		for (T t : this.list) {
			if (function.test(t)) {
				result.add(t);
			}

		}
		ListCreator<T> lCResult = new ListCreator<>(result);
		return lCResult;
	}

	public <TRes> List<TRes> mapEvery(Function<T, TRes> mapper) {
		List<TRes> result = new ArrayList<>();
		for (T t : this.list) {
			result.add(mapper.apply(t));
		}
		return result;
	}
}
