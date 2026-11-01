package dto;

import java.sql.Timestamp;

public class BoardDTO {

    private int id;
    private String title;
    private String content;
    private String writerId;
    private String writerName;
    private Timestamp regDate;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getWriterId() { return writerId; }
    public void setWriterId(String writerId) { this.writerId = writerId; }

    public String getWriterName() { return writerName; }
    public void setWriterName(String writerName) { this.writerName = writerName; }

    public Timestamp getRegDate() { return regDate; }
    public void setRegDate(Timestamp regDate) { this.regDate = regDate; }
}
