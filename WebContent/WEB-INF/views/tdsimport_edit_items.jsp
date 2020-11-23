<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTds.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tdsimport_edit_items.js?ver=${user.versionEspedsg}"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/jquery.calculator.js?ver=${user.versionEspedsg}"></SCRIPT>
	<%-- for dialog popup --%>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	
	<style type = "text/css">
		.ui-dialog{font-size:10pt;}
	</style>
	
	
	<%-- tab container component --%>
	<table width="100%"  class="text14" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkMainList" tabindex=-1 style="display:block;" href="tdsimport.do?action=doFind&sign=${model.sign}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tds.import.list.tab"/></font>
					<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkHeader" tabindex=-1 style="display:block;" href="tdsimport_edit.do?action=doFetch&avd=${model.avd}&opd=${model.opd}
						&sysg=${model.sign}&tuid=${model.tullId}&syst=${model.status}&sydt=${model.datum}">
					
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tds.import.created.mastertopic.tab"/></font>
					<font class="text14MediumBlue">[${model.opd}]</font>
					<c:if test="${model.status == 'M' || empty model.status}">
						<img src="resources/images/update.gif" border="0" alt="edit">
					</c:if>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkInvoices" tabindex=-1 style="display:block;" href="tdsimport_edit_invoice.do?action=doFetch&avd=${model.avd}&sign=${model.sign}
													&opd=${model.opd}&tullId=${model.tullId}
													&status=${model.status}&datum=${model.datum}&fabl=">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tds.import.invoice.tab"/></font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;<spring:message code="systema.tds.import.item.createnew.tab"/></font>
				<c:if test="${model.status == 'M' || empty model.status}">
					<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
				</c:if>
				
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
				 				&nbsp;&nbsp;Medd.typ:&nbsp;<b>${recordTopic.svih_mtyp}</b>
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
							            <td width="30%" class="text14Bold" align="left" >Exportör</td>
							            <td class="text12" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text12" align="left">EORI-nr&nbsp;</td>
							           	<td class="text14MediumBlue" align="left">${recordTopic.svih_aveo}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text12" align="left">Namn&nbsp;</td>
							           	<td class="text14MediumBlue" align="left">${recordTopic.svih_avna}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text12" align="left">Postadress&nbsp;</td>
							           	<td class="text14MediumBlue" align="left">${recordTopic.svih_avpa}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text12" align="left">Landkod&nbsp;</td>
							           	<td class="text14MediumBlue" align="left">${recordTopic.svih_avlk}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text12" align="left">&nbsp;</td>
							           	<td class="text14MediumBlue" align="left"></td>
							        </tr>							        
							        
			        	        </table>
					        </td>
					        <td width="30%">
						 		<table width="80%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text14Bold" align="left" >Mottagare</td>
							            <td class="text12" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text12" align="left">EORI-nr&nbsp;</td>
							           	<td class="text14MediumBlue" align="left">${recordTopic.svih_moeo}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text12" align="left">Namn&nbsp;</td>
							           	<td class="text14MediumBlue" align="left">${recordTopic.svih_mona}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text12" align="left">Postadress&nbsp;</td>
							           	<td class="text14MediumBlue" align="left">${recordTopic.svih_mopa}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text12" align="left">Landkod&nbsp;</td>
							           	<td class="text14MediumBlue" align="left">${recordTopic.svih_molk}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text12" align="left">Handläggare&nbsp;</td>
							           	<td class="text14MediumBlue" align="left">${recordTopic.svih_moha}</td>
							        </tr>
							        
			        	        </table>
					        </td>
					        
					        <td valign="bottom" align="right">
								<table class="tableNoBorderWithRoundCorners"> 
								<%--<table class="tableBorderWithRoundCorners">--%>
								<tr>
									<td class="text11">&nbsp;</td>
									<td align="center" class="tableHeaderField12"><b>Antal</b></td>
									<td align="center" class="tableHeaderField12"><b>B.vikt</b></td>
									<td align="center" class="tableHeaderField12"><b>F.belop</b></td>
								</tr>
								<tr>
									<td class="text12">Huvud</td>
									<td align="right" class="tableCellFirst12">${recordTopic.svih_kota}</td>
									<td align="right" class="tableCell12">${recordTopic.svih_brut}</td>
									<td align="right" class="tableCell12">${recordTopic.svih_fabl}</td>
								</tr>
								<tr>	
									<td class="text12">Linje</td>
									<td align="right" class="tableCellFirst12">${model.recordItemContainerTopic.calculatedItemLinesTotalKolli}</td>
									<td align="right" class="tableCell12">${model.recordItemContainerTopic.calculatedItemLinesTotalGrossWeight}</td>
									<td align="right" class="tableCell12">${model.recordItemContainerTopic.calculatedItemLinesTotalAmount}</td>
								</tr>
								<tr>	
									<td class="text12">Diff.</td>
									<%--Diff Antal --%>
									<c:choose>
									<c:when test="${model.recordItemContainerTopic.diffItemLinesTotalKolliWithInvoiceTotalKolliInt != 0}">
										<td align="right" class="tableCell12" style="background-color:lightyellow;color:red;" >${model.recordItemContainerTopic.diffItemLinesTotalKolliWithInvoiceTotalKolli}</td>
									</c:when>
									<c:otherwise>
										<td align="right" class="tableCell12" style="background-color:lightyellow;" >${model.recordItemContainerTopic.diffItemLinesTotalKolliWithInvoiceTotalKolli}</td>
									</c:otherwise>
									</c:choose>
									
									<%--Diff B.vikt --%>
									<c:choose>
									<c:when test="${model.recordItemContainerTopic.diffItemLinesTotalGrossWeightWithInvoiceTotalGrossWeightDbl != 0}">
										<td align="right" class="tableCell12" style="background-color:lightyellow;color:red;" >${model.recordItemContainerTopic.diffItemLinesTotalGrossWeightWithInvoiceTotalGrossWeight}</td>
									</c:when>
									<c:otherwise>
										<td align="right" class="tableCell12" style="background-color:lightyellow;" >${model.recordItemContainerTopic.diffItemLinesTotalGrossWeightWithInvoiceTotalGrossWeight}</td>
									</c:otherwise>
									</c:choose>
									
									<%--Diff F.belopp --%>
									<c:choose>
									<c:when test="${fn:contains(model.recordItemContainerTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount,'-')}">
										<td align="right" class="tableCell12" style="background-color:lightyellow;color:red;" >${model.recordItemContainerTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount}</td>
									</c:when>
									<c:otherwise>
										<td align="right" class="tableCell12" style="background-color:lightyellow;" >${model.recordItemContainerTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount}</td>
									</c:otherwise>
									</c:choose>
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
							<form id="createNewItemLine" action="tdsimport_edit_items.do">
								<input type="hidden" name="action" id="action" value='doFetch'>
				 				<input type="hidden" name="avd" id="avd" value='${model.avd	}'>
				 				<input type="hidden" name="sign" id="sign" value='${model.sign}'>
								<input type="hidden" name="opd" id="opd" value='${model.opd}'>
				 				<input type="hidden" name="tullId" id="tullId" value='${model.tullId}'>
				 				<input type="hidden" name="status" id="status" value='${model.status}'>
				 				<input type="hidden" name="datum" id="datum" value='${model.datum}'>
				 				<input type="hidden" name="fabl" id="fabl" value='${recordTopic.svih_fabl}'>
				 				<input type="hidden" name="fablAutoControl" id="fablAutoControl" value='${recordTopic.svih_fabl}'/>
				 				<input type="hidden" name="receiverId" id="receiverId" value='${recordTopic.svih_mokn}'>
				 										
								<table width="100%" cellspacing="0" border="0" cellpadding="0">
									<tr>
										<td class="text14Bold">&nbsp;Varuposter&nbsp;&nbsp;
											<c:if test="${model.status == 'M' || empty model.status}">
												<input tabindex=-1 class="inputFormSubmitStd" type="submit" name="submitSkapaNy" value='Skapa ny'>
											</c:if>
											<button tabindex=-1 name="allItemsButton" class="inputFormSubmitStd" type="button" onClick="showPop('allItems');" >Visa alla</button>
											        <span style="background-color:#EEEEEE; position:absolute; left:50px; top:200px; width:1200px; height:1000px;" id="allItems" class="popupWithInputTextThickBorder"  >
										           			<table id="containerdatatableTable" align="left" >
										           			<tr>
											           			<td colspan="3" class="text14"><b>Varuposter</b></td>
											           		</tr>
												           	<tr>	
																<td class="text14" width="95%" >
																<table id="tblItemLinesAll" class="display compact cell-border" width="100%" >
																	<thead> 
																	<tr class="tableHeaderField" >
																	    <th class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.sviv_vano.varupostNr"/>&nbsp;</th>   
													                    <th class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.sviv_ulkd.urspLand"/>&nbsp;</th>
													                    <th class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.sviv_vata.varukod"/><font class="text9">Taric.nr</font>&nbsp;</th>
													                    <th class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.sviv_fokd.formanskod"/>&nbsp;</th>
													                    <th class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.sviv_eup1.forfarande1"/>&nbsp;</th>
													                    <th class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.sviv_brut.bruttoVikt"/>&nbsp;</th>
													                    <th class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.sviv_neto.nettoVikt"/>&nbsp;</th>
													                    <th class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.sviv_ankv.extraMangd"/>&nbsp;</th>
													                    <th class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.sum_of_sviv_kotas.kolliAnt"/>(&Sigma;)</th>
													                    <th class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.sviv_vasl.varuBeskrivning"/>&nbsp;</th>
													                    <th class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.sviv_err.error"/>&nbsp;</th>
													                    <th class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.sviv_fabl.fbelopp"/>&nbsp;</th>
													                    <th class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.sviv_stva.statvarde"/>&nbsp;</th>
													                    
													                    <c:if test="${model.status == 'M' || empty model.status}">
													                    	<th align="center" class="text14" nowrap>Radera</th>
													                    </c:if>   
													                   
													                </tr> 
																	</thead>
																	<tbody>
														           <c:forEach items="${model.list}" var="record" varStatus="counter">    
													               	 <tr class="tableRow" >
														               <td class="text12" >&nbsp;${record.sviv_vano}</td>
														               <td class="text12" >&nbsp;${record.sviv_ulkd}</td>
														               <td class="text12" >&nbsp;${record.sviv_vata}</td>
														               <%--<td class="text12" >&nbsp;${record.sviv_vati}</td> --%>
														               <td class="text12" >&nbsp;${record.sviv_fokd}</td>
														               <td class="text12" >&nbsp;${record.sviv_eup1}</td>
														               <td class="text12" >&nbsp;${record.sviv_brut}</td>
														               <td class="text12" >&nbsp;${record.sviv_neto}</td>
														               <td class="text12" >&nbsp;${record.sviv_ankv}</td>
														               <td class="text12" >&nbsp;${record.sum_of_sviv_kotas}</td>
														               <td class="text12" >&nbsp;${record.sviv_vasl}</td>
														               
														               <td align="center" class="text12">&nbsp;
														               		<c:if test="${not empty record.sviv_err}">
														               			<img valign="bottom" src="resources/images/redFlag.png" width="18px" height="18px" border="0" alt="remove">
														               		</c:if>
														               	</td>	
														               <td class="text12">${record.sviv_fabl}</td>
														               <td class="text12">${record.sviv_stva}</td>
														               
														               <c:if test="${model.status == 'M' || empty model.status}">	
															               <td class="text12" align="center" nowrap>&nbsp;
															               	<a onclick="javascript:return confirm('Är du säker på att du vill radera raden?')" tabindex=-1 href="tdsimport_edit_items.do?action=doDelete&avd=${model.avd}&opd=${model.opd}&lin=${record.sviv_syli}&fabl=${recordTopic.svih_fabl}">
															               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
															               	</a>	
															               </td>
														               </c:if> 
															         </tr>
															         
															        <%-- <c:set var="numberOfItemLinesInTopic" value="${counter.count}" scope="request" />  --%>
															        <c:set var="numberOfItemLinesInTopic" value="${record.sviv_syli}" scope="request" /> 
																   	<c:if test="${counter.count == 1}">
																   		<label id="godmFirstLine" style="display:none;" >${record.sviv_godm}</label>
															     	</c:if>
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
															 	        		<a href="tdsImportItemListExcelView.do" target="_new">
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
										<%--
										<td align="right" class="text12">Fakturabelopp:&nbsp;
											<input tabindex=-1 align="right" type="text" readonly class="inputText12BlueBoldReadOnly" size="16" maxlength="20" value='${recordTopic.svih_fabl}'>
										</td>
										<td align="right" class="text12">Varulinjebelopp:&nbsp;
											<input tabindex=-1 align="right" type="text" readonly class="inputText12BlueBoldReadOnly" size="16" maxlength="20" value='${model.recordItemContainerTopic.calculatedItemLinesTotalAmount}'>
										</td>
										<td align="right" class="text12">Differans:&nbsp;
											<input tabindex=-1 align="right" type="text" readonly
												<c:choose>
												<c:when test="${fn:contains(model.recordItemContainerTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount,'-')}">
													class="inputText12RedBoldReadOnly" 
												</c:when>
												<c:otherwise>
													class="inputText12BlueBoldReadOnly"
												</c:otherwise>
												</c:choose>
												size="16" maxlength="20" value='${model.recordItemContainerTopic.diffItemLinesTotalAmountWithInvoiceTotalAmount}'>
										</td>
										 --%>
									</tr>
									<tr height="2"><td></td></tr>
								</table>
							</form>
							</td>
						</tr> 
						<tr>
							<td >
								<form name="formItemList" id="formItemList" method="POST" >
			               		<input type="hidden" name="opdItemList" id="opdItemList" value="${model.opd}">
	 							<input type="hidden" name="avdItemList" id="avdItemList" value="${model.avd}"> 
		 						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
				 				
				 				<table width="100%" id="containerdatatableTable" cellspacing="2" align="left" >
								<tr>
								<td class="text12">	
							
								<table id="tblItemLines" class="display compact cell-border" >
									<thead>
									<tr class="tableHeaderField" >
										<th width="2%" class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.update"/>&nbsp;</th>   
					                    <th class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.sviv_vano.varupostNr"/>&nbsp;</th>   
					                    <th class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.sviv_ulkd.urspLand"/>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.sviv_vata.varukod"/><font class="text9">Taric.nr</font>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.sviv_fokd.formanskod"/>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.sviv_eup1.forfarande1"/>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.sviv_brut.bruttoVikt"/>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.sviv_neto.nettoVikt"/>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.sviv_ankv.extraMangd"/>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.sum_of_sviv_kotas.kolliAnt"/>(&Sigma;)</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.sviv_vasl.varuBeskrivning"/>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.sviv_err.error"/>&nbsp;</th>
										<th class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.sviv_fabl.fbelopp"/>&nbsp;</th>
										<th class="text14">&nbsp;<spring:message code="systema.tds.import.item.list.label.sviv_stva.statvarde"/>&nbsp;</th>
										<c:if test="${model.status == 'M' || empty model.status}">
					                    	<th align="center" class="text14" nowrap>Radera</th>
					                    </c:if>
					               </tr> 
					               </thead>
					               <tbody>	 
							           <c:forEach items="${model.list}" var="record" varStatus="counter">    
							               <tr class="tableRow" >
							               <td width="2%" class="text14" align="center">
					               				<a style="display:block;" tabindex=-1 id="recordUpdate_${record.sviv_syli}_${record.sviv_vano}" href="#" onClick="getItemData(this);">
					               					<img src="resources/images/update.gif" border="0" alt="edit">&nbsp;
						               			</a>
							               </td>
							               <td width="4%" class="text14" align="center">${record.sviv_vano}</td>
							               <td class="text14" >&nbsp;${record.sviv_ulkd}</td>
							               <td class="text14" >&nbsp;${record.sviv_vata}&nbsp;&nbsp;
							               	  <img id="recordUpdate_${record.sviv_syli}_${record.sviv_vano}" onClick="updateKundensVarReg(this);" src="resources/images/addOrder.png" width="12px" height="12px" border="0" title="Lägg till i kundensvarureg.">
							               </td>
							               <td class="text14" >&nbsp;${record.sviv_fokd}</td>
							               <td class="text14" >&nbsp;${record.sviv_eup1}</td>
							               <td class="text14" >${record.sviv_brut}</td>
							               <td class="text14" >${record.sviv_neto}</td>
							               <td class="text14" >${record.sviv_ankv}</td>
							               <td class="text14" >${record.sum_of_sviv_kotas}</td>
							               <td class="text14" >&nbsp;${record.sviv_vasl}</td>
							               <td align="center" class="text14">&nbsp;
							               		<c:if test="${not empty record.sviv_err}">
							               			<img valign="bottom" src="resources/images/redFlag.png" width="18px" height="18px" border="0" alt="remove">
							               		</c:if>
						               		</td>		
							               <td class="text14">${record.sviv_fabl}</td>
							               <td class="text14">${record.sviv_stva}</td>
							              
							               <c:if test="${model.status == 'M' || empty model.status}">	
								               <td class="text14" align="center" nowrap>&nbsp;
								               	<a onclick="javascript:return confirm('Är du säker på att du vill radera raden?')" tabindex=-1 href="tdsimport_edit_items.do?action=doDelete&avd=${model.avd}&opd=${model.opd}&lin=${record.sviv_syli}&fabl=${recordTopic.svih_fabl}">
								               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
								               	</a>	
								               </td>
							               </c:if>
							               
							           </tr>
								        <%-- <c:set var="numberOfItemLinesInTopic" value="${counter.count}" scope="request" />  --%>
								        <c:set var="numberOfItemLinesInTopic" value="${record.sviv_syli}" scope="request" /> 
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
	 			<form name="tdsImportEditTopicItemForm" id="tdsImportEditTopicItemForm" action="tdsimport_edit_items.do" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="opd" id="opd" value="${model.opd}"/>
				 	<input type="hidden" name="avd" id="avd" value="${model.avd}"/>
				 	<input type="hidden" name="sign" id="sign" value="${model.sign}"/>
				 	<input type="hidden" name="tullId" id="tullId" value="${model.tullId}"/>
				 	<input type="hidden" name="status" id="status" value="${model.status}"/>
				 	<input type="hidden" name="datum" id="datum" value="${model.datum}"/>
				 	<input type="hidden" name="fabl" id="fabl" value="${recordTopic.svih_fabl}"/>
				 	
				 	<input type="hidden" name="sviv_syli" id="sviv_syli" value=''/>
				 	<input type="hidden" name="sviv_vano" id="sviv_vano" value=''/>
				 	
				 	<input type="hidden" name="numberOfItemLinesInTopic" id="numberOfItemLinesInTopic" value="${numberOfItemLinesInTopic}" />
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="100%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text14White">
				 				<b>&nbsp;&nbsp;Varupost&nbsp;</b>
								<img onClick="showPop('updateInfo');" valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 				<span style="position:absolute; left:150px; top:200px; width:800px; height:400px;" id="updateInfo" class="popupWithInputText"  >
					           		<div class="text14" align="left">
					           			<br/>${activeUrlRPGUpdate}<br/><br/>
					           			<button name="updateInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('updateInfo');">Close</button> 
					           		</div>
						        </span>				 				
				 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 				<c:choose>
					 				<c:when test="${not empty model.record.sviv_syli}">
				 						<input title="from model" tabindex=-1 align="center" class="text14BoldLightGreenForItemLinenr" readonly type="text" name="lineNr" id="lineNr" size="3" value="${model.record.sviv_syli}">
				 					</c:when>
				 					<c:otherwise>
				 						<c:choose>
						 					<c:when test="${not empty sviv_syli_SESSION}">
					 							<input title="from session" tabindex=-1 align="center" class="text14BoldLightGreenForItemLinenr" readonly type="text" name="lineNr" id="lineNr" size="3" value="${sviv_syli_SESSION}">
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
							 			<img onMouseOver="showPop('34a_info');" onMouseOut="hidePop('34a_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>34a.</b><font class="text16RedBold" >*</font><span title="sviv_ulkd">Ursp.land</span>
										<div class="text14" style="position: relative;" align="left" >
										<span style="position:absolute;top:2px; width:250px;" id="34a_info" class="popupWithInputText text14"  >
						           			<b>Ursprungslandets kod</b>
						           			<br/>
						           			Här anger du landkoden för ursprungslandet för de varor som du har tagit upp i varuposten.
						           			Landkoden består av två bokstäver (ISO landkoder).
						           			<br/><br/>
											Observera att koden EU endast får användas vid återimport och när du samtidigt yrkar på preferenstull, det vill säga då du i fält 37
											anger en förfarandekod som börjar på 6 eller koderna 4010 och 4071 samt en förmånskod i fält 36 som börjar på 2 eller 3.
						           			<br/>
										</span>
										</div>
										</td>
							 			
							 			<td class="text14" align="left">
							            <img onMouseOver="showPop('33_info');" onMouseOut="hidePop('33_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>33.</b>
							            <c:if test="${ fn:startsWith(recordTopic.svih_mtyp, 'D') || fn:startsWith(recordTopic.svih_mtyp, 'T') || fn:startsWith(recordTopic.svih_mtyp, 'O') }">
							            		<font class="text16RedBold" >*</font>
							            </c:if>
							            <span title="sviv_vata">Varukod</span>
					            		<div class="text14" style="position: relative;" align="left" >
										<span style="position:absolute;top:2px;width:250px;" id="33_info" class="popupWithInputText text14"  >
							           		<b>Varukod</b>
						           			<br/>
						           			Här fyller du i varukoden för den aktuella varuposten. Koden för import består av tulltaxa-numret (10 siffror) och
						           			eventuella tilläggskoder (fyra siffror).
						           			<br/><br/>
						           			I vissa fall ska du ange en eller flera tilläggskoder, exempelvis för varor som är föremål för antidumpning.
						           			<br/><br/>
						           			Du hittar information om varu- och tilläggskoder i Tulltaxa Söksystem. 
										</span>	
										</div>
										</td>
							            
							            <td class="text14" align="left"><b>&nbsp;<span title="sviv_vati">33.3</span></b>&nbsp;&nbsp;</td>
							            <td class="text14" align="left"><b>&nbsp;<span title="sviv_vat4">33.4</span></b>&nbsp;&nbsp;</td>
							            	
							            	<td class="text14" align="left">
							            <img onMouseOver="showPop('36_info');" onMouseOut="hidePop('36_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info" >
				 						<b>36.</b>
				 							<c:if test="${ fn:startsWith(recordTopic.svih_mtyp, 'D') || fn:startsWith(recordTopic.svih_mtyp, 'T') || fn:startsWith(recordTopic.svih_mtyp, 'O') }">
					 							<font class="text16RedBold" >*</font>
				 							</c:if>
				 						<span title="sviv_fokd">Förmåner</span>
				 						
				 						<div class="text14" style="position: relative;" align="left" >
				 						<span style="position:absolute;top:2px; width:250px;"id="36_info" class="popupWithInputText text14"  >
							           		<br>
						           			<b>Förmåner</b>
						           			 I det här fältet yrkar du på förmånsbehandling enligt artikel 20.4 i tullkodex. Du måste fylla i fältet även om du inte yrkar på     
											 förmånsbehandling. I så fall anger du koden 100. De vanligsta anledningarna till förmånsbehandling är varans usrprung, tullsuspension eller tullkvot. Mot et särskilt         
											 ursprungsintyg kan du få tullnedsättning eller tullfrihet. Tullkvot måste du ansöka om i fält 39. Du deklarerar genom att skriva en tresiffrig kod. Se exempel, eller   
											 tryck F4 för att söka efter giltiga koder. 
											 <br><br>
											 Första siffran i koden är 1 Normal tull. Gäller även om du yrkar på                             
 											<ul>
							           			<li><b>1</b> Normal tull.  Gäller även om du yrkar på tullfrihet enligt förordning 918/83 och för retur enligt artikel 185 i tullkodexen. </li>
							           			<li><b>2</b> Almänna preferenssystemet (GPS). </li>
							           			<li><b>3</b> Andra tullförmåner än sådana som avses i kod 2 (frihandelsavtal till exempel EFTA). </li>
							           			<li><b>4</b> Inget uttag av tull, i enlighet med avtal om tullunion som ingåtts av gemenskapen (tex. varor från Turkiet med ATR-certifikat). </li>
							           		</ul>
							           		<br/><br/>
							           		De följande två siffrorna i koden är     
 											<ul>
							           			<li><b>00</b> Inget av de följande fallen.</li>
							           			<li><b>10</b> Tullsuspension</li>
							           			<li><b>15</b> Tullsuspension med använding för särskilda ändamål. </li>
							           			<li><b>18</b> Tullsuspension med intyg om produktens särskilda karaktär.</li>
							           			<li><b>19</b> Tilfällig tullbefrielse för produkter som importeras med luftvärdighetsbevis.  </li>
							           			<li><b>20</b> Tullkvot</li>
							           			<li><b>23</b> Tullkvot med användning för särskilda ändamål.</li>
							           			<li><b>25</b> Tullkvot med intyg om produktens särskilda karaktär.</li>
							           			<li><b>28</b> Tullkvot efter passiv förädling. </li>
							           			<li><b>40</b> Användning för särskilda ändamål til följd av Gemensamma tulltaxan. </li>
							           			<li><b>50</b> Intyg om produktens särskilda karaktär. </li>
							           		</ul>
							           		<br/><br/>
							           		 Du hittar information om möjliga kodkombinationer i Tulltaxa Söksystem.
											 Om du behöver hjälp kan du kontakta TullSvar.                       
							           		
										</span>
				 						</div>
							            </td>
							            
							            <td class="text14" align="left">
							            <img onMouseOver="showPop('37_1_info');" onMouseOut="hidePop('37_1_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>37.</b><font class="text16RedBold" >*</font><span title="sviv_eup1">Förf. 1</span>
				 						
				 						<div class="text14" style="position: relative;" align="left" >
							            <span style="position:absolute;top:2px;width:250px;" id="37_1_info" class="popupWithInputText text14"  >
							           		<br>
						           			<b>Förfarande - Första delfältet</b>
						           			<br/>
						           			Här ska du ange en fyrställig kod som består av två tvåställiga koder. Koden visar vilket importförfarande som avses. De två första siffrorna anger det förfarande som begärs och de två sista siffrorna det föregående förfarandet.
						           			<br/><br/>
						           			Exempel kod 4000<br/>
						           			De två första siffrorna i koden visar det tullförfarande som du anmäler varorna till. 
						           			40 står övergång till fri konsumtion med samtidig övergång till fri omsättning av varor som inte är föremål
						           			för momsbefriad leverans.. De två sista siffrorna i koden visar om varorna tidigare varit föremål för ett tullförfarande. 
						           			00 innebär att det inte finns något föregående tullförfarande.   
						           			<br/><br/>
											En förteckning över de 2- och 6-ställiga koderna finns i Tulltaxa Söksystem.
						           			<br/><br/>
							           		
										</span>
							            </div>
							            </td>
							            
							            <td class="text14" align="left">
							            <img onMouseOver="showPop('37_2_info');" onMouseOut="hidePop('37_2_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>37.</b><span title="sviv_eup2">Förf. 2</span>
				 						<div class="text14" style="position: relative;" align="left" >
							            <span style="position:absolute;top:2px; width:250px;"id="37_2_info" class="popupWithInputText text14"  >
							           		<br>
						           			<b>Förfarande - Andra delfältet</b>
						           			Vid vissa förfarande måste du ange ytterligare en kod i 37:2 som komplement till fält 37:1. Vissa koder har fastställts av kommissionen men även nationella koder får förekomma. Koden består av tre tecken. Om ingen lämplig kod finns lämnas fältet tomt.   
											Koder fastställda av kommissionen består av en bokstav och två siffror.
						           			<ul>
							           			<li><b>Axx</b> Aktiv förädling</li>
							           			<li><b>Bxx</b> Passiv förädling </li>
							           			<li><b>Cxx</b> Befrielse enligt förordning 918/83</li>
							           			<li><b>Dxx</b> Temporär import </li>
							           			<li><b>Exx</b> Jordbruksprodukter (enhetspris och schablonvärde) </li>
							           			<li><b>Fxx</b> Andra (returvaror, reparation, bearbetning under tullkontroll med mera) </li>
							           		</ul>
							           		
							           		Nationella koder kan förekomma och består av två siffror och en bokstav.
											<br/><br/>
											Du hittar koderna i vår kodförteckning i Tulltaxa Söksystem.
							           		
										</span>
							            </div>
							            </td>
							            
							            <td class="text14" align="left">
							            <img onMouseOver="showPop('35_info');" onMouseOut="hidePop('35_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>35.</b><font class="text16RedBold" >*</font><span title="sviv_brut">Brut.vikt(kg)</span>
							            
							            <div class="text14" style="position: relative;" align="left" >
							            <span style="position:absolute;top:2px; width:250px;"id="35_info" class="popupWithInputText text14"  >
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
										<td class="text14" align="left">
										<b>&nbsp;38.</b>&nbsp;&nbsp;<font class="text16RedBold" >*</font><span title="sviv_neto">Net.vikt(kg)</span></td>
										
							        </tr>
							        <tr>
							        	<td align="left" >&nbsp;<button tabindex=-1 title="Kundens vareregister" name="kundensVaruregisterControlButton" id="kundensVaruregisterControlButton" class="buttonGrayWithGreenFrame" type="button" >Sök i kund.varureg.</button></td>
							        	<td align="left">
							            	<select class="inputTextMediumBlueMandatoryField" name="sviv_ulkd" id="sviv_ulkd">
						 						<option value="">-Välj-</option>
							 				  	<c:forEach var="country" items="${model.gcyCodeList}" >
							 				  		<option value="${country.svkd_kd}"<c:if test="${model.record.sviv_ulkd == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
												</c:forEach>  
											</select>
											<a id="sviv_ulkdIdLink" >
						            			<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
						            		</a>																	 			
										</td>
							            <td class="text14" align="left">
							            	<a id="tillaggskoderLink"></a>
							            	<input onKeyPress="return numberKey(event)" onBlur="getFormanskodOnBlurVata(this)" type="text" class="inputTextMediumBlueMandatoryField" name="sviv_vata" id="sviv_vata" size="12" maxlength="10" value="${model.record.sviv_vata}">
							            	<a tabindex="-1" id="sviv_vataIdLink">
           										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
           									</a>
           										
							            	<%-- 
							            	<input onKeyPress="return numberKey(event)" onBlur="getFormanskodOnBlurVata(this)" type="text" class="inputTextMediumBlueMandatoryField" name="sviv_vata" id="sviv_vata" size="12" maxlength="10" value="${model.record.sviv_vata}">
							            	
							            	<img id="imgTaricVarukodSearch" style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" onClick="showPop('searchTaricCodesDialog');">
							            	<%-- ======================================================== --%>
						            		<%-- Here we have the search Taric codes popup window --%>
						            		<%-- ======================================================== --%>
						            		
						            		<%--
						            		<span style="position:absolute; left:300px; top:450px; width:500px; height:210px;" id="searchTaricCodesDialog" class="popupWithInputText"  >
							           		<div class="text10" align="left">
							           			<table>
								           			<tr>
								           			<td>
									           			<table>
									           				<tr>
																<td class="text14">&nbsp;Varukod</td>
																<td class="text14">&nbsp;<input type="text" class="inputText" name="search_svvs_vata" id="search_svvs_vata" size="10" maxlength="10" value=""></td>
															</tr>
										           			<tr>
										           				<td align="right">&nbsp;<button name="searchTaricCode" id="searchTaricCode" class="buttonGray" type="button" onClick="searchTaricVarukod();">Sök</button></td>
										           				<td class="text14">&nbsp;</td>
											           			
											           		</tr>
											           		<tr height="4"><td ></td></tr>
										           		</table>
									           		</td>
									           		</tr>
													
													<tr>
								           			<td>
									           			<table>							           		
											           		<tr>
										           				<td class="text14">&nbsp;Lista</td>
											           			<td>&nbsp;</td>
											           		</tr>
											           		<tr>
																<td colspan="2">&nbsp;
																	<select class="text14" id="taricVarukodList" name="taricVarukodList" size="5" onDblClick="hidePop('searchTaricCodesDialog');">
					 													<option selected value="">-Välj-</option>
					 							 					</select>
																</td>
															</tr>
									           			</table>
								           			</td>
								           			</tr>
														
													<tr>
								           			<td>								           			
														<table width="100%" align="left" border="0">
															<tr align="left" >
																<td >&nbsp;<button name="searchTaricCodesClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchTaricCodesDialog');">Ok</button></td>
															</tr>
														</table>
													</td>
													</tr>
												</table>
											</div>
										</span>
							             --%>
							            
										</td>
										<td class="text14" align="left"><input type="text" class="inputTextMediumBlue" name="sviv_vati" id="sviv_vati" size="4" maxlength="4" value="${model.record.sviv_vati}"></td>
										<td class="text14" align="left"><input type="text" class="inputTextMediumBlue" name="sviv_vat4" id="sviv_vat4" size="4" maxlength="4" value="${model.record.sviv_vat4}"></td>
										
										<td class="text14" align="left">
											<a id="bhandlingarYkoderLink"></a>
							            	<select class="inputTextMediumBlueMandatoryField" name="sviv_fokd" id="sviv_fokd">
						 						<option value="">-Välj-</option>
							 				  	<c:forEach var="code" items="${model.formanCodeList}" >
							 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_fokd == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
												</c:forEach>  
											</select>
											<a tabindex="-1" id="sviv_fokdIdLink" >
						            			<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
					            			</a>																	 			
										</td>
										
										<td align="left">
											<select class="inputTextMediumBlueMandatoryField" name="sviv_eup1" id="sviv_eup1">
						 						<option value="">-Välj-</option>
							 				  	<c:forEach var="code" items="${model.forfarande01CodeList}" >
							 				  		<c:choose>
														<c:when test="${not empty model.record.sviv_eup1}">
								 				  			<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_eup1 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
								 				  		</c:when>
								 				  		<c:otherwise>
								 				  			<option value="${code.svkd_kd}"<c:if test="${recordTopic.svih_eup1 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
								 				  		</c:otherwise>
							 				  		</c:choose>
												</c:forEach>  
											</select>
											<a tabindex="-1" id="sviv_eup1IdLink" >
						            			<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
					            			</a>	
						 				</td>
						 				<td align="left">	
						 					<select class="selectMediumBlueE2" name="sviv_eup2" id="sviv_eup2">
						 						<option value="">-Välj-</option>
							 				  	<c:forEach var="code" items="${model.forfarande02CodeList}" >
							 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_eup2 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
												</c:forEach>  
											</select>	
											<a tabindex="-1" id="sviv_eup2IdLink" >
					            				<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
					            			</a>	
						 				</td>
						 				<td class="text14" align="left"><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="sviv_brut" id="sviv_brut" size="12" maxlength="13" value="${model.record.sviv_brut}"></td>
										<td class="text14" align="left"><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="sviv_neto" id="sviv_neto" size="10" maxlength="11" value="${model.record.sviv_neto}"></td>
										
							        </tr>

							        <tr height="10"><td class="text" align="left" colspan="12"><hr></td></tr>
							        
									<tr>
										<td class="text14" align="left">
										<img onMouseOver="showPop('41_info');" onMouseOut="hidePop('41_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>41.</b><span title="sviv_ankv">Extra mängd:&nbsp;</span>
										<div class="text14" style="position: relative;" align="left" >
										<span style="position:absolute; top:2px; width:250px;"id="41_info" class="popupWithInputText text14" >
											<b>Du anger uppgiften i heltal. Enheten anges inte.</b>
											<br/><br/>Detta fält fyller du bara i för vissa varor. Det kan exempelvis vara uppgift om styck, liter eller kubikmeter. Det är varukoden i fält 33 som styr om fältet ska fyllas i eller inte.
											<br/><br/>I Tulltaxa Söksystem ser du om uppgiften ska deklareras samt vilken mängdenhet du ska ange. 
											
										</span>
										</div>
										</td>
										<td class="text14">
										<img onMouseOver="showPop('31_kantal_info');" onMouseOut="hidePop('31_kantal_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										<span id="kotaRubrik">
											<b>31.</b>
										</span>
				 						<span title="sviv_kota">Kolli antal</span>
										<div class="text14" style="position: relative;" align="left" >
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
				 						<b>31.</b><font class="text16RedBold" >*</font><span title="sviv_kosl">Kolli slag</span>
										<div class="text14" style="position: relative;" align="left" >
										<span style="position:absolute;top:2px; width:250px;" id="31_kslag_info" class="popupWithInputText text14"  >
							           		<br/>
						           			<b>Kollislag</b>
											<br/>
						           			Här anger du kod för hur godset är förpackat, exempelvis kartonger. Om en vara är förpackad i flera olika kollislag kan du ange flera kollislag i samma varupost. Till detta hör även antal kolli och godsmärkning.
										</span>
							            </div>
							            </td>
							            
										<td class="text14" align="left" colspan="3">
										<img onMouseOver="showPop('31_varubesk_info');" onMouseOut="hidePop('31_varubesk_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>31.</b><font class="text16RedBold" >*</font><span title="sviv_vasl">Varubeskrivning&nbsp;</span>
										
										<div class="text14" style="position: relative;" align="left" >
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
				 						<b>31.</b><span title="sviv_godm">Godsmärkning&nbsp;</span>
							            <div class="text14" style="position: relative;" align="left" >
							            <span style="position:absolute;top:2px; width:250px;" id="31_gods_info" class="popupWithInputText text14"  >
							           		<br/>
						           			<b>Godsmärkning</b>
											<br/>
						           			Här anger du hur de kollin som varorna är förpackade i är märkta, obligatoriskt för emballerat gods. 
						           			<br/><br/>
						           			Fältet är frivilligt för bulkvaror:<br/>
						           			<b>(VR, VY, VO, VL, VG eller VQ)</b> <br/>
						           			och oemballerat gods:<br/> 
						           			<b>(NE, NF, NG)</b>
						           			
										</span>
							            </div>
							            </td>
							            
							            <td class="text14" align="left"><b>42.</b>
							            		<c:if test="${ fn:startsWith(recordTopic.svih_mtyp, 'D') || fn:startsWith(recordTopic.svih_mtyp, 'T') || fn:startsWith(recordTopic.svih_mtyp, 'O') }">
							            			<font class="text16RedBold" >*</font>
							            		</c:if>
							            		<span title="sviv_fabl">Varans pris:&nbsp;</span>
						            		</td>
							            <td>&nbsp;</td>
							            <td>&nbsp;</td>
							        </tr>
							        
							        
									<tr>
										<td class="text14" align="left"><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="sviv_ankv" id="sviv_ankv" size="10" maxlength="10" value="${model.record.sviv_ankv}"></td>
					           			<td class="text14" valign="bottom">
											&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="sviv_kota" id="sviv_kota" size="5" maxlength="5" value="${model.record.sviv_kota}">
										</td>
										<td align="left">
					            			<select class="inputTextMediumBlueMandatoryField" name="sviv_kosl" id="sviv_kosl">
							            		<option value="">-Välj-</option>
							 				  	<c:forEach var="code" items="${model.kolliCodeList}" >
			                                	 	<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_kosl == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
												</c:forEach> 
											</select>
											<a tabindex="-1" id="sviv_koslIdLink">
						            			<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
					            			</a>	
										
				            				</td> 										
										<td class="text14" align="left" colspan="3"><input type="text" class="inputTextMediumBlueMandatoryField" name="sviv_vasl" id="sviv_vasl" size="32" maxlength="70" value="${model.record.sviv_vasl}"></td>
							            
							            <td class="text14" align="left"><button name="itemDescriptionExtraInformationButton" class="buttonGray" type="button" onClick="showPop('itemDescriptionExtraInformation');" >Flera varubesk.</button> 
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
																&nbsp;31.2 Varubeskrivning<input type="text" class="inputTextMediumBlue" name="sviv_vas2" id="sviv_vas2" size="35" maxlength="70" value="${model.record.sviv_vas2}">
															</td>
														</tr>
														<tr>
										           			<td class="text14">
																&nbsp;31.3 Varubeskrivning<input type="text" class="inputTextMediumBlue" name="sviv_vas3" id="sviv_vas3" size="35" maxlength="70" value="${model.record.sviv_vas3}">
															</td>
														</tr>
														<tr>
										           			<td class="text14">
																&nbsp;31.4 Varubeskrivning<input type="text" class="inputTextMediumBlue" name="sviv_vas4" id="sviv_vas4" size="35" maxlength="70" value="${model.record.sviv_vas4}">
															</td>
														</tr>
														<tr>
										           			<td class="text14">
																&nbsp;31.5 Varubeskrivning<input type="text" class="inputTextMediumBlue" name="sviv_vas5" id="sviv_vas5" size="35" maxlength="70" value="${model.record.sviv_vas5}">
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
										
										<td class="text14" align="left" colspan="2"><input type="text" class="inputTextMediumBlueUPPERCASE" name="sviv_godm" id="sviv_godm" size="25" maxlength="42" value="${model.record.sviv_godm}"></td>
							            <td colspan="2" class="text14" align="left">
							            		<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="sviv_fabl" id="sviv_fabl" size="11" maxlength="11" value="${model.record.sviv_fabl}">
							            	</td>
										<%--
										<td align="left">
											<c:choose>	
												<c:when test="${model.status == 'M' || empty model.status}">
													<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="systema.tds.import.item.createnew.submit"/>'>
												</c:when>
												<c:otherwise>
						 				    		<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value='Ej uppdaterbart'/>
						 				    	</c:otherwise>	
					 				    	</c:choose>	
										</td>
							             --%>
 							        </tr>
 							        <tr height="20"><td></td></tr>
							        <%-- Session fields needed for the AJAX calculation av Avgifter --%>
							        <input type="hidden" name="session_svih_vufr" id="session_svih_vufr" value="${recordTopic.svih_vufr}">
							        <input type="hidden" name="session_svih_vuva" id="session_svih_vuva" value="${recordTopic.svih_vuva}">
							        <input type="hidden" name="session_svih_vuku" id="session_svih_vuku" value="${recordTopic.svih_vuku}">
							        <input type="hidden" name="session_svih_vufo" id="session_svih_vufo" value="${recordTopic.svih_vufo}">
							        <input type="hidden" name="session_svih_ovko" id="session_svih_ovko" value="${recordTopic.svih_ovko}">
							        <input type="hidden" name="session_svih_kara" id="session_svih_kara" value="${recordTopic.svih_kara}">
							        <input type="hidden" name="session_svih_anra" id="session_svih_anra" value="${recordTopic.svih_anra}">
							        <input type="hidden" name="session_svih_lekd" id="session_svih_lekd" value="${recordTopic.svih_lekd}">
							        <input type="hidden" name="session_svih_vakd" id="session_svih_vakd" value="${recordTopic.svih_vakd}">
							        <input type="hidden" name="session_svih_vaku" id="session_svih_vaku" value="${recordTopic.svih_vaku}">
							        <input type="hidden" name="session_svih_fabl" id="session_svih_fabl" value="${recordTopic.svih_fabl}">
							        <%-- Model fields needed for the AJAX calculation av Bilagda handlingar --%>
							        <input type="hidden" name="session_svih_avut" id="session_svih_avut" value="${recordTopic.svih_avut}">
				 			        <input type="hidden" name="model_avd" id="model_avd" value="${model.avd}">
							        <input type="hidden" name="model_opd" id="model_opd" value="${model.opd}">
							        
							        <tr >        
 									<td valign="top" colspan="6" >&nbsp;&nbsp;
											<table class="tableBorderWithRoundCornersGray">
										        <tr >
										        
										        <td colspan="99" class="text14Bold">
										        		&nbsp;<img onMouseOver="showPop('47_info');" onMouseOut="hidePop('47_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										        		<b>47.</b>Avgifts<label onClick="showPop('debugPrintlnAjaxAdmin');" >b</label>eräkningar
														<span style="position:absolute; left:200px; top:500px; width:600px; height:180px;" id="debugPrintlnAjaxAdmin" class="popupWithInputText"  >
										           		<div class="text12" align="left">
										           			<label id="debugPrintlnAjaxInfo"></label>
										           			&nbsp;&nbsp;&nbsp;&nbsp;<button name="specialInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('debugPrintlnAjaxAdmin');">Close</button> 
										           		</div>
										        		</span>										        		
										        		
										        		<a tabindex="-1" id="avgBerakIdLink">
					            							<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
					            						</a>	
										        
										        <div class="text14" style="position: relative;" align="left" >
										        <span style="position:absolute;top:2px; width:250px;" id="47_info" class="popupWithInputText text14"  >
									           		<br/>
								           			<b>Avgiftsberäkningar</b>
													<br/>
													<b>(1) Slag</b>
													<br/>
													Här anger du avgiftskoden, till exempel:
													<ul>
														<li> A00 för tull på industriprodukter</li>
														<li> A10 för tull på jordbrukprodukter</li>
														<li> B00 för mervärdeskatt</li>
													</ul>
													Observera att du anger kod B00 för all moms och att du måste ange momssatsen. Koden A00 använder du för varor
													som inte är märkta som jodbruksprodukter i Tulltaxa Söksystem. Om det finns både värdetull och jordbrukskomponent, eller både
													värdetull och tilläggstull på socker eller mjöl, anger du avgiftskoden A10 på 2 rader.
													<br/>
													Avgifterna anges i följande ordning:
													<ol>
														<li>Tull på industriprodukter</li>
														<li>Tull på jordbruksprodukter</li>
														<li>Övrig Moms skall alltid stå sist</li>
													</ol>
													<br/>
													Du hittar en förteckning över avgiftskoderna i Tulltaxa Söksystem (här i samma fält).
													<br/>
													<br/>
													<b>(2) Grund (Beskattningsgrund)</b>
													<br/>
													Här anger du det värde som du ska använda för att beräkna avgiften. Beskattningsgrunden för värdetullar
													utgörs av varans tullvärde, det vill säga kostnaden för att få varan till EU:s gränsort, men kan även
													vara en vikt eller ett antal. Du skall inte ange någon enhet, till exempel kr eller kg.
													<br/>
													Decimaler får användas.
													<br/>
													För beräkning av beskattningsunderlaget för moms hänvisasr vi till Mervärdesskattelagen 7 kap 8 § (1994:200).
													<br/><br/>
													<b>(3) Sats (Tullsats)</b>
													<br/>
													Här anger du avgiftssatsen i kronor, per kg, liter etc. För varor som är underkastade tull eller skatt efter värdet anges
													skattesatsen i procent. Du skall även ange vilken enhet det rör sig om, till ex. 8%. Enheten anges i ett eget fält.
													Observera att du måste ange skattesatsen för momsen.
													
												</span>
										        </div>
										        </td>
										        
										        </tr>
												<tr height="5"><td></td></tr>
												<tr>
													<td class="text14" align="left" >&nbsp;<span title="sviva_abk1">Slag</span></td>
			                							<td class="text14" align="left" >&nbsp;<span title="sviva_abg1">Grund</span></td>
			                							<td class="text14" align="left" >&nbsp;<span title="sviva_abs1">Sats</span></td>
			                							<td class="text14" align="left" >&nbsp;
			                							<img onMouseOver="showPop('47_enhet_info');" onMouseOut="hidePop('47_enhet_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			                								<span title="sviva_abx1">Enhet</span>
			                							
			                							
			                						<div class="text14" style="position: relative;" align="left" >	
			                						<span style="position:absolute;top:2px; width:250px;" id="47_enhet_info" class="popupWithInputText text14"  >
										           		<br/>
									           			<b>Beräkningsenheter Gemensamma registret</b> 
									           			<ul>
									           			<c:forEach var="code" items="${model.mdmCodeList}" >
									           				<li>
				                                	 			<b>${code.svkd_kd}</b>&nbsp;${code.svkd_kbs}
				                                	 		</li>
														</c:forEach>
									           			</ul>
													</span>
													</div>
													</td>
													
		                							<td class="text14" align="left" >&nbsp;<span title="sviv_abb1">Belopp</span></td>
			                						</tr>
			 									<tr>
													<td class="text14MediumBlue">
														<select class="selectMediumBlueE2" name="sviva_abk1" id="sviva_abk1">
											            		<option value="">-Välj-</option>
											 				  	<c:forEach var="code" items="${model.chaCodeList}" >
						                                	 		<option value="${code.svkd_kd}"<c:if test="${model.record.sviva_abk1 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																</c:forEach> 
														</select>
													</td>
													<td class="text14MediumBlue">
														<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="sviva_abg1" id="sviva_abg1" size="10" maxlength="11" value="${model.record.sviva_abg1}">
													</td>
													<td class="text14MediumBlue">
														<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="sviva_abs1" id="sviva_abs1" size="10" maxlength="9" value="${model.record.sviva_abs1}">
													</td>
													<td class="text14MediumBlue">
														<select class="selectMediumBlueE2" name="sviva_abx1" id="sviva_abx1">
											            		<option value="">-Välj-</option>
											 				  	<c:forEach var="code" items="${model.mdmCodeList}" >
						                                	 		<option value="${code.svkd_kd}"<c:if test="${model.record.sviva_abx1 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																</c:forEach>
											 				  	
														</select>
													</td>
													<td class="text14MediumBlue">
														<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="sviva_abb1" id="sviva_abb1" size="10" maxlength="9" value="${model.record.sviva_abb1}">
													</td>
												</tr>
			 									<tr>
													<td class="text14MediumBlue">
														<select class="selectMediumBlueE2" name="sviva_abk2" id="sviva_abk2">
											            		<option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.chaCodeList}" >
						                                	 		<option value="${code.svkd_kd}"<c:if test="${model.record.sviva_abk2 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
															</c:forEach> 
														</select>
													</td>
													<td class="text14MediumBlue">
														<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="sviva_abg2" id="sviva_abg2" size="10" maxlength="11" value="${model.record.sviva_abg2}">
													</td>
													<td class="text14MediumBlue">
														<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="sviva_abs2" id="sviva_abs2" size="10" maxlength="9" value="${model.record.sviva_abs2}">
													</td>
													<td class="text14MediumBlue">
														<select class="selectMediumBlueE2" name="sviva_abx2" id="sviva_abx2">
											            		<option value="">-Välj-</option>
											 				  	<c:forEach var="code" items="${model.mdmCodeList}" >
						                                	 		<option value="${code.svkd_kd}"<c:if test="${model.record.sviva_abx2 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																</c:forEach> 
														</select>
													</td>
													<td class="text14MediumBlue">
														<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="sviva_abb2" id="sviva_abb2" size="10" maxlength="9" value="${model.record.sviva_abb2}">
													</td>
												</tr>
			 									<tr>
													<td class="text14MediumBlue">
														<select class="selectMediumBlueE2" name="sviva_abk3" id="sviva_abk3">
											            		<option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.chaCodeList}" >
					                                	 		<option value="${code.svkd_kd}"<c:if test="${model.record.sviva_abk3 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
															</c:forEach> 
														</select>
													</td>
													<td class="text14MediumBlue">
														<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="sviva_abg3" id="sviva_abg3" size="10" maxlength="11" value="${model.record.sviva_abg3}">
													</td>
													<td class="text14MediumBlue">
														<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="sviva_abs3" id="sviva_abs3" size="10" maxlength="9" value="${model.record.sviva_abs3}">
													</td>
													<td class="text14MediumBlue">
														<select class="selectMediumBlueE2" name="sviva_abx3" id="sviva_abx3">
												            		<option value="">-Välj-</option>
											 				  	<c:forEach var="code" items="${model.mdmCodeList}" >
						                                	 		<option value="${code.svkd_kd}"<c:if test="${model.record.sviva_abx3 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																</c:forEach> 
														</select>
													</td>
													<td class="text14MediumBlue">
														<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="sviva_abb3" id="sviva_abb3" size="10" maxlength="9" value="${model.record.sviva_abb3}">
													</td>
												</tr>
			 									<tr>
													<td class="text14MediumBlue">
														<select class="selectMediumBlueE2" name="sviva_abk4" id="sviva_abk4">
											            		<option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.chaCodeList}" >
						                                	 		<option value="${code.svkd_kd}"<c:if test="${model.record.sviva_abk4 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
															</c:forEach> 
														</select>
													</td>
													<td class="text14MediumBlue">
														<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="sviva_abg4" id="sviva_abg4" size="10" maxlength="11" value="${model.record.sviva_abg4}">
													</td>
													<td class="text14MediumBlue">
														<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="sviva_abs4" id="sviva_abs4" size="10" maxlength="9" value="${model.record.sviva_abs4}">
													</td>
													<td class="text14MediumBlue">
														<select class="selectMediumBlueE2" name="sviva_abx4" id="sviva_abx4">
												            		<option value="">-Välj-</option>
											 				  	<c:forEach var="code" items="${model.mdmCodeList}" >
						                                	 		<option value="${code.svkd_kd}"<c:if test="${model.record.sviva_abx4 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																</c:forEach> 
														</select>
													</td>
													<td class="text14MediumBlue">
														<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="sviva_abb4" id="sviva_abb4" size="10" maxlength="9" value="${model.record.sviva_abb4}">
													</td>
												</tr>
			 									<tr>
													<td class="text14MediumBlue">
														<select class="selectMediumBlueE2" name="sviva_abk5" id="sviva_abk5">
											            		<option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.chaCodeList}" >
						                                	 		<option value="${code.svkd_kd}"<c:if test="${model.record.sviva_abk5 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
															</c:forEach> 
														</select>
													</td>
													<td class="text14MediumBlue">
														<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="sviva_abg5" id="sviva_abg5" size="10" maxlength="11" value="${model.record.sviva_abg5}">
													</td>
													<td class="text14MediumBlue">
														<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="sviva_abs5" id="sviva_abs5" size="10" maxlength="9" value="${model.record.sviva_abs5}">
													</td>
													<td class="text14MediumBlue">
														<select class="selectMediumBlueE2" name="sviva_abx5" id="sviva_abx5">
												            		<option value="">-Välj-</option>
											 				  	<c:forEach var="code" items="${model.mdmCodeList}" >
						                                	 		<option value="${code.svkd_kd}"<c:if test="${model.record.sviva_abx5 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																</c:forEach> 
														</select>
													</td>
													<td class="text14MediumBlue">
														<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="sviva_abb5" id="sviva_abb5" size="10" maxlength="9" value="${model.record.sviva_abb5}">
													</td>
												</tr>
			 									<tr>
													<td class="text14MediumBlue">
														&nbsp;
													</td>
													<td class="text14MediumBlue">
														&nbsp;
													</td>
													<td class="text14MediumBlue">
														&nbsp;
													</td>
													<td class="text14MediumBlue">
														&nbsp;
													</td>
													<td class="text14MediumBlue">
														<%--
														<input style="display: none;" class="inputFormSubmitStd" type="button" id="calcAvgifterAjax" value='Räkna om'>
														<input class="inputFormSubmitStd" type="button" id="calcAvgifterDeleteAll" value='Radera värden'>
														 --%>
														<input class="inputFormSubmitStd" type="button" id="calcAvgifterAjax" value="Beräkna">
														
													</td>
												</tr>

											</table>
 							    		</td>
 							    		
 							    		<td valign="top" colspan="6" >&nbsp;&nbsp;
										<table >
										<tr>
								            <td colspan="2" class="text14Bold" align="left" >&nbsp;Statistiska uppgifter&nbsp;
								            		<%--<input class="inputFormSubmitStd" type="button" id="calcOnlyStatisticValueAjax" value='Beräkna'>--%>
								            </td>
								            <td class="text14" align="left" >&nbsp;</td>
								        </tr>
								        <tr height="1"><td></td></tr>
								        <tr>
								            <td class="text14" align="left">
								            &nbsp;<img onMouseOver="showPop('46_info');" onMouseOut="hidePop('46_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 						<b>46.</b>
											<c:if test="${ fn:startsWith(recordTopic.svih_mtyp, 'D') || fn:startsWith(recordTopic.svih_mtyp, 'T') || fn:startsWith(recordTopic.svih_mtyp, 'O') }">
							                		<font class="text16RedBold" >*</font>
								        		</c:if>	
								            <span title="sviv_stva">Statistiskt värde:&nbsp;</span>
								            
								            <div class="text14" style="position: relative;" align="left" >
								            <span style="position:absolute; top:2px; width:250px;" id="46_info" class="popupWithInputText text14"  >
								           		<br/>
							           			<b>Statistiskt värde</b>
												<br/>
							           			Här anger du i heltal det statistiska värdet för varuposten. Observera att du ska ange värdet i svenska kronor. 
												<br/><br/>
												Med statistiskt värde menas varornas värde på den plats och vid den tidpunkt då de förs in på den importerande meldemsstatens 
												statistiska territorium, det vill säga den totala kostnaden för att få varan till svesk gränsort.
												<br/><br/>
												Tidigare har vi använt tullvärde i detta fält. När övergången till statistiskt värde skall vara fullt genomförd är ännu inte klart.
												<br/><br/>
												<b>Värdet</b><br/>
												Värdet kan anges eller beräknas mha <b">Räkna om"</b> vid 47.Avgiftsberäkningar. Värdet är inte obligatoriskt vid vissa typer av meddelande (HNU, H..., etc) .
												
											</span>
											</div>
											</td>
											
								           	<td class="text14" align="left"><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="sviv_stva" id="sviv_stva" size="10" maxlength="10" value="${model.record.sviv_stva}"></td>
								        </tr>
								        <tr>
								            <td class="text14" align="left">
								            &nbsp;<img onMouseOver="showPop('tullvarde_info');" onMouseOut="hidePop('tullvarde_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 						<span title="sviv_stva2">Tullvärde:&nbsp;</span>
					 						
					 						<div class="text14" style="position: relative;" align="left" >
								            <span style="position:absolute; top:2px; width:250px;" id="tullvarde_info" class="popupWithInputText text14"  >
								           		<br/>
							           			<b>Tullvärde</b>
												<br/>
							           			Här anger du tullvärdet för varuposten. I de flesta fall är det samma värde som <code>Statistiskt värde</code> men inte alltid. 
												<br/><br/>
												Tullvärde är den summa som du beräknar tullbeloppet på, och tullvärde är också underlag för beräkning av moms (tullvärde + tull= beskattningsunderlag). 
												<br/><br/>I tullvärdet ska, förutom priset på varan, även ingå andra kostnader som du haft eller kommer att få, för att importera din vara och som inte
												redan ingår i priset. De vanligaste exemplen är frakt- och försäkringskostnader och kostnader för lossning och lastning som uppkommer fram till platsen för införsel till EU. <br/><br/>
												<b>Värdet</b><br/>
												Värdet kan anges eller beräknas mha <b>Beräkna-knappen</b> Värdet är obligatoriskt.
												
											</span>
											</div>
											</td>
											
								           	<td class="text14" align="left"><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="sviv_stva2" id="sviv_stva2" size="10" maxlength="10" value="${model.record.sviv_stva2}"></td>
								        </tr>
								        <tr height="10"><td></td></tr>
										<tr>
											<td class="text14Bold" align="left" >
					 						&nbsp;<img onMouseOver="showPop('callme_info');" onMouseOut="hidePop('callme_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 						<font class="text16RedBold" >*</font><span title="sviv_call">Call me:&nbsp;</span>
								            
								            <div class="text14" style="position: relative;" align="left" >
								            <span style="position:absolute;top:2px; width:250px;" id="callme_info" class="popupWithInputText text14"  >
								           		<div class="text14" align="left">
							           			<br/>
							           			<b>Åtgärdsindikator (Call me-kod)</b>
												<br/><br/>
												Call me är en funktion som används för att upplysa om en sändning omfattas av export/importrestriktioner eller inte. 
												Uppgift om en vara omfattas av export- eller importrestriktioner finns i EU-lagstiftning eller i nationell lagstiftning. 
												I många fall hittar du information om restriktioner i Tulltaxa Söksystem för aktuell varukod. 
												Call me-koden kan också användas för att påkalla en tulltjänstemans uppmärksamhet i samband med klareringen.
												</div>
											</span>
											</div>
											</td>
								        </tr>
								        <tr>    
								            <td colspan="99" class="text14" align="left" >
								            	<select class="inputTextMediumBlueMandatoryField" name="sviv_call" id="sviv_call">
					 								<option value="00" <c:if test="${model.record.sviv_call == '00'}"> selected </c:if> >00 Inga restriktioner</option>
									  				<option value="01" <c:if test="${model.record.sviv_call == '01'}"> selected </c:if> >01 Restriktioner, villkoren uppfyllda</option>
									  				<option value="10" <c:if test="${model.record.sviv_call == '10'}"> selected </c:if> >10 Inga restriktioner, kontakta meddelandeavsändaren</option>
									  				<option value="11" <c:if test="${model.record.sviv_call == '11'}"> selected </c:if> >11 Restriktioner, villkoren uppfyllda kontakta meddelandeavsändaren</option>
									  				<option value="12" <c:if test="${model.record.sviv_call == '12'}"> selected </c:if> >12 Restriktioner, villkoren inte uppfyllda</option>
									  				<option value="13" <c:if test="${model.record.sviv_call == '13'}"> selected </c:if> >13 Licens</option>
									  				<option value="14" <c:if test="${model.record.sviv_call == '14'}"> selected </c:if> >14 Licens, kontakta meddelandeavsändaren</option>
									  				<option value="15" <c:if test="${model.record.sviv_call == '15'}"> selected </c:if> >15 Ansökan om vissa Tullverkets tillstånd</option>
												</select>
											</td>
								        </tr>
								        
								        <tr height="8"><td></td></tr>
								        	<tr align="left">								        
	   							 			<td colspan="10">
												<c:choose>	
													<c:when test="${model.status == 'M' || empty model.status}">
														<input class="inputFormSubmit" type="button" name="btnItemsSave" id="btnItemsSave" value='<spring:message code="systema.tds.import.item.createnew.submit"/>'>
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
 							    		
			        	        </table>
					        </td>
					        
				        </tr>
				        <tr height="10"><td class="text" align="left" colspan="12"><hr></td></tr>
				        <tr>
							<td>
							<table width="65%" class="tableBorderWithRoundCornersGray">
							        <tr >
						            <td colspan="10" class="text14">
						            &nbsp;<img onMouseOver="showPop('44a_info');" onMouseOut="hidePop('44a_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 						<b>44.</b>Särskilda upplysningar/Bilagda handlingar/Certifikat och tillstånd
						            <div class="text14" style="position: relative;" align="left" >
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
								           				<span title="sviv_bit1/sviv_bii1/..."><b>Bilagda handlingar</b></span>
								           				<a tabindex="-1" id="bilagdaHandIdLink">
		            										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
		            									</a>
								           			</td>
								        			</tr>	
							           			<tr>
								           			<td class="text14">
								           				&nbsp;1.Kod
								           				<c:choose>
								 				  		<c:when test="${not empty model.record.sviv_bit1}">
								 				  			<input size="8" maxlength="4" class="selectMediumBlueE2" list="sviv_bit1_list" id="sviv_bit1" name="sviv_bit1" value="${model.record.sviv_bit1}">
								 				  		</c:when>
								 				  		<c:otherwise>
								 				  			<input size="8" maxlength="4" class="selectMediumBlueE2" list="sviv_bit1_list" id="sviv_bit1" name="sviv_bit1" value="${recordTopic.svih_faty}">
								 				  		</c:otherwise>
								 				  		</c:choose>
								           				<datalist id="sviv_bit1_list">
														  <option value="">-Välj-</option>
														  <c:forEach var="code" items="${model.mcfCodeList}" >
										 				  	<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_bit1 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
									 				  	  </c:forEach>  
														</datalist>
								           				
								           			</td>
								           			<td class="text14">&nbsp;Identitet
															<c:choose>
																<c:when test="${not empty model.record.sviv_bii1}">
																	<input type="text" class="inputTextMediumBlue" name="sviv_bii1" id="sviv_bii1" size="35" maxlength="35" value="${model.record.sviv_bii1}">
																</c:when>
																<c:otherwise>
																	<input type="text" class="inputTextMediumBlue" name="sviv_bii1" id="sviv_bii1" size="35" maxlength="35" value="${recordTopic.svih_fatx}">
																</c:otherwise>
															</c:choose>
													</td>
												</tr>
												<tr>	
								           			<td class="text14">
														&nbsp;2.Kod
														<%-- 
								           				<select class="selectMediumBlueE2" name="sviv_bit2" id="sviv_bit2">
									 						<option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.mcfCodeList}" >
										 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_bit2 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
															</c:forEach>  
														</select>
														--%>
														<input size="8" maxlength="4" class="selectMediumBlueE2" list="sviv_bit2_list" id="sviv_bit2" name="sviv_bit2">
														<datalist id="sviv_bit2_list">
														  <option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.mcfCodeList}" >
										 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_bit2 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
															</c:forEach>  
														</datalist>
													</td>
													<td class="text14">
								           				&nbsp;Identitet<input type="text" class="inputTextMediumBlue" name="sviv_bii2" id="sviv_bii2" size="35" maxlength="35" value="${model.record.sviv_bii2}">
								           			</td>
												</tr>
												<tr>
								           			<td class="text14">
								           				&nbsp;3.Kod
								           				<%-- 
								           				<select class="selectMediumBlueE2" name="sviv_bit3" id="sviv_bit3">
									 						<option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.mcfCodeList}" >
										 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_bit3 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
															</c:forEach>  
														</select>
														--%>
														<input size="8" maxlength="4" class="selectMediumBlueE2" list="sviv_bit3_list" id="sviv_bit3" name="sviv_bit3">
														<datalist id="sviv_bit3_list">
														  <option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.mcfCodeList}" >
										 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_bit3 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
															</c:forEach>  
														</datalist>
								           			</td>
								           			<td class="text14">
								           				&nbsp;Identitet<input type="text" class="inputTextMediumBlue" name="sviv_bii3" id="sviv_bii3" size="35" maxlength="35" value="${model.record.sviv_bii3}">
								           			</td>
								           		</tr>
								           		<tr>	
							           				<td class="text14">
								           				&nbsp;4.Kod
								           				<input size="8" maxlength="4" class="selectMediumBlueE2" list="sviv_bit4_list" id="sviv_bit4" name="sviv_bit4">
														<datalist id="sviv_bit4_list">
														  <option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.mcfCodeList}" >
										 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_bit4 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
															</c:forEach>  
														</datalist>
								           			</td>
								           			<td class="text14">
								           				&nbsp;Identitet<input type="text" class="inputTextMediumBlue" name="sviv_bii4" id="sviv_bii4" size="35" maxlength="35" value="${model.record.sviv_bii4}">
								           			</td>
							           			</tr>
							           			<tr>
								           			<td class="text14">
								           				&nbsp;5.Kod
								           				<input size="8" maxlength="4" class="selectMediumBlueE2" list="sviv_bit5_list" id="sviv_bit5" name="sviv_bit5">
														<datalist id="sviv_bit5_list">
														  <option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.mcfCodeList}" >
										 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_bit5 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
															</c:forEach>  
														</datalist>
								           			</td>
								           			<td class="text14">
								           				&nbsp;Identitet<input type="text" class="inputTextMediumBlue" name="sviv_bii5" id="sviv_bii5" size="35" maxlength="35" value="${model.record.sviv_bii5}">
								           			</td>
							           			</tr>
							           			<tr>										           			
								           			<td class="text14">
								           				&nbsp;6.Kod
								           				<input size="8" maxlength="4" class="selectMediumBlueE2" list="sviv_bit6_list" id="sviv_bit6" name="sviv_bit6">
														<datalist id="sviv_bit6_list">
														  <option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.mcfCodeList}" >
										 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_bit6 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
															</c:forEach>  
														</datalist>
								           			</td>
								           			<td class="text14">
								           				&nbsp;Identitet<input type="text" class="inputTextMediumBlue" name="sviv_bii6" id="sviv_bii6" size="35" maxlength="35" value="${model.record.sviv_bii6}">
								           			</td>
							           			</tr>
							           			<tr>
								           			<td class="text14">
								           				&nbsp;7.Kod
								           				<input size="8" maxlength="4" class="selectMediumBlueE2" list="sviv_bit7_list" id="sviv_bit7" name="sviv_bit7">
														<datalist id="sviv_bit7_list">
														  <option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.mcfCodeList}" >
										 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_bit7 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
															</c:forEach>  
														</datalist>
								           			</td>
								           			<td class="text14">
								           				&nbsp;Identitet<input type="text" class="inputTextMediumBlue" name="sviv_bii7" id="sviv_bii7" size="35" maxlength="35" value="${model.record.sviv_bii7}">
								           			</td>
								           		</tr>
								           		<tr>	
								           			<td class="text14">
								           				&nbsp;8.Kod
								           				<input size="8" maxlength="4" class="selectMediumBlueE2" list="sviv_bit8_list" id="sviv_bit8" name="sviv_bit8">
														<datalist id="sviv_bit8_list">
														  <option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.mcfCodeList}" >
										 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_bit8 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
															</c:forEach>  
														</datalist>
								           			</td>
								           			<td class="text14">
								           				&nbsp;Identitet<input type="text" class="inputTextMediumBlue" name="sviv_bii8" id="sviv_bii8" size="35" maxlength="35" value="${model.record.sviv_bii8}">
								           			</td>
							           			</tr>
							           			<tr>
								           			<td class="text14">
								           				&nbsp;9.Kod
								           				<input size="8" maxlength="4" class="selectMediumBlueE2" list="sviv_bit9_list" id="sviv_bit9" name="sviv_bit9">
														<datalist id="sviv_bit9_list">
														  <option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.mcfCodeList}" >
										 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_bit9 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
															</c:forEach>  
														</datalist>
								           			</td>
								           			<td class="text14">
								           				&nbsp;Identitet<input type="text" class="inputTextMediumBlue" name="sviv_bii9" id="sviv_bii9" size="35" maxlength="35" value="${model.record.sviv_bii9}">
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
								           				&nbsp;<span title="sviv_suko">1.Kod</span>
								           				<select class="selectMediumBlueE2" name="sviv_suko" id="sviv_suko">
									 						<option value="">-Välj-</option>
										 				  	<c:forEach var="code" items="${model.salCodeList}" >
										 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_suko == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
															</c:forEach>  
														</select>
								           			</td>
								           			<td class="text14" >
								           				&nbsp;
								           			</td>
												</tr>
												<tr>
													<td colspan="2" class="text14">
														&nbsp;<span title="sviv_sutx">1.Text</span><input type="text" class="inputTextMediumBlue" name="sviv_sutx" id="sviv_sutx" size="35" maxlength="70" value="${model.record.sviv_sutx}">
													</td>
												</tr>
												<tr>	
								           			<td colspan="2" class="text14">
														&nbsp;<span title="sviv_sut2">2.Text</span><input type="text" class="inputTextMediumBlue" name="sviv_sut2" id="sviv_sut2" size="35" maxlength="70" value="${model.record.sviv_sut2}">
													</td>
												</tr>
												<tr>
													<td colspan="2" class="text14">
														&nbsp;<span title="sviv_sut3">3.Text</span><input type="text" class="inputTextMediumBlue" name="sviv_sut3" id="sviv_sut3" size="35" maxlength="70" value="${model.record.sviv_sut3}">
													</td>
												</tr>
												<tr>	
													<td colspan="2" class="text14">
														&nbsp;<span title="sviv_sut4">4.Text</span><input type="text" class="inputTextMediumBlue" name="sviv_sut4" id="sviv_sut4" size="35" maxlength="70" value="${model.record.sviv_sut4}">
													</td>
												</tr>
												<tr>
													<td colspan="2" class="text14">
														&nbsp;<span title="sviv_sut5">5.Text</span><input type="text" class="inputTextMediumBlue" name="sviv_sut5" id="sviv_sut5" size="35" maxlength="70" value="${model.record.sviv_sut5}">
														
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
							            
							            <div class="text14" style="position: relative;" align="left" >
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
							            <td class="text14" align="left">&nbsp;&nbsp;<span title="sviv_suko/sutx...">Särskilda upplysningar kod/text:&nbsp;</td>
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
														<tr>
										           			<td class="text14" >
										           				&nbsp;<span title="sviv_suk6">2.Kod</span>
										           				<select class="selectMediumBlueE2" name="sviv_suk6" id="sviv_suk6">
											 						<option value="">-Välj-</option>
												 				  	<c:forEach var="code" items="${model.salCodeList}" >
												 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_suk6 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																	</c:forEach>  
																</select>
										           			</td>
										           			<td class="text14" >
										           				&nbsp;
										           			</td>
														</tr>
														<tr>
										           			<td class="text14">
																&nbsp;<span title="sviv_sut6">1.Text</span><input type="text" class="inputTextMediumBlue" name="sviv_sut6" id="sviv_sut6" size="35" maxlength="70" value="${model.record.sviv_sut6}">
															</td>
										           			<td class="text14">
																&nbsp;<span title="sviv_sut7">2.Text</span><input type="text" class="inputTextMediumBlue" name="sviv_sut7" id="sviv_sut7" size="35" maxlength="70" value="${model.record.sviv_sut7}">
															</td>
														</tr>
										           		<tr>
										           			<td class="text14">
																&nbsp;<span title="sviv_sut8">3.Text</span><input type="text" class="inputTextMediumBlue" name="sviv_sut8" id="sviv_sut8" size="35" maxlength="70" value="${model.record.sviv_sut8}">
															</td>
															<td class="text14">
																&nbsp;<span title="sviv_sut9">4.Text</span><input type="text" class="inputTextMediumBlue" name="sviv_sut9" id="sviv_sut9" size="35" maxlength="70" value="${model.record.sviv_sut9}">
															</td>
														</tr>
														<tr >
										           			<td class="text14">
																&nbsp;<span title="sviv_suta">5.Text</span><input type="text" class="inputTextMediumBlue" name="sviv_suta" id="sviv_suta" size="35" maxlength="70" value="${model.record.sviv_suta}">
															</td>
															<td class="text14" >
										           				&nbsp;
										           			</td>
										           			
														</tr>
														<tr><td class="text14" height="10"/></tr>
														
														<tr>
										           			<td class="text14" >
										           				&nbsp;<span title="sviv_sukb">3.Kod</span>
										           				<select class="selectMediumBlueE2" name="sviv_sukb" id="sviv_sukb">
											 						<option value="">-Välj-</option>
												 				  	<c:forEach var="code" items="${model.salCodeList}" >
												 				  		<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_sukb == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																	</c:forEach>  
																</select>
										           			</td>
										           			<td class="text14" >
										           				&nbsp;
										           			</td>
										           			
														</tr>
														<tr>
										           			<td class="text14">
																&nbsp;<span title="sviv_sutb">1.Text</span><input type="text" class="inputTextMediumBlue" name="sviv_sutb" id="sviv_sutb" size="35" maxlength="70" value="${model.record.sviv_sutb}">
															</td>
										           			<td class="text14">
																&nbsp;<span title="sviv_sutc">2.Text</span><input type="text" class="inputTextMediumBlue" name="sviv_sutc" id="sviv_sutc" size="35" maxlength="70" value="${model.record.sviv_sutc}">
															</td>
														</tr>
										           		<tr>
										           			<td class="text14">
																&nbsp;<span title="sviv_sutd">3.Text</span><input type="text" class="inputTextMediumBlue" name="sviv_sutd" id="sviv_sutd" size="35" maxlength="70" value="${model.record.sviv_sutd}">
															</td>
															<td class="text14">
																&nbsp;<span title="sviv_sute">4.Text</span><input type="text" class="inputTextMediumBlue" name="sviv_sute" id="sviv_sute" size="35" maxlength="70" value="${model.record.sviv_sute}">
															</td>
														</tr>
														<tr>
										           			<td class="text14">
																&nbsp;<span title="sviv_sutf">5.Text</span><input type="text" class="inputTextMediumBlue" name="sviv_sutf" id="sviv_sutf" size="35" maxlength="70" value="${model.record.sviv_sutf}">
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
				 						<b>40.</b><span title="sviv_tik1/sviv_tit1/sviv_tix1...">Tidigare handlingar:</span>
							            
							            <div class="text14" style="position: relative;" align="left" >
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
										           			<td class="text12" colspan="3">
										           				<b>Tidigare handlingar</b>
										           				<a tabindex="-1" id="tidigareHandlingarIdLink">
		            												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
		            											</a>
										           			</td>
										        		</tr>	
								           				<tr>
										           			<td class="text12">&nbsp;Kategori</td>
										           			<td class="text12">&nbsp;Typ</td>
															<td class="text12">&nbsp;Identitet</td>
										           		</tr>
									           			<tr>
										           			<td class="text12" nowrap >&nbsp;1.
										           				<select class="selectMediumBlueE2" name="sviv_tik1" id="sviv_tik1" >
											 						<option value="">-Välj-</option>
															  		<option value="X" <c:if test="${model.record.sviv_tik1 == 'X'}"> selected </c:if> >X</option>
															  		<option value="Y" <c:if test="${model.record.sviv_tik1 == 'Y'}"> selected </c:if> >Y</option>
															  		<option value="Z" <c:if test="${model.record.sviv_tik1 == 'Z'}"> selected </c:if> >Z</option>
															  	</select>										           			
										           			</td>
										           			<td class="text12" nowrap >
																<select class="selectMediumBlueE2" name="sviv_tit1" id="sviv_tit1">
												            		<option value="">-Välj-</option>
												 				  	<c:forEach var="code" items="${model.thoCodeList}" >
								                                	 	<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_tit1 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																	</c:forEach> 
																</select>										           				
															</td>
															<td class="text12" nowrap >
																&nbsp;<input type="text" class="inputTextMediumBlue" name="sviv_tix1" id="sviv_tix1" size="35" maxlength="35" value="${model.record.sviv_tix1}">
															</td>
										           		</tr>
										           		<tr>
										           			<td class="text12" nowrap >&nbsp;2.
										           				<select class="selectMediumBlueE2" name="sviv_tik2" id="sviv_tik2" >
											 						<option value="">-Välj-</option>
															  		<option value="X" <c:if test="${model.record.sviv_tik2 == 'X'}"> selected </c:if> >X</option>
															  		<option value="Y" <c:if test="${model.record.sviv_tik2 == 'Y'}"> selected </c:if> >Y</option>
															  		<option value="Z" <c:if test="${model.record.sviv_tik2 == 'Z'}"> selected </c:if> >Z</option>
															  	</select>	
										           			</td>
										           			<td class="text12" nowrap >
										           				<select class="selectMediumBlueE2" name="sviv_tit2" id="sviv_tit2">
												            		<option value="">-Välj-</option>
												 				  	<c:forEach var="code" items="${model.thoCodeList}" >
								                                	 	<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_tit2 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																	</c:forEach> 
																</select>
															</td>
															<td class="text12" nowrap >
																&nbsp;<input type="text" class="inputTextMediumBlue" name="sviv_tix2" id="sviv_tix2" size="35" maxlength="35" value="${model.record.sviv_tix2}">
															</td>
										           		</tr>
														<tr>
										           			<td class="text12" nowrap >&nbsp;3.
										           				<select class="selectMediumBlueE2" name="sviv_tik3" id="sviv_tik3" >
											 						<option value="">-Välj-</option>
															  		<option value="X" <c:if test="${model.record.sviv_tik3 == 'X'}"> selected </c:if> >X</option>
															  		<option value="Y" <c:if test="${model.record.sviv_tik3 == 'Y'}"> selected </c:if> >Y</option>
															  		<option value="Z" <c:if test="${model.record.sviv_tik3 == 'Z'}"> selected </c:if> >Z</option>
															  	</select>
									           				</td>
										           			<td class="text12" nowrap >
										           				<select class="selectMediumBlueE2" name="sviv_tit3" id="sviv_tit3">
												            		<option value="">-Välj-</option>
												 				  	<c:forEach var="code" items="${model.thoCodeList}" >
								                                	 	<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_tit3 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																	</c:forEach> 
																</select>
															</td>
															<td class="text12" nowrap >
																&nbsp;<input type="text" class="inputTextMediumBlue" name="sviv_tix3" id="sviv_tix3" size="35" maxlength="35" value="${model.record.sviv_tix3}">
															</td>
										           		</tr>
									           			<tr>
										           			<td class="text12" nowrap >&nbsp;4.
										           				<select class="selectMediumBlueE2" name="sviv_tik4" id="sviv_tik4" >
											 						<option value="">-Välj-</option>
															  		<option value="X" <c:if test="${model.record.sviv_tik4 == 'X'}"> selected </c:if> >X</option>
															  		<option value="Y" <c:if test="${model.record.sviv_tik4 == 'Y'}"> selected </c:if> >Y</option>
															  		<option value="Z" <c:if test="${model.record.sviv_tik4 == 'Z'}"> selected </c:if> >Z</option>
															  	</select>
										           				
										           			</td>
										           			<td class="text12" nowrap >
										           				<select class="selectMediumBlueE2" name="sviv_tit4" id="sviv_tit4">
												            		<option value="">-Välj-</option>
												 				  	<c:forEach var="code" items="${model.thoCodeList}" >
								                                	 	<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_tit4 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																	</c:forEach> 
																</select>
																
															</td>
															<td class="text12">
																&nbsp;<input type="text" class="inputTextMediumBlue" name="sviv_tix4" id="sviv_tix4" size="35" maxlength="35" value="${model.record.sviv_tix4}">
															</td>
															
										           		</tr>
									           			<tr>
										           			<td class="text12" nowrap >&nbsp;5.
										           				<select class="selectMediumBlueE2" name="sviv_tik5" id="sviv_tik5" >
											 						<option value="">-Välj-</option>
															  		<option value="X" <c:if test="${model.record.sviv_tik5 == 'X'}"> selected </c:if> >X</option>
															  		<option value="Y" <c:if test="${model.record.sviv_tik5 == 'Y'}"> selected </c:if> >Y</option>
															  		<option value="Z" <c:if test="${model.record.sviv_tik5 == 'Z'}"> selected </c:if> >Z</option>
															  	</select>
										           			</td>
										           			<td class="text12" nowrap >
										           				<select class="selectMediumBlueE2" name="sviv_tit5" id="sviv_tit5">
												            		<option value="">-Välj-</option>
												 				  	<c:forEach var="code" items="${model.thoCodeList}" >
								                                	 	<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_tit5 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																	</c:forEach> 
																</select>
															</td>
															<td class="text12" nowrap >
																&nbsp;<input type="text" class="inputTextMediumBlue" name="sviv_tix5" id="sviv_tix5" size="35" maxlength="35" value="${model.record.sviv_tix5}">
															</td>
										           		</tr>
									           			<tr>
										           			<td class="text12" nowrap >&nbsp;6.
										           				<select class="selectMediumBlueE2" name="sviv_tik6" id="sviv_tik6" >
											 						<option value="">-Välj-</option>
															  		<option value="X" <c:if test="${model.record.sviv_tik6 == 'X'}"> selected </c:if> >X</option>
															  		<option value="Y" <c:if test="${model.record.sviv_tik6 == 'Y'}"> selected </c:if> >Y</option>
															  		<option value="Z" <c:if test="${model.record.sviv_tik6 == 'Z'}"> selected </c:if> >Z</option>
															  	</select>
										           			</td>
										           			<td class="text12" nowrap >
										           				<select class="selectMediumBlueE2" name="sviv_tit6" id="sviv_tit6">
												            		<option value="">-Välj-</option>
												 				  	<c:forEach var="code" items="${model.thoCodeList}" >
								                                	 	<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_tit6 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																	</c:forEach> 
																</select>
															</td>
															<td class="text12" nowrap >
																&nbsp;<input type="text" class="inputTextMediumBlue" name="sviv_tix6" id="sviv_tix6" size="35" maxlength="35" value="${model.record.sviv_tix6}">
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
							            <td class="text14" align="left">&nbsp;&nbsp;&nbsp;
							            <span title="sviv_god2/sviv_kot2/sviv_kos2">Godsmärkning ,antal kolli och kollislag:&nbsp;</span></td>
							           	<td class="text14" align="left"><button name="goodsMarkButton" class="buttonGray" type="button" onClick="showPop('goodsMark');" >Mer...</button> 
								        <span style="position:absolute; left:480px; top:1000px; width:350px; height:320px;" id="goodsMark" class="popupWithInputText"  >
								           		<div class="text10" align="left" valign="top">
								           			<table>
								           				<tr>
										           			<td class="text12">
										           				&nbsp;Godsmärkning [max 42 tecken]:
										           			</td>
										           			<td class="text12">
																&nbsp;Kolli antal:
															</td>
															<td class="text12">
																&nbsp;Kolli slag [kod]
															</td>
														</tr>
									           			<tr>
										           			<td valign="top" class="text12">
										           				&nbsp;2.<textarea rows="2" cols="15" class="inputTextMediumBlueUPPERCASE" name="sviv_god2" id="sviv_god2" maxlength="42">${model.record.sviv_god2}</textarea>
										           			</td>
										           			<td valign="bottom" class="text12">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="sviv_kot2" id="sviv_kot2" size="5" maxlength="5" value="${model.record.sviv_kot2}">
															</td>
															<td align="left" valign="bottom">
										            			<select class="selectMediumBlueE2" name="sviv_kos2" id="sviv_kos2">
												            		<option value="">-Välj-</option>
												 				  	<c:forEach var="code" items="${model.kolliCodeList}" >
								                                	 	<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_kos2 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																	</c:forEach> 
																</select>
									            			</td> 
															
															
										           		</tr>
														<tr>
										           			<td valign="top" class="text12">
										           				&nbsp;3.<textarea rows="2" cols="15" class="inputTextMediumBlueUPPERCASE" name="sviv_god3" id="sviv_god3" maxlength="42">${model.record.sviv_god3}</textarea>
										           			</td>
										           			<td valign="bottom" class="text12">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="sviv_kot3" id="sviv_kot3" size="5" maxlength="5" value="${model.record.sviv_kot3}">
															</td>
															<td align="left" valign="bottom">
										            			<select class="selectMediumBlueE2" name="sviv_kos3" id="sviv_kos3">
												            		<option value="">-Välj-</option>
												 				  	<c:forEach var="code" items="${model.kolliCodeList}" >
								                                	 	<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_kos3 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																	</c:forEach> 
																</select>
									            			</td> 
															
															
										           		</tr>
									           			<tr>
										           			<td valign="top" class="text12">
										           				&nbsp;4.<textarea rows="2" cols="15" class="inputTextMediumBlueUPPERCASE" name="sviv_god4" maxlength="42">${model.record.sviv_god4}</textarea>
										           			</td>
										           			<td valign="bottom" class="text12">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="sviv_kot4" id="sviv_kot4" size="5" maxlength="5" value="${model.record.sviv_kot4}">
															</td>
															<td align="left" valign="bottom">
										            			<select class="selectMediumBlueE2" name="sviv_kos4" id="sviv_kos4">
												            		<option value="">-Välj-</option>
												 				  	<c:forEach var="code" items="${model.kolliCodeList}" >
								                                	 	<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_kos4 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
																	</c:forEach> 
																</select>
									            			</td> 
															
										           		</tr>
									           			<tr>
										           			<td valign="top" class="text12">
										           				&nbsp;5.<textarea rows="2" cols="15" class="inputTextMediumBlueUPPERCASE" name="sviv_god5" id="sviv_god5" maxlength="42">${model.record.sviv_god5}</textarea>
										           			</td>
										           			<td valign="bottom" class="text12">
																&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="sviv_kot5" id="sviv_kot5" size="5" maxlength="5" value="${model.record.sviv_kot5}">
															</td>
															<td align="left" valign="bottom">
										            			<select class="selectMediumBlueE2" name="sviv_kos5" id="sviv_kos5">
												            		<option value="">-Välj-</option>
												 				  	<c:forEach var="code" items="${model.kolliCodeList}" >
								                                	 	<option value="${code.svkd_kd}"<c:if test="${model.record.sviv_kos5 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
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
							            <td class="text14" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
							            <span title="sviv_co01 ...">Container nr:&nbsp;</span></td>
							           	<td class="text14" align="left"><button name="containerNrButton" class="buttonGray" type="button" onClick="showPop('containerNrInfo');" >Mer...</button> 
								           	<span style="position:absolute; left:480px; top:1000px; width:580px; height:250px;" id="containerNrInfo" class="popupWithInputText"  >
								           		<div class="text10" align="left">
								           			<table>
								           			<tr>
									           			<td class="text12" colspan="5">
									           				<b>Container nr</b>
									           			</td>
									        		</tr>
								           			<tr>
									           			<td class="text12">
									           				&nbsp;1.<input type="text" class="inputTextMediumBlue" name="sviv_co01" id="sviv_co01" size="12" maxlength="17" value="${model.record.sviv_co01}">
									           			</td>
														<td class="text12">
															&nbsp;2.<input type="text" class="inputTextMediumBlue" name="sviv_co02" id="sviv_co02" size="12" maxlength="17" value="${model.record.sviv_co02}">
														</td>
														<td class="text12">
															&nbsp;3.<input type="text" class="inputTextMediumBlue" name="sviv_co03" id="sviv_co03" size="12" maxlength="17" value="${model.record.sviv_co03}">
														</td>
														<td class="text12">
									           				&nbsp;4.<input type="text" class="inputTextMediumBlue" name="sviv_co04" id="sviv_co04" size="12" maxlength="17" value="${model.record.sviv_co04}">
									           			</td>
													</tr>
													<tr>
									           			<td class="text12">
									           				&nbsp;5.<input type="text" class="inputTextMediumBlue" name="sviv_co05" id="sviv_co05" size="12" maxlength="17" value="${model.record.sviv_co05}">
									           			</td>
														<td class="text12">
															&nbsp;6.<input type="text" class="inputTextMediumBlue" name="sviv_co06" id="sviv_co06" size="12" maxlength="17" value="${model.record.sviv_co06}">
														</td>
														<td class="text12">
															&nbsp;7.<input type="text" class="inputTextMediumBlue" name="sviv_co07" id="sviv_co07" size="12" maxlength="17" value="${model.record.sviv_co07}">
														</td>
														<td class="text12">
									           				&nbsp;8.<input type="text" class="inputTextMediumBlue" name="sviv_co08" id="sviv_co08" size="12" maxlength="17" value="${model.record.sviv_co08}">
									           			</td>
													</tr>
													<tr>
									           			<td class="text12">
									           				&nbsp;9.<input type="text" class="inputTextMediumBlue" name="sviv_co09" id="sviv_co09" size="12" maxlength="17" value="${model.record.sviv_co09}">
									           			</td>
														<td class="text12">
															10.<input type="text" class="inputTextMediumBlue" name="sviv_co10" id="sviv_co10" size="12" maxlength="17" value="${model.record.sviv_co10}">
														</td>
														<td class="text12">
															11.<input type="text" class="inputTextMediumBlue" name="sviv_co11" id="sviv_co11"  size="12" maxlength="17" value="${model.record.sviv_co11}">
														</td>
														<td class="text12">
									           				12.<input type="text" class="inputTextMediumBlue" name="sviv_co12" id="sviv_co12" size="12" maxlength="17" value="${model.record.sviv_co12}">
									           			</td>
													</tr>
													<tr>
									           			<td class="text12">
									           				13.<input type="text" class="inputTextMediumBlue" name="sviv_co13" id="sviv_co13" size="12" maxlength="17" value="${model.record.sviv_co13}">
									           			</td>
														<td class="text12">
															14.<input type="text" class="inputTextMediumBlue" name="sviv_co14" id="sviv_co14" size="12" maxlength="17" value="${model.record.sviv_co14}">
														</td>
														<td class="text12">
															15.<input type="text" class="inputTextMediumBlue" name="sviv_co15" id="sviv_co15" size="12" maxlength="17" value="${model.record.sviv_co15}">
														</td>
														<td class="text12">
									           				16.<input type="text" class="inputTextMediumBlue" name="sviv_co16" id="sviv_co16" size="12" maxlength="17" value="${model.record.sviv_co16}">
									           			</td>
													</tr>
													<tr>
									           			<td class="text12">
									           				17.<input type="text" class="inputTextMediumBlue" name="sviv_co17" id="sviv_co17" size="12" maxlength="17" value="${model.record.sviv_co17}">
									           			</td>
														<td class="text12">
															18.<input type="text" class="inputTextMediumBlue" name="sviv_co18" id="sviv_co18" size="12" maxlength="17" value="${model.record.sviv_co18}">
														</td>
														<td class="text12">
															19.<input type="text" class="inputTextMediumBlue" name="sviv_co19" id="sviv_co19" size="12" maxlength="17" value="${model.record.sviv_co19}">
														</td>
														<td class="text12">
									           				20.<input type="text" class="inputTextMediumBlue" name="sviv_co20" id="sviv_co20" size="12" maxlength="17" value="${model.record.sviv_co20}">
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
				 						<b>49.</b>
							            <span title="sviv_lagt">Identifiering av lager:&nbsp;</span>
							            
							            <div class="text14" style="position: relative;" align="left" >
							            <span style="position:absolute;top:2px; width:250px;" id="49_info" class="popupWithInputText text12"  >
							           		<div class="text14" align="left">
						           			<br/>
						           			<b>Identifiering av lager</b>
											<br/>
						           			Fältet är obligatoriskt vid ett tullförfarande som avslutar ett tullagerförfarande, det vill säga om koden för förfarande i fält 37:1 
						           			slutar med 71 eller 78(tullförfarande som avslutar lagring i tullager).
						           			<br/><br/>
											I detta fält ska du identifiera tullagret varorna finns på. Koden för att identifiera tullagret består av tre delar.
											<br/><br/>
											Koden skickas i tre delfält
											<ul>
												<li>1. Ange en bokstav som talar om typ av tullager (framgår av tillståndet).</li>
												<li>2. Här anger du godslokalkoden.</li>
												<li>3. Här anger du landkoden för den medlemsstat som utfärdat tillståndet.</li>
											</ul>
											Exempel<br/>
											A XXX SE, där a är typen av tullager, XXX är godslokalkod och SE är för ett tillstånd utfärdat i Sverige.
											</div>
										</span>
							            </div>
							            </td>
							            
							            <td class="text14" align="left" >
							            	<select class="selectMediumBlueE2" name="sviv_lagt" id="sviv_lagt">
				 								<option selected value="">-Välj-</option>
				 								<c:choose>
				 									<%-- only when new record and header value exists --%>
													<c:when test="${recordTopic.svih_godn!='' && empty model.record.sviv_syli}">
														<option value="A" >A-Allmänt tullager , typ A, där lagerhav har ansv för lagret.</option>
					  									<option value="C" selected >C-Privat tullager, typ C, där lagerhav har ansv för lagret.</option>
					  									<option value="D" >D-Privat tulllager, typ D, där lagerhav h ansv för lagret. Taxeringsgr fastställda</option>
					  									<option value="E" >E-Privat tulllager, typ E, där lagerhav har ansv för lagret. Kvalitetsäkrad förvar</option>
					  									<option value="F" >F-Tullager, typ F, drivs av Tullverket. Beslag - hävs/utlämnas från Tullv tulllage</option>
													</c:when>
													<c:otherwise>
														<option value="A" <c:if test="${model.record.sviv_lagt == 'A'}"> selected </c:if> >A-Allmänt tullager , typ A, där lagerhav har ansv för lagret.</option>
					  									<option value="C" <c:if test="${model.record.sviv_lagt == 'C'}"> selected </c:if> >C-Privat tullager, typ C, där lagerhav har ansv för lagret.</option>
					  									<option value="D" <c:if test="${model.record.sviv_lagt == 'D'}"> selected </c:if> >D-Privat tulllager, typ D, där lagerhav h ansv för lagret. Taxeringsgr fastställda</option>
					  									<option value="E" <c:if test="${model.record.sviv_lagt == 'E'}"> selected </c:if> >E-Privat tulllager, typ E, där lagerhav har ansv för lagret. Kvalitetsäkrad förvar</option>
					  									<option value="F" <c:if test="${model.record.sviv_lagt == 'F'}"> selected </c:if> >F-Tullager, typ F, drivs av Tullverket. Beslag - hävs/utlämnas från Tullv tulllage</option>
													</c:otherwise>
								  				</c:choose>
											</select>
										</td>
										<td class="text14" align="left">&nbsp;&nbsp;
											<span title="sviv_lagi">Id:</span>
											<c:choose>
			 									<%-- only when new record and header value exists --%>
												<c:when test="${recordTopic.svih_godn!='' && empty model.record.sviv_syli}">
													<input type="text" class="inputTextMediumBlue" name="sviv_lagi" id="sviv_lagi" size="10" maxlength="14" value='${recordTopic.svih_godn}'>
												</c:when>
												<c:otherwise>
													<input type="text" class="inputTextMediumBlue" name="sviv_lagi" id="sviv_lagi" size="10" maxlength="14" value='${model.record.sviv_lagi}'>
												</c:otherwise>
											</c:choose>
										</td>
							           	<td class="text14" align="left">&nbsp;
							           	<span title="sviv_lagl">Landkod&nbsp;</span>
							            	<select class="selectMediumBlueE2" name="sviv_lagl" id="sviv_lagl">
						 						<option value="">-Välj-</option>
						 						<c:choose>
				 									<%-- only when new record and header value exists --%>
													<c:when test="${recordTopic.svih_godn!='' && empty model.record.sviv_syli}">
									 				  	<c:forEach var="country" items="${model.gcyCodeList}" >
									 				  		<option value="${country.svkd_kd}"<c:if test="${'SE' == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
														</c:forEach>  
													</c:when>
													<c:otherwise>
														<c:forEach var="country" items="${model.gcyCodeList}" >
															<option value="${country.svkd_kd}"<c:if test="${model.record.sviv_lagl == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
														</c:forEach>		
													</c:otherwise>
												</c:choose>
											</select>
											<a tabindex="-1" id="sviv_laglIdLink">
           										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
           									</a>										
										</td>
							        </tr>
							        
								</table>
							</td>
						</tr>
						<tr height="25"><td></td></tr>
	        			
				</table>
            	</td>
           	</tr>
            <tr height="30"><td></td></tr>
		</table>
		</form>
		</td>
		
		</tr>
		
		
		<tr>
		<td>
			<div id="dialogKundensVaruregister" title="Dialog">
				<form  action="tdsimport_edit_items_doUpdateKundensVaruregister.do" name="updateKundensVaruregisterForm" id="updateKundensVaruregisterForm" method="post">
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="sviw_kota" id="sviw_kota" value=""/>
				 	<input type="hidden" name="sviw_kosl" id="sviw_kosl" value=""/>
				 	<input type="hidden" name="sviw_vasl" id="sviw_vasl" value=""/>
				 	<input type="hidden" name="sviw_godm" id="sviw_godm" value=""/>
				 	<input type="hidden" name="sviw_brut" id="sviw_brut" value=""/>
				 	<input type="hidden" name="sviw_neto" id="sviw_neto" value=""/>
				 	<input type="hidden" name="sviw_kono" id="sviw_kono" value=""/>
				 	<input type="hidden" name="sviw_ankv" id="sviw_ankv" value=""/>
				 	<input type="hidden" name="sviw_suko" id="sviw_suko" value=""/>
				 	<input type="hidden" name="sviw_sutx" id="sviw_sutx" value=""/>
				 	<input type="hidden" name="sviw_atin" id="sviw_atin" value=""/>
				 	<input type="hidden" name="sviw_fabl" id="sviw_fabl" value=""/>
				 	<input type="hidden" name="sviw_bit1" id="sviw_bit1" value=""/>
				 	<input type="hidden" name="sviw_bii1" id="sviw_bii1" value=""/>
				 	<input type="hidden" name="sviw_call" id="sviw_call" value=""/>
				 	<%--
				 	<input type="hidden" name="avd_sviw" id="avd_sviw" value=""/>
				 	<input type="hidden" name="opd_sviw" id="opd_sviw" value=""/>
				 	<input type="hidden" name="sign_sviw" id="sign_sviw" value=""/>
				 	 --%>
				 	
					<table>
						<tr>
							<td colspan="6">Beskrivning:&nbsp;<label class="text14Bold" id="description_sviw" name="description_sviw"></label></td>
						</tr>
						<tr height="5"><td></td></tr>
						
						<tr>
							<td class="tableHeaderFieldFirst" align="left" >&nbsp;UL</td>
          					<td class="tableHeaderField" align="left" >&nbsp;Varukod</td>
          					<td class="tableHeaderField" align="left" >&nbsp;Förmån.kod</td>
          					<td class="tableHeaderField" align="left" >&nbsp;Förfarande</td>
          					<td class="tableHeaderField" align="left" >&nbsp;Kundens art.nr</td>
          					<td class="tableHeaderField" align="left" >&nbsp;Kundnr.</td>
          				</tr>
						<tr>
							<td class="tableCellFirst11"><input class="inputTextReadOnly" type="text" id="sviw_ulkd" name="sviw_ulkd" size="10px" value=""></input></td>
							<td class="tableCell11"><input readonly class="inputTextReadOnly" type="text"  id="sviw_vata" name="sviw_vata" size="10px" value=""></input></td>
							<td class="tableCell11"><input readonly class="inputTextReadOnly" type="text"  id="sviw_fokd" name="sviw_fokd" size="10px" value=""></input></td>
							<td class="tableCell11"><input readonly class="inputTextReadOnly" type="text"  id="sviw_eup1" name="sviw_eup1" size="10px" value=""></input></td>
							<td class="tableCell11"><input  class="inputTextMediumBlue" type="text"  id="sviw_knso" name="sviw_knso" size="10px" value=""></input></td>
							<td class="tableCell11"><input  class="inputTextMediumBlue" type="text"  id="sviw_knnr" name="sviw_knnr" size="10px" value=""></input></td>
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

