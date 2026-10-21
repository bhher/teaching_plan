package dto;

import java.sql.Timestamp;

public class ReviewDTO {
    private int id;
    private int destinationId;
    private String memberId;
    private String memberName;
    private int rating;
    private String content;
    private Timestamp regDate;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getDestinationId() { return destinationId; }
    public void setDestinationId(int destinationId) { this.destinationId = destinationId; }
    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }
    public String getMemberName() { return memberName; }
    public void setMemberName(String memberName) { this.memberName = memberName; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Timestamp getRegDate() { return regDate; }
    public void setRegDate(Timestamp regDate) { this.regDate = regDate; }
}
