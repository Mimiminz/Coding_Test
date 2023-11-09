
# 1206 문제해결 기본 1일차 - View

# 프린트 문을 꼭 잘 확인하고 제출하자......

for t in range(1, 11):
    num = int(input())
    apartment = list(map(int, input().split()))
    value = 0
    for apart in range(2, len(apartment)):
        if apart+2 >= len(apartment):
            break

        left1 = apartment[apart-1]
        left2 = apartment[apart-2]
        current = apartment[apart]
        right1 = apartment[apart+1]
        right2 = apartment[apart+2]
        if current > left1 and current > right1 and current > left2 and current > right2:
            maxValue = max(left1, left2, right1, right2)
            value += current - maxValue
    print("#%d"%t, value)