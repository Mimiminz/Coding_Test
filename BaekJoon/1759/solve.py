#1759 python
#이렇게 if문 때려넣는 건 아닌 것 같긴한데.. 더 생각이 안나서 그냥 이걸로..ㅎ 나중에 맑은 정신으로 다시 풀어봄

vowel = ['a', 'e', 'i', 'o', 'u']

def findpw(c, v, L, password):
    if len(password) == L:
        if v >= 1 and c >= 2:
            ps = ''.join(password)
            print(ps)
        return 0
    for i in pwps:
        if len(password) == 0:
            password.append(i)
            if i in vowel:
                findpw(c, v+1, L, password)
            else:
                findpw(c+1, v, L, password)
            password.pop()
        else:
            if i in vowel and ord(i) > ord(password[-1]):
                password.append(i)
                findpw(c, v+1, L, password)
                password.pop()
            elif not i in vowel and ord(i) > ord(password[-1]):
                password.append(i)
                findpw(c+1, v, L, password)
                password.pop()

L, C = map(int, input().split())
pwps = list(input().split())
pwps.sort()
password = []

findpw(0, 0, L, password)