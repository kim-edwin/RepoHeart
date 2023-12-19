let heartLocations = []; // 하트 위치 정보를 일시적으로 저장하는 배열

document.addEventListener('DOMContentLoaded', function() {
  let heartCursor = document.querySelector('.heart-cursor');
  let isDraggingHeart = false;

  document.addEventListener('mousedown', function(e) {
    if (e.target.classList.contains('draggable-sticker')) {
      return; // 이미지를 클릭했을 때는 무시
    }

    if (!isDraggingHeart) {
      heartCursor.style.display = 'block';
      heartCursor.style.left = e.pageX + 'px';
      heartCursor.style.top = e.pageY + 'px';
    }

    isDraggingHeart = true;
  });

  document.addEventListener('mousemove', function(e) {
    if (isDraggingHeart) {
      heartCursor.style.left = e.pageX + 'px';
      heartCursor.style.top = e.pageY + 'px';
    }
  });

  document.addEventListener('mouseup', function(e) {
    if (isDraggingHeart) {
      isDraggingHeart = false;

      var copy = document.createElement('img');
      copy.src = '../static/assets/img/clickheart.png';
      copy.classList.add('draggable-sticker');
      copy.style.position = 'absolute';
      copy.style.left = heartCursor.style.left;
      copy.style.top = heartCursor.style.top;
      document.body.appendChild(copy);

      // 우클릭 이벤트 리스너를 추가하여 스티커를 제거
      copy.addEventListener('contextmenu', function(event) {
        event.preventDefault();
        document.body.removeChild(copy);
      });

      heartCursor.style.display = 'none';
    }
  });
});