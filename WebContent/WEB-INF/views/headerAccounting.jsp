<!DOCTYPE html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
	<head>
		<meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		
		<title>eSpedsg - Tillfällig lagring</title>

		<link href="/espedsg2/resources/${user.cssEspedsg}?ver=${user.versionEspedsg}" rel="stylesheet" type="text/css"/>
		<link href="resources/accounting.css" rel="stylesheet" type="text/css"/>
		<link type="text/css" href="//cdn.datatables.net/1.10.19/css/jquery.dataTables.css" rel="stylesheet"/>
		<link type="text/css" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/overcast/jquery-ui.css" rel="stylesheet"/>
 		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">		
		<link rel="SHORTCUT ICON" type="image/png" href="resources/images/systema_logo.png"></link>
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.2/css/responsive.bootstrap4.css"/>
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/plug-ins/1.10.19/features/mark.js/datatables.mark.min.css"/>
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.5.6/css/buttons.dataTables.min.css"/>

		
		<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<script type="text/javascript" src="resources/js/jquery.blockUI.js"></script>
		<script type="text/javascript" src="/espedsg2/resources/js/systemaWebGlobal.js?ver=${user.versionEspedsg}"></script>
		<script type="text/javascript" src="//cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="resources/js/accounting.js?ver=${user.versionEspedsg}"></script>	
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
		<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore-min.js"></script>	
		<script type="text/javascript" src="https://cdn.datatables.net/responsive/2.2.2/js/dataTables.responsive.js"></script>
		<script type="text/javascript" src="https://cdn.jsdelivr.net/g/mark.js(jquery.mark.min.js)"></script>
		<script type="text/javascript" src="https://cdn.datatables.net/plug-ins/1.10.19/features/mark.js/datatables.mark.js"></script>
		<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.6/js/dataTables.buttons.min.js"></script>
		<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.flash.min.js"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
		<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.html5.min.js"></script>
		<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.print.min.js"></script>
		<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.6/js/buttons.colVis.min.js"></script>
		
		<script>
			var lang = '${user.usrLang}';
		  	var svlthUrl = "/syjservicesbcore/syjsSVLTH?user=${user.user}";
		  	var kollislagUrl = '/syjservicesbcore/syjsSVTX03F.do?user=${user.user}&02=KLI'
		</script>

	</head>
	
	<input type="hidden" name="language" id=language value="${user.usrLang}">
	
   <table class="noBg" width="100%" border="0" cellspacing="0" cellpadding="0">
		<%--Banner --%>
	 	<tr>
	 		 <%-- class="grayTitanBg" --%>
    		<td height="60" class="headerTdsBannerAreaBg" width="100%" align="left" colspan="3"> 
    			 <table width="100%" border="0" cellspacing="0" cellpadding="0">
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
											<img src="${user.logo}" border="0" >
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
					 		
				 		<td class="text32Bold" width="100%" align="middle" valign="middle" style="color:#778899;" > 
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
				<td height="23" class="tabThinBorderLightGreenLogoutE2" width="100%" align="left" colspan="3"> 
	    			 <table width="100%" border="0" cellspacing="0" cellpadding="0">
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
				    			&nbsp;<font color="#FFFFFF"; style="font-weight: bold;">|</font>

				    			<%-- -------------------------%>
				    			<%-- TILLFÄLLIG LAGRING MENU --%>
				    			<%-- -------------------------%>
				    			<a tabindex=-1 href="accounting_list.do">
				    				&nbsp;<font
				    				<c:choose>           
			                   			<c:when test="${user.activeMenu=='TDS_ACCOUNTING'}">
			                       			class="headerMenuMediumGreen"
			                   			</c:when>
			                   			<c:otherwise>   
			                       			class="headerMenuLightGreen"
			                   			</c:otherwise>
			               			</c:choose>
				    				>&nbsp;Tillfällig lagring&nbsp;</font>
				    			</a>
				    			
				    			<%-- 
				    			<font class="headerMenuLightGreen">&nbsp;<spring:message code="systema.ncts.import.label"/>&nbsp;</font>
				    			--%> 
				    			
				    			<%-- ------------------------------- --%>
				    			<%-- SIGNERING MENU (if applicable)  --%>
				    			<%-- ------------------------------- 
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
				    			<%-- -------------------- 
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
				    			--%>
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
		      				<td class="text14"  align="right">
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
				    				<font class="text14User"  >${user.user}&nbsp;</font>${user.usrLang}</font>
				    				<font color="#FFFFFF"; style="font-weight: bold;">&nbsp;|&nbsp;</font>
					    			<a tabindex=-1 href="logout.do">
					    				<font class="headerMenuGreen"><img src="resources/images/home.gif" border="0">
					    					<font class="text14User" onMouseOver="style='color:lemonchiffon;'" onMouseOut="style='color:black;'" ><spring:message code="dashboard.menu.button"/></font>
					    				</font>
					    			</a>
					    			<font color="#FFFFFF"; style="font-weight: bold;">&nbsp;|&nbsp;</font>
					    			<font class="text12LightGreen" style="cursor:pointer;"  onClick="showPop('versionInfo');">${user.versionSpring}&nbsp;</font>
			    				    <div class="text12" style="position: relative;display: inline;" align="left">
										<span style="position:absolute; left:-150px; top:3px;" id="versionInfo" class="popupWithInputText"  >
							           		<div class="text12" align="left">
							           			<b>${user.versionEspedsg}</b>
							           			<p>
							           				&nbsp;<a id="alinkLog4jLogger" ><font class="text14LightGreen" style="cursor:pointer;">log4j</font></a><br/>
							           			</p>
							           			<button name="versionInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('versionInfo');">Close</button> 
							           		</div>
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
				<td height="23" class="tabThinBorderLightGreenLogoutE2" width="100%" align="left" colspan="3"> 
	    			 <table width="100%" border="0" cellspacing="1" cellpadding="1">
					 	<tr >
				    		<td class="text14" width="50%" align="left" >&nbsp;&nbsp;</td>
	      				<td class="text14" width="50%" align="right">
	      					<img valign="bottom" src="resources/images/countryFlags/Flag_SE.gif" height="12" border="0" alt="country">
		      				&nbsp;
		      				<font class="headerMenuGreen">
				    				<img src="resources/images/appUser.gif" border="0" > 
								<font style="color:#000000" >${user.user}&nbsp;</font>${user.usrLang}
				    			</font>
				    			<font color="#FFFFFF"; style="font-weight: bold;">&nbsp;|&nbsp;</font>
				    			<a tabindex=-1 href="logout.do">
				    				<font class="headerMenuGreen"><img src="resources/images/home.gif" border="0">&nbsp;
				    					<font class="text14User" onMouseOver="style='color:lemonchiffon;'" onMouseOut="style='color:black;'" ><spring:message code="dashboard.menu.button"/>&nbsp;</font>
				    				</font>
				    			</a>
				    			<font color="#FFFFFF"; style="font-weight: bold;">&nbsp;&nbsp;|&nbsp;</font>
				    			<font class="text12LightGreen" style="cursor:pointer;" onClick="showPop('versionInfo');">${user.versionSpring}&nbsp;</font>
						       <div class="text11" style="position: relative;display: inline;" align="left">
								<span style="position:absolute; left:-150px; top:3px; width:150;" id="versionInfo" class="popupWithInputText"  >
									<div class="text11" align="left">
					           			<b>${user.versionEspedsg}</b></br></br>
					           			<button name="versionInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('versionInfo');">Close</button> 
						           	</div>
								</span>
								</div> 
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
				    		<td colspan="2" class="text14" >&nbsp;
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
				    		<td class="text14" >&nbsp;&nbsp;AnvändarId</td>
		      				<td class="text14" >
		      					<input readonly type="text" autocomplete='off' class="inputTextReadOnly" name=userAS400 id="userAS400" size="10" maxlength="10" value='${user.userAS400}'>	
				    		</td>
	      	        </tr>
	      	        <%--
	      	        <tr >
				    		<td class="text14" >&nbsp;&nbsp;Lösenord</td>
		      				<td class="text14" >
		      					<%-- this will ensure not to enable the brower to ask the user to remember the password
		      					<input type="password" autocomplete='off' readonly onfocus="this.removeAttribute('readonly');" class="inputTextMediumBlue" name="pwAS400" id="pwAS400" size="10" maxlength="20" value="">	
				    		</td>
	      	        </tr>
	      	         --%>
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
	    
	    <%-- ------------------------- --%>
		<%-- DIALOG render log4j.log   --%>
		<%-- ------------------------- --%>
		<tr>
		<td>
			<div id="dialogLogger" title="Dialog" style="display:none">
				<form>
			 	<table>
			 		<tr>
						<td colspan="3" class="text14" align="left" >Password</td>
  						</tr>
					<tr >
						<td>
							<input type="password" class="inputText" id="pwd" name="pwd" size="15" maxlength="15" value=''>
						</td>
					</tr>
  						<tr height="10"><td></td></tr>
					<tr>
						<td colspan="3" class="text14MediumBlue" align="left">
							<label id="loggerStatus"></label>
						</td>
					</tr>
				</table>
				</form>
			</div>
		</td>
		</tr>
		
	    
</table>
    		     

	
