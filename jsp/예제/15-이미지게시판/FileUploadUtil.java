package jsp.imageboard;

import jakarta.servlet.http.Part;  // Tomcat 10.1+ 호환 (javax.servlet → jakarta.servlet)
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 파일 업로드 유틸리티 클래스
 */
public class FileUploadUtil {
    // 업로드 디렉토리 경로 (웹 애플리케이션 루트 기준)
    private static final String UPLOAD_DIR = "upload/images";
    
    // 허용된 이미지 확장자
    private static final String[] ALLOWED_EXTENSIONS = {".jpg", ".jpeg", ".png", ".gif", ".bmp", ".webp"};
    
    // 최대 파일 크기 (10MB)
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;
    
    /**
     * 파일 업로드 처리
     * @param part Part 객체 (Servlet 3.0+)
     * @param uploadPath 실제 업로드 경로
     * @return 업로드된 파일명 (실패 시 null)
     */
    public static String uploadFile(Part part, String uploadPath) {
        if (part == null || part.getSize() == 0) {
            return null;
        }
        
        try {
            String originalFileName = getFileName(part);
            if (originalFileName == null || originalFileName.isEmpty()) {
                return null;
            }
            
            // 확장자 확인
            String extension = getFileExtension(originalFileName);
            if (!isAllowedExtension(extension)) {
                throw new IllegalArgumentException("허용되지 않은 파일 형식입니다: " + extension);
            }
            
            // 파일 크기 확인
            if (part.getSize() > MAX_FILE_SIZE) {
                throw new IllegalArgumentException("파일 크기가 너무 큽니다. (최대 10MB)");
            }
            
            // 업로드 디렉토리 생성
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            
            // 고유한 파일명 생성
            String savedFileName = generateUniqueFileName(originalFileName);
            String filePath = uploadPath + File.separator + savedFileName;
            
            // 파일 저장
            try (InputStream input = part.getInputStream();
                 OutputStream output = new FileOutputStream(filePath)) {
                
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            }
            
            return savedFileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Part에서 파일명 추출
     * @param part Part 객체
     * @return 파일명
     */
    private static String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        if (contentDisposition == null) {
            return null;
        }
        
        String[] tokens = contentDisposition.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                String fileName = token.substring(token.indexOf("=") + 2, token.length() - 1);
                // 경로에서 파일명만 추출
                return fileName.substring(fileName.lastIndexOf("\\") + 1);
            }
        }
        return null;
    }
    
    /**
     * 파일 확장자 추출
     * @param fileName 파일명
     * @return 확장자 (소문자)
     */
    private static String getFileExtension(String fileName) {
        int lastDot = fileName.lastIndexOf(".");
        if (lastDot == -1) {
            return "";
        }
        return fileName.substring(lastDot).toLowerCase();
    }
    
    /**
     * 허용된 확장자인지 확인
     * @param extension 확장자
     * @return 허용 여부
     */
    private static boolean isAllowedExtension(String extension) {
        for (String allowed : ALLOWED_EXTENSIONS) {
            if (allowed.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 고유한 파일명 생성
     * @param originalFileName 원본 파일명
     * @return 고유한 파일명
     */
    private static String generateUniqueFileName(String originalFileName) {
        String extension = getFileExtension(originalFileName);
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        return timestamp + "_" + uuid + extension;
    }
    
    /**
     * 파일 삭제
     * @param uploadPath 업로드 경로
     * @param fileName 파일명
     * @return 삭제 성공 여부
     */
    public static boolean deleteFile(String uploadPath, String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return false;
        }
        
        try {
            File file = new File(uploadPath + File.separator + fileName);
            if (file.exists()) {
                return file.delete();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 업로드 디렉토리 경로 가져오기
     * @return 업로드 디렉토리 경로
     */
    public static String getUploadDir() {
        return UPLOAD_DIR;
    }
}
