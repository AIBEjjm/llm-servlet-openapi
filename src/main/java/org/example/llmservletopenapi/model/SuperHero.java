package org.example.llmservletopenapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * SuperHero 모델 클래스
 * SuperHeroAPI에서 받아온 JSON 데이터를 객체로 변환하기 위한 클래스입니다.
 * id, name, powerstats, biography, appearance, work, connections, image 정보를 포함합니다.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuperHero {

    private String id;
    private String name;

    @JsonProperty("powerstats")
    private PowerStats powerStats;

    @JsonProperty("biography")
    private Biography biography;

    @JsonProperty("appearance")
    private Appearance appearance;

    @JsonProperty("work")
    private Work work;

    @JsonProperty("connections")
    private Connections connections;

    @JsonProperty("image")
    private Image image;

    // 기본 생성자 (디버깅 로그 포함)
    public SuperHero() {
        System.out.println("[DEBUG] SuperHero 모델 클래스가 정상적으로 로드되었습니다.");
    }

    // Getter 및 Setter 메서드
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public PowerStats getPowerStats() { return powerStats; }
    public void setPowerStats(PowerStats powerStats) { this.powerStats = powerStats; }

    public Biography getBiography() { return biography; }
    public void setBiography(Biography biography) { this.biography = biography; }

    public Appearance getAppearance() { return appearance; }
    public void setAppearance(Appearance appearance) { this.appearance = appearance; }

    public Work getWork() { return work; }
    public void setWork(Work work) { this.work = work; }

    public Connections getConnections() { return connections; }
    public void setConnections(Connections connections) { this.connections = connections; }

    public Image getImage() { return image; }
    public void setImage(Image image) { this.image = image; }

    /**
     * 내부 클래스 PowerStats
     * 캐릭터의 능력치 데이터를 저장합니다.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PowerStats {
        public String intelligence;
        public String strength;
        public String speed;
        public String durability;
        public String power;
        public String combat;
    }

    /**
     * 내부 클래스 Biography
     * 캐릭터의 전기 정보를 저장합니다.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Biography {
        @JsonProperty("full-name")
        private String fullName;

        @JsonProperty("alter-egos")
        private String alterEgos;

        private List<String> aliases;

        @JsonProperty("place-of-birth")
        private String placeOfBirth;

        @JsonProperty("first-appearance")
        private String firstAppearance;

        private String publisher;
        private String alignment;

        public String getFullName() { return fullName; }
        public void setFullName(String fullName) { this.fullName = fullName; }

        public String getAlterEgos() { return alterEgos; }
        public void setAlterEgos(String alterEgos) { this.alterEgos = alterEgos; }

        public List<String> getAliases() { return aliases; }
        public void setAliases(List<String> aliases) { this.aliases = aliases; }

        public String getPlaceOfBirth() { return placeOfBirth; }
        public void setPlaceOfBirth(String placeOfBirth) { this.placeOfBirth = placeOfBirth; }

        public String getFirstAppearance() { return firstAppearance; }
        public void setFirstAppearance(String firstAppearance) { this.firstAppearance = firstAppearance; }

        public String getPublisher() { return publisher; }
        public void setPublisher(String publisher) { this.publisher = publisher; }

        public String getAlignment() { return alignment; }
        public void setAlignment(String alignment) { this.alignment = alignment; }
    }

    /**
     * 내부 클래스 Appearance
     * 캐릭터의 외형 정보를 저장합니다.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Appearance {
        public String gender;
        public String race;
        public String[] height;
        public String[] weight;

        @JsonProperty("eye-color")
        public String eyeColor;

        @JsonProperty("hair-color")
        public String hairColor;
    }

    /**
     * 내부 클래스 Work
     * 캐릭터의 직업 정보를 저장합니다.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Work {
        public String occupation;
        public String base;
    }

    /**
     * 내부 클래스 Connections
     * 캐릭터의 연결 정보를 저장합니다.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Connections {
        @JsonProperty("group-affiliation")
        public String groupAffiliation;
        public String relatives;
    }

    /**
     * 내부 클래스 Image
     * 캐릭터의 이미지 URL을 저장합니다.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Image {
        public String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
