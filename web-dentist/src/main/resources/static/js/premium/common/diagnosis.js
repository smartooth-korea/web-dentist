
function changeToothColorByLevel(dataList, index, paramCaution, paramDanger) {
	
	// 값 비교를 위한 형변환
	var caution = parseInt(paramCaution);
	var danger = parseInt(paramDanger);
	
	console.log(JSON.stringify(dataList));
	
	var tmpDataList = dataList;
	
	// 1000이상이 넘을 경우 측정값이 보정된 것이므로 1000을 빼서 본래의 값으로 전달
	// 영구치 상악
	if(parseInt(dataList[index].t11) > 100){
		dataList[index].t11 = parseInt(dataList[index].t11)-1000;
	}
	if(parseInt(dataList[index].t12) > 100){
		dataList[index].t12 = parseInt(dataList[index].t12)-1000;
	}
	if(parseInt(dataList[index].t13) > 100){
		dataList[index].t13 = parseInt(dataList[index].t13)-1000;
	}
	if(parseInt(dataList[index].t14) > 100){
		dataList[index].t14 = parseInt(dataList[index].t14)-1000;
	}
	if(parseInt(dataList[index].t15) > 100){
		dataList[index].t15 = parseInt(dataList[index].t15)-1000;
	}
	if(parseInt(dataList[index].t16) > 100){
		dataList[index].t16 = parseInt(dataList[index].t16)-1000;
	}
	if(parseInt(dataList[index].t17) > 100){
		dataList[index].t17 = parseInt(dataList[index].t17)-1000;
	}
	if(parseInt(dataList[index].t18) > 100){
		dataList[index].t18 = parseInt(dataList[index].t18)-1000;
	}
	
	if(parseInt(dataList[index].t21) > 100){
		dataList[index].t21 = parseInt(dataList[index].t21)-1000;
	}
	if(parseInt(dataList[index].t22) > 100){
		dataList[index].t22 = parseInt(dataList[index].t22)-1000;
	}
	if(parseInt(dataList[index].t23) > 100){
		dataList[index].t23 = parseInt(dataList[index].t23)-1000;
	}
	if(parseInt(dataList[index].t24) > 100){
		dataList[index].t24 = parseInt(dataList[index].t24)-1000;
	}
	if(parseInt(dataList[index].t25) > 100){
		dataList[index].t25 = parseInt(dataList[index].t25)-1000;
	}
	if(parseInt(dataList[index].t26) > 100){
		dataList[index].t26 = parseInt(dataList[index].t26)-1000;
	}
	if(parseInt(dataList[index].t27) > 100){
		dataList[index].t27 = parseInt(dataList[index].t27)-1000;
	}
	if(parseInt(dataList[index].t28) > 100){
		dataList[index].t28 = parseInt(dataList[index].t28)-1000;
	}

	// 영구치 하악
	if(parseInt(dataList[index].t31) > 100){
		dataList[index].t31 = parseInt(dataList[index].t31)-1000;
	}
	if(parseInt(dataList[index].t32) > 100){
		dataList[index].t32 = parseInt(dataList[index].t32)-1000;
	}
	if(parseInt(dataList[index].t33) > 100){
		dataList[index].t33 = parseInt(dataList[index].t33)-1000;
	}
	if(parseInt(dataList[index].t34) > 100){
		dataList[index].t34 = parseInt(dataList[index].t34)-1000;
	}
	if(parseInt(dataList[index].t35) > 100){
		dataList[index].t35 = parseInt(dataList[index].t35)-1000;
	}
	if(parseInt(dataList[index].t36) > 100){
		dataList[index].t36 = parseInt(dataList[index].t36)-1000;
	}
	if(parseInt(dataList[index].t37) > 100){
		dataList[index].t37 = parseInt(dataList[index].t37)-1000;
	}
	if(parseInt(dataList[index].t38) > 100){
		dataList[index].t38 = parseInt(dataList[index].t38)-1000;
	}
	
	if(parseInt(dataList[index].t41) > 100){
		dataList[index].t41 = parseInt(dataList[index].t41)-1000;
	}
	if(parseInt(dataList[index].t42) > 100){
		dataList[index].t42 = parseInt(dataList[index].t42)-1000;
	}
	if(parseInt(dataList[index].t43) > 100){
		dataList[index].t43 = parseInt(dataList[index].t43)-1000;
	}
	if(parseInt(dataList[index].t44) > 100){
		dataList[index].t44 = parseInt(dataList[index].t44)-1000;
	}
	if(parseInt(dataList[index].t45) > 100){
		dataList[index].t45 = parseInt(dataList[index].t45)-1000;
	}
	if(parseInt(dataList[index].t46) > 100){
		dataList[index].t46 = parseInt(dataList[index].t46)-1000;
	}
	if(parseInt(dataList[index].t47) > 100){
		dataList[index].t47 = parseInt(dataList[index].t47)-1000;
	}
	if(parseInt(dataList[index].t48) > 100){
		dataList[index].t48 = parseInt(dataList[index].t48)-1000;
	}
	
	
	// 유치
	if(parseInt(dataList[index].t51) > 100){
		dataList[index].t51 = parseInt(dataList[index].t51)-1000;
	}
	if(parseInt(dataList[index].t52) > 100){
		dataList[index].t52 = parseInt(dataList[index].t52)-1000;
	}
	if(parseInt(dataList[index].t53) > 100){
		dataList[index].t53 = parseInt(dataList[index].t53)-1000;
	}
	if(parseInt(dataList[index].t54) > 100){
		dataList[index].t54 = parseInt(dataList[index].t54)-1000;
	}
	if(parseInt(dataList[index].t55) > 100){
		dataList[index].t55 = parseInt(dataList[index].t55)-1000;
	}
	
	
	if(parseInt(dataList[index].t61) > 100){
		dataList[index].t61 = parseInt(dataList[index].t61)-1000;
	}
	if(parseInt(dataList[index].t62) > 100){
		dataList[index].t62 = parseInt(dataList[index].t62)-1000;
	}
	if(parseInt(dataList[index].t63) > 100){
		dataList[index].t63 = parseInt(dataList[index].t63)-1000;
	}
	if(parseInt(dataList[index].t64) > 100){
		dataList[index].t64 = parseInt(dataList[index].t64)-1000;
	}
	if(parseInt(dataList[index].t65) > 100){
		dataList[index].t65 = parseInt(dataList[index].t65)-1000;
	}
	
	
	if(parseInt(dataList[index].t71) > 100){
		dataList[index].t71 = parseInt(dataList[index].t71)-1000;
	}
	if(parseInt(dataList[index].t72) > 100){
		dataList[index].t72 = parseInt(dataList[index].t72)-1000;
	}
	if(parseInt(dataList[index].t73) > 100){
		dataList[index].t73 = parseInt(dataList[index].t73)-1000;
	}
	if(parseInt(dataList[index].t74) > 100){
		dataList[index].t74 = parseInt(dataList[index].t74)-1000;
	}
	if(parseInt(dataList[index].t75) > 100){
		dataList[index].t75 = parseInt(dataList[index].t75)-1000;
	}
	
	
	if(parseInt(dataList[index].t81) > 100){
		dataList[index].t81 = parseInt(dataList[index].t81)-1000;
	}
	if(parseInt(dataList[index].t82) > 100){
		dataList[index].t82 = parseInt(dataList[index].t82)-1000;
	}
	if(parseInt(dataList[index].t83) > 100){
		dataList[index].t83 = parseInt(dataList[index].t83)-1000;
	}
	if(parseInt(dataList[index].t84) > 100){
		dataList[index].t84 = parseInt(dataList[index].t84)-1000;
	}
	if(parseInt(dataList[index].t85) > 100){
		dataList[index].t85 = parseInt(dataList[index].t85)-1000;
	}
	
	
	// 주의, 충지에 대한 치아 색 변경하는 부분
	// 영구치 상악
	if (dataList[index].t11< caution) { 														
		$("#t11").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "25px 28px"
			}
		);
	} else if (dataList[index].t11 >= caution && dataList[index].t11 < danger) {
		$("#t11").css(
			{
				"background":"url(/imgs/tooth/t11_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "25px 28px"
			}
		);
		
	} else if (dataList[index].t11 >= danger && dataList[index].t11 < 100) {
		$("#t11").css(
			{
				"background":"url(/imgs/tooth/t11_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "25px 28px"
			}
		);		
	}
	
	if (dataList[index].t12< caution) { 														
		$("#t12").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "22px 22px"
			}
		);
	} else if (dataList[index].t12 >= caution && dataList[index].t12  < danger) {
		$("#t12").css(
			{
				"background":"url(/imgs/tooth/t12_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "22px 22px"
			}
		);
		
	} else if (dataList[index].t12 >= danger && dataList[index].t12 < 100) {
		$("#t12").css(
			{
				"background":"url(/imgs/tooth/t12_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "22px 22px"
			}
		);		
	}
	
	if (dataList[index].t13< caution) { 														
		$("#t13").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26px 22px"
			}
		);
	} else if (dataList[index].t13 >= caution && dataList[index].t13  < danger) {
		$("#t13").css(
			{
				"background":"url(/imgs/tooth/t13_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26px 22px"
			}
		);
		
	} else if (dataList[index].t13 >= danger && dataList[index].t13 < 100) {
		$("#t13").css(
			{
				"background":"url(/imgs/tooth/t13_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26px 22px"
			}
		);		
	}
	
	if (dataList[index].t14< caution) { 														
		$("#t14").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26px 22.5px"
			}
		);
	} else if (dataList[index].t14 >= caution && dataList[index].t14  < danger) {
		$("#t14").css(
			{
				"background":"url(/imgs/tooth/t14_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26px 22.5px"
			}
		);
		
	} else if (dataList[index].t14 >= danger && dataList[index].t14 < 100) {
		$("#t14").css(
			{
				"background":"url(/imgs/tooth/t14_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26px 22.5px"
			}
		);		
	}
	
	if (dataList[index].t15< caution) { 														
		$("#t15").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26px 21.5px"
			}
		);
	} else if (dataList[index].t15 >= caution && dataList[index].t15  < danger) {
		$("#t15").css(
			{
				"background":"url(/imgs/tooth/t15_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26px 21.5px"
			}
		);
		
	} else if (dataList[index].t15 >= danger && dataList[index].t15 < 100) {
		$("#t15").css(
			{
				"background":"url(/imgs/tooth/t15_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26px 21.5px"
			}
		);		
	}
	
	if (dataList[index].t16< caution) { 														
		$("#t16").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "29.5px 26px"
			}
		);
	} else if (dataList[index].t16 >= caution && dataList[index].t16  < danger) {
		$("#t16").css(
			{
				"background":"url(/imgs/tooth/t16_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "29.5px 26px"
			}
		);
		
	} else if (dataList[index].t16 >= danger && dataList[index].t16 < 100) {
		$("#t16").css(
			{
				"background":"url(/imgs/tooth/t16_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "29.5px 26px"
			}
		);		
	}
	
	if (dataList[index].t17< caution) { 														
		$("#t17").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "25px 28px"
			}
		);
	} else if (dataList[index].t17 >= caution && dataList[index].t17  < danger) {
		$("#t17").css(
			{
				"background":"url(/imgs/tooth/t17_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "29px 25px"
			}
		);
		
	} else if (dataList[index].t17 >= danger && dataList[index].t17 < 100) {
		$("#t17").css(
			{
				"background":"url(/imgs/tooth/t17_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "29px 25px"
			}
		);		
	}
	
	if (dataList[index].t18< caution) { 														
		$("#t18").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "25px 28px"
			}
		);
	} else if (dataList[index].t18 >= caution && dataList[index].t18  < danger) {
		$("#t18").css(
			{
				"background":"url(/imgs/tooth/t18_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "30px 24.5px"
			}
		);
		
	} else if (dataList[index].t18 >= danger && dataList[index].t18 < 100) {
		$("#t18").css(
			{
				"background":"url(/imgs/tooth/t18_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "30px 24.5px"
			}
		);		
	}
	
	
	if (dataList[index].t21< caution) { 														
		$("#t21").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "25px 28px"
			}
		);
	} else if (dataList[index].t21 >= caution && dataList[index].t21  < danger) {
		$("#t21").css(
			{
				"background":"url(/imgs/tooth/t21_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "25px 28px"
			}
		);
		
	} else if (dataList[index].t21 >= danger && dataList[index].t21 < 100) {
		$("#t21").css(
			{
				"background":"url(/imgs/tooth/t21_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "25px 28px"
			}
		);		
	}
	
	if (dataList[index].t22< caution) { 														
		$("#t22").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "22px 22.5px"
			}
		);
	} else if (dataList[index].t22 >= caution && dataList[index].t22  < danger) {
		$("#t22").css(
			{
				"background":"url(/imgs/tooth/t22_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "22px 22.5px"
			}
		);
		
	} else if (dataList[index].t22 >= danger && dataList[index].t22 < 100) {
		$("#t22").css(
			{
				"background":"url(/imgs/tooth/t22_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "22px 22.5px"
			}
		);		
	}
	
	if (dataList[index].t23< caution) { 														
		$("#t23").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "25.5px 21.5px"
			}
		);
	} else if (dataList[index].t23 >= caution && dataList[index].t23  < danger) {
		$("#t23").css(
			{
				"background":"url(/imgs/tooth/t23_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "25.5px 21.5px"
			}
		);
		
	} else if (dataList[index].t23 >= danger && dataList[index].t23 < 100) {
		$("#t23").css(
			{
				"background":"url(/imgs/tooth/t23_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "25.5px 21.5px"
			}
		);		
	}
	
	if (dataList[index].t24< caution) { 														
		$("#t24").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26px 23.5px"
			}
		);
	} else if (dataList[index].t24 >= caution && dataList[index].t24  < danger) {
		$("#t24").css(
			{
				"background":"url(/imgs/tooth/t24_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26px 23.5px"
			}
		);
		
	} else if (dataList[index].t24 >= danger && dataList[index].t24 < 100) {
		$("#t24").css(
			{
				"background":"url(/imgs/tooth/t24_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26px 23.5px"
			}
		);		
	}
	
	if (dataList[index].t25< caution) { 														
		$("#t25").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26.5px 21px"
			}
		);
	} else if (dataList[index].t25 >= caution && dataList[index].t25  < danger) {
		$("#t25").css(
			{
				"background":"url(/imgs/tooth/t25_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26.5px 21px"
			}
		);
		
	} else if (dataList[index].t25 >= danger && dataList[index].t25 < 100) {
		$("#t25").css(
			{
				"background":"url(/imgs/tooth/t25_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26.5px 21px"
			}
		);		
	}
	
	if (dataList[index].t26< caution) { 														
		$("#t26").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "30px 27px"
			}
		);
	} else if (dataList[index].t26 >= caution && dataList[index].t26  < danger) {
		$("#t26").css(
			{
				"background":"url(/imgs/tooth/t26_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "30px 27px"
			}
		);
		
	} else if (dataList[index].t26 >= danger && dataList[index].t26 < 100) {
		$("#t26").css(
			{
				"background":"url(/imgs/tooth/t26_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "30px 27px"
			}
		);		
	}
	
	if (dataList[index].t27< caution) { 														
		$("#t27").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "28px 24px"
			}
		);
	} else if (dataList[index].t27 >= caution && dataList[index].t27  < danger) {
		$("#t27").css(
			{
				"background":"url(/imgs/tooth/t27_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "28px 24px"
			}
		);
		
	} else if (dataList[index].t27 >= danger && dataList[index].t27 < 100) {
		$("#t27").css(
			{
				"background":"url(/imgs/tooth/t27_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "28px 24px"
			}
		);		
	}
	
	if (dataList[index].t28< caution) { 														
		$("#t28").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "30px 24px"
			}
		);
	} else if (dataList[index].t28 >= caution && dataList[index].t28  < danger) {
		$("#t28").css(
			{
				"background":"url(/imgs/tooth/t28_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "30px 24px"
			}
		);
		
	} else if (dataList[index].t28 >= danger && dataList[index].t28 < 100) {
		$("#t28").css(
			{
				"background":"url(/imgs/tooth/t28_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "30px 24px"
			}
		);		
	}


	if (dataList[index].t31< caution) { 														
		$("#t31").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "14px 19.5px"
			}
		);
	} else if (dataList[index].t31 >= caution && dataList[index].t31  < danger) {
		$("#t31").css(
			{
				"background":"url(/imgs/tooth/t31_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "14px 19.5px"
			}
		);
		
	} else if (dataList[index].t31 >= danger && dataList[index].t31 < 100) {
		$("#t31").css(
			{
				"background":"url(/imgs/tooth/t31_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "14px 19.5px"
			}
		);		
	}

	if (dataList[index].t32< caution) { 														
		$("#t32").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "16px 21px"
			}
		);
	} else if (dataList[index].t32 >= caution && dataList[index].t32  < danger) {
		$("#t32").css(
			{
				"background":"url(/imgs/tooth/t32_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "16px 21px"
			}
		);
		
	} else if (dataList[index].t32 >= danger && dataList[index].t32 < 100) {
		$("#t32").css(
			{
				"background":"url(/imgs/tooth/t32_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "16px 21px"
			}
		);		
	}


	if (dataList[index].t33< caution) { 														
		$("#t33").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "20.5px 23px"
			}
		);
	} else if (dataList[index].t33 >= caution && dataList[index].t33  < danger) {
		$("#t33").css(
			{
				"background":"url(/imgs/tooth/t33_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "20.5px 23px"
			}
		);
		
	} else if (dataList[index].t33 >= danger && dataList[index].t33 < 100) {
		$("#t33").css(
			{
				"background":"url(/imgs/tooth/t33_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "20.5px 23px"
			}
		);		
	}


	if (dataList[index].t34< caution) { 														
		$("#t34").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "21.5px 20px"
			}
		);
	} else if (dataList[index].t34 >= caution && dataList[index].t34  < danger) {
		$("#t34").css(
			{
				"background":"url(/imgs/tooth/t34_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "21.5px 20px"
			}
		);
		
	} else if (dataList[index].t34 >= danger && dataList[index].t34 < 100) {
		$("#t34").css(
			{
				"background":"url(/imgs/tooth/t34_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "21.5px 20px"
			}
		);		
	}


	if (dataList[index].t35< caution) { 														
		$("#t35").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "24.5px 22.5px"
			}
		);
	} else if (dataList[index].t35 >= caution && dataList[index].t35  < danger) {
		$("#t35").css(
			{
				"background":"url(/imgs/tooth/t35_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "24.5px 22.5px"
			}
		);
		
	} else if (dataList[index].t35 >= danger && dataList[index].t35 < 100) {
		$("#t35").css(
			{
				"background":"url(/imgs/tooth/t35_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "24.5px 22.5px"
			}
		);		
	}


	if (dataList[index].t36< caution) { 														
		$("#t36").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "28.5px 27px"
			}
		);
	} else if (dataList[index].t36 >= caution && dataList[index].t36  < danger) {
		$("#t36").css(
			{
				"background":"url(/imgs/tooth/t36_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "28.5px 27px"
			}
		);
		
	} else if (dataList[index].t36 >= danger && dataList[index].t36 < 100) {
		$("#t36").css(
			{
				"background":"url(/imgs/tooth/t36_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "28.5px 27px"
			}
		);		
	}


	if (dataList[index].t37< caution) { 														
		$("#t37").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "28.5px 28px"
			}
		);
	} else if (dataList[index].t37 >= caution && dataList[index].t37  < danger) {
		$("#t37").css(
			{
				"background":"url(/imgs/tooth/t37_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "28.5px 28px"
			}
		);
		
	} else if (dataList[index].t37 >= danger && dataList[index].t37 < 100) {
		$("#t37").css(
			{
				"background":"url(/imgs/tooth/t37_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "28.5px 28px"
			}
		);		
	}


	if (dataList[index].t38< caution) { 														
		$("#t38").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "29px 24.5px"
			}
		);
	} else if (dataList[index].t38 >= caution && dataList[index].t38  < danger) {
		$("#t38").css(
			{
				"background":"url(/imgs/tooth/t38_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "29px 24.5px"
			}
		);
		
	} else if (dataList[index].t38 >= danger && dataList[index].t38 < 100) {
		$("#t38").css(
			{
				"background":"url(/imgs/tooth/t38_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "29px 24.5px"
			}
		);		
	}

	
	if (dataList[index].t41< caution) { 														
		$("#t41").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "13.5px 20px"
			}
		);
	} else if (dataList[index].t41 >= caution && dataList[index].t41  < danger) {
		$("#t41").css(
			{
				"background":"url(/imgs/tooth/t41_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "13.5px 20px"
			}
		);
		
	} else if (dataList[index].t41 >= danger && dataList[index].t41 < 100) {
		$("#t41").css(
			{
				"background":"url(/imgs/tooth/t41_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "13.5px 20px"
			}
		);		
	}
	
	if (dataList[index].t42< caution) { 														
		$("#t42").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "17.5px 21px"
			}
		);
	} else if (dataList[index].t42 >= caution && dataList[index].t42  < danger) {
		$("#t42").css(
			{
				"background":"url(/imgs/tooth/t42_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "17.5px 21px"
			}
		);
		
	} else if (dataList[index].t42 >= danger && dataList[index].t42 < 100) {
		$("#t42").css(
			{
				"background":"url(/imgs/tooth/t42_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "17.5px 21px"
			}
		);		
	}
	
	if (dataList[index].t43< caution) { 														
		$("#t43").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "20px 22px"
			}
		);
	} else if (dataList[index].t43 >= caution && dataList[index].t43  < danger) {
		$("#t43").css(
			{
				"background":"url(/imgs/tooth/t43_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "20px 22px"
			}
		);
		
	} else if (dataList[index].t43 >= danger && dataList[index].t43 < 100) {
		$("#t43").css(
			{
				"background":"url(/imgs/tooth/t43_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "20px 22px"
			}
		);		
	}
	
	if (dataList[index].t44< caution) { 														
		$("#t44").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "21px 20px"
			}
		);
	} else if (dataList[index].t44 >= caution && dataList[index].t44  < danger) {
		$("#t44").css(
			{
				"background":"url(/imgs/tooth/t44_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "21px 20px"
			}
		);
		
	} else if (dataList[index].t44 >= danger && dataList[index].t44 < 100) {
		$("#t44").css(
			{
				"background":"url(/imgs/tooth/t44_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "21px 20px"
			}
		);		
	}
	
	if (dataList[index].t45< caution) { 														
		$("#t45").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "25px 22px"
			}
		);
	} else if (dataList[index].t45 >= caution && dataList[index].t45  < danger) {
		$("#t45").css(
			{
				"background":"url(/imgs/tooth/t45_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "25px 22px"
			}
		);
		
	} else if (dataList[index].t45 >= danger && dataList[index].t45 < 100) {
		$("#t45").css(
			{
				"background":"url(/imgs/tooth/t45_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "25px 22px"
			}
		);		
	}
	
	if (dataList[index].t46< caution) { 														
		$("#t46").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "28px 28px"
			}
		);
	} else if (dataList[index].t46 >= caution && dataList[index].t46  < danger) {
		$("#t46").css(
			{
				"background":"url(/imgs/tooth/t46_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "28px 28px"
			}
		);
		
	} else if (dataList[index].t46 >= danger && dataList[index].t46 < 100) {
		$("#t46").css(
			{
				"background":"url(/imgs/tooth/t46_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "28px 28px"
			}
		);		
	}
	
	if (dataList[index].t47< caution) { 														
		$("#t47").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "28px 28px"
			}
		);
	} else if (dataList[index].t47 >= caution && dataList[index].t47  < danger) {
		$("#t47").css(
			{
				"background":"url(/imgs/tooth/t47_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "28px 28px"
			}
		);
		
	} else if (dataList[index].t47 >= danger && dataList[index].t47 < 100) {
		$("#t47").css(
			{
				"background":"url(/imgs/tooth/t47_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "28px 28px"
			}
		);		
	}
	
	if (dataList[index].t48< caution) { 														
		$("#t48").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "28.5px 24.5px"
			}
		);
	} else if (dataList[index].t48 >= caution && dataList[index].t48  < danger) {
		$("#t48").css(
			{
				"background":"url(/imgs/tooth/t48_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "28.5px 24.5px"
			}
		);
		
	} else if (dataList[index].t48 >= danger && dataList[index].t48 < 100) {
		$("#t48").css(
			{
				"background":"url(/imgs/tooth/t48_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "28.5px 24.5px"
			}
		);		
	}
	
	
	if (dataList[index].t51< caution) { 														
		$("#t51").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "24px 18px"
			}
		);
	} else if (dataList[index].t51 >= caution && dataList[index].t51  < danger) {
		$("#t51").css(
			{
				"background":"url(/imgs/tooth/t51_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "24px 18px"
			}
		);
		
	} else if (dataList[index].t51 >= danger && dataList[index].t51 < 100) {
		$("#t51").css(
			{
				"background":"url(/imgs/tooth/t51_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "24px 18px"
			}
		);		
	}
	
	if (dataList[index].t52< caution) { 														
		$("#t52").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "20px 17px"
			}
		);
	} else if (dataList[index].t52 >= caution && dataList[index].t52  < danger) {
		$("#t52").css(
			{
				"background":"url(/imgs/tooth/t52_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "20px 17px"
			}
		);
		
	} else if (dataList[index].t52 >= danger && dataList[index].t52 < 100) {
		$("#t52").css(
			{
				"background":"url(/imgs/tooth/t52_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "20px 17px"
			}
		);		
	}
	
	if (dataList[index].t53< caution) { 														
		$("#t53").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "21px 20px"
			}
		);
	} else if (dataList[index].t53 >= caution && dataList[index].t53  < danger) {
		$("#t53").css(
			{
				"background":"url(/imgs/tooth/t53_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "21px 20px"
			}
		);
		
	} else if (dataList[index].t53 >= danger && dataList[index].t53 < 100) {
		$("#t53").css(
			{
				"background":"url(/imgs/tooth/t53_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "21px 20px"
			}
		);		
	}
	
	if (dataList[index].t54< caution) { 														
		$("#t54").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26.5px 28px"
			}
		);
	} else if (dataList[index].t54 >= caution && dataList[index].t54  < danger) {
		$("#t54").css(
			{
				"background":"url(/imgs/tooth/t54_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26.5px 28px"
			}
		);
		
	} else if (dataList[index].t54 >= danger && dataList[index].t54 < 100) {
		$("#t54").css(
			{
				"background":"url(/imgs/tooth/t54_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26.5px 28px"
			}
		);		
	}
	
	if (dataList[index].t55< caution) { 														
		$("#t55").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "28px 31px"
			}
		);
	} else if (dataList[index].t55 >= caution && dataList[index].t55  < danger) {
		$("#t55").css(
			{
				"background":"url(/imgs/tooth/t55_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "28px 31px"
			}
		);
		
	} else if (dataList[index].t55 >= danger && dataList[index].t55 < 100) {
		$("#t55").css(
			{
				"background":"url(/imgs/tooth/t55_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "28px 31px"
			}
		);		
	}
	
	
	if (dataList[index].t61< caution) { 														
		$("#t61").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "24px 18px"
			}
		);
	} else if (dataList[index].t61 >= caution && dataList[index].t61  < danger) {
		$("#t61").css(
			{
				"background":"url(/imgs/tooth/t61_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "24px 18px"
			}
		);
		
	} else if (dataList[index].t61 >= danger && dataList[index].t61 < 100) {
		$("#t61").css(
			{
				"background":"url(/imgs/tooth/t61_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "24px 18px"
			}
		);		
	}
	
	if (dataList[index].t62< caution) { 														
		$("#t62").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "19px 18px"
			}
		);
	} else if (dataList[index].t62 >= caution && dataList[index].t62  < danger) {
		$("#t62").css(
			{
				"background":"url(/imgs/tooth/t62_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "19px 18px"
			}
		);
		
	} else if (dataList[index].t62 >= danger && dataList[index].t62 < 100) {
		$("#t62").css(
			{
				"background":"url(/imgs/tooth/t62_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "19px 18px"
			}
		);		
	}
	
	if (dataList[index].t63< caution) { 														
		$("#t63").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "20px 19.5px"
			}
		);
	} else if (dataList[index].t63 >= caution && dataList[index].t63  < danger) {
		$("#t63").css(
			{
				"background":"url(/imgs/tooth/t63_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "20px 19.5px"
			}
		);
		
	} else if (dataList[index].t63 >= danger && dataList[index].t63 < 100) {
		$("#t63").css(
			{
				"background":"url(/imgs/tooth/t63_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "20px 19.5px"
			}
		);		
	}
	
	if (dataList[index].t64< caution) { 														
		$("#t64").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26.5px 27px"
			}
		);
	} else if (dataList[index].t64 >= caution && dataList[index].t64  < danger) {
		$("#t64").css(
			{
				"background":"url(/imgs/tooth/t64_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26.5px 27px"
			}
		);
		
	} else if (dataList[index].t64 >= danger && dataList[index].t64 < 100) {
		$("#t64").css(
			{
				"background":"url(/imgs/tooth/t64_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26.5px 27px"
			}
		);		
	}
	
	if (dataList[index].t65< caution) { 														
		$("#t65").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "28px 31px"
			}
		);
	} else if (dataList[index].t65 >= caution && dataList[index].t65  < danger) {
		$("#t65").css(
			{
				"background":"url(/imgs/tooth/t65_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "28px 31px"
			}
		);
		
	} else if (dataList[index].t65 >= danger && dataList[index].t65 < 100) {
		$("#t65").css(
			{
				"background":"url(/imgs/tooth/t65_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "28px 31px"
			}
		);		
	}
	
	
	
	if (dataList[index].t71< caution) { 														
		$("#t71").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "16px 13px"
			}
		);
	} else if (dataList[index].t71 >= caution && dataList[index].t71  < danger) {
		$("#t71").css(
			{
				"background":"url(/imgs/tooth/t71_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "16px 13px"
			}
		);
		
	} else if (dataList[index].t71 >= danger && dataList[index].t71 < 100) {
		$("#t71").css(
			{
				"background":"url(/imgs/tooth/t71_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "16px 13px"
			}
		);		
	}
	
	if (dataList[index].t72< caution) { 														
		$("#t72").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "17px 16px"
			}
		);
	} else if (dataList[index].t72 >= caution && dataList[index].t72  < danger) {
		$("#t72").css(
			{
				"background":"url(/imgs/tooth/t72_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "17px 16px"
			}
		);
		
	} else if (dataList[index].t72 >= danger && dataList[index].t72 < 100) {
		$("#t72").css(
			{
				"background":"url(/imgs/tooth/t72_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "17px 16px"
			}
		);		
	}
	
	if (dataList[index].t73< caution) { 														
		$("#t73").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "20.5px 21px"
			}
		);
	} else if (dataList[index].t73 >= caution && dataList[index].t73  < danger) {
		$("#t73").css(
			{
				"background":"url(/imgs/tooth/t73_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "20.5px 21px"
			}
		);
		
	} else if (dataList[index].t73 >= danger && dataList[index].t73 < 100) {
		$("#t73").css(
			{
				"background":"url(/imgs/tooth/t73_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "20.5px 21px"
			}
		);		
	}
	
	if (dataList[index].t74< caution) { 														
		$("#t74").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26px 25px"
			}
		);
	} else if (dataList[index].t74 >= caution && dataList[index].t74  < danger) {
		$("#t74").css(
			{
				"background":"url(/imgs/tooth/t74_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26px 25px"
			}
		);
		
	} else if (dataList[index].t74 >= danger && dataList[index].t74 < 100) {
		$("#t74").css(
			{
				"background":"url(/imgs/tooth/t74_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26px 25px"
			}
		);		
	}
	
	if (dataList[index].t75< caution) { 														
		$("#t75").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "32px 30px"
			}
		);
	} else if (dataList[index].t75 >= caution && dataList[index].t75  < danger) {
		$("#t75").css(
			{
				"background":"url(/imgs/tooth/t75_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "32px 30px"
			}
		);
		
	} else if (dataList[index].t75 >= danger && dataList[index].t75 < 100) {
		$("#t75").css(
			{
				"background":"url(/imgs/tooth/t75_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "32px 30px"
			}
		);		
	}
	
	
	
	if (dataList[index].t81< caution) { 														
		$("#t81").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "17px 13px"
			}
		);
	} else if (dataList[index].t81 >= caution && dataList[index].t81  < danger) {
		$("#t81").css(
			{
				"background":"url(/imgs/tooth/t81_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "17px 13px"
			}
		);
		
	} else if (dataList[index].t81 >= danger && dataList[index].t81 < 100) {
		$("#t81").css(
			{
				"background":"url(/imgs/tooth/t81_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "17px 13px"
			}
		);		
	}
	
	if (dataList[index].t82< caution) { 														
		$("#t82").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "18px 15px"
			}
		);
	} else if (dataList[index].t82 >= caution && dataList[index].t82  < danger) {
		$("#t82").css(
			{
				"background":"url(/imgs/tooth/t82_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "18px 15px"
			}
		);
		
	} else if (dataList[index].t82 >= danger && dataList[index].t82 < 100) {
		$("#t82").css(
			{
				"background":"url(/imgs/tooth/t82_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "18px 15px"
			}
		);		
	}
	
	if (dataList[index].t83< caution) { 														
		$("#t83").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "20.5px 20px"
			}
		);
	} else if (dataList[index].t83 >= caution && dataList[index].t83  < danger) {
		$("#t83").css(
			{
				"background":"url(/imgs/tooth/t83_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "20.5px 20px"
			}
		);
		
	} else if (dataList[index].t83 >= danger && dataList[index].t83 < 100) {
		$("#t83").css(
			{
				"background":"url(/imgs/tooth/t83_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "20.5px 20px"
			}
		);		
	}
	
	if (dataList[index].t84< caution) { 														
		$("#t84").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26.5px 26px"
			}
		);
	} else if (dataList[index].t84 >= caution && dataList[index].t84  < danger) {
		$("#t84").css(
			{
				"background":"url(/imgs/tooth/t84_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26.5px 26px"
			}
		);
		
	} else if (dataList[index].t84 >= danger && dataList[index].t84 < 100) {
		$("#t84").css(
			{
				"background":"url(/imgs/tooth/t84_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "26.5px 26px"
			}
		);		
	}
	
	if (dataList[index].t85< caution) { 														
		$("#t85").css(
			{
				"background":"url(/imgs/tooth/empty.png)",
				"background-repeat" : "no-repeat",
				"background-size": "32px 31px"
			}
		);
	} else if (dataList[index].t85 >= caution && dataList[index].t85  < danger) {
		$("#t85").css(
			{
				"background":"url(/imgs/tooth/t85_y.png)",
				"background-repeat" : "no-repeat",
				"background-size": "32px 31px"
			}
		);
		
	} else if (dataList[index].t85 >= danger && dataList[index].t85 < 100) {
		$("#t85").css(
			{
				"background":"url(/imgs/tooth/t85_r.png)",
				"background-repeat" : "no-repeat",
				"background-size": "32px 31px"
			}
		);		
	}
	
}


