
# 1289 원재의 메모리 복구하기

T = int(input())

for t in range(1, T+1):
    Bit = list(map(int, input()))
    current = [0]*len(Bit)
    count = 0
    for i in range(len(Bit)):
        dc = current[i]
        if count % 2 != 0:
            dc = (dc+1) % 2
        if Bit[i] != dc:
            count += 1
    print("#%d"%t, count)

