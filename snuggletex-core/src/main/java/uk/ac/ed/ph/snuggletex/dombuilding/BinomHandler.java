package uk.ac.ed.ph.snuggletex.dombuilding;

import org.w3c.dom.Element;
import uk.ac.ed.ph.snuggletex.internal.DOMBuilder;
import uk.ac.ed.ph.snuggletex.internal.SnuggleParseException;
import uk.ac.ed.ph.snuggletex.tokens.ArgumentContainerToken;
import uk.ac.ed.ph.snuggletex.tokens.CommandToken;

/**
 * Handles \binom{...}{...} and creates <mfrac>
 */
public class BinomHandler implements CommandHandler {

    public void handleCommand(DOMBuilder builder, Element parentElement, CommandToken token) throws SnuggleParseException {
        builder.appendMathMLOperatorElement(parentElement, "(");

        Element result = builder.appendMathMLElement(parentElement, "mfrac");
        result.setAttribute("linethickness", "0");
        for (ArgumentContainerToken argument : token.getArguments()) {
            builder.handleMathTokensAsSingleElement(result, argument);
        }
        builder.appendMathMLOperatorElement(parentElement, ")");
    }
}
