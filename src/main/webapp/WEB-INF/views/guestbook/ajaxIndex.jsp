<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GuestbookAddList</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>

	<div id="wrap">

		<!-- header -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //nav -->
		<!-- //nav -->
		<!-- header -->
	
		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax방명록</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">
				
				<div id="content-head" class="clearfix">
					<h3>ajax방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">ajax방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form action="${pageContext.request.contextPath}/guest/add" method="get">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label></th>
									<td><input id="input-uname" type="text" name="name"></td>
									<th><label class="form-text" for="input-pass">패스워드</label></th>
									<td><input id="input-pass"type="password" name="pw"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button type="submit">등록</button></td>
								</tr>
							</tbody>
							
						</table>
						<!-- //guestWrite -->
						
					</form>	
					
					<div id="guestbookListArea"></div>
					<!-- 방명록 리스트 -->
					 
				</div>
				<!-- //guestbook -->
			
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<!-- //footer -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->
		
	</div>
	<!-- //wrap -->

</body>
<script>
//DOM tree가 생성되었을 때
document.addEventListener("DOMContentLoaded", function(){
   //리스트 요청 데이터만 받기
   axios({
        method: 'get',           // put, post, delete                   
        url: '/mysite6/api/guestbooks',
        headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입
        //params: guestbookVo,  //get방식 파라미터로 값이 전달
        //data: guestbookVo,   //put, post, delete 방식 자동으로 JSON으로 변환 전달
        responseType: 'json' //수신타입
    })
    .then(function (response) {
        //console.log(response); //수신데이타
        console.log(response.data); //수신데이타
        
        //리스트자리에 글을 추가한다
        for(let i=0; i<response.data.length; i++){
        	let guestVo = response.data[i];
        	render(guestVo);
        }
        
    })
    .catch(function (error) {
        console.log(error);
    });
});//

//함수들

//방명록 글 작성
function render(guestbookVo){
	console.log("render()");
	console.log(guestbookVo);
	
	let guestbookListArea = document.querySelector("#guestbookListArea");
	console.log(guestbookListArea);
	
	let str = '';
	str += '<table class="guestRead">';
	str += '	<colgroup>';
	str += '		<col style="width: 10%;">';
	str += '		<col style="width: 40%;">';
	str += '		<col style="width: 40%;">';
	str += '		<col style="width: 10%;">';
	str += '	</colgroup>';
	str += '	<tr>';
	str += '		<td>' + guestbookVo.no + '</td>';
	str += '		<td>' + guestbookVo.name + '</td>';
	str += '		<td>' + guestbookVo.regDate + '</td>';
	str += '		<td><a href="">[삭제]</a></td>';
	str += '	</tr>';
	str += '	<tr>';
	str += '		<td colspan=4 class="text-left">' + guestbookVo.content + '</td>';
	str += '	</tr>';
	str += '</table>';
			
	guestbookListArea.insertAdjacentHTML("beforeend", str);
}

</script>
</html>