/**
 * 
 */
package no.systema.tds.tdsexport.model.jsonjackson.topic;

import java.lang.reflect.Field;
import java.util.*;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.main.util.NumberFormatterLocaleAware;

/**
 * @author oscardelatorre
 * @date Nov 25, 2012
 *
 */
public class JsonTdsExportSpecificTopicRecord extends JsonAbstractGrandFatherRecord {
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	
	private Integer sumOfAntalKolliInItemLines = 0;
	public void setSumOfAntalKolliInItemLines(Integer value) {  this.sumOfAntalKolliInItemLines = value; }
	public Integer getSumOfAntalKolliInItemLines() {return this.sumOfAntalKolliInItemLines;}
	
	private String sumOfAntalKolliInItemLinesStr = null;
	public String getSumOfAntalKolliInItemLinesStr() {
		this.sumOfAntalKolliInItemLinesStr = String.valueOf(this.sumOfAntalKolliInItemLines);
		return this.sumOfAntalKolliInItemLinesStr;
	}
	
	//Bruttovikt (SUM) (3 decimals)
	private Double sumOfGrossWeightInItemLines = 0.00D;
	public void setSumOfGrossWeightInItemLines(Double value) {  this.sumOfGrossWeightInItemLines = value; }
	public Double getSumOfGrossWeightInItemLines() {return this.sumOfGrossWeightInItemLines;}
	
	private String sumOfGrossWeightInItemLinesStr = null;
	public String getSumOfGrossWeightInItemLinesStr() {
		//this.sumOfGrossWeightInItemLinesStr = String.valueOf(this.sumOfGrossWeightInItemLines);
		String tmpStr = this.numberFormatter.getDoubleToPlainString(this.sumOfGrossWeightInItemLines, 3);
		this.sumOfGrossWeightInItemLinesStr = tmpStr;
		return this.sumOfGrossWeightInItemLinesStr;
	}
	
	//Fakt.belopp (SUM) (3 decimals)
	private Double sumOfInvoiceAmountInItemLines = 0.00D;
	public void setSumOfInvoiceAmountInItemLines(Double value) {  this.sumOfInvoiceAmountInItemLines = value; }
	public Double getSumOfInvoiceAmountInItemLines() {return this.sumOfInvoiceAmountInItemLines;}
	
	private String sumOfInvoiceAmountInItemLinesStr = null;
	public String getSumOfInvoiceAmountInItemLinesStr() {
		this.sumOfInvoiceAmountInItemLinesStr = this.numberFormatter.getDoubleToPlainString(this.sumOfInvoiceAmountInItemLines, 3);//String.valueOf(this.sumOfInvoiceAmountInItemLines);
		return this.sumOfInvoiceAmountInItemLinesStr;
	}
	
	//Used when different currencies exist. The main currency must be = SEK
	private String invoiceListTotValidCurrency = null;
	public void setInvoiceListTotValidCurrency(String value) {  this.invoiceListTotValidCurrency = value; }
	public String getInvoiceListTotValidCurrency() { return this.invoiceListTotValidCurrency; }
	
	private String invoiceListTotSum = null;
	public void setInvoiceListTotSum(String value) {  this.invoiceListTotSum = value; }
	public String getInvoiceListTotSum() { return this.invoiceListTotSum; }
	
	private String invoiceListTotKurs = null;
	public void setInvoiceListTotKurs(String value) {  this.invoiceListTotKurs = value; }
	public String getInvoiceListTotKurs() { return this.invoiceListTotKurs; }
	
	
	//in order to validate before a "send topic"
	private boolean validUpdate = false;
	public void setValidUpdate(boolean value) {  this.validUpdate = value; }
	public boolean isValidUpdate() {return this.validUpdate;}

	private String testAvdFlag = null;
	public void setTestAvdFlag(String value) {  this.testAvdFlag = value; }
	public String getTestAvdFlag() {return this.testAvdFlag;}
	
	
	//test indicator in production
	private String sveh_0035 = null;
	public void setSveh_0035(String value) {  this.sveh_0035 = value; }
	public String getSveh_0035() {return this.sveh_0035;}

