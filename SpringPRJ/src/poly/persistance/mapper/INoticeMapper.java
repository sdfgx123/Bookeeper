package poly.persistance.mapper;

import java.util.List;

import config.Mapper;
import poly.dto.NoticeDTO;

@Mapper("NoticeMapper") //Service에서 Mapper를 찾을 수 있도록 하기 위함
public interface INoticeMapper {
	
	List<NoticeDTO> getNoticeList() throws Exception;
	
	// 게시판 상세보기
	NoticeDTO getNoticeInfo(NoticeDTO pDTO) throws Exception;
	
	// 공지사항 등록
	int insertNoticeInfo(String title, String content) throws Exception;
	
	// 공지사항 삭제
	int deleteNoticeInfo(int num) throws Exception;

}
