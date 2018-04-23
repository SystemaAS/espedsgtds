<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTds.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tdsglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>		
	<SCRIPT type="text/javascript" src="resources/js/nctsimport_unloading_edit_items.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	
	<%-- tab container component --%>
	<table width="100%"  class="text12" cellspacing="0" border="0" cellpadding="0">
		
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="nctsimport.do?action=doFind&sign=${model.sign}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.ncts.import.list.tab"/></font>
					<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="nctsimport_edit.do?action=doFetch&avd=${recordTopic.tiavd}&opd=${recordTopic.titdn}
						&sysg=${recordTopic.tisg}&syst=${recordTopic.tist}&sydt=${recordTopic.tidt}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.ncts.import.created.mastertopic.tab"/></font>
					<font class="text14MediumBlue">[${recordTopic.titdn}]</font>
					<c:if test="${ recordTopic.tist == 'F' || recordTopic.tist == 'M' || empty recordTopic.tist}">
						<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
					</c:if>
				</a>
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="nctsimport_edit_items.do?action=doFetch&avd=${recordTopic.tiavd}&sign=${recordTopic.tisg}
											&opd=${recordTopic.titdn}&mrnNr=${recordTopic.titrnr}&godsNr=${recordTopic.tign}
											&status=${recordTopic.tist}&datum=${recordTopic.tidt}">
				<font class="tabDisabledLink">
					&nbsp;<spring:message code="systema.ncts.import.item.createnew.tab"/>
				</font>
				<c:if test="${recordTopic.tist == 'F' || recordTopic.tist == 'M' || empty recordTopic.tist}">
					<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
				</c:if>
				</a>
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="nctsimport_unloading_edit.do?avd=${recordTopic.tiavd}&sign=${recordTopic.tisg}
											&opd=${recordTopic.titdn}&mrnNr=${recordTopic.titrnr}&godsNr=${recordTopic.tign}
											&status=${recordTopic.tist}&datum=${recordTopic.tidt}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.ncts.import.unloading.createnew.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/unloading.png" width="16" hight="16" border="0" alt="show log">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">
					&nbsp;<spring:message code="systema.ncts.import.unloading.item.createnew.tab"/>
				</font>
				<img style="vertical-align: bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="item lines">
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="nctsimport_logging.do?avd=${recordTopic.tiavd}&sign=${recordTopic.tisg}
									&opd=${recordTopic.titdn}&mrnNr=${recordTopic.titrnr}&godsNr=${recordTopic.tign}
									&status=${recordTopic.tist}&datum=${recordTopic.tidt}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.ncts.import.logging.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 style="display:block;" href="nctsimport_archive.do?avd=${recordTopic.tiavd}&sign=${recordTopic.tisg}
									&opd=${recordTopic.titdn}&mrnNr=${recordTopic.titrnr}&godsNr=${recordTopic.tign}
									&status=${recordTopic.tist}&datum=${recordTopic.tidt}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.ncts.import.archive.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
				</a>
			</td>
			<td width="13%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					 	
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
				 				&nbsp;Avd&nbsp;<b>${recordTopic.tiavd}</b>
				 				&nbsp;Ärende&nbsp;<b>${recordTopic.titdn}</b>
				 				&nbsp;Sign&nbsp;<b>${recordTopic.tisg}</b>
				 				&nbsp;&nbsp;&nbsp;Status:&nbsp;<b>${recordTopic.tist}</b>
				 				&nbsp;&nbsp;
								<span title="tienkl">Typ av förfarande:</span>&nbsp;
								<c:if test="${recordTopic.tienkl == 'J'}"><b>Förenklad</b></c:if>
								<c:if test="${recordTopic.tienkl == 'N'}"><b>Normal</b></c:if>
				 				&nbsp;&nbsp;&nbsp;Mrn-nr:&nbsp;<b>${recordTopic.titrnr}</b>
				 				&nbsp;&nbsp;&nbsp;Gods-nr:&nbsp;<b>${recordTopic.tign}</b>
			 				</td>
		 				</tr>
		 				<tr height="20"><td></td></tr>
	 				</table>
	 				
					<%-- MASTER Topic information [it is passed through a session object: recordTopic] --%>
				 	<table height="40" width="100%" align="center" class="formFrameTitaniumWhite" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="2"><td class="text" align="left" colspan="2"></td></tr>
				 		<tr>
					 		<td width="50%">
						 		<table width="80%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text12Bold" align="left" >Ansvarig&nbsp;</td>
							            <td class="text12" align="left" ></td>
							        </tr>
									<tr>
							            <td width="30%" class="text12" align="left" >Kundnr&nbsp;</td>
							            <td class="text12MediumBlue" align="left" >${recordTopic.tikn}</td>
							        </tr>
							        							        
							        <tr>
							            <td width="30%" class="text12" align="left">TIN-nr&nbsp;</td>
							           	<td class="text12MediumBlue" align="left"><b>${recordTopic.titin}</b></td>
							        </tr>
									<tr>
							            <td width="30%" class="text12" align="left">Namn&nbsp;</td>
							           	<td class="text12MediumBlue" align="left"><b>${recordTopic.tina}</b></td>
							        </tr>
								</table>
					        </td>
							<td width="50%">
						 		<table width="80%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td class="text12" align="left" >&nbsp;</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text12" align="left">Adress&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopic.tiad1}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text12" align="left">Postadress&nbsp;</td>
							           	<td class="text12MediumBlue" align="left">${recordTopic.tipn}&nbsp;${recordTopic.tips}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text12" align="left">Landkod
							            </td>
							           	<td class="text12MediumBlue" align="left">${recordTopic.tilk}</td>
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
							<form id="createNewItemLine" action="nctsimport_unloading_edit_items.do">
								<input type="hidden" name="action" id="action" value='doFetch'>
				 				<input type="hidden" name="avd" id="avd" value='${model.avd	}'>
				 				<input type="hidden" name="sign" id="sign" value='${model.sign}'>
								<input type="hidden" name="opd" id="opd" value='${model.opd}'>
				 				<input type="hidden" name="mrnNr" id="mrnNr" value='${model.mrnNr}'>
				 				<input type="hidden" name="status" id="status" value='${model.status}'>
				 				<input type="hidden" name="datum" id="datum" value='${model.datum}'>
				 					
							<td class="text14Bold">&nbsp;Varulinjer&nbsp;&nbsp;
								<input tabindex=-1 class="inputFormSubmitStd" type="submit" name="submit" value='Skapa ny'>
								<button name="allItemsButton" class="inputFormSubmitStd" type="button" onClick="showPop('allItems');" >Visa alla</button> 
							        <span style="background-color:#EEEEEE; position:absolute; left:50px; top:200px; width:1100px; height:1000px;" id="allItems" class="popupWithInputTextThickBorder"  >
						           		<div class="ownScrollableSubWindow" style="width:1080px; height:900px; margin:10px;">
						           			<nav>
						           			<table width="100%" border="0" align="left" cellspacing="2">
						           			<tr>
							           			<td colspan="3" class="text14"><b>Varulinjer</b></td>
							           		</tr>
								           	<tr>	
												<td >
												<table width="95%" cellspacing="0" border="0" cellpadding="0">
													<tr class="tableHeaderField" height="20" valign="left">
													    <td class="tableHeaderFieldFirst">&nbsp;<spring:message code="systema.ncts.import.unloading.item.list.label.tvli.linjeNr"/>&nbsp;</td>   
									                    <td align="center" class="tableHeaderField" nowrap>Ta bort</td>
									                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.ncts.import.unloading.item.list.label.nvvnt.varukod"/>&nbsp;</td>   
									                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.ncts.import.unloading.item.list.label.nvdty.dokTyp"/>&nbsp;</td>
									                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.ncts.import.unloading.item.list.label.nvdref.dokRef"/>&nbsp;</td>
									                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.ncts.import.unloading.item.list.label.nvvktb.bruttoVikt"/>&nbsp;</td>
									                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.ncts.import.unloading.item.list.label.nvvktn.nettoVikt"/>&nbsp;</td>
									                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.ncts.import.unloading.item.list.label.nveh.kolliSlag"/></td>
									                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.ncts.import.unloading.item.list.label.nvnt.kolliAnt"/></td>
									                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.ncts.import.unloading.item.list.label.nvvt.varuBeskrivning"/>&nbsp;</td>
									               </tr>  
										           <c:forEach items="${model.list}" var="record" varStatus="counter">    
										               <c:choose>           
										                   <c:when test="${counter.count%2==0}">
										                       <tr class="tableRow" height="20" >
										                   </c:when>
										                   <c:otherwise>   
										                       <tr class="tableOddRow" height="20" >
										                   </c:otherwise>
										               </c:choose>
										               <%-- <td class="tableCellFirst" width="2%">&nbsp;${counter.count}</td> --%>
										               <td width="2%" class="tableCellFirst" align="center">&nbsp;${record.tvli}</td>
										               <td class="tableCell" align="center" nowrap>&nbsp;
										               	<a onclick="javascript:return confirm('Är du säker på att du vill ta bort raden?')" tabindex=-1 href="nctsimport_unloading_edit_items.do?action=doDelete&avd=${record.tvavd}&opd=${record.tvtdn}&lin=${record.tvli}">
										               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
										               	</a>	
										               </td>
										               <td class="tableCell" >&nbsp;${record.nvvnt}</td>
										               <td class="tableCell" >&nbsp;${record.nvdty}</td>
										               <td class="tableCell" >&nbsp;${record.nvdref}</td>
										               <td class="tableCell" align="right" >&nbsp;${record.nvvktb}&nbsp;</td>
										               <td class="tableCell" align="right" >&nbsp;${record.nvvktn}&nbsp;</td>
										               <td class="tableCell" >&nbsp;${record.nveh}</td>
										               <td class="tableCell" >&nbsp;${record.nvnt}</td>
										               <td width="40%" class="tableCell" width="100" >&nbsp;${record.nvvt}</td>
										               
											           </tr>
											        <%-- <c:set var="numberOfItemLinesInTopic" value="${counter.count}" scope="request" /> --%>
											        <c:set var="numberOfItemLinesInTopic" value="${record.tvli}" scope="request" />
											         
										            </c:forEach>
										        </table>
												</td>											           		
									         </tr>
									         </table>
									         </nav>
									         </div>
									         <div>
						           				<table >
												<%-- OK BUTTON --%>
						           				<tr align="left" >
													<td class="text12"><button name="allItemsButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('allItems');">&nbsp;Ok</button></td>
													<td class="text14">&nbsp;&nbsp;&nbsp;
										 	        		<a href="tdsNctsImportUnloadingItemListExcelView.do" target="_new">
									                 		<img valign="bottom" id="itemListExcel" src="resources/images/excel.png" border="0" alt="excel">&nbsp;Excel
										 	        		</a>
										 	        		&nbsp;
											 		</td>
												</tr>
												</table>
								   			</div>
						   				</span>	
							</td>
							</form>
						</tr> 
						<tr>
							<td class="ownScrollableSubWindow" style="width:100%; height:10em;">
								<table width="100%" cellspacing="0" border="0" cellpadding="0">
									<tr class="tableHeaderField" height="20" valign="left">
									    <td class="tableHeaderFieldFirst">&nbsp;<spring:message code="systema.ncts.import.unloading.item.list.label.tvli.linjeNr"/>&nbsp;</td>   
					                    <td align="center" class="tableHeaderField" nowrap>Ta bort</td>
					                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.ncts.import.unloading.item.list.label.nvvnt.varukod"/>&nbsp;</td>   
					                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.ncts.import.unloading.item.list.label.nvdty.dokTyp"/>&nbsp;</td>
					                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.ncts.import.unloading.item.list.label.nvdref.dokRef"/>&nbsp;</td>
					                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.ncts.import.unloading.item.list.label.nvvktb.bruttoVikt"/>&nbsp;</td>
					                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.ncts.import.unloading.item.list.label.nvvktn.nettoVikt"/>&nbsp;</td>
					                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.ncts.import.unloading.item.list.label.nveh.kolliSlag"/></td>
					                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.ncts.import.unloading.item.list.label.nvnt.kolliAnt"/></td>
					                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.ncts.import.unloading.item.list.label.nvvt.varuBeskrivning"/>&nbsp;</td>
					               </tr> 
					               
					               <form name="formItemList" id="formItemList" method="POST" >
					               		<input type="hidden" name="opdItemList" id="opdItemList" value='${model.opd}'>
				 						<input type="hidden" name="avdItemList" id="avdItemList" value='${model.avd}'> 
				 						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
				 						 
							           <c:forEach items="${model.list}" var="record" varStatus="counter">    
							               <c:choose>           
							                   <c:when test="${counter.count%2==0}">
							                       <tr class="tableRow" height="20" >
							                   </c:when>
							                   <c:otherwise>   
							                       <tr class="tableOddRow" height="20" >
							                   </c:otherwise>
							               </c:choose>
							               <%-- <td class="tableCellFirst" width="2%">&nbsp;${counter.count}</td> --%>
							               <td width="2%" class="tableCellFirst" align="center">&nbsp;
							               		<a tabindex=-1 id="recordUpdate_${counter.count}_${record.tvli}" href="#" onClick="getItemData(this);">${record.tvli}
							               			<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;
							               		</a>
								               	
							               </td>
							               <td class="tableCell" align="center" nowrap>&nbsp;
							               	<a onclick="javascript:return confirm('Är du säker på att du vill ta bort raden?')" tabindex=-1 href="nctsimport_unloading_edit_items.do?action=doDelete&avd=${record.tvavd}&opd=${record.tvtdn}&lin=${record.tvli}">
							               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
							               	</a>	
							               </td>
							               
							               <td class="tableCell" >&nbsp;${record.nvvnt}</td>
							               <td class="tableCell" >&nbsp;${record.nvdty}</td>
							               <td class="tableCell" >&nbsp;${record.nvdref}</td>
							               <td class="tableCell" align="right" >&nbsp;${record.nvvktb}&nbsp;</td>
							               <td class="tableCell" align="right" >&nbsp;${record.nvvktn}&nbsp;</td>
							               <td class="tableCell" >&nbsp;${record.nveh}</td>
							               <td class="tableCell" >&nbsp;${record.nvnt}</td>
							               
							               <td width="40%" class="tableCell" width="100" >&nbsp;${record.nvvt}</td>
							               	
							               
								           </tr>
								        <%-- <c:set var="numberOfItemLinesInTopic" value="${counter.count}" scope="request" /> --%>
								        <c:set var="numberOfItemLinesInTopic" value="${record.tvli}" scope="request" />
								         
							            </c:forEach>
						            </form>	
					            </table>
							</td>	
						</tr>
					</table>
				</td>
			</tr>
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
	 			<form name="nctsImportEditTopicItemForm" id="nctsImportEditTopicItemForm" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="opd" id="opd" value='${model.opd}'/>
				 	<input type="hidden" name="avd" id="avd" value='${model.avd}'/>
				 	<input type="hidden" name="sign" id="sign" value='${model.sign}'/>
				 	<input type="hidden" name="status" id="status" value='${model.status}'/>
				 	<input type="hidden" name="datum" id="datum" value='${model.datum}'/>
				 	<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
				 	<input type="hidden" name="numberOfItemLinesInTopic" id="numberOfItemLinesInTopic" value='${numberOfItemLinesInTopic}' />
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="100%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text14White">
				 				<b>&nbsp;&nbsp;Varulinje&nbsp;-&nbsp;Kontrollresultat&nbsp;&nbsp;</b>
				 				<img src="resources/images/update.gif" border="0" alt="edit">
				 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 				<input tabindex=-1 align="center" class="text14BoldLightGreenForItemLinenr" readonly type="text" name="lineNr" id="lineNr" size="3" value="">
			 				</td>
			 				<td class="text14White" align="right">
				 				Konform&nbsp;&nbsp;<font class="text14RedBold">${ recordTopicUnloading.nikonf }</font>&nbsp;&nbsp;&nbsp;
				 			</td>
		 				</tr>
	 				</table>
	 				
					<table width="100%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="4"><td class="text" align="left"></td></tr>
				 		<tr>
					 		<td >
						 		<table width="100%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							 			<td class="text14" align="left" valign="bottom">
							 				<img onMouseOver="showPop('control_code_info');" onMouseOut="hidePop('control_code_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<span title="nvct" >Kod</span>
							 				<div class="text11" style="position: relative;" align="left">
							 				<span style="position:absolute;top:2px; width:250px;" id="control_code_info" class="popupWithInputText text11"  >
							           			<b>Kod</b>
												<br/><br/>
												 (Om lossningsresultat = 0)
												 <ul>
												 	<li><b>DI</b> = Avvikelse i värde.</li>
												 	<li><b>NE</b> = New entry. I samband med ny linje</li>
												 	<li><b>OT</b> = Andra saker att rapportera</li>
												 </ul>
											</span>
											</div>
							 			</td>
							 			<td class="text14" align="left">
							            		&nbsp;&nbsp;<span title="nvctsk" >Språkkod</span>
							            </td>
							            <td colspan="4" class="text14" align="left" valign="bottom">
											&nbsp;<span title="nvctb" >Beskrivning</span>
										</td>
							        </tr>
							        <tr>
							        		<td class="text14" align="left">
											<select class="selectMediumBlueE2"  name="nvct" id="nvct">
												<option value=""<c:if test="${model.record.nvct == ''}"> selected </c:if> >-Välj-</option>
											  	<option value="DI"<c:if test="${model.record.nvct == 'DI'}"> selected </c:if> >DI</option>
											  	<option value="NE"<c:if test="${model.record.nvct == 'NE'}"> selected </c:if> >NE</option>
											  	<option value="OT"<c:if test="${model.record.nvct == 'OT'}"> selected </c:if> >OT</option>
											</select>
			 			            		</td>
							        		<td >
											<select class="selectMediumBlueE2" name="nvctsk" id="nvctsk">
							            			<option value="">-Välj-</option>
						 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.nvctsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach>
											</select>
											<a tabindex="-1" class="text14" target="_blank" href="${model.isoLanguageCodesURL.value}" onclick="${model.isoLanguageCodesURL.windowOpenDimensions}" >
							            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
							            		</a>
										</td>
										<td colspan="4" class="text14" align="left" >
							        			<input type="text" class="inputText" name="nvctb" id="nvctb" size="71" maxlength="70" value="${model.record.nvctb}">
			 			            		</td>
			 			            					 			            		
							        </tr>
							        <tr height="12"><td class="text" align="left"></td></tr>
							        
							 		<tr>
							 			<td class="text14" align="left" valign="bottom">
							 				&nbsp;&nbsp;<span title="tvli" >Linjenr.</span>
							 			</td>
							 			<td class="text14" align="left">
							            		&nbsp;&nbsp;<span title="nvvnt" >Varukod</span>
							            		<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" onClick="showPop('searchTaricCodesDialog');">
								            <%-- ======================================================== --%>
							            		<%-- Here we have the search Taric codes popup window --%>
							            		<%-- ======================================================== --%>
							            		<span style="position:absolute; left:300px; top:450px; width:500px; height:210px;" id="searchTaricCodesDialog" class="popupWithInputText"  >
								           		<div class="text10" align="left">
								           			<table>
									           			<tr>
									           			<td>
										           			<table>
										           				<tr>
																	<td class="text11">&nbsp;Varukod</td>
																	<td class="text11">&nbsp;<input type="text" class="inputText" name="search_svvs_vata" id="search_svvs_vata" size="10" maxlength="8" value=''></td>
																</tr>
											           			<tr>
											           				<td align="right">&nbsp;<button name="searchTaricCode" class="buttonGray" type="button" onClick="searchTaricVarukod();">Sök</button></td>
											           				<td class="text11">&nbsp;</td>
												           			
												           		</tr>
												           		<tr height="4"><td ></td></tr>
											           		</table>
										           		</td>
										           		</tr>
														
														<tr>
									           			<td>
										           			<table>							           		
												           		<tr>
											           				<td class="text11">&nbsp;Lista</td>
												           			<td>&nbsp;</td>
												           		</tr>
												           		<tr>
																	<td colspan="2">&nbsp;
																		<select class="selectMediumBlueE2"  id="taricVarukodList" name="taricVarukodList" size="5" onDblClick="hidePop('searchTaricCodesDialog');">
						 													<option selected value="">-vælg-</option>
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
							            </td>
							            
							            <td nowrap class="text14" align="left" valign="bottom">
											&nbsp;<span title="nvvtsk" >Språkkod</span>
											<a tabindex="-1" class="text14" target="_blank" href="${model.isoLanguageCodesURL.value}" onclick="${model.isoLanguageCodesURL.windowOpenDimensions}" >
							            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
							            		</a>
										</td>
										<td class="text14" align="left">
							            		&nbsp;&nbsp;<span title="nvvktb" >Brut.vikt(kg)</span>
							            </td>
										<td class="text14" align="left">
							            		&nbsp;&nbsp;<span title="nvvktn" >Net.vikt(kg)</span>
							            </td>
							            <td colspan="3" class="text14" align="left">
							            		&nbsp;<font class="text16RedBold" >*</font><span title="nvvt" >Varubeskrivning</span>
							            </td>
							        </tr>
							        <tr>
							        		<td class="text14" align="left">
							        			&nbsp;<input tabindex=-1 readonly type="text" class="inputTextReadOnly" name="tvli" id="tvli" size="4" maxlength="5" value="${model.record.tvli}">
			 			            		</td>
							        		<td class="text14" align="left" >
							        			<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="nvvnt" id="nvvnt" size="6" maxlength="6" value="${model.record.nvvnt}">
			 			            		</td>
			 			            		<td >
											<select class="selectMediumBlueE2" name="nvvtsk" id="nvvtsk">
							            			<option value="">-Välj-</option>
						 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.nvvtsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach>
											</select>
										</td>
										<td class="text14" align="left" >
							        			<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="nvvktb" id="nvvktb" size="9" maxlength="9" value="${model.record.nvvktb}">
			 			            		</td>
			 			            		<td class="text14" align="left" >
							        			<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="nvvktn" id="nvvktn" size="9" maxlength="9" value="${model.record.nvvktn}">
			 			            		</td>
			 			            		<td colspan="3" class="text14" align="left" >
							        			<input type="text" class="inputText" name="nvvt" id="nvvt" size="60" maxlength="70" value="${model.record.nvvt}">
			 			            		</td>
			 			            					 			            		
							        </tr>
							        
							        
							        <tr><td class="text" colspan="9"><hr></td></tr>
							         
							        <tr>
							 			<td class="text14" align="left" valign="bottom">
							 				&nbsp;<b>44.</b>&nbsp;<span title="nvdty" >Dok.typ</span>
							 			</td>
							 			<td colspan="3" class="text14" align="left">
							            		&nbsp;<b>44.</b>&nbsp;<span title="nvdref" >Dok.ref.</span>
							            </td>
										<td class="text14" align="left" valign="bottom">
											<b>44.</b>&nbsp;<span title="nvdsk" >Dok.språk</span>
											<a tabindex="-1" class="text14" target="_blank" href="${model.isoLanguageCodesURL.value}" onclick="${model.isoLanguageCodesURL.windowOpenDimensions}" >
							            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
							            		</a>
										</td>
										
										<td class="text14" align="left">
							            		&nbsp;<b>44.</b>&nbsp;<span title="nvdo" >Uppfyllt upplysningar</span>
							            </td>
							            
										<td class="text14" align="left">
							            		&nbsp;<b>44.</b>&nbsp;<span title="nvdosk" >Upp.språk</span>
							            </td>
							            
							        </tr>
							        <tr>
							        
										<%-- Doc.Type --%>
										<td class="text14" align="left">
											<select class="selectMediumBlueE2" name="nvdty" id="nvdty">
							            		<option value="">-Välj-</option>
							 				  	<c:forEach var="code" items="${model.ncts013_DocType_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.nvdty == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
											</select>										           				
										</td>							        
							        		<td colspan="3" class="text14" align="left" >
							        			<input type="text" class="inputText" name="nvdref" id="nvdref" size="35" maxlength="35" value="${model.record.nvdref}">
			 			            		</td>
			 			            		<td >
											<select class="selectMediumBlueE2" name="nvdsk" id="nvdsk">
							            			<option value="">-Välj-</option>
						 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.nvdsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach>
											</select>
										</td>
										<td class="text14" align="left" >
							        			<input type="text" class="inputText" name="nvdo" id="nvdo" size="26" maxlength="26" value="${model.record.nvdo}">
			 			            		</td>
			 			            		
										<td >
											<select class="selectMediumBlueE2" name="nvdosk" id="nvdosk">
							            			<option value="">-Välj-</option>
						 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.nvdosk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach>
											</select>
										</td>
							        </tr>
							        
							        <tr>
							 			<td colspan="4" class="text14" align="left" valign="bottom">
							 				&nbsp;<b>31.</b>&nbsp;<font class="text16RedBold" >*</font><span title="nvmn" >Godsmärkning</span>
							 			</td>
							 			<td nowrap class="text14" align="left" valign="bottom">
											<b>31.</b>&nbsp;<span title="nvmnsk" >Godsm.språk</span>
											<a tabindex="-1" class="text14" target="_blank" href="${model.isoLanguageCodesURL.value}" onclick="${model.isoLanguageCodesURL.windowOpenDimensions}" >
							            			<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
							            		</a>
										</td>
										<td class="text14" align="left">
							            		&nbsp;<b>31.</b>&nbsp;<span title="nvcnr" >Container</span>
							            </td>
							            <td class="text14" align="left">
							            		&nbsp;<b>31.</b>&nbsp;<font class="text16RedBold" >*</font><span title="nveh" >Kollislag</span>
							            </td>
							           <td class="text14" align="left">
							            		&nbsp;<b>31.</b>&nbsp;<span title="nvnt" >Kolliantal</span>
							            </td>
							           <td class="text14" align="left">
							            		&nbsp;<b>31.</b>&nbsp;<span title="nvnteh" >STK</span>
							            </td>
							        </tr>
							        <tr>
							        		<td colspan="4" class="text14" align="left">
							        			<input type="text" class="inputText" name="nvmn" id="nvmn" size="35" maxlength="42" value="${model.record.nvmn}">
			 			            		</td>
							        		<td >
											<select class="selectMediumBlueE2" name="nvmnsk" id="nvmnsk">
							            			<option value="">-Välj-</option>
						 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.nvmnsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach>
											</select>
										</td>
										<td class="text14" align="left" >
							        			<input type="text" class="inputText" name="nvcnr" id="nvcnr" size="20" maxlength="17" value="${model.record.nvcnr}">
			 			            		</td>
			 			            		
			 			            		<td class="text14" align="left" >
							        			<select class="selectMediumBlueE2" name="nveh" id="nveh">
							            		<option value="">-Välj-</option>
							 				  	<c:forEach var="code" items="${model.ncts017_Kolli_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.nveh == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
											</select>
											<a tabindex=-1 href="renderLocalPdf.do?fn=TDS_EDI_NCTS_forpackningskoder_fn_rekomendationer_ncts.pdf" target="_blank">
		 										<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
		 									</a>		
			 			            		</td>
			 			            		<td class="text14" align="left" >
							        			<input onKeyPress="return numberKey(event)" align="right" type="text" class="inputText" name="nvnt" id="nvnt" size="6" maxlength="5" value="${model.record.nvnt}">
			 			            		</td>
			 			            		<td class="text14" align="left" >
							        			<input onKeyPress="return numberKey(event)" align="right" type="text" class="inputText" name="nvnteh" id="nvnteh" size="6" maxlength="5" value="${model.record.nvnteh}">
			 			            		</td>
							        </tr>
							        
   							        <tr><td class="text" colspan="9"><hr></td></tr>
   							        <tr>
							 			<td colspan="2" class="text14Bold" align="left" >
							 				&nbsp;&nbsp;Känsliga varor
							 			</td>
							 		</tr>
							        <tr>
							 			<td class="text14" align="left" >
							 				&nbsp;&nbsp;<span title="nvfv" >Kod</span>
							 			</td>
							 			<td colspan="2" class="text14" align="left">
							            		&nbsp;&nbsp;<span title="nvfvnt" >Antal</span>
							            </td>
							        </tr>
							        <tr>
							        		<td class="text14" align="left">
							        			<select class="selectMediumBlueE2" name="nvfv" id="nvfv">
								        			<option value="">-Välj-</option>
								 				<option value="1"<c:if test="${model.record.nvfv == '1'}"> selected </c:if> >1</option>
											</select>
			 			            		</td>
							        		<td colspan="2" class="text14" align="left">
							        			<input onKeyPress="return numberKey(event)" align="right" type="text" class="inputText" name="nvfvnt" id="nvfvnt" size="12" maxlength="11" value="${model.record.nvfvnt}">
			 			            		</td>
							        		
							        
						            		<%-- only status = U,H are allowed  --%>
					 				    <c:choose>
						 				    <c:when test="${ recordTopic.tist == 'U' ||  recordTopic.tist == 'H' }">
						 				    		<c:choose>
						 				    			<c:when test="${ recordTopicUnloading.nikonf != '1'}">
									 				    <td align="center" class="text9BlueGreen" valign="bottom"  >
										 				    &nbsp;&nbsp;&nbsp;<input tabindex=-1 class="inputFormSubmit" type="submit" name="submit" onclick="javascript: form.action='nctsimport_unloading_edit_items.do';" value="<spring:message code="systema.ncts.import.unloading.createnew.submit"/>"/>
										 				</td>    	
									 				</c:when>
									 				<c:otherwise>
									 				    <td  align="center" class="text9BlueGreen" valign="bottom"  >
									 				    		&nbsp;&nbsp;&nbsp;<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value="Ej uppdaterbart"/>
									 				    	</td>	
									 				</c:otherwise>
								 				</c:choose>
						 				    </c:when>
						 				    <c:otherwise>
							 				    <td  align="center" class="text9BlueGreen" valign="bottom"  >
							 				    		&nbsp;&nbsp;&nbsp;<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value="Ej uppdaterbart"/>
							 				    	</td>	
						 				    </c:otherwise>	
					 				    </c:choose>
							        </form>
 							        <tr height="5"><td></td></tr>
				        	        </table>
					        </td>
				        </tr>
						
						<%-- ----------------- --%>						
						<%-- READ ONLY SECTION --%>
						<%-- ----------------- --%>					
						<tr height="22"><td>&nbsp;</td></tr>
				        <tr>
 					        <td colspan="3">
							<table width="90%" align="left" class="formFrameHeaderBlueWithBorder" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="15">
						 			<td class="text14White">
						 				&nbsp;&nbsp;<b>Varulinje&nbsp;-&nbsp;Original</b>&nbsp;[från exportören]
					 				</td>
				 				</tr>
			 				</table>				        
					        </td>
				        </tr>
				        
				 		<tr>
					 		<td>
						 		<table align="left" width="90%" class="secondarySectionFrame" border="0" cellspacing="0" cellpadding="0">
						 			<tr>
							 			<td class="text14" align="left" valign="bottom">
							 				&nbsp;<span title="tvvnt" >Varukod</span>
							 			</td>
							 			<td class="text14" align="left" valign="bottom">
							            		&nbsp;&nbsp;<span title="tvvtsk" >Språkkod</span>
							            </td>
										<td class="text14" align="left" valign="bottom">
											&nbsp;<span title="tvvktb" >Brut.vikt(kg)</span>
										</td>
							 			<td class="text14" align="left" valign="bottom">
											&nbsp;<span title="tvvktn" >Net.vikt(kg)</span>
										</td>
							 			<td class="text14" align="left" valign="bottom">
							            		&nbsp;&nbsp;<span title="tvvt" >Varubeskrivning</span>
							            </td>
							            
							        </tr>
							        <tr>
							        		<td class="text14" align="left">
							        			&nbsp;<input readonly type="text" class="inputTextReadOnly" name="tvvnt" id="tvvnt" size="7" maxlength="6" value="${model.record.tvvnt}">
			 			            		</td>
			 			            		<td class="text14" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvvtsk" id="tvvtsk" size="3" maxlength="2" value="${model.record.tvvtsk}">
										</td>
							        		<td class="text14" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvvktb" id="tvvktb" size="10" maxlength="9" value="${model.record.tvvktb}">
										</td>
										<td class="text14" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvvktn" id="tvvktn" size="10" maxlength="9" value="${model.record.tvvktn}">
										</td>
										<td colspan="2" class="text14" align="left" >
							        			<input readonly type="text" class="inputTextReadOnly" name="tvvt" id="tvvt" size="20" maxlength="70" value="${model.record.tvvt}">
			 			            		</td>
			 			            		
							        </tr>
 							        <tr><td class="text" colspan="6"><hr></td></tr>
						 			<tr>
							 			<td class="text14" align="left" valign="bottom">
							 				&nbsp;<span title="tvdty" ><b>44.</b>&nbsp;Dok.typ</span>
							 			</td>
										<td class="text14" align="left" valign="bottom">
											&nbsp;<span title="tvdref"><b>44.</b>&nbsp;Dok.ref.</span>
										</td>
							 			<td class="text14" align="left" valign="bottom">
											&nbsp;<span title="tvdsk"><b>44.</b>&nbsp;Dok.språk</span>
										</td>
							 			<td nowrap class="text14" align="left" valign="bottom">
							            		&nbsp;<span title="tvdo"><b>44.</b>&nbsp;Upp.upplysningar</span>
							            </td>
							            <td nowrap class="text14" align="left" valign="bottom">
							            		&nbsp;<span title="tvdosk"><b>44.</b>&nbsp;Upp.språk</span>
							            </td>
							        </tr>
							        <tr>
							        		<td class="text14" align="left">
							        			&nbsp;<input readonly type="text" class="inputTextReadOnly" name="tvdty" id="tvdty" size="5" maxlength="4" value="${model.record.tvdty}">
			 			            		</td>
							        		<td class="text14" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvdref" id="tvdref" size="20" maxlength="35" value="${model.record.tvdref}">
										</td>
										<td class="text14" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvdsk" id="tvdsk" size="3" maxlength="2" value="${model.record.tvdsk}">
										</td>
										<td class="text14" align="left" >
							        			<input readonly type="text" class="inputTextReadOnly" name="tvdo" id="tvdo" size="20" maxlength="26" value="${model.record.tvdo}">
			 			            		</td>
			 			            		<td class="text14" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvdosk" id="tvdosk" size="3" maxlength="2" value="${model.record.tvdosk}">
										</td>
							        </tr>

 							        <tr><td class="text" colspan="6"><hr></td></tr>
						 			<tr>
							 			<td colspan="2" class="text14" align="left" valign="bottom">
							 				&nbsp;<span title="tvmn" ><b>31.</b>&nbsp;Godsmärkning</span>
							 			</td>
										<td class="text14" align="left" valign="bottom">
											&nbsp;<span title="tvmnsk"><b>31.</b>&nbsp;Godsm.språk</span>
										</td>
							 			<td class="text14" align="left" valign="bottom">
											&nbsp;<span title="tvcnr"><b>31.</b>&nbsp;Container</span>
										</td>
							 			<td class="text14" align="left" valign="bottom">
							            		&nbsp;<span title="tveh"><b>31.</b>&nbsp;Kollislag</span>
							            </td>
							            <td class="text14" align="left" valign="bottom">
							            		&nbsp;<span title="tvnt"><b>31.</b>&nbsp;Kolliantal</span>
							            </td>
							            <td class="text14" align="left" valign="bottom">
							            		&nbsp;<span title="tvnteh"><b>31.</b>&nbsp;STK</span>
							            </td>
							        </tr>
							        <tr>
							        		<td colspan="2" class="text14" align="left">
							        			&nbsp;<input readonly type="text" class="inputTextReadOnly" name="tvmn" id="tvmn" size="20" maxlength="42" value="${model.record.tvmn}">
			 			            		</td>
							        		<td class="text14" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvmnsk" id="tvmnsk" size="3" maxlength="2" value="${model.record.tvmnsk}">
										</td>
										<td class="text14" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvcnr" id="tvcnr" size="20" maxlength="17" value="${model.record.tvcnr}">
										</td>
										
										<td class="text14" align="left" >
							        			<input readonly type="text" class="inputTextReadOnly" name="tveh" id="tveh" size="3" maxlength="2" value="${model.record.tveh}">
			 			            		</td>
			 			            		<td class="text14" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvnt" id="tvnt" size="6" maxlength="5" value="${model.record.tvnt}">
										</td>
										<td class="text14" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvnteh" id="tvnteh" size="6" maxlength="5" value="${model.record.tvnteh}">
										</td>
							        </tr>
							        <tr height="15"><td></td></tr>
			        	        		</table>
					        </td>
				        </tr>
				        <tr height="10"><td class="text" align="left"></td></tr>
				</table>
			</td>
		</tr>
		<tr height="30"><td>&nbsp;</td></tr>
	</table>    
	
	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

