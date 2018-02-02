package com.yeren.cms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.yeren.cms.modle.Link;
import com.yeren.cms.util.PageBean;

@Repository
public class LinkDao {
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sf;
	private static final Logger logger = LoggerFactory.getLogger(LinkDao.class);
	public void save(Link link) {
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(link);
			tx.commit();
			logger.info("新增链接<ID="+link.getId()+":"+link.getName()+">成功");
		} catch (RuntimeException e) {
			logger.info("新增链接<ID="+link.getId()+":"+link.getName()+">失败");
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
			String hql = "delete from Link where id=:id";
			org.hibernate.Query query = session.createQuery(hql);
			query.setInteger("id", id);
			executeUpdate = query.executeUpdate();
			tx.commit();
			logger.info("删除链接<ID="+id+">"+executeUpdate+"条");
		} catch (RuntimeException e) {
			logger.info("删除链接<ID="+id+">失败");
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return executeUpdate;
	}
	
	public int deleteByArticleId(Integer articleId) {
		Session session = sf.openSession();
		Transaction tx = null;
		int executeUpdate = 0;
		try {
			tx = session.beginTransaction();
			String hql = "delete from Link where articleId=:articleId";
			org.hibernate.Query query = session.createQuery(hql);
			query.setInteger("articleId", articleId);
			executeUpdate = query.executeUpdate();
			tx.commit();
			logger.info("通过文章删除链接<ID="+articleId+">"+executeUpdate+"条");
		} catch (RuntimeException e) {
			logger.info("通过文章删除链接<ID="+articleId+">失败");
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return executeUpdate;
	}

	public void update(Link link) {
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(link);
			tx.commit();
			logger.info("修改链接<ID="+link.getId()+":"+link.getName()+">成功");
		} catch (RuntimeException e) {
			logger.info("修改链接<ID="+link.getId()+":"+link.getName()+">成功");
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Link> findById(Integer id) {
		Session session = sf.openSession();
		List<Link> list = null;
		try {
			String hql = "from Link where id=:id";
			org.hibernate.Query query = session.createQuery(hql);
			query.setInteger("id", id);
			list = query.list();
			logger.info("查询链接<ID="+id+">成功");
		} catch (RuntimeException e) {
			logger.info("查询链接<ID="+id+">失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Link> findAll() {
		Session session = sf.openSession();
		List<Link> list = null;
		try {
			String hql = "from Link where articleId=:articleId order by categoryId,sort";
			org.hibernate.Query query = session.createQuery(hql);
			query.setInteger("articleId", -1);
			list = query.list();
			logger.info("查找链接一共<"+list.size()+">个");
		} catch (RuntimeException e) {
			logger.info("查询链接<所有>失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public List<Link> findSomeBycategoryId(Integer categoryId){
		Session session = sf.openSession();
		List<Link> list = null;
		try {
			String hql = "from Link where categoryId=:categoryId order by sort";
			org.hibernate.Query query = session.createQuery(hql);
			query.setInteger("categoryId", categoryId);
			list = query.list();
			logger.info("排序用-查找链接一共<"+list.size()+">个");
		} catch (RuntimeException e) {
			logger.info("排序用-查询链接<部分>失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public List<Link> findAll(Link link,PageBean pb) {
		Session session = sf.openSession();
		List<Link> list = null;
		try {
			String hql = "from Link where articleId=:articleId order by categoryId,sort";
			org.hibernate.Query query = session.createQuery(hql);
			query.setInteger("articleId", -1);
			query.setFirstResult(pb.getStart());
			query.setMaxResults(pb.getRows());
			list = query.list();
			logger.info("分页查找链接一共<"+list.size()+">个");
		} catch (RuntimeException e) {
			logger.info("分页查询链接<所有>失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public int getSum(){
		Session session = sf.openSession();
		List<Link> list = null;
		try {
			String sql = "select * from cms_link where article_id=-1";
			org.hibernate.Query query = session.createSQLQuery(sql);
			list = query.list();
			logger.info("查找链接一共<"+list.size()+">个");
		} catch (RuntimeException e) {
			logger.info("查询链接<所有>失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list.size();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Link> findByCategoryId(Integer categoryId) {
		Session session = sf.openSession();
		List<Link> list = null;
		try {
			String hql = "from Link where categoryId=:categoryId";
			org.hibernate.Query query = session.createQuery(hql);
			query.setInteger("categoryId", categoryId);
			list = query.list();
			logger.info("通过栏目<ID="+categoryId+">查询链接成功");
		} catch (RuntimeException e) {
			logger.info("通过栏目<ID="+categoryId+">查询链接失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Link> findByArticleId(Integer articleId) {
		Session session = sf.openSession();
		List<Link> list = null;
		try {
			String hql = "from Link where articleId=:articleId";
			org.hibernate.Query query = session.createQuery(hql);
			query.setInteger("articleId", articleId);
			list = query.list();
			logger.info("通过文章<ID="+articleId+">查询链接成功");
		} catch (RuntimeException e) {
			logger.info("通过文章<ID="+articleId+">查询链接失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Link> findByAttribute(String attribute){
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(Link.class);
//		Criterion conditionID = Restrictions.like("id", "%" + attribute + "%");
		Criterion conditionName = Restrictions.like("name", "%" + attribute + "%");
		criteria.add(Restrictions.or(conditionName));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Link> findByAttribute2(String attribute){
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(Link.class);
//		Criterion conditionID = Restrictions.like("id", "%" + attribute + "%");
		Criterion conditionName = Restrictions.like("name", "%" + attribute + "%");
		Criterion conditionArticleId = Restrictions.like("articleId", -1);
		criteria.add(Restrictions.or(conditionName));
		criteria.add(Restrictions.or(conditionArticleId));
		return criteria.list();
	}
	
	/**
	 * 更新上下线状态
	 * @param id
	 */
	public void changeStatus(int id) {
		Session session = sf.openSession();
		Transaction tx = null;
		Link link=null;
		try {
			tx = session.beginTransaction();
			List<Link> linkList = findById(id);
			link = linkList.get(0);
			int status=Integer.parseInt(link.getStatus());
			link.setStatus(status/1==0?"1":"0");
			session.update(link);
			tx.commit();
			logger.info("修改链接上下线状态<ID="+link.getId()+":"+link.getName()+">成功");
		} catch (RuntimeException e) {
			logger.info("修改链接上下线状态<ID="+link.getId()+":"+link.getName()+">失败");
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public List<Link> findLinkTopN(Integer categoryId,Integer n){
		Session session = sf.openSession();
		List<Link> list = null;
		try {
			String hql = "from Link where categoryId=:categoryId order by categoryId,sort";
			org.hibernate.Query query = session.createQuery(hql);
			query.setInteger("categoryId", categoryId);
			query.setMaxResults(n);
			list = query.list();
			logger.info("查找link-topN<ID="+categoryId+">查询链接成功");
		} catch (RuntimeException e) {
			logger.info("通找link-topN<ID="+categoryId+">查询链接失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
}
