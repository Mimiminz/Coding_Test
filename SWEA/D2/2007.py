
# 2007 패턴 마디의 길이
# 바보 같이 한 번 틀린 문제. 테스트 용 print 구문 안 지우고 제출 했다.

T = int(input())

for i in range(1, 1+T):
    longStr = input()
    repeat = longStr[0]
    for j in range(1, len(longStr)):
        if repeat == longStr[j:j+len(repeat)]:
            break
        else:
            repeat += longStr[j]
    print("#%d"%i, len(repeat))