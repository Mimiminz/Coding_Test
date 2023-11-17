
# 1873 - 상호의 배틀필드

T = int(input())

for t in range(1, T+1):
    H, W = map(int, input().split())
    table = [list(input()) for _ in range(H)]
    y, x = 0, 0
    dy, dx = 0, 0
    for row in range(H):
        if '>' in table[row] or '<' in table[row] or '^' in table[row] or 'v' in table[row]:
            y = row
            if '>' in table[row]:
                x = table[row].index('>')
                dx = 1
            if '<' in table[row]:
                x = table[row].index('<')
                dx = -1
            if '^' in table[row]:
                x = table[row].index('^')
                dy = -1
            if 'v' in table[row]:
                x = table[row].index('v')
                dy = 1

    N = int(input())
    cmdStr = input()

    for cmd in cmdStr:
        if cmd == 'U':
            if y - 1 >= 0 and table[y-1][x] == '.':
                table[y][x] = '.'
                y -= 1
            table[y][x] = '^'
            dy, dx = -1, 0
        elif cmd == 'D':
            if y + 1 < H and table[y+1][x] == '.':
                table[y][x] = '.'
                y += 1
            table[y][x] = 'v'
            dy, dx = 1, 0
        elif cmd == 'L':
            if x - 1 >= 0 and table[y][x-1] == '.':
                table[y][x] = '.'
                x -= 1
            table[y][x] = '<'
            dy, dx = 0, -1
        elif cmd == 'R':
            if x + 1 < W and table[y][x+1] == '.':
                table[y][x] = '.'
                x += 1
            table[y][x] = '>'
            dy, dx = 0, 1
        elif cmd == 'S':
            sy, sx = y, x
            while True:
                sy += dy
                sx += dx
                if sy < 0 or sy >= H or sx < 0 or sx >= W or table[sy][sx] == '#':
                    break
                if table[sy][sx] == '*':
                    table[sy][sx] = '.'
                    break

    print("#%d"%t, end = ' ')
    for j in range(H):
        print(''.join(table[j]))