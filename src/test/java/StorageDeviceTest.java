import static org.junit.Assume.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class StorageDeviceTest {
	
	private static StorageDevice sd;
	private static int size = 5000;
	
	@BeforeAll
	static void setup() throws Exception {
		sd = new StorageDevice("HD", size);
	}

	@Test
	void testTrue() {
		assumeTrue(sd.getActualSize() < sd.getMaxSize());
	}
	
	@Test
	void testFalse() {
		assumeFalse(sd.getActualSize() > sd.getMaxSize());
	}
	
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	@Test
	void testTimeout() {
		sd.onStartUp();
	}
	
	@Disabled
	@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
	@Test
	void testTimeout2() {
		sd.onStartUp();
	}


}
