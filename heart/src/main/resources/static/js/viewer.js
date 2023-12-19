let heartLocations = []; // 하트 위치 정보를 일시적으로 저장하는 배열

document.addEventListener('DOMContentLoaded', function() {
    let heartCursor = document.querySelector('.heart-cursor');
    let isDraggingHeart = false;
    let url = window.location.href;
    let resumeId = url.substring(url.lastIndexOf('/') + 1);

    // 페이지 로딩 시 서버로부터 하트 정보를 가져와서 화면에 추가
    loadHeartsFromServer();

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
    
            // 현재 페이지 URL에서 resumeId 추출
            //const url = window.location.href;
            //const resumeId = url.substring(url.lastIndexOf('/') + 1);
    
            // 현재 이미지가 몇 번째 이미지인지 가져오기
            const imgSections = document.querySelectorAll('.resume-section img[data-action="zoom"]');
            const currentPageIndex = Array.from(imgSections).findIndex(img => img === e.target);
    
            // 이미지를 찾지 못한 경우 기본값 1로 설정
            const pageNumber = currentPageIndex !== -1 ? currentPageIndex + 1 : 1;
    
            // 하트 위치 정보를 배열에 추가
            const heartDto = {
                heartId: null,
                resumeId: parseInt(resumeId),
                pageNumber: pageNumber,
                xcoordinate: parseInt(heartCursor.style.left),
                ycoordinate: parseInt(heartCursor.style.top),
                // 다른 필요한 데이터도 추가
            };
    
            heartLocations.push(heartDto);
            console.log("하트추가 완료", heartLocations);
    
            // 우클릭 이벤트 리스너를 추가하여 스티커를 제거
            copy.addEventListener('contextmenu', function(event) {
                event.preventDefault();
                document.body.removeChild(copy);
            
                // 현재 클릭된 스티커의 좌표를 가져오기
                const clickedX = parseInt(copy.style.left);
                const clickedY = parseInt(copy.style.top);
            
                // 배열에서 해당 좌표를 가진 하트 정보를 찾아 삭제
                heartLocations = heartLocations.filter(heart => {
                    return heart.xcoordinate !== clickedX || heart.ycoordinate !== clickedY;
                });
            });
    
            heartCursor.style.display = 'none';
        }
    });

    document.addEventListener('contextmenu', function(e) {
        e.preventDefault();
    
        // 스티커 엘리먼트인 경우
        if (e.target.classList.contains('draggable-sticker')) {
            const clickedX = parseInt(e.target.style.left) + 24;
            const clickedY = parseInt(e.target.style.top) + 13;
    
            // 배열에서 해당 좌표를 가진 하트 정보를 찾아 삭제
            heartLocations = heartLocations.filter(heart => {
                return heart.xcoordinate !== clickedX || heart.ycoordinate !== clickedY;
            });
    
            console.log("삭제 완료", heartLocations);
    
            // 클릭된 스티커 제거
            const parent = e.target.parentNode;
            if (parent && parent === document.body) {
                document.body.removeChild(e.target);
            }
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

    // Save 버튼 클릭 이벤트 핸들러
    document.getElementById('saveButton').addEventListener('click', function() {
        updateHearts();
    });

    // 서버로부터 하트 정보를 받아와서 화면에 추가
    function loadHeartsFromServer() {
        
        // 서버에서 하트 정보를 가져오는 API 엔드포인트로 요청
        fetch(`/getHeartsByResumeId/${resumeId}`)
            .then(response => response.json())
            .then(data => {
                // 받아온 하트 정보를 화면에 추가
                addHeartsToScreen(data);

                //받아온 하트 정보를 배열에 추가
                heartLocations = data;
            })
            .catch(error => {
                console.error('Error loading hearts from server:', error);
            });
        
            console.log("하트 로딩 완료"+ heartLocations)
    }

    // 받아온 하트 정보를 화면에 추가하는 함수
    function addHeartsToScreen(heartDtoList) {
        console.log(heartDtoList);
        heartDtoList.forEach(heartDto => {
            // 각 하트의 좌표를 이용하여 화면에 하트 추가
            createHeartImage(heartDto.xcoordinate, heartDto.ycoordinate);
        });
    }

    // 하트 이미지를 생성하고 화면에 추가하는 함수 (예시)
    function createHeartImage(x, y) {

        var offsetX = -24;
        var offsetY = -13;

        var heartImage = document.createElement('img');
        heartImage.src = '../static/assets/img/clickheart.png';
        heartImage.classList.add('draggable-sticker');
        heartImage.style.position = 'absolute';
        heartImage.style.left = x + offsetX + 'px';
        heartImage.style.top = y + offsetY + 'px';
        document.body.appendChild(heartImage);
    }

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
    function updateHearts() {
        const url = window.location.href;
        const resumeId = url.substring(url.lastIndexOf('/') + 1);
    
        const heartDto = {
            resumeId: parseInt(resumeId),
        };
    
        fetch('/updateHearts', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(heartLocations),
        })
        .then(response => response.json())
        .then(data => {
            // 성공적으로 처리된 경우, 필요한 동작 수행
            console.log(data);
            // 배열 비우기
            heartLocations = [];
            // 페이지를 새로고침하거나 다른 동작 수행
            //window.location.reload(); // 페이지를 새로고침하는 예시
        })
        .catch(error => {
            // 오류가 발생한 경우, 오류 메시지를 표시하거나 적절한 처리 수행
            console.error('There was a problem with the fetch operation:', error);
            // 클라이언트 측에서 발생한 에러를 콘솔에 자세하게 출력
            console.error('Client-side error:', error.message, error.stack);
            // 다른 예외 처리 로직 수행
        });
    }
    
});