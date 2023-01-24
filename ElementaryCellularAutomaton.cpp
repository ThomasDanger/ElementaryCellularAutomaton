#include <iostream>

unsigned int calcNextRow(unsigned int, unsigned int);
void drawRow(unsigned int);

int main() {
    unsigned int rule = 256;
    unsigned int numGens = 0;


    while(rule>255) {
        std::cout << "Enter rule (0-255): " << std::endl;
        std::cin >> rule;
    }

    while(numGens <= 0){
        std::cout << "Enter num generations: ";
        std::cin >> numGens;
    }

    unsigned int cur = 1<<16;

    for(int i = 0; i <= numGens; i++){
        drawRow(cur);
        cur = calcNextRow(cur, rule);
    }

    return 0;
}

unsigned int calcNextRow(unsigned int prev, unsigned int rule){
    unsigned int cur = 0;

    if((rule&(1<<(((prev&(3<<30))>>30) + ((prev&1)<<2)))) !=0){
        cur++;
    }

    for(int i = 0; i < 31; i++){
        cur=cur<<1;
        if((rule&(1<<((prev&(7<<29))>>29)))!=0)
            cur++;
        prev=(prev<<1)+((prev&(1<<31))>>31);
    }

    return cur;
}

void drawRow(unsigned int row){
    for(int i = 31; i >=0; i--){
        if((row&(1<<i)) == 1<<i){
            std::cout << "$";
        }
        else{
            std::cout << ".";
        }
    }
    std::cout << "\n";
}
