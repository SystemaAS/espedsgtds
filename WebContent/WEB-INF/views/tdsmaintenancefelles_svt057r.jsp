<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTdsMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tdsmaintenancefelles_svt057r.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
					<td width="20%" valign="bottom" class="tab" align="center">
						<font class="tabLink">Valutakurser</font>
						<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="tdsmaintenancefelles_svt057r.do?id=${model.dbTable}">
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
	 	    <tr height="30"><td>&nbsp;</td></tr>
	 	    
	 	    <tr >
	 	    	<td width="5%">&nbsp;</td>
				<td width="100%" class="text14">
					<form action="tdsmaintenancefelles_svt057r.do?id=${model.dbTable}" name="formRecordSearch" id="formRecordSearch" method="POST" >
					Kode&nbsp;
					<input type="text" class="inputTextMediumBlue" name="searchKode" id="searchKode" size="5" maxlength="3" value='${model.searchKode}'>
					<%--
					&nbsp;Søkebegrep&nbsp;
					<input type="text" class="inputTextMediumBlue" name="searchTaalfa" id="searchTaalfa" size="15" maxlength="25" value='${model.taalfa}'>
					--%>
					&nbsp;&nbsp;<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submitSearch" id="submitSearch" value='<spring:message code="search.label"/>'/>
					
					</form>
				</td>
			</tr>
			
			<%-- list component --%>
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="100%">
				<table id="containerdatatableTable" width="98%" cellspacing="1" border="0" align="left">
			    	    <tr>
						<td class="text11">
						<table id="mainList" class="display compact cell-border" >
							<thead>
							<tr>
								<th align="center" width="2%" class="tableHeaderField" >&nbsp;Ändra&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Kod&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Kurs&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Faktor&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Från datum&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Till datum&nbsp;</th>
			                    <th align="center" class="tableHeaderField">Ta bort</th>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <td id="recordUpdate_${record.svvk_kd}_${record.svvk_dts}" onClick="getRecord(this);" align="center" width="2%" class="tableCellFirst" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<img src="resources/images/update.gif" border="0" alt="edit">
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text14">&nbsp;${record.svvk_kd}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.svvk_krs}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.svvk_omr}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.svvk_dts}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.svvk_dte}&nbsp;</font></td>
				               
				               <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<a onclick="javascript:return confirm('Är du säker på at du vill ta bort denna?')" tabindex=-1 href="tdsmaintenancefelles_svt057r_edit.do?action=doDelete&id=${model.dbTable}&svvk_kd=${record.svvk_kd}&svvk_dts=${record.svvk_dts}">
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
		    
	 	    <tr height="25"><td>&nbsp;</td></tr>
	 	    
	 	    <%-- Validation errors --%>
			<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="100%">
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
				<td width="5">&nbsp;</td>
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
			<tr height="2"><td>&nbsp;</td>
			</tr>
			<tr >
				<td width="5%">&nbsp;</td>
				<td><button name="newRecordButton" id="newRecordButton" class="inputFormSubmitStd" type="button" >Skapa	ny</button></td>
			</tr>
	 	    <tr >
	 	    	<td width="5%">&nbsp;</td>
				<td width="100%">
				<form action="tdsmaintenancefelles_svt057r_edit.do" name="formRecord" id="formRecord" method="POST" >
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="updateId" id=updateId value="${model.updateId}"> 
					<input type="hidden" name="action" id=action value="doUpdate">
					
					<table width="80%" cellspacing="1" border="0" align="left">
			    	    <tr>
							<td class="text14" title="SVVK_KD">&nbsp;<font class="text14RedBold" >*</font>Kod</td>
							<td class="text14" title="SVVK_KRS">&nbsp;<font class="text14RedBold" >*</font>Kurs</td>
							<td class="text14" title="SVVK_OMR">&nbsp;<font class="text14RedBold" >*</font>Faktor</td>
							<td class="text14" title="SVVK_DTS">&nbsp;<font class="text14RedBold" >*</font>Från datum</td>
							<td class="text14" title="SVVK_DTE">&nbsp;Till datum</td>
						</tr>
						<tr>
						<td ><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="svvk_kd" id="svvk_kd" size="4" maxlength="3" value='${model.record.svvk_kd}'></td>
						<td ><input onKeyPress="return amountKey(event)" type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="svvk_krs" id="svvk_krs" size="10" maxlength="11" value='${model.record.svvk_krs}'></td>
						<td ><input onKeyPress="return numberKey(event)" type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="svvk_omr" id="svvk_omr" size="8" maxlength="7" value='${model.record.svvk_omr}'></td>
						<td ><input onKeyPress="return numberKey(event)" type="text" required class="inputTextMediumBlueMandatoryField" name="svvk_dts" id="svvk_dts" size="9" maxlength="8" value='${model.record.svvk_dts}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="svvk_dte" id="svvk_dte" size="9" maxlength="8" value='${model.record.svvk_dte}'></td>
						<td>
							<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Spara'/>
						</td>
						</tr>
						<tr height="3"><td></td>
					</table>
	 	    	</form>
	 	    </tr>
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	     
	 		</table>
		</td>
	</tr>
</table>	

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

