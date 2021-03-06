<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTdsChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/nctsexport_edit_items_childwindow_uppdragslist_gettoitemlines.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			<img title="select" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			Importera Exportärenden
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
						<form name="nctsExportAngivelseForm" id="nctsExportAngivelseForm" action="nctsexport_edit_items_childwindow_uppdragslist_gettoitemlines.do?action=doFind" method="post">
							<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
							<input type="hidden" name="avdNcts" id="avdNcts" value="${model.avdNcts}">
							<input type="hidden" name="opdNcts" id="opdNcts" value="${model.opdNcts}">
						<tr>
							<td class="text14">&nbsp;&nbsp;Avd</td>
							<td class="text14">&nbsp;&nbsp;Sign</td>
							<td class="text14">&nbsp;&nbsp;Ärende</td>
							<td class="text14">&nbsp;&nbsp;Tullid.</td>
							<td class="text14">&nbsp;&nbsp;Medd.typ</td>
							<td class="text14">&nbsp;&nbsp;Datum</td>
							<td class="text14">&nbsp;&nbsp;Status</td>
							<td class="text14">&nbsp;&nbsp;Avs.</td>
							<td class="text14">&nbsp;&nbsp;Mot.</td>
						 </tr>
						 <tr>	
							<td class="text14">&nbsp;<input type="text" class="inputText" name="avd" id="avd" size="5" maxlength="4" value="${searchFilter.avd}"></td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="sign" id="sign" size="5" maxlength="3" value="${searchFilter.sign}"></td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="opd" id="opd" size="8" maxlength="7" value="${searchFilter.opd}"></td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="tullId" id="mrn" size="10" maxlength="35" value="${searchFilter.tullId}"></td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="mtyp" id="refnr" size="4" maxlength="3" value="${searchFilter.mtyp}"></td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="datum" id="datum" size="10" maxlength="8" value="${searchFilter.datum}"></td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="status" id="status" size="3" maxlength="1" value="${searchFilter.status}"></td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="avsNavn" id="avsNavn" size="15" maxlength="25" value="${searchFilter.avsNavn}"></td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="motNavn" id="motNavn" size="15" maxlength="25" value="${searchFilter.motNavn}"></td>
							<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" onClick="setBlockUI(this);" value='<spring:message code="search.label"/>'>
		           		</tr>
		           		</form>
		           		</table>
					</td>
					</tr>
					 								           		
	           		<tr height="20"><td></td></tr>
					
					<tr class="text11" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" >
					
					<%-- this is the datatables grid (content)--%>
					<form action="N/A_is_done_with_jquery....?action=doFind" name="searchForm" id="searchForm" method="post">
						
					<table id="angivelseList" class="display compact cell-border" width="100%" >
						<thead>
						<tr class="tableHeaderField" >
							<th class="text14">&nbsp;Välj&nbsp;</th>
							<th class="text14" title="avd">&nbsp;Avd&nbsp;</th>
		                    <th class="text14" title="sign">&nbsp;Sign&nbsp;</th>
		                    <th class="text14" title="opd">&nbsp;Ärende&nbsp;</th>
		                    <th class="text14" title="tullid">&nbsp;Tullid&nbsp;</th>
		                    <th class="text14" title="mtyp">&nbsp;Medd.typ&nbsp;</th>
		                    <th class="text14" title="datum">&nbsp;Datum&nbsp;</th>
		                    <th class="text14" title="status">&nbsp;Status&nbsp;</th>
		                    <th class="text14" title="avsNavn">&nbsp;Avs&nbsp;</th>
		                    <th class="text14" title="motNavn">&nbsp;Mot&nbsp;</th>
		                    <th class="text14" title="dokref">&nbsp;Dok.ref.&nbsp;</th>
		                    
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.angivelseList}" varStatus="counter">
		                	<c:choose>
			                   <c:when test="${empty record.dokref}">    
				               		<tr style="color: #000000;">
				               </c:when>
				               <c:otherwise>
				               		<tr style="color: #4F8A10;background-color: #DFF2BF;">
				               </c:otherwise>
			               </c:choose>
			               <td align="center" class="text14" >
			               		<input style="cursor:pointer;" class="clazzEksportAware" type="checkbox" value="J" id="syav${record.avd}_syop${record.opd}" name="syav${record.avd}_syop${record.opd}" >
			               		<%-- REMOVED for DACHSER SE (14.nov.2018) Olga requirement 
			               		<c:choose>
				               		<c:when test="${empty record.dokref}">
				               			<input style="cursor:pointer;" class="clazzEksportAware" type="checkbox" value="J" id="syav${record.avd}_syop${record.opd}" name="syav${record.avd}_syop${record.opd}" >
				               		</c:when>
				               		<c:otherwise>
				               			<img title="redan plockad!" style="cursor:pointer;" src="resources/images/lock.gif" border="0" alt="edit">	
				               		</c:otherwise>
			               		</c:choose>
			               		--%>
			               </td>
			               <td width="2%" class="text14NoneColor">&nbsp;${record.avd}</td>
			               <td width="2%" class="text14NoneColor">&nbsp;${record.sign}</td>
			               <td width="2%" class="text14MediumBlue" id="avd${record.avd}@opd${record.opd}@tullid${record.tullId}" >&nbsp;${record.opd}</td>
		               	   <td width="2%" class="text14NoneColor">&nbsp;${record.tullid}</td>
		               	   <td width="2%" class="text14NoneColor">&nbsp;${record.mtyp}</td>
		               	   <td width="2%" class="text14NoneColor">&nbsp;${record.datum}</td>
		               	   <td width="2%" class="text14NoneColor">&nbsp;${record.status}</td>
		               	   <td class="text14NoneColor">&nbsp;${record.avsNavn}</td>
		               	   <td class="text14NoneColor">&nbsp;${record.motNavn}</td>
		               	   <td width="2%" class="text14NoneColor">&nbsp;${record.dokref}</td>
			            </tr> 
			            </c:forEach>
			            </tbody>
		            </table>
		            </form>
	    	        </td>
	    	        
           		</tr>
           		<tr height="10"><td></td></tr>
           		<tr>
		          <td align="left">
		          		&nbsp;<input class="inputFormSubmit" type="button" name="buttonPick" id="buttonPick" value='Plocka'>
		          		&nbsp;&nbsp;&nbsp;<input class="inputFormSubmit" type="button" name="buttonCloseOk" id="buttonCloseOk" value='Importera'>
		          		&nbsp;<input class="inputFormSubmit" type="button" name="cancel" id="cancel" value='Avbryt'>
		          </td>
		         </tr>
       			</table>
		</td>
		</tr>

	</table> 
