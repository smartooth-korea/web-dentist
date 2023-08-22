<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="㈜스마트코리아" />
<meta name="description" content="Smartooth" />
<title>Smartooth :: 치아 모니터링 시스템 :: 진단지(자문치과)</title>
<!-- FAVICON ICO ERROR 방지 -->
<link rel="shortcut icon" type="image/x-icon" href="/imgs/common/logo_img_ori.png">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="js/premium/common/diagnosis.js"></script>
<link rel="stylesheet" href="/css/common/layout.css">
<link rel="stylesheet" href="/css/web/statistics/diagnosis_advice.css">
</head>
	<div class="menu">
		<img id="diagnosis_data_btn" class="menu-btn" src="/imgs/layout/btn/diagnosis_data_btn_enabled.png" style="width: 170px;" onclick="location.href='/web/statistics/diagnosis.do'">
		<img id="diagnosis_graph_btn" class="menu-btn" src="/imgs/layout/btn/diagnosis_graph_btn_disabled.png" style="width: 170px;" onclick="location.href='/web/statistics/graph.do'">
	</div>
	<div class="container-wrap">
		<div class="container" id="container">
			<div class="container-top">
				<div class="container-margin"></div>
				<div id="userName" class="userName">${dataList.userName}</div>
				<div id="" class="measureDt-title">측정 일자</div>
				<div id="measuerDt" class="measureDt">
					<select style="font-size: 17px;" id="measureDtList" onchange="onSelect()">
					<c:set var="arr" value="${measureDtList}"></c:set>
						<c:forEach var="item" items="${measureDtList}">
							<option value="${item}">${item}</option>						
						</c:forEach>
					</select>
				</div>
			</div>
	
			<div class="contentsWrap">
				<div style="width: 100px;">
				</div>			
				<div class="contentsWrap1 toothCondition">
	
					<div id="t56">
						<img id="tooth56" class="teeth" alt="영구치56번" src="/imgs/tooth/empty.png">
					</div>
	
					<div id="t55">
						<img id="tooth55" class="teeth" alt="55번치아" src="/imgs/tooth/empty.png">
					</div>
					<div id="t54">
						<img id="tooth54" class="teeth" alt="54번치아" src="/imgs/tooth/empty.png">
					</div>
					<div id="t53">
						<img id="tooth53" class="teeth" alt="53번치아" src="/imgs/tooth/empty.png">
					</div>
					<div id="t52">
						<img id="tooth52" class="teeth" alt="52번치아" src="/imgs/tooth/empty.png">
					</div>
					<div id="t51">
						<img id="tooth51" class="teeth" alt="51번치아" src="/imgs/tooth/empty.png">
					</div>
					
					<div id="t61">
						<img id="tooth61" class="teeth" alt="61번치아" src="/imgs/tooth/empty.png">
					</div>
					<div id="t62">
						<img id="tooth62" class="teeth" alt="62번치아" src="/imgs/tooth/empty.png">
					</div>
					<div id="t63">
						<img id="tooth63" class="teeth" alt="63번치아" src="/imgs/tooth/empty.png">
					</div>
					<div id="t64">
						<img id="tooth64" class="teeth" alt="64번치아" src="/imgs/tooth/empty.png">
					</div>
					<div id="t65">
						<img id="tooth65" class="teeth" alt="65번치아" src="/imgs/tooth/empty.png">
					</div>
					
					<div id="t66">
						<img id="tooth66" class="teeth" alt="영구치66번" src="/imgs/tooth/empty.png">
					</div>
					
					<div id="t71">
						<img id="tooth71" class="teeth" alt="71번치아" src="/imgs/tooth/empty.png">
					</div>
					<div id="t72">
						<img id="tooth72" class="teeth" alt="72번치아" src="/imgs/tooth/empty.png">
					</div>
					<div id="t73">
						<img id="tooth73" class="teeth" alt="73번치아" src="/imgs/tooth/empty.png">
					</div>
					<div id="t74">
						<img id="tooth74" class="teeth" alt="74번치아" src="/imgs/tooth/empty.png">
					</div>
					<div id="t75">
						<img id="tooth75" class="teeth" alt="75번치아" src="/imgs/tooth/empty.png">
					</div>
					
					<div id="t76">
						<img id="tooth76" class="teeth" alt="영구치76번" src="/imgs/tooth/empty.png">
					</div>
					
					<div id="t81">
						<img id="tooth81" class="teeth" alt="81번치아" src="/imgs/tooth/empty.png">
					</div>
					<div id="t82">
						<img id="tooth82" class="teeth" alt="82번치아" src="/imgs/tooth/empty.png">
					</div>
					<div id="t83">
						<img id="tooth83" class="teeth" alt="83번치아" src="/imgs/tooth/empty.png">
					</div>
					<div id="t84">
						<img id="tooth84" class="teeth" alt="84번치아" src="/imgs/tooth/empty.png">
					</div>
					<div id="t85">
						<img id="tooth85" class="teeth" alt="85번치아" src="/imgs/tooth/empty.png">
					</div>
					
					<div id="t86">
						<img id="tooth86" class="teeth" alt="영구치86번" src="/imgs/tooth/empty.png">
					</div>
	
				</div>
				<div class="contentsWrap2 cavityAmount">
					<div class="cavityValueHeight-top"></div>
					<div class="cavityValue-danger-title">
						병원 내원
					</div>
					<div class="cavityValue" id="cavityDanger">
						${cavityDanger}
					</div>
					<div class="cavityValueHeight-middle"></div>
					<div class="cavityValue-caution-title">
						관리 필요
					</div>
					<div class="cavityValue" id="cavityCaution">
						${cavityCaution}
					</div>
					<div class="cavityValueHeight-bottom"></div>
					<div class="cavityValue-normal-title">
						정 상
					</div>
					<div class="cavityValue" id="cavityNormal">
						${cavityNormal}
					</div>
					<div class="commonWidth125Height25"></div>
					<div class="commonWidth125Height25"></div>
					<div class="commonWidth125Height25"></div>
					<div class="commonWidth125Height25"></div>
					<div class="commonWidth125Height25"></div>
				</div>
				<div style="width: 100px;">
				</div>	
			</div>
			<div class="contentsWrap3 comment" id="comment">
				<div class="title">
					<div id="diag-title">
						<c:choose>
							<c:when test="${dataList.diagTitle ne null}">
								"${dataList.diagTitle}"
							</c:when>
							<c:otherwise>
								"제목을 입력해주세요"
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="contents" id="contents">
					<div id="diagDescript">
						${dataList.diagDescript}
					</div>
				</div>
			</div>
			<div class="contents-bottom-div">
				<div class="contents-bottom">
							<div>*본 결과지는 참고용으로만 사용하시고, 정확한 진단을 위해 치과 방문을 권장합니다.</div>
				</div>
				<div class="commonHeight22"></div>
				<div style="color: #ffffff; text-align: center; fon">
					자문치과 : ${adviceDentistInfo.dentistName}  ${adviceDentistInfo.dentistNum}
				</div>
			</div>
		</div>
	</div>


