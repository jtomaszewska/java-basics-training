/**
 *
 *  @author Tomaszewska Justyna S15313
 *
 */

package examplewithgenerics;

public interface Mapper<TArg, TRes> {

	public TRes mapEvery(TArg arg);
}
