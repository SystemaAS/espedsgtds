<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTdsMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tdsmaintenancefelles_svt058r.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
						<font class="tabLink">Leveransvillkor</font>
						<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="tdsmaintenancefelles_svt058r.do?id=${model.dbTable}">
							<img style="vertical-align: middle;"  src="resources/images/bulletGreen.png" border="0" width="8px" height="8px" alt="db table">
						</a>
					</td>
					<td width="65%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
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
					<form action="tdsmaintenancefelles_svt058r.do?id=${model.dbTable}" name="formRecordSearch" id="formRecordSearch" method="POST" >
					Kode&nbsp;
					<input type="text" class="inputTextMediumBlue" name="searchKode" id="searchKode" size="5" maxlength="3" value='${model.searchKode}'>
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
									<th rowspan="2" align="center" width="2%" class="tableHeaderField" >&nbsp;Ändra&nbsp;</th>
									<th rowspan="2" class="tableHeaderField" >&nbsp;Kod&nbsp;</th>
				                    <th colspan="2" class="tableHeaderField" >&nbsp;Transport&nbsp;</th>
									<th colspan="2" class="tableHeaderField" >&nbsp;Försäkring&nbsp;</th>
									<th rowspan="2" class="tableHeaderField" >&nbsp;Övr.kost.&nbsp;</th>
									<th rowspan="2" class="tableHeaderField" >&nbsp;Kassarab.&nbsp;</th>
									<th rowspan="2" class="tableHeaderField" >&nbsp;Annan rab.&nbsp;</th>
									<th rowspan="2" align="center" class="tableHeaderField">Ta bort</th>
				                </tr>
				                <tr>
				                	<th class="text12">Stat.värde</th>
            						<th class="text12">Tullvärde</th>
            						<th class="text12">Stat.värde</th>
            						<th class="text12">Tullvärde</th>
            					</tr>	  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <td id="recordUpdate_${record.svlv_kd}" onClick="getRecord(this);" align="center" width="2%" class="tableCellFirst" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<img src="resources/images/update.gif" border="0" alt="edit">
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text14">&nbsp;${record.svlv_kd}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.svlv_trText}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.svlv_tr2Text}&nbsp;</font></td>
				               <td align="center" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
				               		<table width="70%" >
				               			<tr >
				               				<td style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="left" ><font class="text14">&nbsp;&nbsp;${record.svlv_fsText}</font></td>
					               			<td style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;"	 align="right" ><font class="text14MediumBlue">${record.svlv_fspFormatted}&nbsp;</font></td>
				               			</tr>
				               		</table>
				               	</td>
				               <td align="center" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
				               		<table width="70%" >
				               			<tr >
				               				<td style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="left" ><font class="text14">&nbsp;&nbsp;${record.svlv_fs2Text}</font></td>
					               			<td style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="right" ><font class="text14MediumBlue">${record.svlv_fs2pFormatted}&nbsp;</font></td>
				               			</tr>
				               		</table>
				               </td>
				               
				               <td width="4%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.svlv_okText}&nbsp;</font></td>
				               <td width="4%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.svlv_krText}&nbsp;</font></td>
				               <td width="4%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.svlv_arText}&nbsp;</font></td>
				               
				               <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<a onclick="javascript:return confirm('Är du säker på at du vill ta bort denna?')" tabindex=-1 href="tdsmaintenancefelles_svt058r_edit.do?action=doDelete&id=${model.dbTable}&svlv_kd=${record.svlv_kd}">
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
				<td><button name="newRecordButton" id="newRecordButton" class="inputFormSubmitStd" type="button" >Skapa ny</button></td>
			</tr>
	 	    <tr >
	 	    	<td width="5%">&nbsp;</td>
				<td width="100%">
				<form action="tdsmaintenancefelles_svt058r_edit.do" name="formRecord" id="formRecord" method="POST" >
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="updateId" id=updateId value="${model.updateId}"> 
					<input type="hidden" name="action" id=action value="doUpdate">
					
					<table width="99%" cellspacing="1" border="0" align="left">
			    	    <tr>
							<td width="8%" class="text14" title="svlv_kd">&nbsp;<font class="text14RedBold" >*</font>Kod</td>
							<td class="text14" title="svlv_tr">&nbsp;Transport - Stat.värde/Tullvärde</td>
							<td class="text14" title="svlv_tr2">&nbsp;Försäkring - Stat.värde/Tullvärde</td>
							<td class="text14" title="svlv_ok">&nbsp;Övr.kost.</td>
							<td class="text14" title="svlv_kr">&nbsp;Kassarab.</td>
							<td class="text14" title="svlv_ar">&nbsp;Annan rab.</td>
						</tr>
						<tr>
							<td width="8%" ><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="svlv_kd" id="svlv_kd" size="4" maxlength="3" value='${model.record.svlv_kd}'></td>
							<td class="text14" >Stat.v.
								<select name="svlv_tr" id="svlv_tr">
									<option value="" >-Välj-</option>
			 						<option value="J" <c:if test="${model.record.svlv_tr == 'J'}"> selected </c:if> >Ja</option>
			 						<option value="N" <c:if test="${model.record.svlv_tr == 'N'}"> selected </c:if> >Nej</option>
								</select>&nbsp;
								Tullv.
								<select name="svlv_tr2" id="svlv_tr2">
									<option value="" >-Välj-</option>
			 						<option value="J" <c:if test="${model.record.svlv_tr2 == 'J'}"> selected </c:if> >Ja</option>
			 						<option value="N" <c:if test="${model.record.svlv_tr2 == 'N'}"> selected </c:if> >Nej</option>
								</select>						
							</td>
							<td class="text14">Stat.v
								<select name="svlv_fs" id="svlv_fs">
									<option value="" >-Välj-</option>
			 						<option value="J" <c:if test="${model.record.svlv_fs == 'J'}"> selected </c:if> >Ja</option>
			 						<option value="N" <c:if test="${model.record.svlv_fs == 'N'}"> selected </c:if> >Nej</option>
			 						<option value="P" <c:if test="${model.record.svlv_fs == 'N'}"> selected </c:if> >Procent</option>
								</select>	
								<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="svlv_fsp" id="svlv_fsp" size="7" maxlength="6" value='${model.record.svlv_fs}'>
								Tullv.
								<select name="svlv_fs2" id="svlv_fs2">
									<option value="" >-Välj-</option>
			 						<option value="J" <c:if test="${model.record.svlv_fs2 == 'J'}"> selected </c:if> >Ja</option>
			 						<option value="N" <c:if test="${model.record.svlv_fs2 == 'N'}"> selected </c:if> >Nej</option>
			 						<option value="P" <c:if test="${model.record.svlv_fs2 == 'N'}"> selected </c:if> >Procent</option>
								</select>
								<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="svlv_fs2p" id="svlv_fs2p" size="7" maxlength="6" value='${model.record.svlv_fs2p}'>
							</td>
							<td >
								<select name="svlv_ok" id="svlv_ok">
									<option value="" >-Välj-</option>
			 						<option value="J" <c:if test="${model.record.svlv_ok == 'J'}"> selected </c:if> >Ja</option>
			 						<option value="N" <c:if test="${model.record.svlv_ok == 'N'}"> selected </c:if> >Nej</option>
								</select>							
							</td>
							<td >
								<select name="svlv_kr" id="svlv_kr">
									<option value="" >-Välj-</option>
			 						<option value="J" <c:if test="${model.record.svlv_kr == 'J'}"> selected </c:if> >Ja</option>
			 						<option value="N" <c:if test="${model.record.svlv_kr == 'N'}"> selected </c:if> >Nej</option>
								</select>		
							</td>
							<td >
								<select name="svlv_ar" id="svlv_ar">
									<option value="" >-Välj-</option>
			 						<option value="J" <c:if test="${model.record.svlv_ar == 'J'}"> selected </c:if> >Ja</option>
			 						<option value="N" <c:if test="${model.record.svlv_ar == 'N'}"> selected </c:if> >Nej</option>
								</select>
							</td>
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

