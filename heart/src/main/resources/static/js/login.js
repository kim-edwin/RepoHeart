const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
  container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
  container.classList.remove("right-panel-active");
});

signInButton.addEventListener('click', () => {
  // 특정 동작을 추가하고 싶은 경우 여기에 코드를 작성
  console.log('Login button clicked!');

  // 기존의 동작(화면 전환)은 이 코드에 영향을 받지 않음
  container.classList.remove('right-panel-active');
});