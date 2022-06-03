<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readOnly}">
	
	<jstl:choose>

	<jstl:when test="${acme:anyOf(command, 'show,update,delete')}">
	<acme:input-textbox code="inventor.item.chimpum.label.code" path="code" placeholder="00-00-00" readonly="TRUE"/>
	</jstl:when>
	<jstl:when test="${acme:anyOf(command, 'create') }">
	<acme:input-textbox code="inventor.item.chimpum.label.code" path="code" placeholder="00-00-00"/>
	</jstl:when>
	</jstl:choose>
	
	
	<acme:input-textarea code="inventor.chimpum.form.label.title" path="title" />
	<acme:input-money code="inventor.chimpum.form.label.description" path="description" placeholder="description" />
	<acme:input-money code="inventor.chimpum.form.label.budget" path="budget" />
	
	
	<jstl:choose>
	<jstl:when test="${acme:anyOf(command, 'show,update,delete')}">
	<acme:input-money code="inventor.chimpum.form.label.budgetInSC" path="priceInSC" readonly="TRUE"/>
	<acme:input-moment code="inventor.chimpum.form.label.creationMoment" path="creationMoment" readonly="TRUE" />
	</jstl:when>
	</jstl:choose>
	
	<acme:input-moment code="inventor.chimpum.form.label.startPeriodOfTime" path="startPeriodOfTime"/>
	<acme:input-moment code="inventor.chimpum.form.label.endPeriodOfTime" path="endPeriodOfTime" />
	<acme:input-url code="inventor.chimpum.form.label.link" path="link" />
	<jstl:choose>
	<jstl:when test="${command == 'create' }">
			<acme:submit code="inventor.chimpum.form.button.create" action="/inventor/chimpum/create?itemId=${itemId}"/>
		</jstl:when>
	</jstl:choose>
	<jstl:choose>
	<jstl:when test="${acme:anyOf(command, 'show,update,delete') && published == false}">
			<acme:submit code="inventor.chimpum.form.button.delete" action="delete"/>
			<acme:submit code="inventor.chimpum.form.button.update" action="update"/>
		</jstl:when>
	</jstl:choose>
</acme:form>
