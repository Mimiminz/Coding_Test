#9663
#원래 이렇게 오래 걸리는 게 맞는 것인가....의문임 이게 최선.?

def check(y, chess):
    for i in range(len(chess)):
        if y == chess[i]:
            return 0
        elif abs(i-len(chess)) == abs(chess[i]-y):
            return 0
        
    return 1

def dfs(N, chess):
    global count
    if len(chess) == N:
        count += 1
        return 1
    for i in range(N):
        if check(i, chess) == 1:
            chess.append(i)
            dfs(N, chess)
            chess.pop()

    return 0

N = int(input())
chess = []
count = 0

dfs(N, chess)
print(count)
