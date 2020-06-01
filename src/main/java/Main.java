
public class Main {

	public static void main(String[] args) throws Exception {
		
		StorageDevice sd = new StorageDevice("HD", 5000);

		Folder a = new Folder("A", sd);
		Folder b = new Folder("B", a);
		Folder c = new Folder("C", b);
		Folder d = new Folder("D", b);
		Folder e = new Folder("E", a);
		Folder f = new Folder("F", sd);
		
		File photo = new File("photo", 496, new FileExtension("png"), c);
				
		sd.structure();
		
	}

}
