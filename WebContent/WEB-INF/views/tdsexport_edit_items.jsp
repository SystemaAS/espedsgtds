<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTds.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tdsexport_edit_items.js?ver=${user.versionEspedsg}"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/jquery.calculator.js"></SCRIPT>
	
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<style type = "text/css">
		.ui-dialog{font-size:10pt;}
	</style>
	
	<%-- for dialog popup 
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/blitzer/jquery-ui.css">
	--%>
	
	
	<%-- tab container component --%>
	<table width="100%"  class="text12" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkMainList" tabindex=-1 style="display:block;" href="tdsexport.do?action=doFind&sign=${model.sign}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tds.export.list.tab"/></font>
					<img src="resources/images/list.gif" border="0" alt="general list">
					
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkHeader" tabindex=-1 style="display:block;" href="tdsexport_edit.do?action=doFetch&avd=${model.avd}&opd=${model.opd}
						&sysg=${model.sign}&tuid=${model.tullId}&syst=${model.status}&sydt=${model.datum}">
					
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tds.export.created.mastertopic.tab"/></font>
					<font class="text14MediumBlue">[${model.opd}]</font>
					<c:if test="${model.status == 'M' || empty model.status}">
						<img src="resources/images/update.gif" border="0" alt="edit">
					</c:if>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkInvoices" tabindex=-1 style="display:block;" href="tdsexport_edit_invoice.do?action=doFetch&avd=${model.avd}&sign=${model.sign}
													&opd=${model.opd}&tullId=${model.tullId}
													&status=${model.status}&datum=${model.datum}&fabl=">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tds.export.invoice.tab"/></font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;<spring:message code="systema.tds.export.item.createnew.tab"/></font>
				<c:if test="${model.status == 'M' || empty model.status}">
					<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
				</c:if>
				
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkLogging" tabindex=-1 style="display:block;" href="tdsexport_logging.do?avd=${model.avd}&sign=${model.sign}&opd=${model.opd}&tullId=${model.tullId}
													&status=${model.status}&datum=${model.datum}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tds.export.logging.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkArchive" tabindex=-1 style="display:block;" href="tdsexport_archive.do?avd=${model.avd}&sign=${model.sign}&opd=${model.opd}&tullId=${model.tullId}
													&status=${model.status}&datum=${model.datum}"">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.tds.export.archive.tab"/>
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
		<table border="0" width="95%" align="center" border="0">
			<tr>
	 			<td >		
	 				<%-- MASTER Topic header --%>
	 				<table width="100%" align="center" class="formFrameHeaderTransparent" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text14MediumBlue">
				 				&nbsp;Avd&nbsp;<b>${model.avd}</b>
				 				&nbsp;Ärende&nbsp;<b>${model.opd}</b>
				 				&nbsp;Sign&nbsp;<b>${model.sign}</b>
				 				&nbsp;&nbsp;&nbsp;&nbsp;Tullid:&nbsp;<b>${model.tullId}</b>
				 				&nbsp;&nbsp;&nbsp;Status:&nbsp;<b>${model.status}</b>
			 				</td>
		 				</tr>
	 				</table>
					<%-- MASTER Topic information [it is passed through a session object: recordTopic] --%>
				 	<table height="40" width="100%" align="center" class="formFrameTitaniumWhite" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="2"><td class="text" align="left" colspan="2"></td></tr>
				 		<tr>
					 		<td width="50%">
						 		<table width="80%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text12Bold" align="left" >Exportör</td>
							            <td class="text12" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text12" align="left">EORI-nr&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopic.sveh_aveo}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text12" align="left">Namn&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopic.sveh_avna}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text12" align="left">Handläggare&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopic.sveh_avha}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text12" align="left">Landkod&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopic.sveh_avlk}</td>
							        </tr>
							        
			        	        </table>
					        </td>
					        <td width="50%">
						 		<table width="80%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text12Bold" align="left" >Mottagare</td>
							            <td class="text12" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text12" align="left">EORI-nr&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopic.sveh_moeo}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text12" align="left">Namn&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopic.sveh_mona}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text12" align="left">Postadress&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopic.sveh_mopa}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text12" align="left">Landkod&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopic.sveh_molk}</td>
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
							<form id="createNewItemLine" action="tdsexport_edit_items.do">
								<input type="hidden" name="action" id="action" value='doFetch'>
				 				<input type="hidden" name="avd" id="avd" value='${model.avd	}'>
				 				<input type="hidden" name="sign" id="sign" value='${model.sign}'>
								<input type="hidden" name="opd" id="opd" value='${model.opd}'>
				 				<input type="hidden" name="tullId" id="tullId" value='${model.tullId}'>
				 				<input type="hidden" name="status" id="status" value='${model.status}'>
				 				<input type="hidden" name="datum" id="datum" value='${model.datum}'>
				 				<input type="hidden" name="fabl" id="fabl" value='${recordTopic.sveh_fabl}'/>
				 				<input type="hidden" name="fablAutoControl" id="fablAutoControl" value='${recordTopic.sveh_fabl}'/>
				 				
				 				<input type="hidden" name="senderId" id="senderId" value='${recordTopic.sveh_avkn}'>
				 							
								<table width="100%" cellspacing="0" border="0" cellpadding="0">
									<tr>
										<td class="text12Bold">&nbsp;Varuposter&nbsp;&nbsp;
											<c:if test="${model.status == 'M' || empty model.status}">
												<input tabindex=-1  class="inputFormSubmitStd" type="submit" name="submitSkapaNy" value='Skapa ny'>
											</c:if>
											<button tabindex=-1 name="allItemsButton" class="inputFormSubmitStd" type="button" onClick="showPop('allItems');" >Visa alla</button> 
											        <span style="background-color:#EEEEEE; position:absolute; left:50px; top:200px; width:1200px; height:1000px;" id="allItems" class="popupWithInputTextThickBorder"  >
										           		
										           			<table id="containerdatatableTable" align="left" >
										           			<tr>
											           			<td colspan="3" class="text14"><b>Varuposter</b></td>
											           		</tr>
												           	<tr>	
																<td class="text14" width="95%" >
																<table id="tblItemLinesAll" class="display compact cell-border" width="95%" >
																	<thead> 
																	<tr class="tableHeaderField">
																	    
													                    <th class="text14">&nbsp;<spring:message code="systema.tds.export.item.list.label.svev_vano.varupostNr"/>&nbsp;</th>   
													                    <th class="text14">&nbsp;<spring:message code="systema.tds.export.item.list.label.svev_ulkd.urspLand"/>&nbsp;</th>
													                    <th class="text14">&nbsp;<spring:message code="systema.tds.export.item.list.label.svev_vata.varukod"/> <font class="text9">Taric.nr</font>&nbsp;</th>
													                    <th class="text14">&nbsp;<spring:message code="systema.tds.export.item.list.label.svev_eup1.forfarande1"/>&nbsp;</th>
													                    <th class="text14">&nbsp;<spring:message code="systema.tds.export.item.list.label.svev_brut.bruttoVikt"/>&nbsp;</th>
													                    <th class="text14">&nbsp;<spring:message code="systema.tds.export.item.list.label.svev_neto.nettoVikt"/>&nbsp;</th>
													                    <th class="text14">&nbsp;<spring:message code="systema.tds.export.item.list.label.svev_ankv.extraMangd"/>&nbsp;</th>
													                    <th class="text14">&nbsp;<spring:message code="systema.tds.export.item.list.label.sum_of_svev_kotas.kolliAnt"/>(&Sigma;)</th>					                    					                    
													                    <th class="text14">&nbsp;<spring:message code="systema.tds.export.item.list.label.svev_vasl.varuBeskrivning"/>&nbsp;</th>
													                    <th class="text14">&nbsp;<spring:message code="systema.tds.export.item.list.label.svev_err.error"/>&nbsp;</th>
													                    <th class="text14">&nbsp;<spring:message code="systema.tds.export.item.list.label.svev_fabl.fbelopp"/>&nbsp;</th>
													                    <th class="text14">&nbsp;<spring:message code="systema.tds.export.item.list.label.svev_stva.statvarde"/>&nbsp;</th>
													                    
													                    <c:if test="${model.status == 'M' || empty model.status}">
													                    	<th align="center" class="text14" nowrap>Radera</th>
													                    </c:if>
													               </tr>  
													               </thead>
													               <tbody>
														           <c:forEach items="${model.list}" var="record" varStatus="counter">    
														               <c:choose>           
														                   <c:when test="${counter.count%2==0}">
														                       <tr class="tableRow" >
														                   </c:when>
														                   <c:otherwise>   
														                       <tr class="tableOddRow" >
														                   </c:otherwise>
														               </c:choose>
														               <td class="text12" >${record.svev_vano}</td>
														               <td class="text12" >${record.svev_ulkd}</td>
														               <td class="text12" >${record.svev_vata}</td>
														               <td class="text12" >${record.svev_eup1}</td>
														               <td class="text12" >${record.svev_brut}</td>
														               <td class="text12" >${record.svev_neto}</td>
														               <td class="text12" >${record.svev_ankv}</td>
														               <td class="text12" >${record.sum_of_svev_kotas}</td>
														               
														               <td class="text12" >${record.svev_vasl}</td>
														               <td align="center" class="text12">&nbsp;
														               		<c:if test="${not empty record.svev_err}">
														               			<img valign="bottom" src="resources/images/redFlag.png" width="18px" height="18px" border="0" alt="remove">
														               		</c:if>
														               	</td>	
														               <td class="text12">${record.svev_fabl}</td>
														               <td class="text12">${record.svev_stva}</td>
														               
														               <c:if test="${model.status == 'M' || empty model.status}">	
															               <td class="text12" align="center" nowrap>&nbsp;
															               	<a onclick="javascript:return confirm('Är du säker på att du vill radera raden?')" tabindex=-1 href="tdsexport_edit_items.do?action=doDelete&avd=${model.avd}&opd=${model.opd}&lin=${record.svev_syli}">
															               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
															               	</a>	
															               </td>
														               </c:if> 
															           </tr>
															        <%-- <c:set var="numberOfItemLinesInTopic" value="${counter.count}" scope="request" />  --%>
															        <c:set var="numberOfItemLinesInTopic" value="${record.svev_syli}" scope="request" />   
														            </c:forEach>
														            </tbody>
														        </table>
																</td>											           		
													         </tr>
													         </table>
													         
													         <div>
													         <table>
													         	<%-- OK BUTTON --%>
										           				<tr align="left" >
																	<td class="text12"><button name="allItemsButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('allItems');">&nbsp;Ok</button></td>
																	<td class="text14">&nbsp;&nbsp;&nbsp;
														 	        		<a href="tdsExportItemListExcelView.do" target="_new">
													                 		<img valign="bottom" id="itemListExcel" src="resources/images/excel.png" border="0" alt="excel">&nbsp;Excel
														 	        		</a>
														 	        		&nbsp;
															 		</td>
																</tr>
															</table>
											   				</div>
									   					</span>	
									   		<c:if test="${model.status == 'M' || empty model.status}">		
												&nbsp;<button tabindex=-1 title="Kontrollera varuposter" name="itemListControlButton" id="itemListControlButton" class="buttonGrayWithGreenFrame" type="button" >Varupostkontroll</button>
											</c:if>
										</td>
										<td width="5%" class="text14">&nbsp;</td>
										<td align="right" class="text12">Fakturabelopp:&nbsp;
											<input tabindex=-1 align="right" type="text" readonly class="inputText12BlueBoldReadOnly" size="12" maxlength="20" value='${recordTopic.sveh_fabl}'>
										</td>
										<td align="right" class="text12">Varulinjebelopp:&nbsp;
											<input tabindex=-1 align="right" type="text" readonly class="inputText12BlueBoldReadOnly" size="12" maxlength="20" value='${model.recordItemContainerTopic.calculatedItemLinesTotalAmount}'>
										</td>
										<td align="right" class="text12">Differans:&nbsp;
											<input tabindex=-1 align="right" type="text" readonly
												<c:choose>
												<c:when test="${fn:contains(model.recordItemContainerTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount,'-')}">
													class="inputtext14RedBoldReadOnly" 
												</c:when>
												<c:otherwise>
													class="inputtext14BlueBoldReadOnly"
												</c:otherwise>
												</c:choose>
												size="12" maxlength=20" value='${model.recordItemContainerTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount}'>
										</td>
									</tr>
									<tr height="2"><td></td></tr>
								</table>
							</form>
							</td>
						</tr> 

						<tr>
							<td >
								<form name="formItemList" id="formItemList" method="POST" >
			               		<input type="hidden" name="opdItemList" id="opdItemList" value='${model.opd}'>
		 						<input type="hidden" name="avdItemList" id="avdItemList" value='${model.avd}'> 
		 						<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
				 						
								<table width="100%" id="containerdatatableTable" cellspacing="2" align="left" >
								<tr>
								<td class="text14">	
							
								<table id="tblItemLines" class="display compact cell-border" >
									<thead>
									<tr class="tableHeaderField">
									    <%-- 
									    <td class="text14">&nbsp;<spring:message code="systema.tds.export.item.list.label.svev_syli.linjeNr"/>&nbsp;</td> 
									    --%>
					                    <th width="2%" class="text14">&nbsp;<spring:message code="systema.tds.export.item.list.label.update"/>&nbsp;</th>   
					                    <th class="text14">&nbsp;<spring:message code="systema.tds.export.item.list.label.svev_vano.varupostNr"/>&nbsp;</th>   
					                    <th class="text14">&nbsp;<spring:message code="systema.tds.export.item.list.label.svev_ulkd.urspLand"/>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.tds.export.item.list.label.svev_vata.varukod"/> <font class="text9">Taric.nr</font>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.tds.export.item.list.label.svev_eup1.forfarande1"/>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.tds.export.item.list.label.svev_brut.bruttoVikt"/>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.tds.export.item.list.label.svev_neto.nettoVikt"/>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.tds.export.item.list.label.svev_ankv.extraMangd"/>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.tds.export.item.list.label.sum_of_svev_kotas.kolliAnt"/>(&Sigma;)</th>					                    					                    
					                    <th class="text14">&nbsp;<spring:message code="systema.tds.export.item.list.label.svev_vasl.varuBeskrivning"/>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.tds.export.item.list.label.svev_err.error"/>&nbsp;</th>
										<th class="text14">&nbsp;<spring:message code="systema.tds.export.item.list.label.svev_fabl.fbelopp"/>&nbsp;</th>
										<th class="text14">&nbsp;<spring:message code="systema.tds.export.item.list.label.svev_stva.statvarde"/>&nbsp;</th>
													                    
					                    <c:if test="${model.status == 'M' || empty model.status}">
					                    	<th align="center" class="text14" nowrap>Radera</th>
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
							               
							               <%-- Internal to CB. Does not have to bee visible to the end user 
							               <td class="text14" align="center">${record.svev_syli}</td> 
							               --%>
						               	   <td width="2%" class="text14" align="center">&nbsp;
							               		<a tabindex=-1 style="display:block;" id="recordUpdate_${record.svev_syli}_${record.svev_vano}" href="#" onClick="getItemData(this);">
							               			<img src="resources/images/update.gif" border="0" alt="edit">&nbsp;
							               		</a>
						               	   </td>
   							               <td align="center" width="4%" class="text14" >${record.svev_vano}</td>
							               <td class="text14" >${record.svev_ulkd}</td>
							               <td class="text14" >${record.svev_vata}&nbsp;&nbsp;
							               	  <img id="recordUpdate_${record.svev_syli}_${record.svev_vano}" onClick="updateKundensVarReg(this);" src="resources/images/addOrder.png" width="12px" height="12px" border="0" title="Lägg till i kundensvarureg.">
							               </td>	  
							               <td class="text14" >${record.svev_eup1}</td>
							               <td class="text14" >${record.svev_brut}</td>
							               <td class="text14" >${record.svev_neto}</td>
							               <td class="text14" >${record.svev_ankv}</td>
							               <td class="text14" >${record.sum_of_svev_kotas}</td>
							               
							               <td class="text14" >${record.svev_vasl}</td>
							               <td align="center" class="text14">&nbsp;
							               		<c:if test="${not empty record.svev_err}">
							               			<img valign="bottom" src="resources/images/redFlag.png" width="18px" height="18px" border="0" alt="remove">
							               		</c:if>
							               	</td>	
							               <td class="text14">${record.svev_fabl}</td>
							               <td class="text14">${record.svev_stva}</td>
							               
							               <c:if test="${model.status == 'M' || empty model.status}">	
								               <td class="text14" align="center" nowrap>&nbsp;
								               	<a onclick="javascript:return confirm('Är du säker på att du vill radera raden?')" tabindex=-1 href="tdsexport_edit_items.do?action=doDelete&avd=${model.avd}&opd=${model.opd}&lin=${record.svev_syli}">
								               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
								               	</a>	
								               </td>
							               </c:if> 
								           </tr>
								        <%-- <c:set var="numberOfItemLinesInTopic" value="${counter.count}" scope="request" />  --%>
								        <c:set var="numberOfItemLinesInTopic" value="${record.svev_syli}" scope="request" />   
							            </c:forEach>
						          </tbody>  
						          </table>
								</td>	
							</tr>
							</table>
						</form>
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
			<%-- ------------------------------------------------- --%>
           	<%-- DETAIL Section - Create Item line PRIMARY SECTION --%>
           	<%-- ------------------------------------------------- --%>
           	<tr>
	 		<td >
	 		<form name="tdsExportEditTopicItemForm" id="tdsExportEditTopicItemForm" action="tdsexport_edit_items.do"  method="post">
           		<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="opd" id="opd" value='${model.opd}'/>
				 	<input type="hidden" name="avd" id="avd" value='${model.avd}'/>
				 	<input type="hidden" name="sign" id="sign" value='${model.sign}'/>
				 	<input type="hidden" name="tullId" id="tullId" value='${model.tullId}'/>
				 	<input type="hidden" name="status" id="status" value='${model.status}'/>
				 	<input type="hidden" name="datum" id="datum" value='${model.datum}'/>
				 	<input type="hidden" name="fabl" id="fabl" value='${recordTopic.sveh_fabl}'/>
				 	<%-- Session fields needed for the AJAX calculation av Statistical values --%>
				 	<input type="hidden" name="session_sveh_vakd" id="session_sveh_vakd" value="${recordTopic.sveh_vakd}">
			        <input type="hidden" name="session_sveh_vaku" id="session_sveh_vaku" value="${recordTopic.sveh_vaku}">
			        <input type="hidden" name="session_sveh_fabl" id="session_sveh_fabl" value="${recordTopic.sveh_fabl}">
			        <input type="hidden" name="session_sveh_vuva" id="session_sveh_vuva" value="${recordTopic.sveh_vuva}">
			        <input type="hidden" name="session_sveh_vuku" id="session_sveh_vuku" value="${recordTopic.sveh_vuku}">
			        <input type="hidden" name="session_sveh_vufr" id="session_sveh_vufr" value="${recordTopic.sveh_vufr}">
			        <input type="hidden" name="session_sveh_sydt" id="session_sveh_sydt" value="${recordTopic.sveh_sydt}">
			        
			        
			        <%-- Session fields needed for the AJAX calculation av YKoder - Bilagdahandlingar --%>
				 	<input type="hidden" name="session_sveh_aube" id="session_sveh_aube" value="${recordTopic.sveh_aube}">
				 	
				 	<input type="hidden" name="svev_syli" id="svev_syli" value=''/>
				 	<input type="hidden" name="svev_vano" id="svev_vano" value=''/>
				 	
				 	<input type="hidden" name="numberOfItemLinesInTopic" id="numberOfItemLinesInTopic" value='${numberOfItemLinesInTopic}' />
				 	
	 		<table style="width:100%">
	 		
           	<tr>
	 			<td >
	 				
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="100%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text14White">
				 				<b>&nbsp;&nbsp;Varupost&nbsp;</b>
								<img onClick="showPop('updateInfo');" src="resources/images/update.gif" border="0" alt="edit">
				 				<span style="position:absolute; left:150px; top:200px; width:800px; height:400px;" id="updateInfo" class="popupWithInputText"  >
					           		<div class="text14" align="left">
					           			<br/>${activeUrlRPGUpdate}<br/><br/>
					           			<button name="updateInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('updateInfo');">Close</button> 
					           		</div>
						        </span>				 				
				 				
				 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 				<c:choose>
					 				<c:when test="${not empty model.record.svev_syli}">
				 						<input title="from model" tabindex=-1 align="center" class="text14BoldLightGreenForItemLinenr" readonly type="text" name="lineNr" id="lineNr" size="3" value="${model.record.svev_syli}">
				 					</c:when>
				 					<c:otherwise>
				 						<c:choose>
						 					<c:when test="${not empty svev_syli_SESSION}">
					 							<input title="from session" tabindex=-1 align="center" class="text14BoldLightGreenForItemLinenr" readonly type="text" name="lineNr" id="lineNr" size="3" value="${svev_syli_SESSION}">
					 						</c:when>
					 						<c:otherwise>
					 							<input title="from session" tabindex=-1 align="center" class="text14BoldLightGreenForItemLinenr" readonly type="text" name="lineNr" id="lineNr" size="3" value="Ny">
					 						</c:otherwise>
				 						</c:choose>
				 					</c:otherwise>
				 				</c:choose>
			 				</td>
		 				</tr>
	 				</table>
					<table width="100%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15"><td class="text" align="left"></td></tr>
				 		<tr>
					 		<td>
						 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
							 		<tr>
							 			<td>&nbsp;</td>
							 			<td class="text14" align="left">
							 			<b>&nbsp;34a.</b>&nbsp;<span title="svev_ulkd">Ursp.land</span>
										</td>
							 			
							 			
							 			<td class="text14" align="left">
							            <img onMouseOver="showPop('33_info');" onMouseOut="hidePop('33_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>33.</b><font class="text16RedBold" >*</font><span title="svev_vata">Varukod</span>
							            
							            
							            <div class="text14" style="position: relative;" align="left">
							            <span style="position:absolute;top:2px; width:250px;" id="33_info" class="popupWithInputText text14"  >
							           		<b>Varukod</b>
						           			<br/>
						           			I första delfältet anger du en varukod (KN-nummer) för varuposten. Koden för export består av åtta siffror. 
											<br/><br/>
						           			Tredje och fjärde delfältet är till för tilläggskoder som ska anges i vissa fall, exempelvis för varor som kan vara föremål för exportrestriktioner eller varor du söker exportbidrag för. 
						           			<br/><br/>
						           			Du hittar information om varu- och tilläggskoder i Taric Söksystem. 
						           			
										</span>
										</div>
							 			</td>
							 			
							            <td class="text14" align="left"><b>&nbsp;<span title="svev_vati">33.3</span></b>&nbsp;&nbsp;</td>
							            <td class="text14" align="left"><b>&nbsp;<span title="svev_vatu">33.4</span></b>&nbsp;&nbsp;</td>
							            <td class="text14" align="left">
							            <img onMouseOver="showPop('37_1_info');" onMouseOut="hidePop('37_1_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>37.</b>
							            <font class="text16RedBold" >*</font><span title="svev_eup1">Förf. 1</span>
							            <div class="text14" style="position: relative;" align="left">
							            <span style="position:absolute;top:2px; width:250px;"id="37_1_info" class="popupWithInputText text14"  >
							           		<br>
						           			<b>Förfarande - Första delfältet</b>
						           			<br/>
						           			Här ska du ange en fyrställig kod som består av två tvåställiga koder. Koden visar vilket exportförfarande som avses. De två första siffrorna anger det förfarande som begärs och de två sista siffrorna det föregående förfarandet.
						           			<br/><br/>
						           			Exempel kod 1000<br/>
						           			De två första siffrorna i koden visar det tullförfarande som du anmäler varorna till. 10 står för permanent export. De två sista siffrorna i koden visar om varorna tidigare varit föremål för ett tullförfarande. 00 innebär att det inte finns något föregående tullförfarande.   
						           			<br/><br/>
						           			Exempel kod 1040<br/>
						           			De två första siffrorna i koden visar det tullförfarande som varorna anmäls till. 10 står för permanent export. De två sista siffrorna i koden visar om varorna tidigare varit föremål för ett tullförfarande. I det här fallet visar 40 att varorna tidigare har importerats.     
						           			<br/><br/>
						           			<b>Förteckning över koderna</b>
						           			<ul>
							           			<li><b>1000</b>&nbsp;Permanent export utan något föregående förfarande.</li>
							           			<li><b>1040</b>&nbsp;Permanent export där varan tidigare har övergått till fri omsättning och fri førbrukning.Denna kod används huvudsakligen då en vara returneras till avsändaren.</li>
							           			<li><b>1076</b>&nbsp;Slutgiltig Export efter Placering i tullager eller frizon, med förskottsbetalning av exportbidrag,av produkter och varor som är avsedda att exporteras i oförändrad skick</li>
							           			<li><b>2100</b>&nbsp;Temporär export</li>
							           			<li><b>2300</b>&nbsp;Temporär export utan föregående förfarande där avsikten är att varan skall returneras oförändrad. </li>
							           			<li><b>3151</b>&nbsp;Återexport av varor som har varit föremål för aktiv förädling.</li>
							           			<li><b>3153</b>&nbsp;Återexport av varor som har varit föremål för temporär import.</li>
							           			<li><b>3171</b>&nbsp;Återexport av varor som har lagrats på tullager.</li>
							           			
							           		</ul>
							           		
										</span>
							            </div>
							            </td>
							            
							            <td class="text14" align="left">
							            <img onMouseOver="showPop('37_2_info');" onMouseOut="hidePop('37_2_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>37.</b>
							            <span title="svev_eup2">Förf. 2</span>
							            
							            <div class="text14" style="position: relative;" align="left">
							            <span style="position:absolute;top:2px; width:250px;"id="37_2_info" class="popupWithInputText text14"  >
							           		<br>
						           			<b>Förfarande - Andra delfältet</b>
						           			Vid vissa förfarande måste du ange ytterligare en kod i 37:2 som komplement till fält 37:1. Vissa koder har fastställts av kommissionen men även nationella koder får förekomma. Koden består av tre tecken. Om ingen lämplig kod finns lämnas fältet tomt.   
											Koder fastställda av kommissionen består av en bokstav och två siffror.
						           			<ul>
							           			<li><b>Axx</b> Återexport efter aktiv förädling</li>
							           			<li><b>Bxx</b> Export för passiv förädling </li>
							           			<li><b>Exx</b> Export av jordbruksprodukter för vilka exportbidrag söks </li>
							           			<li><b>Fxx</b> Andra (export för militär användning eller proviantering) </li>
							           		</ul>
							           		
							           		Nationella koder kan förekomma och består av två siffror och en bokstav. För närvarande finns inga nationella koder på exporten.   
											<br/><br/>
											Du hittar koderna i vår kodförteckning i Taric Söksystem.
							           		
										</span>
							            </div>
							            </td>
							            
							            <td class="text14" align="left">
							            	<img onMouseOver="showPop('35_info');" onMouseOut="hidePop('35_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 							<b>35.</b>&nbsp;<font class="text16RedBold" >*</font><span title="svev_brut">Brut.vikt(kg)</span>
				 						
				 						
				 						<div class="text14" style="position: relative;" align="left">
							            <span style="position:absolute;top:2px; width:250px;" id="35_info" class="popupWithInputText text14"  >
							           		<div class="text14" align="left">
						           			<br/>
						           			<b>Bruttovikt</b><br/>
						           			Bruttovikten anger du antingen för respektive varupost eller för hela sändningen. Du ska inte ange enheten kg. Bruttovikten är varornas totala vikt inklusive allt emballage, men exklusive containrar och annan transportutrustning.
											<br/>
											För bruttovikt som överstiger 1 kg och inbegriper del av kg kan följande avrundning ske:
											<ul>
							           			<li>mellan 0,001 och 0,499 kg: avrundning neråt (kg) </li>
							           			<li>mellan 0,5 och 0,999 kg: avrundning uppåt (kg)</li>
							           		</ul>
						           			Vi rekommenderar att bruttovikt över 1 kg avrundas till heltal. Bruttovikt under 1 kg ska anges med tre decimaler, exempelvis 0.123. Värdet 0 är inte tillåtet. Punkt anges som decimaltecken.
						           			<br/><br/>
						           			<b>Bruttovikt per varupost</b>
						           			Fältet är obligatoriskt vid återexport efter lagring i tullager, om förfarandekod i fält 37:1 är 3171. I övriga fall är det obligatoriskt att ange bruttovikten per varupost om du inte fyllt i fältet "Total bruttovikt" med följande undantag:
						           			<ul>
							           			<li>Du som angett koden "E" (Godkända ekonomiska aktörer) i fältet S32, (Särskild omständighet, kod), behöver inte ange bruttovikt per varupost, om det inte rör sig om återexport enligt ovan.</li>
							           			<li>Bruttovikt per varupost kan också utelämnas vid utförsel till ett område inom gemenskapens tullområde som inte tillhör skatteområdet, exempelvis Åland.</li>
							           		</ul>
							           		
										</span>
										</div>
										</td>
										
										<td class="text14" align="left"><b>&nbsp;38.</b>&nbsp;<font class="text16RedBold" >*</font><span title="svev_neto">Net.vikt(kg)</span></td>
										
							        </tr>
							        <tr>
							        	<td align="left" >&nbsp;<button tabIndex=-1 title="Kundens vareregister" name="kundensVaruregisterControlButton" id="kundensVaruregisterControlButton" class="buttonGrayWithGreenFrame" type="button" >Sök i kund.varureg.</button></td>
							        	<td align="left">
							            	<select class="inputTextMediumBlue" name="svev_ulkd" id="svev_ulkd">
						 						<option value="">-Välj-</option>
							 				  	<c:forEach var="country" items="${model.gcyCodeList}" >
							 				  		<option value="${country.svkd_kd}"<c:if test="${model.record.svev_ulkd == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
												</c:forEach>  
											</select>
											<a tabindex="-1" id="svev_ulkdIdLink" >
						            			<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
						            		</a>
										</td>
							            <td class="text14" align="left">
							            	<a id="tillaggskoderLink"></a>
							            	<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="svev_vata" id="svev_vata" size="10" maxlength="10" value='${model.record.svev_vata}'>
							            	<a tabindex="-1" id="svev_vataIdLink">
           										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
           									</a>
						            	</td>
										<td class="text14" align="left"><input type="text" class="inputTextMediumBlue" name="svev_vati" id="svev_vati" size="8" maxlength="4" value='${model.record.svev_vati}'></td>
										<td class="text14" align="left"><input type="text" class="inputTextMediumBlue" name="svev_vat4" id="svev_vat4" size="8" maxlength="4" value='${model.record.svev_vat4}'></td>
										<td align="left">	
						 					
											<select class="inputTextMediumBlueMandatoryField" name="svev_eup1" id="svev_eup1">
						 						<option value="">-Välj-</option>
							 				  	<c:forEach var="code" items="${model.forfarande01CodeList}" >
							 				  		<c:choose>
														<c:when test="${not empty model.record.svev_eup1}">
								 				  			<option value="${code.svkd_kd}"<c:if test="${model.record.svev_eup1 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
								 				  		</c:when>
								 				  		<c:otherwise>
								 				  			<option value="${code.svkd_kd}"<c:if test="${'1000' == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
								 				  		</c:otherwise>
							 				  		</c:choose>
												</c:forEach>  
											</select>
											
											<a tabindex="-1" id="svev_eup1IdLink" >
						            			<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
					            			</a>
											
						 				</td>
						 				<td align="left">	
						 					<select class="inputTextMediumBlue" name="svev_eup2" id="svev_eup2" >
							 				  <option value="">-Välj-</option>
											  <c:forEach var="code" items="${model.forfarande02CodeList}" >
			 				  					 <option value="${code.svkd_kd}"<c:if test="${model.record.svev_eup2 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
											  </c:forEach> 
											</select>
											<a tabindex="-1" id="svev_eup2IdLink" >
						            			<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
					            			</a>
						 				</td>
						 				<td class="text14" align="left"><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="svev_brut" id="svev_brut" size="10" maxlength="11" value='${model.record.svev_brut}'></td>
										<td class="text14" align="left"><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="svev_neto" id="svev_neto" size="10" maxlength="11" value='${model.record.svev_neto}'></td>
										
							        </tr>
							        <tr height="10"><td class="text" align="left" colspan="9"><hr></td></tr>
							        
									<tr>
										<td class="text14" align="left">
											<img onMouseOver="showPop('41_xmenheter_info');" onMouseOut="hidePop('41_xmenheter_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
												
											<b>41.</b><span title="svev_ankv">Extra mängd:&nbsp;</span>
											<div class="text14" style="position: relative;" align="left">
								            <span style="position:absolute;top:2px; width:250px;" id="41_xmenheter_info" class="popupWithInputText text14"  >
								           		<br/>
							           			<b>41. Extra mängdenheter</b>
												<br/>
							           			Detta fält fyller du bara i för vissa varukoder. Det är varukoden i fält 33 som styr om fältet ska fyllas i eller inte. I Tulltaxan ser du om uppgiften ska deklareras samt vilken mängdenhet du ska ange. Mängdenheten kan exempelvis vara styck, liter eller kubikmeter. Du anger uppgiften i heltal men ska inte ange enheten.
							           			
											</span>
								            </div>
										</td>
										<td class="text14">
										<img onMouseOver="showPop('31_kantal_info');" onMouseOut="hidePop('31_kantal_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										<span id="kotaRubrik">
				 							<b>31.</b>
				 						</span>
										<span title="svev_kota">Kolli antal</span>
										
										<div class="text14" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="31_kantal_info" class="popupWithInputText text14"  >
							           		<b>Kolliantal</b>
						           			<br/>
						           			Antal kolli/delar 
											<br/>
						           			Ange antal kolli för emballerade varor och antal delar för oemballerade varor. 
						           			<br/>Antal delar för oemballerat gods kan aldrig vara noll. 
						           			<br/>Om kollislag avser bulkvaror anges inget kollital. Om kod för bulk (kollislag kod VR, VY, VO, VL, VG OCH VQ) anges på varupost/varuposter i fält 31, ska varje bulkkod räknas som ett kolli när du summerar uppgiften totalt antal kollin på huvudnivå, fält 6. 
						           			<br/><br/>
						           			<b>OBS</b>&nbsp;Om nr <b><font style="color:green;">31.</font></b>Kolli antal lyser grönt indikerar det för mera kolli antal under extraordinära uppgifter nedan i samma bild (under rubrik 31).
										</span>
							            </div>
							            </td>
							            
										<td class="text14">
										<img onMouseOver="showPop('31_kslag_info');" onMouseOut="hidePop('31_kslag_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>31.</b><font class="text16RedBold" >*</font><span title="svev_kosl">Kolli slag</span>
										
										<div class="text14" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="31_kslag_info" class="popupWithInputText text14"  >
							           		<br/>
						           			<b>Kollislag</b>
											<br/>
						           			Här anger du kod för hur godset är förpackat, exempelvis kartonger. Om en vara är förpackad i flera olika kollislag kan du ange flera kollislag i samma varupost. Till detta hör även antal kolli och godsmärkning.
					           				
										</span>
										</div>
										</td>
							            
										<td class="text14" align="left" colspan="2">
										<img onMouseOver="showPop('31_varubesk_info');" onMouseOut="hidePop('31_varubesk_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>31.</b><font class="text16RedBold" >*</font><span title="svev_vasl / svev_vas2...svev_vas5">Varubeskrivning&nbsp;</span>
										<div class="text14" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="31_varubesk_info" class="popupWithInputText text14"  >
							           		<br/>
						           			<b>Varubeskrivning</b>
											<br/>
						           			Här anger du varubeskrivning, det vill säga den normala handelsbenämningen för varan. Denna beskrivning ska vara så noggrann att varan kan klassificeras.
						           			<br/><br/>
						           			<b>Flera Varuposter</b>
											<br/>
						           			Om ett eller flera kolli, med samma kollislag och godsmärkning, innehåller flera olika varor som ska deklareras på flera varuposter, kan du ange hela kolliantalet på en varupost (dvs. fältet "Antal kolli" måste vara större än "0"). På nästa varupost/varuposter anger du då "0" i fältet "Antal kolli". För oemballerat gods (förpackningskoder NE, NF och NG) kan du dock aldrig ange 0 i antal kolli/delar. 
											<br/><br/>
						           			Exempel: Varupost 1
						           			<ul>
							           			<li>"Del av kolli" + godsmärkning</li>
							           			<li>Antal kolli = 1</li>
							           			<li>Kollislagets förpackningskod</li>
							           			<li>Varubeskrivning</li>
							           		</ul>
						           			<br/>
						           			Exempel: Varupost 2
						           			<ul>
							           			<li>"Del av kolli" + godsmärkning (samma som varupost 1)</li>
							           			<li>Antal kolli = 0</li>
							           			<li>Kollislagets förpackningskod (samma som varupost 1)</li>
							           			<li>Varubeskrivning</li>
							           			
							           		</ul>							           			
						           			
										</span>
										</div>
										</td>
							            <td>&nbsp;</td>
										<td class="text14" align="left" colspan="2">
							            <img onMouseOver="showPop('31_gods_info');" onMouseOut="hidePop('31_gods_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>31.</b>
							            <font class="text16RedBold" >*</font><span title="svev_godm">Godsmärkning&nbsp;</span>
							            
							            <div class="text14" style="position: relative;" align="left">
							            <span style="position:absolute;top:2px; width:250px;" id="31_gods_info" class="popupWithInputText text14"  >
							           		<br/>
						           			<b>Godsmärkning</b>
											<br/>
						           			Här anger du hur de kollin som varorna är förpackade i är märkta, obligatoriskt för emballerat gods. Fältet är frivilligt för bulkvaror och oemballerat gods.
						           			
										</span>
							            </div>
							            </td>
							            
							            <td class="text14" align="left"><b></b>&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="svev_fabl">Varans pris&nbsp;</span></td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							        </tr>
							        
							        
									<tr>
										<td class="text14" align="left">&nbsp;
											<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="svev_ankv" id="svev_ankv" size="10" maxlength="10" value='${model.record.svev_ankv}'>
											
										</td>
										<td class="text14" valign="bottom">
											<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="svev_kota" id="svev_kota" size="5" maxlength="5" value="${model.record.svev_kota}">
										</td>
										<td align="left">
					            			<select class="inputTextMediumBlueMandatoryField" name="svev_kosl" id="svev_kosl">
							            		<option value="">-Välj-</option>
							 				  	<c:forEach var="code" items="${model.kolliCodeList}" >
			                                	 	<option value="${code.svkd_kd}"<c:if test="${model.record.svev_kosl == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
												</c:forEach> 
											</select>
											<a tabindex="-1" id="svev_koslIdLink">
						            			<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
					            			</a>
											
				            				</td> 										
										<td class="text14" align="left" colspan="2">
											<input type="text" class="inputTextMediumBlueMandatoryField" name="svev_vasl" id="svev_vasl" size="25" maxlength="70" value="${model.record.svev_vasl}">
										</td>
										<td>
											<button name="itemDescriptionExtraInformationButton" class="buttonGray" type="button" onClick="showPop('itemDescriptionExtraInformation');" >Flera varubesk.</button>
											<span style="position:absolute; left:280px; top:500px; width:350px; height:300px;" id="itemDescriptionExtraInformation" class="popupWithInputText"  >
								           		<div class="text10" align="left">
								           			<table>
								           				<tr>
										           			<td class="text14">
																<b>Flera varubeskrivningar</b>
															</td>
														</tr>
														
									           			<tr>
										           			<td class="text14">
																&nbsp;31.2 Varubeskrivning<input type="text" class="inputTextMediumBlue" name="svev_vas2" id="svev_vas2" size="35" maxlength="70" value="${model.record.svev_vas2}">
															</td>
														</tr>
														<tr>
										           			<td class="text14">
																&nbsp;31.3 Varubeskrivning<input type="text" class="inputTextMediumBlue" name="svev_vas3" id="svev_vas3" size="35" maxlength="70" value="${model.record.svev_vas3}">
															</td>
														</tr>
														<tr>
										           			<td class="text14">
																&nbsp;31.4 Varubeskrivning<input type="text" class="inputTextMediumBlue" name="svev_vas4" id="svev_vas4" size="35" maxlength="70" value="${model.record.svev_vas4}">
															</td>
														</tr>
														<tr>
										           			<td class="text14">
																&nbsp;31.5 Varubeskrivning<input type="text" class="inputTextMediumBlue" name="svev_vas5" id="svev_vas5" size="35" maxlength="70" value="${model.record.svev_vas5}">
															</td>
														</tr>
														
								           			</table>
													<table width="100%" align="left" border="0">
														<tr align="left" >
															<td class="text14"><button name="itemDescriptionExtraInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('itemDescriptionExtraInformation');">&nbsp;Ok</button> 
															</td>
														</tr>
													</table>
												</div>
											</span>
										</td>
										<td class="text14" align="left" colspan="2"><input type="text" class="inputTextMediumBlueMandatoryFieldUPPERCASE" name="svev_godm" id="svev_godm" size="25" maxlength="42" value="${model.record.svev_godm}"></td>
							            <td class="text14" align="left"><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="svev_fabl" id="svev_fabl" size="11" maxlength="11" value="${model.record.svev_fabl}"></td>
							            <td></td>
 							        </tr>
 							        
									<tr height="25"><td></td></tr>
									
									<tr>
									<td colspan="3">
										<table class="tableBorderWithRoundCornersGray">
								 		<tr>
								            <td class="text14Bold" align="left" >&nbsp;Statistiska uppgifter&nbsp;</td>
								            <td class="text14" align="left" >&nbsp;</td>
								        </tr>
								        <tr>
								            <td class="text14" align="left">
								            &nbsp;<img onMouseOver="showPop('46_info');" onMouseOut="hidePop('46_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 						<b>46.</b>
								            <font class="text16RedBold" >*</font><span title="svev_stva">Statistiskt värde:&nbsp;</span>
								            
								            <div class="text14" style="position: relative;" align="left">
								            <span style="position:absolute;top:2px; width:250px;" id="46_info" class="popupWithInputText text14"  >
								           		<br/>
							           			<b>Statistiskt värde</b>
												<br/>
							           			Här anger du i heltal det statistiska värdet för varuposten. Observera att du ska ange värdet i svenska kronor. Du ska inte ange valutaslag SEK utan enbart siffror. Statistiskt värde är den totala kostnaden för varan vid svensk gräns.   
												<br/><br/>
												Du beräknar värdet på följande sätt:<br/>
												Om varorna säljs - det fakturerade beloppet för varorna samt kostnader fram till svensk gräns. I övriga fall - till exempel för varuprover, det belopp varorna skulle ha sålts för om det istället varit en försäljning.   
												<br/><br/>
												Extrakostnader som exempelvis transport- och försäkringskostnader tas med i värdet så långt de är kända (får även uppskattas) när deklarationen inges och endast för den del av kostnaderna som gäller till svensk gräns.   
												<br/><br/>
												Om din faktura är i en annan valuta måste du räkna om värdet till svenska kronor. Du ska då använda antingen Tullverkets växelkurs eller växelkursen vid exporttillfället.   
												<br/><br/>
												Exporterar du varor som genomgått en förädlingsprocess (aktiv förädling) ska det statistiska värdet fastställas som om dessa varor helt producerats här.   
												
											</span>
											</div>
											</td>
											
								           	<td class="text14" align="left"><input tabindex= -1 readonly type="text" class="inputTextReadOnly" name="svev_stva" id="svev_stva" size="10" value="${model.record.svev_stva}"></td>
								        </tr>
								        
								        <tr>
								            <td class="text14" align="left">
								            &nbsp;<img onMouseOver="showPop('tullvarde_info');" onMouseOut="hidePop('tullvarde_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 						<font class="text16RedBold" >*</font><span title="svev_stva2">Tullvärde:&nbsp;</span>
								            
								            <div class="text14" style="position: relative;" align="left">
								            <span style="position:absolute;top:2px; width:250px;" id="tullvarde_info" class="popupWithInputText text14"  >
								           		<br/>
							           			<b>Tullvärde</b>
												<br/>
							           			Här anger du tullvärdet för varuposten. 
												<br/><br/>
												Tullvärde är den summa som du beräknar tullbeloppet på, och tullvärde är också underlag för beräkning av moms (tullvärde + tull= beskattningsunderlag). 
												<br/><br/>I tullvärdet ska, förutom priset på varan, även ingå andra kostnader som du haft eller kommer att få, för att importera din vara och som inte
												redan ingår i priset. De vanligaste exemplen är frakt- och försäkringskostnader och kostnader för lossning och lastning som uppkommer fram till platsen för införsel till EU. <br/><br/>
												
											</span>
											</div>
											</td>
											
								           	<td class="text14" align="left"><input tabindex= -1 readonly type="text" class="inputTextReadOnly" name="svev_stva2" id="svev_stva2" size="10" value="${model.record.svev_stva2}"></td>
								        </tr>
								        <tr height="5"><td></td></tr>
										</table>
									</td>
									
							 		<td valign="top" colspan="5">
								 		<table border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td class="text14Bold" align="left" >
						 						&nbsp;<img onMouseOver="showPop('callme_info');" onMouseOut="hidePop('callme_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 						<font class="text16RedBold" >*</font><span title="svev_call">Call me:&nbsp;</span>
									            
									            <div class="text14" style="position: relative;" align="left">
									            <span style="position:absolute;top:2px; width:250px;" id="callme_info" class="popupWithInputText text14"  >
									           		<br/>
								           			<b>Åtgärdsindikator (Call me-kod)</b>
													<br/>
								           			Fält 44 består av två delfält, ett fritextfält och ett kodfält. Vissa uppgifter ska du alltid fylla i då du deklarerar enligt godkänd exportör, medan ytterligare upplysningar lämnar du vid behov. Det kan exempelvis vara ett tillståndsnummer eller ett licensnummer för exportreglerade varor.
													<br/><br/>
													Call me är en funktion som används för att upplysa om en sändning omfattas av export/importrestriktioner eller inte. 
													Uppgift om en vara omfattas av export- eller importrestriktioner finns i EU-lagstiftning eller i nationell lagstiftning. 
													I många fall hittar du information om restriktioner i Taric Söksystem för aktuell varukod. 
													Call me-koden kan också användas för att påkalla en tulltjänstemans uppmärksamhet i samband med klareringen.
													
												</span>
												</div>
												</td>
									            
									            <td class="text14" align="left" >
									            	<select class="inputTextMediumBlueMandatoryField" name="svev_call" id="svev_call">
						 								<option value="00" <c:if test="${model.record.svev_call == '00' || empty model.record.svev_call}"> selected </c:if> >00-CM Inga exportrestr.</option>
										  				<option value="01" <c:if test="${model.record.svev_call == '01'}"> selected </c:if> >01-CM Exportrestr. villkor uppfyllda</option>
										  				<option value="10" <c:if test="${model.record.svev_call == '10'}"> selected </c:if> >10-CM Inga exportrestr. Call me</option>
										  				<option value="11" <c:if test="${model.record.svev_call == '11'}"> selected </c:if> >11-CM Exportrestr. villkor uppfyllda Call me</option>
										  				<option value="12" <c:if test="${model.record.svev_call == '12'}"> selected </c:if> >12-CM Exportrestr. villkor ej uppfyllda</option>
										  				<option value="13" <c:if test="${model.record.svev_call == '13'}"> selected </c:if> >13-CM Exportlicens</option>
										  				<option value="14" <c:if test="${model.record.svev_call == '14'}"> selected </c:if> >14-CM Exportlicens Call me</option>
										  				<option value="15" <c:if test="${model.record.svev_call == '15'}"> selected </c:if> >15-CM Ansökan om vissa Tullverkets tillstånd</option>
										  				<option value="16" <c:if test="${model.record.svev_call == '16'}"> selected </c:if> >16-CM Exp.bidragsärende som ska kompletteras. Kontakta exp.bidragsgrp. för klarering</option>
													</select>
												</td>
									        </tr>
									        <tr height="10"><td></td></tr>
									        <tr>
										        <td colspan="2" align="left">
												<c:choose>	
													<c:when test="${model.status == 'M' || empty model.status}">
														<input class="inputFormSubmit" type="button" name="btnItemsSave" id="btnItemsSave" value='<spring:message code="systema.tds.export.item.createnew.submit"/>'>
													</c:when>
													<c:otherwise>
							 				    			<input disabled class="inputFormSubmitGrayDisabled" type="button" name="btnItemsSave" value='Ej uppdaterbart'/>
							 				    		</c:otherwise>	
							 				    	</c:choose>	
												</td>
											</tr>
											
											<tr align="left">								        
	   							 			<td colspan="10">
												<div style="float:left;" id="warningCodesFlagDiv" >
													<a id="warningCodesLink" style="cursor:pointer;">
														<img width="18px" height="20px" src="resources/images/redFlag.png" width="18px" height="18px" border="0" alt="warning">
														<font class="text14Red">Det finns Tilläggskoder att välja. Klicka här</font>
													</a>
												</div>				
											</td>
								        </tr>
								        
								        <tr align="left">								        
	   							 			<td colspan="10">
												<div style="float:left;" id="warningCodesFlagDivBh" >
													<a id="warningCodesLinkBh" style="cursor:pointer;">
														<img width="18px" height="20px" src="resources/images/redFlag.png" width="18px" height="18px" border="0" alt="warning">
														<font class="text14Red">Det finns Bilagda Handlingar att välja. Klicka här</font>
													</a>
												</div>				
											</td>
								        </tr>
										</table>
									</td>
									</tr>
									<tr height="5"><td></td></tr>
									
									
									
									
 			        	        </table>
					        </td>
					        
				        </tr>
				        <tr height="10"><td></td></tr>
				        
				        <tr>
							<td>
							<table width="80%" class="tableBorderWithRoundCornersGray">
							        <tr >
						            <td colspan="10" class="text14">
						            &nbsp;<img onMouseOver="showPop('44a_info');" onMouseOut="hidePop('44a_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 						<b>44.</b>Särskilda upplysningar/Bilagda handlingar/Certifikat och tillstånd
						            
						            <div class="text14" style="position: relative;" align="left">
						            <span style="position:absolute;top:2px; width:250px;" id="44a_info" class="popupWithInputText text14"  >
						           		<br/>
					           			<b>Särskilda upplysningar</b>
										<br/>
					           			Fält 44 består av två delfält, ett fritextfält och ett kodfält. Vissa uppgifter ska du alltid fylla i då du deklarerar enligt godkänd exportör, medan ytterligare upplysningar lämnar du vid behov. Det kan exempelvis vara ett tillståndsnummer eller ett licensnummer för exportreglerade varor.
										<br/><br/>
										Särskilda upplysningar (Kod)<br/>
										I vissa fall ska du ange särskilda upplysningar. 
										<br/><br/>
										<b>Bilagda handlingar/Certifikat och tillstånd </b>
										<br/>
										Här ska du ange en eller flera koder följda av ett identitetsnummer för de handlingar, certifikat eller tillstånd som ligger till grund för deklarationen. Koden består av fyra tecken och kan bestå av både bokstäver och siffror. Kod för faktura och identitetsnummer är obligatoriska uppgifter.    
										<br/><br/>
										Du ska även ange ett transportdokumentnummer. I de fall du inte skrivit något i fältet "Kommersiellt referensnummer", är transportdokumentnumret en obligatorisk uppgift. Transportdokumentnummer finns förklarat under rubriken " Transportdokumentnummer".
										
									</span>
							    	</div>
							    	</td>
							        </tr>
									
									<tr height="5"><td></td></tr>
							        <tr>
							            <td valign="top" class="text14" align="left">
						           			<table >
						           				<tr>
								           			<td class="text14" colspan="3">
								           				<span title="svev_bit1-svev_bii1 / svev_bit2-svev_bii2 / etc"><b>Bilagda handlingar</b></span>
								           				<a tabindex="-1" id="bilagdaHandIdLink">
		            										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
		            									</a>
								           			</td>
								        			</tr>	
							           			<tr>
								           			<td class="text14">
								           				&nbsp;1.Kod
								           				<select class="inputTextMediumBlue" name="svev_bit1" id="svev_bit1">
									 						<option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.mcfCodeList}" >
										 				  		<c:choose>
																	<c:when test="${not empty model.record.svev_bit1}">
											 				  			<option value="${code.svkd_kd}"<c:if test="${model.record.svev_bit1 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
											 				  		</c:when>
											 				  		<c:otherwise>
											 				  			<option value="${code.svkd_kd}"<c:if test="${recordTopic.sveh_faty == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
											 				  		</c:otherwise>
											 				  	</c:choose>
															</c:forEach>  
														</select>
								           			</td>
								           			<td class="text14">&nbsp;<span title="svev_bii1">Identitet</span>
														<c:choose>
															<c:when test="${not empty model.record.svev_bii1}">
																<input type="text" class="inputTextMediumBlue" name="svev_bii1" id="svev_bii1" size="35" maxlength="35" value="${model.record.svev_bii1}">
															</c:when>
															<c:otherwise>
																<input type="text" class="inputTextMediumBlue" name="svev_bii1" id="svev_bii1" size="35" maxlength="35" value="${recordTopic.sveh_fatx}">
															</c:otherwise>
														</c:choose>
													</td>
												</tr>
												<tr>	
								           			<td class="text14">
														&nbsp;2.Kod
								           				<select class="inputTextMediumBlue" name="svev_bit2" id="svev_bit2">
									 						<option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.mcfCodeList}" >
										 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.svev_bit2 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
															</c:forEach>  
														</select>
													</td>
													<td class="text14">
								           				&nbsp;Identitet<input type="text" class="inputTextMediumBlue" name="svev_bii2" id="svev_bii2" size="35" maxlength="35" value="${model.record.svev_bii2}">
								           			</td>
												</tr>
												<tr>
								           			<td class="text14">
								           				&nbsp;3.Kod
								           				<select class="inputTextMediumBlue" name="svev_bit3" id="svev_bit3">
									 						<option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.mcfCodeList}" >
										 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.svev_bit3 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
															</c:forEach>  
														</select>
								           			</td>
								           			<td class="text14">
								           				&nbsp;Identitet<input type="text" class="inputTextMediumBlue" name="svev_bii3" id="svev_bii3" size="35" maxlength="35" value="${model.record.svev_bii3}">
								           			</td>
								           		</tr>
								           		<tr>	
							           				<td class="text14">
								           				&nbsp;4.Kod
								           				<select class="inputTextMediumBlue" name="svev_bit4" id="svev_bit4">
									 						<option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.mcfCodeList}" >
										 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.svev_bit4 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
															</c:forEach>  
														</select>
								           			</td>
								           			<td class="text14">
								           				&nbsp;Identitet<input type="text" class="inputTextMediumBlue" name="svev_bii4" id="svev_bii4" size="35" maxlength="35" value="${model.record.svev_bii4}">
								           			</td>
							           			</tr>
							           			<tr>
								           			<td class="text14">
								           				&nbsp;5.Kod
								           				<select class="inputTextMediumBlue" name="svev_bit5" id="svev_bit5">
									 						<option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.mcfCodeList}" >
										 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.svev_bit5 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
															</c:forEach>  
														</select>
								           			</td>
								           			<td class="text14">
								           				&nbsp;Identitet<input type="text" class="inputTextMediumBlue" name="svev_bii5" id="svev_bii5" size="35" maxlength="35" value="${model.record.svev_bii5}">
								           			</td>
							           			</tr>
							           			<tr>										           			
								           			<td class="text14">
								           				&nbsp;6.Kod
								           				<select class="inputTextMediumBlue" name="svev_bit6" id="svev_bit6">
									 						<option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.mcfCodeList}" >
										 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.svev_bit6 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
															</c:forEach>  
														</select>
								           			</td>
								           			<td class="text14">
								           				&nbsp;Identitet<input type="text" class="inputTextMediumBlue" name="svev_bii6" id="svev_bii6" size="35" maxlength="35" value="${model.record.svev_bii6}">
								           			</td>
							           			</tr>
							           			<tr>
								           			<td class="text14">
								           				&nbsp;7.Kod
								           				<select class="inputTextMediumBlue" name="svev_bit7" id="svev_bit7">
									 						<option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.mcfCodeList}" >
										 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.svev_bit7 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
															</c:forEach>  
														</select>
								           			</td>
								           			<td class="text14">
								           				&nbsp;Identitet<input type="text" class="inputTextMediumBlue" name="svev_bii7" id="svev_bii7" size="35" maxlength="35" value="${model.record.svev_bii7}">
								           			</td>
								           		</tr>
								           		<tr>	
								           			<td class="text14">
								           				&nbsp;8.Kod
								           				<select class="inputTextMediumBlue" name="svev_bit8" id="svev_bit8">
									 						<option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.mcfCodeList}" >
										 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.svev_bit8 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
															</c:forEach>  
														</select>
								           			</td>
								           			<td class="text14">
								           				&nbsp;Identitet<input type="text" class="inputTextMediumBlue" name="svev_bii8" id="svev_bii8" size="35" maxlength="35" value="${model.record.svev_bii8}">
								           			</td>
							           			</tr>
							           			<tr>
								           			<td class="text14">
								           				&nbsp;9.Kod
								           				<select class="inputTextMediumBlue" name="svev_bit9" id="svev_bit9">
									 						<option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.mcfCodeList}" >
										 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.svev_bit9 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
															</c:forEach>  
														</select>
								           			</td>
								           			<td class="text14">
								           				&nbsp;Identitet<input type="text" class="inputTextMediumBlue"  name="svev_bii9" id="svev_bii9" size="35" maxlength="35" value="${model.record.svev_bii9}">
								           			</td>
							           			</tr>
						           			</table>
										</td>
							            <td valign="top">
						           			<table>
						           				<tr>
								           			<td class="text14" colspan="2">
								           				<b>Särskilda upplysningar</b>
								           				<a tabindex="-1" id="sarskildaUppIdLink">
		            										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
		            									</a>
								           			</td>
								        			</tr>
							           			<tr>
								           			<td class="text14" >
								           				&nbsp;<span title="svev_suko">1.Kod</span>
								           				<select class="inputTextMediumBlue" name="svev_suko" id="svev_suko">
									 						<option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.salCodeList}" >
										 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.svev_suko == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
															</c:forEach>  
														</select>
								           			</td>
								           			<td class="text14" >
								           				&nbsp;
								           			</td>
												</tr>
												<tr>
													<td colspan="2" class="text14">
														&nbsp;<span title="svev_sutx">1.Text</span><input type="text" class="inputTextMediumBlue" name="svev_sutx" id="svev_sutx" size="35" maxlength="70" value="${model.record.svev_sutx}">
													</td>
												</tr>
												<tr>	
								           			<td colspan="2" class="text14">
														&nbsp;<span title="svev_sut2">2.Text</span><input type="text" class="inputTextMediumBlue" name="svev_sut2" id="svev_sut2" size="35" maxlength="70" value="${Xmodel.record.svev_sut2}">
													</td>
												</tr>
												<tr>
													<td colspan="2" class="text14">
														&nbsp;<span title="svev_sut3">3.Text</span><input type="text" class="inputTextMediumBlue" name="svev_sut3" id="svev_sut3" size="35" maxlength="70" value="${Xmodel.record.svev_sut3}">
													</td>
												</tr>
												<tr>	
													<td colspan="2" class="text14">
														&nbsp;<span title="svev_sut4">4.Text</span><input type="text" class="inputTextMediumBlue" name="svev_sut4" id="svev_sut4" size="35" maxlength="70" value="${Xmodel.record.svev_sut4}">
													</td>
												</tr>
												<tr>
													<td colspan="2" class="text14">
														&nbsp;<span title="svev_sut5">5.Text</span><input type="text" class="inputTextMediumBlue" name="svev_sut5" id="svev_sut5" size="35" maxlength="70" value="${Xmodel.record.svev_sut5}">
														
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
           	<%-- --------------------------------------------------- --%>
           	<%-- DETAIL Section - Create Item line SECONDARY SECTION --%>
           	<%-- --------------------------------------------------- --%>
           	<tr height="10"><td></td></tr>
            <tr>
	 			<td >		
	 				<%-- Item header --%>
	 				<table width="100%" align="center" class="secondarySectionHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text14White">
				 				<b>&nbsp;&nbsp;Varupost&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Extraordinära uppgifter&nbsp;</b>
				 				<img src="resources/images/update.gif" border="0" alt="edit">
				 				
			 				</td>
		 				</tr>
	 				</table>
					<%-- Item input --%>
				 	<table width="100%" align="center" class="secondarySectionFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="10"><td class="text" align="left"></td></tr>
				 		<tr>
					 		<td width="50%">
						 		<table border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td colspan="2" class="text14" align="left">
							            &nbsp;<img onMouseOver="showPop('44_info');" onMouseOut="hidePop('44_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>44.</b>Särskilda upplysningar/Bilagda handlingar/Certifikat och tillstånd
							            
							            <div class="text14" style="position: relative;" align="left">
							            <span style="position:absolute;top:2px; width:250px;" id="44_info" class="popupWithInputText text14"  >
							           		<br/>
						           			<b>Särskilda upplysningar</b>
											<br/>
						           			Fält 44 består av två delfält, ett fritextfält och ett kodfält. Vissa uppgifter ska du alltid fylla i då du deklarerar enligt godkänd exportör, medan ytterligare upplysningar lämnar du vid behov. Det kan exempelvis vara ett tillståndsnummer eller ett licensnummer för exportreglerade varor.
											<br/><br/>
											Särskilda upplysningar (Kod)<br/>
											I vissa fall ska du ange särskilda upplysningar. 
											<br/><br/>
											<b>Bilagda handlingar/Certifikat och tillstånd </b>
											<br/>
											Här ska du ange en eller flera koder följda av ett identitetsnummer för de handlingar, certifikat eller tillstånd som ligger till grund för deklarationen. Koden består av fyra tecken och kan bestå av både bokstäver och siffror. Kod för faktura och identitetsnummer är obligatoriska uppgifter.    
											<br/><br/>
											Du ska även ange ett transportdokumentnummer. I de fall du inte skrivit något i fältet "Kommersiellt referensnummer", är transportdokumentnumret en obligatorisk uppgift. Transportdokumentnummer finns förklarat under rubriken " Transportdokumentnummer".
											
										</span>
										</div>
										</td>
										
							           	<td class="text14" align="left">&nbsp;</td>
							        </tr>
							        <tr height="5"><td class="text" align="left"></td></tr>
									
									<tr>
							            <td class="text14" align="left">&nbsp;&nbsp;Särskilda upplysningar kod/text:&nbsp;</td>
							           	<td class="text14" align="left"><button name="specialInformationCodeButton" class="buttonGray" type="button" onClick="showPop('specialInformationCode');" >Mer...</button> 
								        <span style="position:absolute; left:280px; top:1000px; width:600px; height:420px;" id="specialInformationCode" class="popupWithInputText"  >
								           		<div class="text10" align="left">
								           			<table>
								           				<tr>
										           			<td class="text14" colspan="2">
										           				<b>Särskilda upplysningar</b>
										           				<a tabindex="-1" id="sarskildaUppIdLink2">
		            												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
		            											</a>	
										           			</td>
										        		</tr>
										        		<tr height="5"><td class="text" align="left"></td></tr>
									           			<tr>
										           			<td class="text14" >
										           				&nbsp;<span title="svev_suk6">2.Kod</span>
										           				<select class="inputTextMediumBlue" name="svev_suk6" id="svev_suk6">
											 						<option value="">-Välj-</option>
												 				  	<c:forEach var="code" items="${model.salCodeList}" >
												 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.svev_suk6 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																	</c:forEach>  
																</select>
										           			</td>
										           			<td class="text14" >
										           				&nbsp;
										           			</td>
														</tr>
														<tr>
										           			<td class="text14">
																&nbsp;<span title="svev_sut6">1.Text</span><input type="text" class="inputTextMediumBlue" name="svev_sut6" id="svev_sut6" size="35" maxlength="70" value="${model.record.svev_sut6}">
															</td>
										           			<td class="text14">
																&nbsp;<span title="svev_sut7">2.Text</span><input type="text" class="inputTextMediumBlue" name="svev_sut7" id="svev_sut7" size="35" maxlength="70" value="${model.record.svev_sut7}">
															</td>
														</tr>
										           		<tr>
										           			<td class="text14">
																&nbsp;<span title="svev_sut8">3.Text</span><input type="text" class="inputTextMediumBlue" name="svev_sut8" id="svev_sut8" size="35" maxlength="70" value="${model.record.svev_sut8}">
															</td>
										           			<td class="text14">
																&nbsp;<span title="svev_sut9">4.Text</span><input type="text" class="inputTextMediumBlue" name="svev_sut9" id="svev_sut9" size="35" maxlength="70" value="${model.record.svev_sut9}">
															</td>
														</tr>
														<tr >
										           			<td class="text14">
																&nbsp;<span title="svev_suta">5.Text</span><input type="text" class="inputTextMediumBlue" name="svev_suta" id="svev_suta" size="35" maxlength="70" value="${model.record.svev_suta}">
															</td>
										           			<td class="text14" >
										           				&nbsp;
										           			</td>
														</tr>
														<tr><td class="text14" height="8"/></tr>
														
														<tr>
										           			<td class="text14" >
										           				&nbsp;<span title="svev_sukb">3.Kod</span>
										           				<select class="inputTextMediumBlue" name="svev_sukb" id="svev_sukb">
											 						<option value="">-Välj-</option>
												 				  	<c:forEach var="code" items="${model.salCodeList}" >
												 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.svev_sukb == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																	</c:forEach>  
																</select>
										           			</td>
										           			<td class="text14" >
										           				&nbsp;
										           			</td>
														</tr>
														<tr>
										           			<td class="text14">
																&nbsp;<span title="svev_sutb">1.Text</span><input type="text" class="inputTextMediumBlue" name="svev_sutb" id="svev_sutb" size="35" maxlength="70" value="${model.record.svev_sutb}">
															</td>
										           			<td class="text14">
																&nbsp;<span title="svev_sutc">2.Text</span><input type="text" class="inputTextMediumBlue" name="svev_sutc" id="svev_sutc" size="35" maxlength="70" value="${model.record.svev_sutc}">
															</td>
														</tr>
										           		<tr>
										           			<td class="text14">
																&nbsp;<span title="svev_sutd">3.Text</span><input type="text" class="inputTextMediumBlue" name="svev_sutd" id="svev_sutd" size="35" maxlength="70" value="${model.record.svev_sutd}">
															</td>
										           			<td class="text14">
																&nbsp;<span title="svev_sute">4.Text</span><input type="text" class="inputTextMediumBlue" name="svev_sute" id="svev_sute" size="35" maxlength="70" value="${model.record.svev_sute}">
															</td>
														</tr>
														<tr>
										           			<td class="text14">
																&nbsp;<span title="svev_sutf">5.Text</span><input type="text" class="inputTextMediumBlue" name="svev_sutf" id="svev_sutf" size="35" maxlength="70" value="${model.record.svev_sutf}">
															</td>
										           			<td class="text14" >
										           				&nbsp;
										           			</td>
														</tr>
								           			</table>
													<table width="100%" align="left" border="0">
														<tr align="left" >
															<td class="text14"><button name="specialInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('specialInformationCode');">&nbsp;Ok</button> 
															</td>
														</tr>
													</table>
												</div>
												
										</span>
										</td>
							        </tr>
							        <tr height="10"><td colspan="2" ></td></tr>
							        <tr>
							            <td class="text14" align="left">
							            &nbsp;<img onMouseOver="showPop('40_info');" onMouseOut="hidePop('40_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>40.</b><span title="svev_tik1-svev_tit1-svev_tix1 / svev_tik2-svev_tit2-svev_tix2 ... etc">Tidigare handlingar:</span>
							            
							            <div class="text14" style="position: relative;" align="left">
							            <span style="position:absolute;top:2px; width:250px;" id="40_info" class="popupWithInputText text14"  >
							           		<br/>
						           			<b>Tidigare handlingar</b>
											<br/>
						           			Du får lämna uppgift om tidigare handlingar i en deklaration enligt normalförfarandet men du kan lämna uppgift om tidigare handling i alla typer av deklarationer om du så önskar. 
											<br/><br/>
											Du anger här eventuella tidigare handlingar med hjälp av gemenskapskoder.
											<br/><br/>
											Koden är tredelad. Varje del åtskiljs med bindestreck.
											<br/>
											<b>1. Den första delen</b><br/>
											Anges med en bokstav som anger kategori av tidigare handling:
											<ul>
						           				<li>X Summarisk deklaration</li>
						    	       			<li>Y Ursprunglig deklaration, exempelvis notering i bokföringen eller enhetsdokument.</li>
							           			<li>Z Tidigare handling, exempelvis transiteringshandling.</li>
							           		</ul>
											<br/>
											<b>2. Den andra delen</b><br/>
											Här anger du vilken typ av handling som avses. Detta gör du med en förkortning på upp till tre bokstäver eller siffror, eller en kombination av dessa. Du kan hitta mer information om detta i nedanstående länk. Om tidigare handling är ett Enhetsdokument (ED) ska koden i fält 1:1 användas (EX, EU och CO).
											<br/><br/>
											<b>3. Den tredje delen</b>
											Här anger du identiteten på handlingen, exempelvis registreringsnummer.   
											
										</span>
										</div>
										</td>
							            
							           	<td class="text14" align="left"><button name="previousDocumentsButton" class="buttonGray" type="button" onClick="showPop('previousDocuments');" >Mer...</button> 
								        <span style="position:absolute; left:280px; top:1000px; width:550px; height:280px;" id="previousDocuments" class="popupWithInputText"  >
								           		<div class="text10" align="left">
								           			<table width="100%">
								           				<tr>
										           			<td class="text14" colspan="3">
										           				<b>Tidigare handlingar</b>
										           				<a tabindex="-1" id="tidigareHandlingarIdLink">
		            												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
		            											</a>	
										           			</td>
										        		</tr>	
								           				<tr>
										           			<td class="text14">&nbsp;Kategori</td>
										           			<td class="text14">&nbsp;Typ</td>
															<td class="text14">&nbsp;Identitet</td>
										           		</tr>
									           			<tr>
										           			<td class="text14" nowrap >&nbsp;1.
										           				<select class="inputTextMediumBlue" name="svev_tik1" id="svev_tik1" >
											 						<option value="">-Välj-</option>
															  		<option value="X" <c:if test="${model.record.svev_tik1 == 'X'}"> selected </c:if> >X</option>
															  		<option value="Y" <c:if test="${model.record.svev_tik1 == 'Y'}"> selected </c:if> >Y</option>
															  		<option value="Z" <c:if test="${model.record.svev_tik1 == 'Z'}"> selected </c:if> >Z</option>
															  	</select>										           			
										           			</td>
										           			<td class="text14" nowrap >
																<select class="inputTextMediumBlue" name="svev_tit1" id="svev_tit1">
												            		<option value="">-Välj-</option>
												 				  	<c:forEach var="code" items="${model.thoCodeList}" >
								                                	 	<option value="${code.svkd_kd}"<c:if test="${model.record.svev_tit1 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																	</c:forEach> 
																</select>										           				
															</td>
															<td class="text14" nowrap >
																&nbsp;<input type="text" class="inputTextMediumBlue" name="svev_tix1" id="svev_tix1" size="35" maxlength="35" value='${model.record.svev_tix1}'>
															</td>
										           		</tr>
										           		<tr>
										           			<td class="text14" nowrap >&nbsp;2.
										           				<select class="inputTextMediumBlue" name="svev_tik2" id="svev_tik2" >
											 						<option value="">-Välj-</option>
															  		<option value="X" <c:if test="${model.record.svev_tik2 == 'X'}"> selected </c:if> >X</option>
															  		<option value="Y" <c:if test="${model.record.svev_tik2 == 'Y'}"> selected </c:if> >Y</option>
															  		<option value="Z" <c:if test="${model.record.svev_tik2 == 'Z'}"> selected </c:if> >Z</option>
															  	</select>	
										           			</td>
										           			<td class="text14" nowrap >
										           				<select class="inputTextMediumBlue" name="svev_tit2" id="svev_tit2">
												            		<option value="">-Välj-</option>
												 				  	<c:forEach var="code" items="${model.thoCodeList}" >
								                                	 	<option value="${code.svkd_kd}"<c:if test="${model.record.svev_tit2 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																	</c:forEach> 
																</select>
															</td>
															<td class="text14" nowrap >
																&nbsp;<input type="text" class="inputTextMediumBlue" name="svev_tix2" id="svev_tix2" size="35" maxlength="35" value='${model.record.svev_tix2}'>
															</td>
										           		</tr>
														<tr>
										           			<td class="text14" nowrap >&nbsp;3.
										           				<select class="inputTextMediumBlue" name="svev_tik3" id="svev_tik3" >
											 						<option value="">-Välj-</option>
															  		<option value="X" <c:if test="${model.record.svev_tik3 == 'X'}"> selected </c:if> >X</option>
															  		<option value="Y" <c:if test="${model.record.svev_tik3 == 'Y'}"> selected </c:if> >Y</option>
															  		<option value="Z" <c:if test="${model.record.svev_tik3 == 'Z'}"> selected </c:if> >Z</option>
															  	</select>
									           				</td>
										           			<td class="text14" nowrap >
										           				<select class="inputTextMediumBlue" name="svev_tit3" id="svev_tit3">
												            		<option value="">-Välj-</option>
												 				  	<c:forEach var="code" items="${model.thoCodeList}" >
								                                	 	<option value="${code.svkd_kd}"<c:if test="${model.record.svev_tit3 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																	</c:forEach> 
																</select>
															</td>
															<td class="text14" nowrap >
																&nbsp;<input type="text" class="inputTextMediumBlue" name="svev_tix3" id="svev_tix3" size="35" maxlength="35" value='${model.record.svev_tix3}'>
															</td>
										           		</tr>
									           			<tr>
										           			<td class="text14" nowrap >&nbsp;4.
										           				<select class="inputTextMediumBlue" name="svev_tik4" id="svev_tik4" >
											 						<option value="">-Välj-</option>
															  		<option value="X" <c:if test="${model.record.svev_tik4 == 'X'}"> selected </c:if> >X</option>
															  		<option value="Y" <c:if test="${model.record.svev_tik4 == 'Y'}"> selected </c:if> >Y</option>
															  		<option value="Z" <c:if test="${model.record.svev_tik4 == 'Z'}"> selected </c:if> >Z</option>
															  	</select>
										           				
										           			</td>
										           			<td class="text14" nowrap >
										           				<select class="inputTextMediumBlue" name="svev_tit4" id="svev_tit4">
												            		<option value="">-Välj-</option>
												 				  	<c:forEach var="code" items="${model.thoCodeList}" >
								                                	 	<option value="${code.svkd_kd}"<c:if test="${model.record.svev_tit4 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																	</c:forEach> 
																</select>
																
															</td>
															<td class="text14">
																&nbsp;<input type="text" class="inputTextMediumBlue" name="svev_tix4" id="svev_tix4" size="35" maxlength="35" value='${model.record.svev_tix4}'>
															</td>
															
										           		</tr>
									           			<tr>
										           			<td class="text14" nowrap >&nbsp;5.
										           				<select class="inputTextMediumBlue" name="svev_tik5" id="svev_tik5" >
											 						<option value="">-Välj-</option>
															  		<option value="X" <c:if test="${model.record.svev_tik5 == 'X'}"> selected </c:if> >X</option>
															  		<option value="Y" <c:if test="${model.record.svev_tik5 == 'Y'}"> selected </c:if> >Y</option>
															  		<option value="Z" <c:if test="${model.record.svev_tik5 == 'Z'}"> selected </c:if> >Z</option>
															  	</select>
										           			</td>
										           			<td class="text14" nowrap >
										           				<select class="inputTextMediumBlue" name="svev_tit5" id="svev_tit5">
												            		<option value="">-Välj-</option>
												 				  	<c:forEach var="code" items="${model.thoCodeList}" >
								                                	 	<option value="${code.svkd_kd}"<c:if test="${model.record.svev_tit5 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																	</c:forEach> 
																</select>
															</td>
															<td class="text14" nowrap >
																&nbsp;<input type="text" class="inputTextMediumBlue" name="svev_tix5" id="svev_tix5" size="35" maxlength="35" value='${model.record.svev_tix5}'>
															</td>
										           		</tr>
									           			<tr>
										           			<td class="text14" nowrap >&nbsp;6.
										           				<select class="inputTextMediumBlue" name="svev_tik6" id="svev_tik6" >
											 						<option value="">-Välj-</option>
															  		<option value="X" <c:if test="${model.record.svev_tik6 == 'X'}"> selected </c:if> >X</option>
															  		<option value="Y" <c:if test="${model.record.svev_tik6 == 'Y'}"> selected </c:if> >Y</option>
															  		<option value="Z" <c:if test="${model.record.svev_tik6 == 'Z'}"> selected </c:if> >Z</option>
															  	</select>
										           			</td>
										           			<td class="text14" nowrap >
										           				<select class="inputTextMediumBlue" name="svev_tit6" id="svev_tit6">
												            		<option value="">-Välj-</option>
												 				  	<c:forEach var="code" items="${model.thoCodeList}" >
								                                	 	<option value="${code.svkd_kd}"<c:if test="${model.record.svev_tit6 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																	</c:forEach> 
																</select>
															</td>
															<td class="text14" nowrap >
																&nbsp;<input type="text" class="inputTextMediumBlue" name="svev_tix6" id="svev_tix6" size="35" maxlength="35" value='${model.record.svev_tix6}'>
															</td>
										           		</tr>
								           			</table>
													<table width="100%" align="left" border="0">
														<tr align="left" >
															<td ><button name="previousDocumentsButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('previousDocuments');">&nbsp;Ok</button> 
															</td>
														</tr>
													</table>
												</div>
										</span>
										</td>
							        </tr>
							        
							        <tr height="10"><td colspan="2" ></td></tr>
							        
			        	        </table>
					        </td>
					        <td width="50%" valign="top">
						 		<table border="0" cellspacing="1" cellpadding="0">
						 			<tr>
							            <td class="text14" nowrap colspan="2" align="left"><b>&nbsp;31.</b>Godsmärkning, kolli och varuslag&nbsp;</td>
							           	<td class="text14" align="left">&nbsp;</td>
							        </tr>
							        <tr>
							            <td class="text14" align="left">&nbsp;&nbsp;&nbsp;<span title="svev_god2-svev_kot2-svev_kos2 / etc">Godsmärkning ,antal kolli och kollislag:&nbsp;</td>
							           	<td class="text14" align="left"><button name="goodsMarkButton" class="buttonGray" type="button" onClick="showPop('goodsMark');" >Mer...</button> 
								        <span style="position:absolute; left:480px; top:1000px; width:350px; height:320px;" id="goodsMark" class="popupWithInputText"  >
								           		<div class="text10" align="left" valign="top">
								           			<table>
								           				<tr>
										           			<td class="text14">
										           				&nbsp;Godsmärkning [max 42 tecken]:
										           			</td>
										           			<td class="text14">
																&nbsp;Kolli antal:
															</td>
															<td class="text14">
																&nbsp;Kolli slag [kod]
															</td>
														</tr>
									           			<tr>
										           			<td valign="top" class="text14">
										           				&nbsp;2.<textarea rows="2" cols="15" class="inputTextMediumBlueUPPERCASE" name="svev_god2" id="svev_god2" maxlength="42">${model.record.svev_god2}</textarea>
										           			</td>
										           			<td valign="bottom" class="text14">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="svev_kot2" id="svev_kot2" size="5" maxlength="5" value="${model.record.svev_kot2}">
															</td>
															<td align="left" valign="bottom">
										            			<select class="inputTextMediumBlue" name="svev_kos2" id="svev_kos2">
												            		<option value="">-Välj-</option>
												 				  	<c:forEach var="code" items="${model.kolliCodeList}" >
								                                	 	<option value="${code.svkd_kd}"<c:if test="${model.record.svev_kos2 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																	</c:forEach> 
																</select>
									            			</td> 
															
															
										           		</tr>
														<tr>
										           			<td valign="top" class="text14">
										           				&nbsp;3.<textarea rows="2" cols="15" class="inputTextMediumBlueUPPERCASE" name="svev_god3" id="svev_god3" maxlength="42">${model.record.svev_god3}</textarea>
										           			</td>
										           			<td valign="bottom" class="text14">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="svev_kot3" id="svev_kot3" size="5" maxlength="5" value="${model.record.svev_kot3}">
															</td>
															<td align="left" valign="bottom">
										            			<select class="inputTextMediumBlue" name="svev_kos3" id="svev_kos3">
												            		<option value="">-Välj-</option>
												 				  	<c:forEach var="code" items="${model.kolliCodeList}" >
								                                	 	<option value="${code.svkd_kd}"<c:if test="${model.record.svev_kos3 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																	</c:forEach> 
																</select>
									            			</td> 
															
															
										           		</tr>
									           			<tr>
										           			<td valign="top" class="text14">
										           				&nbsp;4.<textarea rows="2" cols="15" class="inputTextMediumBlueUPPERCASE" name="svev_god4" id="svev_god4" maxlength="42">${model.record.svev_god4}</textarea>
										           			</td>
										           			<td valign="bottom" class="text14">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="svev_kot4" id="svev_kot4" size="5" maxlength="5" value="${model.record.svev_kot4}">
															</td>
															<td align="left" valign="bottom">
										            			<select class="inputTextMediumBlue" name="svev_kos4" id="svev_kos4">
												            		<option value="">-Välj-</option>
												 				  	<c:forEach var="code" items="${model.kolliCodeList}" >
								                                	 	<option value="${code.svkd_kd}"<c:if test="${model.record.svev_kos4 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																	</c:forEach> 
																</select>
									            			</td> 
															
										           		</tr>
									           			<tr>
										           			<td valign="top" class="text14">
										           				&nbsp;5.<textarea rows="2" cols="15" class="inputTextMediumBlueUPPERCASE" name="svev_god5" id="svev_god5" maxlength="42">${model.record.svev_god5}</textarea>
										           			</td>
										           			<td valign="bottom" class="text14">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="svev_kot5" id="svev_kot5" size="5" maxlength="5" value="${model.record.svev_kot5}">
															</td>
															<td align="left" valign="bottom">
										            			<select class="inputTextMediumBlue" name="svev_kos5" id="svev_kos5">
												            		<option value="">-Välj-</option>
												 				  	<c:forEach var="code" items="${model.kolliCodeList}" >
								                                	 	<option value="${code.svkd_kd}"<c:if test="${model.record.svev_kos5 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																	</c:forEach> 
																</select>
									            			</td> 
															
										           		</tr>
									           			
								           			</table>
													<table width="100%" align="left" border="0">
														<tr align="left" >
															<td  class="text14"><button name="goodsMarkButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('goodsMark');">&nbsp;Ok</button> 
															</td>
														</tr>
													</table>
												</div>
										</span>
										</td>
							        </tr>
									<tr>
							            <td class="text14" align="left">&nbsp;&nbsp;&nbsp;&nbsp;<span title="svev_co01/svev_co02...etc">Container nr:&nbsp;</span></td>
							           	<td class="text14" align="left"><button name="containerNrButton" class="buttonGray" type="button" onClick="showPop('containerNrInfo');" >Mer...</button> 
								           	<span style="position:absolute; left:480px; top:1000px; width:580px; height:250px;" id="containerNrInfo" class="popupWithInputText"  >
								           		<div class="text10" align="left">
								           			<table>
								           			<tr>
									           			<td class="text14" colspan="5">
									           				<b>Container nr</b>
									           			</td>
									        		</tr>
								           			<tr>
									           			<td class="text14">
									           				&nbsp;1.<input type="text" class="inputTextMediumBlue" name="svev_co01" id="svev_co01" size="12" maxlength="17" value="${model.record.svev_co01}">
									           			</td>
														<td class="text14">
															&nbsp;2.<input type="text" class="inputTextMediumBlue" name="svev_co02" id="svev_co02" size="12" maxlength="17" value="${model.record.svev_co02}">
														</td>
														<td class="text14">
															&nbsp;3.<input type="text" class="inputTextMediumBlue" name="svev_co03" id="svev_co03" size="12" maxlength="17" value="${model.record.svev_co03}">
														</td>
														<td class="text14">
									           				&nbsp;4.<input type="text" class="inputTextMediumBlue" name="svev_co04" id="svev_co04" size="12" maxlength="17" value="${model.record.svev_co04}">
									           			</td>
													</tr>
													<tr>
									           			<td class="text14">
									           				&nbsp;5.<input type="text" class="inputTextMediumBlue" name="svev_co05" id="svev_co05" size="12" maxlength="17" value="${model.record.svev_co05}">
									           			</td>
														<td class="text14">
															&nbsp;6.<input type="text" class="inputTextMediumBlue" name="svev_co06" id="svev_co06" size="12" maxlength="17" value="${model.record.svev_co06}">
														</td>
														<td class="text14">
															&nbsp;7.<input type="text" class="inputTextMediumBlue" name="svev_co07" id="svev_co07" size="12" maxlength="17" value="${model.record.svev_co07}">
														</td>
														<td class="text14">
									           				&nbsp;8.<input type="text" class="inputTextMediumBlue" name="svev_co08" id="svev_co08" size="12" maxlength="17" value="${model.record.svev_co08}">
									           			</td>
													</tr>
													<tr>
									           			<td class="text14">
									           				&nbsp;9.<input type="text" class="inputTextMediumBlue" name="svev_co09" id="svev_co09" size="12" maxlength="17" value="${model.record.svev_co09}">
									           			</td>
														<td class="text14">
															10.<input type="text" class="inputTextMediumBlue" name="svev_co10" id="svev_co10" size="12" maxlength="17" value="${model.record.svev_co10}">
														</td>
														<td class="text14">
															11.<input type="text" class="inputTextMediumBlue" name="svev_co11" id="svev_co11"  size="12" maxlength="17" value="${model.record.svev_co11}">
														</td>
														<td class="text14">
									           				12.<input type="text" class="inputTextMediumBlue" name="svev_co12" id="svev_co12" size="12" maxlength="17" value="${model.record.svev_co12}">
									           			</td>
													</tr>
													<tr>
									           			<td class="text14">
									           				13.<input type="text" class="inputTextMediumBlue" name="svev_co13" id="svev_co13" size="12" maxlength="17" value="${model.record.svev_co13}">
									           			</td>
														<td class="text14">
															14.<input type="text" class="inputTextMediumBlue" name="svev_co14" id="svev_co14" size="12" maxlength="17" value="${model.record.svev_co14}">
														</td>
														<td class="text14">
															15.<input type="text" class="inputTextMediumBlue" name="svev_co15" id="svev_co15" size="12" maxlength="17" value="${model.record.svev_co15}">
														</td>
														<td class="text14">
									           				16.<input type="text" class="inputTextMediumBlue" name="svev_co16" id="svev_co16" size="12" maxlength="17" value="${model.record.svev_co16}">
									           			</td>
													</tr>
													<tr>
									           			<td class="text14">
									           				17.<input type="text" class="inputTextMediumBlue" name="svev_co17" id="svev_co17" size="12" maxlength="17" value="${model.record.svev_co17}">
									           			</td>
														<td class="text14">
															18.<input type="text" class="inputTextMediumBlue" name="svev_co18" id="svev_co18" size="12" maxlength="17" value="${model.record.svev_co18}">
														</td>
														<td class="text14">
															19.<input type="text" class="inputTextMediumBlue" name="svev_co19" id="svev_co19" size="12" maxlength="17" value="${model.record.svev_co19}">
														</td>
														<td class="text14">
									           				20.<input type="text" class="inputTextMediumBlue" name="svev_co20" id="svev_co20" size="12" maxlength="17" value="${model.record.svev_co20}">
									           			</td>
													</tr>
													</table>
													<table width="100%" align="left" border="0">
														<tr align="left" >
															<td class="text14" ><button name="containerNrButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('containerNrInfo');">&nbsp;Ok</button> 
															</td>
														</tr>
													</table>
												</div>
								           	</span>
											
							           	</td>
							        </tr>
							        <tr height="10"><td></td></tr>
			        	        </table>
					        </td>
				        </tr>
				        <tr height="10"><td></td></tr>
				        <tr>
					 		<td colspan="2">
						 		<table border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td class="text14" align="left" >
							            &nbsp;<img onMouseOver="showPop('49_info');" onMouseOut="hidePop('49_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>49.</b><span title="svev_lagt">Identifiering av lager:&nbsp;</span>
							            
							            <div class="text14" style="position: relative;" align="left">
							            <span style="position:absolute;top:2px; width:250px;" id="49_info" class="popupWithInputText text14"  >
							           		<br/>
						           			<b>Identifiering av lager</b>
											<br/>
						           			Fältet är obligatoriskt vid ett tullförfarande som avslutar ett tullagerförfarande, det vill säga om koden för förfarande i fält 37:1 slutar med 71.
						           			<br/><br/>
											I detta fält ska du identifiera tullagret varorna finns på. Koden för att identifiera tullagret består av tre delar.
											<br/><br/>
											Koden skickas i tre delfält
											<ul>
												<li>1. Ange en bokstav som talar om typ av tullager (framgår av tillståndet).</li>
												<li>2. Här anger du godslokalkoden.</li>
												<li>3. Här anger du landkoden för den medlemsstat som utfärdat tillståndet.</li>
											</ul>
										
										</span>
							            </div>
							            </td>
							            
							            <td class="text14" align="left" >
							            	<select class="inputTextMediumBlue" name="svev_lagt" id="svev_lagt">
				 								<option selected value="">-Välj-</option>
												<c:choose>
				 									<%-- only when new record and header value exists --%>
													<c:when test="${recordTopic.sveh_godn!='' && empty model.record.svev_syli}">
														<option value="A" selected >A-Allmänt tullager , typ A, där lagerhav har ansv för lagret.</option>
					  									<option value="C" >C-Privat tullager, typ C, där lagerhav har ansv för lagret.</option>
					  									<option value="D" >D-Privat tulllager, typ D, där lagerhav h ansv för lagret. Taxeringsgr fastställda</option>
					  									<option value="E" >E-Privat tulllager, typ E, där lagerhav har ansv för lagret. Kvalitetsäkrad förvar</option>
					  									<option value="F" >F-Tullager, typ F, drivs av Tullverket. Beslag - hävs/utlämnas från Tullv tulllage</option>
													</c:when>
													<c:otherwise>
														<option value="A" <c:if test="${model.record.svev_lagt == 'A'}"> selected </c:if> >A-Allmänt tullager , typ A, där lagerhav har ansv för lagret.</option>
										  				<option value="C" <c:if test="${model.record.svev_lagt == 'C'}"> selected </c:if> >C-Privat tullager, typ C, där lagerhav har ansv för lagret.</option>
										  				<option value="D" <c:if test="${model.record.svev_lagt == 'D'}"> selected </c:if> >D-Privat tulllager, typ D, där lagerhav h ansv för lagret. Taxeringsgr fastställda</option>
										  				<option value="E" <c:if test="${model.record.svev_lagt == 'E'}"> selected </c:if> >E-Privat tulllager, typ E, där lagerhav har ansv för lagret. Kvalitetsäkrad förvar</option>
										  				<option value="F" <c:if test="${model.record.svev_lagt == 'F'}"> selected </c:if> >F-Tullager, typ F, drivs av Tullverket. Beslag - hävs/utlämnas från Tullv tulllage</option>
													</c:otherwise>
												</c:choose>
											</select>
										</td>
										<td class="text14" align="left">&nbsp;&nbsp;<span title="svev_lagi">Id:</span>
											<c:choose>
			 									<%-- only when new record and header value exists --%>
												<c:when test="${recordTopic.sveh_godn!='' && empty model.record.svev_syli}">
													<input type="text" class="inputTextMediumBlue" name="svev_lagi" id="svev_lagi" size="10" maxlength="14" value='${recordTopic.sveh_godn}'>
												</c:when>
												<c:otherwise>
													<input type="text" class="inputTextMediumBlue" name="svev_lagi" id="svev_lagi" size="10" maxlength="14" value='${model.record.svev_lagi}'>
												</c:otherwise>
											</c:choose>
										</td>
							           	<td class="text14" align="left">&nbsp;<span title="svev_lagl">Landkod&nbsp;</span>
							            	<select class="inputTextMediumBlue" name="svev_lagl" id="svev_lagl">
						 						<option value="">-Välj-</option>
						 						<c:choose>
				 									<%-- only when new record and header value exists --%>
													<c:when test="${recordTopic.sveh_godn!='' && empty model.record.svev_syli}">
									 				  	<c:forEach var="country" items="${model.gcyCodeList}" >
									 				  		<option value="${country.svkd_kd}"<c:if test="${'SE' == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
														</c:forEach>  
													</c:when>
													<c:otherwise>
									 				  	<c:forEach var="country" items="${model.gcyCodeList}" >
									 				  		<option value="${country.svkd_kd}"<c:if test="${model.record.svev_lagl == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
														</c:forEach>  
													</c:otherwise>
												</c:choose>
											</select>
											<a tabindex="-1" id="svev_laglIdLink">
           										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
           									</a>										
										</td>
							        </tr>
							        
								</table>
							</td>
						</tr>
						<tr height="15"><td></td></tr>
			            <%-- Ytterligare säkerhetsuppgifter --%>							
						<tr>
				 			<td colspan="2">		
				 				<%-- Item header --%>
				 				<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="15">
							 			<td class="text14">
							 				&nbsp;&nbsp;<b>Ytterligare säkerhetsuppgifter&nbsp;</b>
							 				<img src="resources/images/update.gif" border="0" alt="edit">
							 				
						 				</td>
					 				</tr>
				 				</table>
			 				</td>
						</tr>
						<tr height="8"><td></td></tr>
						<tr>
							<td colspan="2">
						 		<table border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td class="text14" align="left">
							            &nbsp;<img onMouseOver="showPop('S02_info');" onMouseOut="hidePop('S02_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>S02.</b><span title="svev_komr">Kommersiellt referensnr.&nbsp;</span>
							            
							            <div class="text14" style="position: relative;" align="left">
							            <span style="position:absolute;top:2px; width:250px;" id="S02_info" class="popupWithInputText text14"  >
							           		<br/>
						           			<b>Kommersiellt referensnr.</b>
											<br/>
						           			Här anger du ditt egna referensnummer för sändningen (UCR) - ett unikt nummer som tilldelas varor i samband med export.
											<br/><br/>
											Ett unikt referensnummer är ett nummer som gör det möjligt att spåra en sändning eller en artikel i ditt affärssystem. Det kan till exempel vara ett ordernummer, sändningsnummer eller kollinummer. Referensnumret är knutet till ett antal uppgifter om sändningen/artikeln i ditt affärssystem.
											<br/><br/>							
											Världstullorganisationens (WCO) standard (ISO 15459) eller motsvarande kan användas.
											<br/><br/>
											Om samma kommersiella referensnummer gäller för alla varuposter eller om exportdeklarationen bara omfattar en varupost, anger du uppgiften på huvudnivå. I annat fall anger du uppgiften på varupostnivå. Kommersiellt referensnummer behöver inte anges i följande fall:
											<ul>
												<li>Om du angett koden "A" (Post- och expressförsändelser) i fältet S32, "Särskild omständighet, kod", behöver du inte ange denna uppgift.</li>
												<li>Kommersiellt referensnummer kan också utelämnas vid utförsel till ett område inom gemenskapens tullområde som inte tillhör skatteområdet, exempelvis Åland.</li>
												<li>Om du angett ett transportdokument i fält 44, "Bilagda handlingar", på minst en varupost, behöver du inte lämna kommersiellt referensnummer.</li>
											</ul>
										</span>
										</div>
										</td>
							           	<td class="text14" align="left"><input type="text" class="inputTextMediumBlue" name="svev_komr" id="svev_komr" size="25" maxlength="70" value="${model.record.svev_komr}"></td>
						           	</tr>
						           	<tr>
							            <td class="text14" align="left">
							            &nbsp;<img onMouseOver="showPop('S27_info');" onMouseOut="hidePop('S27_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>S27.</b><span title="svev_fnkd">FN-kod för farligt gods:&nbsp;</span>
							            
							            <div class="text14" style="position: relative;" align="left">
							            <span style="position:absolute;top:2px; width:250px;" id="S27_info" class="popupWithInputText text14"  >
							           		<br/>
						           			<b>FN-kod för farligt gods</b>
											<br/>
						           			I de fall sändningen innehåller farligt gods är uppgiften obligatorisk. Uppgiften ska anges på varupostnivå. Du ska använda koder från FN:s förteckning över farligt gods, som du till exempel hittar på Myndigheten för samhällsskydd och beredskaps webbplats. Du behöver inte ange FN-kod i följande fall:
						           			<br/>
											<ul>
												<li>Om du angett koden "E" (Godkända ekonomiska aktörer) eller "B" (Proviant och delar till fartyg och luftfartyg) i fält S32, "Särskild omständighet, kod", behöver du inte ange denna uppgift.</li>
												<li>FN-kod (UN-nummer) kan också utelämnas vid utförsel till ett område inom gemenskapens tullområde som inte tillhör skatteområdet, exempelvis Åland.</li>
											</ul>
										
										</span>
										</div>
										</td>
							            <td class="text14" align="left"><input type="text" class="inputTextMediumBlue" name="svev_fnkd" id="svev_fnkd" size="4" maxlength="4" value="${model.record.svev_fnkd}"></td>
							        </tr>
							        <tr>
						 				<td class="text14">
						 				&nbsp;<img onMouseOver="showPop('S29_info');" onMouseOut="hidePop('S29_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>S29.</b><span title="svev_betk">Betalningssätt&nbsp;</span>
						 				
						 				<div class="text14" style="position: relative;" align="left">
						 				<span style="position:absolute;top:2px; width:250px;" id="S29_info" class="popupWithInputText text14"  >
							           		<br/>
						           			<b>Betalningssätt</b>
											<br/>
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
							 				<select class="inputTextMediumBlue" name="svev_betk" id="svev_betk" >
							 				  <option value="">-Välj-</option>
											  <option value="A"<c:if test="${model.record.svev_betk == 'A'}"> selected </c:if> >A</option>
											  <option value="B"<c:if test="${model.record.svev_betk == 'B'}"> selected </c:if> >B</option>
											  <option value="C"<c:if test="${model.record.svev_betk == 'C'}"> selected </c:if> >C</option>
											  <option value="D"<c:if test="${model.record.svev_betk == 'D'}"> selected </c:if> >D</option>
											  <option value="H"<c:if test="${model.record.svev_betk == 'H'}"> selected </c:if> >H</option>
											  <option value="Y"<c:if test="${model.record.svev_betk == 'Y'}"> selected </c:if> >Y</option>
											  <option value="Z"<c:if test="${model.record.svev_betk == 'Z'}"> selected </c:if> >Z</option>
											</select>
						 				</td>
					 				</tr>
					           	</table>
				           	</td>
				        </tr>
	        			<tr height="10"><td></td></tr>
				</table>
            	</td>
           	</tr>
     
            <tr height="30"><td></td></tr>
            </table>
            </form>
            </td>
            </tr>

            
		</table>
		
		</td>
		</tr>
		
		<tr>
		<td>
			<div id="dialogKundensVaruregister" title="Dialog">
				<form  action="tdsexport_edit_items_doUpdateKundensVaruregister.do" name="updateKundensVaruregisterForm" id="updateKundensVaruregisterForm" method="post">
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="svew_kota" id="svew_kota" value=""/>
				 	<input type="hidden" name="svew_kosl" id="svew_kosl" value=""/>
				 	<input type="hidden" name="svew_vasl" id="svew_vasl" value=""/>
				 	<input type="hidden" name="svew_godm" id="svew_godm" value=""/>
				 	<input type="hidden" name="svew_brut" id="svew_brut" value=""/>
				 	<input type="hidden" name="svew_neto" id="svew_neto" value=""/>
				 	<input type="hidden" name="svew_kono" id="svew_kono" value=""/>
				 	<input type="hidden" name="svew_ankv" id="svew_ankv" value=""/>
				 	<input type="hidden" name="svew_suko" id="svew_suko" value=""/>
				 	<input type="hidden" name="svew_sutx" id="svew_sutx" value=""/>
				 	<input type="hidden" name="svew_atin" id="svew_atin" value=""/>
				 	<input type="hidden" name="svew_fabl" id="svew_fabl" value=""/>
				 	<input type="hidden" name="svew_betk" id="svew_betk" value=""/>
				 	<input type="hidden" name="svew_komr" id="svew_komr" value=""/>
				 	<input type="hidden" name="svew_fnkd" id="svew_fnkd" value=""/>
				 	<input type="hidden" name="svew_bit1" id="svew_bit1" value=""/>
				 	<input type="hidden" name="svew_bii1" id="svew_bii1" value=""/>
				 	<input type="hidden" name="svew_call" id="svew_call" value=""/>
				 	<%--
				 	<input type="hidden" name="avd_sviw" id="avd_sviw" value=""/>
				 	<input type="hidden" name="opd_sviw" id="opd_sviw" value=""/>
				 	<input type="hidden" name="sign_sviw" id="sign_sviw" value=""/>
				 	 --%>
				 	
					<table>
						<tr>
							<td colspan="6">Beskrivning:&nbsp;<label class="text14Bold" id="description_svew" name="description_svew"></label></td>
						</tr>
						<tr height="5"><td></td></tr>
						
						<tr>
							<td class="tableHeaderFieldFirst" align="left" >&nbsp;UL</td>
          					<td class="tableHeaderField" align="left" >&nbsp;Varukod</td>
          					<td class="tableHeaderField" align="left" >&nbsp;Förfarande</td>
          					<td class="tableHeaderField" align="left" >&nbsp;Kundens art.nr</td>
          					<td class="tableHeaderField" align="left" >&nbsp;Kundnr.</td>
          				</tr>
						<tr>
							<td class="tableCellFirst11"><input class="inputTextReadOnly" type="text" id="svew_ulkd" name="svew_ulkd" size="10px" value=""></input></td>
							<td class="tableCell11"><input readonly class="inputTextReadOnly" type="text"  id="svew_vata" name="svew_vata" size="10px" value=""></input></td>
							<td class="tableCell11"><input readonly class="inputTextReadOnly" type="text"  id="svew_eup1" name="svew_eup1" size="10px" value=""></input></td>
							<td class="tableCell11"><input  class="inputTextMediumBlue" type="text"  id="svew_knso" name="svew_knso" size="10px" value=""></input></td>
							<td class="tableCell11"><input  class="inputTextMediumBlue" type="text"  id="svew_knnr" name="svew_knnr" size="10px" value=""></input></td>
						</tr>
						
					</table>
				</form>
			</div>
		</td>
		</tr>
		
		
		
		<tr>
			<td>
			<div style="display: none;" id="dialogVarupostkontroll" title="Dialog">
				<form  action="tdsexport_edit_items_autocontrol.do" name="varupostkontrollForm" id="varupostkontrollForm" method="post">
				 	<input type="hidden" name="svev_syav" id="svev_syav" value='${model.avd}'/>
					<input type="hidden" name="svev_syop" id="svev_syop" value='${model.opd}'/>
					<input type="hidden" name="sign" id="sign" value='${model.sign}'/>
					
					<input type="hidden" name="fablAutoControl" id="fablAutoControl" value='${recordTopic.sveh_fabl}'/>
						
					<p>Du kan nollställa alla statistiska värden och tullvärden i samtliga varuposter genom att kryssa för flaggan nedan</p>
					<p>&nbsp;</p>
					<p style="color:#000080;"><input tabindex=-1 type="checkbox" name="statValueNullIt" id="statValueNullIt" value="1" >Nollställ alla statistiska värden i samtliga varuposter</p>
							
				</form>
			</div>
		</td>
		</tr>
		
		
	</table>    
	

		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

