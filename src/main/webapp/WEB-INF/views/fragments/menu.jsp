<%--
- menu.jsp
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
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>

		<acme:menu-option code="master.menu.any">
			<acme:menu-suboption code="master.menu.any.item.list.tool"
				action="/any/item/list-tool" />
			<acme:menu-suboption code="master.menu.any.item.list.component"
				action="/any/item/list-component" />
			<acme:menu-suboption code="master.menu.any.chirp.list-recent"
				action="/any/chirp/list-recent" />
			<acme:menu-suboption code="master.menu.any.chirp.create"
				action="/any/chirp/create" />
			<acme:menu-suboption code="master.menu.any.useraccount.list"
				action="/any/user-account/list" />
			<acme:menu-suboption code="master.menu.any.toolkit.list"
				action="/any/toolkit/list" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.authenticated"
			access="isAuthenticated()">
			<acme:menu-suboption
				code="master.menu.authenticated.announcement.list"
				action="/authenticated/announcement/list" />
			<acme:menu-suboption
				code="master.menu.authenticated.system-configuration.form"
				action="/authenticated/system-configuration/show" />
		</acme:menu-option>


		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
		</acme:menu-option>


		<acme:menu-option code="master.menu.administrator"
			access="hasRole('Administrator')">
			<acme:menu-suboption
				code="master.menu.administrator.create-announcement"
				action="/administrator/announcement/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.user-accounts"
				action="/administrator/user-account/list-recent" />
			<acme:menu-separator />
			<acme:menu-suboption
				code="master.menu.administrator.populate-initial"
				action="/administrator/populate-initial" />
			<acme:menu-suboption code="master.menu.administrator.populate-sample"
				action="/administrator/populate-sample" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.shut-down"
				action="/administrator/shut-down" />
			<acme:menu-separator />
			<acme:menu-suboption
				code="master.menu.administrator.system-configuration.form"
				action="/administrator/system-configuration/show" />
			<acme:menu-suboption
				code="master.menu.administrator.administrator-dashboard.show"
				action="/administrator/administrator-dashboard/show" />

		</acme:menu-option>

		<acme:menu-option code="master.menu.provider"
			access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link"
				action="http://www.example.com/" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer"
			access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link"
				action="http://www.example.com/" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.inventor"
			access="hasRole('Inventor')">
			<acme:menu-suboption code="master.menu.inventor.patronage.list"
				action="/inventor/patronage/list-own" />
			<acme:menu-suboption
				code="master.menu.inventor.patronage-report.list"
				action="/inventor/patronage-report/list-own" />
			<acme:menu-suboption code="master.menu.inventor.item.component"
				action="/inventor/item/list-component" />
			<acme:menu-suboption code="master.menu.inventor.item.tool"
				action="/inventor/item/list-tool" />
			<acme:menu-suboption code="master.menu.inventor.toolkit.list"
				action="/inventor/toolkit/list-own" />


		</acme:menu-option>


		<acme:menu-option code="master.menu.patron" access="hasRole('Patron')">
			<acme:menu-suboption code="master.menu.patron.patronage.list"
				action="/patron/patronage/list-own" />
			<acme:menu-suboption code="master.menu.patron.patronage.create"
				action="/patron/patronage/create" />
			<acme:menu-suboption code="master.menu.patron.patronage-report.list"
				action="/patron/patronage-report/list-own" />
			<acme:menu-suboption code="master.menu.patron.patron-dashboard.show"
				action="/patron/patron-dashboard/show" />
		</acme:menu-option>



	</acme:menu-left>

	<acme:menu-right>

		<acme:menu-option code="master.menu.sign-up"
			action="/anonymous/user-account/create" access="isAnonymous()" />
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in"
			access="isAnonymous()" />

		<acme:menu-option code="master.menu.user-account"
			access="isAuthenticated()">

			<acme:menu-suboption code="master.menu.user-account.general-data"
				action="/authenticated/user-account/update" />

			<acme:menu-suboption code="master.menu.user-account.patron"
				action="/authenticated/patron/update" access="hasRole('Patron')" />

			<acme:menu-suboption code="master.menu.user-account.become-patron"
				action="/authenticated/patron/create" access="!hasRole('Patron')" />

			<acme:menu-suboption code="master.menu.user-account.become-inventor"
				action="/authenticated/inventor/create"
				access="!hasRole('Inventor')" />

			<acme:menu-suboption code="master.menu.user-account.inventor"
				action="/authenticated/inventor/update" access="hasRole('Inventor')" />


		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out"
			action="/master/sign-out" access="isAuthenticated()" />
	</acme:menu-right>
</acme:menu-bar>

