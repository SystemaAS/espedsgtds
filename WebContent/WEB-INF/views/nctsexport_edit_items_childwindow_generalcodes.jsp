<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTdsChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/nctsexport_edit_items_childwindow_generalcodes.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			Sök kod</td>
		</tr>
		<tr>
		<td valign="top">
		
		  		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%" >
										           		
	           		<tr height="10"><td></td></tr>
					
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:30em;">
					<%-- this is the datatables grid (content)--%>
					<table id="generalCodeList" class="display compact cell-border" width="100%" >
						<thead>
						<tr class="tableHeaderField">
							<th class="text14" title="adunnr">&nbsp;Kod&nbsp;</th>
		                    <th class="text14" title="adembg">&nbsp;Beskrivning&nbsp;</th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.generalCodeList}" varStatus="counter">    
			               <tr class="text14">
			               <c:choose>           
		                   	<c:when test="${not empty record.svkd_kd}">
			                   <td nowrap style="cursor:pointer;" class="text14MediumBlue" 
				               		id="kod${record.svkd_kd}@ctype${model.callerType}" >
				               		&nbsp;<img title="select" style="vertical-align:top;" src="resources/images/bebullet.gif" border="0" alt="edit">
				               		&nbsp;&nbsp;${record.svkd_kd}
				               </td>
			               	   <td class="text14">&nbsp;${record.svkd_kbs}</td>
			               	</c:when>
			               	<c:otherwise>
			               		<td nowrap style="cursor:pointer;" class="text14MediumBlue" 
				               		id="kod${record.tkkode}@ctype${model.callerType}" >
				               		&nbsp;<img title="select" style="vertical-align:top;" src="resources/images/bebullet.gif" border="0" alt="edit">
				               		&nbsp;&nbsp;${record.tkkode}
				               </td>
			               	   <td class="text14">&nbsp;${record.tktxtn}</td>
			               	 </c:otherwise>  
			               	</c:choose>   
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