	//SVEH_SYAV SYSPED Avdeling 1 4 4 4 0 S
	private String sveh_syav = null;
	public void setSveh_syav(String value) {  this.sveh_syav = value; }
	public String getSveh_syav() {return this.sveh_syav;}
	//SVEH_SYOP SYSPED Oppdragsnr 5 11 7 7 0 S
	private String sveh_syop = null;
	public void setSveh_syop(String value) {  this.sveh_syop = value; }
	public String getSveh_syop() {return this.sveh_syop;}
	
	private String sveh_prof = null;
	public void setSveh_prof(String value) {  this.sveh_prof = value; }
	public String getSveh_prof() {return this.sveh_prof;}
	
	private String h_xref = null;
	public void setH_xref(String value) {  this.h_xref = value; }
	public String getH_xref() { return this.h_xref;}
	
	private String sveh_tart = null;
	public void setSveh_tart(String value) {  this.sveh_tart = value; }
	public String getSveh_tart() {return this.sveh_tart;}
	
	private String sveh_dek1 = null;
	public void setSveh_dek1(String value) {  this.sveh_dek1 = value; }
	public String getSveh_dek1() {return this.sveh_dek1;}
	
	private String sveh_dek2 = null;
	public void setSveh_dek2(String value) {  this.sveh_dek2 = value; }
	public String getSveh_dek2() {return this.sveh_dek2;}
	
	private String sveh_kenh = null;
	public void setSveh_kenh(String value) {  this.sveh_kenh = value; }
	public String getSveh_kenh() {return this.sveh_kenh;}
	
	private String sveh_tuid = null;
	public void setSveh_tuid(String value) {  this.sveh_tuid = value; }
	public String getSveh_tuid() {return this.sveh_tuid;}
	
	private String sveh_mtyp = null;
	public void setSveh_mtyp(String value) {  this.sveh_mtyp = value; }
	public String getSveh_mtyp() {return this.sveh_mtyp;}
	
	private String sveh_sydt = null;
	public void setSveh_sydt(String value) {  this.sveh_sydt = value; }
	public String getSveh_sydt() {return this.sveh_sydt;}
	
	private String sveh_syst = null;
	public void setSveh_syst(String value) {  this.sveh_syst = value; }
	public String getSveh_syst() {return this.sveh_syst;}
	
	private String sveh_sysg = null;
	public void setSveh_sysg(String value) {  this.sveh_sysg = value; }
	public String getSveh_sysg() {return this.sveh_sysg;}
	
	
	//SVEH_AVKN Avs�ndare, Knr 76 83 8 8 0 S
	private String sveh_avkn = null;
	public void setSveh_avkn(String value) {  this.sveh_avkn = value; }
	public String getSveh_avkn() {return this.sveh_avkn;}
	
	//SVEH_AVEO Avs�ndare, EORI 84 100 17 A
	private String sveh_aveo = null;
	public void setSveh_aveo(String value) {  this.sveh_aveo = value; }
	public String getSveh_aveo() {return this.sveh_aveo;}
	
	//SVEH_AVNA Avs�ndare, namn 101 135 35 A
	private String sveh_avna = null;
	public void setSveh_avna(String value) {  this.sveh_avna = value; }
	public String getSveh_avna() {return this.sveh_avna;}
	
	//SVEH_AVA1 Avs�ndare, adr1 136 170 35 A
	private String sveh_ava1 = null;
	public void setSveh_ava1(String value) {  this.sveh_ava1 = value; }
	public String getSveh_ava1() {return this.sveh_ava1;}
	
	//SVEH_AVA2 Avs�ndare, adr2 171 205 35 A
	private String sveh_ava2 = null;
	public void setSveh_ava2(String value) {  this.sveh_ava2 = value; }
	public String getSveh_ava2() {return this.sveh_ava2;}
	
