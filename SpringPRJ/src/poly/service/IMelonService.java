package poly.service;

import java.util.List;
import java.util.Map;

import poly.dto.MelonDTO;

public interface IMelonService {

	// 멜론 Top100 순위 수집
	public void collectMelonRank() throws Exception;

}