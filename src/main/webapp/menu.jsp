<%--
  Created by IntelliJ IDEA.
  User: sajjad
  Date: 12/12/25
  Time: 8:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<div style="float: right;">
  Welcome, ${sessionScope.username} |
  <html:link action="/logout">Logout</html:link>
</div>
