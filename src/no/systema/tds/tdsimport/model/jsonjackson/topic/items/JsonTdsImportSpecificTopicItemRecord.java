/**
 * 
 */
package no.systema.tds.tdsimport.model.jsonjackson.topic.items;

import java.lang.reflect.Field;
import java.util.*;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * @author oscardelatorre
 * @date Sep 2, 2013
 *
 */
public class JsonTdsImportSpecificTopicItemRecord  extends JsonAbstractGrandFatherRecord   {
	
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
		
	
	//Aux. attr. in order to sum all sviv_kota + sviv_kot2 + sviv_kot3 + sviv_kot4 + sviv_kot5 on HTML GUI
	private Integer sum_of_sviv_kotas = 0;
	public Integer getSum_of_sviv_kotas() {
		int sum = 0;
		try{
			if(this.sviv_kota!=null && !"".equals(this.sviv_kota)){
				sum += Integer.parseInt(this.sviv_kota);
			}
			if(this.sviv_kot2!=null && !"".equals(this.sviv_kot2)){
				sum += Integer.parseInt(this.sviv_kot2);
			}
			if(this.sviv_kot3!=null && !"".equals(this.sviv_kot3)){
				sum += Integer.parseInt(this.sviv_kot3);
			}
			if(this.sviv_kot4!=null && !"".equals(this.sviv_kot4)){
				sum += Integer.parseInt(this.sviv_kot4);
			}
			if(this.sviv_kot5!=null && !"".equals(this.sviv_kot5)){
				sum += Integer.parseInt(this.sviv_kot5);
			}
			this.sum_of_sviv_kotas = sum;
			
		}catch(NumberFormatException e){
			//continue
		}
		return this.sum_of_sviv_kotas;
	}
	
	//Aux. attribute to pass some header values into a Validator for Items.
	private String header_svih_cont = null;
	public void setHeader_svih_cont(String value) {  this.header_svih_cont = value; }
	public String getHeader_svih_cont() {return this.header_svih_cont;}
	
	//Aux. attribute to pass some header values into a Validator for Items. (meddelandetyp)
	private String header_svih_mtyp = null;
	public void setHeader_svih_mtyp(String value) {  this.header_svih_mtyp = value; }
	public String getHeader_svih_mtyp() {return this.header_svih_mtyp;}
	

	private String sviv_syav = null;
	public void setSviv_syav(String value) {  this.sviv_syav = value; }
	public String getSviv_syav() {return this.sviv_syav;}
	
	private String sviv_syop = null;
	public void setSviv_syop(String value) {  this.sviv_syop = value; }
	public String getSviv_syop() {return this.sviv_syop;}
	
	private String sviv_syli = null;
	public void setSviv_syli(String value) {  this.sviv_syli = value; }
	public String getSviv_syli() {return this.sviv_syli;}
	
	private String sviv_vano = null;
	public void setSviv_vano(String value) {  this.sviv_vano = value; }
	public String getSviv_vano() {return this.sviv_vano;}
	
	private String sviv_ulkd = null;
	public void setSviv_ulkd(String value) {  this.sviv_ulkd = value; }
	public String getSviv_ulkd() {return this.sviv_ulkd;}
	
	private String sviv_vata = null;
	public void setSviv_vata(String value) {  this.sviv_vata = value; }
	public String getSviv_vata() {return this.sviv_vata;}
	
	private String sviv_vati = null;
	public void setSviv_vati(String value) {  this.sviv_vati = value; }
	public String getSviv_vati() {return this.sviv_vati;}
	
	private String sviv_eup1 = null;
	public void setSviv_eup1(String value) {  this.sviv_eup1 = value; }
	public String getSviv_eup1() {return this.sviv_eup1;}
	
	private String sviv_eup2 = null;
	public void setSviv_eup2(String value) {  this.sviv_eup2 = value; }
	public String getSviv_eup2() {return this.sviv_eup2;}
	
