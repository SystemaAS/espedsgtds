/**
 * 
 */
package no.systema.tds.tdsexport.model.jsonjackson.topic.items;

import java.lang.reflect.Field;
import java.util.*;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * @author oscardelatorre
 * @date Jan 15, 2013
 *
 */
public class JsonTdsExportSpecificTopicItemRecord extends JsonAbstractGrandFatherRecord  {
	
	//Aux. attribute to validate extra mängd
	private String extraMangdEnhet = null;
	public void setExtraMangdEnhet(String value) {  this.extraMangdEnhet = value; }
	public String getExtraMangdEnhet() {return this.extraMangdEnhet;}
	//Code
	private String extraMangdEnhetCode = null;
	public void setExtraMangdEnhetCode(String value) {  this.extraMangdEnhetCode = value; }
	public String getExtraMangdEnhetCode() {return this.extraMangdEnhetCode;}
	//Description
	private String extraMangdEnhetDescription = null;
	public void setExtraMangdEnhetDescription(String value) {  this.extraMangdEnhetDescription = value; }
	public String getExtraMangdEnhetDescription() {return this.extraMangdEnhetDescription;}
	
	//varukod validity
	private boolean validNumberVata = true;
	public void setValidNumberVata(boolean value) {  this.validNumberVata = value; }
	public boolean isValidNumberVata() {return this.validNumberVata;}
	
	//Used in auto-control of varuposter in order to null all statvalues and run the auto-controll afterwards ( in the same method )
	private String statValueNullIt = null;
	public void setStatValueNullIt(String value) {  this.statValueNullIt = value; }
	public String getStatValueNullIt() {return this.statValueNullIt;}
	
	
	//Aux. attr. in order to sum all svev_kota + svev_kot2 + svev_kot3 + svev_kot4 + svev_kot5 on HTML GUI
	private Integer sum_of_svev_kotas = 0;
	public Integer getSum_of_svev_kotas(){
		int sum = 0;
		try{
			if(this.svev_kota!=null && !"".equals(this.svev_kota)){
				sum += Integer.parseInt(this.svev_kota);
			}
			if(this.svev_kot2!=null && !"".equals(this.svev_kot2)){
				sum += Integer.parseInt(this.svev_kot2);
			}
			if(this.svev_kot3!=null && !"".equals(this.svev_kot3)){
				sum += Integer.parseInt(this.svev_kot3);
			}
			if(this.svev_kot4!=null && !"".equals(this.svev_kot4)){
				sum += Integer.parseInt(this.svev_kot4);
			}
			if(this.svev_kot5!=null && !"".equals(this.svev_kot5)){
				sum += Integer.parseInt(this.svev_kot5);
			}
			this.sum_of_svev_kotas = sum;
			
		}catch(Exception e){
			//Nothing
		}
		
		return sum_of_svev_kotas;
	}
	
	private String svev_syav = null;
	public void setSvev_syav(String value) {  this.svev_syav = value; }
	public String getSvev_syav() {return this.svev_syav;}
	
	private String svev_syop = null;
	public void setSvev_syop(String value) {  this.svev_syop = value; }
	public String getSvev_syop() {return this.svev_syop;}
	
	private String svev_syli = null;
	public void setSvev_syli(String value) {  this.svev_syli = value; }
	public String getSvev_syli() {return this.svev_syli;}
	
	private String svev_vano = null;
	public void setSvev_vano(String value) {  this.svev_vano = value; }
	public String getSvev_vano() {return this.svev_vano;}
	
	private String svev_ulkd = null;
	public void setSvev_ulkd(String value) {  this.svev_ulkd = value; }
	public String getSvev_ulkd() {return this.svev_ulkd;}
	
	private String svev_vata = null;
	public void setSvev_vata(String value) {  this.svev_vata = value; }
	public String getSvev_vata() {return this.svev_vata;}
	
	private String svev_vati = null;
	public void setSvev_vati(String value) {  this.svev_vati = value; }
	public String getSvev_vati() {return this.svev_vati;}
	
	private String svev_eup1 = null;
	public void setSvev_eup1(String value) {  this.svev_eup1 = value; }
	public String getSvev_eup1() {return this.svev_eup1;}
	
	private String svev_eup2 = null;
	public void setSvev_eup2(String value) {  this.svev_eup2 = value; }
	public String getSvev_eup2() {return this.svev_eup2;}
	
