package cn.auction.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.auction.entity.Auction;
import cn.auction.entity.AuctionRecord;
import cn.auction.entity.AuctionUser;
import cn.auction.service.AuctionService;
import cn.auction.service.UpdateAuctionService;

public class AuctionAction extends BaseAction{
	
	private List<Auction> auctionList;
	private AuctionService auctionService;
	private UpdateAuctionService updateAuctionService;
	private Integer auctionid;
	private Auction auction;
	
	private int pageNo;//ҳ��
	private int allPages;//��ҳ��
	private int next;//��һҳ
	private int prev;//��һҳ
	private int pageSize = 12;//ÿҳ��¼��
	
	//�������ļ��ϴ������ݣ���structs ���������������ݵ�ע�룩
	private File pic;	//pic��浽Tomact���ļ�����
	private String picContentType;		//�ļ�����������pic +"ContentType"  jpg gif
	private String picFileName;		//�ļ�������
	
	//�ļ�����·���Ĳ���ע��
	private String savePath;
	
	//������ƷId
	private int auctionId;
	
	//���ļ۸�
	private double auctionPrice;
	
	//���Ĵ�����Ϣ
	private String ErrorMsg;
	
	private Auction auctionExample;
	
	private List<Auction> endList;
	
	private List<Auction> noendList;
	/**
	 * 
	 * ������Ʒ��������1.�ļ��ϴ����� 2.�����Ʒ�����ݿ⣩
	 * @return
	 */  
	public String publishAuctin(){
		try {
			File destFile = new File(this.getSavePath() + "\\" + picFileName);
			FileUtils.copyFile(pic, destFile);
			
			//�����ļ�����
			auction.setAuctionpictype(picFileName);
			auctionService.addAuction(auction);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * ��ҳ������Ʒ��Ϣ
	 * @return
	 */  
	public String auctionList(){
		page();//�޸�ҳ��
		auctionList = auctionService.findAuctionByPages(pageNo, pageSize,auctionExample);
		return SUCCESS;
	}
	
	/**
	 * �˳���¼
	 * @return
	 */  
	public String logout(){
		super.session.clear();
		return SUCCESS;
	}
	
	/**
	 * ɾ����Ʒ
	 * @return
	 */
	public String doDelete(){
		auction = auctionService.getAuction(auctionid);
		System.out.println(auction.getAuctionpictype());
		File file = new File(this.getSavePath() + "\\" + auction.getAuctionpictype());
		if(file.exists()){
			file.delete();
		}
		auctionService.delete(auction);
		return SUCCESS;
	}
	
	/**
	 * ����Id��ȡ������Ʒ����ϸ��Ϣ
	 * @return
	 */
	public String detail(){
		auction = auctionService.getAuction(auctionid);
		return SUCCESS;
	}
	
	/**
	 * �޸�����Ʒ��Ϣ
	 * @return
	 */
	public String doUpdate(){
		if(pic != null){
			try {
				File file = new File(this.getSavePath() + "\\" + auction.getAuctionpictype());
				if(file.exists()){
					file.delete();
				}
				File destFile = new File(this.getSavePath() + "\\" + picFileName);
				FileUtils.copyFile(pic, destFile);
				
				//�����ļ�����
				auction.setAuctionpictype(picFileName);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		updateAuctionService.updateAuction(auction);
		return SUCCESS;
	}
	
	/**
	 * ��Ӿ��ļ�¼
	 * @return
	 * @throws Exception 
	 */
	public String doAuctionBid() throws Exception{
		try {
			AuctionRecord record = new AuctionRecord();
			
			//����Auction��User�Ĺ�ϵ
			auction = new Auction();
			auction.setAuctionid(auctionId);
			record.setAuction(auction);
			
			AuctionUser auctionUser = (AuctionUser)this.session.get("user");
			record.setAuctionUser(auctionUser);
			
			//���õ�ǰʱ��Ϊ����ʱ��
			record.setAuctiontime(new Date());
			
			//���ü۸�
			record.setAuctionprice(auctionPrice);
			
			auctionService.createAuctionBid(record);
		} catch (Exception e) {
			this.setErrorMsg(e.getMessage());
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	
	
	/**
	 * ҳ�����
	 * @return
	 */
	private void page(){
		int allCounts = (int)auctionService.getAllCounts(auctionExample);
		if(allCounts % pageSize == 0){
			allPages = allCounts / pageSize;
		}else{
			allPages = (allCounts / pageSize)+1;
		}
		if(pageNo == 0){
			pageNo = 1;
		}
		if(pageNo > allPages){
			pageNo = allPages;
		}
		
		next = pageNo;
		prev = pageNo;
		if(pageNo <= 1){
			next++;
			prev = 1;
		}else if(pageNo >= allPages){
			next = allPages;
			prev--;
		}else{
			next++;
			prev--;
		}
	}
	
	
	/**
	 * �鿴���Ľ��
	 * @author YS
	 * @date : 2017��6��21�� ����3:00:54
	 */  
	public String toAuctionResult(){
		AuctionUser user = (AuctionUser) this.session.get("user");
		
		//��ѯ������Ʒ
		endList = auctionService.selectBiddedAuction(user);
//		for (Auction auction : endList) {
//			System.out.println(auction.getAuctionname());
//		}
		//��ѯ����������Ʒ
		noendList = auctionService.selectBiddingAuction(user);
//		for (Auction auction : noendList) {
//			System.out.println(auction.getAuctionname());
//		}
		return SUCCESS;
	}
	
	
	public void setAuctionService(AuctionService auctionService) {
		this.auctionService = auctionService;
	}

	public List<Auction> getAuctionList() {
		return auctionList;
	}

	public void setAuctionList(List<Auction> auctionList) {
		this.auctionList = auctionList;
	}

	public int getAuctionid() {
		return auctionid;
	}

	public void setAuctionid(int auctionid) {
		this.auctionid = auctionid;
	}

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getAllPages() {
		return allPages;
	}

	public void setAllPages(int allPages) {
		this.allPages = allPages;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public int getPrev() {
		return prev;
	}

	public void setPrev(int prev) {
		this.prev = prev;
	}

	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public String getPicContentType() {
		return picContentType;
	}

	public void setPicContentType(String picContentType) {
		this.picContentType = picContentType;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}
	
	//upload ��tomcat�ľ���·��
	public String getSavePath() {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public int getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}

	public double getAuctionPrice() {
		return auctionPrice;
	}

	public void setAuctionPrice(double auctionPrice) {
		this.auctionPrice = auctionPrice;
	}

	public String getErrorMsg() {
		return ErrorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}

	public Auction getAuctionExample() {
		return auctionExample;
	}

	public void setAuctionExample(Auction auctionExample) {
		this.auctionExample = auctionExample;
	}

	public void setUpdateAuctionService(UpdateAuctionService updateAuctionService) {
		this.updateAuctionService = updateAuctionService;
	}

	public List<Auction> getEndList() {
		return endList;
	}

	public void setEndList(List<Auction> endList) {
		this.endList = endList;
	}

	public List<Auction> getNoendList() {
		return noendList;
	}

	public void setNoendList(List<Auction> noendList) {
		this.noendList = noendList;
	}
	
}
