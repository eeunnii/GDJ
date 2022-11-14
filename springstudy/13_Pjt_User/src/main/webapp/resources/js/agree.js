
	$(document).ready(function(){
		fn_checkALl();
		fn_checkOne();
		fn_toggleCheck();
	});
	
	

// 모두 동의(모두 동의의 체크 상태를 개별 선택들의 체크 상태)
	function fn_checkALl(){
		// 체크상태 변경
		$('#check_all').click(function(){
			$('.check_one').prop('checked', $('check_all').prop('checked'));
		// 체크 이미지 변경
		if($(this).is('checked')){  // 모두 동의가 체크되었다면 
			$('.lbl_one').addClass('lbl_checked');
		}else{
			$('.lbl_one').removeaddClass('lbl_checked');
		}
		});
	}
	
	// 개별 선택 (항상 개별 선택 4개를 모두 순회하면서 어떤 상태인지 체크해야함)
	function fn_checkOne() {
		$('.check_one').click(function(){
			// 체크 상태 변경
			let checkCount = 0;
			for(let i=0; i<$('.check_one').length; i++){   // this로 적으면 4개 항목중에서 클릭당한애 하나라는 뜻 '.check_one'로 수정함
				checkCount += $($('.check_one')[i]).prop('checked'); //인덱스는 일반변수라 $()제이쿼리로 감싸줌//true는 1이고 false 0이다
			}
			// 체크박수개수vs체크된박수개수  가 같으면 true
			$('#check_all').prop('checked', $('.check_one').lengh == checkCount);
			// 체크 이미지 변경
			if($('#check_all').is(':checked')){
				$('lbl_all').addClass('lbl_checked')
			}
		});
	}
	
	// 체크할 때마다 lbl_checked 클래스를 줬다 뺏었다 하기
		function fn_toggleCheck(){
			$('.lbl_all, .lbl_one').click(function(){
				$(this).toggleClass('lbl_checked');
			});
		}
	
	function fn_submit(){
		
		
		$('#frm_agree').submit(function(event){
			if($('#service').is(':checked')==false || $('#privacy').is(':checked')==false){
				alert('필수약관에 동의하세요');
				event.prevent();
				return;
			}
			
		});
		
	}