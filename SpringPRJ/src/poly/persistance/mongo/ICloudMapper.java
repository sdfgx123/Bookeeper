package poly.persistance.mongo;

import java.util.List;

import poly.dto.CloudDTO;
import poly.dto.MelonDTO;

public interface ICloudMapper {
	
	/**
     * MongoDB 컬렉션 생성하기
     *
     * @param colNm 생성하는 컬렉션 이름
     */
    public void createCollection(String colNm) throws Exception;

    /**
     * MongoDB 데이터 저장하기
     *
     * @param pList 저장될 정보
     */
    public void insertRank(List<CloudDTO> pList, String colNm) throws Exception;
    
    /**
     * MongoDB 에서 데이터 책 제목 데이터 가져오기
     *
     * @param colNm 가져올 컬렉션 이름
     */
    public List<CloudDTO> getTitle(String colNm) throws Exception;
}
