package org.example.llmservletopenapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * SuperHero 모델 클래스
 * SuperHeroAPI에서 받아온 JSON 데이터를 객체로 변환하기 위한 클래스입니다.
 * 각 필드는 JSON 응답의 해당 데이터와 매핑됩니다.
 * 디버깅 로그: 클래스가 로드되면 로그를 출력합니다.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuperHero {

    // 캐릭터 ID
    private String id;
    // 캐릭터 이름
    private String name;

    // 캐릭터의 능력치 정보 (JSON의 "powerstats" 필드와 매핑)
    @JsonProperty("powerstats")
    private PowerStats powerStats;

    // 캐릭터의 전기 정보 (JSON의 "biography" 필드와 매핑)
    @JsonProperty("biography")
    private Biography biography;

    // 기본 생성자
    public SuperHero() {
        // 디버깅 로그: SuperHero 모델이 생성되었음을 출력
        System.out.println("[DEBUG] SuperHero 모델 클래스가 정상적으로 로드되었습니다.");
    }

    // Getter & Setter 메서드
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public PowerStats getPowerStats() { return powerStats; }
    public void setPowerStats(PowerStats powerStats) { this.powerStats = powerStats; }

    public Biography getBiography() { return biography; }
    public void setBiography(Biography biography) { this.biography = biography; }

    /**
     * 내부 클래스 PowerStats
     * 캐릭터의 능력치 데이터를 저장합니다.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PowerStats {
        public String intelligence; // 지능
        public String strength;     // 힘
        public String speed;        // 속도
        public String durability;   // 내구도
        public String power;        // 파워
        public String combat;       // 전투력
    }

    /**
     * 내부 클래스 Biography
     * 캐릭터의 전기(출생, 첫 등장 등) 정보를 저장합니다.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Biography {
        public String fullName;         // 전체 이름
        public String firstAppearance;  // 첫 등장
        public String publisher;        // 출판사
    }
}