	private String svev_brut = null;
	public void setSvev_brut(String value) {  this.svev_brut = value; }
	public String getSvev_brut() {return this.svev_brut;}
	
	private String svev_neto = null;
	public void setSvev_neto(String value) {  this.svev_neto = value; }
	public String getSvev_neto() {return this.svev_neto;}
	
	private String svev_ankv = null;
	public void setSvev_ankv(String value) {  this.svev_ankv = value; }
	public String getSvev_ankv() {return this.svev_ankv;}
	
	private String svev_call = null;
	public void setSvev_call(String value) {  this.svev_call = value; }
	public String getSvev_call() { return this.svev_call;}
	
	private String svev_vasl = null;
	public void setSvev_vasl(String value) {  this.svev_vasl = value; }
	public String getSvev_vasl() {return this.svev_vasl;}
	
	private String svev_vas2 = null;
	public void setSvev_vas2(String value) {  this.svev_vas2 = value; }
	public String getSvev_vas2() {return this.svev_vas2;}
	
	private String svev_vas3 = null;
	public void setSvev_vas3(String value) {  this.svev_vas3 = value; }
	public String getSvev_vas3() {return this.svev_vas3;}
	
	private String svev_vas4 = null;
	public void setSvev_vas4(String value) {  this.svev_vas4 = value; }
	public String getSvev_vas4() {return this.svev_vas4;}
	
	private String svev_vas5 = null;
	public void setSvev_vas5(String value) {  this.svev_vas5 = value; }
	public String getSvev_vas5() {return this.svev_vas5;}
	
	
	private String svev_kota = "0";
	public void setSvev_kota(String value) {
		if(value==null || "".equals(value)){
			this.svev_kota = "0";
		}else{
			this.svev_kota = value;
		}
	}
	public String getSvev_kota() {
		if(this.svev_kota==null || "".equals(this.svev_kota)){
			return "0";
		}else{
			return this.svev_kota;
		}
	}
	
	private String svev_kot2 = null;
	public void setSvev_kot2(String value) {  this.svev_kot2 = value; }
	public String getSvev_kot2() { return this.svev_kot2;}
	
	private String svev_kot3 = null;
	public void setSvev_kot3(String value) {  this.svev_kot3 = value; }
	public String getSvev_kot3() { return this.svev_kot3;}
	
	private String svev_kot4 = null;
	public void setSvev_kot4(String value) {  this.svev_kot4 = value; }
	public String getSvev_kot4() { return this.svev_kot4;}
	
	private String svev_kot5 = null;
	public void setSvev_kot5(String value) {  this.svev_kot5 = value; }
	public String getSvev_kot5() { return this.svev_kot5;}
	
	private String svev_kosl = null;
	public void setSvev_kosl(String value) {  this.svev_kosl = value; }
	public String getSvev_kosl() { return this.svev_kosl;}
	
	private String svev_kos2 = null;
	public void setSvev_kos2(String value) {  this.svev_kos2 = value; }
	public String getSvev_kos2() { return this.svev_kos2;}
	
	private String svev_kos3 = null;
	public void setSvev_kos3(String value) {  this.svev_kos3 = value; }
	public String getSvev_kos3() { return this.svev_kos3;}
	
	private String svev_kos4 = null;
	public void setSvev_kos4(String value) {  this.svev_kos4 = value; }
	public String getSvev_kos4() { return this.svev_kos4;}
	
	private String svev_kos5 = null;
	public void setSvev_kos5(String value) {  this.svev_kos5 = value; }
	public String getSvev_kos5() { return this.svev_kos5;}
	
	private String svev_godm = null;
	public void setSvev_godm(String value) {  this.svev_godm = value; }
	public String getSvev_godm() { return this.svev_godm;}
	
	private String svev_god2 = null;
	public void setSvev_god2(String value) {  this.svev_god2 = value; }
	public String getSvev_god2() { return this.svev_god2;}
	
	private String svev_god3 = null;
	public void setSvev_god3(String value) {  this.svev_god3 = value; }
	public String getSvev_god3() { return this.svev_god3;}
	
	private String svev_god4 = null;
	public void setSvev_god4(String value) {  this.svev_god4 = value; }
	public String getSvev_god4() { return this.svev_god4;}
	
	private String svev_god5 = null;
	public void setSvev_god5(String value) {  this.svev_god5 = value; }
	public String getSvev_god5() { return this.svev_god5; }
	
	private String svev_vat4 = null;
	public void setSvev_vat4(String value) {  this.svev_vat4 = value; }
	public String getSvev_vat4() { return this.svev_vat4; }
	
