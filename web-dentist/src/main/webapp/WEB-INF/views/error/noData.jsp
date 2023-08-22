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
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/css/common/layout.css">
<link rel="stylesheet" href="/css/web/statistics/diagnosis_main.css">
<style type="text/css">
body{
	font-family: 'NanumSquareR';
    display: flex;
    justify-content: center;
    margin-top: 0px;
    margin-left: 0px;
    margin-bottom: 0px;
    margin-right: 0px;
    height: 100vh;
    width: 100%;
    align-items: center;
}

.wrapper{
	z-index: 1;
	display: flex;
    width: 100px;
    height: 100px;
    background-color: gray;
    position: absolute;
    width: 317px;
    height: 455px;
    padding: 30px;
    text-align: center;
    background-color: gray;
    border-radius: 10px;
    box-shadow: 0 2px 3px 0 rgb(34 36 38 / 15%);
    opacity: 40%;
    align-items: center;
    justify-content: center;
    font-family: 'NanumSquareR';
    font-weight: bold;
    font-size: 24px;
}

.top-wrapper{
    z-index: 100;
    display: flex;
    position: absolute;
    width: 317px;
    height: 455px;
    padding: 30px;
    text-align: center;
    align-items: center;
    justify-content: center;
    font-family: 'NanumSquareR';
    font-weight: bold;
    font-size: 24px;
    line-height: 1.4;
}
    
.cavityValue{
	font-weight: bold;
}

</style>
</head>
<body>
<div class="wrapper"></div>
<div class="top-wrapper">${userName}님의<br/>${measureDt}<br/>측정 기록은 없습니다.</div>
<div class="container-wrap">
	<div class="container" id="container">
		<div class="input-form-backgroud row">
			<div class="input-form col-md-12 mx-auto">
				<div class="background">
					<div class="container-top">
						<div class="container-margin"></div>
						<div id="userName" class="userName"></div>
						<div id="" class="measureDt-title">측정 일자</div>
						<div id="measuerDt" class="measureDt">
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
								0
							</div>
							<div class="cavityValueHeight-middle"></div>
							<div class="cavityValue-caution-title">
								영구치(주의)
							</div>
							<div class="cavityValue" id="cavityCaution">
								0
							</div>
							<div class="cavityValueHeight-middle"></div>
							<div class="cavityValue-danger-title">
								유치(심각)
							</div>
							<div class="cavityValue" id="cavityDanger">
								0
							</div>
							<div class="cavityValueHeight-middle"></div>
							<div class="cavityValue-caution-title">
								유치(주의)
							</div>
							<div class="cavityValue" id="cavityCaution">
								0
							</div>
							<div class="commonWidth125Height25"></div>
							<div class="commonWidth125Height25"></div>
							<div class="commonWidth125Height25"></div>
							<div class="commonWidth125Height25"></div>
							<div class="commonWidth125Height25"></div>
						</div>
						<div class="temp3" style="width: 25px;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>