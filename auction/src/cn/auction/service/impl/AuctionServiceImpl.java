package cn.auction.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.auction.dao.AuctionDao;
import cn.auction.dao.AuctionRecordDao;
import cn.auction.entity.Auction;
import cn.auction.entity.AuctionRecord;
import cn.auction.entity.AuctionUser;
import cn.auction.service.AuctionService;

public class AuctionServiceImpl implements AuctionService {
	
	private AuctionDao auctionDao;
	private AuctionRecordDao auctionRecordDao;

	@Override
	public List<Auction> findAllAuction() {
		
		return auctionDao.findAllAuction();
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Auction auction) {
		auctionDao.delete(auction);
	}
	
	@Override
	public Auction getAuction(Integer auctionid) {
		
		return auctionDao.getAuction(auctionid);
	}
	
	@Override
	public List<Auction> findAuctionByPages(int pageNo, int pageSize) {
		return auctionDao.findAuctionByPages(pageNo, pageSize);
	}
	
	@Override
	public List<Auction> findAuctionByPages(int pageNo, int pageSize,
			Auction auctionExample) {
		return auctionDao.findAuctionByPages(pageNo, pageSize, auctionExample);
	}
	
	@Override
	public long getAllCounts() {
		return auctionDao.getAllCounts();
	}
	
	@Override
	public int getAllCounts(Auction auctionExample) {
		return auctionDao.getAllCounts(auctionExample);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void addAuction(Auction auction) {
		auctionDao.addAuction(auction);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateAuction(Auction auction) {
		auctionDao.updateAuction(auction);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void createAuctionBid(AuctionRecord record) throws Exception {
		//�Ȳ�ѯ����ǰ���Ķ�Ӧ����Ʒ����
		Auction auction = auctionDao.getAuction(record.getAuction().getAuctionid());
		
		//1.�ж�ʱ���Ƿ���Ч
		if(auction.getAuctionendtime().after(new Date()) == false){
			throw new Exception("����Ʒ�Ѿ����ڣ�");
		}
		
		//2.�ж���û�о��ļ�¼
		if(auction.getAuctionRecords().size() != 0){
			//��ȡ��߳��ۼ�¼
			AuctionRecord maxRecord = (AuctionRecord) auction.getAuctionRecords().toArray()[0];
			if(record.getAuctionprice() <= maxRecord.getAuctionprice()){
				throw new Exception("���۱�����ڵ�ǰ��߾��ļۣ�");
			}
			
		}else{//û�о��ļ�¼�����۱���������ļ�
			if(record.getAuctionprice() <= auction.getAuctionstartprice()){
				throw new Exception("���۱���������ļۣ�");
			}
		}
		auctionRecordDao.addAuctionRecord(record);
	}
	
	public void setAuctionDao(AuctionDao auctionDao) {
		this.auctionDao = auctionDao;
	}

	public void setAuctionRecordDao(AuctionRecordDao auctionRecordDao) {
		this.auctionRecordDao = auctionRecordDao;
	}

	@Override
	public List<Auction> selectBiddedAuction(AuctionUser user) {
		String hql = "select distinct a from Auction a inner join fetch a.auctionRecords r where a.auctionendtime<:endtime and r.auctionUser.userid=:userid";
		String[] paramNames = {"endtime", "userid"};
		Object[] values = {new Date(), user.getUserid()};
		List<Auction> list = auctionDao.find(hql, paramNames, values);
		
		for (Auction auction : list) {
			AuctionRecord maxRecord = (AuctionRecord) auction.getAuctionRecords().toArray()[0];
			auction.setUsername(maxRecord.getAuctionUser().getUsername());
			auction.setAuctionPrice(maxRecord.getAuctionprice());
		}
		return list;
	}

	@Override
	public List<Auction> selectBiddingAuction(AuctionUser user) {
		String hql = "select distinct a from Auction a inner join fetch a.auctionRecords r where a.auctionendtime>=:endtime and r.auctionUser.userid=:userid";
		String[] paramNames = {"endtime", "userid"};
		Object[] values = {new Date(), user.getUserid()};
		List<Auction> list = auctionDao.find(hql, paramNames, values);
		
		for (Auction auction : list) {
			AuctionRecord maxRecord = (AuctionRecord) auction.getAuctionRecords().toArray()[0];
			auction.setUsername(maxRecord.getAuctionUser().getUsername());
			auction.setAuctionPrice(maxRecord.getAuctionprice());
		}
		return list;
	}
	
}
