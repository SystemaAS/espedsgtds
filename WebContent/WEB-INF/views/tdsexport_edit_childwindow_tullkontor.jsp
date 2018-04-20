<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTdsChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tdsexport_edit_childwindow_tullkontor.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			Søg kode</td>
		</tr>
		<tr>
		<td valign="top">
		
		  		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%" >
					<tr>
					<td>
						<table>
						<form name="tdsExportTullkontorForm" id="tdsExportTullkontorForm" action="tdsexport_edit_childwindow_tullkontor.do?action=doInit" method="post">
							<input type="hidden" name="ctype" id="ctype" value="${model.callerType}">
						<tr>
							<td class="text14">&nbsp;Kode</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="tkkode" id="tkkode" size="10" maxlength="10" value="${model.tkkode}"></td>
						
							<td class="text14">&nbsp;Tollsted</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="tktxtn" id="tktxtn" size="30" maxlength="50" value="${model.tktxtn}"></td>
							
							<td class="text14">&nbsp;</td>
	           				<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="search.label"/>'>
		           		</tr>
		           		
		           		</table>
					</td>
					</tr>
					 
													           		
	           		<tr height="10"><td></td></tr>
					
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:30em;">
					<%-- this is the datatables grid (content)--%>
					<table id="tullkontorList" class="display compact cell-border" width="100%" >
						<thead>
						<tr class="tableHeaderField" >
							<th class="text14" title="tkkode">&nbsp;Kode&nbsp;</th>
		                    <th class="text14" title="tktxtn">&nbsp;Tullkontor&nbsp;</th>
		                    <th class="text14" title="tkavg">&nbsp;Avg.&nbsp;</th>
		                    <th class="text14" title="tkank">&nbsp;Ank.&nbsp;</th>
		                    <th class="text14" title="tktrs">&nbsp;Transit.&nbsp;</th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.tullkontorList}" varStatus="counter">    
			               <c:choose>           
			                   <c:when test="${counter.count%2==0}">
			                       <tr class="text14">
			                   </c:when>
			                   <c:otherwise>   
			                       <tr class="text14">
			                   </c:otherwise>
			               </c:choose>
			               <td nowrap style="cursor:pointer;" class="text14MediumBlue" id="tkkode${record.tkkode}@tktxtn${record.tktxtn}@ctype${model.callerType}" >
		               			<img title="select" style="vertical-align:top;" src="resources/images/bebullet.gif" border="0" alt="edit">&nbsp;${record.tkkode}
			               </td>
		               	   <td class="text14">&nbsp;${record.tktxtn}</td>
		               	   <td class="text14">&nbsp;${record.tkavg}</td>
		               	   <td class="text14">&nbsp;${record.tkank}</td>
		               	   <td class="text14">&nbsp;${record.tktrs}</td>
			            </tr> 
			            </c:forEach>
			            </tbody>
		            </table>
		            </td>
	           		</tr>
        			</table>
		
		</td>
		</tr>
	</table> 
