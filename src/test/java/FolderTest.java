import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FolderTest {
	
	private static Folder a;
	private static int size = 5000;
	
	@BeforeAll
	static void setup() throws Exception {
		StorageDevice sd = new StorageDevice("HD", size);
		a = new Folder("A", sd);
	}
	
	@AfterAll
	public static void showWallet() {
		a.getStorage().structure();
	}
	
	@BeforeEach
	void beforeEach() throws Exception {
		Random rand = new Random();
		int i = rand.nextInt(500);
		System.out.println(new File("randomFile", i, new FileExtension("txt"), a));
	}
	
	@AfterEach
	void afterEach() {
		a.getFiles().get(0).removeFile();
	}
	
	@Test
	void testAssertions() {
		String[] files = {"A","B","A","D"};
		assertAll(
			() -> assertEquals(files[0], a.getName()),
			() -> assertNotEquals(files[1], a.getName()),
			() -> assertEquals(files[2], a.getName()), 
			() -> assertNotEquals(files[3], a.getName()));
	}
	
	@Test
	void testNotNull() {
		assertNotNull(a.getFiles());
	}
	
	@Test
	void testThrows() {
		assertThrows(Exception.class, () -> new File("randomFile", size, new FileExtension("txt"), a));
	}
	

}
