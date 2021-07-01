/**
 * 
 */
package no.systema.tds.z.maintenance.tdsnctsexport.model.jsonjackson.dbtable;

import java.util.Collection;

import lombok.Data;

/**
 * @author oscardelatorre
 * @date Jul, 2021
 *
 */
@Data
public class JsonMaintSvxhContainer {
	private String user = null;
	private String errMsg = null;
	
	private Collection<JsonMaintSvxhRecord> list;
	public void setList(Collection<JsonMaintSvxhRecord> value){ this.list = value; }
	public Collection<JsonMaintSvxhRecord> getList(){ return list; }
	
}
