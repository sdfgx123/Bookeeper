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
    
    /**
     * MongoDB BookInfo 데이터 가져오기
     *
     * @param colNm 가져올 컬렉션 이름
     */
    public List<LibDTO> getBookInfo(String colNm) throws Exception;
    
    /**
     * MongoDB Library Detail에 뿌려줄 _id 기반 row 호출
     *
     * @param colNm  가져올 컬렉션 이름
     * @param isbn 
     */
    public List<LibDTO> getBookDetail(String colNm, String isbn) throws Exception;
    
    /**
     * MongoDB 메모 추가
     *
     * @param colNm  가져올 컬렉션 이름
     * @param isbn 어느 책에 추가할 메모인지
     * @param pList 메모 심은 리스트
     */
    public void insertMemo(String colNm, String isbn, String memo) throws Exception;
}
