#include <stdio.h>
#include <math.h>

int main() {
    long long a = 0;
    long long b = 0;
    long long c = 0;
    long long saved = 0;
    int answer[] = {2,4,1,7,7,5,1,7,0,3,4,1,5,5,3,0};

    while (1) {
        int i = 7;
        for (; i < 16; i++) {
            b = a % 8;
            b = b ^ 7;
            c = a >> b;
            a = a / 8;
            b = b ^ 7;
            b = b ^ c;
            if (b%8 != answer[i]) break;
        }
        if (a == 0 && i == 16 && b % 8 == answer[15]) {
            printf("%lld", saved);
            break;
        }
        saved++;
        a = saved;
        b = 0;
        c = 0;
    }

    return 0;
}