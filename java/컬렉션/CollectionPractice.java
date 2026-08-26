import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 실전: 학원 수강 신청 (List + Set + Map)
 *
 * ArrayList  — 강좌 목록(순서)
 * HashMap    — 강좌 → 잔여 좌석 / 학생ID → 강좌
 * HashSet    — 이미 신청한 학생ID (중복 신청 방지)
 */
public class CollectionPractice {

    static ArrayList<String> courses = new ArrayList<>();
    static HashMap<String, Integer> seats = new HashMap<>();
    static HashSet<String> enrolled = new HashSet<>();
    static HashMap<String, String> whoTook = new HashMap<>();

    public static void main(String[] args) {
        // 1) 강좌 개설
        addCourse("Java기초", 2);
        addCourse("웹프론트", 3);
        addCourse("데이터베이스", 1);

        System.out.println("=== 개설 강좌 ===");
        printCourses();

        // 2) 수강 신청
        System.out.println("\n=== 수강 신청 ===");
        enroll("S001", "Java기초");
        enroll("S002", "Java기초");
        enroll("S001", "웹프론트");   // 이미 신청한 ID → 실패
        enroll("S003", "Java기초");   // 좌석 없음 → 실패
        enroll("S003", "데이터베이스");

        // 3) 현황
        System.out.println("\n=== 잔여 좌석 ===");
        printCourses();

        System.out.println("\n=== 수강 현황 ===");
        for (String id : whoTook.keySet()) {
            System.out.println(id + " : " + whoTook.get(id));
        }

        System.out.println("\n신청 완료 인원(Set): " + enrolled.size() + "명");
    }

    static void addCourse(String name, int seatCount) {
        courses.add(name);
        seats.put(name, seatCount);
    }

    static void printCourses() {
        for (String c : courses) {
            System.out.println(c + " (잔여 " + seats.get(c) + "석)");
        }
    }

    /** 수강 신청: Set으로 중복 ID 차단, Map으로 좌석 관리 */
    static void enroll(String studentId, String course) {
        if (!seats.containsKey(course)) {
            System.out.println(studentId + " → " + course + " 신청 실패 (없는 강좌)");
            return;
        }
        if (enrolled.contains(studentId)) {
            System.out.println(studentId + " → " + course + " 신청 실패 (이미 수강 신청한 ID)");
            return;
        }
        int left = seats.get(course);
        if (left <= 0) {
            System.out.println(studentId + " → " + course + " 신청 실패 (좌석 없음)");
            return;
        }

        seats.put(course, left - 1);
        enrolled.add(studentId);
        whoTook.put(studentId, course);
        System.out.println(studentId + " → " + course + " 신청 성공");
    }
}
