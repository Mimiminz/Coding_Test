import enum
import sys

input = sys.stdin.readline

N, M = map(int,input().split())
check =  []
truth = set(map(int, input().split()[1:]))
for i in range(M):
    check.append(set(map(int, input().split()[1:])))
    
cnt = [False] * M

for _ in range(M):
    for i, a in enumerate(check):
        if (a & truth):
            cnt[i] = True
            truth |= a

print(cnt.count(False))