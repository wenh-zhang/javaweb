package com.validate.service;

import com.validate.domain.Notice;

import java.util.List;

public interface NoticeService {
    List<Notice> getAllNotices();
    void addNotice(Notice notice);
    Notice findNoticeById(String id);
    void updateNotice(Notice notice);
    void deleteNotice(String id);
    List<Notice> getRecentNotice();
}
