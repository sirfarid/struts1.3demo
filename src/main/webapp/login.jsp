<%--
  Created by IntelliJ IDEA.
  User: sajjad
  Date: 12/12/25
  Time: 8:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<html:html>
  <head>
    <title>Login</title>
  </head>
  <body>
  <html:errors/>

  <c:if test="${not empty param.error}">
    <div class="error">Session expired. Please login again.</div>
  </c:if>

  <html:form action="/login" method="post">
    <table>
      <tr>
        <td>Username:</td>
        <td><html:text property="username"/></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><html:password property="password"/></td>
      </tr>
      <tr>
        <td colspan="2" align="center">
          <html:submit value="Login"/>
        </td>
      </tr>
    </table>
  </html:form>
  </body>
</html:html>
