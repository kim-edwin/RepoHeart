# HeartRepo

팀 협업을 위한 Git 및 GitHub 프로세스입니다.

## 초기 설정

1. **원격 저장소 생성:**
   - GitHub에서 "HeartRepo"라는 이름의 원격 저장소 생성.

2. **로컬에서 클론:**
   ```bash
   git clone <원격 저장소 URL>
   cd HeartRepo

## 개인 작업 수행 프로세스

1. **local/main 브랜치에서 origin/main 브랜치를 pull 해온다.**
   ```bash
   local/main 브랜치가 최신화 되었다.

2. **local/개인 브랜치에서 origin/main 브랜치를 pulll 해온다. (pull from 이용)**
   '''bash
   local/개인 브랜치도 최신화 되었다. (local/main과 loca/개인이 같아짐)

3. **local/개인 브랜치에서 작업을 수행한다.**

4. **(작업 완료 후) local/개인 브랜치에서 Commit 할 목록을 staging 한다.**

   4-1. Commit 하지 않을 수정사항들은 staging 하지 않는다.

6. **local/개인 브랜치에서 Commit 한다.**

7. **local/main 브랜치로 이동, 개인 브랜치의 작업 내용을 local/main에 merge한다. (staging 된 항목만 merge된다.)**
   ```bash
   local/개인브랜치와 local/main 브랜치가 같아졌다.

8. **(local/main에서 실행해본 뒤 이상이 없음을 확인하고) origin/main으로 push한다.**

   7.1 push 할 때 현재 가장 최신화 되어있는 origin/main과 충돌할 경우 git이 충돌을 해결하라고 push를 중단한다. 해결하고 push 재실행.
   ```bash
   local/개인브랜치, local/main 브랜치, original/main 브랜치가 같아졌다.

9. **local/개인 브랜치로 이동, local/개인 브랜치를 origin/개인 브랜치로 push한다.**
   ```bash
   local/개인브랜치, local/main 브랜치, original/main 브랜치가 같아졌다.

**위 과정을 반복하면서 프로젝트를 완성시켜나간다.**
