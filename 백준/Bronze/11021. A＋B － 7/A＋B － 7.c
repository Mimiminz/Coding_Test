#include <stdio.h>

int main() {
    int t; // 테스트 케이스 수
    scanf("%d", &t); // 테스트 케이스 입력

    for (int i = 1; i <= t; i++) {
        int A, B; // 두 정수를 저장할 변수
        scanf("%d %d", &A, &B); // 두 정수 입력
        printf("Case #%d: %d\n", i, A + B); // 결과 출력
    }

    return 0;
}
