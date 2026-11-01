<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.ProductDAO" %>
<%@ page import="dto.ProductDTO" %>
<%@ include file="/include/adminCheck.jspf" %>
<%
    request.setCharacterEncoding("UTF-8");

    String name = request.getParameter("name");
    String priceStr = request.getParameter("price");
    String stockStr = request.getParameter("stock");
    String category = request.getParameter("category");
    String description = request.getParameter("description");

    // 필수값 검증
    if (name == null || name.trim().isEmpty()
            || priceStr == null || priceStr.trim().isEmpty()
            || stockStr == null || stockStr.trim().isEmpty()
            || category == null || category.trim().isEmpty()) {
        response.sendRedirect("productWrite.jsp?error=empty");
        return;
    }

    int price;
    int stock;
    try {
        price = Integer.parseInt(priceStr.trim());
        stock = Integer.parseInt(stockStr.trim());
        if (price < 0 || stock < 0) {
            response.sendRedirect("productWrite.jsp?error=number");
            return;
        }
    } catch (NumberFormatException e) {
        response.sendRedirect("productWrite.jsp?error=number");
        return;
    }

    if (description != null) {
        description = description.trim();
        if (description.isEmpty()) {
            description = null;
        }
    }

    ProductDTO p = new ProductDTO();
    p.setName(name.trim());
    p.setPrice(price);
    p.setStock(stock);
    p.setCategory(category.trim());
    p.setDescription(description);

    new ProductDAO().insert(p);

    // 등록 후 목록으로 이동
    response.sendRedirect("productList.jsp");
%>
