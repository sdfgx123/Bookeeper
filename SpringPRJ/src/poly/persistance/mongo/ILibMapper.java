package poly.persistance.mongo;

import java.util.List;

import poly.dto.LibDTO;

public interface ILibMapper {

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
    public void insertBook(List<LibDTO> pList, String colNm) throws Exception;
}
