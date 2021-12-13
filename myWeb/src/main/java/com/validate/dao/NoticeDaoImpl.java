package com.validate.dao;

import java.sql.SQLException;
import java.util.List;

import com.validate.domain.Notice;
import com.validate.util.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class NoticeDaoImpl implements NoticeDao{
    //后台系统，查询所有的公告
    @Override
    public List<Notice> getAllNotices() throws SQLException {
        String sql = "select * from notice order by n_time desc";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanListHandler<Notice>(Notice.class));
    }

    //后台系统，添加公告
    @Override
    public void addNotice(Notice n) throws SQLException {
        String sql = "insert into notice(title,details,n_time) values(?,?,?)";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        runner.update(sql, n.getTitle(),n.getDetails(),n.getN_time());
    }

    //后台系统，根据id查找公告
    @Override
    public Notice findNoticeById(String id) throws SQLException {
        String sql = "select * from notice where id = ?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanHandler<Notice>(Notice.class),id);
    }

    //后台系统，根据id修改单个公告
    @Override
    public void updateNotice(Notice n) throws SQLException {
        String sql = "update notice set title=?,details=?,n_time=? where id=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        runner.update(sql, n.getTitle(),n.getDetails(),n.getN_time(),n.getId());
    }

    //后台系统，根据id删除公告
    @Override
    public void deleteNotice(String id) throws SQLException {
        String sql = "delete from notice where id = ?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        runner.update(sql, id);
    }

    //前台系统，查询最新添加或修改的5条公告
    @Override
    public List<Notice> getRecentNotice() throws SQLException {
        String sql = "select * from notice order by n_time desc limit 5";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanListHandler<Notice>(Notice.class));
    }
}
