<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTds.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tdsglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tdsexport.js?ver=${user.versionEspedsg}"></SCRIPT>	
		
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>


<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="20%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;<spring:message code="systema.tds.export.list.tab"/></font>
				<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
				
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
               		<a style="display:block;" id="copyFromTransportUppdragLink" runat="server" href="#">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tds.export.createnew.tab"/></font>
						<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
					</a>
					 
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
          		<a style="display:block;" href="tdsexportzem.do?action=doFind&sign=${searchFilterTdsExport.sign}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tds.export.list.zem.tab"/></font>
				</a>					 
			</td>
			<td width="40%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
		</tr>
	</table>
	</td>
</tr>

<tr>
	<td>
	<%-- search filter component --%>
		
 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 	        <tr height="3"><td></td></tr>
 	        <form name="tdsExportSearchForm" id="searchForm" action="tdsexport?action=doFind" method="post" >
 	        <tr>	
                <td class="text12" align="left" >&nbsp;&nbsp;&nbsp;<spring:message code="systema.tds.export.list.search.label.avd"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.tds.export.list.search.label.signatur"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.tds.export.list.search.label.arende"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.tds.export.list.search.label.extRef"/></td>
                
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.tds.export.list.search.label.tullid"/>
					<a class="text14" target="_blank" href="${model.taricFragaTullidURL.value}" onclick="${model.taricFragaTullidURL.windowOpenDimensions}" >
	            			<img title="Fråga Tullid (hos Tullverket)" style="vertical-align:bottom;" width="14px" height="14px" src="resources/images/help.png" border="0" alt="question">                		
	            		</a>																	 			
                </td>
                <td class="text12" align="left" >
	 				<img onMouseOver="showPop('meddTyp_info');" onMouseOut="hidePop('meddTyp_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
	 				<spring:message code="systema.tds.export.list.search.label.mtyp"/>
	 				
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
                
                <td class="text12" align="left" >
                	<img onMouseOver="showPop('datum_info');" onMouseOut="hidePop('datum_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
                	<spring:message code="systema.tds.export.list.search.label.datum"/>
                	<div class="text11" style="position: relative;" align="left" >
	                <span style="position:absolute;top:2px; width:250px;" id="datum_info" class="popupWithInputText text11"  >
		           		
		           		Standardsök (tomt datum) gäller <b>15 dagar bakåt</b> i tiden.<br/><br/> 
		           		Om du vill söka längre bak i tiden måste du ange fom datum.<br/>
		           		T.ex. 20130101 söker från 1-jan och fram till idag.
		           		
					</span>
					</div>	
                	</td>
                
                <td class="text12" align="left" >
                <img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
                <spring:message code="systema.tds.export.list.search.label.status"/>
                
                <div class="text11" style="position: relative;" align="left" >
                <span style="position:absolute;top:2px; width:250px;" id="status_info" class="popupWithInputText text11"  >
		           		<br/>
		           		Endast <b>M</b> och <b>' '</b> kan editeras. Alla andra kan man bara titta på
		           		
		           			<ul>
		           				</li>
		           				<li><b>' '</b>&nbsp;Ärendet är öppet för ändring.
		           				<li><b>M</b>&nbsp;Tulltekniskt fel har uppdagats
		           				</li>
		           				<li><b>A</b>&nbsp;Ärendet ligger i en sändning i avvaktan på att bli skickat.
		           				</li>
		           				<li><b>C</b>&nbsp;Ärendet är mottaget hos EDI-nätverket. (Alla sändningar till  EDI-nätverket skickas nu till TDS).
		           				</li>
		           				<li><b>E</b>&nbsp;Ärendet blir ändrat av en handläggare. Om du arbetar med ett ärende och strömavbrott eller liknande inträffar kommer
		           							ärendet att "hänga" med status E.
		           				</li>
								<li><b>F</b>&nbsp;Edifact-tekniskt fel har uppdagats.
		           				</li>
		           				<li><b>G</b>&nbsp;Ärendet är klarerat.
		           				</li>
		           				<li><b>K</b>&nbsp;Kvitterat OK från mottagsfunktionen.
		           				</li>
		           				<li><b>O</b>&nbsp;Ärendet är godkänt, skickat till vidare bearbetning.
		           				</li>
		           				<li><b>Q</b>&nbsp;Ärendet ligger nu i utgående brevlåda för TDS men är inte skickat. Kan ha väntekod satt!
		           				</li>
		           				<li><b>S</b>&nbsp;Skickat till Signering.
		           				</li>
		           				<li><b>+</b>&nbsp;Systemet skapar nu utgående Edifact meddelande för att kunna skicka ärendet.
		           				</li>
		           			</ul>
					</span>
					</div>	
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
                          	 	<option value="${record.avd}"<c:if test="${searchFilterTdsExport.avd == record.avd}"> selected </c:if> >${record.avd}<c:if test="${record.tst == '1'}">&nbsp;(test)</c:if></option>
						</c:forEach> 
					</select>
				</td>
				<td align="left" >
           			<select name="sign" id="sign">
	            		<option value="">-Välj-</option>
	 				  	<c:forEach var="record" items="${model.signList}" >
                       	 	<option value="${record.sign}" <c:if test="${searchFilterTdsExport.sign == record.sign}"> selected </c:if> > ${record.sign}</option>
						</c:forEach> 
					</select>
				</td>
				<td align="left" ><input type="text" class="inputText" name="opd" id="opd" size="10" maxlength="10" value='${searchFilterTdsExport.opd}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="xref" id="xref" size="10" maxlength="20" value='${searchFilterTdsExport.xref}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="tullId" id="tullId" size="14" maxlength="35" value='${searchFilterTdsExport.tullId}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="mtyp" id="mtyp" size="3" maxlength="3" value='${searchFilterTdsExport.mtyp}'>&nbsp;</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="datum" id="datum" size="10" maxlength="8" value='${searchFilterTdsExport.datum}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="status" id="status" size="2" maxlength="1" value='${searchFilterTdsExport.status}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="avsNavn" id="avsNavn" size="10" maxlength="50" value='${searchFilterTdsExport.avsNavn}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="motNavn" id="motNavn" size="10" maxlength="50" value='${searchFilterTdsExport.motNavn}'>&nbsp;</td>
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
	<%-- Validation errors --%>
	<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
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
	<c:if test="${model.errorMessage != null}">
		<tr>
		<td>
           	<table width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
    		       	<tr>
				<td class="textError">
					${model.errorMessage}:&nbsp;${model.errorInfo}
				</td>
				</tr	>
			</table>
		</td>
		</tr>
	</c:if>			
	<%-- list component --%>
	<c:if test="${not empty list}">
	<tr>
		<td>		
		<table width="100%" cellspacing="0" border="0" cellpadding="0">
	    	<%-- separator --%>
	        <tr height="1"><td></td></tr> 
			<tr>
				<td>
				<table width="100%" cellspacing="0" border="0" cellpadding="0">
					<tr class="tableHeaderField" height="20" valign="left">
	                    <td class="tableHeaderFieldFirst">&nbsp;<spring:message code="systema.tds.export.list.search.label.avd"/>&nbsp;</td>   
	                    <td class="tableHeaderField" nowrap>&nbsp;<spring:message code="systema.tds.export.list.search.label.signatur"/>&nbsp;</td>
	                    <td class="tableHeaderField" nowrap>
							<img onMouseOver="showPop('update_info');" onMouseOut="hidePop('update_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
		 					<spring:message code="systema.tds.export.list.search.label.update"/>
		 				</td>	
						<span style="position:absolute; left:1020px; top:150px; width:250px; height:400px;" id="update_info" class="popupWithInputText"  >
			           		<div class="text11" align="left">
			           			<img title="Rättelse" style="vertical-align:bottom;" src="resources/images/request.gif" width="14px" height="12px" border="0">
			           			<b>Rättelse av ej klarerat ärende</b><br/>
				           		<ul>
				           			<li>&nbsp;Status=O och meddelandetyp=[UNU] </li>
				           		</ul>
				           		
				           		<img title="Omprövning" style="vertical-align:bottom;" src="resources/images/lightbulb.png" width="14px" height="12px" border="0">
				           		<b>Omprövning</b><br/>
				           		<ul>
				           			<li>&nbsp;Status=G och meddelandetyp=[UGE] </li>
				           		</ul>
				           		
				           		<img title="Kompletterande" style="vertical-align:bottom;" src="resources/images/complete-icon.png" width="14px" height="12px" border="0">
				           		<b>Kompletterande av tulldeklaration</b><br/>
				           		<ul>
				           			<li>&nbsp;Status=O och meddelandetyp=[UNU eller URT]</li>
				           			<li>&nbsp;Status=G och meddelandetyp=[UGE]</li>
				           		</ul>
				           		
				           		<img title="Uppdatera ärende" style="vertical-align:bottom;" src="resources/images/update.gif" border="0">
				           		<b>Uppdatera</b><br/>
				           		<ul>
				           			<li>&nbsp;Status=tomt eller M</li>
				           		</ul>
							</div>
						</span>	
					
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.tds.export.list.search.label.arende"/>&nbsp;</td>
	                    <td class="tableHeaderField" >&nbsp;<spring:message code="systema.tds.export.list.search.label.extRef"/></td>
	                    <td class="tableHeaderField" nowrap>&nbsp;<spring:message code="systema.tds.export.list.search.label.tullid"/>&nbsp;</td>
	                    <td class="tableHeaderField" nowrap>&nbsp;<spring:message code="systema.tds.export.list.search.label.mtyp"/>&nbsp;</td>
	                    <td class="tableHeaderField" nowrap>&nbsp;<spring:message code="systema.tds.export.list.search.label.datum"/>&nbsp;</td>
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.tds.export.list.search.label.status"/>&nbsp;</td>
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.tds.export.list.search.label.avsandare"/>&nbsp;</td>
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.tds.export.list.search.label.mottagare"/>&nbsp;</td>
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.tds.export.list.search.label.begaranOmKlarering"/></td>
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.tds.export.list.search.label.kopieraArende"/></td>
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.tds.export.list.search.label.raderaArende"/></td>
	                    
	                </tr>     
		            <c:forEach items="${list}" var="topic" varStatus="counter">    
		               <c:choose>           
		                   <c:when test="${counter.count%2==0}">
		                       <tr class="tableRow" height="20" >
		                   </c:when>
		                   <c:otherwise>   
		                       <tr class="tableOddRow" height="20" >
		                   </c:otherwise>
		               </c:choose>
		               <td class="tableCellFirst" width="5%">&nbsp;${topic.avd}</td>
		               <td class="tableCell" >&nbsp;${topic.sign}</td>
		               <td nowrap class="tableCell" align="center" >&nbsp;
		               		<c:choose>
		               	   		<c:when test="${empty topic.status || topic.status=='M'}">
			               	   		<a id="alinkCurrentHeaderId_${counter.count}" onClick="setBlockUI(this);" href="tdsexport_edit.do?action=doFetch&avd=${topic.avd}&opd=${topic.opd}&sysg=${topic.sign}&tuid=${topic.tullid}&syst=${topic.status}&sydt=${topic.datum}">
			               				<img title="Uppdatera ärende" src="resources/images/update.gif" border="0" alt="edit">
		            				</a>
		               			</c:when>
		               			<c:otherwise>
		               				<c:choose>
			               				<c:when test="${ topic.status=='O'}">
				               				<c:if test="${ topic.mtyp=='UNU' }" >
						               	   		<a class="correctionOnNonClearedLink" id="correctionOnNonClearedLink${counter.count}" runat="server" href="#"">
						               				<img title="Rättelse av ej klarerat ärende" src="resources/images/request.gif" width="14px" height="12px" border="0" alt="request">
					            					</a>
					            					&nbsp;|&nbsp;
					            					<a class="completionLink" id="completionLink${counter.count}" runat="server" href="#"">
						               				<img title="Kompletterande tulldeklaration" src="resources/images/complete-icon.png" width="14px" height="12px" border="0" alt="completion">
					            					</a>
				               				</c:if>
				               				<c:if test="${ topic.mtyp=='URT' }" >
						               	   		<a class="completionLink" id="completionLink${counter.count}" runat="server" href="#"">
						               				<img title="Kompletterande tulldeklaration" src="resources/images/complete-icon.png" width="14px" height="12px" border="0" alt="completion">
					            					</a>
				               				</c:if>
			               				</c:when>
			               				<c:otherwise>
				               				<c:if test="${ topic.status=='G'}">
					               				<c:if test="${ topic.mtyp=='UGE' }" >
							               	   		<a class="completionLink" id="completionLink${counter.count}" runat="server" href="#"">
							               				<img title="Kompletterande tulldeklaration" src="resources/images/complete-icon.png" width="14px" height="12px" border="0" alt="completion">
						            					</a>
					               				</c:if>
				               				</c:if>
			               				</c:otherwise>
		               				</c:choose>
		               			</c:otherwise>
	               			</c:choose>
	               			
               		   </td>
	         			<%-- ==================================== --%>
	         			<%-- MODAL DIALOG [RÄTTELSE - CORRECTION] --%>
	         			<%-- ==================================== --%>
	         			<div style="display: none;" class="clazz_dialogCorrectionOnNonClearedTopic" id="dialogCorrectionOnNonClearedTopic${counter.count}" title="Dialog">
							<form  action="tdsexport_completionEventsOnTopic.do" name="correctNonClearedForm${counter.count}" id="correctNonClearedForm${counter.count}" method="post">
									<input type="hidden" name="originalOpd${counter.count}" id="originalOpd${counter.count}" value='${topic.opd}'/>
									<input type="hidden" name="originalAvd${counter.count}" id="originalAvd${counter.count}" value='${topic.avd}'/>
										
									<p class="text14" >Tullid: <font class="text14Bold">${topic.tullid}</font></p>
									<p class="text12" >Är du säker på att du vill rätta detta ej klarerat ärende ? </p>
									<p class="text12" >Meddelandetyp kommer att ändras automatiskt från <font class="text12BoldGray">${topic.mtyp}</font> till
										<c:if test="${ topic.mtyp=='UNU'}">
											<b>URT</b>&nbsp;<code>[Normalförfarande export, rättelse av ej klarerad UNU]</code>
											<input type="hidden" name="originalMtyp${counter.count}" id="originalMtyp${counter.count}" value="URT"/>
										</c:if>
									</p>
							</form>
						</div>
               			<%-- ============================================= --%>
               			<%-- MODAL DIALOG [KOMPLETTERANDE TULLDEKLARATION] --%>
               			<%-- ============================================= --%>
               			<div style="display: none;" class="clazz_dialogCompletionOnTopic" id="dialogCompletionOnTopic${counter.count}" title="Dialog">
							<form  action="tdsexport_completionEventsOnTopic.do" name="completionForm${counter.count}" id="completionForm${counter.count}" method="post">
		 						<input type="hidden" name="originalOpd${counter.count}" id="originalOpd${counter.count}" value='${topic.opd}'/>
		 						<input type="hidden" name="originalAvd${counter.count}" id="originalAvd${counter.count}" value='${topic.avd}'/>
			 						
			 					<p class="text14" >Tullid: <font class="text14Bold">${topic.tullid}</font></p>
				 				<p class="text12" >Är du säker på att du vill komplettera tulldeklarationen ? </p>
					 			<p class="text12" >Meddelandetyp kommer att ändras automatiskt från <font class="text12BoldGray">${topic.mtyp}</font> till
					 				<c:if test="${ topic.mtyp=='UGE'}">
					 					<b>UFF</b>&nbsp;<code>[Kompletterande deklaration efter lokalt klareringsförfarande export med notering i bokföringen]</code>
					 					<input type="hidden" name="originalMtyp${counter.count}" id="originalMtyp${counter.count}" value="UFF"/>
				 					</c:if>
				 					<c:if test="${ topic.mtyp=='UNU' || topic.mtyp=='URT'}">
					 					<b>UKO</b>&nbsp;<code>[Komplettering av UNU eller URT]</code>
					 					<input type="hidden" name="originalMtyp${counter.count}" id="originalMtyp${counter.count}" value="UKO"/>
				 					</c:if>
				 				</p>
							</form>
						</div>

               		   <td class="tableCell" >&nbsp;
               		   		<a id="alinkCurrentHeaderOpdId_${counter.count}" onClick="setBlockUI(this);" href="tdsexport_edit.do?action=doFetch&avd=${topic.avd}&opd=${topic.opd}&sysg=${topic.sign}&tuid=${topic.tullid}&syst=${topic.status}&sydt=${topic.datum}">
	               				&nbsp;${topic.opd}
		               		</a>
		               </td>
		               <td class="tableCell" >&nbsp;${topic.h_xref}</td>
		               <td class="tableCell" >&nbsp;${topic.tullid}</td>
		               <td class="tableCell" >&nbsp;${topic.mtyp}</td>
		               <td class="tableCell" >&nbsp;${topic.datum}</td>
		               <td class="tableCell" >&nbsp;<b>${topic.status}</b></td>
		               <td class="tableCell" >&nbsp;${topic.avsNavn}</td>
		               <td class="tableCell" >&nbsp;${topic.motNavn}</td>
		               
    		               <td class="tableCell" width="10%">&nbsp;
    		               	<c:if test="${ topic.status=='O'}">
               				<c:if test="${ topic.mtyp=='UNU' || topic.mtyp=='URT' || topic.mtyp=='UKO' }" >
			               		<a class="clearanceLink" id="clearanceLink${counter.count}" runat="server" href="#">
									<img style="vertical-align:bottom;" src="resources/images/finishlineFlag.png" width="18px" height="18px" border="0" alt="B.klarering">
									&nbsp;Klarera
								</a>
								 
								<div style="display: none;" class="clazz_dialogRequestClearance" id="dialogRequestClearance${counter.count}" title="Dialog">
									<form  action="tdsexport_requestClearanceOnTopic.do" name="requestClearanceForm${counter.count}" id="requestClearanceForm${counter.count}" method="post">
									 	<input type="hidden" name="action${counter.count}" id="action${counter.count}" value='doUpdate'/>
											<input type="hidden" name="originalOpd${counter.count}" id="originalOpd${counter.count}" value='${topic.opd}'/>
					 						<input type="hidden" name="originalAvd${counter.count}" id="originalAvd${counter.count}" value='${topic.avd}'/>

					 					<p class="text14" >Tullid: <font class="text14Bold">${topic.tullid}</font></p>
						 				<p class="text12" >Är du säker på att du vill skicka en begära om klarering för detta ärende? </p>
							 			<p class="text12" >Meddelandetyp kommer att ändras automatiskt från <font class="text12BoldGray">${topic.mtyp}</font> till
							 				<c:if test="${ topic.mtyp=='UNU' || topic.mtyp=='URT' ||  topic.mtyp=='UKO' }">
							 					<b>UBK</b>&nbsp;<code>[Elektronisk begäran om klarering]</code>
							 					<input type="hidden" name="originalMtyp${counter.count}" id="originalMtyp${counter.count}" value="HBK"/>
							 				</c:if>
						 				</p>
									</form>
								</div>
							</c:if>
						</c:if>
		               </td>
		               
		               <td class="tableCell" width="10%">&nbsp;
		               		
		               		<%-- <button class="buttonGray" type="button" name="copyButton${counter.count}" id="copyButton${counter.count}" >Kopiera</button>	 --%>
							<a class="copyLink" id="copyLink${counter.count}" runat="server" href="#">
								<img src="resources/images/copy.png" border="0" alt="copy">
								&nbsp;Kopiera
							</a>
							<span>
							<div style="display: none;" class="clazz_dialog" id="dialog${counter.count}" title="Dialog">
								<form  action="tdsexport_copyTopic.do" name="copyForm${counter.count}" id="copyForm${counter.count}" method="post">
								 	<input type="hidden" name="action${counter.count}" id="action${counter.count}" value='doUpdate'/>
									<input type="hidden" name="originalAvd${counter.count}" id="originalAvd${counter.count}" value='${topic.avd}'/>
				 					<input type="hidden" name="originalOpd${counter.count}" id="originalOpd${counter.count}" value='${topic.opd}'/>
					 					
									<p class="text12" >Du måste välja ny&nbsp;<code>Avdelning</code>&nbsp;och ny&nbsp;
												<code>Signatur</code> för att kunna kopiera ett ärende
									</p>
									<p class="text12" >Ett nytt ärendenummer kommer att skapas automatiskt.
									</p>
									
									<table>
										<tr>
											<td class="text12" align="left" >&nbsp;Avdelning</td>
	                							<td class="text12" align="left" >&nbsp;Signatur</td>
	                						</tr>
	 									<tr>
											<td class="text12MediumBlue">
												<select class="newAvd" name="newAvd${counter.count}" id="newAvd${counter.count}">
								            		<option value="">-Välj-</option>
								 				  	<c:forEach var="record" items="${model.avdList}" >
							                             <option value="${record.avd}">${record.avd}</option>
													</c:forEach> 
												</select>
											</td>
											<td class="text12MediumBlue">
												<select class="newSign" name="newSign${counter.count}" id="newSign${counter.count}">
								            		<option value="">-Välj-</option>
								 				  	<c:forEach var="record" items="${model.signList}" >
						                             	<option value="${record.sign}" <c:if test="${searchFilterTdsExport.sign == record.sign}"> selected </c:if> > ${record.sign}</option> 	
													</c:forEach> 
												</select>
											</td>
											<td nowrap class="text12">&nbsp;&nbsp;
												<input tabindex=-1 type="checkbox" name="fullCopy" id="fullCopy" value="1" >Fullständig kopiering
											</td>
										</tr>
									</table>
								</form>
							</div>
							
		               </td>
		               <%--DELETE --%>
		               <c:choose>
			               <c:when test="${topic.status == 'M' || empty topic.status}">	
				               <td class="tableCell" align="center" nowrap>&nbsp;
				               	<a onclick="javascript:return confirm('Är du säkert på att du vill radera denna?')"  tabindex=-1 href="tdsexport.do?action=doDelete&avd=${topic.avd}&opd=${topic.opd}">
				               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
				               	</a>	
				               </td>
	    		               </c:when>
			               <c:otherwise>
			               		<td class="tableCell" align="center" nowrap>&nbsp;</td>
			               </c:otherwise>
		               </c:choose>
		               
		            </tr> 
		            </c:forEach>
					
	            </table>
	            
			</td>	
			</tr>
		</table>
		</td>
	</tr>
	
    </c:if> 
    
    	<tr>
	<td>
			<div id="dialogCopyFromTransportUppdrag" title="Dialog">
				<form  action="tdsexport_doFetchTopicFromTransportUppdrag.do" name="copyFromTransportUppdragForm" id="copyFromTransportUppdragForm" method="post">
				 	<input type="hidden" name="actionGS" id="actionGS" value='doUpdate'/>
					<input type="hidden" name="sign" id="sign" value='${searchFilterTdsExport.sign}'/>
						
					<p class="text12" >Du kan hämta ett nytt ärende från Norsk Importförtullning eller från ett Transportuppdrag.
					 	Du måste då välja:&nbsp;<b>Avdelning</b>&nbsp;och&nbsp;<b>Ärendenummer</b>.
					</p>
					<p class="text12">Flödet för att hämta är:
					</p>
					<ol class="text12" >
						<li class="text12" >
							Ett nytt ärende kommer att skapas om det ärendet du matar in finns i antingen 
							(a)&nbsp;<b>Norsk Importförtullning</b> eller (b)&nbsp;<b>Transportuppdrag</b>
						</li>
						<br/>
						<li class="text12" >
							Om ärendet inte finns varken i Norsk Importförtullning eller i Transportuppdrag måste du skapa ett nytt ärende. Du omdirigeras dit automatiskt.
						</li>
					</ol>
					
					<p class="text12" >Om du däremot vill mata in ett nytt ärende, utan att köra denna rutinen, lämna Avdelning och Ärendenr. tomma och klicka på "Gå vidare".
					</p>
					
					<table>
						<tr>
							<td class="text12" align="left" >&nbsp;Avdelning</td>
          							<td class="text12" align="left" >&nbsp;Ärendenr.</td>
          						</tr>
						<tr>
							<td class="text12MediumBlue">
								<select name="selectedAvd" id="selectedAvd">
				            		<option value="">-Välj-</option>
				 				  	<c:forEach var="record" items="${model.avdList}" >
			                             	 	<option value="${record.avd}">${record.avd}</option>
									</c:forEach> 
								</select>
							</td>
							<td class="text12MediumBlue">
								<input type="text" class="inputText" id="selectedOpd" name="selectedOpd" size="10" maxlength="35" value=''>&nbsp;
							</td>
						</tr>
					</table>
				</form>
			</div>
		</td>
		</tr>
    
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

