package sf4jTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sf4jTest {
	private static final Logger logger = LoggerFactory.getLogger(Sf4jTest.class); 
	public static void main(String[] args) {
		System.out.println("ff");
		logger.info("hello {}","SL4J");
	}
}
