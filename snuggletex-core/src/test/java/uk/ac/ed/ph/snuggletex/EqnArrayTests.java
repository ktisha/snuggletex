package uk.ac.ed.ph.snuggletex;

import junit.framework.TestCase;
import uk.ac.ed.ph.snuggletex.testutil.TestFileHelper;


public class EqnArrayTests extends TestCase {
    private SnuggleSession session;

    @Override
    protected void setUp() throws Exception {
        SnuggleEngine engine = new SnuggleEngine();
        session = engine.createSession();
    }

    public void testEqn() {
        try {
            final String data = TestFileHelper.ensureGetResource("eqnArray.txt");
            final String expected = TestFileHelper.ensureGetResource("eqnArray_expected.txt").trim();
            final SnuggleInput snuggleInput = new SnuggleInput(data);
            XMLStringOutputOptions options = new XMLStringOutputOptions();
            options.setIndenting(true);
            options.setAddingMathSourceAnnotations(false);
            session.parseInput(snuggleInput);
            final String xmlString = session.buildXMLString(options);
            assertEquals(expected, xmlString);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