	private String svev_vat5 = null;
	public void setSvev_vat5(String value) {  this.svev_vat5 = value; }
	public String getSvev_vat5() { return this.svev_vat5; }
	
	private String svev_kono = null;
	public void setSvev_kono(String value) {  this.svev_kono = value; }
	public String getSvev_kono() { return this.svev_kono; }
	
	private String svev_suko = null;
	public void setSvev_suko(String value) {  this.svev_suko = value; }
	public String getSvev_suko() { return this.svev_suko; }
	
	private String svev_suk6 = null;
	public void setSvev_suk6(String value) {  this.svev_suk6 = value; }
	public String getSvev_suk6() { return this.svev_suk6; }
	
	private String svev_sukb = null;
	public void setSvev_sukb(String value) {  this.svev_sukb = value; }
	public String getSvev_sukb() { return this.svev_sukb; }
	
	private String svev_sutx = null;
	public void setSvev_sutx(String value) {  this.svev_sutx = value; }
	public String getSvev_sutx() { return this.svev_sutx; }
	
	private String svev_sut2 = null;
	public void setSvev_sut2(String value) {  this.svev_sut2 = value; }
	public String getSvev_sut2() { return this.svev_sut2; }
	
	private String svev_sut3 = null;
	public void setSvev_sut3(String value) {  this.svev_sut3 = value; }
	public String getSvev_sut3() { return this.svev_sut3; }
	
	private String svev_sut4 = null;
	public void setSvev_sut4(String value) {  this.svev_sut4 = value; }
	public String getSvev_sut4() { return this.svev_sut4; }
	
	private String svev_sut5 = null;
	public void setSvev_sut5(String value) {  this.svev_sut5 = value; }
	public String getSvev_sut5() { return this.svev_sut5; }
	
	private String svev_sut6 = null;
	public void setSvev_sut6(String value) {  this.svev_sut6 = value; }
	public String getSvev_sut6() { return this.svev_sut6; }
	
	private String svev_sut7 = null;
	public void setSvev_sut7(String value) {  this.svev_sut7 = value; }
	public String getSvev_sut7() { return this.svev_sut7; }
	
	private String svev_sut8 = null;
	public void setSvev_sut8(String value) {  this.svev_sut8 = value; }
	public String getSvev_sut8() { return this.svev_sut8; }
	
	private String svev_sut9 = null;
	public void setSvev_sut9(String value) {  this.svev_sut9 = value; }
	public String getSvev_sut9() { return this.svev_sut9; }
	
	private String svev_suta = null;
	public void setSvev_suta(String value) {  this.svev_suta = value; }
	public String getSvev_suta() { return this.svev_suta; }
	
	private String svev_sutb = null;
	public void setSvev_sutb(String value) {  this.svev_sutb = value; }
	public String getSvev_sutb() { return this.svev_sutb; }

	private String svev_sutc = null;
	public void setSvev_sutc(String value) {  this.svev_sutc = value; }
	public String getSvev_sutc() { return this.svev_sutc; }

	private String svev_sutd = null;
	public void setSvev_sutd(String value) {  this.svev_sutd = value; }
	public String getSvev_sutd() { return this.svev_sutd; }

	private String svev_sute = null;
	public void setSvev_sute(String value) {  this.svev_sute = value; }
	public String getSvev_sute() { return this.svev_sute; }

	private String svev_sutf = null;
	public void setSvev_sutf(String value) {  this.svev_sutf = value; }
	public String getSvev_sutf() { return this.svev_sutf; }

	private String svev_sutl = null;
	public void setSvev_sutl(String value) {  this.svev_sutl = value; }
	public String getSvev_sutl() { return this.svev_sutl; }
	
	private String svev_sula = null;
	public void setSvev_sula(String value) {  this.svev_sula = value; }
	public String getSvev_sula() { return this.svev_sula; }
	
	private String svev_suok = null;
	public void setSvev_suok(String value) {  this.svev_suok = value; }
	public String getSvev_suok() { return this.svev_suok; }
	
	private String svev_sukr = null;
	public void setSvev_sukr(String value) {  this.svev_sukr = value; }
	public String getSvev_sukr() { return this.svev_sukr; }
	
	private String svev_suar = null;
	public void setSvev_suar(String value) {  this.svev_suar = value; }
	public String getSvev_suar() { return this.svev_suar; }
	
