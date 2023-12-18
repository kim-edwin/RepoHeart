let heartLocations = []; // 하트 위치 정보를 일시적으로 저장하는 배열

document.addEventListener('DOMContentLoaded', function() {
    let heartCursor = document.querySelector('.heart-cursor');
    let isDraggingHeart = false;
  
    document.addEventListener('mousedown', function(e) {
      if (e.target.classList.contains('draggable-sticker')) {
          e.preventDefault();
          return; // 이미지를 클릭했을 때는 무시
      }

    // 클릭된 엘리먼트가 이미지 태그인 경우에만 실행
    if (e.target.tagName.toLowerCase() === 'img') {
        if (!isDraggingHeart) {
            heartCursor.style.display = 'block';
            heartCursor.style.left = e.pageX + 'px';
            heartCursor.style.top = e.pageY + 'px';
        }

        isDraggingHeart = true;
    }
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
        copy.style.left = (parseInt(heartCursor.style.left) - 24) + 'px';
        copy.style.top = (parseInt(heartCursor.style.top) - 13) + 'px';
        document.body.appendChild(copy);

        // 하트 위치 정보를 배열에 추가
        heartLocations.push({
          x: parseInt(heartCursor.style.left),
          y: parseInt(heartCursor.style.top)
        });
  
        // 우클릭 이벤트 리스너를 추가하여 스티커를 제거
        copy.addEventListener('contextmenu', function(event) {
          event.preventDefault();
          document.body.removeChild(copy);
        });
  
        heartCursor.style.display = 'none';
      }
    });

     // Reset 버튼 클릭 이벤트 핸들러
      document.getElementById('resetButton').addEventListener('click', function() {
      // 모든 하트 스티커 제거
      var heartStickers = document.querySelectorAll('.draggable-sticker');
      heartStickers.forEach(function(heartSticker) {
          heartSticker.parentNode.removeChild(heartSticker);
      });
    });

    // 하트 이미지 여부 확인 함수
    function isHeartImage(src) {
      // 여기에 하트 이미지의 특정 조건을 추가하면 됩니다.
      // 예를 들어, 이미지 파일 이름이 "heart"로 시작하는 경우 등
      return src.includes('heart');
    }

    // 부모 노드 중에서 이미지 섹션을 찾는 함수
    function findImageSection(element) {
      while (element && !element.classList.contains('resume-section')) {
          element = element.parentNode;
      }
      return element;
  }

    // 서버로 위치 정보 배열 전송
    function saveHeartLocations() {
      fetch('/saveHeartLocations', {
          method: 'POST',
          headers: {
              'Content-Type': 'application/json',
          },
          body: JSON.stringify(heartLocations),
      });

    // 배열 비우기
    heartLocations = [];
    }

    // Save 버튼 클릭 이벤트 핸들러
    document.getElementById('saveButton').addEventListener('click', function() {
        saveHeartLocations();
    });
    
  });