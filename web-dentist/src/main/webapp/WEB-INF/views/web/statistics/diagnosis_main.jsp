<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="스마투스코리아" />
<meta name="description" content="Smartooth" />
<title>Smartooth :: 치아 모니터링 시스템 :: 진단지</title>
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<!-- <script type="text/javascript" src="js/premium/common/diagnosis.js"></script> -->
<script type="text/javascript" src="js/premium/common/diagnosis.js"></script>
<link rel="shortcut icon" type="image/x-icon" href="/imgs/common/logo_img_ori.png">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="js/statistics/html2canvas.js"></script>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/css/common/layout.css">
<link rel="stylesheet" href="/css/web/statistics/diagnosis_main.css">
</head>
<body>
<div class="container-wrap">
	<div class="container" id="container">
		<div class="input-form-backgroud row">
			<div class="input-form col-md-12 mx-auto">
				<div id="background" class="background">
					<div class="container-top">
						<div class="container-margin"></div>
						<div id="userName" class="userName">${dataList.userName}</div>
						<div id="" class="measureDt-title">측정 일자</div>
						<div id="measuerDt" class="measureDt">
								${measureDt}
						</div>
					</div>
					<div class="contentsWrap">
				<div class="temp1" style="width: 22px;">
				</div>
						<div class="contentsWrap1 toothCondition">
							<div class="toothwrap">
	
								<div id="t11" class="teeth"></div>
								<div id="t12" class="teeth"></div>
								<div id="t13" class="teeth"></div>
								<div id="t14" class="teeth"></div>
								<div id="t15" class="teeth"></div>
								<div id="t16" class="teeth"></div>
								<div id="t17" class="teeth"></div>
								<div id="t18" class="teeth"></div>
	
								<div id="t21" class="teeth"></div>
								<div id="t22" class="teeth"></div>
								<div id="t23" class="teeth"></div>
								<div id="t24" class="teeth"></div>
								<div id="t25" class="teeth"></div>
								<div id="t26" class="teeth"></div>
								<div id="t27" class="teeth"></div>
								<div id="t28" class="teeth"></div>
								
								<div id="t31" class="teeth"></div>
								<div id="t32" class="teeth"></div>
								<div id="t33" class="teeth"></div>
								<div id="t34" class="teeth"></div>
								<div id="t35" class="teeth"></div>
								<div id="t36" class="teeth"></div>
								<div id="t37" class="teeth"></div>
								<div id="t38" class="teeth"></div>
	
								<div id="t41" class="teeth"></div>
								<div id="t42" class="teeth"></div>
								<div id="t43" class="teeth"></div>
								<div id="t44" class="teeth"></div>
								<div id="t45" class="teeth"></div>
								<div id="t46" class="teeth"></div>
								<div id="t47" class="teeth"></div>
								<div id="t48" class="teeth"></div>
	
								<div id="t51" class="teeth"></div>
								<div id="t52" class="teeth"></div>
								<div id="t53" class="teeth"></div>
								<div id="t54" class="teeth"></div>
								<div id="t55" class="teeth"></div>
	
								<div id="t61" class="teeth"></div>
								<div id="t62" class="teeth"></div>
								<div id="t63" class="teeth"></div>
								<div id="t64" class="teeth"></div>
								<div id="t65" class="teeth"></div>
	
								<div id="t71" class="teeth"></div>
								<div id="t72" class="teeth"></div>
								<div id="t73" class="teeth"></div>
								<div id="t74" class="teeth"></div>
								<div id="t75" class="teeth"></div>
	
								<div id="t81" class="teeth"></div>
								<div id="t82" class="teeth"></div>
								<div id="t83" class="teeth"></div>
								<div id="t84" class="teeth"></div>
								<div id="t85" class="teeth"></div>
							</div>
						</div>
							<div class="temp2" style="width: 24px;">
							</div>
							<div class="contentsWrap2 cavityAmount">
							<div class="cavityValueHeight-top"></div>
							<div class="cavityValue-danger-title">
								영구치(심각)
							</div>
							<div class="cavityValue" id="cavityDanger">
								${pmCvDangerCnt}
							</div>
							<div class="cavityValueHeight-middle"></div>
							<div class="cavityValue-caution-title">
								영구치(주의)
							</div>
							<div class="cavityValue" id="cavityCaution">
								${pmCvCautionCnt}
							</div>
							<div class="cavityValueHeight-middle"></div>
							<div class="cavityValue-danger-title">
								유치(심각)
							</div>
							<div class="cavityValue" id="cavityDanger">
								${babyCvDangerCnt}
							</div>
							<div class="cavityValueHeight-middle"></div>
							<div class="cavityValue-caution-title">
								유치(주의)
							</div>
							<div class="cavityValue" id="cavityCaution">
								${babyCvCautionCnt}
							</div>
							<div class="commonWidth125Height25"></div>
							<div class="commonWidth125Height25"></div>
							<div class="commonWidth125Height25"></div>
							<button id="btn_downloadImg" style="margin-left: 25px; font-size: 15px;">화면저장</button>
						</div>
						<div class="temp3" style="width: 25px;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">