	private String sviv_brut = null;
	public void setSviv_brut(String value) {  this.sviv_brut = value; }
	public String getSviv_brut() {return this.sviv_brut;}
	
	private String sviv_fokd = null;
	public void setSviv_fokd(String value) {  this.sviv_fokd = value; }
	public String getSviv_fokd() {return this.sviv_fokd;}
	
	private String sviv_neto = null;
	public void setSviv_neto(String value) {  this.sviv_neto = value; }
	public String getSviv_neto() {return this.sviv_neto;}
	
	private String sviv_ankv = null;
	public void setSviv_ankv(String value) {  this.sviv_ankv = value; }
	public String getSviv_ankv() {return this.sviv_ankv;}
	
	
	private String sviv_vasl = null;
	public void setSviv_vasl(String value) {  this.sviv_vasl = value; }
	public String getSviv_vasl() {return this.sviv_vasl;}
	
	private String sviv_vas2 = null;
	public void setSviv_vas2(String value) {  this.sviv_vas2 = value; }
	public String getSviv_vas2() {return this.sviv_vas2;}
	
	private String sviv_vas3 = null;
	public void setSviv_vas3(String value) {  this.sviv_vas3 = value; }
	public String getSviv_vas3() {return this.sviv_vas3;}
	
	private String sviv_vas4 = null;
	public void setSviv_vas4(String value) {  this.sviv_vas4 = value; }
	public String getSviv_vas4() {return this.sviv_vas4;}
	
	private String sviv_vas5 = null;
	public void setSviv_vas5(String value) {  this.sviv_vas5 = value; }
	public String getSviv_vas5() {return this.sviv_vas5;}
	
	
	private String sviv_kota = "0";
	public void setSviv_kota(String value) {
		if(value==null || "".equals(value)){
			this.sviv_kota = "0";
		}else{
			this.sviv_kota = value;
		}
	}
	public String getSviv_kota() {
		if(this.sviv_kota==null || "".equals(this.sviv_kota)){
			return "0";
		}else{
			return this.sviv_kota;
		}
	}
	
	private String sviv_kot2 = null;
	public void setSviv_kot2(String value) {  this.sviv_kot2 = value; }
	public String getSviv_kot2() { return this.sviv_kot2;}
	
	private String sviv_kot3 = null;
	public void setSviv_kot3(String value) {  this.sviv_kot3 = value; }
	public String getSviv_kot3() { return this.sviv_kot3;}
	
	private String sviv_kot4 = null;
	public void setSviv_kot4(String value) {  this.sviv_kot4 = value; }
	public String getSviv_kot4() { return this.sviv_kot4;}
	
	private String sviv_kot5 = null;
	public void setSviv_kot5(String value) {  this.sviv_kot5 = value; }
	public String getSviv_kot5() { return this.sviv_kot5;}
	
	private String sviv_kosl = null;
	public void setSviv_kosl(String value) {  this.sviv_kosl = value; }
	public String getSviv_kosl() { return this.sviv_kosl;}
	
	private String sviv_kos2 = null;
	public void setSviv_kos2(String value) {  this.sviv_kos2 = value; }
	public String getSviv_kos2() { return this.sviv_kos2;}
	
	private String sviv_kos3 = null;
	public void setSviv_kos3(String value) {  this.sviv_kos3 = value; }
	public String getSviv_kos3() { return this.sviv_kos3;}
	
	private String sviv_kos4 = null;
	public void setSviv_kos4(String value) {  this.sviv_kos4 = value; }
	public String getSviv_kos4() { return this.sviv_kos4;}
	
	private String sviv_kos5 = null;
	public void setSviv_kos5(String value) {  this.sviv_kos5 = value; }
	public String getSviv_kos5() { return this.sviv_kos5;}
	
	private String sviv_godm = null;
	public void setSviv_godm(String value) {  this.sviv_godm = value; }
	public String getSviv_godm() { return this.sviv_godm;}
	
