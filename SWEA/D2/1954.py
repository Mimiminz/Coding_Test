
# 1954 달팽이 숫자

T = int(input())
for t in range(1, 1+T):
    N = int(input())
    start = 1
    answer = [[0 for _ in range(N)] for _ in range(N)]
    x, y = 0, 0
    answer[0][0] = 1
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    current = 0
    for i in range(2, N**2+1):
        if 0 <= x + dx[current] < N and 0 <= y + dy[current] < N:
            if answer[y+dy[current]][x+dx[current]] == 0:
                answer[y+dy[current]][x+dx[current]] = i
            else:
                current += 1
                current %= 4
                answer[y+dy[current]][x+dx[current]] = i
        else:
            current += 1
            current %= 4
            answer[y+dy[current]][x+dx[current]] = i
        x += dx[current]
        y += dy[current]
    print("#%d"%t)
    for x in range(len(answer)):
        for p in answer[x]:
            print(p, end=' ')
        print()