<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes" encoding="UTF-8"/>

    <xsl:template match="/">
        <html>
        <head><title>사용자 리스트</title></head>
        <body>
            <h2>성적 통계 표</h2>
            <table border="1">
                <tr bgcolor="#efefef">
                    <th>이름</th>
                    <th>나이</th>
                    <th>상태</th>
                    <th>등급</th>
                </tr>
                <xsl:for-each select="root/item">
                <tr>
                    <td><xsl:value-of select="name"/></td>
                    <td><xsl:value-of select="age"/></td>
                    <td>
                        <xsl:if test="age &gt;= 20">성인</xsl:if>
                    </td>
                    <td>
                        <xsl:choose>
                            <xsl:when test="score &gt;= 90"><b>A</b></xsl:when>
                            <xsl:when test="score &gt;= 80">B</xsl:when>
                            <xsl:otherwise>C</xsl:otherwise>
                        </xsl:choose>
                    </td>
                </tr>
                </xsl:for-each>
            </table>
        </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