	//SVEH_AVPN Avs�ndare, p-nr 206 214 9 A
	private String sveh_avpn = null;
	public void setSveh_avpn(String value) {  this.sveh_avpn = value; }
	public String getSveh_avpn() {return this.sveh_avpn;}
	
	//SVEH_AVPA Avs�ndare, p-adr 215 249 35 A
	private String sveh_avpa = null;
	public void setSveh_avpa(String value) {  this.sveh_avpa = value; }
	public String getSveh_avpa() {return this.sveh_avpa;}
	
	//SVEH_AVLK Avs�ndare, l-kod 250 251 2 A
	private String sveh_avlk = null;
	public void setSveh_avlk(String value) {  this.sveh_avlk = value; }
	public String getSveh_avlk() {return this.sveh_avlk;}
	
	private String sveh_avha = null;
	public void setSveh_avha(String value) {  this.sveh_avha = value; }
	public String getSveh_avha() {return this.sveh_avha;}
	
	private String sveh_avtl = null;
	public void setSveh_avtl(String value) {  this.sveh_avtl = value; }
	public String getSveh_avtl() {return this.sveh_avtl;}
	
	
	//SVEH_MOKN Mottagare, Knr 442 449 8 8 0 S
	private String sveh_mokn = null;
	public void setSveh_mokn(String value) {  this.sveh_mokn = value; }
	public String getSveh_mokn() {return this.sveh_mokn;}
	
	private String sveh_moeo = null;
	public void setSveh_moeo(String value) {  this.sveh_moeo = value; }
	public String getSveh_moeo() {return this.sveh_moeo;}
	
	//SVEH_MONA Mottagare, namn 450 484 35 A
	private String sveh_mona = null;
	public void setSveh_mona(String value) {  this.sveh_mona = value; }
	public String getSveh_mona() {return this.sveh_mona;}
	
	//SVEH_MOA1 Mottagare, adr1 485 519 35 A
	private String sveh_moa1 = null;
	public void setSveh_moa1(String value) {  this.sveh_moa1 = value; }
	public String getSveh_moa1() {return this.sveh_moa1;}
	
	//SVEH_MOA2 Mottagare, adr2 520 554 35 A
	private String sveh_moa2 = null;
	public void setSveh_moa2(String value) {  this.sveh_moa2 = value; }
	public String getSveh_moa2() {return this.sveh_moa2;}
	
	//SVEH_MOPN Mottagare, p-nr 555 563 9 A
	private String sveh_mopn = null;
	public void setSveh_mopn(String value) {  this.sveh_mopn = value; }
	public String getSveh_mopn() {return this.sveh_mopn;}
	
	//SVEH_MOPA Mottagare, p-adr 564 598 35 A
	private String sveh_mopa = null;
	public void setSveh_mopa(String value) {  this.sveh_mopa = value; }
	public String getSveh_mopa() {return this.sveh_mopa;}
	
	//SVEH_MOLK Mottagare, l-kod
	private String sveh_molk = null;
	public void setSveh_molk(String value) {  this.sveh_molk = value; }
	public String getSveh_molk() {return this.sveh_molk;}
	
	private String sveh_kota = null;
	public void setSveh_kota(String value) {  this.sveh_kota = value; }
	public String getSveh_kota() {return this.sveh_kota;}
	
	private String sveh_rfab = null;
	public void setSveh_rfab(String value) {  this.sveh_rfab = value; }
	public String getSveh_rfab() {return this.sveh_rfab;}
	
	private String sveh_rfac = null;
	public void setSveh_rfac(String value) {  this.sveh_rfac = value; }
	public String getSveh_rfac() {return this.sveh_rfac;}
	
	private String sveh_rfac2 = null;
	public void setSveh_rfac2(String value) {  this.sveh_rfac2 = value; }
	public String getSveh_rfac2() {return this.sveh_rfac2;}
	
