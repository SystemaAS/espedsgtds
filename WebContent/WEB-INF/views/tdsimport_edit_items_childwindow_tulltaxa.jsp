<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTdsChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tdsimport_edit_items_childwindow_tulltaxa.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			<img title="select" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			Sök kod
			</td>
		</tr>
		<tr>
		<td valign="top">
		
		  		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%" >
					<tr>
					<td>
						<table>
						<form name="tdsImportTulltaxaForm" id="tdsImportTulltaxaForm" action="tdsimport_edit_items_childwindow_tulltaxa.do?action=doInit&caller=${model.caller}" method="post">
						<tr>
							<td class="text14">&nbsp;Varukod</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="vkod" id="vkod" size="10" maxlength="10" value="${model.vkod}"></td>
							
							<td class="text14">&nbsp;Beskrivning</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="tekst" id="tekst" size="30" maxlength="50" value="${model.tekst}"></td>
						
							<td class="text14">&nbsp;</td>
	           				<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="search.label"/>'>
		           		</tr>
		           		
		           		</table>
					</td>
					</tr>
					 
													           		
	           		<tr height="10"><td></td></tr>
					
					<tr class="text14" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:30em;">
					<%-- this is the datatables grid (content)--%>
					<table id="tullTaxaList" class="display compact cell-border" width="100%" >
						<thead>
						<tr class="tableHeaderField" >
							<th class="text14" title="adunnr">&nbsp;Grupp&nbsp;</th>
							<th class="text14" title="adunnr">&nbsp;Varukod&nbsp;</th>
		                    <th class="text14" title="adembg">&nbsp;Beskrivning&nbsp;</th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.tullTaxaList}" varStatus="counter">    
			               <c:choose>           
			                   <c:when test="${counter.count%2==0}">
			                       <tr class="text14">
			                   </c:when>
			                   <c:otherwise>   
			                       <tr class="text14">
			                   </c:otherwise>
			               </c:choose>
			               
		               	   <td class="text14" > &nbsp;&nbsp;${record.svvs_vatak} </td>
			               <td nowrap style="cursor:pointer;" class="text14MediumBlue" id="vkod${record.svvs_vata}@text${record.svvs_txtk}@caller${model.caller}" >
			               		<img title="select" valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;${record.svvs_vata}
			               	</td>
		               	   <td class="text14">&nbsp;${record.svvs_txtk}</td>
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
