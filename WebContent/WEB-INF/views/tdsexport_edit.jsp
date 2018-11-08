<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTds.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tdsglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>			
	<SCRIPT type="text/javascript" src="resources/js/tdsexport_edit.js?ver=${user.versionEspedsg}"></SCRIPT>
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
					<c:when test="${not empty model.record.sveh_sysg}">
						<a id="alinkMainList" tabindex=-1 style="display:block;" href="tdsexport.do?action=doFind&sign=${model.record.sveh_sysg}">
							<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tds.export.list.tab"/></font>
						</a>
					</c:when>
					<c:otherwise>
						<a id="alinkMainList" tabindex=-1 style="display:block;" href="tdsexport.do?action=doFind&sign=${model.sign}">
							<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tds.export.list.tab"/></font>
						</a>	
					</c:otherwise>
				</c:choose>
				
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<c:choose> 
			    <c:when test="${editActionOnTopic=='doUpdate' or editActionOnTopic=='doFetch'}">
					<td width="15%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">
							&nbsp;<spring:message code="systema.tds.export.created.mastertopic.tab"/>
						</font>
						<font class="text14MediumBlue">[${model.record.sveh_syop}]</font>
						<c:if test="${model.record.sveh_syst == 'M' || empty model.record.sveh_syst}">
							<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						</c:if>
					</td>
					
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkInvoices" tabindex=-1 style="display:block;" href="tdsexport_edit_invoice.do?action=doFetch&avd=${model.record.sveh_syav}&sign=${model.record.sveh_sysg}
															&opd=${model.record.sveh_syop}&tullId=${model.record.sveh_tuid}
															&status=${model.record.sveh_syst}&datum=${model.record.sveh_sydt}&fabl=${model.record.sveh_fabl}">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tds.export.invoice.tab"/></font>
						</a>
					</td>
					
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkItemLines" tabindex=-1 style="display:block;" href="tdsexport_edit_items.do?action=doFetch&avd=${model.record.sveh_syav}&sign=${model.record.sveh_sysg}
													&opd=${model.record.sveh_syop}&tullId=${model.record.sveh_tuid}
													&status=${model.record.sveh_syst}&datum=${model.record.sveh_sydt}&fabl=${model.record.sveh_fabl}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tds.export.item.createnew.tab"/>
							</font>
							<c:if test="${model.record.sveh_syst == 'M' || empty model.record.sveh_syst}">
								<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
							</c:if>
						</a>
					</td>
					
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkLogging" tabindex=-1 style="display:block;" href="tdsexport_logging.do?avd=${model.record.sveh_syav}&sign=${model.record.sveh_sysg}
													&opd=${model.record.sveh_syop}&tullId=${model.record.sveh_tuid}
													&status=${model.record.sveh_syst}&datum=${model.record.sveh_sydt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tds.export.logging.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
						</a>
					</td>
					
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkArchive" tabindex=-1 style="display:block;" href="tdsexport_archive.do?avd=${model.record.sveh_syav}&sign=${model.record.sveh_sysg}
													&opd=${model.record.sveh_syop}&tullId=${model.record.sveh_tuid}
													&status=${model.record.sveh_syst}&datum=${model.record.sveh_sydt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.tds.export.archive.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
						</a>
					</td>
					<td width="10%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</c:when>
				<c:otherwise>
					<td width="15%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">&nbsp;<spring:message code="systema.tds.export.createnew.tab"/></font>
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
	<form name="tdsExportSaveNewTopicForm" id="tdsExportSaveNewTopicForm" method="post">
	
	<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
			<%-- --- HIDDEN FORM FIELDS (not visible in form but important with an UPDATE ----- --%>			
			<%-- general (from user profile) --%>
			<input type="hidden" name="action" id="action" value='doUpdate'>
			<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
			<input type="hidden" name="opd" id="opd" value='${model.record.sveh_syop}'>
			<%-- topic specific (syop and tuid) --%>
			<input type="hidden" name="sveh_syav" id="sveh_syav" value='${model.record.sveh_syav}'>
			<input type="hidden" name="sveh_syop" id="sveh_syop" value='${model.record.sveh_syop}'>
			<input type="hidden" name="sveh_syst" id="sveh_syst" value='${model.record.sveh_syst}'>
			<input type="hidden" name="sveh_sydt" id="sveh_sydt" value='${model.record.sveh_sydt}'>
			<input type="hidden" name="sveh_tuid" id="sveh_tuid" value='${model.record.sveh_tuid}'>
		
		<tr height="4">
			<td colspan="2">&nbsp;
				<%-- test indicator /per avdelning --%> 
				<c:forEach var="record" items="${avdListSessionTestFlag}" >
					<c:if test="${record.avd == model.record.sveh_syav}">	
						<c:if test="${record.tst == '1'}">&nbsp;&nbsp;	
							<c:set var="isTestAvd" value="1" scope="request" />
						</c:if>
					</c:if>
				</c:forEach>			
			</td>
		</tr>	
		 
		<%--UPDATE MODE --%> 
		<c:choose>
		<c:when test="${editActionOnTopic=='doUpdate' or editActionOnTopic=='doFetch'}">
	    	<input type="hidden" name="avd" id="avd" value='${model.record.sveh_syav}'>
			<input type="hidden" name="sign" id="sign" value='${model.record.sveh_sysg}'>
			<tr >
				<td align="left" class="text14MediumBlue" >
					&nbsp;&nbsp;&nbsp;&nbsp;<span title="sveh_syav">Avd:</span>&nbsp;${model.record.sveh_syav}&nbsp;&nbsp;<span title="sveh_syop">Ärende:</span>&nbsp;<b>${model.record.sveh_syop}</b>
					&nbsp;&nbsp;<span title="sveh_tuid">Tullid:</span>&nbsp;<b>${model.record.sveh_tuid}</b>
					<a tabindex="-1" class="text14" target="_blank" href="${model.taricFragaTullidURL.value}" onclick="${model.taricFragaTullidURL.windowOpenDimensions}" >
            			<img title="Fråga Tullid (hos Tullverket)" style="vertical-align:bottom;" width="14px" height="16px" src="resources/images/help.png" border="0" alt="question">                		
            		</a>
            		<c:choose>
	 				    <c:when test="${model.record.sveh_syst == 'M' || empty model.record.sveh_syst}">																	 			
							&nbsp;&nbsp;<span title="sveh_sysg">Sign:</span>&nbsp;
		           			<select class="selectMediumBlueE2" name="sveh_sysg" id="sveh_sysg">
			            		<option value="">-Välj-</option>
			 				  	<c:forEach var="record" items="${model.signList}" >
                             	 	<option value="${record.sign}"<c:if test="${model.record.sveh_sysg == record.sign}"> selected </c:if> >${record.sign}</option>
								</c:forEach> 
							</select>
						</c:when>
						<c:otherwise>
							&nbsp;&nbsp;<span title="sveh_sysg">Sign:</span>&nbsp;<b>${model.record.sveh_sysg}</b>
							<input type="hidden" name="sveh_sysg" id="sveh_sysg" value='${model.record.sveh_sysg}'>
						</c:otherwise>
					</c:choose>	
            		&nbsp;&nbsp;
					<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					<span title="sveh_syst">Stat</span><a tabindex=-1 id="updateStatusLink" runat="server" href="#"><font class="text14MediumBlue">u</font></a>s:
					<b>
						<c:choose>
							<c:when test="${empty model.record.sveh_syst}">
								-
							</c:when>
							<c:otherwise>
								${model.record.sveh_syst}
							</c:otherwise>
						</c:choose>
					</b>
					&nbsp;&nbsp;<span title="sveh_sydt">Datum:</span>&nbsp;${model.record.sveh_sydt}
					<div class="text11" style="position: relative;" align="left" >
					<span style="position:absolute;left:350px;top:2px;width:250px;" id="status_info" class="popupWithInputText text11"  >
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
					</span>		
					</div>					
				</td>
				<td align="right" >
					<c:if test="${'1' != isTestAvd}">
						<%--This checkbox appears only in real production. Otherwise use the Testavdelning --%>
						<input tabindex=-1 type="checkbox" name="sveh_0035" id="sveh_0035" value="1" <c:if test="${model.record.sveh_0035 == '1'}"> checked </c:if> ><font class="text14MediumBlue"><b>TEST flag</b></font>&nbsp;&nbsp;&nbsp;						
					</c:if>
					<a tabindex=-1 href="tdsexport_edit_printTopic.do?avd=${model.record.sveh_syav}&opd=${model.record.sveh_syop}">
					 	<img style="cursor:pointer;" src="resources/images/printer.png" width="30" hight="30" border="0" alt="Print">
					</a>
					&nbsp;&nbsp;<img title="Print försättsblad" style="vertical-align: bottom;cursor: pointer;" id="printSkilleArkImg" width="30px" height="30px" src="resources/images/printer2.png" border="0" alt="Print försättsblad">
					&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;<img title="Upload dokument" style="vertical-align: bottom;cursor: pointer;" id="uploadFileImg" width="25px" height="25px" src="resources/images/upload.png" border="0" alt="Upload dokument">
					&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			<%-- test indicator /per avdelning  --%>
			<c:if test="${'1' == isTestAvd}">
				<tr>
					<td align="left" class="text14Red" >
						&nbsp;&nbsp;<b>[TEST Avdelning]</b>
						<input type="hidden" name="testAvdFlag" id="testAvdFlag" value='${isTestAvd}'>
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
	 				  		 <c:choose>
	 				  		 <c:when test="${not empty model.record.sveh_syav}">
                        	 	<option value="${record.avd}"<c:if test="${model.record.sveh_syav == record.avd}"> selected </c:if> >${record.avd}<c:if test="${record.tst == '1'}">&nbsp;(test)</c:if></option>
                        	 </c:when>
                        	 <c:otherwise>
                        	 	<option value="${record.avd}"<c:if test="${model.avd == record.avd}"> selected </c:if> >${record.avd}<c:if test="${record.tst == '1'}">&nbsp;(test)</c:if></option>
                        	 </c:otherwise>
                        	 </c:choose>
						</c:forEach> 
					</select>
					
					&nbsp;Sign:&nbsp;
           			<select class="selectMediumBlueE2" name="sign" id="sign">
	            		<option value="">-Välj-</option>
	 				  	<c:forEach var="record" items="${model.signList}" >
                             	 	<option value="${record.sign}"<c:if test="${model.record.sveh_sysg == record.sign}"> selected </c:if> >${record.sign}</option>
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
		<table style="width:90%;" border="0" cellspacing="0" cellpadding="0">
			<tr>
	            <td width="5">&nbsp;</td>
	            <td >
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
	            		
				 		<tr>
				 			<td class="text14">
				 				<img onMouseOver="showPop('1_1_info');" onMouseOut="hidePop('1_1_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>1.1</b><font class="text16RedBold" >*</font><span title="sveh_dek1">Dekl.&nbsp;</span>
			 					<div class="text11" style="position: relative;" align="left" >
			 					<span style="position:absolute;top:2px; width:250px;" id="1_1_info" class="popupWithInputText text11"  >
					           			<ul>
					           				<li><b>EU</b><br>Vid export till EFTA-land, det vill säga Norge, Schweiz, Island, Liechtenstein och andra fördragsslutande parter i konventionen om ett
					           						gemensamt transiteringsförfarande och konventionen om förenkling av formaliteterna vid handel.
					           				</li>
					           				<li><b>CO</b><br>Vid utförsel till ett område inom gemeskapens tullområde men inte tillhör skatteområdet, exempelvis Åland eller 
					           						Kanarieöarna. Eller när du lägger upp förfinansierade varor på tullager.
					           				</li>
					           				<li><b>EX</b><br>Vid export till andra länder än ovan.
					           				</li>
					           			</ul>
								</span>		
								</div>
			 					
				 			</td>
				 			<td>
				 				<select class="inputTextMediumBlueMandatoryField" name="sveh_dek1" id="sveh_dek1" >
				 				  <option value="">-Välj-</option>
								  <option value="EU"<c:if test="${model.record.sveh_dek1 == 'EU'}"> selected </c:if> >EU</option>
								  <option value="CO"<c:if test="${model.record.sveh_dek1 == 'CO'}"> selected </c:if> >CO</option>
								  <option value="EX"<c:if test="${model.record.sveh_dek1 == 'EX'}"> selected </c:if> >EX</option>
								</select>
			 				</td>
			 				<td class="text14">
			 					&nbsp;<img onMouseOver="showPop('48_info');" onMouseOut="hidePop('48_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>48.</b><font class="text16RedBold" >*</font><span title="sveh_upps">Uppskj. bet.&nbsp;</span>
				 				
				 				<div class="text11" style="position: relative;" align="left" >
			 					<span style="position:absolute;top:2px; width:250px;" id="48_info" class="popupWithInputText text11"  >
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
				 				<select class="inputTextMediumBlueMandatoryField" name="sveh_upps" id="sveh_upps" >
			 					  <option value="">-Välj-</option>
								  <option value="O"<c:if test="${model.record.sveh_upps == 'O'}"> selected </c:if> >O</option>
								  <option value="T"<c:if test="${model.record.sveh_upps == 'T'}"> selected </c:if> >T</option>
								  
						  		</select>
			 				</td>
			 				<td class="text14">&nbsp;&nbsp;
			 				<img onMouseOver="showPop('meddTyp_info');" onMouseOut="hidePop('meddTyp_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 				<font class="text16RedBold" >*</font><span title="sveh_mtyp">Medd.typ&nbsp;</span>
			 				<div class="text11" style="position: relative;" align="left" >
			 				<span style="position:absolute;top:2px; width:250px;" id="meddTyp_info" class="popupWithInputText text11"  >
			           			<ul>
				           			<li><b>UNU</b>&nbsp;Normalförfarande export</li>
				           			<li><b>URT</b>&nbsp;Normalförfarande export, rättelse av ej klarerad UNU</li>
				           			<li><b>UKO</b>&nbsp;Komplettering av UNU eller URT</li>
				           			<li><b>UGE</b>&nbsp;Lokalt klareringsförfarande export (godkänd exportör) </li>
				           			<li><b>UGO</b>&nbsp;Komplettering av en klarerad UGE.</li>
				           			<li><b>UFF</b>&nbsp;Kompletterande deklaration efter lokalt klareringsförfarande export med notering i bokföringen.</li>
				           			<li><b>UBK</b>&nbsp;Elektronisk begäran om klarering.</li>
				           		</ul>
							</span>
							</div>
			 				</td>
			 				
				 			<td>
				 				<select class="inputTextMediumBlueMandatoryField" name="sveh_mtyp" id="sveh_mtyp" >
				 				  <option selected value="">-Välj-</option>
								  <option value="UNU"<c:if test="${model.record.sveh_mtyp == 'UNU'}"> selected </c:if> >UNU</option>
								  <option value="UBK"<c:if test="${model.record.sveh_mtyp == 'UBK'}"> selected </c:if> >UBK</option>
								  <option value="URT"<c:if test="${model.record.sveh_mtyp == 'URT'}"> selected </c:if> >URT</option>
								  <option value="UKO"<c:if test="${model.record.sveh_mtyp == 'UKO'}"> selected </c:if> >UKO</option>
								  <option value="UGE"<c:if test="${model.record.sveh_mtyp == 'UGE'}"> selected </c:if> >UGE</option>
								  <option value="UGO"<c:if test="${model.record.sveh_mtyp == 'UGO'}"> selected </c:if> >UGO</option>
								  <option value="UFF"<c:if test="${model.record.sveh_mtyp == 'UFF'}"> selected </c:if> >UFF</option>
								</select>
				 			</td>
				 			
		 				</tr>
		 				<tr>
		 					<td class="text14">
		 						<img onMouseOver="showPop('1_2_info');" onMouseOut="hidePop('1_2_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>1.2</b><font class="text16RedBold" >*</font><span title="sveh_dek2">Dekl.typ&nbsp;</span>
				 				<div class="text11" style="position: relative;" align="left" >
				 				<span style="position:absolute;top:2px; width:250px;" id="1_2_info" class="popupWithInputText text11"  >
				 					<p>Här talar du om vilken typ av deklaration de handlar om.</p>
					           		<ul>
					           			<li><b>A</b>&nbsp;=&nbsp;Meddelande:DNU, DRT, DNK, UNU, URT</li>
					           			<li><b>Z</b>&nbsp;=&nbsp;Meddelande:TQN, TQR, UFF2, UGE</li>
					           		</ul>
								</span>
				 				</div>
				 			</td>
				 			<td>
				 				<select class="inputTextMediumBlueMandatoryField" name="sveh_dek2" id="sveh_dek2" >
				 					<option value="">-Välj-</option>
								  <option value="A"<c:if test="${model.record.sveh_dek2 == 'A'}"> selected </c:if> >A</option>
								  <option value="Z"<c:if test="${model.record.sveh_dek2 == 'Z'}"> selected </c:if> >Z</option>
								  
								</select>
			 				</td>
			 				
			 				<td class="text14">
			 					&nbsp;<img onMouseOver="showPop('24_info');" onMouseOut="hidePop('24_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>24.</b><font class="text16RedBold" >*</font><span title="sveh_tart">Trans.art&nbsp;</span>
				 				
				 				<div class="text11" style="position: relative;" align="left" >
				 				<span style="position:absolute;top:2px; width:250px;" id="24_info" class="popupWithInputText text11"  >
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
				 				<select class="inputTextMediumBlueMandatoryField" name="sveh_tart" id="sveh_tart" >
				 				  <option selected value="">-Välj-</option>
								  <option value="1"<c:if test="${model.record.sveh_tart == 1}"> selected </c:if> >1</option>
								  <option value="2"<c:if test="${model.record.sveh_tart == 2}"> selected </c:if> >2</option>
								  <option value="3"<c:if test="${model.record.sveh_tart == 3}"> selected </c:if> >3</option>
								  <option value="4"<c:if test="${model.record.sveh_tart == 4}"> selected </c:if> >4</option>
								  <option value="5"<c:if test="${model.record.sveh_tart == 5}"> selected </c:if> >5</option>
								  <option value="6"<c:if test="${model.record.sveh_tart == 6}"> selected </c:if> >6</option>
								  <option value="7"<c:if test="${model.record.sveh_tart == 7}"> selected </c:if> >7</option>
								  <option value="8"<c:if test="${model.record.sveh_tart == 8}"> selected </c:if> >8</option>
								  <option value="9"<c:if test="${model.record.sveh_tart == 9}"> selected </c:if> >9</option>
								</select>
			 				</td>
			 				
		 				</tr>
		 				<tr height="5"><td></td></tr>
		 				<tr>
				 			<td class="text14"><span title="h_xref">&nbsp;&nbsp;&nbsp;&nbsp;Ext.ref.&nbsp;</span></td>
				 			<td colspan="2">
				 				<input type="text" class="inputText" name="h_xref" id="h_xref" size="20" maxlength="35" value='${model.record.h_xref}'>
			 				</td>
			 				<td colspan="3" class="text14">
				 				<input id="updateProformaCheckbox" type="checkbox" name="sveh_prof" id="sveh_prof" value="1" <c:if test="${not empty model.record.sveh_prof}"> checked </c:if> ><span title="sveh_prof"><font class="text14MediumBlue">Proformaärende</font></span>
								<div id=updateProformaIcon style="display:inline;">
									<a tabindex=-1 id="updateProformaLink" runat="server" href="#">
										<img src="resources/images/update.gif" border="0" alt="edit">
									</a>
								</div>
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
                                      	${model.errorMessage}]  
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
				 				<div class="text11" style="position: relative;" align="left" >
				 				<span style="position:absolute;top:2px; width:250px;" id="2_info" class="popupWithInputText text11"  >
					           		Begreppet exportör används vid export då gemenskapsvaror förs ut från EU:s tullområde till tredjeland.
									Begreppet avsändare används vid: 
					           		<ul>
					           			<li>utförsel av gemenskapsvaror till länder och områden inom EU som valt att stå utanför skatteunionen, till exempel Åland, samt</li>
					           			<li>återexport, det vill säga då icke-gemenskapsvaror återutförs.</li>
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
							        	<input type="hidden" name="orig_sveh_avkn" id="orig_sveh_avkn" value='${model.record.sveh_avkn}'>
							        	<input type="hidden" name="orig_sveh_avna" id="orig_sveh_avna" value='${model.record.sveh_avna}'>
							        	<input type="hidden" name="orig_sveh_aveo" id="orig_sveh_aveo" value='${model.record.sveh_aveo}'>
							        	<input type="hidden" name="orig_sveh_ava1" id="orig_sveh_ava1" value='${model.record.sveh_ava1}'>
							        	<input type="hidden" name="orig_sveh_ava2" id="orig_sveh_ava2" value='${model.record.sveh_ava2}'>
							        	<input type="hidden" name="orig_sveh_avpn" id="orig_sveh_avpn" value='${model.record.sveh_avpn}'>
							        	<input type="hidden" name="orig_sveh_avpa" id="orig_sveh_avpa" value='${model.record.sveh_avpa}'>
							        	<input type="hidden" name="orig_sveh_avlk" id="orig_sveh_avlk" value='${model.record.sveh_avlk}'>
							        	<input type="hidden" name="orig_sveh_avha" id="orig_sveh_avha" value='${model.record.sveh_avha}'>
							        	<input type="hidden" name="orig_sveh_avtl" id="orig_sveh_avtl" value='${model.record.sveh_avtl}'>
							        	
							        	
							            <td class="text14" align="left" >&nbsp;&nbsp;<span title="sveh_avkn">Kundnummer</span></td>
							            <td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="sveh_avna">Namn&nbsp;</span>
							            	<a tabindex="-1" id="sveh_avnaIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>		
							            </td>
							        </tr>
							        <tr>
							            <td class="text14" align="left"><input type="text" class="inputTextMediumBlue" name="sveh_avkn" id="sveh_avkn" size="8" maxlength="8" value="${model.record.sveh_avkn}"></td>
							            <td class="text14" align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="sveh_avna" id="sveh_avna" size="30" maxlength="35" value="${model.record.sveh_avna}"></td>
							            
							        </tr>
							        
							        <tr>
							            <td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="sveh_aveo">EORI</span></td>
							            <td class="text14" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="sveh_aveo" id="sveh_aveo" size="20" maxlength="17" value="${model.record.sveh_aveo}"></td>
							            <td align="left">&nbsp;</td>
							        </tr>
							        <tr height="4"><td>&nbsp;</td></tr>
							        <tr>
							            <td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="sveh_ava1">Adress 1</span></td>
							            <td class="text14" align="left" >&nbsp;&nbsp;<span title="sveh_ava2">Adress 2</span></td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="sveh_ava1" id="sveh_ava1" size="30" maxlength="35" value="${model.record.sveh_ava1}"></td>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="sveh_ava2" id="sveh_ava2" size="30" maxlength="35" value="${model.record.sveh_ava2}"></td>
							        </tr>
							        <tr>
							        	<td>
							        		<table>
							        		<tr>
							            		<td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="sveh_avpa">Postadress</span></td>
							            		<td class="text14" >&nbsp;</td>
							            	</tr>
							        		<tr>
							            		<td align="left" colspan="2">
							            			<input type="text" class="inputTextMediumBlueMandatoryField" name="sveh_avpa" id="sveh_avpa" size="30" maxlength="35" value="${model.record.sveh_avpa}">
							            		</td> 
							            		<td class="text14" >&nbsp;</td>
							        		</tr>    	
							            	</table>
							            </td>
							            <td>
							            	<table>
							        		<tr>
							            		<td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="sveh_avpn">Postnummer</span></td>
							            		<td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="sveh_avlk">Land</span>
							            		<a tabindex="-1" id="sveh_avlkIdLink">
       												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
           										</a>
							            		</td>
							            		
							            	</tr>
							        		<tr>
							        			<td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="sveh_avpn" id="sveh_avpn" size="10" maxlength="9" value="${model.record.sveh_avpn}"></td> 
							            		<td align="left">
							            			<select class="inputTextMediumBlueMandatoryField" name="sveh_avlk" id="sveh_avlk">
									            		<option value="">-Välj-</option>
									 				  	<c:forEach var="country" items="${model.gcyCodeList}" >
					                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.sveh_avlk == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
														</c:forEach> 
													</select>
												</td>
							            		<td align="left">&nbsp;</td> 
							        		</tr>    	
							            	</table>
							            </td>
							        </tr>
							        
							        <tr>
							            <td align="left" class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="sveh_avha">Handläggare</span></td>
							            <td align="left"class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="sveh_avtl">Telefon</span></td>
							        </tr>
							        <tr>
							            <td align="left" ><input type="text" class="inputTextMediumBlueMandatoryField" name="sveh_avha" id="sveh_avha" size="25" maxlength="35" value="${model.record.sveh_avha}"></td>
							            <td align="left" ><input type="text" class="inputTextMediumBlueMandatoryField" name="sveh_avtl" id="sveh_avtl" size="20" maxlength="25" value="${model.record.sveh_avtl}"></td>
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
				 				<span style="position:absolute;top:2px; width:250px;" id="8_info" class="popupWithInputText text11"  >
				           			<ul>
					           			<li>Här anger du det fullständiga namnet och adressen på varumottagaren, oftast den som köpt varorna.
					           				Observera att även postnummer eller motsvarande ska anges.
					           			</li>
					           			<li>I de fall mottagaren har ett EORI-nummer kan du ange det.</li>
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
							        	<input type="hidden" name="orig_sveh_mokn" id="orig_sveh_mokn" value='${model.record.sveh_mokn}'>
							        	<input type="hidden" name="orig_sveh_mona" id="orig_sveh_mona" value='${model.record.sveh_mona}'>
							        	<input type="hidden" name="orig_sveh_moeo" id="orig_sveh_moeo" value='${model.record.sveh_moeo}'>
							        	<input type="hidden" name="orig_sveh_moa1" id="orig_sveh_moa1" value='${model.record.sveh_moa1}'>
							        	<input type="hidden" name="orig_sveh_moa2" id="orig_sveh_moa2" value='${model.record.sveh_moa2}'>
							        	<input type="hidden" name="orig_sveh_mopn" id="orig_sveh_mopn" value='${model.record.sveh_mopn}'>
							        	<input type="hidden" name="orig_sveh_mopa" id="orig_sveh_mopa" value='${model.record.sveh_mopa}'>
							        	<input type="hidden" name="orig_sveh_molk" id="orig_sveh_molk" value='${model.record.sveh_molk}'>
							        	
							            <td class="text14" align="left" >&nbsp;&nbsp;<span title="sveh_mokn">Kundnummer</span></td>
							            <td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="sveh_mona">Namn&nbsp;</span>
							            	<a tabindex="-1" id="sveh_monaIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>
										</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="sveh_mokn" id="sveh_mokn" size="8" maxlength="8" value="${model.record.sveh_mokn}"></td>
							            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="sveh_mona" id="sveh_mona" size="30" maxlength="35" value="${model.record.sveh_mona}"></td>
							        </tr>
							        <tr>
							            <td class="text14" align="left" >&nbsp;&nbsp;<span title="sveh_moeo">EORI</span></td>
							            <td class="text14" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="sveh_moeo" id="sveh_moeo" size="20" maxlength="17" value="${model.record.sveh_moeo}"></td>
							            <td align="left">&nbsp;</td>
							        </tr>
							        <tr height="4"><td>&nbsp;</td></tr>
							        <tr>
							            <td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="sveh_moa1">Adress 1</span></td>
							            <td class="text14" align="left" >&nbsp;&nbsp;<span title="sveh_moa2">Adress 2</span></td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="sveh_moa1" id="sveh_moa1" size="30" maxlength="35" value="${model.record.sveh_moa1}"></td>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="sveh_moa2" id="sveh_moa2" size="30" maxlength="35" value="${model.record.sveh_moa2}"></td>
							        </tr>
							        <tr>
							        	<td>
							        		<table>
							        		<tr>
							            		<td class="text14" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="sveh_mopa">Postadress</span></td>
											<td align="left">&nbsp;</td>
							            	</tr>
							        		<tr>
							            		<td align="left" colspan="2"><input type="text" class="inputTextMediumBlueMandatoryField" name="sveh_mopa" id="sveh_mopa" size="30" maxlength="35" value="${model.record.sveh_mopa}"></td> 
							            		
							        		</tr>    	
							            	</table>
							            </td>
							            <td>
							            	<table>
							        		<tr>
							        			<td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="sveh_mopn">Postnummer</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							            		<td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="sveh_molk">Land</span>
							            		<a tabindex="-1" id="sveh_molkIdLink">
       												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
           										</a>
							            		</td>
							            		
							            	</tr>
							        		<tr>
							        			<td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="sveh_mopn" id="sveh_mopn" size="10" maxlength="9" value="${model.record.sveh_mopn}"></td> 
							            		
							            		<td align="left">
							            			<select class="inputTextMediumBlueMandatoryField" name="sveh_molk" id="sveh_molk">
									            		<option value="">-Välj-</option>
									 				  	<c:forEach var="country" items="${model.gcyCodeList}" >
					                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.sveh_molk == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
														</c:forEach>  
													</select>
							            		</td> 
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
						 				<b>&nbsp;22.</b><span title="sveh_fabl">Fakt.total&nbsp;</span>
						 			</td>
						 			<td align="left" ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="sveh_fabl" id="sveh_fabl" size="20" maxlength="20" value="${model.record.sveh_fabl}"></td>
						 			<td class="text14">&nbsp;<span title="sveh_vakd">Valuta</span>
						 				<%-- Note: onChange event in jQuery for this currency list --%>
						 				<select class="inputTextMediumBlue" name="sveh_vakd" id="sveh_vakd">
							            		<option value="">-Välj-</option>
						 				  	<c:forEach var="currency" items="${model.mdxCodeList}" >
		                                	 	<option value="${currency.svkd_kd}"<c:if test="${model.record.sveh_vakd == currency.svkd_kd}"> selected </c:if> >${currency.svkd_kd}</option>
											</c:forEach> 
										</select>
										<a tabindex="-1" id="sveh_vakdIdLink">
											<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
										</a>									
					 				</td>
				 				</tr>
				 				<tr>
					 				<td class="text14">
						 				<b>&nbsp;23.</b>&nbsp;<span title="sveh_vaku">Kurs&nbsp;</span>
						 			</td>
						 			<td class="text14" align="left" ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="sveh_vaku" id="sveh_vaku" size="20" maxlength="20" value="${model.record.sveh_vaku}"></td>
						 			<td class="text14" align="left" ><span title="sveh_vaom">Faktor</span><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="sveh_vaom" id="sveh_vaom" size="10" maxlength="10" value='${model.record.sveh_vaom}'></td>
				 				</tr>
			 				</c:when>
			 				<c:otherwise> <%-- otherwise READ ONLY --%>
			 					<tr>
						 			<td class="text14">
						 				<b>&nbsp;22.</b>
						 				<font class="text16RedBold" >*</font><span title="sveh_fabl">Fakt.total&nbsp;</span>
						 			</td>
						 			<td align="left" ><input readonly type="text" class="inputTextReadOnly" name="sveh_fabl" id="sveh_fabl" size="20" maxlength="20" value="${model.record.invoiceListTotSum}"></td>
						 			<td class="text14"><font class="text16RedBold" >*</font><span title="sveh_vakd">Valuta</span>&nbsp;</td>
						 			<td class="text14">
						 				<input readonly type="text" class="inputTextReadOnly" name="sveh_vakd" id="sveh_vakd" size="4" maxlength="3" value="${model.record.invoiceListTotValidCurrency}">						 												
					 				</td>
				 				</tr>
				 				<tr>
					 				<td class="text14">
						 				<b>&nbsp;23.</b>&nbsp;<span title="sveh_vaku"><font class="text16RedBold" >*</font>Kurs&nbsp;</span>
						 			</td>
						 			<td class="text14" align="left" ><input readonly type="text" class="inputTextReadOnly" name="sveh_vaku" id="sveh_vaku" size="20" maxlength="20" value="${model.record.invoiceListTotKurs}"></td>
						 			<td class="text14" align="left" >&nbsp;<span title="sveh_vaom">Faktor</span>&nbsp;</td>
						 			<td class="text14">
						 				<input readonly type="text" class="inputTextReadOnly" name="sveh_vaom" id="sveh_vaom" size="10" maxlength="10" value='${model.record.sveh_vaom}'>
						 			</td>
						 			
				 				</tr>
			 				</c:otherwise>
		 				</c:choose>
		 				
		 				<tr height="10"><td></td></tr>
		 				<tr>
		 					<td class="text14Gray" align="left">
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
			 				<td class="text14Gray" align="right">
				 				<span title="sumOfItemAmounts">Fakt.total (varuposter)&nbsp;</span>
				 			</td>
				 			<td colspan="2" class="text14" align="left" >
				 				<input readonly style="text-align: left" type="text" class="inputTextReadOnly" name="sumOfInvoiceAmountInItemLines" id="sumOfInvoiceAmountInItemLines" size="20" maxlength="20" value="${fn:replace(model.record.sumOfInvoiceAmountInItemLinesStr, '.', ',')}">
				 				<c:if test="${not empty (sumOfInvoiceAmountInItemLinesStr && model.record.sveh_fabl)}">
			            			<c:if test="${model.record.sumOfInvoiceAmountInItemLines != model.record.sveh_fabl_dbl}">
						            	<img onMouseOver="showPop('itemsSumInvoiceAmount_info');" onMouseOut="hidePop('itemsSumInvoiceAmount_info');" width="18px" height="20px" src="resources/images/redFlag.png" border="0" alt="invoice amount warning">	
				            		</c:if>
				            	</c:if>
				            	<div class="text11" style="position: relative;" align="left">
				            		<span style="position:absolute; left:10px; top:2px; width:250px;" id="itemsSumInvoiceAmount_info" class="popupWithInputText text11"  >
				           			<p><b>Fakt.Totalbelopp (varuposter)</b></p>
				           			<p>
				           			Summan of Fakt.Totalbelopp på varupostnivå stämmer inte med det angivna Fakt.Totalbelopp i denna post.
				           			Vi rekommenderar att se över vad som ev. kan vara fel genom att kontrollera varuposterna.
				           			</p>
				           			
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
							            <td class="text14" align="left" >&nbsp;<span title="sveh_fatx">Fakt.nr.&nbsp;</span></td>
							            <td ><input type="text" class="inputTextMediumBlue" name="sveh_fatx" id="sveh_fatx" size="20" maxlength="50" value="${model.record.sveh_fatx}"></td>
							            <td class="text14" align="left" >&nbsp;&nbsp;&nbsp;
							            <img onMouseOver="showPop('faktTyp_info');" onMouseOut="hidePop('faktTyp_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				<span title="sveh_faty">Fakt.typ&nbsp;</span>
							            
							            <div class="text11" style="position: relative;" align="left" >
							            <span style="position:absolute;top:2px; width:250px;" id="faktTyp_info" class="popupWithInputText text11"  >
						           			<ul>
							           			<li><b>N380</b> handelsfaktura</li>
							           			<li><b>N325</b> proformafaktura</li>
							           			<li><b>N935</b> handelsfaktura - ersätter N380 enl. Tullv.</li>
							           		</ul>
										</span>
							            </div>
							            </td>
							            
							            <td>
							 				<select class="selectMediumBlueE2" name="sveh_faty" id="sveh_faty">
							 					<option value="">-Välj-</option><option value="N380"<c:if test="${model.record.sveh_faty == 'N380'}"> selected </c:if> >N380</option>
											  	<option value="N325"<c:if test="${model.record.sveh_faty == 'N325'}"> selected </c:if> >N325</option>
											  	<option value="N935"<c:if test="${model.record.sveh_faty == 'N935'}"> selected </c:if> >N935</option>
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
				 				    <c:when test="${model.record.sveh_syst == 'M' || empty model.record.sveh_syst}">
				 				    	<input tabindex=-1 class="inputFormSubmit" type="submit" name="submit" id="submit" onclick="javascript: form.action='tdsexport_edit.do';" value='<spring:message code="systema.tds.export.createnew.submit"/>'/>
				 				    	&nbsp;&nbsp;
				 				    	<c:if test="${not empty model.record.sveh_syop && model.record.validUpdate}">
				 				    		<input tabindex=-2 class="inputFormSubmit" type="submit" name="send" id="send" onclick="javascript: form.action='tdsexport_send.do';" value='<spring:message code="systema.tds.export.createnew.send"/>'/>
				 				    	</c:if>
				 				    	
				 				    	<%-- As a general Warning BEFORE save/send --%>
			 				    		<div id="submitRedFlag" style="display:none">
								            <img onMouseOver="showPop('itemsSum_infoOnSubmit');" onMouseOut="hidePop('itemsSum_infoOnSubmit');" width="18px" height="20px" src="resources/images/redFlag.png" border="0" alt="kolliantal warning">	
								            <div class="text11" style="position: relative;" align="left">
								            <span style="position:absolute; left:10px; top:2px; width:250px;" id="itemsSum_infoOnSubmit" class="popupWithInputText text11" >
							           			<p>	
							           				<b>Det finns "warnings" i sidan. Kontrollera bilden!</b>
							           			</p>
											</span>	
											</div>											
										</div>
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
			 					<b>15a.</b><font class="text16RedBold" >*</font><span title="sveh_avut">Avsändnings-/Exportland&nbsp;</span>
			 					
			 					<div class="text11" style="position: relative;" align="left" >
			 					<span style="position:absolute;top:2px; width:250px;" id="15_a_info" class="popupWithInputText text11"  >
				           			<ul>
				           				<li>Här anger du landkoden för avsändnings-/exportlandet. Koden består av två bokstäver, SE för Sverige. Avsändnings-/exportlandet kan aldrig vara EU.</li>
				           			</ul>
								</span>
								</div>		
					            </td>
					            <td >
					            		<select class="inputTextMediumBlueMandatoryField" name="sveh_avut" id="sveh_avut">
				 						<option value="">-Välj-</option>
					 				  	<c:forEach var="country" items="${model.gcyCodeList}" >
	                                	  	<option value='${country.svkd_kd}'<c:if test="${model.record.sveh_avut == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
										</c:forEach> 
										
									</select>
									<a tabindex="-1" id="sveh_avutIdLink">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>		
								</td>
							</tr>
							<tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('17_a_info');" onMouseOut="hidePop('17_a_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>17a.</b><font class="text16RedBold" >*</font><span title="sveh_aube">Bestämmelseland, kod&nbsp;</span>
					            <div class="text11" style="position: relative;" align="left" >
					            <span style="position:absolute;top:2px; width:250px;" id="17_a_info" class="popupWithInputText text11"  >
				           			<ul>
				           				<li>Här anger du landkoden för bestämmelselandet, till exempel NO för Norge.<br/><br/></li>
				           				<li>Bestämmelseland betyder det sista kända destinationslandet. 
				           				En omlastning i ett mellanliggande land förändrar inte bestämmelselandet. Bestämmelseland kan aldrig vara EU.</li>
				           			</ul>
								</span>
								</div>	
					            </td>
					            
					            <td >
					            	<select class="inputTextMediumBlueMandatoryField" name="sveh_aube" id="sveh_aube">
				 						
										<option value="">-Välj-</option>
					 				  	<c:forEach var="country" items="${model.gcyCodeList}" >
	                                	  	<option value="${country.svkd_kd}"<c:if test="${model.record.sveh_aube == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
										</c:forEach>
										 
									</select>
									<a tabindex="-1" id="sveh_aubeIdLink">
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
					            <b>26.</b>
					            <font class="text16RedBold" >*</font><span title="sveh_trin">Transportsätt inrikes</span>
					            <div class="text11" style="position: relative;" align="left" >
					            <span style="position:absolute;top:2px; width:250px;" id="26_info" class="popupWithInputText text11"  >
					           		
					           			<ul>
					           				<li>Här anger du koden för transportsätt för det transportmedel på vilket godset är lastat när du lämnar exportdeklarationen</li>
					           			</ul>
									
								</span>	
								</div>
								</td>
									
					            <td >
					            	<select class="inputTextMediumBlueMandatoryField" name="sveh_trin" id="sveh_trin">
				 						<option selected value="">-Välj-</option>
								  		<option value="1"<c:if test="${model.record.sveh_trin == 1}"> selected </c:if> >1.Sjötransport</option>
								  		<option value="2"<c:if test="${model.record.sveh_trin == 2}"> selected </c:if> >2.Järnvägstransport</option>
								  		<option value="3"<c:if test="${model.record.sveh_trin == 3}"> selected </c:if> >3.Vägtransport</option>
								  		<option value="4"<c:if test="${model.record.sveh_trin == 4}"> selected </c:if> >4.Flygtransport</option>
								  		<option value="5"<c:if test="${model.record.sveh_trin == 5}"> selected </c:if> >5.Postförsändelse</option>
								  		<option value="7"<c:if test="${model.record.sveh_trin == 7}"> selected </c:if> >7.Fasta transportinstallationer</option>
								  		<option value="8"<c:if test="${model.record.sveh_trin == 8}"> selected </c:if> >8.Transport på inre vattenvägar</option>
								  		<option value="9"<c:if test="${model.record.sveh_trin == 9}"> selected </c:if> >9.Egen framdrivning</option>
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
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('18_info');" onMouseOut="hidePop('18_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>18.</b>
					            <span title="sveh_trid / sveh_trlk">Transportmedlets identitet och nationalitet vid avgången/ankomsten</span>
					           
					            <div class="text11" style="position: relative;" align="left" >
					            <span style="position:absolute;top:2px; width:250px;" id="18_info" class="popupWithInputText text11"  >
				           			<ul>
					           			<li>Transportmedlets identitet ska inte anges om koden i fält 26 "Transportsätt inrikes" är "5" (Postförsändelser) eller "7" (Fasta transportinstallationer). 
					           			Vid återexport efter lagring i tullager, om förfarandekod i fält 37:1 är 3171, ska uppgiften om transportmedlets identitet inte anges.
					           			<br/><br/></li>
					           			<li>I övriga fall är transportmedlets identitet obligatoriskt för jordbruksvaror som du begär exportbidrag för (det vill säga om du angett någon av dessa koder i fält 37:2: E51, E52, E53, E61, E62, E63, E71, F62, F63) 
					           			och om samtidigt koden i fält 26 är:
					           			<br/><br/></li>
					           			
					           			<li>Du anger registreringsnummer eller namn på det transportmedel som godset kommer att lastas på. 
					           			Om dragbil med släpvagn används och dessa har olika registreringsnummer, ska du ange bådas registreringsnummer. 
					           			</li>
				           			</ul>
								</span>
								</div>
								</td>	
					        </tr>
					        <tr>
				            	<td >
				            		<input type="text" class="inputTextMediumBlue" name="sveh_trid" id="sveh_trid" size="30" maxlength="35" value="${model.record.sveh_trid}">
				            		&nbsp;
				            		<select class="selectMediumBlueE2" name="sveh_trlk" id="sveh_trlk">
				 						<option value="">-Välj-</option>
					 				  	<c:forEach var="country" items="${model.gcyCodeList}" >
					 				  		<option value="${country.svkd_kd}"<c:if test="${model.record.sveh_trlk == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
										</c:forEach> 
									</select>
									<a tabindex="-1" id="sveh_trlkIdLink">
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
			 				<tr height="5"><td class="text">&nbsp;</td> </tr>
			 				<tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('25_info');" onMouseOut="hidePop('25_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>25.</b>
					            <font class="text16RedBold" >*</font><span title="sveh_trgr">Transportsätt vid gränsen</span>
					            <div class="text11" style="position: relative;" align="left" >
					            <span style="position:absolute;top:2px; width:250px;" id="25_info" class="popupWithInputText text11"  >
				           			<ul>
				           				<li>Här anger du koden för transportsätt för det aktiva transportmedel som varorna transporteras med över gemenskapens yttre gräns.</li>
				           			</ul>
								</span>
								</div>
								</td>
										
					            <td >
					            	<select class="inputTextMediumBlueMandatoryField" name="sveh_trgr" id="sveh_trgr">
				 						<option value="">-Välj-</option>
								  		<option value="1"<c:if test="${model.record.sveh_trgr == 1}"> selected </c:if> >1.Sjötransport</option>
								  		<option value="2"<c:if test="${model.record.sveh_trgr == 2}"> selected </c:if> >2.Järnvägstransport</option>
								  		<option value="3"<c:if test="${model.record.sveh_trgr == 3}"> selected </c:if> >3.Vägtransport</option>
								  		<option value="4"<c:if test="${model.record.sveh_trgr == 4}"> selected </c:if> >4.Flygtransport</option>
								  		<option value="5"<c:if test="${model.record.sveh_trgr == 5}"> selected </c:if> >5.Postförsändelse</option>
								  		<option value="7"<c:if test="${model.record.sveh_trgr == 7}"> selected </c:if> >7.Fasta transportinstallationer</option>
								  		<option value="8"<c:if test="${model.record.sveh_trgr == 8}"> selected </c:if> >8.Transport på inre vattenvägar</option>
								  		<option value="9"<c:if test="${model.record.sveh_trgr == 9}"> selected </c:if> >9.Egen framdrivning</option>
								  		
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
					            <b>21.</b><font class="text14RedBold" >*</font>
					            <span title="sveh_trai / sveh_tral">Aktiva transportmedlets identitet och nationalitet vid gränspassagen</span>
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
								
					            <td class="text">&nbsp;</td>
					        </tr>
				            <tr>
				            	<td >
				            		<input type="text" class="inputTextMediumBlue" name="sveh_trai" id="sveh_trai" size="30" maxlength="35" value="${model.record.sveh_trai}">
				            		&nbsp;
				            		<select class="selectMediumBlueE2" name="sveh_tral" id="sveh_tral">
				 						<option value="">-Välj-</option>
					 				  	<c:forEach var="country" items="${model.gcyCodeList}" >
					 				  		<option value="${country.svkd_kd}"<c:if test="${model.record.sveh_tral == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
										</c:forEach>  
									</select>
									<a tabindex="-1" id="sveh_tralIdLink">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>											
									
				            	</td>
				            	<td class="text">&nbsp;</td>
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
					            <img onMouseOver="showPop('30_info');" onMouseOut="hidePop('30_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>30.</b><span title="sveh_golk">Godslokal kod</span>
					            <div class="text11" style="position: relative;" align="left" >
					            <span style="position:absolute;top:2px; width:250px;" id="30_info" class="popupWithInputText text11"  >
					           			<ul>
					           				<li>Koden består av 3 bokstäver och talar om på vilket lager varan finns.
					           				Du kan fråga innehavaren av lagret om du är osäker på godslokalkoden.
					           				</li>
					           			</ul>
									
								</span>	
								</div>
								</td>	
								<td >
									<input type="text" class="inputTextMediumBlue" name="sveh_golk" id="sveh_golk" size="4" maxlength="3" value="${model.record.sveh_golk}">
								</td>
													        
	        				</tr>
	        				<tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('godsnr_info');" onMouseOut="hidePop('godsnr_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <span title="sveh_godn">Godsnummer</span>
					            <div class="text11" style="position: relative;" align="left" >
					            <span style="position:absolute;top:2px; width:250px;" id="godsnr_info" class="popupWithInputText text11"  >
				           			<p>Denna fält är ingenting som hör till tullverkets deklaration.
				           			   Den är endast som hjälp för att ändra alla godsnummer på varupostnivå, om man vill uppdatera alla...	
				           			</p>
								</span>	
								</div>
								</td>	
								<td >
									<input type="text" class="inputTextMediumBlue" name="sveh_godn" id="sveh_godn" size="15" maxlength="14" value="${model.record.sveh_godn}">
								</td>
								<c:if test="${not empty model.record.sveh_syav && not empty model.record.sveh_syop}">
									<c:if test="${model.record.sveh_syst == 'M' || empty model.record.sveh_syst}">
										<td nowrap >&nbsp;
											<button name="godsnrButton" id="godsnrButton" class="buttonGrayWithGreenFrame" type="button" >Uppd. alla vp.</button>
										</td>
									</c:if>	
								</c:if>				       
	        				</tr>
	        				<tr height="5"><td class="text">&nbsp;</td> </tr>
			 				<tr>
					            <td class="text14" align="left">
					            <img onMouseOver="showPop('29_info');" onMouseOut="hidePop('29_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>29.</b><font class="text16RedBold" >*</font><span title="sveh_utfa">Utfartstullkontor&nbsp;</span>
					            
					            <div class="text11" style="position: relative;" align="left" >
					            <span style="position:absolute;top:2px; width:250px;" id="29_info" class="popupWithInputText text11"  >
				           			<ul>
				           				<li>Utfartstullkontor är det tullkontor där godset lämnar gemenskapens tullområde<br/><br/></li>
				           				<li>Du anger utfartstullkontoret med en kod. Koden består av åtta tecken. De två första anger landet med hjälp av landkod. De följande sex tecknen anger det berörda kontoret i detta land.<br/><br/>
				           					Exempel: Det svenska tullkontoret Malmö har koden SE000050 som är en NCTS-kod.
				           				</li>
				           				
				           			</ul>
								</span>	
								</div>
								</td>
					            
							    <td nowrap>
							    <input type="text" class="inputTextMediumBlueMandatoryField" name="sveh_utfa" id="sveh_utfa" size="9" maxlength="8" value="${model.record.sveh_utfa}">
							    <a tabindex="-1" id="sveh_utfaIdLink" >
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>
							    <%-- OBSOLETE
							    <img id="imgUtfartstullKontor" style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" onClick="showPop('searchTullkontorDialog10');">
					             --%>
					             
					             
							    <%-- ======================================================== --%>
				            		<%-- Here we have the search Tullkontor popup window --%>
				            		<%-- ======================================================== --%>
				            		<span style="position:absolute; left:550px; top:512px; width:300px; height:212px;" id="searchTullkontorDialog10" class="popupWithInputText"  >
					           		<div class="text10" align="left">
					           			<table>
					           				<tr>
												<td class="text11">&nbsp;Tullkontor</td>
												<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_utfa" id="search_sveh_utfa" size="18" maxlength="8" value=''></td>
											</tr>
						           			<tr>
												<td class="text11">&nbsp;Kod</td>
												<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_utfa_Code" id="search_sveh_utfa_Code" size="18" maxlength="35" value=''></td>
											</tr>
						           			<tr>
						           				<td class="text11">&nbsp;</td>
							           			<td align="right">&nbsp;<button  name="searchTullkontor10" id="searchTullkontor10" class="buttonGray" type="button" onClick="searchTullkontorOwnWindow()" >Sök</button></td>
							           		</tr>
							           		<tr height="4"><td ></td></tr>
							           		<tr>
						           				<td class="text11">&nbsp;Urval</td>
							           			<td>&nbsp;</td>
							           		</tr>
							           		<tr>
												<td colspan="2">&nbsp;
													<select class="selectMediumBlueE2"  id="tullkontorList" name="tullkontorList" size="3" onDblClick="hidePop('searchTullkontorDialog10');" >
	 													<option selected value="">-Välj-</option>
	 							 					</select>
												</td>
											</tr>
					           			</table>
										<table width="100%" align="left" border="0">
											<tr align="left" >
												<td >&nbsp;<button name="searchTullkontorClose10" id="searchTullkontorClose10" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchTullkontorDialog10');">Ok</button></td>
											</tr>
										</table>
									</div>
								</span>	
								</td>
					        </tr>
				            <tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('6_info');" onMouseOut="hidePop('6_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>6.&nbsp;</b><font class="text16RedBold" >*</font><span title="sveh_kota">Antal kollin (alltid > 0)</span>
					            
					            <div class="text11" style="position: relative;" align="left" >
					            <span style="position:absolute;top:2px; width:250px;" id="6_info" class="popupWithInputText text11"  >
				           			<ul>
				           				<li>Här anger du sändningens totala antal kolli. 
				           					Är godset oemballerat räknas varje vara som ett kolli. 
				           					Varje deklarerad bulkpost i fält 31 räknas som ett kolli i fält 6. 
				           					Uppgifterna om kollital i fält 6 och 31 jämförs med varandra. Det innebär att varje bulkpost på varupostnivå i fält 31 räknas som ett kolli vid summering av antal kolli i fält 6.
				           				</li>
				           			</ul>
									
								</span>	
								</div>
								</td>
					            <td ><input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlueMandatoryField" name="sveh_kota" id="sveh_kota" size="8" maxlength="7" value="${model.record.sveh_kota}"></td>
					        </tr>
					        <tr>
				        		<td class="text14Gray" align="center" >Antal kollin (varuposter)&nbsp;</td>
						        <td colspan="2">
				            		<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="sumOfAntalKolliInItemLines" id="sumOfAntalKolliInItemLines" size="8" maxlength="7" value="${model.record.sumOfAntalKolliInItemLinesStr}">
				            		<c:if test="${not empty (model.record.sumOfAntalKolliInItemLinesStr && model.record.sveh_kota)}">
					            		<c:if test="${model.record.sveh_kota != model.record.sumOfAntalKolliInItemLinesStr}">
							            <img id="imgRedFlagAntalKolliInItems" onMouseOver="showPop('itemsSum_info');" onMouseOut="hidePop('itemsSum_info');" width="18px" height="20px" src="resources/images/redFlag.png" border="0" alt="kolliantal warning">	
					            		</c:if>
				            		</c:if>
				            		<div class="text11" style="position: relative;" align="left">
				            		<span style="position:absolute; left:10px; top:0px; width:250px;" id="itemsSum_info" class="popupWithInputText text11"  >
				           			<p><b>Antal kollin (varuposter)</b></p>
				           			<p>
				           			Summan of antal kolli på varupostnivå stämmer inte med det angivna antal kolli i denna post.
				           			Vi rekommenderar att se över vad som ev. kan vara fel genom att kontrollera varuposterna.
				           			</p>
				           			Om talet här till vänster är = <b>-1</b> betyder det att det finns mer än 0-varuposter och att summan av varuposter kolliantal är = 0 (vilket inte är korrekt).
				           			</span>		
									</div>					            		
					            </td>
					        </tr>
					        <tr height="2"><td class="text">&nbsp;</td> </tr>
					        
				            <tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('35_info');" onMouseOut="hidePop('35_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>35.</b><font class="text16RedBold" >*</font><span title="sveh_brut">Bruttovikt</span>
					            <div class="text11" style="position: relative;" align="left">
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
					            <td ><input onKeyPress="return amountKey(event)" style="text-align: right" type="text" class="inputTextMediumBlueMandatoryField" name="sveh_brut" id="sveh_brut" size="13" maxlength="13" value="${model.record.sveh_brut}"></td>
					        </tr>
					        <tr>
				        		<td class="text14Gray" align="center" >
				        			Bruttovikt (varuposter)&nbsp;
				        		</td>
						        <td colspan="2">
				            		<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="sumOfGrossWeightInItemLinesStr" id="sumOfGrossWeightInItemLinesStr" size="13" maxlength="13" value="${model.record.sumOfGrossWeightInItemLinesStr}">
				            		<c:if test="${not empty (model.record.sumOfGrossWeightInItemLinesStr && model.record.sveh_brut)}">
				            			<c:if test="${model.record.sumOfGrossWeightInItemLines != model.record.sveh_brut_dbl}">
							            	<img id="imgRedFlagSumGrossWeightInItems" onMouseOver="showPop('itemsSumGrossweight_info');" onMouseOut="hidePop('itemsSumGrossweight_info');" width="18px" height="20px" src="resources/images/redFlag.png" border="0" alt="kolliantal warning">	
					            		</c:if>
					            	</c:if>
				            		<div class="text11" style="position: relative;" align="left">
					            		<span style="position:absolute; left:10px; top:0px; width:250px;" id="itemsSumGrossweight_info" class="popupWithInputText text11"  >
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
					        <tr height="2"><td class="text">&nbsp;</td> </tr>
					        <tr height="5"><td>&nbsp;</td></tr>
							<tr>
					 			<td class="text14">
					 				<table align="left" border="0" cellspacing="0" cellpadding="0">
						 				<tr>
							 				<td class="text14">
							 				<img onMouseOver="showPop('19_info');" onMouseOut="hidePop('19_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<b>19.</b>&nbsp;<font class="text16RedBold" >*</font>Container&nbsp;&nbsp;
							 				<div class="text11" style="position: relative;" align="left">
							 				<span style="position:absolute;top:2px; width:250px;" id="19_info" class="popupWithInputText text11"  >
							           			Ange 1 om godset är lastat i container vid passagen av den yttre gränsen. I annat fall anges 0. Om du deklarerat 1 i detta fält måste minst ett containernummer finnas i fält 31
							           			<br/>
						           			</span>
							 				</div>	
							 				</td>
						 				</tr>
					 				</table>
					 			</td>
					 			<td width="2">&nbsp;</td>
				 			</tr>
				 			
					 		<tr>
					 			<td colspan="2" class="text14">
					 				<table align="left" border="0" cellspacing="0" cellpadding="0">
					 				<tr>
					 					<td class="text14">&nbsp;&nbsp;<span title="sveh_cont">Kod&nbsp;</span></td>
					 					<td class="text14">&nbsp;&nbsp;<span title="sveh_last">Lastplatskod&nbsp;</span></td>
				 					</tr>
				 					<tr>
					 					<%--
					 					<td class="text14">&nbsp;Id&nbsp;
					 						<input type="text" class="inputTextMediumBlue" name="sveh_conn" id="sveh_conn" size="20" maxlength="35" value='${model.record.sveh_conn}'>
				 						</td>
				 						 --%>
				 						<td class="text14" >
				 							<select class="inputTextMediumBlueMandatoryField" name="sveh_cont" id="sveh_cont">
						 						<option value="0"<c:if test="${model.record.sveh_cont == 0}"> selected </c:if> >0</option>
										  		<option value="1"<c:if test="${model.record.sveh_cont == 1}"> selected </c:if> >1</option>
										  	</select>
				 						</td>
					 					<td class="text14">&nbsp;&nbsp;
					 						<input type="text" class="inputTextMediumBlue" name="sveh_last" id="sveh_last" size="20" maxlength="25" value="${model.record.sveh_last}">
				 						</td>
			 						</tr>
			 						</table>
					 			</td>
					 			<td width="2">&nbsp;</td>
			 				</tr>
				 			<tr height="20"><td>&nbsp;</td></tr>
					     	
					     	
					     	<tr>
								<td colspan="3" >
									<%-- Special section --%>
									<table align="left" class="formFrameHeader" width="100%" border="0" cellspacing="0" cellpadding="0">
								 		<tr height="15">
								 			<td class="text14White">
								 				<b>&nbsp;12.</b>&nbsp;Fraktkostnader&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
							 				</td>
						 				</tr>
					 				</table>
					 			</td>
				 			</tr>
					     	<tr>
				        		<td colspan="3" class="text14">
					 				<table align="left" class="formFrame" width="100%" border="0" cellspacing="0" cellpadding="0">
					 				<tr height="5"><td></td></tr>
					 				<tr>
					 					<td class="text14">&nbsp;<span title="sveh_vufr">Fraktkostnad&nbsp;</span></td>
					 					<td><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="sveh_vufr" id="sveh_vufr" size="15" maxlength="14" value="${model.record.sveh_vufr}"></td>
					 					<td class="text14">&nbsp;&nbsp;<span title="sveh_vuva">Fraktvaluta</span>
							 				<%-- Note: onChange event in jQuery for this currency list --%>
							 				<select class="inputTextMediumBlue" name="sveh_vuva" id="sveh_vuva">
								            		<option value="">-Välj-</option>
							 				  	<c:forEach var="currency" items="${model.mdxCodeList}" >
			                                	 	<option value="${currency.svkd_kd}"<c:if test="${model.record.sveh_vuva == currency.svkd_kd}"> selected </c:if> >${currency.svkd_kd}</option>
												</c:forEach> 
											</select>
											<a tabindex="-1" id="sveh_vuvaIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>									
						 				</td>
						 				
				 					</tr>
				 					<tr>
					 					<td class="text14">&nbsp;<span title="sveh_vuku">Fraktkurs&nbsp;</span></td>
					 					<td><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="sveh_vuku" id="sveh_vuku" size="10" maxlength="8" value="${model.record.sveh_vuku}"></td>
				 					</tr>
				 					<tr height="5"><td></td></tr>
				 					
			 						</table>
					 			</td>
					        </tr>
					        
					     	<tr height="10"><td>&nbsp;</td></tr>
					     
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="3">
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
				 				    <c:when test="${model.record.sveh_syst == 'M' || empty model.record.sveh_syst}">
				 				    	<input class="inputFormSubmit" type="submit" name="submit2" id="submit2" onclick="javascript: form.action='tdsexport_edit.do';" value='<spring:message code="systema.tds.export.createnew.submit"/>'/>
				 				    	&nbsp;&nbsp;
				 				    	<c:if test="${not empty model.record.sveh_syop && model.record.validUpdate}">
				 				    		<input class="inputFormSubmit" type="submit" name="send2" id="send2" onclick="javascript: form.action='tdsexport_send.do';" value='<spring:message code="systema.tds.export.createnew.send"/>'/>
				 				    		
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
		
		<tr>
		    <td colspan="2" width="100%">		
			<%-- ------------------------------- --%>
			<%-- tab area container DIVISON AREA --%>
			<%-- ------------------------------- --%>
			<table border="0" cellspacing="0" cellpadding="0">
				<tr >
					<td height="10" class="tabNoBorderSeparatorWhite" align="left" > 
			   			 <table border="0" cellspacing="0" cellpadding="0">
						 	<tr >
						 		<%--
						 		<td class="divisionLine">
					    			&nbsp;
					    		</td>
					    		 --%>
					        </tr>
					     </table> 
					</td>
				</tr>
			</table>
			</td>
	 	</tr>
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
				<table style="width:90%" border="0" cellspacing="0" cellpadding="0">
					<tr>
			 			<td width="5">&nbsp;</td>
			            <td >		
			 				<%-- DEKLARANT --%>
			 				<table width="100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="18px">
						 			<td class="text14White">
						 				&nbsp;<img onMouseOver="showPop('14_info');" onMouseOut="hidePop('14_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>&nbsp;14.</b>Deklarant&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						 				&nbsp;&nbsp;<font style="font-style: italic;">[om annat än Avsändare]</font>
				 						
				 						<div class="text11" style="position: relative;" align="left">
						 				<span style="position:absolute;top:2px; width:250px;" id="14_info" class="popupWithInputText text11"  >
							           		
						           			<br>
						           			Här ska du alltid ange uppgifter för deklarant samt ombudsuppgifter när du agerar ombud. 
						           			<br/><br/>
						           			Om sektionen inte fylls i <b>kopieras</b> alla avsändarfälten till deklarantfälten.
						           			<ul>
							           			<li>Deklarant är det företag/den person som lämnar in en tulldeklaration i eget namn eller det företag/den person i vars namn en tulldeklaration lämnas in (tullkodex artikel 4.18). 
							           			Det är bara företag eller personer som är etablerade i gemenskapen eller i Norge som kan vara deklarant vid export samt vid utförsel till länder och 
							           			områden inom gemenskapens tullområde men som inte tillhör skatteområdet. I dessa fall krävs EORI-nummer.
							           			<br></li>
							           			<li>Vid vissa fall för att avsluta ett tullförfarande med återexport behöver deklaranten inte ha EORI-nummer eller vara etablerad i gemenskapen, se mer under avsnittet "Återexport"</li>
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
									        	<input type="hidden" name="orig_sveh_dkkn" id="orig_sveh_dkkn" value='${model.record.sveh_dkkn}'>
									        	<input type="hidden" name="orig_sveh_dkna" id="orig_sveh_dkna" value='${model.record.sveh_dkna}'>
									        	<input type="hidden" name="orig_sveh_dkeo" id="orig_sveh_dkeo" value='${model.record.sveh_dkeo}'>
									        	<input type="hidden" name="orig_sveh_dka1" id="orig_sveh_dka1" value='${model.record.sveh_dka1}'>
									        	<input type="hidden" name="orig_sveh_dka2" id="orig_sveh_dka2" value='${model.record.sveh_dka2}'>
									        	<input type="hidden" name="orig_sveh_dkpn" id="orig_sveh_dkpn" value='${model.record.sveh_dkpn}'>
									        	<input type="hidden" name="orig_sveh_dkpa" id="orig_sveh_dkpa" value='${model.record.sveh_dkpa}'>
									        	<input type="hidden" name="orig_sveh_dklk" id="orig_sveh_dklk" value='${model.record.sveh_dklk}'>
							        	
									            <td class="text14" align="left" >&nbsp;&nbsp;<span title="sveh_dkkn">Kundnummer</span></td>
									            <td class="text14" align="left" >&nbsp;&nbsp;<span title="sveh_dkna">Namn</span>
									            	<a tabindex="-1" id="sveh_dknaIdLink">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
													</a>
									            </td>
									        </tr>
									        <tr>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="sveh_dkkn" id="sveh_dkkn" size="8" maxlength="8" value="${model.record.sveh_dkkn}"></td>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="sveh_dkna" id="sveh_dkna" size="30" maxlength="35" value="${model.record.sveh_dkna}"></td>
									        </tr>
									        <tr>
									            <td class="text14" align="left" >&nbsp;&nbsp;<span title="sveh_dkeo">EORI</span></td>
									            <td class="text14" align="left" >&nbsp;&nbsp;</td>
									        </tr>
									        <tr>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="sveh_dkeo" id="sveh_dkeo" size="17" maxlength="17" value="${model.record.sveh_dkeo}"></td>
									            <td align="left">&nbsp;</td>
									        </tr>
									        <tr height="4"><td>&nbsp;</td></tr>
									        <tr>
									            <td class="text14" align="left" >&nbsp;&nbsp;<span title="sveh_dka1">Adress 1</span></td>
									            <td class="text14" align="left" >&nbsp;&nbsp;<span title="sveh_dka2">Adress 2</span></td>
									        </tr>
									        <tr>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="sveh_dka1" id="sveh_dka1" size="30" maxlength="35" value="${model.record.sveh_dka1}"></td>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="sveh_dka2" id="sveh_dka2" size="30" maxlength="35" value="${model.record.sveh_dka2}"></td>
									        </tr>
									        <tr>
									        		<td>
										        		<table>
										        		<tr>
										            		<td class="text14" align="left" >&nbsp;<span title="sveh_dkpa">Postadress</span></td>
										            		<td align="left">&nbsp;</td>
										            	</tr>
										        		<tr>
										            		<td align="left">
										       				<input type="text" class="inputTextMediumBlue" name="sveh_dkpa" id="sveh_dkpa" size="30" maxlength="35" value="${model.record.sveh_dkpa}">
									            			</td> 
										            		<td align="left">&nbsp;</td>
										        		</tr>    	
										            	</table>
									            </td>
									            <td >
										            	<table>
										        		<tr>
										        			<td class="text14" align="left" >&nbsp;&nbsp;<span title="sveh_dkpn">Postnummer</span></td>
										            		<td class="text14" align="left" >&nbsp;&nbsp;<span title="sveh_dklk">Land</span>
								            				<a tabindex="-1" id="sveh_dklkIdLink">
       															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
           													</a>
										            		</td>
										            	</tr>
										        		<tr >
										        			<td align="left"><input type="text" class="inputTextMediumBlue" name="sveh_dkpn" id="sveh_dkpn" size="10" maxlength="9" value="${model.record.sveh_dkpn}"></td> 
										            		<td align="left">
										            			<select class="selectMediumBlueE2" name="sveh_dklk" id="sveh_dklk">
												            		<option value="">-Välj-</option>
											 				  	<c:forEach var="country" items="${model.gcyCodeList}" >
					 				  							<option value="${country.svkd_kd}"<c:if test="${model.record.sveh_dklk == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
																</c:forEach>  
															</select>
										            		</td> 
										        		</tr>  
										            	</table>
									            </td>
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
				 						<div class="text11" style="position: relative;" align="left">
						 				<span style="position:absolute;top:2px; width:250px;" id="14_b_info" class="popupWithInputText text11"  >
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
									            <td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="sveh_omeo">Ombud EORI</span></td>
									            <td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="sveh_omty">Typ av ombudskap</span></td>
									        </tr>
									        <tr>
									            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="sveh_omeo" id="sveh_omeo" size="16" maxlength="17" value="${model.record.sveh_omeo}"></td>
									            <td align="left">
									            	<select class="inputTextMediumBlueMandatoryField" name="sveh_omty" id="sveh_omty">
					 									<option value="">-Välj-</option>
									  					<option value="2"
										  				<c:if test="${model.record.sveh_omty == '2'}"> selected </c:if> >2-Direkt ombud</option>
										  				<option value="3"
										  				<c:if test="${model.record.sveh_omty == '3'}"> selected </c:if> >3-Indirekt ombud</option>
													</select>
												</td>
									        </tr>
									        
									        <tr>
									            <td align="left" class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="sveh_omha">Handläggare</span></td>
									            <td align="left"class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="sveh_omtl">Telefon</span></td>
									            <td>&nbsp;</td>
									        </tr>
									        <tr>
									            <td align="left" ><input type="text" class="inputTextMediumBlueMandatoryField" name="sveh_omha" id="sveh_omha" size="25" maxlength="35" value="${model.record.sveh_omha}"></td>
									            <td align="left" ><input type="text" class="inputTextMediumBlueMandatoryField" name="sveh_omtl" id="sveh_omtl" size="20" maxlength="35" value="${model.record.sveh_omtl}"></td>
									            <td>&nbsp;</td>
									        </tr>
								        </table>
							      	</td>
								 </tr>
								 <tr height="10"><td></td></tr>
						 	</table>
		            		</td>
		           	</tr>
		           	
		           	<tr>
			 			<td width="5">&nbsp;</td>
			            <td >	
							<%-- create record --%>
						 	<table width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
							<%-- EXTRA INFORMATION fields --%>
								<tr height="15"><td></td></tr>
								<tr>
								 	<td>  
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
									 		<tr>
									            <td class="text14" align="left" >&nbsp;&nbsp;<span title="sveh_res1/res2, etc">Resplan - i ruttordning</span></td>
									            
									        </tr>
									        <tr>
									        	<td>
										        	<table width="80%" border="0" cellspacing="0" cellpadding="0">
											        	<tr>
												            <td class="text14" align="left">&nbsp;&nbsp;Land 1&nbsp;
													            
												            </td>
												            <td class="text14" align="left">&nbsp;&nbsp;Land 2&nbsp;
													            	
												            </td>
												            <td class="text14" align="left">&nbsp;&nbsp;Land 3&nbsp;
													            
												            </td>
												            <td class="text14" align="left">&nbsp;&nbsp;Land 4&nbsp;
													            	
												            </td>
												        </tr>
												        <tr>
												        	<td>    
												            	<select class="selectMediumBlueE2" name="sveh_res1" id="sveh_res1">
												            		<option value="">-Välj-</option>
												 				  	<c:forEach var="country" items="${model.gcyCodeList}" >
					 				  								<option value="${country.svkd_kd}"<c:if test="${model.record.sveh_res1 == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
																	</c:forEach> 
																</select>
															</td>
															<td class="text11" align="left">
												            	<select class="selectMediumBlueE2" name="sveh_res2" id="sveh_res2">
											 						<option value="">-Välj-</option>										            	
												 				  	<c:forEach var="country" items="${model.gcyCodeList}" >
					 				  								<option value="${country.svkd_kd}"<c:if test="${model.record.sveh_res2 == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
																	</c:forEach> 
																</select>
															</td>
															<td class="text11" align="left">
										 						<select class="selectMediumBlueE2" name="sveh_res3" id="sveh_res3">
											 						<option value="">-Välj-</option>
												 				  	<c:forEach var="country" items="${model.gcyCodeList}" >
					 				  								<option value="${country.svkd_kd}"<c:if test="${model.record.sveh_res3 == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
																	</c:forEach>
																</select>
															</td>
															<td class="text11" align="left">													
												            	<select class="selectMediumBlueE2" name="sveh_res4" id="sveh_res4">
											 						<option value="">-Välj-</option>										            	
												 				  	<c:forEach var="country" items="${model.gcyCodeList}" >
					 				  								<option value="${country.svkd_kd}"<c:if test="${model.record.sveh_res4 == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
																	</c:forEach> 
																</select>
															</td>
															
														</tr>
											        		<tr>
												            <td class="text11" align="left">&nbsp;&nbsp;Land 5&nbsp;
													            	
												            </td>
												            <td class="text11" align="left">&nbsp;&nbsp;Land 6&nbsp;
													            
												            </td>
												            <td class="text11" align="left">&nbsp;&nbsp;Land 7&nbsp;
													            	
												            </td>
												            <td class="text11" align="left">&nbsp;&nbsp;Land 8&nbsp;
													            	
												            </td>
												        </tr>
												        <tr>
												        	<td>    
												            	<select class="selectMediumBlueE2" name="sveh_res5" id="sveh_res5">
											 						<option value="">-Välj-</option>										            	
												 				  	<c:forEach var="country" items="${model.gcyCodeList}" >
					 				  								<option value="${country.svkd_kd}"<c:if test="${model.record.sveh_res5 == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
																	</c:forEach> 
																</select>
															</td>
															<td class="text11" align="left">
												            	<select class="selectMediumBlueE2" name="sveh_res6" id="sveh_res6">
											 						<option value="">-Välj-</option>
												 				  	<c:forEach var="country" items="${model.gcyCodeList}" >
					 				  								<option value="${country.svkd_kd}"<c:if test="${model.record.sveh_res6 == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
																	</c:forEach>
																</select>
															</td>
															<td class="text11" align="left">
												            	<select class="selectMediumBlueE2" name="sveh_res7" id="sveh_res7">
											 						<option value="">-Välj-</option>
												 				  	<c:forEach var="country" items="${model.gcyCodeList}" >
					 				  								<option value="${country.svkd_kd}"<c:if test="${model.record.sveh_res7 == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
																	</c:forEach> 
																</select>
															</td>
															<td class="text11" align="left">
												            	<select class="selectMediumBlueE2" name="sveh_res8" id="sveh_res8">
											 						<option value="">-Välj-</option>
												 				  	<c:forEach var="country" items="${model.gcyCodeList}" >
					 				  								<option value="${country.svkd_kd}"<c:if test="${model.record.sveh_res8 == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
																	</c:forEach>
																</select>
															</td>
														</tr>
													</table>
												</td>
									        </tr>
									        
								        </table>
								        
							        </td>
						        </tr>
								<tr height="20"><td></td></tr>
								
								<tr>
						 			<td colspan="2">		
						 				<%-- Item header --%>
						 				<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
									 		<tr height="15">
									 			<td class="text14">
									 				&nbsp;&nbsp;<b>Ytterligare säkerhetsuppgifter&nbsp;</b>
									 				<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
									 				
								 				</td>
							 				</tr>
						 				</table>
					 				</td>
								</tr>
								<tr height="8"><td></td></tr>
					            <tr>
						            <td >
						                <table align="left" border="0" cellspacing="1" cellpadding="0">
						                	<tr>
									            <td class="text14" align="left">
									            <img onMouseOver="showPop('S02_info');" onMouseOut="hidePop('S02_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 								<b>S02.</b>&nbsp;<span title="sveh_komr">Kommersiellt referensnr.&nbsp;</span>
				 								
				 								<div class="text11" style="position: relative;" align="left">
				 								<span style="position:absolute;top:2px; width:250px;" id="S02_info" class="popupWithInputText text11"  >
									           		Här anger du ditt egna referensnummer för sändningen (UCR) - ett unikt nummer som tilldelas varor i samband med export.
													Ett unikt referensnummer är ett nummer som gör det möjligt att spåra en sändning eller en artikel i ditt affärssystem. Det kan till exempel vara ett ordernummer, sändningsnummer eller kollinummer. Referensnumret är knutet till ett antal uppgifter om sändningen/artikeln i ditt affärssystem.
													Världstullorganisationens (WCO) standard (ISO 15459) eller motsvarande kan användas.
													Om samma kommersiella referensnummer gäller för alla varuposter eller om exportdeklarationen bara omfattar en varupost, anger du uppgiften på huvudnivå. I annat fall anger du uppgiften på varupostnivå. Kommersiellt referensnummer behöver inte anges i följande fall
								           			<br/>
								           			<ul>
									           			<li>Om du angett koden "A" (Post- och expressförsändelser) i fältet S32, "Särskild omständighet, kod", behöver du inte ange denna uppgift.</li>
									           			<li>Kommersiellt referensnummer kan också utelämnas vid utförsel till ett område inom gemenskapens tullområde som inte tillhör skatteområdet, exempelvis Åland.</li>
									           			<li>Om du angett ett transportdokument i fält 44, "Bilagda handlingar", på minst en varupost, behöver du inte lämna kommersiellt referensnummer.</li>
									           		</ul>
												</span>
												</div>
												</td>
												
									           	<td class="text14" align="left"><input type="text" class="inputTextMediumBlue" name="sveh_komr" id="sveh_komr" size="25" maxlength="70" value="${model.record.sveh_komr}"></td>
								           	</tr>
						                	<tr>
									 			<td class="text14" align="left" >
									 			<img onMouseOver="showPop('S28_info');" onMouseOut="hidePop('S28_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 								<b>S28.</b>&nbsp;<span title="sveh_sel1 / sel2">Förseglingsid&nbsp;</span>
				 								<div class="text11" style="position: relative;" align="left">
				 								<span style="position:absolute;top:2px; width:250px;" id="S28_info" class="popupWithInputText text11"  >
									           		
								           			Antal förseglingar
								           			<br/>
								           			<ul>
									           			<li>Uppgiften lämnas för närvarande inte i Sverige i UNU.</li>
									           			<li>I dagsläget finns inga tillstånd till förenklat förfarande på export i Sverige där det är föreskrivet att en sändning ska förseglas, och därmed kan uppgiften utelämnas.</li>
									           			<li>Förseglingsidentitet<br/>
														Uppgiften lämnas för närvarande inte i Sverige i UNU.</li>
									           		</ul>
												</span>
												</div>
												</td>
									 			<td class="text14" align="left" >&nbsp;&nbsp;Id(1)&nbsp;<input type="text" class="inputTextMediumBlue" name="sveh_sel1" id="sveh_sel1" size="20" maxlength="70" value="${model.record.sveh_sel1}"></td>
									 		</tr>
									 		<tr>
									 			<td class="text14">&nbsp;</td>
									 			<td class="text14" align="left" >&nbsp;&nbsp;Id(2)&nbsp;<input type="text" class="inputTextMediumBlue" name="sveh_sel2" id="sveh_sel2" size="20" maxlength="70" value="${model.record.sveh_sel2}"></td>
									 			
							 				</tr>
									 		<tr>
								 				<td class="text14">
								 				<img onMouseOver="showPop('S29_info');" onMouseOut="hidePop('S29_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 								<b>S29.&nbsp;</b><span title="sveh_betk">Betalningssätt&nbsp;</span>

								 				<div class="text11" style="position: relative;" align="left">
								 				<span style="position:absolute;top:2px; width:250px;" id="S29_info" class="popupWithInputText text11"  >
								           			Här anger du den kod som motsvarar hur du betalat transportkostnaderna. Uppgiften ska lämnas så långt den är känd, antingen på huvudnivå eller på varupostnivå. Om exportdeklarationen endast innehåller en varupost ska uppgiften anges på huvudnivå.
								           			<br/>
								           			<ul>
									           			<li><b>A</b> Kontantbetalning</li>
									           			<li><b>B</b> Betalning med kreditkort</li>
									           			<li><b>C</b> Betalning med check</li>
									           			<li><b>D</b> Övrigt (om ingen av de andra koderna är tillämplig, kan t.ex. vara direktdebitering av konto dvs. kunden har ett konto hos fraktföraren där pengar är inbetalade i förväg och varifrån det dras pengar varje gång frakten utförs)</li>
									           			<li><b>H</b> Elektronisk betalning (t.ex. betalning över internet till bankgiro/plusgiro eller betalning med kort över internet)</li>
									           			<li><b>Y</b> Konto hos fraktföraren (dvs. kunden har ett kreditkonto hos fraktföraren där frakten debiteras och som sedan faktureras kunden t.ex. en gång i månaden)</li>
									           			<li><b>Z</b> Inte förhandsbetalat (dvs. frakten skickas på mottagarens bekostnad och kunden/mottagaren löser frakten innan frakthandlingar för godset kan disponeras. Detta förekommer t.ex. vid flygfrakt)</li>
									           		</ul>
												</span>
												</div>
												</td>
												
								 				<td>
									 				<select class="selectMediumBlueE2" name="sveh_betk" id="sveh_betk" >
									 				  <option value="">-Välj-</option>
													  <option value="A"<c:if test="${model.record.sveh_betk == 'A'}"> selected </c:if> >A</option>
													  <option value="B"<c:if test="${model.record.sveh_betk == 'B'}"> selected </c:if> >B</option>
													  <option value="C"<c:if test="${model.record.sveh_betk == 'C'}"> selected </c:if> >C</option>
													  <option value="D"<c:if test="${model.record.sveh_betk == 'D'}"> selected </c:if> >D</option>
													  <option value="H"<c:if test="${model.record.sveh_betk == 'H'}"> selected </c:if> >H</option>
													  <option value="Y"<c:if test="${model.record.sveh_betk == 'Y'}"> selected </c:if> >Y</option>
													  <option value="Z"<c:if test="${model.record.sveh_betk == 'Z'}"> selected </c:if> >Z</option>
													</select>
								 				</td>
							 				</tr>
							 				<tr>
								 				<td class="text14">
								 				<img onMouseOver="showPop('S32_info');" onMouseOut="hidePop('S32_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 								<b>S32.&nbsp;</b><span title="sveh_saom">Särsk.omständ&nbsp;</span>
								 				<div class="text11" style="position: relative;" align="left">
								 				<span style="position:absolute;top:2px; width:250px;" id="S32_info" class="popupWithInputText text11"  >
								           			I de fall du deklarerar "A", "B" eller "E" enligt kodtabellen nedan, har du möjlighet att lämna färre uppgifter i deklarationen.
								           			<br/>
								           			<ul>
									           			<li><b>A</b> Post- och expressförsändelser</li>
									           			<li><b>B</b> Proviant och delar till fartyg och luftfartyg</li>
									           			<li><b>E</b> Godkända ekonomiska aktörer</li>
									           		</ul>
									           		Koden "E" (Godkända ekonomiska aktörer) kan bara anges om deklarant, exportör/avsändare samt eventuellt ombud har giltiga AEO-certifikat av typen AEOS (AEO-certifikat Säkerhet och skydd) eller AEOF (AEO-certifikat Tullförenklingar/Säkerhet och skydd). 
									           		<br/>
									           		Uppgiften kan också utelämnas vid utförsel till ett område inom gemenskapens tullområde som inte tillhör skatteområdet, exempelvis Åland. 
												</span>
												</div>
												</td>
												
								 				<td>
									 				<select class="selectMediumBlueE2" name="sveh_saom" id="sveh_saom" >
									 				  <option value="">-Välj-</option>
													  <option value="A"<c:if test="${model.record.sveh_saom == 'A'}"> selected </c:if> >A</option>
													  <option value="B"<c:if test="${model.record.sveh_saom == 'B'}"> selected </c:if> >B</option>
													  <option value="E"<c:if test="${model.record.sveh_saom == 'E'}"> selected </c:if> >E</option>
												    </select>
								 				</td>			 				
							 				</tr>
							 				
							 			
							 				<tr height="15"><td></td></tr>
										</table>
									</td>
									
								</tr>				 	
						 	</table>
					 	</td>
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
								 					&nbsp;&nbsp;<span title="sveh_kvsa">Kvalitetssäkring tillämpas&nbsp;</span>
								 				</td>
								 				<td align="right" class="text14">
								 					<select class="selectMediumBlueE2" name="sveh_kvsa" id="sveh_kvsa">
							 						<option value="">-Välj-</option>
											  		<option value="J"<c:if test="${model.record.sveh_kvsa == 'J'}"> selected </c:if> >Ja</option>
											  		<option value="N"<c:if test="${model.record.sveh_kvsa == 'N'}"> selected </c:if> >Nej</option>
											  		</select>
								 				</td>
							 				</tr>
						 				</table>
						 			</td>
						 		</tr>
						 		</table>
							</td>
						</tr>
						<tr>
							<td width="2">&nbsp;</td>
				 			<td class="text14" >
				 				<table align="left" border="0" cellspacing="0" cellpadding="0">
								<tr>
						 			<td class="text14" align="right" >
						 			<img onMouseOver="showPop('7_info');" onMouseOut="hidePop('7_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 			<b>7.</b>&nbsp;<span title="sveh_rfac / rfac2 / rfac3">Ytterligare ref.nr.&nbsp;</span>
						 			<div class="text11" style="position: relative;" align="left">
						 			<span style="position:absolute;top:2px; width:250px;" id="7_info" class="popupWithInputText text11"  >
					           			<br>
					           			Detta är en frivillig uppgift. Här anger du ditt eget referensnummer.
					           			<br/>
									</span>
									</div>
									</td>
						 			<td align="left" ><input type="text" class="inputTextMediumBlue" name="sveh_rfac" id="sveh_rfac" size="25" maxlength="35" value="${model.record.sveh_rfac}"></td>
						 		</tr>
						 		<tr>
				 					<td align="left" >&nbsp;</td>
						 			<td align="left" ><input type="text" class="inputTextMediumBlue" name="sveh_rfac2" id="sveh_rfac2" size="25" maxlength="35" value="${model.record.sveh_rfac2}"></td>				 			
				 				</tr>
				 				<tr>
						 			<td align="left" >&nbsp;</td>
						 			<td align="left" ><input type="text" class="inputTextMediumBlue" name="sveh_rfac3" id="sveh_rfac3" size="25" maxlength="35" value="${model.record.sveh_rfac3}"></td>
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
				 					<td class="text14" >&nbsp;<span title="sveh_kenh">Klareringsenhet&nbsp;</span></td>
				 					<td class="text14">
				 						<input type="text" class="inputTextMediumBlue" name="sveh_kenh" id="sveh_kenh" size="20" maxlength="35" value="${model.record.sveh_kenh}">
				 					</td>
									</tr>
									<tr>
				 					<td class="text14" >&nbsp;<span title="sveh_kleo">Klareringsbehörig EORI&nbsp;</span></td>
				 					<td class="text14">
				 						<input type="text" class="inputTextMediumBlue" name="sveh_kleo" id="sveh_kleo" size="20" maxlength="17" value="${model.record.sveh_kleo}">
				 					</td>
									</tr>
									<tr>
				 					<td class="text14" >&nbsp;<span title="sveh_trnr">Transitnr/land&nbsp;</span></td>
				 					<td class="text14">
				 						<input type="text" class="inputTextMediumBlue" name="sveh_trnr" id="sveh_trnr" size="20" maxlength="20" value="${model.record.sveh_trnr}">
				 					</td>
									</tr>
									
									<tr>
				 					<td class="text14" >&nbsp;
				 					<img onMouseOver="showPop('berAvg_info');" onMouseOut="hidePop('berAvg_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 					<span title="sveh_beat">Ber.Avg.Tid&nbsp;</span>
				 					<div class="text11" style="position: relative;" align="left">
				 					<span style="position:absolute;top:2px; width:250px;" id="berAvg_info" class="popupWithInputText text11"  >
					           			<br>
					           			Enligt format: CCYYMMDDHHMM
					           			<br/>
									</span>
									</div>
									</td>
									
				 					<td class="text14">
				 						<input type="text" class="inputTextMediumBlue" name="sveh_beat" id="sveh_beat" size="20" maxlength="12" value="${model.record.sveh_beat}">
				 					</td>
									</tr>
									
									
									
						 			
									<tr>
				 					<td class="text14" >&nbsp;<span title="sveh_taxd">Taxeb.Dag..&nbsp;</span></td>
				 					<td class="text14">
				 						<input type="text" class="inputTextMediumBlue" name="sveh_taxd" id="sveh_taxd" size="20" maxlength="25" value="${model.record.sveh_taxd}">
				 					</td>
									</tr>
				 				</table>
				 			</td>
						</tr> 				
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
			<form action="tdsexport_updateStatus.do" name="updateStatusForm" id="updateStatusForm" method="post">
			 	<input type="hidden" name="currentAvd" id="currentAvd" value="${model.record.sveh_syav}">
			 	<input type="hidden" name="currentOpd" id="currentOpd" value="${model.record.sveh_syop}">
			 		
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
	
<%-- proforma dialog --%>	
 <tr>
	<td >
		<div id="dialogUpdateProforma" title="Dialog">
			 	<form action="tdsexport_updateProforma.do" name="updateProformaForm" id="updateProformaForm" >
			 	<input type="hidden" name="currentAvd" id="currentAvd" value='${model.record.sveh_syav}'>
			 	<input type="hidden" name="currentOpd" id="currentOpd" value='${model.record.sveh_syop}'>
			 	<input type="hidden" name="currentOpd" id="currentSign" value='${model.record.sveh_sysg}'>
			 	<c:choose>
				 	<c:when test="${not empty model.record.sveh_prof}">
				 		<input type="hidden" name="currentCheckboxProforma" id="currentCheckboxProforma" value='1'>
				 	</c:when>
				 	<c:otherwise>
				 		<input type="hidden" name="currentCheckboxProforma" id="currentCheckboxProforma" value=''>
				 	</c:otherwise>
			 	</c:choose> 
			 	<p class="text14" >Proformaärendes-fält</p>
				<table >
					<tr>
					<td >
						<table border="0">
						<tr>
							<td class="text14" align="left" title="sveh_tuid" ><b>Tullid</b></td>
							<td class="text14MediumBlue">
								<input type="text" class="inputText" name="sveh_tuid" id="sveh_tuid" size="15" maxlength="10" value="${model.record.sveh_tuid}">
							</td>
						</tr>
						
						</table>
					</td>
					</tr>
					<tr height="10"><td></td></tr> 
					
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
			<form action="tdsexport_edit_printSkilleArkTopic.do" name="skilleArkForm" id="skilleArkForm" method="post">
			 	<input type="hidden" name="currentAvd" id="currentAvd" value="${model.record.sveh_syav}">
			 	<input type="hidden" name="currentOpd" id="currentOpd" value="${model.record.sveh_syop}">
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
						<input type="hidden" name="wsavd" id="wsavd" value='${model.record.sveh_syav}'>
						<input type="hidden" name="wsopd" id="wsopd" value='${model.record.sveh_syop}'>
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
	