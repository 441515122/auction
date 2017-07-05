package cn.auction.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.auction.dao.AuctionRecordDao;
import cn.auction.entity.AuctionRecord;

public class AuctionRecordDaoImpl extends HibernateDaoSupport implements AuctionRecordDao {

	@Override
	public void addAuctionRecord(AuctionRecord auctionRecord) {
		super.getHibernateTemplate().save(auctionRecord);
	}

}
