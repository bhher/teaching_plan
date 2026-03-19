<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="jsp.imageboard.ImageBoardDTO" %>
<%@ page import="jsp.imageboard.ImageBoardDAO" %>
<%@ page import="jsp.imageboard.DBConnection" %>
<%
    // 페이징 파라미터
    String pageParam = request.getParameter("page");
    int currentPage = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;
    int pageSize = 12;  // 한 페이지에 보여줄 게시글 수 (이미지 게시판은 12개)
    int start = (currentPage - 1) * pageSize;
    
    // 검색 파라미터
    String searchType = request.getParameter("searchType");
    String searchKeyword = request.getParameter("searchKeyword");
    if (searchType == null) searchType = "";
    if (searchKeyword == null) searchKeyword = "";
    
    Connection conn = null;
    List<ImageBoardDTO> boardList = null;
    int totalCount = 0;
    int totalPages = 0;
    
    try {
        conn = DBConnection.getConnection();
        ImageBoardDAO boardDAO = new ImageBoardDAO(conn);
        
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
    
    // 업로드 경로 (웹 경로)
    String uploadPath = request.getContextPath() + "/upload/images/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>이미지 게시판</title>
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
        h1 {
            margin-bottom: 30px;
            color: #333;
            text-align: center;
        }
        .header-actions {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
            flex-wrap: wrap;
        }
        .search-box {
            display: flex;
            gap: 10px;
            flex-wrap: wrap;
        }
        .search-box input {
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        /* 커스텀 Select 박스 스타일 */
        .custom-select-wrapper {
            position: relative;
            display: inline-block;
            min-width: 120px;
        }
        .search-box select {
            appearance: none;
            -webkit-appearance: none;
            -moz-appearance: none;
            padding: 8px 35px 8px 12px;
            border: 1px solid #ddd;
            border-radius: 4px;
            background-color: white;
            font-size: 14px;
            color: #333;
            cursor: pointer;
            transition: all 0.3s ease;
            outline: none;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .search-box select:hover {
            border-color: #667eea;
            box-shadow: 0 2px 8px rgba(102, 126, 234, 0.2);
        }
        .search-box select:focus {
            border-color: #667eea;
            box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
        }
        .custom-select-wrapper::after {
            content: '';
            position: absolute;
            top: 50%;
            right: 12px;
            transform: translateY(-50%) rotate(0deg);
            width: 14px;
            height: 14px;
            background-image: url('<%= request.getContextPath() %>/arrow-down.svg');
            background-size: contain;
            background-repeat: no-repeat;
            background-position: center;
            pointer-events: none;
            transition: transform 0.3s cubic-bezier(0.4, 0, 0.2, 1);
        }
        .custom-select-wrapper.select-open::after {
            transform: translateY(-50%) rotate(180deg);
        }
        .search-box button {
            padding: 8px 20px;
            background: #667eea;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .search-box button:hover {
            background: #764ba2;
        }
        .write-btn {
            padding: 10px 20px;
            background: #667eea;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            display: inline-block;
        }
        .write-btn:hover {
            background: #764ba2;
        }
        .board-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
            gap: 20px;
            margin-bottom: 30px;
        }
        .board-item {
            background: white;
            border: 1px solid #ddd;
            border-radius: 8px;
            overflow: hidden;
            transition: transform 0.3s, box-shadow 0.3s;
            cursor: pointer;
        }
        .board-item:hover {
            transform: translateY(-5px);
            box-shadow: 0 5px 15px rgba(0,0,0,0.2);
        }
        .board-image {
            width: 100%;
            height: 200px;
            object-fit: cover;
            background: #f0f0f0;
        }
        .board-info {
            padding: 15px;
        }
        .board-title {
            font-weight: bold;
            margin-bottom: 8px;
            color: #333;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
        .board-meta {
            display: flex;
            justify-content: space-between;
            font-size: 0.9em;
            color: #666;
            margin-top: 10px;
        }
        .no-image {
            width: 100%;
            height: 200px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-size: 3em;
        }
        .pagination {
            text-align: center;
            margin-top: 30px;
        }
        .pagination a {
            display: inline-block;
            padding: 8px 12px;
            margin: 0 5px;
            text-decoration: none;
            border: 1px solid #ddd;
            border-radius: 4px;
            color: #333;
        }
        .pagination a:hover,
        .pagination .current {
            background: #667eea;
            color: white;
            border-color: #667eea;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>📷 이미지 게시판</h1>
        
        <div class="header-actions">
            <div class="search-box">
                <div class="custom-select-wrapper" id="selectWrapper">
                    <select name="searchType" id="searchType">
                        <option value="title" <%= "title".equals(searchType) ? "selected" : "" %>>제목</option>
                        <option value="content" <%= "content".equals(searchType) ? "selected" : "" %>>내용</option>
                        <option value="writer" <%= "writer".equals(searchType) ? "selected" : "" %>>작성자</option>
                    </select>
                </div>
                <input type="text" id="searchKeyword" value="<%= searchKeyword %>" placeholder="검색어 입력">
                <button onclick="search()">검색</button>
            </div>
            <a href="board_write.jsp" class="write-btn">글쓰기</a>
        </div>
        
        <div class="board-grid">
            <% if (boardList != null && !boardList.isEmpty()) { %>
                <% for (ImageBoardDTO board : boardList) { %>
                    <div class="board-item" onclick="location.href='board_view.jsp?id=<%= board.getId() %>'">
                        <% if (board.hasImage()) { %>
                            <img src="<%= uploadPath %><%= board.getImageFile() %>" 
                                 alt="<%= board.getTitle() %>" 
                                 class="board-image"
                                 onerror="this.parentElement.querySelector('.no-image').style.display='flex'; this.style.display='none';">
                            <div class="no-image" style="display:none;">📷</div>
                        <% } else { %>
                            <div class="no-image">📷</div>
                        <% } %>
                        <div class="board-info">
                            <div class="board-title"><%= board.getTitle() %></div>
                            <div class="board-meta">
                                <span>👤 <%= board.getWriter() %></span>
                                <span>👁 <%= board.getHit() %></span>
                            </div>
                        </div>
                    </div>
                <% } %>
            <% } else { %>
                <div style="grid-column: 1 / -1; text-align: center; padding: 40px; color: #999;">
                    등록된 게시글이 없습니다.
                </div>
            <% } %>
        </div>
        
        <% if (totalPages > 0) { %>
            <div class="pagination">
                <% if (currentPage > 1) { %>
                    <a href="?page=<%= currentPage - 1 %><%= searchKeyword != null && !searchKeyword.isEmpty() ? "&searchType=" + searchType + "&searchKeyword=" + searchKeyword : "" %>">이전</a>
                <% } %>
                
                <% for (int i = 1; i <= totalPages; i++) { %>
                    <% if (i == currentPage) { %>
                        <span class="current"><%= i %></span>
                    <% } else { %>
                        <a href="?page=<%= i %><%= searchKeyword != null && !searchKeyword.isEmpty() ? "&searchType=" + searchType + "&searchKeyword=" + searchKeyword : "" %>"><%= i %></a>
                    <% } %>
                <% } %>
                
                <% if (currentPage < totalPages) { %>
                    <a href="?page=<%= currentPage + 1 %><%= searchKeyword != null && !searchKeyword.isEmpty() ? "&searchType=" + searchType + "&searchKeyword=" + searchKeyword : "" %>">다음</a>
                <% } %>
            </div>
        <% } %>
    </div>
    
    <script>
        function search() {
            var searchType = document.getElementById('searchType').value;
            var searchKeyword = document.getElementById('searchKeyword').value;
            location.href = '?searchType=' + searchType + '&searchKeyword=' + encodeURIComponent(searchKeyword);
        }
        
        // Enter 키로 검색
        document.getElementById('searchKeyword').addEventListener('keypress', function(e) {
            if (e.key === 'Enter') {
                search();
            }
        });
        
        // Select 박스 클릭 시 화살표 방향 변경
        (function() {
            var selectElement = document.getElementById('searchType');
            var selectWrapper = document.getElementById('selectWrapper');
            var isOpen = false;
            
            // Select 박스 포커스/블러 이벤트
            selectElement.addEventListener('focus', function() {
                selectWrapper.classList.add('select-open');
                isOpen = true;
            });
            
            selectElement.addEventListener('blur', function() {
                // 약간의 지연을 두어 option 선택이 완료된 후 닫기
                setTimeout(function() {
                    selectWrapper.classList.remove('select-open');
                    isOpen = false;
                }, 200);
            });
            
            // Select 박스 클릭 이벤트
            selectElement.addEventListener('mousedown', function(e) {
                if (!isOpen) {
                    selectWrapper.classList.add('select-open');
                    isOpen = true;
                } else {
                    selectWrapper.classList.remove('select-open');
                    isOpen = false;
                }
            });
            
            // Option 선택 시
            selectElement.addEventListener('change', function() {
                setTimeout(function() {
                    selectWrapper.classList.remove('select-open');
                    isOpen = false;
                }, 100);
            });
        })();
    </script>
</body>
</html>