	private String svev_atin = null;
	public void setSvev_atin(String value) {  this.svev_atin = value; }
	public String getSvev_atin() { return this.svev_atin; }
	
	private String svev_stva = null;
	public void setSvev_stva(String value) {  this.svev_stva = value; }
	public String getSvev_stva() { return this.svev_stva; }

	private String svev_stva2 = null;
	public void setSvev_stva2(String value) {  this.svev_stva2 = value; }
	public String getSvev_stva2() { return this.svev_stva2; }

	private String svev_fabl = null;
	public void setSvev_fabl(String value) {  this.svev_fabl = value; }
	public String getSvev_fabl() { return this.svev_fabl; }
	
	private String svev_betk = null;
	public void setSvev_betk(String value) {  this.svev_betk = value; }
	public String getSvev_betk() { return this.svev_betk; }
	
	private String svev_komr = null;
	public void setSvev_komr(String value) {  this.svev_komr = value; }
	public String getSvev_komr() { return this.svev_komr; }
	
	private String svev_fnkd = null;
	public void setSvev_fnkd(String value) {  this.svev_fnkd = value; }
	public String getSvev_fnkd() { return this.svev_fnkd; }
	
	private String svev_bit1 = null;
	public void setSvev_bit1(String value) {  this.svev_bit1 = value; }
	public String getSvev_bit1() { return this.svev_bit1; }
	
	private String svev_bit2 = null;
	public void setSvev_bit2(String value) {  this.svev_bit2 = value; }
	public String getSvev_bit2() { return this.svev_bit2; }
	
	private String svev_bit3 = null;
	public void setSvev_bit3(String value) {  this.svev_bit3 = value; }
	public String getSvev_bit3() { return this.svev_bit3; }
	
	private String svev_bit4 = null;
	public void setSvev_bit4(String value) {  this.svev_bit4 = value; }
	public String getSvev_bit4() { return this.svev_bit4; }
	
	private String svev_bit5 = null;
	public void setSvev_bit5(String value) {  this.svev_bit5 = value; }
	public String getSvev_bit5() { return this.svev_bit5; }
	
	private String svev_bit6 = null;
	public void setSvev_bit6(String value) {  this.svev_bit6 = value; }
	public String getSvev_bit6() { return this.svev_bit6; }
	
	private String svev_bit7 = null;
	public void setSvev_bit7(String value) {  this.svev_bit7 = value; }
	public String getSvev_bit7() { return this.svev_bit7; }
	
	private String svev_bit8 = null;
	public void setSvev_bit8(String value) {  this.svev_bit8 = value; }
	public String getSvev_bit8() { return this.svev_bit8; }
	
	private String svev_bit9 = null;
	public void setSvev_bit9(String value) {  this.svev_bit9 = value; }
	public String getSvev_bit9() { return this.svev_bit9; }
	
	private String svev_bii1 = null;
	public void setSvev_bii1(String value) {  this.svev_bii1 = value; }
	public String getSvev_bii1() { return this.svev_bii1; }
	
	private String svev_bii2 = null;
	public void setSvev_bii2(String value) {  this.svev_bii2 = value; }
	public String getSvev_bii2() { return this.svev_bii2; }
	
	private String svev_bii3 = null;
	public void setSvev_bii3(String value) {  this.svev_bii3 = value; }
	public String getSvev_bii3() { return this.svev_bii3; }
	
	private String svev_bii4 = null;
	public void setSvev_bii4(String value) {  this.svev_bii4 = value; }
	public String getSvev_bii4() { return this.svev_bii4; }
	
	private String svev_bii5 = null;
	public void setSvev_bii5(String value) {  this.svev_bii5 = value; }
	public String getSvev_bii5() { return this.svev_bii5; }
	
	private String svev_bii6 = null;
	public void setSvev_bii6(String value) {  this.svev_bii6 = value; }
	public String getSvev_bii6() { return this.svev_bii6; }
	
	private String svev_bii7 = null;
	public void setSvev_bii7(String value) {  this.svev_bii7 = value; }
	public String getSvev_bii7() { return this.svev_bii7; }
	
	private String svev_bii8 = null;
	public void setSvev_bii8(String value) {  this.svev_bii8 = value; }
	public String getSvev_bii8() { return this.svev_bii8; }
	
	private String svev_bii9 = null;
	public void setSvev_bii9(String value) {  this.svev_bii9 = value; }
	public String getSvev_bii9() { return this.svev_bii9; }
	