$(document).ready(function() {
	
	// 버튼 클릭시 스크린샷 후 저장
	$("#btn_downloadImg").click(function(e){

		// 치아 측정 값 visibility hidden 처리
		$(".teethValue").css("display", "none");
		html2canvas(document.getElementById("background"), {scale:6}).then(function(canvas) {
			$(".teethValue").css("display", "block");
            var el = document.createElement("a")
            el.href = canvas.toDataURL("image/jpeg");
    		var measureDt = "${dataList.measureDt}";
    		var userName = "${dataList.userName}";
    		var dentalHospitalNm = "${dentalHospitalNm}";
            el.download = userName+" 환자_"+measureDt+".png"; //다운로드 할 파일명 설정
            el.click();
            
        });
    });
	
	// 세션 만료 시 창 닫기
	if('${session}' == 'expired') {
		alert("세션이 만료되어 로그아웃합니다. \n다시 로그인 해주시기 바랍니다.");
		window.close();
	}
	
	// 값을 가져와서 데이터를 확인하여 어떤 색으로 변경할지 정해줘야함
	var dataList = new Array();
	var dataListValue = new Array();
	var index = 0;
	var diagDescript = "${dataList.diagDescript}";
	var cautionLevel = "${cautionLevel}";
	var dangerLevel = "${dangerLevel}"; 
	
	
	dataList.push({
		userId:"${dataList.userId}"
		
		// 유치 상악
		,t51:"${dataList.t38}"
		,t52:"${dataList.t37}"
		,t53:"${dataList.t36}"
		,t54:"${dataList.t35}"
		,t55:"${dataList.t34}"
		,t61:"${dataList.t39}"
		,t62:"${dataList.t40}"
		,t63:"${dataList.t41}"
		,t64:"${dataList.t42}"
		,t65:"${dataList.t43}"
		// 유치 하악		
		,t71:"${dataList.t50}"
		,t72:"${dataList.t49}"
		,t73:"${dataList.t48}"
		,t74:"${dataList.t47}"
		,t75:"${dataList.t46}"
		,t81:"${dataList.t51}"
		,t82:"${dataList.t52}"
		,t83:"${dataList.t53}"
		,t84:"${dataList.t54}"
		,t85:"${dataList.t55}"

		// 영구치
		,t16:"${dataList.t16}"
		,t26:"${dataList.t26}"
		,t36:"${dataList.t36}"
		,t46:"${dataList.t46}"
		
		
		
		// 영구치 상악
		,t11:"${dataList.t08}"
		,t12:"${dataList.t07}"
		,t13:"${dataList.t06}"
		,t14:"${dataList.t05}"
		,t15:"${dataList.t04}"
		,t16:"${dataList.t03}"
		,t17:"${dataList.t02}"
		,t18:"${dataList.t01}"
		,t21:"${dataList.t09}"
		,t22:"${dataList.t10}"
		,t23:"${dataList.t11}"
		,t24:"${dataList.t12}"
		,t25:"${dataList.t13}"
		,t26:"${dataList.t14}"
		,t27:"${dataList.t15}"
		,t28:"${dataList.t16}"
		
		// 영구치 하악
		,t31:"${dataList.t24}"
		,t32:"${dataList.t23}"
		,t33:"${dataList.t22}"
		,t34:"${dataList.t21}"
		,t35:"${dataList.t20}"
		,t36:"${dataList.t19}"
		,t37:"${dataList.t18}"
		,t38:"${dataList.t17}"
		,t41:"${dataList.t25}"
		,t42:"${dataList.t26}"
		,t43:"${dataList.t27}"
		,t44:"${dataList.t28}"
		,t45:"${dataList.t29}"
		,t46:"${dataList.t30}"
		,t47:"${dataList.t31}"
		,t48:"${dataList.t32}"
		
		,cavityNormal:"${cavityNormal}"
		,cavityCaution:"${cavityCaution}"
		,cavityDanger:"${cavityDanger}"

	});
	
	
	dataListValue.push({
		userId:"${dataList.userId}"
		// 유치 상악
		,t51:"${dataList.t38}"
		,t52:"${dataList.t37}"
		,t53:"${dataList.t36}"
		,t54:"${dataList.t35}"
		,t55:"${dataList.t34}"
		,t61:"${dataList.t39}"
		,t62:"${dataList.t40}"
		,t63:"${dataList.t41}"
		,t64:"${dataList.t42}"
		,t65:"${dataList.t43}"
		// 유치 하악		
		,t71:"${dataList.t50}"
		,t72:"${dataList.t49}"
		,t73:"${dataList.t48}"
		,t74:"${dataList.t47}"
		,t75:"${dataList.t46}"
		,t81:"${dataList.t51}"
		,t82:"${dataList.t52}"
		,t83:"${dataList.t53}"
		,t84:"${dataList.t54}"
		,t85:"${dataList.t55}"

		// 영구치
		,t16:"${dataList.t16}"
		,t26:"${dataList.t26}"
		,t36:"${dataList.t36}"
		,t46:"${dataList.t46}"
		
		
		
		// 영구치 상악
		,t11:"${dataList.t08}"
		,t12:"${dataList.t07}"
		,t13:"${dataList.t06}"
		,t14:"${dataList.t05}"
		,t15:"${dataList.t04}"
		,t16:"${dataList.t03}"
		,t17:"${dataList.t02}"
		,t18:"${dataList.t01}"
		,t21:"${dataList.t09}"
		,t22:"${dataList.t10}"
		,t23:"${dataList.t11}"
		,t24:"${dataList.t12}"
		,t25:"${dataList.t13}"
		,t26:"${dataList.t14}"
		,t27:"${dataList.t15}"
		,t28:"${dataList.t16}"
		
		// 영구치 하악
		,t31:"${dataList.t24}"
		,t32:"${dataList.t23}"
		,t33:"${dataList.t22}"
		,t34:"${dataList.t21}"
		,t35:"${dataList.t20}"
		,t36:"${dataList.t19}"
		,t37:"${dataList.t18}"
		,t38:"${dataList.t17}"
		,t41:"${dataList.t25}"
		,t42:"${dataList.t26}"
		,t43:"${dataList.t27}"
		,t44:"${dataList.t28}"
		,t45:"${dataList.t29}"
		,t46:"${dataList.t30}"
		,t47:"${dataList.t31}"
		,t48:"${dataList.t32}"
		
		,cavityNormal:"${cavityNormal}"
		,cavityCaution:"${cavityCaution}"
		,cavityDanger:"${cavityDanger}"

	});
	
	// 	치아 색상 변경		
	changeToothColorByLevel(dataList, index, cautionLevel, dangerLevel);
	
	// 치아 안의 측정 값 표시
	displayToothMeasureValue(dataListValue);
	
});
	

