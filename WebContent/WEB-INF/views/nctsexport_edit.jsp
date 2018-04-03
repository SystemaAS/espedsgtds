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
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="nctsexport.do?action=doFind&sign=${model.record.thsg}">
					<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.ncts.export.list.tab"/></font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<c:choose> 
			    <c:when test="${editActionOnTopic=='doUpdate' or editActionOnTopic=='doFetch'}">
					<td width="15%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">
							&nbsp;<spring:message code="systema.ncts.export.created.mastertopic.tab"/>
						</font>
						<font class="text12MediumBlue">[${model.record.thtdn}]</font>
						<c:if test="${ model.record.thst == 'G' ||  model.status=='F' || model.record.thst == 'M' || empty model.record.thst}">
							<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						</c:if>
						
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a tabindex=-1 style="display:block;" href="nctsexport_edit_items.do?action=doFetch&avd=${model.record.thavd}&sign=${model.record.thsg}
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
						<a tabindex=-1 style="display:block;" href="nctsexport_logging.do?avd=${model.record.thavd}&sign=${model.record.thsg}
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
						<a tabindex=-1 style="display:block;" href="nctsexport_archive.do?avd=${model.record.thavd}&sign=${model.record.thsg}
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
 		<tr height="3"><td colspan="2">&nbsp;</td></tr>
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
			<input type="hidden" name="thsg" id="thsg" value='${model.record.thsg}'>
			<input type="hidden" name="thst" id="thst" value='${model.record.thst}'>
			<input type="hidden" name="thdt" id="thdt" value='${model.record.thdt}'>
			<input type="hidden" name="thtuid" id="thtuid" value='${model.record.thtuid}'>
			<input type="hidden" name="lrnNr" id="lrnNr" value='${model.record.thtuid}'>
	    		<input type="hidden" name="avd" id="avd" value='${model.record.thavd}'>
			<input type="hidden" name="sign" id="sign" value='${model.record.thsg}'>
			
			<tr >
				<td align="left" class="text12MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;Avd:&nbsp;${model.record.thavd}&nbsp;&nbsp;<span title="thtdn">Uppd:</span>&nbsp;<b>${model.record.thtdn}</b>
					&nbsp;&nbsp;<span title="thtuid">LRN-nr:</span>&nbsp;<b>${model.record.thtuid}</b>&nbsp;&nbsp;&nbsp;<span title="thtrnr">MRN-nr:</span>&nbsp;<b>${model.record.thtrnr}</b>&nbsp;&nbsp;
				</td>
				<td align="right" >
					<c:if test="${'1' != isTestAvd}">
						<%--This checkbox appears only in real production. Otherwise use the Testavdelning --%>
						<input type="checkbox" name="th0035" id="th0035" value="1" <c:if test="${model.record.th0035 == '1'}"> checked </c:if> ><font class="text12MediumBlue"><b>TEST flag</b></font>&nbsp;&nbsp;&nbsp;						
					</c:if>
					<a href="ncts_export_edit_printTopic.do?avd=${model.record.thavd}&opd=${model.record.thtdn}">
					 	<img style="cursor:pointer;" src="resources/images/printer.png" width="30" hight="30" border="0" alt="Print">
						&nbsp;&nbsp;&nbsp;
					</a>
				</td>
			</tr>
			<%-- test indicator /per avdelning --%> 
			<c:if test="${'1' == isTestAvd}">
				<tr>
					<td align="left" class="text14Red" >
						&nbsp;&nbsp;<b>[TEST Avdelning]</b>
					</td>
				</tr>
			</c:if>
			<tr >
				<td align="left" class="text11MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span title="thsg">Sign:</span>&nbsp;<b>${model.record.thsg}</b>,&nbsp;&nbsp;<span title="thdt">Datum:</span>&nbsp;<b>${model.record.thdt}</b>,
					&nbsp;&nbsp;<span title="thst">Stat<a id="updateStatusLink" runat="server" href="#"><font class="text11MediumBlue">u</font></a>s:</span>&nbsp;${model.record.thst}
					&nbsp;&nbsp;
					<font class="text16RedBold" >*</font><span title="thenkl">Typ av förfarande</span>&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thsg) --%>
           			<select class="text11" name="thenkl" id="thenkl">
	            		<option value="J"<c:if test="${model.record.thenkl == 'J'}"> selected </c:if> >Förenklad</option>
					  	<option value="N"<c:if test="${model.record.thenkl == 'N'}"> selected </c:if> >Normal</option>
								   
					</select>
					 
					<%--
					&nbsp;&nbsp;Typ:
					<c:choose>
					    <c:when test="${model.record.thenkl=='J'}">
					    	&nbsp;<b>Förenklad</b>
					    </c:when>
					    <c:otherwise>
					    	&nbsp;<b>Normal</b>
					    </c:otherwise>
				    </c:choose>
				    &nbsp;]
				     --%>
				</td>
				<td align="left" class="text12MediumBlue">&nbsp;</td>
			</tr>	
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
				<td align="left" class="text12MediumBlue">
					&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;<font class="text16RedBold" >*</font>Avd:&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thavd) --%>
           			<select name="avd" id="avd">
	            		<option value="">-Välj-</option>
	 				  	<c:forEach var="record" items="${model.avdList}" >
                        	 	<option value="${record.avd}"<c:if test="${model.record.thavd == record.avd}"> selected </c:if> >${record.avd}<c:if test="${record.tst == '1'}">&nbsp;(test)</c:if></option>
						</c:forEach> 
					</select>
					&nbsp;
					<font class="text16RedBold" >*</font><span title="thsg">Sign:</span>&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thsg) --%>
           			<select name="sign" id="sign">
	            		<option value="">-Välj-</option>
	 				  	<c:forEach var="record" items="${model.signList}" >
                             	 	<option value="${record.sign}"<c:if test="${model.record.thsg == record.sign}"> selected </c:if> >${record.sign}</option>
						</c:forEach> 
					</select>
					<font class="text16RedBold" >*</font><span title="thenkl">Typ av förfarande</span>&nbsp;
					<%-- Must be model attribute in order to validate towards the filter (thsg) --%>
           			<select name="thenkl" id="thenkl">
	            		<option value="J"<c:if test="${model.record.thenkl == 'J'}"> selected </c:if> >Förenklad</option>
					  	<option value="N"<c:if test="${model.record.thenkl == 'N'}"> selected </c:if> >Normal</option>
								   
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
		<td width="55%">
		<table border="0" cellspacing="1" cellpadding="0">
			<tr>
	            <td width="5">&nbsp;</td>
	            <td >
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
	            		
				 		<tr>
				 			<td class="text12">
				 				<img onMouseOver="showPop('deklTyp_info');" onMouseOut="hidePop('deklTyp_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>1</b><font class="text16RedBold" >&nbsp;*</font><span title="thdk">Deklaration&nbsp;</span>
			 					<div class="text11" style="position: relative;" align="left">
			 					<span style="position:absolute;top:2px; width:250px;" id="deklTyp_info" class="popupWithInputText text11"  > 
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
				 				<select name="thdk" id="thdk" TABINDEX=1>
				 				  <option value="">-Välj-</option>
				 				  	<c:forEach var="code" items="${model.ncts031_DeklType_CodeList}" >
                                	 	<option value="${code.tkkode}"<c:if test="${model.record.thdk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
									</c:forEach> 
								</select>
			 				</td>
			 				<td class="text12">
			 					&nbsp;<img onMouseOver="showPop('4_info');" onMouseOut="hidePop('4_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>4.</b><span title="thlstl">Lastspec.&nbsp;</span>
			 					<div class="text11" style="position: relative;" align="left">
			 					<span style="position:absolute;top:2px; width:250px;" id="4_info" class="popupWithInputText text11"  >
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
			 				
			 				<td class="text12">&nbsp;&nbsp;
			 					&nbsp;<img onMouseOver="showPop('5_info');" onMouseOut="hidePop('5_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>5.</b><span title="thvpos">V.poster&nbsp;</span>
			 					<div class="text11" style="position: relative;" align="left">
			 					<span style="position:absolute;top:2px; width:250px;" id="5_info" class="popupWithInputText text11"  >
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
				 			<td class="text12White">
								&nbsp;<img onMouseOver="showPop('2_info');" onMouseOut="hidePop('2_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 			<b>&nbsp;2.</b><font class="text16RedBold" >*</font>Avsändare&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 				<div class="text11" style="position: relative;" align="left">
				 				<span style="position:absolute;top:2px; width:250px;" id="2_info" class="popupWithInputText text11"  >
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
							        	
							        	
							            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thkns">Kundnummer</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thnas">Namn&nbsp;</span>
							            	<img id="imgCustomerSearch" style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" onClick="showPop('searchCustomerDialog');">
							            	<%-- =====================================================  --%>
							            	<%-- Here we have the search Sender [Customer] popup window --%>
							            	<%-- =====================================================  --%>
							            	<span style="position:absolute; left:500px; top:280px; width:300px; height:212px;" id="searchCustomerDialog" class="popupWithInputText"  >
								           		<div class="text10" align="left">
								           			<table>
								           				<tr>
															<td class="text11">&nbsp;Kundnummer</td>
															<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_avkn" id="search_sveh_avkn" size="18" maxlength="8" value=""></td>
														</tr>
									           			<tr>
															<td class="text11">&nbsp;Namn</td>
															<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_avna" id="search_sveh_avna" size="18" maxlength="35" value=""></td>
														</tr>
									           			<tr>
									           				<td class="text11">&nbsp;</td>
										           			<td align="right">&nbsp;<button name="searchCustomer" class="buttonGray" type="button" onClick="searchSenderOwnWindow()">Sök</button></td>
										           		</tr>
										           		<tr height="4"><td ></td></tr>
										           		<tr>
									           				<td class="text11">&nbsp;Urval</td>
										           			<td>&nbsp;</td>
										           		</tr>
										           		<tr>
															<td colspan="2">&nbsp;
																<%-- check jQuery events (onChange) for this list --%>
																<select class="text11" id="senderList" name="senderList" size="3" onDblClick="hidePop('searchCustomerDialog');">
				 													<option value="">-Välj-</option>
				 							 					</select>
															</td>
															
															<%-- <input type="hidden" name="hidden_sveh_avkn" id="hidden_sveh_avkn" value=''> --%>
														</tr>
								           			</table>
													<table width="30%" align="left" border="0">
														<tr align="left" >
															<td >&nbsp;<button name="searchCustomerCloseOk" id="searchCustomerCloseOk" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchCustomerDialog');">Ok</button></td>
															<td >&nbsp;<button name="searchCustomerCloseCancel" id="searchCustomerCloseCancel" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchCustomerDialog');">Avbryt</button></td>
														</tr>
													</table>
												</div>
											</span>				
							            </td>
							        </tr>
							        <tr>
							            <td class="text12" align="left"><input type="text" class="inputTextMediumBlue" name="thkns" id="thkns" size="8" maxlength="8" value="${model.record.thkns}"></td>
							            <td class="text12" align="left"><input type="text" class="inputTextMediumBlue" name="thnas" id="thnas" size="30" maxlength="35" value="${model.record.thnas}"></td>
							            
							        </tr>
							        
							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thtins">TIN</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="thtins" id="thtins" size="20" maxlength="17" value="${model.record.thtins}"></td>
							            <td align="left">&nbsp;</td>
							        </tr>
							        <tr height="4"><td>&nbsp;</td></tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;<span title="thads1">Adress 1</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thsks">Språkkod</span>
						            		<a tabindex="-1" class="text14" target="_blank" href="${model.isoLanguageCodesURL.value}" onclick="${model.isoLanguageCodesURL.windowOpenDimensions}" >
						            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
						            		</a>
							            
							            </td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="thads1" id="thads1" size="30" maxlength="35" value="${model.record.thads1}"></td>
							            <td class="text12" align="left" >
							            		&nbsp;<select name="thsks" id="thsks">
								            		<option value="">-Välj-</option>
								 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
				                                	 	<option value="${code.tkkode}"<c:if test="${model.record.thsks == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
													</c:forEach> 
											</select>
										</td>
							        </tr>
							        <tr>
							        		<td>
								        		<table>
								        		<tr>
								            		<td class="text12" align="left" >&nbsp;<span title="thpss">Postadress</span></td>
								            		<td align="left">&nbsp;</td>
								            	</tr>
								        		<tr>
								            		<td align="left">
								       				<input type="text" class="inputTextMediumBlue" name="thpss" id="thpss" size="30" maxlength="35" value="${model.record.thpss}">
							            			</td> 
								            		<td align="left">&nbsp;</td>
								        		</tr>    	
								            	</table>
							            </td>
							            <td >
								            	<table>
								        		<tr>
								        			<td class="text12" align="left" >&nbsp;&nbsp;<span title="thpns">Postnummer</span></td>
								            		<td class="text12" align="left" >&nbsp;&nbsp;<span title="thlks">Land</span>
								            		<a tabindex="-1" class="text14" target="_blank" href="${model.taricLandCodesURL.value}" onclick="${model.taricLandCodesURL.windowOpenDimensions}" >
								            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
								            		</a>
								            		</td>
								            	</tr>
								        		<tr >
								        			<td align="left"><input type="text" class="inputTextMediumBlue" name="thpns" id="thpns" size="10" maxlength="9" value="${model.record.thpns}"></td> 
								            		<td align="left">
								            			<select name="thlks" id="thlks">
										            		<option value="">-Välj-</option>
									 				  	<c:forEach var="country" items="${model.countryCodeList}" >
					                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thlks == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
														</c:forEach> 
													</select>
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
				 			<td class="text12White">
				 				&nbsp;<img onMouseOver="showPop('8_info');" onMouseOut="hidePop('8_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>&nbsp;8.</b>Mottagare&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 				<div class="text11" style="position: relative;" align="left">
				 				<span style="position:absolute;top:2px; width:250px;" id="8_info" class="popupWithInputText text11"  >
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
							        	
							            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thknk">Kundnummer</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thnak">Namn&nbsp;</span>
							            <img id="imgReceiverSearch" style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" onClick="showPop('searchCustomerDialog10');">
							            	<%-- ======================================================== --%>
							            	<%-- Here we have the search Receiver [Customer] popup window --%>
							            	<%-- ======================================================== --%>
							            	<span style="position:absolute; left:500px; top:550px; width:300px; height:212px;" id="searchCustomerDialog10" class="popupWithInputText"  >
								           		<div class="text10" align="left">
								           			<table>
								           				<tr>
															<td class="text11">&nbsp;Kundnummer</td>
															<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_mokn" id="search_sveh_mokn" size="18" maxlength="8" value=""></td>
														</tr>
									           			<tr>
															<td class="text11">&nbsp;Namn</td>
															<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_mona" id="search_sveh_mona" size="18" maxlength="35" value=""></td>
														</tr>
									           			<tr>
									           				<td class="text11">&nbsp;</td>
										           			<td align="right">&nbsp;<button name="searchCustomer10" class="buttonGray" type="button" onClick="searchReceiverOwnWindow()">Sök</button></td>
										           		</tr>
										           		<tr height="4"><td ></td></tr>
										           		<tr>
									           				<td class="text11">&nbsp;Urval</td>
										           			<td>&nbsp;</td>
										           		</tr>
										           		<tr>
															<td colspan="2">&nbsp;
																<select class="text11" id="receiverList" name="receiverList" size="3" onChange="setReceiver();" onDblClick="hidePop('searchCustomerDialog10');">
				 													<option selected value="">-Välj-</option>
				 							 					</select>
															</td>
															
															<%-- <input type="hidden" name="hidden_sveh_avkn" id="hidden_sveh_avkn" value=''> --%>
														</tr>
								           			</table>
													<table width="30%" align="left" border="0">
														<tr align="left" >
															<td >&nbsp;<button name="searchCustomer10CloseOk" id="searchCustomer10CloseOk" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchCustomerDialog10');">Ok</button></td>
															<td >&nbsp;<button name="searchCustomer10CloseCancel" id="searchCustomer10CloseCancel" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchCustomerDialog10');">Avbryt</button></td>
														</tr>
													</table>
												</div>
											</span>	
										</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="thknk" id="thknk" size="8" maxlength="8" value="${model.record.thknk}"></td>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="thnak" id="thnak" size="30" maxlength="35" value="${model.record.thnak}"></td>
							        </tr>

							        <tr>
							            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thtink">TIN</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="thtink" id="thtink" size="20" maxlength="17" value="${model.record.thtink}"></td>
							            <td align="left">&nbsp;</td>
							        </tr>
							        <tr height="4"><td>&nbsp;</td></tr>
							        <tr>
							            <td class="text12" align="left" >&nbsp;<span title="thadk1">Adress 1</span></td>
							            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thskk">Språkkod</span>
						            		<a tabindex="-1" class="text14" target="_blank" href="${model.isoLanguageCodesURL.value}" onclick="${model.isoLanguageCodesURL.windowOpenDimensions}" >
						            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
						            		</a>
							            </td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="thadk1" id="thadk1" size="30" maxlength="35" value="${model.record.thadk1}"></td>
							            <td class="text12" align="left" >
							            		&nbsp;<select name="thskk" id="thskk">
								            		<option value="">-Välj-</option>
								 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
				                                	 	<option value="${code.tkkode}"<c:if test="${model.record.thskk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
													</c:forEach> 
											</select>
										</td>
							        </tr>
							        <tr>
							        		<td>
								        		<table>
								        		<tr>
								            		<td class="text12" align="left" >&nbsp;<span title="thpsk">Postadress</span></td>
								            		<td align="left">&nbsp;</td>
								            	</tr>
								        		<tr>
								            		<td align="left">
								       				<input type="text" class="inputTextMediumBlue" name="thpsk" id="thpsk" size="30" maxlength="35" value="${model.record.thpsk}">
							            			</td> 
								            		<td align="left">&nbsp;</td>
								        		</tr>    	
								            	</table>
							            </td>
							            <td >
								            	<table>
								        		<tr>
								        			<td class="text12" align="left" >&nbsp;&nbsp;<span title="thpnk">Postnummer</span></td>
								            		<td class="text12" align="left" >&nbsp;&nbsp;<span title="thlkk">Land</span>
								            		<a tabindex="-1" class="text14" target="_blank" href="${model.taricLandCodesURL.value}" onclick="${model.taricLandCodesURL.windowOpenDimensions}" >
								            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
								            		</a>
								            		
								            		</td>
								            	</tr>
								        		<tr >
								        			<td align="left"><input type="text" class="inputTextMediumBlue" name="thpnk" id="thpnk" size="10" maxlength="9" value="${model.record.thpnk}"></td> 
								            		<td align="left">
								            			<select name="thlkk" id="thlkk">
										            		<option value="">-Välj-</option>
									 				  	<c:forEach var="country" items="${model.countryCodeList}" >
					                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thlkk == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
														</c:forEach> 
													</select>
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
	                <table align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
				 			<td class="text12"><font class="text16RedBold" >*</font><b><span title="thgbl">Garantibelopp&nbsp;</span></b></td>
				 			<td align="left" ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="thgbl" id="thgbl" size="20" maxlength="20" value="${model.record.thgbl}"></td>
				 			<td class="text12">&nbsp;<font class="text16RedBold" >*</font><span title="thgvk">Valuta</span>
				 				<%-- Note: onChange event in jQuery for this currency list --%>
				 				<select name="thgvk" id="thgvk" >
				 				  <option value="">-Välj-</option>	
				 				  	<c:forEach var="currency" items="${model.currencyCodeList}" >
                                	 	<option value="${currency.svkd_kd}"<c:if test="${model.record.thgvk == currency.svkd_kd}"> selected </c:if> >${currency.svkd_kd}</option>
									</c:forEach>   
								</select>
								<a tabindex="-1" target="_blank" href="${model.taricCurrencyCodesURL.value}" onclick="${model.taricCurrencyCodesURL.windowOpenDimensions}" >
				            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
				            		</a>								
			 				</td>
		 				</tr>
		 				<%--
		 				<tr>
			 				<td class="text12">&nbsp;Kurs&nbsp;</td>
				 			<td class="text12" align="left" ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="sveh_vaku" id="sveh_vaku" size="20" maxlength="20" value='${vaku}'></td>
				 			
		 				</tr>
		 				 --%>
		 				 
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
				 			<td class="text12White">
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
							        	<td class="text12" align="left" >
							        	&nbsp;<img onMouseOver="showPop('51_info');" onMouseOut="hidePop('51_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							        	<b>51.</b><span title="thtsd1/thtsd2...">Planerade transittullkontor - i ruttordning&nbsp;</span>
							        	<div class="text11" style="position: relative;" align="left">
							        	<span style="position:absolute;top:2px; width:250px;" id="51_info" class="popupWithInputText text11"  >
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
							            <td class="text12" align="left" >&nbsp;</td>
							        </tr>
							        
							        <tr>
							        	<td>&nbsp;&nbsp;
							            	<table align="left" border="0" cellspacing="0" cellpadding="0">
							            		<tr>
							            			
						            			<td>
						            			<input type="text" class="inputTextMediumBlue" name="thtsd1" id="thtsd1" size="10" maxlength="8" value="${model.record.thtsd1}">
							            		<img id="imgTullkontorListTransit01" style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" onClick="showPop('searchTransitTullkontor01_Dialog');">
						            				<%-- ============================================================= --%>
									            	<%-- Here we have the search Transittullkontor 01 popup window   --%>
									            	<%-- ============================================================= --%>
									            	<span style="position:absolute; left:350px; top:880px; width:300px; height:212px;" id="searchTransitTullkontor01_Dialog" class="popupWithInputText"  >
										           		<div class="text10" align="left">
										           			<table>
										           				<tr>
																	<td class="text11">&nbsp;Tullkontor</td>
																	<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_utfa_transit01" id="search_sveh_utfa_transit01" size="18" maxlength="8" value=""></td>
																</tr>
											           			<tr>
																	<td class="text11">&nbsp;Kod</td>
																	<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_utfa_Code_transit01" id="search_sveh_utfa_Code_transit01" size="18" maxlength="35" value=""></td>
																</tr>
											           			<tr>
											           				<td class="text11">&nbsp;</td>
												           			<td align="right">&nbsp;<button name="searchTransitTullkontor01" class="buttonGray" type="button" onClick="searchTransitTullkontor01_OwnWindow()">Sök</button></td>
												           		</tr>
												           		<tr height="4"><td ></td></tr>
												           		<tr>
											           				<td class="text11">&nbsp;Urval</td>
												           			<td>&nbsp;</td>
												           		</tr>
												           		<tr>
																	<td colspan="2">&nbsp;
																		<select class="text11" id="tullkontorListTransit01" name="tullkontorListTransit01" size="3" onDblClick="hidePop('searchTransitTullkontor01_Dialog');">
						 													<option selected value="">-Välj-</option>
						 							 					</select>
																	</td>
																</tr>
										           			</table>
															<table width="100%" align="left" border="0">
																<tr align="left" >
																	<td >&nbsp;<button name="searchTransitTullkontor01_Close" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchTransitTullkontor01_Dialog');">Ok</button></td>
																</tr>
															</table>
														</div>
													</span>
												</td>
													
														
							            			<td>
							            			&nbsp;
							            			&nbsp;
							            			<input type="text" class="inputTextMediumBlue" name="thtsd2" id="thtsd2" size="10" maxlength="8" value='${model.record.thtsd2}'>
							            			<img id="imgTullkontorListTransit02" style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" onClick="showPop('searchTransitTullkontor02_Dialog');">
							            				<%-- ============================================================= --%>
										            	<%-- Here we have the search Transittullkontor 02 popup window   --%>
										            	<%-- ============================================================= --%>
										            	<span style="position:absolute; left:350px; top:880px; width:300px; height:212px;" id="searchTransitTullkontor02_Dialog" class="popupWithInputText"  >
											           		<div class="text10" align="left">
											           			<table>
											           				<tr>
																		<td class="text11">&nbsp;Tullkontor</td>
																		<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_utfa_transit02" id="search_sveh_utfa_transit02" size="18" maxlength="8" value=""></td>
																	</tr>
												           			<tr>
																		<td class="text11">&nbsp;Kod</td>
																		<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_utfa_Code_transit02" id="search_sveh_utfa_Code_transit02" size="18" maxlength="35" value=""></td>
																	</tr>
												           			<tr>
												           				<td class="text11">&nbsp;</td>
													           			<td align="right">&nbsp;<button name="searchTransitTullkontor02" class="buttonGray" type="button" onClick="searchTransitTullkontor02_OwnWindow()">Sök</button></td>
													           		</tr>
													           		<tr height="4"><td ></td></tr>
													           		<tr>
												           				<td class="text11">&nbsp;Urval</td>
													           			<td>&nbsp;</td>
													           		</tr>
													           		<tr>
																		<td colspan="2">&nbsp;
																			<select class="text11" id="tullkontorListTransit02" name="tullkontorListTransit02" size="3" onDblClick="hidePop('searchTransitTullkontor02_Dialog');">
							 													<option selected value="">-Välj-</option>
							 							 					</select>
																		</td>
																	</tr>
											           			</table>
																<table width="100%" align="left" border="0">
																	<tr align="left" >
																		<td >&nbsp;<button name="searchTransitTullkontor02_Close" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchTransitTullkontor02_Dialog');">Ok</button></td>
																	</tr>
																</table>
															</div>
														</span>
							            			</td>
							            			
							            			
							            			<td>
							            			&nbsp;
							            			&nbsp;
							            			<input type="text" class="inputTextMediumBlue" name="thtsd3" id="thtsd3" size="10" maxlength="8" value='${model.record.thtsd3}'>
							            			<img id="imgTullkontorListTransit03" style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" onClick="showPop('searchTransitTullkontor03_Dialog');">
							            				<%-- ============================================================= --%>
										            	<%-- Here we have the search Transittullkontor 03 popup window   --%>
										            	<%-- ============================================================= --%>
										            	<span style="position:absolute; left:350px; top:880px; width:300px; height:212px;" id="searchTransitTullkontor03_Dialog" class="popupWithInputText"  >
											           		<div class="text10" align="left">
											           			<table>
											           				<tr>
																		<td class="text11">&nbsp;Tullkontor</td>
																		<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_utfa_transit03" id="search_sveh_utfa_transit03" size="18" maxlength="8" value=""></td>
																	</tr>
												           			<tr>
																		<td class="text11">&nbsp;Kod</td>
																		<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_utfa_Code_transit03" id="search_sveh_utfa_Code_transit03" size="18" maxlength="35" value=""></td>
																	</tr>
												           			<tr>
												           				<td class="text11">&nbsp;</td>
													           			<td align="right">&nbsp;<button name="searchTransitTullkontor03" class="buttonGray" type="button" onClick="searchTransitTullkontor03_OwnWindow()">Sök</button></td>
													           		</tr>
													           		<tr height="4"><td ></td></tr>
													           		<tr>
												           				<td class="text11">&nbsp;Urval</td>
													           			<td>&nbsp;</td>
													           		</tr>
													           		<tr>
																		<td colspan="2">&nbsp;
																			<select class="text11" id="tullkontorListTransit03" name="tullkontorListTransit03" size="3" onDblClick="hidePop('searchTransitTullkontor03_Dialog');">
							 													<option selected value="">-Välj-</option>
							 							 					</select>
																		</td>
																	</tr>
											           			</table>
																<table width="100%" align="left" border="0">
																	<tr align="left" >
																		<td >&nbsp;<button name="searchTransitTullkontor03_Close" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchTransitTullkontor03_Dialog');">Ok</button></td>
																	</tr>
																</table>
															</div>
														</span>
							            			</td>
							            		</tr>
							            		
							            		
							            		<tr>
							            			<td>
							            			<input type="text" class="inputTextMediumBlue" name="thtsd4" id="thtsd4" size="10" maxlength="8" value='${model.record.thtsd4}'>
							            			<img id="imgTullkontorListTransit04" style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" onClick="showPop('searchTransitTullkontor04_Dialog');">
							            				<%-- ============================================================= --%>
										            	<%-- Here we have the search Transittullkontor 04 popup window   --%>
										            	<%-- ============================================================= --%>
										            	<span style="position:absolute; left:350px; top:880px; width:300px; height:212px;" id="searchTransitTullkontor04_Dialog" class="popupWithInputText"  >
											           		<div class="text10" align="left">
											           			<table>
											           				<tr>
																		<td class="text11">&nbsp;Tullkontor</td>
																		<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_utfa_transit04" id="search_sveh_utfa_transit04" size="18" maxlength="8" value=""></td>
																	</tr>
												           			<tr>
																		<td class="text11">&nbsp;Kod</td>
																		<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_utfa_Code_transit04" id="search_sveh_utfa_Code_transit04" size="18" maxlength="35" value=""></td>
																	</tr>
												           			<tr>
												           				<td class="text11">&nbsp;</td>
													           			<td align="right">&nbsp;<button name="searchTransitTullkontor04" class="buttonGray" type="button" onClick="searchTransitTullkontor04_OwnWindow()">Sök</button></td>
													           		</tr>
													           		<tr height="4"><td ></td></tr>
													           		<tr>
												           				<td class="text11">&nbsp;Urval</td>
													           			<td>&nbsp;</td>
													           		</tr>
													           		<tr>
																		<td colspan="2">&nbsp;
																			<select class="text11" id="tullkontorListTransit04" name="tullkontorListTransit04" size="3" onDblClick="hidePop('searchTransitTullkontor04_Dialog');">
							 													<option selected value="">-Välj-</option>
							 							 					</select>
																		</td>
																	</tr>
											           			</table>
																<table width="100%" align="left" border="0">
																	<tr align="left" >
																		<td >&nbsp;<button name="searchTransitTullkontor04_Close" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchTransitTullkontor04_Dialog');">Ok</button></td>
																	</tr>
																</table>
															</div>
														</span>
							            			</td>
							            			
							            			<td>
							            			&nbsp;
							            			&nbsp;
							            			<input type="text" class="inputTextMediumBlue" name="thtsd5" id="thtsd5" size="10" maxlength="8" value='${model.record.thtsd5}'>
							            			<img id="imgTullkontorListTransit05" style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" onClick="showPop('searchTransitTullkontor05_Dialog');">
							            				<%-- ============================================================= --%>
										            	<%-- Here we have the search Transittullkontor 05 popup window   --%>
										            	<%-- ============================================================= --%>
										            	<span style="position:absolute; left:350px; top:880px; width:300px; height:212px;" id="searchTransitTullkontor05_Dialog" class="popupWithInputText"  >
											           		<div class="text10" align="left">
											           			<table>
											           				<tr>
																		<td class="text11">&nbsp;Tullkontor</td>
																		<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_utfa_transit05" id="search_sveh_utfa_transit05" size="18" maxlength="8" value=""></td>
																	</tr>
												           			<tr>
																		<td class="text11">&nbsp;Kod</td>
																		<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_utfa_Code_transit05" id="search_sveh_utfa_Code_transit05" size="18" maxlength="35" value=""></td>
																	</tr>
												           			<tr>
												           				<td class="text11">&nbsp;</td>
													           			<td align="right">&nbsp;<button name="searchTransitTullkontor05" class="buttonGray" type="button" onClick="searchTransitTullkontor05_OwnWindow()">Sök</button></td>
													           		</tr>
													           		<tr height="4"><td ></td></tr>
													           		<tr>
												           				<td class="text11">&nbsp;Urval</td>
													           			<td>&nbsp;</td>
													           		</tr>
													           		<tr>
																		<td colspan="2">&nbsp;
																			<select class="text11" id="tullkontorListTransit05" name="tullkontorListTransit05" size="3" onDblClick="hidePop('searchTransitTullkontor05_Dialog');">
							 													<option selected value="">-Välj-</option>
							 							 					</select>
																		</td>
																	</tr>
											           			</table>
																<table width="100%" align="left" border="0">
																	<tr align="left" >
																		<td >&nbsp;<button name="searchTransitTullkontor05_Close" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchTransitTullkontor05_Dialog');">Ok</button></td>
																	</tr>
																</table>
															</div>
														</span>
							            			</td>
							            			
							            			<td>
							            			&nbsp;
							            			&nbsp;
							            			<input type="text" class="inputTextMediumBlue" name="thtsd6" id="thtsd6" size="10" maxlength="8" value='${model.record.thtsd6}'>
							            			<img id="imgTullkontorListTransit06" style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" onClick="showPop('searchTransitTullkontor06_Dialog');">
							            				<%-- ============================================================= --%>
										            	<%-- Here we have the search Transittullkontor 06 popup window   --%>
										            	<%-- ============================================================= --%>
										            	<span style="position:absolute; left:350px; top:880px; width:300px; height:212px;" id="searchTransitTullkontor06_Dialog" class="popupWithInputText"  >
											           		<div class="text10" align="left">
											           			<table>
											           				<tr>
																		<td class="text11">&nbsp;Tullkontor</td>
																		<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_utfa_transit06" id="search_sveh_utfa_transit06" size="18" maxlength="8" value=""></td>
																	</tr>
												           			<tr>
																		<td class="text11">&nbsp;Kod</td>
																		<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_utfa_Code_transit06" id="search_sveh_utfa_Code_transit06" size="18" maxlength="35" value=""></td>
																	</tr>
												           			<tr>
												           				<td class="text11">&nbsp;</td>
													           			<td align="right">&nbsp;<button name="searchTransitTullkontor06" class="buttonGray" type="button" onClick="searchTransitTullkontor06_OwnWindow()">Sök</button></td>
													           		</tr>
													           		<tr height="4"><td ></td></tr>
													           		<tr>
												           				<td class="text11">&nbsp;Urval</td>
													           			<td>&nbsp;</td>
													           		</tr>
													           		<tr>
																		<td colspan="2">&nbsp;
																			<select class="text11" id="tullkontorListTransit06" name="tullkontorListTransit06" size="3" onDblClick="hidePop('searchTransitTullkontor06_Dialog');">
							 													<option selected value="">-Välj-</option>
							 							 					</select>
																		</td>
																	</tr>
											           			</table>
																<table width="100%" align="left" border="0">
																	<tr align="left" >
																		<td >&nbsp;<button name="searchTransitTullkontor06_Close" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchTransitTullkontor06_Dialog');">Ok</button></td>
																	</tr>
																</table>
															</div>
														</span>
							            			</td>					            			
							            		</tr>
							            		<tr>
							            			<td>
							            			<input type="text" class="inputTextMediumBlue" name="thtsd7" id="thtsd7" size="10" maxlength="8" value='${model.record.thtsd7}'>
							            			<img  id="imgTullkontorListTransit07" style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" onClick="showPop('searchTransitTullkontor07_Dialog');">
							            				<%-- ============================================================= --%>
										            	<%-- Here we have the search Transittullkontor 07 popup window   --%>
										            	<%-- ============================================================= --%>
										            	<span style="position:absolute; left:350px; top:880px; width:300px; height:212px;" id="searchTransitTullkontor07_Dialog" class="popupWithInputText"  >
											           		<div class="text10" align="left">
											           			<table>
											           				<tr>
																		<td class="text11">&nbsp;Tullkontor</td>
																		<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_utfa_transit07" id="search_sveh_utfa_transit07" size="18" maxlength="8" value=""></td>
																	</tr>
												           			<tr>
																		<td class="text11">&nbsp;Kod</td>
																		<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_utfa_Code_transit07" id="search_sveh_utfa_Code_transit07" size="18" maxlength="35" value=""></td>
																	</tr>
												           			<tr>
												           				<td class="text11">&nbsp;</td>
													           			<td align="right">&nbsp;<button name="searchTransitTullkontor07" class="buttonGray" type="button" onClick="searchTransitTullkontor07_OwnWindow()">Sök</button></td>
													           		</tr>
													           		<tr height="4"><td ></td></tr>
													           		<tr>
												           				<td class="text11">&nbsp;Urval</td>
													           			<td>&nbsp;</td>
													           		</tr>
													           		<tr>
																		<td colspan="2">&nbsp;
																			<select class="text11" id="tullkontorListTransit07" name="tullkontorListTransit07" size="3" onDblClick="hidePop('searchTransitTullkontor07_Dialog');">
							 													<option selected value="">-Välj-</option>
							 							 					</select>
																		</td>
																	</tr>
											           			</table>
																<table width="100%" align="left" border="0">
																	<tr align="left" >
																		<td >&nbsp;<button name="searchTransitTullkontor07_Close" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchTransitTullkontor07_Dialog');">Ok</button></td>
																	</tr>
																</table>
															</div>
														</span>
							            			</td>
							            			
							            			
							            			<td>
							            			&nbsp;
							            			&nbsp;
							            			<input type="text" class="inputTextMediumBlue" name="thtsd8" id="thtsd8" size="10" maxlength="8" value='${model.record.thtsd8}'>
							            			<img id="imgTullkontorListTransit08" style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" onClick="showPop('searchTransitTullkontor08_Dialog');">
							            				<%-- ============================================================= --%>
										            	<%-- Here we have the search Transittullkontor 08 popup window   --%>
										            	<%-- ============================================================= --%>
										            	<span style="position:absolute; left:350px; top:880px; width:300px; height:212px;" id="searchTransitTullkontor08_Dialog" class="popupWithInputText"  >
											           		<div class="text10" align="left">
											           			<table>
											           				<tr>
																		<td class="text11">&nbsp;Tullkontor</td>
																		<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_utfa_transit08" id="search_sveh_utfa_transit08" size="18" maxlength="8" value=""></td>
																	</tr>
												           			<tr>
																		<td class="text11">&nbsp;Kod</td>
																		<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_utfa_Code_transit08" id="search_sveh_utfa_Code_transit08" size="18" maxlength="35" value=""></td>
																	</tr>
												           			<tr>
												           				<td class="text11">&nbsp;</td>
													           			<td align="right">&nbsp;<button name="searchTransitTullkontor08" class="buttonGray" type="button" onClick="searchTransitTullkontor08_OwnWindow()">Sök</button></td>
													           		</tr>
													           		<tr height="4"><td ></td></tr>
													           		<tr>
												           				<td class="text11">&nbsp;Urval</td>
													           			<td>&nbsp;</td>
													           		</tr>
													           		<tr>
																		<td colspan="2">&nbsp;
																			<select class="text11" id="tullkontorListTransit08" name="tullkontorListTransit08" size="3" onDblClick="hidePop('searchTransitTullkontor08_Dialog');">
							 													<option selected value="">-Välj-</option>
							 							 					</select>
																		</td>
																	</tr>
											           			</table>
																<table width="100%" align="left" border="0">
																	<tr align="left" >
																		<td >&nbsp;<button name="searchTransitTullkontor08_Close" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchTransitTullkontor08_Dialog');">Ok</button></td>
																	</tr>
																</table>
															</div>
														</span>
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
								        	<td class="text12" align="left" >
								        	&nbsp;<img onMouseOver="showPop('53_info');" onMouseOut="hidePop('53_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								        	<b>53.</b><font class="text16RedBold" >*</font><span title="thtsb">Bestämmelsetullkontor</span>
								        	<img id="imgBestamstullKontor" style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" onClick="showPop('searchTullkontorDialog11');">
								        	&nbsp;
								        	<div class="text11" style="position: relative;" align="left">
								        	<span style="position:absolute;top:2px; width:250px;" id="53_info" class="popupWithInputText text11"  >
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
								        	
						            		<td ><input type="text" class="inputTextMediumBlue" name="thtsb" id="thtsb" size="10" maxlength="8" value="${model.record.thtsb}">
											<%-- ============================================================= --%>
							            	<%-- Here we have the search BestämmelsesTullkontor popup window   --%>
							            	<%-- ============================================================= --%>
							            	<span style="position:absolute; left:350px; top:880px; width:300px; height:212px;" id="searchTullkontorDialog11" class="popupWithInputText"  >
								           		<div class="text10" align="left">
								           			<table>
								           				<tr>
															<td class="text11">&nbsp;Tullkontor</td>
															<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_utfa_bestam" id="search_sveh_utfa_bestam" size="18" maxlength="8" value=""></td>
														</tr>
									           			<tr>
															<td class="text11">&nbsp;Kod</td>
															<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_utfa_Code_bestam" id="search_sveh_utfa_Code_bestam" size="18" maxlength="35" value=""></td>
														</tr>
									           			<tr>
									           				<td class="text11">&nbsp;</td>
										           			<td align="right">&nbsp;<button name="searchTullkontor11" class="buttonGray" type="button" onClick="searchBestamTullkontorOwnWindow()">Sök</button></td>
										           		</tr>
										           		<tr height="4"><td ></td></tr>
										           		<tr>
									           				<td class="text11">&nbsp;Urval</td>
										           			<td>&nbsp;</td>
										           		</tr>
										           		<tr>
															<td colspan="2">&nbsp;
																<select class="text11" id="tullkontorListBestam" name="tullkontorListBestam" size="3" onDblClick="hidePop('searchTullkontorDialog11');">
				 													<option selected value="">-Välj-</option>
				 							 					</select>
															</td>
															
															
														</tr>
								           			</table>
													<table width="100%" align="left" border="0">
														<tr align="left" >
															<td >&nbsp;<button name="searchTullkontorClose11" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchTullkontorDialog11');">Ok</button></td>
														</tr>
													</table>
												</div>
											</span>									            
								            
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
										        	<td  colspan="3" class="text12" align="left" >
										        	&nbsp;<img onMouseOver="showPop('52_info');" onMouseOut="hidePop('52_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										        	<b>52.</b><font class="text16RedBold" >*</font><b>Säkerhet - Garantikoder</b>&nbsp;
										        	
													<div class="text11" style="position: relative;" align="left">
													<span style="position:absolute;top:2px; width:250px;" id="52_info" class="popupWithInputText text11"  >
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
							            			<td class="text12">&nbsp;<font class="text16RedBold" >*</font><span title="thgkd">Kod</span></td>
							            			<td class="text12">&nbsp;<span title="thgft1">Garantinummer</span></td>
							            			<td class="text12">&nbsp;<span title="thgft2">Annan garanti</span></td>
							            		</tr>
							            		<tr>
							            			<td>
							            				<select name="thgkd" id="thgkd" >
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
							            			<td class="text12">&nbsp;<span title="thgadk">Åtkomstkod</span></td>
							            			<td class="text12">&nbsp;<span title="thgbgi">Begränsning inom EF</span></td>
							            			<td class="text12">&nbsp;<span title="thgbgu">Begränsning utanför [landkod]</span></td>
							            		</tr>
							            		<tr>
							            			<td><input type="text" class="inputTextMediumBlue" name="thgadk" id="thgadk" size="5" maxlength="4" value="${model.record.thgadk}"></td>
							            			<td>
								            			<select name="thgbgi" id="thgbgi" >
									 				  <option value="0"<c:if test="${model.record.thgbgi == '0'}"> selected </c:if> >0</option>
													  <option value="1"<c:if test="${model.record.thgbgi == '1'}"> selected </c:if> >1</option>
													</select>
							            			</td>
							            			<td align="left">
								            			<select name="thgbgu" id="thgbgu">
										            		<option value="">-Välj-</option>
									 				  	<c:forEach var="country" items="${model.countryCodeList}" >
					                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thgbgu == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
														</c:forEach> 
													</select>
									            		<a tabindex="-1" class="text14" target="_blank" href="${model.taricLandCodesURL.value}" onclick="${model.taricLandCodesURL.windowOpenDimensions}" >
									            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
									            		</a>
							            			</td> 
							            		</tr>
							            		<tr height="2"><td></td></tr>
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
		<td width="45%" align="center" valign="top">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="2">&nbsp;</td>
					<td valign="top">
			 			<table border="0" cellspacing="0" cellpadding="0">
					 		<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('15_info');" onMouseOut="hidePop('15_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>15.</b><span title="thalk">Avsändnings-/Utf.land&nbsp;</span>
			 					<div class="text11" style="position: relative;" align="left">
			 					<span style="position:absolute;top:2px; width:250px;" id="15_info" class="popupWithInputText text11"  >
				           			<ul>
				           				<li>The name of the country from which goods are to be dispatched/exported shall be
											entered.	
										</li>
				           			</ul>
								</span>		
					            </div>
					            </td>
					            <td >
					            	<select name="thalk" id="thalk">
				 						<option value="">-Välj-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
	                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thalk == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
										</c:forEach> 
									</select>
					            		<a tabindex="-1" class="text14" target="_blank" href="${model.taricLandCodesURL.value}" onclick="${model.taricLandCodesURL.windowOpenDimensions}" >
					            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
					            		</a>
								</td>
							</tr>
							<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('17_info');" onMouseOut="hidePop('17_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>17.</b><span title="thblk">Bestämmelseland, kod&nbsp;</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="17_info" class="popupWithInputText text11"  >
				           			<ul>
				           				<li>The name of the country of destination shall be entered.<br/><br/></li>
				           				<li>Bestämmelseland betyder det sista kända destinationslandet. 
				           				En omlastning i ett mellanliggande land förändrar inte bestämmelselandet. Bestämmelseland kan aldrig vara EU.</li>
				           			</ul>
								</span>	
					            </div>
					            </td>
					            
					            <td >
					            	<select name="thblk" id="thblk">
				 						<option value="">-Välj-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
	                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thblk == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
										</c:forEach> 
									</select>
					            		<a tabindex="-1" class="text14" target="_blank" href="${model.taricLandCodesURL.value}" onclick="${model.taricLandCodesURL.windowOpenDimensions}" >
					            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
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
					            <td class="text12" align="left" colspan="2" >
					            <img onMouseOver="showPop('18_info');" onMouseOut="hidePop('18_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>18.</b><font class="text16RedBold" >*</font><span title="thtaid">Transportmedlets identitet och nationalitet vid avgången</span>
					            
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="18_info" class="popupWithInputText text11"  >
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
				            		<input type="text" class="inputTextMediumBlue" name="thtaid" id="thtaid" size="25" maxlength="35" value="${model.record.thtaid}">
				            	</td>
				            	<td class="text">&nbsp;</td>
	        				</tr>
	        				<tr>
				            	<td class="text12">
				            		<font class="text16RedBold" >*</font><span title="thtalk">Nationalitet</span>
				            		<a tabindex="-1" class="text14" target="_blank" href="${model.taricLandCodesURL.value}" onclick="${model.taricLandCodesURL.windowOpenDimensions}" >
				            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
				            		</a>
				            		
				            		<select name="thtalk" id="thtalk">
				 						<option value="">-Välj-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
	                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thtalk == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
										</c:forEach> 
									</select>
									
									&nbsp;<span title="thtask">Språk</span>
						            		<a tabindex="-1" class="text14" target="_blank" href="${model.isoLanguageCodesURL.value}" onclick="${model.isoLanguageCodesURL.windowOpenDimensions}" >
						            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
						            		</a>
									<select name="thtask" id="thtask">
			            				<option value="">-Välj-</option>
			 				  			<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
                               	 			<option value="${code.tkkode}"<c:if test="${model.record.thtask == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
										</c:forEach> 
									</select>
				            	</td>
				            	<td class="text">&nbsp;</td>
	        				</tr>
	        				<tr>
				            	<td class="text12">
				            		<img onMouseOver="showPop('thtrmi_info');" onMouseOut="hidePop('thtrmi_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				            		<b>26.</b>
				            		<font class="text16RedBold" >*</font><span title="thtrmi">Transportsätt vid avgången&nbsp;</span>
				            		<select name="thtrmi" id="thtrmi">
				 						<option value="">-Välj-</option>
					 				  	<option value="10"<c:if test="${model.record.thtrmi == '10'}"> selected </c:if> >10</option>
					 				  	<option value="12"<c:if test="${model.record.thtrmi == '12'}"> selected </c:if> >12</option>
					 				  	<option value="16"<c:if test="${model.record.thtrmi == '16'}"> selected </c:if> >16</option>
					 				  	<option value="17"<c:if test="${model.record.thtrmi == '17'}"> selected </c:if> >17</option>
					 				  	<option value="18"<c:if test="${model.record.thtrmi == '18'}"> selected </c:if> >18</option>
					 				  	<option value="20"<c:if test="${model.record.thtrmi == '20'}"> selected </c:if> >20</option>
					 				  	<option value="23"<c:if test="${model.record.thtrmi == '23'}"> selected </c:if> >23</option>
					 				  	<option value="30"<c:if test="${model.record.thtrmi == '30'}"> selected </c:if> >30</option>
					 				  	<option value="40"<c:if test="${model.record.thtrmi == '40'}"> selected </c:if> >40</option>
					 				  	<option value="50"<c:if test="${model.record.thtrmi == '50'}"> selected </c:if> >50</option>
					 				  	<option value="70"<c:if test="${model.record.thtrmi == '70'}"> selected </c:if> >70</option>
					 				  	<option value="80"<c:if test="${model.record.thtrmi == '80'}"> selected </c:if> >80</option>
					 				  	<option value="90"<c:if test="${model.record.thtrmi == '90'}"> selected </c:if> >90</option>
					 				  	
									</select>
				            	
				            	<div class="text11" style="position: relative;" align="left">
				            	<span style="position:absolute;top:2px; width:250px;" id="thtrmi_info" class="popupWithInputText text11"  >
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
					            <td class="text12" align="left">
					            <img onMouseOver="showPop('21_info');" onMouseOut="hidePop('21_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>21.</b>
					            <font class="text16RedBold" >*</font><span title="thtgid">Transportmedlets identitet och nationalitet vid gränspassagen</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="21_info" class="popupWithInputText text11"  >
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
				            	<td class="text12">
				            		<font class="text16RedBold" >*</font><span title="thtglk">Nationalitet</span>
				            		<a tabindex="-1" class="text14" target="_blank" href="${model.taricLandCodesURL.value}" onclick="${model.taricLandCodesURL.windowOpenDimensions}" >
				            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
				            		</a>
				            		
				            		<select name="thtglk" id="thtglk">
				 						<option value="">-Välj-</option>
					 				  	<c:forEach var="country" items="${model.countryCodeList}" >
	                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thtglk == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
										</c:forEach> 
									</select>
									&nbsp;<span title="thtgsk">Språk</span>
						            		<a tabindex="-1" class="text14" target="_blank" href="${model.isoLanguageCodesURL.value}" onclick="${model.isoLanguageCodesURL.windowOpenDimensions}" >
						            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
						            		</a>
									<select name="thtgsk" id="thtgsk">
			            				<option value="">-Välj-</option>
			 				  			<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
                               	 			<option value="${code.tkkode}"<c:if test="${model.record.thtgsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
										</c:forEach> 
									</select>
									
				            	</td>
				            	<td class="text">&nbsp;</td>
	        				</tr>
 		       				<tr>
				            	<td class="text12">
				            		<img onMouseOver="showPop('thtrm_info');" onMouseOut="hidePop('thtrm_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				            		<b>25.</b>
				            		<font class="text16RedBold" >*</font><span title="thtrm">Transportsätt vid gränsen&nbsp;</span>
				            		<select name="thtrm" id="thtrm">
				 						<option value="">-Välj-</option>
					 				  	<option value="10"<c:if test="${model.record.thtrm == '10'}"> selected </c:if> >10</option>
					 				  	<option value="12"<c:if test="${model.record.thtrm == '12'}"> selected </c:if> >12</option>
					 				  	<option value="16"<c:if test="${model.record.thtrm == '16'}"> selected </c:if> >16</option>
					 				  	<option value="17"<c:if test="${model.record.thtrm == '17'}"> selected </c:if> >17</option>
					 				  	<option value="18"<c:if test="${model.record.thtrm == '18'}"> selected </c:if> >18</option>
					 				  	<option value="20"<c:if test="${model.record.thtrm == '20'}"> selected </c:if> >20</option>
					 				  	<option value="23"<c:if test="${model.record.thtrm == '23'}"> selected </c:if> >23</option>
					 				  	<option value="30"<c:if test="${model.record.thtrm == '30'}"> selected </c:if> >30</option>
					 				  	<option value="40"<c:if test="${model.record.thtrm == '40'}"> selected </c:if> >40</option>
					 				  	<option value="50"<c:if test="${model.record.thtrm == '50'}"> selected </c:if> >50</option>
					 				  	<option value="70"<c:if test="${model.record.thtrm == '70'}"> selected </c:if> >70</option>
					 				  	<option value="80"<c:if test="${model.record.thtrm == '80'}"> selected </c:if> >80</option>
					 				  	<option value="90"<c:if test="${model.record.thtrm == '90'}"> selected </c:if> >90</option>
					 				  	
									</select>
				            	
				            	<div class="text11" style="position: relative;" align="left">
				            	<span style="position:absolute;top:2px; width:250px;" id="thtrm_info" class="popupWithInputText text11"  >
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
		 			<td class="text12">
		 				<table align="left" border="0" cellspacing="0" cellpadding="0">
			 				<tr>
				 				<td class="text12">
				 				<img onMouseOver="showPop('19_info');" onMouseOut="hidePop('19_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>19.</b>&nbsp;<font class="text16RedBold" >*</font><span title="thkdc">Container&nbsp;&nbsp;</span>
				 				<div class="text11" style="position: relative;" align="left">
				 				<span style="position:absolute;top:2px; width:250px;" id="19_info" class="popupWithInputText text11"  >
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
				 				<td class="text12" >
		 							<select name="thkdc" id="thkdc">
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
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('lastplats_info');" onMouseOut="hidePop('lastplats_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>27.</b>&nbsp;<span title="thlasd/thlas2">Lastningsplats</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="lastplats_info" class="popupWithInputText text11"  >
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
								<td class="text12">&nbsp;</td>
							</tr>
							<tr>	
							    <td class="text12" >
							    	<input type="text" class="inputTextMediumBlue" name="thlasd" id="thlasd" size="17" maxlength="17" value="${model.record.thlasd}">
							    </td>
							    <td class="text12" >
							    	<input type="text" class="inputTextMediumBlue" name="thlas2" id="thlas2" size="17" maxlength="17" value="${model.record.thlas2}">
							    </td>							    
	        				</tr>	
	        				<tr height="10"><td class="text">&nbsp;</td> </tr>
	        						 			
			 				<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('autolagringsplats_info');" onMouseOut="hidePop('autolagringsplats_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <span title="thlsd">Auto.lagringsplats</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="autolagringsplats_info" class="popupWithInputText text11"  >
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
					            <td class="text12" align="left">
					            <img onMouseOver="showPop('tullkontor_info');" onMouseOut="hidePop('tullkontor_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <font class="text16RedBold" >*</font><span title="thcats">Avgångstullkontor&nbsp;</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="tullkontor_info" class="popupWithInputText text11"  >
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
							    <input type="text" class="inputTextMediumBlue" name="thcats" id="thcats" size="8" maxlength="8" value='${model.record.thcats}'>
							    <img id="imgUtfartstullKontor" style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" onClick="showPop('searchTullkontorDialog10');">
					            
							    <%-- ======================================================== --%>
				            	<%-- Here we have the search Tullkontor popup window --%>
				            	<%-- ======================================================== --%>
				            	<span style="position:absolute; left:550px; top:620px; width:300px; height:212px;" id="searchTullkontorDialog10" class="popupWithInputText"  >
					           		<div class="text10" align="left">
					           			<table>
					           				<tr>
												<td class="text11">&nbsp;Tullkontor</td>
												<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_utfa" id="search_sveh_utfa" size="18" maxlength="8" value=""></td>
											</tr>
						           			<tr>
												<td class="text11">&nbsp;Kod</td>
												<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_utfa_Code" id="search_sveh_utfa_Code" size="18" maxlength="35" value=""></td>
											</tr>
						           			<tr>
						           				<td class="text11">&nbsp;</td>
							           			<td align="right">&nbsp;<button name="searchTullkontor10" id="searchTullkontor10" class="buttonGray" type="button" onClick="searchAvgangTullkontorOwnWindow()">Sök</button></td>
							           		</tr>
							           		<tr height="4"><td ></td></tr>
							           		<tr>
						           				<td class="text11">&nbsp;Urval</td>
							           			<td>&nbsp;</td>
							           		</tr>
							           		<tr>
												<td colspan="2">&nbsp;
													<select class="text11" id="tullkontorListAvgang" name="tullkontorListAvgang" size="3" onDblClick="hidePop('searchTullkontorDialog10');">
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
					            <td class="text12" align="left">
					            <img onMouseOver="showPop('sprakkod_foljedok_info');" onMouseOut="hidePop('sprakkod_foljedok_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <font class="text16RedBold" >*</font><span title="thskfd">Språkkod följedok.&nbsp;</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="sprakkod_foljedok_info" class="popupWithInputText text11"  >
					           			<ul>
					           				<li>Följdokument (t.ex. varulista)
					           				</li>
					           			</ul>
								</span>	
								</div>
								</td>
					            <td align="left">
	            					<select name="thskfd" id="thskfd">
			            				<option value="">-Välj-</option>
			 				  			<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
                               	 			<option value="${code.tkkode}"<c:if test="${model.record.thskfd == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
										</c:forEach> 
								</select>
				            		<a tabindex="-1" class="text14" target="_blank" href="${model.isoLanguageCodesURL.value}" onclick="${model.isoLanguageCodesURL.windowOpenDimensions}" >
				            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
				            		</a>
								
								</td> 
							</tr>
							<tr height="2"><td>&nbsp;</td></tr>
							<tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('transnr_dt_info');" onMouseOut="hidePop('transnr_dt_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <span title="thtrnr">Transiteringsnr.</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="transnr_dt_info" class="popupWithInputText text11"  >
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
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('transnr_dt_info');" onMouseOut="hidePop('transnr_dt_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <span title="thtrdt">Transiteringsdatum</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="transnr_dt_info" class="popupWithInputText text11"  >
					           			<ul>
					           				<li>Transiteringsdatum för godkännade</li>
					           			</ul>	
								</span>	
					            </div>
					            </td>
					            
					            <td class="text12" >
					            	<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="thtrdt" id="thtrdt" size="8" maxlength="8" value="${model.record.thtrdt}">
					            	&nbsp;
					            </td>
					        </tr>
					        
					        <tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('deklarantplats_info');" onMouseOut="hidePop('deklarantplats_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <font class="text16RedBold" >*</font><span title="thdst">Deklarantens plats</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="deklarantplats_info" class="popupWithInputText text11"  >
					           			<ul>
					           				<li>Plats (inklusive landkod)</li>
					           			</ul>	
								</span>	
					            </div>
					            </td>
					           
					            <td class="text12" align="left" >
					            	<input type="text" class="inputTextMediumBlue" name="thdst" id="thdst" size="10" maxlength="15" value="${model.record.thdst}">
					            	&nbsp;<font class="text16RedBold" >*</font><span title="thdsk">Dekl.språk</span>
					            	<select name="thdsk" id="thdsk">
			            				<option value="">-Välj-</option>
			 				  			<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
                               	 			<option value="${code.tkkode}"<c:if test="${model.record.thdsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
										</c:forEach> 
									</select>
					            		<a tabindex="-1" class="text14" target="_blank" href="${model.isoLanguageCodesURL.value}" onclick="${model.isoLanguageCodesURL.windowOpenDimensions}" >
					            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
					            		</a>
					            </td>
					        </tr>
					        <tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('deklarant_info');" onMouseOut="hidePop('deklarant_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <span title="thtarf">Deklarant</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="deklarant_info" class="popupWithInputText text11"  >
					           			Fältet är spärrat. Hämtas automatiskt.
								</span>
								</div>
								</td>
					            <td colspan="3" class="text12" align="left" >
					            		<input readonly type="text" class="inputTextReadOnly" name="thtarf" id="thtarf" size="25" value="${model.record.thtarf}">
					            </td>
					        </tr>
					        
							<tr height="15"><td>&nbsp;</td></tr>	 
								    
					        <tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('6_info');" onMouseOut="hidePop('6_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>6.&nbsp;</b><span title="thntk">Kollital</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="6_info" class="popupWithInputText text11"  >
					           			<ul>
					           				<li>The total number of packages (pallets, cartons, coils, etc.) making up the consignments
												in question shall be entered here
					           				</li>
					           			</ul>
					           			Fältet är spärrat. Beräknas automatiskt från varulinjerna.
								</span>	
								</div>
								</td>
					            <td ><input readonly onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextReadOnly" name="thntk" id="thntk" size="8" maxlength="7" value="${model.record.thntk}"></td>
					        </tr>
				            <tr>
					            <td class="text12" align="left" >
					            <img onMouseOver="showPop('bruttovikt_info');" onMouseOut="hidePop('bruttovikt_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>35.</b>
					            <span title="thvkb">Bruttovikt</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="bruttovikt_info" class="popupWithInputText text11"  >
					           			<ul>
					           				<li>Sändningens totala bruttovikt. 
					           				</li>
					           				
					           			</ul>
					           			Fältet är spärrat. Beräknas automatiskt från varulinjerna.
								</span>	
					            </div>
					            </td>
					            <td ><input readonly onKeyPress="return amountKey(event)" style="text-align: right" type="text" class="inputTextReadOnly" name="thvkb" id="thvkb" size="10" maxlength="9" value="${model.record.thvkb}"></td>
					        </tr>
					     	<tr height="5"><td>&nbsp;</td></tr>
					     	
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
				            		<td class="text">&nbsp;</td> 
			 				    <td class="text9BlueGreen" valign="bottom" align="right" >
			 				    	<%-- only status = M or emtpy status is allowed --%>
				 				    <c:choose>
					 				    <c:when test="${ model.record.thst == 'G' ||  model.status=='F' || model.record.thst == 'M' || empty model.record.thst}">
						 				    	<input tabindex=-1 class="inputFormSubmit" type="submit" name="submit" onclick="javascript: form.action='nctsexport_edit.do';" value='<spring:message code="systema.ncts.export.createnew.submit"/>'/>
						 				    	&nbsp;&nbsp;
						 				    	<c:if test="${not empty model.record.thtdn}">
						 				    		<input tabindex=-2 class="inputFormSubmit" type="submit" name="send" onclick="javascript: form.action='nctsexport_send.do';" value='<spring:message code="systema.ncts.export.createnew.send"/>'/>
						 				    	</c:if>
					 				    </c:when>
					 				    <c:otherwise>
					 				    		<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value='Ej uppdaterbart'/>
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
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
			 			<td width="5">&nbsp;</td>
			            <td >		
			 				<%-- ANSVARIG --%>
			 				<table width="100%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="18px">
						 			<td class="text12White">
						 				&nbsp;
						 				<img onMouseOver="showPop('14_info');" onMouseOut="hidePop('14_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						
				 						<b>&nbsp;50.</b><font class="text16RedBold" >*</font>Ansvarig&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 						<div class="text11" style="position: relative;" align="left">
						 				<span style="position:absolute;top:2px; width:250px;" id="14_info" class="popupWithInputText text11"  >
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
									            <td class="text12" align="left">&nbsp;</td> 
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
							        				<td class="text12" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="thtina">TIN</span></td>
									            <td class="text12" align="left" >&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="thnaa">Namn</span>
									            <img id="imgAnsvarigSearch" style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" onClick="showPop('searchCustomerDialog20');">
									            	<%-- ======================================================== --%>
									            	<%-- Here we have the search Ansvarig [Customer] popup window --%>
									            	<%-- ======================================================== --%>
									            	<span style="position:absolute; left:500px; top:1200px; width:300px; height:212px;" id="searchCustomerDialog20" class="popupWithInputText"  >
										           		<div class="text10" align="left">
										           			<table>
										           				<tr>
																	<td class="text11">&nbsp;Kundnummer</td>
																	<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_dkkn" id="search_sveh_dkkn" size="18" maxlength="8" value=""></td>
																</tr>
											           			<tr>
																	<td class="text11">&nbsp;Namn</td>
																	<td class="text11">&nbsp;<input type="text" class="inputText" name="search_sveh_dkna" id="search_sveh_dkna" size="18" maxlength="35" value=""></td>
																</tr>
											           			<tr>
											           				<td class="text11">&nbsp;</td>
												           			<td align="right">&nbsp;<button name="searchCustomer20" class="buttonGray" type="button" onClick="searchAnsvarigOwnWindow()">Sök</button></td>
												           		</tr>
												           		<tr height="4"><td ></td></tr>
												           		<tr>
											           				<td class="text11">&nbsp;Urval</td>
												           			<td>&nbsp;</td>
												           		</tr>
												           		<tr>
																	<td colspan="2">&nbsp;
																		<select class="text11" id="ansvarigList" name="ansvarigList" size="3" onDblClick="hidePop('searchCustomerDialog20');">
						 													<option selected value="">-Välj-</option>
						 							 					</select>
																	</td>
																	
																	<%-- <input type="hidden" name="hidden_sveh_avkn" id="hidden_sveh_avkn" value=''> --%>
																</tr>
										           			</table>
															<table width="30%" align="left" border="0">
																<tr align="left" >
																	<td >&nbsp;<button name="searchCustomer20CloseOk" id="searchCustomer20CloseOk" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchCustomerDialog20');">Ok</button></td>
																	<td >&nbsp;<button name="searchCustomer20CloseCancel" id="searchCustomer20CloseCancel" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchCustomerDialog20');">Avbryt</button></td>
																</tr>
															</table>
														</div>
													</span>	
									            </td>
									            
									        </tr>
									        <tr>
									        		<td align="left"><input type="text" class="inputTextMediumBlue" name="thtina" id="thtina" size="20" maxlength="17" value="${model.record.thtina}"></td>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="thnaa" id="thnaa" size="30" maxlength="35" value="${model.record.thnaa}"></td>
									            
									        </tr>

									        <tr height="4"><td>&nbsp;</td></tr>
									        <tr>
									            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thada1">Adress 1</span></td>
									            <td class="text12" align="left" >&nbsp;&nbsp;<span title="thska">Språkkod</span>
								            		<a tabindex="-1" class="text14" target="_blank" href="${model.isoLanguageCodesURL.value}" onclick="${model.isoLanguageCodesURL.windowOpenDimensions}" >
								            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
								            		</a>
									            
									            </td>
									        </tr>
									        <tr>
									            <td align="left"><input type="text" class="inputTextMediumBlue" name="thada1" id="thada1" size="30" maxlength="35" value="${model.record.thada1}"></td>
									            <td class="text12" align="left" >
									            		&nbsp;<select name="thska" id="thska">
										            		<option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
						                                	 	<option value="${code.tkkode}"<c:if test="${model.record.thska == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
															</c:forEach> 
													</select>
												</td>
									        </tr>
									        <tr>
									        		<td>
										        		<table>
										        		<tr>
										            		<td class="text12" align="left" >&nbsp;&nbsp;<span title="thpsa">Postadress</span></td>
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
										        			<td class="text12" align="left" >&nbsp;&nbsp;<span title="thpna">Postnummer</span></td>
										            		<td class="text12" align="left" >&nbsp;<font class="text16RedBold" >*</font><span title="thlka">Land</span>
										            		<a tabindex="-1" class="text14" target="_blank" href="${model.taricLandCodesURL.value}" onclick="${model.taricLandCodesURL.windowOpenDimensions}" >
										            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
										            		</a>
										            		</td>
										            	</tr>
										        		<tr >
										        			<td align="left"><input type="text" class="inputTextMediumBlue" name="thpna" id="thpna" size="10" maxlength="9" value="${model.record.thpna}"></td> 
										            		<td align="left">
										            			<select name="thlka" id="thlka">
												            		<option value="">-Välj-</option>
											 				  	<c:forEach var="country" items="${model.countryCodeList}" >
							                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.thlka == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
																</c:forEach> 
															</select>
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
		           	
		           	<tr>
			 			<td width="5">&nbsp;</td>
			            <td >	
							<%-- create record --%>
						 	<table width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
						 		<tr>
						            <td width="5">&nbsp;</td>
						            <td >
						                <table align="left" border="0" cellspacing="1" cellpadding="0">
						                	<tr>
									            <td class="text12" align="left">
									            <img onMouseOver="showPop('kontrollresultat_info');" onMouseOut="hidePop('kontrollresultat_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 								&nbsp;<span title="thdkr">Kontrollresultat.&nbsp;</span>
				 								<div class="text11" style="position: relative;" align="left">
				 								<span style="position:absolute;top:2px; width:250px;" id="kontrollresultat_info" class="popupWithInputText text11"  >
								           			Förenklad förfarande
								           			<ul>
									           			<li><b>A3</b>&nbsp; för förenklad förfarande</li>
									           		</ul>
									           		<br>
									           		För normal förfarandet lämnas detta blankt.
									           	</span>
									           	</div>
									           	</td>
									           	<td class="text12" align="left">
									           		<select name="thdkr" id="thdkr" >
									 				  <option value="">-Välj-</option>
													  <option value="A3"<c:if test="${model.record.thdkr == 'A3'}"> selected </c:if> >A3</option>													  
													</select>
									           		
									           	</td>
								           	</tr>
								           	<tr>
									            <td class="text12" align="left">
									            <img onMouseOver="showPop('frist_info');" onMouseOut="hidePop('frist_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 								&nbsp;<span title="thddt">Frist&nbsp;</span>
				 								<div class="text11" style="position: relative;" align="left">
				 								<span style="position:absolute;top:2px; width:250px;" id="frist_info" class="popupWithInputText text11"  >
								           			Tidsfrist
								           			<ul>
									           			<li>...</li>
									           		</ul>
												</span>
												</div>
												</td>
									           	<td class="text12" align="left">
									           		<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="thddt" id="thddt" size="10" maxlength="8" value="${model.record.thddt}">
									           		&nbsp;
									           	</td>
								           	</tr>
								           	<tr height="10"><td></td></tr>
								           	<tr>
									            <td class="text12" align="left">
									            <img onMouseOver="showPop('forsegling_info');" onMouseOut="hidePop('forsegling_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 								&nbsp;<span title="thdfkd">Förseglingsid&nbsp;</span>
				 								<div class="text11" style="position: relative;" align="left">
				 								<span style="position:absolute;top:2px; width:250px;" id="forsegling_info" class="popupWithInputText text11"  >
													Vid Försegling måste man ange samtliga värden (Antal/kod/språk) eller inga.
												</span>
												</div>
												</td>
									           	<td class="text12" align="left">
									           		<input type="text" class="inputTextMediumBlue" name="thdfkd" id="thdfkd" size="20" maxlength="20" value="${model.record.thdfkd}">
									           	</td>
								           	</tr>
								           	<tr>
									            <td class="text12" align="left">&nbsp;&nbsp;&nbsp;&nbsp;<span title="thdant">Förseglingsantal&nbsp;</span></td>
				 								<td class="text12" align="left">
									           		<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="thdant" id="thdant" size="4" maxlength="4" value="${model.record.thdant}">
									           	</td>
								           	</tr>
								           	<tr>
									            <td class="text12" align="left">&nbsp;&nbsp;&nbsp;&nbsp;<span title="thdfsk">Förseglingsspråkkod</span></td>
				 								
									           	<td class="text12" align="left">
									           		<select name="thdfsk" id="thdfsk">
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
									            <td class="text12" align="left">
									            <img onMouseOver="showPop('kontrolltullplats_info');" onMouseOut="hidePop('kontrolltullplats_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 								&nbsp;<span title="thdats/thddtk">Kontroll tullplats och datum&nbsp;</span>
				 								<div class="text11" style="position: relative;" align="left">
				 								<span style="position:absolute;top:2px; width:250px;" id="kontrolltullplats_info" class="popupWithInputText text11"  >
									           		<ul>
									           			<li>?</li>
									           		</ul>
												</span>
												</div>
												</td>
												
									           	<td class="text12" align="left">
									           		<input readonly type="text" class="inputTextReadOnly" name="thdats" id="thdats" size="8" maxlength="8" value="${model.record.thdats}">
									           		<input readonly type="text" class="inputTextReadOnly" name="thddtk" id="thddtk" size="8" maxlength="8" value="${model.record.thddtk}">
									           		&nbsp;
									           	</td>
								           	</tr>
								           	
							           	</table>
						           	</td>
					           	</tr>
						 		<tr height="20"><td></td></tr>
						 		
								<%-- EXTRA INFORMATION fields 
								<tr>
						 			<td colspan="2">		
						 				<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
									 		<tr height="15">
									 			<td class="text12">
									 				&nbsp;&nbsp;<b>Ytterligare säkerhetsuppgifter&nbsp;</b>
									 				<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
									 				
								 				</td>
							 				</tr>
						 				</table>
					 				</td>
								</tr>
								<tr height="8"><td></td></tr>
					            <tr>
						            <td width="5">&nbsp;</td>
						            <td >
						                <table align="left" border="0" cellspacing="1" cellpadding="0">
						                	<tr>
									            <td class="text12" align="left">
									            <img onMouseOver="showPop('S02_info');" onMouseOut="hidePop('S02_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 								<b>S02.</b>&nbsp;Kommersiellt referensnr.&nbsp;</td>
				 								<span style="position:absolute; left:1020px; top:1000px; width:250px; height:500px;" id="S02_info" class="popupWithInputText"  >
									           		<div class="text11" align="left">
								           			<br>
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
									           		</div>
												</span>
									           	<td class="text12" align="left"><input type="text" class="inputTextMediumBlue" name="sveh_komr" id="sveh_komr" size="25" maxlength="70" value='${komr}'></td>
								           	</tr>
						                	<tr>
									 			<td class="text12" align="left" >
									 			<img onMouseOver="showPop('S28_info');" onMouseOut="hidePop('S28_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 								<b>S28.</b>&nbsp;Förseglingsid&nbsp;</td>
				 								<span style="position:absolute; left:1020px; top:1000px; width:250px; height:500px;" id="S28_info" class="popupWithInputText"  >
									           		<div class="text11" align="left">
								           			<br>
								           			Antal förseglingar
								           			<br/>
								           			<ul>
									           			<li>Uppgiften lämnas för närvarande inte i Sverige i UNU.</li>
									           			<li>I dagsläget finns inga tillstånd till förenklat förfarande på export i Sverige där det är föreskrivet att en sändning ska förseglas, och därmed kan uppgiften utelämnas.</li>
									           			<li>Förseglingsidentitet<br/>
														Uppgiften lämnas för närvarande inte i Sverige i UNU.</li>
									           		</ul>
									           		</div>
												</span>
									 			<td class="text12" align="left" >&nbsp;&nbsp;Id(1)&nbsp;<input type="text" class="inputTextMediumBlue" name="sveh_sel1" id="sveh_sel1" size="20" maxlength="70" value='${sel1}'></td>
									 		</tr>
									 		<tr>
									 			<td class="text12">&nbsp;</td>
									 			<td class="text12" align="left" >&nbsp;&nbsp;Id(2)&nbsp;<input type="text" class="inputTextMediumBlue" name="sveh_sel2" id="sveh_sel2" size="20" maxlength="70" value='${sel2}'></td>
									 			
							 				</tr>
									 		<tr>
								 				<td nowrap class="text12">
								 				<img onMouseOver="showPop('S29_info');" onMouseOut="hidePop('S29_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 								<b>S29.&nbsp;</b>Betalningssätt&nbsp;</td>
								 				<span style="position:absolute; left:1020px; top:1000px; width:250px; height:500px;" id="S29_info" class="popupWithInputText"  >
									           		<div class="text11" align="left">
								           			<br>
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
									           		</div>
												</span>
								 				<td>
									 				<select name="sveh_betk" id="sveh_betk" >
									 				  <option value="">-Välj-</option>
													  <option value="A"<c:if test="${betk == 'A'}"> selected </c:if> >A</option>
													  <option value="B"<c:if test="${betk == 'B'}"> selected </c:if> >B</option>
													  <option value="C"<c:if test="${betk == 'C'}"> selected </c:if> >C</option>
													  <option value="D"<c:if test="${betk == 'D'}"> selected </c:if> >D</option>
													  <option value="H"<c:if test="${betk == 'H'}"> selected </c:if> >H</option>
													  <option value="Y"<c:if test="${betk == 'Y'}"> selected </c:if> >Y</option>
													  <option value="Z"<c:if test="${betk == 'Z'}"> selected </c:if> >Z</option>
													</select>
								 				</td>
							 				</tr>
							 				<tr>
								 				<td nowrap class="text12">
								 				<img onMouseOver="showPop('S32_info');" onMouseOut="hidePop('S32_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 								<b>S32.&nbsp;</b>Särsk.omständ&nbsp;</td>
								 				<span style="position:absolute; left:1020px; top:1000px; width:250px; height:500px;" id="S32_info" class="popupWithInputText"  >
									           		<div class="text11" align="left">
								           			<br>
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
									           		</div>
												</span>
								 				<td>
									 				<select name="sveh_saom" id="sveh_saom" >
									 				  <option value="">-Välj-</option>
													  <option value="A"<c:if test="${saom == 'A'}"> selected </c:if> >A</option>
													  <option value="B"<c:if test="${saom == 'B'}"> selected </c:if> >B</option>
													  <option value="E"<c:if test="${saom == 'E'}"> selected </c:if> >E</option>
												    </select>
								 				</td>			 				
							 				</tr>
							 				
							 			
							 				<tr height="15"><td></td></tr>
										</table>
									</td>
									
								</tr>
								--%>				 	
						 	</table>
					 	</td>
				 	</tr>
						 	
					</table>
				</td>
				<%-- --------------- --%>
				<%-- RIGHT SIDE CELL --%>
				<%-- --------------- --%>
				
				<td width="50%" align="center" valign="top">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="2">&nbsp;</td>
							<td valign="top">
					 			<table border="0" cellspacing="0" cellpadding="0">
			                	<tr>
						 			<td class="text12" >
						 				<table align="left" border="0" cellspacing="2" cellpadding="0">
							 				<tr>
								 				<td nowrap class="text12">
								 				<img onMouseOver="showPop('returadress_info');" onMouseOut="hidePop('returadress_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									 			&nbsp;<span title="thtsn1/thtsn2/thtslk">Returadress&nbsp;</span>
									 			<div class="text11" style="position: relative;" align="left">
								 				<span style="position:absolute;top:2px; width:250px;" id="returadress_info" class="popupWithInputText text11"  >
									           			Tullverkets returadress
												</span>
												</div>
												</td>
						 			
								 				<td class="text12">
								 					<input readonly type="text" class="inputTextReadOnly" name="thtsn1" id="thtsn1" size="20" maxlength="35" value="${model.record.thtsn1}">
								 				</td>
							 				</tr>
							 				<tr>
								 				<td class="text12">&nbsp;</td>
					 							<td class="text12">
								 					<input readonly type="text" class="inputTextReadOnly" name="thtsn2" id="thtsn2" size="20" maxlength="35" value="${model.record.thtsn2}">
								 				</td>
							 				</tr>
							 				<tr>
								 				<td nowrap class="text12">&nbsp;</td>
					 							<td class="text12">
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
			 		
				<p class="text12" >Change status as needed.</p>
				<table>
					<tr>
						<td class="text12" align="left" >&nbsp;Status</td>
						<td class="text12MediumBlue">
							<select name="selectedStatus" id="selectedStatus">
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
 
	