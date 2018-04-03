<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTdsChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tdsimport_edit_items_childwindow.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="100px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
	
		<tr><td valign="top" colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
		<img title="select" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
		Koder</td>
		</tr>
		<tr height="8"><td></td></tr>
		<tr>
		<td valign="top">
		<form action="TODO_childwindow_tillaggskoder.do?action=doFind" name="searchForm" id="searchForm" method="post">
				<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left">
					<%-- Tilläggskoder --%>
					<tr><td valign="top" colspan="3" class="text14Bold">&nbsp;Tilläggskoder <font style="font-weight:normal;">(Rubrik 33.3 och 33.4)</font></td></tr>
					<tr>
						<td class="ownScrollableSubWindow" style="width:600px; height:10em;">
							<table id="codeList" width="100%" cellspacing="0" border="0" cellpadding="0">
								<tr class="tableHeaderField" height="20" valign="left">
									<td align="center" class="tableHeaderFieldFirst">&nbsp;Välj&nbsp;</td>
								    <td align="center" class="tableHeaderField">&nbsp;Kod&nbsp;</td>   
				                    <td class="tableHeaderField">&nbsp;Text&nbsp;</td> 
			                    </tr>
			                    <c:forEach items="${model.listTillaggskoder}" var="record" varStatus="counter">    
					               <c:choose>           
					                   <c:when test="${counter.count%2==0}">
					                       <tr class="tableRow" height="20" >
					                   </c:when>
					                   <c:otherwise>   
					                       <tr class="tableOddRow" height="20" >
					                   </c:otherwise>
					               </c:choose>
					               <td class="tableCellFirst" align="center">
					               		<input class="clazzTillaggskodAware" type="checkbox" value="J" id="kod${record.kod}_dt${counter.count}" name="kod${record.kod}_dt${counter.count}" >
					               </td>
					               <td width="10%" class="tableCell" align="center">&nbsp;${record.kod}</td>
					               <td class="tableCell" >&nbsp;${record.txt}</td>
				               </tr>
				               </c:forEach>
				               
		                    </table>  
						</td>
					</tr>
					<%-- Bilagda handlingar Y-koder --%>
					<tr height="15"><td></td></tr>
					<tr><td valign="top" colspan="3" class="text14Bold">&nbsp;Bilagda handlingar Y-koder <font style="font-weight:normal;">(Rubrik 44)</font></td></tr>
					<tr>
						<td class="ownScrollableSubWindow" style="width:600px; height:10em;">
							<table id="codeList" width="100%" cellspacing="0" border="0" cellpadding="0">
								<tr class="tableHeaderField" height="20" valign="left">
									<td align="center" class="tableHeaderFieldFirst">&nbsp;Välj&nbsp;</td>
								    <td align="center" class="tableHeaderField">&nbsp;Kod&nbsp;</td>   
				                    <td class="tableHeaderField">&nbsp;Text&nbsp;</td> 
			                    </tr>
			                    <c:forEach items="${model.listBilagdaHandlingarYkoder}" var="record" varStatus="counter">    
					               <c:choose>           
					                   <c:when test="${counter.count%2==0}">
					                       <tr class="tableRow" height="20" >
					                   </c:when>
					                   <c:otherwise>   
					                       <tr class="tableOddRow" height="20" >
					                   </c:otherwise>
					               </c:choose>
					               <td class="tableCellFirst" align="center">
					               		<input class="clazzYkodAware" type="checkbox" value="J" id="kod${record.kod}_dt${counter.count}" name="kod${record.kod}_dt${counter.count}" >
					               </td>
					               <td width="10%" class="tableCell" align="center">&nbsp;${record.kod}</td>
					               <td class="tableCell" >&nbsp;${record.txt}</td>
				               </tr>
				               </c:forEach>
				               
		                    </table>  
						</td>
					</tr>
					
					
	               <tr>
		               <td align="left">&nbsp;<input class="inputFormSubmit" type="button" name="buttonCodesOk" id="buttonCodesOk" value='OK'></td>
	               </tr>	
			</table>
				
		</form>	
		</td>
		</tr>
	</table> 
