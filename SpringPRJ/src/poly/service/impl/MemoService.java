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
}