	private String sviv_god2 = null;
	public void setSviv_god2(String value) {  this.sviv_god2 = value; }
	public String getSviv_god2() { return this.sviv_god2;}
	
	private String sviv_god3 = null;
	public void setSviv_god3(String value) {  this.sviv_god3 = value; }
	public String getSviv_god3() { return this.sviv_god3;}
	
	private String sviv_god4 = null;
	public void setSviv_god4(String value) {  this.sviv_god4 = value; }
	public String getSviv_god4() { return this.sviv_god4;}
	
	private String sviv_god5 = null;
	public void setSviv_god5(String value) {  this.sviv_god5 = value; }
	public String getSviv_god5() { return this.sviv_god5; }
	
	private String sviv_vat4 = null;
	public void setSviv_vat4(String value) {  this.sviv_vat4 = value; }
	public String getSviv_vat4() { return this.sviv_vat4; }
	
	private String sviv_vat5 = null;
	public void setSviv_vat5(String value) {  this.sviv_vat5 = value; }
	public String getSviv_vat5() { return this.sviv_vat5; }
	
	private String sviv_kono = null;
	public void setSviv_kono(String value) {  this.sviv_kono = value; }
	public String getSviv_kono() { return this.sviv_kono; }
	
	private String sviv_suko = null;
	public void setSviv_suko(String value) {  this.sviv_suko = value; }
	public String getSviv_suko() { return this.sviv_suko; }
	
	private String sviv_suk6 = null;
	public void setSviv_suk6(String value) {  this.sviv_suk6 = value; }
	public String getSviv_suk6() { return this.sviv_suk6; }
	
	private String sviv_sukb = null;
	public void setSviv_sukb(String value) {  this.sviv_sukb = value; }
	public String getSviv_sukb() { return this.sviv_sukb; }
	
	private String sviv_sutx = null;
	public void setSviv_sutx(String value) {  this.sviv_sutx = value; }
	public String getSviv_sutx() { return this.sviv_sutx; }
	
	private String sviv_sut2 = null;
	public void setSviv_sut2(String value) {  this.sviv_sut2 = value; }
	public String getSviv_sut2() { return this.sviv_sut2; }
	
	private String sviv_sut3 = null;
	public void setSviv_sut3(String value) {  this.sviv_sut3 = value; }
	public String getSviv_sut3() { return this.sviv_sut3; }
	
	private String sviv_sut4 = null;
	public void setSviv_sut4(String value) {  this.sviv_sut4 = value; }
	public String getSviv_sut4() { return this.sviv_sut4; }
	
	private String sviv_sut5 = null;
	public void setSviv_sut5(String value) {  this.sviv_sut5 = value; }
	public String getSviv_sut5() { return this.sviv_sut5; }
	
	private String sviv_sut6 = null;
	public void setSviv_sut6(String value) {  this.sviv_sut6 = value; }
	public String getSviv_sut6() { return this.sviv_sut6; }
	
	private String sviv_sut7 = null;
	public void setSviv_sut7(String value) {  this.sviv_sut7 = value; }
	public String getSviv_sut7() { return this.sviv_sut7; }
	
	private String sviv_sut8 = null;
	public void setSviv_sut8(String value) {  this.sviv_sut8 = value; }
	public String getSviv_sut8() { return this.sviv_sut8; }
	
	private String sviv_sut9 = null;
	public void setSviv_sut9(String value) {  this.sviv_sut9 = value; }
	public String getSviv_sut9() { return this.sviv_sut9; }
	
	private String sviv_suta = null;
	public void setSviv_suta(String value) {  this.sviv_suta = value; }
	public String getSviv_suta() { return this.sviv_suta; }
	
	private String sviv_sutb = null;
	public void setSviv_sutb(String value) {  this.sviv_sutb = value; }
	public String getSviv_sutb() { return this.sviv_sutb; }

