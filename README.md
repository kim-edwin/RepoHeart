HeartRepo
이 저장소는 팀 협업을 위한 Git 및 GitHub 프로세스를 정의합니다.

초기 설정
원격 저장소 생성:

GitHub에서 "HeartRepo"라는 이름의 원격 저장소를 생성합니다.
로컬에서 클론:

bash
Copy code
git clone <원격 저장소 URL>
cd HeartRepo
개인 브랜치 생성:

bash
Copy code
git checkout -b <개인 브랜치 이름>
개발 작업을 수행합니다.

로컬에서 작업
로컬에서 작업:

개인 브랜치에서 필요한 작업을 수행합니다.
로컬 Main 브랜치 최신화:

bash
Copy code
git checkout main
git pull origin main
로컬 개인 브랜치 최신화 및 병합:

bash
Copy code
git checkout <개인 브랜치 이름>
git pull origin main
변경 사항을 통합합니다.

로컬 Main 브랜치로 변경 사항 통합:

bash
Copy code
git checkout main
git merge <개인 브랜치 이름>
충돌이 발생하면 충돌을 해결하고 커밋합니다.
로컬 Main 브랜치 푸시:

bash
Copy code
git push origin main
원격 저장소에서 Pull Request 생성:

GitHub 웹 인터페이스에서 Pull Request를 생성합니다.
리뷰 및 머지:

팀원들의 리뷰를 거쳐 변경 사항을 메인 브랜치에 머지합니다.
로컬 및 원격 Main 브랜치 최신화:

bash
Copy code
git checkout main
git pull origin main
주의 사항
충돌이 발생하면 즉시 해결하여 커밋하고 계속 진행합니다.
팀원들과 주기적인 Pull 및 변경 내용 공유를 유지합니다.