<!-- Bootstrap core JavaScript-->
<script src="vendor/bootstrap/js/bootstrap.bundle.js"></script>
<script src="vendor/jquery-easing/jquery.easing.js"></script>
<script src="js/common/sb-admin-2.js"></script>
<script src="js/premium/admin/statistics/html2canvas.js"></script>
<script type="text/javascript">
	
function resetContainerHeight(){

	// 진단 내용 div의 높이
	var diagDescriptHeight = parseInt(document.getElementById('diagDescript').offsetHeight);
	var containerHeight = parseInt(document.getElementById("container").clientHeight);
	var commentHeight = parseInt(document.getElementById("comment").clientHeight);
	var contentsHeight = parseInt(document.getElementById("contents").clientHeight);
	// 기존 높이 (스크롤이 넘어가지 않았을 때의 높이)
	var originalHeight = 275;
	var variableHeight = parseInt(diagDescriptHeight-originalHeight);
	

	if(variableHeight < 0){
		$("#container").css("height", 815);
		// comment 높이 변경 (5px)
		$("#comment").css("height", 325);
		// contents 높이 변경 (5px)
		$("#contents").css("height", 281);
	}else{
		$("#container").css("height", 815 + variableHeight);
		// comment 높이 변경 (5px)
		$("#comment").css("height", 325 + variableHeight);
		// contents 높이 변경 (5px)
		$("#contents").css("height", 295 + variableHeight);
	}

}