	private String svev_co01 = null;
	public void setSvev_co01(String value) {  this.svev_co01 = value; }
	public String getSvev_co01() { return this.svev_co01; }
	
	private String svev_co02 = null;
	public void setSvev_co02(String value) {  this.svev_co02 = value; }
	public String getSvev_co02() { return this.svev_co02; }
	
	private String svev_co03 = null;
	public void setSvev_co03(String value) {  this.svev_co03 = value; }
	public String getSvev_co03() { return this.svev_co03; }
	
	private String svev_co04 = null;
	public void setSvev_co04(String value) {  this.svev_co04 = value; }
	public String getSvev_co04() { return this.svev_co04; }
	
	private String svev_co05 = null;
	public void setSvev_co05(String value) {  this.svev_co05 = value; }
	public String getSvev_co05() { return this.svev_co05; }
	
	private String svev_co06 = null;
	public void setSvev_co06(String value) {  this.svev_co06 = value; }
	public String getSvev_co06() { return this.svev_co06; }
	
	private String svev_co07 = null;
	public void setSvev_co07(String value) {  this.svev_co07 = value; }
	public String getSvev_co07() { return this.svev_co07; }
	
	private String svev_co08 = null;
	public void setSvev_co08(String value) {  this.svev_co08 = value; }
	public String getSvev_co08() { return this.svev_co08; }
	
	private String svev_co09 = null;
	public void setSvev_co09(String value) {  this.svev_co09 = value; }
	public String getSvev_co09() { return this.svev_co09; }
	
	private String svev_co10 = null;
	public void setSvev_co10(String value) {  this.svev_co10 = value; }
	public String getSvev_co10() { return this.svev_co10; }
	
	private String svev_co11 = null;
	public void setSvev_co11(String value) {  this.svev_co11 = value; }
	public String getSvev_co11() { return this.svev_co11; }
	
	private String svev_co12 = null;
	public void setSvev_co12(String value) {  this.svev_co12 = value; }
	public String getSvev_co12() { return this.svev_co12; }
	
	private String svev_co13 = null;
	public void setSvev_co13(String value) {  this.svev_co13 = value; }
	public String getSvev_co13() { return this.svev_co13; }
	
	private String svev_co14 = null;
	public void setSvev_co14(String value) {  this.svev_co14 = value; }
	public String getSvev_co14() { return this.svev_co14; }
	
	private String svev_co15 = null;
	public void setSvev_co15(String value) {  this.svev_co15 = value; }
	public String getSvev_co15() { return this.svev_co15; }
	
	private String svev_co16 = null;
	public void setSvev_co16(String value) {  this.svev_co16 = value; }
	public String getSvev_co16() { return this.svev_co16; }
	
	private String svev_co17 = null;
	public void setSvev_co17(String value) {  this.svev_co17 = value; }
	public String getSvev_co17() { return this.svev_co17; }
	
	private String svev_co18 = null;
	public void setSvev_co18(String value) {  this.svev_co18 = value; }
	public String getSvev_co18() { return this.svev_co18; }
	
	private String svev_co19 = null;
	public void setSvev_co19(String value) {  this.svev_co19 = value; }
	public String getSvev_co19() { return this.svev_co19; }
	
	private String svev_co20 = null;
	public void setSvev_co20(String value) {  this.svev_co20 = value; }
	public String getSvev_co20() { return this.svev_co20; }
	
	private String svev_tik1 = null;
	public void setSvev_tik1(String value) {  this.svev_tik1 = value; }
	public String getSvev_tik1() { return this.svev_tik1; }
	
	private String svev_tik2 = null;
	public void setSvev_tik2(String value) {  this.svev_tik2 = value; }
	public String getSvev_tik2() { return this.svev_tik2; }
	
	private String svev_tik3 = null;
	public void setSvev_tik3(String value) {  this.svev_tik3 = value; }
	public String getSvev_tik3() { return this.svev_tik3; }
	
	private String svev_tik4 = null;
	public void setSvev_tik4(String value) {  this.svev_tik4 = value; }
	public String getSvev_tik4() { return this.svev_tik4; }
	
	private String svev_tik5 = null;
	public void setSvev_tik5(String value) {  this.svev_tik5 = value; }
	public String getSvev_tik5() { return this.svev_tik5; }
	
	private String svev_tik6 = null;
	public void setSvev_tik6(String value) {  this.svev_tik6 = value; }
	public String getSvev_tik6() { return this.svev_tik6; }
	
