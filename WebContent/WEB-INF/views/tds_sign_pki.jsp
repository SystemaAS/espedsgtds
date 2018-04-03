<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTds.jsp" />
<!-- =====================end header ==========================-->

<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="20%" valign="bottom" class="tab" align="center">
				<img onMouseOver="showPop('tdsBehorighetPki_info');" onMouseOut="hidePop('tdsBehorighetPki_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				<font class="tabLink">&nbsp;<spring:message code="systema.tds.sign.tab"/></font>
				<img valign="bottom" src="resources/images/lockOrig.png" width="12" hight="12" border="0" alt="PKI certificate infrastructure">
				<span style="position:absolute; left:300px; top:150px; width:250px; height:100px;" id="tdsBehorighetPki_info" class="popupWithInputText"  >
	           		<div class="text11" align="left">
	           			Denna behörighetskontrollen är nödvändig för att kunna signera dina ärenden inom ramen av PKI-infrastrukturen
					</div>
				</span>
			</td>
			<td width="80%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
			
		</tr>
	</table>
	</td>
</tr>

<tr>
	<td>
	<%-- tab component --%>
 		<table height="300px" width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 	        <tr height="20"><td></td></tr>
 	        <c:choose>
	 	      	<c:when test="${user.authenticatedTdsSignPkiUserAS400 == 'Y'}">
	 	        	<tr>
	 	        	<td>&nbsp;</td>
		 	        <td valign="top">
			 	        <form name="tdsExportSignPkiForm" id="tdsExportSignPkiForm" action="tds_sign_pki_submit_smscode.do" method="post" >
			 	        <table  align="left" border="0" cellspacing="1" cellpadding="0">
					 		<tr >
					    		<td class="text12Gray" >AnvändarId</td>
			      				<td class="text12Gray" >
			      					<input readonly type="text" class="inputTextReadOnly" name=userAS400 id="userAS400" size="10" maxlength="10" value='${user.userAS400}'>	
					    		</td>
			      	        </tr>
			      	        <tr >
					    		<td class="text12Gray" >Lösenord</td>
			      				<td class="text12Gray" >
			      					<input readonly type="password" class="inputTextReadOnly" name="pwAS400" id="pwAS400" size="10" maxlength="20" value='dummy'>	
					    		</td>
			      	        </tr>
				 	        <tr height="30"><td></td></tr>
			      	        
			      	        <tr >
					    		<td class="text12" >
					    		<img onMouseOver="showPop('signSmsCode_info');" onMouseOut="hidePop('signSmsCode_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					    		Engångskod
					    		<span style="position:absolute; left:300px; top:150px; width:250px; height:100px;" id="signSmsCode_info" class="popupWithInputText"  >
					           		<div class="text11" align="left">
					           			Här matar du in din SMS-kod du nyss fått av systemet (som textmeddelande via din telefon).
									</div>
								</span>
					    		</td>

			      				<td class="text12" >
			      					<input  onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="signSmsCode" id="signSmsCode" size="10" maxlength="7" value=''>	
					    		</td>
			      	        </tr>
			      	        
			      	        <tr>
								<td>&nbsp;</td>
								<td align="left"><input class="inputFormLoginSubmitGreen" type="submit" value="<spring:message code="systema.tds.authenticate.random.code.submit"/>" /></td>
							</tr>
			  	     	</table>
			      	    </form>  
				    </td>				    
			    	</tr>  	
			    </c:when>
			    <c:otherwise>
	 	        	<tr>
		 	        	<td>&nbsp;</td>
			 	        <td valign="top">
				 	        <form name="tdsExportSignPkiForm" id="tdsExportSignPkiForm" action="tds_sign_pki.do" method="post" >
				 	        <table align="left" border="0" cellspacing="1" cellpadding="0">
						 		<tr >
						    		<td class="text12" >AnvändarId</td>
				      				<td class="text12" >
				      					<input type="text" class="inputTextMediumBlue" name=userAS400 id="userAS400" size="10" maxlength="10" value='${user.userAS400}'>	
						    		</td>
				      	        </tr>
				      	        <tr >
						    		<td class="text12" >Lösenord</td>
				      				<td class="text12" >
				      					<input type="password" class="inputTextMediumBlue" name="pwAS400" id="pwAS400" size="10" maxlength="20" value=''>	
						    		</td>
				      	        </tr>
				      	        <tr>
									<td>&nbsp;</td>
									<td align="left"><input class="inputFormLoginSubmitGreen" type="submit" value="<spring:message code="systema.tds.authenticate.submit"/>" /></td>
								</tr>
								
				  	     	</table>
				      	    </form>  
					    </td>
					    <td valign="top">
				 	        <table align="left" border="0" cellspacing="0" cellpadding="0">
						 		<tr>
						    		<td valign="top" class="text12">Vid giltigt lösenord överförs ni till del-2 där ett sms-meddelande med en engångskod<br/> skickas då till er personliga mobiltelefon.</td>
			    				</tr>
				  	     	</table>
					    </td>
					    <td valign="top" width="20%">
				 	        <table align="left" border="0" cellspacing="0" cellpadding="0">
						 		<tr>
						    		<td valign="top" class="text12" colspan="2">&nbsp;&nbsp;&nbsp;</td>
			    				</tr>
				  	     	</table>
					    </td>
			    	</tr>
			    	<tr height="20"><td></td></tr>
			    	
			    </c:otherwise>    
		    </c:choose>
			<tr height="20"><td></td></tr>
			
			<%-- Validation errors --%>
			<c:if test="${errorMessage!=null}">
				<tr>
					<td colspan=3>
					<table>
						<tr>
						<td class="textError">					
				            <ul>
				                <li >
				                	${errorMessage}
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
	<tr height="3"><td></td></tr>
	
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

