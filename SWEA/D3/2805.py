
# 2805 농작물 수확하기

T = int(input())

for t in range(1, T+1):
    N = int(input())
    farm = [list(map(int, input())) for _ in range(N)]
    total = 0
    mid = N//2
    for i in range(1, mid+1):
        for num in range(i, N-i):
            total += farm[mid+i][num]
            total += farm[mid-i][num]
    total += sum(farm[mid])
    print("#%d"%t, total)