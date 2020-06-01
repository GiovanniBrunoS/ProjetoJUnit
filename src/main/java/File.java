
public class File {
	
	private String name;
	private int size;
	private Folder parentFolder;
	private FileExtension extension;
	
	public File(String name, int size, FileExtension extension, Folder parentFolder) throws Exception {
		super();
		this.name = name;
		this.size = size;
		this.extension = extension;
		this.parentFolder = parentFolder;
		if (size + parentFolder.getStorage().getActualSize() > parentFolder.getStorage().getMaxSize())
			throw new Exception();
		parentFolder.newFile(this);
	}
	
	public void removeFile() {
		parentFolder.removeFile(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public FileExtension getExtension() {
		return extension;
	}
	
	public Folder getParentFolder() {
		return parentFolder;
	}

	public void setParentFolder(Folder parentFolder) {
		this.parentFolder = parentFolder;
	}
	
	public String toString() {
		return "File: " + this.getName()+"."+this.getExtension().getExtensionType() + ", Folder: " + this.getParentFolder().getName() + ", Size: " + this.getSize();
	}
	
}
