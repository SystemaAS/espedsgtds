<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTds.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tdsglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/tdsexportzem.js?ver=${user.versionEspedsg}"></SCRIPT>	
			
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
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="tdsexport.do?action=doFind&sign=${searchFilter.sign}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tds.export.list.tab"/></font>
					<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
               		<a style="display:block;" id="copyFromTransportUppdragLink" runat="server" href="#">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tds.export.createnew.tab"/></font>
						<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
					</a>
					 
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="20%" valign="bottom" class="tab" align="center" nowrap>
            	<font class="tabLink">&nbsp;<spring:message code="systema.tds.export.list.zem.tab"/></font> 
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
 	        <tr height="15"><td></td></tr>
 	        <form name="tdsExportSearchForm" id="searchForm" action="tdsexportzem?action=doFind" method="post" >
 	        <input type="hidden" name="sign" id="sign" value='${searchFilter.sign}'/>
 	        <tr>	
                <td class="text14" align="left" >&nbsp;&nbsp;&nbsp;<spring:message code="systema.tds.export.list.search.label.zem.tullid"/>
                	<a class="text14" target="_blank" href="${model.taricFragaTullidURL.value}" onclick="${model.taricFragaTullidURL.windowOpenDimensions}" >
            			<img title="Fråga Tullid (hos Tullverket)" style="vertical-align:bottom;" width="14px" height="14px" src="resources/images/help.png" border="0" alt="question">                		
            		</a>																	 			
                </td>
                <td class="text14" align="left" >&nbsp;&nbsp;<spring:message code="systema.tds.export.list.search.label.zem.datum"/></td>
                <td class="text14" align="left" >&nbsp;&nbsp;<spring:message code="systema.tds.export.list.search.label.zem.datumt"/></td>
                <td class="text14" align="left" >&nbsp;&nbsp;<spring:message code="systema.tds.export.list.search.label.zem.mrn"/></td>
                <td class="text14" align="left" >&nbsp;&nbsp;<spring:message code="systema.tds.export.list.search.label.zem.avsandare"/></td>
                <td class="text14" align="left" >&nbsp;&nbsp;<spring:message code="systema.tds.export.list.search.label.zem.mottagare"/></td>
                <td>&nbsp;</td>
			</tr>
 	        <tr>
				<td align="left" >&nbsp;&nbsp;<input type="text" class="inputText" name="tullid" id="tullid" size="20" maxlength="35" value='${searchFilter.tullid}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="datum" id="datum" size="9" maxlength="8" value='${searchFilter.datum}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="datumt" id="datumt" size="9" maxlength="8" value='${searchFilter.datumt}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="mrnnr" id="mrnnr" size="24" maxlength="22" value='${searchFilter.mrnnr}'>&nbsp;</td>
								
				<td align="left" ><input type="text" class="inputText" name="avsNavn" id="avsNavn" size="25" maxlength="50" value='${searchFilter.avsNavn}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="motNavn" id="motNavn" size="25" maxlength="50" value='${searchFilter.motNavn}'>&nbsp;</td>
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
	                    <td class="tableHeaderFieldFirst" nowrap>&nbsp;<spring:message code="systema.tds.export.list.search.label.zem.tullid"/>&nbsp;</td>
	                    <td class="tableHeaderField" nowrap>&nbsp;Datum&nbsp;</td>
	                    <td class="tableHeaderField" nowrap>&nbsp;<spring:message code="systema.tds.export.list.search.label.zem.mrn"/>&nbsp;</td>
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.tds.export.list.search.label.zem.avsandare"/>&nbsp;</td>
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.tds.export.list.search.label.zem.mottagare"/>&nbsp;</td>
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.tds.export.list.search.label.zem.arkivpdfh"/>&nbsp;</td>
	                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.tds.export.list.search.label.zem.arkivpdfv"/>&nbsp;</td>
	                </tr>     
		            <c:forEach items="${list}" var="record" varStatus="counter">    
		               <c:choose>           
		                   <c:when test="${counter.count%2==0}">
		                       <tr class="tableRow" height="20" >
		                   </c:when>
		                   <c:otherwise>   
		                       <tr class="tableOddRow" height="20" >
		                   </c:otherwise>
		               </c:choose>
		               <td class="tableCellFirst" >&nbsp;${record.svzh_tuid}</td>
		               <td class="tableCell" >&nbsp;${record.svzh_utdt}</td>
		               <td class="tableCell" >&nbsp;${record.svzh_mrnn}</td>
		               
		               <td class="tableCell" >&nbsp;${record.svzh_avna}</td>
		               <td class="tableCell" >&nbsp;${record.svzh_molk}&nbsp;&nbsp;${record.svzh_mona}</td>
		               <td class="tableCell" >&nbsp;
		               		<c:if test="${not empty record.pdfh}">
		               			<a href="tds_export_renderArchive.do?fp=${record.pdfh}" target="_new" >
			               			<img src="resources/images/pdf.png" border="0" width="15px" height="15px" alt="Visa arkivdokument" >
			               			${record.pdfhName}
		               			</a>
		               		</c:if>
		               </td>
		               <td class="tableCell" >&nbsp;
		               		<c:if test="${not empty record.pdfv}">
		               		<a href="tds_export_renderArchive.do?fp=${record.pdfv}" target="_new" >
			               		<img src="resources/images/pdf.png" border="0" width="15px" height="15px" alt="Visa arkivdokument" >
			               		${record.pdfvName}
		               		</a>
		               		</c:if>
		               </td>
		               
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
					<input type="hidden" name="sign" id="sign" value='${searchFilter.sign}'/>
											
					<p class="text14" >Du kan hämta ett nytt ärende från Norsk Importförtullning eller från ett Transportuppdrag.
					 	Du måste då välja:&nbsp;<b>Avdelning</b>&nbsp;och&nbsp;<b>Ärendenummer</b>.
					</p>
					<p class="text14">Flödet för att hämta är:
					</p>
					<ol class="text14" >
						<li class="text14" >
							Ett nytt ärende kommer att skapas om det ärendet du matar in finns i antingen 
							(a)&nbsp;<b>Norsk Importförtullning</b> eller (b)&nbsp;<b>Transportuppdrag</b>
						</li>
						<br/>
						<li class="text14" >
							Om ärendet inte finns varken i Norsk Importförtullning eller i Transportuppdrag måste du skapa ett nytt ärende. Du omdirigeras dit automatiskt.
						</li>
					</ol>
					
					<p class="text14" >Om du däremot vill mata in ett nytt ärende, utan att köra denna rutinen, lämna Avdelning och Ärendenr. tomma och klicka på "Gå vidare".
					</p>
					
					<table>
						<tr>
							<td class="text14" align="left" >&nbsp;Avdelning</td>
          							<td class="text14" align="left" >&nbsp;Ärendenr.</td>
          						</tr>
						<tr>
							<td class="text14MediumBlue">
								<select name="selectedAvd" id="selectedAvd">
				            		<option value="">-Välj-</option>
				 				  	<c:forEach var="record" items="${model.avdList}" >
			                             	 	<option value="${record.avd}">${record.avd}</option>
									</c:forEach> 
								</select>
							</td>
							<td class="text14MediumBlue">
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

