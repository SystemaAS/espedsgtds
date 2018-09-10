<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTds.jsp" />
<!-- =====================end header ==========================-->

	<SCRIPT type="text/javascript" src="resources/js/tdsglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>		
	<SCRIPT type="text/javascript" src="resources/js/nctsimport_logging.js?ver=${user.versionEspedsg}"></SCRIPT>
	
<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr>
		<td>
		<%-- tab container component --%>
		<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
			<tr height="25"> 
				<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkMainList" tabindex=-1 style="display:block;" href="nctsimport.do?action=doFind&sign=${model.sign}">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.ncts.import.list.tab"/></font>
						<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
						
					</a>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkHeader" tabindex=-1 style="display:block;" href="nctsimport_edit.do?action=doFetch&avd=${model.avd}&opd=${model.opd}
							&sysg=${model.sign}&syst=${model.status}&sydt=${model.datum}">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.ncts.import.created.mastertopic.tab"/></font>
						<font class="text14MediumBlue">[${model.opd}]</font>
						<c:if test="${ model.status == 'F' || model.status == 'M' || empty model.status}">
							<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						</c:if>
					</a>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkItemLines" tabindex=-1 style="display:block;" href="nctsimport_edit_items.do?action=doFetch&avd=${model.avd}&sign=${model.sign}
												&opd=${model.opd}&mrnNr=${model.mrnNr}&godsNr=${model.godsNr}
												&status=${model.status}&datum=${model.datum}">
						<font class="tabDisabledLink">
							&nbsp;<spring:message code="systema.ncts.import.item.createnew.tab"/>
						</font>
						<c:if test="${ model.status == 'F' || model.status == 'M' || empty model.status}">
							<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
						</c:if>
						
					</a>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkUnloading" tabindex=-1 style="display:block;" href="nctsimport_unloading_edit.do?avd=${model.avd}&sign=${model.sign}
												&opd=${model.opd}&mrnNr=${model.mrnNr}&godsNr=${model.godsNr}
												&status=${model.status}&datum=${model.datum}">
						<font class="tabDisabledLink">
							&nbsp;<spring:message code="systema.ncts.import.unloading.createnew.tab"/>
						</font>
						<img style="vertical-align: bottom" src="resources/images/unloading.png" width="16" hight="16" border="0" alt="show log">
					</a>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkUnloadingItemLines" tabindex=-1 style="display:block;" href="nctsimport_unloading_edit_items.do?action=doFetch&avd=${model.avd}&sign=${model.sign}
										&opd=${model.opd}&mrnNr=${model.mrnNr}&godsNr=${model.godsNr}
										&status=${model.status}&datum=${model.datum}">
						<font class="tabDisabledLink">
							&nbsp;<spring:message code="systema.ncts.import.unloading.item.createnew.tab"/>
						</font>
						<img style="vertical-align: bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="item lines">
					</a>
				</td>
									
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="12%" valign="bottom" class="tab" align="center" nowrap>
					<font class="tabLink">
						&nbsp;<spring:message code="systema.ncts.import.logging.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
				</td>
				
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkArchive" tabindex=-1 style="display:block;" href="nctsimport_archive.do?avd=${model.avd}&sign=${model.sign}
										&opd=${model.opd}&mrnNr=${model.mrnNr}&godsNr=${model.godsNr}
										&status=${model.status}&datum=${model.datum}">
						<font class="tabDisabledLink">
							&nbsp;<spring:message code="systema.ncts.import.archive.tab"/>
						</font>
						<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
					</a>
				</td>
				<td width="13%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			</tr>
			<tr height="3"><td></td></tr>
		</table>
		</td>
	</tr>
	
	<%-- 
	<tr>
		<td>
		<%-- search filter component 
		
 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 	        <tr height="3"><td></td></tr>
 	        <form name="tdsExportSearchForm" id="searchForm" action="tdsexport?action=doFind" method="post" >
 	        <tr>	
                <td class="text12" align="left" >&nbsp;&nbsp;&nbsp;<spring:message code="systema.tds.export.list.search.label.avd"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.tds.export.list.search.label.signatur"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.tds.export.list.search.label.arende"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.tds.export.list.search.label.tullid"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.tds.export.list.search.label.datum"/></td>
                <td class="text12" align="left" >
                <img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
                <spring:message code="systema.tds.export.list.search.label.status"/>
                <span style="position:absolute; left:1020px; top:150px; width:250px; height:400px;" id="status_info" class="popupWithInputText"  >
		           		<div class="text11" align="left">
		           		<br/>
		           		Endast <b>M</b> och <b>' '</b> kan editeras. Alla andra kan man bara titta på
		           		
		           			<ul>
		           				</li>
		           				<li><b>' '</b>&nbsp;Ärendet är öppet för ändring.
		           				<li><b>M</b>&nbsp;Tulltekniskt fel har uppdagats
		           				</li>
		           				<li><b>G</b>&nbsp;Ärendet är klarerat.
		           				</li>
		           				<li><b>S</b>&nbsp;Skickat till Signering.
		           				</li>
		           				<li><b>O</b>&nbsp;Ärendet är godkänt, skickat till vidare bearbetning.
		           				</li>
		           				<li><b>K</b>&nbsp;Kvitterat OK från mottagsfunktionen.
		           				</li>
		           				<li><b>F</b>&nbsp;Edifact-tekniskt fel har uppdagats.
		           				</li>
		           				<li><b>C</b>&nbsp;Ärendet är mottaget hos IBM. (Alla sändningar till IBM skickas nu till TDS).
		           				</li>
		           				<li><b>A</b>&nbsp;Ärendet ligger i en sändning i avvaktan på att bli skickat.
		           				</li>
		           				<li><b>+</b>&nbsp;Systemet skapar nu utgående Edifact meddelande för att kunna skicka ärendet.
		           				</li>
		           				<li><b>Q</b>&nbsp;Ärendet ligger nu i utgående brevlåda för TDS men är inte skickat. Kan ha väntekod satt!
		           				</li>
		           				<li><b>E</b>&nbsp;Ärendet blir ändrat av en handläggare. Om du arbetar med ett ärende och strömavbrott eller liknande inträffar kommer
		           							ärendet att "hänga" med status E.
		           				</li>
								
		           			</ul>
		           			
						</div>
					</span>	
                </td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.tds.export.list.search.label.avsandare"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.tds.export.list.search.label.mottagare"/></td>
                <td>&nbsp;</td>
			</tr>
 	        <tr>
				<td align="left" >&nbsp;
           			<select name="avd" id="avd">
	            		<option value="">-Välj-</option>
	 				  	<c:forEach var="record" items="${model.avdList}" >
                             	 	<option value="${record.avd}"<c:if test="${searchFilter.avd == record.avd}"> selected </c:if> >${record.avd}</option>
						</c:forEach> 
					</select>
				</td>
				<td align="left" >
           			<select name="sign" id="sign">
	            		<option value="">-Välj-</option>
	 				  	<c:forEach var="record" items="${model.signList}" >
                             	 	<option value="${record.sign}"<c:if test="${searchFilter.sign == record.sign}"> selected </c:if> >${record.sign}</option>
						</c:forEach> 
					</select>
				</td>
				<td align="left" ><input type="text" class="inputText" name="opd" size="10" maxlength="10" value='${searchFilter.opd}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="tullId" size="14" maxlength="35" value='${searchFilter.tullId}'>&nbsp;</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="datum" size="10" maxlength="8" value='${searchFilter.datum}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="status" size="2" maxlength="1" value='${searchFilter.status}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="avsNavn" size="10" maxlength="50" value='${searchFilter.avsNavn}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="motNavn" size="10" maxlength="50" value='${searchFilter.motNavn}'>&nbsp;</td>
				<td valign="top" align="left" >
                   &nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="search.label"/>'>
                   <img src="resources/images/find.png" border="0" alt="">
                </td>
			</tr>
			</form>
			<tr height="10"><td></td></tr>
		</table>
	</td>
	</tr>
	
	<tr height="3"><td></td></tr>
	<%-- Validation errors 
	<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller 
	<tr>
		<td>
           	<table width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
           	<tr>
			<td class="textError">					
	            <ul>
	            <c:forEach var="error" items="${errors.allErrors}">
	                <li >
	                	<spring:message code="${error.code}" text="${error.defaultMessage}"/>
	                </li>
	            </c:forEach>
	            </ul>
			</td>
			</tr>
			</table>
		</td>
	</tr>
	</spring:hasBindErrors>	
	
    --%>	
	<%-- list component --%>
	<tr>
		<td>		
		<table width="100%" cellspacing="0" border="0" cellpadding="0">
	    	<%-- separator --%>
	        <tr height="2"><td></td></tr> 
			<tr>
				<td>
				<table width="100%" cellspacing="0" border="0" cellpadding="0">
					<thead>
					<tr class="tableHeaderField" height="20" valign="left">
					
	                    <th class="tableHeaderFieldFirst">&nbsp;<spring:message code="systema.ncts.import.logging.list.label.topicNr"/>&nbsp;</th>
	                    <th class="tableHeaderField">&nbsp;Interch.nr&nbsp;</th>
						<th class="tableHeaderField">&nbsp;<spring:message code="systema.ncts.import.logging.list.label.messageNr"/>&nbsp;</th> 
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.ncts.import.logging.list.label.type"/>&nbsp;</th> 
	                      
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.ncts.import.logging.list.label.date"/>&nbsp;</th>
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.ncts.import.logging.list.label.time"/>&nbsp;</th>
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.ncts.import.logging.list.label.text"/>&nbsp;</th>
	               </tr> 
	               </thead>
	               <tbody>    
		           	<c:forEach items="${list}" var="record" varStatus="counter">    
		               <c:choose>           
		                   <c:when test="${counter.count%2==0}">
		                       <tr class="tableRow" height="20" >
		                   </c:when>
		                   <c:otherwise>   
		                       <tr class="tableOddRow" height="20" >
		                   </c:otherwise>
		               </c:choose>
		               <td class="tableCellFirst" >&nbsp;${record.mtdn}&nbsp;&nbsp;<font class="text10">[${model.sign}]&nbsp;-&nbsp;${model.mrnNr}</font></td>
		               <td class="tableCell">&nbsp;
		               		<a href="ediftplog.do?sssn=${record.msn}&ftplev=EDISS" target="_new" onClick="window.open(this.href,'targetWindow','top=200px,left=600px,height=800px,width=700px,scrollbars=no,status=no,location=no'); return false;">
		               			<img src="resources/images/bebullet.gif" border="0" alt="Vis Ftp log" >
		               			&nbsp;${record.msn}
		               		</a>
		               	</td>
		               <td class="tableCell" >&nbsp;
		               		<a href="ncts_import_renderEdifact.do?fp=${record.wurl}" target="_new" >
			               		<img src="resources/images/list.gif" border="0" width="12px" height="12px" alt="Visa Edifact" >
			               		&nbsp;${record.mmn}
	               		   	</a>
		               </td>
		               <td class="tableCell">&nbsp;${record.m1225}</td>
		               <td class="tableCell" >&nbsp;${record.mdt}</td>
		               <td class="tableCell" >&nbsp;${record.mtm}</td>
		               <td class="tableCell" >&nbsp;${record.wtxt}
		               		<c:if test="${record.wmore == 'X'}">
		               			&nbsp;&nbsp;
		               			<a href="ncts_import_renderLargeText.do?fmn=${record.mmn}" target="_blank" onClick="window.open(this.href,'targetWindow','toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=700,height=500'); return false;">
		               				<img valign="bottom" src="resources/images/largeTextContent.png" width="16" hight="16" border="0" alt="large text content">
		               			</a>
							</c:if>
		               
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
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

