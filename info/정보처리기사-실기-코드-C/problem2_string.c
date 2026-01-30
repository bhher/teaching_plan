#include <stdio.h>
#include <string.h>
#include <ctype.h>

// 문자열을 역순으로 만드는 함수
void reverseString(char* str) {
    int len = strlen(str);
    for (int i = 0; i < len / 2; i++) {
        char temp = str[i];
        str[i] = str[len - 1 - i];
        str[len - 1 - i] = temp;
    }
}

int main() {
    char text[] = "Hello World Java Programming";
    char words[10][50];  // 최대 10개 단어, 각 단어 최대 50자
    int wordCount = 0;
    
    // 1. 공백 기준으로 단어 분리
    char* token = strtok(text, " ");
    while (token != NULL) {
        strcpy(words[wordCount], token);
        wordCount++;
        token = strtok(NULL, " ");
    }
    
    printf("단어 배열: [");
    for (int i = 0; i < wordCount; i++) {
        printf("%s", words[i]);
        if (i < wordCount - 1) printf(", ");
    }
    printf("]\n");
    
    // 2. 각 단어의 길이 계산
    int lengths[10];
    for (int i = 0; i < wordCount; i++) {
        lengths[i] = strlen(words[i]);
    }
    printf("각 단어의 길이: [");
    for (int i = 0; i < wordCount; i++) {
        printf("%d", lengths[i]);
        if (i < wordCount - 1) printf(", ");
    }
    printf("]\n");
    
    // 3. 대문자로 시작하는 단어 필터링
    printf("대문자로 시작하는 단어: [");
    int first = 1;
    for (int i = 0; i < wordCount; i++) {
        if (isupper(words[i][0])) {
            if (!first) printf(", ");
            printf("%s", words[i]);
            first = 0;
        }
    }
    printf("]\n");
    
    // 4. 모든 단어를 역순으로 출력
    printf("역순 문자열: ");
    for (int i = 0; i < wordCount; i++) {
        char temp[50];
        strcpy(temp, words[i]);
        reverseString(temp);
        printf("%s", temp);
        if (i < wordCount - 1) printf(" ");
    }
    printf("\n");
    
    return 0;
}
