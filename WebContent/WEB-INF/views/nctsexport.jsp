<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTds.jsp" />
<!-- =====================end header ==========================-->
<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tdsglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/nctsexport.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
				<font class="tabLink">&nbsp;<spring:message code="systema.ncts.export.list.tab"/></font>
				<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
				
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a style="display:block;" href="nctsexport_edit.do?action=doPrepareCreate&user=${user.user}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.ncts.export.createnew.tab"/></font>
					<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
					
				</a>
			</td>
			<td width="60%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
		</tr>
	</table>
	</td>
</tr>

<tr>
	<td>
	<%-- search filter component --%>
		
 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 	        <tr height="3"><td></td></tr>
 	        <form name="nctsExportSearchForm" id="searchForm" action="nctsexport?action=doFind" method="post" >
 	        <tr>	
                <td class="text12" align="left" >&nbsp;&nbsp;&nbsp;<spring:message code="systema.ncts.export.list.search.label.avd"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.export.list.search.label.signatur"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.export.list.search.label.arende"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.export.list.search.label.lrnNr"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.export.list.search.label.mrnNr"/></td>
                <td class="text12" align="left" >
				<img onMouseOver="showPop('datum_info');" onMouseOut="hidePop('datum_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
                <spring:message code="systema.ncts.export.list.search.label.datum"/>
                <div class="text11" style="position: relative;" align="left">
                <span style="position:absolute;top:2px; width:250px;" id="datum_info" class="popupWithInputText text11"  >
	           		Standardsök (tomt datum) gäller <b>15 dagar bakåt</b> i tiden.<br/><br/> 
	           		Om du vill söka längre bak i tiden måste du ange fom datum.<br/>
	           		T.ex. 20130101 söker från 1-jan och fram till idag.
				</span>	
				</div>
                </td>
                <td class="text12" align="left" >
				<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
                <spring:message code="systema.ncts.export.list.search.label.status"/>
                <div class="text11" style="position: relative;" align="left">
                <span style="position:absolute;top:2px; width:250px;" id="status_info" class="popupWithInputText text11"  >
		           		Endast <b>M</b>, F, G eller <b>' '</b> kan editeras. Alla andra kan man bara titta på.
		           			<ul>
		           				<li><b>' '</b>&nbsp;Ärendet är öppet för ändring.</li>
		           				<li><b>+</b>&nbsp;Temporärt arbetsstatus. Alla som, på ett viss tidpunkt, hade Q-status är nu klara för sändning.
		           				Systemet skapar nu utgående Edifact meddelande för att kunna skicka ärendet.
		           				</li>
		           				<li><b>A</b>&nbsp;Ärendet ligger i en sändning i avvaktan på att bli skickat.</li>
		           				<li><b>C</b>&nbsp;Ärendet är mottaget hos EDI-nätverket. (Alla sändningar till  EDI-nätverket skickas nu till TDS).
		           				</li>
		           				<li><b>D</b>&nbsp;Annullering beviljat (IE09).</li>
								<li><b>E</b>&nbsp;Ärendet blir ändrat av en handläggare. Om du arbetar med ett ärende och strömavbrott eller liknande inträffar kommer
		           							ärendet att "hänga" med status E.</li>
								<li><b>F</b>&nbsp;Edifact-tekniskt fel har uppdagats.</li>
		           				<li><b>G</b>&nbsp;Meddelande om ingen frigivning samt garantifel.</li>
		           				<li><b>K</b>&nbsp;Sigillering OK.</li>
		           				<li><b>N</b>&nbsp;EDIFACT OK.</li>
		           				<li><b>M</b>&nbsp;Tulltekniskt fel har uppdagats (IE16).</li>
								<li><b>P</b>&nbsp;Ärendet är godkänt och fått frigivning (dokument ER printet) (IE29). Skickat till vidare bearbetning .
		           				</li>
								<li><b>Q</b>&nbsp;Ärendet ligger nu i utgående brevlåda för TDS men är inte skickat. Kan ha väntekod satt!</li>
		           				<li><b>R</b>&nbsp;Nödprocedur(rescue routine).</li>
		           				<li><b>S</b>&nbsp;Skickat till Signering.</li>
		           				<li><b>U</b>&nbsp;Har fått MRN-nr. (IE28).</li>
								<li><b>V</b>&nbsp;Varukontroll (IE60).</li>
								<li><b>W</b>&nbsp;Ingen frigivning.</li>
		           				<li><b>Y</b>&nbsp;Frigivning avvisad. <br/>
		           				Efter att ha fått: Ingen frigivning (W) och bett om frigivning (IE62)</li>
		           				<li><b>Z</b>&nbsp;Transitering är avslutat.</li>
								<li><b>Å</b>&nbsp;Temporärt (...arbete).</li>
		           			</ul>
					</span>	
					</div> 
				</td>	               
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.export.list.search.label.mottagare"/></td>
                <td class="text12" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.export.list.search.label.bruttovikt"/></td>
                <td>&nbsp;</td>
			</tr>
 	        <tr>
				<td align="left" >&nbsp;
           			<select name="avd" id="avd">
	            		<option value="">-Välj-</option>
	 				  	<c:forEach var="record" items="${model.avdList}" >
                        	 	<option value="${record.avd}"<c:if test="${searchFilterTdsExportNcts.avd == record.avd}"> selected </c:if> >${record.avd}<c:if test="${record.tst == '1'}">&nbsp;(test)</c:if></option>
						</c:forEach> 
					</select>
				</td>
				<td align="left" >
           			<select name="sign" id="sign">
	            		<option value="">-Välj-</option>
	 				  	<c:forEach var="record" items="${model.signList}" >
                             	 	<option value="${record.sign}"<c:if test="${searchFilterTdsExportNcts.sign == record.sign}"> selected </c:if> >${record.sign}</option>
						</c:forEach> 
					</select>
				</td>
				<td align="left" ><input type="text" class="inputText" name="opd" id="opd" size="10" maxlength="10" value='${searchFilterTdsExportNcts.opd}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="lrnNr" id="lrnNr" size="14" maxlength="35" value='${searchFilterTdsExportNcts.lrnNr}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="mrnNr" id="mrnNr" size="14" maxlength="35" value='${searchFilterTdsExportNcts.mrnNr}'>&nbsp;</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="datum" id="datum" size="10" maxlength="8" value='${searchFilterTdsExportNcts.datum}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="status" id="status" size="2" maxlength="1" value='${searchFilterTdsExportNcts.status}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="motNavn" id="motNavn" size="10" maxlength="50" value='${searchFilterTdsExportNcts.motNavn}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="bruttoVikt" id="bruttoVikt" size="10" maxlength="50" value='${searchFilterTdsExportNcts.bruttoVikt}'>&nbsp;</td>
				
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
	                    <td class="tableHeaderFieldFirst" align="left" >&nbsp;&nbsp;&nbsp;<spring:message code="systema.ncts.export.list.search.label.avd"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.export.list.search.label.signatur"/></td>
                		<td class="tableHeaderField" align="center" >&nbsp;<spring:message code="systema.ncts.export.list.search.label.update"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.export.list.search.label.arende"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.export.list.search.label.lrnNr"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.export.list.search.label.mrnNr"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.export.list.search.label.datum"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.export.list.search.label.status"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.export.list.search.label.mottagare"/></td>
                		<td class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.export.list.search.label.bruttovikt"/></td>
                		<%--
                		<td class="tableHeaderField">&nbsp;Kopiera Ärende&nbsp;</td>
	                     --%>
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
		               <td class="tableCell" align="center" >
              	   	   		<c:if test="${empty topic.status || topic.status=='M' ||  topic.status=='G' ||  topic.status=='F'}">
              	   	   			<%-- only M, null or G (garantifel) are editable in NCTS --%>
              	   	   			<a href="nctsexport_edit.do?action=doFetch&avd=${topic.avd}&opd=${topic.opd}&sysg=${topic.sign}&tuid=${topic.lrnNr}&syst=${topic.status}&sydt=${topic.datum}">
	               					<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
	               				</a>	
	               			</c:if>
	               	   </td>
               		   <td class="tableCell" width="10%" >&nbsp;
	               	   		<a href="nctsexport_edit.do?action=doFetch&avd=${topic.avd}&opd=${topic.opd}&sysg=${topic.sign}&tuid=${topic.lrnNr}&syst=${topic.status}&sydt=${topic.datum}">
	               	   			&nbsp;&nbsp;${topic.opd}
	               			</a>
               		   </td>
		               <td class="tableCell" >&nbsp;${topic.lrnNr}</td>
		               <td class="tableCell" >&nbsp;${topic.mrnNr}</td>
		               <td class="tableCell" >&nbsp;${topic.datum}</td>
		               <td class="tableCell" >&nbsp;<b>${topic.status}</b></td>
		               <td class="tableCell" >&nbsp;${topic.motNavn}</td>
		               <td class="tableCell" >&nbsp;${topic.bruttoVikt}</td>
   		               <%--
   		               <td class="tableCell" width="10%">&nbsp;
		               		<a class="copyLink" id="copyLink${counter.count}" runat="server" href="#">
								<img src="resources/images/copy.png" border="0" alt="copy">
								&nbsp;Kopiera
							</a>
							 
							<div style="display: none; class="clazz_dialog" id="dialog${counter.count}" title="Dialog">
								<form  action="nctsexport_copyTopic.do" name="copyForm${counter.count}" id="copyForm${counter.count}" method="post">
								 	<input type="hidden" name="action${counter.count}" id="action${counter.count}" value='doFetch'/>
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
					                             	 	<option value="${record.sign}">${record.sign}</option>
													</c:forEach> 
												</select>
											</td>
										</tr>
									</table>
								</form>
							</div>
		               </td>
	                	--%>
		               
		            </tr> 
		            </c:forEach>
	            </table>
			</td>	
			</tr>
		</table>
		</td>
		</tr>
    </c:if> 
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