	private String sveh_rfac3 = null;
	public void setSveh_rfac3(String value) {  this.sveh_rfac3 = value; }
	public String getSveh_rfac3() {return this.sveh_rfac3;}
	
	
	private String sveh_kvsa = null;
	public void setSveh_kvsa(String value) {  this.sveh_kvsa = value; }
	public String getSveh_kvsa() { return this.sveh_kvsa;}
	
	private String sveh_dkkn = null;
	public void setSveh_dkkn(String value) {  this.sveh_dkkn = value; }
	public String getSveh_dkkn() { return this.sveh_dkkn;}
	
	private String sveh_dkeo = null;
	public void setSveh_dkeo(String value) {  this.sveh_dkeo = value; }
	public String getSveh_dkeo() { return this.sveh_dkeo;}
	
	private String sveh_dklk = null;
	public void setSveh_dklk(String value) {  this.sveh_dklk = value; }
	public String getSveh_dklk() { return this.sveh_dklk;}
	
	private String sveh_dkna = null;
	public void setSveh_dkna(String value) {  this.sveh_dkna = value; }
	public String getSveh_dkna() { return this.sveh_dkna;}
	
	private String sveh_dka1 = null;
	public void setSveh_dka1(String value) {  this.sveh_dka1 = value; }
	public String getSveh_dka1() { return this.sveh_dka1;}
	
	private String sveh_dka2 = null;
	public void setSveh_dka2(String value) {  this.sveh_dka2 = value; }
	public String getSveh_dka2() { return this.sveh_dka2;}
	
	private String sveh_dkpa = null;
	public void setSveh_dkpa(String value) {  this.sveh_dkpa = value; }
	public String getSveh_dkpa() { return this.sveh_dkpa;}
	
	private String sveh_dkpn = null;
	public void setSveh_dkpn(String value) {  this.sveh_dkpn = value; }
	public String getSveh_dkpn() { return this.sveh_dkpn;}
	
	private String sveh_omeo = null;
	public void setSveh_omeo(String value) {  this.sveh_omeo = value; }
	public String getSveh_omeo() { return this.sveh_omeo;}
	
	private String sveh_omty = null;
	public void setSveh_omty(String value) {  this.sveh_omty = value; }
	public String getSveh_omty() { return this.sveh_omty;}
	
	private String sveh_omha = null;
	public void setSveh_omha(String value) {  this.sveh_omha = value; }
	public String getSveh_omha() { return this.sveh_omha;}
	
	private String sveh_omtl = null;
	public void setSveh_omtl(String value) {  this.sveh_omtl = value; }
	public String getSveh_omtl() { return this.sveh_omtl;}
	
	
	private String sveh_avut = null;
	public void setSveh_avut(String value) {  this.sveh_avut = value; }
	public String getSveh_avut() { return this.sveh_avut;}
	
	private String sveh_aukd = null;
	public void setSveh_aukd(String value) {  this.sveh_aukd = value; }
	public String getSveh_aukd() { return this.sveh_aukd;}
	
	private String sveh_aube = null;
	public void setSveh_aube(String value) {  this.sveh_aube = value; }
	public String getSveh_aube() { return this.sveh_aube;}
	
	private String sveh_trid = null;
	public void setSveh_trid(String value) {  this.sveh_trid = value; }
	public String getSveh_trid() { return this.sveh_trid;}
	
	private String sveh_trlk = null;
	public void setSveh_trlk(String value) {  this.sveh_trlk = value; }
	public String getSveh_trlk() { return this.sveh_trlk;}
	
	private String sveh_cont = null;
	public void setSveh_cont(String value) {  this.sveh_cont = value; }
	public String getSveh_cont() { return this.sveh_cont;}
	
	private String sveh_conn = null;
	public void setSveh_conn(String value) {  this.sveh_conn = value; }
	public String getSveh_conn() { return this.sveh_conn;}
	
	private String sveh_trai = null;
	public void setSveh_trai(String value) {  this.sveh_trai = value; }
	public String getSveh_trai() { return this.sveh_trai;}
	
