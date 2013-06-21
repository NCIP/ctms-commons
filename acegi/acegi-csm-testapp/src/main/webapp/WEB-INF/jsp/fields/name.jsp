<%--
Copyright Northwestern University and SemanticBits, LLC

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/ctms-commons/LICENSE.txt for details.
--%>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<B>Name:</B>
<spring:bind path="name">
  <FONT color="red">
    <B><c:out value="${status.errorMessage}"/></B>
  </FONT>
  <BR><INPUT type="text" maxlength="30" size="30" name="name" value="<c:out value="${status.value}"/>" >
</spring:bind>
<P>
