package poly.persistance.mongo;

public interface IMongoTestMapper {

	/**
	 * MongoDB �÷��� �����ϱ�
	 * 
	 * @param colNm �����ϴ� �÷��� �̸�
	 */
	
	public boolean createCollection(String colNm) throws Exception;
	
}