// function onSelect(){ // 날짜 선택
// 	var userId = "${dataList.userId}";
// 	var userName = "${dataList.userName}";
// 	var measureDt = $("#measureDtList").val();
// 	// 값을 가져와서 데이터를 확인하여 어떤 색으로 변경할지 정해줘야함
// 	var index = 0;
// 	var dataList = new Array();
// 	var diagDescript = "${dataList.diagDescript}";
// 	var cautionLevel = "${cautionLevel}";
// 	var dangerLevel = "${dangerLevel}"; 
	
// 	$.ajax({
// 		type : 'POST',
// 		url : '/web/statistics/ajaxDiagnosis',
// 		data:JSON.stringify ({
// 			"userId" : userId
// 			,"userName" : userName
// 			,"measureDt" : measureDt
// 		}),
// 		dataType:'JSON',
// 		contentType : "application/json; charset=UTF-8",
// 		success : function(data) { 
// 			var code = data.code;
// 			if(code == '999'){
// 				// 세션 만료 시
// 				alert("세션이 만료되어 로그아웃합니다. \n다시 로그인 해주시기 바랍니다.");
// 				location.href = "/login";
// 				return;
// 			}else if(code == '000'){
// 				// 조회 성공 시
// 				dataList.push({
// 					userId: data.dataList.userId
					
