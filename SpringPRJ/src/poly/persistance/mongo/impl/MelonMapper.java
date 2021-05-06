package poly.persistance.mongo.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.mongodb.AggregationOptions;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import poly.dto.MelonDTO;
import poly.dto.MelonSingerDTO;
import poly.dto.MelonSongDTO;
import poly.persistance.mongo.IMelonMapper;
import poly.util.CmmUtil;

@Component("MelonMapper")
public class MelonMapper implements IMelonMapper {

	@Autowired
	private MongoTemplate mongodb;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public boolean createCollection(String colNm) throws Exception {

		log.info(this.getClass().getName() + " .createCollection start");
		
		boolean res = false;
		
		//������ ��ϵ� �÷��� �̸��� �����ϴ��� üũ�ϰ�, �����ϸ� ����  �÷��� ������
		if(mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm);
		}
		
		//�÷��� ���� �� �ε��� ����, MongoDB���� ������ �������� ��Ŀ� �°� �ε����� �ݵ�� ��������!
		//������ ���� ���� ������ �������� ������, �ּ� 10���� �̻� ������ ����� �ӵ� ���̰� �� 10�� �̻� �߻���
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1).append("rank", 1), "rankIdx");
		
		res = true;
		
		log.info(this.getClass().getName() + " .createCollection end");
		
		return res;
	}

	@Override
	public int insertRank(List<MelonDTO> pList, String colNm) throws Exception {

		log.info(this.getClass().getName() + " .insertRank start");
		
		int res = 0;
		
		if(pList == null) {
			
			pList = new ArrayList<MelonDTO>();
			
		}
		
		Iterator<MelonDTO> it = pList.iterator();
		
		while(it.hasNext()) {
			
			MelonDTO pDTO = (MelonDTO) it.next();
			
			if(pDTO == null) {
				
				pDTO = new MelonDTO();
				
			}
			
			mongodb.insert(pDTO, colNm);
			
		}
		
		res = 1;
		
		log.info(this.getClass().getName() + " .insertRank end");
		
		return res;
		
	}

	@Override
	public List<MelonDTO> getRank(String colNm) throws Exception {

		log.info(this.getClass().getName() + " .getRank start");
		
		//�����͸� ������ �÷��� ����
		DBCollection rCol = mongodb.getCollection(colNm);
		
		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();
		
		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<MelonDTO> rList = new ArrayList<MelonDTO>();
		
		MelonDTO rDTO = null;
		
		while(cursor.hasNext()) {
			
			rDTO = new MelonDTO();
			
			final DBObject current = cursor.next();
			
			String collect_time = CmmUtil.nvl((String) current.get("collect_time")); //�����ð�
			String rank = CmmUtil.nvl((String) current.get("rank")); //����
			String song = CmmUtil.nvl((String) current.get("song")); //�뷡����
			String singer = CmmUtil.nvl((String) current.get("singer")); //����
			String album = CmmUtil.nvl((String) current.get("album")); //�ٹ�
			
			log.info("song:"+song);
			log.info("singer:"+singer);
			log.info("album:"+album);
			
			
			rDTO.setCollect_time(collect_time);
			rDTO.setRank(rank);
			rDTO.setSong(song);
			rDTO.setSinger(singer);
			rDTO.setAlbum(album);
			
			rList.add(rDTO); //List�� ������ ����
			
			rDTO = null;
			
		}
		
		log.info(this.getClass().getName() + " .getRank end");
		
		return rList;
	}

	@Override
	public List<MelonSongDTO> getSongForSinger(String colNm, String singer) throws Exception {
		
		log.info(this.getClass().getName() + " .getSongForSinger start");
		
		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);
		
		// 쿼리 생성
		BasicDBObject query = new BasicDBObject();
		query.put("singer", singer);
		
		// 쿼리 실행
		Cursor cursor = rCol.find(query);
		
		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<MelonSongDTO> rList = new ArrayList<MelonSongDTO>();
		
		MelonSongDTO rDTO = null;
		
		while(cursor.hasNext()) {
			
			rDTO = new MelonSongDTO();
			
			final DBObject current = cursor.next();
			
			String rank = CmmUtil.nvl((String) current.get("rank")); // 순위
			String song = CmmUtil.nvl((String) current.get("song")); // 노래
			
			log.info("song : " + song);
			
			rDTO.setRank(rank);
			rDTO.setSong(song);
			
			rList.add(rDTO); // List에 데이터 저장
			
			rDTO = null;
			
		}
		
		log.info(this.getClass().getName() + " .getSongForSinger end");
		
		return rList;
	}

	@Override
	public List<MelonSingerDTO> getRankForSinger(String colNm) throws Exception {
		
		log.info(this.getClass().getName() + " .getRankForSinger start");
		
		DBCollection rCol = mongodb.getCollection(colNm);
		
		List<DBObject> pipeline = Arrays.asList(
                new BasicDBObject()
                        .append("$group", new BasicDBObject()
                                .append("_id", new BasicDBObject()
                                        .append("singer", "$singer")
                                )
                                .append("COUNT(singer)", new BasicDBObject()
                                        .append("$sum", 1)
                                )
                        ), 
                new BasicDBObject()
                        .append("$project", new BasicDBObject()
                                .append("singer", "$_id.singer")
                                .append("COUNT(singer)", "$COUNT(singer)")
                                .append("_id", 0)
                        ), 
                new BasicDBObject()
                        .append("$sort", new BasicDBObject()
                                .append("COUNT(singer)", -1)
                                .append("singer", 1)
                        )
        );
		
		AggregationOptions options = AggregationOptions.builder().allowDiskUse(true).build();
		
		// 쿼리 실행
		Cursor cursor = rCol.aggregate(pipeline, options);
		
		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<MelonSingerDTO> rList = new ArrayList<MelonSingerDTO>();
		
		MelonSingerDTO rDTO = null;
		
		int rank = 1; // 랭킹
		
		while(cursor.hasNext()) {
			
			rDTO = new MelonSingerDTO();
			
			final DBObject current = cursor.next();
			
			String singer = CmmUtil.nvl((String) current.get("singer")); // 가수 
			int song_cnt = (int) current.get("song_cnt"); // 랭크에 올라간 노래의 수
			
			log.info("singer : " + singer);
			log.info("song_cnt : " + song_cnt);
			
			rDTO.setRank(rank); // 조회된 레코드 순서대로 랭킹 저장함
			rDTO.setSinger(singer);
			rDTO.setSong_cnt(song_cnt);
			
			rList.add(rDTO); // List에 데이터 저장
			
			rDTO = null;
			
			rank++; // 랭킹 값을 1씩 증가시킴
			
		}
		
		log.info(this.getClass().getName() + " .getRankForSinger end");
				
		return rList;
	}

}
