#include <iostream>

int calcNextRow(int, int);
void drawRow(int);

int main() {
    int rule = -1;
    int numGens = -1;

    while(rule < 0 || rule>255) {
        std::cout << "Enter rule (0-255): " << std::endl;
        std::cin >> rule;
    }

    while(numGens <= 0){
        std::cout << "Enter num generations: ";
        std::cin >> numGens;
    }

    int cur = 1<<16;

    for(int i = 0; i <= numGens; i++){
        drawRow(cur);
        cur = calcNextRow(cur, rule);
    }

    return 0;
}

int calcNextRow(int prev, int rule){
    int cur = 0;
    for(int i = 0; i< 32; i++){
        if((rule&(1<<(prev&7)))!=0)
            cur++;
        cur=cur<<1;
        prev=prev>>1;
    }
    return cur>>1;
}

void drawRow(int row){
    for(int i = 0; i < 32; i++){
        if((row&1) == 1)
            std::cout << "$";
        else
            std::cout << ".";
        row=row>>1;
    }
    std::cout << "\n";
}
