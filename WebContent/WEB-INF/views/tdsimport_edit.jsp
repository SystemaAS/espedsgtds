<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTds.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tdsglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>			
	<SCRIPT type="text/javascript" src="resources/js/tdsimport_edit.js?ver=${user.versionEspedsg}"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/jquery.calculator.js"></SCRIPT>
	 

<table width="100%" cellspacing="0" border="0" cellpadding="0">
	
 <tr>
 <td>	
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<c:choose>
					<c:when test="${not empty model.record.svih_sysg}">
						<a id="alinkMainList" tabindex=-1 style="display:block;" href="tdsimport.do?action=doFind&sign=${model.record.svih_sysg}">
							<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tds.import.list.tab"/></font>
						</a>
					</c:when>
					<c:otherwise>
						<a id="alinkMainList" tabindex=-1 style="display:block;" href="tdsimport.do?action=doFind&sign=${model.sign}">
							<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tds.import.list.tab"/></font>
						</a>
					</c:otherwise>	
				</c:choose>	
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<c:choose> 
			    <c:when test="${editActionOnTopic=='doUpdate' or editActionOnTopic=='doFetch'}">
					<td width="15%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">
							&nbsp;<spring:message code="systema.tds.import.created.mastertopic.tab"/>
						</font>
						<font class="text14MediumBlue">[${model.record.svih_syop}]</font>
						<c:if test="${model.record.svih_syst == 'M' || empty model.record.svih_syst}">
							<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						</c:if>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkInvoices" tabindex=-1 style="display:block;" href="tdsimport_edit_invoice.do?action=doFetch&avd=${model.record.svih_syav}&sign=${model.record.svih_sysg}
															&opd=${model.record.svih_syop}&tullId=${model.record.svih_tuid}
															&status=${model.record.svih_syst}&datum=${model.record.svih_sydt}&fabl=${model.record.svih_fabl}">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tds.import.invoice.tab"/></font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkItemLines" tabindex=-1 style="display:block;" href="tdsimport_edit_items.do?action=doFetch&avd=${model.record.svih_syav}&sign=${model.record.svih_sysg}
													&opd=${model.record.svih_syop}&tullId=${model.record.svih_tuid}
													&status=${model.record.svih_syst}&datum=${model.record.svih_sydt}&fabl=${model.record.svih_fabl}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tds.import.item.createnew.tab"/>
							</font>
							<c:if test="${model.record.svih_syst == 'M' || empty model.record.svih_syst}">
								<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
							</c:if>
							
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkLogging" tabindex=-1 style="display:block;" href="tdsimport_logging.do?avd=${model.record.svih_syav}&sign=${model.record.svih_sysg}
													&opd=${model.record.svih_syop}&tullId=${model.record.svih_tuid}
													&status=${model.record.svih_syst}&datum=${model.record.svih_sydt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tds.import.logging.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkArchive" tabindex=-1 style="display:block;" href="tdsimport_archive.do?avd=${model.record.svih_syav}&sign=${model.record.svih_sysg}
													&opd=${model.record.svih_syop}&tullId=${model.record.svih_tuid}
													&status=${model.record.svih_syst}&datum=${model.record.svih_sydt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tds.import.archive.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
						</a>
					</td>
					<td width="10%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</c:when>
				<c:otherwise>
					<td width="15%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">&nbsp;<spring:message code="systema.tds.import.createnew.tab"/></font>
						<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
					</td>
					<td width="70%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</c:otherwise>
			</c:choose>
		</tr>
	</table>
	</td>
 </tr>
 
 <tr>
 	<td>
	<%-- --------------------------- --%>	
 	<%-- tab area container PRIMARY  --%>
	<%-- --------------------------- --%>
	<form name="tdsImportSaveNewTopicForm" id="tdsImportSaveNewTopicForm" method="post">
	<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
			<%-- --- HIDDEN FORM FIELDS (not visible in form but important with an UPDATE ----- --%>			
			<%-- general (from user profile) --%>
			<input type="hidden" name="action" id="action" value='doUpdate'>
			<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
			<input type="hidden" name="opd" id="opd" value='${model.record.svih_syop}'>
			<%-- topic specific (syop and tuid) --%>
			<input type="hidden" name="svih_syav" id="svih_syav" value='${model.record.svih_syav}'>
			<input type="hidden" name="svih_syop" id="svih_syop" value='${model.record.svih_syop}'>
			<input type="hidden" name="svih_syst" id="svih_syst" value='${model.record.svih_syst}'>
			<input type="hidden" name="svih_sydt" id="svih_sydt" value='${model.record.svih_sydt}'>
			<input type="hidden" name="svih_tuid" id="svih_tuid" value='${model.record.svih_tuid}'>
		<tr height="4">
			<td colspan="2">&nbsp;
				<%-- test indicator /per avdelning --%> 
				<c:forEach var="record" items="${avdListSessionTestFlag}" >
					<c:if test="${record.avd == model.record.svih_syav}">	
						<c:if test="${record.tst == '1'}">&nbsp;&nbsp;	
							<c:set var="isTestAvd" value="1" scope="request" />
						</c:if>
					</c:if>
				</c:forEach>			
			</td>
		</tr>	

		<c:choose>
		<%-- UPDATE MODE --%> 
	    <c:when test="${editActionOnTopic=='doUpdate' or editActionOnTopic=='doFetch'}">
	    	<input type="hidden" name="avd" id="avd" value='${model.record.svih_syav}'>
			<input type="hidden" name="sign" id="sign" value='${model.record.svih_sysg}'>
			<tr >
				<td align="left" class="text14MediumBlue" >
					&nbsp;&nbsp;&nbsp;&nbsp;<span title="svih_syav">Avd:</span>&nbsp;${model.record.svih_syav}&nbsp;&nbsp;<span title="svih_syop">Ärende:&nbsp;</span><b>${model.record.svih_syop}</b>
					&nbsp;&nbsp;<span title="svih_tuid">Tullid:</span>&nbsp;<b>${model.record.svih_tuid}</b>
					<a tabindex="-1" class="text14" target="_blank" href="${model.taricFragaTullidURL.value}" onclick="${model.taricFragaTullidURL.windowOpenDimensions}" >
            			<img title="Fråga Tullid (hos Tullverket)" style="vertical-align:bottom;" width="14px" height="16px" src="resources/images/help.png" border="0" alt="question">                		
            		</a>
            		<c:choose>
	 				    <c:when test="${model.record.svih_syst == 'M' || empty model.record.svih_syst}">																	 			
							&nbsp;&nbsp;<span title="svih_sysg">Sign:</span>&nbsp;
		           			<select class="selectMediumBlueE2" name="svih_sysg" id="svih_sysg">
			            		<option value="">-Välj-</option>
			 				  	<c:forEach var="record" items="${model.signList}" >
                             	 	<option value="${record.sign}"<c:if test="${model.record.svih_sysg == record.sign}"> selected </c:if> >${record.sign}</option>
								</c:forEach> 
							</select>
						</c:when>
						<c:otherwise>
							&nbsp;&nbsp;<span title="svih_sysg">Sign:</span>&nbsp;<b>${model.record.svih_sysg}</b>
							<input type="hidden" name="svih_sysg" id="svih_sysg" value='${model.record.svih_sysg}'>
						</c:otherwise>
					</c:choose>	
					&nbsp;&nbsp;
					<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					<span title="svih_syst">Stat</span><a tabindex=-1 id="updateStatusLink" runat="server" href="#"><font class="text14MediumBlue">u</font></a>s:
					<b>
						<c:choose>
							<c:when test="${empty model.record.svih_syst}">
								-
							</c:when>
							<c:otherwise>
								${model.record.svih_syst}
							</c:otherwise>
						</c:choose>
					</b>
					&nbsp;&nbsp;<span title="svih_sydt">Datum:</span>&nbsp;${model.record.svih_sydt}
				</td>
				<td align="right" valign="top">
					<c:if test="${'1' != isTestAvd}">
						<%--This checkbox appears only in real production. Otherwise use the Testavdelning --%>
						<input tabindex=-1 type="checkbox" name="svih_0035" id="svih_0035" value="1" <c:if test="${model.record.svih_0035 == '1'}"> checked </c:if> ><font class="text14MediumBlue"><b>TEST flag</b></font>&nbsp;&nbsp;&nbsp;						
					</c:if>
					<a tabindex=-1 href="tdsimport_edit_printTopic.do?avd=${model.record.svih_syav}&opd=${model.record.svih_syop}">
					 	<img style="cursor:pointer;" src="resources/images/printer.png" width="30" hight="30" border="0" alt="Print">
					</a>
					&nbsp;&nbsp;<img title="Print försättsblad" style="vertical-align: bottom;cursor: pointer;" id="printSkilleArkImg" width="30px" height="30px" src="resources/images/printer2.png" border="0" alt="Print försättsblad">
					&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;<img title="Upload dokument" style="vertical-align: bottom;cursor: pointer;" id="uploadFileImg" width="25px" height="25px" src="resources/images/upload.png" border="0" alt="Upload dokument">
					&nbsp;&nbsp;&nbsp;
				</td>
				<div class="text11" style="position: relative;display:inline;" align="left">
					<span style="position:absolute;left:350px;top:40px;width:250px;" id="status_info" class="popupWithInputText"  >
						<font class="text11" >
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
		           		</font>	
					</span>
				</div>		
			</tr>	
			<%-- test indicator /per avdelning --%> 
			<c:if test="${'1' == isTestAvd}">
				<tr>
					<td align="left" class="text14Red" >
						&nbsp;&nbsp;<b>[TEST Avdelning]</b>
					</td>
				</tr>
			</c:if>
			
		</c:when>
		<%-- CREATE MODE --%> 
		<c:otherwise>
			<tr >
				<td align="left" class="text14MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Avd:&nbsp;
           			<select class="selectMediumBlueE2" name="avd" id="avd">
	            		<option value="">-Välj-</option>
	 				  	<c:forEach var="record" items="${model.avdList}" >
                       	 	<option value="${record.avd}"<c:if test="${model.record.svih_syav == record.avd}"> selected </c:if> >${record.avd}<c:if test="${record.tst == '1'}">&nbsp;(test)</c:if></option>
						</c:forEach> 
					</select>
					
					&nbsp;Sign:&nbsp;
           			<select class="selectMediumBlueE2" name="sign" id="sign">
	            		<option value="">-Välj-</option>
	 				  	<c:forEach var="record" items="${model.signList}" >
                             	 	<option value="${record.sign}"<c:if test="${model.record.svih_sysg == record.sign}"> selected </c:if> >${record.sign}</option>
						</c:forEach> 
					</select>
				</td>
				<td>&nbsp;</td>
			</tr>	
		</c:otherwise>
		</c:choose>

		<tr height="10"><td colspan="2">&nbsp;</td></tr>
		<%-- --------------- --%>
		<%-- LEFT SIDE CELL --%>
		<%-- --------------- --%>
		<tr>
		<td width="50%" align="left">
		<table width="80%" border="0" cellspacing="0" cellpadding="0">
			<tr>
	            <td width="5">&nbsp;</td>
	            <td >
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
	            		
				 		<tr>
				 			<td class="text14">
				 				<img onMouseOver="showPop('1_1_info');" onMouseOut="hidePop('1_1_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>1.1</b>
			 					<div class="text11" style="position: relative;display:inline;" align="left">
			 					<span style="position:absolute; top:2px; width:250px;" id="1_1_info" class="popupWithInputText text11"  >
					           			<ul>
					           				<li><b>EU</b><br>Vid import från EFTA-land, det vill säga Norge, Schweiz, Island, Liechtenstein och andra fördragsslutande parter i konventionen om ett
					           						gemensamt transiteringsförfarande och konventionen om förenkling av formaliteterna vid handel.
					           				</li>
					           				<li><b>CO</b><br>Vid införsel fråm ett område inom gemeskapens tullområde men som inte tillhör skatteområdet, exempelvis Åland eller 
					           						Kanarieöarna
					           				</li>
					           				<li><b>IM</b><br>Vid import från andra länder än ovan.
					           				</li>
					           			
					           			</ul>
								</span>		
								</div>
			 					<font class="text16RedBold" >*</font>
			 					<span title="svih_dek1">Dekl.&nbsp;</span>
				 			</td>
				 			<td>
				 				<select class="inputTextMediumBlueMandatoryField" name="svih_dek1" id="svih_dek1" TABINDEX=1>
				 				  <option value="">-Välj-</option>
								  <option value="EU"<c:if test="${model.record.svih_dek1 == 'EU'}"> selected </c:if> >EU</option>
								  <option value="CO"<c:if test="${model.record.svih_dek1 == 'CO'}"> selected </c:if> >CO</option>
								  <option value="IM"<c:if test="${model.record.svih_dek1 == 'IM'}"> selected </c:if> >IM</option>
								</select>
			 				</td>
			 				<td class="text14">
			 					&nbsp;<img onMouseOver="showPop('48_info');" onMouseOut="hidePop('48_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>48.</b><font class="text16RedBold" >*</font><span title="svih_upps">Upp.bet.&nbsp;</span>
			 					<div class="text11" style="position: relative;display:inline;" align="left">
			 					<span style="position:absolute; top:2px; width:250px;" id="48_info" class="popupWithInputText text11"  >
				           			<b>48. Uppskjuten betalning</b>
				           			<ul>
				           				<li>
				           				Om du som deklarant har tullkredit och vill att tull och 
				           				annan skatt ska debiteras på en tullräkning till dig, anger du koden T i detta fält. 
				           				Detta gäller även för ombud som vill att tullräkningen ska skickas till deklaranten.
				           				</li>	
										<li>Om ett ombud åtar sig betalningsskyldighet för tull och annan 
										skatt anger du koden O. Tänk på att koden O innebär att du som ombud tagit på dig 
										betalningsskyldigheten i och med att du utfärdar och undertecknar deklarationen.
										</li>
										<li>Du som deklarant är fortfarande ansvarig för att tull och 
										annan skatt blir betald till Tullverket även om du låter ombudet ta på sig 
										betalningsskyldigheten.
										</li>
									</ul>
								</span>
								</div>
				 			</td>
				 			<td>	
				 				<select class="inputTextMediumBlueMandatoryField" name="svih_upps" id="svih_upps" TABINDEX=5>
			 					  <option value="">-Välj-</option>
								  <option value="O"<c:if test="${model.record.svih_upps == 'O'}"> selected </c:if> >O</option>
								  <option value="T"<c:if test="${model.record.svih_upps == 'T'}"> selected </c:if> >T</option>
								  
						  		</select>
			 				</td>
			 				<td class="text14">
			 				<img onMouseOver="showPop('meddTyp_info');" onMouseOut="hidePop('meddTyp_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 				<font class="text16RedBold" >*</font><span title="svih_mtyp">Medd.typ&nbsp;</span>
			 				<div class="text11" style="position: relative;display:inline;" align="left">
			 				<span style="position:absolute; top:2px; width:250px;" id="meddTyp_info" class="popupWithInputText text11"  >
				           			<ul>
					           			<li><b>DNU</b>&nbsp;Normalförfarande förtullning nyuppläggning.</li>
					           			<li><b>DNK</b>&nbsp;Normalförfarande förtullning nyuppläggning med begäran om klarering.</li>
					           			<li><b>DRT</b>&nbsp;Normalförfarande förtullning rättelse.</li>
					           			<li><b>DBK</b>&nbsp;Normalförfarande förtullning begäran om klarering.</li>
					           			<li><b>HNU</b>&nbsp;Förenklat deklarationsförfarande förtullning nyuppläggning. </li>
					           			<li><b>HNK</b>&nbsp;Förenklat deklarationsförfarande förtullning nyuppläggning med begäran om klarering.</li>
					           			<li><b>HRT</b>&nbsp;Förenklat deklarationsförfarande förtullning rättelse.</li>
					           			<li><b>HBK</b>&nbsp;Förenklat deklarationsförfarande förtullning begäran om klarering.</li>
					           			<li><b>TNU</b>&nbsp;Kompletterande tulldeklaration förtullning nyuppläggning.</li>
					           			<li><b>TRT</b>&nbsp;Kompletterande tulldeklaration förtullning rättelse.</li>
					           			<li><b>TQN</b>&nbsp;Tulldeklaration efter kvalitetssäkrad förvaring nyuppläggning.</li>
					           			<li><b>TQR</b>&nbsp;Tulldeklaration efter kvalitetssäkrad förvaring rättelse.</li>
					           			<li><b>OMP</b>&nbsp;Ansökan om omprövning.</li>
					           			
					           		</ul>
							</span>
							</div>
							
			 				</td>
				 			<td>
				 				<select class="inputTextMediumBlueMandatoryField" name="svih_mtyp" id="svih_mtyp" TABINDEX=10>
				 				  <option selected value="">-Välj-</option>
								  <option value="DNU"<c:if test="${model.record.svih_mtyp == 'DNU'}"> selected </c:if> >DNU</option>
								  <option value="DNK"<c:if test="${model.record.svih_mtyp == 'DNK'}"> selected </c:if> >DNK</option>
								  <option value="DRT"<c:if test="${model.record.svih_mtyp == 'DRT'}"> selected </c:if> >DRT</option>
								  <option value="DBK"<c:if test="${model.record.svih_mtyp == 'DBK'}"> selected </c:if> >DBK</option>
								  <option value="HNU"<c:if test="${model.record.svih_mtyp == 'HNU'}"> selected </c:if> >HNU</option>
								  <option value="HNK"<c:if test="${model.record.svih_mtyp == 'HNK'}"> selected </c:if> >HNK</option>
								  <option value="HRT"<c:if test="${model.record.svih_mtyp == 'HRT'}"> selected </c:if> >HRT</option>
								  <option value="HBK"<c:if test="${model.record.svih_mtyp == 'HBK'}"> selected </c:if> >HBK</option>
								  <option value="TNU"<c:if test="${model.record.svih_mtyp == 'TNU'}"> selected </c:if> >TNU</option>
								  <option value="TRT"<c:if test="${model.record.svih_mtyp == 'TRT'}"> selected </c:if> >TRT</option>
								  <option value="TQN"<c:if test="${model.record.svih_mtyp == 'TQN'}"> selected </c:if> >TQN</option>
								  <option value="TQR"<c:if test="${model.record.svih_mtyp == 'TQR'}"> selected </c:if> >TQR</option>
								  <option value="OMP"<c:if test="${model.record.svih_mtyp == 'OMP'}"> selected </c:if> >OMP</option>
								</select>
				 			</td>
		 				</tr>
		 				<tr>
		 					<td class="text14">
		 						<img onMouseOver="showPop('1_2_info');" onMouseOut="hidePop('1_2_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>1.2</b>&nbsp;<font class="text16RedBold" >*</font><span title="svih_dek2">Dekl.typ&nbsp;</span>
				 				<div class="text11" style="position: relative;display:inline" align="left" >
				 				<span style="position:absolute;top:2px; width:300px;" id="1_2_info" class="popupWithInputText text11">
				 					<b>1.2 Deklarationstyp</b>
					           		<ul>
					           			Här talar du om vilken typ av deklaration de handlar om. Du anger detta med koden A for en deklaration enligt normalförfarandet (meddelande UNU).
					           			<br/>
					           			<br/>
					           			<b>Meddelande och Deklarationstyp (fält 1:2)</b>
					           			<br/>
					           			<li>
					           				<b>HNU, HRT, HNK</b> skall ha <b>C</b>
				           				</li>
				           				<li>
					           				<b>DNU, DRT, DNK, UNU, URT</b> skall ha <b>A</b>
					           			</li>
					           			<li>
					           				<b>DNU, DNK</b> skall ha <b>B</b>
					           			</li>
					           			<li>
					           				<b>TNU, TRT, UFF</b> skall ha <b>Y</b>
					           			</li>
					           			<li>
					           				<b>TQN, TQR, UFF, UGE</b> skall ha <b>Z</b>
					           			</li>
					           			<li>
					           				<b>DRT</b> skall ha <b>X</b>
					           			</li>
					           			<li>
					           				<b>OMP</b> Deklarationstyp skickas ej - hämtas från det ursprungliga ärendet.
					           			</li>
					           		</ul>
								</span>
				 				</div>
				 			</td>
				 			<td>
				 				<select class="inputTextMediumBlueMandatoryField" name="svih_dek2" id="svih_dek2" TABINDEX=2>
				 					<option value="">-Välj-</option>
								  <option value="A"<c:if test="${model.record.svih_dek2 == 'A'}"> selected </c:if> >A</option>
								  <option value="B"<c:if test="${model.record.svih_dek2 == 'B'}"> selected </c:if> >B</option>
								  <option value="C"<c:if test="${model.record.svih_dek2 == 'C'}"> selected </c:if> >C</option>
								  <option value="X"<c:if test="${model.record.svih_dek2 == 'X'}"> selected </c:if> >X</option>
								  <option value="Y"<c:if test="${model.record.svih_dek2 == 'Y'}"> selected </c:if> >Y</option>
								  <option value="Z"<c:if test="${model.record.svih_dek2 == 'Z'}"> selected </c:if> >Z</option>
								</select>
			 				</td>
			 				
			 				<td class="text14">
			 					&nbsp;<img onMouseOver="showPop('24_info');" onMouseOut="hidePop('24_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>24.</b><span title="svih_tart">Trans.art&nbsp;</span>
				 				<div class="text11" style="position: relative;display:inline" align="left" >
				 				<span style="position:absolute; top:2px; width:250px;" id="24_info" class="popupWithInputText text11"  >
					           		<br>Här anger du vilken sorts transaktion det handlar om, exempelvis köp, retur, reparation, gåva eller lån.   
									Om försändelsen innehåller transaktioner med flera transaktionskoder så får du använda den transaktionskod som dominerar, förutom om en av koderna är kod 6. 
									Om försändelsen innehåller en transaktion med kod 6 måste varorna för den transaktionskoden deklareras i en egen deklaration.
					           		<ul>
					           			<li><b>1</b>
					           				&nbsp;Överlåtelse av äganderätten mot ersättning, försäljning, finansiell leasing, förflyttning av eget lager
					           			</li>
					           			<li><b>2</b>
					           				&nbsp;Returer och kostnadsfria ersättningsleveranser, se även 4, 5, 6
					           			</li>
					           			<li><b>3</b>
					           				&nbsp;Överlåtelse av äganderätten utan ersättning till exempel gåva
					           			</li>
					           			<li><b>4</b>
					           				&nbsp;Export för lönbearbetning, ej reparation
					           			</li>
					           			<li><b>5</b>
					           				&nbsp;Export efter lönbearbetning, ej reparation 
					           			</li>
					           			<li><b>6</b>
					           				&nbsp;Export för eller efter reparation, hyra, lån eller operationell leasing
					           			</li>
					           			<li><b>7</b>
					           				&nbsp;Gemensamma försvarsprojekt eller andra gemensamma mellanstatliga produktionsprogram
					           			</li>
					           			<li><b>8</b>
					           				&nbsp;Leveranser av byggnadsmaterial och utrustning till offentlig bygg- och anläggningsverksamhet
					           			</li>
					           			<li><b>9</b>
					           				&nbsp;Övrigt, till exempel flyttsaker 
					           			</li>
					           		</ul>
									
								</span>
								</div>
				 				
			 				</td>
			 				<td>
				 				<select class="selectMediumBlueE2" name="svih_tart" id="svih_tart" TABINDEX=6>
				 				  <option selected value="">-Välj-</option>
								  <option value="1"<c:if test="${model.record.svih_tart == 1}"> selected </c:if> >1</option>
								  <option value="2"<c:if test="${model.record.svih_tart == 2}"> selected </c:if> >2</option>
								  <option value="3"<c:if test="${model.record.svih_tart == 3}"> selected </c:if> >3</option>
								  <option value="4"<c:if test="${model.record.svih_tart == 4}"> selected </c:if> >4</option>
								  <option value="5"<c:if test="${model.record.svih_tart == 5}"> selected </c:if> >5</option>
								  <option value="6"<c:if test="${model.record.svih_tart == 6}"> selected </c:if> >6</option>
								  <option value="7"<c:if test="${model.record.svih_tart == 7}"> selected </c:if> >7</option>
								  <option value="8"<c:if test="${model.record.svih_tart == 8}"> selected </c:if> >8</option>
								  <option value="9"<c:if test="${model.record.svih_tart == 9}"> selected </c:if> >9</option>
								</select>
			 				</td>
			 				<td class="text14">
				 				<img onMouseOver="showPop('37_1_info');" onMouseOut="hidePop('37_1_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 			<b>37.</b><font class="text16RedBold" >*</font><span title="svih_eup1">Förf.</span>
				 				<div class="text11" style="position: relative;" align="left" >
					            <span style="position:absolute;top:2px;width:250px;" id="37_1_info" class="popupWithInputText text11"  >
					           		<br>
				           			<b>Förfarande - Första delfältet</b>
				           			<br/>
				           			Här ska du ange en fyrställig kod som består av två tvåställiga koder. Koden visar vilket importförfarande som avses. De två första siffrorna anger det förfarande som begärs och de två sista siffrorna det föregående förfarandet.
				           			<br/><br/>
				           			Exempel kod 4000<br/>
				           			De två första siffrorna i koden visar det tullförfarande som du anmäler varorna till. 
				           			40 står övergång till fri konsumtion med samtidig övergång till fri omsättning av varor som inte är föremål
				           			för momsbefriad leverans.. De två sista siffrorna i koden visar om varorna tidigare varit föremål för ett tullförfarande. 
				           			00 innebär att det inte finns något föregående tullförfarande.   
				           			<br/><br/>
									En förteckning över de 2- och 6-ställiga koderna finns i Tulltaxa Söksystem.
				           			<br/><br/>
					           		
								</span>
					            </div>
			 				</td>
			 				<td class="text14">
			 					<select class="inputTextMediumBlueMandatoryField" name="svih_eup1" id="svih_eup1">
			 						<option value="">-Välj-</option>
				 				  	<c:forEach var="code" items="${model.forfarande01CodeList}" >
				 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.svih_eup1 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
									</c:forEach>  
								</select>
								<%--COVI TODO	
								<a tabindex="-1" id="svih_eup1IdLink" >
			            			<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
		            			</a>	
		            			 --%>
			 				</td>
			 				
		 				</tr>
		 				<tr height="5"><td></td></tr>
		 				<tr>
				 			<td class="text14"><span title="h_xref">&nbsp;&nbsp;&nbsp;&nbsp;Ext.ref.&nbsp;</span></td>
				 			<td colspan="3">
				 				<input type="text" class="inputText" name="h_xref" id="h_xref" size="20" maxlength="35" value='${model.record.h_xref}'>
			 				</td>
				 		</tr>		
		 				<tr height="10"><td></td></tr>
	 				</table>
 				</td>
			</tr>

			<%-- Validation errors --%>
			<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
			<tr>
				<td width="5">&nbsp;</td>
				<td>
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
				<td >
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
				 			<td class="textError">
				 				<ul>
                                    <li>
                                    	${model.errorMessage} 
                                    </li>
                                </ul>
				 			</td>
						</tr>
					</table>
				</td>
			</tr>
			</c:if>
			
 			<tr>
	 			<td width="5">&nbsp;</td>
	            <td >		
	 				<%-- SENDER --%>
	 				<table width="100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text14White">
								&nbsp;<img onMouseOver="showPop('2_info');" onMouseOut="hidePop('2_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 			<b>&nbsp;2.</b><font class="text16RedBold" >*</font>Avsändare/Exportör&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
					 			<div class="text11" style="position:relative" >
				 				<span style="position:absolute; top:2px; width:250px;" id="2_info" class="popupWithInputText text11"  >
					           			Här anger du din leverantörs fullständiga namn och adress, inklusive landkod och postnummer.  
				           				<ul>
					           				<li>Om din tulldeklaration omfattar varor från flera olika leverantörer anger du antingen den leverantör vars varor dominerar värdemässigt eller skriver ordet "Flera".</li>
						           		</ul>
						           		Observera att uppgifter måste lämnas i samtliga adressfält.<ul>
								</span>
								</div>
				 				
			 				</td>
		 				</tr>
	 				</table>
	 			</td>
	 		</tr>
	 		<tr>	
	 			<td width="5">&nbsp;</td>
	            <td >	
					<%-- create record --%>
				 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
					 		<td width="100%">
						 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="10"><td ></td></tr>
							        
							        <tr>
							        	<%-- ================================================================================== --%>
							        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
							        		 These original values will be used when the user clicks "Cancel" buttons (puttting
							        		 back original value)																--%> 
							        	<%-- ================================================================================== --%>
							        	<input type="hidden" name="orig_svih_avkn" id="orig_svih_avkn" value='${model.record.svih_avkn}'>
							        	<input type="hidden" name="orig_svih_avna" id="orig_svih_avna" value='${model.record.svih_avna}'>
							        	<input type="hidden" name="orig_svih_aveo" id="orig_svih_aveo" value='${model.record.svih_aveo}'>
							        	<input type="hidden" name="orig_svih_ava1" id="orig_svih_ava1" value='${model.record.svih_ava1}'>
							        	<input type="hidden" name="orig_svih_ava2" id="orig_svih_ava2" value='${model.record.svih_ava2}'>
							        	<input type="hidden" name="orig_svih_avpn" id="orig_svih_avpn" value='${model.record.svih_avpn}'>
							        	<input type="hidden" name="orig_svih_avpa" id="orig_svih_avpa" value='${model.record.svih_avpa}'>
							        	<input type="hidden" name="orig_svih_avlk" id="orig_svih_avlk" value='${model.record.svih_avlk}'>
							        	<input type="hidden" name="orig_svih_avha" id="orig_svih_avha" value='${model.record.svih_avha}'>
							        	<input type="hidden" name="orig_svih_avtl" id="orig_svih_avtl" value='${model.record.svih_avtl}'>
							        	
							        	
							            <td class="text14" align="left" >&nbsp;&nbsp;
							            <span title="svih_avkn">Kundnummer</span></td>
							            <td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font>
							            <span title="svih_avna">Namn&nbsp;</span>
							            	<a tabindex="-1" id="svih_avnaIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>		
							            </td>
							        </tr>
							        <tr>
							            <td class="text14" align="left"><input type="text" class="inputTextMediumBlue" name="svih_avkn" id="svih_avkn" size="8" maxlength="8" value="${model.record.svih_avkn}"></td>
							            <td class="text14" align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="svih_avna" id="svih_avna" size="30" maxlength="35" value="${model.record.svih_avna}"></td>
							            
							        </tr>
							        
							        <%--
							        <tr>
							            <td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font>EORI</td>
							            <td class="text14" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="svih_aveo" id="svih_aveo" size="20" maxlength="17" value='${model.record.svih_aveo}'></td>
							            <td align="left">&nbsp;</td>
							        </tr>
							        --%>
							         
							        <tr height="4"><td>&nbsp;</td></tr>
							        <tr>
							            <td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font>
							            <span title="svih_ava1">Adress 1</span></td>
							            <td class="text14" align="left" >&nbsp;&nbsp;
							            <span title="svih_ava2">Adress 2</span></td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="svih_ava1" id="svih_ava1" size="30" maxlength="35" value="${model.record.svih_ava1}"></td>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="svih_ava2" id="svih_ava2" size="30" maxlength="35" value="${model.record.svih_ava2}"></td>
							        </tr>
							        <tr>
							        	<td>
							        		<table>
								        		<tr>
								            		<td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font>
								            		<span title="svih_avpa">Postadress</span></td>
								            		<td class="text14" >&nbsp;</td>
								            	</tr>
								        		<tr>
								            		<td align="left" colspan="2">
								            			<input type="text" class="inputTextMediumBlueMandatoryField" name="svih_avpa" id="svih_avpa" size="30" maxlength="35" value="${model.record.svih_avpa}">
								            		</td> 
								            		<td class="text14" >&nbsp;</td>
								        		</tr>    	
							            	</table>
						            </td>
						            <td>
							            	<table>
								        		<tr>
								            		<td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font>
								            		<span title="svih_avpn">Postnummer</span></td>
								            		<td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font>
								            		<span title="svih_avlk">Land</span>
													<a tabindex="-1" id="svih_avlkIdLink">
           												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
           											</a>																	 			
								            		</td>
								            		
								            	</tr>
								        		<tr>
								        			<td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="svih_avpn" id="svih_avpn" size="10" maxlength="9" value="${model.record.svih_avpn}"></td> 
								            		<td align="left">
								            			<select class="inputTextMediumBlueMandatoryField" name="svih_avlk" id="svih_avlk">
										            		<option value="">-Välj-</option>
										 				  	<c:forEach var="country" items="${model.gcyCodeList}" >
										 				  		<option value="${country.svkd_kd}"<c:if test="${model.record.svih_avlk == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
															</c:forEach>  

														</select>
													</td>
								            		<td align="left">&nbsp;</td> 
								        		</tr>    	
							            	</table>
						            </td>
							        </tr>
							        
							        <tr height="15">
							            <td class="text14Bold" align="left" >&nbsp;</td> 
							        </tr>
						        </table>
					        </td>
				        </tr>
					</table>          
            	</td>
           	</tr> 
           	<tr height="10"><td></td></tr>
           	
           	<%-- RECEIVER --%>
	 		<tr>
	 			<td width="5">&nbsp;</td>
	            <td >		
	 				<table width="100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text14White">
				 				&nbsp;<img onMouseOver="showPop('8_info');" onMouseOut="hidePop('8_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>&nbsp;8.</b><font class="text16RedBold" >*</font>Mottagare&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 				<div class="text11" style="position: relative;" align="left" >
				 				<span style="position:absolute; top:2px; width:250px;" id="8_info" class="popupWithInputText text11"  >
					           		<ul>
					           			<li>Mottagare är vanligtvis densamme som deklaranten, det vill säga den för vars räkning deklarationen lämnas in. 
					           				Det är i allmänhet den som köpt varorna från land utanför EU som är deklarant och även mottagare.
					           			</li>
					           		</ul>
								</span>
				 				</div>
			 				</td>
		 				</tr>
	 				</table>
	 			</td>
	 		</tr>
	 		<tr>	
	 			<td width="5">&nbsp;</td>
	            <td >	
					<%-- create record --%>
				 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
					 		<td width="100%">
						 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="10"><td ></td></tr>
							        
							        <tr>
							        	<%-- ================================================================================== --%>
							        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
							        		 These original values will be used when the user clicks "Cancel" buttons (puttting
							        		 back original value)																--%> 
							        	<%-- ================================================================================== --%>
							        	<input type="hidden" name="orig_svih_mokn" id="orig_svih_mokn" value='${model.record.svih_mokn}'>
							        	<input type="hidden" name="orig_svih_mona" id="orig_svih_mona" value='${model.record.svih_mona}'>
							        	<input type="hidden" name="orig_svih_moeo" id="orig_svih_moeo" value='${model.record.svih_moeo}'>
							        	<input type="hidden" name="orig_svih_moa1" id="orig_svih_moa1" value='${model.record.svih_moa1}'>
							        	<input type="hidden" name="orig_svih_moa2" id="orig_svih_moa2" value='${model.record.svih_moa2}'>
							        	<input type="hidden" name="orig_svih_mopn" id="orig_svih_mopn" value='${model.record.svih_mopn}'>
							        	<input type="hidden" name="orig_svih_mopa" id="orig_svih_mopa" value='${model.record.svih_mopa}'>
							        	<input type="hidden" name="orig_svih_molk" id="orig_svih_molk" value='${model.record.svih_molk}'>
							        	
							            <td class="text14" align="left" >&nbsp;&nbsp;
							            <span title="svih_mokn">Kundnummer</span></td>
							            <td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font>
							            <span title="svih_mona">Namn</span>&nbsp;
							            	<a tabindex="-1" id="svih_monaIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>
										</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="svih_mokn" id="svih_mokn" size="8" maxlength="8" value="${model.record.svih_mokn}"></td>
							            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="svih_mona" id="svih_mona" size="30" maxlength="35" value="${model.record.svih_mona}"></td>
							        </tr>
							        <tr>
							            <td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font>
							            <span title="svih_moeo">EORI</span></td>
							            <td class="text14" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="svih_moeo" id="svih_moeo" size="20" maxlength="17" value="${model.record.svih_moeo}"></td>
							            <td align="left">&nbsp;</td>
							        </tr>
							        <tr height="4"><td>&nbsp;</td></tr>
							        <tr>
							            <td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font>
							            <span title="svih_moa1">Adress 1</span></td>
							            <td class="text14" align="left" >&nbsp;&nbsp;
							            <span title="svih_moa2">Adress 2</span></td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="svih_moa1" id="svih_moa1" size="30" maxlength="35" value="${model.record.svih_moa1}"></td>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="svih_moa2" id="svih_moa2" size="30" maxlength="35" value="${model.record.svih_moa2}"></td>
							        </tr>
							        <tr>
							        	<td>
							        		<table>
								        		<tr>
								            		<td class="text14" align="left" >&nbsp;<font class="text16RedBold" >*</font>
								            		<span title="svih_mopa">Postadress</span></td>
												<td align="left">&nbsp;</td>
								            	</tr>
								        		<tr>
								            		<td align="left" colspan="2"><input type="text" class="inputTextMediumBlueMandatoryField" name="svih_mopa" id="svih_mopa" size="30" maxlength="35" value="${model.record.svih_mopa}"></td> 
								            		
								        		</tr>    	
							            	</table>
						            </td>
						            <td>
							            	<table>
								        		<tr>
								        			<td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font>
								        			<span title="svih_mopn">Postnummer</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
								            		<td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font>
								            		<span title="svih_molk">Land</span>
													<a tabindex="-1" id="svih_molkIdLink">
           												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
           											</a>																	 			
												</td>
								            	</tr>
								        		<tr>
								        			<td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="svih_mopn" id="svih_mopn" size="10" maxlength="9" value="${model.record.svih_mopn}"></td> 
								            		<td align="left">
								            			<select class="inputTextMediumBlueMandatoryField" name="svih_molk" id="svih_molk">
										            		<option value="">-Välj-</option>
										 				  	<c:forEach var="country" items="${model.gcyCodeList}" >
										 				  		<option value="${country.svkd_kd}"<c:if test="${model.record.svih_molk == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
															</c:forEach>  
													</select>
								            		</td> 
								        		</tr>
							            	</table>
						            </td>
							        </tr>
							        <tr>
							            <td align="left" class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font>
							            <span title="svih_moha">Handläggare</span></td>
							            <td align="left"class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font>
							            <span title="svih_motl">Telefon</span></td>
							        </tr>
							        <tr>
							            <td align="left" ><input type="text" class="inputTextMediumBlueMandatoryField" name="svih_moha" id="svih_moha" size="25" maxlength="35" value="${model.record.svih_moha}"></td>
							            <td align="left" ><input type="text" class="inputTextMediumBlueMandatoryField" name="svih_motl" id="svih_motl" size="20" maxlength="25" value="${model.record.svih_motl}"></td>
							        </tr>
							        
							        <tr height="15">
							            <td class="text14Bold" align="left" >&nbsp;</td> 
							        </tr>
						        </table>
					        </td>
				        </tr>
					</table>          
            	</td>
           	</tr> 
           	<%-- INVOICE AMOUNT Fields --%>
			<tr height="10"><td></td></tr>
            <tr>
	            <td width="5">&nbsp;</td>
	            <td >
	                <table align="left" border="0" cellspacing="0" cellpadding="0">
	                	<c:choose>
		                	<c:when test="${ empty model.record.invoiceListTotSum}">
						 		<tr>
						 			<td class="text14">
						 				<b>&nbsp;22.</b>
						 			</td>	
						 			<td class="text14">
						 				<font class="text16RedBold" >*</font>
						 				<span title="svih_fabl">Fakt.total&nbsp;</span>
						 			</td>
						 			<td align="left" >
						 				<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="svih_fabl" id="svih_fabl" size="20" maxlength="20" value="${model.record.svih_fabl}">
						 			</td>
						 			<td class="text14">&nbsp;
						 				<font class="text16RedBold" >*</font>
						 				<span title="svih_vakd">Valuta</span>
						 			</td>
						 			<td class="text14">	
						 				<%-- Note: onChange event in jQuery for this currency list --%>
						 				<select class="inputTextMediumBlueMandatoryField" name="svih_vakd" id="svih_vakd" >
						 				  <option value="">-Välj-</option>	
						 				  <c:forEach var="currency" items="${model.mdxCodeList}" >
					 				  		<option value="${currency.svkd_kd}"<c:if test="${model.record.svih_vakd == currency.svkd_kd}"> selected </c:if> >${currency.svkd_kd}</option>
										  </c:forEach>  
										</select>
										<a tabindex="-1" id="svih_vakdIdLink">
											<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
										</a>																	 			
										
					 				</td>
				 				</tr>
				 				<tr>
				 					<td>&nbsp;</td>
					 				<td class="text14" align="left">
					 					<font class="text16RedBold" >*</font>
						 				<span title="svih_vaku">Kurs&nbsp;</span>
						 			</td>
						 			<td class="text14" align="left" ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="svih_vaku" id="svih_vaku" size="20" maxlength="20" value="${model.record.svih_vaku}"></td>
						 			<td class="text14" align="left" >&nbsp;
						 			<span title="svih_vaom">Faktor</span>
						 			</td>
						 			<td class="text14">
						 				<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="svih_vaom" id="svih_vaom" size="10" maxlength="10" value="${model.record.svih_vaom}">
						 			</td>
				 				</tr>
			 				</c:when>
			 				<c:otherwise> <%-- otherwise READ ONLY --%>
			 					<tr>
						 			<td class="text14">
						 				<b>&nbsp;22.</b>
						 			</td>
						 			<td class="text14">
						 				<font class="text16RedBold" >*</font>	
						 				<span title="svih_fabl">Fakt.total&nbsp;</span>
						 			</td>
						 			<td align="left" >
						 				<input readonly type="text" class="inputTextReadOnly" name="svih_fabl" id="svih_fabl" size="20" maxlength="20" value="${model.record.invoiceListTotSum}">
						 			</td>
						 			<td class="text14">&nbsp;
						 				<font class="text16RedBold" >*</font>
						 			</td>
						 			<td class="text14">	
						 				<span title="svih_vakd">Valuta&nbsp;</span>
						 			</td>
						 			<td class="text14">	
						 				<input readonly type="text" class="inputTextReadOnly" name="svih_vakd" id="svih_vakd" size="4" maxlength="3" value="${model.record.invoiceListTotValidCurrency}">
					 				</td>
				 				</tr>
				 				<tr>
					 				<td>&nbsp;</td>
					 				<td class="text14" >
					 					<font class="text16RedBold" >*</font>
					 					<span title="svih_vaku">Kurs&nbsp;</span>
						 			</td>
						 			<td class="text14" align="left" ><input readonly type="text" class="inputTextReadOnly" name="svih_vaku" id="svih_vaku" size="20" maxlength="20" value="${model.record.invoiceListTotKurs}"></td>
						 			<td>&nbsp;</td>
						 			<td class="text14" align="left" >
						 				<span title="svih_vaom">Faktor&nbsp;</span>
						 			</td>
						 			<td class="text14" align="left" >
						 				<input readonly type="text" class="inputTextReadOnly" name="svih_vaom" id="svih_vaom" size="10" maxlength="10" value="${model.record.svih_vaom}">
						 			</td>
				 				</tr>
			 				</c:otherwise>
		 				</c:choose>
		 				<tr height="10"><td></td></tr>
		 				<tr>
		 					<td class="text14Gray" align="left" colspan="2">
				 				<span title="invoiceListTotSum/invoiceListTotValidCurrency"></span>Fakt.total (fakt.lista)&nbsp;</span>
				 			</td>
				            <td colspan="2" class="text14" align="left" >
				            <input readonly type="text" class="inputTextReadOnly"  name="invoiceListTotSum" id="invoiceListTotSum" size="15" value='${ model.record.invoiceListTotSum}'>
				            <input readonly type="text" class="inputTextReadOnly"  name="invoiceListTotValidCurrency" id="invoiceListTotValidCurrency" size="5" value='${ model.record.invoiceListTotValidCurrency}'>
				            
				            <%-- Removed 2.feb.2018 (CB/DHL requirement. Should be automatic and the button should not be used manually ...
				            &nbsp;<button title="Hente summen fra Finans.oppl." name="getInvoiceListaSumButton" id="getInvoiceListaSumButton" class="buttonGrayWithGreenFrame" type="button" >Hämta Fakt.total</button>
				             --%>
				            
				            <input type="hidden" name="invoiceListTotKurs" id="invoiceListTotKurs" value='${ model.record.invoiceListTotKurs}'>
				            </td>
				        </tr>
				        <tr height="2"><td></td></tr>
				        <tr>
			 				<td class="text14Gray" align="left" colspan="2">
				 				<span title="sumOfItemAmounts">Fakt.total (Varuposter)&nbsp;</span>
				 			</td>
				 			<td colspan="2" class="text14" align="left" >
				 				<input readonly style="text-align: left" type="text" class="inputTextReadOnly" name="sumOfInvoiceAmountInItemLines" id="sumOfInvoiceAmountInItemLines" size="20" maxlength="20" value="${fn:replace(model.record.sumOfInvoiceAmountInItemLinesStr, '.', ',')}">
				 				<c:if test="${not empty (sumOfInvoiceAmountInItemLinesStr && model.record.svih_fabl)}">
			            			<c:if test="${model.record.sumOfInvoiceAmountInItemLines != model.record.svih_fabl_dbl}">
						            	<img onMouseOver="showPop('itemsSumInvoiceAmount_info');" onMouseOut="hidePop('itemsSumInvoiceAmount_info');" width="18px" height="20px" src="resources/images/redFlag.png" border="0" alt="invoice amount warning">	
				            		</c:if>
				            	</c:if>
				            	<div class="text11" style="position: relative;" align="left">
					            		<span style="position:absolute; left:10px; top:0px; width:250px;" id="itemsSumInvoiceAmount_info" class="popupWithInputText"  >
					           			<font class="text11">
					           			<p><b>Fakt.Totalbelopp (varuposter)</b></p>
					           			<p>
					           			Summan of Fakt.Totalbelopp på varupostnivå stämmer inte med det angivna Fakt.Totalbelopp i denna post.
					           			Vi rekommenderar att se över vad som ev. kan vara fel genom att kontrollera varuposterna.
					           			</p>
					           			</font>
										</span>		
									</div>
				 			</td>
		 				</tr>
		 				<tr height="15"><td></td></tr>
					</table>
					</td>
			</tr>
			<tr>
				<td width="5">&nbsp;</td>
	            <td >
					<%-- Special section --%>
					<table align="left" class="formFrameHeader" width="100%" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text14White">
				 				<b>&nbsp;44.</b>&nbsp;Särskilda upplysningar&nbsp;/&nbsp;Bilagda handlingar&nbsp;/&nbsp;Certifikat och tillstånd&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
			 				</td>
		 				</tr>
	 				</table>
	 			</td>
 			</tr>
 			<tr>
	 			<td width="5">&nbsp;</td>
	            <td >
	 				<%-- create record --%>
				 	<table align="left" class="formFrame" width="100%" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
					 		<td>
						 		<table align="left" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="15">
							            <td class="text14Bold" align="left" >&nbsp;</td> 
							        </tr>
							        <tr>
							            <td class="text14" align="left" >&nbsp;
							            <font class="text16RedBold" >*</font><span title="svih_fatx">Fakt.nr.&nbsp;</span></td>
							            <td ><input type="text" class="inputTextMediumBlueMandatoryField" name="svih_fatx" id="svih_fatx" size="40" maxlength="50" value='${model.record.svih_fatx}'></td>
							            <td class="text14" align="left" >&nbsp;&nbsp;&nbsp;
							            <img onMouseOver="showPop('faktTyp_info');" onMouseOut="hidePop('faktTyp_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				<font class="text16RedBold" >*</font><span title="svih_faty">Fakt.typ&nbsp;</span>
							            <div class="text11" style="position: relative;" align="left" > 
							            <span style="position:absolute; top:2px; width:250px;" id="faktTyp_info" class="popupWithInputText text11"  >
						           			<ul>
							           			<li><b>N380</b> handelsfaktura</li>
							           			<li><b>N325</b> proformafaktura</li>
							           		</ul>
										</span>
										</div>							            
										</td>
										
							            <td>
							 				<select class="inputTextMediumBlueMandatoryField" name="svih_faty" id="svih_faty">
							 					<option value="">-Välj-</option><option value="N380"<c:if test="${model.record.svih_faty == 'N380'}"> selected </c:if> >N380</option>
											  	<option value="N325"<c:if test="${model.record.svih_faty == 'N325'}"> selected </c:if> >N325</option>
											</select>
			 							</td>
							        </tr>
							        <tr height="15">
							            <td class="text14Bold" align="left" >&nbsp;</td> 
							        </tr>
								</table>
							</td>
						</tr>
					</table>
					  
				</td>
		  	</tr>
           	</table>
		</td>
		<%-- --------------- --%>
		<%-- RIGHT SIDE CELL --%>
		<%-- --------------- --%>
		<td width="50%" align="left" valign="top">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="2">&nbsp;</td>
					<td valign="top">
			 			<table border="0" cellspacing="0" cellpadding="0">
			 				<tr >	
			            		<td colspan="4" class="text9BlueGreen" valign="bottom" align="left" >
				 				<%-- only status = M or emtpy status is allowed --%>
			 				    <c:choose>
				 				    <c:when test="${model.record.svih_syst == 'M' || empty model.record.svih_syst}">
				 				    	<input tabindex=-1 class="inputFormSubmit" type="submit" name="submit" id="submit" onclick="javascript: form.action='tdsimport_edit.do';" value='<spring:message code="systema.tds.import.createnew.submit"/>'/>
				 				    	&nbsp;&nbsp;
				 				    	<c:if test="${not empty model.record.svih_syop && model.record.validUpdate}">
				 				    		<input tabindex=-2 class="inputFormSubmit" type="submit" name="send" id="send" onclick="javascript: form.action='tdsimport_send.do';" value='<spring:message code="systema.tds.import.createnew.send"/>'/>
				 				    	</c:if>
				 				    </c:when>
				 				    <c:otherwise>
				 				    	<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value='Ej uppdaterbart'/>
				 				    </c:otherwise>	
			 				    </c:choose>
			 				    </td>
		 				    </tr>
		 				    <tr height="5"><td class="text">&nbsp;</td> </tr>
					 		<tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('15_a_info');" onMouseOut="hidePop('15_a_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>15a.</b><font class="text16RedBold" >*</font><span title="svih_avut">Avsändnings-/Exportland</span>
			 					<div class="text11" style="position: relative;" align="left" >
			 					<span style="position:absolute; top:2px; width:250px;" id="15_a_info" class="popupWithInputText text11"  >
				           			<ul>
				           				<li>Här anger du landkoden för avsändnings-/exportlandet. Koden består av två bokstäver, SE för Sverige. Avsändnings-/exportlandet kan aldrig vara EU.</li>
				           			</ul>
								</span>
								</div>		
					            </td>
					            
					            <td >
					            	<select class="inputTextMediumBlueMandatoryField" name="svih_avut" id="svih_avut">
				 						<option value="">-Välj-</option>
					 				  	<c:forEach var="country" items="${model.gcyCodeList}" >
					 				  		<option value="${country.svkd_kd}"<c:if test="${model.record.svih_avut == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
										</c:forEach>  
									</select>
									<a tabindex="-1" id="svih_avutIdLink">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>																	 			
								</td>
							</tr>
				            
						</table>
					</td>
				</tr>
				<tr>
					<td width="2">&nbsp;</td>
			 		<td>
			 			<table border="0" cellspacing="0" cellpadding="0">
					 		<tr height="15">
					            <td class="text14Bold" align="left" >&nbsp;</td> 
					        </tr>
					        <tr height="5"><td class="text">&nbsp;</td> </tr>
							<tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('26_info');" onMouseOut="hidePop('26_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>26.</b><font class="text16RedBold" >*</font><span title="svih_trin">Transportsätt inrikes</span>
					            <div class="text11" style="position: relative;" align="left" >
					            <span style="position:absolute;top:2px; width:250px;" id="26_info" class="popupWithInputText text11"  >
					           		<ul>
				           				<li>Här anger du koden för transportsätt för det transportmedel på vilket godset är lastat när du lämnar exportdeklarationen</li>
				           			</ul>
								</span>
								</div>
								</td>
										
					            <td >
					            	<select class="inputTextMediumBlueMandatoryField" name="svih_trin" id="svih_trin">
				 						<option selected value="">-Välj-</option>
								  		<option value="1"<c:if test="${model.record.svih_trin == 1}"> selected </c:if> >1.Sjötransport</option>
								  		<option value="2"<c:if test="${model.record.svih_trin == 2}"> selected </c:if> >2.Järnvägstransport</option>
								  		<option value="3"<c:if test="${model.record.svih_trin == 3}"> selected </c:if> >3.Vägtransport</option>
								  		<option value="4"<c:if test="${model.record.svih_trin == 4}"> selected </c:if> >4.Flygtransport</option>
								  		<option value="5"<c:if test="${model.record.svih_trgr == 5}"> selected </c:if> >5.Postförsändelse</option>
								  		<option value="7"<c:if test="${model.record.svih_trgr == 7}"> selected </c:if> >7.Fasta transportinstallationer</option>
								  		<option value="8"<c:if test="${model.record.svih_trin == 8}"> selected </c:if> >8.Transport på inre vattenvägar</option>
								  		<option value="9"<c:if test="${model.record.svih_trin == 9}"> selected </c:if> >9.Egen framdrivning</option>
									</select>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="2">&nbsp;</td>
			 		<td>
			 			<table border="0" cellspacing="0" cellpadding="0">
			 				<tr height="5"><td class="text">&nbsp;</td> </tr>
			 				<tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('25_info');" onMouseOut="hidePop('25_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>25.</b><font class="text16RedBold" >*</font><span title="svih_trgr">Transportsätt vid gränsen</span>
					            <div class="text11" style="position: relative;" align="left" >
					            <span style="position:absolute;top:2px; width:250px;" id="25_info" class="popupWithInputText text11"  >
					           		<ul>
				           				<li>Här anger du koden för transportsätt för det aktiva transportmedel som varorna transporteras med över gemenskapens yttre gräns.</li>
				           			</ul>
								</span>	
								</div>	
								</td>
					            <td >
					            	<select class="inputTextMediumBlueMandatoryField" name="svih_trgr" id="svih_trgr">
				 						<option value="">-Välj-</option>
								  		<option value="1"<c:if test="${model.record.svih_trgr == 1}"> selected </c:if> >1.Sjötransport</option>
								  		<option value="2"<c:if test="${model.record.svih_trgr == 2}"> selected </c:if> >2.Järnvägstransport</option>
								  		<option value="3"<c:if test="${model.record.svih_trgr == 3}"> selected </c:if> >3.Vägtransport</option>
								  		<option value="4"<c:if test="${model.record.svih_trgr == 4}"> selected </c:if> >4.Flygtransport</option>
								  		<option value="5"<c:if test="${model.record.svih_trgr == 5}"> selected </c:if> >5.Postförsändelse</option>
								  		<option value="7"<c:if test="${model.record.svih_trgr == 7}"> selected </c:if> >7.Fasta transportinstallationer</option>
								  		<option value="8"<c:if test="${model.record.svih_trgr == 8}"> selected </c:if> >8.Transport på inre vattenvägar</option>
								  		<option value="9"<c:if test="${model.record.svih_trgr == 9}"> selected </c:if> >9.Egen framdrivning</option>
									</select>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="2">&nbsp;</td>
			 		<td>
						<table border="0" cellspacing="0" cellpadding="0">	
					 		<tr>
					            <td class="text14" align="left">
					            <img onMouseOver="showPop('21_info');" onMouseOut="hidePop('21_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>21.</b><span title="svih_tral">Aktiva transportmedlets nationalitet vid gränspassagen</span>
					            <div class="text11" style="position: relative;" align="left" >
					            <span style="position:absolute;top:2px; width:250px;" id="21_info" class="popupWithInputText text11"  >
				           			<ul>
				           				<li><b>Första delfältet</b><br/>
				           				Aktiva transportmedlets identitet vid gränspassagen
										Fältet är obligatoriskt för jordbruksvaror som du begär exportbidrag för (det vill säga om du angett någon av dessa koder i fält 37:2:  E51, E52, E53, E61, E62, E63, E71, F62, F63) och om samtidigt koden i fält 25
										Ange identitet, exempelvis registreringsnummer eller namn på det transportmedel som du tror kommer att användas då godset lämnar gemenskapens yttre gräns.
										<br/><br/></li>
										
										<li><b>Andra delfältet</b><br/>
				           				Aktiva transportmedlets nationalitet vid gränspassagen
										Fältet är obligatoriskt vid export om koden i fält 25 "Transportsätt vid gränsen"
										</li>
										
				           			</ul>
								</span>
								</div>
								</td>	
								
								<td>
				            		<select class="selectMediumBlueE2" name="svih_tral" id="svih_tral">
				 						<option value="">-Välj-</option>
					 				  	<c:forEach var="country" items="${model.gcyCodeList}" >
					 				  		<option value="${country.svkd_kd}"<c:if test="${model.record.svih_tral == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
										</c:forEach>  
									</select>
									<a tabindex="-1" id="svih_tralIdLink">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>																		 			
					            	</td>
					        </tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="2">&nbsp;</td>
			 		<td>
			 			<table border="0" cellspacing="1" cellpadding="0">
			 				<tr height="5"><td class="text">&nbsp;</td> </tr>
			 				<tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('30_info');" onMouseOut="hidePop('30_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>30.</b><span title="svih_golk">Godslokal kod</span>
					            <div class="text11" style="position: relative;" align="left" >
					            <span style="position:absolute; top:2px; width:250px;" id="30_info" class="popupWithInputText text11"  >
				           			<ul>
				           				<li>Koden består av 3 bokstäver och talar om på vilket lager varan finns.
				           				Du kan fråga innehavaren av lagret om du är osäker på godslokalkoden.
				           				</li>
				           			</ul>
								</span>
								</div>	
								</td>

							    <td ><input type="text" class="inputTextMediumBlue" name="svih_golk" id="svih_golk" size="4" maxlength="3" value="${model.record.svih_golk}"></td>	
					        </tr>
					        <tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('godsnr_info');" onMouseOut="hidePop('godsnr_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <span title="svih_godn">Godsnummer</span>
					            <div class="text11" style="position: relative;" align="left" >
					            <span style="position:absolute;top:2px;width:250px;" id="godsnr_info" class="popupWithInputText text11"  >
				           			<p>Denna fält är ingenting som hör till tullverkets deklaration.
				           			   Den är endast som hjälp för att ändra alla godsnummer på varupostnivå, om man vill uppdatera alla...	
				           			</p>
								</span>	
								</div>
								</td>	
								<td ><input type="text" class="inputTextMediumBlue" name="svih_godn" id="svih_godn" size="15" maxlength="14" value="${model.record.svih_godn}"></td>
								<c:if test="${not empty model.record.svih_syav && not empty model.record.svih_syop}">
									<c:if test="${model.record.svih_syst == 'M' || empty model.record.svih_syst}">
										<td nowrap >&nbsp;
											<button name="godsnrButton" id="godsnrButton" class="buttonGrayWithGreenFrame" type="button" >Uppd. alla vp.</button>
										</td>
									</c:if>
								</c:if>					       
	        				</tr>
				            <tr height="5"><td class="text">&nbsp;</td> </tr>
				            <tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('6_info');" onMouseOut="hidePop('6_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>6.&nbsp;</b><font class="text16RedBold" >*</font><span title="svih_kota">Antal kollin (alltid > 0)</span>
					            <div class="text11" style="position: relative;" align="left" >
					            <span style="position:absolute;top:2px; width:250px;" id="6_info" class="popupWithInputText text11"  >
				           			<ul>
				           				<li>Här anger du sändningens totala antal kolli. 
				           					Är godset oemballerat räknas varje vara som ett kolli. 
				           					Varje deklarerad bulkpost i fält <b>31</b> (Varuposter) räknas som ett kolli i fält <b>6</b>.<br/><br/> 
				           					Uppgifterna om kollital i fält 6 och 31 jämförs med varandra. Det innebär att varje bulkpost på varupostnivå i fält 31 räknas som ett kolli vid summering av antal kolli i fält 6.
				           				</li>
				           			</ul>
								</span>	
								</div>
								</td>
					            <td >
				            		<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlueMandatoryField" name="svih_kota" id="svih_kota" size="8" maxlength="7" value="${model.record.svih_kota}">
					            </td>
					        </tr>
					        <tr>
				        		<td class="text14Gray" align="center" >
				        			Antal kollin (varuposter)&nbsp;
				        		</td>
					        	<td >
				            		<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="sumOfAntalKolliInItemLines" id="sumOfAntalKolliInItemLines" size="8" maxlength="7" value="${model.record.sumOfAntalKolliInItemLinesStr}">
				            		<c:if test="${not empty (model.record.sumOfAntalKolliInItemLinesStr && model.record.svih_kota)}">
					            		<c:if test="${model.record.svih_kota != model.record.sumOfAntalKolliInItemLinesStr}">
							            <img onMouseOver="showPop('itemsSum_info');" onMouseOut="hidePop('itemsSum_info');" width="18px" height="20px" src="resources/images/redFlag.png" border="0" alt="kolliantal warning">	
							            <div class="text11" style="position: relative;" align="left">
							            <span style="position:absolute; left:10px; top:2px; width:250px;" id="itemsSum_info" class="popupWithInputText text11" >
						           			<p>	
						           			Summan of antal kolli på varulinjenivå stämmer inte med det angivna antal kolli i denna post. Vi rekommenderar att se över vad som ev. kan vara fel genom att kontrollera varulinjerna.
						           			</p>
						           			Om talet här till vänster är = <b>-1</b> betyder det att det finns mer än 0-varulinjer och att summan av varulinjer kolliantal är = 0 (vilket inte är korrekt).
										</span>	
										</div>											
					            		</c:if>
				            		</c:if>
					            </td>
					        </tr>
					        <tr height="2"><td class="text">&nbsp;</td> </tr>
				            <tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('35_info');" onMouseOut="hidePop('35_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>35.</b><span title="svih_brut">Bruttovikt</span>
					            <div class="text11" style="position: relative;" align="left" >
					            <span style="position:absolute;top:2px; width:250px;" id="35_info" class="popupWithInputText text11"  >
				           			<ul>
				           				<li>Bruttovikten anger du antingen för respektive varupost eller för hela sändningen. Du ska inte ange enheten kg. Bruttovikten är varornas totala vikt inklusive allt emballage, men exklusive containrar och annan transportutrustning.
				           				</li>
				           				<li>För bruttovikt som överstiger 1 kg och inbegriper del av kg kan följande avrundning ske:<br/><br/>
				           					- mellan 0,001 och 0,499 kg: avrundning neråt (kg)<br/>
				           					- mellan 0,5 och 0,999 kg: avrundning uppåt (kg)<br/>
				           				</li>
				           			</ul>
				           			Vi rekommenderar att bruttovikt över 1 kg avrundas till heltal. Bruttovikt under 1 kg ska anges med tre decimaler, exempelvis 0.123. 
				           			Värdet 0 är inte tillåtet. Punkt anges som decimaltecken.
								</span>	
								</div>
					            </td>
					            <td ><input onKeyPress="return amountKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="svih_brut" id="svih_brut" size="13" maxlength="13" value="${model.record.svih_brut}"></td>
					        </tr>
					        <tr>
				        		<td class="text14Gray" align="center" >
				        			Bruttovikt (varuposter)&nbsp;
				        		</td>
						        <td colspan="2">
				            		<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="sumOfGrossWeightInItemLinesStr" id="sumOfGrossWeightInItemLinesStr" size="13" maxlength="13" value="${model.record.sumOfGrossWeightInItemLinesStr}">
				            		
				            		<c:if test="${not empty (model.record.sumOfGrossWeightInItemLinesStr && model.record.svih_brut)}">
				            			<c:if test="${model.record.sumOfGrossWeightInItemLinesStr != model.record.svih_brut}">
							            	<img onMouseOver="showPop('itemsSumGrossweight_info');" onMouseOut="hidePop('itemsSumGrossweight_info');" width="18px" height="20px" src="resources/images/redFlag.png" border="0" alt="kolliantal warning">	
					            		</c:if>
					            		
				            		</c:if>
				            		<div class="text11" style="position: relative;" align="left">
					            		<span style="position:absolute;left:10px; top:2px; width:250px;" id="itemsSumGrossweight_info" class="popupWithInputText text11"  >
					           			<p><b>Bruttovikt (varuposter)</b></p>
					           			<p>
					           			Summan of bruttovikt på varupostnivå stämmer inte med det angivna bruttovikten i denna post.
					           			Vi rekommenderar att se över vad som ev. kan vara fel genom att kontrollera varuposterna.
					           			</p>
					           			Om talet här är = <b>-1</b> betyder det att det finns mer än 0-varuposter och att summan av bruttovikter är = 0 (vilket inte är korrekt).
										</span>		
									</div>	
									 				            		
					            </td>
					        </tr>
					     	<tr height="20"><td>&nbsp;</td></tr>
					     	<tr>
					     		<td class="text14" align="left" >
					            <img onMouseOver="showPop('20_info');" onMouseOut="hidePop('20_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>20a.</b>&nbsp;<span title="svih_lekd">Leveransvillkor - kod</span>
					            <div class="text11" style="position: relative;" align="left" >
					            <span style="position:absolute;top:2px; width:250px;" id="20_info" class="popupWithInputText text11"  >
				           			Incoterms
				           			<ul>
				           				<li><b>EXW</b>&nbsp;- Ex Works</li>
				           				<li><b>FCA</b>&nbsp;- Free Carrier</li>
				           				<li><b>CPT</b>&nbsp;- Carriage Paid to</li>
				           				<li><b>CIP</b>&nbsp;- Carriage and Insurance Paid to</li>
				           				<li><b>DAT</b>&nbsp;- Delivered at Terminal</li>
				           				<li><b>DAP</b>&nbsp;- Delivered at Place</li>
				           				<li><b>DDP</b>&nbsp;- Delivery Duty Paid</li>
				           				<li><b>FAS</b>&nbsp;- Free Alongside Ship</li>
				           				<li><b>FOB</b>&nbsp;- Free on Board</li>
				           				<li><b>CFR</b>&nbsp;- Cost and Freight</li>
				           				<li><b>CIF</b>&nbsp;- Cost, Insurance and Freight</li>
				           			</ul>
								</span>
								</div>	
					            </td>
		 						<td class="text14">
		 							<select class="selectMediumBlueE2" name="svih_lekd" id="svih_lekd">
		 							<option value="">-Välj-</option>
								  		<option value="EXW"<c:if test="${model.record.svih_lekd == 'EXW'}"> selected </c:if> >EXW</option>
								  		<option value="FCA"<c:if test="${model.record.svih_lekd == 'FCA'}"> selected </c:if> >FCA</option>
				 						<option value="CPT"<c:if test="${model.record.svih_lekd == 'CPT'}"> selected </c:if> >CPT</option>
								  		<option value="CIP"<c:if test="${model.record.svih_lekd == 'CIP'}"> selected </c:if> >CIP</option>
				 						<option value="DAT"<c:if test="${model.record.svih_lekd == 'DAT'}"> selected </c:if> >DAT</option>
								  		<option value="DAP"<c:if test="${model.record.svih_lekd == 'DAP'}"> selected </c:if> >DAP</option>
				 						<option value="DDP"<c:if test="${model.record.svih_lekd == 'DDP'}"> selected </c:if> >DDP</option>
								  		<option value="FAS"<c:if test="${model.record.svih_lekd == 'FAS'}"> selected </c:if> >FAS</option>
				 						<option value="FOB"<c:if test="${model.record.svih_lekd == 'FOB'}"> selected </c:if> >FOB</option>
								  		<option value="CFR"<c:if test="${model.record.svih_lekd == 'CFR'}"> selected </c:if> >CFR</option>
								  		<option value="CIF"<c:if test="${model.record.svih_lekd == 'CIF'}"> selected </c:if> >CIF</option>
								  	</select>
		 						</td>
					     	</tr>
							<tr height="1"><td class="text"></td></tr>					     						     	
				            <tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('20b_info');" onMouseOut="hidePop('20b_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>20b.</b>&nbsp;<span title="svih_leor">Leveransvillkor - ort</span>
					            <div class="text11" style="position: relative;" align="left" >
					            <span style="position:absolute;top:2px; width:250px;" id="20b_info" class="popupWithInputText text11"  >
				           			<ul>
				           				<li>Ort skall vara samma som Avsändares postadress. Undantaget <b>DDP</b> som skall ha samma som Mottagarens postadress</li>
				           			</ul>
								</span>	
								</div>
					            </td>
					            <td ><input type="text" class="inputTextMediumBlue" name="svih_leor" id="svih_leor" size="15" maxlength="25" value="${model.record.svih_leor}"></td>
					        </tr>
					        <tr height="10"><td class="text"></td></tr>					     						     	
				            <tr>
								<td class="text14">
					 				<img onMouseOver="showPop('19_info');" onMouseOut="hidePop('19_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 				<b>19.</b>&nbsp;<font class="text16RedBold" >*</font><span title="svih_cont">Container&nbsp;&nbsp;</span>
				 				
					 				<div class="text11" style="position: relative;" align="left" >
					 				<span style="position:absolute;top:2px; width:250px;" id="19_info" class="popupWithInputText text11"  >
					           			<br/>
					           			Ange <b>1</b> om godset är lastat i container vid passagen av den yttre gränsen. I annat fall anges <b>0</b>. 
					           			<br/>
					           			Om du deklarerat 1 i detta fält måste minst ett containernummer finnas i fält <b>31</b> (Container under Extraordinära uppgifter)
					           			<br/>
				           			
									</span>
									</div>
								</td>
								
		 						<td class="text14" >
		 							<select class="inputTextMediumBlueMandatoryField" name="svih_cont" id="svih_cont">
				 						<option value="0"<c:if test="${model.record.svih_cont == 0}"> selected </c:if> >0</option>
								  		<option value="1"<c:if test="${model.record.svih_cont == 1}"> selected </c:if> >1</option>
								  	</select>
		 						</td>
							</tr>
					     	
						</table>
					</td>
				</tr>
				<tr height="20"><td class="text"></td></tr>
				        
				<tr>
					<td width="2">&nbsp;</td>
			 		<td>
			 			<table width="80%" align="left" border="0" cellspacing="0" cellpadding="0">
			 				<tr height="5">
			 					<td class="text">&nbsp;</td> 
			 					<td class="text">&nbsp;</td> 
			 				</tr>
			 				<tr >
				            	<td class="text">&nbsp;</td> 
		 						<td class="text">&nbsp;</td> 
			 				</tr>
				            <tr >	
			            		<td class="text">&nbsp;</td> 
			 				    <td class="text9BlueGreen" valign="bottom" align="left" >
	
			 				    <%-- only status = M or emtpy status is allowed --%>
			 				    <c:choose>
				 				    <c:when test="${model.record.svih_syst == 'M' || empty model.record.svih_syst}">
				 				    	<input tabindex=-1 class="inputFormSubmit" type="submit" name="submit2" id="submit2" onclick="javascript: form.action='tdsimport_edit.do';" value='<spring:message code="systema.tds.import.createnew.submit"/>'/>
				 				    	&nbsp;&nbsp;
				 				    	<c:if test="${not empty model.record.svih_syop && model.record.validUpdate}">
				 				    		<input tabindex=-2 class="inputFormSubmit" type="submit" name="send2" id="send2" onclick="javascript: form.action='tdsimport_send.do';" value='<spring:message code="systema.tds.import.createnew.send"/>'/>
				 				    	</c:if>
				 				    </c:when>
				 				    <c:otherwise>
				 				    	<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit2" value='Ej uppdaterbart'/>
				 				    </c:otherwise>	
			 				    </c:choose>
		 				    
                				</td>
					        </tr>
				            
						</table>
					</td>
				</tr>
				
			</table>
		</td>
		</tr>
		<tr height="20"><td colspan="2">&nbsp;</td></tr>
		
		<tr height="20"><td colspan="2">&nbsp;</td></tr>
		<tr>
		    <td colspan="2" >
			<%-- ---------------------------- --%>
			<%-- tab area container SECONDARY --%>
			<%-- ---------------------------- --%>
			<table width="100%" class="secondarySectionHeader" border="0" cellspacing="0" cellpadding="0">
		 		<tr height="18">
					<td class="text14WhiteBold">
		 				<b>&nbsp;&nbsp;&nbsp;&nbsp;Extraordinära uppgifter&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<%--EXTRAORDINÄRA --%>
		<tr>
			<td colspan="2" >
			<table width="100%" class="secondarySectionFrame" border="0" cellspacing="0" cellpadding="0">
				<tr height="10"><td colspan="2"></td></tr>
				<tr>
				<td width="50%" valign="top">
				<table width="80%" border="0" cellspacing="0" cellpadding="0">
					<tr>
			 			<td width="5">&nbsp;</td>
			            <td >		
			 				<%-- DEKLARANT --%>
			 				<table width="100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="18px">
						 			<td class="text14White">
						 				&nbsp;<img onMouseOver="showPop('14_info');" onMouseOut="hidePop('14_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>&nbsp;14.</b>Deklarant&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						 					&nbsp;&nbsp;<font style="font-style: italic;">[om annat än Mottagare]</font>
					 						<div class="text11" style="position: relative;" align="left" >
							 				<span style="position:absolute;top:2px; width:250px;" id="14_info" class="popupWithInputText text11"  >
								           		<br/>
							           			Här ska du alltid ange uppgifter för deklarant samt ombudsuppgifter när du agerar ombud. 
							           			<br/><br/>
							           			Om sektionen inte fylls i <b>kopieras</b> alla mottagarfälten till deklarantfälten.
							           			<ul>
								           			<li>Deklarant är det företag/den person som lämnar in en tulldeklaration i eget namn eller det företag/den person i vars namn en tulldeklaration lämnas in (tullkodex artikel 4.18). 
								           				Det är endast företag eller personer etablerade inom gemenskapen eller i Norge som kan vara deklarant. 
														<br>Det finns vissa undantag för detta, exempelvis vid temporär import.
								           			</li>
								           		</ul>
											</span>
											</div>
					 				</td>
				 				</tr>
			 				</table>
			 			</td>
			 		</tr>
			 		<tr>	
			 			<td width="5">&nbsp;</td>
			            <td >	
							<%-- create record --%>
						 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
						 		<tr>
							 		<td>
								 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
									 		<tr height="15">
									            <td class="text14" align="left">&nbsp;</td> 
									        </tr>
									        <tr>
									        	<%-- ================================================================================== --%>
									        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
									        		 These original values will be used when the user clicks "Cancel" buttons (puttting
									        		 back original value)																--%> 
									        	<%-- ================================================================================== --%>
									        	<input type="hidden" name="orig_svih_dkkn" id="orig_svih_dkkn" value='${model.record.svih_dkkn}'>
									        	<input type="hidden" name="orig_svih_dkna" id="orig_svih_dkna" value='${model.record.svih_dkna}'>
									        	<input type="hidden" name="orig_svih_dkeo" id="orig_svih_dkeo" value='${model.record.svih_dkeo}'>
									        	<input type="hidden" name="orig_svih_dka1" id="orig_svih_dka1" value='${model.record.svih_dka1}'>
									        	<input type="hidden" name="orig_svih_dka2" id="orig_svih_dka2" value='${model.record.svih_dka2}'>
									        	<input type="hidden" name="orig_svih_dkpn" id="orig_svih_dkpn" value='${model.record.svih_dkpn}'>
									        	<input type="hidden" name="orig_svih_dkpa" id="orig_svih_dkpa" value='${model.record.svih_dkpa}'>
									        	<input type="hidden" name="orig_svih_dklk" id="orig_svih_dklk" value='${model.record.svih_dklk}'>
							        	
									            <td class="text14" align="left" >&nbsp;&nbsp;
									            <span title="svih_dkkn">Kundnummer</span></td>
									            <td class="text14" align="left" >&nbsp;&nbsp;
									            <span title="svih_dkna">Namn</span>
									            	<a tabindex="-1" id="svih_dknaIdLink">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
													</a>
									            </td>
									        </tr>
									        <tr>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="svih_dkkn" id="svih_dkkn" size="8" maxlength="8" value="${model.record.svih_dkkn}"></td>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="svih_dkna" id="svih_dkna" size="30" maxlength="35" value="${model.record.svih_dkna}"></td>
									        </tr>
									        <tr>
									            <td class="text14" align="left" >&nbsp;&nbsp;
									            <span title="svih_dkeo">EORI</span></td>
									            <td class="text14" align="left" >&nbsp;&nbsp;</td>
									        </tr>
									        <tr>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="svih_dkeo" id="svih_dkeo" size="17" maxlength="17" value="${model.record.svih_dkeo}"></td>
									            <td align="left">&nbsp;</td>
									        </tr>
									        <tr height="4"><td>&nbsp;</td></tr>
									        <tr>
									            <td class="text14" align="left" >&nbsp;&nbsp;
									            <span title="svih_dka1">Adress 1</span></td>
									            <td class="text14" align="left" >&nbsp;&nbsp;
									            <span title="svih_dka2">Adress 2</span></td>
									        </tr>
									        <tr>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="svih_dka1" id="svih_dka1" size="30" maxlength="35" value="${model.record.svih_dka1}"></td>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="svih_dka2" id="svih_dka2" size="30" maxlength="35" value="${model.record.svih_dka2}"></td>
									        </tr>
									        <tr>
									        		<td>
										        		<table>
										        		<tr>
										            		<td class="text14" align="left" >&nbsp;
										            		<span title="svih_dkpa">Postadress</span></td>
										            		<td align="left">&nbsp;</td>
										            	</tr>
										        		<tr>
										            		<td align="left">
										       				<input type="text" class="inputTextMediumBlue" name="svih_dkpa" id="svih_dkpa" size="30" maxlength="35" value="${model.record.svih_dkpa}">
									            			</td> 
										            		<td align="left">&nbsp;</td>
										        		</tr>    	
										            	</table>
									            </td>
									            <td >
										            	<table>
										        		<tr>
										        			<td class="text14" align="left" >&nbsp;&nbsp;
										        			<span title="svih_dkpn">Postnummer</span></td>
										            		<td class="text14" align="left" >&nbsp;&nbsp;
										            		<span title="svih_dklk">Land</span>
															<a tabindex="-1" id="svih_dklkIdLink">
           													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
           													</a>																 			
														</td>
										            	</tr>
										        		<tr >
										        			<td align="left"><input type="text" class="inputTextMediumBlue" name="svih_dkpn" id="svih_dkpn" size="10" maxlength="9" value="${model.record.svih_dkpn}"></td> 
										            		<td align="left">
										            			<select class="selectMediumBlueE2" name="svih_dklk" id="svih_dklk">
												            		<option value="">-Välj-</option>
											 				  	<c:forEach var="country" items="${model.gcyCodeList}" >
											 				  		<option value="${country.svkd_kd}"<c:if test="${model.record.svih_dklk == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
																</c:forEach>  
															</select>
										            		</td> 
										        		</tr>  
										            	</table>
									            </td>
									        </tr>

									        <tr>
									            <td align="left" class="text14" align="left" >&nbsp;&nbsp;
									            <span title="svih_dkha">Handläggare</span></td>
									            <td align="left"class="text14" align="left" >&nbsp;&nbsp;
									            <span title="svih_dktl">Telefon</span></td>
									        </tr>
									        <tr>
									            <td align="left" ><input type="text" class="inputTextMediumBlue" name="svih_dkha" id="svih_dkha" size="25" maxlength="35" value="${model.record.svih_dkha}"></td>
									            <td align="left" ><input type="text" class="inputTextMediumBlue" name="svih_dktl" id="svih_dktl" size="20" maxlength="25" value="${model.record.svih_dktl}"></td>
									        </tr>
							        
									        <tr height="15">
								            		<td class="text14Bold" align="left" >&nbsp;</td>
								            		<td class="text14Bold" align="left" >&nbsp;</td> 
									        </tr>  
								        </table>
							        </td>
						        </tr>
							</table>          
		            	</td>
		           	</tr> 
		           	<tr height="10"><td></td></tr>
		           	<tr>
			 			<td width="5">&nbsp;</td>
			            <td >		
			 				<%-- OMBUD --%>
			 				<table width="100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="18px">
						 			<td class="text14White">
						 				&nbsp;<img onMouseOver="showPop('14_b_info');" onMouseOut="hidePop('14_b_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>&nbsp;14.</b><font class="text16RedBold" >*</font>Ombud&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						 				<div class="text11" style="position: relative;" align="left" >
						 				<span style="position:absolute;top:2px; width:250px;" id="14_b_info" class="popupWithInputText text11"  >
							           		<br>
						           			Uppgifter för ombud ska alltid anges när du agerar som ombud. För att agera som ombud krävs att företaget/personen är registrerad som ombud hos Tullverket samt är etablerad i gemenskapen eller i Norge.
						           			<br>
						           			Ombud
											<ul>
							           			<li>ska ange EORI-nummer</li>
							           			<li>behöver inte lämna uppgift om ombudets fullständiga namn och adress</li>
							           			<li>ska lämna namn och telefonnummer till kontaktperson hos ombud</li>
							           			<li>ska om ett tillstånd med tilläggssiffror (två tecken) åberopas ange tilläggssiffrorna efter sitt svenska EORI-nummer.</li>
							           			
							           		</ul>
										</span>
						 				</div>
					 				</td>
				 				</tr>
			 				</table>
			 			</td>
			 		</tr>
			 		<tr>
			 			<td width="5">&nbsp;</td>
			            <td >	
							<%-- create record --%>
						 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
						 		<tr>
							 		<td>
								 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
									 		<tr height="15">
									            <td class="text14" align="left">&nbsp;</td> 
									        </tr>
									        <tr>
									            <td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font>
									            <span title="svih_omeo">Ombud EORI</span></td>
									            <td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font>
									            <span title="svih_omty">Typ av ombudskap</span></td>
									        </tr>
									        <tr>
									            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="svih_omeo" id="svih_omeo" size="16" maxlength="17" value="${model.record.svih_omeo}"></td>
									            <td align="left">
									            	<select class="inputTextMediumBlueMandatoryField" name="svih_omty" id="svih_omty">
					 									<option value="">-Välj-</option>
									  					<option value="2"
										  				<c:if test="${model.record.svih_omty == '2'}"> selected </c:if> >2-Direkt ombud</option>
										  				<option value="3"
										  				<c:if test="${model.record.svih_omty == '3'}"> selected </c:if> >3-Indirekt ombud</option>
													</select>
												</td>
									        </tr>
									        
									        <tr>
									            <td align="left" class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font>
									            <span title="svih_omha">Handläggare</span></td>
									            <td align="left"class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font>
									            <span title="svih_omtl">Telefon</span></td>
									            <td>&nbsp;</td>
									        </tr>
									        <tr>
									            <td align="left" ><input type="text" class="inputTextMediumBlueMandatoryField" name="svih_omha" id="svih_omha" size="25" maxlength="35" value="${model.record.svih_omha}"></td>
									            <td align="left" ><input type="text" class="inputTextMediumBlueMandatoryField" name="svih_omtl" id="svih_omtl" size="20" maxlength="35" value="${model.record.svih_omtl}"></td>
									            <td>&nbsp;</td>
									        </tr>
								        </table>
							      	</td>
								 </tr>
								 <tr height="20"><td></td></tr>
						 	</table>
		            		</td>
		           	</tr>
		           	
		           	<tr>
			 			<td >&nbsp;</td>
			            <td >&nbsp;</td>
			            
				 	</tr>
						 	
					</table>
				</td>
				<td width="2">&nbsp;</td>
				<%-- --------------- --%>
				<%-- RIGHT SIDE CELL --%>
				<%-- --------------- --%>
				<td width="50%" align="left" valign="top">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="2">&nbsp;</td>
							<td valign="top">
					 			<table border="0" cellspacing="0" cellpadding="0">
				                	<tr>
						 			<td class="text14" >
						 				<table align="left" border="0" cellspacing="0" cellpadding="0">
							 				<tr>
								 				<td nowrap class="text14">
								 					&nbsp;&nbsp;
								 					<span title="svih_kvsa">Kvalitetssäkring tillämpas</span>&nbsp;
								 				</td>
								 				<td align="right" class="text14">
								 					<select class="selectMediumBlueE2" name="svih_kvsa" id="svih_kvsa">
							 						<option value="">-Välj-</option>
											  		<option value="J"<c:if test="${model.record.svih_kvsa == 'J'}"> selected </c:if> >Ja</option>
											  		<option value="N"<c:if test="${model.record.svih_kvsa == 'N'}"> selected </c:if> >Nej</option>
											  		</select>
								 				</td>
							 				</tr>
						 				</table>
						 			</td>
						 		</tr>
						 		</table>
							</td>
						</tr>
						<tr height="10"><td></td><td></td></tr>
						<tr>
							<td width="2">&nbsp;</td>
				 			<td class="text14" >
				 				<table align="left" border="0" cellspacing="0" cellpadding="0">
								<tr>
						 			<td class="text14" align="right" >
						 			<img onMouseOver="showPop('7_info');" onMouseOut="hidePop('7_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 			<b>7.</b>&nbsp;<span title="svih_rfac, svih_rfac2">Ytterligare ref.nr.&nbsp;</span>
						 			<div class="text11" style="position: relative;" align="left" >
						 			<span style="position:absolute;top:2px; width:250px;" id="7_info" class="popupWithInputText text11"  >
					           			<br/>
					           			<b>Ytterligare ref.nr.</b><br/><br/>
					           			Detta är en frivillig uppgift. Här anger du ditt eget referensnummer.
					           			<br/>
									</span>
									</div>
									</td>
									
						 			<td align="left" ><input type="text" class="inputTextMediumBlue" name="svih_rfac" id="svih_rfac" size="25" maxlength="35" value="${model.record.svih_rfac}"></td>
						 		</tr>
						 		<tr>
				 					<td align="left" >&nbsp;</td>
						 			<td align="left" ><input type="text" class="inputTextMediumBlue" name="svih_rfac2" id="svih_rfac2" size="25" maxlength="35" value="${model.record.svih_rfac2}"></td>				 			
				 				</tr>
				 				
				 				</table>
				 			</td>
				 		</tr>
				 		<tr height="4"><td width="2">&nbsp;</td><td>&nbsp;</td></tr>
				 		<tr>
				 			<td width="2">&nbsp;</td>
				 			<td class="text14">
				 				<table align="left" border="0" cellspacing="0" cellpadding="0">
				 				<tr height="5"><td>&nbsp;</td></tr>
				 				<tr>
				 					<td class="text14" >
				 					<img onMouseOver="showPop('klareringsenhetEori_info');" onMouseOut="hidePop('klareringsenhetEori_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 				&nbsp;<span title="svih_kenh">Klareringsenhet&nbsp;</span>
					 				<div class="text11" style="position: relative;" align="left" >
					 				<span style="position:absolute;top:2px;width:250px;" id="klareringsenhetEori_info" class="popupWithInputText text11" >
						           		<br/>
					           			Här kan du ange en beteckning som är unik och gemensam för flera deklarationer som skall klareras samtidigt.
				           			</span>
									</div>
									</td>
									
				 					<td class="text14">
				 						<input type="text" class="inputTextMediumBlue" name="svih_kenh" id="svih_kenh" size="20" maxlength="35" value='${model.record.svih_kenh}'>
				 					</td>
									</tr>
									<tr>
				 					<td class="text14" >
			 						<img onMouseOver="showPop('klareringsbEori_info');" onMouseOut="hidePop('klareringsbEori_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 				&nbsp;<span title="svih_kleo">Klareringsbehörig EORI&nbsp;</span>
				 					<div class="text11" style="position: relative;" align="left" >
				 					<span style="position:absolute;top:2px;width:250px;" id="klareringsbEori_info" class="popupWithInputText text11"  >
						           		
						           			<br/>
						           			Vill du som uppgiftslämnare att någon annan skall ha rätt att elektroniskt begära tullklarering så skall dennes
						           			identitetsnr. (landkod + orgnr.) anges här.<br/><br/>
						           			Om du vill att vem som helst (som du ger fullmakt) skall kunna begära klarering åt dig
						           			anger du SE som landkod och 8989898989 som orgnr. Det är bara den ursprunglige uppgiftslämnaren som kan ändra eller komplettera denna uppgift.
					           			
									</span>
									</div>
									</td>
									
				 					<td class="text14">
				 						<input type="text" class="inputTextMediumBlue" name="svih_kleo" id="svih_kleo" size="20" maxlength="17" value="${model.record.svih_kleo}">
				 					</td>
									</tr>
				 				</table>
				 			</td>
						</tr> 
						<tr height="20"><td>&nbsp;</td><td>&nbsp;</td></tr>
						<tr>
							<td width="2">&nbsp;</td>
				 			<td class="text14">
				 				<table align="left" border="0" cellspacing="0" cellpadding="0">
				 				<tr height="5"><td>&nbsp;</td></tr>
				 				<tr>
				 					<td class="text14" ><b>12.&nbsp;Värdeuppgifter&nbsp;</b></td>
								</tr>
									
				 				</table>
				 			</td>
						</tr> 				 		
						<tr>
							<td width="2">&nbsp;</td>
				 			<td class="text14">
				 				<table align="left" border="0" cellspacing="0" cellpadding="0">
				 				<tr height="2"><td></td></tr>
				 				<tr>
				 					<td class="text14" >12.&nbsp;<span title="svih_vufr">Frakt</span>&nbsp; </td>
				 					<td class="text14">
				 						<input onKeyPress="return amountKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="svih_vufr" id="svih_vufr" size="15" maxlength="11" value="${model.record.svih_vufr}">
				 					</td>
								</tr>
								<tr>
				 					<td class="text14" >12.&nbsp;<span title="svih_vufo">Försäkring</span>&nbsp; </td>
				 					<td class="text14">
				 						<input onKeyPress="return amountKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="svih_vufo" id="svih_vufo" size="15" maxlength="11" value="${model.record.svih_vufo}">
				 					</td>
								</tr>
								<tr>
				 					<td class="text14" >12.&nbsp;<span title="svih_ovko">Övriga kostnader</span>&nbsp; </td>
				 					<td class="text14">
				 						<input onKeyPress="return amountKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="svih_ovko" id="svih_ovko" size="15" maxlength="11" value="${model.record.svih_ovko}">
				 					</td>
								</tr>
								<tr>
				 					<td class="text14" >12.&nbsp;<span title="svih_kara">Kassarabatt</span>&nbsp; </td>
				 					<td class="text14">
				 						<input onKeyPress="return amountKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="svih_kara" id="svih_kara" size="15" maxlength="11" value="${model.record.svih_kara}">
				 					</td>
								</tr>
								<tr>
				 					<td class="text14" >12.&nbsp;<span title="svih_anra">Annan rabatt</span>&nbsp; </td>
				 					<td class="text14">
				 						<input onKeyPress="return amountKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="svih_anra" id="svih_anra" size="15" maxlength="11" value="${model.record.svih_anra}">
				 					</td>
								</tr>	
				 				</table>
				 			</td>
						</tr>
						<tr height="10"><td>&nbsp;</td><td>&nbsp;</td></tr> 				
										
					</table>
					</td>
				</tr>
			</table> <%-- END to the wrapper table for EXTRAORDINARY data --%>	
			</td>			
		</tr>		         
	</table> 
	</form> 
	</td>
 </tr>
 
 	<tr>
	<td>
		<%-- change status admin dialog --%>	
		<div id="dialogUpdateStatus" title="Dialog">
			<form action="tdsimport_updateStatus.do" name="updateStatusForm" id="updateStatusForm" method="post">
			 	<input type="hidden" name="currentAvd" id="currentAvd" value="${model.record.svih_syav}">
			 	<input type="hidden" name="currentOpd" id="currentOpd" value="${model.record.svih_syop}">
			 		
				<p class="text14" >Change status as needed.</p>
				<table>
					<tr>
						<td class="text14" align="left" >&nbsp;Status</td>
						<td class="text14MediumBlue">
							<select class="selectMediumBlueE2" name="selectedStatus" id="selectedStatus">
			            		<option value=" ">-vælg-</option>
			            		<option value="M">M</option>
			            		<option value="G">G</option>
			            		<option value="S">S</option>
			            		<option value="O">O</option>
			            		<option value="K">K</option>
			            		<option value="F">F</option>
			            		<option value="C">C</option>
			            		<option value="A">A</option>
			            		<option value=" ">+</option>
			            		<option value="Q">Q</option>
			            		<option value="E">E</option>
			            			<%--
			 				  	<c:forEach var="record" items="${model.statusCodeList}" >
		                            <option value="${record.dkkd_kd}">${record.dkkd_kd}</option>
								</c:forEach>
								 --%> 
							</select>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</td>
	</tr> 
	
		<%-- -------------------------- --%>	
 <%-- print skilleark dialog    --%>	
 <%-- -------------------------- --%>	
 <tr>
	<td>
		<div id="dialogPrintSkilleArk" title="Dialog">
			<form action="tdsimport_edit_printSkilleArkTopic.do" name="skilleArkForm" id="skilleArkForm" method="post">
			 	<input type="hidden" name="currentAvd" id="currentAvd" value="${model.record.svih_syav}">
			 	<input type="hidden" name="currentOpd" id="currentOpd" value="${model.record.svih_syop}">
				<table>
					<tr>
						<td class="text14" align="left" >&nbsp;Typ</td>
						<td class="text14MediumBlue">
							<select class="selectMediumBlueE2" name="selectedType" id="selectedType">
			            		<option value="">-velg-</option>
			            		<c:forEach var="record" items="${model.typeArchiveCodeList}" >
			 				  		<option value="${record.artype}">${record.artype}&nbsp;${record.artxt}</option>
								</c:forEach>  
							</select>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</td>
</tr> 

 <%-- -------------------------- --%>	
 <%-- upload file dialog         --%>	
 <%-- -------------------------- --%>	
	<tr>
		<td valign="bottom" >
			<div id="dialogUploadArchiveDocument" title="Dialog">
				<table align="left" class="popupFloatingWithRoundCorners3D">
				    <tr height="2"><td></td></tr>
			    	<tr>
					<td valign="top">
					<form name="uploadFileForm" id="uploadFileForm" method="post" enctype="multipart/form-data">
						<input type="hidden" name="applicationUserUpload" id="applicationUserUpload" value='${user.user}'>
						<input type="hidden" name="wsavd" id="wsavd" value='${model.record.svih_syav}'>
						<input type="hidden" name="wsopd" id="wsopd" value='${model.record.svih_syop}'>
						<input type="hidden" name="userDate" id="userDate" value=''>
						<input type="hidden" name="userTime" id="userTime" value=''>
						
							<table id="containerdatatableTable" cellspacing="2" align="left">
								<tr>
									<td colspan="3" class="text14Bold">&nbsp;
										<img style="vertical-align:bottom;" src="resources/images/upload.png" border="0" width="20" height="20" alt="upload">
										&nbsp;File Upload&nbsp;							
									</td>
								</tr>
								<tr>
								<tr height="5"><td></td></tr>
								<tr>
								<td>
									<table>
									<%--
									<tr>
										<td class="text11">&nbsp;Nytt filnavn:</td>
										<td class="text11">&nbsp;<input tabindex=-1 type="text" class="inputText" name="fileNameNew" id="fileNameNew" size="20" maxlength="20" value=""></td>
									</tr>
									 --%>
									<tr>
										<td class="text11">&nbsp;Arkivtyp:</td>
										<td class="text11">&nbsp;
											<select class="selectMediumBlueE2" tabindex=-1 name="wstype" id="wstype">
												<c:forEach var="record" items="${user.arkivKodOpdList}" >
						                       	 	<option value="${record.arkKod}">${record.arkKod}-${record.arkTxt}</option>
												</c:forEach> 
											</select>	
										</td>
									</tr>
									<tr height="5"><td></td></tr>
									<tr>	
										<td class="text11">&nbsp;Fil:</td>
										<td class="text11">
			           						&nbsp;<input type="file" name="fileUpload" id="fileUpload" />
			       						</td>
					           		</tr>
					           		</table>
								</td>
								</tr>
								<tr height="5"><td></td></tr>
			       			</table>
					</form>	
					</td>
					</tr>
				</table>
		</div>		
		</td>
	</tr>
	