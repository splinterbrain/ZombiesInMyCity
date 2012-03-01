package cc.pq2.zombiesinmycity;

import java.util.ArrayList;

import android.app.Application;
import cc.pq2.zombiesinmycity.models.Base;

public class ZombiesInMyCityApplication extends Application {
	private final ArrayList<Base> bases = new ArrayList<Base>();

	/* (non-Javadoc)
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	public Base[] getBases(){
		return bases.toArray(new Base[bases.size()]);
	}

	public Base getBase(int index){
		return bases.get(index);
	}
	
	public void addBase(Base base){
		bases.add(base);		
	}
}
