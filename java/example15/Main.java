/**
 * Main 클래스
 * 학생 관리 시스템의 진입점
 */
import model.Student;
import service.StudentService;
import repository.StudentRepository;
import controller.StudentController;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== 학생 관리 시스템 시작 ===\n");
        
        // 의존성 주입
        StudentRepository repository = new StudentRepository();
        StudentService service = new StudentService();
        StudentController controller = new StudentController(service, repository);
        
        // 프로그램 실행
        controller.run();
    }
}