// 					,t51:data.dataList.t38
// 					,t52:data.dataList.t37
// 					,t53:data.dataList.t36
// 					,t54:data.dataList.t35
// 					,t55:data.dataList.t34

// 					,t61:data.dataList.t39
// 					,t62:data.dataList.t40
// 					,t63:data.dataList.t41
// 					,t64:data.dataList.t42
// 					,t65:data.dataList.t43
					
// 					,t71:data.dataList.t50
// 					,t72:data.dataList.t49
// 					,t73:data.dataList.t48
// 					,t74:data.dataList.t47
// 					,t75:data.dataList.t46
					
// 					,t81:data.dataList.t51
// 					,t82:data.dataList.t52
// 					,t83:data.dataList.t53
// 					,t84:data.dataList.t54
// 					,t85:data.dataList.t55

// 					// 영구치
// 					,t16:data.dataList.t16
// 					,t26:data.dataList.t26
// 					,t36:data.dataList.t36
// 					,t46:data.dataList.t46
					
					
// 					// 영구치 상악
// 					,t12:data.dataList.t07
// 					,t11:data.dataList.t08
// 					,t21:data.dataList.t09
// 					,t22:data.dataList.t10
					
// 					// 영구치 하악
// 					,t32:data.dataList.t23
// 					,t31:data.dataList.t24
// 					,t41:data.dataList.t25
// 					,t42:data.dataList.t26
					
// 				});
				
// 				// 정상, 주의, 충치 개수 변경
// 				$("#cavityNormal").html(data.cavityNormal);
// 				$("#cavityCaution").html(data.cavityCaution);
// 				$("#cavityDanger").html(data.cavityDanger);
// 				$("#diag-title").html(data.dataList.diagTitle);
// 				$("#diagDescript").html(data.dataList.diagDescript);
				
// 				// 	치아 색상 변경		
// 				changeToothColorByLevel(dataList, index, cautionLevel, dangerLevel);
// 				// container 높이 재조정			
// 				// resetContainerHeight();
// 			}
			
			
			
// 		},
// 		error : function(){
// 			alert("측정 자료 조회에 실패했습니다. \n관리자에게 문의해주시기 바랍니다.");
// 		}
// 	});
// }
</script>
</html>