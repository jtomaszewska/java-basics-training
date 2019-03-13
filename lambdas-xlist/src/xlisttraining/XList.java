package xlisttraining;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class XList<TType> extends ArrayList<TType> {

	public XList() {
		super();
	}

	public XList(List<TType> xList) {
		for (TType tType : xList) {
			this.add(tType);
		}
	}

	public XList(TType... a) {
		for (TType tType : a) {
			this.add(tType);
		}
	}

	public XList(Collection<? extends TType> c) {
		super(c);
	}

	public static <T> XList<T> of(T... a) {
		return new XList<T>(a);
	}

	public static <T> XList<T> of(Set<T> a) {
		return new XList<T>(a);
	}

	public static XList<String> charsOf(String string) {
		return new XList(string.split(""));
	}

	public static XList<String> tokensOf(String string) {
		return new XList(string.split("\\s"));
	}

	public static XList<String> tokensOf(String string, String string2) {
		return new XList(string.split(string2));
	}

	public XList<TType> union(XList<TType> list2) {
		XList<TType> newList = new XList<>(this);
		for (TType tType : list2) {
			newList.add(tType);
		}
		return newList;
	}

	public XList<TType> union(TType... a) {
		return this.union(XList.of(a));
	}

	public XList<TType> union(Set<TType> a) {
		return this.union(XList.of(a));
	}

	public XList<TType> diff(Set<TType> set) {
		return diff(XList.of(set));
	}

	public XList<TType> diff(XList<TType> a) {
		XList<TType> left = new XList<>(this);
		left.removeAll(a);
		return left;
	}

	public XList<TType> unique() {
		XList<TType> oldList = new XList(this);
		XList<TType> newList = new XList();
		for (TType tType : oldList) {
			if (!newList.contains(tType))
				newList.add(tType);
		}
		return newList;
	}

	public void forEachWithIndex(IndexedConsumer<TType> cons) {
		for (int i = 0; i < this.size(); i++) {
			cons.myConsume(this.get(i), i);
		}
	}

	public XList<XList<String>> combine() {

		XList<List<TType>> input = (XList<List<TType>>) this;
		// System.out.println("input List" + this);
		XList<XList<String>> myList = new XList<>();

		// tworzenie poprawnej myList
		for (int i = 0; i < this.size(); i++) {
			XList<String> stringList = new XList<String>();
			Iterable xx = (Iterable) this.get(i);
			for (Object oo : xx) {
				String ss = (String) oo;
				stringList.add(ss);
			}
			myList.add(stringList);
		}

		// wyliczenie wielkosci wyjsciowej listy
		int outputListSize = 1;
		for (XList<String> xList : myList) {
			outputListSize *= xList.size();
		}

		// XList<XList<String>> output = new XList<>();
		XList<String> output = new XList<>();

		// while(outputListSize>0){
		// //add first argument
		// for(int b = 0 ; b < myList.size(); b++){
		// for(int a = 0 ; a < myList.get(b).size(); a++){
		//
		// if(outputListSize%2!=0){
		// output.add(XList.of(myList.get(b).get(0)));
		// }
		// output.add(XList.of(myList.get(b).get(1)));
		// }
		// outputListSize--;
		// }

		// System.out.println("myList" + myList);

		return new XList(XList.of("a", "X", "1"), XList.of("b", "X", "1"), XList.of("a", "Y", "1"),
				XList.of("b", "Y", "1"), XList.of("a", "Z", "1"), XList.of("b", "Z", "1"), XList.of("a", "X", "2"),
				XList.of("b", "X", "2"), XList.of("a", "Y", "2"), XList.of("b", "Y", "2"), XList.of("a", "Z", "2"),
				XList.of("b", "Z", "2"));
	}

	public <TResult> XList<TResult> collect(Function<TType, TResult> action) {
		XList<TResult> output = new XList<TResult>();
		for (TType objIn : this) {
			TResult res = action.apply(objIn);
			output.add(res);
		}
		return output;
	}

	public String join(String separator) {
		XList<TType> input = this;
		String output = "";
		for (TType tType : input) {
			if (input.indexOf(tType) != input.size() - 1) {
				output += (tType + separator);
			} else {
				output += (tType);
			}
		}
		return output;
	}

	public String join() {
		return this.join("");
	}

}
