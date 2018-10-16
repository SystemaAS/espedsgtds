<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTdsChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tdsexport_edit_childwindow_external_invoices.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="99%" height="100px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
	
		<tr><td valign="top" colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
		<img title="select" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
		Externa Fakturor
		</td>
		</tr>
		
		<tr height="8"><td></td></tr>
		<tr>
		<td valign="top">
		<form action="TODO_childwindow_external_invoices.do?action=doFind" name="searchForm" id="searchForm" method="post">
				<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
				<input type="hidden" name="avd" id="avd" value="${model.avd}">
				<input type="hidden" name="opd" id="opd" value="${model.opd}">
				
				<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" width="98%" align="left">
					<%-- Fakturalista (external invoices) --%>
					<tr height="15"><td></td></tr>
					<tr><td valign="top" colspan="3" class="text14Bold">&nbsp;Fakturalista</td></tr>
					<tr>
						<td colspan="3" >
							<table id="tblInvoices" class="display compact cell-border" width="100%">
								<thead>
								<tr class="tableHeaderField" >
									<th class="text14">&nbsp;Välj&nbsp;</th>
								    <th class="text14">&nbsp;Fakturanr.&nbsp;</th>   
				                    <th class="text14">&nbsp;Typ&nbsp;</th> 
				                    <th align="right" class="text14">&nbsp;Belopp&nbsp;</th> 
				                    <th class="text14">&nbsp;Valuta&nbsp;</th> 
				                    <th class="text14">&nbsp;Tullid&nbsp;</th>
				                    <th align="center" class="text14">Radera</th>
				             
			                    </tr>
			                    </thead>
			                    <tbody>
			                    <c:forEach items="${model.list}" var="record" varStatus="counter">    
					               <c:choose>           
					                   <c:when test="${counter.count%2==0}">
					                       <tr class="tableRow" height="15" >
					                   </c:when>
					                   <c:otherwise>   
					                       <tr class="tableOddRow" height="15" >
					                   </c:otherwise>
					               </c:choose>
					               <td class="text14" align="center">
					               		<input class="clazzInvoiceAware" type="checkbox" value="J" id="id${record.svef_reff}_unik${record.svef_unik}" name="id${record.svef_reff}_unik${record.svef_unik}" >
					               </td>
					               <td width="10%" class="text14" align="center">&nbsp;<span title="reff/unik:${record.svef_reff}/${record.svef_unik}">${record.svef_fatx}</span></td>
					               <td class="text14" >&nbsp;${record.svef_faty}</td>
					               <td align="right" class="text14" >&nbsp;${record.svef_fabl}&nbsp;</td>
					               <td class="text14" >&nbsp;${record.svef_vakd}</td>
					               <td class="text14" >&nbsp;${record.svef_tuid}</td>
					               <td width="4%" class="text14" align="center" nowrap>
						               	<a onclick="javascript:return confirm('Är du säker att du vill radera raden?')" tabindex=-1 href="tdsexport_edit_childwindow_external_invoices_delete.do?action=doDelete&svef_syav=${model.avd}&svef_syop=${model.opd}&svef_reff=${record.svef_reff}&svef_unik=${record.svef_unik}">
						               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
						               	</a>
					               </td>
				               </tr>
				               </c:forEach>
				               </tbody>
		                    </table>  
						</td>
					</tr>
					
					
	               <tr>
		               <td align="left">
		               		&nbsp;<input class="inputFormSubmit" type="button" name="buttonCloseOk" id="buttonCloseOk" value='OK'>
		               		&nbsp;<input class="inputFormSubmit" type="button" name="cancel" id="cancel" value='Avbryt'>
		               </td>
	               </tr>	
			</table>
				
		</form>	
		</td>
		</tr>
	</table> 
