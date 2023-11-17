
# 1240 문제해결 응용 1일차 - 단순 2진 암호코드

T = int(input())
dic = { '0001101':0, '0011001':1, '0010011':2, '0111101':3, '0100011':4, '0110001':5, '0101111':6, '0111011':7, '0110111':8, '0001011':9}

for t in range(1, T+1):
    N, M = map(int,input().split())
    for _ in range(N):
        value = input()
        if '1' in value:
            index = value.rindex('1')
            pw = value[index-55:index+1]

    odd = 0
    even = 0
    sumPW = 0
    for number in range(8):
        pwNum = dic[pw[number*7: number*7+7]]
        if number % 2 != 0:
            even += pwNum
        else:
            odd += pwNum
        sumPW += pwNum

    check = odd*3 + even
    if check % 10 == 0:
        print("#%d"%t, sumPW)
    else:
        print("#%d 0"%t)