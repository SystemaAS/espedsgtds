<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerAccounting.jsp" />
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
		<a class="nav-item nav-link active disabled">
		  <span class="navbar-text no-gutters pb-0 pt-0">
		    Inlägg<c:if test="${action == 2}"> ${record.svlth_irn}</c:if>  
		  </span>
		</a>
	<c:if test="${action == 2}"> 
		<a class="nav-item nav-link nav-new" onClick="setBlockUI();" href="accounting_uttag_list.do?action=2&svlth_irn=${record.svlth_irn}&svlth_id1=${record.svlth_id1}&svlth_im1=${record.svlth_im1}">
		  <span class="navbar-text no-gutters pb-0 pt-0">
			Uttag
		  </span>
		</a>
	</c:if>
	  </div>
	</nav>

	<div class="padded-row-small left-right-border"></div>

	<div class="container-fluid p-1 left-right-bottom-border">

		<form action="accounting_inlagg.do" name="formRecord" id="formRecord" method="POST">
			<input type="hidden" name="action" id="action" value='${action}'>
			<input type="hidden" name="svlth_h" id="svlth_h" value='I'>
	
			<div class="form-row left-right-border formFrameHeader">
				<div class="col-sm-12">
					<span class="rounded-top">Inlägg</span>
				</div>
			</div>
	
			<div class="form-row left-right-border" <c:if test="${action == 2}"> disabled</c:if>>
					<div class="form-group pr-2 pl-1 mb-0">
						<label for="svlth_igl" class="col-form-label-sm mb-0 required">Godslokalkod</label>
						<input type="text" required class="form-control form-control-sm" name="svlth_igl" id="svlth_igl" value="${record.svlth_igl}" size="5" maxlength="3">
					</div>
	
					<div class="form-group pr-2 mb-0">
						<label for="svlth_ign" class="col-form-label-sm mb-0 required">Godsnummer</label>
						<input type="text" required class="form-control form-control-sm" name="svlth_ign" id="svlth_ign" value="${record.svlth_ign}"  size="15" maxlength="12">
					</div>	

					<div class="form-group pr-2 mb-0">
						<label for="svlth_irn" class="col-form-label-sm mb-0 required">MRN</label>
						<input type="text" required class="form-control form-control-sm" name="svlth_irn" id="svlth_irn" value="${record.svlth_irn}"  size="21" maxlength="18">
					</div>	

					<div class="form-group pr-2 mb-0">
						<label for="svlth_iex" class="col-form-label-sm mb-0 required">Extern&nbsp;referans</label>
						<input type="text" required class="form-control form-control-sm" name="svlth_iex" id="svlth_iex" value="${record.svlth_iex}"  size="18" maxlength="15">
					</div>

					<div class="form-group pr-2 mb-0">
						<label for="svlth_id2" class="col-form-label-sm mb-0 required">Ankomstdatum</label>
						<input type="text" required class="form-control form-control-sm" name="svlth_id2" id="svlth_id2" value="${record.svlth_id2}"  size="11" maxlength="8" onKeyPress="return numberKey(event)">
					</div>		
	

					<div class="form-group pr-2 mb-0">
						<label for="svlth_int" class="col-form-label-sm mb-0 required">Antal(räknat)</label>
						<input type="text" required class="form-control form-control-sm" name="svlth_int" id="svlth_int" value="${record.svlth_int}"  size="8" maxlength="5" onKeyPress="return numberKey(event)">
					</div>	

					<div class="form-group pr-2">
						<label for="saldo" class="col-form-label-sm mb-1 pb-0">Saldo</label>
						<label class="form-control-plaintext form-control-sm" id="saldo">${saldo}</label>
					</div>

					<div class="form-group pr-2 pl-1 mb-0">
						<label for="svlth_isl" class="col-form-label-sm mb-0 required">Kollislag</label>
						<div class="input-group">
		                    <input type="text" required class="form-control form-control-sm" name="svlth_isl" id="svlth_isl" value="${record.svlth_isl}" size="4" maxlength="4">&nbsp;
		                    <span class="input-group-prepend">
		       					<a tabindex="-1" id="kollislag_Link">
									<img src="resources/images/find.png" width="14px" height="14px">
								</a>
		                    </span>
		                </div>
		            </div>

					<div class="form-group pr-2 pl-1 mb-0">
						<label for="svlth_ibr" class="col-form-label-sm mb-0 required">Bruttovikt</label>
						<input type="text" required class="form-control form-control-sm" name="svlth_ibr" id="svlth_ibr" value="${record.svlth_ibr}"  size="16" maxlength="14" onKeyPress="return amountKey(event)">
					</div>	

					<div class="form-group pr-2 mb-0">
						<label for="svlth_ivb" class="col-form-label-sm mb-0 required">Varubeskrivning</label>
						<input type="text" required class="form-control form-control-sm" name="svlth_ivb" id="svlth_ivb" value="${record.svlth_ivb}"  size="73" maxlength="70">
					</div>	


			</div>
			<div class="form-row left-right-border" <c:if test="${action == 2}"> disabled</c:if>>

					<div class="form-group pr-2 pl-1 mb-0">
						<label for="svlth_ih1" class="col-form-label-sm mb-0">Tidigare&nbsp;handling&nbsp;1</label>
						<input type="text" class="form-control form-control-sm" name="svlth_ih1" id="svlth_ih1" value="${record.svlth_ih1}"  size="38" maxlength="35">
					</div>	

					<div class="form-group pr-2 mb-0">
						<label for="svlth_ih2" class="col-form-label-sm mb-0 ">Tidigare&nbsp;handling&nbsp;2</label>
						<input type="text" class="form-control form-control-sm" name="svlth_ih2" id="svlth_ih2" value="${record.svlth_ih2}"  size="38" maxlength="35">
					</div>	
					
					<div class="form-group pr-2 mb-0">
						<label for="svlth_ih3" class="col-form-label-sm mb-0">Tidigare&nbsp;handling&nbsp;3</label>
						<input type="text" class="form-control form-control-sm" name="svlth_ih3" id="svlth_ih3" value="${record.svlth_ih3}"  size="38" maxlength="35">
					</div>	
					
					<div class="form-group pr-2 mb-0">
						<label for="svlth_ih4" class="col-form-label-sm mb-0">Tidigare&nbsp;handling&nbsp;4</label>
						<input type="text" class="form-control form-control-sm" name="svlth_ih4" id="svlth_ih4" value="${record.svlth_ih4}"  size="38" maxlength="35">
					</div>	

					<div class="form-group pr-2 mb-0">
						<label for="svlth_ih5" class="col-form-label-sm mb-0">Tidigare&nbsp;handling&nbsp;5</label>
						<input type="text" class="form-control form-control-sm" name="svlth_ih5" id="svlth_ih5" value="${record.svlth_ih5}"  size="38" maxlength="35">
					</div>	

			</div>
			<div class="form-row left-right-bottom-border" <c:if test="${action == 2}"> disabled</c:if>>

					<div class="form-group pr-2 pl-1  mb-0">
						<label for="svlth_itx" class="col-form-label-sm mb-0">Fritext</label>
						<input type="text" class="form-control form-control-sm" name="svlth_itx" id="svlth_itx" value="${record.svlth_itx}"  size="73" maxlength="70">
					</div>			
	
					<div class="form-group col-11 align-self-end">
						<div class="float-md-right">
							<button <c:if test="${action == 2}"> disabled</c:if> class="btn inputFormSubmit btn-sm" id="submitBtn">Spara</button>
						</div>
					</div>		
			
			</div>		
			
			
		</form>
   	</div> <!-- container -->


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