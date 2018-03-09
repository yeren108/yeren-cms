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

import com.yeren.cms.modle.Category;
import com.yeren.cms.modle.Site;
import com.yeren.cms.util.PageBean;

@Repository
public class SiteDao {
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sf;
	private static final Logger logger = LoggerFactory.getLogger(SiteDao.class);
	/**
	 * 新增
	 * @param site
	 */
	public void save(Site site) {
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(site);
			tx.commit();
			logger.info("新增站点<ID="+site.getId()+":"+site.getName()+">成功");
		} catch (RuntimeException e) {
			logger.info("新增站点<ID="+site.getId()+":"+site.getName()+">失败");
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	
	/**
	 * 删除，基本不用
	 * @param id
	 * @return
	 */
	public int delete(Integer id) {
		Session session = sf.openSession();
		Transaction tx = null;
		int executeUpdate = 0;
		try {
			tx = session.beginTransaction();
			String hql = "delete from Site where id=:id";
			org.hibernate.Query query = session.createQuery(hql);
			query.setInteger("id", id);
			executeUpdate = query.executeUpdate();
			tx.commit();
			logger.info("删除站点<ID="+id+">"+executeUpdate+"条");
		} catch (RuntimeException e) {
			logger.info("删除站点<ID="+id+">失败");
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return executeUpdate;
	}
	
	/**
	 * 软删除
	 * @param id
	 * @return
	 */
	public int softDelete(Integer id) {
		Session session = sf.openSession();
		Transaction tx = null;
		int executeUpdate = 0;
		try {
			tx = session.beginTransaction();
			String sql="DELETE site FROM cms_site site WHERE site.id NOT IN ( SELECT category.site_id FROM cms_category category WHERE category.site_id = site.id ) AND site.id = "+id;
			org.hibernate.Query query = session.createSQLQuery(sql);
			executeUpdate = query.executeUpdate();
			tx.commit();
			logger.info("软删除站点<ID="+id+">"+executeUpdate+"条");
		} catch (RuntimeException e) {
			logger.info("软删除站点<ID="+id+">失败");
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return executeUpdate;
	}
	
	/**
	 * 硬删除，强制删除改记录，并删除和其相关的记录
	 * @param id
	 * @return
	 */
	public int hardDelete(Integer id) {
		Session session = sf.openSession();
		Transaction tx = null;
		int executeUpdate = 0;
		try {
			tx = session.beginTransaction();
			String sql="DELETE site,category from cms_site site,cms_category category WHERE site.id=category.site_id and site.id="+id;
			org.hibernate.Query query = session.createSQLQuery(sql);
			executeUpdate = query.executeUpdate();
			tx.commit();
			logger.info("硬删除站点<ID="+id+">"+executeUpdate+"条");
		} catch (RuntimeException e) {
			logger.info("硬删除站点<ID="+id+">成功");
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return executeUpdate;
	}

	/**
	 * 更新
	 * @param site
	 */
	public void update(Site site) {
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(site);
			tx.commit();
			logger.info("修改站点<ID="+site.getId()+":"+site.getName()+">成功");
		} catch (RuntimeException e) {
			logger.info("修改站点<ID="+site.getId()+":"+site.getName()+">失败");
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * 通过ID查找
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Site> findById(Integer id) {
		Session session = sf.openSession();
		List<Site> list = null;
		try {
			String hql = "from Site where id=:id";
			org.hibernate.Query query = session.createQuery(hql);
			query.setInteger("id", id);
			list = query.list();
			logger.info("查找站点<ID="+list.get(0).getName()+">成功");
		} catch (RuntimeException e) {
			logger.info("查找站点<ID="+list.get(0).getName()+">失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	
	/**
	 * 通过ID查找
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findNameById(Integer id) {
		Session session = sf.openSession();
		List<Site> list = null;
		try {
			String sql = "select s.name from cms_site s where id=:id";
			org.hibernate.Query query = session.createQuery(sql);
			query.setInteger("id", id);
			list = query.list();
			if(list!=null&&list.size()>0){
				logger.info("查找站点<ID="+list.get(0).getName()+">成功");
				return list.get(0).getName();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<Site> findAll(){
		Session session = sf.openSession();
		List<Site> list = null;
		try {
			String hql = "from Site order by sort";
			org.hibernate.Query query = session.createQuery(hql);
			list = query.list();
			logger.info("查找站点一共<"+list.size()+">个");
		} catch (RuntimeException e) {
			logger.info("查询站点<所有>失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public List<Site> findAll(PageBean pb) {
		Session session = sf.openSession();
		List<Site> list = null;
		try {
			String hql = "from Site order by sort";
			org.hibernate.Query query = session.createQuery(hql);
			query.setFirstResult(pb.getStart());
			query.setMaxResults(pb.getRows());
			list = query.list();
			logger.info("分页查找站点一共<"+list.size()+">个");
		} catch (RuntimeException e) {
			logger.info("分页查询站点<所有>失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public int getSum(){
		Session session = sf.openSession();
		List<Site> list = null;
		try {
			String sql = "select * from cms_site";
			org.hibernate.Query query = session.createSQLQuery(sql);
			list = query.list();
			logger.info("查找站点一共<"+list.size()+">个");
		} catch (RuntimeException e) {
			logger.info("查询站点<所有>失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list.size();
	}
	
	/**
	 * 通过站点找栏目
	 * @param id
	 * @return
	 */
	public List<Category> findCategoryBySite(Integer id){
		Session session = sf.openSession();
		List<Category> list = null;
		try {
			String sql = "SELECT c.* FROM `cms_category` c JOIN cms_site s ON(c.site_id=s.id) and s.id="+id+" order by sort";
			org.hibernate.Query query = session.createSQLQuery(sql);
			list = query.list();
			logger.info("通过站点<ID="+id+">找栏目成功");
		} catch (RuntimeException e) {
			logger.info("通过站点<ID="+id+">找栏目失败");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	//模糊查询
	@SuppressWarnings("unchecked")
	public List<Site> findByAttribute(String attribute){
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(Site.class);
//		Criterion conditionID = Restrictions.like("id", attribute);
		Criterion conditionName = Restrictions.like("name", "%" + attribute + "%");
//		criteria.add(Restrictions.or(conditionID));
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
		Site site=null;
		try {
			tx = session.beginTransaction();
			List<Site> siteList = findById(id);
			site = siteList.get(0);
			int status=Integer.parseInt(site.getStatus());
			site.setStatus(status/1==0?"1":"0");
			session.update(site);
			tx.commit();
			logger.info("修改站点上下线状态<ID="+site.getId()+":"+site.getName()+">成功");
		} catch (RuntimeException e) {
			logger.info("修改站点上下线状态<ID="+site.getId()+":"+site.getName()+">失败");
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
}