	private String sviv_sutc = null;
	public void setSviv_sutc(String value) {  this.sviv_sutc = value; }
	public String getSviv_sutc() { return this.sviv_sutc; }

	private String sviv_sutd = null;
	public void setSviv_sutd(String value) {  this.sviv_sutd = value; }
	public String getSviv_sutd() { return this.sviv_sutd; }

	private String sviv_sute = null;
	public void setSviv_sute(String value) {  this.sviv_sute = value; }
	public String getSviv_sute() { return this.sviv_sute; }

	private String sviv_sutf = null;
	public void setSviv_sutf(String value) {  this.sviv_sutf = value; }
	public String getSviv_sutf() { return this.sviv_sutf; }

	
	private String sviv_suok = null;
	public void setSviv_suok(String value) {  this.sviv_suok = value; }
	public String getSviv_suok() { return this.sviv_suok; }
	
	private String sviv_sukr = null;
	public void setSviv_sukr(String value) {  this.sviv_sukr = value; }
	public String getSviv_sukr() { return this.sviv_sukr; }
	
	private String sviv_suar = null;
	public void setSviv_suar(String value) {  this.sviv_suar = value; }
	public String getSviv_suar() { return this.sviv_suar; }
	
	private String sviv_atin = null;
	public void setSviv_atin(String value) {  this.sviv_atin = value; }
	public String getSviv_atin() { return this.sviv_atin; }
	
	private String sviv_stva = null;
	public void setSviv_stva(String value) {  this.sviv_stva = value; }
	public String getSviv_stva() { return this.sviv_stva; }

	private String sviv_stva2 = null;
	public void setSviv_stva2(String value) {  this.sviv_stva2 = value; }
	public String getSviv_stva2() { return this.sviv_stva2; }

	private String sviv_fabl = null;
	public void setSviv_fabl(String value) {  this.sviv_fabl = value; }
	public String getSviv_fabl() { return this.sviv_fabl; }
	
	private String sviv_bit1 = null;
	public void setSviv_bit1(String value) {  this.sviv_bit1 = value; }
	public String getSviv_bit1() { return this.sviv_bit1; }
	
	private String sviv_bit2 = null;
	public void setSviv_bit2(String value) {  this.sviv_bit2 = value; }
	public String getSviv_bit2() { return this.sviv_bit2; }
	
	private String sviv_bit3 = null;
	public void setSviv_bit3(String value) {  this.sviv_bit3 = value; }
	public String getSviv_bit3() { return this.sviv_bit3; }
	
	private String sviv_bit4 = null;
	public void setSviv_bit4(String value) {  this.sviv_bit4 = value; }
	public String getSviv_bit4() { return this.sviv_bit4; }
	
	private String sviv_bit5 = null;
	public void setSviv_bit5(String value) {  this.sviv_bit5 = value; }
	public String getSviv_bit5() { return this.sviv_bit5; }
	
	private String sviv_bit6 = null;
	public void setSviv_bit6(String value) {  this.sviv_bit6 = value; }
	public String getSviv_bit6() { return this.sviv_bit6; }
	
	private String sviv_bit7 = null;
	public void setSviv_bit7(String value) {  this.sviv_bit7 = value; }
	public String getSviv_bit7() { return this.sviv_bit7; }
	
	private String sviv_bit8 = null;
	public void setSviv_bit8(String value) {  this.sviv_bit8 = value; }
	public String getSviv_bit8() { return this.sviv_bit8; }
	
	private String sviv_bit9 = null;
	public void setSviv_bit9(String value) {  this.sviv_bit9 = value; }
	public String getSviv_bit9() { return this.sviv_bit9; }
	
	private String sviv_bii1 = null;
	public void setSviv_bii1(String value) {  this.sviv_bii1 = value; }
	public String getSviv_bii1() { return this.sviv_bii1; }
	
	private String sviv_bii2 = null;
	public void setSviv_bii2(String value) {  this.sviv_bii2 = value; }
	public String getSviv_bii2() { return this.sviv_bii2; }
	
