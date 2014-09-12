/* $Id: LaTeXMode.java 742 2012-05-07 13:09:53Z davemckain $
 *
 * Copyright (c) 2008-2011, The University of Edinburgh.
 * All Rights Reserved
 */
package uk.ac.ed.ph.snuggletex.definitions;

import uk.ac.ed.ph.snuggletex.internal.LaTeXTokeniser.Terminator;

/**
 * Enumerates the various modes of LaTeX input.
 * 
 * @author  David McKain
 * @version $Revision: 742 $
 */
public enum LaTeXMode {
    
    /** 
     * The default LaTeX PARAGRAPH Mode, representing free-flowing textual content.
     * Parsing always starts in this mode.
     */
    PARAGRAPH,
    
    /** 
     * LaTeX LR (left-to-right) Mode, used in things like <tt>\\mbox</tt> and friends
     */
    LR,
    
    /** 
     * LaTeX MATH Mode, entered from PARAGRAPH or LR mode via commands such as <tt>\[</tt>
     * or by certain explicit MATH environments.
     */
    MATH,

    /**
     * LaTeX VERBATIM Mode, which pulls in all content until a mandatory {@link Terminator}
     * is found.
     */
    VERBATIM,
    
    ;
}
