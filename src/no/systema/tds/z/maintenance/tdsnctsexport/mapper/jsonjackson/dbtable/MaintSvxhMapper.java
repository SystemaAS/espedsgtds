/**
 * 
 */
package no.systema.tds.z.maintenance.tdsnctsexport.mapper.jsonjackson.dbtable;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.tds.z.maintenance.tdsnctsexport.model.jsonjackson.dbtable.JsonMaintSvxghContainer;
import no.systema.tds.z.maintenance.tdsnctsexport.model.jsonjackson.dbtable.JsonMaintSvxghRecord;
import no.systema.tds.z.maintenance.tdsnctsexport.model.jsonjackson.dbtable.JsonMaintSvxhContainer;
import no.systema.tds.z.maintenance.tdsnctsexport.model.jsonjackson.dbtable.JsonMaintSvxhRecord;

/**
 * @author oscardelatorre
 * @date Jul, 2021
 * 
 */
public class MaintSvxhMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(MaintSvxhMapper.class.getName());
	
	public JsonMaintSvxhContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonMaintSvxhContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonMaintSvxhContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSvxhRecord> list = container.getList();
		for(JsonMaintSvxhRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
