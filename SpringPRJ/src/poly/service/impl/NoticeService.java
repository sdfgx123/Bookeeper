package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.NoticeDTO;
import poly.persistance.mapper.INoticeMapper;
import poly.service.INoticeService;

@Service("NoticeService")
public class NoticeService implements INoticeService {

	@Resource(name = "NoticeMapper")
	INoticeMapper noticeMapper;
	
	@Override
	public List<NoticeDTO> getNoticeList() throws Exception {
		
		return noticeMapper.getNoticeList();
		
	}

	@Override
	public NoticeDTO getNoticeInfo(NoticeDTO pDTO) throws Exception {
		
		return noticeMapper.getNoticeInfo(pDTO);
	}

	@Override
	public int insertNoticeInfo(String title, String content) throws Exception
	{
		return noticeMapper.insertNoticeInfo(title, content);
	}

	@Override
	public int deleteNoticeInfo(int num) throws Exception {
		
		return noticeMapper.deleteNoticeInfo(num);
	}
	
}
