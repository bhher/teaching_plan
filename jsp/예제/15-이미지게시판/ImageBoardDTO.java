package jsp.imageboard;

import java.sql.Timestamp;

/**
 * 이미지 게시글 정보를 담는 DTO (Data Transfer Object)
 */
public class ImageBoardDTO {
    private int id;
    private String title;
    private String content;
    private String writer;
    private String password;
    private String imageFile;        // 저장된 이미지 파일명
    private String imageOriginal;     // 원본 이미지 파일명
    private int hit;
    private Timestamp regDate;
    private Timestamp modDate;
    
    // 기본 생성자
    public ImageBoardDTO() {
    }
    
    // 전체 필드 생성자
    public ImageBoardDTO(int id, String title, String content, String writer, 
                         String password, String imageFile, String imageOriginal,
                         int hit, Timestamp regDate, Timestamp modDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.password = password;
        this.imageFile = imageFile;
        this.imageOriginal = imageOriginal;
        this.hit = hit;
        this.regDate = regDate;
        this.modDate = modDate;
    }
    
    // 게시글 작성용 생성자
    public ImageBoardDTO(String title, String content, String writer, String password, 
                         String imageFile, String imageOriginal) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.password = password;
        this.imageFile = imageFile;
        this.imageOriginal = imageOriginal;
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
    
    public String getImageFile() {
        return imageFile;
    }
    
    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }
    
    public String getImageOriginal() {
        return imageOriginal;
    }
    
    public void setImageOriginal(String imageOriginal) {
        this.imageOriginal = imageOriginal;
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
     * 이미지 파일이 있는지 확인
     */
    public boolean hasImage() {
        return imageFile != null && !imageFile.trim().isEmpty();
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
        return "ImageBoardDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", writer='" + writer + '\'' +
                ", imageFile='" + imageFile + '\'' +
                ", hit=" + hit +
                ", regDate=" + regDate +
                '}';
    }
}
