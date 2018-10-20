<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTds.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tdsglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/nctsimport_edit.js?ver=${user.versionEspedsg}"></SCRIPT>
 	
	
<table width="100%" cellspacing="0" border="0" cellpadding="0">
	
 <tr>
 <td>	
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
				<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<c:choose>
						<c:when test="${not empty model.record.tisg}">
							<a id="alinkMainList" tabindex=-1 style="display:block;" href="nctsimport.do?action=doFind&sign=${model.record.tisg}">
								<font class="tabDisabledLink">&nbsp;<spring:message code="systema.ncts.import.list.tab"/></font>
								<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
							</a>
						</c:when>
						<c:otherwise>
							<a id="alinkMainList" tabindex=-1 style="display:block;" href="nctsimport.do?action=doFind&sign=${model.sign}">
								<font class="tabDisabledLink">&nbsp;<spring:message code="systema.ncts.import.list.tab"/></font>
								<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
							</a>	
						</c:otherwise>
					</c:choose>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				
				<c:choose> 
			    	<c:when test="${editActionOnTopic=='doUpdate' or editActionOnTopic=='doFetch'}">
					<td width="15%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">
							&nbsp;<spring:message code="systema.ncts.import.created.mastertopic.tab"/>
						</font>
						<font class="text14MediumBlue">[${model.record.titdn}]</font>
						<c:if test="${model.record.tist == 'F' || model.record.tist == 'M' || empty model.record.tist}">
							<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						</c:if>
						
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkItemLines" tabindex=-1 style="display:block;" href="nctsimport_edit_items.do?action=doFetch&avd=${model.record.tiavd}&sign=${model.record.tisg}
													&opd=${model.record.titdn}&mrnNr=${model.record.titrnr}&godsNr=${model.record.tign}
													&status=${model.record.tist}&datum=${model.record.tidt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.ncts.import.item.createnew.tab"/>
							</font>
							<c:if test="${ model.record.tist == 'F' || model.record.tist == 'M' || empty model.record.tist}">
								<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
							</c:if>
							
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkUnloading" tabindex=-1 style="display:block;" href="nctsimport_unloading_edit.do?avd=${model.record.tiavd}&sign=${model.record.tisg}
													&opd=${model.record.titdn}&mrnNr=${model.record.titrnr}&godsNr=${model.record.tign}
													&status=${model.record.tist}&datum=${model.record.tidt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.ncts.import.unloading.createnew.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/unloading.png" width="16" hight="16" border="0" alt="show log">
						</a>
					</td>
					
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkUnloadingItemLines" tabindex=-1 style="display:block;" href="nctsimport_unloading_edit_items.do?action=doFetch&avd=${model.record.tiavd}&sign=${model.record.tisg}
											&opd=${model.record.titdn}&mrnNr=${model.record.titrnr}&godsNr=${model.record.tign}
											&status=${model.record.tist}&datum=${model.record.tidt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.ncts.import.unloading.item.createnew.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="item lines">
						</a>
					</td>
					
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkLogging" tabindex=-1 style="display:block;" href="nctsimport_logging.do?avd=${model.record.tiavd}&sign=${model.record.tisg}
											&opd=${model.record.titdn}&mrnNr=${model.record.titrnr}&godsNr=${model.record.tign}
											&status=${model.record.tist}&datum=${model.record.tidt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.ncts.import.logging.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkArchive" tabindex=-1 style="display:block;" href="nctsimport_archive.do?avd=${model.record.tiavd}&sign=${model.record.tisg}
											&opd=${model.record.titdn}&mrnNr=${model.record.titrnr}&godsNr=${model.record.tign}
											&status=${model.record.tist}&datum=${model.record.tidt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.ncts.import.archive.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
						</a>
					</td>
					<td width="13%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</c:when>
				<c:otherwise>
					<td width="15%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">&nbsp;<spring:message code="systema.ncts.import.createnew.tab"/></font>
						<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
						
					</td>
					<td width="80%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
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
	<form name="nctsImportSaveNewTopicForm" id="nctsImportSaveNewTopicForm" method="post">
	
	<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 		<tr height="3"><td colspan="2">&nbsp;</td></tr>
 		<%-- GENERAL HIDDEN --%> 
	    <input type="hidden" name="thmf" id="thmf" value="015">
		
		<tr height="4">
			<td colspan="2">&nbsp;
				<%-- test indicator /per avdelning --%> 
				<c:forEach var="record" items="${avdListSessionTestFlag}" >
					<c:if test="${record.avd == model.record.tiavd}">	
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
	    	<%-- --- HIDDEN FORM FIELDS (not visible in form but important with an UPDATE ----- --%>			
			<%-- general (from user profile) --%>
			<input type="hidden" name="action" id="action" value='doUpdate'>
			<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
			<input type="hidden" name="opd" id="opd" value='${model.record.titdn}'>
			<%-- topic specific --%>
			<input type="hidden" name="tiavd" id="tiavd" value='${model.record.tiavd}'>
			<input type="hidden" name="titdn" id="titdn" value='${model.record.titdn}'>
			<input type="hidden" name="tisg" id="tisg" value='${model.record.tisg}'>
			<input type="hidden" name="tist" id="tist" value='${model.record.tist}'>
			<input type="hidden" name="tidt" id="tidt" value='${model.record.tidt}'>
	    		<input type="hidden" name="avd" id="avd" value='${model.record.tiavd}'>
			<input type="hidden" name="sign" id="sign" value='${model.record.tisg}'>
			
			<tr >
				<td colspan="3" align="left" class="text14MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;Avd:&nbsp;${model.record.tiavd}&nbsp;&nbsp;<span title="titdn">Ärende:</span>&nbsp;<b>${model.record.titdn}</b>
					&nbsp;&nbsp;&nbsp;<span title="titrnr">MRN-nr:</span>&nbsp;<b>${model.record.titrnr}</b>&nbsp;&nbsp;Gods-nr:&nbsp;<b>${model.record.tign}</b>
				</td>
				<td align="right" >
					<c:if test="${'1' != isTestAvd}">
						<%--This checkbox appears only in real production. Otherwise use the Testavdelning --%>
						<input type="checkbox" name="ti0035" id="ti0035" value="1" <c:if test="${model.record.ti0035 == '1'}"> checked </c:if> ><font class="text14MediumBlue"><b>TEST flag</b></font>&nbsp;&nbsp;&nbsp;
					</c:if>
				</td>
			</tr>
			<tr height="2"><td colspan="2">&nbsp;</td></tr>
			<tr >
				<td align="left" class="text14MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;<span title="tisg">Sign:</span>&nbsp;<b>${model.record.tisg}</b>,&nbsp;&nbsp;<span title="tidt">Datum:</span>&nbsp;<b>${model.record.tidt}</b>,
					&nbsp;
					<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
           			<span title="tist">Stat<a id="updateStatusLink" runat="server" href="#"><font class="text11MediumBlue">u</font></a>s:</span>&nbsp;<b>${model.record.tist}</b>
					&nbsp;&nbsp;
					<font class="text16RedBold" >*</font><span title="tienkl">Typ av förfarande</span>&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thsg) --%>
           			<select class="inputTextMediumBlueMandatoryField" name="tienkl" id="tienkl">
	            			<option value="J"<c:if test="${model.record.tienkl == 'J'}"> selected </c:if> >Förenklad</option>
					  	<option value="N"<c:if test="${model.record.tienkl == 'N'}"> selected </c:if> >Normal</option>
					</select>
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
			<%-- test indicator /per avdelning --%> 
			<c:if test="${'1' == isTestAvd}">
				<tr>
					<td align="left" class="text14Red" >
						&nbsp;&nbsp;&nbsp;&nbsp;<b>[TEST Avdelning]</b>
					</td>
				</tr>
			</c:if>	
		</c:when>
		<%-- CREATE MODE --%> 
		<c:otherwise>
			<%-- --- HIDDEN FORM FIELDS (not visible in form but important with an UPDATE ----- --%>			
			<%-- general (from user profile) --%>
			<input type="hidden" name="action" id="action" value='doUpdate'>
			<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
			<input type="hidden" name="opd" id="opd" value='${model.record.titdn}'>
			<%-- topic specific (syop and tuid) --%>
			<input type="hidden" name="titdn" id="titdn" value='${model.record.titdn}'>
			<input type="hidden" name="tist" id="tist" value='${model.record.tist}'>
			<input type="hidden" name="tidt" id="tidt" value='${model.record.tidt}'>
			
			<tr >
				<td align="left" class="text14MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;<font class="text16RedBold" >*</font><span title="avd(tiavd)">Avd:</span>&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thavd) --%>
           			<select class="selectMediumBlueE2" name="avd" id="avd">
	            		<option value="">-Välj-</option>
	 				  	<c:forEach var="record" items="${model.avdList}" >
                       	 	<option value="${record.avd}"<c:if test="${model.record.tiavd == record.avd}"> selected </c:if> >${record.avd}<c:if test="${record.tst == '1'}">&nbsp;(test)</c:if></option>
						</c:forEach> 
					</select>
					&nbsp;
					<font class="text16RedBold" >*</font><span title="sign(tisg)">Sign:</span>&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thsg) --%>
           			<select class="selectMediumBlueE2" name="sign" id="sign">
	            		<option value="">-Välj-</option>
	 				  	<c:forEach var="record" items="${model.signList}" >
	 				  		<c:choose>
		 				  		<c:when test="${empty model.record.tisg}">
		                        	 	<option value="${record.sign}"<c:if test="${user.tdsSign == record.sign}"> selected </c:if> >${record.sign}</option>
		 				  		</c:when>
		 				  		<c:otherwise>
		                        	 	<option value="${record.sign}"<c:if test="${model.record.tisg == record.sign}"> selected </c:if> >${record.sign}</option>
	                        	 	</c:otherwise>
                        	 	</c:choose>
						</c:forEach> 
					</select>
					<font class="text16RedBold" >*</font><span title="tienkl">Typ av förfarande</span>&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thsg) --%>
           			<select class="inputTextMediumBlueMandatoryField" name="tienkl" id="tienkl">
	            			<option value="J"<c:if test="${model.record.tienkl == 'J'}"> selected </c:if> >Förenklad</option>
					  	<option value="N"<c:if test="${model.record.tienkl == 'N'}"> selected </c:if> >Normal</option>
					</select>
				</td>
				<td>&nbsp;</td>
			</tr>	
		</c:otherwise>
		</c:choose>

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
				<td colspan="10">
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
				 			<td class="textError">
				 				<ul>
                                    <li>
                                      	Fel vid uppdatering:${model.errorMessage}]  
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
					 				Näringliv - Ansvarige&nbsp;
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
					 			<td class="text14"><input type="text" class="inputTextMediumBlue" name="tikn" id="tikn" size="8" maxlength="8" value="${model.record.tikn}"></td>
					 			<td class="text14">&nbsp;</td>
			 				</tr>
			 				<tr >
								<%-- ================================================================================== --%>
						        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
						        		 These original values will be used when the user clicks "Cancel" buttons (puttting
						        		 back original value)																--%> 
						        	<%-- ================================================================================== --%>
						        	<input type="hidden" name="orig_tikn" id="orig_tikn" value='${model.record.tikn}'>
						        	<input type="hidden" name="orig_tina" id="orig_tina" value='${model.record.tina}'>
						        	<input type="hidden" name="orig_titin" id="orig_titin" value='${model.record.titin}'>
						        	<input type="hidden" name="orig_tiad1" id="orig_tiad1" value='${model.record.tiad1}'>
						        	<input type="hidden" name="orig_tipn" id="orig_tipn" value='${model.record.tipn}'>
						        	<input type="hidden" name="orig_tips" id="orig_tips" value='${model.record.tips}'>
						        	<input type="hidden" name="orig_tilk" id="orig_tilk" value='${model.record.tilk}'>
						        	<input type="hidden" name="orig_tisk" id="orig_tisk" value='${model.record.tisk}'>
			 				
					 			<td class="text14">&nbsp;<font class="text16RedBold" >*</font><span title="titin">TIN</span></td>
					 			<td class="text14">&nbsp;<font class="text16RedBold" >*</font><span title="tina">Namn</span>
						            <a tabindex="-1" id="tinaIdLink">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
									</a>
					 			</td>
			 				</tr>
			 				<tr >
					 			<td class="text14"><input type="text" class="inputTextMediumBlueMandatoryField" name="titin" id="titin" size="17" maxlength="17" value="${model.record.titin}"></td>
					 			<td class="text14"><input type="text" class="inputTextMediumBlueMandatoryField" name="tina" id="tina" size="20" maxlength="30" value="${model.record.tina}"></td>
			 				</tr>
			 				<tr >
					 			<td class="text14">&nbsp;<span title="tiad1">Adress</span></td>
					 			<td class="text14">&nbsp;<span title="tisk">Språkkod</span></td>
			 				</tr>
			 				<tr >
					 			<td class="text14"><input type="text" class="inputTextMediumBlue" name="tiad1" id="tiad1" size="30" maxlength="30" value="${model.record.tiad1}"></td>
					 			<td class="text14">
					 				<select class="selectMediumBlueE2" name="tisk" id="tisk">
						            		<option value="">-Välj-</option>
						 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
					                             <option value="${code.tkkode}"<c:if test="${model.record.tisk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
											</c:forEach> 
									</select>
									<a tabindex="-1" id="tiskIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
					 			</td>
			 				</tr>
			 				<tr >
					 			<td class="text14">&nbsp;<span title="tips">Postadress</span></td>
					 			<td class="text14">&nbsp;<span title="tipn">Postnr</span></td>
			 				</tr>
			 				<tr >
					 			<td class="text14"><input type="text" class="inputTextMediumBlue" name="tips" id="tips" size="24" maxlength="24" value="${model.record.tips}"></td>
					 			<td class="text14"><input type="text" class="inputTextMediumBlue" name="tipn" id="tipn" size="9" maxlength="9" value="${model.record.tipn}"></td>
			 				</tr>
			 				<tr >
					 			<td class="text14">&nbsp;<span title="tilk">Landkod</span></td>
					 			<td class="text14">&nbsp</td>
			 				</tr>
			 				<tr >
					 			<td>
					 				<select class="selectMediumBlueE2" name="tilk" id="tilk">
						            		<option value="">-Välj-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
	                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.tilk == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
										</c:forEach> 
									</select>
									<a tabindex="-1" id="tilkIdLink" OnClick="triggerChildWindowCountryCodes(this)">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
				 				</td>
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
				            <td ><font class="text16RedBold" >*</font><span title="tign">Godsnr</span></td>
				            <td colspan="3" ><input type="text" class="inputTextMediumBlueMandatoryField" name="tign" id="tign" size="36" maxlength="35" value="${model.record.tign}"></td>
			            </tr>
						<tr>
				            <td >&nbsp;</td>
				            <td ><font class="text16RedBold" >*</font><span title="tignsk">Språkkod</span>&nbsp;</td>
				            <td >
					            <select class="inputTextMediumBlueMandatoryField" name="tignsk" id="tignsk">
					            		<option value="">-Välj-</option>
					 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
				                             <option value="${code.tkkode}"<c:if test="${model.record.tignsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
										</c:forEach> 
								</select>
				 				<a tabindex="-1" id="tignskIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								</a>
				            </td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><font class="text16RedBold" >*</font><span title="titrnr">MRN-nr</span></td>
				            <td colspan="3" ><input type="text" class="inputTextMediumBlueMandatoryField" name="titrnr" id="titrnr" size="36" maxlength="18" value="${model.record.titrnr}"></td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><font class="text16RedBold" >*</font><span title="tialk">Avs.land</span>&nbsp;</td>
				            <td >
					            <select class="inputTextMediumBlueMandatoryField" name="tialk" id="tialk">
				            		<option value="">-Välj-</option>
				 				  	<c:forEach var="country" items="${model.countryCodeList}" >
	                              	 	<option value="${country.svkd_kd}"<c:if test="${model.record.tialk == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
									</c:forEach> 
								</select>
					 			<a tabindex="-1" id="tialkIdLink" OnClick="triggerChildWindowCountryCodes(this)">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								</a>							
				            </td>
			            </tr>
			            <tr height="10"><td>&nbsp;</td></tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="tialsk">Avt.lag.plats (kod)</span></td>
				            <td ><input type="text" class="inputText" name="tialsk" id="tialsk" size="17" maxlength="17" value="${model.record.tialsk}"></td>
				            <td ><span title="tialss">Språkkod</span>&nbsp;</td>
				            <td >
					            <select class="selectMediumBlueE2" name="tialss" id="tialss">
					            		<option value="">-Välj-</option>
					 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
				                             <option value="${code.tkkode}"<c:if test="${model.record.tialss == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
										</c:forEach> 
								</select>
								<a tabindex="-1" id="tialssIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								</a>
				            </td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="tials">Avt.lag.plats</span></td>
				            <td ><input type="text" class="inputText" name="tials" id="tials" size="20" maxlength="35" value="${model.record.tials}"></td>
			            </tr>
			            <tr height="2"><td>&nbsp;</td></tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="tiglsk">Godk.lag.plats (kod)</span></td>
				            <td ><input type="text" class="inputText" name="tiglsk" id="tiglsk" size="17" maxlength="17" value="${model.record.tiglsk}"></td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="tiacts">Kontrollplats (kod)</span></td>
				            <td ><input type="text" class="inputText" name="tiacts" id="tiacts" size="17" maxlength="17" value="${model.record.tiacts}"></td>
			            </tr>
			            <tr height="2"><td>&nbsp;</td></tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><font class="text16RedBold" >*</font><span title="titsb">Best.tullkontor</span></td>
				            <td ><input type="text" class="inputTextMediumBlueMandatoryField" name="titsb" id="titsb" size="9" maxlength="8" value="${model.record.titsb}">
				            	<a id="titsbIdLink" OnClick="triggerChildWindowTullkontorCodes(this)">
			            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
			            		</a>
				            </td>
				            <td ><span title="tiskb">Språkkod</span>&nbsp;</td>
				            <td >
					            <select class="selectMediumBlueE2" name="tiskb" id="tiskb">
					            		<option value="">-Välj-</option>
					 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
				                             <option value="${code.tkkode}"<c:if test="${model.record.tiskb == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
										</c:forEach> 
								</select>
				 				<a tabindex="-1" id="tiskbIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								</a>
				            </td>
			            </tr>
			            <tr>
				            <td >&nbsp;</td>
				            <td ><span title="tidtf">Frigivningsdatum</span></td>
				            <td ><input readonly type="text" class="inputTextReadOnly" name="tidtf" id="tidtf" size="8" maxlength="8" value="${model.record.tidtf}"></td>
			            </tr>
			            
			            <tr height="2"><td>&nbsp;</td></tr>
			            <tr >	
			            		<td class="text">&nbsp;</td>
			            		<td class="text">&nbsp;</td>
			            		 
		 				    	<%-- only status = M or emtpy status is allowed --%>
		 				    <c:choose>
			 				    <c:when test="${ model.record.tist == 'F' || model.record.tist == 'M' || empty model.record.tist}">
			 				    <td class="text9BlueGreen" valign="bottom"  >
				 				    	<input tabindex=-1 class="inputFormSubmit" type="submit" name="submit" onclick="javascript: form.action='nctsimport_edit.do';" value='<spring:message code="systema.ncts.import.createnew.submit"/>'/>
				 				</td>    	
				 				    	&nbsp;&nbsp;
				 				    	<c:if test="${not empty model.record.titdn}">
					 				    	<td colspan="2" class="text9BlueGreen" valign="bottom"  >
					 				    		<input tabindex=-2 class="inputFormSubmit" type="submit" name="send" onclick="javascript: form.action='nctsimport_send.do';" value='<spring:message code="systema.ncts.import.createnew.send"/>'/>
					 				    	</td>	
				 				    	</c:if>
			 				    </c:when>
			 				    <c:otherwise>
				 				    <td colspan="2" class="text9BlueGreen" valign="bottom"  >
				 				    		<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value='Ej uppdaterbart'/>
				 				    	</td>	
			 				    </c:otherwise>	
		 				    </c:choose>
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
 	</form>
 	</td>
</tr>
<tr>
	<td>
		<%-- change status admin dialog --%>	
		<div id="dialogUpdateStatus" title="Dialog">
			<form action="nctsimport_updateStatus.do" name="updateStatusForm" id="updateStatusForm" method="post">
			 	<input type="hidden" name="currentAvd" id="currentAvd" value="${model.record.tiavd}">
			 	<input type="hidden" name="currentOpd" id="currentOpd" value="${model.record.titdn}">
			 		
				<p class="text14" >Change status as needed.</p>
				<table>
					<tr>
						<td class="text14" align="left" >&nbsp;Status</td>
						<td class="text14MediumBlue">
							<select class="selectMediumBlueE2" name="selectedStatus" id="selectedStatus">
			            			<option value=" ">-vælg-</option>
				            		<option value="A">A</option>
				            		<option value="C">C</option>
				            		<option value="E">E</option>
				            		<option value="F">F</option>
				            		<option value="H">H</option>
				            		<option value="K">K</option>
				            		<option value="L">L</option>
				            		<option value="M">M</option>
				            		<option value="N">N</option>
				            		<option value="Q">Q</option>
				            		<option value="P">P</option>
				            		<option value="Q">Q</option>
				            		<option value="S">S</option>
				            		<option value="U">U</option>
				            		<option value="V">V</option>
				            		<option value="Z">Z</option>
				            		
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
 
	