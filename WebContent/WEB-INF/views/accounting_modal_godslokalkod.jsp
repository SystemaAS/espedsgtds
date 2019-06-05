<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerAccounting.jsp" />
<!-- =====================end header ==========================-->

<link href="resources/accounting.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript">
	"use strict";

	jq(document).ready(function() {
		getGodslokalkod('#selectGodslokalkod');
		
		jq('#godsLokalkodModal').modal({
			  backdrop: 'static'
		})
		
	});

</script>

<div class="container-fluid">

	<div class="modal fade" id="godsLokalkodModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-sm" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="modalLabel">Välj Godslokalkod</h5>
	      </div>
	      <div class="modal-body">
	      	<form action="accounting_list.do" name="glkformRecord" id="glkformRecord" method="POST">
				<div class="form-group">
					<label for="selectGodslokalkod" class="col-form-label-sm mb-0">Godslokalkod</label>
					<select class="form-control form-control-sm w-auto" name="selectGodslokalkod" id="selectGodslokalkod"></select>	
				</div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button disabled id="glkButton" type="button" class="btn btn-primary" data-dismiss="modal">Ok</button>
	      </div>
	    </div>
	  </div>
	</div>

</div>