	private String svev_tik7 = null;
	public void setSvev_tik7(String value) {  this.svev_tik7 = value; }
	public String getSvev_tik7() { return this.svev_tik7; }
	
	private String svev_tik8 = null;
	public void setSvev_tik8(String value) {  this.svev_tik8 = value; }
	public String getSvev_tik8() { return this.svev_tik8; }
	
	private String svev_tik9 = null;
	public void setSvev_tik9(String value) {  this.svev_tik9 = value; }
	public String getSvev_tik9() { return this.svev_tik9; }
	
	private String svev_tit1 = null;
	public void setSvev_tit1(String value) {  this.svev_tit1 = value; }
	public String getSvev_tit1() { return this.svev_tit1; }
	
	private String svev_tit2 = null;
	public void setSvev_tit2(String value) {  this.svev_tit2 = value; }
	public String getSvev_tit2() { return this.svev_tit2; }
	
	private String svev_tit3 = null;
	public void setSvev_tit3(String value) {  this.svev_tit3 = value; }
	public String getSvev_tit3() { return this.svev_tit3; }
	
	private String svev_tit4 = null;
	public void setSvev_tit4(String value) {  this.svev_tit4 = value; }
	public String getSvev_tit4() { return this.svev_tit4; }
	
	private String svev_tit5 = null;
	public void setSvev_tit5(String value) {  this.svev_tit5 = value; }
	public String getSvev_tit5() { return this.svev_tit5; }
	
	private String svev_tit6 = null;
	public void setSvev_tit6(String value) {  this.svev_tit6 = value; }
	public String getSvev_tit6() { return this.svev_tit6; }
	
	private String svev_tit7 = null;
	public void setSvev_tit7(String value) {  this.svev_tit7 = value; }
	public String getSvev_tit7() { return this.svev_tit7; }
	
	private String svev_tit8 = null;
	public void setSvev_tit8(String value) {  this.svev_tit8 = value; }
	public String getSvev_tit8() { return this.svev_tit8; }
	
	private String svev_tit9 = null;
	public void setSvev_tit9(String value) {  this.svev_tit9 = value; }
	public String getSvev_tit9() { return this.svev_tit9; }
	
	private String svev_tix1 = null;
	public void setSvev_tix1(String value) {  this.svev_tix1 = value; }
	public String getSvev_tix1() { return this.svev_tix1; }
	
	private String svev_tix2 = null;
	public void setSvev_tix2(String value) {  this.svev_tix2 = value; }
	public String getSvev_tix2() { return this.svev_tix2; }
	
	private String svev_tix3 = null;
	public void setSvev_tix3(String value) {  this.svev_tix3 = value; }
	public String getSvev_tix3() { return this.svev_tix3; }
	
	private String svev_tix4 = null;
	public void setSvev_tix4(String value) {  this.svev_tix4 = value; }
	public String getSvev_tix4() { return this.svev_tix4; }
	
	private String svev_tix5 = null;
	public void setSvev_tix5(String value) {  this.svev_tix5 = value; }
	public String getSvev_tix5() { return this.svev_tix5; }
	
	private String svev_tix6 = null;
	public void setSvev_tix6(String value) {  this.svev_tix6 = value; }
	public String getSvev_tix6() { return this.svev_tix6; }
	
	private String svev_tix7 = null;
	public void setSvev_tix7(String value) {  this.svev_tix7 = value; }
	public String getSvev_tix7() { return this.svev_tix7; }
	
	private String svev_tix8 = null;
	public void setSvev_tix8(String value) {  this.svev_tix8 = value; }
	public String getSvev_tix8() { return this.svev_tix8; }
	
	private String svev_tix9 = null;
	public void setSvev_tix9(String value) {  this.svev_tix9 = value; }
	public String getSvev_tix9() { return this.svev_tix9; }
	
	private String svev_lagi = null;
	public void setSvev_lagi(String value) {  this.svev_lagi = value; }
	public String getSvev_lagi() { return this.svev_lagi;}
	
	private String svev_lagt = null;
	public void setSvev_lagt(String value) {  this.svev_lagt = value; }
	public String getSvev_lagt() { return this.svev_lagt;}
    
	private String svev_lagl = null;
	public void setSvev_lagl(String value) {  this.svev_lagl = value; }
	public String getSvev_lagl() { return this.svev_lagl;}
	
	private String svev_err = null;
	public void setSvev_err(String value) {  this.svev_err = value; }
	public String getSvev_err() { return this.svev_err;}
	
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}

}
