package com.gadawski.app;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import com.gadawski.app.util.ObjectReader;

/**
 * Simple example use of Drools with facts get from file.
 * 
 * @author l.gadawski@gmail.com
 * 
 */
public class ObjectsFromFileExample {
	/**
	 * Name of file with data for resoning.
	 */
	private static final String FILE_NAME = "generatedData.dat";

	public static void main(final String[] args) {
		final ObjectsFromFileExample app = new ObjectsFromFileExample();
		app.startInferencing();
	}

	/**
	 * Create all possible artifact for reasoning and fires all rules.
	 */
	public void startInferencing() {
		final KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		knowledgeBuilder.add(ResourceFactory.newClassPathResource("rules.drl",
				ObjectsFromFileExample.class), ResourceType.DRL);

		final KnowledgeBase knowledgeBase = KnowledgeBaseFactory
				.newKnowledgeBase();
		knowledgeBase.addKnowledgePackages(knowledgeBuilder
				.getKnowledgePackages());

		checkKnowledgeForErrors(knowledgeBuilder);

		final StatefulKnowledgeSession knowledgeSession = knowledgeBase
				.newStatefulKnowledgeSession();

		final List<Object> list = readObjectsFromFile();
		insertObjectsIntoSession(knowledgeSession, list);

		final long start = System.currentTimeMillis();
		knowledgeSession.fireAllRules();
		final long time = System.currentTimeMillis() - start;
		System.err.println("Time: " + time + "ms");

		knowledgeSession.dispose();
	}

	/**
	 * Checks {@link KnowledgeBuilder} for errors, if so throws
	 * {@link RuntimeException} exception.
	 * 
	 * @param knowledgeBuilder
	 *            - knowledge to check.
	 */
	private void checkKnowledgeForErrors(final KnowledgeBuilder knowledgeBuilder) {
		if (knowledgeBuilder.hasErrors()) {
			throw new RuntimeException("Compilation error.\n"
					+ knowledgeBuilder.getErrors().toString());
		}
	}

	/**
	 * Inserts give list to the session.
	 * 
	 * @param knowledgeSession
	 *            - session to insert objects.
	 * @param list
	 *            - list of object to insert.
	 */
	private void insertObjectsIntoSession(
			final StatefulKnowledgeSession knowledgeSession,
			final List<Object> list) {
		for (final Iterator<Object> it = list.iterator(); it.hasNext();) {
			final Object object = it.next();
			knowledgeSession.insert(object);
		}
	}

	/**
	 * Through InputStream reads all objects from file_name.
	 * 
	 * @return List of objects from file.
	 */
	private static List<Object> readObjectsFromFile() {
		final InputStream inputStream = ObjectsFromFileExample.class
				.getResourceAsStream("data/" + FILE_NAME);
		final List<Object> list = ObjectReader.getInputObjects(inputStream);
		return list;
	}
}