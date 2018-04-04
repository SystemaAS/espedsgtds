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

/**
 * @author oscardelatorre
 * @date Jun 23, 2017
 * 
 */
public class MaintSvxghMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = Logger.getLogger(MaintSvxghMapper.class.getName());
	
	public JsonMaintSvxghContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonMaintSvxghContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonMaintSvxghContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSvxghRecord> list = container.getList();
		for(JsonMaintSvxghRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
