
# 첫번째로 풀어본 SWEA문제. 놀랍겠지만 이걸 5번이나 틀렸다. 왜냐고? 저 아래에 주석처리해 놓은 테스트 케이스를 입력 받는 인풋때문에....
# 예시 입력창에 있길래 당연히 테스트케이스를 받는 건 줄 알았다. 문제에는 없었지만 예시가 그렇게 돼있어서 원래 그런 건 줄 알았지....

# 1926 간단한 369 게임

# T = int(input())
# for test_case in range(1, T + 1):

number = int(input())
for i in range(1, number+1):
    strn = str(i)
    check = strn.count('3') + strn.count('6') + strn.count('9')
    if check != 0:
        print('-'*check, end='')
    else:
        print(strn, end='')