<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTdsMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tdsmaintenancefelles_svt055r.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>


<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr height="15"><td>&nbsp;</td></tr>
	<tr>
		<td>
		<%-- tab container component --%>
		<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
				<tr height="25"> 
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkTdsMaintFellesGate" tabindex=-1 style="display:block;" href="tdsmaintenancefelles.do">
						<font class="tabDisabledLink">&nbsp;TDS - Underhåll</font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tab" align="center">
						<font class="tabLink">Firmauppl.</font>&nbsp;<font class="text14">SVT055 / SVTFI</font>&nbsp;
						<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="tdsmaintenancefelles_svt055r.do?id=${model.dbTable}">
							<img style="vertical-align: middle;"  src="resources/images/bulletGreen.png" border="0" width="8px" height="8px" alt="db table">
						</a>
					</td>
					<td width="70%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
				</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<%-- space separator --%>
			<form action="tdsmaintenancefelles_svt055r_edit.do" name="formRecord" id="formRecord" method="POST" >
			<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
			<input type="hidden" name="updateId" id=updateId value="${model.updateId}"> 
			<input type="hidden" name="action" id=action value="doUpdate">
					
	 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
	 	    <tr height="30"><td>&nbsp;</td></tr>
	 	    
	 	    <%-- Validation errors --%>
			<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="100%">
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
	            	<tr >
					<td >					
			            <ul class="isa_error text14" >
			            <c:forEach var="error" items="${errors.allErrors}">
			                <li >
			                	<spring:message code="${error.code}" text="${error.defaultMessage}"/>&nbsp;&nbsp;
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
				 			<td >
				 				<ul class="isa_error text14" >
                                    <li>[ERROR on Update] - Server return code: ${model.errorMessage}</li>                                    
                                </ul>
				 			</td>
						</tr>
					</table>
				</td>
			</tr>
			</c:if>
			<%--
			<tr >
				<td width="5%">&nbsp;</td>
				<td><button name="newRecordButton" id="newRecordButton" class="inputFormSubmitStd" type="button" >Opret ny</button></td>
			</tr>
			 --%>
	 	    <tr >
	 	    	<td width="5%">&nbsp;</td>
				<td width="100%">
					<table class=formFrameHeaderPeachWithBorder width="80%" cellspacing="0" border="0" align="left">
						<tr><td class="text14White" >&nbsp;<img style="vertical-align: middle;"  src="resources/images/bulletGreen.png" border="0" width="10px" height="10px" alt="test"></td>
						</tr>
					</table>
					<table class="formFramePeachGrayRoundBottom" width="80%" cellspacing="1" border="0" align="left">	
						<%--
						------------------------ 
						 UNB
						------------------------ 
						--%>
						<tr height="8"><td colspan="3"></td>
						<tr>
							<td colspan="2" class="text14" title=""><b>&nbsp;UNB</b>&nbsp;
								<img style="vertical-align: middle;"  src="resources/images/checkmarkOK.png" border="0" width="12px" height="12px" alt="UNB">
								<font class="text14">&nbsp;&nbsp;&nbsp;Test&nbsp;</font>
								<select name="svtf_0035" id="svtf_0035">
			 						<option value="1" <c:if test="${model.record.svtf_0035 == '1'}"> selected </c:if> >Ja</option>
			 						<option value="" <c:if test="${empty model.record.svtf_0035}"> selected </c:if> >Nej</option>
								</select>
								
							</td>	
						</tr>
						<tr height="5"><td></td>
						<tr>
							<td class="text14" title="svtf_0004"><font class="text14RedBold" >*</font>Avsändarid
								<a tabindex="-1" id="svtf_0004IdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>
							</td>
							<td class="text14" title="svtf_0008">&nbsp;Avs.vidareadress</td>
						</tr>
						<tr>
							<td ><input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="svtf_0004" id="svtf_0004" size="36" maxlength="35" value='${model.record.svtf_0004}'></td>
							<td ><input type="text" class="inputTextMediumBlue" name="svtf_0008" id="svtf_0008" size="15" maxlength="14" value='${model.record.svtf_0008}'></td>
						</tr>
						<tr>
							<td class="text14" title="svtf_0010"><font class="text14RedBold" >*</font>Mottagarid
								<a tabindex="-1" id="svtf_0010IdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>
							</td>
							<td class="text14" title="svtf_0014">&nbsp;Mottag.vidareadress</td>
							<td class="text14" title="svtf_0022"><font class="text14RedBold" >*</font>Lösenord</td>
						</tr>
						<tr>
							<td ><input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="svtf_0010" id="svtf_0010" size="36" maxlength="35" value='${model.record.svtf_0010}'></td>
							<td ><input type="text" class="inputTextMediumBlue" name="svtf_0014" id="svtf_0014" size="15" maxlength="14" value='${model.record.svtf_0014}'></td>
							<td ><input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="svtf_0022" id="dktf_0022" size="15" maxlength="14" value='${model.record.svtf_0022}'></td>
						</tr>
						<tr height="20"><td></td>
						<tr>
							<td class="text14" title="svtf_pref"><font class="text14RedBold" >*</font>Tullid prefix</td>
							<td class="text14" title="svtf_numb"><font class="text14RedBold" >*</font>Sist använda nr.</td>
							<td class="text14" title="svtf_kval">&nbsp;Kvalitetsäkrad</td>
							<td class="text14" title="svtf_usri"><font class="text14RedBold" >*</font>SMS-sender Userid
								<a tabindex="-1" id="svtf_usriIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>
							</td>
							<td class="text14" title="svtf_usra"><font class="text14RedBold" >*</font>SMS-sender Adress</td>
						</tr>
						<tr>
							<td ><input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="svtf_pref" id="svtf_pref" size="4" maxlength="3" value='${model.record.svtf_pref}'></td>
							<td ><input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="svtf_numb" id="svtf_numb" size="7" maxlength="6" value='${model.record.svtf_numb}'></td>
							<td >
								<select name="svtf_kval" id="svtf_kval">
			 						<option value="J" <c:if test="${model.record.svtf_kval == '1'}"> selected </c:if> >Ja</option>
			 						<option value="" <c:if test="${empty model.record.svtf_kval}"> selected </c:if> >Nej</option>
								</select>
							</td>
							<td >
								<input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="svtf_usri" id="svtf_usri" size="15" maxlength="14" value='${model.record.svtf_usri}'>
							</td>
							<td >
								<input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="svtf_usra" id="svtf_usra" size="15" maxlength="14" value='${model.record.svtf_usra}'>
							</td>
						</tr>
						
			 	    	<tr height="15"><td></td>
						<tr>
							<td colspan="10">
								<table class="formFrameTitaniumWhite" width="99%" cellspacing="0" border="0" align="left">
									<tr height="5"><td></td></tr>
									<tr>
										<td class="text14" >&nbsp;<b>Säkerhet och Certifikat</b></td>
									</tr>
									<tr height="5"><td></td>
									<tr>
										<td class="text14" >&nbsp;<b>Security party id</b></td>
									</tr>
									<tr>
										<td class="text14" title="svtf_sec1"><font class="text14RedBold" >*</font>Egen</td>
										<td class="text14" title="svtf_sec2"><font class="text14RedBold" >*</font>Tullverket</td>
									</tr>
									<tr>
										<td >
											<input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="svtf_sec1" id="svtf_sec1" size="18" maxlength="17" value='${model.record.svtf_sec1}'>
										</td>
										<td >
											<input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="svtf_sec2" id="svtf_sec2" size="18" maxlength="17" value='${model.record.svtf_sec2}'>
										</td>
									</tr>
									<tr height="10"><td></td>
									<tr>
										<td class="text14" >&nbsp;<b>Certifikatserienr.</b></td>
									</tr>
									<tr>
										<td class="text14" title="svtf_cer1"><font class="text14RedBold" >*</font>Egen</td>
										<td class="text14" title="svtf_cer2"><font class="text14RedBold" >*</font>Tullverket</td>
									</tr>
									<tr>
										<td >
											<input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="svtf_cer1" id="svtf_cer1" size="36" maxlength="35" value='${model.record.svtf_cer1}'>
										</td>
										<td >
											<input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="svtf_cer2" id="svtf_cer2" size="36" maxlength="35" value='${model.record.svtf_cer2}'>
										</td>
									</tr>
									<tr height="10"><td></td>
									<tr>
										<td class="text14" >&nbsp;<b>Certifikatnyckelid</b></td>
									</tr>
									<tr>
										<td class="text14" title="svtf_cer3">&nbsp;Egen</td>
										<td class="text14" title="svtf_cer4">&nbsp;Tullverket</td>
									</tr>
									<tr>
										<td >
											<input type="text" class="inputTextMediumBlue" name="svtf_cer3" id="svtf_cer3" size="36" maxlength="35" value='${model.record.svtf_cer3}'>
										</td>
										<td >
											<input type="text" class="inputTextMediumBlue" name="svtf_cer4" id="svtf_cer4" size="36" maxlength="35" value='${model.record.svtf_cer4}'>
										</td>
									</tr>
									
									<tr height="10"><td>&nbsp;</td></tr>
								</table>
							</td>
						</tr>
						
						<tr height="10"><td>&nbsp;</td></tr>
	 	    		</table>
	 	    		
	 	    		<table width="80%" cellspacing="1" border="0" align="left">
	 	    			<tr height="10"><td>&nbsp;</td>
			    	    <tr>
				    	    <td class="text14" align="right">
								<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Spara'/>
							</td>
						</tr>
						<tr height="3"><td></td>
					</table>	 
	 	    </tr>
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	   </table>
	 		</form>
		</td>
	</tr>
</table>	

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