	private String sviv_bii3 = null;
	public void setSviv_bii3(String value) {  this.sviv_bii3 = value; }
	public String getSviv_bii3() { return this.sviv_bii3; }
	
	private String sviv_bii4 = null;
	public void setSviv_bii4(String value) {  this.sviv_bii4 = value; }
	public String getSviv_bii4() { return this.sviv_bii4; }
	
	private String sviv_bii5 = null;
	public void setSviv_bii5(String value) {  this.sviv_bii5 = value; }
	public String getSviv_bii5() { return this.sviv_bii5; }
	
	private String sviv_bii6 = null;
	public void setSviv_bii6(String value) {  this.sviv_bii6 = value; }
	public String getSviv_bii6() { return this.sviv_bii6; }
	
	private String sviv_bii7 = null;
	public void setSviv_bii7(String value) {  this.sviv_bii7 = value; }
	public String getSviv_bii7() { return this.sviv_bii7; }
	
	private String sviv_bii8 = null;
	public void setSviv_bii8(String value) {  this.sviv_bii8 = value; }
	public String getSviv_bii8() { return this.sviv_bii8; }
	
	private String sviv_bii9 = null;
	public void setSviv_bii9(String value) {  this.sviv_bii9 = value; }
	public String getSviv_bii9() { return this.sviv_bii9; }
	
	private String sviv_co01 = null;
	public void setSviv_co01(String value) {  this.sviv_co01 = value; }
	public String getSviv_co01() { return this.sviv_co01; }
	
	private String sviv_co02 = null;
	public void setSviv_co02(String value) {  this.sviv_co02 = value; }
	public String getSviv_co02() { return this.sviv_co02; }
	
	private String sviv_co03 = null;
	public void setSviv_co03(String value) {  this.sviv_co03 = value; }
	public String getSviv_co03() { return this.sviv_co03; }
	
	private String sviv_co04 = null;
	public void setSviv_co04(String value) {  this.sviv_co04 = value; }
	public String getSviv_co04() { return this.sviv_co04; }
	
	private String sviv_co05 = null;
	public void setSviv_co05(String value) {  this.sviv_co05 = value; }
	public String getSviv_co05() { return this.sviv_co05; }
	
	private String sviv_co06 = null;
	public void setSviv_co06(String value) {  this.sviv_co06 = value; }
	public String getSviv_co06() { return this.sviv_co06; }
	
	private String sviv_co07 = null;
	public void setSviv_co07(String value) {  this.sviv_co07 = value; }
	public String getSviv_co07() { return this.sviv_co07; }
	
	private String sviv_co08 = null;
	public void setSviv_co08(String value) {  this.sviv_co08 = value; }
	public String getSviv_co08() { return this.sviv_co08; }
	
	private String sviv_co09 = null;
	public void setSviv_co09(String value) {  this.sviv_co09 = value; }
	public String getSviv_co09() { return this.sviv_co09; }
	
	private String sviv_co10 = null;
	public void setSviv_co10(String value) {  this.sviv_co10 = value; }
	public String getSviv_co10() { return this.sviv_co10; }
	
	private String sviv_co11 = null;
	public void setSviv_co11(String value) {  this.sviv_co11 = value; }
	public String getSviv_co11() { return this.sviv_co11; }
	
	private String sviv_co12 = null;
	public void setSviv_co12(String value) {  this.sviv_co12 = value; }
	public String getSviv_co12() { return this.sviv_co12; }
	
	private String sviv_co13 = null;
	public void setSviv_co13(String value) {  this.sviv_co13 = value; }
	public String getSviv_co13() { return this.sviv_co13; }
	
	private String sviv_co14 = null;
	public void setSviv_co14(String value) {  this.sviv_co14 = value; }
	public String getSviv_co14() { return this.sviv_co14; }
	
