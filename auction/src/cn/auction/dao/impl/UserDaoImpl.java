package cn.auction.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.auction.dao.UserDao;
import cn.auction.entity.AuctionUser;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public void addUser(AuctionUser auctionUser) {
		super.getHibernateTemplate().save(auctionUser);
	}

	@Override
	public void updateUser(AuctionUser auctionUser) {
		super.getHibernateTemplate().update(auctionUser);
	}

	@Override
	public void deleteUser(AuctionUser auctionUser) {
		super.getHibernateTemplate().delete(auctionUser);
	}

	@Override
	public AuctionUser getUser(int userid) {
		AuctionUser auctionUser = new AuctionUser();
		auctionUser = super.getHibernateTemplate().get(AuctionUser.class, userid);
		return auctionUser;
	}

	@Override
	public List<AuctionUser> findUsers(AuctionUser auctionUser) {
		return super.getHibernateTemplate().findByExample(auctionUser);
	}

	@Override
	public List<AuctionUser> findAllUsers() {
		return super.getHibernateTemplate().find("from AuctionUser");
	}

}
