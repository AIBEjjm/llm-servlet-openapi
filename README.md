gpt한테 부탁한 superhero api 공식문서 해석

SuperHero API는 슈퍼히어로 및 빌런의 다양한 정보를 제공하는 RESTful API입니다. **액세스 토큰**을 발급받으면 특정 캐릭터의 능력치, 전기, 외모, 직업, 관계, 이미지 등을 조회할 수 있습니다.

---

## **📌 기본 사용법**
1. **액세스 토큰 발급**  
   - [SuperHero API 공식 웹사이트](https://superheroapi.com/api/access-token)에서 GitHub 계정을 이용해 발급.
2. **API 요청 형식**  
   - `https://superheroapi.com/api/액세스토큰/엔드포인트`
   - 예시: `https://superheroapi.com/api/your-access-token/70`  
     (ID 70번 캐릭터의 모든 정보 조회)

---

## **📌 API 엔드포인트 정리**
| **엔드포인트** | **HTTP 메서드** | **설명** |
|--------------|-------------|----------|
| `/id` | `GET` | 캐릭터 ID를 이용해 모든 정보 조회 |
| `/id/powerstats` | `GET` | 캐릭터의 능력치 조회 (지능, 힘, 속도 등) |
| `/id/biography` | `GET` | 캐릭터의 전기 정보 조회 (본명, 출판사, 첫 등장 등) |
| `/id/appearance` | `GET` | 캐릭터의 외모 정보 조회 (성별, 키, 체중 등) |
| `/id/work` | `GET` | 캐릭터의 직업 및 활동 지역 조회 |
| `/id/connections` | `GET` | 캐릭터의 소속 그룹 및 친척 관계 조회 |
| `/id/image` | `GET` | 캐릭터의 이미지 URL 조회 |
| `/search/name` | `GET` | 캐릭터 이름으로 검색해 ID 반환 |

---

## **📌 상세 API 설명 및 예제**

### **🔹 1. 특정 캐릭터 정보 조회**
- 특정 ID를 가진 캐릭터의 **모든 정보를 조회**  
- **요청:**  
  ```
  GET https://superheroapi.com/api/your-access-token/70
  ```
- **응답 예시:**
  ```json
  {
    "response": "success",
    "id": "70",
    "name": "Batman",
    "powerstats": {...},
    "biography": {...},
    "appearance": {...},
    "work": {...},
    "connections": {...},
    "image": {...}
  }
  ```

---

### **🔹 2. 능력치 조회**
- 캐릭터의 **능력치(Intelligence, Strength, Speed, Durability, Power, Combat)**
- **요청:**  
  ```
  GET https://superheroapi.com/api/your-access-token/70/powerstats
  ```
- **응답 예시:**
  ```json
  {
    "intelligence": "100",
    "strength": "26",
    "speed": "27",
    "durability": "50",
    "power": "47",
    "combat": "100"
  }
  ```

---

### **🔹 3. 전기 정보 조회**
- 캐릭터의 **본명, 출판사, 첫 등장, 성향** 등 조회
- **요청:**  
  ```
  GET https://superheroapi.com/api/your-access-token/70/biography
  ```
- **응답 예시:**
  ```json
  {
    "full-name": "Bruce Wayne",
    "alter-egos": "No alter egos found.",
    "place-of-birth": "Gotham City",
    "first-appearance": "Detective Comics #27",
    "publisher": "DC Comics",
    "alignment": "good"
  }
  ```

---

### **🔹 4. 외모 정보 조회**
- **키, 몸무게, 눈 색깔, 머리 색깔** 등 조회
- **요청:**  
  ```
  GET https://superheroapi.com/api/your-access-token/70/appearance
  ```
- **응답 예시:**
  ```json
  {
    "gender": "Male",
    "race": "Human",
    "height": ["6'2", "188 cm"],
    "weight": ["210 lb", "95 kg"],
    "eye-color": "Blue",
    "hair-color": "Black"
  }
  ```

---

### **🔹 5. 직업 정보 조회**
- **직업 및 활동 기반 조회**
- **요청:**  
  ```
  GET https://superheroapi.com/api/your-access-token/70/work
  ```
- **응답 예시:**
  ```json
  {
    "occupation": "Businessman",
    "base": "Batcave, Gotham City"
  }
  ```

---

### **🔹 6. 관계 정보 조회**
- **소속 그룹 및 친척 관계 조회**
- **요청:**  
  ```
  GET https://superheroapi.com/api/your-access-token/70/connections
  ```
- **응답 예시:**
  ```json
  {
    "group-affiliation": "Justice League",
    "relatives": "Thomas Wayne (father), Martha Wayne (mother)"
  }
  ```

---

### **🔹 7. 이미지 조회**
- **캐릭터의 이미지 URL 제공**
- **요청:**  
  ```
  GET https://superheroapi.com/api/your-access-token/70/image
  ```
- **응답 예시:**
  ```json
  {
    "url": "https://www.superherodb.com/pictures2/portraits/10/100/639.jpg"
  }
  ```

---

### **🔹 8. 이름으로 캐릭터 검색**
- 캐릭터의 이름을 검색해 **ID 획득**
- **요청:**  
  ```
  GET https://superheroapi.com/api/your-access-token/search/batman
  ```
- **응답 예시:**
  ```json
  {
    "response": "success",
    "results-for": "batman",
    "results": [
      {
        "id": "70",
        "name": "Batman",
        "biography": {...},
        "appearance": {...},
        "powerstats": {...},
        "image": {...}
      }
    ]
  }
  ```

---

## **📌 정리**
- **SuperHero API를 사용하면 슈퍼히어로 및 빌런의 다양한 정보를 조회할 수 있음.**
- **필요한 데이터 종류에 따라 적절한 엔드포인트를 호출하면 JSON 데이터로 응답을 받을 수 있음.**
- **검색 → ID 획득 → 상세 정보 조회 순서로 사용 가능.**
- **이미지 URL도 제공되어 UI에 쉽게 적용 가능.**

### ✅ **예제 워크플로우**
1. `/search/name` → 캐릭터 이름으로 ID 획득
2. `/id` → 전체 정보 조회 또는 `/id/특정엔드포인트`로 필요한 데이터 조회
3. `/id/image` → 이미지 URL 가져오기

---

# 트러블 슈팅
## 302 ERROR

- 문제상황

웹 브라우저에서 직접 url로 api를 요청할때는 json형식으로 잘 출력되었는데 IDLE내에 콘솔창에서는 302 오류가 났다.

- 해결 방법

1. Java의 HttpClient가 302 Redirect를 자동으로 따라가도록 설정
```java
HttpClient.newBuilder()
    .followRedirects(HttpClient.Redirect.ALWAYS)
    .build();
```
- 기본적으로 Java의 HttpClient는 302 Redirect를 자동으로 처리하지 않음
- 위 옵션을 추가하면 Redirect를 자동으로 처리하도록 변경

2. User-Agent를 추가하여 브라우저처럼 요청
```java
.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
```
- 일부 API는 봇(Bot) 요청을 차단할 수도 있음
- 브라우저처럼 보이도록 요청하면 차단되지 않을 가능성이 높음