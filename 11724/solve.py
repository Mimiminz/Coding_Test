#11724.python

from collections import deque

def bfs(graph, visited):
    while que:
        v = que.popleft()
        for i in graph[v]:
            if visited[i] == 0:
                visited[i] = 1
                que.append(i)

N, M = map(int, input().split())
graph = [[] for _ in range(N+1)]
visited = [0 for i in range(N+1)]
visited[0] = 1
count = 0
que = deque()

for _ in range(M):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

for i in range(1, N+1):
    if visited[i] == 0:
        que.append(i)
        bfs(graph, visited)
        count += 1
print(count)
