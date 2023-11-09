#7576 python

from collections import deque

def bfs(graph, visited):
    while que:
        x, y = que.popleft()
        plus = visited[y][x] + 1
        if y > 0 and graph[y-1][x] == 0 and visited[y-1][x] == 0:
            #print("위")
            visited[y-1][x] = plus
            graph[y-1][x] = 1
            que.append([x, y-1])
        if x > 0 and graph[y][x-1] == 0 and visited[y][x-1] == 0:
            #print("왼쪽")
            visited[y][x-1] = plus
            graph[y][x-1] = 1
            que.append([x-1, y])
        if y < N-1 and graph[y+1][x] == 0 and visited[y+1][x] == 0:
            #print("아래")
            visited[y+1][x] = plus
            graph[y+1][x] = 1
            que.append([x, y+1])
        if x < M-1 and graph[y][x+1] == 0 and visited[y][x+1] == 0:
            #print("오른쪽")
            visited[y][x+1] = plus
            graph[y][x+1] = 1
            que.append([x+1, y])


M, N = map(int, input().split())
graph = [[] for _ in range(N)]
visited = [[0 for _ in range(M)] for _ in range(N)]
day = 0

que = deque()

for i in range(N):
    graph[i] = list(map(int, input().split()))

if any(0 in l for l in graph):
    for i in range(N):
        for j in range(M):
            if graph[i][j] == 1:
                que.append([j,i])
    bfs(graph, visited)
    if any(0 in l for l in graph):   
        print(-1)
    else:
        print(max(map(max,visited)))
else:
    print(0)