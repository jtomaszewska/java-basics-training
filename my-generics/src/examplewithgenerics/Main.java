/**
 *
 *  @author Tomaszewska Justyna S15313
 *
 */

package examplewithgenerics;



import java.util.*;

public class Main {
  public Main() {
    List<Integer> src1 = Arrays.asList(1, 7, 9, 11, 12);
    System.out.println(test1(src1));

    List<String> src2 = Arrays.asList("a", "zzzz", "vvvvvvv" );
    System.out.println(test2(src2));
  }

  public List<Integer> test1(List<Integer> src) {
    //Selector<Integer> sel = new SelectorInteger();
    Mapper<Integer, Integer> map = new MapperInteger(); 
    return ListCreatorFactory.create(src).when(e -> e < 10 /*sel*/).mapEvery(map);
  }

  public List<Integer> test2(List<String> src) {
    BooleanFunction<String> sel = new SelectorString(); 
    Mapper<String, Integer> map = new MapperString();  
    return ListCreatorFactory.create(src).when(sel).mapEvery(map);
  }

  public static void main(String[] args) {
    new Main();
  }
}
