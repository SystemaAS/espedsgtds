<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTds.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tdsglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/nctsexport_edit.js?ver=${user.versionEspedsg}"></SCRIPT>
 
 	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
	

<table width="100%" cellspacing="0" border="0" cellpadding="0">
	
 <tr>
 <td>	
	<%-- tab container component --%>
	<table width="100%"  class="text12" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<c:choose>
					<c:when test="${not empty model.record.thsg}">
						<a id="alinkMainList" tabindex=-1 style="display:block;" href="nctsexport.do?action=doFind&sign=${model.record.thsg}">
							<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.ncts.export.list.tab"/></font>
						</a>
					</c:when>
					<c:otherwise>
						<a id="alinkMainList" tabindex=-1 style="display:block;" href="nctsexport.do?action=doFind&sign=${model.sign}">
							<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.ncts.export.list.tab"/></font>
						</a>	
					</c:otherwise>
				</c:choose>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<c:choose> 
			    <c:when test="${editActionOnTopic=='doUpdate' or editActionOnTopic=='doFetch'}">
					<td width="15%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">
							&nbsp;<spring:message code="systema.ncts.export.created.mastertopic.tab"/>
						</font>
						<font class="text14MediumBlue">[${model.record.thtdn}]</font>
						<c:if test="${ model.record.thst == 'G' ||  model.status=='F' || model.record.thst == 'M' || empty model.record.thst}">
							<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						</c:if>
						
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkItemLines" tabindex=-1 style="display:block;" href="nctsexport_edit_items.do?action=doFetch&avd=${model.record.thavd}&sign=${model.record.thsg}
													&opd=${model.record.thtdn}&tullId=${model.record.thtuid}&mrnNr=${model.record.thtrnr}
													&status=${model.record.thst}&datum=${model.record.thdt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.ncts.export.item.createnew.tab"/>
							</font>
							<c:if test="${ model.record.thst == 'G' ||  model.status=='F' || model.record.thst == 'M' || empty model.record.thst}">
								<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
							</c:if>
							
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkLogging" tabindex=-1 style="display:block;" href="nctsexport_logging.do?avd=${model.record.thavd}&sign=${model.record.thsg}
													&opd=${model.record.thtdn}&tullId=${model.record.thtuid}&mrnNr=${model.record.thtrnr}
													&status=${model.record.thst}&datum=${model.record.thdt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.ncts.export.logging.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkArchive" tabindex=-1 style="display:block;" href="nctsexport_archive.do?avd=${model.record.thavd}&sign=${model.record.thsg}
													&opd=${model.record.thtdn}&tullId=${model.record.thtuid}&mrnNr=${model.record.thtrnr}
													&status=${model.record.thst}&datum=${model.record.thdt}">
							<font class="tabDisabledLink">
								&nbsp;<spring:message code="systema.ncts.export.archive.tab"/>
							</font>
							<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
						</a>
					</td>
					<td width="20%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</c:when>
				<c:otherwise>
					<td width="20%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">&nbsp;<spring:message code="systema.ncts.export.createnew.tab"/></font>
						<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
						
					</td>
					<td width="60%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
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
	<form name="nctsExportSaveNewTopicForm" id="nctsExportSaveNewTopicForm" method="post">
	
	<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 		<%-- GENERAL HIDDEN --%> 
	    <input type="hidden" name="thmf" id="thmf" value="015">
		
		<tr height="4">
			<td colspan="2">&nbsp;
				<%-- test indicator /per avdelning --%> 
				<c:forEach var="record" items="${avdListSessionTestFlag}" >
					<c:if test="${record.avd == model.record.thavd}">	
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
			<input type="hidden" name="opd" id="opd" value='${model.record.thtdn}'>
			<%-- topic specific (syop and tuid) --%>
			<input type="hidden" name="thavd" id="thavd" value='${model.record.thavd}'>
			<input type="hidden" name="thtdn" id="thtdn" value='${model.record.thtdn}'>
			<input type="hidden" name="thst" id="thst" value='${model.record.thst}'>
			<input type="hidden" name="thdt" id="thdt" value='${model.record.thdt}'>
			<input type="hidden" name="thtuid" id="thtuid" value='${model.record.thtuid}'>
			<input type="hidden" name="lrnNr" id="lrnNr" value='${model.record.thtuid}'>
	    		<input type="hidden" name="avd" id="avd" value='${model.record.thavd}'>
			<input type="hidden" name="sign" id="sign" value='${model.record.thsg}'>
			
			<tr >
				<td align="left" class="text14MediumBlue">
					&nbsp;&nbsp;Avd:&nbsp;${model.record.thavd}&nbsp;&nbsp;<span title="thtdn">Ärende</span>&nbsp;<b>${model.record.thtdn}</b>
				</td>
				<td align="right" >
					<c:if test="${'1' != isTestAvd}">
						<%--This checkbox appears only in real production. Otherwise use the Testavdelning --%>
						<input type="checkbox" name="th0035" id="th0035" value="1" <c:if test="${model.record.th0035 == '1'}"> checked </c:if> ><font class="text14MediumBlue"><b>TEST flag</b></font>&nbsp;&nbsp;&nbsp;						
					</c:if>
					<a href="ncts_export_edit_printTopic.do?avd=${model.record.thavd}&opd=${model.record.thtdn}">
					 	<img style="cursor:pointer;" src="resources/images/printer.png" width="30" hight="30" border="0" alt="Print">
						&nbsp;&nbsp;&nbsp;
					</a>
				</td>
			</tr>
			<tr >
				<td align="left" class="text14MediumBlue">
					&nbsp;&nbsp;<span title="thtuid">LRN-nr:</span>&nbsp;<b>${model.record.thtuid}</b>&nbsp;&nbsp;<span title="thtrnr">MRN-nr:</span>&nbsp;<b>${model.record.thtrnr}</b>&nbsp;&nbsp;
				</td>
			</tr>
			
			<tr >
				<td align="left" class="text14MediumBlue">
					<c:choose>
	 				    <c:when test="${model.record.thst == 'M' || empty model.record.thst}">																	 			
							&nbsp;&nbsp;<span title="thsg">Sign:</span>&nbsp;
		           			<select class="selectMediumBlueE2" name="thsg" id="thsg">
			            		<option value="">-Välj-</option>
			 				  	<c:forEach var="record" items="${model.signList}" >
                             	 	<option value="${record.sign}"<c:if test="${model.record.thsg == record.sign}"> selected </c:if> >${record.sign}</option>
								</c:forEach> 
							</select>
						</c:when>
						<c:otherwise>
							&nbsp;&nbsp;<span title="thsg">Sign:</span>&nbsp;<b>${model.record.thsg}</b>
							<input type="hidden" name="thsg" id="thsg" value='${model.record.thsg}'>
			
						</c:otherwise>
					</c:choose>
					&nbsp;&nbsp;<span title="thdt">Datum:</span>&nbsp;<b>${model.record.thdt}</b>,
					&nbsp;&nbsp;<span title="thst">Stat<a tabindex=-1 id="updateStatusLink" runat="server" href="#"><font class="text11MediumBlue">u</font></a>s:</span>&nbsp;${model.record.thst}
					&nbsp;&nbsp;
					<font class="text16RedBold" >*</font><span title="thenkl">Typ av förfarande</span>&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thsg) --%>
           			<select class="selectMediumBlueE2" name="thenkl" id="thenkl">
	            		<option value="J"<c:if test="${model.record.thenkl == 'J'}"> selected </c:if> >Förenklad</option>
					  	<option value="N"<c:if test="${model.record.thenkl == 'N'}"> selected </c:if> >Normal</option>
								   
					</select>
					
				</td>
				<td align="left" class="text14MediumBlue">&nbsp;</td>
			</tr>
			<%-- test indicator /per avdelning --%> 
			<c:if test="${'1' == isTestAvd}">
				<tr height="5"><td colspan="2">&nbsp;</td></tr>
				<tr>
					<td colspan="3" align="left" class="text14Red" >
						&nbsp;&nbsp;<b>[TEST Avdelning]</b>
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
			<input type="hidden" name="opd" id="opd" value='${model.record.thtdn}'>
			<%-- topic specific (syop and tuid) --%>
			<input type="hidden" name="thtdn" id="thtdn" value='${model.record.thtdn}'>
			<input type="hidden" name="thst" id="thst" value='${model.record.thst}'>
			<input type="hidden" name="thdt" id="thdt" value='${model.record.thdt}'>
			<input type="hidden" name="thtuid" id="thtuid" value='${model.record.thtuid}'>
			<input type="hidden" name="lrnNr" id="lrnNr" value='${model.record.thtuid}'>
			
			<tr >
				<td align="left" class="text14MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;<font class="text16RedBold" >*</font>Avd:&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thavd) --%>
           			<select class="selectMediumBlueE2" name="avd" id="avd">
	            		<option value="">-Välj-</option>
	 				  	<c:forEach var="record" items="${model.avdList}" >
                        	<c:choose>
	 				  		 <c:when test="${not empty model.record.thavd}">
                        	 	<option value="${record.avd}"<c:if test="${model.record.thavd == record.avd}"> selected </c:if> >${record.avd}<c:if test="${record.tst == '1'}">&nbsp;(test)</c:if></option>
                        	 </c:when>
                        	 <c:otherwise>
                        	 	<option value="${record.avd}"<c:if test="${model.avd == record.avd}"> selected </c:if> >${record.avd}<c:if test="${record.tst == '1'}">&nbsp;(test)</c:if></option>
                        	 </c:otherwise>
                        	 </c:choose>
						</c:forEach> 
					</select>
					&nbsp;
					<font class="text16RedBold" >*</font><span title="thsg">Sign:</span>&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thsg) --%>
           			<select class="selectMediumBlueE2" name="sign" id="sign">
	            		<option value="">-Välj-</option>
	 				  	<c:forEach var="record" items="${model.signList}" >
                             	 	<option value="${record.sign}"<c:if test="${model.record.thsg == record.sign}"> selected </c:if> >${record.sign}</option>
						</c:forEach> 
					</select>
					<font class="text16RedBold" >*</font><span title="thenkl">Typ av förfarande</span>&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thsg) --%>
           			<select class="selectMediumBlueE2" name="thenkl" id="thenkl">
	            		<option value="J"<c:if test="${model.record.thenkl == 'J'}"> selected </c:if> >Förenklad</option>
					  	<option value="N"<c:if test="${model.record.thenkl == 'N'}"> selected </c:if> >Normal</option>
								   
					</select>
				</td>
				<td>&nbsp;</td>
			</tr>	
		</c:otherwise>
		</c:choose>

		<tr height="5"><td colspan="2">&nbsp;</td></tr>
		<%-- --------------- --%>
		<%-- LEFT SIDE CELL --%>
		<%-- --------------- --%>
		<tr>
		<td width="50%">
		<table style="width:90%;" border="0" cellspacing="1" cellpadding="0">
			<tr>
	            <td width="5">&nbsp;</td>
	            <td >
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
	            		
				 		<tr>
				 			<td class="text14">
				 				<img onMouseOver="showPop('deklTyp_info');" onMouseOut="hidePop('deklTyp_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>1</b><font class="text16RedBold" >&nbsp;*</font><span title="thdk">Procedur&nbsp;</span>
			 					<div class="text12" style="position: relative;" align="left">
			 					<span style="position:absolute;top:2px; width:250px;" id="deklTyp_info" class="popupWithInputText text12"  > 
					           			<ul>
					           				<li>
					           				 	<b>T-</b>&nbsp;for the transportation of mixed consignments of T1 and T2 goods. In this case, the
													goods will be specified in separate SAD-BIS forms or loading lists for each
													status category of goods. The blank space after the symbol T should be struck
													through to prevent the insertion of any additional digits or numbers.
					           				</li>	
											<li>
					           				 	<b>T1</b>&nbsp;to cover the movement of non-Community goods within the Community and
													to/from the Community and the EFTA countries, as well as between the EFTA
													countries themselves. T1 is also used to cover the movement of Community goods in
													certain circumstances..
					           				</li>
					           				<li>
					           				 	<b>T2</b>&nbsp;for the transportation of Community goods where required.
					           				</li>
					           				<li>
					           				 	<b>T2F</b>&nbsp;
					           				 	for Community goods consigned to/from/between the non-fiscal territories of the Community
					           				 	E.g. Åland och Kanarieöarna
					           				</li>
					           				
					           				<li>
					           				 	<b>T2SM</b>&nbsp;SE T2 + SAN MARINO (Gäller inte NCTS Sverige [ref. NCTS Manual sida 174].)
					           				</li>
					           				<li>
					           				 	<b>TIR</b>Transport Internationaux Routiers (Gäller inte NCTS Sverige [ref. NCTS Manual sida 174].)
					           				</li>
					           				
					           				<li>
					           				 	<b>SS</b>Förhandsanmälan
					           				</li>
					           				<li>
					           				 	<b>T2L</b>TEST
					           				</li>
					           				
					           			</ul>
					           			Note: where the symbols T1, T2, T2F, T1bis, T2bis, T2Fbis, as appropriate, have been
										omitted the goods shall be deemed to have been placed under the T1 procedure.
					           			<br/>
					           			Procedurernas syfte är att visa vad ditt gods har för tullstatus det vill säga om det är en gemenskapsvara eller icke-gemenskapsvara.
										<br/>Transiterar du en icke-gemenskapsvara ska du alltid använda T1 proceduren. Om du istället transiterar en gemenskapsvara kan du oftast använda T2 proceduren.						           			
								</span>		
				 				</div>
				 			</td>	
				 			<td>
				 				<select class="selectMediumBlueE2" name="thdk" id="thdk">
				 				  <option value="">-Välj-</option>
				 				  	<c:forEach var="code" items="${model.ncts031_DeklType_CodeList}" >
                                	 	<option value="${code.tkkode}"<c:if test="${model.record.thdk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
									</c:forEach> 
								</select>
			 				</td>
			 				<td class="text14">
			 					&nbsp;<img onMouseOver="showPop('4_info');" onMouseOut="hidePop('4_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>4.</b><span title="thlstl">Lastspec.&nbsp;</span>
			 					<div class="text12" style="position: relative;" align="left">
			 					<span style="position:absolute;top:2px; width:250px;" id="4_info" class="popupWithInputText text12"  >
					           			<ul>
					           				<li>
					           				The total number of loading lists or descriptive commercial lists attached, if any, will
											be entered		
											</li>	
										</ul>
								</span>
								</div>								
				 			</td>
				 			<td>	
				 				<input type="text" class="inputTextMediumBlue"  name="thlstl" id="thlstl" size="8" maxlength="4" value='${model.record.thlstl}'>
			 				</td>
			 				
			 				<td class="text14">&nbsp;&nbsp;
			 					&nbsp;<img onMouseOver="showPop('5_info');" onMouseOut="hidePop('5_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>5.</b><span title="thvpos">V.poster&nbsp;</span>
			 					<div class="text12" style="position: relative;" align="left">
			 					<span style="position:absolute;top:2px; width:250px;" id="5_info" class="popupWithInputText text12"  >
					           			<ul>
					           				<li>
					           				 The total number of items declared on the SAD will be entered here. The number of
											 items corresponds to the number of boxes 31 to be completed. 
					           				</li>	
											
										</ul>
										Fältet är spärrat. Beräknas automatiskt från varulinjerna.
								</span>
								</div>
				 			</td>
				 			<td>	
				 				<input type="text" class="inputTextReadOnly" readonly name="thvpos" id="thvpos" size="8" maxlength="8" value='${model.record.thvpos}'>
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
                                      	Fel vid uppdatering. [ERROR:${model.errorMessage}]  
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
	 			<td width="5">&nbsp;</td>
	            <td >		
	 				<%-- SENDER --%>
	 				<table width="100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text14White">
								&nbsp;<img onMouseOver="showPop('2_info');" onMouseOut="hidePop('2_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 			<b>&nbsp;2.</b><font class="text16RedBold" >*</font>Avsändare&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 				<div class="text12" style="position: relative;" align="left">
				 				<span style="position:absolute;top:2px; width:250px;" id="2_info" class="popupWithInputText text12"  >
				           			Completion of this box is optional for the Contracting Parties.
									The full name and the address of the consignor/exporter concerned shall be entered.
									<br/>The Contracting Parties may add to the explanatory note the requirement to include a
									reference to the identification number allocated by the competent authorities for tax,
									statistical or other purposes.
									<br/>Where consignments are grouped, the word “Various” in the appropriate language, may
									be entered in this box and the list of consignors may be attached to the declaration.<ul>
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
							        	<input type="hidden" name="orig_thkns" id="orig_thkns" value='${model.record.thkns}'>
							        	<input type="hidden" name="orig_thnas" id="orig_thnas" value='${model.record.thnas}'>
							        	<input type="hidden" name="orig_thtins" id="orig_thtins" value='${model.record.thtins}'>
							        	<input type="hidden" name="orig_thads1" id="orig_thads1" value='${model.record.thads1}'>
							        	<input type="hidden" name="orig_thpns" id="orig_thpns" value='${model.record.thpns}'>
							        	<input type="hidden" name="orig_thpss" id="orig_thpss" value='${model.record.thpss}'>
							        	<input type="hidden" name="orig_thlks" id="orig_thlks" value='${model.record.thlks}'>
							        	<input type="hidden" name="orig_thsks" id="orig_thsks" value='${model.record.thsks}'>
							        	
							        	
							            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thkns">Kundnummer</span></td>
							            <td class="text14" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thnas">Namn&nbsp;</span>
							            	<a tabindex="-1" id="thnasIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>				
							            </td>
							        </tr>
							        <tr>
							            <td class="text14" align="left"><input type="text" class="inputTextMediumBlue" name="thkns" id="thkns" size="8" maxlength="8" value="${model.record.thkns}"></td>
							            <td class="text14" align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="thnas" id="thnas" size="30" maxlength="35" value="${model.record.thnas}"></td>
							            
							        </tr>
							        
							        <tr>
							            <td class="text14" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thtins">TIN</span></td>
							            <td class="text14" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="thtins" id="thtins" size="20" maxlength="17" value="${model.record.thtins}"></td>
							            <td align="left">&nbsp;</td>
							        </tr>
							        <tr height="4"><td>&nbsp;</td></tr>
							        <tr>
							            <td class="text14" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thads1">Adress 1</span></td>
							            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thsks">Språkkod</span>
						            		
							            </td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="thads1" id="thads1" size="30" maxlength="35" value="${model.record.thads1}"></td>
							            <td class="text14" align="left" >
				            			&nbsp;<select class="selectMediumBlueE2" name="thsks" id="thsks">
								            		<option value="">-Välj-</option>
								 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
				                                	 	<option value="${code.tkkode}"<c:if test="${model.record.thsks == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
													</c:forEach> 
											</select>
											<a tabindex="-1" id="thsksIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
							            
										</td>
							        </tr>
							        <tr>
							        		<td>
								        		<table>
								        		<tr>
								            		<td class="text14" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thpss">Postadress</span></td>
								            		<td align="left">&nbsp;</td>
								            	</tr>
								        		<tr>
								            		<td align="left">
								       				<input type="text" class="inputTextMediumBlueMandatoryField" name="thpss" id="thpss" size="30" maxlength="35" value="${model.record.thpss}">
							            			</td> 
								            		<td align="left">&nbsp;</td>
								        		</tr>    	
								            	</table>
							            </td>
							            <td >
								            	<table>
								        		<tr>
								        			<td class="text14" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thpns">Postnummer</span></td>
								            		<td class="text14" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thlks">Land</span>
								            		
								            		</td>
								            	</tr>
								        		<tr >
								        			<td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="thpns" id="thpns" size="10" maxlength="9" value="${model.record.thpns}"></td> 
								            		<td align="left">
								            			<select class="inputTextMediumBlueMandatoryField" name="thlks" id="thlks">
										            		<option value="">-Välj-</option>
									 				  	<c:forEach var="country" items="${model.countryCodeList}" >
					                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thlks == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
														</c:forEach> 
													</select>
													<a tabindex="-1" id="thlksIdLink" OnClick="triggerChildWindowCountryCodes(this)">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
													</a>
								            		</td> 
								        		</tr>  
								            	</table>
							            </td>
						            	</tr>
							        <tr height="15">
							            <td class="text12Bold" align="left" >&nbsp;</td> 
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
				 				<b>&nbsp;8.</b>Mottagare&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 				<div class="text12" style="position: relative;" align="left">
				 				<span style="position:absolute;top:2px; width:250px;" id="8_info" class="popupWithInputText text12"  >
				           			<br>
				           			<ul>
					           			<li>The full name and address of the person(s) or company(s) to whom the goods are to be
											delivered (consignee) shall be entered here. Where consignments are grouped, theword
											‘various’, in the appropriate language, may be entered in this box and the list of
											consignees may be attached to the SAD.
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
							        	<input type="hidden" name="orig_thknk" id="orig_thknk" value='${model.record.thknk}'>
							        	<input type="hidden" name="orig_thnak" id="orig_thnak" value='${model.record.thnak}'>
							        	<input type="hidden" name="orig_thtink" id="orig_thtink" value='${model.record.thtink}'>
							        	<input type="hidden" name="orig_thadk1" id="orig_thadk1" value='${model.record.thadk1}'>
							        	<input type="hidden" name="orig_thpnk" id="orig_thpnk" value='${model.record.thpnk}'>
							        	<input type="hidden" name="orig_thpsk" id="orig_thpsk" value='${model.record.thpsk}'>
							        	<input type="hidden" name="orig_thlkk" id="orig_thlkk" value='${model.record.thlkk}'>
							        	<input type="hidden" name="orig_thskk" id="orig_thskk" value='${model.record.thskk}'>
							        	
							            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thknk">Kundnummer</span></td>
							            <td class="text14" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thnak">Namn&nbsp;</span>
							            	<a tabindex="-1" id="thnakIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>	
										</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="thknk" id="thknk" size="8" maxlength="8" value="${model.record.thknk}"></td>
							            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="thnak" id="thnak" size="30" maxlength="35" value="${model.record.thnak}"></td>
							        </tr>

							        <tr>
							            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thtink">TIN</span></td>
							            <td class="text14" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="thtink" id="thtink" size="20" maxlength="17" value="${model.record.thtink}"></td>
							            <td align="left">&nbsp;</td>
							        </tr>
							        <tr height="4"><td>&nbsp;</td></tr>
							        <tr>
							            <td class="text14" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thadk1">Adress 1</span></td>
							            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thskk">Språkkod</span></td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="thadk1" id="thadk1" size="30" maxlength="35" value="${model.record.thadk1}"></td>
							            <td class="text14" align="left" >
							            	<select class="selectMediumBlueE2" name="thskk" id="thskk">
								            		<option value="">-Välj-</option>
								 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
				                                	 	<option value="${code.tkkode}"<c:if test="${model.record.thskk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
													</c:forEach> 
											</select>
											<a tabindex="-1" id="thskkIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
										</td>
							        </tr>
							        <tr>
							        		<td>
								        		<table>
								        		<tr>
								            		<td class="text14" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thpsk">Postadress</span></td>
								            		<td align="left">&nbsp;</td>
								            	</tr>
								        		<tr>
								            		<td align="left">
								       				<input type="text" class="inputTextMediumBlueMandatoryField" name="thpsk" id="thpsk" size="30" maxlength="35" value="${model.record.thpsk}">
							            			</td> 
								            		<td align="left">&nbsp;</td>
								        		</tr>    	
								            	</table>
							            </td>
							            <td >
								            	<table>
								        		<tr>
								        			<td class="text14" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thpnk">Postnummer</span></td>
								            		<td class="text14" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thlkk">Land</span></td>
								            	</tr>
								        		<tr >
								        			<td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="thpnk" id="thpnk" size="10" maxlength="9" value="${model.record.thpnk}"></td> 
								            		<td align="left">
								            			<select class="inputTextMediumBlueMandatoryField" name="thlkk" id="thlkk">
										            		<option value="">-Välj-</option>
									 				  	<c:forEach var="country" items="${model.countryCodeList}" >
					                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thlkk == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
														</c:forEach> 
													</select>
													<a tabindex="-1" id="thlkkIdLink" OnClick="triggerChildWindowCountryCodes(this)">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
													</a>
								            		
								            		</td> 
								        		</tr>  
								            	</table>
							            </td>
						            	</tr>
							        
							        <tr height="15">
							            <td class="text12Bold" align="left" >&nbsp;</td> 
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
					<%-- Special section --%>
					<table align="left" class="formFrameHeader" width="100%" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text14White">
				 				&nbsp;Särskilda upplysningar kring resplan och garanti&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
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
							            <td class="text12Bold" align="left" >&nbsp;</td> 
							            <td class="text12Bold" align="left" >&nbsp;</td> 
							            
							        </tr>
							        <tr>
							        	<td class="text14" align="left" >
							        	&nbsp;<img onMouseOver="showPop('51_info');" onMouseOut="hidePop('51_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							        	<b>51.</b><span title="thtsd1/thtsd2...">Planerade transittullkontor - i ruttordning&nbsp;</span>
							        	<div class="text12" style="position: relative;" align="left">
							        	<span style="position:absolute;top:2px; width:250px;" id="51_info" class="popupWithInputText text12"  >
						           			Vid förenkladförfarandet skall man minst fylla i det första fältet
						           			<br/><br/>
						           			The intended offices of entry into each Contracting Party whose territory is to be
											transited in the course of carriage shall be entered. Where the operation involves
											transiting territory other than that of the Contracting Parties, the office of exit by which
											the means of transport will leave the territory of the Contracting Parties shall be
											entered.
										</span>	
										</div>	
							        	</td>
							            <td class="text14" align="left" >&nbsp;</td>
							        </tr>
							        
							        <tr>
							        	<td>&nbsp;&nbsp;
							            	<table align="left" border="0" cellspacing="0" cellpadding="0">
							            		<tr>
							            			
						            			<td class="text14">1.
						            			<input type="text" class="inputTextMediumBlue" name="thtsd1" id="thtsd1" size="10" maxlength="8" value="${model.record.thtsd1}">
							            		<a id="thtsd1IdLink" OnClick="triggerChildWindowTullkontorCodes(this)">
							            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
							            		</a>
												</td>
												<td class="text14">&nbsp;&nbsp;2.
							            			<input type="text" class="inputTextMediumBlue" name="thtsd2" id="thtsd2" size="10" maxlength="8" value='${model.record.thtsd2}'>
								            		<a id="thtsd2IdLink" OnClick="triggerChildWindowTullkontorCodes(this)">
								            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								            		</a>
						            			</td>
						            			<td class="text14">&nbsp;&nbsp;3.
							            			<input type="text" class="inputTextMediumBlue" name="thtsd3" id="thtsd3" size="10" maxlength="8" value='${model.record.thtsd3}'>
							            			<a id="thtsd3IdLink" OnClick="triggerChildWindowTullkontorCodes(this)">
							            				<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
							            			</a>
							            			</td>
							            		</tr>
							            		
							            		<tr>
							            			<td class="text14">4.
							            			<input type="text" class="inputTextMediumBlue" name="thtsd4" id="thtsd4" size="10" maxlength="8" value='${model.record.thtsd4}'>
							            			<a id="thtsd4IdLink" OnClick="triggerChildWindowTullkontorCodes(this)">
								            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								            		</a>
							            			</td>
							            			
							            			<td class="text14">&nbsp;&nbsp;5.
							            			<input type="text" class="inputTextMediumBlue" name="thtsd5" id="thtsd5" size="10" maxlength="8" value='${model.record.thtsd5}'>
							            			<a id="thtsd5IdLink" OnClick="triggerChildWindowTullkontorCodes(this)">
								            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								            		</a>
							            			</td>
							            			
							            			<td class="text14">&nbsp;&nbsp;6.
							            			<input type="text" class="inputTextMediumBlue" name="thtsd6" id="thtsd6" size="10" maxlength="8" value='${model.record.thtsd6}'>
							            			<a id="thtsd6IdLink" OnClick="triggerChildWindowTullkontorCodes(this)">
								            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								            		</a>
							            			</td>					            			
							            		</tr>
							            		<tr>
							            			<td class="text14">7.
							            			<input type="text" class="inputTextMediumBlue" name="thtsd7" id="thtsd7" size="10" maxlength="8" value='${model.record.thtsd7}'>
							            			<a id="thtsd7IdLink" OnClick="triggerChildWindowTullkontorCodes(this)">
								            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								            		</a>
							            			</td>
							            			
							            			
							            			<td class="text14">&nbsp;&nbsp;8.
							            			<input type="text" class="inputTextMediumBlue" name="thtsd8" id="thtsd8" size="10" maxlength="8" value='${model.record.thtsd8}'>
							            			<a id="thtsd8IdLink" OnClick="triggerChildWindowTullkontorCodes(this)">
								            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								            		</a>
							            			</td>
							            		</tr>
							            	</table>
							            </td>     
							        </tr>
							        
							        <tr height="10"><td>&nbsp;</td></tr>

							        <tr>
							        	<td>&nbsp;&nbsp;
							        	<table align="left" border="0" cellspacing="0" cellpadding="0">
								        	<tr>
								        	<td class="text14" align="left" >
								        	&nbsp;<img onMouseOver="showPop('53_info');" onMouseOut="hidePop('53_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								        	<b>53.</b><font class="text16RedBold" >*</font><span title="thtsb">Bestämmelsetullkontor</span>
								        	&nbsp;
								        	<div class="text12" style="position: relative;" align="left">
								        	<span style="position:absolute;top:2px; width:250px;" id="53_info" class="popupWithInputText text12"  >
							           			The name of the customs office at which the goods shall be presented in order to end
												the transit operation shall be entered (office of destination). 
												<br/><br/>
							           			The offices of destination
												are shown in the 'list of customs offices competent to deal with transit operations'. The
												website address is:
												http://europa.eu.int/comm/taxation_customs/dds/en/csrdhome.htm.
											</span>
											</div>		
								        	</td>
								        	
						            		<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="thtsb" id="thtsb" size="10" maxlength="8" value="${model.record.thtsb}">
						            		<a id="thtsbIdLink" OnClick="triggerChildWindowTullkontorCodes(this)">
						            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
						            		</a>
							            	</td>	
								            </tr>
							            </table>
							            </td>
							            <td>&nbsp;</td>
							        </tr>
							        	<tr height="18"><td>&nbsp;</td></tr>
							        <tr >
							        	<td >&nbsp;&nbsp;
							            	<table class="tableBorderWithRoundCornersGray" align="left" border="0" cellspacing="2" cellpadding="0">
							            		<tr height="2"><td ></td></tr>
									        	<tr >
										        	<td  colspan="3" class="text14" align="left" >
										        	&nbsp;<img onMouseOver="showPop('52_info');" onMouseOut="hidePop('52_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										        	<b>52.</b><font class="text16RedBold" >*</font><b>Säkerhet - Garantikoder</b>&nbsp;
										        	
													<div class="text12" style="position: relative;" align="left">
													<span style="position:absolute;top:2px; width:250px;" id="52_info" class="popupWithInputText text12"  >
										           			Enter the appropriate code for the type of guarantee being used in the sub division
															marked “CODE” as specified in the table below.
															<br/><br/>
															Enter the appropriate information as specified in the table below under ‘other entries’,
															where required.
															<br/><br/>
															If the comprehensive guarantee, a guarantee waiver or individual guarantee is not valid
															for all the Contracting Parties, add 'not valid for' followed by the code of the relevant
															Contracting Party or Parties
										           			<br/><br/>
										           			Garantikoder
										           			<ul>
											           			<li>
											           				<b>0</b>&nbsp;Garantibefrielse
											           			</li>
											           			<li>
											           				<b>1</b>&nbsp;Universalgaranti
											           			</li>
											           			<li>
											           				<b>2</b>&nbsp;Enkelgaranti/Garantist
											           			</li>
											           			<li>
											           				<b>3</b>&nbsp;Enkelgaranti/Kontant
											           			</li>
											           			<li>
											           				<b>4</b>&nbsp;Enkelgarantiblanketter
											           			</li>
											           			<li>
											           				<b>6</b>&nbsp;Undantaget garanti enligt transiteringskonventionen.
											           			</li>
											           			<li>
											           				<b>A</b>&nbsp;For guarantee waiver by agreement.
											           			</li>
											           			<li>
											           				<b>7</b>&nbsp;For guarantee waiver for the journey between.
											           			</li>
											           			<li>
											           				<b>9</b>&nbsp;For individual guarantee of the type under point 3 of Annex IV to Appendix I (NCTS manual);
											           			</li>
											           		</ul>
										           	</span>	
										           	</div>
										           	</td>	
									        	</tr>
									        	<tr height="5"><td></td></tr>
							            		<tr>
							            			<td class="text14">&nbsp;<font class="text16RedBold" >*</font><span title="thgkd">Kod</span></td>
							            			<td class="text14">&nbsp;<span title="thgft1">Garantinummer</span></td>
							            			<td class="text14">&nbsp;<span title="thgft2">Annan garanti</span></td>
							            		</tr>
							            		<tr>
							            			<td>
							            				<select class="selectMediumBlueE2" name="thgkd" id="thgkd" >
										 				  <option value="">-Välj-</option>
														  <option value="0"<c:if test="${model.record.thgkd == '0'}"> selected </c:if> >0</option>
														  <option value="1"<c:if test="${model.record.thgkd == '1'}"> selected </c:if> >1</option>
														  <option value="2"<c:if test="${model.record.thgkd == '2'}"> selected </c:if> >2</option>
														  <option value="3"<c:if test="${model.record.thgkd == '3'}"> selected </c:if> >3</option>
														  <option value="4"<c:if test="${model.record.thgkd == '4'}"> selected </c:if> >4</option>
														  <option value="6"<c:if test="${model.record.thgkd == '6'}"> selected </c:if> >6</option>
														  <option value="A"<c:if test="${model.record.thgkd == 'A'}"> selected </c:if> >A</option>
														  <option value="7"<c:if test="${model.record.thgkd == '7'}"> selected </c:if> >7</option>
														  <option value="9"<c:if test="${model.record.thgkd == '9'}"> selected </c:if> >9</option>
														</select>
							            			</td>
							            			<td><input type="text" class="inputTextMediumBlue" name="thgft1" id="thgft1" size="24" maxlength="24" value="${model.record.thgft1}"></td>
							            			<td><input type="text" class="inputTextMediumBlue" name="thgft2" id="thgft2" size="30" maxlength="35" value="${model.record.thgft2}"></td>
							            		</tr>
							            		<tr>
							            			<td class="text14">&nbsp;<span title="thgadk">Åtkomstkod</span></td>
							            			<td class="text14">&nbsp;<span title="thgbgi">Begränsning inom EF</span></td>
							            			<td class="text14">&nbsp;<span title="thgbgu">Begränsning utanför [landkod]</span></td>
							            		</tr>
							            		<tr>
							            			<td><input type="text" class="inputTextMediumBlue" name="thgadk" id="thgadk" size="5" maxlength="4" value="${model.record.thgadk}"></td>
							            			<td>
								            			<select class="selectMediumBlueE2" name="thgbgi" id="thgbgi" >
									 				  <option value="0"<c:if test="${model.record.thgbgi == '0'}"> selected </c:if> >0</option>
													  <option value="1"<c:if test="${model.record.thgbgi == '1'}"> selected </c:if> >1</option>
													</select>
							            			</td>
							            			<td align="left">
								            			<select class="selectMediumBlueE2" name="thgbgu" id="thgbgu">
										            		<option value="">-Välj-</option>
									 				  	<c:forEach var="country" items="${model.countryCodeList}" >
					                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thgbgu == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
														</c:forEach> 
													</select>
									            	<a tabindex="-1" id="thgbguIdLink" OnClick="triggerChildWindowCountryCodes(this)">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
													</a>
							            			</td> 
							            		</tr>
							            		<tr height="2"><td></td></tr>
							            		
							            		
							            		<tr>
										            <td colspan="2" class="text14"><font class="text16RedBold" >*</font><span title="thgbl">Garantibelopp&nbsp;</span></td>
										 			<td class="text14">&nbsp;<font class="text16RedBold" >*</font><span title="thgvk">Valuta</span></td>
										 		</tr>
										 		<tr>	
										 			<td colspan="2" align="left" ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="thgbl" id="thgbl" size="20" maxlength="20" value="${model.record.thgbl}"></td>
										 			<td class="text14">
										 				<select class="inputTextMediumBlueMandatoryField" name="thgvk" id="thgvk" >
										 				  <option value="">-Välj-</option>	
										 				  	<c:forEach var="currency" items="${model.currencyCodeList}" >
						                                	 	<option value="${currency.svkd_kd}"<c:if test="${model.record.thgvk == currency.svkd_kd}"> selected </c:if> >${currency.svkd_kd}</option>
															</c:forEach>   
														</select>
														<a tabindex="-1" id="thgvkIdLink" >
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
														</a>								
									 				</td>
								 				</tr>
								 				<tr height="15"><td></td></tr>
											
							            	</table>
							            </td>  
							            <td>&nbsp;</td>
							        </tr>
							        
							        <tr height="15">
							            <td class="text12Bold" align="left" >&nbsp;</td> 
							            <td class="text12Bold" align="left" >&nbsp;</td> 
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
			            		<td class="text9BlueGreen" valign="bottom" align="left" >
			 				    	<%-- only status = M or emtpy status is allowed --%>
				 				    <c:choose>
					 				    <c:when test="${ model.record.thst == 'G' ||  model.status=='F' || model.record.thst == 'M' || empty model.record.thst}">
						 				    	<input tabindex=-1 class="inputFormSubmit" type="submit" name="submit" id="submit" onclick="javascript: form.action='nctsexport_edit.do';" value='<spring:message code="systema.ncts.export.createnew.submit"/>'/>
						 				    	&nbsp;&nbsp;
						 				    	<c:if test="${not empty model.record.thtdn && model.record.validUpdate}">
						 				    		<input tabindex=-2 class="inputFormSubmit" type="submit" name="send" id="send" onclick="javascript: form.action='nctsexport_send.do';" value='<spring:message code="systema.ncts.export.createnew.send"/>'/>
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
					        <tr height="5"><td colspan="2">&nbsp;</td></tr>
					 		<tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('15_info');" onMouseOut="hidePop('15_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>15.</b><font class="text16RedBold" >*</font><span title="thalk">Avsändnings-/Utf.land&nbsp;</span>
			 					<div class="text12" style="position: relative;" align="left">
			 					<span style="position:absolute;top:2px; width:250px;" id="15_info" class="popupWithInputText text12"  >
				           			<ul>
				           				<li>The name of the country from which goods are to be dispatched/exported shall be
											entered.	
										</li>
				           			</ul>
								</span>		
					            </div>
					            </td>
					            <td >
					            	<select class="inputTextMediumBlueMandatoryField" name="thalk" id="thalk">
				 						<option value="">-Välj-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
	                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thalk == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
										</c:forEach> 
									</select>
					            	<a tabindex="-1" id="thalkIdLink" OnClick="triggerChildWindowCountryCodes(this)">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>	
								</td>
							</tr>
							<tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('17_info');" onMouseOut="hidePop('17_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>17.</b><font class="text16RedBold" >*</font><span title="thblk">Bestämmelseland&nbsp;</span>
					            <div class="text12" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="17_info" class="popupWithInputText text12"  >
				           			<ul>
				           				<li>The name of the country of destination shall be entered.<br/><br/></li>
				           				<li>Bestämmelseland betyder det sista kända destinationslandet. 
				           				En omlastning i ett mellanliggande land förändrar inte bestämmelselandet. Bestämmelseland kan aldrig vara EU.</li>
				           			</ul>
								</span>	
					            </div>
					            </td>
					            
					            <td >
					            	<select class="inputTextMediumBlueMandatoryField" name="thblk" id="thblk">
				 						<option value="">-Välj-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
	                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thblk == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
										</c:forEach> 
									</select>
				            		<a tabindex="-1" id="thblkIdLink" OnClick="triggerChildWindowCountryCodes(this)">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
									
								</td>
					        </tr>
				            
						</table>
					</td>
				</tr>
				
				<tr height="20"><td colspan="2">&nbsp;</td></tr>
		
				<tr>
					<td width="2">&nbsp;</td>
			 		<td>
			 			<table border="0" cellspacing="2" cellpadding="0">
			 				<tr>
					            <td class="text14" align="left" colspan="2" >
					            <img onMouseOver="showPop('18_info');" onMouseOut="hidePop('18_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>18.</b><font class="text16RedBold" >*</font><span title="thtaid">Transportmedlets identitet och nationalitet vid avgång</span>
					            
					            <div class="text12" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="18_info" class="popupWithInputText text12"  >
					           			Följande uppgifter skall anges
					           			<ul>
						           			<li><b>First subdivision</b><br>
						           			The means of identification, for example the registration
											number(s) or name of the means of transport (lorry, ship, railway wagon, aircraft)
											on which the goods are directly loaded on presentation at the office of departure.
						           			</li>
						           			<br/>
						           			<li><b>Second subdivision</b><br>
												The nationality of the means of transport, using the codes
												laid down for that purpose.						           			
											</li>
					           			</ul>
					           			If the means of transport is made up of several means of transport, the nationality of
										the means of transport, which provides propulsion, shall be entered.
										<br/><br/>
					           			For example, where a tractor and a trailer with different registration numbers are used,
										enter the registration numbers of both tractor and trailer, and the nationality of the tractor.
										<br/>
										<b>LORRIES</b><br/>
										the vehicle’s registration number shall be entered
										<br/><br/><b>CONTAINERS</b><br/>
										the container’s number shall not be entered in this box, but in box 31. Details of the
										vessel or vehicle which is transporting the container shall be entered in box18.
										<br/><br/><b>SHIP</b><br/>
										the name of the ship shall be entered
										<br/><br/><b>AIRCRAFT</b><br/>
										the aircraft’s registration letters shall be entered
										<br/><br/><b>RAIL</b><br/>
										The railway carriage’s number shall be entered. Details of the nationality shall not be
										entered.
										For demountable bodies on railway carriages the demountable body’s number shall be
										entered in box 31, as a demountable body is considered to be a container.
										<br/><br/><b>FIXED TRANSPORT INSTALLATIONS</b><br/>
										Details of the registration number or nationality shall not be entered.
										In other cases, declaration of the nationality is optional for Contracting Parties.
								</span>
								</div>
								</td>
								<td class="text">&nbsp;</td>	
					        </tr>
					        <tr>
				            	<td >
				            		<input type="text" class="inputTextMediumBlueMandatoryField" name="thtaid" id="thtaid" size="25" maxlength="35" value="${model.record.thtaid}">
				            	</td>
				            	<td class="text">&nbsp;</td>
	        				</tr>
	        				<tr>
				            	<td class="text14">
				            		<font class="text16RedBold" >*</font><span title="thtalk">Nationalitet</span>
				            		
				            		
				            		<select class="inputTextMediumBlueMandatoryField" name="thtalk" id="thtalk">
				 						<option value="">-Välj-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
	                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thtalk == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
										</c:forEach> 
									</select>
									<a tabindex="-1" id="thtalkIdLink" OnClick="triggerChildWindowCountryCodes(this)">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
									&nbsp;<span title="thtask">Språk</span>
						            		
									<select class="selectMediumBlueE2" name="thtask" id="thtask">
			            				<option value="">-Välj-</option>
			 				  			<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
                               	 			<option value="${code.tkkode}"<c:if test="${model.record.thtask == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
										</c:forEach> 
									</select>
									<a tabindex="-1" id="thtaskIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
				            	</td>
				            	<td class="text">&nbsp;</td>
	        				</tr>
	        				<tr>
				            	<td class="text14">
				            		<img onMouseOver="showPop('thtrmi_info');" onMouseOut="hidePop('thtrmi_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				            		<b>26.</b>
				            		<font class="text16RedBold" >*</font><span title="thtrmi">Transportsätt vid avgången&nbsp;</span>
				            		<select class="inputTextMediumBlueMandatoryField" name="thtrmi" id="thtrmi">
				 						<option value="">-Välj-</option>
					 				  	<option title="Sjötransport" value="10"<c:if test="${model.record.thtrmi == '10'}"> selected </c:if> >10</option>
					 				  	<option title="Järnvägsvagn på sjögående fartyg" value="12"<c:if test="${model.record.thtrmi == '12'}"> selected </c:if> >12</option>
					 				  	<option title="Vägmotorfordon på sjögående fartyg" value="16"<c:if test="${model.record.thtrmi == '16'}"> selected </c:if> >16</option>
					 				  	<option title="Släpvagn eller påhängsvagn på sjögående fartyg" value="17"<c:if test="${model.record.thtrmi == '17'}"> selected </c:if> >17</option>
					 				  	<option title="Fartyg för inre vattenvägar på sjögående fartyg" value="18"<c:if test="${model.record.thtrmi == '18'}"> selected </c:if> >18</option>
					 				  	<option title="Järnvägstransport" value="20"<c:if test="${model.record.thtrmi == '20'}"> selected </c:if> >20</option>
					 				  	<option title="Vägfordon på järnvägsvagn" value="23"<c:if test="${model.record.thtrmi == '23'}"> selected </c:if> >23</option>
					 				  	<option title="Landsvägstransport" value="30"<c:if test="${model.record.thtrmi == '30'}"> selected </c:if> >30</option>
					 				  	<option title="Flygtransport" value="40"<c:if test="${model.record.thtrmi == '40'}"> selected </c:if> >40</option>
					 				  	<option title="Postförsändelse" value="50"<c:if test="${model.record.thtrmi == '50'}"> selected </c:if> >50</option>
					 				  	<option title="Transport vid fasta installationer" value="70"<c:if test="${model.record.thtrmi == '70'}"> selected </c:if> >70</option>
					 				  	<option title="Transport på inre vattenvägar" value="80"<c:if test="${model.record.thtrmi == '80'}"> selected </c:if> >80</option>
					 				  	<option title="Egen framdrivning" value="90"<c:if test="${model.record.thtrmi == '90'}"> selected </c:if> >90</option>
					 				  	
									</select>
				            	
				            	<div class="text12" style="position: relative;" align="left">
				            	<span style="position:absolute;top:2px; width:250px;" id="thtrmi_info" class="popupWithInputText text12"  >
					           			This box is optional for EU Member States in respect of Community transit.
										Using the appropriate Community codes, enter the mode of
										transport upon departure.
					           			<ul>
						           			<li>
						           				<b>10</b>&nbsp;Sjötransport
						           			</li>
						           			<li>
						           				<b>12</b>&nbsp;Järnvägsvagn på sjögående fartyg
						           			</li>
						           			<li>
						           				<b>16</b>&nbsp;Vägmotorfordon på sjögående fartyg
						           			</li>
						           			<li>
						           				<b>17</b>&nbsp;Släpvagn eller påhängsvagn på sjögående fartyg
						           			</li>
						           			<li>
						           				<b>18</b>&nbsp;Fartyg för inre vattenvägar på sjögående fartyg
						           			</li>
						           			<li>
						           				<b>20</b>&nbsp;Järnvägstransport
						           			</li>
						           			<li>
						           				<b>23</b>&nbsp;Vägfordon på järnvägsvagn
						           			</li>
						           			<li>
						           				<b>30</b>&nbsp;Landsvägstransport
						           			</li>
						           			<li>
						           				<b>40</b>&nbsp;Flygtransport
						           			</li>
						           			<li>
						           				<b>50</b>&nbsp;Postförsändelse
						           			</li>
						           			<li>
						           				<b>70</b>&nbsp;Transport vid fasta installationer
						           			</li>
						           			<li>
						           				<b>80</b>&nbsp;Transport på inre vattenvägar
						           			</li>
						           			<li>
						           				<b>90</b>&nbsp;Egen framdrivning
						           			</li>
					           			</ul>
								</span>
								</div>
								</td>
								<td class="text">&nbsp;</td>
	        				</tr>
						</table>
					</td>
				</tr>
				
				<tr height="10"><td class="text">&nbsp;</td> </tr>
				<tr>
					<td width="2">&nbsp;</td>
			 		<td>
						<table border="0" cellspacing="2" cellpadding="0">	
					 		<tr>
					            <td class="text14" align="left">
					            <img onMouseOver="showPop('21_info');" onMouseOut="hidePop('21_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>21.</b>
					            <font class="text16RedBold" >*</font><span title="thtgid">Transportmedlets identitet och nationalitet vid gränspassage</span>
					            <div class="text12" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="21_info" class="popupWithInputText text12"  >
					           			Enter the appropriate code for the type (lorry, ship, railway wagon, aircraft, etc.) and
										the means of identification (e.g. registration number or name) of the active means of
										transport (i.e. the means of transport providing propulsion) which it is presumed will
										propel the consignment across the external border of the Contracting Party where the
										office of departure is located. 
										<br/><br/>Then enter the code for the nationality of the means of
										transport, as known at the time the goods were placed under the transit procedure.
										<br/><br/><br/><br/>
										Where combined transport or several means of transport are used, the active means of
										transport is the unit which provides propulsion for the whole combination. For example
										in the case of a lorry on a sea-going vessel the active means of transport is the ship;
										and where a combination of a tractor and a trailer is used, the active means of
										transport is the tractor.
								</span>	
								</div>
								</td>
					            <td class="text">&nbsp;</td>
					        </tr>
				            <tr>
				            	<td >
				            		<input type="text" class="inputTextMediumBlue" name="thtgid" id="thtgid" size="25" maxlength="35" value="${model.record.thtgid}">
				            	</td>
				            	<td class="text">&nbsp;</td>
	        				</tr>
	        				<tr>
				            	<td class="text14">
				            		<font class="text16RedBold" >*</font><span title="thtglk">Nationalitet</span>
				            		
				            		<select class="inputTextMediumBlueMandatoryField" name="thtglk" id="thtglk">
				 						<option value="">-Välj-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
	                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thtglk == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
										</c:forEach> 
									</select>
									<a tabindex="-1" id="thtglkIdLink" OnClick="triggerChildWindowCountryCodes(this)">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
				            		&nbsp;<span title="thtgsk">Språk</span>
						            <select class="selectMediumBlueE2" name="thtgsk" id="thtgsk">
			            				<option value="">-Välj-</option>
			 				  			<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
                               	 			<option value="${code.tkkode}"<c:if test="${model.record.thtgsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
										</c:forEach> 
									</select>
									<a tabindex="-1" class="text14" target="_blank" href="${model.isoLanguageCodesURL.value}" onclick="${model.isoLanguageCodesURL.windowOpenDimensions}" >
				            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
				            		</a>
									
				            	</td>
				            	<td class="text">&nbsp;</td>
	        				</tr>
 		       				<tr>
				            	<td class="text14">
				            		<img onMouseOver="showPop('thtrm_info');" onMouseOut="hidePop('thtrm_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				            		<b>25.</b>
				            		<font class="text16RedBold" >*</font><span title="thtrm">Transportsätt vid gränsen&nbsp;</span>
				            		<select class="inputTextMediumBlueMandatoryField" name="thtrm" id="thtrm">
				 						<option value="">-Välj-</option>
					 				  	<option title="Sjötransport" value="10"<c:if test="${model.record.thtrm == '10'}"> selected </c:if> >10</option>
					 				  	<option title="Järnvägsvagn på sjögående fartyg" value="12"<c:if test="${model.record.thtrm == '12'}"> selected </c:if> >12</option>
					 				  	<option title="Vägmotorfordon på sjögående fartyg" value="16"<c:if test="${model.record.thtrm == '16'}"> selected </c:if> >16</option>
					 				  	<option title="Släpvagn eller påhängsvagn på sjögående fartyg" value="17"<c:if test="${model.record.thtrm == '17'}"> selected </c:if> >17</option>
					 				  	<option title="Fartyg för inre vattenvägar på sjögående fartyg" value="18"<c:if test="${model.record.thtrm == '18'}"> selected </c:if> >18</option>
					 				  	<option title="Järnvägstransport" value="20"<c:if test="${model.record.thtrm == '20'}"> selected </c:if> >20</option>
					 				  	<option title="Vägfordon på järnvägsvagn" value="23"<c:if test="${model.record.thtrm == '23'}"> selected </c:if> >23</option>
					 				  	<option title="Landsvägstransport" value="30"<c:if test="${model.record.thtrm == '30'}"> selected </c:if> >30</option>
					 				  	<option title="Flygtransport" value="40"<c:if test="${model.record.thtrm == '40'}"> selected </c:if> >40</option>
					 				  	<option title="Postförsändelse" value="50"<c:if test="${model.record.thtrm == '50'}"> selected </c:if> >50</option>
					 				  	<option title="Transport vid fasta installationer" value="70"<c:if test="${model.record.thtrm == '70'}"> selected </c:if> >70</option>
					 				  	<option title="Transport på inre vattenvägar" value="80"<c:if test="${model.record.thtrm == '80'}"> selected </c:if> >80</option>
					 				  	<option title="Egen framdrivning" value="90"<c:if test="${model.record.thtrm == '90'}"> selected </c:if> >90</option>
					 				  	
									</select>
				            	
				            	<div class="text12" style="position: relative;" align="left">
				            	<span style="position:absolute;top:2px; width:250px;" id="thtrm_info" class="popupWithInputText text12"  >
					           			Using the following codes enter the mode of the active means of transport on which the
										goods will leave the territory of the Contracting Party in which the office of departure
										is located.
					           			<ul>
						           			<li>
						           				<b>10</b>&nbsp;Sjötransport
						           			</li>
						           			<li>
						           				<b>12</b>&nbsp;Järnvägsvagn på sjögående fartyg
						           			</li>
						           			<li>
						           				<b>16</b>&nbsp;Vägmotorfordon på sjögående fartyg
						           			</li>
						           			<li>
						           				<b>17</b>&nbsp;Släpvagn eller påhängsvagn på sjögående fartyg
						           			</li>
						           			<li>
						           				<b>18</b>&nbsp;Fartyg för inre vattenvägar på sjögående fartyg
						           			</li>
						           			<li>
						           				<b>20</b>&nbsp;Järnvägstransport
						           			</li>
						           			<li>
						           				<b>23</b>&nbsp;Vägfordon på järnvägsvagn
						           			</li>
						           			<li>
						           				<b>30</b>&nbsp;Landsvägstransport
						           			</li>
						           			<li>
						           				<b>40</b>&nbsp;Flygtransport
						           			</li>
						           			<li>
						           				<b>50</b>&nbsp;Postförsändelse
						           			</li>
						           			<li>
						           				<b>70</b>&nbsp;Transport vid fasta installationer
						           			</li>
						           			<li>
						           				<b>80</b>&nbsp;Transport på inre vattenvägar
						           			</li>
						           			<li>
						           				<b>90</b>&nbsp;Egen framdrivning
						           			</li>
					           			</ul>
								</span>
								</div>
								</td>
								<td class="text">&nbsp;</td>
	        				</tr>
	        					
						</table>
					</td>
				</tr>
				<tr height="15"><td class="text">&nbsp;</td> </tr>
				
				<tr>
					<td width="2">&nbsp;</td>
		 			<td class="text14">
		 				<table align="left" border="0" cellspacing="0" cellpadding="0">
			 				<tr>
				 				<td class="text14">
				 				<img onMouseOver="showPop('19_info');" onMouseOut="hidePop('19_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>19.</b>&nbsp;<font class="text16RedBold" >*</font><span title="thkdc">Container&nbsp;&nbsp;</span>
				 				<div class="text12" style="position: relative;" align="left">
				 				<span style="position:absolute;top:2px; width:250px;" id="19_info" class="popupWithInputText text12"  >
					           		Enter one of the following codes to give the presumed situation at the border of the
									Contracting Party in whose territory the office of departure is located, as known at the
									time the goods are placed under the transit procedure.
				           			<ul>
					           			<li><b>0</b>&nbsp;-&nbsp;Where the goods are not carried in a container</li>
					           			<li><b>1</b>&nbsp;-&nbsp;Where the goods are carried in a container</li>
					           		</ul>
								</span>
								</div>
								</td>
				 				<td class="text14" >
		 							<select class="inputTextMediumBlueMandatoryField" name="thkdc" id="thkdc">
				 						<option value="0"<c:if test="${model.record.thkdc == 0}"> selected </c:if> >0</option>
								  		<option value="1"<c:if test="${model.record.thkdc == 1}"> selected </c:if> >1</option>
								  	</select>
		 						</td>
			 				</tr>
		 				</table>
		 			</td>				 			
		 		</tr>	
				<tr>
					<td width="2">&nbsp;</td>
			 		<td>
			 			<table border="0" cellspacing="1" cellpadding="0">
			 				
							<tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('lastplats_info');" onMouseOut="hidePop('lastplats_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>27.</b>&nbsp;<span title="thlasd/thlas2">Lastningsplats</span>
					            <div class="text12" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="lastplats_info" class="popupWithInputText text12"  >
					           			<ul>
					           				<li>Enter, using the appropriate code where available, the place where the goods are to be
												loaded onto the active means of transport on which they are to cross the border of the
												Contracting Party in whose territory the office of departure is located, as known at the
												time the goods are placed under the transit procedure.
											</li>
					           			</ul>
								</span>
								</div>
								</td>
								<td class="text14">&nbsp;</td>
							</tr>
							<tr>	
							    <td class="text14" >
							    	<input type="text" class="inputTextMediumBlue" name="thlasd" id="thlasd" size="17" maxlength="17" value="${model.record.thlasd}">
							    </td>
							    <td class="text14" >
							    	<input type="text" class="inputTextMediumBlue" name="thlas2" id="thlas2" size="17" maxlength="17" value="${model.record.thlas2}">
							    </td>							    
	        				</tr>	
	        				<tr height="10"><td class="text">&nbsp;</td> </tr>
	        						 			
			 				<tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('autolagringsplats_info');" onMouseOut="hidePop('autolagringsplats_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <span title="thlsd">Auto.lagringsplats</span>
					            <div class="text12" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="autolagringsplats_info" class="popupWithInputText text12"  >
				           			<ul>
				           				<li>...</li>
				           			</ul>
								</span>	
								</div>
								</td>
							    <td class="text" >
							    	<input type="text" class="inputTextMediumBlue" name="thlsd" id="thlsd" size="17" maxlength="17" value="${model.record.thlsd}">
							    </td>
	        				</tr>
	        				
			 				<tr>
					            <td class="text14" align="left"><img onMouseOver="showPop('tullkontor_info');" onMouseOut="hidePop('tullkontor_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>C.</b><font class="text16RedBold" >*</font><span title="thcats">Avgångstullkontor&nbsp;</span>
					            <div class="text12" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="tullkontor_info" class="popupWithInputText text12"  >
					           			<ul>
					           				<li>Avgångstullkontor är det tullkontor där godset lämnar gemenskapens tullområde<br/><br/></li>
					           				<li>Du anger avgångstullkontoret med en kod. Koden består av åtta tecken. De två första anger landet med hjälp av landkod. De följande sex tecknen anger det berörda kontoret i detta land.<br/><br/>
					           					Exempel: Det svenska tullkontoret Malmö har koden SE000050 som är en NCTS-kod.
					           				</li>
					           				
					           			</ul>
								</span>	
					            </div>
					            </td>
							    <td class="text">
							    <input type="text" class="inputTextMediumBlueMandatoryField" name="thcats" id="thcats" size="9" maxlength="8" value='${model.record.thcats}'>
							    <img id="imgUtfartstullKontor" style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" onClick="showPop('searchTullkontorDialog10');">
					            
							    <%-- ======================================================== --%>
				            	<%-- Here we have the search Tullkontor popup window --%>
				            	<%-- ======================================================== --%>
				            	<span style="position:absolute; left:550px; top:620px; width:300px; height:212px;" id="searchTullkontorDialog10" class="popupWithInputText"  >
					           		<div class="text10" align="left">
					           			<table>
					           				<tr>
												<td class="text12">&nbsp;Tullkontor</td>
												<td class="text12">&nbsp;<input type="text" class="inputText" name="search_sveh_utfa" id="search_sveh_utfa" size="18" maxlength="8" value=""></td>
											</tr>
						           			<tr>
												<td class="text12">&nbsp;Kod</td>
												<td class="text12">&nbsp;<input type="text" class="inputText" name="search_sveh_utfa_Code" id="search_sveh_utfa_Code" size="18" maxlength="35" value=""></td>
											</tr>
						           			<tr>
						           				<td class="text12">&nbsp;</td>
							           			<td align="right">&nbsp;<button name="searchTullkontor10" id="searchTullkontor10" class="buttonGray" type="button" onClick="searchAvgangTullkontorOwnWindow()">Sök</button></td>
							           		</tr>
							           		<tr height="4"><td ></td></tr>
							           		<tr>
						           				<td class="text12">&nbsp;Urval</td>
							           			<td>&nbsp;</td>
							           		</tr>
							           		<tr>
												<td colspan="2">&nbsp;
													<select class="selectMediumBlueE2"  id="tullkontorListAvgang" name="tullkontorListAvgang" size="3" onDblClick="hidePop('searchTullkontorDialog10');">
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
							    
					        </tr>
					        <tr height="2"><td>&nbsp;</td></tr>
					        <tr>
					            <td class="text14" align="left">
					            <img onMouseOver="showPop('sprakkod_foljedok_info');" onMouseOut="hidePop('sprakkod_foljedok_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <font class="text16RedBold" >*</font><span title="thskfd">Språkkod följedok.&nbsp;</span>
					            <div class="text12" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="sprakkod_foljedok_info" class="popupWithInputText text12"  >
					           			<ul>
					           				<li>Följdokument (t.ex. varulista)
					           				</li>
					           			</ul>
								</span>	
								</div>
								</td>
					            <td align="left">
	            					<select class="inputTextMediumBlueMandatoryField" name="thskfd" id="thskfd">
			            				<option value="">-Välj-</option>
			 				  			<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
                               	 			<option value="${code.tkkode}"<c:if test="${model.record.thskfd == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
										</c:forEach> 
								</select>
			            		<a tabindex="-1" id="thskfdIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
								</a>
								
								</td> 
							</tr>
							<tr height="2"><td>&nbsp;</td></tr>
							<tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('transnr_dt_info');" onMouseOut="hidePop('transnr_dt_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <span title="thtrnr">Transiteringsnr.</span>
					            <div class="text12" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="transnr_dt_info" class="popupWithInputText text12"  >
					           			<ul>
					           				<li>Transiteringsnr för godkännade
					           				</li>
					           			</ul>	
								</span>	
					            </div>
					            </td>
					            <td >
					            	<input type="text" class="inputTextMediumBlue" name="thtrnr" id="thtrnr" size="17" maxlength="18" value="${model.record.thtrnr}">&nbsp;&nbsp;
					            </td>
					        </tr>
					        <tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('transnr_dt_info');" onMouseOut="hidePop('transnr_dt_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <span title="thtrdt">Transiteringsdatum</span>
					            <div class="text12" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="transnr_dt_info" class="popupWithInputText text12"  >
					           			<ul>
					           				<li>Transiteringsdatum för godkännade</li>
					           			</ul>	
								</span>	
					            </div>
					            </td>
					            
					            <td class="text14" >
					            	<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="thtrdt" id="thtrdt" size="8" maxlength="8" value="${model.record.thtrdt}">
					            	&nbsp;
					            </td>
					        </tr>
					        
					        <tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('deklarantplats_info');" onMouseOut="hidePop('deklarantplats_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <font class="text16RedBold" >*</font><span title="thdst">Deklarantens plats</span>
					            <div class="text12" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="deklarantplats_info" class="popupWithInputText text12"  >
					           			<ul>
					           				<li>Plats (inklusive landkod)</li>
					           			</ul>	
								</span>	
					            </div>
					            </td>
					           
					            <td class="text14" align="left" >
					            	<input type="text" class="inputTextMediumBlueMandatoryField" name="thdst" id="thdst" size="15" maxlength="15" value="${model.record.thdst}">
					            	&nbsp;<font class="text16RedBold" >*</font><span title="thdsk">Dekl.språk</span>
					            	<select class="inputTextMediumBlueMandatoryField" name="thdsk" id="thdsk">
			            				<option value="">-Välj-</option>
			 				  			<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
                               	 			<option value="${code.tkkode}"<c:if test="${model.record.thdsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
										</c:forEach> 
									</select>
				            		<a tabindex="-1" id="thdskIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
					            </td>
					        </tr>
					        <tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('deklarant_info');" onMouseOut="hidePop('deklarant_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <span title="thtarf">Deklarant</span>
					            <div class="text12" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="deklarant_info" class="popupWithInputText text12"  >
					           			Fältet är spärrat. Hämtas automatiskt.
								</span>
								</div>
								</td>
					            <td colspan="3" class="text14" align="left" >
					            		<input readonly type="text" class="inputTextReadOnly" name="thtarf" id="thtarf" size="25" value="${model.record.thtarf}">
					            </td>
					        </tr>
					        
							<tr height="15"><td>&nbsp;</td></tr>	 
								    
					        <tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('6_info');" onMouseOut="hidePop('6_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>6.&nbsp;</b><span title="thntk">Kollital</span>
					            <div class="text12" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="6_info" class="popupWithInputText text12"  >
					           			<ul>
					           				<li>The total number of packages (pallets, cartons, coils, etc.) making up the consignments
												in question shall be entered here
					           				</li>
					           			</ul>
					           			Fältet är spärrat. Beräknas automatiskt från varulinjerna.
								</span>	
								</div>
								</td>
					            <td ><input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="thntk" id="thntk" size="8" maxlength="7" value="${model.record.thntk}"></td>
					        </tr>
				            <tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('bruttovikt_info');" onMouseOut="hidePop('bruttovikt_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>35.</b>
					            <span title="thvkb">Bruttovikt</span>
					            <div class="text12" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="bruttovikt_info" class="popupWithInputText text12"  >
					           			<ul>
					           				<li>Sändningens totala bruttovikt. 
					           				</li>
					           				
					           			</ul>
					           			Fältet är spärrat. Beräknas automatiskt från varulinjerna.
								</span>	
					            </div>
					            </td>
					            <td ><input onKeyPress="return amountKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="thvkb" id="thvkb" size="10" maxlength="9" value="${model.record.thvkb}"></td>
					        </tr>
					     	<tr height="2"><td>&nbsp;</td></tr>
					     	
					     	<tr>
					        		<td class="text14Gray" align="left" >
					        			Antal kolli (i varuposterna)&nbsp;
					        		</td>
						        	<td >
					            		<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="sumOfAntalKolliInItemLines" id="sumOfAntalKolliInItemLines" size="8" maxlength="7" value="${ model.record.sumOfAntalKolliInItemLinesStr}">
					            		<c:if test="${not empty ( model.record.sumOfAntalKolliInItemLinesStr &&  model.record.thntk)}">
						            		<c:if test="${ model.record.thntk !=  model.record.sumOfAntalKolliInItemLinesStr}">
								            <img id="imgRedFlagAntalKolliInItems" onMouseOver="showPop('itemsSumKolli_info');" onMouseOut="hidePop('itemsSumKolli_info');" width="18px" height="20px" src="resources/images/redFlag.png" border="0" alt="kolliantal warning">	
								            <div class="text11" style="position: relative;" align="left">
									            <span style="position:absolute; left:10px; top:0px;" id="itemsSumKolli_info" class="popupWithInputText"  >
								           			<font class="text11">
								           			<p>
								           			Summan av antal paket på varulinjenivå stämmer inte med det angivna summan antal paket per huvudpost.
								           			Kontrollera varulinjerna.
													</p>
								           			<p>
								           			Om nummret till vänster är = <b>-1</b> betyder det att det är mer än 0-varulinjer, och summan av varelinjer kolliantal är = 0 (som inte är korrekt).
								           			</p>
								           			</font>
												</span>	
											</div>
						            		</c:if>
					            		</c:if>
					            </td>
					        </tr>
					         
					        <tr>
				        		<td class="text14Gray" align="left" >Antal varuposter &nbsp;</td>
						        	<td >
					            		<input readonly style="text-align: right" type="text" class="inputTextReadOnly" name="sumOfAntalItemLines" id="sumOfAntalItemLines" size="8" value="${ model.record.sumOfAntalItemLinesStr}">
					            		<c:if test="${not empty ( model.record.sumOfAntalItemLinesStr)}">
						            		<c:if test="${ model.record.sumOfAntalItemLines <= 0 }">
								            <img onMouseOver="showPop('itemsSumAntalLines_info');" onMouseOut="hidePop('itemsSumAntalLines_info');" width="18px" height="20px" src="resources/images/redFlag.png" border="0" alt="varelinjerantal warning">	
								            <div class="text11" style="position: relative;" align="left">
									            <span style="position:absolute; left:10px; top:0px;" id="itemsSumAntalLines_info" class="popupWithInputText"  >
								           			<font class="text11" >Summan av ​​antal varulinjer måste vara > 0</font>
												</span>
											</div>	
						            		</c:if>
					            		</c:if>
					            </td>
					        </tr>
					     	<tr height="10"><td>&nbsp;</td></tr>
					     	<tr>
					            <td class="text14" align="left">
					            <img onMouseOver="showPop('kontrollresultat_info');" onMouseOut="hidePop('kontrollresultat_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
 								<b>D.</b>&nbsp;<span title="thdkr">Kontrollresultat.&nbsp;</span>
 								<div class="text12" style="position: relative;" align="left">
 								<span style="position:absolute;top:2px; width:250px;" id="kontrollresultat_info" class="popupWithInputText text12"  >
				           			Förenklad förfarande
				           			<ul>
					           			<li><b>A3</b>&nbsp; för förenklad förfarande</li>
					           		</ul>
					           		<br>
					           		För normal förfarandet lämnas detta blankt.
					           	</span>
					           	</div>
								</td>
					           	<td class="text14" align="left">
					           		<select class="selectMediumBlueE2" name="thdkr" id="thdkr" >
					 				  <option value="">-Välj-</option>
									  <option value="A3"<c:if test="${model.record.thdkr == 'A3'}"> selected </c:if> >A3</option>													  
									</select>
					           		
					           	</td>
				           	</tr>
				           	<tr>
					            <td class="text14" align="left">
					            <img onMouseOver="showPop('frist_info');" onMouseOut="hidePop('frist_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
 								<b>D.</b>&nbsp;<span title="thddt">Frist&nbsp;</span>
 								<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute;top:2px; width:250px;" id="frist_info" class="popupWithInputText text11"  >
				           				Frist	
				           			<br/>
				           			<ul>
					           			<li>...</li>
					           		</ul>
								</span>
								</div>
								</td>
					           	<td class="text14" align="left">
					           		<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="thddt" id="thddt" size="10" maxlength="8" value="${model.record.thddt}">
					           		&nbsp;
					           	</td>
				           	</tr>
						</table>
					</td>
				</tr>
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
			            		<td class="text9BlueGreen" valign="bottom" align="left" >
			 				    	<%-- only status = M or emtpy status is allowed --%>
				 				    <c:choose>
					 				    <c:when test="${ model.record.thst == 'G' ||  model.status=='F' || model.record.thst == 'M' || empty model.record.thst}">
						 				    	<input tabindex=-1 class="inputFormSubmit" type="submit" name="submit2" id="submit2" onclick="javascript: form.action='nctsexport_edit.do';" value='<spring:message code="systema.ncts.export.createnew.submit"/>'/>
						 				    	&nbsp;&nbsp;
						 				    	<c:if test="${not empty model.record.thtdn && model.record.validUpdate}">
						 				    		<input tabindex=-2 class="inputFormSubmit" type="submit" name="send2" id="send2" onclick="javascript: form.action='nctsexport_send.do';" value='<spring:message code="systema.ncts.export.createnew.send"/>'/>
						 				    	</c:if>
					 				    </c:when>
					 				    <c:otherwise>
					 				    		<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit2" value='Ej uppdaterbart'/>
					 				    </c:otherwise>	
				 				    </c:choose>
	                				</td>
					        </tr>
					        <tr height="25"><td colspan="2">&nbsp;</td></tr>
					        
					        <c:if test="${model.record.warrantyAlarm}">
					        <tr>
						        <td colspan="2" >&nbsp;&nbsp;
						            	<table style="border-color:red;border-width: 2px 2px 2px 2px;" class="tableBorderWithRoundCorners" align="left" border="0" cellspacing="2" cellpadding="1">
								    		<tr>
									    		<td class="text14" colspan="2">
									            <img width="18px" height="20px" src="resources/images/redFlag.png" border="0" alt="emergency">	
										    		<b>Använt garantibelopp</b>&nbsp;<font class="text14RedBold">över</font>&nbsp;<b>alarmgräns på</b>&nbsp; 
										    		<b>${model.record.tgprm}&nbsp;%</b>
										    		<ul class="text14">
										    			<li>
										    				Totalt garantibelopp är: <font class="text14GreenBold">${model.record.tggbl}</font>
										    			</li>
										    			<li>
										    				Totalt använt garantibelopp (inklusive detta ärende) är: <font class="text14RedBold">${model.record.tggblb}</font>
										    			</li>
										    		</ul>
								    			</td>	
								    		</tr>
								    		 	   
						        		</table>
						        </td>
					        </tr>
					        </c:if>
					        
						</table>
					</td>
				</tr>
				
			</table>
		</td>
		</tr>

		<tr height="20"><td colspan="2">&nbsp;</td></tr>
		
		<tr>
		    <td colspan="2">		
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
				<table style="width:90%;" border="0" cellspacing="0" cellpadding="0">
					<tr>
			 			<td width="5">&nbsp;</td>
			            <td >		
			 				<%-- ANSVARIG --%>
			 				<table width="100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="18px">
						 			<td class="text14White">
						 				&nbsp;
						 				<img onMouseOver="showPop('14_info');" onMouseOut="hidePop('14_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						
				 						<b>&nbsp;50.</b><font class="text16RedBold" >*</font>Ansvarig&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 						<div class="text12" style="position: relative;" align="left">
						 				<span style="position:absolute;top:2px; width:250px;" id="14_info" class="popupWithInputText text12"  >
						           			Här ska du alltid ange uppgifter för deklarant/ansvarig . 
						           			<ul>
							           			<li>The principal’s name (full name of the person or company) and full address shall be
													entered as well as the identification number, if any, allocated by the competent
													authorities. 
													<br/><br/>If appropriate, the full name (person or company) of the authorised
													representative who signs on behalf of the principal shall be entered.
													Subject to any specific provisions on the use of computerised systems, the original of
													the handwritten signature of the person concerned must appear on the SAD copy no.1,
													which is to be kept at the office of departure. If this is a legal person, the signatory
													shall add after his signature his full name and the capacity in which he is signing.
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
									        	<input type="hidden" name="orig_sveh_dkkn" id="orig_sveh_dkkn" value='${dkkn}'>
									        	<input type="hidden" name="orig_thnaa" id="orig_thnaa" value='${model.record.thnaa}'>
									        	<input type="hidden" name="orig_thtina" id="orig_thtina" value='${model.record.thtina}'>
									        	<input type="hidden" name="orig_thada1" id="orig_thada1" value='${model.record.thada1}'>
									        	<input type="hidden" name="orig_thpna" id="orig_thpna" value='${model.record.thpna}'>
									        	<input type="hidden" name="orig_thpsa" id="orig_thpsa" value='${model.record.thpsa}'>
									        	<input type="hidden" name="orig_thlka" id="orig_thlka" value='${model.record.thlka}'>
									        	<input type="hidden" name="orig_thska" id="orig_thska" value='${model.record.thska}'>
						        				<td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="thtina">TIN</span></td>
									            <td class="text14" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="thnaa">Namn</span>
										            <a tabindex="-1" id="thnaaIdLink">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
													</a>
									            </td>
									            
									        </tr>
									        <tr>
									        		<td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="thtina" id="thtina" size="20" maxlength="17" value="${model.record.thtina}"></td>
									            <td align="left"><input type="text" class="inputTextMediumBlueMandatoryField" name="thnaa" id="thnaa" size="30" maxlength="35" value="${model.record.thnaa}"></td>
									            
									        </tr>

									        <tr height="4"><td>&nbsp;</td></tr>
									        <tr>
									            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thada1">Adress 1</span></td>
									            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thska">Språkkod</span></td>
									        </tr>
									        <tr>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="thada1" id="thada1" size="30" maxlength="35" value="${model.record.thada1}"></td>
									            <td class="text14" align="left" >
									            	<select class="selectMediumBlueE2" name="thska" id="thska">
										            		<option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
						                                	 	<option value="${code.tkkode}"<c:if test="${model.record.thska == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
															</c:forEach> 
													</select>
													<a tabindex="-1" id="thskaIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
													</a>
												</td>
									        </tr>
									        <tr>
									        		<td>
										        		<table>
										        		<tr>
										            		<td class="text14" align="left" >&nbsp;&nbsp;<span title="thpsa">Postadress</span></td>
										            		<td align="left">&nbsp;</td>
										            	</tr>
										        		<tr>
										            		<td align="left">
										       				<input type="text" class="inputTextMediumBlue" name="thpsa" id="thpsa" size="30" maxlength="35" value="${model.record.thpsa}">
									            			</td> 
										            		<td align="left">&nbsp;</td>
										        		</tr>    	
										            	</table>
									            </td>
									            <td >
										            	<table>
										        		<tr>
										        			<td class="text14" align="left" >&nbsp;&nbsp;<span title="thpna">Postnummer</span></td>
										            		<td class="text14" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thlka">Land</span></td>
										            	</tr>
										        		<tr >
										        			<td align="left"><input type="text" class="inputTextMediumBlue" name="thpna" id="thpna" size="10" maxlength="9" value="${model.record.thpna}"></td> 
										            		<td align="left">
										            			<select class="inputTextMediumBlueMandatoryField" name="thlka" id="thlka">
												            		<option value="">-Välj-</option>
												 				  	<c:forEach var="country" items="${model.countryCodeList}" >
								                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thlka == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
																	</c:forEach> 
																</select>
																<a tabindex="-1" id="thlkaIdLink" OnClick="triggerChildWindowCountryCodes(this)">
																	<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
																</a>
										            		</td> 
										        		</tr>  
										            	</table>
									            </td>
								            	</tr>
									        
									        <tr height="15">
							            		<td class="text12Bold" align="left" >&nbsp;</td>
							            		<td class="text12Bold" align="left" >&nbsp;</td> 
									        </tr>  
									        
								        </table>
							        </td>
						        </tr>
							</table>          
		            	</td>
		           	</tr> 
		           	<tr height="10"><td></td></tr>
		           	
		      
						 	
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
			                	<tr>
						 			<td class="text14" >
						 				<table align="left" border="0" cellspacing="2" cellpadding="0">
						 				
						 					<tr>
									            <td class="text14" align="left">
									            <img onMouseOver="showPop('forsegling_info');" onMouseOut="hidePop('forsegling_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 								&nbsp;<span title="thdfkd">Förseglingsid&nbsp;</span>
				 								<div class="text12" style="position: relative;" align="left">
				 								<span style="position:absolute;top:2px; width:250px;" id="forsegling_info" class="popupWithInputText text12"  >
													Vid Försegling måste man ange samtliga värden (Antal/kod/språk) eller inga.
												</span>
												</div>
												</td>
									           	<td class="text14" align="left">
									           		<input type="text" class="inputTextMediumBlue" name="thdfkd" id="thdfkd" size="20" maxlength="20" value="${model.record.thdfkd}">
									           	</td>
								           	</tr>
								           	<tr>
									            <td class="text14" align="left">&nbsp;&nbsp;&nbsp;&nbsp;<span title="thdant">Förseglingsantal&nbsp;</span></td>
				 								<td class="text14" align="left">
									           		<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="thdant" id="thdant" size="4" maxlength="4" value="${model.record.thdant}">
									           	</td>
								           	</tr>
								           	<tr>
									            <td class="text14" align="left">&nbsp;&nbsp;&nbsp;&nbsp;<span title="thdfsk">Förseglingsspråkkod</span></td>
				 								
									           	<td class="text14" align="left">
									           		<select class="selectMediumBlueE2" name="thdfsk" id="thdfsk">
							            				<option value="">-Välj-</option>
							 				  			<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
				                               	 			<option value="${code.tkkode}"<c:if test="${model.record.thdfsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
														</c:forEach> 
													</select>
									           		&nbsp;
					            					
									           		
									           	</td>
								           	</tr>
								           	<tr height="10"><td></td></tr>
								           	<tr>
									            <td class="text14" align="left">
									            <img onMouseOver="showPop('kontrolltullplats_info');" onMouseOut="hidePop('kontrolltullplats_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 								&nbsp;<span title="thdats/thddtk">Kontroll tullplats och datum&nbsp;</span>
				 								<div class="text12" style="position: relative;" align="left">
				 								<span style="position:absolute;top:2px; width:250px;" id="kontrolltullplats_info" class="popupWithInputText text12"  >
									           		<ul>
									           			<li>?</li>
									           		</ul>
												</span>
												</div>
												</td>
												
									           	<td class="text14" align="left">
									           		<input readonly type="text" class="inputTextReadOnly" name="thdats" id="thdats" size="8" maxlength="8" value="${model.record.thdats}">
									           		<input readonly type="text" class="inputTextReadOnly" name="thddtk" id="thddtk" size="8" maxlength="8" value="${model.record.thddtk}">
									           		&nbsp;
									           	</td>
								           	</tr>
						 				
							 				<tr>
								 				<td nowrap class="text14">
								 				<img onMouseOver="showPop('returadress_info');" onMouseOut="hidePop('returadress_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									 			&nbsp;<span title="thtsn1/thtsn2/thtslk">Returadress&nbsp;</span>
									 			<div class="text12" style="position: relative;" align="left">
								 				<span style="position:absolute;top:2px; width:250px;" id="returadress_info" class="popupWithInputText text12"  >
									           			Tullverkets returadress
												</span>
												</div>
												</td>
						 			
								 				<td class="text14">
								 					<input readonly type="text" class="inputTextReadOnly" name="thtsn1" id="thtsn1" size="20" maxlength="35" value="${model.record.thtsn1}">
								 				</td>
							 				</tr>
							 				<tr>
								 				<td class="text14">&nbsp;</td>
					 							<td class="text14">
								 					<input readonly type="text" class="inputTextReadOnly" name="thtsn2" id="thtsn2" size="20" maxlength="35" value="${model.record.thtsn2}">
								 				</td>
							 				</tr>
							 				<tr>
								 				<td nowrap class="text14">&nbsp;</td>
					 							<td class="text14">
								 					<input readonly type="text" class="inputTextMediumBlue" name="thtslk" id="thtslk" size="2" maxlength="2" value="${model.record.thtslk}">
								 				</td>
							 				</tr>
						 				</table>
						 			</td>
						 		</tr>
						 		</table>
							</td>
						</tr>
					</table>
				 </td>
			</tr>
			
			<tr>
				<td colspan="2">&nbsp;&nbsp;
		            	<table width="99%" align="center" class="tableBorderWithRoundCornersDarkRed" border="0" cellspacing="2" cellpadding="0">
		            		<tr>
							<td width="50%" align="left" valign="top" class="text14" align="left" >
								<table width="99%" align="left" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="18px">
							 			<td class="text14">
							 				&nbsp;<img onMouseOver="showPop('sikkerhed_info');" onMouseOut="hidePop('sikkerhed_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									        	<b>Säkerhet</b>&nbsp;
									        <div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="sikkerhed_info" class="popupWithInputText text11"  >
													TODO
												</span>
											</div>
						 				</td>
					 				</tr>
					 				<tr>
					 					
					            			<td class="text14">&nbsp;<span title="thsik">
					            				<img onMouseOver="showPop('sikkerhedIndicator_info');" onMouseOut="hidePop('sikkerhedIndicator_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            				Säkerhetsindikator</span>&nbsp;
					            				
											
					            				<select class="selectMediumBlueE2" name="thsik" id="thsik" >
							 				  <option value=""<c:if test="${model.record.thsik == '0' || empty model.record.thsik }"> selected </c:if> >0</option>
											  <option value="1"<c:if test="${model.record.thsik == '1'}"> selected </c:if> >1</option>
											</select>
											&nbsp;&nbsp;<span title="thdta">Ank.dato</span>&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="thdta" id="thdta" size="9" maxlength="8" value="${model.record.thdta}">
											&nbsp;<span title="thtma">Tid</span>&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="thtma" id="thtma" size="5" maxlength="4" value="${model.record.thtma}">
											
											<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="sikkerhedIndicator_info" class="popupWithInputText text11"  >
												Indikator for specifik omständighet (0 eller 1)	
											</span>
											</div>	
										</td>
					            		</tr>
					            		<tr>
					            			<td class="text14">&nbsp;<span title="thkref">Kommersiellt ref.nr</span></td>
					            		</tr>
					            		<tr>
					            			<td><input type="text" class="inputTextMediumBlue" name="thkref" id="thkref" size="70" maxlength="70" value="${model.record.thkref}"></td>
					            		</tr>
					            		<tr>
					            			<td class="text14">&nbsp;<span title="thtref">Transportör ref.nr</span></td>
					            		</tr>
					            		<tr>
										<td><input type="text" class="inputTextMediumBlue" name="thtref" id="thtref" size="70" maxlength="35" value="${model.record.thtref}"></td>
					            		</tr>
					        		</table>
					        </td>
				            <td width="50%" align="left" valign="top" class="text14" align="left" >   
				            		<table width="99%" align="left" border="0" cellspacing="0" cellpadding="0">
				            			<tr height="18px">
					            			<td class="text14">&nbsp;</td>
					            		</tr>
		            					<tr>
					            			<td class="text14">
					            				<img onMouseOver="showPop('spesOmstand_info');" onMouseOut="hidePop('spesOmstand_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            				<span title="thspom">Spec.omständighet</span>&nbsp;
					            				<select class="selectMediumBlueE2" name="thspom" id="thspom">
					            				<option value="">-välj-</option>
					 				  			<c:forEach var="code" items="${model.ncts096_SpecOmst_CodeList}" >
	                             	 				<option value="${code.tkkode}"<c:if test="${model.record.thspom == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
											</select>
											<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="spesOmstand_info" class="popupWithInputText text11"  >
								           			<ul>
								           				<c:forEach var="code" items="${model.ncts096_SpecOmst_CodeList}" >
									 				  		<li><b>${code.tkkode}</b>&nbsp;${code.tktxtn}</li>
								 				  		</c:forEach>	
								           			</ul>
											</span>
											</div>
				            				</td>
					            			<td class="text14">
					            				<img onMouseOver="showPop('betalmade_info');" onMouseOut="hidePop('betalmade_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            				<span title="thtkbm">Transp.kost/Betal.sätt</span>&nbsp;
					            				<select class="selectMediumBlueE2" name="thtkbm" id="thtkbm">
					            				<option value="">-välj-</option>
					 				  			<c:forEach var="code" items="${model.ncts116_BetalningTransport_CodeList}" >
	                             	 				<option value="${code.tkkode}"<c:if test="${model.record.thtkbm == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
											</select>
											<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="betalmade_info" class="popupWithInputText text11"  >
								           			<ul>
								           				<c:forEach var="code" items="${model.ncts116_BetalningTransport_CodeList}" >
									 				  		<li><b>${code.tkkode}</b>&nbsp;${code.tktxtn}</li>
								 				  		</c:forEach>	
								           			</ul>
											</span>
											</div>
					            			</td>
					            		</tr>
					            		<tr>
					            			<td class="text14">&nbsp;&nbsp;<span title="thlosd">Lossplats</span>
					            				<input type="text" class="inputTextMediumBlue" name="thlosd" id="thlosd" size="30" maxlength="35" value="${model.record.thlosd}">
					            			</td>
					            			<td class="text14">&nbsp;&nbsp;<span title="thlosdsk">Lossplats spåkkod</span>
					            				<select class="selectMediumBlueE2" name="thlosdsk" id="thlosdsk">
					            				<option value="">-välj-</option>
					 				  			<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
				                             	 			<option value="${code.tkkode}"<c:if test="${model.record.thlosdsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
											</select>
											<a tabindex="-1" id="thlosdskIdLink"  OnClick="triggerChildWindowLanguageCodes(this)">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>
					            			</td>
					            		</tr>
					            		<tr>
					            			<td class="text14">&nbsp;
					            				<table>
					            				<tr>
					            					<td colspan="4" class="text14">&nbsp;
					            					<span title="thlkr1-8">Landkod för resplan</span>
					            					<a tabindex="-1" id="thlkr1-8"  OnClick="triggerChildWindowCountryCodes(this)">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
													</a>
					            					</td>
					            				</tr>
					            				<tr>
					            				<td class="text14">1.
					            				<select class="selectMediumBlueE2" name="thlkr1" id="thlkr1">
								            		<option value="">-välj-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thlkr1 == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
													</c:forEach> 
												</select>
												</td>
												<td class="text14">&nbsp;&nbsp;2.
					            				<select class="selectMediumBlueE2" name="thlkr2" id="thlkr2">
								            		<option value="">-välj-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thlkr2 == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
													</c:forEach> 
												</select>
												</td>
												<td class="text14">&nbsp;&nbsp;3.
					            				<select class="selectMediumBlueE2" name="thlkr3" id="thlkr3">
								            		<option value="">-välj-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thlkr3 == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</c:forEach> 
												</select>
												</td>
												<td class="text14">&nbsp;&nbsp;4.
												<select class="selectMediumBlueE2" name="thlkr4" id="thlkr4">
								            		<option value="">-välj-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thlkr4 == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
													</c:forEach> 
												</select>
					            				</td>
					            				</tr>
					            				<tr>	
					            				<td class="text14">5.
						            				<select class="selectMediumBlueE2" name="thlkr5" id="thlkr5">
								            		<option value="">-välj-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thlkr5 == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
													</c:forEach> 
												</select>
					            				</td>
					            				<td class="text14">&nbsp;&nbsp;6.
					            				<select class="selectMediumBlueE2" name="thlkr6" id="thlkr6">
								            		<option value="">-välj-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thlkr6 == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
													</c:forEach> 
												</select>
						            				</td>
					            				<td class="text14">&nbsp;&nbsp;7.
					            				<select class="selectMediumBlueE2" name="thlkr7" id="thlkr7">
								            		<option value="">-välj-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thlkr7 == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
													</c:forEach> 
												</select>
												</td>
												<td class="text14">&nbsp;&nbsp;8.
					            				<select class="selectMediumBlueE2" name="thlkr8" id="thlkr8">
								            		<option value="">-välj-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thlkr8 == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
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
		            		<tr height="3"><td>&nbsp;</td></tr>
				        	<tr>
				        		<td width="50%" align="center" valign="top">
								<table width="99%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="18px">
							 			<td class="text14White">
							 				&nbsp;
					 						&nbsp;Avsändare - Säkerhet&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						 				</td>
					 				</tr>
			 					</table>
			 					<table width="99%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
							 		<tr>	
							 			<td width="5">&nbsp;</td>
							            <td >	
											<%-- create record --%>
										 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
										 		<tr>
											 		<td>
												 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
											 				<%-- ================================================================================== --%>
													        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
													        		 These original values will be used when the user clicks "Cancel" buttons (puttting
													        		 back original value)																--%> 
													        	<%-- ================================================================================== --%>
													        	<input type="hidden" name="orig_thknss" id="orig_thknss" value='${model.record.thknss}'>
													        	<input type="hidden" name="orig_thnass" id="orig_thnass" value='${model.record.thnass}'>
													        	<input type="hidden" name="orig_thtinss" id="orig_thtinss" value='${model.record.thtinss}'>
													        	<input type="hidden" name="orig_thadss1" id="orig_thadss1" value='${model.record.thadss1}'>
													        	<input type="hidden" name="orig_thpnss" id="orig_thpnss" value='${model.record.thpnss}'>
													        	<input type="hidden" name="orig_thpsss" id="orig_thpsss" value='${model.record.thpsss}'>
													        	<input type="hidden" name="orig_thlkss" id="orig_thlkss" value='${model.record.thlkss}'>
													        	<input type="hidden" name="orig_thskss" id="orig_thskss" value='${model.record.thskss}'>
													        	
													        	<tr height="10">
													            <td class="text14" align="left">&nbsp;</td> 
													        </tr>
													 		<tr height="15">
													            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thknss">Kundnummer</span></td>
													            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thnass">Namn</span>
													            	<a tabindex="-1" id="thnassIdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
																	</a>
											            		</td>
													        </tr>
													        <tr>
													        		<td align="left"><input type="text" class="inputTextMediumBlue" name="thknss" id="thknss" size="10" maxlength="8" value="${model.record.thknss}"></td>
													            <td align="left"><input type="text" class="inputTextMediumBlue" name="thnass" id="thnass" size="30" maxlength="35" value="${model.record.thnass}"></td>
													        </tr>
													        <tr>
											        				<td class="text14" align="left" >&nbsp;&nbsp;<span title="thtinss">TIN</span></td>
													        </tr>
													        <tr>
													        		<td align="left"><input type="text" class="inputTextMediumBlue" name="thtinss" id="thtinss" size="20" maxlength="17" value="${model.record.thtinss}"></td>
													        </tr>
				
													        <tr height="4"><td>&nbsp;</td></tr>
													        <tr>
													            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thadss1">Adress</span></td>
													            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thskss">Språkkod</span>
												            		
													            </td>
													        </tr>
													        <tr>
													            <td align="left"><input type="text" class="inputTextMediumBlue" name="thadss1" id="thadss1" size="30" maxlength="30" value="${model.record.thadss1}"></td>
													            <td class="text14" align="left" >
													            		&nbsp;<select class="selectMediumBlueE2" name="thskss" id="thskss">
														            		<option value="">-välj-</option>
														 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
										                                	 	<option value="${code.tkkode}"<c:if test="${model.record.thskss == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																			</c:forEach> 
																	</select>
																	<a tabindex="-1" id="thskssIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
																	</a>
													            
																</td>
													        </tr>
													        <tr>
													        		<td>
														        		<table>
														        		<tr>
														            		<td class="text14" align="left" >&nbsp;&nbsp;<span title="thpsss">Postadress</span></td>
														            		<td align="left">&nbsp;</td>
														            	</tr>
														        		<tr>
														            		<td align="left">
														       				<input type="text" class="inputTextMediumBlue" name="thpsss" id="thpsss" size="30" maxlength="24" value="${model.record.thpsss}">
													            			</td> 
														            		<td align="left">&nbsp;</td>
														        		</tr>    	
														            	</table>
													            </td>
													            <td >
														            	<table>
														        		<tr>
														        			<td class="text14" align="left" >&nbsp;&nbsp;<span title="thpnss">Postnummer</span></td>
														            		<td class="text14" align="left" >&nbsp;<span title="thlkss">Land</span>
														            		
														            		</td>
														            	</tr>
														        		<tr >
														        			<td align="left"><input type="text" class="inputTextMediumBlue" name="thpnss" id="thpnss" size="10" maxlength="8" value="${model.record.thpnss}"></td> 
														            		<td align="left">
														            			<select class="selectMediumBlueE2" name="thlkss" id="thlkss">
																            		<option value="">-välj-</option>
															 				  	<c:forEach var="country" items="${model.countryCodeList}" >
											                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thlkss == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
																				</c:forEach> 
																			</select>
																			<a id="thlkssIdLink" OnClick="triggerChildWindowCountryCodes(this)">
														            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
														            		</a>
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
		 						</table>
			 					<table width="99%" align="center" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="5"><td>&nbsp;</td></tr>
			 					</table>	
								<table width="99%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="18px">
							 			<td class="text14White">
							 				&nbsp;
					 						&nbsp;Transportör - Säkerhet&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						 				</td>
					 				</tr>
			 					</table>
			 					<table width="99%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
							 		<tr>	
							 			<td width="5">&nbsp;</td>
							            <td >	
											<%-- create record --%>
										 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
										 		<tr>
											 		<td>
												 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
											 				<%-- ================================================================================== --%>
													        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
													        		 These original values will be used when the user clicks "Cancel" buttons (puttting
													        		 back original value)																--%> 
													        	<%-- ================================================================================== --%>
													        	<input type="hidden" name="orig_thknt" id="orig_thknt" value='${model.record.thknt}'>
													        	<input type="hidden" name="orig_thnat" id="orig_thnat" value='${model.record.thnat}'>
													        	<input type="hidden" name="orig_thtint" id="orig_thtint" value='${model.record.thtint}'>
													        	<input type="hidden" name="orig_thadt1" id="orig_thadt1" value='${model.record.thadt1}'>
													        	<input type="hidden" name="orig_thpnt" id="orig_thpnt" value='${model.record.thpnt}'>
													        	<input type="hidden" name="orig_thpst" id="orig_thpst" value='${model.record.thpst}'>
													        	<input type="hidden" name="orig_thlkt" id="orig_thlkt" value='${model.record.thlkt}'>
													        	<input type="hidden" name="orig_thskt" id="orig_thskt" value='${model.record.thskt}'>
													        	
													        	<tr height="10">
													            <td class="text14" align="left">&nbsp;</td> 
													        </tr>
													 		<tr height="15">
													            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thknt">Kundnummer</span></td>
													            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thnat">Namn</span>
													            	<a tabindex="-1" id="thnatIdLink">
																	<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
																	</a>
											            		</td>
													        </tr>
													        <tr>
													        		<td align="left"><input type="text" class="inputTextMediumBlue" name="thknt" id="thknt" size="10" maxlength="8" value="${model.record.thknt}"></td>
													            <td align="left"><input type="text" class="inputTextMediumBlue" name="thnat" id="thnat" size="30" maxlength="35" value="${model.record.thnat}"></td>
													        </tr>
													        <tr>
											        				<td class="text14" align="left" >&nbsp;&nbsp;<span title="thtint">TIN</span></td>
													        </tr>
													        <tr>
													        		<td align="left"><input type="text" class="inputTextMediumBlue" name="thtint" id="thtint" size="20" maxlength="17" value="${model.record.thtint}"></td>
													        </tr>
				
													        <tr height="4"><td>&nbsp;</td></tr>
													        <tr>
													            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thadt1">Adress</span></td>
													            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thskt">Språkkod</span>
												            		
													            
													            </td>
													        </tr>
													        <tr>
													            <td align="left"><input type="text" class="inputTextMediumBlue" name="thadt1" id="thadt1" size="30" maxlength="30" value="${model.record.thadt1}"></td>
													            <td class="text14" align="left" >
													            		&nbsp;<select class="selectMediumBlueE2" name="thskt" id="thskt">
														            		<option value="">-välj-</option>
														 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
										                                	 	<option value="${code.tkkode}"<c:if test="${model.record.thskt == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																			</c:forEach> 
																	</select>
																	<a tabindex="-1" id="thsktIdLink"  OnClick="triggerChildWindowLanguageCodes(this)">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
																	</a>
																</td>
													        </tr>
													        <tr>
													        		<td>
														        		<table>
														        		<tr>
														            		<td class="text14" align="left" >&nbsp;&nbsp;<span title="thpst">Postadress</span></td>
														            		<td align="left">&nbsp;</td>
														            	</tr>
														        		<tr>
														            		<td align="left">
														       				<input type="text" class="inputTextMediumBlue" name="thpst" id="thpst" size="30" maxlength="24" value="${model.record.thpst}">
													            			</td> 
														            		<td align="left">&nbsp;</td>
														        		</tr>    	
														            	</table>
													            </td>
													            <td >
														            	<table>
														        		<tr>
														        			<td class="text14" align="left" >&nbsp;&nbsp;<span title="thpnt">Postnummer</span></td>
														            		<td class="text14" align="left" >&nbsp;<span title="thlkt">Land</span>
														            		
														            		</td>
														            	</tr>
														        		<tr >
														        			<td align="left"><input type="text" class="inputTextMediumBlue" name="thpnt" id="thpnt" size="10" maxlength="8" value="${model.record.thpnt}"></td> 
														            		<td align="left">
														            			<select class="selectMediumBlueE2" name="thlkt" id="thlkt">
																            		<option value="">-välj-</option>
															 				  	<c:forEach var="country" items="${model.countryCodeList}" >
											                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thlkt == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
																				</c:forEach> 
																			</select>
																			<a id="thlktIdLink" OnClick="triggerChildWindowCountryCodes(this)">
															            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
														            		</a>
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
		 						</table>
			        			</td>
			        			<td width="50%" align="center" valign="top">
								<table width="99%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="18px">
							 			<td class="text14White">
							 				&nbsp;
					 						&nbsp;Mottagare - Säkerhet&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						 				</td>
					 				</tr>
			 					</table>
			 					<table width="99%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
							 		<tr>	
							 			<td width="5">&nbsp;</td>
							            <td >	
											<%-- create record --%>
										 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
										 		<tr>
											 		<td>
												 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
													 		<%-- ================================================================================== --%>
													        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
													        		 These original values will be used when the user clicks "Cancel" buttons (puttting
													        		 back original value)																--%> 
													        	<%-- ================================================================================== --%>
													        	<input type="hidden" name="orig_thknks" id="orig_thknks" value='${model.record.thknks}'>
													        	<input type="hidden" name="orig_thnaks" id="orig_thnaks" value='${model.record.thnaks}'>
													        	<input type="hidden" name="orig_thtinks" id="orig_thtinks" value='${model.record.thtinks}'>
													        	<input type="hidden" name="orig_thadks1" id="orig_thadks1" value='${model.record.thadks1}'>
													        	<input type="hidden" name="orig_thpnks" id="orig_thpnks" value='${model.record.thpnks}'>
													        	<input type="hidden" name="orig_thpsks" id="orig_thpsks" value='${model.record.thpsks}'>
													        	<input type="hidden" name="orig_thlkks" id="orig_thlkks" value='${model.record.thlkks}'>
													        	<input type="hidden" name="orig_thskks" id="orig_thskks" value='${model.record.thskks}'>
													 		<tr height="10">
													            <td class="text14" align="left">&nbsp;</td> 
													        </tr>
													        <tr height="15">
													            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thknks">Kundnummer</span></td>
													            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thnaks">Namn</span>
													            	<a tabindex="-1" id="thnaksIdLink">
																	<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
																	</a>	
													            </td>
													        </tr>
													        <tr>
													        		<td align="left"><input type="text" class="inputTextMediumBlue" name="thknks" id="thknks" size="10" maxlength="8" value="${model.record.thknks}"></td>
													            <td align="left"><input type="text" class="inputTextMediumBlue" name="thnaks" id="thnaks" size="30" maxlength="35" value="${model.record.thnaks}"></td>
													        </tr>
													        <tr>
											        				<td class="text14" align="left" >&nbsp;&nbsp;<span title="thtinks">EORI</span></td>
													        </tr>
													        <tr>
													        		<td align="left"><input type="text" class="inputTextMediumBlue" name="thtinks" id="thtinks" size="20" maxlength="17" value="${model.record.thtinks}"></td>
													        </tr>
				
													        <tr height="4"><td>&nbsp;</td></tr>
													        <tr>
													            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thadks1">Adress</span></td>
													            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thskks">Språkkod</span>
												            		
													            </td>
													        </tr>
													        <tr>
													            <td align="left"><input type="text" class="inputTextMediumBlue" name="thadks1" id="thadks1" size="30" maxlength="30" value="${model.record.thadks1}"></td>
													            <td class="text14" align="left" >
													            		&nbsp;<select class="selectMediumBlueE2" name="thskks" id="thskks">
														            		<option value="">-välj-</option>
														 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
										                                	 	<option value="${code.tkkode}"<c:if test="${model.record.thskks == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																			</c:forEach> 
																	</select>
																	<a tabindex="-1" id="thskksIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
																	</a>
																</td>
													        </tr>
													        <tr>
													        		<td>
														        		<table>
														        		<tr>
														            		<td class="text14" align="left" >&nbsp;&nbsp;<span title="thpsks">Postadress</span></td>
														            		<td align="left">&nbsp;</td>
														            	</tr>
														        		<tr>
														            		<td align="left">
														       				<input type="text" class="inputTextMediumBlue" name="thpsks" id="thpsks" size="30" maxlength="24" value="${model.record.thpsks}">
													            			</td> 
														            		<td align="left">&nbsp;</td>
														        		</tr>    	
														            	</table>
													            </td>
													            <td >
														            	<table>
														        		<tr>
														        			<td class="text14" align="left" >&nbsp;&nbsp;<span title="thpnks">Postnummer</span></td>
														            		<td class="text14" align="left" >&nbsp;<span title="thlkks">Land</span>
														            		
														            		</td>
														            	</tr>
														        		<tr >
														        			<td align="left"><input type="text" class="inputTextMediumBlue" name="thpnks" id="thpnks" size="10" maxlength="8" value="${model.record.thpnks}"></td> 
														            		<td align="left">
														            			<select class="selectMediumBlueE2" name="thlkks" id="thlkks">
																            		<option value="">-välj-</option>
															 				  	<c:forEach var="country" items="${model.countryCodeList}" >
											                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thlkks == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
																				</c:forEach> 
																			</select>
																			<a id="thlkksIdLink" OnClick="triggerChildWindowCountryCodes(this)">
															            			<img style="cursor:pointer;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
														            		</a>
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
		 						</table>
			        			</td>
				        	</tr>
				        	<tr height="10"><td>&nbsp;</td></tr>
		            	</table>
		            </td>  
		            <td>&nbsp;</td>
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
			<form action="nctsexport_updateStatus.do" name="updateStatusForm" id="updateStatusForm" method="post">
			 	<input type="hidden" name="currentAvd" id="currentAvd" value="${model.record.thavd}">
			 	<input type="hidden" name="currentOpd" id="currentOpd" value="${model.record.thtdn}">
			 		
				<p class="text14" >Change status as needed.</p>
				<table>
					<tr>
						<td class="text14" align="left" >&nbsp;Status</td>
						<td class="text14MediumBlue">
							<select class="selectMediumBlueE2" name="selectedStatus" id="selectedStatus">
			            			<option value=" ">-vælg-</option>
				            		<option value="A">A</option>
				            		<option value="C">C</option>
				            		<option value="D">D</option>
				            		<option value="E">E</option>
				            		<option value="F">F</option>
				            		<option value="G">G</option>
				            		<option value="K">K</option>
				            		<option value="M">M</option>
				            		<option value="N">N</option>
				            		<option value="P">P</option>
				            		<option value="Q">Q</option>
				            		<option value="R">R</option>
				            		<option value="S">S</option>
				            		<option value="U">U</option>
				            		<option value="V">V</option>
				            		<option value="W">W</option>
				            		<option value="Y">Y</option>
				            		<option value="Z">Z</option>
			            			
							</select>
							
						</td>
					</tr>
				</table>
			</form>
		</div>
	</td>
</tr> 
 
	