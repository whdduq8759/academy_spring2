<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>
        .pagination {
            width: 60%;
            margin-top: 10px;
            list-style: none;
            display: flex;
        }

        .pagination>li {
            justify-content: flex-end;
            margin-right: 5px;
        }

        .pagination>li>a {
            text-decoration: none;
            color: black;
        }

        .pagination>li>a:hover {
            color: yellowgreen;
        }

        .pagination>li.active>a {
            font-weight: bold;
            color: orangered;
            font-size: 1.1em;
        }

        .amount {
            width: 100%;
            display: flex;
            justify-content: flex-end;
            margin-bottom: 10px;
            position: absolute;
            right: 6%;
            top: 12%;
        }

        .amount a {
            display: block;
            color: #fff;
            background: #f00;
            width: 50px;
            height: 20px;
            border-radius: 5px;
            margin-right: 5px;
            text-align: center;
            font-weight: 700;
            text-decoration: none;

        }
 
    </style>

    <%@ include file= "../include/static-head.jsp" %>
</head>

<body>


    <%@ include file= "../include/header.jsp" %>

    <h2>게시글 목록</h2>

    <div class="amount">
        <a href="#">10</a>
        <a href="#">20</a>
        <a href="#">30</a>
    </div>

    <table border="1" class="table table-hover">
        <tr class="table-dark">
            <th>번호</th>
            <th>작성자</th>
            <th>제목</th>
            <th>작성시간</th>
            <th>조회수</th>
            <th>비고</th>
        </tr>


        <c:forEach var="article" items="${articles}">
            <tr>
                <td>${article.boardNo}</td>
                <td>${article.writer}</td>
                <td>
                    <a href="/board/content?boardNo=${article.boardNo}">${article.title}</a>

                    <c:if test="${article.newFlag}">
                        <span class="badge rounded-pill bg-danger">new</span>
                    </c:if>

                </td>
                <td>
                    <fmt:formatDate value="${article.regDate}" pattern="yyyy-MM-dd a hh:mm:ss" />


                </td>
                <td>${article.viewCnt}</td>
                <td>

                    <a data-board-no="${article.boardNo}" class="del-btn" href="#">[삭제]</a>
                    </a>

                </td>
            </tr>
        </c:forEach>

    </table>

    <!-- 페이지 영역 -->
    <ul class="pagination">


    </ul>


    <!-- 검색창 영역 -->
    <div class="search">
        <form action="/board/list" id="search-form">

            <select name="type">
                <option value="title">제목</option>
                <option value="content">내용</option>
                <option value="writer">작성자</option>
                <option value="titleContent">제목+내용
                </option>
            </select>

            <input type="text" name="keyword" placeholder="검색어를 입력!" value="">

            <button type="submit">검색</button>

        </form>
    </div>

    <p>
        <a href="/board/write">게시글 작성하기</a>
    </p>

    <script>
        // 게시물 등록 처리 알림
        const msg = '${msg}';
        if (msg === 'success') {
            alert('게시물 등록 성공!')
        } else if (msg === 'fail') {
            alert('게시물 등록 실패!')
        }


        // 삭제 버튼 클릭 이벤트
        const table = document.querySelector('table');


        table.addEventListener('click', e => {

            if (!e.target.matches('table a.del-btn')) return;

            e.preventDefault(); // a태그 링크이동기능 중지

            console.log('삭제 버튼 클릭됨!');

            const boardNo = e.target.dataset.boardNo;

            if (confirm('정말로 삭제하시겠습니까?')) {
                location.href = '/board/delete?boardNo=' + boardNo;
            }
        });
    </script>




</body>

</html>