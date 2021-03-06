/* $Id:EqnArrayBuilder.java 179 2008-08-01 13:41:24Z davemckain $
 *
 * Copyright (c) 2008-2011, The University of Edinburgh.
 * All Rights Reserved
 */
package uk.ac.ed.ph.snuggletex.dombuilding;

import org.w3c.dom.Element;
import uk.ac.ed.ph.snuggletex.internal.DOMBuilder;
import uk.ac.ed.ph.snuggletex.internal.DOMBuilder.MathContentBuilderCallback;
import uk.ac.ed.ph.snuggletex.internal.SnuggleParseException;
import uk.ac.ed.ph.snuggletex.tokens.ArgumentContainerToken;
import uk.ac.ed.ph.snuggletex.tokens.CommandToken;
import uk.ac.ed.ph.snuggletex.tokens.EnvironmentToken;
import uk.ac.ed.ph.snuggletex.tokens.FlowToken;

import java.util.List;

/**
 * Handles the <tt>eqnarray*</tt> environment.
 * 
 * @see MathEnvironmentHandler
 * 
 * @author  David McKain
 * @version $Revision:179 $
 */
public final class EqnArrayHandler implements EnvironmentHandler {

    public void handleEnvironment(final DOMBuilder builder, Element parentElement, EnvironmentToken token)
            throws SnuggleParseException {
        /* Compute the geometry of the table and make sure its content model is OK */
        int[] geometry = TabularHandler.computeTableDimensions(token.getContent());
        final int numColumns = geometry[1];

        /* Create callback to generate the actual content for the MathML */
        MathContentBuilderCallback callback = new MathContentBuilderCallback() {
            public void buildMathElementContent(Element contentContainerElement,
                    ArgumentContainerToken mathContentToken, boolean isAnnotated)
                    throws SnuggleParseException {
                /* Build <mtable/> */
                Element mtableElement = builder.appendMathMLElement(contentContainerElement, "mtable");
                Element mtrElement, mtdElement;
                for (FlowToken rowToken : mathContentToken) {
                    mtrElement = builder.appendMathMLElement(mtableElement, "mtr");
                    List<FlowToken> columns = ((CommandToken) rowToken).getArguments()[0].getContents();
                    for (FlowToken columnToken : columns) {
                        mtdElement = builder.appendMathMLElement(mtrElement, "mtd");
                        mtdElement.setAttribute("form", "infix");
                        mtdElement.setAttribute("lspace", "mediummathspace");
                        builder.handleTokens(mtdElement, ((CommandToken) columnToken).getArguments()[0].getContents(), true);
                    }
                    /* Add empty <td/> for missing columns */
                    for (int i=0; i<numColumns-columns.size(); i++) {
                        builder.appendMathMLElement(mtrElement, "mtd");
                    }
                }

            }
        };
        
        /* Now general MathML and any required annotations */
        builder.buildMathElement(parentElement, token, token.getContent(), true, callback);
    }
}
