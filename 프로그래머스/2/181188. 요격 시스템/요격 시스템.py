def solution(targets):
    answer = 0
    targets.sort(key=lambda x: (x[1], x[1]))
    num = 0
    check = 0
    while num < len(targets):
        answer += 1
        last = targets[num][1]
        for i in range(num+1, len(targets)):
            if last <= targets[i][0]:
                num = i
                check = 1
                break
        if check == 0:
            break
        check = 0
    return answer