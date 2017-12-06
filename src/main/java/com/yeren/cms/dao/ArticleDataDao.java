package com.yeren.cms.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.yeren.cms.modle.ArticleData;

@Repository
public class ArticleDataDao {
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sf;
	private static final Logger logger = LoggerFactory.getLogger(ArticleDataDao.class);

	public void save(ArticleData articleData) {
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(articleData);
			tx.commit();
			logger.info("新增文章内容<ID="+articleData.getId()+">成功");
		} catch (RuntimeException e) {
			logger.info("新增文章内容<ID="+articleData.getId()+">失败");
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public int delete(Integer id) {
		Session session = sf.openSession();
		Transaction tx = null;
		int executeUpdate = 0;
		try {
			tx = session.beginTransaction();
			String hql = "delete from ArticleData where id=:id";
			org.hibernate.Query query = session.createQuery(hql);
			query.setInteger("id", id);
			executeUpdate = query.executeUpdate();
			tx.commit();
			logger.info("删除文章内容<ID="+id+">"+executeUpdate+"条");
		} catch (RuntimeException e) {
			logger.info("删除文章内容<ID="+id+">失败");
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return executeUpdate;
	}

	public void update(ArticleData articleData) {
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(articleData);
			tx.commit();
			logger.info("更新文章内容<ID="+articleData.getId()+">成功");
		} catch (RuntimeException e) {
			logger.info("更新文章内容<ID="+articleData.getId()+">失败");
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ArticleData> findById(Integer id) {
		Session session = sf.openSession();
		List<ArticleData> list = null;
		try {
			String hql = "from ArticleData where id=:id";
			org.hibernate.Query query = session.createQuery(hql);
			query.setInteger("id", id);
			list = query.list();
			logger.info("查找文章内容一共<"+list.size()+">个");
		} catch (RuntimeException e) {
			logger.info("查询文章内容<所有>失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
}
