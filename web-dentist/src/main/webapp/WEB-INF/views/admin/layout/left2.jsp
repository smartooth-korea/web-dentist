<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">

ul{
	font-family: 'NanumSquareR';
}

.sidebar-heading{
    font-size: 14px;
}

.fas{
	margin-left: 5px;
}

.collapse-item{
	padding-left: 28px;
	font-size: 12px; font-weight: normal;	
}

h6{
	color: #318CDD;
}


</style>
<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/admin/main.do">
        <div>
            <img src="/imgs/common/logo_img.png" style="width: 50px;">
        </div>
<!--  <sup>2</sup> -->
        <div class="sidebar-brand-text mx-3" style="padding-top: 5px;">Smartooth</div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0"/>

    <!-- Nav Item - Dashboard -->
    <li class="nav-item active">
        <a class="nav-link" href='/admin/main.do' ">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span></a>
    </li>

    <!-- Heading -->
    <div class="sidebar-heading">
<!--         Interface -->
    </div>

    <c:if test="${userInfo.userType eq 'SU'}">
    <!-- Divider -->
    <hr class="sidebar-divider">
    <!-- Heading -->
    <div class="sidebar-heading">
        <h6>유치원 서비스</h6>
    </div>
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseDiagnosis"
            aria-expanded="true" aria-controls="collapseDiagnosis">
            <i class="fas fa-fw fa-sitemap"></i>
            <span>등록</span>
        </a>
        <div id="collapseDiagnosis" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header" style="color: #318CDD; text-shadow: 0px 0px 0px #318CDD; font-size: 12px; font-weight: bold;">
                <a href="/admin/organ/selectOrganList">유치원 기관 등록 / 조회</a>
            </h6>
            <h6 class="collapse-header" style="color: #318CDD; text-shadow: 0px 0px 0px #318CDD; font-size: 12px; font-weight: bold;">
                <a href="/admin/organ/selectDepartmentList">유치원 반 등록 / 조회</a>
            </h6>
            <h6 class="collapse-header" style="color: #318CDD; text-shadow: 0px 0px 0px #318CDD; font-size: 12px; font-weight: bold;">
                <a href="/admin/user/selectMeasurerUserList">측정자 등록 / 조회</a>
            </h6>
            <h6 class="collapse-header" style="color: #318CDD; text-shadow: 0px 0px 0px #318CDD; font-size: 12px; font-weight: bold;">
                <a href="/admin/user/selectUserList?userType=ST">피측정자 등록 / 조회</a>
            </h6>
            <h6 class="collapse-header" style="color: #318CDD; text-shadow: 0px 0px 0px #318CDD; font-size: 12px; font-weight: bold;">
                <a href="/admin/user/registerUserListBatch">피측정자 일괄등록</a>
            </h6>            
            </div>
        </div>
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseResultSheets"
            aria-expanded="true" aria-controls="collapseDiagnosis">
            <i class="fas fa-fw fa-tooth"></i>
            <span>조회</span>
        </a>
        <div id="collapseResultSheets" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header" style="color: #318CDD; text-shadow: 0px 0px 0px #318CDD; font-size: 12px; font-weight: bold;">
                    <a href="/admin/statistics/selectUserMeasureList?userType=ST">결과지 조회</a>
                </h6>
            </div>
        </div>
    </li>
    </c:if>


    <c:if test="${userInfo.userType eq 'SU'}">
    <!-- Divider -->
    <hr class="sidebar-divider">
    <!-- Heading -->
    <div class="sidebar-heading">
        <h6>치과병원 서비스</h6>
    </div>
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseDiagnosis2"
            aria-expanded="true" aria-controls="collapseDiagnosis">
            <i class="fas fa-fw fa-sitemap"></i>
            <span>등록</span>
        </a>
        <div id="collapseDiagnosis2" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header" style="color: #318CDD; text-shadow: 0px 0px 0px #318CDD; font-size: 12px; font-weight: bold;">
                    <a href="/admin/organ/selectDentalHospitalList">기관 등록 / 조회</a>
                </h6>
            </div>
        </div>
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseResultSheets2"
            aria-expanded="true" aria-controls="collapseDiagnosis">
            <i class="fas fa-fw fa-tooth"></i>
            <span>조회</span>
        </a>
        <div id="collapseResultSheets2" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header" style="color: #318CDD; text-shadow: 0px 0px 0px #318CDD; font-size: 12px; font-weight: bold;">
                    <a href="/admin/statistics/selectUserMeasureList?userType=PA">결과지 조회</a>
                </h6>
                <h6 class="collapse-header" style="color: #318CDD; text-shadow: 0px 0px 0px #318CDD; font-size: 12px; font-weight: bold;">
                    <a href="#">결과 조회</a>
                </h6>                
            </div>  
        </div>      
    </li>
    </c:if>

    <c:if test="${userInfo.userType eq 'DL' || userInfo.userType eq 'SU'}">
    <!-- Divider -->
    <hr class="sidebar-divider">
    <!-- Heading -->
    <div class="sidebar-heading">
        <h6>딜러</h6>
    </div>    
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseDealer"
            aria-expanded="true" aria-controls="collapseDiagnosis">
            <i class="fas fa-fw fa-user"></i>
            <span>기관</span>
        </a>
        <div id="collapseDealer" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header" style="color: #318CDD; text-shadow: 0px 0px 0px #318CDD; font-size: 12px; font-weight: bold;">
                    <a href="/admin/organ/selectDealerRequestList">담당 기관 추가 요청 / 조회</a>
                </h6>
            </div>
        </div> 
    </li>    
    </c:if>

    <c:if test="${userInfo.userType eq 'SU'}">
    <!-- Divider -->
    <hr class="sidebar-divider">
    <!-- Heading -->
    <div class="sidebar-heading">
        <h6>설정</h6>
    </div>        
        <!-- Nav Item - Utilities Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUser" aria-expanded="true" aria-controls="collapseUser">
                <i class="fas fa-fw fa-user"></i>
                <span>사용자</span>
            </a>
            <div id="collapseUser" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header" style="color: #318CDD; text-shadow: 0px 0px 0px #318CDD; font-size: 12px; font-weight: bold;">
                        <a href="/admin/user/selectDealerList">딜러 리스트</a>
                    </h6>
            </div>
        </li>        
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities">
                <i class="fas fa-fw fa-cog"></i>
                <span>애플리케이션</span>
            </a>
            <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header" style="color: #318CDD; text-shadow: 0px 0px 0px #318CDD; font-size: 12px; font-weight: bold;">
                        <a href="/admin/setting/selectAppVerInfo">버전 관리</a>
                    </h6>                   
                </div>
            </div>
        </li>
    
    
        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">
    
        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>
    
    <!-- Sidebar Message -->
    <!--     <div class="sidebar-card d-none d-lg-flex"> -->
    <!--         <img class="sidebar-card-illustration mb-2" src="imgs/undraw_rocket.svg" alt="..."> -->
    <!--         <p class="text-center mb-2"><strong>SB Admin Pro</strong> is packed with premium features, components, and more!</p> -->
    <!--         <a class="btn btn-success btn-sm" href="https://startbootstrap.com/theme/sb-admin-pro">Upgrade to Pro!</a> -->
    <!--     </div> -->
    </c:if>    

    <!-- Divider -->
    <hr class="sidebar-divider">
    
    
</ul>
<!-- End of Sidebar -->