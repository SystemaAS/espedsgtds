package no.systema.tds.accounting.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.Data;

/**
 * This is the what-the-fuck workaround for Chrome posting twice.
 * 
 * 
 * @author fredrikmoller
 * @date 2019-07-02
 */
@Service
@Data
public class WTF {

	Map<String, Long> godsNrMap = new HashMap<String, Long>();
	
}
