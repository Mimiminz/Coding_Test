import sys
input = sys.stdin.readline
from itertools import combinations, permutations

a = int(input())
b = int(input())
check = [2, 3, 5, 7, 11, 13, 17] # 소수
c = [ i for i in range(1, 19)] 

pera = a/100.0
perb = b/100.0
sa = sb = 0

# 확률 구하는 공식
# p(k) = 18Ck * P**k * (1-p)**18-k 
for i in range(len(check)):
    # nC2 조합 n!/((n-r)! * r!)
    combi = len(list(combinations(c, check[i]))) # 18Ck
    sa += combi * pow(pera, check[i]) * pow(1.0-pera, 18-check[i])
    sb += combi * pow(perb, check[i]) * pow(1.0-perb, 18-check[i])

print(sa+sb - sa*sb) # 두 팀이 동시에 소수 점수를 낼 확률을 빼주기