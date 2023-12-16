document.addEventListener('DOMContentLoaded', function() {
  var dragSticker = document.getElementById('drag-sticker');

  // 드래그 시작 이벤트
  dragSticker.addEventListener('dragstart', function(e) {
      e.dataTransfer.setData('text/plain', e.target.id);
  });

  // 드롭 영역에 드래그 요소가 들어왔을 때
  document.addEventListener('dragover', function(e) {
      e.preventDefault(); // 기본 드래그 이벤트 방지
      e.target.classList.add('drop-area-highlight');
  });

  // 드롭 영역에서 드래그 요소가 나갈 때
  document.addEventListener('dragleave', function(e) {
      e.target.classList.remove('drop-area-highlight');
  });

  // 드롭 이벤트
  document.addEventListener('drop', function(e) {
      e.preventDefault(); // 기본 드롭 이벤트 방지
      var data = e.dataTransfer.getData('text/plain');
      var copy = document.getElementById(data).cloneNode(true); // 복제 생성
      copy.style.position = 'absolute';
      copy.style.left = e.pageX - 50 + 'px'; // 스티커 이미지의 중앙을 마우스 포인터 위치에 맞춤
      copy.style.top = e.pageY - 50 + 'px'; // 위와 동일하게 y좌표 조정
      document.body.appendChild(copy);
      e.target.classList.remove('drop-area-highlight');

      // 우클릭 이벤트 리스너를 추가하여 스티커를 제거
      copy.addEventListener('contextmenu', function(event) {
          event.preventDefault(); // 기본 우클릭 메뉴 방지
          document.body.removeChild(copy); // 스티커 제거
      });
  });
});
