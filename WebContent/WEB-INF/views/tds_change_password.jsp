<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTds.jsp" />
<!-- =====================end header ==========================-->
<SCRIPT type="text/javascript" src="resources/js/tds_change_password.js?ver=${user.versionEspedsg}"></SCRIPT>	


<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="20%" valign="bottom" class="tab" align="center">
				<img onMouseOver="showPop('changePassword_info');" onMouseOut="hidePop('changePassword_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				<font class="tabLink">&nbsp;<spring:message code="systema.tds.change.password.tab"/></font>
				<img valign="bottom" src="resources/images/lockOrig.png" width="12" hight="12" border="0" alt="Change password">
				<span style="position:absolute; left:300px; top:150px; width:250px; height:100px;" id="changePassword_info" class="popupWithInputText"  >
	           		<div class="text11" align="left">
	           			Byte av lösen.
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
 	        	<tr>
 	        	<td>&nbsp;</td>
	 	        <td valign="top">
		 	        <form name="tdsChangePasswordForm" id="tdsChangePasswordForm" action="tds_change_password_submit.do" method="post" >
		 	        <table  align="left" border="0" cellspacing="1" cellpadding="0">
				 		<tr >
				    		<td class="text14Gray" >Användarid</td>
		      				<td class="text14Gray" >
		      					<input readonly type="text" class="inputTextReadOnly" name=userAS400 id="userAS400" size="10" maxlength="10" value='${user.userAS400}'>	
				    		</td>
		      	        </tr>
		      	        <tr height="15"><td></td></tr>
		      	        <tr >
					    		<td class="text14" >Nytt lösenord:</td>
		      				<td class="text" >
		      					<input type="password" class="inputText" name="pwAS400N1" id="pwAS400N1" size="10" maxlength="20" value=''>	
					    		</td>
		      	        </tr>
		      	        <tr >
					    		<td class="text14" >Repetera lösenord:</td>
		      				<td class="text" >
		      					<input type="password" class="inputText" name="pwAS400N2" id="pwAS400N2" size="10" maxlength="20" value=''>	
					    		</td>
		      	        </tr>
			 	        <tr height="30"><td></td></tr>
		      	        
		      	        <tr>
							<td>&nbsp;</td>
							<td align="left"><input class="inputFormLoginSubmitGreen" type="submit" value="<spring:message code="systema.tds.change.password.submit"/>" /></td>
						</tr>
		  	     	</table>
		      	    </form>  
			    </td>				    
		    	</tr>  	
			<tr height="4"><td></td></tr>
			
			<c:choose>
				<%-- Validation errors --%>
				<c:when test="${not empty errorMessage}">
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
				</c:when>
				<%-- Success --%>
				<c:otherwise>
					<c:if test="${not empty successMessage}">
						<tr>
							<td colspan=3>
							<table>
								<tr>
								<td class="text14MediumBlue">					
					                	${successMessage}
								</td>
								</tr>
							</table>
							</td>
						</tr>
					</c:if>
				</c:otherwise>
			</c:choose>				
		</table>
	</td>
	</tr>
	<tr height="3"><td></td></tr>
	
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

