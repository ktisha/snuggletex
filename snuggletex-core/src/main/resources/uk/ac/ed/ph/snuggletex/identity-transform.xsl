<?xml version="1.0"?>
<!--

Identitiy transform

$Id: identity-transform.xsl 742 2012-05-07 13:09:53Z davemckain $

-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="*">
    <xsl:copy>
      <xsl:copy-of select="@*"/>
      <xsl:apply-templates/>
    </xsl:copy>
  </xsl:template>

  <xsl:template match="text()|comment()|processing-instruction()">
    <xsl:copy-of select="."/>
  </xsl:template>

</xsl:stylesheet>


