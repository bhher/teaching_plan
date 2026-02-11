/**
 * 예제 10: 미디어 플레이어 시스템
 * 업캐스팅과 다형성을 활용한 다양한 미디어 파일 재생 시스템
 */
import java.util.ArrayList;

// 미디어 파일 추상 클래스
abstract class MediaFile {
    protected String fileName;
    protected String filePath;
    protected long fileSize; // 바이트 단위
    protected int duration; // 초 단위
    
    MediaFile(String fileName, String filePath, long fileSize, int duration) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.duration = duration;
    }
    
    // 추상 메서드: 각 미디어 타입마다 다른 재생 방식
    abstract void play();
    
    // 추상 메서드: 각 미디어 타입마다 다른 정보
    abstract String getMediaInfo();
    
    // 공통 메서드: 파일 정보 출력
    void printFileInfo() {
        System.out.println("파일명: " + fileName);
        System.out.println("경로: " + filePath);
        System.out.println("크기: " + formatFileSize(fileSize));
        System.out.println("재생시간: " + formatDuration(duration));
        System.out.println("미디어 정보: " + getMediaInfo());
    }
    
    // 파일 크기 포맷팅
    private String formatFileSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.2f KB", bytes / 1024.0);
        return String.format("%.2f MB", bytes / (1024.0 * 1024.0));
    }
    
    // 재생 시간 포맷팅
    private String formatDuration(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        if (hours > 0) {
            return String.format("%d:%02d:%02d", hours, minutes, secs);
        }
        return String.format("%d:%02d", minutes, secs);
    }
    
    String getFileName() {
        return fileName;
    }
}

// 오디오 파일
class AudioFile extends MediaFile {
    private String artist;
    private String album;
    private int bitrate; // kbps
    
    AudioFile(String fileName, String filePath, long fileSize, int duration, 
              String artist, String album, int bitrate) {
        super(fileName, filePath, fileSize, duration);
        this.artist = artist;
        this.album = album;
        this.bitrate = bitrate;
    }
    
    @Override
    void play() {
        System.out.println("🎵 오디오 재생: " + fileName);
        System.out.println("   아티스트: " + artist + ", 앨범: " + album);
    }
    
    @Override
    String getMediaInfo() {
        return "아티스트: " + artist + ", 앨범: " + album + ", 비트레이트: " + bitrate + " kbps";
    }
    
    void showLyrics() {
        System.out.println(fileName + "의 가사를 표시합니다.");
    }
}

// 비디오 파일
class VideoFile extends MediaFile {
    private int width;
    private int height;
    private int frameRate; // fps
    private String codec;
    
    VideoFile(String fileName, String filePath, long fileSize, int duration,
              int width, int height, int frameRate, String codec) {
        super(fileName, filePath, fileSize, duration);
        this.width = width;
        this.height = height;
        this.frameRate = frameRate;
        this.codec = codec;
    }
    
    @Override
    void play() {
        System.out.println("🎬 비디오 재생: " + fileName);
        System.out.println("   해상도: " + width + "x" + height + ", 프레임레이트: " + frameRate + " fps");
    }
    
    @Override
    String getMediaInfo() {
        return "해상도: " + width + "x" + height + ", 프레임레이트: " + frameRate + " fps, 코덱: " + codec;
    }
    
    void showSubtitles() {
        System.out.println(fileName + "의 자막을 표시합니다.");
    }
}

// 이미지 파일
class ImageFile extends MediaFile {
    private int width;
    private int height;
    private String format; // JPEG, PNG 등
    
    ImageFile(String fileName, String filePath, long fileSize, int width, int height, String format) {
        super(fileName, filePath, fileSize, 0); // 이미지는 재생 시간 없음
        this.width = width;
        this.height = height;
        this.format = format;
    }
    
    @Override
    void play() {
        System.out.println("🖼️ 이미지 표시: " + fileName);
        System.out.println("   크기: " + width + "x" + height + ", 형식: " + format);
    }
    
    @Override
    String getMediaInfo() {
        return "크기: " + width + "x" + height + ", 형식: " + format;
    }
    
    void zoom() {
        System.out.println(fileName + "을(를) 확대합니다.");
    }
}

// 문서 파일
class DocumentFile extends MediaFile {
    private int pageCount;
    private String documentType; // PDF, DOCX 등
    
    DocumentFile(String fileName, String filePath, long fileSize, int pageCount, String documentType) {
        super(fileName, filePath, fileSize, 0); // 문서는 재생 시간 없음
        this.pageCount = pageCount;
        this.documentType = documentType;
    }
    
    @Override
    void play() {
        System.out.println("📄 문서 열기: " + fileName);
        System.out.println("   페이지 수: " + pageCount + ", 형식: " + documentType);
    }
    
    @Override
    String getMediaInfo() {
        return "페이지 수: " + pageCount + ", 형식: " + documentType;
    }
    
