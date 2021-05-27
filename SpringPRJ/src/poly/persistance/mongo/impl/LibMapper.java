package poly.persistance.mongo.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;

import poly.dto.LibDTO;
import poly.persistance.mongo.ILibMapper;

@Component("LibMapper")
public class LibMapper implements ILibMapper {
	
	@Autowired
	private MongoTemplate mongodb;
	
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public void createCollection(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".createCollection Start!");
		 // 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
        if (mongodb.collectionExists(colNm)) {
            mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
        }
        // 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
        // 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장시 속도가 약 10배 이상 발생함
        mongodb.createCollection(colNm).createIndex(new BasicDBObject("contents", 1).append("title", 1), "titleIdx");

        log.info(this.getClass().getName() + ".createCollection End!");
	}

	@Override
	public void insertBook(List<LibDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertBook Start!");
		int res;
		if (pList==null) {
			pList = new ArrayList<>();
		}
		Iterator<LibDTO> it = pList.iterator();
		while (it.hasNext()) {
			LibDTO pDTO = it.next();
			if (pDTO==null) {
				pDTO = new LibDTO();
			}
			mongodb.insert(pDTO, colNm);
		}
		res = 1;
		log.info(this.getClass().getName() + ".insertBook end!");
	}
	
	
}
