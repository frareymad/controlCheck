<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.toolkit.list.label.code" path="code"/>
	<acme:list-column code="inventor.toolkit.list.label.title" path="title"/>
	<acme:list-column code="inventor.toolkit.list.label.published" path="published"/>
</acme:list>

<jstl:if test="${command == 'list-own'}">
	<acme:button code="inventor.toolkit.list.button.create" action="/inventor/toolkit/create"/>
</jstl:if>