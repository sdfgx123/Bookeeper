package poly.service;

import java.util.List;

import poly.dto.NoticeDTO;

public interface INoticeService {

	List<NoticeDTO> getNoticeList() throws Exception;
	
	NoticeDTO getNoticeInfo(NoticeDTO pDTO) throws Exception;
	
}
