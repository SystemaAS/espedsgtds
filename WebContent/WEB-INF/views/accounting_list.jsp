<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerAccounting.jsp" />
<!-- =====================end header ==========================-->

<link href="resources/accounting.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript">
	"use strict";

	jq(document).ready(function() {
		initSvlthSearch();
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
		    Skapa&nbsp;nytt&nbsp;inlägg
		  </span>	
		</a>
	  </div>
	</nav>

	<div class="padded-row-small left-right-border"></div>	

	<div class="form-row left-right-border">

		<div class="form-group pr-2 pl-1">
			<label for="selectGodsnr" class="col-form-label-sm mb-0">Godsnummer</label>
			<input type="text" class="form-control form-control-sm" id="selectGodsnr"  size="17"  maxlength="15">
		</div>

		<div class="form-group pr-2">
			<label for="selectMrn" class="col-form-label-sm mb-0">MRN</label>
			<input type="text" class="form-control form-control-sm" id="selectMrn" size="20" maxlength="18"/>
		</div>

		<div class="form-group pr-2">
			<label for="selectArrival" class="col-form-label-sm mb-0">Från ankomstdatum</label>
			<input type="text" class="form-control form-control-sm" id="selectArrival"  size="10"  maxlength="8">
		</div>

		<div class="form-group col-2 align-self-end">
			<div class="float-md-right">
				<button class="btn inputFormSubmit btn-sm" onclick="loadSvlth()" id="submitBtn"  autofocus>Sök</button>
			</div>
		</div>	

	</div>

	<div class="left-right-bottom-border no-gutters">
		<table class="display compact cell-border responsive nowrap" id="svlthTable">
			<thead class="tableHeaderField">
				<tr>
					<th>Visa</th>
					<th>Godsnummer</th>
					<th>Händelse</th>
					<th>Antal</th>
					<th>Saldo</th>
					<th>Extern&nbsp;referans</th>
					<th>MRN</th>
					<th>Arkiverad</th>
					<th>Ankomstdatum</th>
					<th>Uttagsdatum</th>
					<th>Godslokalkod</th>
					<th>Kollislag</th>
					<th>Bruttovikt</th>
					<th>Varubeskrivning</th>
					<th>Rättad händelse</th>
					<th>Rättat antal</th>
					<th>Rättelse fritext</th>
					<th>Tidigare&nbsp;handling-1</th>
					<th>Tidigare&nbsp;handling-2</th>
					<th>Tidigare&nbsp;handling-3</th>
					<th>Tidigare&nbsp;handling-4</th>
					<th>Tidigare&nbsp;handling-5</th>
					<th>Intern fritext</th>
					<th>Arkiveringsdatum</th>
					<th>Rättelse</th>					
				</tr>
			</thead>
		</table>
	</div>	

</div>

