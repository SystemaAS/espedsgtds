<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTdsChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tdsimport_edit_invoice_childwindow_generalcodes.js?ver=${user.versionEspedsg}"></SCRIPT>
	
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
					<%--
					<tr>
					<td>
						<table>
						<tr>
							<td class="text14">&nbsp;Kod</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="kode" id="kode" size="10" maxlength="10" value="${Xmodel.container.kode}"></td>
							
							<td class="text14">&nbsp;Beskrivning</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="tekst" id="tekst" size="10" maxlength="20" value="${Xmodel.container.tekst}"></td>
						
							<td class="text14">&nbsp;</td>
	           				<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="search.label"/>'>
		           		</tr>
		           		
		           		</table>
					</td>
					</tr>
					--%> 
													           		
	           		<tr height="10"><td></td></tr>
					
					<tr class="text14" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:30em;">
					<%-- this is the datatables grid (content)--%>
					<table id="generalCodeList" class="display compact cell-border" width="100%" >
						<thead>
						<tr class="tableHeaderField" >
							<th class="text14" title="adunnr">&nbsp;Kod&nbsp;</th>
		                    <th class="text14" title="adembg">&nbsp;Beskrivning&nbsp;</th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.generalCodeList}" varStatus="counter">    
			               <c:choose>           
			                   <c:when test="${counter.count%2==0}">
			                       <tr class="text14">
			                   </c:when>
			                   <c:otherwise>   
			                       <tr class="text14">
			                   </c:otherwise>
			               </c:choose>
			               
		               	   <td nowrap style="cursor:pointer;" class="text14MediumBlue" 
				               		id="kod${record.svkd_kd}@ctype${model.callerType}" >
				               		&nbsp;<img title="select" valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				               		&nbsp;&nbsp;${record.svkd_kd}
			               </td>
		               	   <td class="text14">&nbsp;${record.svkd_kbs}</td>
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
