package com.validate.service;

import com.validate.dao.NoticeDao;
import com.validate.dao.NoticeDaoImpl;
import com.validate.domain.Notice;

import java.sql.SQLException;
import java.util.List;


public class NoticeServiceImpl implements NoticeService{
    private NoticeDao dao = new NoticeDaoImpl();
    //后台系统，查询所有公告
    @Override
    public List<Notice> getAllNotices() {
        try {
            return dao.getAllNotices();
        } catch (SQLException e) {
            throw new RuntimeException("查询所有的公告失败！");
        }
    }
    //后台系统，添加公告
    @Override
    public void addNotice(Notice notice) {
        try {
            dao.addNotice(notice);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("添加公告失败!");
        }
    }
    //后台系统，根据id查找公告
    @Override
    public Notice findNoticeById(String id) {
        try {
            return dao.findNoticeById(id);
        } catch (SQLException e) {
            throw new RuntimeException("根据id查找公告失败！");
        }
    }

    //后台系统，根据id修改公告
    @Override
    public void updateNotice(Notice notice) {
        try {
            dao.updateNotice(notice);
        } catch (SQLException e) {
//			throw new RuntimeException("根据id修改公告失败！");
            e.printStackTrace();
        }
    }

    //后台系统，根据id删除公告
    @Override
    public void deleteNotice(String id) {
        try {
            dao.deleteNotice(id);
        } catch (SQLException e) {
            throw new RuntimeException("根据id删除公告失败！");
        }
    }

    //前台系统，查询最新添加或修改的一条公告
    @Override
    public List<Notice> getRecentNotice() {
        try {
            return dao.getRecentNotice();
        } catch (SQLException e) {
            throw new RuntimeException("查询最新添加或修改的5条公告失败！");
        }
    }
}
