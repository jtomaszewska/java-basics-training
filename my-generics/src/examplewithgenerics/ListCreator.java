/**
 *
 *  @author Tomaszewska Justyna S15313
 *
 */

package examplewithgenerics;

import java.util.ArrayList;
import java.util.List;

public class ListCreator<T> {

	private List<T> list;

	public ListCreator(List<T> list1) {
		this.list = list1;
	}

	public ListCreator<T> when(BooleanFunction<T> function) {
		ArrayList<T> result = new ArrayList<>();
		for (T t : this.list) {
			if (function.isSatisfied(t)) {
				result.add(t);
			}

		}
		ListCreator<T> lCResult = new ListCreator<>(result);
		return lCResult;
	}

	public <TRes> List<TRes> mapEvery(Mapper<T, TRes> mapper) {
		List<TRes> result = new ArrayList<>();
		for (T t : this.list) {
			result.add(mapper.mapEvery(t));
		}
		return result;
	}

}
