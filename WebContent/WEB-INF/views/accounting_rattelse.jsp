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
	
	let hasError = "${error}";
	
	jq(document).ready(function() {
		loadEvent();
		if (hasError == "") {
				clearValuesRattelse();
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
				<div class="form-group pr-2 col-1">
					<label for="event" class="col-form-label-sm mb-0 pb-0">Händelse</label>
					<label class="form-control-plaintext form-control-sm font-weight-bold pt-0 pb-0" id="event"/>
				</div>
				<div class="form-group pr-2 col-1">
					<label for="godsnr" class="col-form-label-sm mb-0 pb-0">Godsnummer</label>
					<label class="form-control-plaintext form-control-sm font-weight-bold pt-0 pb-0" id="godsnr"/>
				</div>	
				<div class="form-group pr-2 col-1">
					<label for="position" class="col-form-label-sm mb-0 pb-0">Position</label>
					<label class="form-control-plaintext form-control-sm font-weight-bold pt-0 pb-0" id="position"/>
				</div>	
				<div class="form-group pr-2 col-2">
					<label for="mrn" class="col-form-label-sm mb-0 pb-0">MRN</label>
					<label class="form-control-plaintext form-control-sm font-weight-bold pt-0 pb-0" id="mrn"/>
				</div>
				<div class="form-group pr-2 col-1">
					<label for="antal" class="col-form-label-sm mb-0 pb-0">Räknat&nbsp;antal</label>
					<label class="form-control-plaintext form-control-sm font-weight-bold pt-0 pb-0" id="antal"/>
				</div>	
				<div class="form-group pr-2 col-1">
					<label for="saldo" class="col-form-label-sm mb-0 pb-0">Saldo</label>
					<label class="form-control-plaintext form-control-sm font-weight-bold pt-0 pb-0" id="saldo"/>
				</div>
				<div class="form-group pr-2 col-1">
					<label for="slag" class="col-form-label-sm mb-0 pb-0">Kollislag</label>
					<label class="form-control-plaintext form-control-sm font-weight-bold pt-0 pb-0" id="slag"/>
				</div>
				<div class="form-group pr-2 col-1">
					<label for="beskrivning" class="col-form-label-sm mb-0 pb-0">Beskrivning</label>
					<label class="form-control-plaintext form-control-sm font-weight-bold pt-0 pb-0" id="beskrivning"/>
				</div>
				<div class="form-group pr-2 col-1">
					<label for="vikt" class="col-form-label-sm mb-0 pb-0">Bruttovikt</label>
					<label class="form-control-plaintext form-control-sm font-weight-bold pt-0 pb-0" id="vikt"/>
				</div>


			</div>
	</div>
	
	<div class="panel-body left-right-border no-gutters">
		<table class="display compact cell-border responsive nowrap" id="rattelseTable">
			<thead class="tableHeaderField">
				<tr>
					<th>Nytt&nbsp;MRN</th>
					<th>Nytt&nbsp;ankomstdatum</th>
					<th>Nytt&nbsp;räknat&nbsp;antal</th>
					<th>Nytt&nbsp;kollislag</th>
					<th>Ny&nbsp;beskrivning</th>
					<th>Ny&nbsp;bruttovikt</th>
					<th>Ny&nbsp;utgående&nbsp;handling&nbsp;1</th>
					<th>Nytt&nbsp;tullid</th>
					<th>Nytt&nbsp;uttaget&nbsp;antal</th>
					<th>Nytt&nbsp;uttagsdatum</th>
					<th>Information</th>
					<th>Arkiverad</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<form action="accounting_rattelse.do" name="rattformRecord" id="rattformRecord" method="POST">
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


		<div class="container-fluid p-1 left-right-bottom-border" id="kalle">

			<div class="form-row left-right-border formFrameHeader">
				<div class="col-sm-12">
					<span class="rounded-top">&nbsp;Ny&nbsp;rättelse</span>
				</div>
			</div>

			<div class="form-row left-right-bottom-border formFrame" id="inlagg">

					<div class="form-group pr-2 mb-0">
						<label for="svlth_rrn" class="col-form-label-sm mb-0">MRN</label>
						<input type="text" class="form-control form-control-sm" name="svlth_rrn" id="svlth_rrn" value="${record.svlth_rrn}"  size="21" maxlength="18">
					</div>	

					<div class="form-group pr-2 mb-0">
						<label for="svlth_rex" class="col-form-label-sm mb-0">Extern&nbsp;referans</label>
						<input type="text" class="form-control form-control-sm" name="svlth_rex" id="svlth_rex" value="${record.svlth_rex}"  size="18" maxlength="15">
					</div>
					<div class="form-group pr-2 mb-0">
						<label for="svlth_rd2" class="col-form-label-sm mb-0">Ankomstdatum</label>
						<input type="text" class="form-control form-control-sm" name="svlth_rd2" id="svlth_rd2" value="${record.svlth_rd2}"  size="11" maxlength="8" onKeyPress="return numberKey(event)">
					</div>		
	
					<div class="form-group pr-2 mb-0">
						<label for="svlth_rnt" class="col-form-label-sm mb-0">Antal(räknat)</label>
						<input type="text" class="form-control form-control-sm" name="svlth_rnt" id="svlth_rnt" value="${record.svlth_rnt}"  size="8" maxlength="5" onKeyPress="return numberKey(event)">
					</div>	

					<div class="form-group pr-2 pl-1 mb-0">
						<label for="svlth_rsl" class="col-form-label-sm mb-0">Kollislag</label>
						<div class="input-group">
		                    <input type="text" class="form-control form-control-sm" name="svlth_rsl" id="svlth_rsl" value="${record.svlth_rsl}" size="4" maxlength="4">&nbsp;
		                    <span class="input-group-prepend">
		       					<a tabindex="-1" id="kollislag_Link2">
									<img src="resources/images/find.png" width="14px" height="14px">
								</a>
		                    </span>
		                </div>
		            </div>

					<div class="form-group pr-2 pl-1 mb-0">
						<label for="svlth_rbr" class="col-form-label-sm mb-0">Bruttovikt</label>
						<input type="text" class="form-control form-control-sm" name="svlth_rbr" id="svlth_rbr" value="${record.svlth_rbr}"  size="16" maxlength="14" onKeyPress="return amountKey(event)">
					</div>	

					<div class="form-group pr-2 pl-1 mb-0 col-6">
						<label for="svlth_rvb" class="col-form-label-sm mb-0">Varubeskrivning</label>
						<input type="text" class="form-control form-control-sm" name="svlth_rvb" id="svlth_rvb" value="${record.svlth_rvb}"  size="73" maxlength="70">
	<!--  
						<a data-toggle="collapse" href="#moreDesc" aria-expanded="false" aria-controls="moreDesc">Ytterligare varubeskrivning</a>
	-->
					</div>	

					<div class="form-group pr-2 col-auto">
						<label for="svlth_rtx" class="col-form-label-sm mb-0">Information</label>
						<input type="text" class="form-control form-control-sm" name="svlth_rtx" id="svlth_rtx" value="${record.svlth_rtx}" size="73" maxlength="70">
					</div>

					<div class="form-group col-11 align-self-end">
						<div class="float-md-right">
							<button class="btn inputFormSubmit btn-sm" id="submitBtn">Spara</button>
						</div>
					</div>	


			</div>

			<div class="form-row left-right-bottom-border formFrame" id="uttag">

				<div class="form-group pr-2 col-auto">
					<label for="svlth_rexU" class="col-form-label-sm mb-0">Utgående&nbsp;handling&nbsp;1</label>
					<input autofocus type="text" class="form-control form-control-sm" name="svlth_rexU" id="svlth_rexU" value="${record.svlth_rexU}"  size="18" maxlength="15">
	<!--  
					<a data-toggle="collapse" href="#moreEvents" aria-expanded="false" aria-controls="moreEvents">Ytterligare&nbsp;utgående&nbsp;handling</a>
	-->
				</div>

				<div class="form-group pr-2 col-auto">
					<label for="svlth_ruti" class="col-form-label-sm mb-0">Tullid</label>
					<input type="text" class="form-control form-control-sm" name="svlth_ruti" id="svlth_ruti" value="${record.svlth_ruti}" size="21" maxlength="18">
				</div>

				<div class="form-group pr-2 col-auto">
					<label for="svlth_rntU" class="col-form-label-sm mb-0">Antal</label>
					<input type="text" class="form-control form-control-sm" name="svlth_rntU" id="svlth_rntU" value="${record.svlth_rntU}" size="7" maxlength="5">
				</div>

				<div class="form-group pr-2 col-auto">
					<label for="svlth_rud1" class="col-form-label-sm mb-0">Uttagsdatum</label>
					<input type="text" class="form-control form-control-sm" name="svlth_rud1" id="svlth_rud1" value="${record.svlth_rud1}"  size="11" maxlength="8" onKeyPress="return numberKey(event)">
				</div>	
	
				<div class="form-group pr-3 col-auto">
					<label for="svlth_rtxU" class="col-form-label-sm mb-0">Information</label>
					<input type="text" class="form-control form-control-sm" name="svlth_rtxU" id="svlth_rtxU" value="${record.svlth_rtxU}" size="73" maxlength="70">
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


