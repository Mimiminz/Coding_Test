def solution(alp, cop, problems):
    answer = 0
    maxalp = max(p[0] for p in problems)
    maxcop = max(p[1] for p in problems)
    
    dp = [[float('inf')] * (maxcop+1) for _ in range(maxalp+1)]
    
    alp = min(alp, maxalp) 
    cop = min(cop, maxcop)
    
    dp[alp][cop] = 0
    
    for a in range(alp, maxalp+1):
        for c in range(cop, maxcop+1):
            if a < maxalp:
                dp[a+1][c] = min(dp[a+1][c], dp[a][c] + 1)
            if c < maxcop:
                dp[a][c+1] = min(dp[a][c+1], dp[a][c] + 1)
                
            for p in problems:
                if a >= p[0] and c >= p[1]:
                    newalp = min(a+p[2], maxalp)
                    newcop = min(c+p[3], maxcop)
                    dp[newalp][newcop] = min(dp[newalp][newcop], dp[a][c] + p[4])
    
    answer = dp[maxalp][maxcop]
    return answer