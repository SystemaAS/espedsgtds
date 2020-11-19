/**
 * 
 */
package no.systema.tds.model.jsonjackson;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import lombok.Data;
import no.systema.jservices.common.dao.ArkivpDao;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemRecord;

/**
 * @author oscardelatorre
 * @date Nov 2020
 */
@Data
public class JsonTdsArkivpContainer {
	private String user;
	private String errMsg;
	private Collection<ArkivpDao> list;
	public void setList(Collection<ArkivpDao> value){ this.list = value; }
	public Collection<ArkivpDao> getList(){ return list; }
	
}
