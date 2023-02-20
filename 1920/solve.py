N = int(input())
A = list(map(int, input().split()))
M = int(input())
check = list(map(int, input().split()))
A.sort()

for num in check:
   first = 0
   last = N-1
   exist = False
   while first <= last:
      mid = (first+last) // 2
      if num == A[mid]:
         exist = True
         print(1)
         break
      elif num > A[mid]:
         first = mid + 1
      else:
         last = mid - 1
   if exist == False:
      print(0)