# 2806 N-Queen

def isCheck(current):
    for r in range(current):
        if rowQ[current] == rowQ[r] or abs(rowQ[current] - rowQ[r]) == abs(current - r):
            return False
    return True


def checkQueen(current):
    global result
    if current == N:
        result += 1
        return

    for i in range(N):
        rowQ[current] = i
        if isCheck(current):
            checkQueen(current + 1)


T = int(input())

for t in range(1, T + 1):
    N = int(input())
    result = 0
    rowQ = [0] * N
    if N != 0:
        checkQueen(0)

    print("#%d" % t, result)
