function q1() {
  // 1. input-q1의 입력값을 가져온다.
  let value = $("#input-q1").val();
  // 2. 만약 입력값이 빈칸이면 if(입력값=='')
  if (value == "") {
    // 3. alert('입력하세요!') 띄우기
    alert("입력하세요!");
  } else {
    // 4. alert(입력값) 띄우기
    alert(value);
  }
}

function q2() {
  // 1. input-q2 값을 가져온다.
  let email = $("#input-q2").val();
  // 2. 만약 가져온 값에 @가 있으면 (includes 이용하기 - 찾아보자!)
  if (email.includes("@")) {
    // 3. info.spartacoding@gmail.com -> gmail 만 추출해서
    // 4. alert(도메인 값);으로 띄우기
    let domainWithDot = email.split("@")[1];
    let onlyDomain = domainWithDot.split(".")[0];
    alert(onlyDomain);
  } else {
    // 5. 만약 이메일이 아니면 '이메일이 아닙니다.' 라는 얼럿 띄우기
    alert("이메일이 아닙니다.");
  }
}

function q3() {
  // 1. input-q3 값을 가져온다.
  let newName = $("#input-q3").val();
  if (newName == "") {
    alert("이름을 입력하세요");
    return;
  }
  // 2. 가져온 값을 이용해 names-q3에 붙일 태그를 만든다. (let temp_html = `<li>${가져온 값}</li>`)
  let temp_html = `<li>${newName}</li>`;
  // 3. 만들어둔 temp_html을 names-q3에 붙인다.(jQuery의 $('...').append(temp_html)을 이용하면 굿!)
  $("#names-q3").append(temp_html);
  $("#input-q3").val("");
}

function q3_remove() {
  // 1. names-q3의 내부 태그를 모두 비운다.(jQuery의 $('....').empty()를 이용하면 굿!)
  $("#names-q3").empty();
}
