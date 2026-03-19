package notion;

import java.sql.Timestamp;

/**
 * 노트 데이터 전송 객체
 */
public class NoteDTO {
    private int id;
    private String title;
    private String content;
    private String writer;
    private int ref;
    private int reLevel;
    private int reStep;
    private int deleted;
    private Timestamp deletedAt;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getWriter() { return writer; }
    public void setWriter(String writer) { this.writer = writer; }

    public int getRef() { return ref; }
    public void setRef(int ref) { this.ref = ref; }

    public int getReLevel() { return reLevel; }
    public void setReLevel(int reLevel) { this.reLevel = reLevel; }

    public int getReStep() { return reStep; }
    public void setReStep(int reStep) { this.reStep = reStep; }

    public int getDeleted() { return deleted; }
    public void setDeleted(int deleted) { this.deleted = deleted; }

    public Timestamp getDeletedAt() { return deletedAt; }
    public void setDeletedAt(Timestamp deletedAt) { this.deletedAt = deletedAt; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

    public boolean isRoot() { return reLevel == 0; }
    public boolean isInTrash() { return deleted == 1; }
}
