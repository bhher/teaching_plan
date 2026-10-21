package dto;

import java.sql.Timestamp;

public class DestinationDTO {
    private int id;
    private String name;
    private String region;
    private String description;
    private String image;
    private Timestamp regDate;
    private int stayCount;
    private Double avgRating;
    private int reviewCount;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public Timestamp getRegDate() { return regDate; }
    public void setRegDate(Timestamp regDate) { this.regDate = regDate; }
    public int getStayCount() { return stayCount; }
    public void setStayCount(int stayCount) { this.stayCount = stayCount; }
    public Double getAvgRating() { return avgRating; }
    public void setAvgRating(Double avgRating) { this.avgRating = avgRating; }
    public int getReviewCount() { return reviewCount; }
    public void setReviewCount(int reviewCount) { this.reviewCount = reviewCount; }
}
