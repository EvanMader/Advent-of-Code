#include <stdio.h>
#include <math.h>

int main() {
    long long a = 0;
    long long b = 0;
    long long c = 0;
    long long saved = 0;
    int answer[] = {2,4,1,7,7,5,1,7,0,3,4,1,5,5,3,0};

    for (int result = 15; result >= 0; result --) {
        for (int i = result; i < 16; i++) {
            b = a % 8;
            b = b ^ 7;
            c = a >> b;
            a = a / 8;
            b = b ^ 7;
            b = b ^ c;
            if (b%8 == answer[i]) continue;
            else {
                saved++;
                i = result-1;
                a = saved;
            }
        }
        printf("%lld\n", saved);
        saved *= 8;
        a = saved;
    }

    printf("%lld", a);
    return 0;
}