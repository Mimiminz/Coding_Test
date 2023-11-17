
# 1209 문제해결 기본 2일차 - Sum

for _ in range(10):
    t = int(input())
    ColSum = [0]*100
    rl = 0
    lr = 0
    result = 0
    for i in range(100):
        rowList = list(map(int, input().split()))
        result = max(result, sum(rowList))
        rl += rowList[i]
        lr += rowList[99-i]
        for j in range(100):
            ColSum[j] += rowList[j]

    result = max(result, max(ColSum), rl, lr)
    print("#%d"%t, result)