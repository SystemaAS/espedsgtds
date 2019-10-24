<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenanceChildWindows.jsp" />
<!-- =====================end header ====================================-->
	
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenance_childwindow_tulltaxa.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="350px" class="tableHeaderField"" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td valign="top" colspan="3" class="text16">
				<p>Tulltaxa fildistribution vidarebehandling</p>
				<p>
				<c:choose>
					<c:when test="${not empty model.error}">
						<h1><font style="background-color:red"><b>ERROR on PGP or GZIP processes</b></font></h1>
					</c:when>
					<c:otherwise>
						<b>Started process OK. Close window</b>
					</c:otherwise>
				</c:choose>
				</p>
			
			</td>
		</tr>
		
		
		
	</table> 
