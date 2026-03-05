package jsp.board;

import java.sql.Timestamp;

/**
 * 게시글 정보를 담는 DTO (Data Transfer Object)
 */
public class BoardDTO {
    private int id;
    private String title;
    private String content;
    private String writer;
    private String password;
    private int hit;
    private Timestamp regDate;
    private Timestamp modDate;
    
    // 기본 생성자
    public BoardDTO() {
    }
    
    // 전체 필드 생성자
    public BoardDTO(int id, String title, String content, String writer, 
                     String password, int hit, Timestamp regDate, Timestamp modDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.password = password;
        this.hit = hit;
        this.regDate = regDate;
        this.modDate = modDate;
    }
    
    // 게시글 작성용 생성자
    public BoardDTO(String title, String content, String writer, String password) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.password = password;
        this.hit = 0;
    }
    
    // Getter와 Setter 메서드
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getWriter() {
        return writer;
    }
    
    public void setWriter(String writer) {
        this.writer = writer;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getHit() {
        return hit;
    }
    
    public void setHit(int hit) {
        this.hit = hit;
    }
    
    public Timestamp getRegDate() {
        return regDate;
    }
    
    public void setRegDate(Timestamp regDate) {
        this.regDate = regDate;
    }
    
    public Timestamp getModDate() {
        return modDate;
    }
    
    public void setModDate(Timestamp modDate) {
        this.modDate = modDate;
    }
    
    // 유틸리티 메서드
    /**
     * 제목이 비어있는지 확인
     */
    public boolean hasTitle() {
        return title != null && !title.trim().isEmpty();
    }
    
    /**
     * 내용이 비어있는지 확인
     */
    public boolean hasContent() {
        return content != null && !content.trim().isEmpty();
    }
    
    /**
     * 내용의 줄바꿈을 <br>로 변환
     */
    public String getContentWithBr() {
        if (content == null) {
            return "";
        }
        return content.replace("\n", "<br>").replace("\r", "");
    }
    
    /**
     * 제목이 길면 자르기
     */
    public String getShortTitle(int maxLength) {
        if (title == null) {
            return "";
        }
        if (title.length() > maxLength) {
            return title.substring(0, maxLength) + "...";
        }
        return title;
    }
    
    @Override
    public String toString() {
        return "BoardDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", writer='" + writer + '\'' +
                ", hit=" + hit +
                ", regDate=" + regDate +
                '}';
    }
}
