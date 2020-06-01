import java.util.ArrayList;
import java.util.List;

public class Folder {
	
	private String name;
	private int size;
	private Folder parentFolder;
	private StorageDevice storage;
	private List<Folder> subFolders;
	private List<File> files;
		
	public Folder(String name, StorageDevice storage) throws Exception {
		super();
		this.name = name;
		this.size = 1;
		this.subFolders = new ArrayList<>();
		this.files = new ArrayList<>();
		this.storage = storage;
		if (size + storage.getActualSize() > storage.getMaxSize())
			throw new Exception();
		storage.newFolder(this);
	}
	
	public Folder(String name, Folder parentFolder) throws Exception {
		super();
		this.name = name;
		this.size = 1;
		this.subFolders = new ArrayList<>();
		this.files = new ArrayList<>();
		this.parentFolder = parentFolder;
		this.storage = parentFolder.getStorage();
		if (size + storage.getActualSize() > storage.getMaxSize())
			throw new Exception();
		parentFolder.newFolder(this);
	}

	
	public void newFolder(Folder folder) {
		subFolders.add(folder);
		this.setSize(this.getSize() + folder.getSize());
		notifyParentNew(folder.getSize());
	}
	
	public void remove() {
		parentFolder.subFolders.remove(this);
		parentFolder.setSize(parentFolder.getSize() - this.getSize());
		parentFolder.notifyParentRemove(this.getSize());
		this.setParentFolder(null);
	}
	
	public void newFile(File file) {
		files.add(file);
		this.setSize(this.getSize() + file.getSize());
		notifyParentNew(file.getSize());
	}

	public void removeFile(File file) {
		files.remove(file);
		file.setParentFolder(null);
		this.setSize(this.getSize() - file.getSize());
		notifyParentRemove(file.getSize());
	}
	
	public void notifyParentNew(int size) {
		if(parentFolder != null) {
			parentFolder.setSize(parentFolder.getSize() + size);
			parentFolder.notifyParentNew(size);
		}else {
			storage.setActualSize(storage.getActualSize() + size);
		}
	}
	
	public void notifyParentRemove(int size) {
		if(parentFolder != null) {
			parentFolder.setSize(parentFolder.getSize() - size);
			parentFolder.notifyParentRemove(size);
		}else {
			storage.setActualSize(storage.getActualSize() - size);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Folder> getSubFolders() {
		return subFolders;
	}

	public List<File> getFiles() {
		return files;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Folder getParentFolder() {
		return parentFolder;
	}

	public void setParentFolder(Folder parentFolder) {
		this.parentFolder = parentFolder;
	}

	public StorageDevice getStorage() {
		return storage;
	}
	
	public void setStorage(StorageDevice storage) {
		this.storage = storage;
	}
	
	public String toString() {
		return   "Folder: " + this.getName() + ", Parent: " + (this.parentFolder == null ? this.getStorage().getName() : this.getParentFolder().getName()) + ", Size: " + this.getSize();
	}
	
	public void structure() {
		System.out.println(this);
		if(files.size() > 0)
			for(File file: files)
				System.out.println(file);
		if(subFolders.size()>0) 
			for(Folder folder: subFolders) 
				folder.structure();
	}
			
}
