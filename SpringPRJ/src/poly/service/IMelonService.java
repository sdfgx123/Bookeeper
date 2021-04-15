package poly.service;

import java.util.List;

import poly.dto.MelonDTO;

public interface IMelonService {

	/**
	 * ��� Top 100 ���� �����ϱ�
	 */
	
	public int collectMelonRank() throws Exception;

	public List<MelonDTO> getRank();
	
}
