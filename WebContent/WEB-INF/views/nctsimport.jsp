<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTds.jsp" />
<!-- =====================end header ==========================-->
<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tdsglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/nctsimport.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
				<font class="tabLink">&nbsp;<spring:message code="systema.ncts.import.list.tab"/></font>
				<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a style="display:block;" href="nctsimport_edit.do?action=doPrepareCreate&user=${user.user}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.ncts.import.createnew.tab"/></font>
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
 	        <form name="nctsImportSearchForm" id="searchForm" action="nctsimport?action=doFind" method="post" >
 	        <tr>	
                <td class="text14" align="left" >&nbsp;&nbsp;&nbsp;<spring:message code="systema.ncts.import.list.search.label.avd"/></td>
                <td class="text14" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.import.list.search.label.signatur"/></td>
                <td class="text14" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.import.list.search.label.arende"/></td>
                <td class="text14" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.import.list.search.label.mrnNr"/></td>
                <td class="text14" align="left" >
				<img onMouseOver="showPop('datum_info');" onMouseOut="hidePop('datum_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
                <spring:message code="systema.ncts.import.list.search.label.datum"/>
                <div class="text11" style="position: relative;" align="left">
                <span style="position:absolute;top:2px; width:250px;" id="datum_info" class="popupWithInputText text11"  >
	           		Standardsök (tomt datum) gäller <b>15 dagar bakåt</b> i tiden.<br/><br/> 
	           		Om du vill söka längre bak i tiden måste du ange fom datum.<br/>
	           		T.ex. 20130101 söker från 1-jan och fram till idag.
				</span>	
				</div>
                </td>
                <td class="text14" align="left" >
				<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
                <spring:message code="systema.ncts.import.list.search.label.status"/>
                <div class="text11" style="position: relative;" align="left">
                <span style="position:absolute;top:2px; width:250px;" id="status_info" class="popupWithInputText text11"  >
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
					</span>	  
					</div>
				</td>	              
                <td class="text14" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.import.list.search.label.type"/></td>
                <td class="text14" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.import.list.search.label.ansvarig"/></td>
                <td class="text14" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.import.list.search.label.godsnr"/></td>
                <td class="text14" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.import.list.search.label.frigivningsDatum"/></td>
                
                <td>&nbsp;</td>
			</tr>
 	        <tr>
				<td align="left" >&nbsp;
           			<select class="selectMediumBlueE2" name="avd" id="avd">
	            		<option value="">-Välj-</option>
	 				  	<c:forEach var="record" items="${model.avdList}" >
                        	 	<option value="${record.avd}"<c:if test="${searchFilterTdsImportNcts.avd == record.avd}"> selected </c:if> >${record.avd}<c:if test="${record.tst == '1'}">&nbsp;(test)</c:if></option>
						</c:forEach> 
					</select>
				</td>
				<td align="left" >
           			<select class="selectMediumBlueE2" name="sign" id="sign">
	            		<option value="">-Välj-</option>
	 				  	<c:forEach var="record" items="${model.signList}" >
                             	 	<option value="${record.sign}"<c:if test="${searchFilterTdsImportNcts.sign == record.sign}"> selected </c:if> >${record.sign}</option>
						</c:forEach> 
					</select>
				</td>
				<td align="left" ><input type="text" class="inputText" name="opd" id="opd" size="10" maxlength="10" value="${searchFilterTdsImportNcts.opd}">&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="mrnNr" id="mrnNr" size="15" maxlength="18" value="${searchFilterTdsImportNcts.mrnNr}">&nbsp;</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="datum" id="datum" size="9" maxlength="8" value="${searchFilterTdsImportNcts.datum}">&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="status" id="status" size="2" maxlength="1" value="${searchFilterTdsImportNcts.status}">&nbsp;</td>
				<td align="left" >
					<select class="selectMediumBlueE2" name="forenklad" id="forenklad">
		            		<option value="">-Välj-</option>
		            		<option value="J" <c:if test="${searchFilterTdsImportNcts.forenklad == 'J'}"> selected </c:if> >Förenkl.</option>
		            		<option value="N" <c:if test="${searchFilterTdsImportNcts.forenklad == 'N'}"> selected </c:if> >Normal</option>
					</select>
				</td>
				<td align="left" ><input type="text" class="inputText" name="ansNavn" id="ansNavn" size="12" maxlength="50" value="${searchFilterTdsImportNcts.ansNavn}">&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="godsNr" id="godsNr" size="12" maxlength="35" value="${searchFilterTdsImportNcts.godsNr}">&nbsp;</td>
				<td align="left" ><input onKeyPress="return numberKey(event)" type="text" class="inputText" name="datumFr" id="datumFr" size="9" maxlength="8" value="${searchFilterTdsImportNcts.datumFr}">&nbsp;</td>

				
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
					<thead>
					<tr class="tableHeaderField" height="20" valign="left">
                    <th class="tableHeaderFieldFirst" align="left" >&nbsp;&nbsp;&nbsp;<spring:message code="systema.ncts.import.list.search.label.avd"/></th>
                		<th class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.import.list.search.label.signatur"/></th>
                		<th class="tableHeaderField" align="center" >&nbsp;<spring:message code="systema.ncts.import.list.search.label.update"/></th>
                		<th class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.import.list.search.label.arende"/></th>
                		<th class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.import.list.search.label.mrnNr"/></th>
                		<th class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.import.list.search.label.datum"/></th>
                		<th class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.import.list.search.label.status"/></th>
                		<th class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.import.list.search.label.type"/></th>
                		<th class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.import.list.search.label.ansvarig"/></th>
                		<th class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.import.list.search.label.godsnr"/></th>
                		<th class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.ncts.import.list.search.label.frigivningsDatum"/></th>
                		<%--
                		<td class="tableHeaderField">&nbsp;Kopiera Ärende&nbsp;</td>
	                 --%>    
                	</tr> 
                	</thead>
                	<tbody>
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
		               		<c:choose>
		               		<c:when test="${empty topic.status || topic.status == 'M' || topic.status == 'F'}">
	              	   	   		<span title="Ärendet är uppdaterbart"></span>
	            	   	   			<a href="nctsimport_edit.do?action=doFetch&avd=${topic.avd}&opd=${topic.opd}&status=${topic.status}">
	               					<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
	               				</a>
               				</c:when>
               				<c:otherwise>
               					<c:choose>
	               					<c:when test="${topic.status == 'U' || topic.status == 'H'}">
		              	   	   			<span title="Lossning är uppdaterbar">
		              	   	   			<a href="nctsimport_unloading_edit.do?action=doFetch&origo=list&avd=${topic.avd}&sign=${topic.sign}&opd=${topic.opd}&status=${topic.status}&datum=${topic.datum}">
											<img valign="bottom" src="resources/images/unloading.png" width="16" hight="16" border="0" alt="lossning - editable">					
										</a>
		              	   	   			</span>
		               				</c:when>
		               				<c:otherwise>
												               				
		               				</c:otherwise>
	               				</c:choose>									
               				</c:otherwise>
	               			</c:choose>
	               	   </td>
               		   <td class="tableCell" width="10%" >&nbsp;
               		   		<c:choose>
		               		<c:when test="${empty topic.status || topic.status == 'M' ||  topic.status == 'F'}">
	              	   	   		<span title="Ärendet är uppdaterbart"></span>
	            	   	   			<a href="nctsimport_edit.do?action=doFetch&avd=${topic.avd}&opd=${topic.opd}&status=${topic.status}">
	               					&nbsp;&nbsp;${topic.opd}
	               				</a>
               				</c:when>
               				<c:otherwise>
               					<c:choose>
	               					<c:when test="${topic.status == 'U' || topic.status == 'H'}">
		              	   	   			<span title="Lossning är uppdaterbar">
		              	   	   			<a href="nctsimport_unloading_edit.do?action=doFetch&origo=list&avd=${topic.avd}&sign=${topic.sign}&opd=${topic.opd}&status=${topic.status}&datum=${topic.datum}">
											&nbsp;&nbsp;${topic.opd}
										</a>
		              	   	   			</span>
		               				</c:when>
		               				<c:otherwise>
										<a href="nctsimport_edit.do?action=doFetch&avd=${topic.avd}&opd=${topic.opd}&status=${topic.status}">
											&nbsp;&nbsp;${topic.opd}
										</a>		               				
		               				</c:otherwise>
	               				</c:choose>									
               				</c:otherwise>
	               			</c:choose>
               		   </td>
		               <td class="tableCell" >&nbsp;${topic.mrnNr}</td>
		               <td class="tableCell" >&nbsp;${topic.datum}</td>
		               <td class="tableCell" >&nbsp;<b>${topic.status}</b></td>
		               <td class="tableCell" >&nbsp;
               				<c:if test="${topic.forenklad == 'J'}">Förenklad</c:if>
		               		<c:if test="${topic.forenklad == 'N'}">Normal</c:if>
		               </td>
		               <td class="tableCell" >&nbsp;${topic.ansNavn}</td>
		               <td class="tableCell" >&nbsp;${topic.godsNr}</td>
		               <td class="tableCell" >&nbsp;${topic.datumFr}</td>
		            </tr> 
		            </c:forEach>
		            </tbody>
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

