<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTdsChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tdsexport_childwindow_external_references.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="99%" height="100px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
	
		<tr><td valign="top" colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
		<img title="select" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
		Externa Referenser
		</td>
		</tr>
		
		<tr height="8"><td></td></tr>
		<tr>
		<td valign="top">
			<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
		 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
			<table id="containerdatatableTable" width="98%" align="left">
				<%-- List (external references) --%>
				<tr height="15"><td></td></tr>
				<tr>
					<td colspan="3" >
						<table id="tblList" class="display compact cell-border" width="100%">
							<thead>
							<tr class="tableHeaderField">
								<th class="text14">&nbsp;Extern refnr.&nbsp;</th>
								<th class="text14">&nbsp;Avd.&nbsp;</th>   
			                    <th class="text14">&nbsp;Datum&nbsp;</th>   
			                    <th class="text14">&nbsp;Avsändare&nbsp;</th> 
			                    <th class="text14">&nbsp;Mottagare&nbsp;</th> 
			                    <th align="center" class="text14">Radera</th>
		                    </tr>
		                    </thead>
		                    <tbody>
		                    <c:forEach items="${model.listExternalRef}" var="record" varStatus="counter">    
				               <td  style="cursor:pointer;" class="text14MediumBlue" id="ref${record.fssok}" >
			               		&nbsp;&nbsp;${record.fssok}
			               		</td>
				               <td class="text14" >&nbsp;${record.fsavd}&nbsp;</td>
				               <td class="text14" >&nbsp;${record.fsdtop}&nbsp;</td>
				               <td class="text14" >&nbsp;${record.henas}</td>
				               <td class="text14" >&nbsp;${record.henak}</td>
				               <td width="2%" class="text14" align="center" nowrap>
					               	<a onclick="javascript:return confirm('Är du säker att du vill radera raden?')" tabindex=-1 href="tdsexport_edit_childwindow_external_references_delete.do?action=doDelete&svef_syav=${model.avd}&svef_syop=${model.opd}&svef_reff=${Xrecord.svef_reff}&svef_unik=${Xrecord.svef_unik}">
					               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
					               	</a>
				               </td>
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
