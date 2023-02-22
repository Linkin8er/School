def joseph(n,k):
    if n== 1:
        return 0
    else:
        return (joseph(n-1, k) +k)% n

def fac(n):
    if n== 1:
        return 1
    else:
        return (fac(n-1))*n

def main():
    print(joseph(16,2)+1)

main()