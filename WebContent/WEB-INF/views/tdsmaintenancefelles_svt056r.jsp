<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTdsMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tdsmaintenancefelles_svt056r.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>


<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr height="15"><td>&nbsp;</td></tr>
	<tr>
		<td>
		<%-- tab container component --%>
		<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
				<tr height="25"> 
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkTdsMaintFellesGate" tabindex=-1 style="display:block;" href="tdsmaintenancefelles.do">
						<font class="tabDisabledLink">&nbsp;TDS - Underhåll</font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="25%" valign="bottom" class="tab" align="center">
						<font class="tabLink">Handläggare</font>
						<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="tdsmaintenancefelles_svt056r.do?id=${model.dbTable}">
							<img style="vertical-align: middle;"  src="resources/images/bulletGreen.png" border="0" width="8px" height="8px" alt="db table">
						</a>
					</td>
					<td width="70%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<%-- space separator --%>
	 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	    
			<%-- list component --%>
			<tr>
				<td width="3%">&nbsp;</td>
				<td width="100%">
				<table id="containerdatatableTable" width="98%" cellspacing="1" border="0" align="left">
			    	    <tr>
						<td class="text11">
						<table id="mainList" class="display compact cell-border" >
							<thead>
							<tr>
							    <th width="2%" class="tableHeaderFieldFirst" align="center" >Ändra</th>                                                            
								<th width="10%" class="tableHeaderField" align="center" >Signatur</th>
								<th class="tableHeaderField" align="center" >Namn</th>
			                    <th class="tableHeaderField" align="left" >&nbsp;Userid</th>
			                    <th nowrap class="tableHeaderField" align="center" >Ta bort</th>
			                    
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <td width="2%" id="recordUpdate_${record.svth_sysg}" onClick="getRecord(this);" align="center" class="tableCellFirst" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
					               	<img src="resources/images/update.gif" border="0" alt="edit">
				               </td>
				               <td width="10%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="center">${record.svth_sysg}</td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;">&nbsp;${record.svth_namn}&nbsp;</td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${record.svth_usid}&nbsp;</td>
		                       <td nowrap align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<a onclick="javascript:return confirm('Är du säker på at du vill ta bort denna?')" tabindex=-1 href="tdsmaintenancefelles_svt056r_edit.do?action=doDelete&svth_sysg=${record.svth_sysg}">
					               		<img valign="bottom" src="resources/images/delete.gif" border="0" width="15px" height="15px" alt="remove">
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
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	    
	 	    <%-- ----------------- --%>
			<%-- Validation errors --%>
			<%-- ----------------- --%>
			<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
			<tr>
				<td width="5%">&nbsp;</td>
				<td >
		           	<table align="left" border="0" cellspacing="0" cellpadding="0">
		           	<tr >
		           	<td >					
			            <ul class="isa_error text14" >
			            <c:forEach var="error" items="${errors.allErrors}">
			                <li >
			                	<spring:message code="${error.code}" text="${error.defaultMessage}"/>&nbsp;&nbsp;
			                </li>
			            </c:forEach>
			            </ul>
					</td>
					</tr>
					</table>
				</td>
			</tr>
			</spring:hasBindErrors>
			
			<%-- Other errors (none validation errors) --%>
			<c:if test="${not empty model.errorMessage}">
			<tr>
				<td width="5%">&nbsp;</td>
				<td >
		           	<table align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
				 			<td >
				 				<ul class="isa_error text14" >
		                                  <li>[ERROR on Update] - Server return code: ${model.errorMessage}</li>                                    
		                              </ul>
				 			</td>
						</tr>
					</table>
				</td>
			</tr>
			</c:if>
	 	    
	 	    <tr>
				<td width="5%">&nbsp;</td>
				<td width="100%">
				<form action="tdsmaintenancefelles_svt056r_edit.do" name="formRecord" id="formRecord" method="POST" onSubmit="enableDisabledFields();">
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="updateId" id=updateId value="${model.updateId}"> <%-- this value is set in AJAX in order to know if the SAVE = ADD or UPDATE --%>
					<input type="hidden" name="action" id=action value="doUpdate">
					 
					<table width="95%" cellspacing="1" border="0" align="left">
						<tr height="5"><td></td></tr>
						<tr >
							<td><button name="newRecordButton" id="newRecordButton" class="inputFormSubmitStd" type="button" >Skapa ny</button></td>
						</tr>
						<tr height="20"><td></td></tr>
						<tr>
							<td class="text14" ><font class="text14RedBold" >*</font><span title="svth_sysg">Signatur&nbsp;</span>
								<c:choose>
									<c:when test="${not empty model.updateId}">
										<input readonly type="text" class="inputTextReadOnly"  name="svth_sysg" id="svth_sysg" size="5" maxlength="3" value='${model.record.svth_sysg}'>
									</c:when>
									<c:otherwise>
										<input type="text" class="inputTextMediumBlueMandatoryField"  name="svth_sysg" id="svth_sysg" size="5" maxlength="3" value='${model.record.svth_sysg}'>
									</c:otherwise>
								</c:choose>
								<a tabindex="-1" id="svth_sysgIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>
								&nbsp;&nbsp;<font class="text14RedBold" >*</font><span title="svth_namn">Navn&nbsp;</span>
								<input type="text" class="inputTextMediumBlueMandatoryField" name="svth_namn" id="svth_namn" size="36" maxlength="35" value='${model.record.svth_namn}'>
								
 	    						&nbsp;&nbsp;&nbsp;<font class="text14RedBold" >*</font><span title="syuser">Userid</span>
 	    						<input type="text" class="inputTextMediumBlueMandatoryField" name="svth_usid" id="svth_usid" size="11" maxlength="10" value="${model.record.svth_usid}" />
 	    						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 	    						<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Spara' onClick="setBlockUI(this);"/>
								
 	    					</td>
		    	    	</tr>
		    	    	<tr height="20"><td></td></tr>
					</table>
				</form>
				</td>
			</tr>

	 		</table>
		</td>
	</tr>
</table>	

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

