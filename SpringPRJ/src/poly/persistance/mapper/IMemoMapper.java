package poly.persistance.mapper;

import config.Mapper;

@Mapper("MemoMapper")
public interface IMemoMapper {

	//메모 초기화
	int initMemo(String colNm, String isbn) throws Exception;
	
	//lib detail 메모 호출
	String getMemo(String colNm, String isbn) throws Exception;
	
	//MYSQL 메모 저장
	int insertMemo(String colnm, String isbn, String memo) throws Exception;
}
