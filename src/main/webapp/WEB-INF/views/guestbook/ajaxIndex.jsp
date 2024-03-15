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
<style>
.modal{
	width: 100%;
	height: 100%;
	position: fixed;
	top: 0;
	left: 0;
	z-index: 999;
	overflow: auto;
	background: rgba(0, 0, 0, .4);
	display: none;
}

#myModal .modal-content{
	width: 400px;
	margin: 100px auto;
	padding: 0px 20px 20px 20px;
	background: #ffffff;
	border : 1px solid #888888;
}

.closeBtn{
	text-aline: right;
	color: #aaaaaa;
	font-size: 28px;
	font-weight: bold;
	cursor: pointer;
}
</style>
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
					<form id="guestForm" action="" method="get">
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
									<td><input id="input-pass" type="password" name="pw"></td>
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

					<!-- 모달 창 컨텐츠 -->
					<div id="myModal" class="modal">
						<div id="guestbook" class="modal-content">
							<div class="closeBtn">×</div>
							<div class="m-header">패스워드를 입력하세요</div>
							<div class="m-body">
								<input class="m-password" type="password" name="password" value=""><br>
								<input type="text" name="no" value="">
							</div>
							<div class="m-footer">
								<button class="btnDelete" type="button">삭제</button>
							</div>
						</div>
					</div>

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

	//리스트요청해서 데이터 받고 그리기
	getListAndRender();
   
   //등록버튼 클릭했을때(데이터만 받기)
   let guestForm = document.querySelector("#guestForm");
   guestForm.addEventListener("submit", addAndRender);
   
   //모달창 호출 버튼을 클릭했을때
   let guestbookListArea = document.querySelector("#guestbookListArea");
   
   guestbookListArea.addEventListener("click", callModal);// guestbookListArea
   
   //모달창 닫기(X)
   let closeBtn = document.querySelector(".closeBtn");
   closeBtn.addEventListener("click", closeModal);
   
   //모달창의 삭제버튼 클릭 했을때
   let btnDelete = document.querySelector(".btnDelete");
   btnDelete.addEventListener("click", deleteAndRemove);//btnDelete
   
});//DOMtree

////////////////////////////함수들////////////////////////////////////

//모달창에서 삭제 누르기(글 삭제)
function deleteAndRemove(){
	   console.log("클릭");
	   
	   //데이터 모으고
	   let pw = document.querySelector('#myModal [name="password"]').value;
	   let no = document.querySelector('#myModal [name="no"]').value;
	   let guestVo = {
			   pw: pw,
			   no: no
	   };
	   
	   //서버로 전송
	   axios({
		   method: 'delete', // put, post, delete
		   url: '${pageContext.request.contextPath}/api/guestbooks/' + no +'',
		   headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입
		   params: guestVo, //get방식 파라미터로 값이 전달
		   //data: guestbookVo, //put, post, delete 방식 자동으로 JSON으로 변환 전달
		   responseType: 'json' //수신타입
		   }).then(function (response) {
		   	console.log(response); //수신데이타
		   	console.log(response.data); //수신데이타
		   	
		   	if(response.data == 1){
				let tagid = "#t-" + no;
				let removeTag = document.querySelector(tagid);
				
				removeTag.remove();
				
				let modal = document.querySelector(".modal");
				modal.style.display = "none";
		   	}
		   	
		   }).catch(function (error) {
		  	 console.log(error);
		   });
	   
}//deleteAndRemove

//모달창 닫기
function closeModal(){
	   console.log("모달창 닫기");
	   
	   let modal = document.querySelector(".modal");
		modal.style.display = "none";
	   
}//closeModal

//모달창 보이기
function callModal(event){
		console.log(event.target.tagName);
		
		if(event.target.tagName=="BUTTON"){
			
			console.log("모달창 보이기");
			
			let modal = document.querySelector(".modal");
			modal.style.display = "block";
			
			
			//hidden에 no값을
			let noTag = document.querySelector('[name="no"]');
			noTag.value = event.target.dataset.no;
			
			//패스워드창 비우기
			document.querySelector('.m-password').value = "";
		}
		
}//callModal

//글 저장하고 그리기
function addAndRender(event) {
	event.preventDefault();
	   console.log("글쓰기버튼 클릭");
	   
	   //폼의 데이터 가져오기
	   let name = document.querySelector('[name="name"]').value;
	   let pw = document.querySelector('[name="pw"]').value;
	   let content = document.querySelector('[name="content"]').value;
	   
	   let guestVo = {
			name: name,
			pw: pw,
			content: content
	   };
	   console.log(guestVo);
	   
	   //서버로 데이터 전송
	   axios({
		   method: 'post', // put, post, delete
		   url: '${pageContext.request.contextPath}/api/guestbooks',
		   headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입
		   //params: guestVo, //get방식 파라미터로 값이 전달
		   data: guestVo, //put, post, delete 방식 자동으로 JSON으로 변환 전달
		   responseType: 'json' //수신타입
		   }).then(function (response) {
		   	console.log(response); //수신데이타
		   	console.log(response.data); //수신데이타
		   	let guestbookVo = response.data;
		   	
		   	render(guestbookVo, "up");
		   	
		   	document.querySelector('[name="name"]').value="";
		   	document.querySelector('[name="pw"]').value="";
		   	document.querySelector('[name="content"]').value="";
		   	
		   }).catch(function (error) {
		  	 console.log(error);
		   });
}//addAndRender

//리스트 가져와서 render
function getListAndRender() {
	   //리스트 요청 데이터만 받기
	   axios({
	        method: 'get',           // put, post, delete                   
	        url: '${pageContext.request.contextPath}/api/guestbooks',
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
	        	render(guestVo, "up");
	        }
	        
	    })
	    .catch(function (error) {
	        console.log(error);
	    });
}//getListAndRender

//방명록 글 작성
function render(guestbookVo, dir){
	//console.log("render()");
	//console.log(guestbookVo);
	
	let guestbookListArea = document.querySelector("#guestbookListArea");
	console.log(guestbookListArea);
	
	let str = '';
	str += '<table id="t-'+ guestbookVo.no +'" class="guestRead">';
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
	str += '		<td><button class="btnModal" type="button" data-no="'+ guestbookVo.no +'">삭제</button></td>';
	str += '	</tr>';
	str += '	<tr>';
	str += '		<td colspan=4 class="text-left">' + guestbookVo.content + '</td>';
	str += '	</tr>';
	str += '</table>';
			
	if(dir == "down"){
		guestbookListArea.insertAdjacentHTML("beforeend", str);
		
	} else if(dir =="up"){
		guestbookListArea.insertAdjacentHTML("afterbegin", str);
		
	} else{
		console.log("방향체크");
	}
}

</script>
</html>