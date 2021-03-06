<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerAccounting.jsp" />
<!-- =====================end header ==========================-->

<link href="resources/accounting.css" rel="stylesheet" type="text/css"/>
<style>

.entry:not(:first-of-type)
{
    margin-top: 10px;
}

.glyphicon
{
    font-size: 12px;
}

</style>
<script type="text/javascript">
	"use strict";

	var h_svlth_h = "${headRecord.svlth_h}";
	var h_svlth_ign = "${headRecord.svlth_ign}";
	var h_svlth_pos = "${headRecord.svlth_pos}";
	var h_svlth_id1 = "${headRecord.svlth_id1}";
	var h_svlth_im1 = "${headRecord.svlth_im1}";	

	let hasError = "${error}";
	
	jq(document).ready(function() {
			loadSvlthUttag();
 			if (hasError == "") {
 				clearValuesUttag();
 			}
 			
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
		    Uttag
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
		<div class="container-fluid p-1 left-right-border">
	
			<div class="form-row formFrameHeader">
				<div class="col-sm-12">
					<span class="rounded-top">Inlägg</span>
				</div>
			</div>	
		
			<div class="form-row no-gutters left-right-bottom-border">
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
					<label for="arrival" class="col-form-label-sm mb-0 pb-0">Ankomstdatum</label>
					<label class="form-control-plaintext form-control-sm font-weight-bold pt-0 pb-0" id="arrival"/>
				</div>
				<div class="form-group pr-2 col-2">
					<label for="archive" class="col-form-label-sm mb-0 pb-0">Arkiveringsdatum</label>
					<label class="form-control-plaintext form-control-sm font-weight-bold pt-0 pb-0" id="archive"/>
				</div>
				<div class="form-group pr-2 col-1">
					<label for="saldo" class="col-form-label-sm mb-0 pb-0">Saldo</label>
					<label class="form-control-plaintext form-control-sm font-weight-bold pt-0 pb-0" id="saldo"/>
				</div>
			</div>
	</div>

	<div class="panel-body left-right-border no-gutters">
		<table class="display compact cell-border responsive nowrap" id="uttagTable">
			<thead class="tableHeaderField">
				<tr>
					<th>Utgående&nbsp;handling</th>
					<th>Uttagsdatum</th>
					<th>Tullid</th>
					<th>Antal</th>
					<th>Intern&nbsp;information</th>
					<th>Arkiverad</th>
				</tr>
			</thead>
		</table>
	</div>	
	
	<form action="accounting_uttag.do" name="utformRecord" id="utformRecord" method="POST">
		<input type="hidden" name="action" id="action" value='1'>
		<input type="hidden" name="svlth_h" id="svlth_h" value='U'>
		<input type="hidden" name="svlth_igl" id="svlth_igl" value='${headRecord.svlth_igl}'>
		<input type="hidden" name="svlth_iex" id="svlth_iex" value='${headRecord.svlth_iex}'>
		<input type="hidden" name="svlth_irn" id="svlth_irn" value='${headRecord.svlth_irn}'>
		<input type="hidden" name="svlth_ign" id="svlth_ign" value='${headRecord.svlth_ign}'>
		<input type="hidden" name="svlth_pos" id="svlth_pos" value='${headRecord.svlth_pos}'>
		<input type="hidden" name="svlth_id1" id="svlth_id1" value='${headRecord.svlth_id1}'>
		<input type="hidden" name="svlth_im1" id="svlth_im1" value='${headRecord.svlth_im1}'>
		<input type="hidden" name="svlth_id2" id="svlth_id2" value='${headRecord.svlth_id2}'>
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

		<input type="hidden" name="h_svlth_h" id="h_svlth_h"  value='${headRecord.svlth_h}'>
		<input type="hidden" name="h_svlth_ign" id="h_svlth_ign"  value='${headRecord.svlth_ign}'>
		<input type="hidden" name="h_svlth_pos" id="h_svlth_pos"  value='${headRecord.svlth_pos}'>
		<input type="hidden" name="h_svlth_id1" id="h_svlth_id1"  value='${headRecord.svlth_id1}'>
		<input type="hidden" name="h_svlth_im1" id="h_svlth_im1"  value='${headRecord.svlth_im1}'>



		<div class="container-fluid p-1 left-right-bottom-border">

			<div class="form-row left-right-border formFrameHeader">
				<div class="col-sm-12">
					<span class="rounded-top">&nbsp;Nytt&nbsp;uttag</span>
				</div>
			</div>

			<div class="form-row left-right-border formFrame">

				<div class="form-group pr-2 col-auto">
					<label for="svlth_uex" class="col-form-label-sm mb-0 required">Utgående&nbsp;handling</label>
					<input autofocus required type="text" class="form-control form-control-sm" name="svlth_uex" id="svlth_uex" value="${record.svlth_uex}"  size="18" maxlength="15">
					<a data-toggle="collapse" href="#moreEvents" aria-expanded="false" aria-controls="moreEvents">Ytterligare&nbsp;utgående&nbsp;handling</a>

				</div>

				<div class="form-group pr-2 col-auto">
					<label for="svlth_uti" class="col-form-label-sm mb-0 required">Tullid</label>
					<input required type="text" class="form-control form-control-sm" name="svlth_uti" id="svlth_uti" value="${record.svlth_uti}" size="21" maxlength="18">
				</div>

				<div class="form-group pr-2 col-auto">
					<label for="svlth_unt" class="col-form-label-sm mb-0 required">Antal</label>
					<input required type="text" class="form-control form-control-sm" name="svlth_unt" id="svlth_unt" value="${record.svlth_unt}" size="7" maxlength="5">
				</div>

				<div class="form-group pr-2 col-auto">
					<label for="svlth_ud1" class="col-form-label-sm mb-0 required">Uttagsdatum</label>
					<input required  type="text" class="form-control form-control-sm" name="svlth_ud1" id="svlth_ud1" value="${record.svlth_ud1}" size="11" maxlength="10" onKeyPress="return numberKey(event)">
				</div>	


			</div> <!-- form-row -->
	
			<div class="form-row left-right-border formFrame">
				  <div class="col-6">
				    <div class="collapse multi-collapse" id="moreEvents">
				      <div class="card card-body">

						<div class="form-group mb-0">
							<label for="svlth_uex2" class="col-form-label-sm mb-0">Utgående&nbsp;handling</label>
							<input type="text" class="form-control form-control-sm" name="svlth_uex2" id="svlth_uex2" value="${record.svlth_uex2}"  size="18" maxlength="15">
						</div>	
						<div class="form-group mb-0">
							<label for="svlth_uex3" class="col-form-label-sm mb-0">Utgående&nbsp;handling</label>
							<input type="text" class="form-control form-control-sm" name="svlth_uex3" id="svlth_uex3" value="${record.svlth_uex3}"  size="18" maxlength="15">
						</div>	
	
						<div class="form-group mb-0">
							<label for="svlth_uex4" class="col-form-label-sm mb-0">Utgående&nbsp;handling</label>
							<input type="text" class="form-control form-control-sm" name="svlth_uex4" id="svlth_uex4" value="${record.svlth_uex4}"  size="18" maxlength="15">
						</div>	
	
						<div class="form-group mb-0">
							<label for="svlth_uex5" class="col-form-label-sm mb-0">Utgående&nbsp;handling</label>
							<input type="text" class="form-control form-control-sm" name="svlth_uex5" id="svlth_uex5" value="${record.svlth_uex5}"  size="18" maxlength="15">
						</div>	

						<div class="form-group mb-0">
							<label for="svlth_uex6" class="col-form-label-sm mb-0">Utgående&nbsp;handling</label>
							<input type="text" class="form-control form-control-sm" name="svlth_uex6" id="svlth_uex6" value="${record.svlth_uex6}"  size="18" maxlength="15">
						</div>	
							
				      </div>
				    </div>
				  </div>
				  <div class="col-6">
				    <div class="collapse multi-collapse" id="moreEvents">
				      <div class="card card-body">

						<div class="form-group mb-0">
							<label for="svlth_uex7" class="col-form-label-sm mb-0">Utgående&nbsp;handling</label>
							<input type="text" class="form-control form-control-sm" name="svlth_uex7" id="svlth_uex7" value="${record.svlth_uex7}"  size="18" maxlength="15">
						</div>	

						<div class="form-group mb-0">
							<label for="svlth_uex8" class="col-form-label-sm mb-0">Utgående&nbsp;handling</label>
							<input type="text" class="form-control form-control-sm" name="svlth_uex8" id="svlth_uex8" value="${record.svlth_uex8}"  size="18" maxlength="15">
						</div>	
	
						<div class="form-group mb-0">
							<label for="svlth_uex9" class="col-form-label-sm mb-0">Utgående&nbsp;handling</label>
							<input type="text" class="form-control form-control-sm" name="svlth_uex9" id="svlth_uex9" value="${record.svlth_uex9}"  size="18" maxlength="15">
						</div>	
	
						<div class="form-group mb-0">
							<label for="svlth_uex10" class="col-form-label-sm mb-0">Utgående&nbsp;handling</label>
							<input type="text" class="form-control form-control-sm" name="svlth_uex10" id="svlth_uex10" value="${record.svlth_uex10}"  size="18" maxlength="15">
						</div>	

						<div class="form-group mb-0">
							<label for="svlth_uex11" class="col-form-label-sm mb-0">Utgående&nbsp;handling</label>
							<input type="text" class="form-control form-control-sm" name="svlth_uex11" id="svlth_uex11" value="${record.svlth_uex11}"  size="18" maxlength="15">
						</div>	
	
				      </div>
				    </div>
				  </div>


			</div>			
	
			<div class="form-row left-right-bottom-border formFrame">
	
				<div class="form-group pr-3 col-auto">
					<label for="svlth_utx" class="col-form-label-sm mb-0">Intern&nbsp;information</label>
					<input type="text" class="form-control form-control-sm" name="svlth_utx" id="svlth_utx" value="${record.svlth_utx}" size="73" maxlength="70">
				</div>

				<div class="form-group col-11 align-self-end">
					<div class="float-md-right">
						<button class="btn inputFormSubmit btn-sm" id="submitBtn">Spara</button>
					</div>
				</div>			
		
			</div>
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
	

	<c:if test="${not empty info}">
		<div class="container-fluid p-1 left-right-bottom-border">
			<div class="form-row no-gutters">
	
				<div class="alert alert-info" role="alert">
					<p class="mb-0">${info}</p>
				</div>
	
			</div>
		</div>
	</c:if>	
	
	
	
	
	
	
	

</div> <!-- container -->


