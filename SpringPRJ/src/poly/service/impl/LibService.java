package poly.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.dto.LibDTO;
import poly.persistance.mongo.ILibMapper;
import poly.service.ILibService;

@Service("LibService")
public class LibService implements ILibService {

	@Resource(name = "LibMapper")
	private ILibMapper libMapper;
	
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public List<LibDTO> getBookInfo(String id) throws Exception {
		log.info(this.getClass().getName() + ".getBookInfo Start!");
		String colNm = id + "_library";
		List<LibDTO> rList = libMapper.getBookInfo(colNm);
		
		return rList;
	}

	@Override
	public List<LibDTO> getBookDetail(String id, String isbn) throws Exception {
		log.info(this.getClass().getName() + ".getBookDetail Start!");
		String colNm = id + "_library";
		List<LibDTO> rList = libMapper.getBookDetail(colNm, isbn);
		if (rList==null) {
			rList = new ArrayList<LibDTO>();
		}
		log.info(this.getClass().getName() + ".getBookDetail end!");
		return rList;
	}

	
	
}
