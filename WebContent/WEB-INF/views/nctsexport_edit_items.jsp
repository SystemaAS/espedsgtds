<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTds.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/nctsexport_edit_items.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	
	<%-- tab container component --%>
	<table width="100%"  class="text14" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkMainList" tabindex=-1 style="display:block;" href="nctsexport.do?action=doFind&sign=${model.sign}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.ncts.export.list.tab"/></font>
					<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkHeader" tabindex=-1 style="display:block;" href="nctsexport_edit.do?action=doFetch&avd=${model.avd}&opd=${model.opd}
						&sysg=${model.sign}&tuid=${model.tullId}&syst=${model.status}&sydt=${model.datum}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.ncts.export.created.mastertopic.tab"/></font>
					<font class="text14MediumBlue">[${model.opd}]</font>
					<c:if test="${ model.status == 'G' ||  model.status=='F' || model.status == 'M' || empty model.status}">
						<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
					</c:if>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;<spring:message code="systema.ncts.export.item.createnew.tab"/></font>
				<c:if test="${model.status == 'G' ||  model.status=='F' || model.status == 'M' || empty model.status}">
					<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
				</c:if>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkLogging" tabindex=-1 style="display:block;" href="nctsexport_logging.do?avd=${model.avd}&sign=${model.sign}&opd=${model.opd}&tullId=${model.tullId}
											&mrnNr=${model.mrnNr}&status=${model.status}&datum=${model.datum}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.ncts.export.logging.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkArchive" tabindex=-1 style="display:block;" href="nctsexport_archive.do?avd=${model.avd}&sign=${model.sign}&opd=${model.opd}&tullId=${model.tullId}
											&mrnNr=${model.mrnNr}&status=${model.status}&datum=${model.datum}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.ncts.export.archive.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
				</a>
			</td>
			<td width="20%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			
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
				 				&nbsp;Ärende&nbsp;<b>${model.opd}</b>
				 				&nbsp;Sign&nbsp;<b>${model.sign}</b>
				 				&nbsp;&nbsp;&nbsp;Lrn-nr.:&nbsp;<b>${model.tullId}</b>
				 				&nbsp;&nbsp;&nbsp;Status:&nbsp;<b>${model.status}</b>
				 				&nbsp;&nbsp;&nbsp;Mrn-nr.:&nbsp;<b>${model.mrnNr}</b>
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
							            <td width="30%" class="text12Bold" align="left" >Avsändare&nbsp;</td>
							            <td class="text12" align="left" >${recordTopic.thkns}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text12" align="left">TIN-nr&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopic.thtins}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text12" align="left">Namn&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopic.thnas}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text12" align="left">Adress&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopic.thads1}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text12" align="left">Postadress&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopic.thpns}&nbsp;${recordTopic.thpss}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text12" align="left">Landkod
							            </td>
							           	<td class="text12MediumBlue" align="left">${recordTopic.thlks}</td>
							        </tr>
							        
			        	        </table>
					        </td>
					        <td width="50%">
						 		<table width="80%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text12Bold" align="left" >Mottagare&nbsp;</td>
							            <td class="text12" align="left" >${recordTopic.thknk}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text12" align="left">TIN-nr&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopic.thtink}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text12" align="left">Namn&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopic.thnak}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text12" align="left">Adress&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopic.thadk1}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text12" align="left">Postadress&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopic.thpnk}&nbsp;${recordTopic.thpsk}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text12" align="left">Landkod
							            </td>
							           	<td class="text12MediumBlue" align="left">${recordTopic.thlkk}</td>
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
							<form id="createNewItemLine" action="nctsexport_edit_items.do">
								<input type="hidden" name="action" id="action" value='doFetch'>
				 				<input type="hidden" name="avd" id="avd" value='${model.avd	}'>
				 				<input type="hidden" name="sign" id="sign" value='${model.sign}'>
								<input type="hidden" name="opd" id="opd" value='${model.opd}'>
				 				<input type="hidden" name="tullId" id="tullId" value='${model.tullId}'>
				 				<input type="hidden" name="mrnNr" id="mrnNr" value='${model.mrnNr}'>
				 				<input type="hidden" name="status" id="status" value='${model.status}'>
				 				<input type="hidden" name="datum" id="datum" value='${model.datum}'>
				 					
				 			<table width="100%" cellspacing="0" border="0" cellpadding="0">
				 			<tr>		
							<td class="text14Bold">&nbsp;Befintliga varuposter&nbsp;&nbsp;
								<c:if test="${model.status == 'G' ||  model.status=='F' || model.status == 'M' || empty model.status}">
									<input tabindex=-1 class="inputFormSubmitStd" type="submit" name="submit" value='Skapa ny'>
								</c:if>
								<button name="allItemsButton" class="inputFormSubmitStd" type="button" onClick="showPop('allItems');" >Visa alla</button> 
										        <span style="background-color:#EEEEEE; position:absolute; left:50px; top:200px; width:1200px; height:1000px;" id="allItems" class="popupWithInputTextThickBorder"  >
									           			<table id="containerdatatableTable" width="98%" align="left" >
									           			<tr>	
															<td class="text14">
															<table id="tblItemLinesAll" class="display compact cell-border">
																<thead>
																<tr class="tableHeaderField" >	
																    <th class="text14">&nbsp;<spring:message code="systema.ncts.export.item.list.label.tvli.linjeNr"/>&nbsp;</th>   
												                    <th class="text14">&nbsp;<spring:message code="systema.ncts.export.item.list.label.tvvnt.varukod"/>&nbsp;</th>   
												                    <th class="text14">&nbsp;<spring:message code="systema.ncts.export.item.list.label.tvvt.varuBeskrivning"/>&nbsp;</th>
												                    <th class="text14">&nbsp;<spring:message code="systema.ncts.export.item.list.label.tvdk.deklTyp"/>&nbsp;</th>
												                    <th class="text14">&nbsp;<spring:message code="systema.ncts.export.item.list.label.tvnas.avsNamn"/>&nbsp;</th>
												                    <th class="text14">&nbsp;<spring:message code="systema.ncts.export.item.list.label.tvnak.motNamn"/>&nbsp;</th>
												                    <th class="text14">&nbsp;<spring:message code="systema.ncts.export.item.list.label.tvdty.dokTyp"/>&nbsp;</th>
												                    <th class="text14">&nbsp;<spring:message code="systema.ncts.export.item.list.label.tvvktb.bruttoVikt"/>&nbsp;</th>
												                    <th class="text14">&nbsp;<spring:message code="systema.ncts.export.item.list.label.tvvktn.nettoVikt"/>&nbsp;</th>
												                    <th class="text14">&nbsp;<spring:message code="systema.ncts.export.item.list.label.sum_of_tvnt.kolliAnt"/>(&Sigma;) / Stk</th>
												                    <c:if test="${model.status == 'G' ||  model.status=='F' || model.status == 'M' || empty model.status}">
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
				               							               <td width="2%" class="text14" align="center">&nbsp;${record.tvli}</td>
														               <td width="2%" class="text14" >&nbsp;${record.tvvnt}</td>
														               <td class="text14" >&nbsp;${record.tvvt}</td>
														               <td width="2%" class="text14" >&nbsp;${record.tvdk}</td>
														               <td class="text14" >&nbsp;${record.tvnas}</td>
														               <td class="text14" >&nbsp;${record.tvnak}</td>
														               <td width="2%" class="text14" >&nbsp;${record.tvdty}</td>
														               <td width="2%" class="text14" align="right" >&nbsp;${record.tvvktb}&nbsp;</td>
														               <td width="2%" class="text14" align="right" >&nbsp;${record.tvvktn}&nbsp;</td>
														               <td width="2%" class="text14" >&nbsp;${record.sum_of_tvnt}</td>
														               
														               <c:if test="${model.status == 'G' ||  model.status=='F' || model.status == 'M' || empty model.status}">	
															               <td width="2%" class="text14" align="center" nowrap>
															               	<a onclick="javascript:return confirm('Är du säker på att du vill ta bort raden?')" tabindex=-1 href="nctsexport_edit_items.do?action=doDelete&avd=${record.tvavd}&opd=${record.tvtdn}&lin=${record.tvli}">
															               		<img src="resources/images/delete.gif" border="0" alt="remove">
															               	</a>	
															               </td>
														               </c:if>	
														               </tr>
														            <%-- this param is used ONLY in this JSP --%>   
															        <c:set var="totalNumberOfItemLines" value="${counter.count}" scope="request" />
															        <%-- this param is used throughout the Controller --%>
															        <c:set var="numberOfItemLinesInTopic" value="${record.tvli}" scope="request" />
														        </c:forEach>
														        </tbody>
													        </table>
															</td>											           		
												         </tr>
												         </table>
											         	
											         	<div>
								           				<table >
															<%-- OK BUTTON --%>
									           				<tr align="left" >
																<td class="text14"><button name="allItemsButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('allItems');">&nbsp;Ok</button></td>
																<td class="text14">&nbsp;&nbsp;&nbsp;
													 	        		<a href="tdsNctsExportItemListExcelView.do" target="_new">
												                 		<img valign="bottom" id="itemListExcel" src="resources/images/excel.png" border="0" alt="excel">&nbsp;Excel
													 	        		</a>
													 	        		&nbsp;
														 		</td>
															</tr>
														</table>
										   				</div>
								   				</span>	
								   		<c:if test="${model.status == 'G' ||  model.status=='F' || model.status == 'M' || empty model.status}">
								   			&nbsp;<button title="Import av varuposter" name="itemLinesImportButton" id="itemLinesImportButton" class="buttonGrayWithGreenFrame" type="button" >Importera varuposter</button>	
								   		</c:if>			
									</td>
									<td width="20%" class="text14">&nbsp;</td>
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
		 						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
		 						 
								<table width="100%" id="containerdatatableTable" cellspacing="2" align="left" >
								<tr>
								<td class="text14">
							
								<table id="tblItemLines" class="display compact cell-border">
									<thead>
									<tr class="tableHeaderField" >
									    <th class="text14">&nbsp;<spring:message code="systema.ncts.export.item.list.label.tvli.linjeNr"/>&nbsp;</th>   
					                    <th class="text14">&nbsp;Uppdat.&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.ncts.export.item.list.label.tvvnt.varukod"/>&nbsp;</th>   
					                    <th class="text14">&nbsp;<spring:message code="systema.ncts.export.item.list.label.tvvt.varuBeskrivning"/>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.ncts.export.item.list.label.tvdk.deklTyp"/>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.ncts.export.item.list.label.tvnas.avsNamn"/>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.ncts.export.item.list.label.tvnak.motNamn"/>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.ncts.export.item.list.label.tvdty.dokTyp"/>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.ncts.export.item.list.label.tvvktb.bruttoVikt"/>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.ncts.export.item.list.label.tvvktn.nettoVikt"/>&nbsp;</th>
					                    <th class="text14">&nbsp;<spring:message code="systema.ncts.export.item.list.label.sum_of_tvnt.kolliAnt"/>(&Sigma;)</th>
					                    <c:if test="${model.status == 'G' ||  model.status=='F' || model.status == 'M' || empty model.status}">
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
							               <td width="2%" class="text14" align="center">${record.tvli}</td>
							               <td width="2%" class="text14" align="center">&nbsp;
							               		<a tabindex=-1 id="recordUpdate_${counter.count}_${record.tvli}" href="#" onClick="getItemData(this);">
							               			<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;
							               		</a>
							               </td>
							               <td width="2%" class="text14" >&nbsp;${record.tvvnt}</td>
							               <td class="text14">&nbsp;${record.tvvt}</td>
							               <td width="2%" class="text14" >&nbsp;${record.tvdk}</td>
							               <td class="text14" >&nbsp;${record.tvnas}</td>
							               <td class="text14" >&nbsp;${record.tvnak}</td>
							               <td width="2%" class="text14" >&nbsp;${record.tvdty}</td>
							               <td width="2%" class="text14" align="right" >&nbsp;${record.tvvktb}&nbsp;</td>
							               <td width="2%" class="text14" align="right" >&nbsp;${record.tvvktn}&nbsp;</td>
							               <td width="2%" class="text14" >&nbsp;${record.sum_of_tvnt}</td>
							               
							               	<c:if test="${model.status == 'G' ||  model.status=='F' || model.status == 'M' || empty model.status}">	
								               <td width="2%" class="text14" align="center" nowrap>
								               	<a onclick="javascript:return confirm('Är du säker på att du vill ta bort raden?')" tabindex=-1 href="nctsexport_edit_items.do?action=doDelete&avd=${record.tvavd}&opd=${record.tvtdn}&lin=${record.tvli}">
								               		<img src="resources/images/delete.gif" border="0" alt="remove">
								               	</a>	
								               </td>
							               </c:if>
							                
								           </tr>
								        <%-- <c:set var="numberOfItemLinesInTopic" value="${counter.count}" scope="request" /> --%>
								        <c:set var="numberOfItemLinesInTopic" value="${record.tvli}" scope="request" />
								         
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
	 			<form name="nctsExportEditTopicItemForm" id="nctsExportEditTopicItemForm" action="nctsexport_edit_items.do" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="opd" id="opd" value='${model.opd}'/>
				 	<input type="hidden" name="avd" id="avd" value='${model.avd}'/>
				 	<input type="hidden" name="sign" id="sign" value='${model.sign}'/>
				 	<input type="hidden" name="tullId" id="tullId" value='${model.tullId}'/>
				 	<input type="hidden" name="status" id="status" value='${model.status}'/>
				 	<input type="hidden" name="datum" id="datum" value='${model.datum}'/>
				 	<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
				 	<input type="hidden" name="numberOfItemLinesInTopic" id="numberOfItemLinesInTopic" value='${numberOfItemLinesInTopic}' />
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="100%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text14White">
				 				<b>&nbsp;&nbsp;Varupost&nbsp;</b>
				 				<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 				<input tabindex=-1 align="center" class="text14BoldLightGreenForItemLinenr" readonly type="text" name="lineNr" id="lineNr" size="3" value=''>
								
			 				</td>
		 				</tr>
	 				</table>
					<table width="100%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15"><td class="text" align="left"></td></tr>
				 		<tr>
					 		<td>
						 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
							 		<tr>
							 			<td class="text14" align="left">
							 				&nbsp;<span title="tvli" >Linjenr.</span>
							 			</td>
							 			<td class="text14" align="left">
							            <img id="imgTaricVarukodSearch" onMouseOver="showPop('32_info');" onMouseOut="hidePop('32_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>32.</b><span title="tvvnt" >Varukod</span>
							            
							          	<div class="text14" style="position: relative;" align="left">
							            <span style="position:absolute;top:2px; width:250px;" id="32_info" class="popupWithInputText text14"  >
						           			<b>Varukod</b>
						           			<br/>
						           			Enter the number of the item in question in relation to the total number of articles
											declared in the forms and SAD-BIS forms used<br/><br/>
						           			<br/>
										</span>
										</div>
										</td>
							 			<td class="text14" >
							 				<img onMouseOver="showPop('deklTyp_info');" onMouseOut="hidePop('deklTyp_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 					<b>1.</b>&nbsp;<span title="tvdk" >Dekl.&nbsp;</span>
						 					<div class="text14" style="position: relative;" align="left">
						 					<span style="position:absolute;top:2px; width:250px;" id="deklTyp_info" class="popupWithInputText text14"  >
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
							 			<td class="text14" align="left">
							 			<img onMouseOver="showPop('15_info');" onMouseOut="hidePop('15_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>15.</b>&nbsp;<span title="tvalk" >Avg.land</span>
						            		
							 			<div class="text14" style="position: relative;" align="left">
						 				<span style="position:absolute;top:2px; width:250px;"id="15_info" class="popupWithInputText text14"  >
						           			<b>Important</b><br/>
						           			This field should be entered if the sibling fields on header (15 and 17) are empty
						           			<br/>
											As a general rule, when item: 1.Deklaration type (in header) = <b>T-</b> there will be a need for filling these fields at an item line level
						           			<br/><br/>
											This is by defintion since T- is a combination of T1 and T2 meaning that each item line belongs either to one or the other						           			
										</span>
										</div>
										</td>
							            
							            
							            
							            <td class="text14" align="left">
							            <img onMouseOver="showPop('17_info');" onMouseOut="hidePop('17_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>17.</b>&nbsp;<span title="tvblk" >Best.land</span>
						            		
							            <div class="text14" style="position: relative;" align="left">
						 				<span style="position:absolute;top:2px; width:250px;"id="17_info" class="popupWithInputText text14"  >
						           			<b>Important</b><br/>
						           			This field should be entered if the sibling fields on header (15 and 17) are empty
						           			<br/>
											As a general rule, when item: 1.Deklaration type (in header) = <b>T-</b> there will be a need for filling these fields at an item line level
						           			<br/><br/>
											This is by defintion since T- is a combination of T1 and T2 meaning that each item line belongs either to one or the other						           			
										</span>
										</div>
										</td>
										
							            <td class="text14" align="left">
							            <img onMouseOver="showPop('35_info');" onMouseOut="hidePop('35_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>35.</b><span title="tvvktb" >Brut.vikt(kg)</span>
							            <div class="text14" style="position: relative;" align="left">
							            <span style="position:absolute;top:2px; width:250px;" id="35_info" class="popupWithInputText text14"  >
						           			<b>Bruttovikt</b><br/>
						           			Enter the gross mass, in kilograms, of the goods described in the corresponding box 31.The ‘gross mass’ means the total weight of the goods including all packing but excluding containers and other transport equipment.
											<br/>
											As a general rule, where the gross mass exceeds 1 kilogram and includes a fraction of a unit (kilogram), the figure entered in this box may be rounded as follows:
											<ul>
							           			<li>Fraction from 0.001 to 0.499: rounded down to the nearest kilogram</li>
							           			<li>Fraction from 0.500 to 0.999: rounded up to the nearest kilogram.</li>
							           		</ul>
										</span>
										</div>
										</td>
										<td class="text14" align="left"><b>&nbsp;38.</b>&nbsp;<span title="tvvktn" >Net.vikt(kg)</span></td>
										<td class="text14" align="left"><b>&nbsp;44.</b>&nbsp;<span title="tvdty" >Dok.Typ</span>&nbsp;&nbsp;</td>
										<td class="text14" align="left"><b>&nbsp;44.</b>&nbsp;<span title="tvdref" >Dok.Ref</span>&nbsp;&nbsp;</td>
										
							        </tr>
							        <tr>
							        		<td class="text14" align="left">
							        			&nbsp;<input readonly type="text" class="inputText" name="tvli" id="tvli" size="3" maxlength="5" value="${model.record.tvli}">
			 			            		</td>
							            
							        		<td class="text14" align="left">
							        			<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="tvvnt" id="tvvnt" size="12" maxlength="6" value="${model.record.tvvnt}">
							        			<a tabindex="-1" id="tvvntIdLink" >
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
												</a>
			 			            		</td>
							            
							            <td>&nbsp;
							 				<select class="selectMediumBlueE2" name="tvdk" id="tvdk">
							            		<option value="">-välj-</option>
							 				  	<c:forEach var="code" items="${model.ncts031_DeklType_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvdk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
											</select>											
						 				</td>
										<td align="left">&nbsp;
					            			<select class="selectMediumBlueE2" name="tvalk" id="tvalk">
							            		<option value="">-välj-</option>
						 				  	<c:forEach var="country" items="${model.countryCodeList}" >
		                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.tvalk == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
											</c:forEach> 
											</select>
				            				</td> 
										
										<td align="left">&nbsp;
					            			<select class="selectMediumBlueE2" name="tvblk" id="tvblk">
							            		<option value="">-välj-</option>
						 				  	<c:forEach var="country" items="${model.countryCodeList}" >
		                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.tvblk == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
											</c:forEach> 
											</select>
					            			</td> 
										
						 				<td class="text14" align="left"><input onKeyPress="return amountKey(event)" type="text" class="inputText" name="tvvktb" id="tvvktb" size="10" maxlength="11" value="${model.record.tvvktb}"></td>
										<td class="text14" align="left"><input onKeyPress="return amountKey(event)" type="text" class="inputText" name="tvvktn" id="tvvktn" size="10" maxlength="11" value="${model.record.tvvktn}"></td>
										<%-- Doc.Type --%>
										<td >
											<select class="selectMediumBlueE2" name="tvdty" id="tvdty">
							            		<option value="">-välj-</option>
							 				  	<c:forEach var="code" items="${model.ncts013_DocType_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvdty == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
											</select>
											<a tabindex="-1" id="tvdtyIdLink" OnClick="triggerChildWindowDocTypeCodes(this)">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>										           				
										</td>
										<td class="text14" align="left"><input type="text" class="inputText" name="tvdref" id="tvdref" size="10" maxlength="20" value="${model.record.tvdref}"></td>
										
							        </tr>
							        <tr height="10"><td class="text" align="left" colspan="9"><hr></td></tr>
							        
									<tr>
										<td class="text14" align="left"><b>&nbsp;44.</b>&nbsp;<span title="tvdsk" >Dok.språk</span>
										</td>
										
										<td class="text14" align="left">
										<img onMouseOver="showPop('44_main_info');" onMouseOut="hidePop('44_main_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">										
										<b>44.</b>&nbsp;Tilläggsuppl.
										<div class="text14" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="44_main_info" class="popupWithInputText text14"  >
							           		<b>Tilläggsupplysningar</b>
						           			<br/>
											Sektionen är indelad i en <b>huvud Rubrik 44</b> (som är denna) och en <b>sekundär Rubrik 44</b>, som du hittar under Extraordinära Uppgifter.
											<br/><br/>
						           			I den här huvudsektionen registrerar du huvuduppgifterna för Rubrik 44. 
						           			<br/><br/>
						           			Den sekundära sektionen under Extraordinära uppgifter använder du när du behöver registrera
						           			flera förekomster av Rubrik 44
										</span>										
										</div>
										</td>
										<td class="text14" nowrap>
										<img onMouseOver="showPop('31_kslag_info');" onMouseOut="hidePop('31_kslag_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>31.</b><font class="text16RedBold" >*</font><span title="tveh" >Kollislag</span>
				 						<div class="text14" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="31_kslag_info" class="popupWithInputText text14"  >
						           			<b>Kollislag [Enhet]</b>
											<br/>
						           			The correct kind of packages shall be entered.
										</span>
										</div>
										</td>
										
										<td class="text14">
										<img onMouseOver="showPop('31_kantal_info');" onMouseOut="hidePop('31_kantal_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>31.</b><span title="tvnt" >Kolli antal</span>
				 						<div class="text14" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="31_kantal_info" class="popupWithInputText text14"  >
							           		<b>Kolliantal</b>
						           			<br/>
						           			Antal kolli/delar 
											<br/>
						           			Ange antal kolli för emballerade varor och antal delar för oemballerade varor. Antal delar för oemballerat gods kan aldrig vara noll. Om kollislag avser bulkvaror anges inget kollital. 
										</span>
							            </div>
							            </td>
										
							            <td class="text14" >
										<img onMouseOver="showPop('31_styck_enhet_info');" onMouseOut="hidePop('31_styck_enhet_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>31.</b><span title="tvnteh" >Styck</span>
										<div class="text14" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="31_styck_enhet_info" class="popupWithInputText text14"  >
							           		<b>Styck</b>
											<br/>
						           			Här anger du antal styck ifall kolliantal inte kan användas, exempelvis bulkvaror som gas, vätskor, etc.
						           			<br/>
						           			Detta fält är mandatory när kollislag = <b>VQ, VG, VL, VY, VR, VO</b><br/><br/>
						           			Kollislag: <b>NE</b> är oemballerad gods och måste alltid ha del (styck) > 0. 
										</span>
										</div>
										</td>
							            
										<td class="text14" align="left" colspan="2">
										<img onMouseOver="showPop('31_varubesk_info');" onMouseOut="hidePop('31_varubesk_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>31.</b>
										<font class="text16RedBold" >*</font><span title="tvvt / tvvt2, tvvt3, etc" >Varubeskrivning&nbsp;</span>
										<div class="text14" style="position: relative;" align="left">
										<span style="position:absolute;top:2px; width:250px;" id="31_varubesk_info" class="popupWithInputText text14"  >
							           		<b>Varubeskrivning</b>
											<br/>
						           			Här anger du varubeskrivning, det vill säga den normala handelsbenämningen för varan. Denna beskrivning ska vara så noggrann att varan kan klassificeras.
						           			
										</span>
							            </div>
							            </td>
										<td class="text14" align="left" colspan="2">
						            		<b>31.</b>&nbsp;<span title="tvvtsk" >Varubesk.språkkod</span>
					            		</td>
							        </tr>
							        
									<tr>
										<td >
											<select class="selectMediumBlueE2" name="tvdsk" id="tvdsk">
							            			<option value="">-välj-</option>
						 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvdsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach>
											</select>
							            	<a tabindex="-1" id="tvdskIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>
																					           				
										</td>
										
										<td class="text14" align="left"><button name="44_aditionalInfo_01" class="buttonGray" type="button" onClick="showPop('44_aditionalInfo_01');" >Lägg till</button> 
								        <div class="text12" style="position: relative;" align="left">
								        <span style="position:absolute; top:2px; width:650px; height:260px;" id="44_aditionalInfo_01" class="popupWithInputText"  >
						           			<table border="0" cellspacing="1" cellpadding="0">
						           			<tr>
						           			<td>
						           				<table class="lightGrayBg" >
						           				<tr>
								           			<td class="text14" colspan="2">
								           				<b>44.Tilläggsupplysningar (1)</b>
								           			</td>
								        			</tr>
												<tr>
								           			<td style="font-style:italic;" class="text11Gray">&nbsp;<span title="tvdty_readonly" >Dok.typ</span></td>
								           			<td style="font-style:italic;" class="text11Gray">&nbsp;<span title="tvdref_readonly" >Dok.ref.</span></td>
													<td style="font-style:italic;" class="text11Gray">&nbsp;<span title="tvdsk_readonly" >Dok.språkkod</span></td>
													<td class="text14">&nbsp;<span title="tvdo" >Upplysningar</span></td>
													<td class="text14">&nbsp;<span title="tvdosk" >Upplysn.språkkod</span></td>
												</tr>
							           			<tr>
								           			
								           			<td class="text14" align="left"><input readonly type="text" class="inputTextReadOnly" name="tvdty_readonly" id="tvdty_readonly" size="10" maxlength="20" value="${model.record.tvdty}"></td>										           			
								           			<td class="text14" align="left"><input readonly type="text" class="inputTextReadOnly" name="tvdref_readonly" id="tvdref_readonly" size="10" maxlength="20" value="${model.record.tvdref}"></td>										           			
								           			<td class="text14" align="left"><input readonly type="text" class="inputTextReadOnly" name="tvdsk_readonly" id="tvdsk_readonly" size="10" maxlength="20" value="${model.record.tvdsk}"></td>										           			
								           			
								           			<td class="text14" nowrap >
								           				&nbsp;<input type="text" class="inputText" name="tvdo" id="tvdo" size="20" maxlength="26" value='${model.record.tvdo}'>										           			
								           			</td>
					            			   			<td class="text14" nowrap >&nbsp;
									            			<select class="selectMediumBlueE2" name="tvdosk" id="tvdosk">
											            		<option value="">-välj-</option>
										 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
						                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvdosk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
															</c:forEach> 
														</select>
										            		
								           			</td>
								           			
			           							</tr>
			           							<tr>
								           			<td class="text14">&nbsp;<span title="tvtlo" >Kod [Tilläggsinformation]</span></td>
								           			<td class="text14">&nbsp;</td>
								           			<td class="text14">&nbsp;<span title="tvexkd" >Export från EF</span></td>
													<td class="text14">&nbsp;<span title="tvexlk" >Export från land</span></td>
													
												</tr>
							           			<tr>
								           			<td class="text14" colspan="2" >
														<select class="selectMediumBlueE2" name="tvtlo" id="tvtlo">
										 				  <option selected value="">-välj-</option>
														  <option value="CAL"<c:if test="${model.record.tvtlo == 'CAL'}"> selected </c:if> >CAL [Skuldbelopp]</option>
														  <option value="DG0"<c:if test="${model.record.tvtlo == 'DG0'}"> selected </c:if> >DG0 [Export med restriktioner]</option>
														  <option value="DG1"<c:if test="${model.record.tvtlo == 'DG1'}"> selected </c:if> >DG1 [Export med avgift]</option>
														  <option value="DG2"<c:if test="${model.record.tvtlo == 'DG2'}"> selected </c:if> >DG2 [Export]</option>
													  	</select>	
								           			</td>
								           			<td class="text14" >&nbsp;
														<select class="selectMediumBlueE2" name="tvexkd" id="tvexkd">
										 				  <option value=""<c:if test="${model.record.tvexkd == '0' || model.record.tvexkd == ''}"> selected </c:if> >0</option>
														  <option value="1"<c:if test="${model.record.tvexkd == '1'}"> selected </c:if> >1</option>
													  	</select>	
								           			</td>
								           			
					            			   			<td class="text14" nowrap >&nbsp;
									            			<select class="selectMediumBlueE2" name="tvexlk" id="tvexlk">
									 						<option value="">-välj-</option>
										 				  	<c:forEach var="country" items="${model.countryCodeList}" >
						                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.tvexlk == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
															</c:forEach> 
														</select>
										            		
								           			</td>
								           			
			           							</tr>
			           							<tr>
								           			<td class="text14" colspan="3">&nbsp;<span title="tvmtxt" >Text</span></td>
													<td class="text14">&nbsp;<span title="tvmsk" >Textspråk</span></td>
													
												</tr>
							           			<tr>
								           			<td class="text14" colspan="3" >
								           				&nbsp;<input type="text" class="inputText" name="tvmtxt" id="tvmtxt" size="35" maxlength="70" value="${model.record.tvmtxt}">										           			
								           			</td>
								           			<td class="text14" nowrap >&nbsp;
									            			<select class="selectMediumBlueE2" name="tvmsk" id="tvmsk">
											            		<option value="">-välj-</option>
										 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
						                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvmsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
															</c:forEach> 
														</select>
										            		
								           			</td>
			           							</tr>					           															           			
			           							</table>
			           						</td>
			           						</tr>
			           						
			           						<tr height="4"><td class="text" align="left"></td></tr>
			           						</table>
			           						
			           						
											<table width="100%" align="left" border="0">
												<tr align="left" >
													<td class="text14"><button name="44_aditionalInfo_01ButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('44_aditionalInfo_01');">&nbsp;Ok</button> 
													</td>
												</tr>
											</table>
										</span>
										</div>
										</td>
										
					           			<td align="left">
					            			<select class="inputTextMediumBlueMandatoryField" name="tveh" id="tveh">
							            		<option value="">-välj-</option>
							 				  	<c:forEach var="code" items="${model.ncts017_Kolli_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tveh == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
											</select>
											<a tabindex="-1" id="tvehIdLink" OnClick="triggerChildWindowKolliSlagCodes(this)">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>											
				            				</td> 
				            				<td class="text14" valign="bottom">
											&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="tvnt" id="tvnt" size="5" maxlength="5" value="${model.record.tvnt}">
										</td>
										<td class="text14" valign="bottom">
											&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="tvnteh" id="tvnteh" size="5" maxlength="5" value="${model.record.tvnteh}">
										</td>
																				
										<td class="text14" align="left" colspan="2">
											<input type="text" class="inputTextMediumBlueMandatoryField" name="tvvt" id="tvvt" size="25" maxlength="70" value="${model.record.tvvt}">
											<button name="itemDescriptionExtraInformationButton" class="buttonGray" type="button" onClick="showPop('itemDescriptionExtraInformation');" >Lägg till</button>
											<div class="text12" style="position: relative;" align="left">
									        <span style="position:absolute; top:2px; width:350px; height:300px;" id="itemDescriptionExtraInformation" class="popupWithInputText"  >
							           			<table>
							           				<tr>
									           			<td class="text14">
															<b>Flera varubeskrivningar</b>
														</td>
													</tr>
													
								           			<tr>
									           			<td class="text14">
															&nbsp;31.2 Varubeskrivning<input type="text" class="inputText" name="tvvt2" id="tvvt2" size="35" maxlength="70" value="${tvvt2}">
														</td>
													</tr>
													<tr>
									           			<td class="text14">
															&nbsp;31.3 Varubeskrivning<input type="text" class="inputText" name="tvvt3" id="tvvt3" size="35" maxlength="70" value="${tvvt3}">
														</td>
													</tr>
													<tr>
									           			<td class="text14">
															&nbsp;31.4 Varubeskrivning<input type="text" class="inputText" name="tvvt4" id="tvvt4" size="35" maxlength="70" value="${tvvt4}">
														</td>
													</tr>
													<tr>
									           			<td class="text14">
															&nbsp;31.5 Varubeskrivning<input type="text" class="inputText" name="tvvt5" id="tvvt5" size="35" maxlength="70" value="${tvvt5}">
														</td>
													</tr>
													
							           			</table>
												<table width="100%" align="left" border="0">
													<tr align="left" >
														<td class="text14"><button name="itemDescriptionExtraInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('itemDescriptionExtraInformation');">&nbsp;Ok</button> 
														</td>
													</tr>
												</table>
											</span>
											</div>
										</td>
										
										<td class="text14" align="left" colspan="2">
											<select class="selectMediumBlueE2" name="tvvtsk" id="tvvtsk">
								            		<option value="">-välj-</option>
								 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
				                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvvtsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
													</c:forEach>
											</select>
						            		<a tabindex="-1" id="tvvtskIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>
										</td>
							            
 							        </tr>
 							        
 							        <tr height="10"><td class="text" align="left" colspan="9"><hr></td></tr>
									<tr>
										<td class="text14" align="left">
							            <img onMouseOver="showPop('31_gods_info');" onMouseOut="hidePop('31_gods_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>31.</b><span title="tvmn" >Godsmärkning&nbsp;</span>
				 						<div class="text14" style="position: relative;" align="left">
							            <span style="position:absolute;top:2px; width:250px;" id="31_gods_info" class="popupWithInputText text14"  >
							           		<b>Godsmärkning</b>
											<br/>
						           			Här anger du hur de kollin som varorna är förpackade i är märkta, obligatoriskt för emballerat gods. Fältet är frivilligt för bulkvaror och oemballerat gods.
						           		</span>
							            </div>
							            </td>
							            
										<td class="text14" align="left" >
										<b>31.</b><span title="tvmnsk" >Godsmärk.språk&nbsp;</span></td>
										<td>&nbsp;</td>
										
							        </tr>
							        <tr>
						        		<td valign="top" class="text14">
											&nbsp;<input type="text" class="inputText" name="tvmn" id="tvmn" size="17" maxlength="17" value='${model.record.tvmn}'>
										</td>
										<td valign="top" class="text14" >
											<select class="selectMediumBlueE2" name="tvmnsk" id="tvmnsk">
							            			<option value="">-välj-</option>
						 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvmnsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach>
											</select>										           				
							            	<a tabindex="-1" id="tvmnskIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>
											
										</td>
					           			<td colspan="4">
					           				<input  type="hidden" name="ownToldsats" id="ownToldsats" size="9" value=''>
							            	<table cellspacing="0" border="0" cellpadding="0">
							            		
							            		<tr class="tableHeaderField10" height="20" valign="left">
												 	<td align="center" class="tableHeaderFieldFirst11" title="svxv_222">&nbsp;Fakt.belopp&nbsp;</td> 
								                    <td align="center" class="tableHeaderField11" title="svxv_221">&nbsp;Fakt.valuta&nbsp;</td> 
								                    <td align="center" class="tableHeaderField11" title="svxv_221b">&nbsp;Kurs&nbsp;</td>
								                    <td align="center" class="tableHeaderField11" title="svxv_221c">&nbsp;Faktor&nbsp;</td>
								                    <td align="center" class="tableHeaderField11">&nbsp;Belopp&nbsp;SEK</td> 
									             </tr> 
									             <tr class="tableRow">
									              	<td class="tableCellFirst" align="right" >&nbsp;<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue10MandatoryField"  style="text-align: right" name="svxv_222" id="svxv_222" size="16" maxlength="16" value='${model.record.svxv_222}'>&nbsp;</td>
							               			<td class="tableCell" align="right" >&nbsp;
							               				<select class="inputTextMediumBlueMandatoryField" name="svxv_221" id="svxv_221" >
										 				  <option value="">-vælg-</option>	
										 				  	<c:forEach var="code" items="${model.currencyCodeList}" >
						                                	 	<option value="${code.svkd_kd}"<c:if test="${model.record.svxv_221 == code.svkd_kd}"> selected </c:if> >${code.svkd_kd}</option>
															</c:forEach> 
														</select>
														<a tabindex="-1" id="svxv_221IdLink" >
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find2.png" width="10px" height="10px" border="0" alt="search" >
														</a>
							               				</td>
							               			<td class="tableCell" align="right" >&nbsp;<input readonly type="text" class="inputText10ReadOnly"  style="text-align: right" name="svxv_221b" id="svxv_221b" size="16" maxlength="8" value='${model.record.svxv_221b}'>&nbsp;</td>
						               				<td class="tableCell" align="right" >&nbsp;<input readonly type="text" class="inputText10ReadOnly"  style="text-align: right" name="svxv_221c" id="svxv_221c" size="16" maxlength="7" value='${model.record.svxv_221c}'>&nbsp;</td>
						               				<td class="tableCell" align="right" ><span title="svxv_222b">&nbsp;Fakt.blp&nbsp;</span><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue10MandatoryField"  style="text-align: right" name="svxv_222b" id="svxv_222b" size="16" maxlength="16" value='${model.record.svxv_222b}'>&nbsp;</td>
							               		 </tr>
									             <tr class="tableRow">
									              	<td class="tableCellFirst" align="right" >&nbsp;</td>
							               			<td class="tableCell" align="right" >&nbsp;</td>
							               			<td class="tableCell" align="right" >&nbsp;</td>
						               				<td class="tableCell" align="right" >&nbsp;</td>
						               				<td class="tableCell" align="right" ><span title="svxv_46">&nbsp;Tollv.&nbsp;</span><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue10MandatoryField" style="text-align: right" name="svxv_46" id="svxv_46" size="16" maxlength="10" value='${model.record.svxv_46}'>&nbsp;</td>
							               		 </tr>
							               		 <tr class="tableRow">
									              	<td class="tableCellFirst" align="right" >&nbsp;</td>
							               			<td class="tableCell" align="right" >&nbsp;</td>
							               			<td class="tableCell" align="right" >&nbsp;</td>
						               				<td class="tableCell" align="right" >&nbsp;</td>
						               				<td class="tableCell" align="right" ><span title="svxv_42b">&nbsp;Moms&nbsp;</span><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue10MandatoryField" style="text-align: right"  name="svxv_42b" id="svxv_42b" size="16" maxlength="10" value='${model.record.svxv_42b}'>&nbsp;</td>
							               		 </tr>	
							               		 <tr class="tableRow">
									              	<td class="tableCellFirst" align="right" >&nbsp;</td>
							               			<td class="tableCell" align="right" >&nbsp;</td>
							               			<td class="tableCell" align="right" >&nbsp;</td>
						               				<td class="tableCell" align="right" ><span title="svxv_42c">&nbsp;<b>TOTAL</b>&nbsp;</span></td>
						               				<td class="tableCell" align="right" >&nbsp;<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue10MandatoryField" style="text-align: right" name="svxv_42c" id="svxv_42c" size="16" maxlength="12" value='${model.record.svxv_42c}'>&nbsp;</td>
							               		 </tr>	
							            	</table>
							            	
											
							            </td>
							            <td valign="top">
							            	<c:choose>	
												<c:when test="${model.status == 'G' ||  model.status=='F' || model.status == 'M' || empty model.status}">
													<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="systema.ncts.export.item.createnew.submit"/>'>
												</c:when>
												<c:otherwise>
						 				    		<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value='Ej uppdaterbart'/>
					 				    		</c:otherwise>	
				 				    		</c:choose>	
							            </td>
							            
 							        </tr>
			        	        </table>
					        </td>
					        
				        </tr>
				        <tr height="10"><td></td></tr>
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
				 				<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
				 				
			 				</td>
		 				</tr>
	 				</table>
					<%-- Item input --%>
				 	<table width="100%" align="center" class="secondarySectionFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="10"><td class="text" align="left"></td></tr>
				 		
				 		<tr>
		            		<td class="text14" align="left" >&nbsp;&nbsp;&nbsp;&nbsp;
		            		<img onMouseOver="showPop('tvtdn2_info');" onMouseOut="hidePop('tvtdn2_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
		            		<span title="tvavd2/tvtdn2" >Ärendenr.</span>&nbsp;&nbsp;
		            			<input type="text" class="inputTextMediumBlue" style="background-color:#FFFF99;" name="tvavd2" id="tvavd2" size="4" maxlength="4" value="">
		            			<input type="text" class="inputTextMediumBlue" style="background-color:#FFFF99;" name="tvtdn2" id="tvtdn2" size="8" maxlength="7" value="">
		            			<a tabindex="-1" id="tvtdn2IdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" border="0" alt="search" >
								</a>
		            			&nbsp;&nbsp;<span title="xref" >Exst.ref.nr.</span>	
		            			<input type="text" class="inputTextMediumBlue" style="background-color:#FFFF99;"  name="xref" id="xref" size="8" maxlength="7" value="">
		            			<a tabindex="-1" id="xrefIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" border="0" alt="search" >
								</a>
							
		            		<div class="text12" style="position: relative;" align="left">
							<span style="position:absolute;top:2px; width:250px;" id="tvtdn2_info" class="popupWithInputText text12"  >
								<b>Avd/Angiv.nr</b><br/>
			           			Hvis deklarasjonen er basert på et eksport angivelse, vises avd./angiv.nr. her.
 							</span>
							</div>	
		            			
		            		</td>
		            	</tr>
		            	 
		        		<tr height="5"><td class="text" align="left"></td></tr>
				 		
				 		<tr>
			 				<%-- SENDER --%>
				 			<td >		
				 				<table width="95%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="15">
							 			<td class="text14White">
											&nbsp;<img onMouseOver="showPop('2_info');" onMouseOut="hidePop('2_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								 			<b>&nbsp;2.</b>Avsändare/Exportör&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
								 			<div class="text14" style="position: relative;" align="left">
							 				<span style="position:absolute;top:2px; width:250px;" id="2_info" class="popupWithInputText text14"  >
								           		Begreppet exportör används vid export då gemenskapsvaror förs ut från EU:s tullområde till tredjeland.
												Begreppet avsändare används vid: 
								           		<ul>
								           			<li>utförsel av gemenskapsvaror till länder och områden inom EU som valt att stå utanför skatteunionen, till exempel Åland, samt</li>
								           			<li>återexport, det vill säga då icke-gemenskapsvaror återutförs.</li>
								           		</ul>
											</span>
											</div>
						 				</td>
					 				</tr>
				 				</table>
				 			</td>
				 			
			 				<%-- RECEIVER --%>
							<td>		
				 				<table width="95%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="15">
							 			<td class="text14White">
							 				&nbsp;<img onMouseOver="showPop('8_info');" onMouseOut="hidePop('8_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<b>&nbsp;8.</b>Mottagare&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
							 				<div class="text14" style="position: relative;" align="left">
							 				<span style="position:absolute;top:2px; width:250px;" id="8_info" class="popupWithInputText text14"  >
								           		<ul>
								           			<li>Här anger du det fullständiga namnet och adressen på varumottagaren, oftast den som köpt varorna.
								           				Observera att även postnummer eller motsvarande ska anges.
								           			</li>
								           			<li>I de fall mottagaren har ett EORI-nummer kan du ange det.</li>
								           		</ul>
											</span>
							 				</div>
						 				</td>
					 				</tr>
				 				</table>
				 			</td>
				 		</tr>
				 		
				 		<tr>
			 				<%-- SENDER --%>
					 		<td >
						 		<table width="95%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
							 		
							        <tr>
							        	<%-- ================================================================================== --%>
							        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
							        		 These original values will be used when the user clicks "Cancel" buttons (puttting
							        		 back original value)																--%> 
							        	<%-- ================================================================================== --%>
							        	<input type="hidden" name="orig_tvkns" id="orig_tvkns" value='${model.record.tvkns}'>
							        	<input type="hidden" name="orig_tvnas" id="orig_tvnas" value='${model.record.tvnas}'>
							        	<input type="hidden" name="orig_tvtins" id="orig_tvtins" value='${model.record.tvtins}'>
							        	<input type="hidden" name="orig_tvads1" id="orig_tvads1" value='${model.record.tvads1}'>
							        	<input type="hidden" name="orig_tvpns" id="orig_tvpns" value='${model.record.tvpns}'>
							        	<input type="hidden" name="orig_tvpss" id="orig_tvpss" value='${model.record.tvpss}'>
							        	<input type="hidden" name="orig_tvlks" id="orig_tvlks" value='${model.record.tvlks}'>
							        	<input type="hidden" name="orig_tvsks" id="orig_tvsks" value='${model.record.tvsks}'>
							        	
							            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tvkns" >Kundnummer</span></td>
							            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tvnas" >Namn&nbsp;</span>
							            	<a tabindex="-1" id="tvnasIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
										</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="tvkns" id="tvkns" size="9" maxlength="8" value="${model.record.tvkns}"></td>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="tvnas" id="tvnas" size="20" maxlength="35" value="${model.record.tvnas}"></td>
							        </tr>
							        <tr>
							            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tvtins" >TIN</span></td>
							            <td class="text14" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="tvtins" id="tvtins" size="20" maxlength="17" value="${model.record.tvtins}"></td>
							            <td align="left">&nbsp;</td>
							        </tr>
							        <tr height="4"><td>&nbsp;</td></tr>
							        <tr>
							            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tvads1" >Adress 1</span></td>
							            <td class="text14" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="tvads1" id="tvads1" size="30" maxlength="35" value="${model.record.tvads1}"></td>
							            <td align="left">&nbsp;</td>
							        </tr>
							        <tr>
							        	<td>
							        		<table>
							        		<tr>
							            		<td class="text14" align="left" >&nbsp;&nbsp;<span title="tvpns" >Postnummer</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							            		<td class="text14" align="left" >&nbsp;&nbsp;<span title="tvpss" >Postadress</span></td>
							            	</tr>
							        		<tr>
							            		<td align="left"><input type="text" class="inputTextMediumBlue" name="tvpns" id="tvpns" size="10" maxlength="9" value="${model.record.tvpns}"></td> 
							            		<td align="left"><input type="text" class="inputTextMediumBlue" name="tvpss" id="tvpss" size="10" maxlength="35" value="${model.record.tvpss}"></td> 
							        		</tr>    	
							            	</table>
							            </td>
							            <td>
							            	<table>
							        		<tr>
							            		<td class="text14" align="left" >&nbsp;&nbsp;<span title="tvlks" >Landkod</span></td>
							            		<td class="text14" align="left" >&nbsp;&nbsp;<span title="tvsks" >Språkkod</span></td>
							            	</tr>
							        		<tr>
							            		<td align="left">
							            			<select class="selectMediumBlueE2" name="tvlks" id="tvlks">
									            		<option value="">-välj-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.tvlks == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
													</c:forEach> 
												</select>
												<a tabindex="-1" id="tvlksIdLink" OnClick="triggerChildWindowCountryCodes(this)">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
												</a>
							            		</td> 
							            		<td align="left">
							            			<select class="selectMediumBlueE2" name="tvsks" id="tvsks">
									            		<option value="">-välj-</option>
									 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
					                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvsks == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
														</c:forEach> 
												</select>
												<a tabindex="-1" id="tvsksIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
												</a>
											</td> 
							        		</tr>    	
							            	</table>
							            </td>
							        </tr>
							        <tr height="15">
							            <td class="text14Bold" align="left" >&nbsp;</td> 
							        </tr>
						        </table>
					        </td>

			 				<%-- RECEIVER --%>
					 		<td >
						 		<table width="95%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
							 		<tr>
							        	<%-- ================================================================================== --%>
							        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
							        		 These original values will be used when the user clicks "Cancel" buttons (puttting
							        		 back original value)																--%> 
							        	<%-- ================================================================================== --%>
							        	<input type="hidden" name="orig_tvknk" id="orig_tvknk" value='${model.record.tvknk}'>
							        	<input type="hidden" name="orig_tvnak" id="orig_tvnak" value='${model.record.tvnak}'>
							        	<input type="hidden" name="orig_tvtink" id="orig_tvtink" value='${model.record.tvtink}'>
							        	<input type="hidden" name="orig_tvadk1" id="orig_tvadk1" value='${model.record.tvadk1}'>
							        	<input type="hidden" name="orig_tvpnk" id="orig_tvpnk" value='${model.record.tvpnk}'>
							        	<input type="hidden" name="orig_tvpsk" id="orig_tvpsk" value='${model.record.tvpsk}'>
							        	<input type="hidden" name="orig_tvlkk" id="orig_tvlkk" value='${model.record.tvlkk}'>
							        	<input type="hidden" name="orig_tvskk" id="orig_tvskk" value='${model.record.tvskk}'>
							        	
							            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tvknk" >Kundnummer</span></td>
							            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tvnak" >Namn&nbsp;</span>
							            	<a tabindex="-1" id="tvnakIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
										</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="tvknk" id="tvknk" size="9" maxlength="8" value="${model.record.tvknk}"></td>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="tvnak" id="tvnak" size="20" maxlength="35" value="${model.record.tvnak}"></td>
							        </tr>
							        <tr>
							            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tvtink" >TIN</span></td>
							            <td class="text14" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="tvtink" id="tvtink" size="20" maxlength="17" value="${model.record.tvtink}"></td>
							            <td align="left">&nbsp;</td>
							        </tr>
							        <tr height="4"><td>&nbsp;</td></tr>
							        <tr>
							            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tvadk1" >Adress 1</span></td>
							            <td class="text14" align="left" >&nbsp;&nbsp;</td>
							        </tr>
							        <tr>
							            <td align="left"><input type="text" class="inputTextMediumBlue" name="tvadk1" id="tvadk1" size="30" maxlength="35" value="${model.record.tvadk1}"></td>
							            <td align="left">&nbsp;</td>
							        </tr>
							        <tr>
							        	<td>
							        		<table>
							        		<tr>
							            		<td class="text14" align="left" >&nbsp;&nbsp;<span title="tvpnk" >Postnummer&nbsp;</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							            		<td class="text14" align="left" >&nbsp;&nbsp;<span title="tvpsk" >Postadress</span></td>
							            	</tr>
							        		<tr>
							            		<td align="left"><input type="text" class="inputTextMediumBlue" name="tvpnk" id="tvpnk" size="10" maxlength="9" value="${model.record.tvpnk}"></td> 
							            		<td align="left"><input type="text" class="inputTextMediumBlue" name="tvpsk" id="tvpsk" size="10" maxlength="35" value="${model.record.tvpsk}"></td> 
							        		</tr>    	
							            	</table>
							            </td>
							            <td>
							            	<table>
							        		<tr>
							            		<td class="text14" align="left" >&nbsp;&nbsp;<span title="tvlkk" >Landkod</span></td>
							            		<td class="text14" align="left" >&nbsp;&nbsp;<span title="tvskk" >Språkkod</span></td>
							            	</tr>
							        		<tr>
							            		<td align="left">
							            			<select class="selectMediumBlueE2" name="tvlkk" id="tvlkk">
									            		<option value="">-välj-</option>
								 				  	<c:forEach var="country" items="${model.countryCodeList}" >
				                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.tvlkk == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
													</c:forEach> 
												</select>
												<a tabindex="-1" id="tvlkkIdLink" OnClick="triggerChildWindowCountryCodes(this)">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
												</a>
							            		</td> 
							            		<td align="left">
							            			<select class="selectMediumBlueE2" name="tvskk" id="tvskk">
									            		<option value="">-välj-</option>
									 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
					                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvskk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
														</c:forEach> 
												</select>
												<a tabindex="-1" id="tvskkIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
												</a>
											</td> 
							        		</tr>    	
							            	</table>
							            </td>
							        </tr>
							        <tr height="15">
							            <td class="text14Bold" align="left" >&nbsp;</td> 
							        </tr>
						        </table>
					        </td>
				        </tr>
				        
				 		<!-- to be desided if this section (below) has big changes or not (waiting for Christer...) -->
				 		<tr height="15"><td class="text" align="left"></td></tr>
				 		
				 		<tr>
					 		<td width="50%">
						 		<table border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td colspan="2" class="text14" align="left">
							            &nbsp;<img onMouseOver="showPop('44_info');" onMouseOut="hidePop('44_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>44.</b>Tilläggsupplysningar/Bilagda handlingar [Dokument]/Certifikat och tillstånd
							            <div class="text14" style="position: relative;" align="left">
							            <span style="position:absolute;top:2px; width:250px;" id="44_info" class="popupWithInputText text14"  >
							           			<b>Tilläggsupplysningar/Bilagda handlingar [Dokument]/Certifikat och tillstånd</b>
												<br/>
							           			This box shall contain information necessary in respect of special regulations that
												might apply in the country of dispatch/export together with the reference numbers of the documents produced in support of 
												the declaration (e.g. the serial number of the T5 182 control copy, the export licence or permit number, the data required under 
												veterinary and phytosanitary regulations, the bill of lading number, etc.).<br/><br/>
												<br/><br/>
												In the case of goods involving greater risk of fraud, or where the customs authorities or the principal consider it necessary, an itinerary shall be prescribed. Details of at least
												the countries to be transited shall be entered. Where an exemption from the requirement of a prescribed itinerary has been authorised, the endorsment ‘Prescribed
												itinerary waived’ shall be entered. The sub - division ‘ “Additional information code” (AI) shall not be completed. 
										</span>
										</div>
										</td>										
							           	<td class="text14" align="left">&nbsp;</td>
							        </tr>
							        
							        
							        <tr height="5"><td class="text" align="left"></td></tr>
									<tr>
							            <td class="text14" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tilläggsupplysningar&nbsp;</td>
							           	<td class="text14" align="left"><button name="specialInformationCodeButton" class="buttonGray" type="button" onClick="showPop('specialInformationCode');" >Lägg till</button> 
								        <span style="position:absolute; left:280px; top:800px; width:650px; height:580px;" id="specialInformationCode" class="popupWithInputText"  >
								           		<div class="text10" align="left">
								           			<table border="0" cellspacing="1" cellpadding="0">
								           			<tr>
								           			<td>
								           				<table class="lightGrayBg" >
								           				<tr>
										           			<td class="text14" colspan="2">
										           				<b>44.Tilläggsupplysningar (2)</b>
										           			</td>
										        			</tr>
														<tr>
										           			<td class="text14">&nbsp;<span title="tvdty2" >Dok.typ</span></td>
										           			<td class="text14">&nbsp;<span title="tvdref2" >Dok.ref.</span></td>
															<td class="text14">&nbsp;<span title="tvdsk2" >Dok.språkkod</span></td>
															
															<td class="text14">&nbsp;<span title="tvdo2" >Upplysningar</span></td>
															<td class="text14">&nbsp;<span title="tvdosk2" >Upplysn.språkkod</span>
															</td>
														</tr>
									           			<tr>
										           			<td class="text14" nowrap >
																<select class="selectMediumBlueE2" name="tvdty2" id="tvdty2">
												            		<option value="">-välj-</option>
												 				  	<c:forEach var="code" items="${model.ncts013_DocType_CodeList}" >
								                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvdty2 == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																	</c:forEach> 
																</select>	
										           			</td>
										           			<td class="text14" align="left"><input type="text" class="inputText" name="tvdref2" id="tvdref2" size="10" maxlength="20" value="${model.record.tvdref2}"></td>										           			
										           			
							            			   			<td class="text14" nowrap >&nbsp;
											            			<select class="selectMediumBlueE2" name="tvdsk2" id="tvdsk2">
												            			<option value="">-välj-</option>
											 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvdsk2 == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																	</c:forEach>
																</select>
												            		
										           			</td>
										           			<td class="text14" nowrap >
										           				&nbsp;<input type="text" class="inputText" name="tvdo2" id="tvdo2" size="20" maxlength="26" value="${model.record.tvdo2}">										           			
										           			</td>
							            			   			<td class="text14" nowrap >&nbsp;
											            			<select class="selectMediumBlueE2" name="tvdosk2" id="tvdosk2">
													            		<option value="">-välj-</option>
												 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvdosk2 == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																	</c:forEach> 
																</select>
												            		
										           			</td>
										           			
					           							</tr>
					           							<tr>
										           			<td class="text14">&nbsp;<span title="tvtlo2" >Kod [Tilläggsinformation]</span></td>
										           			<td class="text14">&nbsp;</td>
										           			<td class="text14">&nbsp;<span title="tvexkd2" >Export från EF</span></td>
															<td class="text14">&nbsp;<span title="tvexlk2" >Export från land</span></td>
															
														</tr>
									           			<tr>
										           			<td class="text14" colspan="2" >
																<select class="selectMediumBlueE2" name="tvtlo2" id="tvtlo2">
												 				  <option selected value="">-välj-</option>
																  <option value="CAL"<c:if test="${model.record.tvtlo2 == 'CAL'}"> selected </c:if> >CAL [Skuldbelopp]</option>
																  <option value="DG0"<c:if test="${model.record.tvtlo2 == 'DG0'}"> selected </c:if> >DG0 [Export med restriktioner]</option>
																  <option value="DG1"<c:if test="${model.record.tvtlo2 == 'DG1'}"> selected </c:if> >DG1 [Export med avgift]</option>
																  <option value="DG2"<c:if test="${model.record.tvtlo2 == 'DG2'}"> selected </c:if> >DG2 [Export]</option>
															  	</select>	
										           			</td>
										           			<td class="text14" >&nbsp;
																<select class="selectMediumBlueE2" name="tvexkd2" id="tvexkd2">
												 				  <option value=""<c:if test="${model.record.tvexkd2 == '0' || model.record.tvexkd2 == ''}"> selected </c:if> >0</option>
																  <option value="1"<c:if test="${model.record.tvexkd2 == '1'}"> selected </c:if> >1</option>
															  	</select>	
										           			</td>
										           			
							            			   			<td class="text14" nowrap >&nbsp;
											            			<select class="selectMediumBlueE2" name="tvexlk2" id="tvexlk2">
											 						<option value="">-välj-</option>
												 				  	<c:forEach var="country" items="${model.countryCodeList}" >
								                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.tvexlk2 == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
																	</c:forEach> 
																</select>
												            		
										           			</td>
										           			
					           							</tr>
					           							<tr>
										           			<td class="text14" colspan="3">&nbsp;<span title="tvmtxt2" >Text</span></td>
															<td class="text14">&nbsp;<span title="tvmsk2" >Textspråk</span>
															</td>
															
														</tr>
									           			<tr>
										           			<td class="text14" colspan="3" >
										           				&nbsp;<input type="text" class="inputText" name="tvmtxt2" id="tvmtxt2" size="25" maxlength="70" value="${model.record.tvmtxt2}">										           			
										           			</td>
										           			<td class="text14" nowrap >&nbsp;
											            			<select class="selectMediumBlueE2" name="tvmsk2" id="tvmsk2">
													            		<option value="">-välj-</option>
												 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvmsk2 == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																	</c:forEach> 
																</select>
												            		
										           			</td>
					           							</tr>					           															           			
					           							</table>
					           						</td>
					           						</tr>
					           						
					           						<tr height="5"><td class="text" align="left"></td></tr>
					           						
					           						<tr>
								           			<td>
								           				<table class="grayBg">
								           				<tr>
										           			<td class="text14" colspan="2">
										           				<b>44.Tilläggsupplysningar (3)</b>
										           			</td>
										        			</tr>
														<tr>
										           			<td class="text14">&nbsp;<span title="tvdty3" >Dok.typ</span></td>
										           			<td class="text14">&nbsp;<span title="tvdref3" >Dok.ref.</span></td>
															<td class="text14">&nbsp;<span title="tvdsk3" >Dok.språkkod</span>
															</td>
															<td class="text14">&nbsp;<span title="tvdo3" >Upplysningar</span></td>
															<td class="text14">&nbsp;<span title="tvdosk3" >Upplysn.språkkod</span></td>
														</tr>
									           			<tr>
										           			<td class="text14" nowrap>
																<select class="selectMediumBlueE2" name="tvdty3" id="tvdty3">
												            		<option value="">-välj-</option>
												 				  	<c:forEach var="code" items="${model.ncts013_DocType_CodeList}" >
								                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvdty3 == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																	</c:forEach> 
																</select>	
										           			</td>
										           			<td class="text14" align="left"><input type="text" class="inputText" name="tvdref3" id="tvdref3" size="10" maxlength="20" value="${model.record.tvdref3}"></td>										           			
										           			
							            			   			<td class="text14" nowrap >&nbsp;
											            			<select class="selectMediumBlueE2" name="tvdsk3" id="tvdsk3">
												            			<option value="">-välj-</option>
											 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvdsk3 == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																	</c:forEach>
																</select>
												            		
										           			</td>
										           			<td class="text14" nowrap >
										           				&nbsp;<input type="text" class="inputText" name="tvdo3" id="tvdo3" size="20" maxlength="26" value="${model.record.tvdo3}">										           			
										           			</td>
							            			   			<td class="text14" nowrap >&nbsp;
											            			<select class="selectMediumBlueE2" name="tvdosk3" id="tvdosk3">
													            		<option value="">-välj-</option>
												 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvdosk3 == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																	</c:forEach> 
																</select>
												            		
										           			</td>
										           			
					           							</tr>
					           							<tr>
										           			<td class="text14">&nbsp;<span title="tvtlo3" >Kod [Tilläggsinformation]</span></td>
										           			<td class="text14">&nbsp;</td>
										           			<td class="text14">&nbsp;<span title="tvexkd3" >Export från EF</span></td>
															<td class="text14">&nbsp;<span title="tvexlk3" >Export från land</span></td>
															
														</tr>
									           			<tr>
										           			<td class="text14" colspan="2" >
																<select class="selectMediumBlueE2" name="tvtlo3" id="tvtlo3">
												 				  <option selected value="">-välj-</option>
																  <option value="CAL"<c:if test="${model.record.tvtlo3 == 'CAL'}"> selected </c:if> >CAL [Skuldbelopp]</option>
																  <option value="DG0"<c:if test="${model.record.tvtlo3 == 'DG0'}"> selected </c:if> >DG0 [Export med restriktioner]</option>
																  <option value="DG1"<c:if test="${model.record.tvtlo3 == 'DG1'}"> selected </c:if> >DG1 [Export med avgift]</option>
																  <option value="DG2"<c:if test="${model.record.tvtlo3 == 'DG2'}"> selected </c:if> >DG2 [Export]</option>
															  	</select>	
										           			</td>
										           			<td class="text14" >&nbsp;
																<select class="selectMediumBlueE2" name="tvexkd3" id="tvexkd3">
												 				  <option value=""<c:if test="${model.record.tvexkd3 == '0' || model.record.tvexkd3 == ''}"> selected </c:if> >0</option>
																  <option value="1"<c:if test="${model.record.tvexkd3 == '1'}"> selected </c:if> >1</option>
															  	</select>	
										           			</td>
										           			
							            			   			<td class="text14" nowrap >&nbsp;
											            			<select class="selectMediumBlueE2" name="tvexlk3" id="tvexlk3">
											 						<option value="">-välj-</option>
												 				  	<c:forEach var="country" items="${model.countryCodeList}" >
								                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.tvexlk3 == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
																	</c:forEach> 
																</select>
												            		
										           			</td>
										           			
					           							</tr>
					           							<tr>
										           			<td class="text14" colspan="3">&nbsp;<span title="tvmtxt3" >Text</span></td>
															<td class="text14">&nbsp;<span title="tvmsk3" >Textspråk</span></td>
														</tr>
									           			<tr>
										           			<td class="text14" colspan="3" >
										           				&nbsp;<input type="text" class="inputText" name="tvmtxt3" id="tvmtxt3" size="25" maxlength="70" value="${model.record.tvmtxt3}">										           			
										           			</td>
										           			<td class="text14" nowrap >&nbsp;
											            			<select class="selectMediumBlueE2" name="tvmsk3" id="tvmsk3">
													            		<option value="">-välj-</option>
												 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvmsk3 == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																	</c:forEach> 
																</select>
												            		
										           			</td>
					           							</tr>					           															           			
					           							</table>
					           						</td>
					           						</tr>
					           						
					           						<tr height="5"><td class="text" align="left"></td></tr>
					           						
					           						<tr>
								           			<td>
								           				<table class="lightGrayBg">
								           				<tr>
										           			<td class="text14" colspan="2">
										           				<b>44.Tilläggsupplysningar (4)</b>
										           			</td>
										        			</tr>
														<tr>
										           			<td class="text14">&nbsp;<span title="tvdty4" >Dok.typ</span></td>
										           			<td class="text14">&nbsp;<span title="tvdref4" >Dok.ref.</span></td>
															<td class="text14">&nbsp;<span title="tvdsk4" >Dok.språkkod</span>
															</td>
															<td class="text14">&nbsp;<span title="tvdo4" >Upplysningar</span></td>
															<td class="text14">&nbsp;<span title="tvdosk4" >Upplysn.språkkod</span></td>
														</tr>
									           			<tr>
										           			<td class="text14" nowrap>
																<select class="selectMediumBlueE2" name="tvdty4" id="tvdty4">
												            		<option value="">-välj-</option>
												 				  	<c:forEach var="code" items="${model.ncts013_DocType_CodeList}" >
								                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvdty4 == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																	</c:forEach> 
																</select>	
										           			</td>
										           			<td class="text14" align="left"><input type="text" class="inputText" name="tvdref4" id="tvdref4" size="10" maxlength="20" value="${model.record.tvdref4}"></td>										           			
										           			
							            			   			<td class="text14" nowrap >&nbsp;
											            			<select class="selectMediumBlueE2" name="tvdsk4" id="tvdsk4">
												            			<option value="">-välj-</option>
											 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvdsk4 == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																	</c:forEach>
																</select>
												            		
										           			</td>
										           			<td class="text14" nowrap >
										           				&nbsp;<input type="text" class="inputText" name="tvdo4" id="tvdo4" size="20" maxlength="26" value="${model.record.tvdo4}">										           			
										           			</td>
							            			   			<td class="text14" nowrap >&nbsp;
											            			<select class="selectMediumBlueE2" name="tvdosk4" id="tvdosk4">
													            		<option value="">-välj-</option>
												 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvdosk4 == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																	</c:forEach> 
																</select>
												            		
										           			</td>
										           			
					           							</tr>
					           							<tr>
										           			<td class="text14">&nbsp;<span title="tvtlo4" >Kod [Tilläggsinformation]</span></td>
										           			<td class="text14">&nbsp;</td>
										           			<td class="text14">&nbsp;<span title="tvexkd4" >Export från EF</span></td>
															<td class="text14">&nbsp;<span title="tvexlk4" >Export från land</span></td>
															
														</tr>
									           			<tr>
										           			<td class="text14" colspan="2" >
																<select class="selectMediumBlueE2" name="tvtlo4" id="tvtlo4">
												 				  <option selected value="">-välj-</option>
																  <option value="CAL"<c:if test="${model.record.tvtlo4 == 'CAL'}"> selected </c:if> >CAL [Skuldbelopp]</option>
																  <option value="DG0"<c:if test="${model.record.tvtlo4 == 'DG0'}"> selected </c:if> >DG0 [Export med restriktioner]</option>
																  <option value="DG1"<c:if test="${model.record.tvtlo4 == 'DG1'}"> selected </c:if> >DG1 [Export med avgift]</option>
																  <option value="DG2"<c:if test="${model.record.tvtlo4 == 'DG2'}"> selected </c:if> >DG2 [Export]</option>
															  	</select>	
										           			</td>
										           			<td class="text14" >&nbsp;
																<select class="selectMediumBlueE2" name="tvexkd4" id="tvexkd4">
												 				  <option value=""<c:if test="${model.record.tvexkd4 == '0' || model.record.tvexkd4 == ''}"> selected </c:if> >0</option>
																  <option value="1"<c:if test="${model.record.tvexkd4 == '1'}"> selected </c:if> >1</option>
															  	</select>	
										           			</td>
										           			
							            			   			<td class="text14" nowrap >&nbsp;
											            			<select class="selectMediumBlueE2" name="tvexlk4" id="tvexlk4">
											 						<option value="">-välj-</option>
												 				  	<c:forEach var="country" items="${model.countryCodeList}" >
								                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.tvexlk4 == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
																	</c:forEach> 
																</select>
												            		
										           			</td>
										           			
					           							</tr>
					           							<tr>
										           			<td class="text14" colspan="3">&nbsp;<span title="tvmtxt4" >Text</span></td>
															<td class="text14">&nbsp;<span title="tvmsk4" >Textspråk</span></td>
														</tr>
									           			<tr>
										           			<td class="text14" colspan="3" >
										           				&nbsp;<input type="text" class="inputText" name="tvmtxt4" id="tvmtxt4" size="25" maxlength="70" value="${model.record.tvmtxt4}">										           			
										           			</td>
										           			<td class="text14" nowrap >&nbsp;
											            			<select class="selectMediumBlueE2" name="tvmsk4" id="tvmsk4">
													            		<option value="">-välj-</option>
												 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvmsk4 == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																	</c:forEach> 
																</select>
												            		
										           			</td>
					           							</tr>					           															           			
					           							</table>
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
				 						<b>40.</b>Tidigare handlingar
							            <div class="text14" style="position: relative;" align="left">
							            <span style="position:absolute;top:2px; width:250px;" id="40_info" class="popupWithInputText text14"  >
						           			<b>Tidigare handlingar</b>
											<br/>
						           			Give the reference for the previous customs destination or corresponding customs documents.
											In cases where more than one reference has to be entered, the word ‘Various’ may be entered in this box and a list of the references concerned may accompany the transit declaration.  
										</span>
										</div>
										</td>
							            
							           	<td class="text14" align="left"><button name="previousDocumentsButton" class="buttonGray" type="button" onClick="showPop('previousDocuments');" >Lägg till</button> 
								        <span style="position:absolute; left:280px; top:800px; width:700px; height:200px;" id="previousDocuments" class="popupWithInputText"  >
								           		<div class="text10" align="left">
								           			<table width="100%">
								           				<tr>
										           			<td class="text14" colspan="5">
										           				<b>Tidigare handlingar</b>
										           			</td>
										        			</tr>	
								           				<tr>
										           			<td class="text14">&nbsp;<span title="tvtdt" >Dok.typ</span></td>
										           			<td class="text14">&nbsp;<span title="tvtdr" >Dok.ref.</span></td>
															<td class="text14">&nbsp;<span title="tvtdsk" >Dok.språkkod</span></td>
															<td class="text14">&nbsp;<span title="tvtdo" >Upplysningar</span></td>
															<td class="text14">&nbsp;<span title="tvtdos" >Upplysn.språkkod</span></td>
														</tr>
									           			<tr>
										           			<td class="text14" nowrap >&nbsp;1.
										           				<select class="selectMediumBlueE2" name="tvtdt" id="tvtdt">
												 				  <option selected value="">-välj-</option>
																  <option value="CO"<c:if test="${model.record.tvtdt == 'CO'}"> selected </c:if> >CO</option>
																  <option value="EU"<c:if test="${model.record.tvtdt == 'EU'}"> selected </c:if> >EU</option>
																  <option value="EX"<c:if test="${model.record.tvtdt == 'EX'}"> selected </c:if> >EX</option>
																  <option value="IM"<c:if test="${model.record.tvtdt == 'IM'}"> selected </c:if> >IM</option>
																  <option value="T-"<c:if test="${model.record.tvtdt == 'T-'}"> selected </c:if> >T-</option>
																  <option value="T1"<c:if test="${model.record.tvtdt == 'T1'}"> selected </c:if> >T1</option>
																  <option value="T2"<c:if test="${model.record.tvtdt == 'T2'}"> selected </c:if> >T2</option>
																  <option value="T2ATA"<c:if test="${model.record.tvtdt == 'T2ATA'}"> selected </c:if> >T2ATA</option>
																  <option value="T2CIM"<c:if test="${model.record.tvtdt == 'T2CIM'}"> selected </c:if> >T2CIM</option>
																  <option value="T2F"<c:if test="${model.record.tvtdt == 'T2F'}"> selected </c:if> >T2F</option>
																  <option value="T2L"<c:if test="${model.record.tvtdt == 'T2L'}"> selected </c:if> >T2L</option>
																  <option value="T2LF"<c:if test="${model.record.tvtdt == 'T2LF'}"> selected </c:if> >T2LF</option>
																  <option value="T2TIR"<c:if test="${model.record.tvtdt == 'T2TIR'}"> selected </c:if> >T2TIR</option>
																</select>
										           			</td>
										           			<td class="text14" nowrap >
										           				&nbsp;<input type="text" class="inputText" name="tvtdr" id="tvtdr" size="20" maxlength="20" value="${model.record.tvtdr}">										           			
										           			</td>
							            			   			<td class="text14" nowrap >&nbsp;
											            			<select class="selectMediumBlueE2" name="tvtdsk" id="tvtdsk">
													            		<option value="">-välj-</option>
												 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvtdsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																	</c:forEach> 
																</select>
												            		
										           			</td>
										           			<td class="text14" nowrap >
										           				&nbsp;<input type="text" class="inputText" name="tvtdo" id="tvtdo" size="20" maxlength="26" value="${model.record.tvtdo}">										           			
										           			</td>
							            			   			<td class="text14" nowrap >&nbsp;
											            			<select class="selectMediumBlueE2" name="tvtdos" id="tvtdos">
													            		<option value="">-välj-</option>
												 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tvtdos == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																	</c:forEach> 
																</select>
												            		
										           			</td>
										           			
										           		</tr>
										           		
									           			<tr>
										           			<tr>
										           			<td class="text14" nowrap >&nbsp;2.
										           				<select class="selectMediumBlueE2" name="tvtdtTODO" id="tvtdtTODO">
												 				  <option selected value="">-välj-</option>
																  <option value="CO"<c:if test="${model.record.tvtdt == 'CO'}"> selected </c:if> >CO</option>
																  <option value="EU"<c:if test="${model.record.tvtdt == 'EU'}"> selected </c:if> >EU</option>
																  <option value="EX"<c:if test="${model.record.tvtdt == 'EX'}"> selected </c:if> >EX</option>
																  <option value="IM"<c:if test="${model.record.tvtdt == 'IM'}"> selected </c:if> >IM</option>
																  <option value="T-"<c:if test="${model.record.tvtdt == 'T-'}"> selected </c:if> >T-</option>
																  <option value="T1"<c:if test="${model.record.tvtdt == 'T1'}"> selected </c:if> >T1</option>
																  <option value="T2"<c:if test="${model.record.tvtdt == 'T2'}"> selected </c:if> >T2</option>
																  <option value="T2ATA"<c:if test="${model.record.tvtdt == 'T2ATA'}"> selected </c:if> >T2ATA</option>
																  <option value="T2CIM"<c:if test="${model.record.tvtdt == 'T2CIM'}"> selected </c:if> >T2CIM</option>
																  <option value="T2F"<c:if test="${model.record.tvtdt == 'T2F'}"> selected </c:if> >T2F</option>
																  <option value="T2L"<c:if test="${model.record.tvtdt == 'T2L'}"> selected </c:if> >T2L</option>
																  <option value="T2LF"<c:if test="${model.record.tvtdt == 'T2LF'}"> selected </c:if> >T2LF</option>
																  <option value="T2TIR"<c:if test="${model.record.tvtdt == 'T2TIR'}"> selected </c:if> >T2TIR</option>
																</select>
										           			</td>
										           			<td class="text14" nowrap >
										           				&nbsp;<input type="text" class="inputTextReadOnly" size="20" maxlength="20" value="NOT SUPPORTED">										           			
										           			</td>
							            			   			<td class="text14" nowrap >&nbsp;
											            			<select class="selectMediumBlueE2" >
													            		<option value="">-välj-</option>
												 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${code.tkkode}">${code.tkkode}</option>
																	</c:forEach> 
																</select>
										           			</td>
										           			<td class="text14" nowrap >
										           				&nbsp;<input type="text" class="inputTextReadOnly" size="26" maxlength="26" value="NOT SUPPORTED">										           			
										           			</td>
							            			   			<td class="text14" nowrap >&nbsp;
											            			<select class="selectMediumBlueE2" >
													            		<option value="">-välj-</option>
												 				  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
								                                	 	<option value="${code.tkkode}">${code.tkkode}</option>
																	</c:forEach> 
																</select>
										           			</td>
										           		</tr>
									           			<tr align="left" >
															<td colspan="5" ><button name="previousDocumentsButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('previousDocuments');">&nbsp;Ok</button> 
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
							            <td class="text14" align="left">&nbsp;&nbsp;&nbsp;Godsmärkning ,antal kolli och kollislag:&nbsp;</td>
							           	<td class="text14" align="left"><button name="goodsMarkButton" class="buttonGray" type="button" onClick="showPop('goodsMark');" >Lägg till</button> 
								       	<div class="text12" style="position: relative;" align="left">
								        <span style="position:absolute; top:2px; width:450px; height:320px;" id="goodsMark" class="popupWithInputText"  >
							           		<table>
							           				<tr>
									           			<td class="text14">
									           				&nbsp;<span title="tvmn2/tvmn3/tvmn4..." >Godsmärkning [max 42 tecken]:</span>
									           			</td>
									           			<td class="text14">
															&nbsp;<span title="tveh2/tveh3/tveh4..." >Kolli slag [kod]</span>
														</td>
														<td class="text14">
															&nbsp;<span title="tvnt2/tvnt3/tvnt4..." >Kolli antal:</span>
														</td>
														<td class="text14">
															&nbsp;<span title="tvnteh2/tvnteh3/tvnteh4..." >Styck:</span>
														</td>
														
														
													</tr>
								           			<tr>
									           			<td valign="top" class="text14">
									           				&nbsp;2.<textarea rows="2" cols="15" class="inputText" name="tvmn2" id="tvmn2" maxlength="42">${model.record.tvmn2}</textarea>
									           			</td>
									           			<td align="left" valign="bottom">
									            			<select class="selectMediumBlueE2" name="tveh2" id="tveh2">
											            		<option value="">-välj-</option>
											 				  	<c:forEach var="code" items="${model.ncts017_Kolli_CodeList}" >
							                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tveh2 == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																</c:forEach> 
															</select>
								            				</td> 
														<td valign="bottom" class="text14">
															&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="tvnt2" id="tvnt2" size="5" maxlength="5" value="${model.record.tvnt2}">
														</td>
														<td valign="bottom" class="text14">
															&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="tvnteh2" id="tvnteh2" size="5" maxlength="5" value="${model.record.tvnteh2}">
														</td>
									           		</tr>
													<tr>
									           			<td valign="top" class="text14">
									           				&nbsp;3.<textarea rows="2" cols="15" class="inputText" name="tvmn3" id="tvmn3" maxlength="42">${model.record.tvmn3}</textarea>
									           			</td>
									           			<td align="left" valign="bottom">
									            			<select class="selectMediumBlueE2" name="tveh3" id="tveh3">
											            		<option value="">-välj-</option>
											 				  	<c:forEach var="code" items="${model.ncts017_Kolli_CodeList}" >
							                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tveh3 == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																</c:forEach> 
															</select>
								            				</td> 
														<td valign="bottom" class="text14">
															&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="tvnt3" id="tvnt3" size="5" maxlength="5" value="${model.record.tvnt3}">
														</td>
														<td valign="bottom" class="text14">
															&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="tvnteh3" id="tvnteh3" size="5" maxlength="5" value="${model.record.tvnteh3}">
														</td>
									           		</tr>
								           			<tr>
									           			<td valign="top" class="text14">
									           				&nbsp;4.<textarea rows="2" cols="15" class="inputText" name="tvmn4" id="tvmn4" maxlength="42">${model.record.tvmn4}</textarea>
									           			</td>
									           			<td align="left" valign="bottom">
									            			<select class="selectMediumBlueE2" name="tveh4" id="tveh4">
											            		<option value="">-välj-</option>
											 				  	<c:forEach var="code" items="${model.ncts017_Kolli_CodeList}" >
							                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tveh4 == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																</c:forEach> 
															</select>
								            				</td> 
														<td valign="bottom" class="text14">
															&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="tvnt4" id="tvnt4" size="5" maxlength="5" value="${model.record.tvnt4}">
														</td>
														<td valign="bottom" class="text14">
															&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="tvnteh4" id="tvnteh4" size="5" maxlength="5" value="${model.record.tvnteh4}">
														</td>
														
									           		</tr>
								           			<tr>
									           			<td valign="top" class="text14">
									           				&nbsp;5.<textarea rows="2" cols="15" class="inputText" name="tvmn5" id="tvmn5" maxlength="42">${model.record.tvmn5}</textarea>
									           			</td>
									           			<td align="left" valign="bottom">
									            			<select class="selectMediumBlueE2" name="tveh5" id="tveh5">
											            		<option value="">-välj-</option>
											 				  	<c:forEach var="code" items="${model.ncts017_Kolli_CodeList}">
							                                	 	<option value="${code.tkkode}"<c:if test="${model.record.tveh5 == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
																</c:forEach> 
															</select>
								            				</td> 
														<td valign="bottom" class="text14">
															&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="tvnt5" id="tvnt5" size="5" maxlength="5" value="${model.record.tvnt5}">
														</td>
														<td valign="bottom" class="text14">
															&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="tvnteh5" id="tvnteh5" size="5" maxlength="5" value="${model.record.tvnteh5}">
														</td>
														
									           		</tr>
								           			
							           			</table>
												<table width="100%" align="left" border="0">
													<tr align="left" >
														<td  class="text14"><button name="goodsMarkButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('goodsMark');">&nbsp;Ok</button> 
														</td>
													</tr>
												</table>
										</span>
										</div>
										</td>
							        </tr>
									<tr>
							            <td class="text14" align="left">&nbsp;&nbsp;&nbsp;Container nr:&nbsp;</td>
							           	<td class="text14" align="left">
							           	<button name="containerNrButton" class="buttonGray" type="button" onClick="showPop('containerNrInfo');" >Lägg till</button> 
								           	<div class="text12" style="position: relative;" align="left">
								           	<span style="position:absolute; top:2px; width:680px; height:200px;" id="containerNrInfo" class="popupWithInputText"  >
								           			<table>
								           			<tr>
									           			<td class="text14" colspan="5">
									           				<b><span title="tvcnr/tvcnr2/tvcnr3.../tvcnr6/svev_co07/svev_co08.../svev_co12" >Container nr</span></b>
									           			</td>
									        		</tr>
								           			<tr>
									           			<td class="text14">
									           				&nbsp;1.<input type="text" class="inputText" name="tvcnr" id="tvcnr" size="17" maxlength="17" value="${model.record.tvcnr}">
									           			</td>
														<td class="text14">
															&nbsp;2.<input type="text" class="inputText" name="tvcnr2" id="tvcnr2" size="17" maxlength="17" value="${model.record.tvcnr2}">
														</td>
														<td class="text14">
															&nbsp;3.<input type="text" class="inputText" name="tvcnr3" id="tvcnr3" size="17" maxlength="17" value="${model.record.tvcnr3}">
														</td>
														<td class="text14">
									           				&nbsp;4.<input type="text" class="inputText" name="tvcnr4" id="tvcnr4" size="17" maxlength="17" value="${model.record.tvcnr4}">
									           			</td>
													</tr>
													<tr>
									           			<td class="text14">
									           				&nbsp;5.<input type="text" class="inputText" name="tvcnr5" id="tvcnr5" size="17" maxlength="17" value="${model.record.tvcnr5}">
									           			</td>
														<td class="text14">
															&nbsp;6.<input type="text" class="inputText" name="tvcnr6" id="tvcnr6" size="17" maxlength="17" value="${model.record.tvcnr6}">
														</td>
														<td class="text14">
															&nbsp;7.<input type="text" class="inputTextReadOnly" name="svev_co07" id="svev_co07" size="17" maxlength="17" value="NOT SUPPORTED">
														</td>
														<td class="text14">
									           				&nbsp;8.<input type="text" class="inputTextReadOnly" name="svev_co08" id="svev_co08" size="17" maxlength="17" value="NOT SUPPORTED">
									           			</td>
													</tr>
													<tr>
									           			<td class="text14">
									           				&nbsp;9.<input type="text" class="inputTextReadOnly" name="svev_co09" id="svev_co09" size="17" maxlength="17" value="NOT SUPPORTED">
									           			</td>
														<td class="text14">
															10.<input type="text" class="inputTextReadOnly" name="svev_co10" id="svev_co10" size="17" maxlength="17" value="NOT SUPPORTED">
														</td>
														<td class="text14">
															11.<input type="text" class="inputTextReadOnly" name="svev_co11" id="svev_co11"  size="17" maxlength="17" value="NOT SUPPORTED">
														</td>
														<td class="text14">
									           				12.<input type="text" class="inputTextReadOnly" name="svev_co12" id="svev_co12" size="17" maxlength="17" value="NOT SUPPORTED">
									           			</td>
													</tr>
													
													</table>
													<table width="100%" align="left" border="0">
														<tr align="left" >
															<td class="text14" ><button name="containerNrButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('containerNrInfo');">&nbsp;Ok</button> 
															</td>
														</tr>
													</table>
								           	</span>
											</div>
							           	</td>
							        </tr>
							        <tr height="10"><td></td></tr>
							        
							        <tr>
							            <td colspan="2" class="text14" align="left">&nbsp;&nbsp;<b>Känsliga varor</b>&nbsp;</td>
							        </tr>
							        <tr>
							            <td colspan="2" class="text14" align="left">
							            		&nbsp;
							            		Kod:&nbsp;<input readonly type="text" class="inputTextReadOnly" name="tvfv" id="tvfv" size="3" maxlength="2" value="${model.record.tvfv}">
							            		&nbsp;&nbsp;Antal:&nbsp;<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="tvfvnt" id="tvfvnt" size="15" maxlength="11" value="${model.record.tvfvnt}">
							            </td>
							            
							        </tr>
							             
							        <tr height="10"><td></td></tr>
							        
							        
							</table>
					        </td>
				        </tr>
				        
				        
				        
				        <%-- SÄKERHET HERE --%>
				        <tr>
								<td colspan="2">&nbsp;&nbsp;
						            <table width="90%" align="center" class="tableBorderWithRoundCornersDarkRed" border="0" cellspacing="2" cellpadding="0">
						            		<tr>
											<td colspan="2" width="100%" align="left" valign="top" class="text14" >
												<table width="90%" align="left" border="0" cellspacing="0" cellpadding="0">
											 		<tr height="18px">
											 			<td class="text14">
											 				&nbsp;<img onMouseOver="showPop('sikkerhet_info');" onMouseOut="hidePop('sikkerhet_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
													        	<b>Säkerhet</b>&nbsp;Arbeta med (förhandsanmälan)
													        <div class="text12" style="position: relative;" align="left">
															<span style="position:absolute;top:2px; width:250px;" id="sikkerhet_info" class="popupWithInputText text12"  >
									           					TODO
															</span>
															</div>	
										 				</td>
									 				</tr>
									 				<tr>
								            			<td class="text14">
								            				<img onMouseOver="showPop('betalmade_info');" onMouseOut="hidePop('betalmade_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								            				<span title="tvtkbm">Transp.kost/Betal.sätt</span>&nbsp;
								            				<select class="selectMediumBlueE2" name="tvtkbm" id="tvtkbm">
								            				<option value="">-välj-</option>
								 				  			<c:forEach var="code" items="${model.ncts116_BetalningTransport_CodeList}" >
				                             	 				<option value="${code.tkkode}"<c:if test="${model.record.tvtkbm == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
															</c:forEach> 
														</select>
														<div class="text12" style="position: relative;" align="left">
															<span style="position:absolute;top:2px; width:250px;" id="betalmade_info" class="popupWithInputText text12"  >
											           			<ul>
											           				<c:forEach var="code" items="${model.ncts116_BetalningTransport_CodeList}" >
												 				  		<li><b>${code.tkkode}</b>&nbsp;${code.tktxtn}</li>
											 				  		</c:forEach>	
											           			</ul>
														</span>
														</div>
								            			</td>
								            			<td class="text14" nowrap>
								            				<img onMouseOver="showPop('farliggods_info');" onMouseOut="hidePop('farliggods_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								            				<span title="tvfgkd">Farliggods KD</span>&nbsp;
								            				<input type="text" class="inputTextMediumBlue" name="tvfgkd" id="tvfgkd" size="5" maxlength="4" value="${model.record.tvfgkd}">
															<a title="UN-Dangerous Goods List" tabindex=-1 href="renderLocalPdf.do?fn=UN_dangerousGoods.pdf" target="_blank">
				 												<img style="vertical-align:middle;" width="16px" height="16px" src="resources/images/pdf.png" border="0" alt="pdf">
				 											</a>
														
														<div class="text12" style="position: relative;" align="left">
														<span style="position:absolute;top:2px; width:250px;" id="farliggods_info" class="popupWithInputText text12"  >
											           			Refer to UN-list of dangerous goods
														</span>
														</div>
														
														</td>
								            		</tr>
								            		<tr height="5"><td></td></tr>
								 					<tr>
								            			<td class="text14">&nbsp;<span title="tvkref">Kommersiellt ref.nr</span></td>
								            		</tr>
								            		<tr>
								            			<td colspan="2"><input type="text" class="inputTextMediumBlue" name="tvkref" id="tvkref" size="70" maxlength="70" value="${model.record.tvkref}"></td>
								            		</tr>
								        		</table>
									        </td>
								            </tr>
						            		
						            		<tr height="3"><td>&nbsp;</td></tr>
								        	<tr>
								        		<td width="50%" valign="top">
								        		<table width="90%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
											 		<tr height="18px">
											 			<td class="text14White">
											 				&nbsp;
									 						&nbsp;Avsändare - Säkerhet&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
										 				</td>
									 				</tr>
							 					</table>
							 					</td>
							 					<td width="50%" valign="top">
													<table width="90%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
												 		<tr height="18px">
												 			<td class="text14White">
												 				&nbsp;
										 						&nbsp;Mottagare - Säkerhet&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
											 				</td>
										 				</tr>
								 					</table>
							 					</td>
							 				</tr>	
							 				<tr>
							 					<td width="50%" valign="top">
							 					<table width="90%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
								 			 		<tr>
												 		<td>
													 		<table border="0" cellspacing="0" cellpadding="0">
													 		
												 				<%-- ================================================================================== --%>
														        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
														        		 These original values will be used when the user clicks "Cancel" buttons (puttting
														        		 back original value)																--%> 
														        	<%-- ================================================================================== --%>
														        	<input type="hidden" name="orig_tvknss" id="orig_tvknss" value='${model.record.tvknss}'>
														        	<input type="hidden" name="orig_tvnass" id="orig_tvnass" value='${model.record.tvnass}'>
														        	<input type="hidden" name="orig_tvtinss" id="orig_tvtinss" value='${model.record.tvtinss}'>
														        	<input type="hidden" name="orig_tvadss1" id="orig_tvadss1" value='${model.record.tvadss1}'>
														        	<input type="hidden" name="orig_tvpnss" id="orig_tvpnss" value='${model.record.tvpnss}'>
														        	<input type="hidden" name="orig_tvpsss" id="orig_tvpsss" value='${model.record.tvpsss}'>
														        	<input type="hidden" name="orig_tvlkss" id="orig_tvlkss" value='${model.record.tvlkss}'>
														        	<input type="hidden" name="orig_tvskss" id="orig_tvskss" value='${model.record.tvskss}'>
														        	
														        	<tr height="10">
														            <td class="text14" align="left">&nbsp;</td> 
														        </tr>
														 		<tr height="15">
														            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tvknss">Kundnummer</span></td>
														            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tvnass">Namn</span>
														            	<a tabindex="-1" id="tvnassIdLink">
																			<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
																		</a>
												            		</td>
														        </tr>
														        <tr>
														        		<td align="left"><input type="text" class="inputTextMediumBlue" name="tvknss" id="tvknss" size="10" maxlength="8" value="${model.record.tvknss}"></td>
														            <td align="left"><input type="text" class="inputTextMediumBlue" name="tvnass" id="tvnass" size="30" maxlength="35" value="${model.record.tvnass}"></td>
														        </tr>
														        <tr>
												        				<td class="text14" align="left" >&nbsp;&nbsp;<span title="tvtinss">TIN</span></td>
														        </tr>
														        <tr>
														        		<td align="left"><input type="text" class="inputTextMediumBlue" name="tvtinss" id="tvtinss" size="20" maxlength="17" value="${model.record.tvtinss}"></td>
														        </tr>
					
														        <tr height="4"><td>&nbsp;</td></tr>
														        <tr>
														            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tvadss1">Adress</span></td>
														            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tvskss">Språkkod</span></td>
														        </tr>
														        <tr>
														            <td align="left"><input type="text" class="inputTextMediumBlue" name="tvadss1" id="tvadss1" size="30" maxlength="30" value="${model.record.tvadss1}"></td>
														            <td class="text14" align="left" >
														            		&nbsp;<select class="selectMediumBlueE2" name="tvskss" id="tvskss">
															            		<option value="">-välj-</option>
															 				  	<c:forEach var="record" items="${model.ncts012_Sprak_CodeList}" >
										                                	 	<option value="${record.tkkode}"<c:if test="${model.record.tvskss == record.tkkode}"> selected </c:if> >${record.tkkode}</option>
										                                	 	</c:forEach> 
																		</select>
																		<a tabindex="-1" id="tvskssIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
																			<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
																		</a>
																	</td>
														        </tr>
														        <tr>
														        		<td>
															        		<table>
															        		<tr>
															            		<td class="text14" align="left" >&nbsp;&nbsp;<span title="tvpsss">Postadress</span></td>
															            		<td align="left">&nbsp;</td>
															            	</tr>
															        		<tr>
															            		<td align="left">
															       				<input type="text" class="inputTextMediumBlue" name="tvpsss" id="tvpsss" size="30" maxlength="24" value="${model.record.tvpsss}">
														            			</td> 
															            		<td align="left">&nbsp;</td>
															        		</tr>    	
															            	</table>
														            </td>
														            <td >
															            	<table>
															        		<tr>
															        			<td class="text14" align="left" >&nbsp;&nbsp;<span title="tvpnss">Postnummer</span></td>
															            		<td class="text14" align="left" >&nbsp;<span title="tvlkss">Land</span></td>
															            	</tr>
															        		<tr >
															        			<td align="left"><input type="text" class="inputTextMediumBlue" name="tvpnss" id="tvpnss" size="10" maxlength="8" value="${model.record.tvpnss}"></td> 
															            		<td align="left">
															            			<select class="selectMediumBlueE2" name="tvlkss" id="tvlkss">
																	            		<option value="">-välj-</option>
																	            	<c:forEach var="country" items="${model.countryCodeList}" >
												                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.tvlkss == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
																					</c:forEach> 
																				</select>
																				<a tabindex="-1" id="tvlkssIdLink" OnClick="triggerChildWindowCountryCodes(this)">
																					<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
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
												        
							            		<td width="50%" valign="top" align="left">
						 						<table width="90%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
											 		<tr>
												 		<td>
													 		<table border="0" cellspacing="0" cellpadding="0">
															 		<%-- ================================================================================== --%>
															        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
															        		 These original values will be used when the user clicks "Cancel" buttons (puttting
															        		 back original value)																--%> 
															        	<%-- ================================================================================== --%>
															        	<input type="hidden" name="orig_tvknks" id="orig_tvknks" value='${model.record.tvknks}'>
															        	<input type="hidden" name="orig_tvnaks" id="orig_tvnaks" value='${model.record.tvnaks}'>
															        	<input type="hidden" name="orig_tvtinks" id="orig_tvtinks" value='${model.record.tvtinks}'>
															        	<input type="hidden" name="orig_tvadks1" id="orig_tvadks1" value='${model.record.tvadks1}'>
															        	<input type="hidden" name="orig_tvpnks" id="orig_tvpnks" value='${model.record.tvpnks}'>
															        	<input type="hidden" name="orig_tvpsks" id="orig_tvpsks" value='${model.record.tvpsks}'>
															        	<input type="hidden" name="orig_tvlkks" id="orig_tvlkks" value='${model.record.tvlkks}'>
															        	<input type="hidden" name="orig_tvskks" id="orig_tvskks" value='${model.record.tvskks}'>
															 		<tr height="10">
															            <td class="text14" align="left">&nbsp;</td> 
															        </tr>
															        <tr height="15">
															            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tvknks">Kundnummer</span></td>
															            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tvnaks">Namn</span>
															            	<a tabindex="-1" id="tvnaksIdLink">
																				<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
																			</a>	
															            </td>
															        </tr>
															        <tr>
															        		<td align="left"><input type="text" class="inputTextMediumBlue" name="tvknks" id="tvknks" size="10" maxlength="8" value="${model.record.tvknks}"></td>
															            <td align="left"><input type="text" class="inputTextMediumBlue" name="tvnaks" id="tvnaks" size="30" maxlength="35" value="${model.record.tvnaks}"></td>
															        </tr>
															        <tr>
													        				<td class="text14" align="left" >&nbsp;&nbsp;<span title="tvtinks">EORI</span></td>
															        </tr>
															        <tr>
															        		<td align="left"><input type="text" class="inputTextMediumBlue" name="tvtinks" id="tvtinks" size="20" maxlength="17" value="${model.record.tvtinks}"></td>
															        </tr>
						
															        <tr height="4"><td>&nbsp;</td></tr>
															        <tr>
															            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tvadks1">Adress</span></td>
															            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tvskks">Språkkod</span></td>
															        </tr>
															        <tr>
															            <td align="left"><input type="text" class="inputTextMediumBlue" name="tvadks1" id="tvadks1" size="30" maxlength="30" value="${model.record.tvadks1}"></td>
															            <td class="text14" align="left" >
															            		&nbsp;<select class="selectMediumBlueE2" name="tvskks" id="tvskks">
																            		<option value="">-välj-</option>
																 				  	<c:forEach var="record" items="${model.ncts012_Sprak_CodeList}" >
											                                	 	<option value="${record.tkkode}"<c:if test="${model.record.tvskks == record.tkkode}"> selected </c:if> >${record.tkkode}</option>
											                                	 	</c:forEach>
																			</select>
																			<a tabindex="-1" id="tvskksIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
																				<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
																			</a>
																		</td>
															        </tr>
															        <tr>
															        		<td>
																        		<table>
																        		<tr>
																            		<td class="text14" align="left" >&nbsp;&nbsp;<span title="tvpsks">Postadress</span></td>
																            		<td align="left">&nbsp;</td>
																            	</tr>
																        		<tr>
																            		<td align="left">
																       				<input type="text" class="inputTextMediumBlue" name="tvpsks" id="tvpsks" size="30" maxlength="24" value="${model.record.tvpsks}">
															            			</td> 
																            		<td align="left">&nbsp;</td>
																        		</tr>    	
																            	</table>
															            </td>
															            <td >
																            	<table>
																        		<tr>
																        			<td class="text14" align="left" >&nbsp;&nbsp;<span title="tvpnks">Postnummer</span></td>
																            		<td class="text14" align="left" >&nbsp;<span title="tvlkks">Land</span></td>
																            	</tr>
																        		<tr >
																        			<td align="left"><input type="text" class="inputTextMediumBlue" name="tvpnks" id="tvpnks" size="10" maxlength="8" value="${model.record.tvpnks}"></td> 
																            		<td align="left">
																            			<select class="selectMediumBlueE2" name="tvlkks" id="tvlkks">
																		            		<option value="">-välj-</option>
																	 				  	<c:forEach var="country" items="${model.countryCodeList}" >
													                                	 	<option value="${country.svkd_kd}"<c:if test="${model.record.tvlkks == country.svkd_kd}"> selected </c:if> >${country.svkd_kd}</option>
																						</c:forEach>
																						
																					</select>
																					<a tabindex="-1" id="tvlkksIdLink" OnClick="triggerChildWindowCountryCodes(this)">
																						<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
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
								        	<tr height="10"><td>&nbsp;</td></tr>
						            	</table>
						            </td>  
						            <td>&nbsp;</td>
						        </tr>
				        
				        
						<tr height="25"><td></td></tr>
				</table>
            	</td>
           	</tr>
            <tr height="30"><td></td></tr>
		</table>
		</td>
		</tr>
	</table>    
	
	</form>
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

