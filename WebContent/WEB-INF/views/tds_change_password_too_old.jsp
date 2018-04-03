<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %> <!-- generally you will include this in a header.jsp -->
 

<%-- This <tds_change_password_too_old.jsp> page DOES NOT include headerTds.jsp since it should be decouple
meaning that we avoid more exceptions inside the headerTds.jsp general header. There are already many exceptions.
It has been a design decision in order to keep this function explicitly separate from the TDS gateway (tdsgate.do) --%> 
 
 
<html>
	<head>
		<link href="resources/${user.cssEspedsg}" rel="stylesheet" type="text/css"/>
		<link type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.0/themes/overcast/jquery-ui.css" rel="stylesheet">
		<link rel="SHORTCUT ICON" type="image/png" href="resources/images/systema_logo.png"></link>
		<%-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> --%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
		<title>Systema - TDS</title>
	</head>
	<body>
	<%-- include som javascript functions --%>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.24/jquery-ui.min.js"></script>
	
	<script type="text/javascript" src="resources/js/systemaWebGlobal.js?ver=${user.versionEspedsg}"></script>
	
    <table class="noBg" width="1100" border="0" cellspacing="0" cellpadding="0">
		<%--Banner --%>
	 	<tr>
	 		 <%-- class="grayTitanBg" --%>
    		<td height="60" class="headerTdsBannerAreaBg" width="100%" align="left" colspan="3"> 
    			 <table width="1100" border="0" cellspacing="0" cellpadding="0">
    			 	<tr>
			        	<td>&nbsp;</td>
			        	<td>&nbsp;</td>
				 		<td>&nbsp;</td>
			        </tr>
				 	<tr>
				 		<td class="text12white" width="10%" align=left valign="bottom" >&nbsp;</td>
				 		<td class="text22Bold" width="80%" align="middle" valign="middle" > Systema - TDS</td>
			    		<td class="text12" width="10%" align="center" valign="middle" ><img src="resources/images/systema_logo.png" border="0" width=90px height=60px ></td>
			      		<%-- <td class="text12white" width="10%" align=right valign="bottom" >&nbsp;</td>--%>
			        </tr>
			        <tr>
			        	<td>&nbsp;</td>
			        	<td>&nbsp;</td>
				 		<td class="text14" width="10%" align=right valign="bottom" >&nbsp;</td>
			        </tr>
			        <tr class="text" height="1"><td></td></tr>
			     </table> 
			</td>
		</tr>
    		<tr>
			<td height="23" class="tabThinBorderLightSlateGray" width="100%" align="left" colspan="3"> 
    			 <table width="1100" border="0" cellspacing="1" cellpadding="1">
				 	<tr >
			    		<td class="text11" width="50%" align="left" >&nbsp;&nbsp;</td>
      				<td class="text11" width="50%" align="right">
      					<font class="headerMenuGreen">
			    				<img src="resources/images/appUser.gif" border="0" > 
							<font style="color:#000000" >${user.user}&nbsp;</font>${user.usrLang}
			    			</font>
			    			<font color="#FFFFFF"; style="font-weight: bold;">&nbsp;|&nbsp;</font>
			    			<a href="logoutTds.do">
			    				<font class="headerMenuGreen"><img src="resources/images/home.gif" border="0">&nbsp;
			    					<font style="color:#000000" ><spring:message code="dashboard.menu.button"/>&nbsp;</font>
			    				</font>
			    			</a>
			    		</td>
	      	        </tr>
	      	     </table> 
			</td>
	    </tr>
		<tr class="text" height="20"><td></td></tr>
		<tr>
			<td>
			<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
			<tr>
				<td>
				<%-- tab container component --%>
				<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
					<tr height="2"><td></td></tr>
					<tr height="25"> 
						<td colspan="2" class="text12" >&nbsp;
							<img onMouseOver="showPop('changePassword_info');" onMouseOut="hidePop('changePassword_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							<font class="tabLink">&nbsp;<spring:message code="systema.tds.change.password.tab"/> eftersom lösenordet är för gammalt</font>
							<img valign="bottom" src="resources/images/lockOrig.png" width="12" hight="12" border="0" alt="Change password">
							<span style="position:absolute; left:300px; top:150px; width:250px; height:100px;" id="changePassword_info" class="popupWithInputText"  >
				           		<div class="text11" align="left">
				           			Byte av lösen.
								</div>
							</span>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			</table>
			</td>
		</tr>

		<tr>
		<td>
		<%-- tab component --%>
		<table width="250" border="0" cellspacing="1" cellpadding="0">
	 	        <tr height="20"><td></td></tr>
	 	        	<tr>
	 	        	<td>&nbsp;</td>
		 	        <td valign="top">
			 	        <form name="tdsChangePasswordForm" id="tdsChangePasswordForm" action="tds_change_password_too_old_submit.do" method="post" >
			 	        <table  align="left" border="0" cellspacing="1" cellpadding="0">
					 		<tr >
						    		<td class="text12Gray" >Användarid</td>
			      				<td class="text12Gray" >
			      					<input readonly type="text" class="inputTextReadOnly" name=userAS400 id="userAS400" size="10" maxlength="10" value='${user.userAS400}'>	
						    		</td>
			      	        </tr>
			      	        <tr height="15"><td></td></tr>
			      	        <tr >
						    		<td class="text12" >Nytt lösenord:</td>
			      				<td class="text" >
			      					<input type="password" class="inputText" name="pwAS400N1" id="pwAS400N1" size="10" maxlength="20" value=''>	
						    		</td>
			      	        </tr>
			      	        <tr >
						    		<td class="text12" >Repetera lösenord:</td>
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
						<%-- It is not necessary since the immediate action is a redirection to the main tdsgate...
						<c:if test="${not empty successMessage}">
							<tr>
								<td colspan=3>
								<table>
									<tr>
									<td class="text12MediumBlue">					
						                	${successMessage}
									</td>
									</tr>
								</table>
								</td>
							</tr>
						</c:if>
						 --%>
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

