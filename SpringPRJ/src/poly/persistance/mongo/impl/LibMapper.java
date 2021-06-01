package poly.persistance.mongo.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import poly.dto.LibDTO;
import poly.dto.MelonSongDTO;
import poly.persistance.mongo.ILibMapper;
import poly.util.CmmUtil;

@Component("LibMapper")
public class LibMapper implements ILibMapper {
	
	@Autowired
	private MongoTemplate mongodb;
	
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public void createCollection(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".createCollection Start!");
		 // 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
        //if (mongodb.collectionExists(colNm)) {
        //    mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
        //}
        
        // 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
        // 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장시 속도가 약 10배 이상 발생함
        mongodb.createCollection(colNm);
        //.createIndex(new BasicDBObject("contents", 1).append("title", 1), "titleIdx");
        
        

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

	// libMain 넘어갈 때
	@Override
	public List<LibDTO> getBookInfo(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".getBookInfo Start!");
		DBCollection rCol = mongodb.getCollection(colNm);
		Iterator<DBObject> cursor = rCol.find();
		List<LibDTO> rList = new ArrayList<>();
		LibDTO rDTO;
		while (cursor.hasNext()) {
			rDTO = new LibDTO();
			final DBObject current = cursor.next();
			String title = CmmUtil.nvl((String) current.get("title"));
			String contents = CmmUtil.nvl((String) current.get("contents"));
			String thumbnail = CmmUtil.nvl((String) current.get("thumbnail"));
			String datetime = CmmUtil.nvl((String) current.get("datetime"));
			String authors = CmmUtil.nvl((String) current.get("authors"));
			String publisher = CmmUtil.nvl((String) current.get("publisher"));
			String isbn = CmmUtil.nvl((String) current.get("isbn"));
			String memo = CmmUtil.nvl((String) current.get("memo"));
			rDTO.setTitle(title);
			rDTO.setContents(contents);
			rDTO.setThumbnail(thumbnail);
			rDTO.setDatetime(datetime);
			rDTO.setAuthors(authors);
			rDTO.setPublisher(publisher);
			rDTO.setIsbn(isbn);
			rDTO.setMemo(memo);
			rList.add(rDTO);
			rDTO = null;
		}
		log.info(this.getClass().getName() + ".getBookInfo end!");
		return rList;
	}

	// libDetail 넘어갈 때
	@Override
	public List<LibDTO> getBookDetail(String colNm, String isbn) throws Exception {
		log.info(this.getClass().getName() + ".getBookDetail Start!");
		DBCollection rCol = mongodb.getCollection(colNm);
		BasicDBObject query = new BasicDBObject();
		
		query.put("isbn", isbn);
		Cursor cursor = rCol.find(query);
		List<LibDTO> rList = new ArrayList<LibDTO>();
		LibDTO rDTO = null;
		while (cursor.hasNext()) {
			rDTO = new LibDTO();
			final DBObject current = cursor.next();
			String title = CmmUtil.nvl((String) current.get("title"));
			String contents = CmmUtil.nvl((String) current.get("contents"));
			String thumbnail = CmmUtil.nvl((String) current.get("thumbnail"));
			String datetime = CmmUtil.nvl((String) current.get("datetime"));
			String authors = CmmUtil.nvl((String) current.get("authors"));
			String publisher = CmmUtil.nvl((String) current.get("publisher"));
			String memo = CmmUtil.nvl((String) current.get("memo"));
			String isbn1 = CmmUtil.nvl((String) current.get("isbn"));
			log.info("title : " + title);
			log.info("contents : " + contents);
			log.info("datetime : " + datetime);
			log.info("authors : " + authors);
			log.info("isbn : " + isbn1);
			rDTO.setTitle(title);
			rDTO.setContents(contents);
			rDTO.setThumbnail(thumbnail);
			rDTO.setDatetime(datetime);
			rDTO.setAuthors(authors);
			rDTO.setPublisher(publisher);
			rDTO.setPublisher(publisher);
			rDTO.setMemo(memo);
			rDTO.setIsbn(isbn1);
			rList.add(rDTO);
			rDTO = null;
		}
		log.info(this.getClass().getName() + ".getBookDetail end!");
		return rList;
	}

	@Override
	public void insertMemo(String colNm, String isbn, String memo) throws Exception {
		log.info(this.getClass().getName() + ".insertMemo Start");
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.append("isbn", isbn);
		BasicDBObject updateQuery = new BasicDBObject();
		updateQuery.append("$set", new BasicDBObject().append("memo", memo));
		mongodb.getCollection(colNm).updateMulti(searchQuery, updateQuery);
		log.info(this.getClass().getName() + ".insertMemo end");
	}
	
	
}
