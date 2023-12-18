document.addEventListener('DOMContentLoaded', function() {
  // 드래그 시작 이벤트
  document.addEventListener('dragstart', function(e) {
      if (e.target.classList.contains('draggable-sticker')) {
          e.dataTransfer.setData('text/plain', e.target.src);
      }
  });

  // 드롭 영역에 드래그 요소가 들어왔을 때
  document.addEventListener('dragover', function(e) {
      e.preventDefault();
      if (e.target.classList.contains('drop-area')) {
          e.target.classList.add('drop-area-highlight');
      }
  });

  // 드롭 영역에서 드래그 요소가 나갈 때
  document.addEventListener('dragleave', function(e) {
      if (e.target.classList.contains('drop-area')) {
          e.target.classList.remove('drop-area-highlight');
      }
  });

  // 드롭 이벤트
  document.addEventListener('drop', function(e) {
      e.preventDefault();
      var data = e.dataTransfer.getData('text/plain');
      var copy = document.createElement('img');
      copy.src = data;
      copy.classList.add('draggable-sticker');
      copy.style.position = 'absolute';
      copy.style.left = e.pageX - 25 + 'px'; // 이미지 크기 절반으로 조정
      copy.style.top = e.pageY - 25 + 'px';
      document.body.appendChild(copy);

      // 우클릭 이벤트 리스너를 추가하여 스티커를 제거
      copy.addEventListener('contextmenu', function(event) {
          event.preventDefault();
          document.body.removeChild(copy);
      });
  });
});