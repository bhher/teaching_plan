#include <stdio.h>
#include <string.h>

int main() {
    char names[][20] = {"김철수", "이영희", "박민수", "최지영", "정수진"};
    int scores[] = {85, 92, 78, 88, 95};
    int count = 5;
    
    // 1. 평균 점수 계산
    int sum = 0;
    for (int i = 0; i < count; i++) {
        sum += scores[i];
    }
    double average = (double)sum / count;
    printf("평균 점수: %.1f\n", average);
    
    // 2. 평균 이상 학생 출력
    printf("평균 이상 학생:\n");
    for (int i = 0; i < count; i++) {
        if (scores[i] >= average) {
            printf("  %s: %d점\n", names[i], scores[i]);
        }
    }
    
    // 3. 등급 부여 및 집계
    int gradeCount[4] = {0};  // A, B, C, D
    char grades[5];
    
    for (int i = 0; i < count; i++) {
        if (scores[i] >= 90) {
            grades[i] = 'A';
            gradeCount[0]++;
        } else if (scores[i] >= 80) {
            grades[i] = 'B';
            gradeCount[1]++;
        } else if (scores[i] >= 70) {
            grades[i] = 'C';
            gradeCount[2]++;
        } else {
            grades[i] = 'D';
            gradeCount[3]++;
        }
    }
    
    // 4. 등급별 학생 수 출력
    printf("\n등급별 학생 수:\n");
    printf("  A등급: %d명\n", gradeCount[0]);
    printf("  B등급: %d명\n", gradeCount[1]);
    printf("  C등급: %d명\n", gradeCount[2]);
    printf("  D등급: %d명\n", gradeCount[3]);
    
    return 0;
}
