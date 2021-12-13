package com.validate.dao;

import com.validate.domain.Notice;

import java.sql.SQLException;
import java.util.List;

public interface NoticeDao {
    List<Notice> getAllNotices() throws SQLException;
    void addNotice(Notice n) throws SQLException;
    Notice findNoticeById(String id) throws SQLException;
    void updateNotice(Notice n) throws SQLException;
    void deleteNotice(String id) throws SQLException;
    List<Notice> getRecentNotice() throws SQLException;
}
