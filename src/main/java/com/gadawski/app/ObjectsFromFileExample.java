package com.gadawski.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.core.marshalling.impl.ProtobufMessages.KnowledgeSession;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import com.gadawski.util.Messages;
import com.gadawski.util.ObjectReader;
import com.gadawski.util.common.Counter;
import com.gadawski.util.common.MyAppConfig;
import com.gadawski.util.db.jdbc.JdbcManagerUtil;
import com.gadawski.util.db.jpa.EntityManagerUtil;

/**
 * Simple example use of Drools with facts get from file.
 * 
 * @author l.gadawski@gmail.com
 * 
 */
public class ObjectsFromFileExample {
    private static final String VER_6_FINAL = "6.0.0.Final";
    private static final String VER_5_FINAL = "5.5.0.Final";
    private static final String RULES_DRL = "rules.drl";
    private static final String DROOLS_PROPS = "drools.properties";

    public static final String GENERATED_DATA_RESOURCES_PATH = "D:/_development/workspace/example/myCode/my-app/src/main/resources/com/gadawski/app/data/generatedData.dat";

    private Properties props;

    /**
     * @param selected
     *            - if rule engine should use db.
     * @param generatedData
     */
    public ObjectsFromFileExample(final boolean selected) {
        MyAppConfig.USE_DB = selected;
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
        final ObjectsFromFileExample app = new ObjectsFromFileExample(true);
        app.start();
    }

    /**
     * @throws IOException
     */
    public void start() throws IOException {
        props = getProperties();
        String value = props.getProperty("drools.version");
        if (value != null) {
            if (value.equals(VER_5_FINAL)) {
                startInferencing();
            }
            if (value.equals(VER_6_FINAL)) {
                startInferencingDrools6();
            }
        }
        throw new IOException(Messages.CANT_LOAD_DROOLS_VERSION
                + ", loaded value: " + value);
    }

    /**
     * 
     */
    private void startInferencingDrools6() {
        System.out.println("hello drools 6!");
    }

    /**
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    private Properties getProperties() throws FileNotFoundException,
            IOException {
        if (props == null) {
            props = new Properties();
            loadProperties();
        }
        return props;
    }

    /**
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void loadProperties() throws FileNotFoundException, IOException {
        URL filePath = ObjectsFromFileExample.class.getResource(DROOLS_PROPS);
        if (filePath == null) {
            throw new FileNotFoundException(Messages.CANT_FIND_PROPS);
        }
        props.load(new FileInputStream(filePath.getPath()));
    }

    /**
     * Create all possible artifact for reasoning and fires all rules.
     * 
     * @throws FileNotFoundException
     */
    private void startInferencing() throws FileNotFoundException {

        final KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory
                .newKnowledgeBuilder();
        knowledgeBuilder.add(ResourceFactory.newClassPathResource(RULES_DRL,
                ObjectsFromFileExample.class), ResourceType.DRL);

        final KnowledgeBase knowledgeBase = KnowledgeBaseFactory
                .newKnowledgeBase();
        knowledgeBase.addKnowledgePackages(knowledgeBuilder
                .getKnowledgePackages());

        checkKnowledgeForErrors(knowledgeBuilder);

        final StatefulKnowledgeSession knowledgeSession = knowledgeBase
                .newStatefulKnowledgeSession();

        initilizeGlobalCounterInRule(knowledgeSession);

        List<Object> list = null;
        list = readObjectsFromFile();

        final long start = System.currentTimeMillis();
        System.out.println("Start. Use DB: " + MyAppConfig.USE_DB);

        insertObjectsIntoSession(knowledgeSession, list);

        final long checkpoint = System.currentTimeMillis() - start;
        System.out.println("Objects inserted after: " + checkpoint + "ms");

        knowledgeSession.fireAllRules();

        final long time = System.currentTimeMillis() - start;
        System.out.println("End.");
        System.err.println("Total time: " + time + "ms");

        cleanup(knowledgeSession);
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
        for (final Iterator<Object> it = list.iterator(); it.hasNext();) {
            final Object object = it.next();
            knowledgeSession.insert(object);
        }
    }

    /**
     * Disposes {@link KnowledgeSession} and cleanup {@link EntityManagerUtil}.
     * 
     * @param knowledgeSession
     *            ks to dispose.
     */
    private void cleanup(final StatefulKnowledgeSession knowledgeSession) {
        EntityManagerUtil entityManagerUtil = EntityManagerUtil.getInstance();
        entityManagerUtil.close();
        JdbcManagerUtil jdbcManager = JdbcManagerUtil.getInstance();
        jdbcManager.truncateAgendaItems();
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
        final Counter value = new Counter(0);
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
     * Through InputStream reads all objects from file_name.
     * 
     * @return List of objects from file.
     * @throws FileNotFoundException
     */
    private List<Object> readObjectsFromFile() throws FileNotFoundException {
        URL resourcesPathUrl = ObjectsFromFileExample.class
                .getResource(GENERATED_DATA_RESOURCES_PATH);
        if (resourcesPathUrl == null) {
            throw new FileNotFoundException(Messages.CANT_OPEN_RESOURCES_PATH);
        }
        final File file = new File(GENERATED_DATA_RESOURCES_PATH);
        InputStream inputStream = null;
        inputStream = new FileInputStream(file);
        ObjectReader objectReader = new ObjectReader();
        final List<Object> list = objectReader.getInputObjects(inputStream);
        return list;
    }
}
