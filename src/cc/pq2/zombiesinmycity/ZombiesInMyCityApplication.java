package cc.pq2.zombiesinmycity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import android.app.Application;
import android.content.Context;
import cc.pq2.zombiesinmycity.models.Base;
import cc.pq2.zombiesinmycity.models.Mission;
import cc.pq2.zombiesinmycity.models.MissionType;

public class ZombiesInMyCityApplication extends Application {
	private final ArrayList<Base> bases = new ArrayList<Base>();
	private final ArrayList<MissionType> missionTypes = new ArrayList<MissionType>(); 
	private Mission currentMission;
	
	/* (non-Javadoc)
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		
		missionTypes.add(new MissionType("Retrieve food", "groceries"));
		missionTypes.add(new MissionType("Stock weapons", "hardware"));
		
		File file = getApplicationContext().getFileStreamPath("bases.dat");
		if(false && file.exists()){
			try {
				FileInputStream fis = openFileInput("bases.dat");
				ObjectInputStream ois = new ObjectInputStream(fis);
				Object savedBases[] = (Object[])ois.readObject();
				for(Object base : savedBases){
//					this.addBase((Base)base);
					this.bases.add((Base)base);
				}
				ois.close();
				fis.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (StreamCorruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassCastException e){
				e.printStackTrace();
			}
		}
	}
	
	public Base[] getBases(){
		return bases.toArray(new Base[bases.size()]);
	}

	public Base getBase(int index){
		return bases.get(index);
	}
	
	public void addBase(Base base){
		bases.add(base);
		
		//Resave bases
		try {
			FileOutputStream fos = openFileOutput("bases.dat", Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(bases.toArray());
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Mission getCurrentMission() {
		return currentMission;
	}

	public void setCurrentMission(Mission currentMission) {
		this.currentMission = currentMission;
	}
}
