<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenanceChildWindows.jsp" />
<!-- =====================end header ====================================-->
	
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenance_childwindow_tulltaxa.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			Tulltaxa fildistribution
			</td>
		</tr>
		<tr>
		<td valign="top">
		
		  		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%" >
					<tr>
					<td>
						<form name="tulltaxaFilesForm" id="tulltaxaFilesForm" onClick="setBlockUI()" action="tdsmaintenancefelles_tulltaxa_pgp.do" method="post">
							<input type="hidden" name="lnkpgp" id="lnkpgp" value="${model.tulltaxaObject.lnkpgp}">
							<input type="hidden" name="lnkzip" id="lnkzip" value="${model.tulltaxaObject.lnkzip}">
							<input type="hidden" name="lnkunzip" id="lnkunzip" value="${model.tulltaxaObject.lnkunzip}">
							<c:choose>
								<c:when test="${not empty tulltaxaObject.errMsg}">
									<p>
										<font style="background-color:red"><b>ERROR on FTP back-end when fetching files from TULLVERKET Fildistribution</b></font>
										<br/>
										<b>${tulltaxaObject.errMsg}</b>
									</p>
								</c:when>
								<c:otherwise>
									<table>
										<tr>
											<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Fortsätt med filbehandling'></td>
						           		</tr>
					           		</table>
								</c:otherwise>
							</c:choose>
							
		           		</form>
					</td>
					</tr>
					<tr height="5"><td></td></tr>
					
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:30em;">
					<%-- this is the datatables grid (content)--%>
					<table id="mainList" class="display compact cell-border" width="100%" >
						<thead>
						<tr style="background-color:#EEEEEE">
							<th width="10%" class="text14" >&nbsp;Tullverket fildist.&nbsp;</th>
							<th class="text14" >&nbsp;Filnamn&nbsp;</th>
		                    
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.listpgp}" varStatus="counter">    
			            <tr class="text14">

			               <td align="center" width="10%" style="cursor:pointer;" class="text14MediumBlue" >
			               		<img title="Fil hämtad OK" style="vertical-align:middle;" src="resources/images/checkmarkOK.png" width="14" height="14" border="0" alt="edit">
			               		
			               	</td>
		               	   <td class="text14">&nbsp;${record}</td>
		               	    
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
