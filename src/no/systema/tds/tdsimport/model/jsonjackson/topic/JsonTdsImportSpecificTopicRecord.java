/**
 * 
 */
package no.systema.tds.tdsimport.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.main.util.NumberFormatterLocaleAware;
import java.util.*;
import java.lang.reflect.Field;
/**
 * @author oscardelatorre
 * @date Sep 2, 2013
 *
 */
public class JsonTdsImportSpecificTopicRecord extends JsonAbstractGrandFatherRecord{
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	
	//Antal kolli (SUM)
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
	public void setSumOfGrossWeightInItemLinesStr(String value) {  this.sumOfGrossWeightInItemLinesStr = value; }
	public String getSumOfGrossWeightInItemLinesStr() {
		if(this.sumOfGrossWeightInItemLinesStr!=null){
			//nothing
		}else{
			String tmpStr = this.numberFormatter.getDoubleToPlainString(this.sumOfGrossWeightInItemLines, 3);
			this.sumOfGrossWeightInItemLinesStr = tmpStr;
		}
		return this.sumOfGrossWeightInItemLinesStr;
	}
	
	//Fakt.belopp (SUM) (3 decimals)
	private Double sumOfInvoiceAmountInItemLines = 0.00D;
	public void setSumOfInvoiceAmountInItemLines(Double value) {  this.sumOfInvoiceAmountInItemLines = value; }
	public Double getSumOfInvoiceAmountInItemLines() {return this.sumOfInvoiceAmountInItemLines;}
	
