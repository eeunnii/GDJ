<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	// 요청 파라미터 
	request.setCharacterEncoding("UTF-8");
	String item = request.getParameter("item");
	int amount = Integer.parseInt(request.getParameter("amount"));
	
	// 제품+구매수량-> Map 
	Map<String,Object> product = new HashMap<>();
	product.put("item",item);
	product.put("amount", amount);
	
	//  session에 장바구니를 cart 속성으로 저장한 상황이다.
	// 1. session에 cart 속성이 있는지 확인한다. 
	// 2. cart 속성이 없으면 새로 만들어서 저장한다. 
	// 배열, 리스트가 cart의 type
	// List<map> 실무에서 많이 나옴
	List<Map<String, Object>> cart = (List<Map<String, Object>>)session.getAttribute("cart");
	if(cart == null){
		cart = new ArrayList<>();
		session.setAttribute("cart", cart);
	}
	
	// session의 cart에 product 저장하기(장바구니 물건 처음 담을 때는 새로운 카드고 그 이후에 담는건 있던 카트)
	cart.add(product);
	
%>

<script>
	alret('<%=product.get("item")%>) 제품을 장바구니에 추가했습니다.');
	if(confirm('장바구니를 확인하려면 "확인", 계속 쇼핑하려면 "취소"를 누르세요.')){
		location.href='03_cart_list.jsp';
	}else{
		location.href='01_form.jsp';
	}