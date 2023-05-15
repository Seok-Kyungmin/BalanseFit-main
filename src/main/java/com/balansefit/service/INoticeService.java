package com.balansefit.service;

import com.balansefit.dto.NoticeDTO;
import java.util.List;

public interface INoticeService {

    List<NoticeDTO> getNoticeList() throws Exception;

    void InsertNoticeInfo(NoticeDTO pDTO) throws Exception;

    NoticeDTO getNoticeInfo(NoticeDTO pDTO) throws Exception;

    void updateNoticeInfo(NoticeDTO pDTO) throws Exception;

    void deleteNoticeInfo(NoticeDTO pDTO) throws Exception;

}