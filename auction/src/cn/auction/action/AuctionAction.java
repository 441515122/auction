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
	
	private int pageNo;//页码
	private int allPages;//总页数
	private int next;//上一页
	private int prev;//下一页
	private int pageSize = 12;//每页记录数
	
	//以下是文件上传的数据（由structs 框架拦截器完成数据的注入）
	private File pic;	//pic另存到Tomact的文件夹中
	private String picContentType;		//文件的内容类型pic +"ContentType"  jpg gif
	private String picFileName;		//文件的名称
	
	//文件保存路径的参数注入
	private String savePath;
	
	//竞拍物品Id
	private int auctionId;
	
	//竞拍价格
	private double auctionPrice;
	
	//竞拍错误消息
	private String ErrorMsg;
	
	private Auction auctionExample;
	
	private List<Auction> endList;
	
	private List<Auction> noendList;
	/**
	 * 
	 * 接受商品发布请求（1.文件上传处理 2.添加商品到数据库）
	 * @return
	 */  
	public String publishAuctin(){
		try {
			File destFile = new File(this.getSavePath() + "\\" + picFileName);
			FileUtils.copyFile(pic, destFile);
			
			//设置文件名称
			auction.setAuctionpictype(picFileName);
			auctionService.addAuction(auction);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 分页加载物品信息
	 * @return
	 */  
	public String auctionList(){
		page();//修改页码
		auctionList = auctionService.findAuctionByPages(pageNo, pageSize,auctionExample);
		return SUCCESS;
	}
	
	/**
	 * 退出登录
	 * @return
	 */  
	public String logout(){
		super.session.clear();
		return SUCCESS;
	}
	
	/**
	 * 删除物品
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
	 * 根据Id获取到拍卖品的详细信息
	 * @return
	 */
	public String detail(){
		auction = auctionService.getAuction(auctionid);
		return SUCCESS;
	}
	
	/**
	 * 修改拍卖品信息
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
				
				//设置文件名称
				auction.setAuctionpictype(picFileName);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		updateAuctionService.updateAuction(auction);
		return SUCCESS;
	}
	
	/**
	 * 添加竞拍记录
	 * @return
	 * @throws Exception 
	 */
	public String doAuctionBid() throws Exception{
		try {
			AuctionRecord record = new AuctionRecord();
			
			//设置Auction与User的关系
			auction = new Auction();
			auction.setAuctionid(auctionId);
			record.setAuction(auction);
			
			AuctionUser auctionUser = (AuctionUser)this.session.get("user");
			record.setAuctionUser(auctionUser);
			
			//设置当前时间为竞拍时间
			record.setAuctiontime(new Date());
			
			//设置价格
			record.setAuctionprice(auctionPrice);
			
			auctionService.createAuctionBid(record);
		} catch (Exception e) {
			this.setErrorMsg(e.getMessage());
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	
	
	/**
	 * 页码计算
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
	 * 查看竞拍结果
	 * @author YS
	 * @date : 2017年6月21日 下午3:00:54
	 */  
	public String toAuctionResult(){
		AuctionUser user = (AuctionUser) this.session.get("user");
		
		//查询过期商品
		endList = auctionService.selectBiddedAuction(user);
//		for (Auction auction : endList) {
//			System.out.println(auction.getAuctionname());
//		}
		//查询正在拍卖商品
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
	
	//upload 在tomcat的绝对路径
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