	private String sviv_co15 = null;
	public void setSviv_co15(String value) {  this.sviv_co15 = value; }
	public String getSviv_co15() { return this.sviv_co15; }
	
	private String sviv_co16 = null;
	public void setSviv_co16(String value) {  this.sviv_co16 = value; }
	public String getSviv_co16() { return this.sviv_co16; }
	
	private String sviv_co17 = null;
	public void setSviv_co17(String value) {  this.sviv_co17 = value; }
	public String getSviv_co17() { return this.sviv_co17; }
	
	private String sviv_co18 = null;
	public void setSviv_co18(String value) {  this.sviv_co18 = value; }
	public String getSviv_co18() { return this.sviv_co18; }
	
	private String sviv_co19 = null;
	public void setSviv_co19(String value) {  this.sviv_co19 = value; }
	public String getSviv_co19() { return this.sviv_co19; }
	
	private String sviv_co20 = null;
	public void setSviv_co20(String value) {  this.sviv_co20 = value; }
	public String getSviv_co20() { return this.sviv_co20; }
	
	private String sviv_tik1 = null;
	public void setSviv_tik1(String value) {  this.sviv_tik1 = value; }
	public String getSviv_tik1() { return this.sviv_tik1; }
	
	private String sviv_tik2 = null;
	public void setSviv_tik2(String value) {  this.sviv_tik2 = value; }
	public String getSviv_tik2() { return this.sviv_tik2; }
	
	private String sviv_tik3 = null;
	public void setSviv_tik3(String value) {  this.sviv_tik3 = value; }
	public String getSviv_tik3() { return this.sviv_tik3; }
	
	private String sviv_tik4 = null;
	public void setSviv_tik4(String value) {  this.sviv_tik4 = value; }
	public String getSviv_tik4() { return this.sviv_tik4; }
	
	private String sviv_tik5 = null;
	public void setSviv_tik5(String value) {  this.sviv_tik5 = value; }
	public String getSviv_tik5() { return this.sviv_tik5; }
	
	private String sviv_tik6 = null;
	public void setSviv_tik6(String value) {  this.sviv_tik6 = value; }
	public String getSviv_tik6() { return this.sviv_tik6; }
	
	private String sviv_tik7 = null;
	public void setSviv_tik7(String value) {  this.sviv_tik7 = value; }
	public String getSviv_tik7() { return this.sviv_tik7; }
	
	private String sviv_tik8 = null;
	public void setSviv_tik8(String value) {  this.sviv_tik8 = value; }
	public String getSviv_tik8() { return this.sviv_tik8; }
	
	private String sviv_tik9 = null;
	public void setSviv_tik9(String value) {  this.sviv_tik9 = value; }
	public String getSviv_tik9() { return this.sviv_tik9; }
	
	private String sviv_tit1 = null;
	public void setSviv_tit1(String value) {  this.sviv_tit1 = value; }
	public String getSviv_tit1() { return this.sviv_tit1; }
	
	private String sviv_tit2 = null;
	public void setSviv_tit2(String value) {  this.sviv_tit2 = value; }
	public String getSviv_tit2() { return this.sviv_tit2; }
	
	private String sviv_tit3 = null;
	public void setSviv_tit3(String value) {  this.sviv_tit3 = value; }
	public String getSviv_tit3() { return this.sviv_tit3; }
	
	private String sviv_tit4 = null;
	public void setSviv_tit4(String value) {  this.sviv_tit4 = value; }
	public String getSviv_tit4() { return this.sviv_tit4; }
	
	private String sviv_tit5 = null;
	public void setSviv_tit5(String value) {  this.sviv_tit5 = value; }
	public String getSviv_tit5() { return this.sviv_tit5; }
	
	private String sviv_tit6 = null;
	public void setSviv_tit6(String value) {  this.sviv_tit6 = value; }
	public String getSviv_tit6() { return this.sviv_tit6; }
	
