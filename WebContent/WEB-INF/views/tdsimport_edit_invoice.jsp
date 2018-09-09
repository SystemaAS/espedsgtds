	<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTds.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/jquery.calculator.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/jquery-ui-timepicker-addon.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/tdsimport_edit_invoice.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<%-- for dialog popup --%>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/blitzer/jquery-ui.css">
	<style type = "text/css">
		.ui-dialog{font-size:10pt;}
	</style>
	
	<%-- tab container component --%>
	<table width="100%"  class="text14" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkMainList" tabindex=-1 style="display:block;" href="tdsimport.do?action=doFind&sign=${model.sign}">
					<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tds.import.list.tab"/></font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkHeader" tabindex=-1 style="display:block;" href="tdsimport_edit.do?action=doFetch&avd=${model.avd}&opd=${model.opd}
						&sysg=${model.sign}&tuid=${model.tullId}&syst=${model.status}&sydt=${model.datum}">
					
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tds.import.created.mastertopic.tab"/></font>
					<font class="text14MediumBlue">[${model.opd}]</font>
					<c:if test="${model.status == 'M' || empty model.status}">
						<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
					</c:if>
				</a>
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;<spring:message code="systema.tds.import.invoice.tab"/></font>
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkItemLines" tabindex=-1 style="display:block;" href="tdsimport_edit_items.do?action=doFetch&avd=${model.avd}&sign=${model.sign}
											&opd=${model.opd}&tullId=${model.tullId}
											&status=${model.status}&datum=${model.datum}&fabl=${recordTopic.svih_fabl}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tds.import.item.createnew.tab"/>
					</font>
					<c:if test="${model.status == 'M' || empty model.status}">
						<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
					</c:if>
					
				</a>
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkLogging" tabindex=-1 style="display:block;" href="tdsimport_logging.do?avd=${model.avd}&sign=${model.sign}&opd=${model.opd}&tullId=${model.tullId}
													&status=${model.status}&datum=${model.datum}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tds.import.logging.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkArchive" tabindex=-1 style="display:block;" href="tdsimport_archive.do?avd=${model.avd}&sign=${model.sign}&opd=${model.opd}&tullId=${model.tullId}
													&status=${model.status}&datum=${model.datum}"">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tds.import.archive.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
				</a>
			</td>
			
			<td width="10%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
		</tr>
	</table>
	<%-- -------------------------------- --%>	
 	<%-- tab area container MASTER TOPIC  --%>
	<%-- -------------------------------- --%>
 	<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
		<tr height="15"><td colspan="2">&nbsp;</td></tr>	
		
		<tr>
		<td >
		<table border="0" width="95%" align="center">
			<tr>
	 			<td >		
	 				<%-- MASTER Topic header --%>
	 				<table width="80%" align="left" class="formFrameHeaderTransparent" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text14MediumBlue">
				 				&nbsp;Avd&nbsp;<b>${model.avd}</b>
				 				&nbsp;Tolldeknr.&nbsp;<b>${model.opd}</b>
				 				&nbsp;Sign&nbsp;<b>${model.sign}</b>
				 				&nbsp;&nbsp;Status:&nbsp;<b>${model.status}</b>
				 				&nbsp;&nbsp;Dekl.:&nbsp;<b>${recordTopic.svih_dek1}</b>
			 				</td>
		 				</tr>
	 				</table>
					<%-- MASTER Topic information [it is passed through a session object: XX] --%>
				 	<table height="40" width="80%" align="left" class="formFrameTitaniumWhite" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="2"><td class="text" align="left" colspan="2"></td></tr>
				 		<tr>
					 		<td width="50%">
						 		<table width="100%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text14Bold" align="left" >Exportör</td>
							            <td class="text14" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text14" align="left">EORI&nbsp;</td>
							           	<td class="text14MediumBlue" align="left"></td>
							        </tr>
							        <tr>
							            <td width="30%" class="text14" align="left">Namn&nbsp;</td>
							           	<td class="text14MediumBlue" align="left">${recordTopic.svih_avna}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text14" align="left">Adress 1&nbsp;</td>
							           	<td class="text14MediumBlue" align="left">${recordTopic.svih_ava1}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text14" align="left">Adress 2&nbsp;</td>
							           	<td class="text14MediumBlue" align="left">${recordTopic.svih_ava2}</td>
							        </tr>
							        
									<tr>
							            <td width="30%" class="text14" align="left">Postadress&nbsp;</td>
							           	<td class="text14MediumBlue" align="left">${recordTopic.svih_avpn}&nbsp;${recordTopic.svih_avpa}&nbsp;${recordTopic.svih_avlk}</td>
							        </tr>
							        <tr>
							        		<td width="30%" class="text14" align="left">&nbsp;</td>
							        </tr>						        
			        	        </table>
					        </td>
					        <td width="50%">
						 		<table width="100%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text14Bold" align="left" >Mottagare</td>
							            <td class="text14" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text14" align="left">EORI.&nbsp;</td>
							           	<td class="text14MediumBlue" align="left">${recordTopic.svih_moeo}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text14" align="left">Namn&nbsp;</td>
							           	<td class="text14MediumBlue" align="left">${recordTopic.svih_mona}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text14" align="left">Adress 1&nbsp;</td>
							           	<td class="text14MediumBlue" align="left">${recordTopic.svih_moa1}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text14" align="left">Adress 2&nbsp;</td>
							           	<td class="text14MediumBlue" align="left">${recordTopic.svih_moa2}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text14" align="left">Postadress&nbsp;</td>
							           	<td class="text14MediumBlue" align="left">${recordTopic.svih_mopn}&nbsp;${recordTopic.svih_mopa}&nbsp;${recordTopic.svih_molk}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text14" align="left">Handläggare&nbsp;</td>
							           	<td class="text14MediumBlue" align="left">${recordTopic.svih_moha}&nbsp;tel:${recordTopic.svih_motl}</td>
							        </tr>
							        
			        	        </table>
					        </td>
				        </tr>
					</table>          
            	</td>
           	</tr> 
           	<%-- ---------------------- --%>
           	<%-- LIST of existent ITEMs --%>
           	<%-- ---------------------- --%>
           	<tr>
				<td>
					<table width="100%" cellspacing="0" border="0" cellpadding="0">
	    				<%-- separator --%>
	        			<tr height="10"><td></td></tr> 
						<tr >
							<td>
								<table width="80%" cellspacing="0" border="0" cellpadding="0">
									<tr>
										<%--
										<td class="text14Bold">&nbsp;Antal handlingar&nbsp;&nbsp;<font class="text14MediumBlue"><b>${model.recordItemContainerInvoiceTopic.totalNumberOfItemLines}</b></font>
						            	</td>
						            	--%>
						            	<td align="right" class="text14">&nbsp;</td> 
										<td align="right" class="text14">Fsum:&nbsp;
											<input tabindex=-1 align="right" type="text" readonly class="inputText11BlueBoldReadOnly" size="12" value="${recordTopic.svih_fabl}">
											<font class="inputText11BlueBoldReadOnly">${recordTopic.svih_vakd}</font>
										</td>
										
										<td align="right" class="text14">Vsum&nbsp;(&Sigma;):&nbsp;
											<input tabindex=-1 align="right" type="text" readonly class="inputText11BlueBoldReadOnly" size="12" value="${model.recordItemContainerInvoiceTopic.calculatedItemLinesTotalAmount}">
											<font class="inputText11BlueBoldReadOnly">${model.recordItemContainerInvoiceTopic.calculatedValidCurrency}</font>											
										</td>
										 
										<%-- Bug somewhere !!!
										<td align="right" class="text14">Diff:&nbsp;
											<input tabindex=-1 align="right" type="text" readonly
												<c:choose>
												<c:when test="${fn:contains(model.recordItemContainerInvoiceTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount,'-')}">
													class="inputText11RedBoldReadOnly" 
												</c:when>
												<c:otherwise>
													class="inputText11BlueBoldReadOnly"
												</c:otherwise>
												</c:choose>
												size="12" maxlength=20" value='${model.recordItemContainerInvoiceTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount}'>
										</td>
										 --%>
									</tr>
									<tr height="2"><td></td></tr>
								</table>
							
							</td>
						</tr> 
						
						<tr>
							<td >
							<form name="formItemList" id="formItemList" method="POST" >
					               		<input type="hidden" name="opdItemList" id="opdItemList" value="${model.opd}">
				 						<input type="hidden" name="avdItemList" id="avdItemList" value="${model.avd}">
				 						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
							
								<table id="containerdatatableTable" width="80%" cellspacing="2" align="left" >
								<tr>
								<td>
								<%-- this is the datatables grid (content) --%>
								<table id="tblInvoices" class="display compact cell-border" width="100%">
									<thead>
									<tr class="tableHeaderField" >
										<th width="2%" class="text14">&nbsp;Uppd.&nbsp;</th> 
									    <th class="text14" ><span title="svif_faty">&nbsp;Typ&nbsp;</span></th>
					                    <th class="text14"><span title="svif_fatx">&nbsp;Identitet&nbsp;</span></th>   
					                    <th align="right" class="text14" ><span title="svif_fabl">&nbsp;Belopp&nbsp;</span></th>
					                    <th align="center" class="text14" ><span title="svif_vakd">Valuta</span></th>
					                    <th align="right" class="text14" ><span title="svif_vaku">&nbsp;Kurs&nbsp;</span></th>
					                    <th class="text14" align="left"><span title="svif_omr">Faktor&nbsp;</span></th> 
					                    <c:if test="${model.status == 'M' || empty model.status}">
					                    	<th align="center" class="text14" >Radera</th>
					                    </c:if>
					                    
					               </tr> 
					               </thead>
					               <tbody>
				 					  <c:forEach items="${model.list}" var="record" varStatus="counter">    
							               <c:choose>           
							                   <c:when test="${counter.count%2==0}">
							                       <tr class="tableRow" height="20" >
							                   </c:when>
							                   <c:otherwise> 
							                       <tr class="tableOddRow" height="20" >
							                   </c:otherwise>
							               </c:choose>
							               <td align="center" width="2%" class="text14" >
							               		<a tabindex=-1 id="recordUpdate_${record.svif_fatx}" href="#" onClick="getItemData(this);">
							               			&nbsp;<img src="resources/images/update.gif" border="0" alt="edit">&nbsp;
							               		</a>
							               </td>
							               <td class="text14" >&nbsp;${record.svif_faty}</td>
							               <td width="10%" class="text14" >${record.svif_fatx}</td>
							               <td align="right" class="text14" >&nbsp;${record.svif_fabl}&nbsp;</td>
							               <td align="center" class="text14" >${record.svif_vakd}</td>
							               <td align="right" class="text14" >&nbsp;${record.svif_vaku}&nbsp;</td>
							               <td class="text14" >&nbsp;${record.svif_omr}</td>
							               <c:if test="${model.status == 'M' || empty model.status}">	
								               <td width="4%" class="text14" align="center" nowrap>
								               	<a onclick="javascript:return confirm('Är du säker att du vill radera raden?')" tabindex=-1 href="tdsimport_edit_invoice.do?action=doDelete&sign=${model.sign}&avd=${model.avd}&opd=${model.opd}&status=${model.status}&fak=${record.svif_fatx}">
								               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
								               	</a>	&nbsp;
								               </td>
							               </c:if>
							            </tr>
								        <%-- this param is used ONLY in this JSP 
								        <c:set var="totalNumberOfItemLines" value="${counter.count}" scope="request" />
								        --%> 
								        <%-- this param is used throughout the Controller --%>
								        <c:set var="numberOfItemLinesInTopic" value="${Xrecord.svln}" scope="request" /> 
								        </c:forEach>
								       </tbody> 
						            </table>
						        </td>
						        </tr>
						        </table>
						       </form> 
							</td>
							
						</tr>
					</table>
				</td>
			</tr>
			<tr height="1"><td></td></tr>
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
			
			<%-- Other errors (none validation errors) --%>
			<c:if test="${not empty model.errorMessage}">
			<tr>
				<td colspan="3">
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
			<%-- ------------------------------------------------- --%>
           	<%-- DETAIL Section - Create Item line PRIMARY SECTION --%>
           	<%-- ------------------------------------------------- --%>
           	<tr>
	           	<td>
	           	<form name="createNewItemLine" id="createNewItemLine" method="post" >
					<input type="hidden" name="action" id="action" value='doFetch'>
	 				<input type="hidden" name="opd" id="opd" value="${model.opd}"/>
	 				<input type="hidden" name="avd" id="avd" value="${model.avd}"/>
	 				<input type="hidden" name="sign" id="sign" value="${model.sign}"/>
	 				<input type="hidden" name="status" id="status" value="${Xmodel.status}"/>
	 				<input type="hidden" name="datum" id="datum" value='${model.datum}'>
	 				<input type="hidden" name="fabl" id="fabl" value='${recordTopic.svih_fabl}'>
	 				<input type="hidden" name="totalGrossWeight" id="totalGrossWeight" value='${XrecordTopicTvinnSad.sevkb}'>
	 				<table width="80%" cellspacing="0" border="0" cellpadding="0">
						<tr>
							<td class="text14Bold">
								<c:if test="${model.status == 'M' || empty model.status}">
									<input tabindex=-1 class="inputFormSubmitStd" type="submit" name="submit" onclick="javascript: form.action='tdsimport_edit_invoice.do';" value="Skapa ny">
									&nbsp;<button title="Import av externa fakturor" name="importInvoicesButton" id="importInvoicesButton" class="buttonGrayWithGreenFrame" type="button" >Importera externa fakturor</button>
								</c:if>
							</td>
						</tr>
				   </table>
			   	</form>
           		</td>
           	</tr>
           	<%-- ------------------------------------------------- --%>
           	<%-- Init form in case we want to reload               --%>
           	<%-- ------------------------------------------------- --%>
           	<tr>
	 			<td >
	 				<form name="tdsImportEditTopicInvoiceItemForm" id="tdsImportEditTopicInvoiceItemForm" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="opd" id="opd" value="${model.opd}"/>
				 	<input type="hidden" name="avd" id="avd" value="${model.avd}"/>
				 	<input type="hidden" name="sign" id="sign" value="${model.sign}"/>
				 	<input type="hidden" name="isModeUpdate" id="isModeUpdate" value="${model.record.isModeUpdate}"/>
				 	<input type="hidden" name="status" id="status" value="${Xmodel.status}"/>
				 	<input type="hidden" name="datum" id="datum" value="${Xmodel.datum}"/>
				 	<input type="hidden" name="fabl" id="fabl" value="${recordTopic.svih_fabl}"/>
				 	<input type="hidden" name="lineId" id="lineId" value="">
				 	<%-- <input type="hidden" name="numberOfItemLinesInTopic" id="numberOfItemLinesInTopic" value="${numberOfItemLinesInTopic}" /> --%>
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="80%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text14White" align="left" >
				 				<b>&nbsp;&nbsp;B.handlingar&nbsp;</b>

				 				<img onClick="showPop('updateInfo');" src="resources/images/update.gif" border="0" alt="edit">
				 				<span style="position:absolute; left:150px; top:200px; width:800px; height:400px;" id="updateInfo" class="popupWithInputText"  >
		           		   			<div class="text14" align="left" style="display:block;width:700px;word-break:break-all;">
		           		   				${activeUrlRPGUpdate}<br/><br/>
		           		   				<button name="updateInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('updateInfo');">Close</button> 
		           		   			</div>
						        </span>  
			 				</td>
		 				</tr>
	 				</table>
	 				
					<table width="80%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15"><td class="text" align="left"></td></tr>
				 		<tr>
					 		<td>
						 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
							 		<tr>
							 			<td class="text14" align="left"><font class="text16RedBold" >*</font><span title="svif_faty">&nbsp;Typ</span></td>
							            <td class="text14" align="left"><span title="svif_fatx">Identitet</span></td>
							            <td class="text14" align="left"><span title="svif_fabl">&nbsp;Belopp</span></td>
							            <td class="text14" align="left"><span title="svif_vakd">&nbsp;Valuta</span></td>
					            		<td class="text14" align="left"><span title="svif_vaku">&nbsp;Kurs</span></td>
					            		<td class="text14" align="left"><span title="factor">Faktor&nbsp;</span></td>
							        </tr>
							        <tr>
						        		<td>
											<select autofocus="autofocus" class="inputTextMediumBlueMandatoryField" name="svif_faty" id="svif_faty">
						 						<option value="">-Välj-</option>
						 						
							 				  	<c:forEach var="code" items="${model.mcfCodeList}" >
							 				  		<c:choose>
														<c:when test="${not empty model.record.svif_faty}">
								 				  			<option value="${code.svkd_kd}"<c:if test="${model.record.svif_faty == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
								 				  		</c:when>
								 				  		<c:otherwise>
								 				  			<option value="${code.svkd_kd}"<c:if test="${model.record.svif_faty == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
								 				  		</c:otherwise>
								 				  	</c:choose>
												</c:forEach>
												  
											</select>
											<a tabindex="-1" id="bilagdaHandIdLink">
           										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
           									</a>
			 							</td>
			 							<td align="left">
						        			<input type="text" class="inputTextMediumBlue" name="svif_fatx" id="svif_fatx" size="20" maxlength="17" value="${model.record.svif_fatx}">
										</td>
										
										<td class="text14" align="left">
							            		<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="svif_fabl" id="svif_fabl" size="13" maxlength="12" value="${model.record.svif_fabl}">
							            </td>
										<td align="left" nowrap>
							            	<select class="inputTextMediumBlue" name="svif_vakd" id="svif_vakd">
						 						<option value="">-välj-</option>
						 						 
							 				  	<c:forEach var="currency" items="${model.mdxCodeList}" >
							 				  		<option value="${currency.svkd_kd}"<c:if test="${model.record.svif_vakd == currency.svkd_kd}"> selected </c:if> >${currency.svkd_kd}</option>
												</c:forEach> 
												 
											</select>
											<a tabindex="-1" id="valutaIdLink">
           										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
           									</a>
										</td>
						        		<td class="text14" align="left">
						            		<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="svif_vaku" id="svif_vaku" size="10" maxlength="8" value="${model.record.svif_vaku}">
							            </td>
							            <%-- this field is only used via Ajax since there is no database field. It is used to disclosed a factor when changing the currency --%>
							 			<td class="text14Grey" align="left" ><input readonly type="text" class="inputTextReadOnly" name="factor" id="factor" size="6" value=""></td>
							        </tr>
							        <tr height="10"><td class="text" align="left"></td></tr>
						        </table>
					        </td>
				        </tr>
					    <tr height="10"><td colspan="2" ></td></tr>
					    <tr>	
						    <td align="left" colspan="5">
								<input class="inputFormSubmit" type="submit" name="submit" onclick="javascript: form.action='tdsimport_edit_invoice.do';" value='Spara'>
							</td>							        	
				        </tr>
        	        </table>
        	         
		        </td>
		    </tr>
			<tr height="20"><td colspan="2" ></td></tr>
			<tr height="30"><td></td></tr>
			
		</table>
		</td>
		</tr>
	</table>    
	
	</form>
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

