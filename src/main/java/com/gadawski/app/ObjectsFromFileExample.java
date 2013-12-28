package com.gadawski.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.reteoo.JoinNode;
import org.drools.runtime.StatefulKnowledgeSession;

import com.gadawski.app.gui.MainWindow;
import com.gadawski.util.ObjectReader;
import com.gadawski.util.common.Counter;
import com.gadawski.util.db.EntityManagerUtil;

/**
 * Simple example use of Drools with facts get from file.
 * 
 * @author l.gadawski@gmail.com
 * 
 */
public class ObjectsFromFileExample {
    /**
     * @param selected
     *            - if rule engine should use db.
     */
    public ObjectsFromFileExample(boolean selected) {
        JoinNode.USE_DB = selected;
    }

    /**
     * @param args
     */
    public static void main(final String[] args) {
        final ObjectsFromFileExample app = new ObjectsFromFileExample(true);
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

        initilizeGlobalCounterInRule(knowledgeSession);

        final List<Object> list = readObjectsFromFile();

        final long start = System.currentTimeMillis();
        System.out.println("Start. Use DB: " + JoinNode.USE_DB);

        insertObjectsIntoSession(knowledgeSession, list);
        knowledgeSession.fireAllRules();

        final long time = System.currentTimeMillis() - start;
        System.out.println("End.");
        System.err.println("Total time: " + time + "ms");

        knowledgeSession.dispose();
    }

    /**
     * Initializes global counter in rule.
     * 
     * @param knowledgeSession
     *            for which global has to be set.
     */
    private void initilizeGlobalCounterInRule(
            final StatefulKnowledgeSession knowledgeSession) {
        Counter value = new Counter(0);
        knowledgeSession.setGlobal("counter", value);
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
     * Inserts given list to the session.
     * 
     * @param knowledgeSession
     *            - session to insert objects.
     * @param list
     *            - list of object to insert.
     */
    private void insertObjectsIntoSession(
            final StatefulKnowledgeSession knowledgeSession,
            final List<Object> list) {
        EntityManagerUtil entityManagerUtil = EntityManagerUtil.getInstance();
        entityManagerUtil.beginTransaction();
        for (final Iterator<Object> it = list.iterator(); it.hasNext();) {
            final Object object = it.next();
            if (JoinNode.USE_DB) {
                entityManagerUtil.saveObject(object);
            }
            knowledgeSession.insert(object);
        }
        entityManagerUtil.commitTransaction();
        entityManagerUtil.close();
    }

    /**
     * Through InputStream reads all objects from file_name.
     * 
     * @return List of objects from file.
     */
    private static List<Object> readObjectsFromFile() {
        // final InputStream inputStream = ObjectsFromFileExample.class
        // .getResourceAsStream("data/" + FILE_NAME);
        File file = new File(MainWindow.path);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        final List<Object> list = ObjectReader.getInputObjects(inputStream);
        return list;
    }
}
