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
import com.yeren.cms.util.PageBean;

@Repository
public class ArticleDao {
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sf;
	private static final Logger logger = LoggerFactory.getLogger(ArticleDao.class);

	public void save(Article article) {
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(article);
			tx.commit();
			logger.info("新增文章<ID="+article.getId()+":"+article.getName()+">成功");
		} catch (RuntimeException e) {
			logger.info("新增文章<ID="+article.getId()+":"+article.getName()+">失败");
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
			String hql = "delete from Article where id=:id";
			org.hibernate.Query query = session.createQuery(hql);
			query.setInteger("id", id);
			executeUpdate = query.executeUpdate();
			tx.commit();
			logger.info("删除文章<ID="+id+">"+executeUpdate+"条");
		} catch (RuntimeException e) {
			logger.info("删除文章<ID="+id+">失败");
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return executeUpdate;
	}

	public void update(Article article) {
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(article);
			tx.commit();
			logger.info("修改文章<ID="+article.getId()+":"+article.getName()+">成功");
		} catch (RuntimeException e) {
			logger.info("修改文章<ID="+article.getId()+":"+article.getName()+">失败");
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Article> findById(Integer id) {
		Session session = sf.openSession();
		List<Article> list = null;
		try {
			String hql = "from Article where id=:id";
			org.hibernate.Query query = session.createQuery(hql);
			query.setInteger("id", id);
			list = query.list();
			logger.info("查找文章<ID="+id+">成功");
		} catch (RuntimeException e) {
			logger.info("查找文章<ID="+id+">失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	
	public int softDelete(Integer id){
		//TODO 暂时没有完成的，可能用不到
		return 0;
	}
	
	public int hardDelete(Integer id){
		Session session = sf.openSession();
		Transaction tx = null;
		int executeUpdate = 0;
		try {
			tx = session.beginTransaction();
			String sql = "DELETE a,ad FROM cms_article a,cms_article_data ad WHERE a.id=ad.id AND  a.id="+id;
			org.hibernate.Query query = session.createSQLQuery(sql);
			executeUpdate = query.executeUpdate();
			tx.commit();
			logger.info("硬删除文章和文章内容<ID="+id+">"+executeUpdate+"条");
		} catch (RuntimeException e) {
			logger.info("硬删除文章和文章内容<ID="+id+">失败");
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return executeUpdate;
	}
	
	public List<Article> findAll(){
		Session session = sf.openSession();
		List<Article> list = null;
		try {
			String hql = "from Article order by categoryId,sort";
			org.hibernate.Query query = session.createQuery(hql);
			list = query.list();
			logger.info("查找文章一共<"+list.size()+">个");
		} catch (RuntimeException e) {
			logger.info("查询文章<所有>失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public List<Article> findSomeBycategoryId(Integer categoryId){
		Session session = sf.openSession();
		List<Article> list = null;
		try {
			String hql = "from Article where categoryId=:categoryId order by sort";
			org.hibernate.Query query = session.createQuery(hql);
			query.setInteger("categoryId", categoryId);
			list = query.list();
			logger.info("排序用-查找文章一共<"+list.size()+">个");
		} catch (RuntimeException e) {
			logger.info("排序用-查询文章<部分>失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public List<Article> findAll(Article article,PageBean pb) {
		Session session = sf.openSession();
		List<Article> list = null;
		try {
			String hql = "from Article order by categoryId,sort";
			org.hibernate.Query query = session.createQuery(hql);
			query.setFirstResult(pb.getStart());
			query.setMaxResults(pb.getRows());
			list = query.list();
			logger.info("分页查找文章一共<"+list.size()+">个");
		} catch (RuntimeException e) {
			logger.info("分页查询文章<所有>失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public int getSum(){
		Session session = sf.openSession();
		List<Article> list = null;
		try {
			String sql = "select * from cms_article";
			org.hibernate.Query query = session.createSQLQuery(sql);
			list = query.list();
			logger.info("查找文章一共<"+list.size()+">个");
		} catch (RuntimeException e) {
			logger.info("查询文章<所有>失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list.size();
	}
	
	public List<Article> findArticleDataByArticle(Integer id){
		Session session = sf.openSession();
		List<Article> list = null;
		try {
			String sql = "SELECT ad.* FROM cms_article a,cms_article_data ad WHERE a.id=ad.id AND a.id="+id;
			org.hibernate.Query query = session.createSQLQuery(sql);
			list = query.list();
			logger.info("通过文章<ID="+id+">查询文章内容成功");
		} catch (RuntimeException e) {
			logger.info("通过文章<ID="+id+">查询文章内容失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public List<Article> findLinkByArticle(Integer id){
		Session session = sf.openSession();
		List<Article> list = null;
		try {
			String sql = "SELECT l.* FROM cms_article a,cms_link l WHERE a.id=l.article_id AND a.id="+id;
			org.hibernate.Query query = session.createSQLQuery(sql);
			list = query.list();
			logger.info("通过文章<ID="+id+">查询链接成功");
		} catch (RuntimeException e) {
			logger.info("通过文章<ID="+id+">查询链接失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Article> findByAttribute(String attribute){
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(Article.class);
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
		Article article=null;
		try {
			tx = session.beginTransaction();
			List<Article> articleList = findById(id);
			article = articleList.get(0);
			int status=Integer.parseInt(article.getStatus());
			article.setStatus(status/1==0?"1":"0");
			session.update(article);
			tx.commit();
			logger.info("修改文章上下线状态<ID="+article.getId()+":"+article.getName()+">成功");
		} catch (RuntimeException e) {
			logger.info("修改文章上下线状态<ID="+article.getId()+":"+article.getName()+">失败");
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public List<Article> findArticleTopN(Integer categoryId,Integer n){
		Session session = sf.openSession();
		List<Article> list = null;
		try {
			String hql = "from Article where categoryId=:categoryId order by categoryId,sort";
			org.hibernate.Query query = session.createQuery(hql);
			query.setInteger("categoryId", categoryId);
			query.setMaxResults(n);
			list = query.list();
			logger.info("查找article-topN<ID="+categoryId+">查询链接成功");
		} catch (RuntimeException e) {
			logger.info("通找article-topN<ID="+categoryId+">查询链接失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
}
