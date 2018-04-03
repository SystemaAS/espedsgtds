<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTdsChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/tdsexport_edit_items_childwindow_kundensvarereg.js?ver=${user.versionEspedsg}"></SCRIPT>
	
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
							<th class="text11" title="svew_knso">&nbsp;Varunr&nbsp;</th>
		                    <th class="text11" title="svew_vata">&nbsp;Varukod/Tulltaxa&nbsp;</th>
		                    <th class="text11" title="svew_vasl">&nbsp;Beskrivning&nbsp;</th>
		                    <th class="text11" title="svew_ulkd">&nbsp;Ursp.land&nbsp;</th>
		                    <th class="text11" title="svew_kosl">&nbsp;Kolli slag&nbsp;</th>
		                    <th class="text11" title="svew_brut">&nbsp;Bruttovikt&nbsp;</th>
		                    <th class="text11" title="svew_neto">&nbsp;Nettovikt&nbsp;</th>
		                    <th class="text11" title="svew_fabl">&nbsp;Varans pris&nbsp;</th>
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
			               <td nowrap style="cursor:pointer;" class="text11MediumBlue" id="svew_knso${record.svew_knso}@svew_vasl${record.svew_vasl}
			               		@svew_vata${record.svew_vata}@svew_brut${record.svew_brut}@svew_neto${record.svew_neto}
			               		@svew_ulkd${record.svew_ulkd}@svew_fokd${record.svew_fokd}@svew_eup1${record.svew_eup1}@svew_kota${record.svew_kota}
			               		@svew_kosl${record.svew_kosl}@svew_godm${record.svew_godm}@svew_kono${record.svew_kono}@svew_ankv${record.svew_ankv}
			               		@svew_suko${record.svew_suko}@svew_sutx${record.svew_sutx}@svew_atin${record.svew_atin}@svew_fabl${record.svew_fabl}
			               		@svew_betk${record.svew_betk}@svew_komr${record.svew_komr}@svew_fnkd${record.svew_fnkd}@svew_bit1${record.svew_bit1}
			               		@svew_bii1${record.svew_bii1}@svew_call${record.svew_call}">
			               		
               			   		<img title="select" valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;${record.svew_knso}
			               </td>
		               	   <td class="text11">&nbsp;${record.svew_vata}</td>
		               	   <td class="text11">&nbsp;${record.svew_vasl}</td>
		               	   <td class="text11">&nbsp;${record.svew_ulkd}</td>
		               	   <td class="text11">&nbsp;${record.svew_kosl}</td>
		               	   
		               	   <td class="text11">&nbsp;${record.svew_brut}</td>
		               	   <td class="text11">&nbsp;${record.svew_neto}</td>
		               	   <td class="text11">&nbsp;${record.svew_fabl}</td>
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
