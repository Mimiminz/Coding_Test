import sys
from collections import deque

input = sys.stdin.readline
deq = deque()

M, N = map(int,input().split())
count = 0
number = map(int, input().split())
for i in range(M):
    deq.append(i+1)
for i in number:
    while True:
        if i == deq[0]:
            deq.popleft()
            break
        else:
            count += 1
            if deq.index(i) > len(deq)/2:
                deq.appendleft(deq.pop())
            else:
                deq.append(deq.popleft())
print(count)