	private String sveh_tral = null;
	public void setSveh_tral(String value) {  this.sveh_tral = value; }
	public String getSveh_tral() { return this.sveh_tral;}
	
	private String sveh_faty = null;
	public void setSveh_faty(String value) {  this.sveh_faty = value; }
	public String getSveh_faty() { return this.sveh_faty;}
	
	private String sveh_fatx = null;
	public void setSveh_fatx(String value) {  this.sveh_fatx = value; }
	public String getSveh_fatx() { return this.sveh_fatx;}
	
	private String sveh_vakd = null;
	public void setSveh_vakd(String value) {  this.sveh_vakd = value; }
	public String getSveh_vakd() { return this.sveh_vakd;}
	
	private String sveh_vaku = null;
	public void setSveh_vaku(String value) {  this.sveh_vaku = value; }
	public String getSveh_vaku() { return this.sveh_vaku;}
	
	private String sveh_vaom = null;
	public void setSveh_vaom(String value) {  this.sveh_vaom = value; }
	public String getSveh_vaom() { return this.sveh_vaom;}
	
	private String sveh_fabl = null;
	public void setSveh_fabl(String value) {  this.sveh_fabl = value; }
	public String getSveh_fabl() { return this.sveh_fabl;}
	
	private Double sveh_fabl_dbl = 0.00D;
	public void setSveh_fabl_dbl(Double value) {  this.sveh_fabl_dbl = value; }
	public Double getSveh_fabl_dbl() {
		Double retval = sveh_fabl_dbl;
		if(sveh_fabl!=null && !"".equals(sveh_fabl)){
			try{
				retval =  Double.parseDouble(this.sveh_fabl.replace(",", "."));
			}catch(Exception e){
				//nothing
			}
		}
		return retval;
	}
	private String sveh_trgr = null;
	public void setSveh_trgr(String value) {  this.sveh_trgr = value; }
	public String getSveh_trgr() { return this.sveh_trgr;}
	
	private String sveh_trin = null;
	public void setSveh_trin(String value) {  this.sveh_trin = value; }
	public String getSveh_trin() { return this.sveh_trin;}
	
	private String sveh_grkd = null;
	public void setSveh_grkd(String value) {  this.sveh_grkd = value; }
	public String getSveh_grkd() { return this.sveh_grkd;}
	
	private String sveh_godm = null;
	public void setSveh_godm(String value) {  this.sveh_godm = value; }
	public String getSveh_godm() { return this.sveh_godm;}
	
	private String sveh_golk = null;
	public void setSveh_golk(String value) {  this.sveh_golk = value; }
	public String getSveh_golk() { return this.sveh_golk;}
	
	private String sveh_godn = null;
	public void setSveh_godn(String value) {  this.sveh_godn = value; }
	public String getSveh_godn() { return this.sveh_godn;}
	
	private String sveh_kosl = null;
	public void setSveh_kosl(String value) {  this.sveh_kosl = value; }
	public String getSveh_kosl() { return this.sveh_kosl;}
	
	private String sveh_vasl = null;
	public void setSveh_vasl(String value) {  this.sveh_vasl = value; }
	public String getSveh_vasl() { return this.sveh_vasl;}
	
	private String sveh_brut = null;
	public void setSveh_brut(String value) {  this.sveh_brut = value; }
	public String getSveh_brut() { return this.sveh_brut;}
	
	private Double sveh_brut_dbl = 0.00D;
	public void setSveh_brut_dbl(Double value) {  this.sveh_brut_dbl = value; }
	public Double getSveh_brut_dbl() {
		Double retval = sveh_brut_dbl;
		if(sveh_brut!=null && !"".equals(sveh_brut)){
			try{
				retval =  Double.parseDouble(this.sveh_brut.replace(",", "."));
			}catch(Exception e){
				//nothing
			}
		}
		return retval;
	}
	
	
	private String sveh_suti = null;
	public void setSveh_suti(String value) {  this.sveh_suti = value; }
	public String getSveh_suti() { return this.sveh_suti;}
	
