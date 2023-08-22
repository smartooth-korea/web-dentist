<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- WEB -->
<!-- menu -->
<script type="text/javascript">
function logout(){
	if (!confirm("로그아웃 하시겠습니까?")) {
        return false;
    } else {
    	window.location.href = "/logout.do";
    }
}
</script>
<div class="menu">
	<div id="diagnosis_data_btn" class="menu_btn" onclick="location.href='/web/statistics/diagnosis'">진단 결과지</div>
	<div id="diagnosis_graph_btn" class="menu_btn"  onclick="location.href='/web/statistics/graph'">구강관리에 따른 원아 순위</div>
<!-- 	<div id="logout" onclick="location.href='/web/statistics/logout.do'"> -->
	<div id="logout" onclick=logout();>
		<img id="logout_btn" alt="logout" src="/imgs/layout/btn/logout_btn.png">
	</div>
</div>
<!-- End of menu -->