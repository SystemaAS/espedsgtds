<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTdsMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tdsmaintenancenctsexport_svx030r.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
						<a id="alinkSadMaintNctsExportGate" tabindex=-1 style="display:block;" href="tdsmaintenancenctsexport.do">
						<font class="tabDisabledLink">&nbsp;TDS - Underhåll</font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="tdsmaintenancenctsexport_svx030r.do?id=SVXGH">
							<font class="tabDisabledLink">Garanti
							<img style="vertical-align: middle;"  src="resources/images/bulletGreen.png" border="0" width="8px" height="8px" alt="db table">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tab" align="center">
						<font class="tabLink">Friställning av Garanti
						<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="tdsmaintenancenctsexport_svx030r_fbrukt.do?id=SVXH">
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
	 	    <tr height="50"><td>&nbsp;</td></tr>
	 	    
	 	    <tr >
	 	    	<td width="5%">&nbsp;</td>
				<td width="100%" class="text14">
					<form action="tdsmaintenancenctsexport_svx030r_fbrukt.do?id=SVXH" name="formRecordSearch" id="formRecordSearch" method="POST" >
					
					<img onMouseOver="showPop('g_info');" onMouseOut="hidePop('g_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					Garantinr.&nbsp;
					<input type="text" class="inputTextMediumBlue" name="searchGaranti" id="searchGaranti" size="26" maxlength="25" value='${model.searchGaranti}'>
					&nbsp;&nbsp;<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submitSearch" id="submitSearch" value='<spring:message code="search.label"/>'/>
					
					&nbsp;&nbsp;&nbsp;
					<label nowrap class="inputTextMediumBlue11 isa_warning">
						Friställning av Garanti gäller endast för avslutade transiteringar (status: <b>P</b> = 029 eller <b>Z</b> = 045)
					</label>
					
					</form>
					
					<div class="text12" style="position: relative;" align="left">
 					<span style="position:absolute;top:2px; width:250px;" id="g_info" class="popupWithInputText text12"  >
	           			Sök på ett garantinr för att se alla aktiva och inte aktiva garantibelopp.	
						
					</span>		
		            </div>
				
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
								
								<th align="center" width="2%" class="tableHeaderField" >&nbsp;Uppd.&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Status&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Avd&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Ärende&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Sign&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Datum&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Fristdatum&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Garantinr&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Tillgångskod&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Garantibelopp&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Valuta&nbsp;</th>
								
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <c:choose>
					               <c:when test="${not empty record.thst && (record.thst == 'P' || record.thst == 'Z')}">
					               		<td id="recordUpdate_${record.thavd}_${record.thtdn}_${record.thsg}" onClick="getRecordReservedGuaranty(this);" align="center" width="2%" class="tableCellFirst" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
				               				<img src="resources/images/update.gif" border="0" alt="edit">
						               </td>	
					               </c:when>
					               <c:otherwise>
					               		<td style="cursor:crosshair" title="Aktiv garanti. Kan inte friställas än." align="center"><img src="resources/images/bulletGreen.png" width="10" height="10" border="0" alt="edit"></td>
						           </c:otherwise>
				               </c:choose>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text14">&nbsp;${record.thst}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text14">&nbsp;${record.thavd}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
				               
				               		<a href="nctsexport_edit.do?action=doFetch&avd=${record.thavd}&opd=${record.thtdn}&sysg=${record.thsg}&tuid=&syst=${record.thst}&sydt=${record.thdt}">
				               			&nbsp;${record.thtdn}&nbsp;
				               		</a>	
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.thsg}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.thdt}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.thddt}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.thgft1}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.thgadk}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.thgbl}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.thgvk}&nbsp;</font></td>
				               
				              
				            </tr> 
				            </c:forEach>
				            </tbody>
				            
			            </table>
					</td>	
					</tr>
				</table>
				</td>
			</tr>
		    
	 	    <tr height="15"><td>&nbsp;</td></tr>
	 	    
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
			<tr height="2"><td>&nbsp;</td></tr>
			</c:if>

			
	 	    <tr >
	 	    	<td width="5%">&nbsp;</td>
				<td width="100%">
				<form action="tdsmaintenancenctsexport_svx030r_fbrukt_edit.do" name="formRecord" id="formRecord" method="POST" >
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="searchGaranti" id="searchGaranti" value="${model.searchGaranti}">
					
					<table width="60%" cellspacing="1" border="0" align="left">
						<tr>
								<td class="text14" title="thst">&nbsp;Status</td>
								<td class="text14" title="thavd">&nbsp;Avd</td>
								<td class="text14" title="thtdn">&nbsp;Ärende</td>
								<td class="text14" title="thsg">&nbsp;Sign</td>
								<td class="text14" title="thdt">&nbsp;Datum</td>
								<td class="text14" title="thddt">&nbsp;Fristdatum</td>
								<td class="text14" title="thgft1">&nbsp;Garantinr</td>
								<td class="text14" title="thgbl">&nbsp;Belopp</td>
						</tr>
						<tr>	
								<td ><input type="text" readonly  class="inputTextReadOnly" name="thst" id="thst" size="2" value='' /></td>
								<td ><input type="text" readonly  class="inputTextReadOnly" name="thavd" id="thavd" size="8" value='' /></td>
								<td ><input type="text" readonly  class="inputTextReadOnly" name="thtdn" id="thtdn" size="8" value='' /></td>
								<td ><input type="text" readonly  class="inputTextReadOnly" name="thsg" id="thsg" size="5" value='' /></td>
								<td ><input type="text" readonly  class="inputTextReadOnly" name="thdt" id="thdt" size="10" value='' /></td>
								<td ><input type="text" readonly  class="inputTextReadOnly" name="thddt" id="thddt" size="10" value='' /></td>
								<td ><input type="text" readonly  class="inputTextReadOnly" name="thgft1" id="thgft1" size="30" value='' /></td>
								<td ><input type="text" readonly  class="inputTextReadOnly" name="thgbl" id="thgbl" size="20" value='' /></td>
								<td><input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Friställ'/></td>				
						</tr>
									
									
					</table>
					</form>
				</td>
			</tr>
			<tr>
				
			</tr>
			<tr height="3"><td></td>
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	     
	 		</table>
		</td>
	</tr>
</table>	

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

