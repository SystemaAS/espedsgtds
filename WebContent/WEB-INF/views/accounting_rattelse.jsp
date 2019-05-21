<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerAccounting.jsp" />
<!-- =====================end header ==========================-->

<link href="resources/accounting.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript">
	"use strict";
	
	var h_svlth_h = "${headRecord.svlth_h}";
	var h_svlth_ign = "${headRecord.svlth_ign}";
	var h_svlth_pos = "${headRecord.svlth_pos}";
	var h_svlth_id1 = "${headRecord.svlth_id1}";
	var h_svlth_im1 = "${headRecord.svlth_im1}";
	
	jq(document).ready(function() {
		loadEvent();
	});
</script>

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
		<a class="nav-item nav-link nav-new active disabled">
	      <span class="navbar-text no-gutters pb-0 pt-0">
		    Rättelse
		  </span>		
		</a>
	  </div>
	</nav>


	<div class="padded-row-small left-right-border"></div>
		<div class="container-fluid p-1 left-right-border">
	
			<div class="form-row left-right-bottom-border formFrameHeader">
				<div class="col-sm-12">
					<span class="rounded-top">Registrerad&nbsp;händelse</span>
				</div>
			</div>	
		
			<div class="form-row no-gutters left-right-border">
				<div class="form-group pr-2 col-2">
					<label for="event" class="col-form-label-sm mb-0 pb-0">Händelse</label>
					<label class="form-control-plaintext form-control-sm font-weight-bold pt-0 pb-0" id="event"/>
				</div>
				<div class="form-group pr-2 col-2">
					<label for="mrn" class="col-form-label-sm mb-0 pb-0">MRN</label>
					<label class="form-control-plaintext form-control-sm font-weight-bold pt-0 pb-0" id="mrn"/>
				</div>
				<div class="form-group pr-2 col-2">
					<label for="godsnr" class="col-form-label-sm mb-0 pb-0">Godsnummer</label>
					<label class="form-control-plaintext form-control-sm font-weight-bold pt-0 pb-0" id="godsnr"/>
				</div>	
				<div class="form-group pr-2 col-2">
					<label for="position" class="col-form-label-sm mb-0 pb-0">Position</label>
					<label class="form-control-plaintext form-control-sm font-weight-bold pt-0 pb-0" id="position"/>
				</div>	
				<div class="form-group pr-2 col-2">
					<label for="antal" class="col-form-label-sm mb-0 pb-0">Räknat&nbsp;antal</label>
					<label class="form-control-plaintext form-control-sm font-weight-bold pt-0 pb-0" id="antal"/>
				</div>	
				<div class="form-group pr-2 col-1">
					<label for="saldo" class="col-form-label-sm mb-0 pb-0">Saldo</label>
					<label class="form-control-plaintext form-control-sm font-weight-bold pt-0 pb-0" id="saldo"/>
				</div>
			</div>
	</div>
	
		<div class="panel-body left-right-border no-gutters">
		<table class="display compact cell-border responsive nowrap" id="rattelseTable">
			<thead class="tableHeaderField">
				<tr>
					<th>Nytt&nbsp;räknat&nbsp;antal</th>
					<th>Intern&nbsp;information</th>
					<th>Arkiverad</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<form action="accounting_rattelse.do" name="formRecord" id="formRecord" method="POST">
		<input type="hidden" name="action" id="action" value='1'>
		<input type="hidden" name="svlth_h" id="svlth_h" value='R'>
		<input type="hidden" name="svlth_igl" id="svlth_igl" value='${headRecord.svlth_igl}'>
		<input type="hidden" name="svlth_iex" id="svlth_iex" value='${headRecord.svlth_iex}'>
		<input type="hidden" name="svlth_irn" id="svlth_irn" value='${headRecord.svlth_irn}'>
		<input type="hidden" name="svlth_ign" id="svlth_ign" value='${headRecord.svlth_ign}'>
		<input type="hidden" name="svlth_pos" id="svlth_pos" value='${headRecord.svlth_pos}'>
		<input type="hidden" name="svlth_id1" id="svlth_id1" value='${headRecord.svlth_id1}'>
		<input type="hidden" name="svlth_im1" id="svlth_im1" value='${headRecord.svlth_im1}'>
		<input type="hidden" name="svlth_id2" id="svlth_id2" value='${headRecord.svlth_id2}'>
		<input type="hidden" name="svlth_itx" id="svlth_itx" value='${headRecord.svlth_itx}'>
		<input type="hidden" name="svlth_ih1" id="svlth_ih1" value='${headRecord.svlth_ih1}'>
		<input type="hidden" name="svlth_ih2" id="svlth_ih2" value='${headRecord.svlth_ih2}'>
		<input type="hidden" name="svlth_ih3" id="svlth_ih3" value='${headRecord.svlth_ih3}'>
		<input type="hidden" name="svlth_ih4" id="svlth_ih4" value='${headRecord.svlth_ih4}'>
		<input type="hidden" name="svlth_ih5" id="svlth_ih5" value='${headRecord.svlth_ih5}'>
		<input type="hidden" name="svlth_int" id="svlth_int" value='${headRecord.svlth_int}'>
		<input type="hidden" name="svlth_isl" id="svlth_isl" value='${headRecord.svlth_isl}'>
		<input type="hidden" name="svlth_ibr" id="svlth_ibr" value='${headRecord.svlth_ibr}'>
		<input type="hidden" name="svlth_ivb" id="svlth_ivb" value='${headRecord.svlth_ivb}'>
		<input type="hidden" name="svlth_ivb2" id="svlth_ivb2" value='${headRecord.svlth_ivb2}'>
		<input type="hidden" name="svlth_ivb3" id="svlth_ivb3" value='${headRecord.svlth_ivb3}'>
		<input type="hidden" name="svlth_ivb4" id="svlth_ivb4" value='${headRecord.svlth_ivb4}'>
		<input type="hidden" name="svlth_ivb5" id="svlth_ivb5" value='${headRecord.svlth_ivb5}'>
		<input type="hidden" name="svlth_rty" id="svlth_rty" value='${headRecord.svlth_h}'>
		<input type="hidden" name="svlth_uti" id="svlth_uti"  value='${headRecord.svlth_uti}'>
		<input type="hidden" name="svlth_uex" id="svlth_uex"  value='${headRecord.svlth_uex}'>
		<input type="hidden" name="svlth_utx" id="svlth_utx"  value='${headRecord.svlth_utx}'>
		<input type="hidden" name="svlth_ud1" id="svlth_ud1"  value='${headRecord.svlth_ud1}'>
		<input type="hidden" name="svlth_um1" id="svlth_um1"  value='${headRecord.svlth_um1}'>
		
		<!-- Head -->		
		<input type="hidden" name="h_svlth_h" id="h_svlth_h"  value='${headRecord.svlth_h}'>
		<input type="hidden" name="h_svlth_ign" id="h_svlth_ign"  value='${headRecord.svlth_ign}'>
		<input type="hidden" name="h_svlth_pos" id="h_svlth_pos"  value='${headRecord.svlth_pos}'>
		<input type="hidden" name="h_svlth_id1" id="h_svlth_id1"  value='${headRecord.svlth_id1}'>
		<input type="hidden" name="h_svlth_im1" id="h_svlth_im1"  value='${headRecord.svlth_im1}'>
		<input type="hidden" name="h_svlth_ud1" id="h_svlth_ud1"  value='${headRecord.svlth_ud1}'>
		<input type="hidden" name="h_svlth_um1" id="h_svlth_um1"  value='${headRecord.svlth_um1}'>


		<div class="container-fluid p-1 left-right-bottom-border">

			<div class="form-row left-right-border formFrameHeader">
				<div class="col-sm-12">
					<span class="rounded-top">&nbsp;Ny&nbsp;rättelse</span>
				</div>
			</div>

			<div class="form-row left-right-bottom-border formFrame">

				<div class="form-group pr-2 col-auto">
					<label for="svlth_unt" class="col-form-label-sm mb-0 required">Nytt&nbsp;räknat&nbsp;antal</label>
					<input required type="text" class="form-control form-control-sm" name="svlth_rnt" id="svlth_rnt" value="${record.svlth_rnt}" size="8" maxlength="5">
				</div>
				<div class="form-group pr-3 col-auto">
					<label for="svlth_rtx" class="col-form-label-sm mb-0">Intern&nbsp;information</label>
					<input type="text" class="form-control form-control-sm" name="svlth_rtx" id="svlth_rtx" value="${record.svlth_rtx}" size="73" maxlength="70">
				</div>

				<div class="form-group col-11 align-self-end">
					<div class="float-md-right">
						<button class="btn inputFormSubmit btn-sm" id="submitBtn">Spara</button>
					</div>
				</div>					
	
			</div> <!-- form-row -->

		</div>

	</form>
		
	<c:if test="${not empty error}">
		<div class="container-fluid p-1 left-right-bottom-border">
			<div class="form-row no-gutters">
	
				<div class="alert alert-danger" role="alert">
					<p class="mb-0">${error}</p>
				</div>
	
			</div>
		</div>
	</c:if>

   	</div> <!-- container -->


