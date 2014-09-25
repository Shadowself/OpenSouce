package com.newcapec.mobile.tv.electric.bean;

import java.io.Serializable;

import com.newcapec.mobile.tv.electric.util.Tools;

public class Page implements Serializable {
	public static int INIT_PAGE_SIZE = 5;
	/**
	 * 
	 */
	private static final long serialVersionUID = 6055970393059679571L;
	private int pageSize = INIT_PAGE_SIZE;
	private int listSize;
	private int currentPage;
	private int totoalPages;
	private int totalNums;
	private int beginItemNum;
	private int endItemNum;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotoalPages() {
		return totoalPages;
	}

	public void setTotoalPages(int totoalPages) {
		this.totoalPages = totoalPages;
	}

	public int getBeginItemNum() {
		return beginItemNum;
	}

	public void setBeginItemNum(int beginItemNum) {
		this.beginItemNum = beginItemNum;
	}

	public int getEndItemNum() {
		return endItemNum;
	}

	public void setEndItemNum(int endItemNum) {
		this.endItemNum = endItemNum;
	}

	public int getTotalNums() {
		return totalNums;
	}

	public void setTotalNums(int totalNums) {
		this.totalNums = totalNums;
	}

	public void initPagnationParam() {

		// 计算总页数
		float floatTotalCount = this.getTotalNums();
		float floatTotalPages = floatTotalCount / this.getPageSize();
		float totalPages = this.getTotalNums() / this.getPageSize();
		if (totalPages != floatTotalPages) {
			totalPages = totalPages + 1;
		}
		this.setTotoalPages((int) (totalPages));

		// 当前页一个记录数
		if (this.getCurrentPage() == 1) {
			this.setBeginItemNum(0);
		} else {
			int startNum = (this.getCurrentPage() - 1) * this.getPageSize();
			this.setBeginItemNum(startNum);
		}
		if (this.getCurrentPage() < this.getTotoalPages()) {
			this.setEndItemNum(this.getBeginItemNum() + this.getPageSize() - 1);
		} else if (this.getCurrentPage() == this.getTotoalPages()) {
			this.setEndItemNum(this.getTotalNums() - 1);
		}
	}

	public void pageCount() {
		// 计算总页数
		float floatTotalCount = this.getTotalNums();
		float floatTotalPages = floatTotalCount / this.getPageSize();
		float totalPages = this.getTotalNums() / this.getPageSize();
		if (totalPages != floatTotalPages) {
			totalPages = totalPages + 1;
		}
		this.setTotoalPages((int) (totalPages));
	}
}
