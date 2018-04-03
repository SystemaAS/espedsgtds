<!DOCTYPE html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %> <!-- generally you will include this in a header.jsp -->

<html>
	<head>
		<link href="resources/${user.cssEspedsg}?ver=${user.versionEspedsg}" rel="stylesheet" type="text/css"/>
		<link href="resources/jquery.calculator.css" rel="stylesheet" type="text/css"/>
		<link type="text/css" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/overcast/jquery-ui.css" rel="stylesheet">
		<%--<link type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.0/themes/smoothness/jquery-ui.css" rel="stylesheet"> --%>
		
		<%-- datatables grid CSS --%>
		<link type="text/css" href="//cdn.datatables.net/1.10.11/css/jquery.dataTables.css" rel="stylesheet">
		
		<c:choose>
			<%-- set up BEFORE login --%>
			<c:when test="${ fn:contains(user.cssEspedsg, 'Toten') }"> 
				<link rel="SHORTCUT ICON" type="image/ico" href="resources/images/toten_ico.ico"></link>
			</c:when>
			<c:otherwise>
				<link rel="SHORTCUT ICON" type="image/png" href="resources/images/systema_logo.png"></link>
			</c:otherwise>
		</c:choose>
		<%-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> --%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE" />
		<title>eSpedsg - TDS</title>
	</head>
	<body>
	<%-- include som javascript functions --%>
	<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="resources/js/systemaWebGlobal.js?ver=${user.versionEspedsg}"></script>
	<SCRIPT type="text/javascript" src="resources/js/headerTds.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
	<%--datatables grid --%>
	<script type="text/javascript" src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
	
    <table class="noBg" width="1250" border="0" cellspacing="0" cellpadding="0">
		<%--Banner --%>
	 	<tr>
	 		 <%-- class="grayTitanBg" --%>
    		<td height="60" class="headerTdsBannerAreaBg" width="100%" align="left" colspan="3"> 
    			 <table width="1250" border="0" cellspacing="0" cellpadding="0">
    			 	<tr>
			        	<td>&nbsp;</td>
			        	<td>&nbsp;</td>
				 		<td>&nbsp;</td>
			        </tr>
				 	<tr>
				 		<c:choose>
					 		<c:when test="${not empty user.logo}">
				 				<c:choose>
					 				<c:when test="${fn:contains(user.logo, '/')}">
					 					<td class="text12" width="10%" align="center" valign="middle" >
											<img src="${user.logo}" border="0" width="30px" height="20px">
										</td>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${fn:contains(user.logo, 'systema')}">
											<td class="text12white" width="10%" align=left valign="bottom" >&nbsp;
												<img src="resources/images/${user.logo}" border="0" width=80px height=50px>
											</td>
											</c:when>
											<c:otherwise>
												<c:if test="${fn:contains(user.logo, 'logo')}">
													<td class="text12white" width="10%" align=left valign="bottom" >&nbsp;
														<img src="resources/images/${user.logo}" border="0" >
													</td>
												</c:if>
											</c:otherwise>
										</c:choose>	
									</c:otherwise>
								</c:choose>
   			 				</c:when> 
   			 				<c:otherwise>
						 		<td class="text12white" width="10%" align=left valign="bottom" >&nbsp;</td>
						 		<%-- <td class="text12white" width="10%" align=right valign="bottom" >&nbsp;</td>--%>
					 		</c:otherwise>
				 		</c:choose>
					 		
				 		<td class="text22Bold" width="80%" align="middle" valign="middle" style="color:#778899;" > 
				 			eSped<font style="color:#003300;">sg</font> - TDS
				 		</td>
			    		<td class="text12" width="10%" align="center" valign="middle" ><img src="resources/images/systema_logo.png" border="0" width=80px height=50px ></td>
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
		<%-- Header menu --%>
		<c:choose>
		<c:when test="${user.authorizedTdsUserAS400 == 'Y'}">
			<tr >
				<td height="23" class="tabThinBorderLightSlateGray" width="100%" align="left" colspan="3"> 
	    			 <table width="1250" border="0" cellspacing="0" cellpadding="0">
					 	<tr >
				    		<td class="text11" width="75%" align="left" >&nbsp;&nbsp;
				    			<%-- --------------- --%>
				    			<%-- TDS EXPORT MENU --%>
				    			<%-- --------------- --%>
				    			<a tabindex=-1 href="tdsexport.do?action=doFind&sign=${user.tdsSign}">
				    				&nbsp;<font 
				    				<c:choose>           
			                   			<c:when test="${user.activeMenu=='TDS_EXPORT'}">
			                       			class="headerMenuMediumGreen"
			                   			</c:when>
			                   			<c:otherwise>   
			                       			class="headerMenuLightGreen"
			                   			</c:otherwise>
			               			</c:choose>
				    				
				    				>&nbsp;<spring:message code="systema.tds.export.label"/>&nbsp;</font>
				    			</a>
				    			&nbsp;<font color="#FFFFFF"; style="font-weight: bold;">|</font>
				    			
				    			<%-- --------------- --%>
				    			<%-- TDS IMPORT MENU --%>
				    			<%-- --------------- --%>
				    			<a tabindex=-1 href="tdsimport.do?action=doFind&sign=${user.tdsSign}">
				    				&nbsp;<font 
				    				<c:choose>           
			                   			<c:when test="${user.activeMenu=='TDS_IMPORT'}">
			                       			class="headerMenuMediumGreen"
			                   			</c:when>
			                   			<c:otherwise>   
			                       			class="headerMenuLightGreen"
			                   			</c:otherwise>
			               			</c:choose>
				    				
				    				>&nbsp;<spring:message code="systema.tds.import.label"/>&nbsp;</font>
				    			</a>
				    			&nbsp;<font color="#FFFFFF"; style="font-weight: bold;">|</font>
				    			<%-- ---------------- --%>
				    			<%-- NCTS EXPORT MENU --%>
				    			<%-- ---------------- --%>
				    			<a tabindex=-1 href="nctsexport.do?action=doFind&sign=${user.tdsSign}">
				    				&nbsp;<font
				    				<c:choose>           
			                   			<c:when test="${user.activeMenu=='NCTS_EXPORT'}">
			                       			class="headerMenuMediumGreen"
			                   			</c:when>
			                   			<c:otherwise>   
			                       			class="headerMenuLightGreen"
			                   			</c:otherwise>
			               			</c:choose>
				    				>&nbsp;<spring:message code="systema.ncts.export.label"/>&nbsp;</font>
				    			</a>
				    			&nbsp;<font color="#FFFFFF"; style="font-weight: bold;">|</font>
				    			
				    			<%-- ---------------- --%>
				    			<%-- NCTS IMPORT MENU --%>
				    			<%-- ---------------- --%>
				    			<a tabindex=-1 href="nctsimport.do?action=doFind&sign=${user.tdsSign}">
				    				&nbsp;<font
				    				<c:choose>           
			                   			<c:when test="${user.activeMenu=='NCTS_IMPORT'}">
			                       			class="headerMenuMediumGreen"
			                   			</c:when>
			                   			<c:otherwise>   
			                       			class="headerMenuLightGreen"
			                   			</c:otherwise>
			               			</c:choose>
				    				>&nbsp;<spring:message code="systema.ncts.import.label"/>&nbsp;</font>
				    			</a>
				    			
				    			<%-- 
				    			<font class="headerMenuLightGreen">&nbsp;<spring:message code="systema.ncts.import.label"/>&nbsp;</font>
				    			--%> 
				    			
				    			<%-- ------------------------------- --%>
				    			<%-- SIGNERING MENU (if applicable)  --%>
				    			<%-- ------------------------------- --%>
				    			<c:if test="${user.authorizedTdsSignPkiUserAS400 == 'Y'}">
					    			<font color="#FFFFFF"; style="font-weight: bold;">&nbsp;|&nbsp;</font>
					    			<a tabindex=-1 href="tds_sign_pki.do">
					    				<font
						    				<c:choose>           
					                   			<c:when test="${user.activeMenu=='SIGN_PKI'}">
					                       			class="headerMenuMediumGreen"
					                   			</c:when>
					                   			<c:otherwise>   
					                       			class="headerMenuLightGreen"
					                   			</c:otherwise>
					               			</c:choose>
					    				>&nbsp;<spring:message code="systema.tds.sign.pki"/>&nbsp;
					    				</font>
					    			</a>
				    			</c:if>
				    			
				    			&nbsp;<font color="#FFFFFF"; style="font-weight: bold;">&nbsp;|&nbsp;</font>
				    			<%-- -------------------- --%>
				    			<%-- CHANGE PASSWORD MENU --%>
				    			<%-- -------------------- --%>
				    			<a tabindex=-1 href="tds_change_password.do">
				    				&nbsp;<font
				    				<c:choose>           
			                   			<c:when test="${user.activeMenu=='CHANGE_PASSWORD'}">
			                       			class="headerMenuMediumGreen"
			                   			</c:when>
			                   			<c:otherwise>   
			                       			class="headerMenuLightGreen"
			                   			</c:otherwise>
			               			</c:choose>
				    				>&nbsp;<spring:message code="systema.tds.change.password.label"/>&nbsp;</font>
				    			</a>
				    			
				    			&nbsp;<font color="#FFFFFF"; style="font-weight: bold;">&nbsp;|&nbsp;</font>
				    			<%-- -------------- --%>
				    			<%-- ADMIN  MENU    --%>
				    			<%-- -------------- --%>
				    			<a tabindex=-1 href="tdsadmin_transport.do">
				    				&nbsp;<font
				    				<c:choose>           
			                   			<c:when test="${user.activeMenu=='TDS_ADMIN'}">
			                       			class="headerMenuAdminOn"
			                   			</c:when>
			                   			<c:otherwise>   
			                       			class="headerMenuAdmin"
			                   			</c:otherwise>
		               			</c:choose>
		               			>&nbsp;&nbsp;<spring:message code="systema.tds.admin.label"/>&nbsp;&nbsp;</font>
				    			</a>
				    			&nbsp;<font color="#FFFFFF"; style="font-weight: bold;">|</font>
				    			<%-- ------------------- --%>
				    			<%-- Maintenance  MENU    --%>
				    			<%-- -------------------- --%>
				    			<a tabindex=-1 href="tdsmaintenancegate.do">
				    				&nbsp;<font class="headerMenuAdmin">
			                   		&nbsp;&nbsp;Underhåll&nbsp;</font>
				    			</a>
		      				</td>		      				
		      				<td class="text11"  align="right">
		      					<img valign="bottom" src="resources/images/countryFlags/Flag_SE.gif" height="12" border="0" alt="country">
		      					&nbsp;
		      					<font class="headerMenuGreen">
				    				<img src="resources/images/appUser.gif" border="0" onClick="showPop('specialInformationAdmin');" > 
							        <span style="position:absolute; left:100px; top:150px; width:1000px; height:400px;" id="specialInformationAdmin" class="popupWithInputText"  >
						           		<div class="text11" align="left">
						           			${activeUrlRPG}
						           			<br/><br/>
						           			<button name="specialInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('specialInformationAdmin');">Close</button> 
						           		</div>
						        		</span>	
				    				<font class="text11User"  >${user.user}&nbsp;</font>${user.usrLang}</font>
				    				<font color="#FFFFFF"; style="font-weight: bold;">&nbsp;|&nbsp;</font>
					    			<a tabindex=-1 href="logoutTds.do">
					    				<font class="headerMenuGreen"><img src="resources/images/home.gif" border="0">
					    					<font class="text11User"  ><spring:message code="dashboard.menu.button"/></font>
					    				</font>
					    			</a>
					    			<font color="#FFFFFF"; style="font-weight: bold;">&nbsp;|&nbsp;</font>
					    			<font class="text12LightGreen" style="cursor:pointer;"  onClick="showPop('versionInfo');">${user.versionSpring}&nbsp;</font>
			    				    <div class="text11" style="position: relative;" align="left">
										<span style="position:absolute; left:5px; top:30px; width:250px" id="versionInfo" class="popupWithInputText"  >	
						           			&nbsp;<b>${user.versionEspedsg}</b>
						           			<br/><br/>
						           			&nbsp;<a href="renderLocalLog4j.do" target="_blank">log4j</a>
						           			<br/><br/><br/>
						           			<button name="versionInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('versionInfo');">Close</button> 
						           		</span>
									</div>  
					    		</td>
		      				
				        </tr>
				     </table> 
				</td>
		    </tr>
	    </c:when>
	    
	    <c:otherwise>
	    		<tr>
				<td height="23" class="tabThinBorderLightSlateGray" width="100%" align="left" colspan="3"> 
	    			 <table width="1250" border="0" cellspacing="1" cellpadding="1">
					 	<tr >
				    		<td class="text11" width="50%" align="left" >&nbsp;&nbsp;</td>
	      				<td class="text11" width="50%" align="right">
	      					<img valign="bottom" src="resources/images/countryFlags/Flag_SE.gif" height="12" border="0" alt="country">
		      				&nbsp;
		      				<font class="headerMenuGreen">
				    				<img src="resources/images/appUser.gif" border="0" > 
								<font style="color:#000000" >${user.user}&nbsp;</font>${user.usrLang}
				    			</font>
				    			<font color="#FFFFFF"; style="font-weight: bold;">&nbsp;|&nbsp;</font>
				    			<a tabindex=-1 href="logoutTds.do">
				    				<font class="headerMenuGreen"><img src="resources/images/home.gif" border="0">&nbsp;
				    					<font style="color:#000000;" ><spring:message code="dashboard.menu.button"/>&nbsp;</font>
				    				</font>
				    			</a>
				    			<font color="#FFFFFF"; style="font-weight: bold;">&nbsp;&nbsp;|&nbsp;</font>
				    			<font class="text12LightGreen" style="cursor:pointer;" onClick="showPop('versionInfo');">${user.versionSpring}&nbsp;</font>
						        <span style="position:absolute; left:800px; top:105px; width:150px; height:50px;" id="versionInfo" class="popupWithInputText"  >
						           		<div class="text11" align="left">
						           			<b>${user.versionEspedsg}</b></br></br>
						           			<button name="versionInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('versionInfo');">Close</button> 
						           		</div>
						        </span>  
				    		</td>
		      	        </tr>
		      	     </table> 
				</td>
		    </tr>
			<tr class="text" height="20"><td></td></tr>
		    <tr>
				<td width="100%" align="left" >
					<form autocomplete='off' action="tdsgate.do" name="loginTdsForm" id="loginTdsForm" method="POST" > 
    			 		<table width="250" border="0" cellspacing="1" cellpadding="0">
    			 		<tr >
				    		<td colspan="2" class="text12" >&nbsp;
				    			<img onMouseOver="showPop('tdsBehorighet_info');" onMouseOut="hidePop('tdsBehorighet_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				    			<b>TDS Behörighetskontroll</b>
								<span style="position:absolute; left:300px; top:120px; width:250px; height:200px;" id="tdsBehorighet_info" class="popupWithInputText"  >
					           		<div class="text11" align="left">
					           			Denna behörighetskontrollen är nödvändig för att kunna använda både
					           			<ol>
					           				<li><b>TDS</b>&nbsp;Export/Import</li>
					           				<li><b>NCTS</b>&nbsp;Export/Import</li>	
										</ol>
										<br/>Det föreslagna användarid:et finns redan i den generella inloggningen till eSped.
										<br/>Kontakta din systemadministratör vid lösenordsproblem.
									</div>
								</span>				    		
				    		</td>
	      	        </tr>
	      	        <tr class="text" height="5"><td></td></tr>
				 	<tr >
				    		<td class="text12" >&nbsp;&nbsp;AnvändarId</td>
		      				<td class="text12" >
		      					<input readonly type="text" autocomplete='off' class="inputTextReadOnly" name=userAS400 id="userAS400" size="10" maxlength="10" value='${user.userAS400}'>	
				    		</td>
	      	        </tr>
	      	        <tr >
				    		<td class="text12" >&nbsp;&nbsp;Lösenord</td>
		      				<td class="text12" >
		      					<%-- this will ensure not to enable the brower to ask the user to remember the password --%>
		      					<input type="password" autocomplete='off' readonly onfocus="this.removeAttribute('readonly');" class="inputTextMediumBlue" name="pwAS400" id="pwAS400" size="10" maxlength="20" value="">	
				    		</td>
	      	        </tr>
	      	        <tr>
						<td>&nbsp;</td>
						<td align="left"><input class="inputFormLoginSubmitGreen" type="submit" value="<spring:message code="login.user.submit"/>" /></td>
					</tr>
	      	     	</table>
		      	    </form>  
				</td>
		    </tr>
		    <%-- Validation Error section --%>
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
		</c:otherwise>
	    </c:choose>
	    
	    <tr class="text" height="2"><td></td></tr>
		
		
		<%-- ------------------------------------
		Content after banner och header menu
		------------------------------------- --%>
		<tr>
    		<td width="100%" align="left" colspan="3"> 
    		     
     