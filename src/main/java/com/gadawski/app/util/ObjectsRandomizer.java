package com.gadawski.app.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;

import com.gadawski.app.facts.Person;

/**
 * Utility class responsible for generating random objects to file.
 * 
 * @author l.gadawski@gmail.com
 * 
 */
public class ObjectsRandomizer {
	public static final String sTEXT_CODIS = "utf-8";
	public static final String sLINE_SEPARATOR = System
			.getProperty("line.separator");
	private static final int sNUM_OF_PEOPLE = 6000;

	/**
	 * Generates data to file.
	 * 
	 * @param path
	 *            - file name for output data.
	 */
	public static void generateData(String path) {
		Writer writer = null;
		try {
			URL url = ObjectsRandomizer.class.getResource("../" + path);
			File file = new File(url.toURI().getPath());
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), sTEXT_CODIS));
			writeToFile(writer);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * For a give writer, writes to file.
	 * 
	 * @param writer
	 *            - writer to write :)
	 * @throws IOException
	 *             - throws if I/O exception.
	 */
	private static void writeToFile(Writer writer) throws IOException {
		writePeople(writer);
		writeCars(writer);
		writeHouses(writer);
	}

	private static void writeCars(Writer writer) {
		// TODO Auto-generated method stub

	}

	private static void writeHouses(Writer writer) {
		// TODO Auto-generated method stub

	}

	private static void writePeople(Writer writer) throws IOException {
		Random random = new Random();
		for (int i = 0; i < sNUM_OF_PEOPLE; i++) {
			int age = randomInt(random, Person.MIN_AGE, Person.MAX_AGE);
			BigDecimal income = randomBigDecimal(random, Person.MIN_INCOME,
					Person.MAX_INCOME);
			BigDecimal cash = randomBigDecimal(random, Person.MIN_CASH,
					Person.MAX_CASH);
			boolean married = random.nextBoolean();
			writer.write("(person (age " + age + ") (income " + income
					+ ") (cash " + cash + ") (married " + married + ") )"
					+ sLINE_SEPARATOR);
		}
	}

	private static int randomInt(Random random, int min, int max) {
		return random.nextInt((max - min) + 1) + min;
	}

	/**
	 * TODO change for proper randoming BigDecimal number if necessary!
	 * 
	 * @param random
	 * @param min
	 * @param max
	 * @return
	 */
	private static BigDecimal randomBigDecimal(Random random, float min,
			float max) {
		return BigDecimal.valueOf(random.nextFloat() * (max - min) + min).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
}
