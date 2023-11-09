N, M = map(int, input().split())
height = list(map(int, input().split()))
fst = 1
lst = max(height)
while fst <= lst:
   mid = (fst + lst) // 2
   length = 0
   for i in height:
      if i >= mid:
         length += i - mid
   if length >= M:
      fst = mid + 1
   else:
      lst = mid - 1
print(lst)