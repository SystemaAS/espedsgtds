<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerAccountingArchive.jsp" />
<!-- =====================end header ==========================-->

<link href="resources/accounting.css" rel="stylesheet" type="text/css"/>


<div class="container-fluid">

	<div class="padded-row-small"></div>	

	<nav>
	  <div class="nav nav-tabs" id="nav-tab" role="tablist">
	    <a class="nav-item nav-link img-list no-gutters" onClick="setBlockUI();" href="accounting_list.do">
		  <span class="navbar-text no-gutters pb-0 pt-0">
		    Bokföring
		  </span>
	    </a>
		<a class="nav-item nav-link nav-new no-gutters" onClick="setBlockUI();" href="accounting_inlagg.do?action=1">
		  <span class="navbar-text no-gutters pb-0 pt-0">
		    Skapa&nbsp;nytt&nbsp;inlägg
		  </span>	
		</a>
		<a class="nav-item nav-link nav-archive active disabled no-gutters">
		  <span class="navbar-text no-gutters pb-0 pt-0">
		    Arkiv
		  </span>	
		</a>
		<a class="img-help justify-content-end ml-auto" href="resources/files/Accounting.pdf" target="_blank">	
		  <span class="navbar-text no-gutters pb-0 pt-0">
		    &nbsp;Hjälp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		  </span>
		</a>		
	  </div>
	  
	</nav>

	<div class="padded-row-small left-right-border"></div>
	<div class="left-right-border">
		<label class="inputTextMediumBlue11 isa_warning">
			Gäller endast för Tullverkets Övergångslösning
		</label>
	</div>
	<form name="searchForm" id="searchForm" action="accounting_archive.do" method="post" >
	<input type="hidden" name="language" id="language" value='${user.usrLang}'>	
	<div class="form-row left-right-border">
		<div class="form-group pr-2 pl-1 mb-0">
			<label for="godslokalkod" class="col-form-label-sm mb-0">Godslokalkod</label>
			<input type="text" class="form-control-plaintext form-control form-control-sm" id="godslokalkod" value="${godslokalkod}">
		</div>

		<div class="form-group pr-2 pl-1">
			<label title="arrfk" for="selectGodsnr" class="col-form-label-sm mb-0">Godsnummer</label>
			<input type="text" class="form-control form-control-sm" id="arrfk" name="arrfk"  size="17"  maxlength="15" value="${record.arrfk}" />
		</div>

		<div class="form-group pr-2">
			<label title="arbhis" for="selectMrn" class="col-form-label-sm mb-0">MRN</label>
			<input type="text" class="form-control form-control-sm" id="arbhis" name="arbhis" size="20" maxlength="18" value="${record.arbhis}"/>
		</div>

		<div class="form-group pr-2">
			<label title="ardate" for="selectArrivalFrom" class="col-form-label-sm mb-0">F.o.m&nbsp;ankomstdatum</label>
			<input type="text" class="form-control form-control-sm" id="ardate" name="ardate"  size="11"  maxlength="8" value='<c:if test="${record.ardate!='0'}">${record.ardate}</c:if>'/>
		</div>

		<div class="form-group pr-2">
			<label title="own_ardateTo" for="selectArrivalTo" class="col-form-label-sm mb-0">T.o.m&nbsp;ankomstdatum</label>
			<input type="text" class="form-control form-control-sm" id="own_ardateTo" name="own_ardateTo"  size="11"  maxlength="8" value='<c:if test="${record.ardate!='0'}">${record.own_ardateTo}</c:if>'/>
		</div>


		<div class="form-group col-2 align-self-end">
			<div class="float-md-right">
				<button class="btn inputFormSubmit btn-sm" id="submitBtn"  autofocus>Sök</button>
			</div>
		</div>	
		
		
	</div>
	</form>
	
	<div class="left-right-bottom-border no-gutters">
		<table id="mainList" class="display compact cell-border" width="100%">
			<thead class="tableHeaderField">
				<tr>
					<th>Godsnummer</th>
					<th>Pos</th>
					<th>MRN</th>
					<th>PDF</th>
					<th>PDF-Arkiverad</th>
					<th>Firma</th>
				</tr>
			</thead>
			<tbody> 
	           	<c:forEach items="${list}" var="record" varStatus="counter">    
		       	<tr class="tableRow" height="20" >
	          	   <td width="2%" class="tableCellFirst">${record.arrfk}</td>
	          	   <td width="2%" class="tableCell">${record.arunde}</td>
	          	   <td width="2%" class="tableCell">${record.arbhis}</td>
	          	   <td width="2%" class="tableCell">
	          	   		<a href="accounting_renderArchive.do?fp=${pdfDir}${record.arlink}" target="_new" >
			               		<img src="resources/images/pdf.png" border="0" width="16px" height="16px" alt="Visa arkivdokument" >
			               		${record.arlink}
	               		</a>
	          	   </td>
	          	   <td width="2%" class="tableCell">${record.ardate}-${record.artime}</td>
	          	   <td width="2%" class="tableCell">${record.arfirm}</td> 	   
        		 	</tr>
        		 	</c:forEach>
     		 </tbody>
		</table>
	</div>	

</div>

