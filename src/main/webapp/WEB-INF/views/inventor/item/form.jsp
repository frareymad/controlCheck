<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>


<acme:form readonly="${readOnly}"> 
	<acme:input-textbox code="inventor.item.list.label.name" path="name"/>
	<jstl:choose>

	<jstl:when test="${acme:anyOf(command, 'show,update-tool,delete, publish, update-component, update-tool')}">
	<acme:input-textbox code="inventor.item.form.label.code" path="code" placeholder="XXX-000-X" readonly="TRUE"/>
	</jstl:when>
	<jstl:when test="${acme:anyOf(command, 'create-component, create-tool') }">
	<acme:input-textbox code="inventor.item.form.label.code" path="code" placeholder="XXX-000-X"/>
	</jstl:when>
	</jstl:choose>
	<acme:input-textbox code="inventor.item.form.label.technology" path="technology"/>
	<acme:input-textarea code="inventor.item.form.label.description" path="description"/>

	<acme:input-textbox code="inventor.item.list.label.link" path="link"/>
	<acme:input-money code="inventor.item.list.label.retailprice" path="retailPrice"/>
	
	
	
	<jstl:choose>
	<jstl:when test="${acme:anyOf(command, 'show, update-tool, update-component, delete, publish')}">
	<acme:input-money code="inventor.item.list.label.retailpriceInSC" path="priceInSC" readonly="true"/>
	</jstl:when>
	</jstl:choose>
	


	<jstl:choose>

		<jstl:when test="${acme:anyOf(command, 'show, update-tool, update-component, delete')}">
	<acme:input-textbox code="inventor.item.list.label.published" path="published" readonly="TRUE"/>
	</jstl:when>
	</jstl:choose>
	
	<jstl:choose>
	
	
	<jstl:when test="${acme:anyOf(command,'show, update-component, delete, publish') && itemType == 'COMPONENT'}">
	<acme:input-select code="inventor.item.form.label.type" path="itemType" readonly="TRUE">
		<acme:input-option value="COMPONENT" code="inventor.item.form.label.component" selected ="${itemType == 'COMPONENT' }"/>
    	
	</acme:input-select>
	</jstl:when>
	<jstl:when test="${acme:anyOf(command,'show, update-tool, delete, publish') && itemType == 'TOOL'}">
	<acme:input-select code="inventor.item.form.label.type" path="itemType" readonly="TRUE">
		
    	<acme:input-option value="TOOL" code="inventor.item.form.label.tool" selected ="${itemType == 'TOOL' }"/>
	</acme:input-select>
	</jstl:when>
	</jstl:choose>
	
	<jstl:choose>

		<jstl:when test="${command == 'create-component' }">
			<acme:submit code="inventor.item.form.button.create-component" action="/inventor/item/create-component"/>
		</jstl:when>
    
		<jstl:when test="${acme:anyOf(command,'show, update-component, delete, publish') && published == false && itemType == 'COMPONENT' }"> 
            <acme:submit code="inventor.item.form.button.update-component" action="/inventor/item/update-component"/>
            <acme:submit code="inventor.item.form.button.delete-component" action="delete-component"/>
            <acme:submit code="inventor.item.form.button.publish" action="publish"/>
    </jstl:when>


		<jstl:when test="${command == 'create-tool' }">
			<acme:submit code="inventor.item.form.button.create" action="/inventor/item/create-tool"/>
		</jstl:when>
    
		<jstl:when test="${acme:anyOf(command,'show, update-tool, delete, publish') && published == false  && itemType == 'TOOL'}"> 
            <acme:submit code="inventor.item.form.button.update" action="/inventor/item/update-tool"/>
            <acme:submit code="inventor.item.form.button.delete" action="delete-tool"/>
            <acme:submit code="inventor.item.form.button.publish" action="publish"/>
            <acme:button code="inventor.chimpum.form.button.create" action="/inventor/chimpum/create?itemId=${itemId}"/>
            <acme:button code="inventor.item.form.button.listChimpum" action="/inventor/chimpum/list?itemId=${itemId}"/>
    </jstl:when>
    <jstl:when test="${acme:anyOf(command,'show, update-tool, delete, publish') && itemType == 'TOOL'}"> 
    	<acme:button code="inventor.item.form.button.listChimpum" action="/inventor/chimpum/list?itemId=${itemId}"/>
    </jstl:when>
	</jstl:choose>
	
	
	
	
		
	

</acme:form>