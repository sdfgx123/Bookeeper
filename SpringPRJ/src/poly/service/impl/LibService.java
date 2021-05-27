package poly.service.impl;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.persistance.mongo.ILibMapper;
import poly.service.ILibService;

@Service("LibService")
public class LibService implements ILibService {

	@Resource(name = "LibMapper")
	private ILibMapper libMapper;
	
	private Logger log = Logger.getLogger(this.getClass());
	
}
