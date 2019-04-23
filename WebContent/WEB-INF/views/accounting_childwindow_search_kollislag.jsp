<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>


<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerAccountingChildWindow.jsp" />
<!-- =====================end header ==========================-->

<link href="resources/accounting.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript">
	"use strict";
 	let caller = "#${model.caller}";
	jq(document).ready(function() {
		initSvtx03fSearch(caller);
	});	
</script>

<div class="container-fluid">

<!--  
	<div class="form-row">
		<div class="form-group pr-2 pl-1">
			<label for="selectLevnr" class="col-form-label-sm mb-0">Id</label>
			<input type="text" class="form-control form-control-sm" id="selectId" size="12" maxlength="10">	
		</div>

		<div class="form-group pr-2 pl-1">
			<label for="selectLnavn" class="col-form-label-sm mb-0">Beskrivning</label>
			<input type="text" class="form-control form-control-sm" id="selectBeskrivning" size="50" maxlength="250" autofocus>	
		</div>

		<div class="form-group col-2 align-self-end">
			<div class="float-md-right">
				<button class="btn inputFormSubmit" onclick="loadSvtx03f();" id="submitBtn">Sök</button>
			</div>
		</div>
	</div>
-->

	<div>
		<table class="display compact cell-border responsive nowrap" id="svtx03fTable">
			<thead class="tableHeaderField">
				<tr>
					<th>Id</th>
					<th>Välj</th>					
					<th>Beskrivning</th>
				</tr>
			</thead>
		</table>
	</div>
  
</div>