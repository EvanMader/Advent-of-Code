#include <stdio.h>

int main() {
    long long a = 265601188299675;
    long long b = 0;
    long long c = 0;

    for (int i = 0; i < 16; i++) {
        b = a % 8;
        b = b ^ 7;
        c = a >> b;
        a = a / 8;
        b = b ^ 7;
        b = b ^ c;
        printf("%lld", b%8);
    }
}