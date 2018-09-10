<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTds.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tdsglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/nctsimport_unloading_edit.js?ver=${user.versionEspedsg}"></SCRIPT>
 
 	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
<form name="nctsImportSaveNewTopicForm" id="nctsImportSaveNewTopicForm" method="post">

<table width="100%" cellspacing="0" border="0" cellpadding="0">
	
 <tr>
 <td>	
	<%-- tab container component --%>
	<table width="100%"  class="text12" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkMainList"  tabindex=-1 style="display:block;" href="nctsimport.do?action=doFind&sign=${recordTopic.tisg}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.ncts.import.list.tab"/></font>
					<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkHeader"  tabindex=-1 style="display:block;" href="nctsimport_edit.do?action=doFetch&avd=${recordTopic.tiavd}&opd=${recordTopic.titdn}
						&sysg=${recordTopic.tisg}&syst=${recordTopic.tist}&sydt=${recordTopic.tidt}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.ncts.import.created.mastertopic.tab"/></font>
					<font class="text14MediumBlue">[${recordTopic.titdn}]</font>
					<c:if test="${ recordTopic.tist == 'F' || recordTopic.tist == 'M' || empty recordTopic.tist}">
						<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
					</c:if>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkItemLines"  tabindex=-1 style="display:block;" href="nctsimport_edit_items.do?action=doFetch&avd=${recordTopic.tiavd}&sign=${recordTopic.tisg}
											&opd=${recordTopic.titdn}&mrnNr=${recordTopic.titrnr}&godsNr=${recordTopic.tign}
											&status=${recordTopic.tist}&datum=${recordTopic.tidt}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.ncts.import.item.createnew.tab"/>
					</font>
					<c:if test="${ recordTopic.tist == 'F' || recordTopic.tist == 'M' || empty recordTopic.tist}">
						<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
					</c:if>
					
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">
					&nbsp;<spring:message code="systema.ncts.import.unloading.createnew.tab"/>
				</font>
				<img style="vertical-align: bottom" src="resources/images/unloading.png" width="16" hight="16" border="0" alt="show log">
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkUnloadingItemLines"  tabindex=-1 style="display:block;" href="nctsimport_unloading_edit_items.do?action=doFetch&avd=${recordTopic.tiavd}&sign=${recordTopic.tisg}	
											&opd=${recordTopic.titdn}&mrnNr=${recordTopic.titrnr}&godsNr=${recordTopic.tign}
											&status=${recordTopic.tist}&datum=${recordTopic.tidt}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.ncts.import.unloading.item.createnew.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="item lines">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkLogging"  tabindex=-1 style="display:block;" href="nctsimport_logging.do?avd=${recordTopic.tiavd}&sign=${recordTopic.tisg}
									&opd=${recordTopic.titdn}&mrnNr=${recordTopic.titrnr}&godsNr=${recordTopic.tign}
									&status=${recordTopic.tist}&datum=${recordTopic.tidt}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.ncts.import.logging.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkArchive"  tabindex=-1 style="display:block;" href="nctsimport_archive.do?avd=${recordTopic.tiavd}&sign=${recordTopic.tisg}
									&opd=${recordTopic.titdn}&mrnNr=${recordTopic.titrnr}&godsNr=${recordTopic.tign}
									&status=${recordTopic.tist}&datum=${recordTopic.tidt}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.ncts.import.archive.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
				</a>
			</td>
			<td width="13%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
		</tr>
	</table>
	</td>
 </tr>
 
 <tr>
 	<td>
	<%-- --------------------------- --%>	
 	<%-- tab area container PRIMARY  --%>
	<%-- --------------------------- --%>
	<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 		<tr height="3"><td colspan="2">&nbsp;</td></tr>
 		<%-- GENERAL HIDDEN --%> 
	    <input type="hidden" name="thmf" id="thmf" value="015">
			
		<%-- --- HIDDEN FORM FIELDS (not visible in form but important with an UPDATE ----- --%>			
		<%-- general (from user profile) --%>
		<input type="hidden" name="action" id="action" value='doUpdate'>
		<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
		<input type="hidden" name="opd" id="opd" value='${recordTopic.titdn}'>
		<%-- topic specific (syop and tuid) --%>
		<input type="hidden" name="tiavd" id="tiavd" value='${recordTopic.tiavd}'>
		<input type="hidden" name="titdn" id="titdn" value='${recordTopic.titdn}'>
		<input type="hidden" name="tisg" id="tisg" value='${recordTopic.tisg}'>
		<input type="hidden" name="tist" id="tist" value='${recordTopic.tist}'>
		<input type="hidden" name="tidt" id="tidt" value='${recordTopic.tidt}'>
    		<input type="hidden" name="avd" id="avd" value='${recordTopic.tiavd}'>
		<input type="hidden" name="sign" id="sign" value='${recordTopic.tisg}'>
		<tr >
			<td colspan="3" align="left" class="text14MediumBlue">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Avd:&nbsp;<b>${recordTopic.tiavd}</b>&nbsp;&nbsp;<span title="titdn">Ärende:</span>&nbsp;<b>${recordTopic.titdn}</b>
				&nbsp;&nbsp;&nbsp;<span title="titrnr">MRN-nr:</span>&nbsp;<b>${recordTopic.titrnr}</b>&nbsp;&nbsp;Gods-nr:&nbsp;<b>${recordTopic.tign}</b>
			</td>
		</tr>
		<tr >
			<td align="left" class="text14MediumBlue">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[&nbsp;<span title="tisg">Sign:</span>&nbsp;<b>${recordTopic.tisg}</b>,&nbsp;&nbsp;<span title="tidt">Datum:</span>&nbsp;<b>${recordTopic.tidt}</b>
				&nbsp;
				<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
           		<span title="tist">Status:</span>&nbsp;<b>${recordTopic.tist}</b>
				&nbsp;&nbsp;
				<span title="tienkl">Typ av förfarande:</span>&nbsp;
				<c:if test="${recordTopic.tienkl == 'J'}"><b>Förenklad</b></c:if>
				<c:if test="${recordTopic.tienkl == 'N'}"><b>Normal</b></c:if>  
				
			</td>
				<span style="position:absolute; left:800px; top:150px; width:250px; height:520px;" id="status_info" class="popupWithInputText"  >
					<div class="text11" align="left">
		           		<br/>
		           		Endast <b>M</b>, F eller <b>' '</b> kan editeras. Alla andra kan man bara titta på.
		           			<ul>
		           				<li><b>' '</b>&nbsp;Ärendet är öppet för ändring.</li>
		           				<li><b>+</b>&nbsp;Temporärt arbetsstatus. Alla som, på ett viss tidpunkt, hade Q-status är nu klara för sändning.
		           				Systemet skapar nu utgående Edifact meddelande för att kunna skicka ärendet.
		           				</li>
		           				<li><b>A</b>&nbsp;Ärendet ligger i en sändning i avvaktan på att bli skickat.</li>
		           				<li><b>C</b>&nbsp;Ärendet är mottaget hos EDI-nätverket. (Alla sändningar till  EDI-nätverket skickas nu till Tullverket).
		           				</li>
								<li><b>E</b>&nbsp;Ärendet blir ändrat av en handläggare. Om du arbetar med ett ärende och strömavbrott eller liknande inträffar kommer
		           							ärendet att "hänga" med status E.</li>
								<li><b>F</b>&nbsp;Edifact-tekniskt fel har uppdagats.</li>
								<li><b>H</b>&nbsp;Ärendet har avvisats (IE58 eller Control) pga av lossningsanmärkningar</li>
								<li><b>K</b>&nbsp;Sigillering OK.</li>
								<li><b>L</b>&nbsp;Lossningsanm.har sänts (IE44)</li>
								<li><b>M</b>&nbsp;Tulltekniskt fel har uppdagats (IE08).</li>
		           				<li><b>N</b>&nbsp;EDIFACT OK.</li>
								<li><b>P</b>&nbsp;Transiteringen är avslutat (IE25 är mottaget).</li>
								<li><b>Q</b>&nbsp;Ärendet ligger nu i utgående brevlåda för TDS men är inte skickat. Kan ha väntekod satt!</li>
		           				<li><b>S</b>&nbsp;Skickat till Signering.</li>
		           				<li><b>U</b>&nbsp;Har fått lossningstillstånd. (IE43).</li>
								<li><b>V</b>&nbsp;IE43 är mottaget.</li>
		           				<li><b>Z</b>&nbsp;Transitering är avslutat/kvi	tterat (IE45)</li>
								<li><b>Å</b>&nbsp;Temporärt (...arbete).</li>
		           			</ul>
						</div>
				</span>	                
		</tr>	

		<tr height="10"><td colspan="2">&nbsp;</td></tr>
		<%-- --------------- --%>
		<%-- CONTENT --%>
		<%-- --------------- --%>
		<tr>
		<td >
		<table align="center" width="98%" border="0" cellspacing="1" cellpadding="0">
			
			<%-- Validation errors --%>
			<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
			<tr>
				<td width="5">&nbsp;</td>
				<td colspan="10">
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
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
			
			<%-- Other errors (none validation errors) --%>
			<c:if test="${not empty model.errorMessage}">
			<tr>
				<td width="5">&nbsp;</td>
				<td colspan="10">
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
				 			<td class="textError">
				 				<ul>
                                    <li>
                                      	Fel vid uppdatering ${model.errorMessage}]  
                                    </li>
                                    <li>
                                      	[META-INFO: ${model.errorInfo}]  
                                    </li>
                                    
                                </ul>
				 			</td>
						</tr>
					</table>
				</td>
			</tr>
			</c:if>

		<tr>
			<td width="50%"class="text14" valign="top">
				<table width="90%" align="left" border="0" cellspacing="1" cellpadding="0">
				 	<tr >
					 	<td >
						<table width="100%" class="formFrameHeader" border="0" cellspacing="1" cellpadding="0">
					 		<tr height="15">
					 			<td class="text14White">
					 				&nbsp;Näringliv - Ansvarige&nbsp;
				 				</td>
			 				</tr>
			            </table>
			            </td>
		            </tr>
		            <tr >
					 	<td>
						<table width="100%" class="formFrame" border="0" cellspacing="2" cellpadding="1">
					 		<tr >
					 			<td class="text14">&nbsp;<span title="tikn">Kundnr</span></td>
					 			<td class="text14">&nbsp;</td>
			 				</tr>
			 				<tr >
					 			<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="tikn" id="tikn" size="8" maxlength="8" value="${recordTopic.tikn}"></td>
					 			<td class="text14">&nbsp;</td>
			 				</tr>
			 				<tr >
					 			<td class="text14">&nbsp;<span title="titin">TIN</span></td>
					 			<td class="text14">&nbsp;<span title="tina">Namn</span></td>
			 				</tr>
			 				<tr >
					 			<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="titin" id="titin" size="17" maxlength="17" value="${recordTopic.titin}"></td>
					 			<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="tina" id="tina" size="20" maxlength="30" value="${recordTopic.tina}"></td>
			 				</tr>
			 				<tr >
					 			<td class="text14">&nbsp;<span title="tiad1">Adress</span></td>
					 			<td class="text14">&nbsp;<span title="tisk">Språkkod</span></td>
			 				</tr>
			 				<tr >
					 			<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="tiad1" id="tiad1" size="30" maxlength="30" value="${recordTopic.tiad1}"></td>
					 			<td ><input readonly type="text" class="inputTextMediumBlue" name="tisk" id="tisk" size="2" maxlength="2" value="${recordTopic.tisk}"></td>
			 				</tr>
			 				<tr >
					 			<td class="text14">&nbsp;<span title="tips">Postadress</span></td>
					 			<td class="text14">&nbsp;<span title="tipn">Postnr</span></td>
			 				</tr>
			 				<tr >
					 			<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="tips" id="tips" size="24" maxlength="24" value="${recordTopic.tips}"></td>
					 			<td class="text14"><input readonly type="text" class="inputTextMediumBlue" name="tipn" id="tipn" size="9" maxlength="9" value="${recordTopic.tipn}"></td>
			 				</tr>
			 				<tr >
					 			<td class="text14">&nbsp;<span title="tilk">Landkod</span></td>
					 			<td class="text14">&nbsp</td>
			 				</tr>
			 				<tr >
			 					<td ><input readonly type="text" class="inputTextMediumBlue" name="tilk" id="tilk" size="2" maxlength="2" value="${recordTopic.tilk}"></td>
					 			<td class="text14">
					 				&nbsp;
					 			</td>
			 				</tr>
			            </table>
			            </td>
		            </tr>
	            
	            </table>
            </td>
            
            	<td width="50%"class="text14" valign="top">
				<table width="100%" align="center" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td valign="top">
						<table border="0" cellspacing="1" cellpadding="0">
						<tr>
				            <td >&nbsp;</td>
				            <td ><span title="tign">Godsnr</span></td>
				            <td colspan="3" ><input readonly type="text" class="inputText" name="tign" id="tign" size="36" maxlength="35" value="${recordTopic.tign}"></td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="tignsk">Språkkod</span>&nbsp;</td>
				            <td ><input readonly type="text" class="inputText" name="tignsk" id="tignsk" size="2" maxlength="2" value="${recordTopic.tignsk}"></td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="titrnr">MRN-nr</span></td>
				            <td colspan="3"><input readonly type="text" class="inputText" name="titrnr" id="titrnr" size="36" maxlength="35" value="${recordTopic.titrnr}"></td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="tialk">Avs.land</span>&nbsp;</td>
				            <td ><input readonly type="text" class="inputText" name="tialk" id="tialk" size="2" maxlength="2" value="${recordTopic.tialk}"></td>
				            <td >&nbsp;</td>
			            </tr>
			            <tr height="10"><td>&nbsp;</td></tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="tialsk">Avt.lag.plats (kod)</span></td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tialsk" id="tialsk" size="17" maxlength="17" value="${recordTopic.tialsk}"></td>
				            <td ><span title="tialss">Språkkod</span>&nbsp;</td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tialss" id="tialss" size="2" maxlength="2" value="${recordTopic.tialss}"></td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="tials">Avt.lag.plats</span></td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tials" id="tials" size="20" maxlength="35" value="${recordTopic.tials}"></td>
			            </tr>
			            <tr height="2"><td>&nbsp;</td></tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="tiglsk">Godk.lag.plats (kod)</span></td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tiglsk" id="tiglsk" size="17" maxlength="17" value="${recordTopic.tiglsk}"></td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="tiacts">Kontrollplats (kod)</span></td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tiacts" id="tiacts" size="17" maxlength="17" value="${recordTopic.tiacts}"></td>
			            </tr>
			            <tr height="2"><td>&nbsp;</td></tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="titsb">Mellanliggande best.tullkontor</span></td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="titsb" id="titsb" size="8" maxlength="8" value="${recordTopic.titsb}"></td>
				            <td ><span title="tiskb">Språkkod&nbsp;</span></td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tiskb" id="tiskb" size="2" maxlength="2" value="${recordTopic.tiskb}"></td>
				            
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="tidtf">Frigivningsdatum</span></td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tidtf" id="tidtf" size="8" maxlength="8" value="${recordTopic.tidtf}"></td>
			            </tr>
			            
			            <tr height="2"><td>&nbsp;</td></tr>
			            </table>
				        </td>
			        </tr>
	            </table>
            </td>
		</tr>

		<tr height="15"><td colspan="2">&nbsp;</td></tr>
		
		<%-- ------------------------------------------ --%>
		<%-- SUB-SECTION that allows editable fields    --%>
		<%-- ------------------------------------------ --%>
	 	<tr >
		 	<td colspan="2">
			<table width="100%" class="formFrameHeader" border="0" cellspacing="1" cellpadding="0">
		 		<tr height="15">
		 			<td class="text14White">
		 				&nbsp;Lossningsanmärkningar&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
	 				</td>
 				</tr>
            </table>
            </td>
        </tr>
	 	<tr >
		 	<td width="50%">
				<table width="100%" class="formFrame" border="0" cellspacing="1" cellpadding="0">
			 		<tr height="15">
			 			<td class="text14">
			 				&nbsp;<span title="tivpos">Total antal varulinjer [NCTS]</span>
		 				</td>
			 			<td class="text14">
			 				&nbsp;<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="tivpos" id="tivpos" size="5" maxlength="5" value="${model.record.tivpos}">
		 				</td>
	 				</tr>
			 		<tr height="15">
			 			<td class="text14">
			 				&nbsp;<span title="tintk">Total kollital [NCTS]</span>
		 				</td>
			 			<td class="text14">
			 				&nbsp;<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="tintk" id="tintk" size="5" maxlength="4" value="${model.record.tintk}">
		 				</td>
	 				</tr>
			 		<tr height="15">
			 			<td class="text14">
			 				&nbsp;<span title="tivkb">Total bruttovikt [NCTS]</span>
		 				</td>
			 			<td class="text14">
			 				&nbsp;<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="tivkb" id="tivkb" size="11" maxlength="11" value="${model.record.tivkb}">
		 				</td>
	 				</tr>
	 				<tr height="5"><td></td></tr>
	            </table>
            </td>
		 	<td width="50%">
				<table width="100%" class="formFrame" border="0" cellspacing="1" cellpadding="0">
			 		<tr height="15">
			 			<td class="text14">
			 				&nbsp;<span title="nivpos">Total antal varulinjer [CTL]</span>
		 				</td>
			 			<td class="text14">
			 				&nbsp;<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="nivpos" id="nivpos" size="5" maxlength="5" value="${model.record.nivpos}">
		 				</td>
	 				</tr>
			 		<tr height="15">
			 			<td class="text14">
			 				&nbsp;<span title="nintk">Total kollital [CTL]</span>
		 				</td>
			 			<td class="text14">
			 				&nbsp;<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="nintk" id="nintk" size="5" maxlength="4" value="${model.record.nintk}">
		 				</td>
	 				</tr>
			 		<tr height="15">
			 			<td class="text14">
			 				&nbsp;<span title="nivkb">Total bruttovikt [CTL]</span>
		 				</td>
			 			<td class="text14">
			 				&nbsp;<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="nivkb" id="nivkb" size="11" maxlength="11" value="${model.record.nivkb}">
		 				</td>
	 				</tr>
	 				<tr height="5"><td></td></tr>
	            </table>
            </td>
        </tr>
        
        
		<tr>			
			<td colspan="2">
			<table class="formFrame" width="100%" align="left" border="0" cellspacing="1" cellpadding="0">
 				<tr height="5"><td></td></tr>
				<tr>
				<td width="50%" class="text14" valign="bottom">
					<table width="100%" align="left" border="0" cellspacing="1" cellpadding="0">
					 	<tr >
						 	<td>
							<table width="100%" border="0" cellspacing="1" cellpadding="0">
				 				<tr>		
									<td class="text14">&nbsp;<span title="nikonf">Konform</span></td>			 			
						 			<td class="text14">
										<select class="selectMediumBlueE2" name="nikonf" id="nikonf">
						            			<option value="">-Välj-</option>
						            			<option value="0"<c:if test="${model.record.nikonf == '0'}"> selected </c:if> >Nej</option>
					 					  	<option value="1"<c:if test="${model.record.nikonf == '1'}"> selected </c:if> >Ja</option>
										</select>
									</td>
									<td class="text14">&nbsp;<span title="nifulf">Slutfört</span></td>
						 			<td class="text14">
										<select class="selectMediumBlueE2" name="nifulf" id="nifulf">
						            			<option value="1"<c:if test="${model.record.nifulf == '1'}"> selected </c:if> >Ja</option>
										</select>
						 			</td>
						 			<td class="text14">&nbsp;<span title="nidtl">Lossningsdatum</span></td>
						 			<td class="text14">
						 				<input type="text" class="inputTextMediumBlue" name="nidtl" id="nidtl" size="9" maxlength="8" value="${model.record.nidtl}">
					 				</td>
						 		</tr>
						 	</table>
						 	</td>	
						 </tr>
						 <tr height="5"><td></td></tr>
						 <tr>
						 	<td>
						 	<table class="tableBorderWithRoundCornersGray" width="100%" border="0" cellspacing="1" cellpadding="0">		
				 				<tr >
						 			<td colspan="2" class="text14">&nbsp;<b>CTL resultat(OT/DI)</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span title="nictsk">Språkkod</span>&nbsp;
							            <select class="selectMediumBlueE2" name="nictsk" id="nictsk">
							            		<option value="">-Välj-</option>
							 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
						                             <option value="${code.tkkode}"<c:if test="${model.record.nictsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
										</select>
										<a tabindex="-1" class="text14" target="_blank" href="${model.isoLanguageCodesURL.value}" onclick="${model.isoLanguageCodesURL.windowOpenDimensions}" >
						            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
						            		</a>
					            		</td>					 			
						 		</tr>
						 		<tr>	
						 			<td class="text14" >&nbsp;&nbsp;
						 			<img onMouseOver="showPop('control_OT_code_info');" onMouseOut="hidePop('control_OT_code_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 			<span title="nictb">OT:</span>&nbsp;
						 				<div class="text11" style="position: relative;" align="left">
						 				<span style="position:absolute;top:2px; width:250px;" id="control_OT_code_info" class="popupWithInputText text11"  >
						           			<b>CTL resultat</b>
											<br/><br/>
											 (Om lossningsresultat = 0)
											 <ul>
											 	<li><b>DI</b> = Avvikelse i värde.</li>
											 	<li><b>OT</b> = Andra saker att rapportera</li>
											 </ul>
										</span>
										</div>
						 			</td>
						 			<td class="text14">
										<input type="text" class="inputTextMediumBlue" name="nictb" id="nictb" size="40" maxlength="70" value="${model.record.nictb}">
									</td>
								</tr>
								<tr>	
						 			<td class="text14" >&nbsp;&nbsp;
						 			<img onMouseOver="showPop('control_DI_code_info');" onMouseOut="hidePop('control_DI_code_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 			<span title="nictb2">DI:</span>
						 				<div class="text11" style="position: relative;" align="left">
						 				<span style="position:absolute;top:2px; width:250px;" id="control_DI_code_info" class="popupWithInputText text11"  >
						           			<b>CTL resultat</b>
											<br/><br/>
											 (Om lossningsresultat = 0)
											 <ul>
											 	<li><b>DI</b> = Avvikelse i värde.</li>
											 	<li><b>OT</b> = Andra saker att rapportera</li>
											 </ul>
										</span>
										</div>						 			
						 			</td>
						 			<td class="text14">
										<input type="text" class="inputTextMediumBlue" name="nictb2" id="nictb2" size="40" maxlength="70" value="${model.record.nictb2}">
									</td>
								</tr>
								<%-- finns på grön skärm men används inte längre. Vi gömmer dessa här tills vidare besked
								<tr>	
									<td class="text14" >&nbsp;&nbsp;&nbsp;<span title="nictp">Pekare:</span>&nbsp;</td>
						 			<td class="text14">
										<input type="text" class="inputTextMediumBlue" name="nictp" id="nictp" size="6" maxlength="5" value="${model.record.nictp}">
										&nbsp;<span title="nictnv">Nytt värde:</span>&nbsp;
										<input type="text" class="inputTextMediumBlue" name="nictnv" id="nictnv" size="16" maxlength="15" value="${model.record.nictnv}">
									</td>
						 		</tr>
						 		 --%>
						 		<tr height="5"><td></td></tr>
				            </table>
				            </td>
			            </tr>
					 	<tr height="5"><td></td></tr>
						<tr>
						 	<td>
						 	<table class="tableBorderWithRoundCornersGray" width="100%" border="0" cellspacing="1" cellpadding="0">		
				 				<tr >
						 			<td colspan="2" class="text14">&nbsp;<span title""><b>Försegling</b></span></td>					 			
						 		</tr>
						 		<tr>	
						 			<td class="text14" >&nbsp;&nbsp;&nbsp;<span title="nidfst">Status:</span>&nbsp;</td>
						 			<td class="text14">
					           			<select class="selectMediumBlueE2"  name="nidfst" id="nidfst">
						            			<option value="1"<c:if test="${model.record.nidfst == '1'}"> selected </c:if> >Ok</option>
										  	<option value="0"<c:if test="${model.record.nidfst == '0'}"> selected </c:if> >Inte Ok</option>
										</select>
									</td>
								</tr>
								<tr>	
						 			<td class="text14" >&nbsp;&nbsp;&nbsp;<span title="nidant">Antal:</span>&nbsp;</td>
						 			<td class="text14">
										<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="nidant" id="nidant" size="4" maxlength="4" value="${model.record.nidant}">
									</td>
								</tr>
								<tr>	
									<td class="text14" >&nbsp;&nbsp;&nbsp;<span title="nidfkd">Id:</span>&nbsp;</td>
						 			<td class="text14">
										<input type="text" class="inputTextMediumBlue" name="nidfkd" id="nidfkd" size="20" maxlength="20" value="${model.record.nidfkd}">
									</td>
						 		</tr>
						 		<tr>
						 			<td class="text14" >
						 				<span title="nidfsk">&nbsp;&nbsp;&nbsp;Språkkod</span>&nbsp;
						 			</td>
						 			<td 	class="text14" >
							            <select class="selectMediumBlueE2" name="nidfsk" id="nidfsk">
							            		<option value="">-Välj-</option>
							 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
						                             <option value="${code.tkkode}"<c:if test="${model.record.nidfsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
										</select>
										<a tabindex="-1" class="text14" target="_blank" href="${model.isoLanguageCodesURL.value}" onclick="${model.isoLanguageCodesURL.windowOpenDimensions}" >
						            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
						            		</a>
						 			</td>
						 		</tr>
						 		<tr height="5"><td></td></tr>
				            </table>
				            </td>
		 				    
			            </tr>
			            
		            </table>
	            	</td>
				<td width="50%" class="text14" valign="Top">
					<table width="100%" align="left" border="0" cellspacing="1" cellpadding="0">
						 <tr height="35"><td>&nbsp;</td></tr>
						 <tr>
						 	<td >
						 	<table class="tableBorderWithRoundCornersGray" width="85%" border="0" cellspacing="1" cellpadding="0">
								
								<tr>	
						 			<td class="text14">&nbsp;&nbsp;&nbsp;&nbsp;<b><span title="nimn1/nimn2">Märkning</span></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			<span title="nimnsk">Språkkod</span>&nbsp;
							            <select class="selectMediumBlueE2" name="nimnsk" id="nimnsk">
							            		<option value="">-Välj-</option>
							 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
						                             <option value="${code.tkkode}"<c:if test="${model.record.nimnsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
										</select>
										<a tabindex="-1" class="text14" target="_blank" href="${model.isoLanguageCodesURL.value}" onclick="${model.isoLanguageCodesURL.windowOpenDimensions}" >
						            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
						            		</a>
					            		</td>		
								</tr>
								<tr>	
						 			<td class="text14"><span title="nimn1">&nbsp;&nbsp;</span>
										<input type="text" class="inputTextMediumBlue" name="nimn1" id="nimn1" size="70" maxlength="70" value="${model.record.nimn1}">
									</td>
								</tr>
								<tr>	
						 			<td class="text14"><span title="nimn2">&nbsp;&nbsp;</span>
										<input type="text" class="inputTextMediumBlue" name="nimn2" id="nimn2" size="70" maxlength="70" value="${model.record.nimn2}">
									</td>
						 		</tr>
						 		<tr height="5"><td></td></tr>
				            </table>
				            </td>
			            </tr>
			            <tr height="5"><td></td></tr>
			            <tr>
			            		<td align="center" >
						 	<table>
						 		<tr>
					            		<%-- only status = U,H are allowed  --%>
				 				    <c:choose>
					 				    <c:when test="${ recordTopic.tist == 'U' || recordTopic.tist == 'H' }">
						 				    <td class="text9BlueGreen" valign="bottom"  >
							 				    &nbsp;<input tabindex=-1 class="inputFormSubmit" type="submit" name="submit" onclick="javascript: form.action='nctsimport_unloading_edit.do';" value="<spring:message code="systema.ncts.import.unloading.createnew.submit"/>"/>
							 				
							 					<%-- NOTE: we use the same routine as for the Topic ... --%>
						 				    		<input tabindex=-1 class="inputFormSubmit" type="submit" name="send" onclick="javascript: form.action='nctsimport_unloading_send.do';" value='<spring:message code="systema.ncts.import.unloading.createnew.send"/>'/>
						 				    	</td>	
					 				    </c:when>
					 				    <c:otherwise>
						 				    <td  align="center" class="text9BlueGreen" valign="bottom"  >
						 				    		&nbsp;&nbsp;&nbsp;<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value="Ej uppdaterbart"/>
						 				    	</td>	
					 				    </c:otherwise>	
				 				    </c:choose>
		 				    		</tr>
		 				    </table>
				        </tr>    
		            </table>
	            </td>
	          </tr>
          	</table>
          	</td>
		</tr>
		
		<tr height="20"><td colspan="2">&nbsp;</td></tr>
		
	</table> 
	 
	</td>
 </tr>
</table>
</td>
</tr>
</table>
</form>	