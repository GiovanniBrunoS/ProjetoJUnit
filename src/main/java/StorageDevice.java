import java.util.ArrayList;
import java.util.List;

public class StorageDevice {
	
	private String name;
	private int maxSize;
	private int actualSize;
	private List<Folder> subFolders;
	
	public StorageDevice(String name, int size) {
		super();
		this.name = name;
		this.maxSize = size;
		this.actualSize = 0;
		this.subFolders = new ArrayList<>();
	}
	
	public void newFolder(Folder folder) {
		subFolders.add(folder);
		this.setActualSize(this.getActualSize() + folder.getSize());
	}

	public void removeFolder(Folder folder) {
		subFolders.remove(folder);
		folder.setStorage(null);
		this.setActualSize(this.getActualSize() - folder.getSize());
	}

	public String getName() {
		return name;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public int getActualSize() {
		return actualSize;
	}

	public void setActualSize(int actualSize) {
		this.actualSize = actualSize;
	}
	
	public String toString() {
		return "Storage Device: " + this.getName() + ", Actual Size: " + this.getActualSize() + ", Max Size: " + this.getMaxSize() ;
	}
	
	public void onStartUp() {
		System.out.println("Starting Windows");
		for(int i=0;i<100000;i++);
	}
	
	public void structure() {
		System.out.println(this);
		for(Folder folder : subFolders) {
			folder.structure();
		}
	}

}
