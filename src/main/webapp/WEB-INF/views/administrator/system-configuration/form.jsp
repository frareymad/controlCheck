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

<acme:form>
	<acme:input-textbox code="administrator.system-configuration.form.label.acceptedCurrencies" path="acceptedCurrencies"/>
	<%--<acme:input-textbox code="administrator.system-configuration.form.label.systemCurrency" path="systemCurrency"/>--%>
	<acme:input-select code="administrator.system-configuration.form.label.systemCurrency" path="systemCurrency">
		<jstl:forEach items="${acceptedCurrenciesList}" var="acceptedCurrenciesListOption">
			<acme:input-option code="${acceptedCurrenciesListOption}" value="${acceptedCurrenciesListOption}" selected="${systemCurrency == acceptedCurrenciesListOption}"/>
		</jstl:forEach>
		
	</acme:input-select>
	<acme:input-textbox code="administrator.system-configuration.form.label.strongSpamWords" path="strongSpamWords"/>
	<acme:input-double code="administrator.system-configuration.form.label.strongSpamThreshold" path="strongSpamThreshold"/>
	<acme:input-textbox code="administrator.system-configuration.form.label.weakSpamWords" path="weakSpamWords"/>
	<acme:input-double code="administrator.system-configuration.form.label.weakSpamThreshold" path="weakSpamThreshold"/>
	<acme:input-textarea code="administrator.system-configuration.form.label.money" path="moneyExchangeService" readonly="TRUE"/>

	<%--<acme:button test="${command == 'show'}" code="administrator.system-configuration.form.button.update" action="/administrator/system-configuration/update"/> --%>
	<acme:submit code="administrator.system-configuration.form.button.update" action="/administrator/system-configuration/update"/>
</acme:form> 
