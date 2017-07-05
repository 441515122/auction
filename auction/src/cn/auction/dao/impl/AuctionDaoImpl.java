package cn.auction.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.hql.ast.tree.RestrictableStatement;
import org.hibernate.jmx.HibernateService;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.auction.dao.AuctionDao;
import cn.auction.entity.Auction;

public class AuctionDaoImpl extends HibernateDaoSupport implements AuctionDao {

	@Override
	public List<Auction> findAllAuction() {
		return super.getHibernateTemplate().find("select a from Auction a order by a.auctionstarttime desc");
	}

	@Override
	public void delete(Auction auction) {
		super.getHibernateTemplate().delete(auction);
	}

	@Override
	public Auction getAuction(Integer auctionid) {
		return super.getHibernateTemplate().get(Auction.class, auctionid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Auction> findAuctionByPages(final int pageNo, final int pageSize) {
		
		return super.getHibernateTemplate().executeFind(new HibernateCallback<List<Auction>>() {
			
			public List<Auction> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("select a from Auction a order by a.auctionstarttime desc");
				
				query.setFirstResult((pageNo-1)*pageSize);
				query.setMaxResults(pageSize);
				
				return query.list();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Auction> findAuctionByPages(final int pageNo, final int pageSize,
			final Auction auctionExample) {
		return super.getHibernateTemplate().executeFind(new HibernateCallback<List<Auction>>() {

			@Override
			public List<Auction> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(Auction.class);
				
				if(auctionExample != null){
					if(auctionExample.getAuctionname() != null && !"".equals(auctionExample.getAuctionname())){
						criteria.add(Restrictions.like("auctionname", auctionExample.getAuctionname(), MatchMode.ANYWHERE));
					}
					if(auctionExample.getAuctiondesc() != null && !"".equals(auctionExample.getAuctiondesc())){
						criteria.add(Restrictions.like("auctiondesc", auctionExample.getAuctiondesc(), MatchMode.ANYWHERE));
					}
					if(auctionExample.getAuctionstarttime() != null){
						criteria.add(Restrictions.ge("auctionstarttime", auctionExample.getAuctionstarttime()));
					}
					if(auctionExample.getAuctionendtime() != null){
						criteria.add(Restrictions.le("auctionendtime", auctionExample.getAuctionendtime()));
					}
					if(auctionExample.getAuctionstartprice() != null){
						criteria.add(Restrictions.gt("auctionstartprice", auctionExample.getAuctionstartprice()));
					}
				}
				criteria.setFirstResult((pageNo-1)*pageSize);
				criteria.setMaxResults(pageSize);
				
				criteria.addOrder(Order.desc("auctionstarttime"));
				
				return criteria.list();
			}
			
		});
	}
	
	@Override
	public long getAllCounts() {
		return (Long) super.getHibernateTemplate().find("select count(a) from Auction a").get(0);
	}

	@Override
	public Integer getAllCounts(final Auction auctionExample) {
		return super.getHibernateTemplate().execute(new HibernateCallback<Integer>(){

			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(Auction.class);
				
				if(auctionExample != null){
					if(auctionExample.getAuctionname() != null && !"".equals(auctionExample.getAuctionname())){
						criteria.add(Restrictions.like("auctionname", auctionExample.getAuctionname(), MatchMode.ANYWHERE));
					}
					if(auctionExample.getAuctiondesc() != null && !"".equals(auctionExample.getAuctiondesc())){
						criteria.add(Restrictions.like("auctiondesc", auctionExample.getAuctiondesc(), MatchMode.ANYWHERE));
					}
					if(auctionExample.getAuctionstarttime() != null){
						criteria.add(Restrictions.ge("auctionstarttime", auctionExample.getAuctionstarttime()));
					}
					if(auctionExample.getAuctionendtime() != null){
						criteria.add(Restrictions.le("auctionendtime", auctionExample.getAuctionendtime()));
					}
					if(auctionExample.getAuctionstartprice() != null){
						criteria.add(Restrictions.gt("auctionstartprice", auctionExample.getAuctionstartprice()));
					}
				}
				criteria.setProjection(Projections.rowCount());
				return (Integer) criteria.uniqueResult();
			}
			
		});
	}
	
	@Override
	public void addAuction(Auction auction) {
		super.getHibernateTemplate().save(auction);
	}

	@Override
	public void updateAuction(Auction auction) {
		super.getHibernateTemplate().update(auction);
	}

	@Override
	public List<Auction> find(String hql, String[] paramNames, Object[] values) {
		return super.getHibernateTemplate().findByNamedParam(hql, paramNames, values);
	}

}