$(document).ready(function() {
	
	// container 크기 변경
	resetContainerHeight();
	
	// 세션 만료 시 창 닫기
	if('${session}' == 'expired') {
		alert("세션이 만료되어, 로그아웃 되었습니다. \n다시 로그인 해주시기 바랍니다.");
		window.close();
	}
	
	// 값을 가져와서 데이터를 확인하여 어떤 색으로 변경할지 정해줘야함
	var dataList = new Array();
	var index = 0;
	var diagDescript = '${dataList.diagDescript}';
	var cautionLevel = "${cautionLevel}";
	var dangerLevel = "${dangerLevel}"; 
	
	
	dataList.push({
		userId:"${dataList.userId}"
		,t51:"${dataList.t08}"
		,t52:"${dataList.t07}"
		,t53:"${dataList.t06}"
		,t54:"${dataList.t05}"
		,t55:"${dataList.t04}"
		// 영구치
		,t56:"${dataList.t03}"
		,t61:"${dataList.t09}"
		,t62:"${dataList.t10}"
		,t63:"${dataList.t11}"
		,t64:"${dataList.t12}"
		,t65:"${dataList.t13}"
		// 영구치
		,t66:"${dataList.t14}"
		,t71:"${dataList.t24}"
		,t72:"${dataList.t23}"
		,t73:"${dataList.t22}"
		,t74:"${dataList.t21}"
		,t75:"${dataList.t20}"
		// 영구치
		,t76:"${dataList.t19}"
		,t81:"${dataList.t25}"
		,t82:"${dataList.t26}"
		,t83:"${dataList.t27}"
		,t84:"${dataList.t28}"
		,t85:"${dataList.t29}"
		// 영구치
		,t86:"${dataList.t30}"
		,cavityNormal:"${cavityNormal}"
		,cavityCaution:"${cavityCaution}"
		,cavityDanger:"${cavityDanger}"

	});
	
	// 	치아 색상 변경		
	changeToothColorByLevel(dataList, index, cautionLevel, dangerLevel);
	
	
});
	

function onSelect(){ // 날짜 선택
	
	var measureDt = $("#measureDtList").val();
	var userId = "${dataList.userId}";
	// 값을 가져와서 데이터를 확인하여 어떤 색으로 변경할지 정해줘야함
	var index = 0;
	var dataList = new Array();
	var diagDescript = '${dataList.diagDescript}';
	var cautionLevel = "${cautionLevel}";
	var dangerLevel = "${dangerLevel}"; 
	
	$.ajax({
		type : 'POST',
		url : '/web/statistics/ajaxDiagnosis',
		data:JSON.stringify ({
			"userId" : userId
			,"measureDt" : measureDt
			
		}),
		dataType:'JSON',
		contentType : "application/json; charset=UTF-8",
		contentType : 'json',
		success : function(data) { 
				
			console.log(data.dataList);
			
			dataList.push({
				userId: data.dataList[0].userId
				,t51: data.dataList[0].t08
				,t52: data.dataList[0].t07
				,t53: data.dataList[0].t06
				,t54: data.dataList[0].t05
				,t55: data.dataList[0].t04
				// 영구치
				,t56: data.dataList[0].t03
				
				,t61: data.dataList[0].t09
				,t62: data.dataList[0].t10
				,t63: data.dataList[0].t11
				,t64: data.dataList[0].t12
				,t65: data.dataList[0].t13
				// 영구치
				,t66: data.dataList[0].t14
				
				,t71: data.dataList[0].t24
				,t72: data.dataList[0].t23
				,t73: data.dataList[0].t22
				,t74: data.dataList[0].t21
				,t75: data.dataList[0].t20
				// 영구치
				,t76: data.dataList[0].t19
				
				,t81: data.dataList[0].t25
				,t82: data.dataList[0].t26
				,t83: data.dataList[0].t27
				,t84: data.dataList[0].t28
				,t85: data.dataList[0].t29
				// 영구치
				,t86: data.dataList[0].t30
				
			});
			
			// 정상, 주의, 충치 개수 변경
			$("#cavityNormal").html(data.cavityNormal);
			$("#cavityCaution").html(data.cavityCaution);
			$("#cavityDanger").html(data.cavityDanger);
			$("#diagTitle").html(data.dataList[0].diagTitle);
			$("#diagDescript").html(data.dataList[0].diagDescript);
			
			// 	치아 색상 변경		
			changeToothColorByLevel(dataList, index, cautionLevel, dangerLevel);
			
			
			resetContainerHeight();
			
			
		},
		error : function(){
			alert("측정 자료 조회에 실패했습니다. \n관리자에게 문의해주시기 바랍니다.");
		}
	});
}
</script>
</html>