	private String sveh_abub = null;
	public void setSveh_abub(String value) {  this.sveh_abub = value; }
	public String getSveh_abub() { return this.sveh_abub;}
	
	private String sveh_eup1 = null;
	public void setSveh_eup1(String value) {  this.sveh_eup1 = value; }
	public String getSveh_eup1() { return this.sveh_eup1;}
	
	private String sveh_eup2 = null;
	public void setSveh_eup2(String value) {  this.sveh_eup2 = value; }
	public String getSveh_eup2() { return this.sveh_eup2;}
		
	private String sveh_call = null;
	public void setSveh_call(String value) {  this.sveh_call = value; }
	public String getSveh_call() { return this.sveh_call;}
	
	private String sveh_upps = null;
	public void setSveh_upps(String value) {  this.sveh_upps = value; }
	public String getSveh_upps() { return this.sveh_upps;}
	
	private String sveh_tid1 = null;
	public void setSveh_tid1(String value) {  this.sveh_tid1 = value; }
	public String getSveh_tid1() { return this.sveh_tid1;}
	
	private String sveh_tid2 = null;
	public void setSveh_tid2(String value) {  this.sveh_tid2 = value; }
	public String getSveh_tid2() { return this.sveh_tid2;}
	
	private String sveh_tid3 = null;
	public void setSveh_tid3(String value) {  this.sveh_tid3 = value; }
	public String getSveh_tid3() { return this.sveh_tid3;}
	
	private String sveh_tid21 = null;
	public void setSveh_tid21(String value) {  this.sveh_tid21 = value; }
	public String getSveh_tid21() { return this.sveh_tid21;}
	
	private String sveh_tid22 = null;
	public void setSveh_tid22(String value) {  this.sveh_tid22 = value; }
	public String getSveh_tid22() { return this.sveh_tid22;}
	
	private String sveh_tid23 = null;
	public void setSveh_tid23(String value) {  this.sveh_tid23 = value; }
	public String getSveh_tid23() { return this.sveh_tid23;}
    
	private String sveh_kleo = null;
	public void setSveh_kleo(String value) {  this.sveh_kleo = value; }
	public String getSveh_kleo() { return this.sveh_kleo;}
	
	private String sveh_trnr = null;
	public void setSveh_trnr(String value) {  this.sveh_trnr = value; }
	public String getSveh_trnr() { return this.sveh_trnr;}
	
	private String sveh_lagi = null;
	public void setSveh_lagi(String value) {  this.sveh_lagi = value; }
	public String getSveh_lagi() { return this.sveh_lagi;}
	
	private String sveh_lagt = null;
	public void setSveh_lagt(String value) {  this.sveh_lagt = value; }
	public String getSveh_lagt() { return this.sveh_lagt;}
    
	private String sveh_lagl = null;
	public void setSveh_lagl(String value) {  this.sveh_lagl = value; }
	public String getSveh_lagl() { return this.sveh_lagl;}
	
	private String sveh_utfa = null;
	public void setSveh_utfa(String value) {  this.sveh_utfa = value; }
	public String getSveh_utfa() { return this.sveh_utfa;}
	
	private String sveh_last = null;
	public void setSveh_last(String value) {  this.sveh_last = value; }
	public String getSveh_last() { return this.sveh_last;}
	
	private String sveh_taxd = null;
	public void setSveh_taxd(String value) {  this.sveh_taxd = value; }
	public String getSveh_taxd() { return this.sveh_taxd;}
	
	private String sveh_beat = null;
	public void setSveh_beat(String value) {  this.sveh_beat = value; }
	public String getSveh_beat() { return this.sveh_beat;}
	
	private String sveh_betk = null;
	public void setSveh_betk(String value) {  this.sveh_betk = value; }
	public String getSveh_betk() { return this.sveh_betk;}
		
