#include <stdio.h>
#include <string.h>

typedef struct {
    int student_id;
    char name[50];
    int age;
    char department[50];
    int score;
} Student;

int main() {
    Student students[] = {
        {1, "김철수", 20, "컴퓨터공학", 85},
        {2, "이영희", 21, "전자공학", 92},
        {3, "박민수", 20, "컴퓨터공학", 78},
        {4, "최지영", 22, "전자공학", 88},
        {5, "정수진", 21, "컴퓨터공학", 95},
        {6, "한동욱", 20, "기계공학", 82},
        {7, "송미영", 22, "컴퓨터공학", 90},
        {8, "윤태호", 21, "전자공학", 87}
    };
    int student_count = 8;
    
    // 1. 컴퓨터공학과 학생들의 평균 점수
    int csSum = 0, csCount = 0;
    for (int i = 0; i < student_count; i++) {
        if (strcmp(students[i].department, "컴퓨터공학") == 0) {
            csSum += students[i].score;
            csCount++;
        }
    }
    printf("컴퓨터공학과 평균 점수: %.2f\n", (double)csSum / csCount);
    
    // 2. 각 학과별 학생 수와 평균 점수
    char departments[10][50];
    int deptCount = 0;
    
    // 고유한 학과 찾기
    for (int i = 0; i < student_count; i++) {
        int found = 0;
        for (int j = 0; j < deptCount; j++) {
            if (strcmp(students[i].department, departments[j]) == 0) {
                found = 1;
                break;
            }
        }
        if (!found) {
            strcpy(departments[deptCount++], students[i].department);
        }
    }
    
    printf("학과별 통계:\n");
    for (int i = 0; i < deptCount; i++) {
        int count = 0, sum = 0;
        for (int j = 0; j < student_count; j++) {
            if (strcmp(students[j].department, departments[i]) == 0) {
                count++;
                sum += students[j].score;
            }
        }
        printf("  %s: 학생수 %d명, 평균 %.2f점\n", 
               departments[i], count, (double)sum / count);
    }
    
    // 3. 점수가 85점 이상인 학생
    printf("85점 이상 학생:\n");
    for (int i = 0; i < student_count; i++) {
        if (students[i].score >= 85) {
            printf("  %s: %d점\n", students[i].name, students[i].score);
        }
    }
    
    // 4. 나이가 21세 이상인 학생 중 점수가 가장 높은 학생
    Student* maxStudent = NULL;
    int maxScore = -1;
    for (int i = 0; i < student_count; i++) {
        if (students[i].age >= 21 && students[i].score > maxScore) {
            maxScore = students[i].score;
            maxStudent = &students[i];
        }
    }
    if (maxStudent != NULL) {
        printf("21세 이상 최고점 학생: %s (%d점)\n", 
               maxStudent->name, maxStudent->score);
    }
    
    return 0;
}
