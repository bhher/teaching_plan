<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.ProductDAO" %>
<%@ page import="dto.ProductDTO" %>
<%@ include file="/include/adminCheck.jspf" %>
<%
    request.setCharacterEncoding("UTF-8");

    String idParam = request.getParameter("id");
    String name = request.getParameter("name");
    String priceStr = request.getParameter("price");
    String stockStr = request.getParameter("stock");
    String category = request.getParameter("category");
    String description = request.getParameter("description");

    if (idParam == null || idParam.trim().isEmpty()) {
        response.sendRedirect("productList.jsp");
        return;
    }

    int id;
    try {
        id = Integer.parseInt(idParam.trim());
    } catch (NumberFormatException e) {
        response.sendRedirect("productList.jsp");
        return;
    }

    if (name == null || name.trim().isEmpty()
            || priceStr == null || priceStr.trim().isEmpty()
            || stockStr == null || stockStr.trim().isEmpty()
            || category == null || category.trim().isEmpty()) {
        response.sendRedirect("productUpdate.jsp?id=" + id + "&error=empty");
        return;
    }

    int price;
    int stock;
    try {
        price = Integer.parseInt(priceStr.trim());
        stock = Integer.parseInt(stockStr.trim());
        if (price < 0 || stock < 0) {
            response.sendRedirect("productUpdate.jsp?id=" + id + "&error=number");
            return;
        }
    } catch (NumberFormatException e) {
        response.sendRedirect("productUpdate.jsp?id=" + id + "&error=number");
        return;
    }

    if (description != null) {
        description = description.trim();
        if (description.isEmpty()) {
            description = null;
        }
    }

    ProductDTO p = new ProductDTO();
    p.setId(id);
    p.setName(name.trim());
    p.setPrice(price);
    p.setStock(stock);
    p.setCategory(category.trim());
    p.setDescription(description);

    new ProductDAO().update(p);

    response.sendRedirect("productView.jsp?id=" + id);
%>