    void print() {
        System.out.println(fileName + "을(를) 인쇄합니다. (총 " + pageCount + "페이지)");
    }
}

public class Example10_MediaPlayer {
    public static void main(String[] args) {
        System.out.println("=== 실전 예제: 미디어 플레이어 시스템 ===\n");
        
        // 다양한 타입의 미디어 파일을 부모 타입 배열에 저장 (업캐스팅)
        MediaFile[] mediaFiles = new MediaFile[6];
        mediaFiles[0] = new AudioFile("song1.mp3", "/music/song1.mp3", 5242880, 240, 
                                      "아이유", "Love Poem", 320);
        mediaFiles[1] = new VideoFile("movie1.mp4", "/video/movie1.mp4", 1048576000, 7200,
                                     1920, 1080, 30, "H.264");
        mediaFiles[2] = new ImageFile("photo1.jpg", "/images/photo1.jpg", 2097152,
                                     3840, 2160, "JPEG");
        mediaFiles[3] = new DocumentFile("document1.pdf", "/docs/document1.pdf", 5242880,
                                       150, "PDF");
        mediaFiles[4] = new AudioFile("song2.mp3", "/music/song2.mp3", 3145728, 180,
                                     "BTS", "Love Yourself", 256);
        mediaFiles[5] = new VideoFile("clip1.mp4", "/video/clip1.mp4", 52428800, 300,
                                    1280, 720, 60, "H.265");
        
        System.out.println("=== 모든 미디어 파일 정보 출력 ===");
        for (MediaFile media : mediaFiles) {
            media.printFileInfo();
            System.out.println();
        }
        
        System.out.println("=== 모든 미디어 재생 ===");
        for (MediaFile media : mediaFiles) {
            media.play();  // 다형성: 각 미디어 타입에 맞는 재생 방식
            System.out.println();
        }
        
        System.out.println("=== 전체 파일 통계 ===");
        long totalSize = 0;
        int totalDuration = 0;
        for (MediaFile media : mediaFiles) {
            totalSize += media.fileSize;
            totalDuration += media.duration;
        }
        System.out.println("총 파일 크기: " + formatTotalSize(totalSize));
        System.out.println("총 재생 시간: " + formatTotalDuration(totalDuration));
        
        System.out.println("\n=== 미디어 타입별 특수 기능 ===");
        for (MediaFile media : mediaFiles) {
            if (media instanceof AudioFile) {
                AudioFile audio = (AudioFile) media;
                audio.showLyrics();
            } else if (media instanceof VideoFile) {
                VideoFile video = (VideoFile) media;
                video.showSubtitles();
            } else if (media instanceof ImageFile) {
                ImageFile image = (ImageFile) media;
                image.zoom();
            } else if (media instanceof DocumentFile) {
                DocumentFile doc = (DocumentFile) media;
                doc.print();
            }
        }
        
        System.out.println("\n=== 특정 파일 검색 ===");
        String searchFileName = "song1.mp3";
        MediaFile found = findMediaFile(mediaFiles, searchFileName);
        if (found != null) {
            System.out.println("검색 결과:");
            found.printFileInfo();
        } else {
            System.out.println(searchFileName + "을(를) 찾을 수 없습니다.");
        }
        
        System.out.println("\n=== 오디오 파일만 필터링 ===");
        ArrayList<MediaFile> audioFiles = filterByType(mediaFiles, AudioFile.class);
        System.out.println("오디오 파일 목록:");
        for (MediaFile media : audioFiles) {
            media.printFileInfo();
            System.out.println();
        }
    }
    
    // 업캐스팅을 활용한 공통 메서드: 파일명으로 검색
    static MediaFile findMediaFile(MediaFile[] mediaFiles, String fileName) {
        for (MediaFile media : mediaFiles) {
            if (media.getFileName().equals(fileName)) {
                return media;
            }
        }
        return null;
    }
    
    // 업캐스팅을 활용한 공통 메서드: 타입별 필터링
    static <T extends MediaFile> ArrayList<MediaFile> filterByType(MediaFile[] mediaFiles, Class<T> type) {
        ArrayList<MediaFile> result = new ArrayList<>();
        for (MediaFile media : mediaFiles) {
            if (type.isInstance(media)) {
                result.add(media);
            }
        }
        return result;
    }
    
    // 파일 크기 포맷팅 헬퍼 메서드
    static String formatTotalSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.2f KB", bytes / 1024.0);
        if (bytes < 1024 * 1024 * 1024) return String.format("%.2f MB", bytes / (1024.0 * 1024.0));
        return String.format("%.2f GB", bytes / (1024.0 * 1024.0 * 1024.0));
    }
    
    // 재생 시간 포맷팅 헬퍼 메서드
    static String formatTotalDuration(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        if (hours > 0) {
            return String.format("%d시간 %d분 %d초", hours, minutes, secs);
        }
        return String.format("%d분 %d초", minutes, secs);
    }
}
