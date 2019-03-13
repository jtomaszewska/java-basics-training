package examplewithgenerics;

import java.util.List;

public class ListCreatorFactory {
	
	public static <TInputListType> ListCreator<TInputListType> create(List<TInputListType> list1) {
		return new ListCreator<TInputListType>(list1);
	}

}
