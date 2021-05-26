package poly.persistance.mongo.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import poly.dto.CloudDTO;
import poly.dto.MelonDTO;
import poly.persistance.mongo.ICloudMapper;
import poly.util.CmmUtil;

@Component("CloudMapper")
public class CloudMapper implements ICloudMapper {

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
        mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1).append("rank", 1), "rankIdx");

        log.info(this.getClass().getName() + ".createCollection End!");
    }
	
	@Override
    public void insertRank(List<CloudDTO> pList, String colNm) throws Exception {

        log.info(this.getClass().getName() + ".insertRank Start!");

        int res;

        if (pList == null) {
            pList = new ArrayList<>();
        }

        Iterator<CloudDTO> it = pList.iterator();

        while (it.hasNext()) {
            CloudDTO pDTO = it.next();

            if (pDTO == null) {
                pDTO = new CloudDTO();
            }

            mongodb.insert(pDTO, colNm);

        }

        res = 1;

        log.info(this.getClass().getName() + ".insertRank End!");

    }
	
	@Override
    public List<CloudDTO> getTitle(String colNm) throws Exception {

        log.info(this.getClass().getName() + ".getTitle Start!");

        // 데이터를 가져올 컬렉션 선택
        DBCollection rCol = mongodb.getCollection(colNm);

        // 컬렉션으로부터 전체 데이터 가져오기
        Iterator<DBObject> cursor = rCol.find();

        // 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
        List<CloudDTO> rList = new ArrayList<>();

        // 퀴즈팩별 정답률 일자별 저장하기
        CloudDTO rDTO;

        while (cursor.hasNext()) {

            rDTO = new CloudDTO();

            final DBObject current = cursor.next();

            String title = CmmUtil.nvl((String) current.get("word")); // 책 제목

            rDTO.setWord(title);

            rList.add(rDTO); // List에 데이터 저장

            rDTO = null;

        }

        log.info(this.getClass().getName() + ".getTitle End!");

        return rList;
    }
}
