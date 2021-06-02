package poly.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.persistance.mapper.IMemoMapper;
import poly.service.IMemoService;

@Service("MemoService")
public class MemoService implements IMemoService {

	@Resource(name = "MemoMapper")
	IMemoMapper memoMapper;
	
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public int initMemo(String colNm, String isbn) throws Exception {
		return memoMapper.initMemo(colNm, isbn);
	}

	@Override
	public String getMemo(String colNm, String isbn) throws Exception {
		return memoMapper.getMemo(colNm, isbn);
	}

	@Override
	public int insertMemo(String colnm, String isbn, String memo) throws Exception {
		log.info("서비스 colnm 확인용 : " + colnm);
		log.info("서비스 isbn 확인용 : " + isbn);
		log.info("서비스 memo 확인용 : " + memo);
		return memoMapper.insertMemo(colnm, isbn, memo);
	}
}
