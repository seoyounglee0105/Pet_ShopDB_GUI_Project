package com.mungyang.controller;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.mungyang.dto.OrderDTO;
import com.mungyang.service.MemberService;
import com.mungyang.service.OrderService;
import com.mungyang.service.ProductService;
import com.mungyang.viewFrame.ShopFrame;

public class OrderController {

	private OrderService orderService;
	private MemberService memberService;
	private ProductService productService;
	private ShopFrame mContext;
	
	public OrderController(ShopFrame mContext) {
		this.mContext = mContext;
		orderService = new OrderService();
		memberService = new MemberService();
		productService = new ProductService();
	}
	
	// 주문 추가 요청
	public int requestAddOrder(OrderDTO orderDTO) {
		int responseType = 0;
				
		// 성공하면 1 반환
		responseType = orderService.addOrder(orderDTO);
	
		if (responseType == 1) {
			// 제품의 sales 증가
			productService.updateSales(orderDTO.getAmount(), orderDTO.getProductId());
			
			// 해당 회원의 총 주문 금액 합을 확인해서 회원 등급을 갱신함
			ArrayList<OrderDTO> list = orderService.selectOrder(orderDTO.getMemberId());
			int totalPriceSum = 0;
			for (OrderDTO dto : list) {
				totalPriceSum += dto.getTotalPrice();
			}
			String old_grade = memberService.findMember(orderDTO.getMemberId()).getMemberGrade();
			String new_grade = null;
			
			// 총 주문 금액 합이 500000원 이상이면 골드 등급
			if (totalPriceSum >= 500000) {
				memberService.updateGrade("Gold", orderDTO.getMemberId());
				mContext.getLoginMemberDto().setMemberGrade("Gold");
				mContext.updateGradeIcon("Gold");
				new_grade = "Gold";
			// 총 주문 금액 합이 100000원 이상이면 실버 등급
			} else if (totalPriceSum >= 100000) {
				memberService.updateGrade("Silver", orderDTO.getMemberId());
				mContext.getLoginMemberDto().setMemberGrade("Silver");
				mContext.updateGradeIcon("Silver");
				new_grade = "Silver";
			// 그 외
			} else {
				memberService.updateGrade("Bronze", orderDTO.getMemberId());
				mContext.getLoginMemberDto().setMemberGrade("Bronze");
				mContext.updateGradeIcon("Bronze");
				new_grade = "Bronze";
			}
			
			// 회원 등급이 변경되었다면 2 반환
			if (old_grade.equals(new_grade) == false) {
				responseType = 2;
			}
		}	
		return responseType;
	}
	
	// 주문 조회 요청 (회원별)
	public ArrayList<OrderDTO> requestSelectOrder(String memberId){
		ArrayList<OrderDTO> resultList = new ArrayList<>();
		
		resultList = orderService.selectOrder(memberId);
		return resultList;
	}
	
	
	
}
