<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과지 호출 API</title>
</head>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">



$(document).ready(function(){
	$('#submit').click(function(){ 
// 		$.ajax({
// 			type:'POST',   //post 방식으로 전송
// 			url:'/dentist/login.do',   //데이터를 주고받을 파일 주소
// 			data:JSON.stringify ({ //변수에 담긴 데이터를 전송해준다 (JSON 방식)
// 				"userId" : "KRDE1120068010-1"
// 				,"measureDt" : "2023-08-08"
// 			}),
// 			dataType:'JSON', //데이터 타입 JSON
// 			contentType : "application/json; charset=UTF-8",
// 			success : function(data){   //파일 주고받기가 성공했을 경우. data 변수 안에 값을 담아온다.
// 				$('#message').html(data);  //현재 화면 위 id="message" 영역 안에 data안에 담긴 html 코드를 넣어준다. 
// 			}
// 		});
		alert("호출합니다.");
		location.href="/admin/statistics/diagnosis.do?userId=KRDE1120068010-1&measureDt=2023-08-08"
	});
});
</script>
<body>
		결과지 호출 API >>> <input type="button" id="submit" value="버튼"/>
</body>
</html>