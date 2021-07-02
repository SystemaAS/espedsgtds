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
					<td width="20%" valign="bottom" class="tab" align="center">
						<font class="tabLink">Garanti
						<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="tdsmaintenancenctsexport_svx030r.do?id=${model.dbTable}">
							<img style="vertical-align: middle;"  src="resources/images/bulletGreen.png" border="0" width="8px" height="8px" alt="db table">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="tdsmaintenancenctsexport_svx030r_fbrukt.do?id=SVXH">
							<font class="tabDisabledLink">Friställning av Garanti
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
					<form action="tdsmaintenancenctsexport_svx030r.do?id=${model.dbTable}" name="formRecordSearch" id="formRecordSearch" method="POST" >
					Garantinr.&nbsp;
					<input type="text" class="inputTextMediumBlue" name="searchGaranti" id="searchGaranti" size="26" maxlength="25" value='${model.searchGaranti}'>
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
								<th align="center" width="2%" class="tableHeaderField" >&nbsp;Uppd.&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Typ&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Garantinr.&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Tullkontor&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Sändningsdatum&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Status&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Företagsnr.&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Ny tillgångskod&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Gml.tillgångskod&nbsp;</th>
								<th align="center" class="tableHeaderField">Ta bort</th>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <td id="recordUpdate_${record.tggnr}_${Xrecord.svvk_dts}" onClick="getRecord(this);" align="center" width="2%" class="tableCellFirst" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<img src="resources/images/update.gif" border="0" alt="edit">
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text14">&nbsp;${record.tggty}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text14">&nbsp;${record.tggnr}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.tgtsd}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.tgdt}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.tgst}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.tgtina}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.tgakny}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.tgakgm}&nbsp;</font></td>
				               
				               <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<a onclick="javascript:return confirm('Är du säker på att du vill ta bort denna?')" tabindex=-1 href="tdsmaintenancenctsexport_svx030r_edit.do?action=doDelete&id=${model.dbTable}&tggnr=${record.tggnr}">
					               		<img valign="bottom" src="resources/images/delete.gif" border="0" width="15px" height="15px" alt="remove">
					               	</a>
				               </td>
				            </tr> 
				            </c:forEach>
				            </tbody>
				            <%--
				            <tfoot>
							<tr>
							    <th align="center" width="2%" class="tableHeaderFieldWhiteBg11" >&nbsp;Uppd.&nbsp;</th>
							    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TGGTY</th>
								<th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TGGNR</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TGTSD&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TGDT&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TGST&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TGTINA&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TGAKNY&nbsp;</th>
			                    <th align="center" class="tableHeaderFieldWhiteBg11" >&nbsp;TGAKGM&nbsp;</th>
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

			</tr>
			<tr >
				<td width="5%">&nbsp;</td>
				<td><button name="newRecordButton" id="newRecordButton" class="inputFormSubmitStd" type="button" >Skapa ny</button></td>
			</tr>
			<tr height="2"><td>&nbsp;</td></tr>
	 	    <tr >
	 	    	<td width="5%">&nbsp;</td>
				<td width="100%">
				<form action="tdsmaintenancenctsexport_svx030r_edit.do" name="formRecord" id="formRecord" method="POST" >
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="updateId" id=updateId value="${model.updateId}"> 
					<input type="hidden" name="action" id=action value="doUpdate">
					
					<table class="tableHeaderField" width="98%" cellspacing="1" border="0" align="left">
						<tr height="5"><td>&nbsp;</td></tr>
						
						<tr>
							<%-- LEFT CELL --%>
							<td width="50%" valign="top">
								<table width="100%" cellspacing="1" border="0" align="left">
									<tr>
										<td class="text14" title="tgkna">&nbsp;Huvudansvarig</td>
										<td ><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="tgkna" id="tgkna" size="9" maxlength="8" value='${model.record.tgkna}'></td>
									</tr>
									<tr>	
										<td class="text14" title="tgtina">&nbsp;<font class="text14RedBold" >*</font>Företagsnr.</td>
										<td ><input type="text" required  oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="tgtina" id="tgtina" size="18" maxlength="17" value='${model.record.tgtina}' /></td>
									</tr>
									<tr>
										<td class="text14" title="tgnaa">&nbsp;<font class="text14RedBold" >*</font>Namn</td>
										<td ><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="tgnaa" id="tgnaa" size="31" maxlength="30" value='${model.record.tgnaa}' /></td>
									</tr>
									<tr>	
										<td class="text14" title="tgada1">&nbsp;Adress 1</td>
										<td ><input type="text" class="inputTextMediumBlue" name="tgada1" id="tgada1" size="31" maxlength="30" value='${model.record.tgada1}'></td>
									</tr>
									
									<tr>	
										<td class="text14" title="tgpna/tgpsa">&nbsp;<font class="text14RedBold" >*</font>Postnr/Postadr.</td>
										<td >
											<input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="tgpna" id="tgpna" size="10" maxlength="9" value='${model.record.tgpna}'>
											<input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="tgpsa" id="tgpsa" size="25" maxlength="24" value='${model.record.tgpsa}'>
										</td>
									</tr>
									<tr>	
										<td class="text14" title="tglka">&nbsp;<font class="text14RedBold" >*</font>Landkod</td>
										<td ><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="tglka" id="tglka" size="2" maxlength="2" value='${model.record.tglka}'></td>
									</tr>
									
								</table>
							</td>
							<%-- RIGHT CELL --%>
							<td width="50%" valign="top">
								<table width="100%" cellspacing="1" border="0" align="left">
									<tr>
										<td class="text14" title="tgtsd">&nbsp;<font class="text14RedBold" >*</font>Garantitullkontor:</td>
										<td ><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="tgtsd" id="tgtsd" size="9" maxlength="8" value='${model.record.tgtsd}'></td>
									</tr>
									<tr>	
										<td class="text14" title="tggty/tggnr">&nbsp;<font class="text14RedBold" >*</font>Garantityp / Garantinr.</td>
										<td >
											<input type="text" onKeyPress="return numberKey(event)" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="tggty" id="tggty" size="2" maxlength="1" value='${model.record.tggty}'>
											<input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="tggnr" id="tggnr" size="25" maxlength="24" value='${model.record.tggnr}'>
										</td>
									</tr>
									<tr>	
										<td class="text14" title="tggfv">&nbsp;Gjeld f.vare</td>
										<td class="text14" >
											<select name="tggfv" id="tggfv" >
		 					  					<option value="">-velg-</option>
		 					  					<option value="J"<c:if test="${ model.record.tggfv == 'J'}"> selected </c:if> >Ja</option>
							  					<option value="N"<c:if test="${ model.record.tggfv == 'N'}"> selected </c:if> >Nej</option>
							  				</select>
										</td>
									</tr>
									<tr>
										<td class="text14" title="tgakny">&nbsp;<font class="text14RedBold" >*</font>Ny tillgångskod</td>
										<td class="text14" >
											<input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="tgakny" id="tgakny" size="5" maxlength="4" value='${model.record.tgakny}'>
											&nbsp;&nbsp;&nbsp;<font class="text14RedBold" >*</font><span title="tgakgm">Gml. tillgångskod</span>
											<input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="tgakgm" id="tgakgm" size="5" maxlength="4" value='${model.record.tgakgm}'>
										</td>
									</tr>
									<tr>
										<td class="text14" title="tggbl">&nbsp;<font class="text14RedBold" >*</font>Garantibelopp</td>
										<td class="text14" >
											<input type="text" onKeyPress="return numberKey(event)" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="tggbl" id="tggbl" size="12" maxlength="11" value='${model.record.tggbl}'>
											&nbsp;&nbsp;&nbsp;<font class="text14RedBold" >*</font><span title="tggvk">Valuta</span>
											<select required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="tggvk" id="tggvk" >
		 					  					<option value="">-velg-</option>
												<c:forEach var="record" items="${model.currencyList}" >
							 				  		<option value="${record.svvk_kd}"<c:if test="${model.record.tggvk == record.svvk_kd}"> selected </c:if> >${record.svvk_kd}</option>
												</c:forEach> 
							  					
							  				</select>
										</td>
									</tr>
									<tr>
										<td class="text14" title="tggblb">&nbsp;Förbruk.garantibelopp</td>
										<td >
											<input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="tggblb" id="tggblb" size="12" maxlength="11" value='${model.record.tggblb}'>
										</td>
									</tr>
									<tr>
										<td class="text14" title="tgprm">&nbsp;Varning</td>
										<td >
											<input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="tgprm" id="tgprm" size="4" maxlength="3" value='${model.record.tgprm}'>
											&nbsp;<font class="text11">(När det är användt mer än X %)</font>
										</td>
									</tr>
									<tr>
										<td class="text14" title="tgst">&nbsp;Status</td>
										<td >
											<input type="text" class="inputTextMediumBlue" name="tgst" id="tgst" size="2" maxlength="1" value='${model.record.tgst}'>
											
										</td>
									</tr>	
								</table>
							</td>
						</tr>
						<tr>
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