	private String sviv_tit7 = null;
	public void setSviv_tit7(String value) {  this.sviv_tit7 = value; }
	public String getSviv_tit7() { return this.sviv_tit7; }
	
	private String sviv_tit8 = null;
	public void setSviv_tit8(String value) {  this.sviv_tit8 = value; }
	public String getSviv_tit8() { return this.sviv_tit8; }
	
	private String sviv_tit9 = null;
	public void setSviv_tit9(String value) {  this.sviv_tit9 = value; }
	public String getSviv_tit9() { return this.sviv_tit9; }
	
	private String sviv_tix1 = null;
	public void setSviv_tix1(String value) {  this.sviv_tix1 = value; }
	public String getSviv_tix1() { return this.sviv_tix1; }
	
	private String sviv_tix2 = null;
	public void setSviv_tix2(String value) {  this.sviv_tix2 = value; }
	public String getSviv_tix2() { return this.sviv_tix2; }
	
	private String sviv_tix3 = null;
	public void setSviv_tix3(String value) {  this.sviv_tix3 = value; }
	public String getSviv_tix3() { return this.sviv_tix3; }
	
	private String sviv_tix4 = null;
	public void setSviv_tix4(String value) {  this.sviv_tix4 = value; }
	public String getSviv_tix4() { return this.sviv_tix4; }
	
	private String sviv_tix5 = null;
	public void setSviv_tix5(String value) {  this.sviv_tix5 = value; }
	public String getSviv_tix5() { return this.sviv_tix5; }
	
	private String sviv_tix6 = null;
	public void setSviv_tix6(String value) {  this.sviv_tix6 = value; }
	public String getSviv_tix6() { return this.sviv_tix6; }
	
	private String sviv_tix7 = null;
	public void setSviv_tix7(String value) {  this.sviv_tix7 = value; }
	public String getSviv_tix7() { return this.sviv_tix7; }
	
	private String sviv_tix8 = null;
	public void setSviv_tix8(String value) {  this.sviv_tix8 = value; }
	public String getSviv_tix8() { return this.sviv_tix8; }
	
	private String sviv_tix9 = null;
	public void setSviv_tix9(String value) {  this.sviv_tix9 = value; }
	public String getSviv_tix9() { return this.sviv_tix9; }
	
	private String sviv_lagi = null;
	public void setSviv_lagi(String value) {  this.sviv_lagi = value; }
	public String getSviv_lagi() { return this.sviv_lagi;}
	
	private String sviv_lagt = null;
	public void setSviv_lagt(String value) {  this.sviv_lagt = value; }
	public String getSviv_lagt() { return this.sviv_lagt;}
    
	private String sviv_lagl = null;
	public void setSviv_lagl(String value) {  this.sviv_lagl = value; }
	public String getSviv_lagl() { return this.sviv_lagl;}
	
	private String sviv_call = null;
	public void setSviv_call(String value) {  this.sviv_call = value; }
	public String getSviv_call() { return this.sviv_call;}
	
	
	//Avgifter
	private String sviva_abk1 = null;
	public void setSviva_abk1(String value) {  this.sviva_abk1 = value; }
	public String getSviva_abk1() { return this.sviva_abk1;}
	
	private String sviva_abg1 = null;
	public void setSviva_abg1(String value) {  this.sviva_abg1 = value; }
	public String getSviva_abg1() { return this.sviva_abg1;}
	
	private String sviva_abs1 = null;
	public void setSviva_abs1(String value) {  this.sviva_abs1 = value; }
	public String getSviva_abs1() { return this.sviva_abs1;}
	
	private String sviva_abx1 = null;
	public void setSviva_abx1(String value) {  this.sviva_abx1 = value; }
	public String getSviva_abx1() { return this.sviva_abx1;}
	
	private String sviva_abb1 = null;
	public void setSviva_abb1(String value) {  this.sviva_abb1 = value; }
	public String getSviva_abb1() { return this.sviva_abb1;}

	
	private String sviva_abk2 = null;
	public void setSviva_abk2(String value) {  this.sviva_abk2 = value; }
	public String getSviva_abk2() { return this.sviva_abk2;}
	
