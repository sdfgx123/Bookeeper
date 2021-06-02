package poly.service;

public interface IMemoService {

	int initMemo(String colNm, String isbn) throws Exception;
	
	String getMemo(String colNm, String isbn) throws Exception;
	
	int insertMemo(String colnm, String isbn, String memo) throws Exception;
}