	private String sumOfInvoiceAmountInItemLinesStr = null;
	public String getSumOfInvoiceAmountInItemLinesStr() {
		this.sumOfInvoiceAmountInItemLinesStr = String.valueOf(this.sumOfInvoiceAmountInItemLines);
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
	
	
	
	//test indicator in production
	private String svih_0035 = null;
	public void setSvih_0035(String value) {  this.svih_0035 = value; }
	public String getSvih_0035() {return this.svih_0035;}
		
	private String svih_syav = null;
	public void setSvih_syav(String value) {  this.svih_syav = value; }
	public String getSvih_syav() {return this.svih_syav;}
	
	private String svih_syop = null;
	public void setSvih_syop(String value) {  this.svih_syop = value; }
	public String getSvih_syop() {return this.svih_syop;}
	
	private String h_xref = null;
	public void setH_xref(String value) {  this.h_xref = value; }
	public String getH_xref() { return this.h_xref;}
	
	private String svih_tart = null;
	public void setSvih_tart(String value) {  this.svih_tart = value; }
	public String getSvih_tart() {return this.svih_tart;}
	
	private String svih_dek1 = null;
	public void setSvih_dek1(String value) {  this.svih_dek1 = value; }
	public String getSvih_dek1() {return this.svih_dek1;}
	
	private String svih_dek2 = null;
	public void setSvih_dek2(String value) {  this.svih_dek2 = value; }
	public String getSvih_dek2() {return this.svih_dek2;}
	
	private String svih_kenh = null;
	public void setSvih_kenh(String value) {  this.svih_kenh = value; }
	public String getSvih_kenh() {return this.svih_kenh;}
	
	private String svih_tuid = null;
	public void setSvih_tuid(String value) {  this.svih_tuid = value; }
	public String getSvih_tuid() {return this.svih_tuid;}
	
	private String svih_mtyp = null;
	public void setSvih_mtyp(String value) {  this.svih_mtyp = value; }
	public String getSvih_mtyp() {return this.svih_mtyp;}
	
	private String svih_sydt = null;
	public void setSvih_sydt(String value) {  this.svih_sydt = value; }
	public String getSvih_sydt() {return this.svih_sydt;}
	
	private String svih_syst = null;
	public void setSvih_syst(String value) {  this.svih_syst = value; }
	public String getSvih_syst() {return this.svih_syst;}
	
	private String svih_sysg = null;
	public void setSvih_sysg(String value) {  this.svih_sysg = value; }
	public String getSvih_sysg() {return this.svih_sysg;}
	
	
	private String svih_avkn = null;
	public void setSvih_avkn(String value) {  this.svih_avkn = value; }
	public String getSvih_avkn() {return this.svih_avkn;}
	
	private String svih_aveo = null;
	public void setSvih_aveo(String value) {  this.svih_aveo = value; }
	public String getSvih_aveo() {return this.svih_aveo;}
	
	private String svih_avna = null;
	public void setSvih_avna(String value) {  this.svih_avna = value; }
	public String getSvih_avna() {return this.svih_avna;}
	
	private String svih_ava1 = null;
	public void setSvih_ava1(String value) {  this.svih_ava1 = value; }
	public String getSvih_ava1() {return this.svih_ava1;}
	
	private String svih_ava2 = null;
	public void setSvih_ava2(String value) {  this.svih_ava2 = value; }
	public String getSvih_ava2() {return this.svih_ava2;}
	
	private String svih_avpn = null;
	public void setSvih_avpn(String value) {  this.svih_avpn = value; }
	public String getSvih_avpn() {return this.svih_avpn;}
	
	private String svih_avpa = null;
	public void setSvih_avpa(String value) {  this.svih_avpa = value; }
	public String getSvih_avpa() {return this.svih_avpa;}
	
	private String svih_avlk = null;
	public void setSvih_avlk(String value) {  this.svih_avlk = value; }
	public String getSvih_avlk() {return this.svih_avlk;}
	
	
	//**************
	private String svih_avha = null;
	public void setSvih_avha(String value) {  this.svih_avha = value; }
	public String getSvih_avha() {return this.svih_avha;}
	
	private String svih_avtl = null;
	public void setSvih_avtl(String value) {  this.svih_avtl = value; }
	public String getSvih_avtl() {return this.svih_avtl;}
	//**************
	
	
	
	//SVIH_MOKN Mottagare, Knr 442 449 8 8 0 S
	private String svih_mokn = null;
	public void setSvih_mokn(String value) {  this.svih_mokn = value; }
	public String getSvih_mokn() {return this.svih_mokn;}
	
	private String svih_moeo = null;
	public void setSvih_moeo(String value) {  this.svih_moeo = value; }
	public String getSvih_moeo() {return this.svih_moeo;}
	
	//SVIH_MONA Mottagare, namn 450 484 35 A
	private String svih_mona = null;
	public void setSvih_mona(String value) {  this.svih_mona = value; }
	public String getSvih_mona() {return this.svih_mona;}
	
	//SVIH_MOA1 Mottagare, adr1 485 519 35 A
	private String svih_moa1 = null;
	public void setSvih_moa1(String value) {  this.svih_moa1 = value; }
	public String getSvih_moa1() {return this.svih_moa1;}
	
	//SVIH_MOA2 Mottagare, adr2 520 554 35 A
	private String svih_moa2 = null;
	public void setSvih_moa2(String value) {  this.svih_moa2 = value; }
	public String getSvih_moa2() {return this.svih_moa2;}
	
	//SVIH_MOPN Mottagare, p-nr 555 563 9 A
	private String svih_mopn = null;
	public void setSvih_mopn(String value) {  this.svih_mopn = value; }
	public String getSvih_mopn() {return this.svih_mopn;}
	
	//SVIH_MOPA Mottagare, p-adr 564 598 35 A
	private String svih_mopa = null;
	public void setSvih_mopa(String value) {  this.svih_mopa = value; }
	public String getSvih_mopa() {return this.svih_mopa;}
	
	//SVIH_MOLK Mottagare, l-kod
	private String svih_molk = null;
	public void setSvih_molk(String value) {  this.svih_molk = value; }
	public String getSvih_molk() {return this.svih_molk;}
	
	private String svih_moha = null;
	public void setSvih_moha(String value) {  this.svih_moha = value; }
	public String getSvih_moha() {return this.svih_moha;}
	
	private String svih_motl = null;
	public void setSvih_motl(String value) {  this.svih_motl = value; }
	public String getSvih_motl() {return this.svih_motl;}
	
	

	private String svih_vufo = "0";
	public void setSvih_vufo(String value) {  this.svih_vufo = value; }
	public String getSvih_vufo() {return this.svih_vufo;}
	
	private String svih_vufr = "0";
	public void setSvih_vufr(String value) {  this.svih_vufr = value; }
	public String getSvih_vufr() {return this.svih_vufr;}

	private String svih_vuva = null;
	public void setSvih_vuva(String value) {  this.svih_vuva = value; }
	public String getSvih_vuva() {return this.svih_vuva;}
	
	private String svih_vuku = null;
	public void setSvih_vuku(String value) {  this.svih_vuku = value; }
	public String getSvih_vuku() {return this.svih_vuku;}

	private String svih_ovko = "0";
	public void setSvih_ovko(String value) {  this.svih_ovko = value; }
	public String getSvih_ovko() {return this.svih_ovko;}

	private String svih_kara = "0";
	public void setSvih_kara(String value) {  this.svih_kara = value; }
	public String getSvih_kara() {return this.svih_kara;}

	private String svih_anra = "0";
	public void setSvih_anra(String value) {  this.svih_anra = value; }
	public String getSvih_anra() {return this.svih_anra;}
	
	
	
	private String svih_kota = null;
	public void setSvih_kota(String value) {  this.svih_kota = value; }
	public String getSvih_kota() {return this.svih_kota;}
	
	private String svih_rfab = null;
	public void setSvih_rfab(String value) {  this.svih_rfab = value; }
	public String getSvih_rfab() {return this.svih_rfab;}
	
	private String svih_rfac = null;
	public void setSvih_rfac(String value) {  this.svih_rfac = value; }
	public String getSvih_rfac() {return this.svih_rfac;}
	
	private String svih_rfac2 = null;
	public void setSvih_rfac2(String value) {  this.svih_rfac2 = value; }
	public String getSvih_rfac2() {return this.svih_rfac2;}
	
	private String svih_rfac3 = null;
	public void setSvih_rfac3(String value) {  this.svih_rfac3 = value; }
	public String getSvih_rfac3() {return this.svih_rfac3;}
	
	
	private String svih_kvsa = null;
	public void setSvih_kvsa(String value) {  this.svih_kvsa = value; }
	public String getSvih_kvsa() { return this.svih_kvsa;}
	
	private String svih_dkkn = null;
	public void setSvih_dkkn(String value) {  this.svih_dkkn = value; }
	public String getSvih_dkkn() { return this.svih_dkkn;}
	
	private String svih_dkeo = null;
	public void setSvih_dkeo(String value) {  this.svih_dkeo = value; }
	public String getSvih_dkeo() { return this.svih_dkeo;}
	
	private String svih_dklk = null;
	public void setSvih_dklk(String value) {  this.svih_dklk = value; }
	public String getSvih_dklk() { return this.svih_dklk;}
	
	private String svih_dkna = null;
	public void setSvih_dkna(String value) {  this.svih_dkna = value; }
	public String getSvih_dkna() { return this.svih_dkna;}
	
	private String svih_dka1 = null;
	public void setSvih_dka1(String value) {  this.svih_dka1 = value; }
	public String getSvih_dka1() { return this.svih_dka1;}
	
	private String svih_dka2 = null;
	public void setSvih_dka2(String value) {  this.svih_dka2 = value; }
	public String getSvih_dka2() { return this.svih_dka2;}
	
	private String svih_dkpa = null;
	public void setSvih_dkpa(String value) {  this.svih_dkpa = value; }
	public String getSvih_dkpa() { return this.svih_dkpa;}
	
	private String svih_dkpn = null;
	public void setSvih_dkpn(String value) {  this.svih_dkpn = value; }
	public String getSvih_dkpn() { return this.svih_dkpn;}
	
	private String svih_dkha = null;
	public void setSvih_dkha(String value) {  this.svih_dkha = value; }
	public String getSvih_dkha() {return this.svih_dkha;}
	
	private String svih_dktl = null;
	public void setSvih_dktl(String value) {  this.svih_dktl = value; }
	public String getSvih_dktl() {return this.svih_dktl;}
	
	
	
	private String svih_omeo = null;
	public void setSvih_omeo(String value) {  this.svih_omeo = value; }
	public String getSvih_omeo() { return this.svih_omeo;}
	
	private String svih_omty = null;
	public void setSvih_omty(String value) {  this.svih_omty = value; }
	public String getSvih_omty() { return this.svih_omty;}
	
	private String svih_omha = null;
	public void setSvih_omha(String value) {  this.svih_omha = value; }
	public String getSvih_omha() { return this.svih_omha;}
	
	private String svih_omtl = null;
	public void setSvih_omtl(String value) {  this.svih_omtl = value; }
	public String getSvih_omtl() { return this.svih_omtl;}
	
	private String svih_avut = null;
	public void setSvih_avut(String value) {  this.svih_avut = value; }
	public String getSvih_avut() { return this.svih_avut;}
	
	private String svih_aukd = null;
	public void setSvih_aukd(String value) {  this.svih_aukd = value; }
	public String getSvih_aukd() { return this.svih_aukd;}
	
	
	private String svih_trid = null;
	public void setSvih_trid(String value) {  this.svih_trid = value; }
	public String getSvih_trid() { return this.svih_trid;}
	
	private String svih_trlk = null;
	public void setSvih_trlk(String value) {  this.svih_trlk = value; }
	public String getSvih_trlk() { return this.svih_trlk;}
	
	private String svih_cont = null;
	public void setSvih_cont(String value) {  this.svih_cont = value; }
	public String getSvih_cont() { return this.svih_cont;}

	private String svih_lekd = null;
	public void setSvih_lekd(String value) {  this.svih_lekd = value; }
	public String getSvih_lekd() { return this.svih_lekd;}

	private String svih_leor = null;
	public void setSvih_leor(String value) {  this.svih_leor = value; }
	public String getSvih_leor() { return this.svih_leor;}

	
	private String svih_trai = null;
	public void setSvih_trai(String value) {  this.svih_trai = value; }
	public String getSvih_trai() { return this.svih_trai;}
	
	private String svih_tral = null;
	public void setSvih_tral(String value) {  this.svih_tral = value; }
	public String getSvih_tral() { return this.svih_tral;}
	
	private String svih_faty = null;
	public void setSvih_faty(String value) {  this.svih_faty = value; }
	public String getSvih_faty() { return this.svih_faty;}
	
	private String svih_fatx = null;
	public void setSvih_fatx(String value) {  this.svih_fatx = value; }
	public String getSvih_fatx() { return this.svih_fatx;}
	
	private String svih_vakd = null;
	public void setSvih_vakd(String value) {  this.svih_vakd = value; }
	public String getSvih_vakd() { return this.svih_vakd;}
	
	private String svih_vaku = null;
	public void setSvih_vaku(String value) {  this.svih_vaku = value; }
	public String getSvih_vaku() { return this.svih_vaku;}
	
	private String svih_vaom = null;
	public void setSvih_vaom(String value) {  this.svih_vaom = value; }
	public String getSvih_vaom() { return this.svih_vaom;}
	
	private String svih_fabl = null;
	public void setSvih_fabl(String value) {  this.svih_fabl = value; }
	public String getSvih_fabl() { return this.svih_fabl;}
	
	private Double svih_fabl_dbl = 0.00D;
	public void setSvih_fabl_dbl(Double value) {  this.svih_fabl_dbl = value; }
	public Double getSvih_fabl_dbl() {
		Double retval = svih_fabl_dbl;
		if(svih_fabl!=null && !"".equals(svih_fabl)){
			try{
				retval =  Double.parseDouble(this.svih_fabl.replace(",", "."));
			}catch(Exception e){
				//nothing
			}
		}
		return retval;
	}
	
	private String svih_trgr = null;
	public void setSvih_trgr(String value) {  this.svih_trgr = value; }
	public String getSvih_trgr() { return this.svih_trgr;}
	
	private String svih_trin = null;
	public void setSvih_trin(String value) {  this.svih_trin = value; }
	public String getSvih_trin() { return this.svih_trin;}
	
	private String svih_grkd = null;
	public void setSvih_grkd(String value) {  this.svih_grkd = value; }
	public String getSvih_grkd() { return this.svih_grkd;}

	private String svih_golk = null;
	public void setSvih_golk(String value) {  this.svih_golk = value; }
	public String getSvih_golk() { return this.svih_golk;}
	
	private String svih_godn = null;
	public void setSvih_godn(String value) {  this.svih_godn = value; }
	public String getSvih_godn() { return this.svih_godn;}
	
	private String svih_brut = null;
	public void setSvih_brut(String value) {  this.svih_brut = value; }
	public String getSvih_brut() { return this.svih_brut;}
	
	private Double svih_brut_dbl = 0.00D;
	public void setSvih_brut_dbl(Double value) {  this.svih_brut_dbl = value; }
	public Double getSvih_brut_dbl() {
		Double retval = svih_brut_dbl;
		if(svih_brut!=null && !"".equals(svih_brut)){
			try{
				retval =  Double.parseDouble(this.svih_brut.replace(",", "."));
			}catch(Exception e){
				//nothing
			}
		}
		return retval;
	}
	
	private String svih_suti = null;
	public void setSvih_suti(String value) {  this.svih_suti = value; }
	public String getSvih_suti() { return this.svih_suti;}
	
	private String svih_abub = null;
	public void setSvih_abub(String value) {  this.svih_abub = value; }
	public String getSvih_abub() { return this.svih_abub;}
	
	private String svih_eup1 = null;
	public void setSvih_eup1(String value) {  this.svih_eup1 = value; }
	public String getSvih_eup1() { return this.svih_eup1;}
	
	private String svih_eup2 = null;
	public void setSvih_eup2(String value) {  this.svih_eup2 = value; }
	public String getSvih_eup2() { return this.svih_eup2;}
	
	private String svih_upps = null;
	public void setSvih_upps(String value) {  this.svih_upps = value; }
	public String getSvih_upps() { return this.svih_upps;}

	private String svih_kleo = null;
	public void setSvih_kleo(String value) {  this.svih_kleo = value; }
	public String getSvih_kleo() { return this.svih_kleo;}
	
	private String svih_trnr = null;
	public void setSvih_trnr(String value) {  this.svih_trnr = value; }
	public String getSvih_trnr() { return this.svih_trnr;}
		
	private String svih_sgdk = null;
	public void setSvih_sgdk(String value) {  this.svih_sgdk = value; }
	public String getSvih_sgdk() { return this.svih_sgdk;}

	private String svih_sgme = null;
	public void setSvih_sgme(String value) {  this.svih_sgme = value; }
	public String getSvih_sgme() { return this.svih_sgme;}

	private String svih_sgut = null;
	public void setSvih_sgut(String value) {  this.svih_sgut = value; }
	public String getSvih_sgut() { return this.svih_sgut;}

	private String svih_sgid = null;
	public void setSvih_sgid(String value) {  this.svih_sgid = value; }
	public String getSvih_sgid() { return this.svih_sgid;}
	
	private String svih_sgdt = null;
	public void setSvih_sgdt(String value) {  this.svih_sgdt = value; }
	public String getSvih_sgdt() { return this.svih_sgdt;}
    
	private String svih_byte = null;
	public void setSvih_byte(String value) {  this.svih_byte = value; }
	public String getSvih_byte() { return this.svih_byte;}
    
	private String svih_kval = null;
	public void setSvih_kval(String value) {  this.svih_kval = value; }
	public String getSvih_kval() { return this.svih_kval;}
	
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