	private String sviva_abg2 = null;
	public void setSviva_abg2(String value) {  this.sviva_abg2 = value; }
	public String getSviva_abg2() { return this.sviva_abg2;}
	
	private String sviva_abs2 = null;
	public void setSviva_abs2(String value) {  this.sviva_abs2 = value; }
	public String getSviva_abs2() { return this.sviva_abs2;}
	
	private String sviva_abx2 = null;
	public void setSviva_abx2(String value) {  this.sviva_abx2 = value; }
	public String getSviva_abx2() { return this.sviva_abx2;}
	
	private String sviva_abb2 = null;
	public void setSviva_abb2(String value) {  this.sviva_abb2 = value; }
	public String getSviva_abb2() { return this.sviva_abb2;}
	

	private String sviva_abk3 = null;
	public void setSviva_abk3(String value) {  this.sviva_abk3 = value; }
	public String getSviva_abk3() { return this.sviva_abk3;}
	
	private String sviva_abg3 = null;
	public void setSviva_abg3(String value) {  this.sviva_abg3 = value; }
	public String getSviva_abg3() { return this.sviva_abg3;}
	
	private String sviva_abs3 = null;
	public void setSviva_abs3(String value) {  this.sviva_abs3 = value; }
	public String getSviva_abs3() { return this.sviva_abs3;}
	
	private String sviva_abx3 = null;
	public void setSviva_abx3(String value) {  this.sviva_abx3 = value; }
	public String getSviva_abx3() { return this.sviva_abx3;}
	
	private String sviva_abb3 = null;
	public void setSviva_abb3(String value) {  this.sviva_abb3 = value; }
	public String getSviva_abb3() { return this.sviva_abb3;}

	
	private String sviva_abk4 = null;
	public void setSviva_abk4(String value) {  this.sviva_abk4 = value; }
	public String getSviva_abk4() { return this.sviva_abk4;}
	
	private String sviva_abg4 = null;
	public void setSviva_abg4(String value) {  this.sviva_abg4 = value; }
	public String getSviva_abg4() { return this.sviva_abg4;}
	
	private String sviva_abs4 = null;
	public void setSviva_abs4(String value) {  this.sviva_abs4 = value; }
	public String getSviva_abs4() { return this.sviva_abs4;}
	
	private String sviva_abx4 = null;
	public void setSviva_abx4(String value) {  this.sviva_abx4 = value; }
	public String getSviva_abx4() { return this.sviva_abx4;}
	
	private String sviva_abb4 = null;
	public void setSviva_abb4(String value) {  this.sviva_abb4 = value; }
	public String getSviva_abb4() { return this.sviva_abb4;}

	
	private String sviva_abk5 = null;
	public void setSviva_abk5(String value) {  this.sviva_abk5 = value; }
	public String getSviva_abk5() { return this.sviva_abk5;}
	
	private String sviva_abg5 = null;
	public void setSviva_abg5(String value) {  this.sviva_abg5 = value; }
	public String getSviva_abg5() { return this.sviva_abg5;}
	
	private String sviva_abs5 = null;
	public void setSviva_abs5(String value) {  this.sviva_abs5 = value; }
	public String getSviva_abs5() { return this.sviva_abs5;}
	
	private String sviva_abx5 = null;
	public void setSviva_abx5(String value) {  this.sviva_abx5 = value; }
	public String getSviva_abx5() { return this.sviva_abx5;}
	
	private String sviva_abb5 = null;
	public void setSviva_abb5(String value) {  this.sviva_abb5 = value; }
	public String getSviva_abb5() { return this.sviva_abb5;}

	private String sviv_err = null;
	public void setSviv_err(String value) {  this.sviv_err = value; }
	public String getSviv_err() { return this.sviv_err;}
	
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
