<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>치과 목록 검색 (정보제공 동의/미동의 확인)</title>
</head>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#submit').click(function(){ 
		$.ajax({
			type:'POST',   //post 방식으로 전송
			url:'/customer/organ/selectDentalHospitalList.do',   //데이터를 주고받을 파일 주소
			data:JSON.stringify ({ //변수에 담긴 데이터를 전송해준다 (JSON 방식)
				"userId" : "KRPA__________01-mochalove@smartooth.co"
				,"dentalHospitalNm" : "스마투스"
			}),
			dataType:'JSON', //데이터 타입 JSON
			contentType : "application/json; charset=UTF-8",
			success : function(data){   //파일 주고받기가 성공했을 경우. data 변수 안에 값을 담아온다.
				console.log(JSON.stringify(data));
			}
		});
	});
});
</script>
<body>
		치과 목록 검색 (정보제공 동의/미동의 확인) >>> <input type="button" id="submit" value="버튼"/>
</body>
</html>