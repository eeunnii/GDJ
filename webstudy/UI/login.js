// consol창에 콜렉션이라고 나오면 배열이라고 봐두댐

// 폼이 서브밋,, 버튼이 서브밋

document.getElementById('btn_signin').onclick = function(event){
    var pw = document.getElementById('pw');
    if(pw.value ==''){
        alert('비밀번호를 입력하세요');
        event.preventDefault();  // 버튼의 기본동작은 submit. 그 기본 동작을 막는 다는 뜻--서브밋 막을 때 많이 씀
        return;  // 이게 없다면 return이후의 코드가 잇으면 그거까지 실행해벌임
    }
}

// onkeyup 한글자한글자마다 쓸 때 마다 
document.getElementById('id').onkeyup = function(){
    var id =document.getElementById('id');
    var id_msg =document.getElementById('id_msg')
    if(id.value.length==0){
        id_msg.textContent='';
    }else if(id.value.length<4){
        id_msg.textContent = '아이디는 4자 이상입니다.';
    }else if(id.value.length>=4){
        id_msg.textContent = '정상적인 아이디입니다.';
    }
}

// 아이디 4글자 이하면 submit 막는거 ㅁ구현해보기~~~~~~~(4점)