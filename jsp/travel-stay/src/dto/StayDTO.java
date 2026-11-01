package dto;

import java.sql.Timestamp;

public class StayDTO {
    private int id;
    private int destinationId;
    private String name;
    private String address;
    private int price;
    private String description;
    private String image;
    private Timestamp regDate;
    private String destinationName;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getDestinationId() { return destinationId; }
    public void setDestinationId(int destinationId) { this.destinationId = destinationId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public Timestamp getRegDate() { return regDate; }
    public void setRegDate(Timestamp regDate) { this.regDate = regDate; }
    public String getDestinationName() { return destinationName; }
    public void setDestinationName(String destinationName) { this.destinationName = destinationName; }
}