function displayToothMeasureValue(dataList){
	
	console.log(JSON.stringify(dataList));
	
	if(dataList[0].t11 == -99) dataList[0].t11 = " ";
	if(dataList[0].t12 == -99) dataList[0].t12 = " ";
	if(dataList[0].t13 == -99) dataList[0].t13 = " ";
	if(dataList[0].t14 == -99) dataList[0].t14 = " ";
	if(dataList[0].t15 == -99) dataList[0].t15 = " ";
	if(dataList[0].t16 == -99) dataList[0].t16 = " ";
	if(dataList[0].t17 == -99) dataList[0].t17 = " ";
	if(dataList[0].t18 == -99) dataList[0].t18 = " ";
	if(dataList[0].t21 == -99) dataList[0].t21 = " ";
	if(dataList[0].t22 == -99) dataList[0].t22 = " ";
	if(dataList[0].t23 == -99) dataList[0].t23 = " ";
	if(dataList[0].t24 == -99) dataList[0].t24 = " ";
	if(dataList[0].t25 == -99) dataList[0].t25 = " ";
	if(dataList[0].t26 == -99) dataList[0].t26 = " ";
	if(dataList[0].t27 == -99) dataList[0].t27 = " ";
	if(dataList[0].t28 == -99) dataList[0].t28 = " ";
	if(dataList[0].t31 == -99) dataList[0].t31 = " ";
	if(dataList[0].t32 == -99) dataList[0].t32 = " ";
	if(dataList[0].t33 == -99) dataList[0].t33 = " ";
	if(dataList[0].t34 == -99) dataList[0].t34 = " ";
	if(dataList[0].t35 == -99) dataList[0].t35 = " ";
	if(dataList[0].t36 == -99) dataList[0].t36 = " ";
	if(dataList[0].t37 == -99) dataList[0].t37 = " ";
	if(dataList[0].t38 == -99) dataList[0].t38 = " ";
	if(dataList[0].t41 == -99) dataList[0].t41 = " ";
	if(dataList[0].t42 == -99) dataList[0].t42 = " ";
	if(dataList[0].t43 == -99) dataList[0].t43 = " ";
	if(dataList[0].t44 == -99) dataList[0].t44 = " ";
	if(dataList[0].t45 == -99) dataList[0].t45 = " ";
	if(dataList[0].t46 == -99) dataList[0].t46 = " ";
	if(dataList[0].t47 == -99) dataList[0].t47 = " ";
	if(dataList[0].t48 == -99) dataList[0].t48 = " ";
	if(dataList[0].t51 == -99) dataList[0].t51 = " ";
	if(dataList[0].t52 == -99) dataList[0].t52 = " ";
	if(dataList[0].t53 == -99) dataList[0].t53 = " ";
	if(dataList[0].t54 == -99) dataList[0].t54 = " ";
	if(dataList[0].t55 == -99) dataList[0].t55 = " ";
	if(dataList[0].t61 == -99) dataList[0].t61 = " ";
	if(dataList[0].t62 == -99) dataList[0].t62 = " ";
	if(dataList[0].t63 == -99) dataList[0].t63 = " ";
	if(dataList[0].t64 == -99) dataList[0].t64 = " ";
	if(dataList[0].t65 == -99) dataList[0].t65 = " ";
	if(dataList[0].t71 == -99) dataList[0].t71 = " ";
	if(dataList[0].t72 == -99) dataList[0].t72 = " ";
	if(dataList[0].t73 == -99) dataList[0].t73 = " ";
	if(dataList[0].t74 == -99) dataList[0].t74 = " ";
	if(dataList[0].t75 == -99) dataList[0].t75 = " ";
	if(dataList[0].t81 == -99) dataList[0].t81 = " ";
	if(dataList[0].t82 == -99) dataList[0].t82 = " ";
	if(dataList[0].t83 == -99) dataList[0].t83 = " ";
	if(dataList[0].t84 == -99) dataList[0].t84 = " ";
	if(dataList[0].t85 == -99) dataList[0].t85 = " ";
	
	
	if(parseInt(dataList[0].t11) > 100){
		dataList[0].t11 = parseInt(dataList[0].t11)-1000;
		dataList[0].t11 = "<u>"+dataList[0].t11+"</u>";
	}
	if(parseInt(dataList[0].t12) > 100){
		dataList[0].t12 = parseInt(dataList[0].t12)-1000;
		dataList[0].t12 = "<u>"+dataList[0].t12+"</u>";
	}
	if(parseInt(dataList[0].t13) > 100){
		dataList[0].t13 = parseInt(dataList[0].t13)-1000;
		dataList[0].t13 = "<u>"+dataList[0].t13+"</u>";
	}
	if(parseInt(dataList[0].t14) > 100){
		dataList[0].t14 = parseInt(dataList[0].t14)-1000;
		dataList[0].t14 = "<u>"+dataList[0].t14+"</u>";
	}
	if(parseInt(dataList[0].t15) > 100){
		dataList[0].t15 = parseInt(dataList[0].t15)-1000;
		dataList[0].t15 = "<u>"+dataList[0].t15+"</u>";
	}
	if(parseInt(dataList[0].t16) > 100){
		dataList[0].t16 = parseInt(dataList[0].t16)-1000;
		dataList[0].t16 = "<u>"+dataList[0].t16+"</u>";
	}
	if(parseInt(dataList[0].t17) > 100){
		dataList[0].t17 = parseInt(dataList[0].t17)-1000;
		dataList[0].t17 = "<u>"+dataList[0].t17+"</u>";
	}
	if(parseInt(dataList[0].t18) > 100){
		dataList[0].t18 = parseInt(dataList[0].t18)-1000;
		dataList[0].t18 = "<u>"+dataList[0].t18+"</u>";
	}
	if(parseInt(dataList[0].t21) > 100){
		dataList[0].t21 = parseInt(dataList[0].t21)-1000;
		dataList[0].t21 = "<u>"+dataList[0].t21+"</u>";
	}
	if(parseInt(dataList[0].t22) > 100){
		dataList[0].t22 = parseInt(dataList[0].t22)-1000;
		dataList[0].t22 = "<u>"+dataList[0].t22+"</u>";
	}
	if(parseInt(dataList[0].t23) > 100){
		dataList[0].t23 = parseInt(dataList[0].t23)-1000;
		dataList[0].t23 = "<u>"+dataList[0].t23+"</u>";
	}
	if(parseInt(dataList[0].t24) > 100){
		dataList[0].t24 = parseInt(dataList[0].t24)-1000;
		dataList[0].t24 = "<u>"+dataList[0].t24+"</u>";
	}
	if(parseInt(dataList[0].t25) > 100){
		dataList[0].t25 = parseInt(dataList[0].t25)-1000;
		dataList[0].t25 = "<u>"+dataList[0].t25+"</u>";
	}
	if(parseInt(dataList[0].t26) > 100){
		dataList[0].t26 = parseInt(dataList[0].t26)-1000;
		dataList[0].t26 = "<u>"+dataList[0].t26+"</u>";
	}
	if(parseInt(dataList[0].t27) > 100){
		dataList[0].t27 = parseInt(dataList[0].t27)-1000;
		dataList[0].t27 = "<u>"+dataList[0].t27+"</u>";
	}
	if(parseInt(dataList[0].t28) > 100){
		dataList[0].t28 = parseInt(dataList[0].t28)-1000;
		dataList[0].t28 = "<u>"+dataList[0].t28+"</u>";
	}
	if(parseInt(dataList[0].t31) > 100){
		dataList[0].t31 = parseInt(dataList[0].t31)-1000;
		dataList[0].t31 = "<u>"+dataList[0].t31+"</u>";
	}
	if(parseInt(dataList[0].t32) > 100){
		dataList[0].t32 = parseInt(dataList[0].t32)-1000;
		dataList[0].t32 = "<u>"+dataList[0].t32+"</u>";
	}
	if(parseInt(dataList[0].t33) > 100){
		dataList[0].t33 = parseInt(dataList[0].t33)-1000;
		dataList[0].t33 = "<u>"+dataList[0].t33+"</u>";
	}
	if(parseInt(dataList[0].t34) > 100){
		dataList[0].t34 = parseInt(dataList[0].t34)-1000;
		dataList[0].t34 = "<u>"+dataList[0].t34+"</u>";
	}
	if(parseInt(dataList[0].t35) > 100){
		dataList[0].t35 = parseInt(dataList[0].t35)-1000;
		dataList[0].t35 = "<u>"+dataList[0].t35+"</u>";
	}
	if(parseInt(dataList[0].t36) > 100){
		dataList[0].t36 = parseInt(dataList[0].t36)-1000;
		dataList[0].t36 = "<u>"+dataList[0].t36+"</u>";
	}
	if(parseInt(dataList[0].t37) > 100){
		dataList[0].t37 = parseInt(dataList[0].t37)-1000;
		dataList[0].t37 = "<u>"+dataList[0].t37+"</u>";
	}
	if(parseInt(dataList[0].t38) > 100){
		dataList[0].t38 = parseInt(dataList[0].t38)-1000;
		dataList[0].t38 = "<u>"+dataList[0].t38+"</u>";
	}
	if(parseInt(dataList[0].t41) > 100){
		dataList[0].t41 = parseInt(dataList[0].t41)-1000;
		dataList[0].t41 = "<u>"+dataList[0].t41+"</u>";
	}
	if(parseInt(dataList[0].t42) > 100){
		dataList[0].t42 = parseInt(dataList[0].t42)-1000;
		dataList[0].t42 = "<u>"+dataList[0].t42+"</u>";
	}
	if(parseInt(dataList[0].t43) > 100){
		dataList[0].t43 = parseInt(dataList[0].t43)-1000;
		dataList[0].t43 = "<u>"+dataList[0].t43+"</u>";
	}
	if(parseInt(dataList[0].t44) > 100){
		dataList[0].t44 = parseInt(dataList[0].t44)-1000;
		dataList[0].t44 = "<u>"+dataList[0].t44+"</u>";
	}
	if(parseInt(dataList[0].t45) > 100){
		dataList[0].t45 = parseInt(dataList[0].t45)-1000;
		dataList[0].t45 = "<u>"+dataList[0].t45+"</u>";
	}
	if(parseInt(dataList[0].t46) > 100){
		dataList[0].t46 = parseInt(dataList[0].t46)-1000;
		dataList[0].t46 = "<u>"+dataList[0].t46+"</u>";
	}
	if(parseInt(dataList[0].t47) > 100){
		dataList[0].t47 = parseInt(dataList[0].t47)-1000;
		dataList[0].t47 = "<u>"+dataList[0].t47+"</u>";
	}
	if(parseInt(dataList[0].t48) > 100){
		dataList[0].t48 = parseInt(dataList[0].t48)-1000;
		dataList[0].t48 = "<u>"+dataList[0].t48+"</u>";
	}
	
	if(parseInt(dataList[0].t51) > 100){
		dataList[0].t51 = parseInt(dataList[0].t51)-1000;
		dataList[0].t51 = "<u>"+dataList[0].t51+"</u>";
	}
	if(parseInt(dataList[0].t52) > 100){
		dataList[0].t52 = parseInt(dataList[0].t52)-1000;
		dataList[0].t52 = "<u>"+dataList[0].t52+"</u>";
	}
	if(parseInt(dataList[0].t53) > 100){
		dataList[0].t53 = parseInt(dataList[0].t53)-1000;
		dataList[0].t53 = "<u>"+dataList[0].t53+"</u>";
	}
	if(parseInt(dataList[0].t54) > 100){
		dataList[0].t54 = parseInt(dataList[0].t54)-1000;
		dataList[0].t54 = "<u>"+dataList[0].t54+"</u>";
	}
	if(parseInt(dataList[0].t55) > 100){
		dataList[0].t55 = parseInt(dataList[0].t55)-1000;
		dataList[0].t55 = "<u>"+dataList[0].t55+"</u>";
	}
	
	if(parseInt(dataList[0].t61) > 100){
		dataList[0].t61 = parseInt(dataList[0].t61)-1000;
		dataList[0].t61 = "<u>"+dataList[0].t61+"</u>";
	}
	if(parseInt(dataList[0].t62) > 100){
		dataList[0].t62 = parseInt(dataList[0].t62)-1000;
		dataList[0].t62 = "<u>"+dataList[0].t62+"</u>";
	}
	if(parseInt(dataList[0].t63) > 100){
		dataList[0].t63 = parseInt(dataList[0].t63)-1000;
		dataList[0].t63 = "<u>"+dataList[0].t63+"</u>";
	}
	if(parseInt(dataList[0].t64) > 100){
		dataList[0].t64 = parseInt(dataList[0].t64)-1000;
		dataList[0].t64 = "<u>"+dataList[0].t64+"</u>";
	}
	if(parseInt(dataList[0].t65) > 100){
		dataList[0].t65 = parseInt(dataList[0].t65)-1000;
		dataList[0].t65 = "<u>"+dataList[0].t65+"</u>";
	}
	
	if(parseInt(dataList[0].t71) > 100){
		dataList[0].t71 = parseInt(dataList[0].t71)-1000;
		dataList[0].t71 = "<u>"+dataList[0].t71+"</u>";
	}
	if(parseInt(dataList[0].t72) > 100){
		dataList[0].t72 = parseInt(dataList[0].t72)-1000;
		dataList[0].t72 = "<u>"+dataList[0].t72+"</u>";
	}
	if(parseInt(dataList[0].t73) > 100){
		dataList[0].t73 = parseInt(dataList[0].t73)-1000;
		dataList[0].t73 = "<u>"+dataList[0].t73+"</u>";
	}
	if(parseInt(dataList[0].t74) > 100){
		dataList[0].t74 = parseInt(dataList[0].t74)-1000;
		dataList[0].t74 = "<u>"+dataList[0].t74+"</u>";
	}
	if(parseInt(dataList[0].t75) > 100){
		dataList[0].t75 = parseInt(dataList[0].t75)-1000;
		dataList[0].t75 = "<u>"+dataList[0].t75+"</u>";
	}
	
	if(parseInt(dataList[0].t81) > 100){
		dataList[0].t81 = parseInt(dataList[0].t81)-1000;
		dataList[0].t81 = "<u>"+dataList[0].t81+"</u>";
	}
	if(parseInt(dataList[0].t82) > 100){
		dataList[0].t82 = parseInt(dataList[0].t82)-1000;
		dataList[0].t82 = "<u>"+dataList[0].t82+"</u>";
	}
	if(parseInt(dataList[0].t83) > 100){
		dataList[0].t83 = parseInt(dataList[0].t83)-1000;
		dataList[0].t83 = "<u>"+dataList[0].t83+"</u>";
	}
	if(parseInt(dataList[0].t84) > 100){
		dataList[0].t84 = parseInt(dataList[0].t84)-1000;
		dataList[0].t84 = "<u>"+dataList[0].t84+"</u>";
	}
	if(parseInt(dataList[0].t85) > 100){
		dataList[0].t85 = parseInt(dataList[0].t85)-1000;
		dataList[0].t85 = "<u>"+dataList[0].t85+"</u>";
	}
	
	$("#t11").html(dataList[0].t11);
	$("#t12").html(dataList[0].t12);
	$("#t13").html(dataList[0].t13);
	$("#t14").html(dataList[0].t14);
	$("#t15").html(dataList[0].t15);
	$("#t16").html(dataList[0].t16);
	$("#t17").html(dataList[0].t17);
	$("#t18").html(dataList[0].t18);
	$("#t21").html(dataList[0].t21);
	$("#t22").html(dataList[0].t22);
	$("#t23").html(dataList[0].t23);
	$("#t24").html(dataList[0].t24);
	$("#t25").html(dataList[0].t25);
	$("#t26").html(dataList[0].t26);
	$("#t27").html(dataList[0].t27);
	$("#t28").html(dataList[0].t28);
	$("#t31").html(dataList[0].t31);
	$("#t32").html(dataList[0].t32);
	$("#t33").html(dataList[0].t33);
	$("#t34").html(dataList[0].t34);
	$("#t35").html(dataList[0].t35);
	$("#t36").html(dataList[0].t36);
	$("#t37").html(dataList[0].t37);
	$("#t38").html(dataList[0].t38);
	$("#t41").html(dataList[0].t41);
	$("#t42").html(dataList[0].t42);
	$("#t43").html(dataList[0].t43);
	$("#t44").html(dataList[0].t44);
	$("#t45").html(dataList[0].t45);
	$("#t46").html(dataList[0].t46);
	$("#t47").html(dataList[0].t47);
	$("#t48").html(dataList[0].t48);
	$("#t51").html(dataList[0].t51);
	$("#t52").html(dataList[0].t52);
	$("#t53").html(dataList[0].t53);
	$("#t54").html(dataList[0].t54);
	$("#t55").html(dataList[0].t55);
	$("#t61").html(dataList[0].t61);
	$("#t62").html(dataList[0].t62);
	$("#t63").html(dataList[0].t63);
	$("#t64").html(dataList[0].t64);
	$("#t65").html(dataList[0].t65);
	$("#t71").html(dataList[0].t71);
	$("#t72").html(dataList[0].t72);
	$("#t73").html(dataList[0].t73);
	$("#t74").html(dataList[0].t74);
	$("#t75").html(dataList[0].t75);
	$("#t81").html(dataList[0].t81);
	$("#t82").html(dataList[0].t82);
	$("#t83").html(dataList[0].t83);
	$("#t84").html(dataList[0].t84);
	$("#t85").html(dataList[0].t85);
}

	
