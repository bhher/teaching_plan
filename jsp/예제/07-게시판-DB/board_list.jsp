<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="jsp.board.BoardDTO" %>
<%@ page import="jsp.board.BoardDAO" %>
<%@ page import="jsp.board.DBConnection" %>
<%
    // 페이징 파라미터
    String pageParam = request.getParameter("page");
    int currentPage = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;
    int pageSize = 10;  // 한 페이지에 보여줄 게시글 수
    int start = (currentPage - 1) * pageSize;
    
    // 검색 파라미터
    String searchType = request.getParameter("searchType");
    String searchKeyword = request.getParameter("searchKeyword");
    if (searchType == null) searchType = "";
    if (searchKeyword == null) searchKeyword = "";
    
    Connection conn = null;
    List<BoardDTO> boardList = null;
    int totalCount = 0;
    int totalPages = 0;
    
    try {
        conn = DBConnection.getConnection();
        BoardDAO boardDAO = new BoardDAO(conn);
        
        if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
            // 검색
            totalCount = boardDAO.getSearchCount(searchType, searchKeyword);
            boardList = boardDAO.searchBoards(searchType, searchKeyword, start, pageSize);
        } else {
            // 전체 목록
            totalCount = boardDAO.getTotalCount();
            boardList = boardDAO.selectAllBoards(start, pageSize);
        }
        
        totalPages = (int) Math.ceil((double) totalCount / pageSize);
        
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        DBConnection.close(conn);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판 목록</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: 'Malgun Gothic', sans-serif;
            background-color: #f5f5f5;
            padding: 20px;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h2 {
            margin-bottom: 20px;
            color: #333;
        }
        .top-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            border: none;
            cursor: pointer;
            font-size: 14px;
        }
        .btn:hover {
            background-color: #45a049;
        }
        .btn-primary {
            background-color: #2196F3;
        }
        .btn-primary:hover {
            background-color: #1976D2;
        }
        .search-box {
            display: flex;
            gap: 10px;
            margin-bottom: 20px;
        }
        .search-box select {
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .search-box input[type="text"] {
            flex: 1;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .search-box button {
            padding: 8px 15px;
            background-color: #666;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
            font-weight: bold;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .title-cell {
            max-width: 400px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
        .title-cell a {
            color: #333;
            text-decoration: none;
        }
        .title-cell a:hover {
            color: #4CAF50;
            text-decoration: underline;
        }
        .pagination {
            text-align: center;
            margin-top: 20px;
        }
        .pagination a {
            display: inline-block;
            padding: 8px 12px;
            margin: 0 5px;
            color: #333;
            text-decoration: none;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .pagination a:hover {
            background-color: #4CAF50;
            color: white;
            border-color: #4CAF50;
        }
        .pagination .current {
            background-color: #4CAF50;
            color: white;
            border-color: #4CAF50;
        }
        .empty-message {
            text-align: center;
            padding: 50px;
            color: #999;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>게시판</h2>
        
        <div class="top-bar">
            <div>
                <span>총 <%= totalCount %>개의 게시글</span>
            </div>
            <a href="board_write.jsp" class="btn">글쓰기</a>
        </div>
        
        <!-- 검색 박스 -->
        <form action="board_list.jsp" method="get" class="search-box">
            <select name="searchType">
                <option value="title" <%= "title".equals(searchType) ? "selected" : "" %>>제목</option>
                <option value="writer" <%= "writer".equals(searchType) ? "selected" : "" %>>작성자</option>
                <option value="content" <%= "content".equals(searchType) ? "selected" : "" %>>내용</option>
            </select>
            <input type="text" name="searchKeyword" value="<%= searchKeyword %>" placeholder="검색어를 입력하세요">
            <button type="submit">검색</button>
            <% if (searchKeyword != null && !searchKeyword.isEmpty()) { %>
                <a href="board_list.jsp" class="btn btn-primary">전체보기</a>
            <% } %>
        </form>
        
        <!-- 게시글 목록 -->
        <% if (boardList != null && boardList.size() > 0) { %>
        <table>
            <thead>
                <tr>
                    <th style="width: 80px;">번호</th>
                    <th>제목</th>
                    <th style="width: 120px;">작성자</th>
                    <th style="width: 100px;">조회수</th>
                    <th style="width: 180px;">등록일</th>
                </tr>
            </thead>
            <tbody>
                <% for (BoardDTO board : boardList) { %>
                <tr>
                    <td><%= board.getId() %></td>
                    <td class="title-cell">
                        <a href="board_view.jsp?id=<%= board.getId() %>">
                            <%= board.getTitle() %>
                        </a>
                    </td>
                    <td><%= board.getWriter() %></td>
                    <td><%= board.getHit() %></td>
                    <td><%= board.getRegDate() != null ? board.getRegDate().toString().substring(0, 16) : "" %></td>
                </tr>
                <% } %>
            </tbody>
        </table>
        
        <!-- 페이징 -->
        <% if (totalPages > 0) { %>
        <div class="pagination">
            <% if (currentPage > 1) { %>
                <a href="board_list.jsp?page=<%= currentPage - 1 %><%= searchKeyword != null && !searchKeyword.isEmpty() ? "&searchType=" + searchType + "&searchKeyword=" + searchKeyword : "" %>">이전</a>
            <% } %>
            
            <% for (int i = 1; i <= totalPages; i++) { %>
                <% if (i == currentPage) { %>
                    <span class="current"><%= i %></span>
                <% } else { %>
                    <a href="board_list.jsp?page=<%= i %><%= searchKeyword != null && !searchKeyword.isEmpty() ? "&searchType=" + searchType + "&searchKeyword=" + searchKeyword : "" %>"><%= i %></a>
                <% } %>
            <% } %>
            
            <% if (currentPage < totalPages) { %>
                <a href="board_list.jsp?page=<%= currentPage + 1 %><%= searchKeyword != null && !searchKeyword.isEmpty() ? "&searchType=" + searchType + "&searchKeyword=" + searchKeyword : "" %>">다음</a>
            <% } %>
        </div>
        <% } %>
        
        <% } else { %>
        <div class="empty-message">
            등록된 게시글이 없습니다.
        </div>
        <% } %>
    </div>
</body>
</html>
