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

import com.yeren.cms.modle.Article;
import com.yeren.cms.modle.Category;
import com.yeren.cms.modle.Link;
import com.yeren.cms.util.PageBean;

@Repository
public class CategoryDao {
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sf;
	private static final Logger logger = LoggerFactory.getLogger(CategoryDao.class);

	public void save(Category category) {
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(category);
			tx.commit();
			logger.info("新增栏目<ID="+category.getId()+":"+category.getName()+">成功");
		} catch (RuntimeException e) {
			logger.info("新增栏目<ID="+category.getId()+":"+category.getName()+">失败");
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	// 不用
	public int delete(Integer id) {
		Session session = sf.openSession();
		Transaction tx = null;
		int executeUpdate = 0;
		try {
			tx = session.beginTransaction();
			String hql = "delete from Category where id=:id";
			org.hibernate.Query query = session.createQuery(hql);
			query.setInteger("id", id);
			executeUpdate = query.executeUpdate();
			tx.commit();
			logger.info("删除栏目<ID="+id+">"+executeUpdate+"条");
		} catch (RuntimeException e) {
			logger.info("删除栏目<ID="+id+">成功");
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return executeUpdate;
	}

	public int softDelete(Integer id) {
		Session session = sf.openSession();
		Transaction tx = null;
		int executeUpdate = 0;
		try {
			tx = session.beginTransaction();
			String sql = "DELETE fa FROM cms_category fa " + "JOIN cms_category sun ON fa.id <> sun.parent_id "
					+ "WHERE fa.id NOT IN ( SELECT article.category_id " + "FROM cms_article article WHERE fa.id = article.category_id ) "
					+ "AND fa.id NOT IN ( SELECT link.category_id FROM cms_link link WHERE fa.id = link.category_id ) " + "AND fa.id = " + id;
			org.hibernate.Query query = session.createSQLQuery(sql);
			executeUpdate = query.executeUpdate();
			logger.info("软删除栏目<ID="+id+">"+executeUpdate+"条");
			tx.commit();
		} catch (RuntimeException e) {
			logger.info("软删除栏目<ID="+id+">失败");
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return executeUpdate;
	}

	public int hardDelete(Integer id) {
		Session session = sf.openSession();
		Transaction tx = null;
		int executeUpdate = 0;
		try {
			tx = session.beginTransaction();
			String sql = "DELETE category,article,link " + "FROM cms_article article, cms_category category, cms_link link "
					+ "WHERE category.id = article.category_id " + "AND category.id = link.category_id " + "AND category.id = " + id;
			org.hibernate.Query query = session.createSQLQuery(sql);
			executeUpdate = query.executeUpdate();
			logger.info("硬删除栏目<ID="+id+">"+executeUpdate+"条");
			tx.commit();
		} catch (RuntimeException e) {
			logger.info("硬删除栏目<ID="+id+">失败");
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return executeUpdate;
	}

	public void update(Category category) {
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(category);
			tx.commit();
			logger.info("更新栏目<ID="+category.getId()+":"+category.getName()+">成功");
		} catch (RuntimeException e) {
			logger.info("更新栏目<ID="+category.getId()+":"+category.getName()+">失败");
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Category> findById(Integer id) {
		Session session = sf.openSession();
		List<Category> list = null;
		try {
			String hql = "from Category where id=:id";
			org.hibernate.Query query = session.createQuery(hql);
			query.setInteger("id", id);
			list = query.list();
			logger.info("查询栏目<ID="+id+">成功");
		} catch (RuntimeException e) {
			logger.info("查询栏目<ID="+id+">失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public List<Category> findCategoryByCategory(Integer id) {
		Session session = sf.openSession();
		List<Category> list = null;
		try {
			String sql = "SELECT c2.* from cms_category c1 JOIN cms_category c2 ON c1.id=c2.parent_id AND c1.id="+id+" order by sort";
			org.hibernate.Query query = session.createSQLQuery(sql);
			list = query.list();
			logger.info("通过栏目<ID="+id+">查询栏目成功");
		} catch (RuntimeException e) {
			logger.info("通过栏目<ID="+id+">查询栏目失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public List<Article> findArticleByCategory(Integer id){
		Session session = sf.openSession();
		List<Article> list = null;
		try {
			String sql = "SELECT a.* FROM cms_article a JOIN cms_category c ON(c.id=a.category_id) and c.id="+id+" order by sort";
			org.hibernate.Query query = session.createSQLQuery(sql);
			list = query.list();
			logger.info("通过栏目<ID="+id+">查询文章成功");
		} catch (RuntimeException e) {
			logger.info("通过栏目<ID="+id+">查询文章失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public List<Link> findLinkByCategory(Integer id){
		Session session = sf.openSession();
		List<Link> list = null;
		try {
			String sql = "SELECT l.* FROM cms_link l JOIN cms_category c ON(c.id=l.category_id) and c.id="+id+" order by sort";
			org.hibernate.Query query = session.createSQLQuery(sql);
			list = query.list();
			logger.info("通过栏目<ID="+id+">查询链接成功");
		} catch (RuntimeException e) {
			logger.info("通过栏目<ID="+id+">查询链接失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public List<Category> findAll(){
		Session session = sf.openSession();
		List<Category> list = null;
		try {
			String hql = "from Category order by siteId,sort";
			org.hibernate.Query query = session.createQuery(hql);
			list = query.list();
			logger.info("查找栏目一共<"+list.size()+">个");
		} catch (RuntimeException e) {
			logger.info("查询栏目<所有>失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public List<Category> findSomeBySiteId(Integer siteId){
		Session session = sf.openSession();
		List<Category> list = null;
		try {
			String hql = "from Category where siteId=:siteId order by sort";
			org.hibernate.Query query = session.createQuery(hql);
			query.setInteger("siteId", siteId);
			list = query.list();
			logger.info("排序用-查找栏目一共<"+list.size()+">个");
		} catch (RuntimeException e) {
			logger.info("排序用-查询栏目<部分>失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public List<Category> findAll(Category category,PageBean pb) {
		Session session = sf.openSession();
		List<Category> list = null;
		try {
			String hql = "from Category order by siteId,sort";
			org.hibernate.Query query = session.createQuery(hql);
			query.setFirstResult(pb.getStart());
			query.setMaxResults(pb.getRows());
			list = query.list();
			logger.info("分页查找栏目一共<"+list.size()+">个");
		} catch (RuntimeException e) {
			logger.info("分页查询栏目<所有>失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public int getSum(){
		Session session = sf.openSession();
		List<Category> list = null;
		try {
			String sql = "select * from cms_category";
			org.hibernate.Query query = session.createSQLQuery(sql);
			list = query.list();
			logger.info("查找栏目一共<"+list.size()+">个");
		} catch (RuntimeException e) {
			logger.info("查询栏目<所有>失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list.size();
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> findByAttribute(String attribute){
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(Category.class);
//		Criterion conditionID = Restrictions.like("id", "%" + attribute + "%");
		Criterion conditionName = Restrictions.like("name", "%" + attribute + "%");
		criteria.add(Restrictions.or(conditionName));
		return criteria.list();
	}
	
	/**
	 * 更新上下线状态
	 * @param id
	 */
	public void changeStatus(int id) {
		Session session = sf.openSession();
		Transaction tx = null;
		Category category=null;
		try {
			tx = session.beginTransaction();
			List<Category> categoryList = findById(id);
			category = categoryList.get(0);
			int status=Integer.parseInt(category.getStatus());
			category.setStatus(status/1==0?"1":"0");
			session.update(category);
			tx.commit();
			logger.info("修改栏目上下线状态<ID="+category.getId()+":"+category.getName()+">成功");
		} catch (RuntimeException e) {
			logger.info("修改栏目上下线状态<ID="+category.getId()+":"+category.getName()+">失败");
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}