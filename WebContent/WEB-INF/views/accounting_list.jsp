<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerAccounting.jsp" />
<!-- =====================end header ==========================-->

<link href="resources/accounting.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript">
	"use strict";
	var signatur = "${user.signatur}";
	var attkode = "&attkode="+signatur;
	
	jq(document).ready(function() {

		initSvlthSearch();
		
// 		jq('#selectAttkode').append('<option selected="true">${user.signatur}</option>');
// 		jq('#selectAttkode').prop('selectedIndex', '${user.signatur}');			
		
// 		getAttKode('#selectAttkode');
		
	});

</script>

<div class="container-fluid">

	<div class="padded-row-small"></div>	

	<nav>
	  <div class="nav nav-tabs" id="nav-tab" role="tablist">
	    <a class="nav-item nav-link img-list active disabled no-gutters">
		  <span class="navbar-text no-gutters pb-0 pt-0">
		    Bokföring
		  </span>	
	    </a>
		<a class="nav-item nav-link nav-new no-gutters" onClick="setBlockUI();" href="accounting_inlagg.do?action=1">
		  <span class="navbar-text no-gutters pb-0 pt-0">
		    Skapa nytt inlägg
		  </span>	
		</a>
	  </div>
	</nav>

	<div class="padded-row-small left-right-border"></div>	

	<div class="form-row left-right-border">
		<div class="form-group pr-2 col-1">
			<label for="selectMrn" class="col-form-label-sm mb-0">MRN</label>
			<input type="text" class="form-control form-control-sm" id="selectMrn" size="20" maxlength="18"/>
		</div>

		<div class="form-group pr-2 col-1">
			<label for="selectArrival" class="col-form-label-sm mb-0">Ankomstdatum</label>
			<input type="text" class="form-control form-control-sm" id="selectArrival"  size="13"  maxlength="13">
		</div>

		<div class="form-group pr-2 col-1">
			<label for="selectRegdate" class="col-form-label-sm mb-0">Arkiveringsdatum</label>
			<input type="text" class="form-control form-control-sm" id="selectRegdate"  size="13"  maxlength="13">
		</div>

		<div class="form-group col-2 align-self-end">
			<div class="float-md-right">
				<button class="btn inputFormSubmit btn-sm" onclick="loadSvlth()" id="submitBtn"  autofocus>Søk</button>
			</div>
		</div>	

	</div>

	


	<div class="left-right-bottom-border no-gutters">
		<table class="display compact cell-border responsive nowrap" id="svlthTable">
			<thead class="tableHeaderField">
				<tr>
					<th>Godslokalkod</th>
					<th>Inlägg</th>
					<th>Extern referans</th>
					<th>MRN</th>
					<th>Godsnummer</th>
					<th>Arkiveringsdatum</th>
					<th>Ankomstdatum</th>
					<th>Tidigare handling-1</th>
					<th>Tidigare handling-2</th>
					<th>Tidigare handling-3</th>
					<th>Tidigare handling-4</th>
					<th>Tidigare handling-5</th>
					<th>Antal</th>
					<th>Kollislag</th>
					<th>Bruttovikt</th>
					<th>Varubeskrivning</th>
					<th>Intern fritext</th>
				</tr>
			</thead>
		</table>
	</div>	

</div>

