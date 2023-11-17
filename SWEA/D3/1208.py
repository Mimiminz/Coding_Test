
# 1208 문제해결 기본 1일차 - Flatten

for t in range(1, 11):
    count = int(input())
    dumplist = list(map(int, input().split()))
    for i in range(count):
        maxD = max(dumplist)
        minD = min(dumplist)
        if maxD - minD <= 1:
            print('break')
            break
        maxIndex = dumplist.index(maxD)
        minIndex = dumplist.index(minD)
        dumplist[maxIndex] -= 1
        dumplist[minIndex] += 1

    maxD = max(dumplist)
    minD = min(dumplist)
    print("#%d"%t, maxD - minD)
