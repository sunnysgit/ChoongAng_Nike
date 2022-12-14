package com.ch.nike.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ch.nike.dto.Refund;
import com.ch.nike.mapper.RefundMapper;

@Service
public class RefundServiceImpl implements RefundService {
	@Autowired
	private RefundMapper rm;
	
	// AccountCon
	public Refund selectPro(int orderDetailNo) {
		return rm.selectPro(orderDetailNo);
	}
	public int selectRefundNum() {
		return rm.selectRefundNum();
	}
	public void insertRefund(Refund refund) {
		rm.insertRefund(refund);
	}
	public void updateRefundChk(int orderDetailNo) {
		rm.updateRefundChk(orderDetailNo);
	}
	
	
	

	

}