	private String sveh_komr = null;
	public void setSveh_komr(String value) {  this.sveh_komr = value; }
	public String getSveh_komr() { return this.sveh_komr;}

	
	private String sveh_res1 = null;
	public void setSveh_res1(String value) {  this.sveh_res1 = value; }
	public String getSveh_res1() { return this.sveh_res1;}
	
	private String sveh_res2 = null;
	public void setSveh_res2(String value) {  this.sveh_res2 = value; }
	public String getSveh_res2() { return this.sveh_res2;}
	
	private String sveh_res3 = null;
	public void setSveh_res3(String value) {  this.sveh_res3 = value; }
	public String getSveh_res3() { return this.sveh_res3;}
	
	private String sveh_res4 = null;
	public void setSveh_res4(String value) {  this.sveh_res4 = value; }
	public String getSveh_res4() { return this.sveh_res4;}
	
	private String sveh_res5 = null;
	public void setSveh_res5(String value) {  this.sveh_res5 = value; }
	public String getSveh_res5() { return this.sveh_res5;}

	private String sveh_res6 = null;
	public void setSveh_res6(String value) {  this.sveh_res6 = value; }
	public String getSveh_res6() { return this.sveh_res6;}

	private String sveh_res7 = null;
	public void setSveh_res7(String value) {  this.sveh_res7 = value; }
	public String getSveh_res7() { return this.sveh_res7;}

	private String sveh_res8 = null;
	public void setSveh_res8(String value) {  this.sveh_res8 = value; }
	public String getSveh_res8() { return this.sveh_res8;}

	private String sveh_sel1 = null;
	public void setSveh_sel1(String value) {  this.sveh_sel1 = value; }
	public String getSveh_sel1() { return this.sveh_sel1;}

	private String sveh_sel2 = null;
	public void setSveh_sel2(String value) {  this.sveh_sel2 = value; }
	public String getSveh_sel2() { return this.sveh_sel2;}

	private String sveh_saom = null;
	public void setSveh_saom(String value) {  this.sveh_saom = value; }
	public String getSveh_saom() { return this.sveh_saom;}

	private String sveh_sgdk = null;
	public void setSveh_sgdk(String value) {  this.sveh_sgdk = value; }
	public String getSveh_sgdk() { return this.sveh_sgdk;}

	private String sveh_sgme = null;
	public void setSveh_sgme(String value) {  this.sveh_sgme = value; }
	public String getSveh_sgme() { return this.sveh_sgme;}

	private String sveh_sgut = null;
	public void setSveh_sgut(String value) {  this.sveh_sgut = value; }
	public String getSveh_sgut() { return this.sveh_sgut;}

	private String sveh_sgid = null;
	public void setSveh_sgid(String value) {  this.sveh_sgid = value; }
	public String getSveh_sgid() { return this.sveh_sgid;}
	
	private String sveh_sgdt = null;
	public void setSveh_sgdt(String value) {  this.sveh_sgdt = value; }
	public String getSveh_sgdt() { return this.sveh_sgdt;}
    
	private String sveh_byte = null;
	public void setSveh_byte(String value) {  this.sveh_byte = value; }
	public String getSveh_byte() { return this.sveh_byte;}
    
	private String sveh_kval = null;
	public void setSveh_kval(String value) {  this.sveh_kval = value; }
	public String getSveh_kval() { return this.sveh_kval;}
	
	private String sveh_vuva = null;
	public void setSveh_vuva(String value) {  this.sveh_vuva = value; }
	public String getSveh_vuva() { return this.sveh_vuva;}
	
	private String sveh_vuku = null;
	public void setSveh_vuku(String value) {  this.sveh_vuku = value; }
	public String getSveh_vuku() { return this.sveh_vuku;}
	
	private String sveh_vufr = null;
	public void setSveh_vufr(String value) {  this.sveh_vufr = value; }
	public String getSveh_vufr() { return this.sveh_vufr;}
	
	
	/**
	 * Used for java reflection in other classes
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
