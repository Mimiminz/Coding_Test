
#1859 백만 장자 프로젝트

T = int(input())

for i in range(1, T+1):
    N = int(input())
    value = list(map(int, input().split()))
    print("#%d" %i, end=' ')
    maxNum = value[-1]
    sumValue = 0
    buyValue = 0
    num = 0
    for j in range(len(value)-2, -1, -1):
        if value[j] > maxNum:
            sumValue += maxNum*num + buyValue
            maxNum = value[j]
            num = 0
            buyValue = 0
        else:
            num += 1
            buyValue -= value[j]
    sumValue += maxNum*num + buyValue
    print(sumValue)


