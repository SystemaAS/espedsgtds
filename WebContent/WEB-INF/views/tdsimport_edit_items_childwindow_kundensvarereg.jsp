<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTdsChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tdsimport_edit_items_childwindow_kundensvarereg.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			<img title="select" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			Sök kod
			</td>
		</tr>
		<tr>
		<td valign="top">
		
		  		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%" >
					<%-- 
					<tr>
					<td>
						<table>
						<form name="tvinnsadImportTolltariffForm" id="tvinnsadImportTolltariffForm" action="tvinnsadimport_edit_items_childwindow_tolltariff.do?action=doInit" method="post">
						<tr>
							<td class="text11">&nbsp;Varekod</td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="vkod" id="vkod" size="10" maxlength="10" value="${model.vkod}"></td>
							<td class="text11">&nbsp;</td>
	           				<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="search.label"/>'>
		           		</tr>
		           		
		           		</table>
					</td>
					</tr>
					--%> 
													           		
	           		<tr height="5"><td></td></tr>
					
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:30em;">
					<%-- this is the datatables grid (content)--%>
					<table id="kundensVareRegList" class="display compact cell-border" width="100%" >
						<thead>
						<tr style="background-color:#EEEEEE">
							<th class="text11" title="adunnr">&nbsp;Varunr&nbsp;</th>
		                    <th class="text11" title="adunnr">&nbsp;Varukod/Tulltaxa&nbsp;</th>
		                    <th class="text11" title="adembg">&nbsp;Beskrivning&nbsp;</th>
		                    <th class="text11" title="adembg">&nbsp;Ursp.land&nbsp;</th>
		                    <th class="text11" title="adembg">&nbsp;Kolli slag&nbsp;</th>
		                    <th class="text11" title="adunnr">&nbsp;Bruttovikt&nbsp;</th>
		                    <th class="text11" title="adunnr">&nbsp;Nettovikt&nbsp;</th>
		                    <th class="text11" title="adunnr">&nbsp;Varans pris&nbsp;</th>
		                </tr> 
		                </thead>
		                <tbody>
		                <c:forEach var="record" items="${model.kundensVareRegList}" varStatus="counter">    
			               <c:choose>           
			                   <c:when test="${counter.count%2==0}">
			                       <tr class="text11">
			                   </c:when>
			                   <c:otherwise>   
			                       <tr class="text11">
			                   </c:otherwise>
			               </c:choose>
			               <td nowrap style="cursor:pointer;" class="text11MediumBlue" id="sviw_knso${record.sviw_knso}@sviw_vasl${record.sviw_vasl}
			               		@sviw_vata${record.sviw_vata}@sviw_brut${record.sviw_brut}@sviw_neto${record.sviw_neto}
			               		@sviw_ulkd${record.sviw_ulkd}@sviw_fokd${record.sviw_fokd}@sviw_eup1${record.sviw_eup1}@sviw_kota${record.sviw_kota}
			               		@sviw_kosl${record.sviw_kosl}@sviw_godm${record.sviw_godm}@sviw_kono${record.sviw_kono}@sviw_ankv${record.sviw_ankv}
			               		@sviw_suko${record.sviw_suko}@sviw_sutx${record.sviw_sutx}@sviw_atin${record.sviw_atin}@sviw_fabl${record.sviw_fabl}
			               		@sviw_betk${record.sviw_betk}@sviw_komr${record.sviw_komr}@sviw_fnkd${record.sviw_fnkd}@sviw_bit1${record.sviw_bit1}
			               		@sviw_bii1${record.sviw_bii1}@sviw_call${record.sviw_call}">
			               		
               			   		<img title="select" valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;${record.sviw_knso}
			               </td>
		               	   <td class="text11">&nbsp;${record.sviw_vata}</td>
		               	   <td class="text11">&nbsp;${record.sviw_vasl}</td>
		               	   <td class="text11">&nbsp;${record.sviw_ulkd}</td>
		               	   <td class="text11">&nbsp;${record.sviw_kosl}</td>
		               	   
		               	   <td class="text11">&nbsp;${record.sviw_brut}</td>
		               	   <td class="text11">&nbsp;${record.sviw_neto}</td>
		               	   <td class="text11">&nbsp;${record.sviw_fabl}</td>
			            </tr> 
			            </c:forEach>
			            </tbody>
		            </table>
		            </td>
	           		</tr>
        			</table>
		
		</td>
		</tr>
	</table> 
