<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTdsMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tdsmaintenancenctsimport_svx001r.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>


<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr height="15"><td>&nbsp;</td></tr>
	<tr>
		<td>
		<%-- tab container component   --%>
		<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
				<tr height="25"> 
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkSadMaintExportGate" tabindex=-1 style="display:block;" href="tdsmaintenancenctsimport.do">
							<font class="tabDisabledLink">&nbsp;TDS - Underhåll</font>
							<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkSadMaintExportKoder" tabindex=-1 style="display:block;" href="tdsmaintenancenctsimport_kodergate.do?id=SVXKODF">
							<font class="tabDisabledLink">&nbsp;Underhåll - KODER</font>&nbsp;
							<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="30%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">&nbsp;Kodtyp&nbsp;<font class="text12">${model.tkunik}&nbsp;${model.legend}</font></font>&nbsp;
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
					</td>
					<td width="50%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
				</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<%-- space separator --%>
	 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
	 	    <tr height="30"><td>&nbsp;</td></tr>
	 	    
	 	    <%--
	 	    <tr >
	 	    	<td width="5%">&nbsp;</td>
				<td width="100%" class="text12">
					<form action="skatmaintenanceexport_dkg210d.do?id=${Xmodel.dbTable}" name="formRecordSearch" id="formRecordSearch" method="POST" >
					Kode&nbsp;
					<input type="text" class="inputTextMediumBlue" name="searchKode" id="searchKode" size="5" maxlength="3" value='${Xmodel.searchKode}'>
					&nbsp;&nbsp;<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submitSearch" id="submitSearch" value='<spring:message code="search.label"/>'/>
					</form>
				</td>
			</tr>
			--%>
			
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
								<th align="center" width="2%" class="tableHeaderField" >&nbsp;Uppd.&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Kod&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Text&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Text engelsk&nbsp;</th>
			                    <th align="center" class="tableHeaderField">Ta bort</th>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <td id="recordUpdate_${record.tkunik}_${record.tkkode}" onClick="getRecord(this);" align="center" width="2%" class="tableCellFirst" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<img src="resources/images/update.gif" border="0" alt="edit">
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.tkkode}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.tktxtn}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.tktxte}&nbsp;</font></td>
				               <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<a onclick="javascript:return confirm('Er du sikker på at du vil fjerne denne?')" tabindex=-1 href="tdsmaintenancenctsexport_svx001r_edit.do?action=doDelete&id=${model.dbTable}&tkunik=${record.tkunik}&tkkode=${record.tkkode}">
					               		<img valign="bottom" src="resources/images/delete.gif" border="0" width="15px" height="15px" alt="remove">
					               	</a>
				               </td>
				            </tr> 
				            </c:forEach>
				            </tbody>
				            <%--
				            <tfoot>
							<tr>
							    <th align="center" width="2%" class="tableHeaderFieldWhiteBg11" >&nbsp;Ändra&nbsp;</th>
								<th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TKKODE</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TKTXTN&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TKTXTE&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11"></th>
			                </tr>  
			                </tfoot>
			                 --%> 
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
			            <ul class="isa_error text12" >
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
				 				<ul class="isa_error text12" >
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
				<td><button name="newRecordButton" id="newRecordButton" class="inputFormSubmitStd" type="button" >Opret	 ny</button></td>
			</tr>
	 	    <tr >
	 	    	<td width="5%">&nbsp;</td>
				<td width="100%">
				<form action="tdsmaintenancenctsimport_svx001r_edit.do" name="formRecord" id="formRecord" method="POST" >
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="updateId" id=updateId value="${model.updateId}"> 
					<input type="hidden" name="action" id=action value="doUpdate">
					<input type="hidden" name="tkunik" id=tkunik value="${model.tkunik}"> 
					<input type="hidden" name="legend" id=legend value="${model.legend}"> 
					
					<table width="80%" cellspacing="1" border="0" align="left">
			    	    <tr>
							<td class="text12" title="TKKODE">&nbsp;<font class="text14RedBold" >*</font>Kode</td>
							<td class="text12" title="TKTXTN">&nbsp;Tekst</td>
							<td class="text12" title="TKTXTE">&nbsp;Tekst engelsk</td>
						</tr>
						<tr>
						<td ><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="tkkode" id="tkkode" size="11" maxlength="10" value='${model.record.tkkode}'></td>
						<td ><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="tktxtn" id="tktxtn" size="36" maxlength="35" value='${model.record.tktxtn}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="tktxte" id="tktxte" size="36" maxlength="35" value='${model.record.tktxte}'></td>
						<td>
							<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Spare'/>
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

