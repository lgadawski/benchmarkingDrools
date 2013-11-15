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

public class ObjectReader {

	/**
	 * Convert the facts from the <code>InputStream</code> to a list of objects.
	 */
	public static List<Object> getInputObjects(InputStream inputStream) {
		List<Object> list = new ArrayList<Object>();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					inputStream));

			String line;
			while ((line = br.readLine()) != null) {
				if (line.trim().length() == 0 || line.trim().startsWith(";")) {
					continue;
				}
				StringTokenizer st = new StringTokenizer(line, "() ");

				String type = st.nextToken();
				if ("person".equals(type)) {
					Person person = readPerson(line, st);
					list.add(person);
				}
				if ("car".equals(type)) {
					Car car = readCar(line, st);
					list.add(car);
				}
				if ("house".equals(type)) {
					House house = readHouse(line, st);
					list.add(house);
				}
			}
			inputStream.close();
		} catch (IOException e) {
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
	private static House readHouse(String line, StringTokenizer st)
			throws IOException {
		if (!"name".equals(st.nextToken())) {
			throw new IOException("expected 'name' in: " + line);
		}
		String name = st.nextToken();
		
		if (!"price".equals(st.nextToken())) {
			throw new IOException("expected 'price' in: " + line);
		}
		String price = st.nextToken();
		
		if (!"area".equals(st.nextToken())) {
			throw new IOException("expected 'area' in: " + line);
		}
		String area = st.nextToken();
		
		House house = new House(name, new BigDecimal(price), new BigDecimal(area));
		return house;
	}

	/**
	 * Read car attributes from line.
	 * 
	 * @throws IOException
	 *             - thrown exception if wrong format.
	 */
	private static Car readCar(String line, StringTokenizer st)
			throws IOException {
		if (!"name".equals(st.nextToken())) {
			throw new IOException("expected 'name' in: " + line);
		}
		String name = st.nextToken();

		if (!"price".equals(st.nextToken())) {
			throw new IOException("expected 'price' in: " + line);
		}
		String price = st.nextToken();

		Car car = new Car(name, new BigDecimal(price));
		return car;
	}

	/**
	 * Read person attributes from line.
	 * 
	 * @throws IOException
	 *             - thrown exception if wrong format.
	 */
	private static Person readPerson(String line, StringTokenizer st)
			throws IOException {
		if (!"age".equals(st.nextToken())) {
			throw new IOException("expected 'age' in: " + line);
		}
		String age = st.nextToken();

		if (!"income".equals(st.nextToken())) {
			throw new IOException("expected 'income' in: " + line);
		}
		String income = st.nextToken();

		if (!"cash".equals(st.nextToken())) {
			throw new IOException("expected 'cash' in: " + line);
		}
		String cash = st.nextToken();

		if (!"married".equals(st.nextToken())) {
			throw new IOException("expected 'married' in: " + line);
		}
		String married = st.nextToken();

		Person person = new Person(Integer.parseInt(age),
				new BigDecimal(income), new BigDecimal(cash),
				Boolean.parseBoolean(married));
		return person;
	}
}
