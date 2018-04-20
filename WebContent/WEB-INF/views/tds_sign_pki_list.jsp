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
			<td width="30%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLinkSign">&nbsp;<spring:message code="systema.tds.sign.pki.list.tab"/></font>
				<img title="30 min. timeout" valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
				&nbsp;<font style="font-style:italic">[${timeElapsedInMinutes}&nbsp;min]</font>
			</td>
			<td width="70%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
		</tr>
	</table>
	</td>
</tr>
<%-- separator space within the tab and before the matrix list --%>
<tr>
	<td>
	<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
	 	        <tr height="30"><td>&nbsp;</td></tr>
	</table>
	</td>
</tr> 	    

<%-- list component --%>
<tr>
	<td>
	<c:if test="${not empty list}">
	<tr>
		<td>		
		<table width="100%" cellspacing="0" border="0" cellpadding="0">
	    	<%-- separator --%>
	        <tr height="1"><td></td></tr> 
			<tr>
				<td>
				<table width="100%" cellspacing="0" border="0" cellpadding="0">
					<thead>
					<tr class="tableHeaderField" height="20" valign="left">
	                    <th class="tableHeaderFieldFirst">&nbsp;<spring:message code="systema.tds.sign.pki.list.topic.column.label.avd"/>&nbsp;</th>   
	                    <th class="tableHeaderField" >&nbsp;<spring:message code="systema.tds.sign.pki.list.topic.column.label.signatur"/>&nbsp;</th>
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.tds.sign.pki.list.topic.column.label.arende"/>&nbsp;</th>
	                    <th class="tableHeaderField" >&nbsp;<spring:message code="systema.tds.sign.pki.list.topic.column.label.refId"/>&nbsp;</th>
	                    <th class="tableHeaderField" >&nbsp;<spring:message code="systema.tds.sign.pki.list.topic.column.label.datum"/>&nbsp;</th>
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.tds.sign.pki.list.topic.column.label.status"/>&nbsp;</th>
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.tds.sign.pki.list.topic.column.label.partnerName"/>&nbsp;</th>
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.tds.sign.pki.list.topic.column.label.mtyp"/>&nbsp;</th>
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.tds.sign.pki.list.topic.column.label.ursprung"/>&nbsp;</th>
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.tds.sign.pki.list.topic.column.label.godkann"/>&nbsp;</th>
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.tds.sign.pki.list.topic.column.label.aterkalla"/>&nbsp;</th>
	                    
	               </tr> 
	               </thead>
	               
	               <form  name="signOrRevokeTopicForm" id="signOrRevokeTopicForm" method="post">
	               <tbody>
		           		<c:forEach items="${list}" var="topic" varStatus="counter"> 
		           			<input type="hidden" name="refid_${counter.count}" value='<c:out value="${topic.refid}"/>' />
		           			<input type="hidden" name="syavd_${counter.count}" value='<c:out value="${topic.syav}"/>' />
		           			<input type="hidden" name="syop_${counter.count}" value='<c:out value="${topic.syop}"/>' />
		           			<input type="hidden" name="ursp_${counter.count}" value='<c:out value="${topic.ursp}"/>' />
		           			
		           		   <c:choose>           
			                   <c:when test="${counter.count%2==0}">
			                       <tr class="tableRow" height="20" >
			                   </c:when>
			                   <c:otherwise>   
			                       <tr class="tableOddRow" height="20" >
			                   </c:otherwise>
			               </c:choose>
			               <td class="tableCellFirst" width="5%">&nbsp;${topic.syav}</td>
			               <td class="tableCell" >&nbsp;${topic.sysg}</td>
			               <td class="tableCell" >
			               <a href="tds_sign_pki_renderEdifact.do?fp=${topic.url}" target="_new" >
			               		<img src="resources/images/list.gif" border="0" width="12px" height="12px" alt="Visa Edifact" >
			               		&nbsp;${topic.syop}
	               		   </a>
			               </td>
			               <td class="tableCell" >&nbsp;${topic.refid}</td>
			               <td class="tableCell" >&nbsp;${topic.sydt}</td>
			               <td class="tableCell" >&nbsp;${topic.syst}</td>
			               <td class="tableCell" >&nbsp;${topic.namn}</td>
			               <td class="tableCell" >&nbsp;${topic.mtyp}</td>
			               <td class="tableCell" >&nbsp;${topic.urspDecoded}</td>
			               <td class="tableCell" align="center" >
	               				<input type="radio" name="signGrp_${counter.count}" id="signGrp_${counter.count}" value="approved" ><br>
			               </td>
			               <td class="tableCell" align="center" >
			               		<input type="radio" name="signGrp_${counter.count}" id="signGrp_${counter.count}" value="revoked"><br>
			               </td>
			            </tr> 
			            <c:set var="nrOfRecordsInPage" value="${counter.count}" scope="request" />
			            </c:forEach>
			           </tbody> 
			            <input type="hidden" name="nrOfRecordsInPage" value='<c:out value="${nrOfRecordsInPage}"/>' />
		               
	            	</table>
				</td>	
			</tr>
			<tr height="2"><td>&nbsp;</td></tr>
			<tr>
				<td align="right">
					<input TABINDEX=-1 class="inputFormSubmit" type="submit" name="submit" onclick="javascript: form.action='tds_sign_pki_approve_revoke.do';" value='Utför'/>&nbsp;
					<input TABINDEX=-1 class="inputFormSubmit" type="submit" name="send" onclick="javascript: form.action='tds_sign_pki_list.do';" value='Nollställ'/>
				</td>
			</tr>
			</form>
			
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
    </c:if> 
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

