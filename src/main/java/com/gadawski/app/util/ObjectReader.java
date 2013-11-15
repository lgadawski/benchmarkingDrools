package com.gadawski.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.gadawski.app.facts.Car;
import com.gadawski.app.facts.House;
import com.gadawski.app.facts.Person;

/**
 * Utility class responsible for reading objects from file.
 * 
 * @author l.gadawski@gmail.com
 * 
 */
public class ObjectReader {

	/**
	 * Convert the facts from the <code>InputStream</code> to a list of objects.
	 */
	public static List<Object> getInputObjects(final InputStream inputStream) {
		final List<Object> list = new ArrayList<Object>();

		try {
			final BufferedReader br = new BufferedReader(new InputStreamReader(
					inputStream));

			String line;
			while ((line = br.readLine()) != null) {
				if (line.trim().length() == 0 || line.trim().startsWith(";")) {
					continue;
				}
				final StringTokenizer st = new StringTokenizer(line, "() ");

				final String type = st.nextToken();
				if ("person".equals(type)) {
					final Person person = readPerson(line, st);
					list.add(person);
				}
				if ("car".equals(type)) {
					final Car car = readCar(line, st);
					list.add(car);
				}
				if ("house".equals(type)) {
					final House house = readHouse(line, st);
					list.add(house);
				}
			}
			inputStream.close();
		} catch (final IOException e) {
			throw new IllegalArgumentException(
					"Could not read inputstream properly.", e);
		}

		return list;
	}

	/**
	 * Read house attributes from line.
	 * 
	 * @throws IOException
	 *             - thrown if wrong format.
	 */
	private static House readHouse(final String line, final StringTokenizer st)
			throws IOException {
		if (!"name".equals(st.nextToken())) {
			throw new IOException("expected 'name' in: " + line);
		}
		final String name = st.nextToken();

		if (!"price".equals(st.nextToken())) {
			throw new IOException("expected 'price' in: " + line);
		}
		final String price = st.nextToken();

		if (!"area".equals(st.nextToken())) {
			throw new IOException("expected 'area' in: " + line);
		}
		final String area = st.nextToken();

		final House house = new House(name, new BigDecimal(price),
				new BigDecimal(area));
		return house;
	}

	/**
	 * Read car attributes from line.
	 * 
	 * @throws IOException
	 *             - thrown exception if wrong format.
	 */
	private static Car readCar(final String line, final StringTokenizer st)
			throws IOException {
		if (!"name".equals(st.nextToken())) {
			throw new IOException("expected 'name' in: " + line);
		}
		final String name = st.nextToken();

		if (!"price".equals(st.nextToken())) {
			throw new IOException("expected 'price' in: " + line);
		}
		final String price = st.nextToken();

		final Car car = new Car(name, new BigDecimal(price));
		return car;
	}

	/**
	 * Read person attributes from line.
	 * 
	 * @throws IOException
	 *             - thrown exception if wrong format.
	 */
	private static Person readPerson(final String line, final StringTokenizer st)
			throws IOException {
		if (!"name".equals(st.nextToken())) {
			throw new IOException("expected 'name' in: " + line);
		}
		String name = st.nextToken();

		if (!"age".equals(st.nextToken())) {
			throw new IOException("expected 'age' in: " + line);
		}
		final String age = st.nextToken();

		if (!"income".equals(st.nextToken())) {
			throw new IOException("expected 'income' in: " + line);
		}
		final String income = st.nextToken();

		if (!"cash".equals(st.nextToken())) {
			throw new IOException("expected 'cash' in: " + line);
		}
		final String cash = st.nextToken();

		if (!"married".equals(st.nextToken())) {
			throw new IOException("expected 'married' in: " + line);
		}
		final String married = st.nextToken();

		final Person person = new Person(name, Integer.parseInt(age),
				new BigDecimal(income), new BigDecimal(cash),
				Boolean.parseBoolean(married));
		return person;
	}
}
